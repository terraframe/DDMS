<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>
      View AbstractMosquitoCollection
    </title>
  </head>
  <body>
    <mjl:messages>
      <mjl:message />
    </mjl:messages>
    <mjl:form name="mdss.entomology.AbstractMosquitoCollection.form.name" id="mdss.entomology.AbstractMosquitoCollection.form.id" method="POST">
      <mjl:input value="${item.id}" type="hidden" param="id" />
      <dl>
        <dt>
          <label>
            ${item.dateCollectedMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.dateCollected}
        </dd>
        <dt>
          <label>
            ${item.geoEntityMd.displayLabel}
          </label>
        </dt>
        <dd>
          <mjl:commandLink display="${item.geoEntity.keyName}" action="mdss.test.GeoEntityController.view.mojo" name="mdss.test.GeoEntity.form.view.link">
            <mjl:property value="${item.geoEntity.id}" name="id" />
          </mjl:commandLink>
        </dd>
      </dl>
      <mjl:command value="Edit" action="mdss.entomology.AbstractMosquitoCollectionController.edit.mojo" name="mdss.entomology.AbstractMosquitoCollection.form.edit.button" />
      <br />
    </mjl:form>
    <dl>
      <dt>
        <label>
          Parent Relationships
        </label>
      </dt>
      <dd>
        <ul>
          <li>
            <mjl:commandLink display="SpecieRow" action="mdss.entomology.CollectionSpecieController.parentQuery.mojo" name="mdss.entomology.CollectionSpecie.parentQuery.link">
              <mjl:property value="${item.id}" name="parentId" />
            </mjl:commandLink>
          </li>
          <li>
            <mjl:commandLink display="True Specie Collection" action="mdss.entomology.CollectionTrueSpecieController.parentQuery.mojo" name="mdss.entomology.CollectionTrueSpecie.parentQuery.link">
              <mjl:property value="${item.id}" name="parentId" />
            </mjl:commandLink>
          </li>
        </ul>
      </dd>
    </dl>
    <mjl:commandLink display="View All" action="mdss.entomology.AbstractMosquitoCollectionController.viewAll.mojo" name="mdss.entomology.AbstractMosquitoCollection.viewAll.link" />
  </body>
</html>
