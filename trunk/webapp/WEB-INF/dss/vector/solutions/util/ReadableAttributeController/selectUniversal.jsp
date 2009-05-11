<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>

<c:set var="action" value="dss.vector.solutions.util.ReadableAttributeController.getAttributes.mojo" scope="page"/>

  <dl>
    <dt><label>MDSS 114 - Person Management</label></dt>
    <dd>
      <mjl:commandLink
        name="Person"
        display="Person"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.Person"/>
    </dd>
  </dl>
  <dl>
    <dt><label>MDSS 141 - IRS Team Management</label></dt>
    <dd>
      <mjl:commandLink
        name="SprayTeam"
        display="Spray Team"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.SprayTeam"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="SprayLeader"
        display="Spray Leader"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.SprayLeader"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="SprayOperator"
        display="Spray Operator"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.SprayOperator"/>
    </dd>
  </dl>
