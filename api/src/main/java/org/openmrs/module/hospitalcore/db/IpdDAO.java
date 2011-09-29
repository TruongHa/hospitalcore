package org.openmrs.module.hospitalcore.db;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmission;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmissionLog;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmitted;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmittedLog;

public interface IpdDAO {
	
	public IpdPatientAdmission saveIpdPatientAdmission(IpdPatientAdmission admission) throws DAOException;
	public IpdPatientAdmissionLog saveIpdPatientAdmissionLog(IpdPatientAdmissionLog admissionLog) throws DAOException;
	public IpdPatientAdmitted saveIpdPatientAdmitted(IpdPatientAdmitted admitted) throws DAOException;
	public IpdPatientAdmittedLog saveIpdPatientAdmittedLog(IpdPatientAdmittedLog admitted) throws DAOException;
	
	public IpdPatientAdmittedLog getIpdPatientAdmittedLog(Integer id) throws DAOException;
	public IpdPatientAdmitted getIpdPatientAdmitted(Integer id) throws DAOException;
	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(Integer id) throws DAOException;
	public IpdPatientAdmission getIpdPatientAdmission(Integer id) throws DAOException;
	
	public List<IpdPatientAdmittedLog> getAllIpdPatientAdmittedLog() throws DAOException;
	public List<IpdPatientAdmitted> getAllIpdPatientAdmitted() throws DAOException;
	public List<IpdPatientAdmissionLog> listIpdPatientAdmissionLog(Integer patientId, Integer admissionWardId,String status,Integer min, Integer max)
	throws DAOException;
	public List<IpdPatientAdmission> getAllIpdPatientAdmission() throws DAOException;
	
	public List<IpdPatientAdmission> searchIpdPatientAdmission(String patientSearch, ArrayList<Integer> userIds, String fromDate, String toDate, ArrayList<Integer> wardIds, String status) throws APIException;
	public List<IpdPatientAdmitted> searchIpdPatientAdmitted(String patientSearch, ArrayList<Integer> userIds, String fromDate, String toDate, ArrayList<Integer> wardIds, String status) throws APIException;
	
	public void removeIpdPatientAdmission(IpdPatientAdmission admission) throws DAOException;
	public void removeIpdPatientAdmitted(IpdPatientAdmitted admitted) throws DAOException;
	
	public List<IpdPatientAdmittedLog> listAdmittedLogByPatientId(Integer patientId) throws DAOException;
	
	public IpdPatientAdmitted getAdmittedByPatientId(Integer patientId) throws DAOException;
}
