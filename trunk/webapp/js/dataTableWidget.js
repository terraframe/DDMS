//This file contains functions for setting up a data table.

// setting up mojo  namespace
var MojoGrid = YAHOO.namespace('MojoGrid');

(function () {

	MojoGrid.validateBool = function(inputValue, currentValue, editorInstance){
		return "test";
	}

	MojoGrid.createDataTable = function(table_data) {
	// locals to be returned
    var myDataSource, myDataTable;

    // set the fields
    if(table_data.fields == null)
    {
    	table_data.fields = table_data.columnDefs.map(function(c){return c.key}).filter(function(c){return c != 'delete'});
    }

    if(typeof table_data.saveFunction === 'undefined'  )
    {
    	table_data.saveFunction = "saveAll";
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
			if (feild.title)
			{
			  myDataTable.getThEl(myDataTable.getColumn(feild.key)).title = feild.title;
			}
			//now we set the labels for bools
			editor = myDataTable.getColumn(feild.key).editor
			 if(editor && editor instanceof YAHOO.widget.RadioCellEditor )
			  {
			  	  for each(radioOpt in editor.radioOptions)
				  if(record.getData(feild.key)== radioOpt.value)
				  {
					  record.setData(feild.key, radioOpt.label);
				  }
				  myDataTable.render();
			  }
		}
	    if(table_data.after_row_load)
	    {
	       table_data.after_row_load(record,myDataTable);
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
            if ( nextCell &&  myDataTable.getColumn(nextCell).editor)
            {
            	myDataTable.showCellEditor( nextCell );
                e.returnValue   = false;
                e.preventDefault();
                return false;
            }
             // No next cell, go to the next row and search for editable cell
            else
            {
            	var nextRow = myDataTable.getNextTrEl(cell);
            	// No next cell, make a new row and open the editor for that one
            	if(! nextRow)
                {
            		addRow();
            		var nextRow = myDataTable.getNextTrEl(cell);
                }
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

        // not sure what we want to do this for enter
        if ( e.keyCode == 13 ) {
        }
    };
    myDataTable.subscribe("editorKeydownEvent", editorKeyEvent);

	// Save edits back to the original data array
	var saveSomeData = function(oArgs) {
		  var record = oArgs.editor.getRecord();
		  var editor = oArgs.editor;
		  var index = myDataTable.getRecordIndex(record);
		  if(editor instanceof YAHOO.widget.DropdownCellEditor )
		  {
			  //look get the index for this label
			  str = oArgs.editor.getColumn().key+"Labels.indexOf(oArgs.newData)";
			  i = eval(str);
			  //use the index to find the ID from the IDs array
			  str = oArgs.editor.getColumn().key+"Ids["+i+"]" ;
			  id = eval(str);
			  var save_now = 'table_data.rows[' + index + '].' + oArgs.editor.getColumn().key + ' = "' + id + '"';
		  }
		  else
		  {
			  var save_now = 'table_data.rows[' + index + '].' + oArgs.editor.getColumn().key + ' = "' + oArgs.newData + '"';
			  if(editor instanceof YAHOO.widget.RadioCellEditor )
			  {
			  	//When an item is selected YUI displays the value instead of the label, so we fix this.
			  	for each(radioOpt in editor.radioOptions)
				  if(oArgs.newData == radioOpt.value)
				  {

					  myDataTable.updateCell(record,editor.getColumn(), radioOpt.label);
					  //record.setData(editor.getColumn().key, radioOpt.label);
				  }

			  }

			 /// if(editor instanceof YAHOO.widget.DateCellEditor )
			 // {
				 //oArgs.newData = MojoCal.getLocalizedString(oArgs.newData);
				 //save_now = 'table_data.rows[' + index + '].' + oArgs.editor.getColumn().key + ' = "' + oArgs.newData + '"';
			 // }

		  }
	      eval(save_now);

	      table_data.dirty = true;
	      btnSaveRows.set("disabled", false);
	      if(table_data.after_row_edit)
	      {
	    	  table_data.after_row_edit(record);
	    	  myDataTable.render();
	      }


	      if(oArgs.editor.getColumn().sum && oArgs.newData)
		  {
			  var lastIndex  = table_data.rows.length - 1;
	    	  var lastRecord = myDataTable.getRecord(lastIndex);
			  var lastTd = myDataTable.getTdEl({record:lastRecord, column:editor.getColumn()});

			  var oldData = parseInt(oArgs.oldData);
			  if(!oldData)oldData=0;

			  var oldTotal = parseInt(lastRecord.getData(editor.getColumn().key));
			  if(!oldTotal)oldTotal=0;

			  var newTotal = oldTotal + parseInt(oArgs.newData) - oldData;

			  if(index != lastIndex )
			  {
				  myDataTable.updateCell(lastRecord,editor.getColumn(),newTotal);
				 //lastRecord.setData(editor.getColumn().key, newTotal);
				  var save_now = 'table_data.rows[' + lastIndex + '].' + oArgs.editor.getColumn().key + ' = "' + oArgs.newTotal+ '"';
				  eval(save_now);
			  }

			  var sum = 0;
			  for each(row in myDataTable.getRecordSet().getRecords())
			  {
				  var x = parseInt(row.getData(editor.getColumn().key));
				 // if(x && row != lastRecord)sum += x;
				  if(x && myDataTable.getRecordIndex(row) != lastIndex)sum += x;
			  }
			  if(parseInt(lastRecord.getData(editor.getColumn().key)) != sum)
			  {
				  YAHOO.util.Dom.addClass(lastTd, "dataTableSumError");
			  }
			  else
			  {
				  YAHOO.util.Dom.removeClass(lastTd, "dataTableSumError");
			  }

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
			if (confirm(MDSS.Localized.Confirm_Delete_Row + (row_index+1) + '?')) {
				if(typeof row_id !== 'undefined' && row_id.length > 1){
				var request = new MDSS.Request( {
					dataTable :myDataTable,
					row_index :row_index,
					onSuccess : function(deletedRow) {
					table_data.rows.splice(request.row_index,1);
					request.dataTable.deleteRow(target);
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
		var request = new MDSS.Request( {
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
	    str = table_data.data_type +"." +table_data.saveFunction + '(request,v_arr)';
	    //alert(str);
    	eval(str);

	});
	btnSaveRows.set("disabled", true);
	}


	// Add one row to the bottom
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

	table_data.myDataTable = myDataTable;
	return {
        oDS: myDataSource,
        oDT: myDataTable
    };
	};
})();
