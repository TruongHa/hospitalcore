/**
 * <p> File: org.openmrs.module.hospitalcore.web.controller.department.DepartmentListController.java </p>
 * <p> Project: hospitalcore-omod </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Email: chuyennmth@gmail.com</p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Apr 13, 2011 2:06:31 PM </p>
 * <p> Update date: Apr 13, 2011 2:06:31 PM </p>
 **/

package org.openmrs.module.hospitalcore.web.controller.department;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.PatientDashboardService;
import org.openmrs.module.hospitalcore.model.Department;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p> Class: DepartmentListController </p>
 * <p> Package: org.openmrs.module.hospitalcore.web.controller.department </p> 
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Apr 13, 2011 2:06:31 PM </p>
 * <p> Update date: Apr 13, 2011 2:06:31 PM </p>
 **/
@Controller("DepartmentListController")
@RequestMapping("/module/hospitalcore/departmentList.form")
public class DepartmentListController {
	@RequestMapping(method=RequestMethod.GET)
	public String list( @RequestParam(value="searchName",required=false)  String searchName, 
							 @RequestParam(value="pageSize",required=false)  Integer pageSize, 
	                         @RequestParam(value="currentPage",required=false)  Integer currentPage,
	                         Map<String, Object> model, HttpServletRequest request){
		
		PatientDashboardService patientDashboardService = Context.getService(PatientDashboardService.class);
		
		List<Department> departments = patientDashboardService.listDepartment(null);
		
		model.put("departments", departments);
		
		return "/module/hospitalcore/department/departmentList";
	}
}
