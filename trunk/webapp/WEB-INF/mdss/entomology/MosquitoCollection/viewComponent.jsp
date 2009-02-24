<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>
<%@page import="java.util.*"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONArray"%>
<%@page import="mdss.entomology.MosquitoCollectionDTO"%>
<%@page import="mdss.entomology.MorphologicalSpecieGroupViewDTO"%>

<%!
 static String join(ArrayList<?> s, String delimiter) {
      StringBuilder builder = new StringBuilder();
      Iterator iter = s.iterator();
      while (iter.hasNext()) {
         builder.append(iter.next());
          if (iter.hasNext()) {
              builder.append(delimiter);
          }
      }
      return builder.toString();
 }
%>

<%@page import="mdss.entomology.MorphologicalSpecieGroup"%>
<%@page import="mdss.mo.SpecieDTO"%>
<%@page import="mdss.mo.IdentificationMethodDTO"%><mjl:messages>
	<mjl:message />
</mjl:messages>
<mjl:form name="mdss.entomology.MosquitoCollection.form.name"
	id="mdss.entomology.MosquitoCollection.form.id" method="POST">
	<mjl:input value="${item.id}" type="hidden" param="id" />
	<dl>
		<dt><label> ${item.collectionMethodMd.displayLabel} </label></dt>
		<dd><mjl:commandLink display="${item.collectionMethod.termName}"
			action="mdss.entomology.CollectionMethodController.view.mojo"
			name="mdss.entomology.CollectionMethod.form.view.link">
			<mjl:property value="${item.collectionMethod.id}" name="id" />
		</mjl:commandLink></dd>
		<dt><label> ${item.dateCollectedMd.displayLabel} </label></dt>
		<dd>${item.dateCollected}</dd>
		<dt><label> ${item.geoEntityMd.displayLabel} </label></dt>
		<dd><mjl:commandLink display="${item.geoEntity.geoId}"
			action="mdss.test.GeoEntityController.view.mojo"
			name="mdss.test.GeoEntity.form.view.link">
			<mjl:property value="${item.geoEntity.id}" name="id" />
		</mjl:commandLink></dd>
	</dl>
	<mjl:command value="Edit"
		action="mdss.entomology.MosquitoCollectionController.edit.mojo"
		name="mdss.entomology.MosquitoCollection.form.edit.button" />
	<br />
</mjl:form>

<div id="basic"></div>
<div id="dt-options"><a id="dt-options-link"
	href="fallbacklink.html">Table Options</a></div>
<div id="columnshowhide"></div>

<div id="dt-dlg" class="inprogress"><span class="corner_tr"></span>
<span class="corner_tl"></span> <span class="corner_br"></span> <span
	class="corner_bl"></span>
<div class="hd">Choose which columns you would like to see:</div>
<div id="dt-dlg-picker" class="bd"></div>

</div>

<div id="buttons">

<span id="addrow" class="yui-button yui-push-button"> <span
	class="first-child">
<button type="button">New Row</button>
</span> </span> <span id="saverows" class="yui-button yui-push-button"> <span
	class="first-child">
<button type="button">Save Rows To DB</button>
</span> </span></div>
<script type="text/javascript" src="js/dataTableWidget.js"></script>
    <%
	ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    
    //JSONArray species = new JSONArray(Arrays.asList(SpecieDTO.getAllTermNames(clientRequest)));
    //out.println("var species = "+species.toString() + " ;");
    
    
    //ResourceBundle RB = ResourceBundle.getBundle("mdss", locale);
    //String greeting = RB.getString("HELLO");
    //out.println("test of bundle is "+greeting);
    %>

<script type="text/javascript">      
    <%
    String[] types_to_load =
	{
	   "mdss.entomology.MorphologicalSpecieGroupView",
	   "mdss.test.GeoEntity", "mdss.test.Terrain"
	};
    
	//ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
	out.println("client_request ='" + clientRequest.getSessionId() + "' ;");
    out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , types_to_load,true));

    JSONArray species = new JSONArray(Arrays.asList(SpecieDTO.getAllTermNames(clientRequest)));
    out.println("var species = "+species.toString() + " ;");
    
    JSONArray ident_methods = new JSONArray(Arrays.asList(IdentificationMethodDTO.getAllTermNames(clientRequest)));
    out.println("var ident_methods = "+ident_methods.toString() + " ;");
    
    %>
    
    table_data = { rows:
    	<% 	  	
    	        MosquitoCollectionDTO mosquito_collection = (MosquitoCollectionDTO) request.getAttribute("item");
    	        MorphologicalSpecieGroupViewDTO[] rows = mosquito_collection.getMorphologicalSpecieGroups();
    		    int i = 1;
    		    ArrayList<String> arr = new ArrayList<String>();
    		     for (MorphologicalSpecieGroupViewDTO row : rows)  {
    		       //row.lock(); 
    		       ArrayList<String> buff = new ArrayList<String>();
    		       buff.add("GroupId:'" + row.getGroupId() + "'");
    		       //buff.add("row:'" + i + "'");
    		       buff.add("Specie:'" + row.getSpecie() + "'");
    		       buff.add("IdentificationMethod:'" + row.getIdentificationMethod() + "'");
    		       buff.add("Quantity:'" + row.getQuantity() + "'");
    		       arr.add("{" +join(buff,",")+ "}");
    		       i++;	       
    		     }
    		     out.println("[" +join(arr,",\n")+ "]");
    		     
    	 %>
    	 ,columnDefs:[
    	            {key:"GroupId",label:"ID",hidden:true},
    	            {key:"Specie",label:'<%=MorphologicalSpecieGroup.getSpecieMd().getDisplayLabel()%>',resizeable:true,editor: new YAHOO.widget.DropdownCellEditor({dropdownOptions:species,disableBtns:true})},
    	            {key:"IdentificationMethod",label:"<%=MorphologicalSpecieGroup.getIdentificationMethodMd().getDisplayLabel()%>",resizeable:true,editor: new YAHOO.widget.RadioCellEditor({radioOptions:ident_methods,disableBtns:true})},
    	            {key:"Quantity",label:"<%=MorphologicalSpecieGroup.getQuantityMd().getDisplayLabel()%>",resizeable:true,editor: new YAHOO.widget.TextboxCellEditor({validator:YAHOO.widget.DataTable.validateNumber,disableBtns:true})},
    	            {key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}
    	            
    	        ],
    	        fields: ["GroupId","Specie","IdentificationMethod","Quantity"],
    	        copy_from_above: ["IdentificationMethod"],
    	        defaults: {GroupId:"",Specie:"",IdentificationMethod:"",Quantity:""},
    	        div_id: "basic",
    	        collection_id: '${item.id}',
        	    data_type: "Mojo.$.mdss.entomology.MorphologicalSpecieGroupView"
    	        
    	    };   
  window.addEventListener('load', MojoGrid.createDataTable(table_data) , false);
</script>