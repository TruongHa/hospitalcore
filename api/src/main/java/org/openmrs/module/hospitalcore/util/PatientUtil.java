package org.openmrs.module.hospitalcore.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonAttributeType;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;

public class PatientUtil {

	public static final String MODULE_ID = "hospitalcore.";
	public final static String PATIENT_ATTRIBUTE_CATEGORY = "Patient Category";
	public final static String PATIENT_ATTRIBUTE_BPL_NUMBER = "BPL Number";
	public final static String PATIENT_ATTRIBUTE_RSBY_NUMBER = "RSBY Number";
	public final static String PATIENT_AGE_CATEGORY = MODULE_ID + "ageCategory";

	/**
	 * Get patient category printout based on patient's category
	 * 
	 * @param patient
	 * @return
	 */
	public static String getPatientCategory(Patient patient) {
		String category = "";

		String patientCategory = getPatientAttribute(patient,
				PATIENT_ATTRIBUTE_CATEGORY);
		if (!StringUtils.isBlank(patientCategory)) {
			if (patientCategory.contains("General"))
				category += "General";
			String RSBYNo = getPatientAttribute(patient,
					PATIENT_ATTRIBUTE_RSBY_NUMBER);
			String BPLNo = getPatientAttribute(patient,
					PATIENT_ATTRIBUTE_BPL_NUMBER);
			if (!StringUtils.isBlank(RSBYNo)) {
				category += "RSBY";
			} else if (!StringUtils.isBlank(BPLNo)) {
				category += "BPL";
			}
			if (patientCategory.contains("MLC")) {
				category += ", MLC";
			}
		}

		return category;
	}

	/**
	 * Get the fullname of patient
	 * 
	 * @param patient
	 * @return
	 */
	public static String getFullName(Patient patient) {
		String fullName = "";

		if (!StringUtils.isBlank(patient.getGivenName())) {
			fullName += patient.getGivenName() + " ";
		}

		if (!StringUtils.isBlank(patient.getMiddleName())) {
			fullName += patient.getMiddleName() + " ";
		}

		if (!StringUtils.isBlank(patient.getFamilyName())) {
			fullName += patient.getFamilyName();
		}

		fullName = StringUtils.trim(fullName);
		return fullName;
	}

	/**
	 * Get the age category based on patient's age
	 * 
	 * @param patient
	 * @return
	 */
	public static String getAgeCategory(Patient patient) {
		String ageCategories = GlobalPropertyUtil.getString(
				PATIENT_AGE_CATEGORY, "null");
		try {
			String[] categories = ageCategories.split(";");
			for (String category : categories) {
				String[] parts = category.split(":");
				String categoryName = parts[1];
				String categoryCondition = parts[0];
				String[] conditions = categoryCondition.split("-");
				Integer lower = Integer.parseInt(conditions[0]);
				Integer upper = Integer.parseInt(conditions[1]);
				if ((lower <= patient.getAge()) && (patient.getAge() <= upper))
					return categoryName;
			}
		} catch (Exception e) {
			System.out.println("Error while generating age category!");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Get patient attribute
	 * 
	 * @param patient
	 * @param attributeNameType
	 * @return
	 */
	public static String getPatientAttribute(Patient patient,
			String attributeNameType) {
		String value = null;
		PersonAttributeType pat = Context.getPersonService()
				.getPersonAttributeTypeByName(attributeNameType);
		PersonAttribute pa = patient.getAttribute(pat);
		if (pa != null) {
			value = pa.getValue();
		}
		return value;
	}

	public static void removePatientAttribute(Patient patient,
			String attributeNameType) {
		PersonAttributeType pat = Context.getPersonService()
				.getPersonAttributeTypeByName(attributeNameType);
		PersonAttribute pa = patient.getAttribute(pat);
		patient.removeAttribute(pa);
		Context.getPatientService().savePatient(patient);
	}
	
	public static Map<String, String> getAttributes(Patient patient){		
		Map<String, String> attributes = new HashMap<String, String>();
		
		for (String key : patient.getAttributeMap().keySet()) {
			attributes.put(patient.getAttributeMap().get(key).getAttributeType().getName(),
					patient.getAttributeMap().get(key).getValue());
		}
		
		// get last encounter 
		List<EncounterType> types = new ArrayList<EncounterType>();
		EncounterType reginit = Context.getEncounterService().getEncounterType(1);
		types.add(reginit);
		EncounterType revisit = Context.getEncounterService().getEncounterType(2);
		types.add(revisit);		
		Encounter lastVisit = Context.getService(HospitalCoreService.class).getLastVisitEncounter(patient, types);
		
		if(lastVisit!=null){
			for(Obs obs:lastVisit.getAllObs()){
				if(!obs.isVoided()){
					if(obs.getConcept().getDatatype().getName().equalsIgnoreCase("Coded")){
						attributes.put(obs.getConcept().getName().getName(), obs.getValueCoded().getName().getName());	
					} else if(obs.getConcept().getDatatype().getName().equalsIgnoreCase("Text")){
						attributes.put(obs.getConcept().getName().getName(), obs.getValueText());	
					}
					
				}
			}
		}
		
		return attributes;
	}
}
