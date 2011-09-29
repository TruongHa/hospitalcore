package org.openmrs.module.hospitalcore;

import java.util.List;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.model.RadiologyDepartment;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RadiologyCoreService extends OpenmrsService {		
	
	/**
	 * Get all radiology departments
	 * @return
	 */
	public List<RadiologyDepartment> getAllRadiologyDepartments();
}
