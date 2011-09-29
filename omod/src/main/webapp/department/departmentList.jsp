<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="Manage department" otherwise="/login.htm" redirect="/module/hospitalcore/departmentList.form" />

<spring:message var="pageTitle" code="hospitalcore.department.manage" scope="page"/>

<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="../includes/js_css.jsp" %>
<h2><spring:message code="hospitalcore.department.manage"/></h2>	

<br />
<c:forEach items="${errors.allErrors}" var="error">
	<span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><
</c:forEach>
<input type="button" value="<spring:message code='hospitalcore.department.add'/>" onclick="ACT.go('department.form');"/>

<br /><br />
<form method="get" >
<span class="boxHeader"><spring:message code="hospitalcore.department.list"/></span>
<div class="box">
<c:choose>
<c:when test="${not empty departments}">
<table cellpadding="5" cellspacing="0" width="100%">
<tr align="center">
	<th>#</th>
	<th><spring:message code="hospitalcore.department.name"/></th>
	<th><spring:message code="hospitalcore.department.ward"/></th>
	<th><spring:message code="hospitalcore.department.retired"/></th>
	<th><spring:message code="hospitalcore.department.createdOn"/></th>
	<th><spring:message code="hospitalcore.department.createdBy"/></th>
	<th></th>
</tr>
<c:forEach items="${departments}" var="department" varStatus="varStatus">
	<tr align="center" class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '>
		<td><c:out value="${ varStatus.count }"/></td>	
		<td><a href="#" onclick="ACT.go('department.form?departmentId=${ department.id}');">${department.name}</a> </td>
		<td>
			<c:forEach items="${department.wards}" var="ward" >
				${ward.name}<br/>
			</c:forEach>
		
		</td>
		<td>${department.retired }</td>
		<td><openmrs:formatDate date="${department.createdOn}" type="textbox"/></td>
		<td>${department.createdBy}</td>
		<td>
			<a href="#" title="Add|View|Edit concept to department" onclick="ACT.go('departmentConcept.form?dId=${ department.id}');">Add|View|Edit concept</a>
		</td>
	</tr>
</c:forEach>

</table>
</c:when>
<c:otherwise>
	No department found.
</c:otherwise>
</c:choose>

</div>
</form>


<%@ include file="/WEB-INF/template/footer.jsp" %>