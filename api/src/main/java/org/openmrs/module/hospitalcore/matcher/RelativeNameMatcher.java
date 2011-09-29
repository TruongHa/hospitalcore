package org.openmrs.module.hospitalcore.matcher;

import org.openmrs.Patient;
import org.openmrs.PersonAttribute;
import org.openmrs.api.context.Context;

public class RelativeNameMatcher implements Matcher<Patient> {
	private String[] parts;

	public RelativeNameMatcher(String relativeName) {
		relativeName = relativeName.toLowerCase();
		relativeName = relativeName.replaceAll("'", " ");
		parts = relativeName.split(" ");
	}

	public boolean matches(Object obj) {
		Patient patient = (Patient) obj;
		PersonAttribute pat = patient.getAttribute(Context.getPersonService()
				.getPersonAttributeTypeByName("Father/Husband Name"));
		String relativeName = pat.getValue().toLowerCase();
		for (String part : parts) {
			if (relativeName.contains(part))
				return true;
		}
		return false;
	}
}
