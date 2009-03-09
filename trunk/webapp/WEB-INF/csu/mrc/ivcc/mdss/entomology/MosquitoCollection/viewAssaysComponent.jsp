<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>
<%@page import="java.util.*"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%> 
<%@page import="org.json.JSONArray"%>
<%@page import="csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTO"%>
<%@page import="csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupViewDTO"%>
<%@page import="csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroup"%>
<%@page import="csu.mrc.ivcc.mdss.entomology.assay.*"%>
<%@page import="csu.mrc.ivcc.mdss.mo.*"%>
<%@page import="csu.mrc.ivcc.mdss.util.Halp" %>
<%@page import="org.json.*"%>
<%@page import="java.lang.reflect.InvocationTargetException"%>
<%@page import="com.terraframe.mojo.transport.metadata.*"%>
<%@page import="csu.mrc.ivcc.mdss.entomology.MosquitoViewDTO"%>
<%@page import="com.terraframe.mojo.business.ViewDTO"%>
<%@page import="csu.mrc.ivcc.mdss.entomology.assay.AssayTestResult"%>
<%@page import="csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupViewDTO"%>
<%@page import="csu.mrc.ivcc.mdss.entomology.assay.biochemical.BiochemicalAssayTestResultDTO"%>
<%@page import="com.terraframe.mojo.dataaccess.MdAttributeVirtualDAOIF"%>
<%@page import="csu.mrc.ivcc.mdss.entomology.MosquitoView"%>
<%@page import="com.terraframe.mojo.business.generation.GenerationUtil"%>
<%@page import="csu.mrc.ivcc.mdss.entomology.assay.biochemical.BiochemicalAssayTestResult"%>
<%@page import="csu.mrc.ivcc.mdss.entomology.assay.infectivity.InfectivityAssayTestResult"%>
<%@page import="csu.mrc.ivcc.mdss.entomology.assay.molecular.MolecularAssayTestResult"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.regex.Pattern"%>


<%!static String getDisplayLabels(AbstractTermDTO[] terms, String name) throws JSONException {
	JSONArray ids = new JSONArray();
	JSONArray labels = new JSONArray();
	for(AbstractTermDTO term : terms)
	 {
	    ids.put(term.getId());
	    labels.put(term.getDisplayLabel());
	} 
	return name +"Ids = " + ids.toString()+"; \n "+ name + "Labels = "+ labels.toString() +";";
}




static String getDataMap(ViewDTO[] rows, String[] attribs,ViewDTO view) throws JSONException{
	JSONArray map = new JSONArray();
	ArrayList<String> ordered_attribs = new ArrayList(Arrays.asList(attribs));
	for(String a : view.getAccessorNames())
	{
		if(! ordered_attribs.contains(a)  )
		{
			ordered_attribs.add(a.substring(0,1).toUpperCase() + a.substring(1));
		}
	}
	System.out.println("attribs length =  "+ordered_attribs.size());
	for(ViewDTO row : rows)
	 {
		JSONObject element = new JSONObject();		
		Class c = row.getClass();	

		for(String attrib : ordered_attribs)
		{		
			try
			{
				System.out.println("Setting "+attrib);
				String value = (String) c.getMethod("get"+attrib).invoke(row).toString();
				System.out.println("Setting "+attrib+" to "+value);
				      
                if("TestDate" == attrib )
                {
                	SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                	element.put(attrib,df.format(new Date(value)));
                }
                else
                {
                	//FIXME: this is a hack for enums
                   String clean_value = value.replaceAll("\\[", "").replaceAll("\\]", "");
                   element.put(attrib,clean_value );
                }
                
			}
			catch (IllegalAccessException x) {
				System.out.println(x+" "+x.getCause());
			}
			catch (IllegalArgumentException  x) {
				System.out.println(x+" "+x.getCause());
			}
			catch (InvocationTargetException x) {
				System.out.println(x+" "+x.getCause());
			}
			catch (NoSuchMethodException x) {
				System.out.println("No such method get"+attrib);
			}
			catch (NullPointerException x) {
				System.out.println("Null Pointer Exception get"+attrib);
			}

		}	
		map.put(element);
	} 
	return map.toString().replaceAll(",",",\n");
}

