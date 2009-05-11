<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@page import="java.util.*"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONArray"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultTestIntervalViewDTO"%>
<%
AdultDiscriminatingDoseAssayDTO adda = (AdultDiscriminatingDoseAssayDTO) request.getAttribute("item");
%>


<c:set var="page_title" value="View_ADDA"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.name" id="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
<mjl:component item="${item}" param="dto">

  <dl>
    <mjl:dt attribute="collection">
      <mjl:commandLink display="${item.collection.displayLabel}" action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo"
        name="dss.vector.solutions.entomology.MosquitoCollection.form.view.link">
        <mjl:property value="${item.collection.id}" name="id" />
      </mjl:commandLink>
    </mjl:dt>
    <dt><label> ${item.testDateMd.displayLabel} </label></dt>
    <dd class="formatDate">${item.testDate}</dd>
    <mjl:dt attribute="controlTestMortality">

      ${item.controlTestMortality}
</mjl:dt>
    <mjl:dt attribute="testMethod">
      <mjl:commandLink display="${item.testMethod.displayLabel}" action="dss.vector.solutions.mo.ResistanceMethodologyController.view.mojo"
        name="dss.vector.solutions.mo.ResistanceMethodology.form.view.link">
        <mjl:property value="${item.testMethod.id}" name="id" />
      </mjl:commandLink>
    </mjl:dt>
    <mjl:dt attribute="generation">
      <mjl:commandLink display="${item.generation.displayLabel}" action="dss.vector.solutions.mo.GenerationController.view.mojo" name="dss.vector.solutions.mo.Generation.form.view.link">
        <mjl:property value="${item.generation.id}" name="id" />
      </mjl:commandLink>
    </mjl:dt>
    <mjl:dt attribute="isofemale">

      ${item.isofemale}
</mjl:dt>
    <dt><label> ${item.sexMd.displayLabel} </label></dt>
    <dd><c:forEach var="enumName" items="${item.sexEnumNames}">
            ${item.sexMd.enumItems[enumName]}
        </c:forEach></dd>
    <mjl:dt attribute="specie">
      <mjl:commandLink display="${item.specie.displayLabel}" action="dss.vector.solutions.mo.SpecieController.view.mojo" name="dss.vector.solutions.mo.Specie.form.view.link">
        <mjl:property value="${item.specie.id}" name="id" />
      </mjl:commandLink>
    </mjl:dt>
    <mjl:dt attribute="identificationMethod">
      <mjl:commandLink display="${item.identificationMethod.displayLabel}" action="dss.vector.solutions.mo.IdentificationMethodController.view.mojo"
        name="dss.vector.solutions.mo.IdentificationMethod.form.view.link">
        <mjl:property value="${item.identificationMethod.id}" name="id" />
      </mjl:commandLink>
    </mjl:dt>

    <dl>
      <dt><label> ${item.ageRange.startPointMd.displayLabel} </label></dt>
      <dd>${item.ageRange.startPoint}</dd>
      <dt><label> ${item.ageRange.endPointMd.displayLabel} </label></dt>
      <dd>${item.ageRange.endPoint}</dd>
    </dl>
    <mjl:dt attribute="fed">

      ${item.fed}
</mjl:dt>
    <mjl:dt attribute="gravid">

      ${item.gravid}
</mjl:dt>
    <mjl:dt attribute="exposureTime">

      ${item.exposureTime}
</mjl:dt>
    <mjl:dt attribute="holdingTime">

      ${item.holdingTime}
</mjl:dt>
    <mjl:dt attribute="insecticide">
      <mjl:commandLink display="${item.insecticide.displayLabel}" action="dss.vector.solutions.general.InsecticideController.view.mojo" name="insecticide.form.link">
        <mjl:property value="${item.insecticide.id}" name="id" />
      </mjl:commandLink>
    </mjl:dt>
    <mjl:dt attribute="quantityTested">

      ${item.quantityTested}
</mjl:dt>
    <mjl:dt attribute="quantityLive">

      ${item.quantityLive}
