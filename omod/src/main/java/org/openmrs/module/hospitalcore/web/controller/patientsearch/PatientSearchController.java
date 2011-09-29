package org.openmrs.module.hospitalcore.web.controller.patientsearch;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openmrs.Patient;
import org.openmrs.PersonAttribute;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("HospitalcoreSearchPatientController")
public class PatientSearchController{

	@RequestMapping(value="/module/hospitalcore/searchPatient.form", method = RequestMethod.GET)
	public String showForm(@RequestParam("searchBoxView") String searchBoxView, HttpServletRequest request, Model model) throws IOException {		
		model.addAttribute("searchBoxView", searchBoxView + ".jsp");
		return "/module/hospitalcore/patientSearchPlugin/searchPatient";
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/module/hospitalcore/searchPatient.form", method = RequestMethod.POST)
	public String searchPatient(@RequestParam("query") String query,
			@RequestParam("view") String view, HttpServletRequest request,
			Model model) {
		List<Patient> patients = Context.getService(HospitalCoreService.class).searchPatient(query);
		
		for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
			String parameterName = (String) e.nextElement();
			model.addAttribute(parameterName,
					request.getParameter(parameterName));
		}
		Map<Integer, Map<Integer, String>> attributeMap = buildAttributeMap(patients);
		model.addAttribute("patients", patients);
		model.addAttribute("attributeMap", attributeMap);
		return view;
	}
	
	private Map<Integer, Map<Integer, String>> buildAttributeMap(List<Patient> patients){
		HospitalCoreService hcs = Context.getService(HospitalCoreService.class);
		Map<Integer, Map<Integer, String>> attributeMap = new HashMap<Integer, Map<Integer, String>>();
		for(Patient patient:patients){
			Map<Integer, String> attributes = new HashMap<Integer, String>();
			for(PersonAttribute pa:hcs.getPersonAttributes(patient.getPatientId())){
				attributes.put(pa.getAttributeType().getId(), pa.getValue());
			}
			attributeMap.put(patient.getPatientId(), attributes);
		}
		return attributeMap;
	}
	
	@RequestMapping(value="/module/hospitalcore/getPatientResultCount.form", method = RequestMethod.POST)
	public void getPatientResultCount(@RequestParam("query") String query, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		BigInteger count = Context.getService(HospitalCoreService.class).getPatientSearchResultCount(query);
		out.print(count);
	}
}
