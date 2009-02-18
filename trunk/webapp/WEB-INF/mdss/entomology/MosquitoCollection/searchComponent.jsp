<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<mjl:form name="mdss.entomology.MosquitoCollection.search">
  <dl>
    <dt> Geo Id </dt>
    <dd> <mjl:input param="geoId" type="text" /></dd>
    <dt> Date </dt>
    <dd> <mjl:input param="collectionDate" type="text" classes="DatePick" /></dd>
  </dl>  
  <mjl:command action="mdss.entomology.MosquitoCollectionController.searchByGeoIdAndDate" name="search.button"/>
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div>