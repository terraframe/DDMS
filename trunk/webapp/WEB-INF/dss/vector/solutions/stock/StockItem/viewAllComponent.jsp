<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_StockItem" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.stock.StockItemController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="itemId"/>  
    <mjl:attributeColumn attributeName="itemName">
      <mjl:row>
        ${item.itemName.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="quantity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="unit">
      <mjl:row>
        ${item.unit.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.stock.StockItemController.view.mojo">
          <mdss:localize key="View" />
          <mjl:property name="id" value="${item.concreteId}" />
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
<mjl:commandLink name="StockItemController.newInstance" action="dss.vector.solutions.stock.StockItemController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Stock_Item" />
</mjl:commandLink>
