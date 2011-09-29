package org.openmrs.module.hospitalcore.util;

import org.apache.commons.lang.StringUtils;
import org.openmrs.GlobalProperty;
import org.openmrs.api.context.Context;

public class GlobalPropertyUtil {
	
	/**
	 * Get boolean value from a specific global property. Unless the global property is found, the defaultValue will be returned. 
	 * @param globalPropertyName
	 * @param defaultValue
	 * @return
	 */
	public static Boolean getBoolean(String globalPropertyName, Boolean defaultValue){
		String value = Context.getAdministrationService().getGlobalProperty(
				globalPropertyName);
		
		Boolean result = defaultValue;
		
		if (!StringUtils.isBlank(value)) {
			result = Boolean.parseBoolean(value);
		}
		return result;
	}
	
	/**
	 * Get String value from a specific global property. Unless the global property is found, the defaultValue will be returned. 
	 * @param globalPropertyName
	 * @param defaultValue
	 * @return
	 */
	public static String getString(String globalPropertyName, String defaultValue){
		String value = Context.getAdministrationService().getGlobalProperty(
				globalPropertyName);
		
		String result = defaultValue;
		
		if (!StringUtils.isBlank(value)) {
			result = value;
		}
		return result;
	}
	
	/**
	 * Get Integer value from a specific global property. Unless the global property is found, the defaultValue will be returned. 
	 * @param globalPropertyName
	 * @param defaultValue
	 * @return
	 */
	public static Integer getInteger(String globalPropertyName, Integer defaultValue){
		String value = Context.getAdministrationService().getGlobalProperty(
				globalPropertyName);
		
		Integer result = defaultValue;
		
		if (!StringUtils.isBlank(value)) {
			try {
				result = Integer.parseInt(value);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * Set string value for a specific global property
	 * @param globalPropertyName
	 * @param value
	 */
	public static void setString(String globalPropertyName, String value){		
		GlobalProperty gp = Context.getAdministrationService().getGlobalPropertyObject(globalPropertyName);		
		if(gp!=null){			
			gp.setPropertyValue(value);
			Context.getAdministrationService().saveGlobalProperty(gp);			
		}
	}
	
	/**
	 * Save a new global property unless it exists
	 * 
	 * @param name
	 * @param description
	 * @param value
	 */
	public static void saveGlobalProperty(String name, String description,
			Object value) {
		GlobalProperty gp = Context.getAdministrationService()
				.getGlobalPropertyObject(name);
		if (gp == null) {
			gp = new GlobalProperty();
		}
		gp.setProperty(name);
		gp.setDescription(description);
		gp.setPropertyValue(value.toString());
		Context.getAdministrationService().saveGlobalProperty(gp);
	}
}
