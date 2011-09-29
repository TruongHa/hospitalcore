package org.openmrs.module.hospitalcore.db;

import java.math.BigInteger;
import java.util.List;

import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonAttributeType;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.concept.ConceptModel;

public interface HospitalCoreDAO {

	public List<Obs> listObsGroup(Integer personId, Integer conceptId, Integer min, Integer max) throws DAOException;
	public Obs getObsGroupCurrentDate(Integer personId, Integer conceptId) throws DAOException;	
	public Integer buildConcepts(List<ConceptModel> conceptModels);
	public List<Patient> searchPatient(String nameOrIdentifier,String gender, int age , int rangeAge, String date, int rangeDay,String relativeName) throws DAOException;
	
	/**
	 * Search patients
	 * @param hql
	 * @return
	 */
	public List<Patient> searchPatient(String hql);
	
	/**
	 * Get patient search result count
	 * @param hql
	 * @return
	 */
	public BigInteger getPatientSearchResultCount(String hql);
	
	/** 
	 * Get all attributes of an patient
	 * @param patientId
	 * @return
	 */
	public List<PersonAttribute> getPersonAttributes(Integer patientId);
	
	/**
	 * Get last visit encounter
	 * @param patient
	 * @param types
	 * @return
	 */
	public Encounter getLastVisitEncounter(Patient patient, List<EncounterType> types);
}
