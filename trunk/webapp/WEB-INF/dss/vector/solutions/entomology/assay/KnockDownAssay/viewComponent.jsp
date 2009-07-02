<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="java.util.*"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONArray"%>
<%@page import="dss.vector.solutions.entomology.assay.KnockDownAssayDTO"%>
<%@page import="dss.vector.solutions.util.Halp" %>
<%@page import="java.text.DecimalFormat"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultTestIntervalViewDTO"%>
<%
KnockDownAssayDTO adda = (KnockDownAssayDTO) request.getAttribute("item");
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

<%
    String[] types =
    {
    "dss.vector.solutions.entomology.assay.KnockDownAssay",
    "dss.vector.solutions.entomology.assay.AdultTestIntervalView"
    };
    String[] attribs = { "IntervalId","Assay","Period","IntervalTime","KnockedDown"};
    String last_column = "{key:'Percent',label:'%',resizeable:true}";

    Integer[] no_show_arr = {0,1};
    List no_show_list = Arrays.asList(no_show_arr);
    Integer[] no_edit_arr = {0,1,2,3};
    List no_edit_list = Arrays.asList(no_edit_arr);
%>
<%=Halp.loadTypes((List<String>) Arrays.asList(types))%>
<script type="text/javascript" defer="defer">
calculate_percent = function(record){
  return ((parseInt(record.getData('KnockedDown'))*100.0)/<%=adda.getQuantityTested()%>).toFixed(1)+"%";
}
table_data = {
        rows:<%=Halp.getDataMap(adda.getTestIntervals(),attribs,adda.getTestIntervals()[0])%>,
            columnDefs:<%=Halp.getColumnSetup(adda.getTestIntervals()[0],attribs,last_column,false,no_show_list,no_edit_list)%>,
            defaults: {IntervalId:"",Period:"",IntervalTime:"",KnockedDown:"",Percent:""},
            div_id: "intervals",
            addButton:false,
            excelButtons:false,
            data_type: "Mojo.$.dss.vector.solutions.entomology.assay.AdultTestIntervalView",
            after_row_load:function(record){this.myDataTable.updateCell(record, 'Percent', calculate_percent(record))},
            after_row_edit:function(record){this.myDataTable.updateCell(record, 'Percent', calculate_percent(record))},
            after_save:function(){location.href="./dss.vector.solutions.entomology.assay.KnockDownAssayController.view.mojo?id=${item.id}";}
      };
MojoGrid.createDataTable(table_data);
</script>