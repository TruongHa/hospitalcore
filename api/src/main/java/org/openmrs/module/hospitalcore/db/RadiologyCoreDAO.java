package org.openmrs.module.hospitalcore.db;

import java.util.List;

import org.openmrs.module.hospitalcore.model.RadiologyDepartment;

public interface RadiologyCoreDAO {
	
	/**
	 * Get all radiology departments
	 * @return
	 */
	public List<RadiologyDepartment> getAllRadiologyDepartments();
}
