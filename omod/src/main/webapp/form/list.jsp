<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="../includes/js_css.jsp" %>

<a href="editForm.form">Add new form</a>

<table id="myTable" class="tablesorter">
	<thead>
		<tr>			
			<th>Name</th>
			<th>Description</th>
			<th width="200px;"></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="form" items="${forms}" varStatus="index">
			<c:choose>
				<c:when test="${index.count mod 2 == 0}">
					<c:set var="klass" value="odd"/>
				</c:when>					
				<c:otherwise>
					<c:set var="klass" value="even"/>
				</c:otherwise>
			</c:choose>
			<tr class="${klass}">				
				<td>
					<a href="editForm.form?id=${form.id}">${form.name}</a>
				</td>
				<td>
					${form.description}
				</td>
				<td>
					<center>
						<a href="deleteForm.form?id=${form.id}">
							Delete
						</a>
						&nbsp;&nbsp;|&nbsp;&nbsp;
						<a href="showForm.form?id=${form.id}&mode=preview&height=600&width=800" class="thickbox">
							Preview
						</a>
					</center>
				</td>
			</tr>	
		</c:forEach>
	</tbody>
</table>

<%@ include file="/WEB-INF/template/footer.jsp" %>  