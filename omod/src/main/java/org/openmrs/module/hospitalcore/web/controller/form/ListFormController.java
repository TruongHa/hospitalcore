package org.openmrs.module.hospitalcore.web.controller.form;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.CoreForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("HospitalcoreListFormController")
@RequestMapping("/module/hospitalcore/listForm.form")
public class ListFormController {
	
	@ModelAttribute("forms")
	public List<CoreForm> getAllForms(){
		return Context.getService(HospitalCoreService.class).getCoreForms();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listForms(
			Model model) {		
		return "/module/hospitalcore/form/list";
	}

}
