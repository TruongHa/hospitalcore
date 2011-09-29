package org.openmrs.module.hospitalcore.db;

import java.util.List;

import org.openmrs.module.hospitalcore.model.Lab;

public interface LaboratoryCoreDAO {
	
	/**
	 * Get all radiology departments
	 * @return
	 */
	public List<Lab> getAllDepartments();
}
