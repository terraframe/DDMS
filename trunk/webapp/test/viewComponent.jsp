<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>

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
<mjl:messages>
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
      <ul>
        <c:forEach var="enumName" items="${item.collectionMethodEnumNames}">
          <li>
            ${item.collectionMethodMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
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

<mjl:commandLink display="View All" action="mdss.entomology.MosquitoCollectionController.viewAll.mojo" name="mdss.entomology.MosquitoCollection.viewAll.link" />

<br>
<br>
Import Status: <span id="imported">Loading Types</span><br />
<br>
<br>

<style type="text/css">
				.hide {
					display:none;
				}
				.my-highlight-row {
					font-weight:bold;
				}
				.delete-button {
					cursor:pointer;
					background: transparent url(/MDSS/imgs/delete.png) no-repeat center center;
					width:16px;height:16px;
				}
				.insert-button {
					cursor:pointer;
					background: transparent url(/MDSS/imgs/add.png) no-repeat center center;
					width:16px;height:16px;
				}
				.yui-skin-sam .yui-dt td.align-right {
					text-align:right;
				}
				#ft {
					margin-top:2em;
				}
				dt {
					font-weight:bold;
					margin-top:5px;
				}
				
</style>

<div id="basic"></div>
<div id="dt-options"><a id="dt-options-link" href="fallbacklink.html">Table Options</a></div>
<div id="columnshowhide"></div>

<div id="dt-dlg" class="inprogress">
<span class="corner_tr"></span>
<span class="corner_tl"></span>
<span class="corner_br"></span>
<span class="corner_bl"></span>
<div class="hd">
    Choose which columns you would like to see:
</div>
<div id="dt-dlg-picker" class="bd"></div>

</div>




<div id="buttons"> 

<span id="loadRows" class="yui-button yui-push-button"> 
    <span class="first-child"> 
        <button type="button" id="create" disabled="disabled" >Get MorphologicalSpecieGroups via Ajax</button> 
    </span> 
</span> 
    
 <span id="addrow" class="yui-button yui-push-button"> 
    <span class="first-child"> 
        <button type="button">New Row</button> 
    </span> 
</span> 


<span id="saverows" class="yui-button yui-push-button"> 
    <span class="first-child"> 
        <button type="button">Save Rows To DB</button> 
    </span> 
</span> 
    

