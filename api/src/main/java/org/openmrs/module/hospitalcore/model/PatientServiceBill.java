/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.openmrs.Patient;
import org.openmrs.User;

/**
 *
 */
public class PatientServiceBill implements Serializable {
	
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	
	private Integer patientServiceBillId;
	
	private Patient patient;
	
	private User creator;
	
	private BigDecimal amount;
	
	private BigDecimal actualAmount;
	
	private Boolean printed = false;
	
	private Boolean voided = false;
	
	private Date voidedDate;
	
	private Date createdDate;
	
	private String description;
	
	private Receipt receipt;
	
	private Boolean freeBill = false;	
	
	private Set<PatientServiceBillItem> billItems;
	
	public Integer getPatientServiceBillId() {
		return patientServiceBillId;
	}
	
	public void setPatientServiceBillId(Integer patientServiceBillId) {
		this.patientServiceBillId = patientServiceBillId;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Boolean getPrinted() {
		return printed;
	}
	
	public void setPrinted(Boolean printed) {
		this.printed = printed;
	}
	
	public Boolean getVoided() {
		return voided;
	}
	
	public void setVoided(Boolean voided) {
		this.voided = voided;
	}
	
	public Date getVoidedDate() {
		return voidedDate;
	}
	
	public void setVoidedDate(Date voidedDate) {
		this.voidedDate = voidedDate;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addBillItem(PatientServiceBillItem item) {
		if (billItems == null)
			billItems = new HashSet<PatientServiceBillItem>();
		billItems.add(item);
	}
	
	public Set<PatientServiceBillItem> getBillItems() {
		return billItems;
	}
	
	public void setBillItems(Set<PatientServiceBillItem> billItems) {
		this.billItems = billItems;
	}
	
	public User getCreator() {
		return creator;
	}
	
	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public Boolean getFreeBill() {
		return freeBill;
	}

	public void setFreeBill(Boolean freeBill) {
		this.freeBill = freeBill;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}
}
