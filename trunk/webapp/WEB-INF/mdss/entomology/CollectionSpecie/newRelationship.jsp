<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>
      Parent/Child Selection
    </title>
  </head>
  <body>
    <mjl:form name="mdss.entomology.CollectionSpecie.form.name" id="mdss.entomology.CollectionSpecie.form.id" method="POST">
      <dl>
        <dt>
          <label>
            Abstract Mosquito Collection
          </label>
        </dt>
        <dd>
          <mjl:select var="current" valueAttribute="id" items="${parentList}" param="parentId">
            <mjl:option>
              ${current.keyName}
            </mjl:option>
          </mjl:select>
        </dd>
        <dt>
          <label>
            Morphological Specie Group
          </label>
        </dt>
        <dd>
          <mjl:select var="current" valueAttribute="id" items="${childList}" param="childId">
            <mjl:option>
              ${current.keyName}
            </mjl:option>
          </mjl:select>
        </dd>
      </dl>
      <mjl:command value="New Instance" action="mdss.entomology.CollectionSpecieController.newInstance.mojo" name="mdss.entomology.CollectionSpecie.form.newInstance.button" />
    </mjl:form>
  </body>
</html>
