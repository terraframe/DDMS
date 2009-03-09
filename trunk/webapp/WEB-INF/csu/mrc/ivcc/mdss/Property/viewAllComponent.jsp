<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even ="evenRow" odd="oddRow">
  <mjl:context action="csu.mrc.ivcc.mdss.PropertyController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="description">
      <mjl:header>
        Description
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="propertyName">
      <mjl:header>
        Name
      </mjl:header>
      
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="propertyPackage">
      <mjl:header>
        Package
      </mjl:header>
      
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="propertyValue">
      <mjl:header>
        Value
      </mjl:header>
      
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="csu.mrc.ivcc.mdss.PropertyController.view.mojo" name="view.link">
          <mjl:property value="${item.id}" name="id" />
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
<mjl:commandLink display="Create a new Property" action="csu.mrc.ivcc.mdss.PropertyController.newInstance.mojo" name="PropertyController.newInstance" />
