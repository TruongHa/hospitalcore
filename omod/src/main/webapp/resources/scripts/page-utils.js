HOSPITALCORE={
		onChangeDiagnosis : function(container, id)
		{
			if(container == 'diagnosis'){
				jQuery("#availableDiagnosisList option[value=" +id+ "]").appendTo("#selectedDiagnosisList");
				jQuery("#availableDiagnosisList option[value=" +id+ "]").remove();
				jQuery("#diagnosis").val("");
			}
			if(container == 'procedure'){
				jQuery("#availableProcedureList option[value=" +id+ "]").appendTo("#selectedProcedureList");
				jQuery("#availableProcedureList option[value=" +id+ "]").remove();
				jQuery("#procedure").val("");
			}
		},
		onChangeDepartment : function(thiz)
		{
			ACT.go('departmentConcept.form?dId='+jQuery(thiz).val());
		},
		submit : function(){
			jQuery('#selectedDiagnosisList option').each(function(i) {  
				 jQuery(this).attr("selected", "selected");  
			}); 
			jQuery('#selectedProcedureList option').each(function(i) {  
				 jQuery(this).attr("selected", "selected");  
			}); 
			jQuery("#formDepartmentConcept").submit();
		}
		
};


