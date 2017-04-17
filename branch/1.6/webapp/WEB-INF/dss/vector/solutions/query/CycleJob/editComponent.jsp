<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld"%>
<style>
<!--
  .job-dl {
    border: none;
    padding: 1em;
    background: none;
    min-width: 0px;
    max-width: 300px;
    margin-left: 0px;
    margin-bottom: 5px;
  }
-->
</style>
<dl class="job-dl">
	<mjl:form id="job-form" name="job-name" method="POST">
		<%@include file="form.jsp"%>
	</mjl:form>
</dl>