static String getDropdownSetup(ViewDTO view, String[] attribs, String extra_rows,ClientRequestIF clientRequest ) throws JSONException
	{
    ArrayList<String> arr = new ArrayList<String>();
	int colnum = 0;
	Class v = view.getClass();
	//List<String> v_attribs = view.getAttributeNames();
	ArrayList<String> ordered_attribs = new ArrayList(Arrays.asList(attribs));
	for(String a : view.getAccessorNames())
	{
		if(! ordered_attribs.contains(a) && a.length() > 3 )
		{
			ordered_attribs.add(a.substring(0,1).toUpperCase() + a.substring(1));
		}
	}
	
	ArrayList<String> dropdownbuff = new ArrayList<String>();
	for(String attrib : ordered_attribs)
	 {
		try
		{			
			AttributeMdDTO md = (AttributeMdDTO) v.getMethod("get"+attrib+"Md").invoke(view); 
			Class mdClass = md.getClass();	
			if(md instanceof AttributeReferenceMdDTO)
			{
				Class mo_term = md.getJavaType();
				if(AbstractTermDTO.class.isAssignableFrom(mo_term) )
				{
			      AbstractTermDTO[] terms = (AbstractTermDTO[]) mo_term.getMethod("getAll",new Class[] {ClientRequestIF.class}).invoke(null,clientRequest);
				  dropdownbuff.add(getDisplayLabels(terms,attrib));
				}					
			}
		}
		catch (Exception x) {
			System.out.println("Other exception on "+attrib +" " + x.getMessage());
		}
		colnum ++;
	}	
	if(extra_rows.length() > 0)
	{
		arr.add(extra_rows);
	}
	return (Halp.join(dropdownbuff,"\n"));
}


static String getColumnSetup(ViewDTO view, String[] attribs, String extra_rows, boolean autoload ) throws JSONException
{
ArrayList<String> arr = new ArrayList<String>();
int colnum = 0;
Class v = view.getClass();
//List<String> v_attribs = view.getAttributeNames();
ArrayList<String> ordered_attribs = new ArrayList(Arrays.asList(attribs));
for(String a : view.getAccessorNames())
{
	String upcased_attrib = a.substring(0,1).toUpperCase() + a.substring(1);
    if(! ordered_attribs.contains(upcased_attrib) && a.length() > 3 && autoload)
	{
		ordered_attribs.add(upcased_attrib);
	}
}

ArrayList<String> dropdownbuff = new ArrayList<String>();
for(String attrib : ordered_attribs)
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
			if(! Arrays.asList(attribs).contains(attrib))
			{
				buff.add("hidden:true");
			}
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
				editor = "new YAHOO.widget.TextboxCellEditor({disableBtns:true})";
			}
			if(md instanceof AttributeDateMdDTO)
			{
				buff.add("formatter:YAHOO.widget.DataTable.formatDate");
                editor = "new YAHOO.widget.DateCellEditor({disableBtns:true})";
                //editor = "new YAHOO.widget.DateCellEditor({calendar:MojoCal.init(),disableBtns:true})";
				//editor = "new YAHOO.widget.TextboxCellEditor({disableBtns:true})";
			}
			if(md instanceof AttributeEnumerationMdDTO)
			{
				editor = "new YAHOO.widget.RadioCellEditor({radioOptions:['";    
				editor += Halp.join(((AttributeEnumerationMdDTO) md).getEnumNames(),"','");  
                editor += "'],disableBtns:true})";
			}
			if(md instanceof AttributeReferenceMdDTO)
			{
				Class refrenced_class = md.getJavaType();
				
				if(AssayTestResult.class.isAssignableFrom(refrenced_class) )
				{
					editor = "new YAHOO.widget.TextboxCellEditor({disableBtns:true})";
				}
				
				if(AbstractTermDTO.class.isAssignableFrom(refrenced_class) )
				{
				   editor = "new YAHOO.widget.DropdownCellEditor({dropdownOptions:"+attrib+"Labels,disableBtns:true})";		
				   buff.add("save_as_id:true");	
				}
				else
				{
					editor = "new YAHOO.widget.TextboxCellEditor({disableBtns:true})";
				}
				
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
	catch (Exception x) {
		System.out.println("Other exception on "+attrib +" " + x.getMessage());
	}
	colnum ++;
}	
if(extra_rows.length() > 0)
{
	arr.add(extra_rows);
}
return ("[" +Halp.join(arr,",\n")+ "]");
//return (Halp.join(dropdownbuff,",\n")+ ",columnDefs:[" +Halp.join(arr,",\n")+ "]");
}


static String buildChekboxTable(MosquitoViewDTO view, Class superAssayClass ) throws JSONException{
	String s = "<table><tr><th colspan=\"2\">";
	s += superAssayClass.getSimpleName().substring(0,superAssayClass.getSimpleName().indexOf("Assay")) +"</th></tr>";
	
    	Class viewClass = view.getClass();
     
    	MosquitoView mv = new MosquitoView();
     
    	 Map<Class<AssayTestResult>, MdAttributeVirtualDAOIF> assayMap = mv.getAssayMap();
     
        for (Class<AssayTestResult> c : assayMap.keySet())
        {
          // Get the result
          MdAttributeVirtualDAOIF mdAttribute = assayMap.get(c);
          String attributeName = GenerationUtil.upperFirstCharacter(mdAttribute.getAccessorName());
    		try
    		{      
         
         		if(superAssayClass.isAssignableFrom(c) )
         		{
         			 s += "<tr><td><input type=\"checkbox\" id =\""+ attributeName + "\" onclick=\"";
                     s += "showCol('"+ attributeName + "',this.checked)";
                     s += "\"/></td><td>" ;
         			 s += attributeName + "</td></tr>";
         		}
    		}
    		catch (Exception e) {
    			System.out.println("Exception on "+e.getMessage() +" " + e);
    		}
    	}
		
	return s + "</table>";
}

