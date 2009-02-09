<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>
      New MorphologicalSpecieGroup
    </title>
  </head>
  <body>
    <mjl:messages>
      <mjl:message />
    </mjl:messages>
    <mjl:form name="mdss.entomology.MorphologicalSpecieGroup.form.name" id="mdss.entomology.MorphologicalSpecieGroup.form.id" method="POST">
      <mjl:component item="${item}" param="dto">
        <dl>
          <dt>
            <label>
              ${item.collectionMd.displayLabel}
            </label>
          </dt>
          <dd>
            <mjl:select var="current" valueAttribute="id" items="${mdss_entomology_MorphologicalSpecieGroup_collection}" param="collection">
              <mjl:option>
                ${current.keyName}
              </mjl:option>
            </mjl:select>
          </dd>
          <dt>
            <label>
              ${item.identificationMethodMd.displayLabel}
            </label>
          </dt>
          <dd>
            <mjl:select var="current" valueAttribute="enumName" items="${mdss_entomology_MorphologicalSpecieGroup_identificationMethod}" param="identificationMethod">
              <c:choose>
                <c:when test="${mjl:contains(item.identificationMethodEnumNames, current.enumName)}">
                  <mjl:option selected="selected">
                    ${item.identificationMethodMd.enumItems[current.enumName]}
                  </mjl:option>
                </c:when>
                <c:otherwise>
                  <mjl:option>
                    ${item.identificationMethodMd.enumItems[current.enumName]}
                  </mjl:option>
                </c:otherwise>
              </c:choose>
            </mjl:select>
          </dd>
          <dt>
            <label>
              ${item.quanityMd.displayLabel}
            </label>
          </dt>
          <dd>
            <mjl:input type="text" param="quanity" />
            <mjl:messages attribute="quanity">
              <mjl:message />
            </mjl:messages>
          </dd>
          <dt>
            <label>
              ${item.specieMd.displayLabel}
            </label>
          </dt>
          <dd>
            <mjl:select var="current" valueAttribute="enumName" items="${mdss_entomology_MorphologicalSpecieGroup_specie}" param="specie">
              <c:choose>
                <c:when test="${mjl:contains(item.specieEnumNames, current.enumName)}">
                  <mjl:option selected="selected">
                    ${item.specieMd.enumItems[current.enumName]}
                  </mjl:option>
                </c:when>
                <c:otherwise>
                  <mjl:option>
                    ${item.specieMd.enumItems[current.enumName]}
                  </mjl:option>
                </c:otherwise>
              </c:choose>
            </mjl:select>
          </dd>
        </dl>
      </mjl:component>
      <mjl:command value="Create" action="mdss.entomology.MorphologicalSpecieGroupController.create.mojo" name="mdss.entomology.MorphologicalSpecieGroup.form.create.button" />
    </mjl:form>
  </body>
</html>
