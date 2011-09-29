<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:choose>
	<c:when test="${type eq 'textbox'}">
		<input type="text" name="${obsName}" value="" title="${obsName}"/>
	</c:when>
	<c:when test="${type eq 'selection'}">
		<select name="${obsName}" title="${obsName}">
			<option value=''>Please select</option>
			<c:forEach var="option" items="${options}">
				<option value="${option}">${option}</option>
			</c:forEach>
		</select>
	</c:when>
	<c:when test="${type eq 'radio'}">
		<c:forEach var="option" items="${options}">
			<span><input type="radio" name="${obsName}" value="${option}" title="${obsName}"> ${option}</span>
		</c:forEach>
	</c:when>
</c:choose>