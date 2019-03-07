<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <p><a href="controller?action=viewCarList">[Return to List]</a></p>
  
  <form:form method="post" action="controller" commandName="car">
  <input type="hidden" name="action" value="saveCar" />
  <input type="hidden" name="id" value="${car.id}<%-- Set this value to id property of car attribute --%>" />

  <table>
    <!-- input fields -->
      <td><form:input type="text" path="make"  value="${car.make}" /></td>
    </tr>  
    <tr>  
      <td><form:input type="text" path="model" value="${car.model}" /></td>
    </tr>
    <tr>
      <td><form:input type="text" path="modelYear" value="${car.modelYear}" /></td>
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