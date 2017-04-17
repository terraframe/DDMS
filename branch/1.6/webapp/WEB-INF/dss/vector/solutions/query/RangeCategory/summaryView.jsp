<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<li>
<div id="${category.id}_div">
  <input type="hidden" id="${category.id}_type" value="${category.type}" />
  <img id="${category.id}_edit" class="clickable" src="imgs/icons/wand.png" />
  <img id="${category.id}_delete" class="clickable" src="imgs/icons/delete.png" />
  <mdss:localize key="Range_Category" />: ${category.lowerBoundStr} &lt; ${category.upperBoundStr}
</div>
</li>