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
<%@page import="dss.vector.solutions.util.ColumnSetup"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<%
KnockDownAssayDTO adda = (KnockDownAssayDTO) request.getAttribute("item");
%>


<%@page import="org.apache.taglibs.standard.tag.common.fmt.BundleSupport"%><c:set var="page_title" value="View_Knockdown_Assay"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.KnockDownAssay.form.name" id="dss.vector.solutions.entomology.assay.KnockDownAssay.form.id" method="POST">
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
      <mjl:dt attribute="testMethod">
        <c:if test="${testMethod != null}">
          ${testMethod.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="generation">
        <c:if test="${generation != null}">
          ${generation.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="isofemale">
        ${item.isofemale}
      </mjl:dt>
      <mjl:dt attribute="sex">
        <c:if test="${sex != null}">
          ${sex.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="specie">
        <c:if test="${specie != null}">
          ${specie.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="identificationMethod">
        <c:if test="${identificationMethod != null}">
          ${identificationMethod.displayLabel}
        </c:if>
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
      <mjl:dt attribute="kd50">
        ${item.kd50}
      </mjl:dt>
      <mjl:dt attribute="kd95">
        ${item.kd95}
      </mjl:dt>      
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.KnockDownAssayController.edit.mojo" name="dss.vector.solutions.entomology.assay.KnockDownAssay.form.edit.button" classes="submitButton" />
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
    <mjl:commandLink action="dss.vector.solutions.entomology.assay.KnockDownAssayController.newInstance.mojo" name="newWiththisCollection">
      <mjl:property value="${item.collection.id}" name="collection_id" />
      <fmt:message key="Create_Another_Knock_Down_Assay_With_This_Collection"/>
    </mjl:commandLink>  
  </li>
  <li>
    <mjl:commandLink action="dss.vector.solutions.entomology.assay.KnockDownAssayController.viewAll.mojo" name="viewAll.link" >
      <fmt:message key="View_All_KDA"/>
    </mjl:commandLink>      
  </li>
</ul>


<%
    String[] types =
    {
    "dss.vector.solutions.entomology.assay.KnockDownAssay",
    "dss.vector.solutions.entomology.assay.AdultTestIntervalView"
    };
    String[] attribs = { "IntervalId","Assay","Period","IntervalTime","KnockedDown"};

    String lastColumnHeader = "%";
    try {
        ResourceBundle localized = BundleSupport.getLocalizationContext(pageContext).getResourceBundle();
    	lastColumnHeader = localized.getString("Knock_Down_Percentage_Heading");
    } catch (Exception e) {
    	// Do nothing--keep default header of %
    }
    String last_column = "{key:'Percent',label:'" + lastColumnHeader + "',resizeable:true}";

    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
    map.put("IntervalId", new ColumnSetup(true, false, null, null, null));
    map.put("Assay", new ColumnSetup(true, false, null, null, null));
    map.put("Period", new ColumnSetup(false, false, null, null, null));
    map.put("IntervalTime", new ColumnSetup(false, false, null, null, null));
%>
<%=Halp.loadTypes((List<String>) Arrays.asList(types))%>
<script type="text/javascript" defer="defer">
calculate_percent = function(record){
  return ((parseInt(record.getData('KnockedDown'))*100.0)/<%=adda.getQuantityTested()%>).toFixed(1)+"%";
}

table_data = {
        rows:<%=Halp.getDataMap(adda.getTestIntervals(),attribs,adda.getTestIntervals()[0])%>,
            columnDefs:<%=Halp.getColumnSetup(adda.getTestIntervals()[0],attribs,last_column,false,map)%>,
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