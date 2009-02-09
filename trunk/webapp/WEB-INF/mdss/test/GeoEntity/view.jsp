<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>
      View GeoEntity
    </title>
  </head>
  <body>
    <mjl:messages>
      <mjl:message />
    </mjl:messages>
    <mjl:form name="mdss.test.GeoEntity.form.name" id="mdss.test.GeoEntity.form.id" method="POST">
      <mjl:input value="${item.id}" type="hidden" param="id" />
      <dl>
        <dt>
          <label>
            ${item.entityNameMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.entityName}
        </dd>
        <dt>
          <label>
            ${item.terrainMd.displayLabel}
          </label>
        </dt>
        <dd>
          <ul>
            <c:forEach var="enumName" items="${item.terrainEnumNames}">
              <li>
                ${item.terrainMd.enumItems[enumName]}
              </li>
            </c:forEach>
          </ul>
        </dd>
      </dl>
      <mjl:command value="Edit" action="mdss.test.GeoEntityController.edit.mojo" name="mdss.test.GeoEntity.form.edit.button" />
      <br />
    </mjl:form>
    <dl>
    </dl>
    <mjl:commandLink display="View All" action="mdss.test.GeoEntityController.viewAll.mojo" name="mdss.test.GeoEntity.viewAll.link" />
  </body>
</html>
