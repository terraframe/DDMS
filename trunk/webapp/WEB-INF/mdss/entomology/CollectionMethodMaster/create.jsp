<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>
      New CollectionMethodMaster
    </title>
  </head>
  <body>
    <mjl:messages>
      <mjl:message />
    </mjl:messages>
    <mjl:form name="mdss.entomology.CollectionMethodMaster.form.name" id="mdss.entomology.CollectionMethodMaster.form.id" method="POST">
      <mjl:component item="${item}" param="dto">
        <dl>
          <dt>
            <label>
              ${item.displayLabelMd.displayLabel}
            </label>
          </dt>
          <dd>
            <mjl:input type="text" param="displayLabel" />
            <mjl:messages attribute="displayLabel">
              <mjl:message />
            </mjl:messages>
          </dd>
          <dt>
            <label>
              ${item.enumNameMd.displayLabel}
            </label>
          </dt>
          <dd>
            <mjl:input type="text" param="enumName" />
            <mjl:messages attribute="enumName">
              <mjl:message />
            </mjl:messages>
          </dd>
        </dl>
      </mjl:component>
      <mjl:command value="Create" action="mdss.entomology.CollectionMethodMasterController.create.mojo" name="mdss.entomology.CollectionMethodMaster.form.create.button" />
    </mjl:form>
  </body>
</html>
