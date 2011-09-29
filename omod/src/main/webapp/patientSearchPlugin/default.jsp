<script type="text/javascript">
	
	/** 
	 ** SEARCH FUNCTION
	 **/
	PATIENTSEARCH = {
		target: "#patientSearchResult",
		resultView: "",
		selectClause: "",
		fromClause: "",
		whereClause: "",
		orderClause: "",
		limitClause: "",
		query: "",
		currentRow: 0,
		rowPerPage: 10,
		totalRow: 0,		
		advanceSearch: false,
		form: null,
		success: function(data){},
		beforeNewSearch: function(){},
		
		/** CONSTRUCTOR  FOR PATIENT SEARCH
		 * options = {
		 *		rowPerPage: number of rows per result page. Default is 10.
		 * }
		 */		
		init: function(options){
		
			// Set value for configuration			
			this.resultView = options.resultView;
			this.target = options.target;
			this.rowPerPage = options.rowPerPage;	
			this.success = options.success;
			this.beforeNewSearch = options.beforeNewSearch;
			
			// Display form
			this.form = jQuery("#patientSearchForm");
			jQuery("#advanceSearch", this.form).hide();	
			jQuery("#ageRange", this.form).val(5);
			jQuery("#nameOrIdentifier", this.form).keyup(function(event){				
				if(event.keyCode == 13){	
					nameInCapital = StringUtils.capitalize(jQuery("#nameOrIdentifier", PATIENTSEARCH.form).val());
					jQuery("#nameOrIdentifier", PATIENTSEARCH.form).val(nameInCapital);
					PATIENTSEARCH.search(true);
				}
			});
			jQuery("#lastVisit", this.form).change(function(){
				PATIENTSEARCH.search(true);
			});
			jQuery("#relativeName", this.form).blur(function(){
				PATIENTSEARCH.search(true);
			});
			jQuery("#age", this.form).blur(function(){
				PATIENTSEARCH.search(true);
			});
			jQuery("#gender", this.form).change(function(){
				PATIENTSEARCH.search(true);
			});
			jQuery("#ageRange", this.form).blur(function(){
				PATIENTSEARCH.search(true);
			});
			
			// Add Validation
			jQuery.validator.addMethod("nameOrIdentifier", function(value, element) { 
				result = true;
				value = value.toUpperCase();
				if(value.length>3){
					pattern = /[A-Z0-9\s-]+/;
					for(i=0; i<value.length; i++){
						if(!pattern.test(value[i])){																
							result = false;							
							break;
						}
					}					
				}					
				return result;
			}, "Please enter patient name/identifier in correct format!");
			
			jQuery.validator.addMethod("ageValidator", function(value, element) { 
				console.debug("ageValidator -> " + value);		
				allowable = "0123456789";
				for(i=0; i<value.length; i++){
					if(allowable.indexOf(value[i])<0)
						return false;
				}
				return true;				
			}, "Please enter patient age in digits");
		},
		
		/** SEARCH */
		search: function(newQuery){
		
			// validate the form
			validatedResult = this.validate();			
			if(validatedResult) {
				// reset navigation for new query
				if(newQuery == true){
					this.currentRow = 0;		
					// callback
					PATIENTSEARCH.beforeNewSearch();
					jQuery("#searchLoader", PATIENTSEARCH.form).html("<img src='" + openmrsContextPath + "/moduleResources/hospitalcore/ajax-loader.gif" + "'/>&nbsp;");				
				}
				
				var query = this.buildQuery();				
				
				jQuery(PATIENTSEARCH.target).mask("<img src='" + openmrsContextPath + "/moduleResources/hospitalcore/ajax-loader.gif" + "'/>&nbsp;");				
				
				jQuery.ajax({
					type : "POST",
					url : openmrsContextPath + "/module/hospitalcore/searchPatient.form",
					data : ({
						query: query,
						view: PATIENTSEARCH.resultView
					}),				
					success : function(data) {						
						jQuery(PATIENTSEARCH.target).html(data);						
						if(PATIENTSEARCH.currentRow==0){
							PATIENTSEARCH.getPatientResultCount();
							jQuery("#searchLoader", PATIENTSEARCH.form).html("");
						} else {						
							jQuery(PATIENTSEARCH.target).append("<div>" + PATIENTSEARCH.generateNavigation() + "</div>");	
							
							// callback
							PATIENTSEARCH.success({
								totalRow: PATIENTSEARCH.totalRow
							});
						}
						jQuery(PATIENTSEARCH.target).unmask();
						
					},
					error : function(xhr, ajaxOptions, thrownError) {
						alert(thrownError);
					}
				});
			};
		},
		
		/** GET PATIENT RESULT COUNT */
		getPatientResultCount: function(){
			
			
			var query = this.buildCountQuery();
			
			jQuery.ajax({
				type : "POST",
				url : openmrsContextPath + "/module/hospitalcore/getPatientResultCount.form",
				data : ({
					query: query
				}),				
				success : function(data) {					
					PATIENTSEARCH.totalRow = data;	
					jQuery(PATIENTSEARCH.target).append("<div>" + PATIENTSEARCH.generateNavigation() + "</div>");		
					// callback
					PATIENTSEARCH.success({
						totalRow: PATIENTSEARCH.totalRow
					});
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert(thrownError);
				}
			});
		},
		
		/** BUILD QUERY */
		buildQuery: function(){
		
			// Get value from form			
			nameOrIdentifier = jQuery.trim(jQuery("#nameOrIdentifier", this.form).val());	
			nameOrIdentifier = nameOrIdentifier.replace(/\s/g, "");			
		
			// Build essential query
			this.selectClause = "SELECT DISTINCT pt.patient_id, pi.identifier, pn.given_name, pn.middle_name, pn.family_name, ps.gender, ps.birthdate, EXTRACT(YEAR FROM (FROM_DAYS(DATEDIFF(NOW(),ps.birthdate)))) age, pn.person_name_id";
			this.fromClause   = " FROM `patient` pt";
			this.fromClause  += " INNER JOIN person ps ON ps.person_id = pt.patient_id";
			this.fromClause  += " INNER JOIN person_name pn ON pn.person_id = ps.person_id";
			this.fromClause  += " INNER JOIN patient_identifier pi ON pi.patient_id = pt.patient_id";
			this.whereClause  = " WHERE";
			this.whereClause += " (pi.identifier LIKE '%" + nameOrIdentifier + "%' OR CONCAT(IFNULL(pn.given_name, ''), IFNULL(pn.middle_name, ''), IFNULL(pn.family_name,'')) LIKE '" + nameOrIdentifier + "%')";			
			this.orderClause = " ORDER BY pt.patient_id ASC";
			this.limitClause = " LIMIT " + this.currentRow + ", " + this.rowPerPage;			

			//	Build extended queries
			if(this.advanceSearch){
				this.buildGenderQuery();
				this.buildAgeQuery();
				this.buildRelativeNameQuery();
				this.buildLastVisitQuery();
			}
			
			// Return the built query
			this.query = this.selectClause + this.fromClause + this.whereClause + this.orderClause + this.limitClause;		
			return this.query;
		},
		
		/** BUILD COUNT QUERY */
		buildCountQuery: function(){
		
			// Get value from form			
			nameOrIdentifier = jQuery.trim(jQuery("#nameOrIdentifier", this.form).val());			
			nameOrIdentifier = nameOrIdentifier.replace(/\s/g, "");
		
			// Build essential query
			this.selectClause = "SELECT COUNT(DISTINCT pt.patient_id)";
			this.fromClause   = " FROM `patient` pt";
			this.fromClause  += " INNER JOIN person ps ON ps.person_id = pt.patient_id";
			this.fromClause  += " INNER JOIN person_name pn ON pn.person_id = ps.person_id";
			this.fromClause  += " INNER JOIN patient_identifier pi ON pi.patient_id = pt.patient_id";
			this.whereClause  = " WHERE";
			this.whereClause += " (pi.identifier LIKE '%" + nameOrIdentifier + "%' OR CONCAT(IFNULL(pn.given_name, ''), IFNULL(pn.middle_name, ''), IFNULL(pn.family_name,'')) LIKE '" + nameOrIdentifier + "%')";						

			//	Build extended queries
			if(this.advanceSearch){
				this.buildGenderQuery();
				this.buildAgeQuery();
				this.buildRelativeNameQuery();
				this.buildLastVisitQuery();
			}
			
			// Return the built query
			this.query = this.selectClause + this.fromClause + this.whereClause;		
			return this.query;
		},
		
		/** NEXT PAGE */
		nextPage: function(){
			this.currentRow += this.rowPerPage;
			this.search(false);
		},
		
		/** PREV PAGE */
		prevPage: function(){
			this.currentRow -= this.rowPerPage;
			this.search(false);
		},
		
		/** SHOW ADVANCE SEARCH */
		toggleAdvanceSearch: function(){
			if(this.advanceSearch){
				jQuery("#advanceSearch", this.form).hide();
				this.advanceSearch = false;
			} else {
				jQuery("#advanceSearch", this.form).show();
				this.advanceSearch = true;
			}
			
		},
		
		/** BUILD QUERY FOR GENDER */
		buildGenderQuery: function(){
			value = jQuery.trim(jQuery("#gender", this.form).val());
			if(value!='Any'){
				this.whereClause += " AND (ps.gender = '" + value + "') ";
			}
		},
		
		/** BUILD QUERY FOR AGE */
		buildAgeQuery: function(){
			value =jQuery.trim(jQuery("#age", this.form).val());
			if(value!=undefined && value.length>0){
				
				if(StringUtils.isDigit(StringUtils.right(value, 1))){
					value += "y";					
				}
				type = StringUtils.right(value, 1);
				value = parseInt(value.substring(0, value.length-1));
				range = parseInt(jQuery.trim(jQuery("#ageRange", this.form).val()));
				
				if(type=="y"){									
					
					jQuery("#rangeUnit").html("Year(s)");
					this.whereClause += "AND (EXTRACT(YEAR FROM (FROM_DAYS(DATEDIFF(NOW(),ps.birthdate)))) >=" + (value - range) + " AND EXTRACT(YEAR FROM (FROM_DAYS(DATEDIFF(NOW(),ps.birthdate)))) <= " + (value + range) + ") ";
				} else {
					days = value + range;
					if(type=="m"){
						days = days * 30;
						jQuery("#rangeUnit").html("Month(s)");
					} else if(type=="w") {
						jQuery("#rangeUnit").html("Week(s)");
						days = days * 7;
					} else {
						jQuery("#rangeUnit").html("Day(s)");
					}
					this.whereClause += "AND (DATEDIFF(NOW(),ps.birthdate) <= " + (days) + ") ";
				}
			}
		},
		
		/** BUILD QUERY FOR RELATIVE NAME */
		buildRelativeNameQuery: function(){
			value = jQuery.trim(jQuery("#relativeName", this.form).val());
			personAttributeTypeName = "Father/Husband Name";
			if(value!=undefined && value.length>0){
				this.fromClause += " INNER JOIN person_attribute paRelativeName ON ps.person_id= paRelativeName.person_id";
				this.fromClause += " INNER JOIN person_attribute_type patRelativeName ON paRelativeName.person_attribute_type_id = patRelativeName.person_attribute_type_id ";
				this.whereClause += " AND (patRelativeName.name LIKE '%" + personAttributeTypeName + "%' AND paRelativeName.value LIKE '%" + value + "%')";
			}
		},
		
		/** BUILD QUERY FOR LAST VISIT */
		buildLastVisitQuery: function(){
			value = jQuery.trim(jQuery("#lastVisit", this.form).val());
			if(value!='Any'){
				this.fromClause += " INNER JOIN encounter e ON e.patient_id = pt.patient_id";
				this.whereClause += " AND (DATEDIFF(NOW(), e.date_created) <= " + value + ")";
			}
		},
		
		/** GENERATE THE NAVIGATION BAR */
		generateNavigation: function(){
			navbar = this.totalRow + " patients found.";
			
			if(this.currentRow > 0) {
				navbar += "&nbsp;&nbsp;<a href='javascript:PATIENTSEARCH.prevPage();'>&laquo;&laquo; Prev</a>&nbsp;&nbsp;";
			}
			
			navbar += "page " + (this.currentRow/this.rowPerPage + 1);
			
			if(this.currentRow + this.rowPerPage < this.totalRow) {
				navbar += "&nbsp;&nbsp;<a href='javascript:PATIENTSEARCH.nextPage();'>Next &raquo;&raquo;</a>&nbsp;&nbsp;";
			}
			
			return navbar;
		},
		
		/** VALIDATE FORM BEFORE QUERYING */
		validate: function(){
			jQuery("#errorSection", this.form).html("<ul id='errorList' class='error'></ul>");
			result = true;
			result = result && this.validateNameOrIdentifier();
			result = result && this.validateAge();
			return result;
		},
		
		/** VALIDATE NAME OR IDENTIFIER */
		validateNameOrIdentifier: function(){
			
			value = jQuery("#nameOrIdentifier", this.form).val();
			value = value.toUpperCase();
			if(value.length>=3){
				pattern = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 -";
				for(i=0; i<value.length; i++){
					if(pattern.indexOf(value[i])<0){	
						jQuery("#errorList", this.form).append("<li>Please enter patient name/identifier in correct format!</li>");
						return false;							
					}
				}	
				return true;
			} else {
				jQuery("#errorList", this.form).append("<li>Please enter at least 3 letters of patient name/identifier</li>");
				return false;
			}			
		},
		
		/** VALIDATE AGE */
		validateAge: function(){
			if(this.advanceSearch){				
				value = jQuery("#age", this.form).val();
				pattern = "0123456789ymwd";
				for(i=0; i<value.length; i++){
					if(pattern.indexOf(value[i])<0){	
						jQuery("#errorList", this.form).append("<li>Please enter patient age in digits!</li>");
						return false;							
					}
				}	
				return true;
			} else {
				return true;
			}
		}
	}
