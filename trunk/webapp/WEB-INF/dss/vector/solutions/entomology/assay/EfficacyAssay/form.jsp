<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
      <mjl:dt attribute="geoId">
        <mjl:input id="geoIdEl" param="geoId" type="text" value="${item.geoId}" maxlength="16" classes="geoInput"/>
      </mjl:dt>
      <mjl:dt attribute="testDate">
        <mjl:input type="text" param="testDate" classes="DatePick NoFuture" id="testDate" />
      </mjl:dt>
      <mjl:dt attribute="testMethod">
        <mjl:select var="current" valueAttribute="id" items="${testMethod}" param="testMethod">
          <mjl:option>
            ${current.termName}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="specie">
        <mjl:select var="current" valueAttribute="id" items="${specie}" param="specie" includeBlank="true">
          <mjl:option>
            ${current.termName}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="colonyName">
        <mjl:input type="text" param="colonyName" />
      </mjl:dt>
      <mjl:dt attribute="ageRange">
        <dl>
          <mjl:struct param="ageRange">
            <mjl:dt attribute="startPoint" type="text"  />
            <mjl:dt attribute="endPoint" type="text"  />
          </mjl:struct>
        </dl>
      </mjl:dt>
      <mjl:dt attribute="sex">
        <mjl:select var="current" valueAttribute="enumName" items="${sex}" param="sex">
          <mjl:option>
            ${item.sexMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="gravid">
        <mjl:input type="text" param="gravid" />
      </mjl:dt>
      <mjl:dt attribute="fed">
        <mjl:input type="text" param="fed" />
      </mjl:dt>
      <mjl:dt attribute="insecticide">
        <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticide">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
        <a href="dss.vector.solutions.general.InsecticideController.viewAll.mojo"><fmt:message key="Manage_Insecticides" /></a>
      </mjl:dt>
      <mjl:dt attribute="timeOnSurface" >
        <mjl:input type="text" param="timeOnSurface" />
      </mjl:dt>
      <mjl:dt attribute="surfacePostion">
        <mjl:select var="current" valueAttribute="enumName" items="${surfacePostion}" param="surfacePostion">
          <mjl:option>
            ${item.surfacePostionMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="exposureTime">
        <mjl:input type="text" param="exposureTime" />
      </mjl:dt>
      <mjl:dt attribute="holdingTime">
        <mjl:input type="text" param="holdingTime" />
      </mjl:dt>
      <mjl:dt attribute="quantityTested">
        <mjl:input type="text" param="quantityTested" />
      </mjl:dt>
      <mjl:dt attribute="quantityDead">
        <mjl:input type="text" param="quantityDead" />
      </mjl:dt>
    </mjl:component>
