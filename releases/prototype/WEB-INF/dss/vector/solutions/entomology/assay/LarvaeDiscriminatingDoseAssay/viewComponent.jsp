<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONArray"%>
<%@page import="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO"%>
<%@page import="dss.vector.solutions.util.Halp" %>
<%@page import="java.text.DecimalFormat"%>
<%@page import="dss.vector.solutions.entomology.assay.LarvaeTestIntervalViewDTO"%>
<%
LarvaeDiscriminatingDoseAssayDTO ldda = (LarvaeDiscriminatingDoseAssayDTO) request.getAttribute("item");
%>
<mjl:messages>
  <mjl:message />
</mjl:messages>
  <dl>
    <dt>
      <label>
        ${item.collectionMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.collection.displayLabel}" action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo" name="dss.vector.solutions.entomology.MosquitoCollection.form.view.link">
        <mjl:property value="${item.collection.id}" name="id" />
      </mjl:commandLink>
    </dd>
  <dt>
      <label>
        ${item.testDateMd.displayLabel}
      </label>
    </dt>
    <dd class="formatDate">
      ${item.testDate}
    </dd>
    <dt>
      <label>
        ${item.controlTestMortalityMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.controlTestMortality}
    </dd>
    <dt>
      <label>
        ${item.testMethodMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.testMethod.displayLabel}" action="dss.vector.solutions.mo.ResistanceMethodologyController.view.mojo" name="dss.vector.solutions.mo.ResistanceMethodology.form.view.link">
        <mjl:property value="${item.testMethod.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.generationMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.generation.displayLabel}" action="dss.vector.solutions.mo.GenerationController.view.mojo" name="dss.vector.solutions.mo.Generation.form.view.link">
        <mjl:property value="${item.generation.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.isofemaleMd.displayLabel}
      </label>
    </dt>
    <dd>
    <%= Halp.translateBool(((LarvaeDiscriminatingDoseAssayDTO)request.getAttribute("item")).getIsofemaleMd(),(((LarvaeDiscriminatingDoseAssayDTO)request.getAttribute("item")).getIsofemale()))%>    
    </dd>
    <dt>
      <label>
        ${item.specieMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.specie.displayLabel}" action="dss.vector.solutions.mo.SpecieController.view.mojo" name="dss.vector.solutions.mo.Specie.form.view.link">
        <mjl:property value="${item.specie.id}" name="id" />
      </mjl:commandLink>
    </dd>
     <dt>
      <label>
        ${item.identificationMethodMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.identificationMethod.displayLabel}" action="dss.vector.solutions.mo.IdentificationMethodController.view.mojo" name="dss.vector.solutions.mo.IdentificationMethod.form.view.link">
        <mjl:property value="${item.identificationMethod.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.startPointMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.startPoint.displayLabel}" action="dss.vector.solutions.mo.LarvaeAgeController.view.mojo" name="dss.vector.solutions.mo.LarvaeAge.form.view.link">
        <mjl:property value="${item.startPoint.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.endPointMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.endPoint.displayLabel}" action="dss.vector.solutions.mo.LarvaeAgeController.view.mojo" name="dss.vector.solutions.mo.LarvaeAge.form.view.link">
        <mjl:property value="${item.endPoint.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.exposureTimeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.exposureTime}
    </dd>
     <dt>
      <label>
        ${item.holdingTimeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.holdingTime}
    </dd>
    <dt>
      <label>
        ${item.insecticideMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.insecticide.displayLabel}" action="dss.vector.solutions.general.InsecticideController.view.mojo" name="insecticide.form.link">
        <mjl:property value="${item.insecticide.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.quantityTestedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.quantityTested}
    </dd>
     <dt>
      <label>
        ${item.quantityLiveMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.quantityLive}
    </dd>
     <dt>
      <label>
        ${item.quantityDeadMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.quantityDead}
    </dd>
     <dt>  
      <label>
        ${item.mortalityMd.displayLabel}
      </label>
    </dt>
    <dd>
        ${item.mortality}
    </dd>
     
    <dt>
      <label>
        ${item.intervalTimeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.intervalTime}
    </dd>
     

  </dl>
  <mjl:form name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.name" id="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />

  <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.edit.mojo" name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.edit.button" classes="submitButton" />
  <br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.viewAll.mojo" name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.viewAll.link" />


<div id="intervals"></div>

<div id="buttons" class="noprint">
 <span id="intervalsSaverows" class="yui-button yui-push-button"> 
 <span class="first-child">
<button type="button"><fmt:message key="Save_Rows_To_DB"/></button>
</span> 
</span>


<a href="javascript:window.print()"><img src="./imgs/icons/printer.png"></a>

</div>
<script type="text/javascript">      
    <%
    String[] types_to_load =
  {
     "dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay",
     "dss.vector.solutions.entomology.assay.LarvaeTestIntervalView"
  }; 
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , types_to_load,true));
    DecimalFormat percent = new DecimalFormat("0.0");

    %>
    table_data = { 
        rows:<%
             // LarvaeDiscriminatingDoseAssayDTO ldda = (LarvaeDiscriminatingDoseAssayDTO) request.getAttribute("item");
                  LarvaeTestIntervalViewDTO[] rows = ldda.getTestIntervals();
                ArrayList<String> arr = new ArrayList<String>();
                 for (LarvaeTestIntervalViewDTO row : rows)  {
                   ArrayList<String> buff = new ArrayList<String>();
                   buff.add("IntervalId:'" + row.getIntervalId() + "'");
                   buff.add("Period:'" + row.getPeriod() + "'");
                   buff.add("IntervalTime:'" + row.getIntervalTime() + "'");
                   buff.add("QuantityDead:'" + row.getQuantityDead() + "'");
                   buff.add("Percent:'" + percent.format((row.getQuantityDead()*100.0)/ldda.getQuantityTested())+"%'");
                   arr.add("{" +Halp.join(buff,",")+ "}");    
                 }
                 out.println("[" +Halp.join(arr,",\n")+ "]");%>        
             ,columnDefs:[
                  {key:"IntervalId",label:"ID",hidden:true},
                  {key:"Period",label:'<%=rows[0].getPeriodMd().getDisplayLabel()%>'},
                  {key:"IntervalTime",label:'<%=rows[0].getIntervalTimeMd().getDisplayLabel()%>',resizeable:true},
                  {key:"QuantityDead",label:"<%=rows[0].getQuantityDeadMd().getDisplayLabel()%>",resizeable:true,editor: new YAHOO.widget.TextboxCellEditor({validator:YAHOO.widget.DataTable.validateNumber,disableBtns:true})},
                  {key:"Percent",label:"%",resizeable:true} ],
              defaults: {IntervalId:"",Period:"",IntervalTime:"",KnockedDown:"",Percent:""},
              div_id: "intervals",
              collection_setter: "setAssay('${item.id}')",
              data_type: "Mojo.$.dss.vector.solutions.entomology.assay.LarvaeTestIntervalView",
              after_row_edit:function(record){record.setData('Percent',((parseInt(record.getData('QuantityDead'))*100.0)/<%=ldda.getQuantityTested()%>).toFixed(1)+"%");},
              after_save:function(){location.href="./dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.view.mojo?id=${item.id}";}
          };   
    YAHOO.util.Event.onDOMReady(MojoGrid.createDataTable(table_data));
</script>
