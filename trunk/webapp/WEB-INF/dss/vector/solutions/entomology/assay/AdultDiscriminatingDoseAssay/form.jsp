<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="collection">
        <mjl:select var="current" valueAttribute="id" items="${collection}" param="collection">
          <mjl:option>
                ${current.displayLabel}
              </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="testDate" type="text" classes="DatePick" />
      <mjl:dt attribute="testMethod">
        <mjl:select var="current" valueAttribute="id" items="${testMethod}" param="testMethod">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="generation">
        <mjl:select var="current" valueAttribute="id" items="${generation}" param="generation">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="isofemale">
        <mjl:boolean param="isofemale"  />
      </mjl:dt>
      <mjl:dt attribute="sex">
        <mjl:select var="current" valueAttribute="enumName" items="${sex}" param="sex">
          <c:choose>
            <c:when test="${mjl:contains(item.sexEnumNames, current.enumName)}">
              <mjl:option selected="selected">
                ${item.sexMd.enumItems[current.enumName]}
              </mjl:option>
            </c:when>
            <c:otherwise>
              <mjl:option>
                ${item.sexMd.enumItems[current.enumName]}
              </mjl:option>
            </c:otherwise>
          </c:choose>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="specie">
        <mjl:select var="current" valueAttribute="id" items="${specie}" param="specie" includeBlank="true">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="identificationMethod">
        <mjl:select var="current" valueAttribute="id" items="${identificationMethod}" param="identificationMethod">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
     <mjl:dt attribute="ageRange">
      <dl>
        <mjl:struct param="ageRange">
          <mjl:dt attribute="startPoint" type="text"  />
          <mjl:dt attribute="endPoint" type="text"  />
        </mjl:struct>
      </dl>
      </mjl:dt>
      <mjl:dt attribute="fed">
        <mjl:input type="text" param="fed" />
      </mjl:dt>
      <mjl:dt attribute="gravid">
        <mjl:input type="text" param="gravid" />
      </mjl:dt>
      <mjl:dt attribute="exposureTime">
        <mjl:input type="text" param="exposureTime" />
      </mjl:dt>
      <mjl:dt attribute="holdingTime">
        <mjl:input type="text" param="holdingTime" />
      </mjl:dt>
      <mjl:dt attribute="insecticide">
        <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticide">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
        <a href="dss.vector.solutions.general.InsecticideController.viewAll.mojo"><fmt:message key="Manage_Insecticides" /></a>
      </mjl:dt>
      <mjl:dt attribute="quantityTested">
        <mjl:input type="text" param="quantityTested" />
      </mjl:dt>

      <mjl:dt attribute="quantityDead">
        <mjl:input type="text" param="quantityDead" />
      </mjl:dt>
      <mjl:dt attribute="controlTestMortality">
        <mjl:input type="text" param="controlTestMortality" />
      </mjl:dt>

      <mjl:dt attribute="intervalTime">
        <mjl:input type="text" param="intervalTime" />
      </mjl:dt>
    </mjl:component>
