package org.openmrs.module.hospitalcore.matcher;

import org.openmrs.Patient;

public class GenderMatcher implements Matcher<Patient> {
    private final String gender;
    public GenderMatcher(String gender) {
        this.gender = gender;
    }

    public boolean matches(Object obj) {
    	if(!gender.equalsIgnoreCase("any")){
    		if(((Patient)obj).getGender().equalsIgnoreCase(gender)){
        		return true;
        	}else{
        		return false;
        	}	
    	} else {
    		return true;
    	}
    	
    	
    }
}
