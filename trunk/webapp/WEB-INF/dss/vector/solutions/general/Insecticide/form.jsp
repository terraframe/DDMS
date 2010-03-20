<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.general.InsecticideDTO"%>



<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="activeIngredient">
    <mdss:mo param="activeIngredient" value="${activeIngredient}"/>
  </mjl:dt>
  <mjl:dt attribute="amount">
    <mjl:input param="amount" type="text" />
  </mjl:dt>
  <mjl:dt attribute="units">
    <mdss:mo param="units" value="${units}"/>
  </mjl:dt>
</mjl:component>