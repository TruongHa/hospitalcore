/**
 * <p> File: org.openmrs.module.hospitalcore.db.PatientQueueDAO.java </p>
 * <p> Project: hospitalcore-api </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Feb 16, 2011 12:34:23 PM </p>
 * <p> Update date: Feb 16, 2011 12:34:23 PM </p>
 **/

package org.openmrs.module.hospitalcore.db;

import java.util.List;

import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.model.OpdPatientQueue;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;

/**
 * <p> Class: PatientQueueDAO </p>
 * <p> Package: org.openmrs.module.hospitalcore.db </p> 
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Feb 16, 2011 12:34:23 PM </p>
 * <p> Update date: Feb 16, 2011 12:34:23 PM </p>
 **/
public interface PatientQueueDAO {
	//opd patient queue
	public OpdPatientQueue saveOpdPatientQueue(OpdPatientQueue opdPatientQueue) throws DAOException;
	public OpdPatientQueue updateOpdPatientQueue(Integer id, String status) throws DAOException;
	public OpdPatientQueue getOpdPatientQueueById(Integer id) throws DAOException;
	public void deleteOpdPatientQueue(OpdPatientQueue opdPatientQueue) throws DAOException;
	public List<OpdPatientQueue> listOpdPatientQueue(String patientName ,  Integer referralConceptId,String status, int min, int max) throws DAOException;
	public Integer countOpdPatientQueue(String patientName , String searchType,Integer referralConceptId,String status) throws DAOException;
	//opd patient queue log
	public OpdPatientQueueLog saveOpdPatientQueueLog(OpdPatientQueueLog opdPatientQueueLog) throws DAOException ;
	public OpdPatientQueueLog getOpdPatientQueueLogById(Integer id) throws DAOException;
	public List<OpdPatientQueue> getAllPatientInQueue() throws DAOException ;
	public OpdPatientQueue getOpdPatientQueue(String patientIdentifier,Integer opdConceptId) throws DAOException;
}
