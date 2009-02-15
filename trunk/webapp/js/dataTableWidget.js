//This file contians functions for setting up a data table.  
  
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
