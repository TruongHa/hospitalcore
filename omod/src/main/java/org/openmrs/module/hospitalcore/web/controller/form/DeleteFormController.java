package org.openmrs.module.hospitalcore.web.controller.form;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.CoreForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("HospitalcoreDeleteFormController")
@RequestMapping("/module/hospitalcore/deleteForm.form")
public class DeleteFormController {

	@RequestMapping(method = RequestMethod.GET)
	public String deleteForm(@RequestParam("id") Integer id,
			Model model) {
		
		CoreForm form = Context.getService(HospitalCoreService.class).getCoreForm(id);
		if(form!=null){
			Context.getService(HospitalCoreService.class).deleteCoreForm(form);
		}
		return "redirect:/module/hospitalcore/listForm.form";
	}

}