%>






<%@page import="java.text.SimpleDateFormat"%><div id="cal1Container" class="yui-skin-sam"></div> 

<mjl:messages>
	<mjl:message />
</mjl:messages>

<h2>Mosquito Collection</h2>
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

</mjl:form>

<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
MosquitoCollectionDTO mosquito_collection = (MosquitoCollectionDTO) request.getAttribute("item");
MosquitoViewDTO[] rows = mosquito_collection.getMosquitos();
MosquitoViewDTO mdView = new MosquitoViewDTO(clientRequest);
String[] attribs = { "MosquitoId","Specie","IdentificationMethod","Generation","Isofemale","Sex","TestDate"};

String delete_row = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
//out.println(getColumnSetup(mdView,attribs,delete_row,clientRequest));

%>



<h2>Mosquitos</h2>
<div class="fldContainer">
    <div class="fcTop">
<br>
    <div id="checkBoxContanier" >
        <div style="float:left;margin-left:3em;">
<%=buildChekboxTable(mdView, BiochemicalAssayTestResult.class) %>
</div>
<div style="float:left;margin-left:3em;">
<%=buildChekboxTable(mdView, InfectivityAssayTestResult.class) %>
</div>
<div style="float:left;margin-left:3em;">
<%=buildChekboxTable(mdView, MolecularAssayTestResult.class) %>
</div>
</div>

<div id="buttons">
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<div id="Mosquitos"></div>
<span id="MosquitosAddrow" class="yui-button yui-push-button"> <span
 class="first-child">
<button type="button">New Row</button>
</span> </span> <span id="MosquitosSaverows" class="yui-button yui-push-button"> <span
 class="first-child">
<button type="button">Save Rows To DB</button>
</span> </span></div>

<script type="text/javascript">   

function showCol(key,checked)
{
  if(checked)
  {
    table_data.myDataTable.showColumn(key);
    table_data.myDataTable.showColumn(key+'Method');
  }
  else
  {
    table_data.myDataTable.hideColumn(key);
    table_data.myDataTable.hideColumn(key+'Method');
  }
}

    <%String[] types_to_load =
  {
     "csu.mrc.ivcc.mdss.entomology.MosquitoView","csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupView"
  };
    out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , types_to_load,true));   
    %>
    <%=getDropdownSetup(mdView,attribs,delete_row,clientRequest)%>
    MojoCal.init()
    table_data = {rows:<%=getDataMap(rows,attribs,mdView)%>,      
                columnDefs:<%=getColumnSetup(mdView,attribs,delete_row,true)%>,
              defaults: {},
              copy_from_above: ["IdentificationMethod"],
              div_id: "Mosquitos",
              collection_setter: "setCollection('${item.id}')",
              data_type: "Mojo.$.csu.mrc.ivcc.mdss.entomology.MosquitoView",  
                width:"60em"        
          };   
    YAHOO.util.Event.onDOMReady(MojoGrid.createDataTable(table_data));
</script>
 
    <div class="fcTopLeft"></div></div>
    <div class="fcBottom"><div class="fcBottomLeft"></div></div>
    

</div>


<br>
<br>
<br>
<h2>UninterestingSpecieGroups</h2>
<div class="fldContainer">
    <div class="fcTop">
    <div id="buttons">
    <br>
<br>
<br>
<div id="UninterestingSpecieGroups"></div>

<span id="UninterestingSpecieGroupsAddrow" class="yui-button yui-push-button"> <span
  class="first-child">
<button type="button">New Row</button>
</span> </span> <span id="UninterestingSpecieGroupsSaverows" class="yui-button yui-push-button"> <span
  class="first-child">
<button type="button">Save Rows To DB</button>
</span> </span></div>
<%
UninterestingSpecieGroupViewDTO[] unint_rows = mosquito_collection.getUninterestingSpecieGroups();
UninterestingSpecieGroupViewDTO mdUnIntView = new UninterestingSpecieGroupViewDTO(clientRequest);
String[] unint_attribs = { "GroupId","SampleId","Specie","IdentificationMethod","Quantity"};
%>

<script type="text/javascript"> 
<%=getDropdownSetup(mdUnIntView,unint_attribs,delete_row,clientRequest)%>

UninterestingSpecieGroupData = { rows:<%=getDataMap(unint_rows,unint_attribs,mdUnIntView)%>,       
       columnDefs: <%=getColumnSetup(mdUnIntView,unint_attribs,delete_row,false)%>,
              defaults: {},
              div_id: "UninterestingSpecieGroups",
              copy_from_above: ["IdentificationMethod"],
              collection_setter: "setCollection('${item.id}')",
              data_type: "Mojo.$.csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupView"
              
          };   
    YAHOO.util.Event.onDOMReady(MojoGrid.createDataTable(UninterestingSpecieGroupData));
</script>
    
    <div class="fcTopLeft"></div></div>
    <div class="fcBottom"><div class="fcBottomLeft"></div></div>

</div>

