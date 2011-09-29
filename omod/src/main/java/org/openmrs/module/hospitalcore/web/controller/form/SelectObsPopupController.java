package org.openmrs.module.hospitalcore.web.controller.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("HospitalcoreSelectObsPopupController")
@RequestMapping("/module/hospitalcore/selectObsPopup.form")
public class SelectObsPopupController {

	@RequestMapping(method = RequestMethod.GET)
	public String showForm(@RequestParam("type") String type, Model model) {
		model.addAttribute("type", type);
		return "/module/hospitalcore/form/selectObsPopup";
	}

}
