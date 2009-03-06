<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="mosquito">
      <mjl:header>
        Mosquito
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="testResult">
      <mjl:header>
        E-KDR Test Result
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new E-KDR Test Result" action="csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultController.newInstance.mojo" name="EKDRTestResultController.newInstance" />
