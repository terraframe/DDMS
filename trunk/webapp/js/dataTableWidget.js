//This file contains functions for setting up a data table.

// setting up mojo  namespace
var MojoGrid = YAHOO.namespace('MojoGrid');


MojoGrid.validateBool = function(inputValue, currentValue, editorInstance) {
	return "test";
};

MojoGrid.createDataTable = function(table_data) {
	// locals to be returned
	var myDataSource, myDataTable;

	// set the fields
	if (typeof table_data.fields === 'undefined') {
		table_data.fields = table_data.columnDefs.map( function(c) {
			return c.key;
		}).filter( function(c) {
			return (c !== 'delete');
		});
	}

	if (typeof table_data.saveFunction === 'undefined') {
		table_data.saveFunction = "saveAll";
	}

	if (typeof table_data.addButton === 'undefined') {
		table_data.addButton = "allreadyThere";
	}

	if (typeof table_data.copy_from_above === 'undefined') {
		table_data.copy_from_above = [];
	}

	table_data.dirty = false;

	if(table_data.compressHeaders)
	{
	  table_data.columnDefs.map(function(col){
	    var maxLength = 0;
	    var words = col.label.split(' ');
	    words.map(function(w){if(w.length>maxLength)maxLength=w.length;})
	    var buff = '';
	    var lineLength = 0;
	    words.map(function(w){
	      if(lineLength + w.length > maxLength){
	       buff += '<br>';
	       lineLength = 0;
	      }
	       buff += w + ' ';
	       lineLength += w.length;
	      });
	    col.label = buff;
	  });
	}

	// load the data
	myDataSource = new YAHOO.util.DataSource(table_data.rows);
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource.responseSchema = {
		fields : table_data.fields
	};

	// FIXME: figure out how to set a max width
	myDataTable = new YAHOO.widget.ScrollingDataTable(table_data.div_id, table_data.columnDefs, myDataSource, {
		width : table_data.width
	});

	table_data.myDataTable = myDataTable;

	function getLabelFromId(feild, id) {
		var i = window[feild + "Ids"].indexOf(id);
		return window[feild + "Labels"][i];
	}



	// the data comes from the server as ids, we need to set the labels
	myDataTable.getRecordSet().getRecords().map( function(record) {
		table_data.columnDefs.map( function(feild) {
			if (feild.save_as_id) {
				var label = getLabelFromId(feild.key, record.getData(feild.key));
				record.setData(feild.key, label);
			}
			if (feild.title) {
				myDataTable.getThEl(myDataTable.getColumn(feild.key)).title = feild.title;
			}
			//now we set the labels for bools
				var editor = myDataTable.getColumn(feild.key).editor;
				if (editor && editor instanceof YAHOO.widget.RadioCellEditor) {
					editor.radioOptions.map( function(radioOpt) {
						if (record.getData(feild.key) === radioOpt.value) {
							myDataTable.updateCell(record, feild.key, radioOpt.label);
						}
					});
				}
				if (editor && editor instanceof YAHOO.widget.DateCellEditor) {
					var date = MDSS.Calendar.parseDate(record.getData(feild.key));
					myDataTable.updateCell(record, feild.key, date);
				}
			});
		if (table_data.after_row_load) {
			table_data.after_row_load(record);
		}
	});
	myDataTable.render();

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
	var editorKeyEvent = function(obj) {
		// 9 = tab, 13 = enter
		var e = obj.event;

		function findNext(cell) {
			var newCell = null;
			if (e.shiftKey) {
				newCell = myDataTable.getPreviousTdEl(cell);
			} else {
				newCell = myDataTable.getNextTdEl(cell);
			}
			while (newCell !== null && (myDataTable.getColumn(newCell).editor === null || myDataTable.getColumn(newCell).hidden === true)) {
				if (e.shiftKey) {
					newCell = myDataTable.getPreviousTdEl(newCell);
				} else {
					newCell = myDataTable.getNextTdEl(newCell);
				}
			}
			return (newCell);
		}

		if (e.keyCode === 9) {

			var cell = myDataTable.getCellEditor().getTdEl();
			var nextCell = findNext(cell);
			var nextRow = null;

			// No editable cell found on this row, go to the next row and search for
			// editable cell
			if (nextCell === null) {
				if (e.shiftKey) {
					nextRow = myDataTable.getPreviousTrEl(cell);
				} else {
					nextRow = myDataTable.getNextTrEl(cell);
				}

				// No next cell, make a new row and open the editor for that one
				if (nextRow === null) {
					if (table_data.addButton !== false) {
						addRow();
						nextRow = myDataTable.getLastTrEl();
					} else {
						// wrap around
						// nextRow = myDataTable.getFirstTrEl();
					}
				}
				if (e.shiftKey) {
					nextCell = findNext(myDataTable.getLastTdEl(nextRow));
				} else {
					nextCell = findNext(myDataTable.getFirstTdEl(nextRow));
				}
			}
			// YAHOO.util.Event.stopEvent(e);
			// e.returnValue = false;
			// e.preventDefault();
			myDataTable.saveCellEditor();
			if (nextCell) {
				myDataTable.showCellEditor(nextCell);
			}
		}

	};
	myDataTable.subscribe("editorKeydownEvent", editorKeyEvent);

	// Save edits back to the original data array
	var saveSomeData = function(oArgs) {
		var record = oArgs.editor.getRecord();
		var editor = oArgs.editor;
		var index = myDataTable.getRecordIndex(record);
		var save_now = "";
		if (editor instanceof YAHOO.widget.DropdownCellEditor) {
			var i = window[oArgs.editor.getColumn().key + "Labels"].indexOf(oArgs.newData);
			var id = window[oArgs.editor.getColumn().key + "Ids"][i];
			save_now = 'table_data.rows[' + index + '].' + oArgs.editor.getColumn().key + ' = "' + id + '"';

		} else {
		  save_now = 'table_data.rows[' + index + '].' + oArgs.editor.getColumn().key + ' = "' + oArgs.newData + '"';
			if (editor instanceof YAHOO.widget.RadioCellEditor) {
				//When an item is selected YUI displays the value instead of the label, so we fix this.
				editor.radioOptions.map( function(radioOpt) {
					if (oArgs.newData == radioOpt.value) {
						myDataTable.updateCell(record, editor.getColumn(), radioOpt.label);
					}
				});
			}

		}
		eval(save_now);

		table_data.dirty = true;
		btnSaveRows.set("disabled", false);
		if (table_data.after_row_edit) {
			table_data.after_row_edit(record);
		}

		if (oArgs.editor.getColumn().sum && oArgs.newData && table_data.rows.length > 1) {
			var lastIndex = table_data.rows.length - 1;
			var lastRecord = myDataTable.getRecord(lastIndex);
			var lastTd = myDataTable.getTdEl( {
				record : lastRecord,
				column : editor.getColumn()
			});

			var oldData = parseInt(oArgs.oldData,10);
			oldData = oldData || 0;

			var oldTotal = parseInt(lastRecord.getData(editor.getColumn().key),10);
      oldTotal = oldTotal || 0;

			var newTotal = oldTotal + parseInt(oArgs.newData,10) - oldData;

			if (index !== lastIndex && newTotal) {
				myDataTable.updateCell(lastRecord, editor.getColumn(), newTotal);
				table_data.rows[lastIndex][editor.getColumn().key] = newTotal;
			}

			var sum = 0;

			myDataTable.getRecordSet().getRecords().map( function(row) {
				var x = parseInt(row.getData(editor.getColumn().key),10);
				if (x && myDataTable.getRecordIndex(row) !== lastIndex){
					sum += x;
				}
			});

			if (parseInt(lastRecord.getData(editor.getColumn().key),10) != sum) {
				YAHOO.util.Dom.addClass(lastTd, "dataTableSumError");
			} else {
				YAHOO.util.Dom.removeClass(lastTd, "dataTableSumError");
			}

		}

	};

	MojoGrid.saveHandler = saveSomeData;
	myDataTable.subscribe("editorSaveEvent", saveSomeData);

	myDataTable.subscribe("cellMouseoutEvent", myDataTable.onEventUnhighlightCell);

	var onCellClick = function(oArgs) {
		var target = oArgs.target;
		var column = myDataTable.getColumn(target);
		switch (column.action) {
			case 'delete':
				var record = myDataTable.getRecord(target);
				var row_id = record.getData(table_data.fields[0].key);
				var row_index = myDataTable.getRecordIndex(record);
				if (confirm(MDSS.Localized.Confirm_Delete_Row + (row_index + 1) + '?')) {
					if (typeof row_id !== 'undefined' && row_id.length > 1) {
						var request = new MDSS.Request( {
							dataTable : myDataTable,
							row_index : row_index,
							onSuccess : function(deletedRow) {
								table_data.rows.splice(request.row_index, 1);
								request.dataTable.deleteRow(target);
							}
						});
						Mojo.deleteEntity(request, row_id);
					} else {
						myDataTable.deleteRow(target);
						table_data.rows.splice(row_index, 1);
					}

				}
				break;
			default:
				myDataTable.onEventShowCellEditor(oArgs);
				break;
		}
	};

	myDataTable.subscribe("cellClickEvent", onCellClick);

	if (YAHOO.util.Dom.get('buttons') === null) {
		var tableDiv = YAHOO.util.Dom.get(table_data.div_id);
		var buttons = document.createElement('span');
		buttons.id = table_data.div_id + 'Buttons';
		YAHOO.util.Dom.addClass(buttons, 'noprint');
		YAHOO.util.Dom.addClass(buttons, 'dataTableButtons');
		buttons.innerHTML = '';

		if (table_data.addButton !== false) {
			buttons.innerHTML += '<button type="button" id="' + table_data.div_id + 'Addrow">' + MDSS.localize('New_Row') + '</button>';
		}

		buttons.innerHTML += '<button type="button" id="' + table_data.div_id + 'Saverows">' + MDSS.localize('Save_Rows_To_DB') + '</button>';

		if (table_data.excelButtons !== false) {
			buttons.innerHTML += '<form method="get" action="excelimport" style="display: inline;"><span class="yui-button yui-push-button"> <span class="first-child"><button type="submit">' + MDSS.localize('Excel_Import_Header') + '</button></span></span></form>';
			buttons.innerHTML += '<form method="post" action="excelexport" style="display: inline;"><input type="hidden" name="type" value="' + table_data.div_id + '" /><span class="yui-button yui-push-button"> <span class="first-child"><button type="submit">' + MDSS.localize('Excel_Export_Header') + '</button></span></span></form>';
		}

		YAHOO.util.Dom.insertAfter(buttons, tableDiv);
	}

	if (YAHOO.util.Dom.get(table_data.div_id + 'Saverows')) {
		// set up the button that saves the rows to the db
		var btnSaveRows = new YAHOO.widget.Button(table_data.div_id + "Saverows");
		btnSaveRows.on("click", function(){
			// save any open editors before we send the ajax request
				myDataTable.saveCellEditor();
				var request = new MDSS.Request( {
					// success handler for saved rows
					dataTable : myDataTable,
					table_data : table_data,
					btnSaveRows : btnSaveRows,
					onSuccess : function(savedRows) {
						if (!table_data.dont_update_on_save) {
							var id_key = table_data.fields[0].key;

							for( var i = 0; i < savedRows.length; i++) {
								var id = savedRows[i]["get" + id_key]();
								this.dataTable.getRecord(i).setData(id_key, id);
								this.table_data.rows[i][id_key] = id;
							}

							table_data.dirty = false;
							btnSaveRows.set("disabled", true);
							this.dataTable.render();

							if (table_data.after_save) {
								table_data.after_save();
							}
						}
					}
				});

				function setValue(){
					var setter_exists = Mojo.util.isFunction(view['set' + attrib.key]);
					if (setter_exists) {
						if (val != null) {
							if (view.attributeMap[attribName].dtoType == "AttributeDateDTO") {
								view['set' + attrib.key](MDSS.Calendar.parseDate(val));
							} else {
								view['set' + attrib.key](val);
							}
						} else {
							//FIXME: this is a workaround for a bug in mojo
							view['set' + attrib.key]("");
						}
				  }
					else{
						 // enum setters start with "add" instead of "set"
						var setter_exists = Mojo.util.isFunction(view['add' + attrib.key]);
						if (setter_exists) {
							view['add' + attrib.key](val);
						}
					}
				}

				var view_arr = new Array();

				for ( var r = 0; r < table_data.rows.length; r++) {
					var row = table_data.rows[r];
					var view_contructor = Mojo.util.getType(table_data.data_type);
					var view = new view_contructor();

					for ( var i = 0; i < table_data.fields.length; i++) {
						var attrib = table_data.fields[i];
						var val = row[attrib.key];
						var attribName = attrib.key.substring(0, 1).toLowerCase() + attrib.key.substring(1);
					  setValue();
					}
					view_arr.push(view);
				}
				eval(table_data.data_type + "." + table_data.saveFunction + '(request,view_arr)');
				btnSaveRows.set("disabled", true);
			});
	}


	// Add one row to the bottom
	var addRow = function() {
		// Clear sort when necessary
		if (bReverseSorted) {
			myDataTable.set("sortedBy", null);
		}

		// FIREFOX ONLY
		var new_data_row = eval(uneval(table_data.defaults));
		var new_label_row = eval(uneval(table_data.defaults));
		if (table_data.rows.length > 0) {
			var last_row_index = table_data.rows.length - 1;
			table_data.copy_from_above.map( function(feild) {
				new_data_row[feild] = table_data.rows[last_row_index][feild];
				var label = myDataTable.getRecord(last_row_index).getData(feild);
				new_label_row[feild] = label;

			});
		}
		table_data.rows.push(new_data_row);
		myDataTable.addRow(new_label_row);
		table_data.dirty = true;
		btnSaveRows.set("disabled", false);
	}

	if (YAHOO.util.Dom.get(table_data.div_id + 'Addrow')) {
		var btnAddRow = new YAHOO.widget.Button(table_data.div_id + "Addrow");
		btnAddRow.on("click", addRow);

	}

	return {
		oDS : myDataSource,
		oDT : myDataTable
	};
};