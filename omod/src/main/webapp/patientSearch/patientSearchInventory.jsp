<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/headerMinimal.jsp" %>
<c:choose>
	<c:when test="${not empty patients}" >
	<span class="boxHeader">List Patients</span> 
	<table class="box">
		<tr>
			<th>Identifier</th>
			<th>Name</th>
			<th>Age</th>
		</tr>
		<c:forEach items="${patients}" var="patient" varStatus="varStatus">
			<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '  >
				<td ><a href="#" onclick="ISSUE.addPatient('createPatientIssueDrug.form?patientId=${patient.patientId}');">${patient.patientIdentifier.identifier}</a></td>
				<td>${patient.givenName} ${patient.middleName} ${patient.familyName}</td>
                <td>
                	<c:choose>
                		<c:when test="${patient.age == 0  }">&lt 1</c:when>
                		<c:otherwise >${patient.age }</c:otherwise>
                	</c:choose>
                </td>
			</tr>
		</c:forEach>
	</table>
	</c:when>
	<c:otherwise>
	No Patient found.
	</c:otherwise>
</c:choose>
<script>
	INVENTORY.initTableHover();
</script>

<%@ include file="patientSearchPaging.jsp"%>
