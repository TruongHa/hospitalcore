package org.openmrs.module.hospitalcore.web.controller.concept;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("HCSDiagnosisImporterController")
@RequestMapping("/module/hospitalcore/conceptImport.form")
public class DiagnosisImporterController {

	@RequestMapping(method = RequestMethod.GET)
	public String getUploadForm(
			Model model) {
		model.addAttribute("uploadFile", new UploadFile());
		return "/module/hospitalcore/concept/uploadForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(UploadFile uploadFile, BindingResult result, Model model) {
		if (result.hasErrors()) {
			for (Object obj : result.getAllErrors()) {
				Error error = (Error) obj;
				System.err.println("Error: " + error.getCause() + " - "
						+ error.getMessage());
			}
			return "/module/hospitalcore/concept/uploadForm";
		}
		
		System.out.println("Begin importing");
		Integer diagnosisNo = 0;
		try {
			HospitalCoreService hcs = (HospitalCoreService) Context.getService(HospitalCoreService.class);
			diagnosisNo = hcs.importConcepts(uploadFile.getDiagnosisFile().getInputStream(), 
					uploadFile.getMappingFile().getInputStream(), 
					uploadFile.getSynonymFile().getInputStream());
			model.addAttribute("diagnosisNo", diagnosisNo);
			System.out.println("Diagnosis imported " + diagnosisNo);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("fail", true);
			model.addAttribute("error", e.toString());
		}

		return "/module/hospitalcore/concept/uploadForm";
	}
}
