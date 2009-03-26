//This file contains functions for setting up a data table.  

// setting up mojo  namespace
var MojoGrid = YAHOO.namespace('MojoGrid');

(function () {
	MojoGrid.createDataTable = function(table_data) {
	// locals to be returned
    var myDataSource, myDataTable;
    
    // set the fields
    if(table_data.fields == null)
    {
    	table_data.fields = table_data.columnDefs.map(function(c){return c.key}).filter(function(c){return c != 'delete'});
    }
    
    table_data.dirty = false;
    
	// load the data
	myDataSource = new YAHOO.util.DataSource(table_data.rows);
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource.responseSchema = {
			fields :  table_data.fields
	};
    // FIXME: figure out how to set a max width
	myDataTable = new YAHOO.widget.ScrollingDataTable(table_data.div_id,
			table_data.columnDefs, myDataSource, {width:table_data.width});
	

	function getLabelFromId(feild,id)
	{
		  str = feild+"Ids.indexOf(id)";
		  i = eval(str);
		  str = feild+"Labels["+i+"]" ;
		  return(eval(str));
	}
	
	// the data comes from the server as ids, we need to set the labels
	for each(record in myDataTable.getRecordSet().getRecords())
	{
		for each(feild in table_data.columnDefs)
		{
			if (feild.save_as_id)
			{
			  label = getLabelFromId(feild.key,record.getData(feild.key));
			  // alert(label);
			  record.setData(feild.key, label);
			}
		}
	}
	myDataTable.render();

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
	
	/***************************************************************************
	 * handleEditorKeyEvent ( obj ) Handle a keypress when the Cell Editor is
	 * open Enter will close the editor and move down Tab will close the editor
	 * and move right. Use the handleTableKeyEvent() to handle the moving Open a
	 * new cell editor on the newly focused cell
	 */
    var editorKeyEvent = function ( obj ) {
        // 9 = tab, 13 = enter
        var e   = obj.event;
        if ( e.keyCode == 9 ) {
            var cell        = myDataTable.getCellEditor().getTdEl();
            var nextCell    = myDataTable.getNextTdEl( cell );
            myDataTable.saveCellEditor();
            if ( nextCell &&  myDataTable.getColumn(nextCell).editor) {
            	myDataTable.showCellEditor( nextCell );
                e.returnValue   = false;
                e.preventDefault();
                return false;
            }
            else {
                // No next cell, go to the next row an search for editable cells
            	var nextRow = myDataTable.getNextTrEl(cell);
            	if(nextRow)
            	{
	                var nextCell  = myDataTable.getFirstTdEl(nextRow);
	                while(nextCell && ! myDataTable.getColumn(nextCell).editor)
	                {
	                	var nextCell = myDataTable.getNextTdEl( nextCell );
	                }
	                if ( nextCell ) {
	                	myDataTable.showCellEditor( nextCell );
	                    e.returnValue   = false;
	                    e.preventDefault();
	                    return false;
	                }
            	}
            }
           
        }
        if ( e.keyCode == 13 ) {

            var cell        = myDataTable.getCellEditor().getTdEl();  
            var nextCell    = myDataTable.getBelowTdEl( cell );
            myDataTable.saveCellEditor();
            if ( nextCell ) {
            	myDataTable.showCellEditor( nextCell );
                e.returnValue   = false;
                e.preventDefault();
                e=null;
                return false;
            }
            else {
                // No next cell, make a new row and open the editor for that one
            	addRow();
            }
            // BUG: If pressing Enter, editor gets hidden right away due to YUI
			// default event
            // putting e.preventDefault() and return false here makes no
			// difference
        }
    };
    myDataTable.subscribe("editorKeydownEvent", editorKeyEvent);
	
	// Save edits back to the original data array
	var saveSomeData = function(oArgs) {
		  var record = oArgs.editor.getRecord();
		  var editor = oArgs.editor;
		  index = myDataTable.getRecordIndex(record);
		  if(editor instanceof YAHOO.widget.DropdownCellEditor )
		  {
			  str = oArgs.editor.getColumn().key+"Labels.indexOf(oArgs.newData)";
			  i = eval(str);
			  str = oArgs.editor.getColumn().key+"Ids["+i+"]" ;
			  id = eval(str);
			  var save_now = 'table_data.rows[' + index + '].' + oArgs.editor.getColumn().key + ' = "' + id + '"';
		  }
		  else
		  {
			  var save_now = 'table_data.rows[' + index + '].' + oArgs.editor.getColumn().key + ' = "' + oArgs.newData + '"';
		  }
	      eval(save_now);	
	      table_data.dirty = true;
	      btnSaveRows.set("disabled", false);
	      if(table_data.after_row_edit)
	      {
	    	  table_data.after_row_edit(record);
	    	  myDataTable.render();
	      }
	};
	myDataTable.subscribe("editorSaveEvent", saveSomeData);
	
	myDataTable.subscribe("cellMouseoutEvent",
			myDataTable.onEventUnhighlightCell);

	var onCellClick = function(oArgs) {
		var target = oArgs.target;
		column = myDataTable.getColumn(target); 
		switch (column.action) {
		case 'delete':		
			record = myDataTable.getRecord(target);
			row_id = record.getData(table_data.fields[0].key);
			row_index = myDataTable.getRecordIndex(record);
			if (confirm('Are you sure you want to delete row ' + (row_index+1) + '?')) {
				if(typeof row_id !== 'undefined' && row_id.length > 1){
				var request = new Mojo.ClientRequest( {
					dataTable :myDataTable,
					row_index :row_index,
					onSuccess : function(deletedRow) {
					table_data.rows.splice(request.row_index,1);
					request.dataTable.deleteRow(target);					
					// alert('row deleted on server');
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
				table_data.rows.splice(row_index,1);
			}
				
			}
			break;
		default:
			myDataTable.onEventShowCellEditor(oArgs);
			break;
		}
	};

	myDataTable.subscribe("cellClickEvent", onCellClick);
    
	if(YAHOO.util.Dom.get(table_data.div_id+'Saverows'))
	{
	// set up the button that saves the rows to the db
	var btnSaveRows = new YAHOO.widget.Button(table_data.div_id+"Saverows"); 
	btnSaveRows.on("click", function() {
		var request = new Mojo.ClientRequest( {
			// success handler for saved rows
			dataTable : myDataTable,
			table_data : table_data,
			btnSaveRows :btnSaveRows,
			onSuccess : function(savedRows) {
				// alert("Saved " + savedRows.length + " Rows!");
			    if(! table_data.dont_update_on_save)
			    {
					var i = 0;
					id_key = table_data.fields[0].key;
					for each(row in savedRows)
					{
						record = this.dataTable.getRecord(i);
						id = eval("savedRows[i].get" + id_key +"()");
						str = "record.setData('" + id_key + "','" + id + "')";
						eval(str);
				        eval("this.table_data.rows[i]."+id_key+"= id");				
						i = i + 1;
					}
					table_data.dirty = false;
					btnSaveRows.set("disabled", true);
					this.dataTable.render();
					if(table_data.after_save)
				    {
				    	table_data.after_save();
				    }
		        }
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
	    	if(table_data.collection_setter)
	    	{
	    		str = "v."+table_data.collection_setter;
	    		eval(str);
	    	}
	    	for each (attrib in table_data.fields)
	    	{
	    		setter_exists = eval("typeof (v.set"+attrib.key+") == 'function'")
	    		if(setter_exists)
	    		{
	    			val = eval('row.'+ attrib.key);
	    			if(typeof val !== 'undefined')
	    			{
	    				eval_str = 'v.set'+attrib.key+'(val)';
		    			// alert (eval_str);
		    			eval(eval_str);
	    			}
	    		
	    		}
	    		else
	    		{
	    			setter_exists = eval("typeof (v.add"+attrib.key+") == 'function'")
		    		if(setter_exists)
		    		{
		    			eval_str = 'v.add'+attrib.key+'(row.'+attrib.key+')';
		    			// alert (eval_str);
		    			eval(eval_str);
		    		}
	    		}
	    	}
			v_arr.push(v);
	    }	    
	    str = table_data.data_type + '.saveAll(request,v_arr)';
	    // alert(v_arr);
    	eval(str);

	});
	btnSaveRows.set("disabled", true);
	}
	
	
	// function Add one row to the bottom
	var addRow = function() {
		// Clear sort when necessary
		if (bReverseSorted) {
			 myDataTable.set("sortedBy", null);
		}
        // clone the object
		// FIREFOX ONLY
		new_data_row = eval(uneval(table_data.defaults));
		new_label_row = eval(uneval(table_data.defaults));
		// var record = YAHOO.widget.DataTable._cloneObject(new_defs);
		// record = new YAHOO.widget.Record(table_data.defaults);
		// record.row = i++;
		if (table_data.rows.length > 0)
		{
			last_row_index = table_data.rows.length - 1;
			for each(feild in table_data.copy_from_above)
			{
			    	str = 'new_data_row.' + feild + ' = table_data.rows[last_row_index].' + feild ;
			    	eval(str);
			    	label = myDataTable.getRecord(last_row_index).getData(feild);
			    	str = 'new_label_row.' + feild + " = '"+ label + "'";
			    	eval(str); 
			}
        }		
		table_data.rows.push(new_data_row);
		myDataTable.addRow(new_label_row);
		table_data.dirty = true;
		btnSaveRows.set("disabled", false);
	}
	
	if(YAHOO.util.Dom.get(table_data.div_id+'Addrow')){
		var btnAddRow = new YAHOO.widget.Button(table_data.div_id+"Addrow");
		btnAddRow.on("click", addRow);
		
	}
	
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
	table_data.myDataTable = myDataTable; 
	return {
        oDS: myDataSource,
        oDT: myDataTable
    };
	};
})();
