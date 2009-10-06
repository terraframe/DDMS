//This file contains functions for setting up a data table.

// setting up mojo  namespace
var MojoGrid = YAHOO.namespace('MojoGrid');
MojoGrid.cellLock = false;
MojoGrid.limitTab = false;


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

  // set the save handler
  if(!table_data.saveHandler) {
	table_data.saveHandler = function (request, view_array) {
	  // Get the class which defines the save function
	  var klass = Mojo.Meta.findClass(table_data.data_type.substring(7));
	      
	   // Get the save function
	  var saveMethod = klass[table_data.saveFunction];
	      
	  // Invoke the save method
	  saveMethod(request, view_array);
    }
  }

  // set the default value for disable button
  if(!Mojo.Util.isBoolean(table_data.cleanDisable)) {
	  table_data.cleanDisable = true;
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
      var editor = myDataTable.getColumn(feild.key).editor;

      if (feild.save_as_id) {
        var label = getLabelFromId(feild.key, record.getData(feild.key));
        record.setData(feild.key, label);
      }else{
	      if (editor && editor instanceof YAHOO.widget.DropdownCellEditor){
	        //data comes in as value instead of label, so we fix this.
	        for( var i = 0; i < editor.dropdownOptions.length; i++) {
	          if (record.getData(feild.key) === editor.dropdownOptions[i].value){
	          	record.setData(feild.key, editor.dropdownOptions[i].label);
	            //myDataTable.updateCell(record, editor.getColumn(), editor.dropdownOptions[i].label);
	          }
	        }
	      }
      }
      if (editor && editor instanceof YAHOO.widget.DateCellEditor) {
        var date = MDSS.Calendar.parseDate(record.getData(feild.key));
        myDataTable.updateCell(record, feild.key, date);
      }

      if (feild.title) {
        myDataTable.getThEl(myDataTable.getColumn(feild.key)).title = feild.title;
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

  myDataTable.subscribe("cellMouseoutEvent", myDataTable.onEventUnhighlightCell);


  myDataTable.set("selectionMode","singlecell");


  myDataTable.subscribe("tbodyFocusEvent", function() {
  var selectedCells = this.getSelectedCells();
    if(selectedCells.length === 0) {
      this.selectCell(this.getFirstTdEl());
    }
  });

  myDataTable.subscribe("editorSaveEvent", function(o) {
  this.focusTbodyEl();
  });

  myDataTable.subscribe("editorCancelEvent", function(o) {
  this.focusTbodyEl();
  });

  var onCellClick = function(oArgs) {
    var target = oArgs.target;
    //myDataTable.focusTbodyEl();
    myDataTable.unselectAllCells();
    myDataTable.selectCell(target);
    var column = myDataTable.getColumn(target);

    // don't allow editing if column is hidden
    if(column.hidden)
    {
      return;
    }

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
            Mojo.Facade.deleteEntity(request, row_id);
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


  /***************************************************************************
   * handleEditorKeyEvent ( obj ) Handle a keypress when the Cell Editor is
   * open Enter will close the editor and move down Tab will close the editor
   * and move right. Use the handleTableKeyEvent() to handle the moving Open a
   * new cell editor on the newly focused cell */

  var editorKeyEvent = function(obj) {

    // 9 = tab, 13 = enter
    var e = obj.event;

    if (e.keyCode === 9) {

      e.preventDefault();
      YAHOO.util.Event.stopEvent(e);

      if(MojoGrid.cellLock)
      {
        // Avoids multiple Tabs in rapid succession.
        return;
      }
      else
      {
        MojoGrid.cellLock = true;
      }

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

      try
      {
        //YAHOO.log("Tabbed Key Press on Cell:" + cell.headers, "warn", "Widget");

        var cell = myDataTable.getCellEditor().getTdEl();
        var nextCell = findNext(cell);
        var nextRow = null;

        // No editable cell found on this row, go to the next row and search for
        // editable cell
        if (nextCell === null)
        {
          nextCell = cell;

        }


        myDataTable.saveCellEditor();
        //YAHOO.log("Saved Cell Editor:" + cell.headers, "warn", "Widget");

        if (nextCell) {
          //YAHOO.log("Selecting Cell Editor:" + nextCell.headers, "warn", "Widget");

          myDataTable.unselectAllCells();

          myDataTable.selectCell(nextCell);
          myDataTable.showCellEditor(nextCell);
          myDataTable.selectCell(nextCell);

          //YAHOO.log("Showing Cell Editor:" + nextCell.headers, "warn", "Widget");
        }
      }
      finally
      {
        MojoGrid.cellLock = false;
      }
    }

  };

  myDataTable.subscribe("editorKeydownEvent", editorKeyEvent);


  // Save edits back to the original data array
  var saveSomeData = function(oArgs) {

    var record = oArgs.editor.getRecord();
    var editor = oArgs.editor;
    var index = myDataTable.getRecordIndex(record);

    if (editor instanceof YAHOO.widget.DropdownCellEditor && window[oArgs.editor.getColumn().key + "Labels"])
    {
      	var i = window[oArgs.editor.getColumn().key + "Labels"].indexOf(oArgs.newData);
        var id = window[oArgs.editor.getColumn().key + "Ids"][i];
        table_data.rows[index][oArgs.editor.getColumn().key]  = id;
    }
    else
    {
    	table_data.rows[index][oArgs.editor.getColumn().key] = oArgs.newData ;
      if (editor instanceof YAHOO.widget.DropdownCellEditor) {
        //When an item is selected YUI displays the value instead of the label, so we fix this.
        editor.dropdownOptions.map( function(opt) {
          if (oArgs.newData === opt.value){
            myDataTable.updateCell(record, editor.getColumn(), opt.label);
          }
        });
      }

    }

    table_data.dirty = true;
    btnSaveRows.set("disabled", false);
    if (table_data.after_row_edit) {
      table_data.after_row_edit(record);
    }

    sumColumn(oArgs);

    //myDataTable.unselectCell(editor.getTdEl());
    YAHOO.log("Saved Cell:" + editor._oColumn.label, "warn", "Widget");
  };
  
  
//calculate totals
  var sumColumn = function(oArgs) {

    if (oArgs.editor.getColumn().sum  && table_data.rows.length > 1) {
    	var record = oArgs.editor.getRecord();
      var lastIndex = table_data.rows.length - 1;
      var lastRecord = myDataTable.getRecord(lastIndex);
      var editor = oArgs.editor;
      var index = myDataTable.getRecordIndex(record);
      var lastTd = myDataTable.getTdEl( {
        record : lastRecord,
        column : editor.getColumn()
      });

      //no calculation is done if number is entered manualy
      if(table_data.rows[lastIndex][editor.getColumn().key])
      {
      	return;
      }
      
      //no calculation is done if cell was tabbed past
      if(!(oArgs.newData || oArgs.oldData ))
      {
      	return;
      }
      
      
      var newData = parseInt(oArgs.newData,10);
      newData = newData || 0;
      
      var oldData = parseInt(oArgs.oldData,10);
      oldData = oldData || 0;

      var oldTotal = parseInt(lastRecord.getData(editor.getColumn().key),10);
      oldTotal = oldTotal || 0;

      var newTotal = oldTotal + newData - oldData;

      if (index !== lastIndex) {
        myDataTable.updateCell(lastRecord, editor.getColumn(), newTotal);
        //we do not save autocalculated values
        //table_data.rows[lastIndex][editor.getColumn().key] = newTotal;
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
  
//  // The default handler to use when saving a table
//  var defaultSaveHandler = function (request, view_array) {
//	  // Get the class which defines the save function
//      var klass = Mojo.Meta.findClass(table_data.data_type.substring(7));
//      
//      // Get the save function
//      var saveMethod = klass[table_data.saveFunction];
//      
//      // Invoke the save method
//      saveMethod(request, view_array);      
//  }
  var setValue = function(view, attribute, value){
      var attributeName = attribute.key.substring(0, 1).toLowerCase() + attribute.key.substring(1);

      var setter_exists = Mojo.Util.isFunction(view['set' + attribute.key]);
      
      if (setter_exists) {
        if (value != null) {
          if (view.attributeMap[attributeName] instanceof com.terraframe.mojo.transport.attributes.AttributeDateDTO) {
            view['set' + attribute.key](MDSS.Calendar.parseDate(value));
          }
          else {
            view['set' + attribute.key](value);
          }
        } 
        else {
          //FIXME: this is a workaround for a bug in mojo
          view['set' + attribute.key]("");
        }
      }
      else{
         // enum setters start with "add" instead of "set"
        var setter_exists = Mojo.Util.isFunction(view['add' + attribute.key]);
        if (setter_exists) {
          view['add' + attribute.key](value);
        }
      }
    }  
  
  var createObjectRepresentation = function() {
      var view_arr = new Array();

      for ( var r = 0; r < table_data.rows.length; r++) {
        var row = table_data.rows[r];
        var view_contructor = Mojo.Meta.findClass(table_data.data_type.substring(7));
        var view = new view_contructor();

        for ( var i = 0; i < table_data.fields.length; i++) {
          var attrib = table_data.fields[i];
          var val = row[attrib.key];

          setValue(view, attrib, val);
        }
        view_arr.push(view);
      }
      
      return view_arr;
  }

  var persistData = function() {
	  // save any open editors before we send the ajax request
      myDataTable.saveCellEditor();
      var request = new MDSS.Request( {
        // success handler for saved rows
        dataTable : myDataTable,
        table_data : table_data,
        btnSaveRows : btnSaveRows,
        onSuccess : function(savedRows) {
          if (!table_data.dont_update_on_save) {
        	  
        	// Get the keys of the columns which need to be reloaded
        	if(!table_data.reloadKeys) {
        	  table_data.reloadKeys = [table_data.fields[0].key];
        	}
        	else {
        		table_data.reloadKeys.push(table_data.fields[0].key);
        	}

        	// Refresh the displayed values of the columns specified in 'reloadKeys'
            for( var i = 0; i < savedRows.length; i++) {
            	var record = this.dataTable.getRecord(i);
            	var row = this.table_data.rows[i];

            	for(var j = 0; j < table_data.reloadKeys.length; j++) {
            	  var reloadKey = table_data.reloadKeys[j];
            	  var attributeName = reloadKey.substr(0, 1).toLowerCase() + reloadKey.substr(1);
            	  
            	  var reloadValue = "";              
            	  
            	  if(savedRows[i].getAttributeDTO(attributeName) instanceof AttributeReferenceDTO) {

            		  reloadValue = savedRows[i]["getValue"](attributeName);              
            	  }
            	  else {
            		  reloadValue = savedRows[i]["get" + reloadKey]();              
            	  }
            		  
                  
                  record.setData(reloadKey, reloadValue);                  
                  row[reloadKey] = reloadValue;
            	}
            }

            table_data.dirty = false;
            
            btnSaveRows.set("disabled", table_data.cleanDisable);
            
            this.dataTable.render();

            if (table_data.after_save) {
              table_data.after_save();
            }
            myDataTable.fireEvent("tableSaveEvent");
          }
        }
      });


      var view_arr = createObjectRepresentation();
    	  
      // Save the table
      table_data.saveHandler(request, view_arr);
      
      btnSaveRows.set("disabled", true);
    };



  MojoGrid.saveHandler = saveSomeData;
  myDataTable.subscribe("editorSaveEvent", saveSomeData);

  if (YAHOO.util.Dom.get('buttons') === null) {

	var tableDiv = YAHOO.util.Dom.get(table_data.div_id);
    var buttons = document.createElement('span');
    
    if(!table_data.saveLabelKey){
    	table_data.saveLabelKey = 'Save_Rows_To_DB';
    }
    
    buttons.id = table_data.div_id + 'Buttons';
    YAHOO.util.Dom.addClass(buttons, 'noprint');
    YAHOO.util.Dom.addClass(buttons, 'dataTableButtons');
    buttons.innerHTML = '';

    if (table_data.addButton !== false) {
      buttons.innerHTML += '<button type="button" id="' + table_data.div_id + 'Addrow">' + MDSS.localize('New_Row') + '</button>';
    }

    buttons.innerHTML += '<button type="button" id="' + table_data.div_id + 'Saverows">' + MDSS.localize(table_data.saveLabelKey) + '</button>';

    if (table_data.excelButtons !== false) {
      buttons.innerHTML += '<form method="get" action="excelimport" style="display: inline;"><input type="hidden" name="excelType" value="' + table_data.excelType + '" /><span class="yui-button yui-push-button"> <span class="first-child"><button type="submit">' + MDSS.localize('Excel_Import_Header') + '</button></span></span></form>';
      buttons.innerHTML += '<form method="post" action="excelexport" style="display: inline;"><input type="hidden" name="excelType" value="' + table_data.excelType + '" /><span class="yui-button yui-push-button"> <span class="first-child"><button type="submit">' + MDSS.localize('Excel_Export_Header') + '</button></span></span></form>';
    }

    // Setup the custom buttons
    if(Mojo.Util.isArray(table_data.customButtons)) {
  	  for(var i = 0; i < table_data.customButtons.length; i++) {
  		  var config = table_data.customButtons[i];
  		  
  		  // Create the button and add it next to the previous button 
  		  buttons.innerHTML += '<button type="button" id="' + table_data.div_id + '.' + config.id + '">' + config.label + '</button>';
  	  }
    }

    YAHOO.util.Dom.insertAfter(buttons, tableDiv);
  }

  if (YAHOO.util.Dom.get(table_data.div_id + 'Saverows')) {
    // set up the button that saves the rows to the db
    var btnSaveRows = new YAHOO.widget.Button(table_data.div_id + "Saverows");
    btnSaveRows.on("click", persistData);
  }

  // Setup the custom button actions
  if(Mojo.Util.isArray(table_data.customButtons)) {
    for(var i = 0; i < table_data.customButtons.length; i++) {
      var config = table_data.customButtons[i];
  		  
  	  // set up the button that saves the rows to the db
  	  var customButton = new YAHOO.widget.Button(table_data.div_id + "." + config.id);
	  customButton.on("click", config.action);
    }
  }

  // Add one row to the bottom
  var addRow = function() {

	// Execute before row add
    if(typeof beforeRowAdd !== 'undefined' && Mojo.Util.isFunction(beforeRowAdd))
    {
    	beforeRowAdd();
    }

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

	// Execute after row add
    if(typeof afterRowAdd !== 'undefined' && Mojo.Util.isFunction(afterRowAdd))
    {
    	var index = myDataTable.getRecordSet().getLength() - 1;
    	afterRowAdd(myDataTable.getRecord(index), index);
    }
  };

  if (YAHOO.util.Dom.get(table_data.div_id + 'Addrow')) {
    var btnAddRow = new YAHOO.widget.Button(table_data.div_id + "Addrow");
    btnAddRow.on("click", addRow);

  }
  //myLogReader = new YAHOO.widget.LogReader();
  return {
    oDS : myDataSource,
    oDT : myDataTable,
    getObjects : createObjectRepresentation
  };
};