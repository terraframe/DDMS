<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>



    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="collectionMethod">
        <mdss:mo param="collectionMethod" value="${collectionMethod}"/>
      </mjl:dt>
      <mjl:dt attribute="dateCollected" type="text" classes="DatePick" />
      <dt><label> ${item.geoEntityMd.displayLabel} </label></dt>
      <dd>${item.geoEntity.displayString} 
       <mjl:input type="hidden" param="geoEntity" id="dto.geoEntity.id" value="${item.geoEntity.id}" /> <mjl:messages attribute="geoEntity">
        <mjl:message />
      </mjl:messages></dd>
    </mjl:component>