<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Update_Country_Image"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<c:set scope="request" var="Upload"><mdss:localize key="Upload"/></c:set>

<form method="post" enctype="multipart/form-data" action="dss.vector.solutions.PropertyController.setFlag.mojo">
  <dl>
    <dt>
      <mdss:localize key="Update_Country_Image"/>
    </dt>
    <dd>
      <input type="file" name="upfile"/> <br />
      <input class="submitButton" type="submit" value="${Upload}" style="margin-left: 0px; top: 0px;" >     
    </dd>
  </dl>
</form>