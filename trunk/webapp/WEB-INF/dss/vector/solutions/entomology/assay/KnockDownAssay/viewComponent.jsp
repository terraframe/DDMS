<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@page import="java.util.*"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONArray"%>
<%@page import="dss.vector.solutions.entomology.assay.KnockDownAssayDTO"%>
<%@page import="dss.vector.solutions.util.Halp" %>
<%@page import="java.text.DecimalFormat"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultTestIntervalViewDTO"%>
<%
KnockDownAssayDTO kda = (KnockDownAssayDTO) request.getAttribute("item");
%>


<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.KnockDownAssay.form.name" id="dss.vector.solutions.entomology.assay.KnockDownAssay.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <mjl:dt attribute="collection">
<mjl:commandLink display="${item.collection.displayLabel}" action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo" name="dss.vector.solutions.entomology.MosquitoCollection.form.view.link">
        <mjl:property value="${item.collection.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
  <dt>
      <label>
        ${item.testDateMd.displayLabel}
      </label>
    </dt>
    <dd class="formatDate">
      ${item.testDate}
    </dd>
     <mjl:dt attribute="testMethod">
<mjl:commandLink display="${item.testMethod.displayLabel}" action="dss.vector.solutions.mo.ResistanceMethodologyController.view.mojo" name="dss.vector.solutions.mo.ResistanceMethodology.form.view.link">
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
    <dt>
      <label>
        ${item.sexMd.displayLabel}
      </label>
    </dt>
    <dd>
        <c:forEach var="enumName" items="${item.sexEnumNames}">
            ${item.sexMd.enumItems[enumName]}
        </c:forEach>
    </dd>
    <mjl:dt attribute="specie">
<mjl:commandLink display="${item.specie.displayLabel}" action="dss.vector.solutions.mo.SpecieController.view.mojo" name="dss.vector.solutions.mo.Specie.form.view.link">
        <mjl:property value="${item.specie.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
     <mjl:dt attribute="identificationMethod">
<mjl:commandLink display="${item.identificationMethod.displayLabel}" action="dss.vector.solutions.mo.IdentificationMethodController.view.mojo" name="dss.vector.solutions.mo.IdentificationMethod.form.view.link">
        <mjl:property value="${item.identificationMethod.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
        <dt>
          <label>
            ${item.ageRange.startPointMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.ageRange.startPoint}
        </dd>
        <dt>
          <label>
            ${item.ageRange.endPointMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.ageRange.endPoint}
        </dd>
    <mjl:dt attribute="fed">
      ${item.fed}
</mjl:dt>
       <mjl:dt attribute="gravid">
      ${item.gravid}
</mjl:dt>
    <mjl:dt attribute="exposureTime">
      ${item.exposureTime}
</mjl:dt>

    <mjl:dt attribute="insecticide">
<mjl:commandLink display="${item.insecticide.displayLabel}" action="dss.vector.solutions.general.InsecticideController.view.mojo" name="insecticide.form.link">
        <mjl:property value="${item.insecticide.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="quantityTested">
      ${item.quantityTested}
</mjl:dt>


    <mjl:dt attribute="intervalTime">
      ${item.intervalTime}
</mjl:dt>


  </dl>

  <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.KnockDownAssayController.edit.mojo" name="dss.vector.solutions.entomology.assay.KnockDownAssay.form.edit.button" classes="submitButton" />
  <br />
  </mjl:component>
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.assay.KnockDownAssayController.viewAll.mojo" name="dss.vector.solutions.entomology.assay.KnockDownAssay.viewAll.link" />


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
     "dss.vector.solutions.entomology.assay.KnockDownAssay",
     "dss.vector.solutions.entomology.assay.AdultTestIntervalView"
  };
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , types_to_load,true));
    DecimalFormat percent = new DecimalFormat("0.0");

    %>
    table_data = {
        rows:<%
             // KnockDownAssayDTO kda = (KnockDownAssayDTO) request.getAttribute("item");
                  AdultTestIntervalViewDTO[] rows = kda.getTestIntervals();
                ArrayList<String> arr = new ArrayList<String>();
                 for (AdultTestIntervalViewDTO row : rows)  {
                   ArrayList<String> buff = new ArrayList<String>();
                   buff.add("IntervalId:'" + row.getIntervalId() + "'");
                   buff.add("Period:'" + row.getPeriod() + "'");
                   buff.add("IntervalTime:'" + row.getIntervalTime() + "'");
                   buff.add("KnockedDown:'" + row.getKnockedDown() + "'");
                   buff.add("Percent:'" + percent.format((row.getKnockedDown()*100.0)/kda.getQuantityTested())+"%'");
                   arr.add("{" +Halp.join(buff,",")+ "}");
                 }
                 out.println("[" +Halp.join(arr,",\n")+ "]");%>
             ,columnDefs:[
                  {key:"IntervalId",label:"ID",hidden:true},
                  {key:"Period",label:'<%=rows[0].getPeriodMd().getDisplayLabel()%>'},
                  {key:"IntervalTime",label:'<%=rows[0].getIntervalTimeMd().getDisplayLabel()%>',resizeable:true},
                  {key:"KnockedDown",label:"<%=rows[0].getKnockedDownMd().getDisplayLabel()%>",resizeable:true,editor: new YAHOO.widget.TextboxCellEditor({validator:YAHOO.widget.DataTable.validateNumber,disableBtns:true})},
                  {key:"Percent",label:"%",resizeable:true} ],
              defaults: {IntervalId:"",Period:"",IntervalTime:"",KnockedDown:"",Percent:""},
              div_id: "intervals",
              collection_setter: "setAssay('${item.id}')",
              data_type: "Mojo.$.dss.vector.solutions.entomology.assay.AdultTestIntervalView",
              after_row_load:function(record){record.setData('Percent',((parseInt(record.getData('KnockedDown'))*100.0)/<%=kda.getQuantityTested()%>).toFixed(1)+"%");},
              after_save:function(){location.href="./dss.vector.solutions.entomology.assay.KnockDownAssayController.view.mojo?id=${item.id}";}
          };
    YAHOO.util.Event.onDOMReady(MojoGrid.createDataTable(table_data));
</script>