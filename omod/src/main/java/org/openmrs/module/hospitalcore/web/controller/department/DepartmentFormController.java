/**
 * <p> File: org.openmrs.module.hospitalcore.web.controller.department.DepartmentFormController.java </p>
 * <p> Project: hospitalcore-omod </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Email: chuyennmth@gmail.com</p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Apr 13, 2011 2:06:19 PM </p>
 * <p> Update date: Apr 13, 2011 2:06:19 PM </p>
 **/

package org.openmrs.module.hospitalcore.web.controller.department;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.PatientDashboardService;
import org.openmrs.module.hospitalcore.model.Department;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

/**
 * <p> Class: DepartmentFormController </p>
 * <p> Package: org.openmrs.module.hospitalcore.web.controller.department </p> 
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Apr 13, 2011 2:06:19 PM </p>
 * <p> Update date: Apr 13, 2011 2:06:19 PM </p>
 **/

@Controller("DepartmentFormController")
@RequestMapping("/module/hospitalcore/department.form")
public class DepartmentFormController {
	@RequestMapping(method = RequestMethod.GET)
	public String firstView(@ModelAttribute("department") Department department, @RequestParam(value="departmentId",required=false) Integer id, Model model) {
		if( id != null ){
			PatientDashboardService dashboardService = Context.getService(PatientDashboardService.class);
			department = dashboardService.getDepartmentById(id);
			model.addAttribute("department",department);
		}
		return "/module/hospitalcore/department/department";
	}
	@ModelAttribute("wards")
	public List<Concept> populateCategories(HttpServletRequest request) {
		int deparmentId = NumberUtils.toInt(request.getParameter("departmentId") , 0);
		List<Concept> wards = new ArrayList<Concept>();
		Concept opdWardConcept = Context.getConceptService().getConceptByName("OPD WARD");
		if(opdWardConcept != null && CollectionUtils.isNotEmpty(opdWardConcept.getAnswers())){
			for(ConceptAnswer answer : opdWardConcept.getAnswers()){
				if(!wards.contains(answer.getAnswerConcept())){
					wards.add(answer.getAnswerConcept());
				}
			}
		}
		
		Concept ipdWardConcept = Context.getConceptService().getConceptByName("IPD WARD");
		if(ipdWardConcept != null && CollectionUtils.isNotEmpty(ipdWardConcept.getAnswers())){
			for(ConceptAnswer answer : ipdWardConcept.getAnswers()){
				if(!wards.contains(answer.getAnswerConcept())){
					wards.add(answer.getAnswerConcept());
				}
			}
		}
		//ensure one ward only belong one department
		PatientDashboardService patientDashboardService =Context.getService(PatientDashboardService.class);
		List<Department> listDepartment = patientDashboardService.listDepartment(null);
		if(CollectionUtils.isNotEmpty(listDepartment)){
			for(Department department : listDepartment){
				if(deparmentId != department.getId()){
					wards.removeAll(department.getWards());
				}
			}
		}
		
		return wards;
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Set.class, "wards",new CustomCollectionEditor(Set.class){
			ConceptService conceptService = Context.getConceptService();
			protected Object convertElement(Object element)
			    {
				  Integer conceptId = null;
			      if (element instanceof Integer)
			    	  conceptId = (Integer) element;
			      else if (element instanceof String)
			    	  conceptId = NumberUtils.toInt((String) element , 0);
			      return conceptId != null ? conceptService.getConcept(conceptId) : null;
			    }
		});
	}
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("department") Department department, BindingResult bindingResult, HttpServletRequest request, SessionStatus status) {
		new DepartmentValidator().validate(department, bindingResult);
		if (bindingResult.hasErrors()) {
			return "/module/hospitalcore/department/department";
			
		}else{
			PatientDashboardService patientDashboardService = Context.getService(PatientDashboardService.class);
			department.setCreatedBy(Context.getAuthenticatedUser().getGivenName());
			department.setCreatedOn(new Date());
			patientDashboardService.createDepartment(department);
			status.setComplete();
			return "redirect:/module/hospitalcore/departmentList.form";
		}
	}
}
