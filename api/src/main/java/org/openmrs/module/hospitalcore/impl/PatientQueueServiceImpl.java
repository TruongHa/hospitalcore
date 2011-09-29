/**
 * <p> File: org.openmrs.module.hospitalcore.impl.PatientQueueServiceImpl.java </p>
 * <p> Project: hospitalcore-api </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Feb 16, 2011 12:36:59 PM </p>
 * <p> Update date: Feb 16, 2011 12:36:59 PM </p>
 **/

package org.openmrs.module.hospitalcore.impl;

import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.PatientQueueService;
import org.openmrs.module.hospitalcore.db.PatientQueueDAO;
import org.openmrs.module.hospitalcore.model.OpdPatientQueue;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;

/**
 * <p> Class: PatientQueueServiceImpl </p>
 * <p> Package: org.openmrs.module.hospitalcore.impl </p> 
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Feb 16, 2011 12:36:59 PM </p>
 * <p> Update date: Feb 16, 2011 12:36:59 PM </p>
 **/
public class PatientQueueServiceImpl  extends BaseOpenmrsService implements PatientQueueService{
	
	public PatientQueueServiceImpl() {
	}

	protected PatientQueueDAO dao;
	
	

	public PatientQueueDAO getDao() {
		return dao;
	}

	public void setDao(PatientQueueDAO dao) {
		this.dao = dao;
	}

	public OpdPatientQueue saveOpdPatientQueue(OpdPatientQueue opdPatientQueue)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.saveOpdPatientQueue(opdPatientQueue);
	}

	public OpdPatientQueue updateOpdPatientQueue(Integer id, String status)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.updateOpdPatientQueue(id, status);
	}

	public OpdPatientQueue getOpdPatientQueueById(Integer id)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.getOpdPatientQueueById(id);
	}

	public void deleteOpdPatientQueue(OpdPatientQueue opdPatientQueue)
			throws APIException {
		// TODO Auto-generated method stub
		dao.deleteOpdPatientQueue(opdPatientQueue);
	}

	public List<OpdPatientQueue> listOpdPatientQueue(String patientName,
			Integer referralConceptId, String status, int min, int max)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.listOpdPatientQueue(patientName, referralConceptId, status, min, max);
	}

	public Integer countOpdPatientQueue(String patientName, String searchType,
			Integer referralConceptId, String status) throws APIException {
		// TODO Auto-generated method stub
		return dao.countOpdPatientQueue(patientName, searchType,referralConceptId, status);
	}

	public OpdPatientQueueLog saveOpdPatientQueueLog(
			OpdPatientQueueLog opdPatientQueueLog) throws APIException {
		// TODO Auto-generated method stub
		return dao.saveOpdPatientQueueLog(opdPatientQueueLog);
	}

	public OpdPatientQueueLog getOpdPatientQueueLogById(Integer id)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.getOpdPatientQueueLogById(id);
	}

	public OpdPatientQueueLog copyTo(OpdPatientQueue opdPatientQueue){
		OpdPatientQueueLog opdPatientQueueLog = new OpdPatientQueueLog();
		opdPatientQueueLog.setBirthDate(opdPatientQueue.getBirthDate());
		opdPatientQueueLog.setCreatedOn(opdPatientQueue.getCreatedOn());
		opdPatientQueueLog.setOpdConcept(opdPatientQueue.getOpdConcept());
		opdPatientQueueLog.setOpdConceptName(opdPatientQueue.getOpdConceptName());
		opdPatientQueueLog.setPatientIdentifier(opdPatientQueue.getPatientIdentifier());
		opdPatientQueueLog.setPatient(opdPatientQueue.getPatient());
		opdPatientQueueLog.setPatientName(opdPatientQueue.getPatientName());
		opdPatientQueueLog.setReferralConcept(opdPatientQueue.getReferralConcept());
		opdPatientQueueLog.setReferralConceptName(opdPatientQueue.getReferralConceptName());
		opdPatientQueueLog.setSex(opdPatientQueue.getSex());
		opdPatientQueueLog.setStatus(opdPatientQueue.getStatus());
		opdPatientQueueLog.setUser(opdPatientQueue.getUser());
		return opdPatientQueueLog;
	}
	
	

	public OpdPatientQueue getOpdPatientQueue(String patientIdentifier,Integer opdConceptId)throws APIException {
		// TODO Auto-generated method stub
		return dao.getOpdPatientQueue(patientIdentifier,opdConceptId);
	}

	public List<OpdPatientQueue> getAllPatientInQueue() throws APIException {
		return dao.getAllPatientInQueue();
	}
}
