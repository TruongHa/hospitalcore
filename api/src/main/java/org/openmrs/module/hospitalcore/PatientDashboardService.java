package org.openmrs.module.hospitalcore;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.module.hospitalcore.model.Department;
import org.openmrs.module.hospitalcore.model.DepartmentConcept;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PatientDashboardService {
	
	public List<Concept> searchDiagnosis(String text) throws APIException;
	
	public List<Concept> searchProcedure(String text) throws APIException;
	
	public List<Order> getOrders(List<Concept> concepts,Patient patient, Location location, Date orderStartDate) throws APIException;
	
	public List<Concept> getAnswers(Concept labSet)  throws APIException;
	
	public List<Encounter> getEncounter(Patient p , Location loc, EncounterType encType, String date) throws APIException;
	
	public Set<Concept> listDiagnosisByOpd(Integer opdConcept) throws APIException;
	
	//Department
	public Department createDepartment(Department department) throws APIException;
	public void removeDepartment(Department department) throws APIException;
	public Department getDepartmentById(Integer id) throws APIException;
	public Department getDepartmentByWard(Integer wardId) throws APIException;
	public List<Department> listDepartment(Boolean retired) throws APIException;
	public Department getDepartmentByName(String name) throws APIException;
	//DepartmentConcept
	public DepartmentConcept createDepartmentConcept(DepartmentConcept departmentConcept) throws APIException;
	public DepartmentConcept getByDepartmentAndConcept(Integer departmentId, Integer conceptId) throws APIException;
	public DepartmentConcept getById(Integer id) throws APIException;
	public void removeDepartmentConcept(DepartmentConcept departmentConcept) throws APIException;
	public List<DepartmentConcept> listByDepartment(Integer departmentId, Integer typeConcept) throws APIException;
	public List<Concept> listByDepartmentByWard(Integer wardId, Integer typeConcept) throws APIException;
	
	
}
