/**
 * <p> File: org.openmrs.module.hospitalcore.util.HospitalCoreConstants.java </p>
 * <p> Project: hospitalcore-api </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Mar 24, 2011 1:41:13 PM </p>
 * <p> Update date: Mar 24, 2011 1:41:13 PM </p>
 **/

package org.openmrs.module.hospitalcore.util;

/**
 * <p> Class: HospitalCoreConstants </p>
 * <p> Package: org.openmrs.module.hospitalcore.util </p> 
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Mar 24, 2011 1:41:13 PM </p>
 * <p> Update date: Mar 24, 2011 1:41:13 PM </p>
 **/
public class HospitalCoreConstants {
	public static final String MODULE_ID = "hospitalcore";
	public static final String PROPERTY_OBSGROUP = MODULE_ID + ".obsGroup";
	public static final String PROPERTY_MEDICAL_EXAMINATION = MODULE_ID + ".medicalExamination";
	public static final String PROPERTY_IPDENCOUNTER = MODULE_ID + ".ipdEncounter";
	public static final String PROPERTY_IDENTIFIER_PREFIX = MODULE_ID + ".identifier_prefix";
	public static final String PROPERTY_HOSPITAL_NAME = MODULE_ID + ".hospitalName";
	
	public static final String CONCEPT_DATATYPE_CODED = "Coded";
	public static final String CONCEPT_DATATYPE_NA = "N/A";
	public static final String CONCEPT_CLASS_QUESTION = "Question";
	public static final String CONCEPT_CLASS_MISC = "Misc";
	public static final String CONCEPT_ADMISSION_OUTCOME = "Admission outcome";
	
	public static final Integer LABTEST_STATUS_NEW = 0;
	public static final Integer LABTEST_STATUS_ACCEPTED = 1;
	public static final Integer LABTEST_STATUS_PRINTED = 2;	
}
