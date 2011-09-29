package org.openmrs.module.hospitalcore.util;

import org.apache.commons.lang.StringUtils;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;

public class OrderUtil {
	
	private static final String RADIOLOGY_ORDER_TYPE = "billing.encounterType.radiology";
	
	public static void saveRadiologyOrder(PatientServiceBillItem item){		
		String radiologyEncounterTypeName = GlobalPropertyUtil.getString(RADIOLOGY_ORDER_TYPE, null);
		if(!StringUtils.isBlank(radiologyEncounterTypeName)){
			EncounterType et = Context.getEncounterService().getEncounterType(radiologyEncounterTypeName);
			if(et!=null){
				Encounter encounter = new Encounter();
				
			}
		}
	}
	
	
}
