<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_Stock_Transactions" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="current" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.stock.StockEventController.getPage.mojo" >
    <mjl:property name="item" value="${item}"/>
    <mjl:property name="geoId" value="${geoId}"/>
    <mjl:property name="startDate" value="${startDate}"/>
    <mjl:property name="endDate" value="${endDate}"/>
  </mjl:context>  
  <mjl:columns>
    <mjl:attributeColumn attributeName="eventDate">
      <mjl:row>
        <span class="formatDate">${current.eventDate}</span>
      </mjl:row>
    </mjl:attributeColumn>  
    <mjl:attributeColumn attributeName="itemLabel" >
      <mjl:row>
        ${current.itemLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="staffLabel" >
      <mjl:row>
        ${current.staffLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="otherParty" >
      <mjl:row>
        ${current.otherParty}
      </mjl:row>
    </mjl:attributeColumn>    
    <mjl:attributeColumn attributeName="transactionType">
      <mjl:row>
        <ul>
          <c:forEach var="enumName" items="${current.transactionTypeEnumNames}">
            ${current.transactionTypeMd.enumItems[enumName]}
          </c:forEach>        
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="quantity" />
    <mjl:attributeColumn attributeName="cost">
      <mjl:row>
        <fmt:formatNumber type="currency" currencyCode="${currency}" value="${current.cost}" />
      </mjl:row>
    </mjl:attributeColumn>        
  </mjl:columns>
  <mjl:pagination>
    <mjl:page />
  </mjl:pagination>
</mjl:table>
<br />
<span class="noprint">
  <mjl:commandLink  action="dss.vector.solutions.stock.StockEventController.search.mojo" name="search.link" >
    <mjl:property name="geoId" value="${geoId}"/>
    <mjl:property name="itemId" value="${item}"/>
    <mjl:property name="date" value="${startDate}"/>    
    <mjl:property name="endDate" value="${endDate}"/>    
    <mdss:localize key="Back_To_Search"/>
  </mjl:commandLink>
</span>