</mjl:dt>
    <mjl:dt attribute="quantityDead">

      ${item.quantityDead}
</mjl:dt>
    <mjl:dt attribute="mortality">

        ${item.mortality}
</mjl:dt>
    <dt><label> KD50 </label></dt>
    <dd><fmt:formatNumber pattern="##.##">${item.KD50}</fmt:formatNumber></dd>
    <dt><label> KD95 </label></dt>
    <dd><fmt:formatNumber pattern="##.##">${item.KD95}</fmt:formatNumber></dd>
    <mjl:dt attribute="intervalTime">

      ${item.intervalTime}
</mjl:dt>
  </dl>
  </mjl:component>

  <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.edit.mojo"
    name="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.edit.button" classes="submitButton" />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.viewAll.mojo"
  name="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.viewAll.link" />


<div id="intervals"></div>

<div id="buttons" class="noprint"><span id="intervalsSaverows" class="yui-button yui-push-button"> <span class="first-child">
<button type="button"><fmt:message key="Save_Rows_To_DB" /></button>
</span> </span> <a href="javascript:window.print()"><img src="./imgs/icons/printer.png"></a></div>
<script type="text/javascript">
    <%
    String[] types_to_load =
	{
	   "dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay",
	   "dss.vector.solutions.entomology.assay.AdultTestIntervalView"
	};
	ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , types_to_load,true));
    DecimalFormat percent = new DecimalFormat("0.0");

    %>
    table_data = {
    		rows:<%
    		     // AdultDiscriminatingDoseAssayDTO adda = (AdultDiscriminatingDoseAssayDTO) request.getAttribute("item");
                  AdultTestIntervalViewDTO[] rows = adda.getTestIntervals();
    	          ArrayList<String> arr = new ArrayList<String>();
   	    		     for (AdultTestIntervalViewDTO row : rows)  {
   	    		       ArrayList<String> buff = new ArrayList<String>();
   	    		       buff.add("IntervalId:'" + row.getIntervalId() + "'");
   	    		       buff.add("Assay:'" + row.getAssay().getId() + "'");
   	    		       buff.add("Period:'" + row.getPeriod() + "'");
   	    		       buff.add("IntervalTime:'" + row.getIntervalTime() + "'");
   	    		       buff.add("KnockedDown:'" + row.getKnockedDown() + "'");
   	    		       buff.add("Percent:'" + percent.format((row.getKnockedDown()*100.0)/adda.getQuantityTested())+"%'");
   	    		       arr.add("{" +Halp.join(buff,",")+ "}");
   	    		     }
   	    		     out.println("[" +Halp.join(arr,",\n")+ "]");%>
    	    	 ,columnDefs:[
    	            {key:"IntervalId",label:"ID",hidden:true},
    	            {key:"Assay",label:"AssayId",hidden:true},
    	            {key:"Period",label:'<%=rows[0].getPeriodMd().getDisplayLabel()%>'},
    	            {key:"IntervalTime",label:'<%=rows[0].getIntervalTimeMd().getDisplayLabel()%>',resizeable:true},
    	            {key:"KnockedDown",label:"<%=rows[0].getKnockedDownMd().getDisplayLabel()%>",resizeable:true,editor: new YAHOO.widget.TextboxCellEditor({validator:YAHOO.widget.DataTable.validateNumber,disableBtns:true})},
    	            {key:"Percent",label:"%",resizeable:true} ],
    	        defaults: {IntervalId:"",Period:"",IntervalTime:"",KnockedDown:"",Percent:""},
    	        div_id: "intervals",
    	        //collection_setter: "setAssay('${item.id}')",
        	    data_type: "Mojo.$.dss.vector.solutions.entomology.assay.AdultTestIntervalView",
        	    after_row_edit:function(record){record.setData('Percent',((parseInt(record.getData('KnockedDown'))*100.0)/<%=adda.getQuantityTested()%>).toFixed(1)+"%");},
    	        after_save:function(){location.href="./dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.view.mojo?id=${item.id}";}
    	    };
    YAHOO.util.Event.onDOMReady(MojoGrid.createDataTable(table_data));
</script>