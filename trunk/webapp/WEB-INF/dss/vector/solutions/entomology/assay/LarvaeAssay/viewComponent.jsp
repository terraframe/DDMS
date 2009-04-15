<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.LarvaeAssay.form.name" id="dss.vector.solutions.entomology.assay.LarvaeAssay.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <mjl:dt attribute="endPoint">
<mjl:commandLink display="${item.endPoint.keyName}" action="dss.vector.solutions.mo.LarvaeAgeController.view.mojo" name="dss.vector.solutions.mo.LarvaeAge.form.view.link">
        <mjl:property value="${item.endPoint.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="startPoint">
<mjl:commandLink display="${item.startPoint.keyName}" action="dss.vector.solutions.mo.LarvaeAgeController.view.mojo" name="dss.vector.solutions.mo.LarvaeAge.form.view.link">
        <mjl:property value="${item.startPoint.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="collection">
<mjl:commandLink display="${item.collection.keyName}" action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo" name="dss.vector.solutions.entomology.MosquitoCollection.form.view.link">
        <mjl:property value="${item.collection.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="exposureTime">
      ${item.exposureTime}
</mjl:dt>
    <mjl:dt attribute="generation">
<mjl:commandLink display="${item.generation.keyName}" action="dss.vector.solutions.mo.GenerationController.view.mojo" name="dss.vector.solutions.mo.Generation.form.view.link">
        <mjl:property value="${item.generation.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="identificationMethod">
<mjl:commandLink display="${item.identificationMethod.keyName}" action="dss.vector.solutions.mo.IdentificationMethodController.view.mojo" name="dss.vector.solutions.mo.IdentificationMethod.form.view.link">
        <mjl:property value="${item.identificationMethod.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="intervalTime">
      ${item.intervalTime}
</mjl:dt>
    <mjl:dt attribute="isofemale">
      ${item.isofemale}
</mjl:dt>
    <mjl:dt attribute="quantityTested">
      ${item.quantityTested}
</mjl:dt>
    <mjl:dt attribute="testMethod">
<mjl:commandLink display="${item.testMethod.keyName}" action="dss.vector.solutions.mo.ResistanceMethodologyController.view.mojo" name="dss.vector.solutions.mo.ResistanceMethodology.form.view.link">
        <mjl:property value="${item.testMethod.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="insecticide">
<mjl:commandLink display="${item.insecticide.keyName}" action="dss.vector.solutions.general.InsecticideController.view.mojo" name="dss.vector.solutions.general.Insecticide.form.view.link">
        <mjl:property value="${item.insecticide.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="specie">
<mjl:commandLink display="${item.specie.keyName}" action="dss.vector.solutions.mo.SpecieController.view.mojo" name="dss.vector.solutions.mo.Specie.form.view.link">
        <mjl:property value="${item.specie.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="testDate">
      ${item.testDate}
</mjl:dt>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.LarvaeAssayController.edit.mojo" name="dss.vector.solutions.entomology.assay.LarvaeAssay.form.edit.button" />
  <br />
  </mjl:component>
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.assay.LarvaeAssayController.viewAll.mojo" name="dss.vector.solutions.entomology.assay.LarvaeAssay.viewAll.link" />
