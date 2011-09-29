package org.openmrs.module.hospitalcore.db.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.openmrs.module.hospitalcore.db.RadiologyCoreDAO;
import org.openmrs.module.hospitalcore.model.RadiologyDepartment;

public class HibernateRadiologyCoreDAO implements RadiologyCoreDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<RadiologyDepartment> getAllRadiologyDepartments() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyDepartment.class);
		return criteria.list();
	}
}