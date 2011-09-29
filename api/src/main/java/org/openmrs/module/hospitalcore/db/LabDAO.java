package org.openmrs.module.hospitalcore.db;

import java.util.Date;
import java.util.List;

import org.openmrs.Order;
import org.openmrs.Role;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.model.Lab;
import org.openmrs.module.hospitalcore.model.LabTest;

public interface LabDAO {
	/**
	 * LAB
	 */
	public Lab saveLab(Lab lab) throws DAOException;
	
	public Lab getLabByName(String name) throws DAOException;
	
	public List<Lab> getAllLab() throws DAOException;
	
	public List<Lab> getAllActivelab() throws DAOException;
	
	public Lab getLabByRole(Role role) throws DAOException;
	
	public List<Lab> getLabByRoles(List<Role> roles) throws DAOException;
	
	public Lab getLabById(Integer labId) throws DAOException;
	
	public void deleteLab(Lab lab) throws DAOException;
	
	
	/**
	 * LAB TEST
	 */
	
	public LabTest saveLabTest(LabTest labTest) throws DAOException;
	
	public LabTest getLabTestById(Integer labTestId) throws DAOException;
	
	public LabTest getLabTestByOrder(Order order) throws DAOException;
	
	public LabTest getLabTestBySampleNumber(String sampleNumber) throws DAOException;
	
	public List<LabTest> getLatestLabTestByDate(Date today,Date nextDay, Lab lab ) throws DAOException;
	
	public void deleteLabTest(LabTest labtest) throws DAOException;
}
