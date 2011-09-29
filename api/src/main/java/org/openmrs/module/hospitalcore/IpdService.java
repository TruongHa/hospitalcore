package org.openmrs.module.hospitalcore;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmission;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmissionLog;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmitted;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmittedLog;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=false)
public interface IpdService extends OpenmrsService{
	
	public IpdPatientAdmission saveIpdPatientAdmission(IpdPatientAdmission admission) throws APIException;
	
	public void removeIpdPatientAdmission(IpdPatientAdmission admission) throws APIException;
	
	public IpdPatientAdmissionLog saveIpdPatientAdmissionLog(IpdPatientAdmissionLog admissionLog) throws APIException;
	
	public IpdPatientAdmitted saveIpdPatientAdmitted(IpdPatientAdmitted admitted) throws APIException;
	
	public void removeIpdPatientAdmitted(IpdPatientAdmitted admitted) throws APIException;
	
	public IpdPatientAdmittedLog saveIpdPatientAdmittedLog(IpdPatientAdmittedLog admitted) throws APIException;
	
	@Transactional(readOnly = true)
	public IpdPatientAdmittedLog getIpdPatientAdmittedLog(Integer id) throws APIException;
	
	@Transactional(readOnly = true)
	public IpdPatientAdmitted getIpdPatientAdmitted(Integer id) throws APIException;
	
	@Transactional(readOnly = true)
	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(Integer id) throws APIException;
	
	@Transactional(readOnly = true)
	public IpdPatientAdmission getIpdPatientAdmission(Integer id) throws APIException;
	
	@Transactional(readOnly = true)
	public List<IpdPatientAdmittedLog> getAllIpdPatientAdmittedLog() throws APIException;
	
	@Transactional(readOnly = true)
	public List<IpdPatientAdmitted> getAllIpdPatientAdmitted() throws APIException;
	
	@Transactional(readOnly = true)
	public List<IpdPatientAdmissionLog> listIpdPatientAdmissionLog(Integer patientId, Integer admissionWardId,String status,Integer min, Integer max)
			throws APIException;
	
	@Transactional(readOnly = true)
	public List<IpdPatientAdmission> getAllIpdPatientAdmission() throws APIException;
	
	@Transactional(readOnly = true)
	public List<IpdPatientAdmission> searchIpdPatientAdmission(String patientSearch, ArrayList<Integer> userIds, String fromDate, String toDate, ArrayList<Integer> wardIds, String status) throws APIException;
	
	@Transactional(readOnly = true)
	public List<IpdPatientAdmitted> searchIpdPatientAdmitted(String patientSearch, ArrayList<Integer> userIds, String fromDate, String toDate, ArrayList<Integer> wardIds, String status) throws APIException;
	
	public IpdPatientAdmitted transfer(Integer id, Integer wardId, Integer doctorId, String bed) throws APIException;
	
	public IpdPatientAdmittedLog discharge(Integer id, Integer outComeConceptId) throws APIException;
	
	@Transactional(readOnly = true)
	public List<IpdPatientAdmittedLog> listAdmittedLogByPatientId(Integer patientId) throws APIException;
	
	@Transactional(readOnly = true)
	public IpdPatientAdmitted getAdmittedByPatientId(Integer patientId) throws APIException;

}
