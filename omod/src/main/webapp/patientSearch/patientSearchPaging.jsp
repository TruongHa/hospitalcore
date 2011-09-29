<br/><br/>${size} patients found.
<c:if test="${not empty prevPage}">
<a href="#" onClick="ADVSEARCH.searchPatient(${prevPage}, 10)">
	&laquo;&laquo; Previous
</a>
</c:if>
&nbsp;&nbsp;
page ${currentPage + 1}
&nbsp;&nbsp;
<c:if test="${not empty nextPage}">
	<a href="#" onClick="ADVSEARCH.searchPatient(${nextPage}, 10)">
		Next &raquo;&raquo;
	</a>
</c:if>


