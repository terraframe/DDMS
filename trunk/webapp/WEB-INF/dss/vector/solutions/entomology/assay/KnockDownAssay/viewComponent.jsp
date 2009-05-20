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
<c:set var="page_title" value="View_Knockdown_Assay"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.KnockDownAssay.form.name" id="dss.vector.solutions.entomology.assay.KnockDownAssay.form.id" method="POST">
  <dl>
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:commandLink display="Create_Another_Knock_Down_Assay_With_This_Collection" action="dss.vector.solutions.entomology.assay.KnockDownAssayController.newInstance.mojo" name="newWiththisCollection">
        <mjl:property value="${item.collection.id}" name="id" />
      </mjl:commandLink>
      <mjl:dt attribute="collection">
        ${item.collection.collectionId} ${item.collection.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="testDate">
        <span class="formatDate">${item.testDate}</span>
      </mjl:dt>
      <mjl:dt attribute="testMethod">
        ${item.testMethod.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="generation">
        ${item.generation.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="isofemale">
        ${item.isofemale}
      </mjl:dt>
      <mjl:dt attribute="sex">
        <c:forEach var="enumName" items="${item.sexEnumNames}">
          ${item.sexMd.enumItems[enumName]}
        </c:forEach>
      </mjl:dt>
      <mjl:dt attribute="specie">
        ${item.specie.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="identificationMethod">
        ${item.identificationMethod.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="ageRange">
        <mjl:struct param="ageRange">
          <mjl:dt attribute="startPoint">
            ${item.ageRange.startPoint}
          </mjl:dt>
          <mjl:dt attribute="endPoint">
            ${item.ageRange.endPoint}
          </mjl:dt>
        </mjl:struct>
      </mjl:dt>
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
        ${item.insecticide.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="quantityTested">
        ${item.quantityTested}
      </mjl:dt>
      <mjl:dt attribute="intervalTime">
        ${item.intervalTime}
      </mjl:dt>
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.KnockDownAssayController.edit.mojo" name="dss.vector.solutions.entomology.assay.KnockDownAssay.form.edit.button" classes="submitButton" />
  </dl>
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
                   buff.add("Assay:'" + row.getAssay().getId() + "'");
                   buff.add("Period:'" + row.getPeriod() + "'");
                   buff.add("IntervalTime:'" + row.getIntervalTime() + "'");
                   buff.add("KnockedDown:'" + row.getKnockedDown() + "'");
                   buff.add("Percent:'" + percent.format((row.getKnockedDown()*100.0)/kda.getQuantityTested())+"%'");
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
              after_row_load:function(record){record.setData('Percent',((parseInt(record.getData('KnockedDown'))*100.0)/<%=kda.getQuantityTested()%>).toFixed(1)+"%");},
              after_save:function(){location.href="./dss.vector.solutions.entomology.assay.KnockDownAssayController.view.mojo?id=${item.id}";}
          };
    YAHOO.util.Event.onDOMReady(MojoGrid.createDataTable(table_data));
</script>