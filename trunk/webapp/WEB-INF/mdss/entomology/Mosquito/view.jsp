<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>
      View Mosquito
    </title>
  </head>
  <body>
    <mjl:messages>
      <mjl:message />
    </mjl:messages>
    <mjl:form name="mdss.entomology.Mosquito.form.name" id="mdss.entomology.Mosquito.form.id" method="POST">
      <mjl:input value="${item.id}" type="hidden" param="id" />
      <dl>
        <dt>
          <label>
            ${item.generationMd.displayLabel}
          </label>
        </dt>
        <dd>
          <ul>
            <c:forEach var="enumName" items="${item.generationEnumNames}">
              <li>
                ${item.generationMd.enumItems[enumName]}
              </li>
            </c:forEach>
          </ul>
        </dd>
        <dt>
          <label>
            ${item.isofemaleMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.isofemale}
        </dd>
        <dt>
          <label>
            ${item.sexMd.displayLabel}
          </label>
        </dt>
        <dd>
          <ul>
            <c:forEach var="enumName" items="${item.sexEnumNames}">
              <li>
                ${item.sexMd.enumItems[enumName]}
              </li>
            </c:forEach>
          </ul>
        </dd>
        <dt>
          <label>
            ${item.testDateMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.testDate}
        </dd>
        <dt>
          <label>
            ${item.collectionMd.displayLabel}
          </label>
        </dt>
        <dd>
          <mjl:commandLink display="${item.collection.keyName}" action="mdss.entomology.AbstractMosquitoCollectionController.view.mojo" name="mdss.entomology.AbstractMosquitoCollection.form.view.link">
            <mjl:property value="${item.collection.id}" name="id" />
          </mjl:commandLink>
        </dd>
        <dt>
          <label>
            ${item.collectionIdMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.collectionId}
        </dd>
        <dt>
          <label>
            ${item.identificationMethodMd.displayLabel}
          </label>
        </dt>
        <dd>
          <ul>
            <c:forEach var="enumName" items="${item.identificationMethodEnumNames}">
              <li>
                ${item.identificationMethodMd.enumItems[enumName]}
              </li>
            </c:forEach>
          </ul>
        </dd>
        <dt>
          <label>
            ${item.specieMd.displayLabel}
          </label>
        </dt>
        <dd>
          <ul>
            <c:forEach var="enumName" items="${item.specieEnumNames}">
              <li>
                ${item.specieMd.enumItems[enumName]}
              </li>
            </c:forEach>
          </ul>
        </dd>
      </dl>
      <mjl:command value="Edit" action="mdss.entomology.MosquitoController.edit.mojo" name="mdss.entomology.Mosquito.form.edit.button" />
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
            <mjl:commandLink display="Abstract Mosqito Collection" action="mdss.entomology.CollectionTrueSpecieController.childQuery.mojo" name="mdss.entomology.CollectionTrueSpecie.childQuery.link">
              <mjl:property value="${item.id}" name="childId" />
            </mjl:commandLink>
          </li>
        </ul>
      </dd>
    </dl>
    <mjl:commandLink display="View All" action="mdss.entomology.MosquitoController.viewAll.mojo" name="mdss.entomology.Mosquito.viewAll.link" />
  </body>
</html>
