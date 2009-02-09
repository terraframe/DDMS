<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>
      Edit CollectionTrueSpecie
    </title>
  </head>
  <body>
    <mjl:messages>
      <mjl:message />
    </mjl:messages>
    <mjl:form name="mdss.entomology.CollectionTrueSpecie.form.name" id="mdss.entomology.CollectionTrueSpecie.form.id" method="POST">
      <mjl:component item="${item}" param="dto">
        <dl>
        </dl>
      </mjl:component>
      <mjl:command value="Update" action="mdss.entomology.CollectionTrueSpecieController.update.mojo" name="mdss.entomology.CollectionTrueSpecie.form.update.button" />
      <mjl:command value="Delete" action="mdss.entomology.CollectionTrueSpecieController.delete.mojo" name="mdss.entomology.CollectionTrueSpecie.form.delete.button" />
      <mjl:command value="Cancel" action="mdss.entomology.CollectionTrueSpecieController.cancel.mojo" name="mdss.entomology.CollectionTrueSpecie.form.cancel.button" />
    </mjl:form>
  </body>
</html>
