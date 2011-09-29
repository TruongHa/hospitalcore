/**
 * <p> File: org.openmrs.module.hospitalcore.model.DepartmentConcept.java </p>
 * <p> Project: hospitalcore-api </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Apr 13, 2011 12:51:19 PM </p>
 * <p> Update date: Apr 13, 2011 12:51:19 PM </p>
 **/

package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;

import org.openmrs.Concept;

/**
 * <p> Class: DepartmentConcept </p>
 * <p> Package: org.openmrs.module.hospitalcore.model </p> 
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Apr 13, 2011 12:51:19 PM </p>
 * <p> Update date: Apr 13, 2011 12:51:19 PM </p>
 **/
public class DepartmentConcept implements Serializable {
	public static final int[] TYPES = {1,2}; // 1= Diagnosis, 2=Procedure
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer typeConcept;
	private Department department;
	private Concept concept;
	private Date createdOn;
	private String createdBy;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getTypeConcept() {
		return typeConcept;
	}
	public void setTypeConcept(Integer typeConcept) {
		this.typeConcept = typeConcept;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Concept getConcept() {
		return concept;
	}
	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
	
}
