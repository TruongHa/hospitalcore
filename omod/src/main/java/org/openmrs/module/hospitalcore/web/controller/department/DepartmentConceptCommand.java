/**
 * <p> File: org.openmrs.module.hospitalcore.web.controller.department.DepartmentConceptCommand.java </p>
 * <p> Project: hospitalcore-omod </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Email: chuyennmth@gmail.com</p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Apr 13, 2011 4:59:18 PM </p>
 * <p> Update date: Apr 13, 2011 4:59:18 PM </p>
 **/

package org.openmrs.module.hospitalcore.web.controller.department;

/**
 * <p> Class: DepartmentConceptCommand </p>
 * <p> Package: org.openmrs.module.hospitalcore.web.controller.department </p> 
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Apr 13, 2011 4:59:18 PM </p>
 * <p> Update date: Apr 13, 2011 4:59:18 PM </p>
 **/
public class DepartmentConceptCommand {
	private Integer[] selectedDiagnosisList;
	private Integer[] selectedProcedureList;
	private Integer departmentId;
	public Integer[] getSelectedDiagnosisList() {
		return selectedDiagnosisList;
	}
	public void setSelectedDiagnosisList(Integer[] selectedDiagnosisList) {
		this.selectedDiagnosisList = selectedDiagnosisList;
	}
	public Integer[] getSelectedProcedureList() {
		return selectedProcedureList;
	}
	public void setSelectedProcedureList(Integer[] selectedProcedureList) {
		this.selectedProcedureList = selectedProcedureList;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	
	
}
