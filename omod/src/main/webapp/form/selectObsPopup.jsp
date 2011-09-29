<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="../includes/js_css.jsp" %>

<script type="text/javascript">	
	function getContextPath() {
		pn = location.pathname;
		len = pn.indexOf("/", 1);
		cp = pn.substring(0, len);
		return cp;
	}
	
	jQuery(document).ready(function(){		
		jQuery("#conceptPopup").autocomplete(getContextPath() + '/module/radiology/ajax/autocompleteConceptSearch.htm').result(function(event, item){window.parent.insertObs(jQuery('#conceptPopup').val(), '${type}'); tb_remove();});
		jQuery("#conceptPopup").focus();
	});
</script>

<center>
	<table cellspacing="20">
		<tr>
			<td>
				<b>Concept</b>
			</td>
			<td>
				<input id="conceptPopup" style="width:350px;"/>
			</td>
		</tr>
	</table>
	<input type="button" onClick="javascript:window.parent.insertObs(jQuery('#conceptPopup').val()); tb_remove();" value="Insert"/>
	<input type="button" onClick="tb_remove();" value="Close"/>
</center>