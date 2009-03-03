<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="ivcc.mrc.csu.mdss.entomology.IdentificationMethodController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="termName">
      <mjl:header>
        Term Name
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="ivcc.mrc.csu.mdss.entomology.IdentificationMethodController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Identification Method" action="ivcc.mrc.csu.mdss.entomology.IdentificationMethodController.newInstance.mojo" name="IdentificationMethodController.newInstance" />
