//This file contains functions for setting up a data table.  

// setting up mojo  namespace
var MojoGrid = YAHOO.namespace('MojoGrid');

(function () {
	// Enclosing everything within this anonymous function
	// makes everything declared inside invisible outside
	// so I am free to declare any handy shortcuts
	
	
	// Array Remove - By John Resig (MIT Licensed)
	Array.remove = function(array, from, to) {
	  var rest = array.slice((to || from) + 1 || array.length);
	  array.length = from < 0 ? array.length + from : from;
	  return array.push.apply(array, rest);
	};

	MojoGrid.createDataTable = function(table_data) {
	// locals to be returned
    var myDataSource, myDataTable;

	// load the data
	myDataSource = new YAHOO.util.DataSource(table_data.rows);
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource.responseSchema = {
		fields :table_data.fields
	};

	myDataTable = new YAHOO.widget.DataTable(table_data.div_id,
			table_data.columnDefs, myDataSource, {width:"30em", height:"10em"});

	var i = (table_data.rows.length + 1);
	var bReverseSorted = false;

	// Track when Column is reverse-sorted, since new data will come in out of
	// order
	var trackReverseSorts = function(oArg) {
		bReverseSorted = (oArg.dir === YAHOO.widget.DataTable.CLASS_DESC);
	};
	myDataTable.subscribe("columnSortEvent", trackReverseSorts);

	// Set up editing flow
	var highlightEditableCell = function(oArgs) {
		var elCell = oArgs.target;
		if (YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {
			this.highlightCell(elCell);
		}
	};
	myDataTable.subscribe("cellMouseoverEvent", highlightEditableCell);
	
	// Save edits back to the original data array
	var saveSomeData = function(oArgs) {
		  var record = oArgs.editor.getRecord();
		  var save_now = 'table_data.rows[' + record._nCount + '].' + oArgs.editor.getColumn().key + ' = "' + oArgs.newData + '"';
		  // alert(save_now);
	      eval(save_now);	
	};
	myDataTable.subscribe("editorSaveEvent", saveSomeData);
	
	myDataTable.subscribe("cellMouseoutEvent",
			myDataTable.onEventUnhighlightCell);

	var onCellClick = function(oArgs) {
		var target = oArgs.target;
		column = myDataTable.getColumn(target); 
		record = myDataTable.getRecord(target);
		row_id = record.getData(table_data.fields[0].key);

		switch (column.action) {
		case 'delete':
			if (confirm('Are you sure you want to delete row ' + (record._nCount+1) + '?')) {
				if(row_id.length > 1){
				var request = new Mojo.ClientRequest( {
					dataTable :myDataTable,
					row_index :record._nCount,
					onSuccess : function(deletedRow) {
					Array.remove(table_data.rows,request.row_index);
					request.dataTable.deleteRow(target);					
					//alert('row deleted on server');

				},
				onFailure : function(e) {
					alert(e.getLocalizedMessage());
				}
				});
				Mojo.deleteEntity(request, row_id);
			}
			else
			{
				myDataTable.deleteRow(target);
				Array.remove(table_data.rows,request.row_index);
			}
				
				}
			break;
		default:
			this.onEventShowCellEditor(oArgs);
			break;
		}
	};

	myDataTable.subscribe("cellClickEvent", onCellClick);
    
	
	// set up the button that saves the rows to the db
	var btnSaveRows = new YAHOO.widget.Button("saverows"); 
	btnSaveRows.on("click", function() {
		var request = new Mojo.ClientRequest( {
			// success handler for saved rows
			dataTable : myDataTable,
			table_data : this.table_data,
			onSuccess : function(savedRows) {
				alert("Saved " + savedRows.length + " Rows!");
				var i = 0;
				id_key = table_data.fields[0].key
				for each(row in savedRows)
				{
					record = this.dataTable.getRecord(i);
					id = savedRows[i].getGroupId();
					str = "record.setData('" + id_key + "','" + id + "')";
					eval(str);
			        //table_data[i].GroupId = id;				
					i = i + 1;
				}
				this.dataTable.render();
			},

			// alert the exception message
			onFailure : function(e) {
				if(e instanceof Mojo.dto.ProblemExceptionDTO )
				{
					 for each (problem in e.getProblems())
					{
					   alert(problem.getLocalizedMessage());
					}
				}
				else{
					alert(e.getLocalizedMessage());
				}
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
			    // alert(str);  
	    		eval(str);
	    	}
			v_arr.push(v);
	    }	    
	    str = table_data.data_type + '.saveAll(request,v_arr)';
    	eval(str);

	});
	
// // Add passed in rows
// this.addRow = function(row) {
// // Clear sort when necessary
// if (bReverseSorted) {
// myDataTable.set("sortedBy", null);
// }
//
// var record = YAHOO.widget.DataTable._cloneObject(table_data.defaults);
// //record.row = i++;
// record.qty = row.getQuanity();
// record.specie_name = row.getSpecie()[0].getDisplayLabel();
// record.ident_method = row.getIdentificationMethod()[0]
// .getDisplayLabel();
// myDataTable.addRow(record);
// };

	// Add one row to the bottom
	var btnAddRow = new YAHOO.widget.Button("addrow");
	btnAddRow.on("click", function() {
		// Clear sort when necessary
			if (bReverseSorted) {
				 myDataTable.set("sortedBy", null);
			}
            // clone the object
			// FIREFOX ONLY
			new_defs = eval(uneval(table_data.defaults));
			// var record = YAHOO.widget.DataTable._cloneObject(new_defs);
			// record = new YAHOO.widget.Record(table_data.defaults);
			// record.row = i++;
			if (table_data.rows.length > 0)
			{
				last_row_index = table_data.rows.length - 1
				for each (feild in table_data.copy_from_above)
		        {	    	
			    	str = 'new_defs.' + feild + ' = table_data.rows[last_row_index].' + feild ;
			    	eval(str);
			    }	    
	        }		
			table_data.rows.push(new_defs);
			myDataTable.addRow(new_defs);
		}, this, true);

	// stuff to turn cols on and off
	// Shows dialog, creating one when necessary
	var newCols = true;
	var showDlg = function(e) {
		YAHOO.util.Event.stopEvent(e);

		if (newCols) {
			// Populate Dialog
			// Using a template to create elements for the SimpleDialog
			var allColumns = myDataTable.getColumnSet().keys;
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
	YAHOO.util.Event.addListener("dt-options-link", "click", showDlg, this, true);
	
	return {
        oDS: myDataSource,
        oDT: myDataTable
    };
	};
})();
