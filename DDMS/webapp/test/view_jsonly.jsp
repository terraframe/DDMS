<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <jsp:include page="/WEB-INF/templates/yuiIncludes.jsp" />
    <title>
      View MosquitoCollection
    </title>
    <script type="text/javascript" src="js/RunwaySDK.js"></script>
  </head>
  <body class=" yui-skin-sam">
    <jsp:include page="viewComponent.jsp" />
    <br>
    <br>
    Import Status: <span id="imported">Loading Types</span><br />
    <div id="rows">
    
    </div>
    
    <div id="basic"></div> 
    
    	<div id="buttons"> 
    	
    	<span id="loadRows" class="yui-button yui-push-button"> 
	        <span class="first-child"> 
	            <button type="button" id="create" disabled="disabled" >Get MorphologicalSpecieGroups</button> 
	        </span> 
	    </span> 
	    
     <span id="addrow" class="yui-button yui-push-button"> 
	        <span class="first-child"> 
	            <button type="button">Add one row</button> 
	        </span> 
	    </span> 
	    
	    <span id="deleterow" class="yui-button yui-push-button"> 
	        <span class="first-child"> 
	            <button type="button">Delete top row</button> 
	        </span> 
	    </span> 
	    
	  </div>  
    <script type="text/javascript">
    
    
     function createDataTable() {
        var data = {specie_name:"",ident_method:"",qty:""};
        //load the data
        var species = Mojo.$.mdss.entomology.Specie.values().map(function(arg){return arg.getDisplayLabel();});
        var ident_methods = Mojo.$.mdss.entomology.IdentificationMethod.values().map(function(arg){return arg.getDisplayLabel();});
        var rowObjs = [];
        var myColumnDefs = [
            {key:"row",label:"Row",resizeable:true,sortable:true},
            {key:"specie_name",label:"Row",resizeable:true,editor: new YAHOO.widget.DropdownCellEditor({dropdownOptions:species})},
            {key:"ident_method",label:"Row",resizeable:true,editor: new YAHOO.widget.RadioCellEditor({radioOptions:ident_methods,disableBtns:true})},
            {key:"qty",label:"Row",resizeable:true,editor: new YAHOO.widget.TextboxCellEditor({validator:YAHOO.widget.DataTable.validateNumber})}
        ];

        this.myDataSource = new YAHOO.util.DataSource([]);
        this.myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        this.myDataSource.responseSchema = {
            fields: ["specie_name","ident_method","qty"]
        };
        
        this.myDataTable = new YAHOO.widget.DataTable("basic",
                myColumnDefs, this.myDataSource, {});
                
        var i=1,
            bReverseSorted = false;

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
        this.myDataTable.subscribe("cellClickEvent", this.myDataTable.onEventShowCellEditor);
        
        
		
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
            this.myDataTable.addRow(record);
        },this,true);
        
        // Delete one row from the top
        var btnDeleteRow = new YAHOO.widget.Button("deleterow");
        btnDeleteRow.on("click", function() {
            if(this.myDataTable.getRecordSet().getLength() > 0) {
                this.myDataTable.deleteRow(0);
            }
        },this,true);

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
         for (var row in rows)
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

  window.addEventListener('load', loadTypes , false);

</script>
    
  </body>
</html>
