<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>
      View CollectionTrueSpecie
    </title>
  </head>
  <body>
    <mjl:messages>
      <mjl:message />
    </mjl:messages>
    <mjl:form name="mdss.entomology.CollectionTrueSpecie.form.name" id="mdss.entomology.CollectionTrueSpecie.form.id" method="POST">
      <mjl:input value="${item.id}" type="hidden" param="id" />
      <dl>
        <dt>
          <label>
            Abstract Mosquito Collection
          </label>
        </dt>
        <dd>
          <mjl:commandLink display="${item.parent.keyName}" action="mdss.entomology.AbstractMosquitoCollectionController.view.mojo" name="mdss.entomology.AbstractMosquitoCollection.form.view.link">
            <mjl:property value="${item.parentId}" name="id" />
          </mjl:commandLink>
        </dd>
        <dt>
          <label>
            True Specie Collection
          </label>
        </dt>
        <dd>
          <mjl:commandLink display="${item.child.keyName}" action="mdss.entomology.TrueSpecieEntityController.view.mojo" name="mdss.entomology.TrueSpecieEntity.form.view.link">
            <mjl:property value="${item.childId}" name="id" />
          </mjl:commandLink>
        </dd>
      </dl>
      <mjl:command value="Edit" action="mdss.entomology.CollectionTrueSpecieController.edit.mojo" name="mdss.entomology.CollectionTrueSpecie.form.edit.button" />
      <br />
    </mjl:form>
    <mjl:commandLink display="View All" action="mdss.entomology.CollectionTrueSpecieController.viewAll.mojo" name="mdss.entomology.CollectionTrueSpecie.viewAll.link" />
  </body>
</html>
