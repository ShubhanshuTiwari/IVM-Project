<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <p><a href="controller?action=viewCarList">[Return to List]</a></p>
  
  <%-- <form method="post" action="controller"> --%>
  <input type="hidden" name="action" value="saveCar" />
  <form:input path="id" type="hidden" />

  <table>
    <!-- input fields -->
      <td><form:input path="make" /></td>
    </tr>  
    <tr>  
      <td><form:input path="model" /></td>
    </tr>
    <tr>
      <td><form:input path="modelYear" /></td>
    </tr>
    
    <!-- Save/Reset buttons -->
    <tr>
      <td colspan="2">
        <input type="submit" name="save" value="Save" /> 
        &nbsp;&nbsp;
        <input type="reset" name="reset" value="Reset" />
      </td>
    </tr>                
  </form:form>