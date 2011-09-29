<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ include file="/WEB-INF/template/include.jsp"%>

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
			<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '  onclick="QUEUE.selectPatientInSystem(${patient.patientId})">
				<td >${patient.patientIdentifier.identifier}</td>
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
	QUEUE.initTableHover();
</script>

<%@ include file="patientSearchPaging.jsp"%>
