<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>
      New GeoEntity
    </title>
  </head>
  <body>
    <mjl:messages>
      <mjl:message />
    </mjl:messages>
    <mjl:form name="mdss.test.GeoEntity.form.name" id="mdss.test.GeoEntity.form.id" method="POST">
      <mjl:component item="${item}" param="dto">
        <dl>
          <dt>
            <label>
              ${item.entityNameMd.displayLabel}
            </label>
          </dt>
          <dd>
            <mjl:input type="text" param="entityName" />
            <mjl:messages attribute="entityName">
              <mjl:message />
            </mjl:messages>
          </dd>
          <dt>
            <label>
              ${item.terrainMd.displayLabel}
            </label>
          </dt>
          <dd>
            <mjl:select var="current" valueAttribute="enumName" items="${mdss_test_GeoEntity_terrain}" param="terrain">
              <c:choose>
                <c:when test="${mjl:contains(item.terrainEnumNames, current.enumName)}">
                  <mjl:option selected="selected">
                    ${item.terrainMd.enumItems[current.enumName]}
                  </mjl:option>
                </c:when>
                <c:otherwise>
                  <mjl:option>
                    ${item.terrainMd.enumItems[current.enumName]}
                  </mjl:option>
                </c:otherwise>
              </c:choose>
            </mjl:select>
          </dd>
        </dl>
      </mjl:component>
      <mjl:command value="Create" action="mdss.test.GeoEntityController.create.mojo" name="mdss.test.GeoEntity.form.create.button" />
    </mjl:form>
  </body>
</html>
