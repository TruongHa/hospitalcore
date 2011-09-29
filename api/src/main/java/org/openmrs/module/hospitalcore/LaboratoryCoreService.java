package org.openmrs.module.hospitalcore;

import java.util.List;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.model.Lab;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LaboratoryCoreService extends OpenmrsService {		
	
	/**
	 * Get all departments
	 * @return
	 */
	public List<Lab> getAllDepartments();
}
