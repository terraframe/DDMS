<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>
      Edit AbstractMosquitoCollection
    </title>
  </head>
  <body>
    <mjl:messages>
      <mjl:message />
    </mjl:messages>
    <mjl:form name="mdss.entomology.AbstractMosquitoCollection.form.name" id="mdss.entomology.AbstractMosquitoCollection.form.id" method="POST">
      <mjl:component item="${item}" param="dto">
        <dl>
          <dt>
            <label>
              ${item.dateCollectedMd.displayLabel}
            </label>
          </dt>
          <dd>
            <mjl:input type="text" param="dateCollected" />
            <mjl:messages attribute="dateCollected">
              <mjl:message />
            </mjl:messages>
          </dd>
          <dt>
            <label>
              ${item.geoEntityMd.displayLabel}
            </label>
          </dt>
          <dd>
            <mjl:select var="current" valueAttribute="id" items="${mdss_entomology_AbstractMosquitoCollection_geoEntity}" param="geoEntity">
              <mjl:option>
                ${current.keyName}
              </mjl:option>
            </mjl:select>
          </dd>
        </dl>
      </mjl:component>
      <mjl:command value="Update" action="mdss.entomology.AbstractMosquitoCollectionController.update.mojo" name="mdss.entomology.AbstractMosquitoCollection.form.update.button" />
      <mjl:command value="Delete" action="mdss.entomology.AbstractMosquitoCollectionController.delete.mojo" name="mdss.entomology.AbstractMosquitoCollection.form.delete.button" />
      <mjl:command value="Cancel" action="mdss.entomology.AbstractMosquitoCollectionController.cancel.mojo" name="mdss.entomology.AbstractMosquitoCollection.form.cancel.button" />
    </mjl:form>
  </body>
</html>