</div>  	  
       
    <script type="text/javascript">
    <%
    String[] types_to_load =
	{
	   "mdss.entomology.IdentificationMethod"
	};
	ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String jsonstring = com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , types_to_load);
    org.json.JSONObject json = new org.json.JSONObject(jsonstring); 
    out.print(json.getString(com.terraframe.mojo.transport.conversion.json.JSONReturnObject.RETURN_VALUE));
    %>
    // var species = Mojo.$.mdss.entomology.Specie.values().map(function(arg){return arg.getDisplayLabel();});
     var ident_methods = Mojo.$.mdss.entomology.IdentificationMethod.values().map(function(arg){return arg.getDisplayLabel();});
     
       
       table_data = { rows:
<% 	  	    
	    List<mdss.entomology.MorphologicalSpecieGroupDTO> rows = (List<mdss.entomology.MorphologicalSpecieGroupDTO>) request.getAttribute("rows");
	    int i = 1;
	    ArrayList<String> arr = new ArrayList<String>();
	     for (mdss.entomology.MorphologicalSpecieGroupDTO row : rows)  {
	       row.lock(); 
	       ArrayList<String> buff = new ArrayList<String>();
	       buff.add("id:'" + row + "'");
	       buff.add("row:'" + i + "'");
	       //ASK NAFIE WHY THERE IS NO getDisplayLabel() here
	       buff.add("specie_name:'" + row.getSpecie().get(0).getName() + "'");
	       buff.add("ident_method:'" + row.getIdentificationMethod().get(0).getName() + "'");
	       buff.add("qty:'" + row.getQuanity() + "'");
	       arr.add("{" +join(buff,",")+ "}");
	       i++;	       
	     }
	     out.println("[" +join(arr,",\n")+ "]");
	     
 %>
 ,columnDefs:[
            {key:"id",label:"Row",hidden:true},
            {key:"row",label:"Row",resizeable:true,sortable:true},
            {key:"specie_name",label:"<%=mdss.entomology.MorphologicalSpecieGroup.getSpecieMd().getDisplayLabel()%>",resizeable:true,editor: new YAHOO.widget.DropdownCellEditor({dropdownOptions:species})},
            {key:"ident_method",label:"<%=mdss.entomology.MorphologicalSpecieGroup.getIdentificationMethodMd().getDisplayLabel()%>",resizeable:true,editor: new YAHOO.widget.RadioCellEditor({radioOptions:ident_methods,disableBtns:true})},
            {key:"qty",label:"<%=mdss.entomology.MorphologicalSpecieGroup.getQuanityMd().getDisplayLabel()%>",resizeable:true,editor: new YAHOO.widget.TextboxCellEditor({validator:YAHOO.widget.DataTable.validateNumber})},
            {key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}
            
        ],
        fields: ["id","row","specie_name","ident_method","qty"],
        div_id: "basic"
        
    };
     function createDataTable() {
        var data = {specie_name:"",ident_method:"",qty:""};
        //load the data

        this.myDataSource = new YAHOO.util.DataSource(table_data.rows);
        this.myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        this.myDataSource.responseSchema = {
            fields: table_data.fields
        };
        
        this.myDataTable = new YAHOO.widget.DataTable(table_data.div_id,
                table_data.columnDefs, this.myDataSource, {});
                
        var i = (table_data.rows.length + 1);
        var  bReverseSorted = false;

        // Track when Column is reverse-sorted, since new data will come in out of order
        var trackReverseSorts = function(oArg) {
            bReverseSorted = (oArg.dir === YAHOO.widget.DataTable.CLASS_DESC);
        };
        this.myDataTable.subscribe("columnSortEvent", trackReverseSorts);
        
             // Set up editing flow
        var highlightEditableCell = function(oArgs) {
            var elCell = oArgs.target;
            if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {
                this.highlightCell(elCell);
            }
        };
        this.myDataTable.subscribe("cellMouseoverEvent", highlightEditableCell);
        this.myDataTable.subscribe("cellMouseoutEvent", this.myDataTable.onEventUnhighlightCell);
        
        var onCellClick = function(oArgs) {
        var target = oArgs.target;
        column = this.getColumn(target),
        record = this.getRecord(target);
        row_id = record.getData('id');
        

    switch (column.action) {
        case 'delete':
            if (confirm('Are you sure you want to delete row ' + record.getData('row') + '?')) {
	            var request = new Mojo.ClientRequest({
	              
	              dataTable : this,
			      // alert the exception message
			      onSuccess : function(deletedMorphologicalSpecieGroup){
			         //TELL NAFIE THAT THIS SCOPING THING IS AWSOME
			         request.dataTable.deleteRow(target); 
			         alert('row deleted on server');
			         
				  },
			      onFailure : function(e){
			        alert(e.getLocalizedMessage());
			      }
			    });	   
			    //ASK NAFIE ABOUT THIS 
	            //Mojo.$.mdss.entomology.MorphologicalSpecieGroup.delete(request,row_id); 
	            Mojo.deleteEntity(request,row_id);                     
            } 
            break;
        default:
            this.onEventShowCellEditor(oArgs);
            break;
         }
     };

        
        
        this.myDataTable.subscribe("cellClickEvent", onCellClick);
        
        
        
        // Add passed in rows
        this.saveRows = function(){
           var request = new Mojo.ClientRequest({
			// success handler for newly created mosquito
			onSuccess : function(newMorphologicalSpecieGroup){
			    var info = "Got The MorphologicalSpecieGroup \n";
			    info += "toString: "+ newMorphologicalSpecieGroup.toString();
			    alert(info);
			  },
			  
			  // alert the exception message
			  onFailure : function(e){
			    alert(e.getLocalizedMessage());
			  }
			});
			
			var MorphologicalSpecieGroup = new Mojo.$.mdss.entomology.MorphologicalSpecieGroup;
			Mojo.$.mdss.entomology.MosquitoCollection.get(request,"<%= request.getParameter(mdss.entomology.MosquitoCollectionDTO.ID) %>");
            
            var record = YAHOO.widget.DataTable._cloneObject(data);
            record.row = i++;
            record.qty = row.getQuanity();
		    record.specie_name = row.getSpecie()[0].getDisplayLabel();
		    record.ident_method = row.getIdentificationMethod()[0].getDisplayLabel();
            this.myDataTable.addRow(record);
        };
		
		 // Add passed in rows
        this.addRow = function(row){
            // Clear sort when necessary
            if(bReverseSorted) {
                this.myDataTable.set("sortedBy", null);
            }
            
            var record = YAHOO.widget.DataTable._cloneObject(data);
            record.row = i++;
            record.qty = row.getQuanity();
		    record.specie_name = row.getSpecie()[0].getDisplayLabel();
		    record.ident_method = row.getIdentificationMethod()[0].getDisplayLabel();
            this.myDataTable.addRow(record);
        };
        
         // Add one row to the bottom
        var btnAddRow = new YAHOO.widget.Button("addrow");
        btnAddRow.on("click", function() {
            // Clear sort when necessary
            if(bReverseSorted) {
                this.myDataTable.set("sortedBy", null);
            }
            
            var record = YAHOO.widget.DataTable._cloneObject(data);
            record.row = i++;
            /*
            var newRow = new Mojo.$.mdss.entomology.MorphologicalSpecieGroup();
           
		    var request = new Mojo.ClientRequest({
		      dataTable : this,
		      // success handler for newly created mosquito
		      onSuccess : function(newGeoEntity){
		        var info = "New GeoEntity Created-- \n";
		        
		        alert(info);
		      },
		      
		      // alert the exception message
		      onFailure : function(e){
		        alert(e.getLocalizedMessage());
		      }
		    });
		    
		    newRow.apply(request);
            */
            this.myDataTable.addRow(record);
        },this,true);
        
        
        //stuff to turn cols on and off
	            // Shows dialog, creating one when necessary
	    var newCols = true;
	    var showDlg = function(e) {
	        YAHOO.util.Event.stopEvent(e);
	
	        if(newCols) {
	            // Populate Dialog
	            // Using a template to create elements for the SimpleDialog
	            var allColumns = YAHOO.example.DynamicData.myDataTable.getColumnSet().keys;
	            var elPicker = YAHOO.util.Dom.get("dt-dlg-picker");
	            var elTemplateCol = document.createElement("div");
	            YAHOO.util.Dom.addClass(elTemplateCol, "dt-dlg-pickercol");
	            var elTemplateKey = elTemplateCol.appendChild(document.createElement("span"));
	            YAHOO.util.Dom.addClass(elTemplateKey, "dt-dlg-pickerkey");
	            var elTemplateBtns = elTemplateCol.appendChild(document.createElement("span"));
	            YAHOO.util.Dom.addClass(elTemplateBtns, "dt-dlg-pickerbtns");
	            var onclickObj = {fn:handleButtonClick, obj:this, scope:false };
	            
	            // Create one section in the SimpleDialog for each Column
	            var elColumn, elKey, elButton, oButtonGrp;
	            for(var i=0,l=allColumns.length;i<l;i++) {
	                var oColumn = allColumns[i];
	                
	                // Use the template
	                elColumn = elTemplateCol.cloneNode(true);
	                
	                // Write the Column key
	                elKey = elColumn.firstChild;
	                elKey.innerHTML = oColumn.getKey();
	                
	                // Create a ButtonGroup
	                oButtonGrp = new YAHOO.widget.ButtonGroup({ 
	                                id: "buttongrp"+i, 
	                                name: oColumn.getKey(), 
	                                container: elKey.nextSibling
	                });
	                oButtonGrp.addButtons([
	                    { label: "Show", value: "Show", checked: ((!oColumn.hidden)), onclick: onclickObj},
	                    { label: "Hide", value: "Hide", checked: ((oColumn.hidden)), onclick: onclickObj}
	                ]);
	                                
	                elPicker.appendChild(elColumn);
	            }
	            newCols = false;
	    	}
	        myDlg.show();
	    };
	    var hideDlg = function(e) {
	        this.hide();
	    };
	    var handleButtonClick = function(e, oSelf) {
	        var sKey = this.get("name");
	        if(this.get("value") === "Hide") {
	            // Hides a Column
	            myDataTable.hideColumn(sKey);
	        }
	        else {
	            // Shows a Column
	            myDataTable.showColumn(sKey);
	        }
	    };
	    
	    // Create the SimpleDialog
	    YAHOO.util.Dom.removeClass("dt-dlg", "inprogress");
	    var myDlg = new YAHOO.widget.SimpleDialog("dt-dlg", {
	            width: "30em",
			    visible: false,
			    modal: true,
			    buttons: [ 
					{ text:"Close",  handler:hideDlg }
	            ],
	            fixedcenter: true,
	            constrainToViewport: true
		});
		myDlg.render();
	
	    // Nulls out myDlg to force a new one to be created
	    myDataTable.subscribe("columnReorderEvent", function(){
	        newCols = true;
	        YAHOO.util.Event.purgeElement("dt-dlg-picker", true);
	        YAHOO.util.Dom.get("dt-dlg-picker").innerHTML = "";
	    }, this, true);
		
		// Hook up the SimpleDialog to the link
		YAHOO.util.Event.addListener("dt-options-link", "click", showDlg, this, true);
      return this;
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
        species = Mojo.$.mdss.entomology.Specie.values().map(function(arg){return arg.getDisplayLabel();});
        ident_methods = Mojo.$.mdss.entomology.IdentificationMethod.values().map(function(arg){return arg.getDisplayLabel();});
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

  window.addEventListener('load', loadTypes , false);

</script>