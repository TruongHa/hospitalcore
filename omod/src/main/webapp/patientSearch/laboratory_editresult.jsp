<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/template/include.jsp"%>

<c:choose>
	<c:when test="${not empty patients}" >	
	<div class="boxHeader">Patients</div>
	<table class="box">
		<tr>
			<th>Identifier</th>
			<th>Name</th>
			<th>Gender</th>
			<th>Age</th>
		</tr>
		<c:forEach items="${patients}" var="patient" varStatus="varStatus">
			<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" }'>
				<td>
					<a href="#" onClick="getTests('${patient.patientIdentifier.identifier}');">
						${patient.patientIdentifier.identifier}
					</a>
				</td>
				<td>${patient.givenName} ${patient.middleName} ${patient.familyName}</td>
				<td>
					<c:choose>
                		<c:when test="${patient.gender eq 'M'}">
							<img src="${pageContext.request.contextPath}/images/male.gif"/>
						</c:when>
                		<c:otherwise><img src="${pageContext.request.contextPath}/images/female.gif"/></c:otherwise>
                	</c:choose>
				</td>
                <td>
                	<c:choose>
                		<c:when test="${patient.age == 0}">&lt 1</c:when>
                		<c:otherwise >${patient.age}</c:otherwise>
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
<%@ include file="patientSearchPaging.jsp"%>