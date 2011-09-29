/**
 * <p> File: org.openmrs.module.hospitalcore.web.controller.autocomplete.AutoCompleteController.java </p>
 * <p> Project: hospitalcore-omod </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Jan 26, 2011 5:15:41 PM </p>
 * <p> Update date: Jan 26, 2011 5:15:41 PM </p>
 **/

package org.openmrs.module.hospitalcore.web.controller.autocomplete;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.PatientDashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p> Class: AutoCompleteController </p>
 * <p> Package: org.openmrs.module.hospitalcore.web.controller.autocomplete </p> 
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Jan 26, 2011 5:15:41 PM </p>
 * <p> Update date: Jan 26, 2011 5:15:41 PM </p>
 **/
@Controller("HospitalCoreAutoCompleteController")
public class AutoCompleteController {
	
	
	@RequestMapping("/module/hospitalcore/checkSession.htm")
	public String checkSession(HttpServletRequest request,Model model) {
		 if( Context.getAuthenticatedUser() != null &&  Context.getAuthenticatedUser().getId() != null){
			 model.addAttribute("session","1");
		 }else{
			 model.addAttribute("session","0");
		 }
		
		return "module/hospitalcore/session/checkSession";
	}
	
	@RequestMapping(value="/module/hospitalcore/autoCompleteDiagnosis.htm", method=RequestMethod.GET)
	public String autoCompleteDiagnosis(@RequestParam(value="q",required=false) String name, Model model) {
		List<Concept> diagnosis = Context.getService(PatientDashboardService.class).searchDiagnosis(name);
		model.addAttribute("diagnosis",diagnosis);
		return "module/hospitalcore/autocomplete/autoCompleteDiagnosis";
	}
	
	@RequestMapping(value="/module/hospitalcore/autoCompleteProcedure.htm", method=RequestMethod.GET)
	public String autoCompleteProcedure(@RequestParam(value="q",required=false) String name, Model model) {
		List<Concept> procedures = Context.getService(PatientDashboardService.class).searchProcedure(name);
		
		model.addAttribute("procedures",procedures);
		return "module/hospitalcore/autocomplete/autoCompleteProcedure";
	}
	
}
