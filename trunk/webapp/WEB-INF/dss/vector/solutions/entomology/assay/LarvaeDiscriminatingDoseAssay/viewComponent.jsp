<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="java.util.*"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONArray"%>
<%@page import="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="dss.vector.solutions.entomology.assay.LarvaeTestIntervalViewDTO"%>
<%@page import="dss.vector.solutions.util.ColumnSetup"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>



<c:set var="page_title" value="View_Larvae_Diagnostic_Dose_Assay"  scope="request"/>
<%
LarvaeDiscriminatingDoseAssayDTO adda = (LarvaeDiscriminatingDoseAssayDTO) request.getAttribute("item");
%>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.name" id="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.id" method="POST">
  <dl>
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="collection">
        <mjl:commandLink name="collection.link" action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo" >
          <mjl:property name="id" value="${item.collection.id}"/>
          ${item.collection.collectionId}
        </mjl:commandLink>
      </mjl:dt>
      <mjl:dt attribute="testDate">
        <span class="formatDate">${item.testDate}</span>
      </mjl:dt>
      <mjl:dt attribute="controlTestMortality">
        ${item.controlTestMortality}
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
      <mjl:dt attribute="specie">
        ${item.specie.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="identificationMethod">
        ${item.identificationMethod.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="startPoint">
        ${item.startPoint.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="endPoint">
        ${item.endPoint.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="exposureTime">
        ${item.exposureTime}
      </mjl:dt>
      <mjl:dt attribute="intervalTime">
        ${item.intervalTime}
      </mjl:dt>
      <mjl:dt attribute="holdingTime">
        ${item.holdingTime}
      </mjl:dt>
      <mjl:dt attribute="insecticide">
        ${item.insecticide.displayLabel}
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
      <mjl:dt attribute="lt50">
        ${item.lt50}
      </mjl:dt>
      <mjl:dt attribute="lt95">
        ${item.lt95}
      </mjl:dt>     
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.edit.mojo"
      name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.edit.button" classes="submitButton" />
  </dl>
</mjl:form>

<div id="intervals"></div>

<ul>
  <li> 
    <mjl:commandLink name="collection.link" action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo" >
      <mjl:property name="id" value="${item.collection.id}"/>
      <fmt:message key="Return_to_Collection"/>
    </mjl:commandLink>
  </li>
  <li>
    <mjl:commandLink action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.newInstance.mojo" name="newWiththisCollection">
      <mjl:property value="${item.collection.id}" name="collection_id" />
      <fmt:message key="Create_Another_Larvae_Diagnostic_Assay_With_This_Collection"/>
    </mjl:commandLink>   
  </li>
  <li> 
    <mjl:commandLink action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.viewAll.mojo" name="viewAll.link" >
      <fmt:message key="View_All_LDA"/>
    </mjl:commandLink>
  </li>
</ul>

<%
    String[] types =
    {
    "dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay",
    "dss.vector.solutions.entomology.assay.LarvaeTestIntervalView"
    };
    String[] attribs = { "IntervalId","Assay","Period","IntervalTime","QuantityDead"};
    String last_column = "{key:'Percent',label:'%',resizeable:true}";

    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
    map.put("IntervalId", new ColumnSetup(true, false, null, null, null));
    map.put("Assay", new ColumnSetup(true, false, null, null, null));
    map.put("Period", new ColumnSetup(false, false, null, null, null));
    map.put("IntervalTime", new ColumnSetup(false, false, null, null, null));
%>

<%=Halp.loadTypes((List<String>) Arrays.asList(types))%>
<script type="text/javascript" defer="defer">
calculate_percent = function(record){
  return ((parseInt(record.getData('QuantityDead'))*100.0)/<%=adda.getQuantityTested()%>).toFixed(1)+"%";
}
table_data = {
        rows:<%=Halp.getDataMap(adda.getTestIntervals(),attribs,adda.getTestIntervals()[0])%>,
            columnDefs:<%=Halp.getColumnSetup(adda.getTestIntervals()[0],attribs,last_column,false,map)%>,
            div_id: "intervals",
            addButton:false,
            excelButtons:false,
            data_type: "Mojo.$.dss.vector.solutions.entomology.assay.LarvaeTestIntervalView",
            after_row_load:function(record){this.myDataTable.updateCell(record, 'Percent', calculate_percent(record))},
            after_row_edit:function(record){this.myDataTable.updateCell(record, 'Percent', calculate_percent(record))},
            after_save:function(){location.href="./dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.view.mojo?id=${item.id}";}
      };
MojoGrid.createDataTable(table_data);
</script>