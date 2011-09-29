<%@ include file="/WEB-INF/template/include.jsp" %>
<?xml version="1.0"?>
<items>
<c:choose>
<c:when test="${not empty diagnosis}">
<c:forEach items="${diagnosis}" var="diag" varStatus="loop">
  <item>
    <text>${diag.name}</text>
    <value>${diag.id}</value>
  </item>
</c:forEach>  
</c:when>
</c:choose>

</items>