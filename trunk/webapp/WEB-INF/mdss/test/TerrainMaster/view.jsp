<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>
      View TerrainMaster
    </title>
  </head>
  <body>
    <mjl:messages>
      <mjl:message />
    </mjl:messages>
    <mjl:form name="mdss.test.TerrainMaster.form.name" id="mdss.test.TerrainMaster.form.id" method="POST">
      <mjl:input value="${item.id}" type="hidden" param="id" />
      <dl>
        <dt>
          <label>
            ${item.displayLabelMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.displayLabel}
        </dd>
        <dt>
          <label>
            ${item.enumNameMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.enumName}
        </dd>
      </dl>
      <mjl:command value="Edit" action="mdss.test.TerrainMasterController.edit.mojo" name="mdss.test.TerrainMaster.form.edit.button" />
      <br />
    </mjl:form>
    <dl>
      <dt>
        <label>
          Child Relationships
        </label>
      </dt>
      <dd>
        <ul>
          <li>
            <mjl:commandLink display="" action="com.terraframe.mojo.system.metadata.EnumerationAttributeItemController.childQuery.mojo" name="com.terraframe.mojo.system.metadata.EnumerationAttributeItem.childQuery.link">
              <mjl:property value="${item.id}" name="childId" />
            </mjl:commandLink>
          </li>
        </ul>
      </dd>
    </dl>
    <mjl:commandLink display="View All" action="mdss.test.TerrainMasterController.viewAll.mojo" name="mdss.test.TerrainMaster.viewAll.link" />
  </body>
</html>
