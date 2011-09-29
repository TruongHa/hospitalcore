/**
 * <p> File: org.openmrs.module.hospitalcore.PatientQueueService.java </p>
 * <p> Project: hospitalcore-api </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Feb 16, 2011 12:36:28 PM </p>
 * <p> Update date: Feb 16, 2011 12:36:28 PM </p>
 **/

package org.openmrs.module.hospitalcore;

import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.model.OpdPatientQueue;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p> Class: PatientQueueService </p>
 * <p> Package: org.openmrs.module.hospitalcore </p> 
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Feb 16, 2011 12:36:28 PM </p>
 * <p> Update date: Feb 16, 2011 12:36:28 PM </p>
 **/
@Transactional
public interface PatientQueueService extends OpenmrsService {
	//opd patient queue
	public OpdPatientQueue saveOpdPatientQueue(OpdPatientQueue opdPatientQueue) throws APIException;
	public OpdPatientQueue updateOpdPatientQueue(Integer id, String status) throws APIException;
	public OpdPatientQueue getOpdPatientQueueById(Integer id) throws APIException;
	public void deleteOpdPatientQueue(OpdPatientQueue opdPatientQueue) throws APIException;
	public List<OpdPatientQueue> listOpdPatientQueue(String patientName ,Integer referralConceptId,String status, int min, int max) throws APIException;
	public Integer countOpdPatientQueue(String patientName , String searchType,Integer referralConceptId,String status) throws APIException;
	//opd patient queue log
	public OpdPatientQueueLog saveOpdPatientQueueLog(OpdPatientQueueLog opdPatientQueueLog) throws APIException ;
	public OpdPatientQueueLog getOpdPatientQueueLogById(Integer id) throws APIException;
	public List<OpdPatientQueue> getAllPatientInQueue() throws APIException ;
	public OpdPatientQueueLog copyTo(OpdPatientQueue opdPatientQueue)throws APIException ;
	public OpdPatientQueue getOpdPatientQueue(String patientIdentifier,Integer opdConceptId) throws APIException;
}
