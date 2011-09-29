

var EVT =
{
	ready : function()
	{
		/**
		 * Page Actions
		 */
		var enableCheck = true;
		var pageId = jQuery("#pageId").val();
		if(enableCheck && pageId != undefined && eval("CHECK." + pageId))
		{
			eval("CHECK." + pageId + "()");
		}
		jQuery('.date-pick').datepicker({minDate: '-100y', dateFormat: 'dd/mm/yy'});

		/**
		 * Ajax Indicator when send and receive data
		 */
		if(jQuery.browser.msie)
		{
			jQuery.ajaxSetup({cache: false});
		}
	
	}
};

var CHECK = 
{
	pageDepartment : function()
	{
		var validator = jQuery("#formDepartment").validate(
		{
			event : "blur",
			rules : 
			{
			
				"name" : { required : true},
				"wards" : { required : true}
			}
		});
	},
	pageDepartmentConcept : function()
	{
		
		jQuery("#diagnosis").autocomplete('autoCompleteDiagnosis.htm', {
			delay:1000,
			scroll: true,
			 parse: function(xml){
	                var results = [];
	                $(xml).find('item').each(function() {
	                    var text = $.trim($(this).find('text').text());
	                    var value = $.trim($(this).find('value').text());
	                    results[results.length] = { 'data': { text: text, value: value },
	                        'result': text, 'value': value
	                    };
	                });
	                return results;

			 },
			formatItem: function(data) {
				  return data.text;
			},
			formatResult: function(data) {
			      return data.text;
			}
			  
			}).result(function(event, item) {
				HOSPITALCORE.onChangeDiagnosis('diagnosis',item.value);
			});
		
		
		jQuery("#procedure").autocomplete('autoCompleteProcedure.htm', {
			delay:1000,
			scroll: true,
			 parse: function(xml){
	                var results = [];
	                $(xml).find('item').each(function() {
	                    var text = $.trim($(this).find('text').text());
	                    var value = $.trim($(this).find('value').text());
	                    results[results.length] = { 'data': { text: text, value: value },
	                        'result': text, 'value': value
	                    };
	                });
	                return results;

			 },
			formatItem: function(data) {
				  return data.text;
			},
			formatResult: function(data) {
			      return data.text;
			}
			  
			}).result(function(event, item) {
				HOSPITALCORE.onChangeDiagnosis('procedure',item.value);
			});

		var validator = jQuery("#formDepartmentConcept").validate(
		{
			event : "blur",
			rules : 
			{
				"selectedDiagnosisList" : { required : true},
				"selectedProcedureList" : { required : true},
				"departmentId" : { required : true}
			}
		});
	}
	
};

/**
 * Pageload actions trigger
 */

jQuery(document).ready(
	function() 
	{
		EVT.ready();
	}
);



