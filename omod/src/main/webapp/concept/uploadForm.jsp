<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/hospitalcore/scripts/jquery/jquery-1.4.2.min.js"></script>
<c:choose>
	<c:when test="${empty diagnosisNo}">
		<script type="text/javascript">
			function submitForm(){
				$("#loading").show();
				$("#uploadFileForm").submit();
			}
		</script>
		<div id="loading" style="display:none;">
			Please wait while importing diagnosis. It takes time based on the number of diagnosis...
		</div>
		<form id="uploadFileForm" method="post" enctype="multipart/form-data">	
			<spring:bind path="uploadFile.diagnosisFile">
				Diagnosis: 
				<input type="file" name="${status.expression}" />
			</spring:bind><br/>
			<spring:bind path="uploadFile.synonymFile">
				Synonym: 
				<input type="file" name="${status.expression}" />
			</spring:bind><br/>
			<spring:bind path="uploadFile.mappingFile">
				Mapping: 
				<input type="file" name="${status.expression}" />
			</spring:bind><br/>
			<input type="button" value="Upload" onClick="submitForm();"/>
		</form>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${fail}">
				${error}
			</c:when>
			<c:otherwise>
				${diagnosisNo} diagnosis have been imported!
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>


<%@ include file="/WEB-INF/template/footer.jsp"%>


