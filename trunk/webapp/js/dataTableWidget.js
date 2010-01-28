var MojoGrid = YAHOO.namespace('MojoGrid');
MojoGrid.cellLock = false;
MojoGrid.limitTab = false;


Mojo.Meta.newClass('MDSS.Event', {
  Instance : {
    initialize : function(type, value) {
      this.type = type;
      this.value = value;
    },
    
    getType : function() {
      return this.type;
    },
    
    getValue : function() {
      return this.value;
    }    
  },
  Static : {
    AFTER_ROW_ADD : 1,
    BEFORE_ROW_ADD : 2,
    AFTER_SAVE : 3,
    AFTER_PROBLEM : 4,
    AFTER_FAILURE : 5,
    AFTER_SELECTION : 6
  }
});

/**
 * Class to support functionality for all data grids
 */
Mojo.Meta.newClass('MDSS.dataGrid', {

  IsAbstract : false,
/*
  sampleTableData :{ 
    rows:[{"Collection":"79u4pk5vn8ik162omp5osu5sf6fgkn9pqswtic0sn1rb1bnqjq55et2t581fcvl0","Quantity":"2","Id":"xxairm6cnzw95157jdxnwugc9vvxed1r1p5h9e4d6yo03g38077vo7cycroins1n","QuantityFemale":"1","QuantityMale":"1","IdentificationMethod":"6p759e2830nifbz2a6qa5262bcodfsbkxccfwaj7etspalczxfe1im8q73mklelp","Specie":"8cpfq1kiui89rciqk4uduvdhvdzbh8psxccfwaj7etspalczxfe1im8q73mklelp","GroupId":"scfpeaqfv2eva4ln3u8l5r9jc1bq064zqm938dd8mus496g8igt9kdnvlhofk8lu"}],
    columnDefs: [{key:'GroupId',label:'Morphological Group Id',hidden:true},
               {key:'Collection',label:'Assays',hidden:true},
               {key:'Specie',label:'Species',editor:new YAHOO.widgetOntologyTermEditor.OntologyTermEditor({klass:'dss.vector.solutions.entomology.MorphologicalSpecieGroupView',attribute:'Specie',disableBtns:true})},
               {key:'IdentificationMethod',label:'Identification Method',editor:new YAHOO.widget.OntologyTermEditor({klass:'dss.vector.solutions.entomology.MorphologicalSpecieGroupView',attribute:'IdentificationMethod',disableBtns:true})},
               {key:'QuantityMale',label:'Number Male',editor:new YAHOO.widget.TextboxCellEditor({disableBtns:true})},
               {key:'QuantityFemale',label:'Number Female',editor:new YAHOO.widget.TextboxCellEditor({disableBtns:true})},
               {key:'Quantity',label:'Total Number',editor:new YAHOO.widget.TextboxCellEditor({disableBtns:true})},
               {key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}],
    defaults: {GroupId:"",Collection:"79u4pk5vn8ik162omp5osu5sf6fgkn9pqswtic0sn1rb1bnqjq55et2t581fcvl0", Specie:"",IdentificationMethod:"",QuantityMale:"",QuantityFemale:"",Quantity:""},
    div_id: "MorphologicalSpecieGroups",
    excelButtons:false,
    copy_from_above: ["IdentificationMethod"],
    //collection_setter: "setCollection('79u4pk5vn8ik162omp5osu5sf6fgkn9pqswtic0sn1rb1bnqjq55et2t581fcvl0')",
    data_type: "Mojo.$.dss.vector.solutions.entomology.MorphologicalSpecieGroupView"

  },
  */
  
  Instance : {
    
    /*
    myDataSource : null,
    myDataTable : null,
    tableData: null,
    bReverseSorted : false,
    btnSaveRows : false,
    btnAddRow : false,
    */
     
    initialize : function(data)
    {         
            this.tableData = data;
          this.myDataSource = null;
          this.myDataTable = null;
          this.bReverseSorted = false;
          this.btnSaveRows = false;
          this.btnAddRow = false;
          this.disableButton = !Mojo.Util.isBoolean(data.cleanDisable) ? true : data.cleanDisable;

          // set the fields
          if (typeof this.tableData.fields === 'undefined') {
            this.tableData.fields = this.tableData.columnDefs.map( function(c) {
              return c.key;
            }).filter( function(c) {
              return (c !== 'delete');
            });
          }
        
          if (typeof this.tableData.saveFunction === 'undefined') {
            this.tableData.saveFunction = "saveAll";
          }
        
          if (typeof this.tableData.addButton === 'undefined') {
            this.tableData.addButton = "allreadyThere";
          }
        
          if (typeof this.tableData.copy_from_above === 'undefined') {
            this.tableData.copy_from_above = [];
          }
        
          this.tableData.dirty = false;
        
          // load the data
          this.myDataSource = new YAHOO.util.DataSource(this.tableData.rows);
          this.myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
          this.myDataSource.responseSchema = {
            fields : this.tableData.fields
          };
        
          // Scrolling Data Table is slow, so we use regular data table if possible
          if(this.tableData.width){
             this.myDataTable = new YAHOO.widget.ScrollingDataTable(this.tableData.div_id, this.tableData.columnDefs, this.myDataSource, {
                width : this.tableData.width
              });
          }
          else
          {
             this.myDataTable = new YAHOO.widget.DataTable(this.tableData.div_id, this.tableData.columnDefs, this.myDataSource, {});
          }          
                   
          //set this so it accessable by other methods in the jsp
          this.myDataTable.tableData = this.tableData;          
          this.tableData.myDataTable = this.myDataTable;
          
          // the data comes from the server as ids, we need to set the labels
          this._mapRecords();
          
          this.myDataTable.set("selectionMode","singlecell");
          
          this.myDataTable.render();
          
          this.myDataTable.subscribe("columnSortEvent", this._trackReverseSorts);

          this.myDataTable.subscribe("cellMouseoverEvent", this._highlightEditableCell);

          this.myDataTable.subscribe("cellMouseoutEvent", this.myDataTable.onEventUnhighlightCell);

          this.myDataTable.subscribe("tbodyFocusEvent", function() {
          var selectedCells = this.getSelectedCells();
            if(selectedCells.length === 0) {
              this.selectCell(this.getFirstTdEl());
            }
          });

          this.myDataTable.subscribe("editorSaveEvent", function(o) {
            this.focusTbodyEl();
          });

          this.myDataTable.subscribe("editorCancelEvent", function(o) {
            this.focusTbodyEl();
          });
          
          this.myDataTable.subscribe("cellClickEvent", this.onCellClick, null, this);
          
          this.myDataTable.subscribe("editorKeydownEvent", this.editorKeyEvent, null, this);

          this.myDataTable.subscribe("editorSaveEvent", this.saveSomeData, null, this);
          
          this._setUpButtons();
          
          /*
          //myLogReader = new YAHOO.widget.LogReader();
          return {
            oDS : this.myDataSource,
            oDT : this.myDataTable,
            getObjects : this.createObjectRepresentation
          };
         */
    this._listeners = [];
          
    },
    
    _getDisableButton : function() {
      return this.disableButton;
    },
  
    _getLabelFromId : function(feild, id) {
      var i = window[feild + "Ids"].indexOf(id);
      return window[feild + "Labels"][i];
    },
    
    _trackReverseSorts : function(oArg) {
      bReverseSorted = (oArg.dir === YAHOO.widget.DataTable.CLASS_DESC);
    },
    
    // Set up editing flow
    _highlightEditableCell : function(oArgs) {
      var elCell = oArgs.target;
      if (YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {
        this.highlightCell(elCell);
      }
    },
    
    _saveHandler : function(request, view_array) {
      // Get the class which defines the save function
      var klass = Mojo.Meta.findClass(this.tableData.data_type.substring(7));
          
       // Get the save function
      var saveMethod = klass[this.tableData.saveFunction];
          
      // Invoke the save method
      saveMethod(request, view_array);
    },
    
    _mapRecords : function() {
      this.recordIndex = 0;
      this.myDataTable.getRecordSet().getRecords().map( function(record) {
        this.record = record;
        this.tableData.columnDefs.map( function(feild) {
          var editor = this.myDataTable.getColumn(feild.key).editor;

          if (feild.save_as_id) {
            var label = this._getLabelFromId(feild.key, this.record.getData(feild.key));
            this.record.setData(feild.key, label);
          }
          else{
            if (editor && editor instanceof YAHOO.widget.DropdownCellEditor){
              //data comes in as value instead of label, so we fix this.
              for( var i = 0; i < editor.dropdownOptions.length; i++) {
                var recordValue = this.record.getData(feild.key);
                var optionValue = editor.dropdownOptions[i].value;
                var label = editor.dropdownOptions[i].label;
                
                if (recordValue === optionValue){
                  this.record.setData(feild.key, label);
                  //myDataTable.updateCell(this.record, editor.getColumn(), editor.dropdownOptions[i].label);
                }
              }
            }
          }
          if (editor instanceof YAHOO.widget.OntologyTermEditor )            
          {
              editor.tableData = this.tableData;
              var data = this.record.getData(feild.key);
              if(data){
                var id = data.split('^^^^')[1];
                var displayLabel = data.split('^^^^')[0];
                var r = this.tableData.rows[this.recordIndex];
                if(r){
                  r[feild.key] = id;
                  this.record.setData(feild.key, displayLabel);
                }
              }
          }
          if (editor && editor instanceof YAHOO.widget.DateCellEditor) {
            var date = MDSS.Calendar.parseDate(this.record.getData(feild.key));
            this.myDataTable.updateCell(this.record, feild.key, date);
          }

          if (feild.title) {
             var th = this.myDataTable.getThEl(this.myDataTable.getColumn(feild.key));
             if(th)
             {
               th.title = feild.title;
             }
          }

          },this);
        if (this.tableData.after_row_load) {
          this.tableData.after_row_load(this.record);
        }
        this.recordIndex++;
      },this);
      this.record = null;
      this.recordIndex  = null;
    },
    
    _setUpButtons : function(record) {
    if (YAHOO.util.Dom.get('buttons') === null) {

      var tableDiv = YAHOO.util.Dom.get(this.tableData.div_id);
        var buttons = document.createElement('span');
        
        if(!this.tableData.saveLabelKey){
          this.tableData.saveLabelKey = 'Save_Rows_To_DB';
        }
        
        buttons.id = this.tableData.div_id + 'Buttons';
        YAHOO.util.Dom.addClass(buttons, 'noprint');
        YAHOO.util.Dom.addClass(buttons, 'dataTableButtons');
        buttons.innerHTML = '';

        if (this.tableData.addButton !== false) {
          buttons.innerHTML += '<button type="button" id="' + this.tableData.div_id + 'Addrow">' + MDSS.localize('New_Row') + '</button>';
        }

        buttons.innerHTML += '<button type="button" id="' + this.tableData.div_id + 'Saverows">' + MDSS.localize(this.tableData.saveLabelKey) + '</button>';

        if (this.tableData.excelButtons !== false) {
          buttons.innerHTML += '<form method="get" action="excelimport" style="display: inline;"><input type="hidden" name="excelType" value="' + this.tableData.excelType + '" /><span class="yui-button yui-push-button"> <span class="first-child"><button type="submit">' + MDSS.localize('Excel_Import_Header') + '</button></span></span></form>';
          buttons.innerHTML += '<form method="post" action="excelexport" style="display: inline;"><input type="hidden" name="excelType" value="' + this.tableData.excelType + '" /><span class="yui-button yui-push-button"> <span class="first-child"><button type="submit">' + MDSS.localize('Excel_Export_Header') + '</button></span></span></form>';
        }

        // Setup the custom buttons
        if(Mojo.Util.isArray(this.tableData.customButtons)) {
          for(var i = 0; i < this.tableData.customButtons.length; i++) {
            var config = this.tableData.customButtons[i];
            
            // Create the button and add it next to the previous button 
            buttons.innerHTML += '<button type="button" id="' + this.tableData.div_id + '.' + config.id + '">' + config.label + '</button>';
          }
        }

        YAHOO.util.Dom.insertAfter(buttons, tableDiv);
      }
    
      if (YAHOO.util.Dom.get(this.tableData.div_id + 'Addrow')) {
        this.btnAddRow = new YAHOO.widget.Button(this.tableData.div_id + "Addrow");
        this.btnAddRow.on("click", this.addRow, null, this);
      } 
    
      if (YAHOO.util.Dom.get(this.tableData.div_id + 'Saverows')) {
        // set up the button that saves the rows to the db
        this.btnSaveRows = new YAHOO.widget.Button(this.tableData.div_id + "Saverows");
        this.btnSaveRows.on("click", this.persistData, null, this);
        this.toggleSaveButton(this._getDisableButton());
      }
  
      // Setup the custom button actions
      if(Mojo.Util.isArray(this.tableData.customButtons)) {
        for(var i = 0; i < this.tableData.customButtons.length; i++) {
          var config = this.tableData.customButtons[i];
            
          // set up the button that saves the rows to the db
          var customButton = new YAHOO.widget.Button(this.tableData.div_id + "." + config.id);
        customButton.on("click", config.action, null, this);
        }
      }
      
     
    },
    
    findNext : function(cell,e) {
      var newCell = null;
      if (e.shiftKey) {
        newCell = this.myDataTable.getPreviousTdEl(cell);
      } else {
        newCell = this.myDataTable.getNextTdEl(cell);
      }
      while (newCell !== null && (this.myDataTable.getColumn(newCell).editor === null || this.myDataTable.getColumn(newCell).hidden === true)) {
        if (e.shiftKey) {
          newCell = this.myDataTable.getPreviousTdEl(newCell);
        } else {
          newCell = this.myDataTable.getNextTdEl(newCell);
        }
      }
      return (newCell);
    },
    
    /***************************************************************************
     * handleEditorKeyEvent ( obj ) Handle a keypress when the Cell Editor is
     * open Enter will close the editor and move down Tab will close the editor
     * and move right. Use the handleTableKeyEvent() to handle the moving Open a
     * new cell editor on the newly focused cell */

    editorKeyEvent : function(obj) {

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



        try
        {
          //YAHOO.log("Tabbed Key Press on Cell:" + cell.headers, "warn", "Widget");

          var cell = this.myDataTable.getCellEditor().getTdEl();
          var nextCell = this.findNext(cell,e);
          var nextRow = null;

          // No editable cell found on this row, go to the next row and search for
          // editable cell
          if (nextCell === null)
          {
            nextCell = cell;
          }


          this.myDataTable.saveCellEditor();
          //YAHOO.log("Saved Cell Editor:" + cell.headers, "warn", "Widget");

          if (nextCell) {
            //YAHOO.log("Selecting Cell Editor:" + nextCell.headers, "warn", "Widget");

            this.myDataTable.unselectAllCells();

            this.myDataTable.selectCell(nextCell);
            this.myDataTable.showCellEditor(nextCell);
            this.myDataTable.selectCell(nextCell);

            //YAHOO.log("Showing Cell Editor:" + nextCell.headers, "warn", "Widget");
          }
        }
        finally
        {
          MojoGrid.cellLock = false;
        }
      }

    },


    // Save edits back to the original data array
    saveSomeData : function(oArgs) {

      var record = oArgs.editor.getRecord();
      var editor = oArgs.editor;
      var index = this.myDataTable.getRecordIndex(record);
      
      //do nothing if nothing changed
      if(oArgs.newData == oArgs.oldData )
      {
        return;
      }

      if (editor instanceof YAHOO.widget.DropdownCellEditor && window[oArgs.editor.getColumn().key + "Labels"])
      {
          var i = window[oArgs.editor.getColumn().key + "Labels"].indexOf(oArgs.newData);
          var id = window[oArgs.editor.getColumn().key + "Ids"][i];
          this.tableData.rows[index][oArgs.editor.getColumn().key]  = id;
      }
      else if (editor instanceof YAHOO.widget.OntologyTermEditor )
      {
          var id = oArgs.newData;
          var displayLabel = editor.getInputDisplayLabel();
          this.tableData.rows[index][oArgs.editor.getColumn().key] = id;
          this.myDataTable.updateCell(record, editor.getColumn(), displayLabel);       
      }
      else
      {
        this.tableData.rows[index][oArgs.editor.getColumn().key] = oArgs.newData ;
        if (editor instanceof YAHOO.widget.DropdownCellEditor) {
          //When an item is selected YUI displays the value instead of the label, so we fix this.
          Mojo.Iter.forEach(editor.dropdownOptions, function(opt){
            if (oArgs.newData === opt.value){
              this.myDataTable.updateCell(record, editor.getColumn(), opt.label);
            }
          }, this);          
        }
      }

      this.tableData.dirty = true;
      this.enableSaveButton();
      if (this.tableData.after_row_edit) {
        this.tableData.after_row_edit(record);
      }

      this._sumColumn.apply(this,[oArgs]);

      //this.myDataTable.unselectCell(editor.getTdEl());
      //YAHOO.log("Saved Cell:" + editor._oColumn.label, "warn", "Widget");
    },
    
    
  //calculate totals
    _sumColumn : function(oArgs) {

      if (oArgs.editor.getColumn().sum  && this.tableData.rows.length > 1) {
        var record = oArgs.editor.getRecord();
        var cellValue = record.getData(oArgs.editor.getColumn().key);
        var lastIndex = this.tableData.rows.length - 1;
        var lastRecord = this.myDataTable.getRecord(lastIndex);
        var editor = oArgs.editor;
        var index = this.myDataTable.getRecordIndex(record);
        var dt = this.myDataTable;
        var manualLastRowData = this.tableData.rows[lastIndex][editor.getColumn().key];
        var lastTd = this.myDataTable.getTdEl( {
          record : lastRecord,
          column : editor.getColumn()
        });
        
        var editedTd = this.myDataTable.getTdEl( {
          record : record,
          column : editor.getColumn()
        });
        
        //no calculation is done if cell was tabbed past
        if(!(oArgs.newData || oArgs.oldData ))
        {
          return;
        }
        
        //they have entered data so make sure to remove the calcuated style
        YAHOO.util.Dom.removeClass(this.myDataTable.getTdLinerEl(editedTd), "calculated");
        
        var newData = parseInt(oArgs.newData,10);
        newData = newData || 0;
        
        var oldData = parseInt(oArgs.oldData,10);
        oldData = oldData || 0;

        var oldTotal = parseInt(lastRecord.getData(editor.getColumn().key),10);
        oldTotal = oldTotal || 0;

        var newTotal = oldTotal + newData - oldData;

        //no calculation is done if number is entered manualy
        if (index !== lastIndex  && (! manualLastRowData) && (oArgs.newData != '' || cellValue == '')) {
          dt.updateCell(lastRecord, editor.getColumn(), newTotal);
          YAHOO.util.Dom.addClass(this.myDataTable.getTdLinerEl(lastTd), "calculated");
          //we do not save autocalculated values
          //this.tableData.rows[lastIndex][editor.getColumn().key] = newTotal;
        }

        var sum = 0;

        dt.getRecordSet().getRecords().map( function(row) {
          var x = parseInt(row.getData(editor.getColumn().key),10);
          if (x && dt.getRecordIndex(row) !== lastIndex){
            sum += x;
          }
        });
                
        
        if (parseInt(lastRecord.getData(editor.getColumn().key),10) != sum) {
          YAHOO.util.Dom.addClass(lastTd, "dataTableSumError");
        } else {
          YAHOO.util.Dom.removeClass(lastTd, "dataTableSumError");
        }

      }

      
    },

    _setValue : function(view, attribute, value){
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
      }, 
    
    createObjectRepresentation : function() {
        var view_arr = new Array();

        for ( var r = 0; r < this.tableData.rows.length; r++) {
          var row = this.tableData.rows[r];
          var view_contructor = Mojo.Meta.findClass(this.tableData.data_type.substring(7));
          var view = new view_contructor();

          for ( var i = 0; i < this.tableData.fields.length; i++) {
            var attrib = this.tableData.fields[i];
            var val = row[attrib.key];

            this._setValue(view, attrib, val);
          }
          view_arr.push(view);
        }
        
        return view_arr;
    },
    
    persistData : function() {
      // save any open editors before we send the ajax request
        this.myDataTable.saveCellEditor();
        
        var request = new MDSS.Request( {
          // success handler for saved rows
          dataTable : this.myDataTable,
          tableData : this.tableData,
          btnSaveRows : this.btnSaveRows,
          thisRef : this,
          onSuccess : function(savedRows) {
            if (!this.tableData.dont_update_on_save) {
              
            // Get the keys of the columns which need to be reloaded
            if(!this.tableData.reloadKeys) {
              this.tableData.reloadKeys = [this.tableData.fields[0].key];
            }
            else {
              this.tableData.reloadKeys.push(this.tableData.fields[0].key);
            }

            // Refresh the displayed values of the columns specified in 'reloadKeys'
              for( var i = 0; i < savedRows.length; i++) {
                var record = this.dataTable.getRecord(i);
                var row = this.tableData.rows[i];

                for(var j = 0; j < this.tableData.reloadKeys.length; j++) {
                  var reloadKey = this.tableData.reloadKeys[j];
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

              this.tableData.dirty = false;
              
              this.thisRef.toggleSaveButton(this.thisRef._getDisableButton());
              
              this.dataTable.render();

              if (this.tableData.after_save) {
                this.tableData.after_save();
              }
              
              this.thisRef.myDataTable.fireEvent("tableSaveEvent");
              
              this.thisRef.fireEvent(new MDSS.Event(MDSS.Event.AFTER_SAVE, {}));
            }
          }
        });
        
        // Re enable the save button widget when there is a problem
        var oldOnProblemExceptionDTO = request.onProblemExceptionDTO;
        var newOnProblemExceptionDTO = function(e) {
           oldOnProblemExceptionDTO.apply(request, [e]);
          
          this.thisRef.enableSaveButton();
          this.thisRef.fireEvent(new MDSS.Event(MDSS.Event.AFTER_PROBLEM, {}))
        }
        
        var oldOnFailure = request.onFailure;
        var newOnFailure = function(e) {
          oldOnFailure.apply(request, [e]);
          
          this.thisRef.enableSaveButton();
          this.thisRef.fireEvent(new MDSS.Event(MDSS.Event.AFTER_FAILURE, {}))
        }

        request.onProblemExceptionDTO = newOnProblemExceptionDTO;
        request.onFailure = newOnFailure;

        var view_arr = this.createObjectRepresentation();

        // Disable the save button until the request has been executed
        this.disableSaveButton();
        
        // Save the table
        if(Mojo.Util.isFunction(this.tableData.saveHandler)) {
          this.tableData.saveHandler(request, view_arr);
        }
        else
        {
          this._saveHandler(request, view_arr);
        }
      },
    
    toggleSaveButton : function(disabled) {
      this.btnSaveRows.set("disabled", disabled);       
    },
    
    enableSaveButton : function() {
      this.toggleSaveButton(false);
    },
    
    disableSaveButton : function() {
      this.toggleSaveButton(true);
    },
    
    // Add one row to the bottom
    addRow : function() {

    // Execute before row add
      var event = new MDSS.Event(MDSS.Event.BEFORE_ROW_ADD, {});
          
      this.fireEvent(event);

      // Clear sort when necessary
      if (this.bReverseSorted) {
        this.myDataTable.set("sortedBy", null);
      }

      // FIREFOX ONLY
      var new_data_row =  this.getDefaultValues();
      var new_label_row = this.getDefaultLabels();
      if (this.tableData.rows.length > 0) {
        var last_row_index = this.tableData.rows.length - 1;
        this.tableData.copy_from_above.map( function(feild) {
          new_data_row[feild] = this.tableData.rows[last_row_index][feild];
          var label = this.myDataTable.getRecord(last_row_index).getData(feild);
          new_label_row[feild] = label;

        },this);
      }
      this.tableData.rows.push(new_data_row);
      this.myDataTable.addRow(new_label_row);
      this.tableData.dirty = true;
      this.enableSaveButton();

      // Execute after row add
      var index = this.myDataTable.getRecordSet().getLength() - 1;
      var record = this.myDataTable.getRecord(index);
      
      var event = new MDSS.Event(MDSS.Event.AFTER_ROW_ADD, {index:index, record:record});
      
      this.fireEvent(event);
      
      return event;
    },
    
    fireEvent : function(event) {       
      for(var i = 0; i < this._listeners.length; i++) {
        this._listeners[i](event);
      }    	
    },
    
    getDefaultValues : function() {
      var row = new Object();
      var defaults = this.tableData.defaults;
      
      var keys = Mojo.Util.getKeys(defaults);
      
      for(var i = 0; i < keys.length; i++) {
        var key = keys[i];
        
        var value = ((defaults[key].value != null) ? defaults[key].value : defaults[key]) ;
        
        row[key] = value;
      }
      
      return row;
    },
    
    getDefaultLabels : function() {
      var row = new Object();
      var defaults = this.tableData.defaults;
        
      var keys = Mojo.Util.getKeys(defaults);
        
      for(var i = 0; i < keys.length; i++) {
        var key = keys[i];
          
        var value = ((defaults[key].value != null) ? defaults[key].value : defaults[key]) ;
        var label = defaults[key].label;
          
        row[key] = ((label != null )?label:value);
      }
      
      return row;
    },
    
    
    onCellClick : function(oArgs) {
      var target = oArgs.target;
      //this.myDataTable.focusTbodyEl();
      this.myDataTable.unselectAllCells();
      this.myDataTable.selectCell(target);
      var column = this.myDataTable.getColumn(target);

      // don't allow editing if column is hidden
      if(column.hidden)
      {
        return;
      }

      switch (column.action) {
        case 'delete':
          var record = this.myDataTable.getRecord(target);
          var row_id = record.getData(this.tableData.fields[0].key);
          var row_index = this.myDataTable.getRecordIndex(record);
          if (confirm(MDSS.Localized.Confirm_Delete_Row + (row_index + 1) + '?')) {
            if (typeof row_id !== 'undefined' && row_id.length > 1) {
              var request = new MDSS.Request( {
                dataTable : this.myDataTable,
                row_index : row_index,
                thisRef : this,
                onSuccess : function(deletedRow) {
                  this.thisRef.tableData.rows.splice(request.row_index, 1);
                  request.dataTable.deleteRow(target);
                }
              });
              Mojo.Facade.deleteEntity(request, row_id);
            } else {
              this.myDataTable.deleteRow(target);
              this.tableData.rows.splice(row_index, 1);
            }

          }
          break;
        default:
          this.myDataTable.onEventShowCellEditor(oArgs);
          break;
      }
    },

    addListener : function(listener){
      this._listeners.push(listener);
    }
  }

});



MojoGrid.createDataTable = function(data){ 
  return new MDSS.dataGrid(data);
};

//MojoGrid.saveHandler = this.saveSomeData;
