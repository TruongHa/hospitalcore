package org.openmrs.module.hospitalcore.util;

import org.openmrs.ConceptClass;
import org.openmrs.api.context.Context;

public class ConceptClassUtil {
	private static ConceptClass medicalExaminationClass = null;
	
	/**
	 * Get concept class of medical examination class
	 * @return ConceptClass
	 */
	public static ConceptClass getMedicalExamminationClass(){
		return getConceptClass(medicalExaminationClass, HospitalCoreConstants.PROPERTY_MEDICAL_EXAMINATION);
	}
	
	private static ConceptClass getConceptClass(ConceptClass conceptClass, String keyname){
		if(conceptClass==null){
			String idStr = Context.getAdministrationService().getGlobalProperty(keyname);
			try {
				Integer id = Integer.parseInt(idStr);
				conceptClass = Context.getConceptService().getConceptClass(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conceptClass;
	}
}
