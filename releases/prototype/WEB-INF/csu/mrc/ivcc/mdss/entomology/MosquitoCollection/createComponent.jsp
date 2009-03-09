<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="csu.mrc.ivcc.mdss.entomology.MosquitoCollection.form.name" id="csu.mrc.ivcc.mdss.entomology.MosquitoCollection.form.id" method="POST">
<div class="fldContainer">
    <div class="fcTop">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.collectionMethodMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${MosquitoCollection_collectionMethod}" param="collectionMethod">
          <mjl:option>
            ${current.termName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.dateCollectedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="dateCollected" classes="DatePick" id="dto.dateCollected.id"/>
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
        <mjl:select var="current" valueAttribute="id" items="${AbstractMosquitoCollection_geoEntity}" param="geoEntity">
          <mjl:option>
            ${current.geoId}
          </mjl:option>
        </mjl:select>
      </dd>
    </dl>
  </mjl:component>
   
<div class="fcTopLeft"></div></div>
    <div class="fcBottom"><div class="fcBottomLeft"></div></div>
</div>

<div class="submitButton_bl"></div>  
  <mjl:command value="Create" action="csu.mrc.ivcc.mdss.entomology.MosquitoCollectionController.create.mojo" name="csu.mrc.ivcc.mdss.entomology.MosquitoCollection.form.create.button" classes="submitButton"/>
</mjl:form>
<div id="cal1Container" class="yui-skin-sam"></div> 