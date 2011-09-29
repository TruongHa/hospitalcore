package org.openmrs.module.hospitalcore.web.controller.form;

import java.util.Date;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.CoreForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("HospitalcoreEditFormController")
@RequestMapping("/module/hospitalcore/editForm.form")
public class EditFormController {
	
	@ModelAttribute("form")
	public CoreForm getForm(
			@RequestParam(value = "id", required = false) Integer id) {
		if (id != null) {
			CoreForm form = Context.getService(HospitalCoreService.class).getCoreForm(id);
			if (form != null)
				return form;
		}
		return new CoreForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showForm(
			Model model) {		
		return "/module/hospitalcore/form/edit";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String saveForm(@ModelAttribute("form") CoreForm form,
			Model model) {
		form.setCreatedOn(new Date());
		form.setCreatedBy(Context.getAuthenticatedUser());
		Context.getService(HospitalCoreService.class).saveCoreForm(form);
		return "redirect:/module/hospitalcore/listForm.form";
	}
}
