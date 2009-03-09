<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable">
  <mjl:context action="csu.mrc.ivcc.mdss.geo.generated.NonSentinalSiteController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="activated">
      <mjl:header>
        Activated
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="entityName">
      <mjl:header>
        Geo Entity Name
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="geoId">
      <mjl:header>
        Geo Id
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="csu.mrc.ivcc.mdss.geo.generated.NonSentinalSiteController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Non-Sentinal Site" action="csu.mrc.ivcc.mdss.geo.generated.NonSentinalSiteController.newInstance.mojo" name="NonSentinalSiteController.newInstance" />
