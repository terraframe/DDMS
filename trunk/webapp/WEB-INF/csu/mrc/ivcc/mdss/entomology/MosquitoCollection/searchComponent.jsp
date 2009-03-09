<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<mjl:form name="csu.mrc.ivcc.mdss.entomology.MosquitoCollection.search" method="POST">
  <dl>
    <dt> Geo Id </dt>
    <dd> <mjl:input param="geoId" type="text" /></dd>
    <dt> Date </dt>
    <dd> <mjl:input param="collectionDate" type="text" classes="DatePick" id="collectionDate"/></dd>
  </dl>  
  <br>
  <br>
  <mjl:command classes="submitButton" action="csu.mrc.ivcc.mdss.entomology.MosquitoCollectionController.searchByGeoIdAndDate.mojo" name="search.button"
  value="Search"
  />
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div>

