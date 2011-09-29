/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.hospitalcore;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.model.Ambulance;
import org.openmrs.module.hospitalcore.model.AmbulanceBill;
import org.openmrs.module.hospitalcore.model.BillableService;
import org.openmrs.module.hospitalcore.model.Company;
import org.openmrs.module.hospitalcore.model.Driver;
import org.openmrs.module.hospitalcore.model.MiscellaneousService;
import org.openmrs.module.hospitalcore.model.MiscellaneousServiceBill;
import org.openmrs.module.hospitalcore.model.PatientServiceBill;
import org.openmrs.module.hospitalcore.model.Receipt;
import org.openmrs.module.hospitalcore.model.Tender;
import org.openmrs.module.hospitalcore.model.TenderBill;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 */
@Transactional
public interface BillingService extends OpenmrsService{

	
	/**
	 * TENDER
	 */
	@Transactional(readOnly = true)
	@Authorized( { BillingConstants.PRIV_VIEW_TENDERS })
	public List<Tender> listTender(int min, int max) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_ADD_EDIT_TENDER })
	public Tender saveTender(Tender tender) throws APIException;

	@Authorized( { BillingConstants.PRIV_VIEW_TENDERS })
	public int countListTender()  throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_TENDERS })
	public Tender getTenderById(Integer id) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_DELETE_TENDER})
	public void deleteTender(Tender tender) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_TENDERS})
	public Tender getTenderByNameAndNumber(String name, int number) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_TENDERS})
	public List<Tender> getActiveTenders() throws APIException;
	 
	/**
	 * COMPANY 
	 */
	@Authorized( { BillingConstants.PRIV_ADD_EDIT_COMPANY})
	public Company saveCompany(Company company) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_DELETE_COMPANY})
	public void deleteCompany(Company company) throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_COMPANIES})
	public List<Company> listCompany(int min, int max) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_COMPANIES})
	public int countListCompany() throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_COMPANIES})
	public Company getCompanyById(Integer id) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_COMPANIES})
    public Company getCompanyByName(String name) throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_COMPANIES})
	public List<Company> searchCompany(String searchText) throws APIException;

	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_COMPANIES})
	public List<Company> getAllCompany() throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_COMPANIES})
	public List<Company> getAllActiveCompany() throws APIException;
	
	/**
	 * DRIVER
	 */
	@Authorized( { BillingConstants.PRIV_ADD_EDIT_DRIVER})
	public Driver saveDriver(Driver driver) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_DELETE_DRIVER})
	public void deleteDriver(Driver driver) throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_DRIVERS})
	public List<Driver> listDriver(int min, int max) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_DRIVERS})
	public int countListDriver() throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_DRIVERS})
	public Driver getDriverById(Integer id) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_DRIVERS})
    public Driver getDriverByName(String name) throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_DRIVERS})
	public List<Driver> searchDriver(String searchText) throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_DRIVERS})
	public List<Driver> getAllDriver() throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_DRIVERS})
	public List<Driver> getAllActiveDriver() throws APIException;
	
	/**
	 * TENDER BILL
	 */
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public TenderBill getTenderBillById(Integer tenderBillId) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_ADD_BILL})
	public TenderBill saveTenderBill(TenderBill tenderBill) throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public List<TenderBill> listTenderBillByCompany(int min, int max, Company company) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public int countListTenderBillByCompany(Company company) throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public List<TenderBill> getAllTenderBill() throws APIException;
	
	/**
	 * AMBULANCE 
	 */
	@Authorized( { BillingConstants.PRIV_ADD_EDIT_AMBULANCE})
	public Ambulance saveAmbulance(Ambulance ambulance) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_DELETE_AMBULANCE})
	public void deleteAmbulance(Ambulance ambulance) throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_AMBULANCES})
	public List<Ambulance> listAmbulance(int min, int max) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_AMBULANCES})
	public int countListAmbulance() throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_AMBULANCES})
	public Ambulance getAmbulanceById(Integer id) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_AMBULANCES})
    public Ambulance getAmbulanceByName(String name) throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_AMBULANCES})
	public List<Ambulance> getAllAmbulance() throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_AMBULANCES})
	public List<Ambulance> getActiveAmbulances() throws APIException;

	/**
	 * AMBULANCE BILL
	 */
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public AmbulanceBill getAmbulanceBillById(Integer ambulanceBillId) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_ADD_BILL})
	public AmbulanceBill saveAmbulanceBill(AmbulanceBill ambulanceBill) throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public List<AmbulanceBill> listAmbulanceBillByDriver(int min, int max, Driver driver) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public int countListAmbulanceBillByDriver(Driver driver) throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public List<AmbulanceBill> getAllAmbulanceBill() throws APIException;
	
	
	/**
	 * SERVICE
	 */
	@Transactional(readOnly=true)
	public BillableService getServiceById(Integer id) throws APIException;
	
	@Transactional(readOnly=true)
	public List<BillableService>  getAllServices() throws APIException;
	
	public BillableService saveService(BillableService service) throws APIException;
	
	@Transactional(readOnly=true)
	public BillableService getServiceByConceptId(Integer conceptId) throws APIException;
	
	public String traversServices(Concept concept, Map<Integer, BillableService> mapServices) throws APIException;
	
	public void saveServices(Collection<BillableService> services) throws APIException;
	
	public String traversTab(Concept concept, Map<Integer, BillableService> mapServices ,  int count) throws APIException;
	
	public void disableService(Integer conceptId) throws APIException;
	
	/**
	 * BILLABLE SERVICE BILL
	 */
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public PatientServiceBill getPatientServiceBillById(Integer patientServiceBillId) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_ADD_BILL})
	public PatientServiceBill savePatientServiceBill(PatientServiceBill patientServiceBill) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_ADD_BILL})
	 public void saveBillEncounterAndOrder(PatientServiceBill bill) throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public List<PatientServiceBill> listPatientServiceBillByPatient(int min, int max, Patient patient) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public int countListPatientServiceBillByPatient(Patient patient) throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public List<PatientServiceBill> getAllPatientServiceBill() throws APIException;
	
	@Authorized( { BillingConstants.PRIV_EDIT_BILL})
	public void voidBill(PatientServiceBill bill) throws APIException;
	
	
	/**
	 * MISCELLANEOUS BILL
	 */
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public MiscellaneousServiceBill getMiscellaneousServiceBillById(Integer MiscellaneousServiceBillId) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_ADD_BILL})
	public MiscellaneousServiceBill saveMiscellaneousServiceBill(MiscellaneousServiceBill MiscellaneousServiceBill) throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public List<MiscellaneousServiceBill> listMiscellaneousServiceBill(int min, int max) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public int countListMiscellaneousServiceBill() throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public List<MiscellaneousServiceBill> listMiscellaneousServiceBill(int min, int max, MiscellaneousService service ) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public int countListMiscellaneousServiceBill( MiscellaneousService service) throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_BILL})
	public List<MiscellaneousServiceBill> getAllMiscellaneousServiceBill() throws APIException;
	
	/**
	 * MISCELLANEOUS SERVICE
	 */
	@Authorized( { BillingConstants.PRIV_VIEW_MISCELLANEOUS_SERVICE})
	public MiscellaneousService getMiscellaneousServiceById(Integer MiscellaneousServiceId) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_ADD_EDIT_MISCELLANEOUS_SERVICE})
	public MiscellaneousService saveMiscellaneousService(MiscellaneousService MiscellaneousService) throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_MISCELLANEOUS_SERVICE})
	public List<MiscellaneousService> listMiscellaneousService(int min, int max) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_MISCELLANEOUS_SERVICE})
	public int countListMiscellaneousService() throws APIException;
	
	@Transactional(readOnly=true)
	@Authorized( { BillingConstants.PRIV_VIEW_MISCELLANEOUS_SERVICE})
	public List<MiscellaneousService> getAllMiscellaneousService() throws APIException;
	
	@Authorized( { BillingConstants.PRIV_DELETE_MISCELLANEOUS_SERVICE})
	public void deleteMiscellaneousService(MiscellaneousService miscellaneousService) throws APIException;
	
	@Authorized( { BillingConstants.PRIV_VIEW_MISCELLANEOUS_SERVICE})
    public MiscellaneousService getMiscellaneousServiceByName(String name) throws APIException;
	
	/**
	 * Receipt
	 */

	@Authorized( { BillingConstants.PRIV_ADD_BILL})
	public Receipt createReceipt() throws APIException;
	
	@Authorized( { BillingConstants.PRIV_ADD_BILL})
	public void updateReceipt() throws APIException;
}
