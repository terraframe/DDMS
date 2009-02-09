<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>
      View All Mosquito Objects
    </title>
  </head>
  <body>
    <mjl:messages>
      <mjl:message />
    </mjl:messages>
    <mjl:table var="item" query="${query}">
      <mjl:context action="mdss.entomology.MosquitoController.viewPage.mojo" />
      <mjl:columns>
        <mjl:attributeColumn attributeName="generation">
          <mjl:header>
            Generation
          </mjl:header>
        </mjl:attributeColumn>
        <mjl:attributeColumn attributeName="isofemale">
          <mjl:header>
            Isofemale
          </mjl:header>
        </mjl:attributeColumn>
        <mjl:attributeColumn attributeName="sex">
          <mjl:header>
            Sex
          </mjl:header>
        </mjl:attributeColumn>
        <mjl:attributeColumn attributeName="testDate">
          <mjl:header>
            Test Date
          </mjl:header>
        </mjl:attributeColumn>
        <mjl:attributeColumn attributeName="collection">
          <mjl:header>
            Mosquito Collection
          </mjl:header>
        </mjl:attributeColumn>
        <mjl:attributeColumn attributeName="collectionId">
          <mjl:header>
            Collection Id
          </mjl:header>
        </mjl:attributeColumn>
        <mjl:attributeColumn attributeName="identificationMethod">
          <mjl:header>
            Identification Method
          </mjl:header>
        </mjl:attributeColumn>
        <mjl:attributeColumn attributeName="specie">
          <mjl:header>
            Specie
          </mjl:header>
        </mjl:attributeColumn>
        <mjl:freeColumn>
          <mjl:header>
            
          </mjl:header>
          <mjl:row>
            <a href="mdss.entomology.MosquitoController.view.mojo?id=${item.id}">
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
    <mjl:commandLink display="Create a new Mosquito" action="mdss.entomology.MosquitoController.newInstance.mojo" name="MosquitoController.newInstance" />
  </body>
</html>
