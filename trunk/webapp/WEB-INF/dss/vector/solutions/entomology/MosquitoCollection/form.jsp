<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="collectionMethod">
        <mjl:select var="current" valueAttribute="id" items="${MosquitoCollection_collectionMethod}" param="collectionMethod">
          <mjl:option>
            ${current.termName}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="dateCollected" type="text" classes="DatePick" />
      <dt><label> ${item.geoEntityMd.displayLabel} </label></dt>
      <dd>${item.geoEntity.geoId} (${item.geoEntity.entityName})
       <mjl:input type="hidden" param="geoEntity" id="dto.geoEntity.id" /> <mjl:messages attribute="geoEntity">
        <mjl:message />
      </mjl:messages></dd>
    </mjl:component>
