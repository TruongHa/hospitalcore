<%@ include file="/WEB-INF/template/include.jsp" %>
<?xml version="1.0"?>
<items>
<c:choose>
<c:when test="${not empty procedures}">
<c:forEach items="${procedures}" var="pro" varStatus="loop">
  <item>
    <text>${pro.name}</text>
    <value>${pro.id}</value>
  </item>
</c:forEach>  
</c:when>
</c:choose>

</items>