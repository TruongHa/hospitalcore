package org.openmrs.module.hospitalcore.db;

import java.util.Date;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.ConceptClass;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.model.Department;
import org.openmrs.module.hospitalcore.model.DepartmentConcept;

public interface PatientDashboardDAO {
	public List<Order> getOrders(List<Concept> concepts, Patient patient, Location location, Date orderStartDate) throws DAOException;
	public List<Concept> searchConceptsByNameAndClass(String text, ConceptClass clazz) throws DAOException;
	public List<Encounter> getEncounter(Patient p , Location loc, EncounterType encType, String date) throws DAOException;
	
	//Department
	public Department createDepartment(Department department) throws DAOException;
	public void removeDepartment(Department department) throws DAOException;
	public Department getDepartmentById(Integer id) throws DAOException;
	public Department getDepartmentByWard(Integer wardId) throws DAOException;
	public List<Department> listDepartment(Boolean retired) throws DAOException;
	public Department getDepartmentByName(String name) throws DAOException;
	//DepartmentConcept
	public DepartmentConcept createDepartmentConcept(DepartmentConcept departmentConcept) throws DAOException;
	public DepartmentConcept getByDepartmentAndConcept(Integer departmentId, Integer conceptId) throws DAOException;
	public DepartmentConcept getById(Integer id) throws DAOException;
	public void removeDepartmentConcept(DepartmentConcept departmentConcept) throws DAOException;
	public List<DepartmentConcept> listByDepartment(Integer departmentId, Integer typeConcept) throws DAOException;
	public List<Concept> listByDepartmentByWard(Integer wardId, Integer typeConcept) throws DAOException;
}
