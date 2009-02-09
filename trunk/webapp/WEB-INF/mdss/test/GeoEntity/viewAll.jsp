<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>
      View All GeoEntity Objects
    </title>
  </head>
  <body>
    <mjl:messages>
      <mjl:message />
    </mjl:messages>
    <mjl:table var="item" query="${query}">
      <mjl:context action="mdss.test.GeoEntityController.viewPage.mojo" />
      <mjl:columns>
        <mjl:attributeColumn attributeName="entityName">
          <mjl:header>
            Geo Entity Name
          </mjl:header>
        </mjl:attributeColumn>
        <mjl:attributeColumn attributeName="terrain">
          <mjl:header>
            Terrain
          </mjl:header>
        </mjl:attributeColumn>
        <mjl:freeColumn>
          <mjl:header>
            
          </mjl:header>
          <mjl:row>
            <a href="mdss.test.GeoEntityController.view.mojo?id=${item.id}">
              view
            </a>
          </mjl:row>
          <mjl:footer>
            
          </mjl:footer>
        </mjl:freeColumn>
      </mjl:columns>
      <mjl:pagination>
        <mjl:page />
      </mjl:pagination>
    </mjl:table>
    <br />
    <mjl:commandLink display="Create a new Geo Entity" action="mdss.test.GeoEntityController.newInstance.mojo" name="GeoEntityController.newInstance" />
  </body>
</html>
