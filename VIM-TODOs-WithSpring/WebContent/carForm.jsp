<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><%@page errorPage="WEB-INF/showErrorMessage.jsp" %><html><head>  <link rel="stylesheet" type="text/css" href="default.css"></head><body>	<p><%@include file="showImage.jsp"%></p>
  <p><a href="controller?action=viewCarList">[Return to List]</a></p>
      
  <%-- <form method="post" action="controller"> --%>  <form:form action="controller" method="post" commandName="car">
  <input type="hidden" name="action" value="saveCar" />
  <form:input path="id" type="hidden" />

  <table>
    <!-- input fields -->    <tr>      <td>Make<font color="red"><sup>*</sup></font> </td>
      <td><form:input path="make" /></td>
    </tr>  
    <tr>        <td>Model</td>
      <td><form:input path="model" /></td>
    </tr>
    <tr>      <td class="model-year">Model Year</td>
      <td><form:input path="modelYear" /></td>
    </tr>
    
    <!-- Save/Reset buttons -->
    <tr>
      <td colspan="2">
        <input type="submit" name="save" value="Save" /> 
        &nbsp;&nbsp;
        <input type="reset" name="reset" value="Reset" />
      </td>
    </tr>                  </table>
  </form:form></body></html>