</script>
<form id="patientSearchForm">
	<div id="errorSection">
		
	</div>
	<table cellspacing="10">
		<tr>	
			<td>Name/Identifier</td>
			<td><input id="nameOrIdentifier" style="width:300px;"/></td>
			<td><a href="javascript:PATIENTSEARCH.toggleAdvanceSearch();">Advance search</a></td>
			<td id="searchLoader"></td>
		</tr>	
	</table>
	<div id="advanceSearch">
		<table cellspacing="10">
			<tr>
				<td>Gender</td>
				<td colspan="3">
					<select id="gender" style="width: 100px">
						<option value="Any">Any</option>
						<option value="M">Male</option>
						<option value="F">Female</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Age</td>
				<td>
					<input id="age" style="width: 100px"/>
				</td>				
				<td>Range &plusmn;</td>
				<td>
					<select id="ageRange" style="width: 100px">
						<option value="0">Exact</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
					<span id="rangeUnit"></span>
				</td>
			</tr>
			<tr>
				<td>Last Visit</td>
				<td colspan="3">
					<select id="lastVisit" style="width: 100px">
						<option value="Any">Anytime</option>
						<option value="31">Last month</option>
						<option value="183">Last 6 months</option>
						<option value="366">Last year</option>
					</select>
				</td>	
			</tr>
			<tr>
				<td>Relative Name</td>
				<td colspan="3">
					<input id="relativeName" style="width: 100px"/>
				</td>	
			</tr>
		</table>
	</div>	
</form>