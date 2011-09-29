package org.openmrs.module.hospitalcore.impl;

import java.util.List;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.LaboratoryCoreService;
import org.openmrs.module.hospitalcore.db.LaboratoryCoreDAO;
import org.openmrs.module.hospitalcore.model.Lab;

public class LaboratoryCoreServiceImpl extends BaseOpenmrsService implements
		LaboratoryCoreService {	

	protected LaboratoryCoreDAO dao;

	public void setDao(LaboratoryCoreDAO dao) {
		this.dao = dao;
	}

	//
	// RADIOLOGY DEPARTMENT
	//
	public List<Lab> getAllDepartments() {
		return dao.getAllDepartments();
	}
}
