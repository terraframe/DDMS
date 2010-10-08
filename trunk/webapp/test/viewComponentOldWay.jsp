<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
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
<%@page import="mdss.entomology.SpecieDTO"%>
<%@page import="mdss.entomology.IdentificationMethodDTO"%><mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.entomology.MosquitoCollection.form.name" id="mdss.entomology.MosquitoCollection.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.collectionMethodMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.collectionMethod.keyName}" action="mdss.entomology.CollectionMethodController.view.mojo" name="mdss.entomology.CollectionMethod.form.view.link">
        <mjl:property value="${item.collectionMethod.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.dateCollectedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.dateCollected}
    </dd>
    <dt>
      <label>
        ${item.geoEntityMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.geoEntity.keyName}" action="mdss.test.GeoEntityController.view.mojo" name="mdss.test.GeoEntity.form.view.link">
        <mjl:property value="${item.geoEntity.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="mdss.entomology.MosquitoCollectionController.edit.mojo" name="mdss.entomology.MosquitoCollection.form.edit.button" />
  <br />
</mjl:form>
<dl>
  <dt>
    <label>
      Parent Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink display="True Specie Collection" action="mdss.entomology.CollectionTrueSpecieController.parentQuery.mojo" name="mdss.entomology.CollectionTrueSpecie.parentQuery.link">
          <mjl:property value="${item.id}" name="parentId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="mdss.entomology.MosquitoCollectionController.viewAll.mojo" name="mdss.entomology.MosquitoCollection.viewAll.link" />



<br>
<br>
Import Status:
<span id="imported">Loading Types</span>
<br />
<br>
<br>

<style type="text/css">
.hide {
	display: none;
}

.my-highlight-row {
	font-weight: bold;
}

.delete-button {
	cursor: pointer;
	background: transparent url(/DDMS/imgs/delete.png) no-repeat center
		center;
	width: 16px;
	height: 16px;
}

.insert-button {
	cursor: pointer;
	background: transparent url(/DDMS/imgs/add.png) no-repeat center center;
	width: 16px;
	height: 16px;
}

.yui-skin-sam .yui-dt td.align-right {
	text-align: right;
}

#ft {
	margin-top: 2em;
}

dt {
	font-weight: bold;
	margin-top: 5px;
}
</style>

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

<div id="buttons"><span id="loadRows"
	class="yui-button yui-push-button"> <span class="first-child">
<button type="button" id="create" disabled="disabled">Get
MorphologicalSpecieGroups via Ajax</button>
</span> </span> <span id="addrow" class="yui-button yui-push-button"> <span
	class="first-child">
<button type="button"><fmt:message key="New_Row"/></button>
</span> </span> <span id="saverows" class="yui-button yui-push-button"> <span
	class="first-child">
<button type="button"><fmt:message key="Save_Rows_To_DB"/></button>
</span> </span></div>
<script type="text/javascript" src="js/dataTableWidget.js"></script>
<script type="text/javascript">      
    <%
    String[] types_to_load =
	{
	   "mdss.entomology.IdentificationMethod","mdss.entomology.Specie"
	};
	ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    //String jsonstring = com.runwaysdk.web.json.JSONController.importTypes(clientRequest.getSessionId() , types_to_load);
    //org.json.JSONObject json = new org.json.JSONObject(jsonstring); 
    //out.print(json.getString(com.runwaysdk.transport.conversion.json.JSONReturnObject.RETURN_VALUE));

    JSONArray species = new JSONArray(Arrays.asList(SpecieDTO.getAllTermNames(clientRequest)));
    out.println("var species = "+species.toString());
    
    JSONArray ident_methods = new JSONArray(Arrays.asList(IdentificationMethodDTO.getAllTermNames(clientRequest)));
    out.println("var ident_methods = "+ident_methods.toString());
    
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
	       buff.add("group_id:'" + row.getGroupId() + "'");
	       //buff.add("row:'" + i + "'");
	       buff.add("specie:'" + row.getSpecie() + "'");
	       buff.add("identification_method:'" + row.getIdentificationMethod() + "'");
	       buff.add("quantity:'" + row.getQuantity() + "'");
	       arr.add("{" +join(buff,",")+ "}");
	       i++;	       
	     }
	     out.println("[" +join(arr,",\n")+ "]");
	     
 %>
 ,columnDefs:[
            {key:"group_id",label:"ID",hidden:true},
            {key:"specie",label:"<%=MorphologicalSpecieGroup.getSpecieMd().getDisplayLabel()%>",resizeable:true,editor: new YAHOO.widget.DropdownCellEditor({dropdownOptions:species})},
            {key:"identification_method",label:"<%=MorphologicalSpecieGroup.getIdentificationMethodMd().getDisplayLabel()%>",resizeable:true,editor: new YAHOO.widget.RadioCellEditor({radioOptions:ident_methods,disableBtns:true})},
            {key:"quantity",label:"<%=MorphologicalSpecieGroup.getQuantityMd().getDisplayLabel()%>",resizeable:true,editor: new YAHOO.widget.TextboxCellEditor({validator:YAHOO.widget.DataTable.validateNumber})},
            {key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}
            
        ],
        fields: ["group_id","specie","identification_method","quantity"],
        div_id: "basic"
        
    };

  function getMosquitoCollection()
  {  
    var request = new Mojo.ClientRequest({
    
      // success handler for newly created mosquito
      onSuccess : function(newMosquitoCollection){
        var info = "Got The MosquitoCollection-- \n";
        info += "toString: "+ newMosquitoCollection.toString();
        //alert(info);
		getRows(newMosquitoCollection);
      },
      
      // alert the exception message
      onFailure : function(e){
        alert(e.getLocalizedMessage());
      }
    });
    var mosquitoCollection = new Mojo.$.mdss.entomology.MosquitoCollection;
    Mojo.$.mdss.entomology.MosquitoCollection.get(request,"<%= request.getParameter(mdss.entomology.MosquitoCollectionDTO.ID) %>");
  }
  
  function getRows(mosquitoCollection)
  {
  var request = new Mojo.ClientRequest({
    
      // success handler for newly created mosquito
      onSuccess : function(rows){
        var el = new YAHOO.util.Element('rows'); 
         for each (row in rows)
         {
		    YAHOO.example.DynamicData.addRow(row);
	    }
      },
      
      // alert the exception message
      onFailure : function(e){
        alert(e.getLocalizedMessage());
      }
    });
    var rows = mosquitoCollection.getAllMorphologicalSpecieGroup(request);
  }
  //TODO: Load these types via jsp
  var species;
  var ident_methods;
  function loadTypes(){
  
    // import the types needed to create a new GeoEntity
    var request = new Mojo.ClientRequest({
      onSuccess : function(){

        // import worked, so enable GeoEntity creation
        document.getElementById('imported').innerHTML = 'Success';
        var create = document.getElementById('create');
        create.disabled = '';
        //create.addEventListener('click', createGeoEntity, false);
        create.addEventListener('click', getMosquitoCollection, false);
        YAHOO.example.DynamicData = createDataTable();
      },
      onFailure : function(){
        document.getElementById('imported').innerHTML = 'Failure';
      }
    });
    
    var types = ['mdss.test.GeoEntity', 'mdss.test.Terrain','mdss.entomology.MosquitoCollection',
    'mdss.entomology.MorphologicalSpecieGroup','mdss.entomology.Specie','mdss.entomology.IdentificationMethod'];
    var options = {appendTo : document.getElementsByTagName('head')[0]};
    Mojo.importTypes(request, types, options);
  }

  //window.addEventListener('load', loadTypes , false);
  window.addEventListener('load', createDataTable , false);

</script>