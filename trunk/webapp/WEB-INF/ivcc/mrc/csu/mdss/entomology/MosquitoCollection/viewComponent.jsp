<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>
<%@page import="java.util.*"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONArray"%>
<%@page import="ivcc.mrc.csu.mdss.entomology.MosquitoCollectionDTO"%>
<%@page import="ivcc.mrc.csu.mdss.entomology.MorphologicalSpecieGroupViewDTO"%>
<%@page import="ivcc.mrc.csu.mdss.entomology.MorphologicalSpecieGroup"%>
<%@page import="ivcc.mrc.csu.mdss.mo.*"%>
<%@page import="ivcc.mrc.csu.mdss.util.Halp" %>
<%@page import="org.json.*"%>
<%!
 static String getDropDownMap(AbstractTermDTO[] terms) throws JSONException {
	JSONObject map = new JSONObject();
	for(AbstractTermDTO term : terms)
	 {
	    map.put(term.getDisplayLabel(),term.getId());
	} 
	return map.toString();
}


static String getDropDownMap2(AbstractTermDTO[] terms) throws JSONException {
	JSONArray map = new JSONArray();
	for(AbstractTermDTO term : terms)
	 {
		JSONObject element = new JSONObject();
		element.put("value",term.getId());
		element.put("label",term.getDisplayLabel());
		
		map.put(element);
	} 
	return map.toString();
}



static String getDisplayLabels(AbstractTermDTO[] terms, String name) throws JSONException {
	JSONArray ids = new JSONArray();
	JSONArray labels = new JSONArray();
	for(AbstractTermDTO term : terms)
	 {
	    ids.put(term.getId());
	    labels.put(term.getDisplayLabel());
	} 
	return name +"Ids = " + ids.toString()+"; \n "+ name + "Labels = "+ labels.toString() +";";
}



static String getDataMap(MorphologicalSpecieGroupViewDTO[] rows, String[] attribs) throws JSONException{
	JSONArray map = new JSONArray();
	for(MorphologicalSpecieGroupViewDTO row : rows)
	 {
		JSONObject element = new JSONObject();		
		Class c = row.getClass();	
		for(String attrib : attribs)
		{		
			try
			{
				String value = (String) c.getMethod("get"+attrib).invoke(row).toString();	
				element.put(attrib,value);
			}
			catch (IllegalAccessException x) {
			}
			catch (IllegalArgumentException  x) {
			}
			catch (InvocationTargetException x) {
			}
			catch (NoSuchMethodException x) {
				System.out.println("No such method get"+attrib);
			}

		}	
		map.put(element);
	} 
	return map.toString();
}

static String getColumnSetup(MorphologicalSpecieGroupViewDTO[] rows, String[] attribs) throws JSONException{
	JSONArray map = new JSONArray();
	for(MorphologicalSpecieGroupViewDTO row : rows)
	 {
		JSONObject element = new JSONObject();		
		Class c = row.getClass();
		int colnum = 0;
		for(String attrib : attribs)
		{		
			try
			{
				element.put("key",attrib);
				AttributeCharacterMdDTO md = (AttributeCharacterMdDTO) c.getMethod("get"+attrib+"Md").invoke(row);
			    
				Class mdClass = md.getClass();
				String label = (String) c.getMethod("getDisplayLabel").invoke(row);	
				
				element.put("label",label);
				if(colnum == 0)
				{
					
					
				//	{key:"GroupId",label:"ID",hidden:true},
				}
				
				String value = (String) c.getMethod("get"+attrib).invoke(row).toString();	
				element.put(attrib,value);
			}
			catch (IllegalAccessException x) {
			}
			catch (IllegalArgumentException  x) {
			}
			catch (InvocationTargetException x) {
			}
			catch (NoSuchMethodException x) {
				System.out.println("No such method get"+attrib);
			}

		}	
		map.put(element);
	} 
	return map.toString();
}




%>


<%@page import="java.lang.reflect.InvocationTargetException"%>
<%@page import="com.terraframe.mojo.dataaccess.MdClassDAOIF"%>
<%@page import="com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO"%><mjl:messages>
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

<div id="MorphologicalSpecieGroups"></div>
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


<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
MosquitoCollectionDTO mosquito_collection = (MosquitoCollectionDTO) request.getAttribute("item");
MorphologicalSpecieGroupViewDTO[] rows = mosquito_collection.getMorphologicalSpecieGroups();
String[] attribs = { "GroupId","Specie","IdentificationMethod","Quantity"};

out.println(getColumnSetup(rows,attribs));
%>


<script type="text/javascript">      
    <%String[] types_to_load =
	{
	   "ivcc.mrc.csu.mdss.entomology.MorphologicalSpecieGroupView"
	};
    
	

	// THIS LINE CRASHES TOMCAT WITH Invalid memory access of location 00000000 eip=00000000
	//out.println(Halp.getDropDownMap(SpecieDTO.getAll(clientRequest)));
	//out.println(Halp.getDropDownMap2(Arrays.asList(SpecieDTO.getAll(clientRequest))));
   // SpecieDTO[] arrggg = SpecieDTO.getAll(clientRequest);
		
    out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , types_to_load,true));

    out.println( getDisplayLabels(SpecieDTO.getAll(clientRequest),"Specie"));
    out.println(getDisplayLabels(IdentificationMethodDTO.getAll(clientRequest),"IdentificationMethod"));
    MorphologicalSpecieGroupViewDTO msgView = new MorphologicalSpecieGroupViewDTO(clientRequest);
    
    
    
    %>
    table_data = { rows:
    	  <%=getDataMap(rows,attribs)%>		   
    	 ,columnDefs:[
    	            {key:"GroupId",label:"ID",hidden:true},
    	            {key:"Specie",label:'<%=msgView.getSpecieMd().getDisplayLabel()%>',resizeable:true,editor: new YAHOO.widget.DropdownCellEditor({dropdownOptions:SpecieLabels,disableBtns:true}),save_as_id:true},
    	            {key:"IdentificationMethod",label:"<%=msgView.getIdentificationMethodMd().getDisplayLabel()%>",resizeable:true,editor: new YAHOO.widget.DropdownCellEditor({dropdownOptions:IdentificationMethodLabels,disableBtns:true}),save_as_id:true,copy_from_above:true},
    	            {key:"Quantity",label:"<%=msgView.getQuantityMd().getDisplayLabel()%>",resizeable:true,editor: new YAHOO.widget.TextboxCellEditor({validator:YAHOO.widget.DataTable.validateNumber,disableBtns:true})},
    	            {key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}
    	            
    	        ],
    	        defaults: {GroupId:"",Specie:"",IdentificationMethod:"",Quantity:""},
    	        div_id: "MorphologicalSpecieGroups",
    	        collection_setter: "setCollectionId('${item.id}')",
        	    data_type: "Mojo.$.ivcc.mrc.csu.mdss.entomology.MorphologicalSpecieGroupView"
    	        
    	    };   
    YAHOO.util.Event.onDOMReady(MojoGrid.createDataTable(table_data));
</script>