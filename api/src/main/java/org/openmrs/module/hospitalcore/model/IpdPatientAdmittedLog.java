/**
 * <p> File: org.openmrs.module.hospitalcore.model.IpdPatientAdmitted.java </p>
 * <p> Project: hospitalcore-api </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Mar 17, 2011 12:46:16 PM </p>
 * <p> Update date: Mar 17, 2011 12:46:16 PM </p>
 **/

package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.module.hospitalcore.util.DateUtils;
import org.openmrs.module.hospitalcore.util.PatientUtil;

/**
 * <p> Class: IpdPatientAdmitted </p>
 * <p> Package: org.openmrs.module.hospitalcore.model </p> 
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Mar 17, 2011 12:46:16 PM </p>
 * <p> Update date: Mar 17, 2011 12:46:16 PM </p>
 **/
public class IpdPatientAdmittedLog implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	private Date admissionDate;
	private Patient patient; 
	private String patientName;
	private String patientAddress;
	private String bed;
	private String fatherName;
	private String patientIdentifier;
	private Date birthDate;
	private String gender;
	private Concept admittedWard; //    :  the ipd ward concept name that patient is transferd from
	private User user; // :     1 ) user who transferred  this patient
					   //2)  user discharged this patient
					   //	3) user called !
	private String  status ; //   : discharge, transfer, call (STRING ) 
	private String caste; // : String
	private BigDecimal monthlyIncome ; //:  String
	private String basicPay; // : String
	
	private User ipdAdmittedUser; // User admitted in patient admission queue
	
	//private String bed_number : concept _id   ( N/A )
	private IpdPatientAdmissionLog  patientAdmissionLog;
	private IpdPatientAdmittedLog patientAdmittedLogTransferFrom;
	private String admissionOutCome;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getAge(){
		Integer age =DateUtils.getAgeFromBirthday(birthDate);
		if(age == null){
			return "";
		}
		return  age > 0? age.intValue()+"" : "<1"; 
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	
	public BigDecimal getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(BigDecimal monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public String getBasicPay() {
		return basicPay;
	}
	public void setBasicPay(String basicPay) {
		this.basicPay = basicPay;
	}
	public IpdPatientAdmissionLog getPatientAdmissionLog() {
		return patientAdmissionLog;
	}
	public void setPatientAdmissionLog(IpdPatientAdmissionLog patientAdmissionLog) {
		this.patientAdmissionLog = patientAdmissionLog;
	}
	public String getPatientIdentifier() {
		return patientIdentifier;
	}
	public void setPatientIdentifier(String patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}
	public User getIpdAdmittedUser() {
		return ipdAdmittedUser;
	}
	public void setIpdAdmittedUser(User ipdAdmittedUser) {
		this.ipdAdmittedUser = ipdAdmittedUser;
	}
	public Concept getAdmittedWard() {
		return admittedWard;
	}
	public void setAdmittedWard(Concept admittedWard) {
		this.admittedWard = admittedWard;
	}
	public String getPatientAddress() {
		return patientAddress;
	}
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}
	public String getBed() {
		return bed;
	}
	public void setBed(String bed) {
		this.bed = bed;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public IpdPatientAdmittedLog getPatientAdmittedLogTransferFrom() {
		return patientAdmittedLogTransferFrom;
	}
	public void setPatientAdmittedLogTransferFrom(
			IpdPatientAdmittedLog patientAdmittedLogTransferFrom) {
		this.patientAdmittedLogTransferFrom = patientAdmittedLogTransferFrom;
	}
	public String getAdmissionOutCome() {
		return admissionOutCome;
	}
	public void setAdmissionOutCome(String admissionOutCome) {
		this.admissionOutCome = admissionOutCome;
	}
	
	
}
