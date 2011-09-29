/**
 * <p> File: org.openmrs.module.hospitalcore.model.IpdPatientAdmissionLog.java </p>
 * <p> Project: hospitalcore-api </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Mar 17, 2011 12:20:41 PM </p>
 * <p> Update date: Mar 17, 2011 12:20:41 PM </p>
 **/

package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;

import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.module.hospitalcore.util.DateUtils;
import org.openmrs.module.hospitalcore.util.PatientUtil;

/**
 * 
 * <p> Class: IpdPatientAdmissionLog </p>
 * <p> Package: org.openmrs.module.hospitalcore.model </p> 
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Mar 17, 2011 12:22:35 PM </p>
 * <p> Update date: Mar 17, 2011 12:22:35 PM </p>
 *
 */
public class IpdPatientAdmissionLog implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date admissionDate;
	private Patient patient;
	private String patientIdentifier;
	private String patientName;
	private Date birthDate;
	private String gender;
	private Concept admissionWard ; //: ipd ward concept id that patient is admitted to.
	private String status ; //: String  : admitted  /  canceled
	private OpdPatientQueueLog opdLog;
	private User opdAmittedUser ;
	private Obs opdObsGroup;
	
	private Encounter ipdEncounter;
	public Date getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getPatientCategory()
	{
		return PatientUtil.getPatientCategory(patient);
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Concept getAdmissionWard() {
		return admissionWard;
	}
	public void setAdmissionWard(Concept admissionWard) {
		this.admissionWard = admissionWard;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public OpdPatientQueueLog getOpdLog() {
		return opdLog;
	}
	public void setOpdLog(OpdPatientQueueLog opdLog) {
		this.opdLog = opdLog;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPatientIdentifier() {
		return patientIdentifier;
	}
	public void setPatientIdentifier(String patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}
	public User getOpdAmittedUser() {
		return opdAmittedUser;
	}
	public void setOpdAmittedUser(User opdAmittedUser) {
		this.opdAmittedUser = opdAmittedUser;
	}
	public Obs getOpdObsGroup() {
		return opdObsGroup;
	}
	public void setOpdObsGroup(Obs opdObsGroup) {
		this.opdObsGroup = opdObsGroup;
	}
	public Encounter getIpdEncounter() {
		return ipdEncounter;
	}
	public void setIpdEncounter(Encounter ipdEncounter) {
		this.ipdEncounter = ipdEncounter;
	}
	public String getAge(){
		Integer age =DateUtils.getAgeFromBirthday(birthDate);
		if(age == null){
			return "";
		}
		return  age > 0? age.intValue()+"" : "<1"; 
	}
	
	
}
