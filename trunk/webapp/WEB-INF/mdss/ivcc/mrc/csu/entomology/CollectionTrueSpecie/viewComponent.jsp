<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecie.form.name" id="mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecie.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        Abstract Mosquito Collection
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.parent.keyName}" action="mdss.ivcc.mrc.csu.entomology.AbstractMosquitoCollectionController.view.mojo" name="mdss.ivcc.mrc.csu.entomology.AbstractMosquitoCollection.form.view.link">
        <mjl:property value="${item.parentId}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        True Specie Collection
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.child.keyName}" action="mdss.ivcc.mrc.csu.entomology.TrueSpecieEntityController.view.mojo" name="mdss.ivcc.mrc.csu.entomology.TrueSpecieEntity.form.view.link">
        <mjl:property value="${item.childId}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecieController.edit.mojo" name="mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecie.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink display="View All" action="mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecieController.viewAll.mojo" name="mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecie.viewAll.link" />
