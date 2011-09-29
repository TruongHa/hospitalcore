package org.openmrs.module.hospitalcore.db.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.db.PatientQueueDAO;
import org.openmrs.module.hospitalcore.model.OpdPatientQueue;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;

public class HibernatePatientQueueDAO implements PatientQueueDAO {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat formatterExt = new SimpleDateFormat("dd/MM/yyyy");
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
	
	public OpdPatientQueue saveOpdPatientQueue(OpdPatientQueue opdPatientQueue) throws DAOException {
		return (OpdPatientQueue) sessionFactory.getCurrentSession().merge(opdPatientQueue);
	}
	
	public OpdPatientQueue updateOpdPatientQueue(Integer id, String status) throws DAOException {
		OpdPatientQueue opdPatientQueue = getOpdPatientQueueById(id);
		opdPatientQueue.setStatus(status);
		return (OpdPatientQueue) sessionFactory.getCurrentSession().merge(opdPatientQueue);
	}
	
	public OpdPatientQueue getOpdPatientQueueById(Integer id) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OpdPatientQueue.class);
		criteria.add(Restrictions.eq("id", id));
		OpdPatientQueue opdPatientQueue = (OpdPatientQueue) criteria.uniqueResult();
		return opdPatientQueue;
	}
	
	public OpdPatientQueue getOpdPatientQueue(String patientIdentifier,Integer opdConceptId) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OpdPatientQueue.class, "queue")
				.createAlias("queue.opdConcept", "opdConcept");
		criteria.add(Restrictions.eq("queue.patientIdentifier", patientIdentifier));
		criteria.add(Restrictions.eq("opdConcept.conceptId", opdConceptId));
		String date = formatterExt.format(new Date());
		String startFromDate = date + " 00:00:00";
		String endFromDate = date + " 23:59:59";
		try {
			criteria.add(Restrictions.and(Restrictions.ge(
					"queue.createdOn", formatter.parse(startFromDate)), Restrictions.le(
					"queue.createdOn", formatter.parse(endFromDate))));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error convert date: "+ e.toString());
			e.printStackTrace();
		}
		criteria.addOrder(Order.desc("queue.createdOn"));
		
		List<OpdPatientQueue> list = criteria.list();
		return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
	}
	
	public void deleteOpdPatientQueue(OpdPatientQueue opdPatientQueue) throws DAOException {
		sessionFactory.getCurrentSession().delete(opdPatientQueue);
	}
	
	@SuppressWarnings("unchecked")
	public List<OpdPatientQueue> listOpdPatientQueue(String searchText ,  Integer conceptId,String status, int min, int max) throws DAOException{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OpdPatientQueue.class,"opdPatientQueue");
		if(!StringUtils.isBlank(searchText)){
	    	criteria.add(Restrictions.or(Restrictions.like("opdPatientQueue.patientIdentifier",  "%"+searchText+"%"),Restrictions.like("opdPatientQueue.patientName",  "%"+searchText+"%")));
		}
		if(conceptId != null && conceptId > 0){
			criteria.createAlias( "opdPatientQueue.opdConcept","opdConcept");
			criteria.add(Restrictions.eq("opdConcept.conceptId", conceptId));
		}
		if(!StringUtils.isBlank(status)){
			criteria.add(Restrictions.eq("opdPatientQueue.status", status));
		}
		//only get data if that's current date
		//we need this because maybe cron-job not work normal
		String date = formatterExt.format(new Date());
		String startFromDate = date + " 00:00:00";
		String endFromDate = date + " 23:59:59";
		try {
			criteria.add(Restrictions.and(Restrictions.ge(
					"opdPatientQueue.createdOn", formatter.parse(startFromDate)), Restrictions.le(
					"opdPatientQueue.createdOn", formatter.parse(endFromDate))));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error convert date: "+ e.toString());
			e.printStackTrace();
		}
		criteria.addOrder(Order.asc("opdPatientQueue.createdOn"));
		if(max > 0){
			criteria.setFirstResult(min).setMaxResults(max);
		}
		 List<OpdPatientQueue> list =  criteria.list();

		return list;
	}
	
	public Integer countOpdPatientQueue(String patientName , String searchType,Integer conceptId,String status) throws DAOException{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OpdPatientQueue.class,"opdPatientQueue");
		if(!StringUtils.isBlank(patientName)){
			criteria.add(Restrictions.like("opdPatientQueue.patientName",  "%"+patientName+"%"));
		}
		if(conceptId != null){
			criteria.createAlias("opdPatientQueue.opdConcept", "opdConcept");
			criteria.add(Restrictions.eq("opdConcept.conceptId", conceptId));
		}
		if(!StringUtils.isBlank(status)){
			criteria.add(Restrictions.eq("opdPatientQueue.status", status));
		}
		//only get data if that's current date
		//we need this because maybe cron-job not work normal
		String date = formatterExt.format(new Date());
		String startFromDate = date + " 00:00:00";
		String endFromDate = date + " 23:59:59";
		try {
			criteria.add(Restrictions.and(Restrictions.ge(
					"opdPatientQueue.createdOn", formatter.parse(startFromDate)), Restrictions.le(
					"opdPatientQueue.createdOn", formatter.parse(endFromDate))));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error convert date: "+ e.toString());
			e.printStackTrace();
		}
		Number rs =  (Number) criteria.setProjection( Projections.rowCount() ).uniqueResult();
		return rs != null ? rs.intValue() : 0;
	}
	
	//patient queue log
	public OpdPatientQueueLog saveOpdPatientQueueLog(OpdPatientQueueLog opdPatientQueueLog) throws DAOException {
		return (OpdPatientQueueLog) sessionFactory.getCurrentSession().merge(opdPatientQueueLog);
	}
	
	public OpdPatientQueueLog getOpdPatientQueueLogById(Integer id) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OpdPatientQueueLog.class);
		criteria.add(Restrictions.eq("id", id));
		OpdPatientQueueLog opdPatientQueueLog = (OpdPatientQueueLog) criteria.uniqueResult();
		return opdPatientQueueLog;
	}

	@SuppressWarnings("unchecked")
	public List<OpdPatientQueue> getAllPatientInQueue() throws DAOException {
		//for sure everything always get less than one date
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OpdPatientQueue.class,"opdPatientQueue");
		String date = formatterExt.format(new Date());
		String startFromDate = date + " 00:00:00";
		try {
			criteria.add(Restrictions.lt(
					"opdPatientQueue.createdOn", formatter.parse(startFromDate)));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error convert date: "+ e.toString());
			e.printStackTrace();
		}
		return criteria.list();
	}
	
}
