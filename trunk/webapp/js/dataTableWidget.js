//This file contains functions for setting up a data table.  

function createDataTable(table_data) {
	
	// load the data
	this.myDataSource = new YAHOO.util.DataSource(table_data.rows);
	this.myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	this.myDataSource.responseSchema = {
		fields :table_data.fields
	};

	this.myDataTable = new YAHOO.widget.DataTable(table_data.div_id,
			table_data.columnDefs, this.myDataSource, {});

	var i = (table_data.rows.length + 1);
	var bReverseSorted = false;

	// Track when Column is reverse-sorted, since new data will come in out of order
	var trackReverseSorts = function(oArg) {
		bReverseSorted = (oArg.dir === YAHOO.widget.DataTable.CLASS_DESC);
	};
	this.myDataTable.subscribe("columnSortEvent", trackReverseSorts);

	// Set up editing flow
	var highlightEditableCell = function(oArgs) {
		var elCell = oArgs.target;
		if (YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {
			this.highlightCell(elCell);
		}
	};
	this.myDataTable.subscribe("cellMouseoverEvent", highlightEditableCell);
	
	//Save edits back to the original data array
	var saveSomeData = function(oArgs) {
		  var record = oArgs.editor.getRecord();
		  var save_now = 'table_data.rows[' + record._nCount + '].' + oArgs.editor.getColumn().key + ' = "' + oArgs.newData + '"';
		  //alert(save_now);
	      eval(save_now);	
	};
	this.myDataTable.subscribe("editorSaveEvent", saveSomeData);
	
	this.myDataTable.subscribe("cellMouseoutEvent",
			this.myDataTable.onEventUnhighlightCell);

	var onCellClick = function(oArgs) {
		var target = oArgs.target;
		column = this.getColumn(target), record = this.getRecord(target);
		row_id = record.getData('id');

		switch (column.action) {
		case 'delete':
			if (confirm('Are you sure you want to delete row ' + record
					.getData('row') + '?')) {
				var request = new Mojo.ClientRequest( {
					dataTable :this,
					onSuccess : function(deletedMorphologicalSpecieGroup) {
					request.dataTable.deleteRow(target);
					alert('row deleted on server');

				},
				onFailure : function(e) {
					alert(e.getLocalizedMessage());
				}
				});
				Mojo.deleteEntity(request, row_id);
			}
			break;
		default:
			this.onEventShowCellEditor(oArgs);
			break;
		}
	};

	this.myDataTable.subscribe("cellClickEvent", onCellClick);
    
	
	//set up the button that saves the rows to the db
	var btnSaveRows = new YAHOO.widget.Button("saverows"); 
	btnSaveRows.on("click", function() {
		var request = new Mojo.ClientRequest( {
			// success handler for saved rows
			onSuccess : function(newMorphologicalSpecieGroup) {
				alert("Saved The Rows!");
			},

			// alert the exception message
			onFailure : function(e) {
				alert(e.getLocalizedMessage());
			}
		});
	
	    var v_arr = new Array();
	    
	    for each (row in table_data.rows)
        {
	    	var v;
	    	str = 'v = new ' + table_data.data_type + '()';
	    	eval(str);
	        v.setCollectionId(table_data.collection_id);
	    	for each (attrib in table_data.fields)
	    	{
			    str = 'v.set'+attrib.key+'(row.'+attrib.key+')';
	    		eval(str);
	    	}
			v_arr.push(v);
	    }	    
	    str = table_data.data_type + '.saveAll(request,v_arr)';
    	eval(str);

	});
	
	
//	// Add passed in rows
//	this.addRow = function(row) {
//		// Clear sort when necessary
//		if (bReverseSorted) {
//			this.myDataTable.set("sortedBy", null);
//		}
//
//		var record = YAHOO.widget.DataTable._cloneObject(table_data.defaults);
//		//record.row = i++;
//		record.qty = row.getQuanity();
//		record.specie_name = row.getSpecie()[0].getDisplayLabel();
//		record.ident_method = row.getIdentificationMethod()[0]
//				.getDisplayLabel();
//		this.myDataTable.addRow(record);
//	};

	// Add one row to the bottom
	var btnAddRow = new YAHOO.widget.Button("addrow");
	btnAddRow.on("click", function() {
		// Clear sort when necessary
			if (bReverseSorted) {
				this.myDataTable.set("sortedBy", null);
			}

			var record = YAHOO.widget.DataTable._cloneObject(table_data.defaults);
			
			record.row = i++;
			table_data.rows.push(table_data.defaults);
			this.myDataTable.addRow(record);
		}, this, true);

	// stuff to turn cols on and off
	// Shows dialog, creating one when necessary
	var newCols = true;
	var showDlg = function(e) {
		YAHOO.util.Event.stopEvent(e);

		if (newCols) {
			// Populate Dialog
			// Using a template to create elements for the SimpleDialog
			var allColumns = this.myDataTable.getColumnSet().keys;
			var elPicker = YAHOO.util.Dom.get("dt-dlg-picker");
			var elTemplateCol = document.createElement("div");
			YAHOO.util.Dom.addClass(elTemplateCol, "dt-dlg-pickercol");
			var elTemplateKey = elTemplateCol.appendChild(document
					.createElement("span"));
			YAHOO.util.Dom.addClass(elTemplateKey, "dt-dlg-pickerkey");
			var elTemplateBtns = elTemplateCol.appendChild(document
					.createElement("span"));
			YAHOO.util.Dom.addClass(elTemplateBtns, "dt-dlg-pickerbtns");
			var onclickObj = {
				fn :handleButtonClick,
				obj :this,
				scope :false
			};

			// Create one section in the SimpleDialog for each Column
			var elColumn, elKey, elButton, oButtonGrp;
			for ( var i = 0, l = allColumns.length; i < l; i++) {
				var oColumn = allColumns[i];

				// Use the template
				elColumn = elTemplateCol.cloneNode(true);

				// Write the Column key
				elKey = elColumn.firstChild;
				elKey.innerHTML = oColumn.getKey();

				// Create a ButtonGroup
				oButtonGrp = new YAHOO.widget.ButtonGroup( {
					id :"buttongrp" + i,
					name :oColumn.getKey(),
					container :elKey.nextSibling
				});
				oButtonGrp.addButtons( [ {
					label :"Show",
					value :"Show",
					checked :((!oColumn.hidden)),
					onclick :onclickObj
				}, {
					label :"Hide",
					value :"Hide",
					checked :((oColumn.hidden)),
					onclick :onclickObj
				} ]);

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
		if (this.get("value") === "Hide") {
			// Hides a Column
			myDataTable.hideColumn(sKey);
		} else {
			// Shows a Column
			myDataTable.showColumn(sKey);
		}
	};

	// Create the SimpleDialog
	YAHOO.util.Dom.removeClass("dt-dlg", "inprogress");
	var myDlg = new YAHOO.widget.SimpleDialog("dt-dlg", {
		width :"30em",
		visible :false,
		modal :true,
		buttons : [ {
			text :"Close",
			handler :hideDlg
		} ],
		fixedcenter :true,
		constrainToViewport :true
	});
	myDlg.render();

	// Nulls out myDlg to force a new one to be created
	myDataTable.subscribe("columnReorderEvent", function() {
		newCols = true;
		YAHOO.util.Event.purgeElement("dt-dlg-picker", true);
		YAHOO.util.Dom.get("dt-dlg-picker").innerHTML = "";
	}, this, true);

	// Hook up the SimpleDialog to the link
	YAHOO.util.Event.addListener("dt-options-link", "click", showDlg, this,
			true);
	return this;
};
