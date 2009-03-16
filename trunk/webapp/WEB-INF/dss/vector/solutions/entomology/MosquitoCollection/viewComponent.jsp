<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>
<%@page import="java.util.*"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONArray"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO"%>
<%@page import="dss.vector.solutions.entomology.MorphologicalSpecieGroup"%>
<%@page import="dss.vector.solutions.mo.*"%>
<%@page import="dss.vector.solutions.util.Halp" %>
<%@page import="org.json.*"%>
<%@page import="java.lang.reflect.InvocationTargetException"%>
<%@page import="com.terraframe.mojo.transport.metadata.*"%>
<%@page import="com.terraframe.mojo.business.ViewDTO"%>

<%!static String getDropDownMap(AbstractTermDTO[] terms) throws JSONException {
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


static String getColumnSetup(ViewDTO view, String[] attribs, String extra_rows) throws JSONException
	{
    ArrayList<String> arr = new ArrayList<String>();
	int colnum = 0;
	Class v = view.getClass();
	for(String attrib : attribs)
	 {
		try
		{
			ArrayList<String> buff = new ArrayList<String>();
			
			buff.add("key:'"+attrib+"'");	
			
			AttributeMdDTO md = (AttributeMdDTO) v.getMethod("get"+attrib+"Md").invoke(view); 
			Class mdClass = md.getClass();		
			//buff.add("class:"+mdClass.toString());								
			String label = (String) mdClass.getMethod("getDisplayLabel").invoke(md).toString();	
			buff.add("label:'"+label+"'");								
			if(colnum == 0)
			{
				buff.add("hidden:true");
			}
			else
			{
				String editor = "null";
				
				if(md instanceof AttributeIntegerMdDTO)
				{
					editor = "new YAHOO.widget.TextboxCellEditor({validator:YAHOO.widget.DataTable.validateNumber,disableBtns:true})";
				}
				if(md instanceof AttributeBooleanMdDTO)
				{
					editor = "new YAHOO.widget.CheckboxCellEditor({checkboxOptions:['true','false'],disableBtns:true})";
				}
				if(md instanceof AttributeCharacterMdDTO)
				{
					editor = "new YAHOO.widget.DropdownCellEditor({dropdownOptions:"+attrib+"Labels,disableBtns:true})";
					buff.add("save_as_id:true");
				}
				if(md instanceof AttributeReferenceMdDTO)
				{
					editor = "new YAHOO.widget.DropdownCellEditor({dropdownOptions:"+attrib+"Labels,disableBtns:true})";					
					buff.add("save_as_id:true");
				}
				buff.add("editor:"+ editor);
		    }
			
			arr.add("{" +Halp.join(buff,",")+ "}"); 
		}
		catch (IllegalAccessException x) {
			System.out.println("IllegalAccessException on " + attrib +" " + x.getMessage());			
		}
		catch (IllegalArgumentException  x) {
			System.out.println("IllegalArgumentException on "+attrib +" " + x.getMessage());
		}
		catch (InvocationTargetException x) {
			System.out.println("InvocationTargetException on "+attrib +" " + x.getMessage());
		}
		catch (NoSuchMethodException x) {
			System.out.println("No such method on "+attrib + x.getMessage());
		}	
		colnum ++;
	}	
	if(extra_rows.length() > 0)
	{
		arr.add(extra_rows);
	}
	return ("[" +Halp.join(arr,",\n")+ "]");
}%>





<mjl:messages>

	<mjl:message />
</mjl:messages>

<mjl:form name="mdss.entomology.MosquitoCollection.form.name"
 id="mdss.entomology.MosquitoCollection.form.id" method="POST">
<div class="fldContainer">
    <div class="fcTop"><div class="fcTopLeft"></div></div>
    <div class="fcBottom"><div class="fcBottomLeft"></div></div>
    <div style="position:absolute; left:20px; top:25px;">

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
</div>
</div>
<div class="submitButton_bl"></div>    
 <mjl:command value="Edit"
  action="dss.vector.solutions.entomology.MosquitoCollectionController.edit.mojo"
  name="dss.vector.solutions.entomology.MosquitoCollection.form.edit.button" classes="submitButton"/>
  </mjl:form>

<br/>
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

<span id="MorphologicalSpecieGroupsAddrow" class="yui-button yui-push-button"> 
<span class="first-child">
<button type="button">New Row</button>
 </span> 
 </span> 

<span id="MorphologicalSpecieGroupsSaverows" class="yui-button yui-push-button"> 
<span class="first-child">
<button type="button">Save Rows To DB</button>
</span> </span></div>


<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
Object ojbect = request.getAttribute("item");
MosquitoCollectionDTO mosquito_collection = (MosquitoCollectionDTO) ojbect;
MorphologicalSpecieGroupViewDTO[] rows = mosquito_collection.getMorphologicalSpecieGroups();
String[] attribs = { "GroupId","Specie","IdentificationMethod","Quantity"};
MorphologicalSpecieGroupViewDTO mdView = new MorphologicalSpecieGroupViewDTO(clientRequest);

String delete_row = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
//out.println(getColumnSetup(mdView,attribs,delete_row));
%>

<script type="text/javascript">      
    <%String[] types_to_load =
	{
	   "dss.vector.solutions.entomology.MorphologicalSpecieGroupView"
	};
    
	// THIS LINE CRASHES TOMCAT WITH Invalid memory access of location 00000000 eip=00000000
	//out.println(Halp.getDropDownMap(SpecieDTO.getAll(clientRequest)));
	//out.println(Halp.getDropDownMap2(Arrays.asList(SpecieDTO.getAll(clientRequest))));
   // SpecieDTO[] arrggg = SpecieDTO.getAll(clientRequest);
		
    out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , types_to_load,true));

    out.println( getDisplayLabels(SpecieDTO.getAll(clientRequest),"Specie"));
    out.println(getDisplayLabels(IdentificationMethodDTO.getAll(clientRequest),"IdentificationMethod"));

    %>
    table_data = { rows:<%=getDataMap(rows,attribs)%>,		   
    	 columnDefs: <%=getColumnSetup(mdView,attribs,delete_row)%>,
    	        defaults: {GroupId:"",Specie:"",IdentificationMethod:"",Quantity:""},
    	        div_id: "MorphologicalSpecieGroups",
    	        copy_from_above: ["IdentificationMethod"],
    	        collection_setter: "setCollectionId('${item.id}')",
        	    data_type: "Mojo.$.dss.vector.solutions.entomology.MorphologicalSpecieGroupView"
    	        
    	    };   
    YAHOO.util.Event.onDOMReady(MojoGrid.createDataTable(table_data));
</script>