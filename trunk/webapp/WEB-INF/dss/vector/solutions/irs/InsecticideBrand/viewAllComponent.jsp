<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_InsecticideBrand" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.irs.InsecticideBrandController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="productName">
      <mjl:row>
        ${item.productName.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="activeIngredient">
      <mjl:row>
        ${item.activeIngredient.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="concentrationQuantifier">
      <mjl:row>
        <fmt:formatNumber minFractionDigits="2" value="${item.concentrationQuantifier}" />    
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="concentrationQualifier">
      <mjl:row>
        <ul>
          <c:forEach items="${item.concentrationQualifierEnumNames}" var="enumName">
            <li>
              ${item.concentrationQualifierMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="insecticideUse">
      <mjl:row>
        <ul>
          <c:forEach items="${item.insecticideUseEnumNames}" var="enumName">
            <li>
              ${item.insecticideUseMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="enabled">
      <mjl:row>
        ${item.enabled ? item.enabledMd.positiveDisplayLabel : item.enabledMd.negativeDisplayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.irs.InsecticideBrandController.view.mojo">
          <mdss:localize key="View" />
          <mjl:property name="id" value="${item.id}" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
  <mjl:pagination>
    <mjl:page />
  </mjl:pagination>
</mjl:table>
<br />
<mjl:commandLink name="InsecticideBrandController.newInstance" action="dss.vector.solutions.irs.InsecticideBrandController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Insecticide_brand" />
</mjl:commandLink>
