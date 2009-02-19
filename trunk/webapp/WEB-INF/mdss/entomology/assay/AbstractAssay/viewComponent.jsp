<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.entomology.assay.AbstractAssay.form.name" id="mdss.entomology.assay.AbstractAssay.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.collectionMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.collection.keyName}" action="mdss.entomology.MosquitoCollectionController.view.mojo" name="mdss.entomology.MosquitoCollection.form.view.link">
        <mjl:property value="${item.collection.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.identificationMethodMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.identificationMethod.keyName}" action="mdss.entomology.IdentificationMethodController.view.mojo" name="mdss.entomology.IdentificationMethod.form.view.link">
        <mjl:property value="${item.identificationMethod.id}" name="id" />
      </mjl:commandLink>
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
        ${item.specieMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.specie.keyName}" action="mdss.entomology.SpecieController.view.mojo" name="mdss.entomology.Specie.form.view.link">
        <mjl:property value="${item.specie.id}" name="id" />
      </mjl:commandLink>
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
        ${item.testMethodMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.testMethod.keyName}" action="mdss.entomology.AssayMethodController.view.mojo" name="mdss.entomology.AssayMethod.form.view.link">
        <mjl:property value="${item.testMethod.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="mdss.entomology.assay.AbstractAssayController.edit.mojo" name="mdss.entomology.assay.AbstractAssay.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="mdss.entomology.assay.AbstractAssayController.viewAll.mojo" name="mdss.entomology.assay.AbstractAssay.viewAll.link" />
