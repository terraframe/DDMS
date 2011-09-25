<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>


<mjl:component param="form" item="${form}">
    <li>
        <label>Form Name</label>
        <mjl:input value="${form.formName}" type="text" param="formName" />
    </li>
    <li>
        <label>Form Display Label</label>
        <mjl:input value="${form.displayLabel}" type="text" param="displayLabel" />
    </li>
</mjl:component>