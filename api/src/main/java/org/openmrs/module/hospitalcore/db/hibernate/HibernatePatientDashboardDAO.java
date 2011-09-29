package org.openmrs.module.hospitalcore.db.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.ConceptClass;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.db.PatientDashboardDAO;
import org.openmrs.module.hospitalcore.model.Department;
import org.openmrs.module.hospitalcore.model.DepartmentConcept;

public class HibernatePatientDashboardDAO implements PatientDashboardDAO{
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * Hibernate session factory
	 */
	private SessionFactory sessionFactory;
	
	/**
	 * Set session factory
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    public List<Order> getOrders(List<Concept> concepts,  Patient patient, Location location, Date orderStartDate) throws DAOException {
        Map<Integer,Integer> monthEOMMap = new HashMap<Integer, Integer>();
        
        String hql = "from Order as o where o.voided = 0 and ";
        
        if (concepts != null && concepts.size() > 0){
            hql += " o.concept in (:conceptIds) ";
            if ( (patient != null) || location != null || orderStartDate != null)
                hql += " and ";
        }
        
        if (patient != null){
            hql += " o.patient = (:patient)";
            if (location != null || orderStartDate != null)
                hql += " and ";
        }    
        if (location != null){
            hql += " o.encounter.location = :location";
            if (orderStartDate != null)
                hql += " and ";
        }
        if (orderStartDate != null){
            SimpleDateFormat sdf = Context.getDateFormat();

            //Date orderdate = orderStartDate;
            //Calendar cal = Calendar.getInstance();
            //cal.setTime(orderdate);
            //System.out.println("orderdate = " + cal.getTime());
            //cal.roll( Calendar.DATE, +1 );
            String startDate = sdf.format(orderStartDate)+" 00:00:00";
            String endDate = sdf.format(orderStartDate)+" 23:59:59";
            hql += " ((o.startDate is null and o.encounter.encounterDatetime BETWEEN '" +startDate+ "' AND '"+endDate+ "' ) OR (    o.startDate is not null and  o.startDate  BETWEEN '" +startDate+ "' AND '"+endDate+ "'  )  )";
           
        }
        
        hql += " order by o.startDate asc";
        //System.out.println("hql "+hql);
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        if (concepts != null && concepts.size() > 0){
            query.setParameterList("conceptIds", concepts);
        }    
        
        if (patient != null){
            query.setParameter("patient", patient);
        }
        if (location != null)
            query.setParameter("location", location);
        
        return (List<Order>) query.list();
    }


	@SuppressWarnings("unchecked")
	public List<Concept> searchConceptsByNameAndClass(String text,
			ConceptClass clazz) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Concept.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Expression.eq("retired", false));
		criteria.add(Restrictions.eq("conceptClass", clazz));
		if( StringUtils.isNotBlank(text))
		{
			criteria.createAlias("names", "names");
			criteria.add(Expression.like("names.name", text, MatchMode.ANYWHERE));
		}
		return criteria.list();
	}

	public List<Encounter> getEncounter(Patient patient, Location location,
			EncounterType encType, String date) throws DAOException {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Encounter.class);
		crit.add(Expression.eq("patient", patient));
		if (location != null && location.getLocationId() != null) {
			crit.add(Expression.eq("location", location));
		}
		if( StringUtils.isNotBlank(date) ){
			String startDate = "";
			String endDate = "";
			if( "recent".equalsIgnoreCase(date)){
				SimpleDateFormat formatter = Context.getDateFormat();
				Calendar cal = Calendar.getInstance();
				String today = Context.getDateFormat().format(cal.getTime());
				cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1 );
				date = formatter.format(cal.getTime());
				startDate = date + " 00:00:00";
				endDate = today + " 23:59:59";
	        }else {
				startDate = date + " 00:00:00";
				endDate = date + " 23:59:59";
	        }
			try {
				crit.add(Restrictions.and(Restrictions.ge("encounterDatetime", formatter.parseObject(startDate)),
						Restrictions.le("encounterDatetime", formatter.parseObject(endDate))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		crit.add(Expression.eq("voided", false));
		
		crit.add(Expression.eq("encounterType", encType));
		
		crit.addOrder(org.hibernate.criterion.Order.desc("encounterDatetime"));
		return crit.list();	
	}
	
	//Department
	public Department createDepartment(Department department) throws DAOException{
		return (Department) sessionFactory.getCurrentSession().merge(department); 
	}
	public void removeDepartment(Department department) throws DAOException{
		sessionFactory.getCurrentSession().delete(department);
	}
	public Department getDepartmentById(Integer id) throws DAOException{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Department.class, "department");
		criteria.add(Restrictions.eq("department.id", id));
		Department r = (Department)criteria.uniqueResult();
		return r;
	}
	public Department getDepartmentByName(String name) throws DAOException{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Department.class, "department");
		criteria.add(Restrictions.eq("department.name", name));
		criteria.setFirstResult(0).setMaxResults(1);
		List<Department> list = criteria.list();
		return CollectionUtils.isNotEmpty(list)?list.get(0) : null;
	}
	public Department getDepartmentByWard(Integer wardId) throws DAOException{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Department.class, "department");
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
		.createCriteria("department.wards", Criteria.LEFT_JOIN)
		.add(Restrictions.eq("id",wardId));
		criteria.setFirstResult(0).setMaxResults(1);
		List<Department> list = criteria.list();
		return CollectionUtils.isNotEmpty(list)?list.get(0) : null;
	}
	public List<Department> listDepartment(Boolean retired) throws DAOException{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Department.class, "department");
		if(retired != null){
			criteria.add(Restrictions.eq("department.retired", retired));
		}
		List<Department> list = criteria.list();
		return list;
	}
	//DepartmentConcept
	public DepartmentConcept createDepartmentConcept(DepartmentConcept departmentConcept) throws DAOException{
		return (DepartmentConcept) sessionFactory.getCurrentSession().merge(departmentConcept); 
	}
	public DepartmentConcept getByDepartmentAndConcept(Integer departmentId, Integer conceptId) throws DAOException{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DepartmentConcept.class, "departmentConcept");
		criteria.add(Restrictions.eq("departmentConcept.department.id", departmentId))
		.add(Restrictions.eq("departmentConcept.concept.id", conceptId));
		criteria.setFirstResult(0).setMaxResults(1);
		List<DepartmentConcept> list = criteria.list();
		return CollectionUtils.isNotEmpty(list)?list.get(0) : null;
	}
	public DepartmentConcept getById(Integer id) throws DAOException{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DepartmentConcept.class, "departmentConcept");
		criteria.add(Restrictions.eq("departmentConcept.id", id));
		criteria.setFirstResult(0).setMaxResults(1);
		DepartmentConcept r =(DepartmentConcept)criteria.uniqueResult();
		return r;
	}
	public void removeDepartmentConcept(DepartmentConcept departmentConcept) throws DAOException{
		sessionFactory.getCurrentSession().delete(departmentConcept); 
	}
	public List<DepartmentConcept> listByDepartment(Integer departmentId, Integer typeConcept) throws DAOException{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DepartmentConcept.class, "departmentConcept");
		criteria.add(Restrictions.eq("departmentConcept.department.id", departmentId))
		.add(Restrictions.eq("departmentConcept.typeConcept", typeConcept));
		List<DepartmentConcept> list = criteria.list();
		return list;
	}

	public List<Concept> listByDepartmentByWard(Integer wardId,
			Integer typeConcept) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DepartmentConcept.class, "departmentConcept")
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
		.createAlias("departmentConcept.department", "department")
		.add(Restrictions.eq("departmentConcept.typeConcept", typeConcept))
		.add(Restrictions.eq("department.retired", false));
		criteria.createCriteria("department.wards", Criteria.LEFT_JOIN)
		.add(Restrictions.eq("id",wardId));
		List<DepartmentConcept> list = criteria.list();
		if(CollectionUtils.isNotEmpty(list)){
			List<Concept> listConcept = new ArrayList<Concept>();
			for(DepartmentConcept dC : list){
				listConcept.add(dC.getConcept());
			}
			return listConcept;
		}
		return null;
	}
	
}
