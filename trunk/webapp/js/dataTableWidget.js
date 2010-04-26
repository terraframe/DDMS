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
    AFTER_SELECTION : 6,
    AFTER_DELETE : 7,
    BEFORE_SEARCH : 8,
    RELOAD_VALUE : 9,
    SUCCESSFUL_SAVE: 10
  }
});

Mojo.Meta.newClass('MDSS.ModelMetadata', {
  Instance : {
    initialize : function(prop) {
      this._start = prop.start;
      this._end = prop.end;
      this._type = prop.type;
      this._keys = prop.keys;
    },
    
    getKeys : function() {
      return this._keys;
    },
    
    getKey : function(index) {
      return this._keys[index];
    },
    
    getStart : function() {
      return this._start;
    },
    
    getEnd : function() {
      return this._end;
    },
    
    getType : function() {
      return this._type;
    }
  }
});

Mojo.Meta.newClass('MDSS.DataGridModel' ,{
  Instance : {
    initialize : function(metadata, rows, saveHandler) {
      this._metadata = metadata;
      this._rows = rows;
      this._listeners = [];
      this._controller = null; 
      this._saveHandler = saveHandler;
    },
            
    _update : function(rows) {
      var keys = this._metadata.getKeys();
      var length = this.length();
  
      // Refresh the values of all the columns
      for( var i = 0; i < length; i++) {

        for(var j = 0; j < keys.length; j++) {
          var key = keys[j];
          var attributeName = key.substr(0, 1).toLowerCase() + key.substr(1);
                    
          var value = rows[i].getValue(attributeName);              
          
          this.setData(i, key, value);
          
          this.fireEvent(new MDSS.Event(MDSS.Event.RELOAD_VALUE, {row:i, col:key, value:value}));
        }
      }
      
      this.fireEvent(new MDSS.Event(MDSS.Event.SUCCESSFUL_SAVE, {}));
    },
    
    _createRequest : function(){
      var request = new MDSS.Request( {
        // success handler for saved rows
        that : this,
        onSuccess : function(rows) {
          this.that._update(rows);      
        }
      });
          
      // Re enable the save button widget when there is a problem
      var oldOnProblemExceptionDTO = request.onProblemExceptionDTO;
      var newOnProblemExceptionDTO = function(e) {
        oldOnProblemExceptionDTO.apply(request, [e]);
            
        this.that.fireEvent(new MDSS.Event(MDSS.Event.AFTER_PROBLEM, {}))
      }
          
      var oldOnFailure = request.onFailure;
      var newOnFailure = function(e) {
        oldOnFailure.apply(request, [e]);
            
        this.that.fireEvent(new MDSS.Event(MDSS.Event.AFTER_FAILURE, {}))
      }

      request.onProblemExceptionDTO = newOnProblemExceptionDTO;
      request.onFailure = newOnFailure;      
      
      return request;
    },
    
    save : function() {      
      var request = this._createRequest();         

      var objects = this.getObjects();
          
      // Save the table
      this._saveHandler(request, objects);
    },
        
    getObjects : function() {
      var objects = new Array();
      
      var type = this._metadata.getType();
      var start = this._metadata.getStart();
      var end = this._metadata.getEnd();

      for ( var r = 0; r < this._rows.length; r++) {
        var row = this._rows[r];
                
        var _contructor = Mojo.Meta.findClass(type);
        var object = new _contructor();

        for ( var i = start; i < end; i++) {
          var key = this._metadata.getKey(i);
          var value = row[key];

          this._setObjectValue(object, key, value);
        }
        
        objects.push(object);
      }
        
      return objects;
    },
    
    _setObjectValue : function(object, key, value){
      var attributeName = key.substring(0, 1).toLowerCase() + key.substring(1);

      var setter_exists = Mojo.Util.isFunction(object['set' + key]);
        
      if (setter_exists) {
        if (value) {
          if (object.attributeMap[attributeName] instanceof com.runwaysdk.transport.attributes.AttributeDateDTO) {
            object['set' + key](MDSS.Calendar.parseDate(value));
          }
          else {
            object['set' + key](value);
          }
        } 
      }
      else{
        // enum setters start with "add" instead of "set"
        var setter_exists = Mojo.Util.isFunction(object['add' + key]);
        
        if (setter_exists) {
          object['add' + key](value);
        }
      }
    },
    
    setData : function(row, col, value) {
      this._rows[row][col] = value;
    },
    
    getData : function(row, col) {
      return this._rows[row][col];
    },
        
    length : function() {
      return this._rows.length;
    },
    
    // Add one row to the bottom
    addRow : function(row) {
      this._rows.push(row);
    },
    
    getRow : function(row) {
      return this._rows[row];
    },   
    
    getRows : function() {
      return this._rows;
    },   
    
    fireEvent : function(event) {       
      for(var i = 0; i < this._listeners.length; i++) {
        this._listeners[i](event);
      }      
    },
    
    hasRow : function(index) {
      return (this.length() > index);
    },
        
    deleteRow : function(index) {
      if(this.hasRow(index)) {
        var key = this._metadata.getKey(0);
        var id = this.getData(index, key);

        var request = new MDSS.Request( {
          id : id,
          index : index,
          that : this,
          onSuccess : function() {
            this.that.removeRow(this.index, this.id);
          }
        });

        if(id != null) {
          Mojo.Facade.deleteEntity(request, id);
        }
        else {
          this.removeRow(index, id);
        }
      }
    },
    
    removeRow : function(index, id) {
      this._rows.splice(index, 1);

      this.fireEvent(new MDSS.Event(MDSS.Event.AFTER_DELETE, {id:id, index:index}));
    },

    addListener : function(listener){
      this._listeners.push(listener);
    }
  },
  
  Static : {
    getDefaultSaveHandler : function(saveFunction, dataType)  {
      saveFunction = (typeof saveFunction === 'undefined' ? "saveAll" : saveFunction);
    
      var handler = function(request, parameters) {      
        // Get the class which defines the save function
        var klass = Mojo.Meta.findClass(dataType);
            
        // Get the save function
        var saveMethod = klass[saveFunction];
            
        // Invoke the save method
        saveMethod(request, parameters);
      };
      
      return handler;
    }
  }
});

/**
 * Class to support functionality for all data grids
 */
Mojo.Meta.newClass('MDSS.DataGrid', {

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
     
    initialize : function(model, data)
    {     
      this._model = model;

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
                
      if (typeof this.tableData.addButton === 'undefined') {
        this.tableData.addButton = "allreadyThere";
      }
        
      if (typeof this.tableData.copy_from_above === 'undefined') {
        this.tableData.copy_from_above = [];
      }
        
      this.tableData.dirty = false;
        
      // load the data
      this.myDataSource = new YAHOO.util.DataSource(this._model.getRows());
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
      else {
        this.myDataTable = new YAHOO.widget.DataTable(this.tableData.div_id, this.tableData.columnDefs, this.myDataSource, {});
      }          
                   
      //set this so it accessable by other methods in the jsp
      this.myDataTable.tableData = this.tableData;          
      this.tableData.myDataTable = this.myDataTable;
          
      // the data comes from the server as ids, we need to set the labels
      this._initializeRecords();
          
      this.myDataTable.set("selectionMode","singlecell");
          
      this.myDataTable.render();
          
      this.myDataTable.subscribe("columnSortEvent", this._trackReverseSorts);

      this.myDataTable.subscribe("cellMouseoverEvent", this._highlightEditableCell);

      this.myDataTable.subscribe("cellMouseoutEvent", this.myDataTable.onEventUnhighlightCell);

      this.myDataTable.subscribe("cellClickEvent", this.onCellClick, null, this);
         
      this.myDataTable.subscribe("editorKeydownEvent", this.editorKeyEvent, null, this);

      this.myDataTable.subscribe("editorSaveEvent", this._saveHandler, null, this);
          
      this._setUpButtons();

      this._model.addListener(Mojo.Util.bind(this, this._modelEventHandler));
    },
    
    _getDisableButton : function() {
      return this.disableButton;
    },
  
    _getLabelFromId : function(field, id) {
      var i = window[field + "Ids"].indexOf(id);
      return window[field + "Labels"][i];
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
    
    _modelEventHandler : function(event) {
      if(event.getType() == MDSS.Event.RELOAD_VALUE) {
        var map = event.getValue();
        
        this.updateValue(map.row, map.col, map.value);
      }
      else if(event.getType() == MDSS.Event.AFTER_PROBLEM) {
        this.enableSaveButton();
      }
      else if(event.getType() == MDSS.Event.AFTER_FAILURE) {
        this.enableSaveButton();
      }
      else if(event.getType() == MDSS.Event.SUCCESSFUL_SAVE) {
        this.success();
      }
      else if(event.getType() == MDSS.Event.AFTER_DELETE) {
        var map = event.getValue();
        
        this.myDataTable.deleteRow(map.index);      
      }
    },
    
    updateValue : function(row, col, value) {
      var record = this.myDataTable.getRecord(row);
                      
      var editor = this.myDataTable.getColumn(col).editor;
      
      if (editor && !(editor instanceof YAHOO.widget.DropdownCellEditor || editor instanceof YAHOO.widget.OntologyTermEditor )) {
        record.setData(col, value);                  
      }
    },
    
    success : function() {
      this.tableData.dirty = false;
                 
      this.toggleSaveButton(this._getDisableButton());
                
      this.myDataTable.render();

      if (this.myDataTable.after_save) {
        this.myDataTable.after_save();
      }
                
      this.myDataTable.fireEvent("tableSaveEvent");    
    },
    
    // Add one row to the bottom
    addRow : function() {
      // Execute before row add
      var event = new MDSS.Event(MDSS.Event.BEFORE_ROW_ADD, {});
            
      this._model.fireEvent(event);
    

      // Clear sort when necessary
      if (this.bReverseSorted) {
        this.myDataTable.set("sortedBy", null);
      }

      // FIREFOX ONLY
      var new_data_row =  this.getDefaultValues();
      var new_label_row = this.getDefaultLabels();
      
      if (this._model.length() > 0) {
        var last_row_index = this._model.length() - 1;
        
        this.tableData.copy_from_above.map( function(field) {
          new_data_row[field] = this._model.getData(last_row_index, field);
          
          var label = this.myDataTable.getRecord(last_row_index).getData(field);
          new_label_row[field] = label;
        },this);
      }
      
      this._model.addRow(new_data_row);

      this.myDataTable.addRow(new_label_row);
      
      this.tableData.dirty = true;
      this.enableSaveButton();

      // Execute after row add
      var index = this.myDataTable.getRecordSet().getLength() - 1;
      var record = this.myDataTable.getRecord(index);
      
      var event = new MDSS.Event(MDSS.Event.AFTER_ROW_ADD, {index:index, record:record});
      
      this._model.fireEvent(event);
      
      return event;
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
    
    _initializeRecords : function() {
      this.recordIndex = 0;
      this.myDataTable.getRecordSet().getRecords().map( function(record) {
        this.record = record;
        this.tableData.columnDefs.map( function(field) {
          var editor = this.myDataTable.getColumn(field.key).editor;

          if (field.save_as_id) {
            var label = this._getLabelFromId(field.key, this.record.getData(field.key));
            this.record.setData(field.key, label);
          }
          else{
            if (editor && editor instanceof YAHOO.widget.DropdownCellEditor){
              
              //data comes in as value instead of label, so we fix this.
              for( var i = 0; i < editor.dropdownOptions.length; i++) {
                var recordValue = this.record.getData(field.key);
                
                var optionValue = editor.dropdownOptions[i].value;
                var label = editor.dropdownOptions[i].label;
                
                if (recordValue === optionValue){
                  this.record.setData(field.key, label);
                }
              }
            }
          }
          
          if (editor instanceof YAHOO.widget.OntologyTermEditor )            
          {
            editor.tableData = this.tableData;
            var data = this.record.getData(field.key);
            if(data){
              var id = data.split('^^^^')[1];
              var displayLabel = data.split('^^^^')[0];
                
              if(this._model.hasRow(this.recordIndex)) {
                this._model.setData(this.recordIndex, field.key, id);
                this.record.setData(field.key, displayLabel);
              }
            }
          }
          
          if (editor && editor instanceof YAHOO.widget.DateCellEditor) {
            var date = MDSS.Calendar.parseDate(this.record.getData(field.key));
            this.myDataTable.updateCell(this.record, field.key, date);
          }

          if (field.title) {
            var th = this.myDataTable.getThEl(this.myDataTable.getColumn(field.key));
            if(th) {
              th.title = field.title;
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
        this.btnSaveRows.on("click", this._persistHandler, null, this);
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
      var nKey = e.keyCode ;
      if(nKey === 13 )//Enter
      {
        if(e.shiftKey) {
          newCell = this.myDataTable.getAboveTdEl(cell);
        }
        else{ 
          newCell = this.myDataTable.getBelowTdEl(cell);
        }
      }
      else if(nKey === 9)//Tab
      {
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
      }
      return (newCell);
    },
    
    /***************************************************************************
     * handleEditorKeyEvent ( obj ) Handle a keypress when the Cell Editor is
     * open Enter will close the editor and move down Tab will close the editor
     * and move right. Use the handleTableKeyEvent() to handle the moving Open a
     * new cell editor on the newly focused cell */

    editorKeyEvent : function(obj) {

      var e = obj.event;

      // 9 = tab, 13 = enter
      if (e.keyCode === 9  || e.keyCode === 13) {

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
          var cell = this.myDataTable.getCellEditor().getTdEl();
          var nextCell = this.findNext(cell,e);

          this.myDataTable.saveCellEditor();
          if (nextCell) {
            this.myDataTable.unselectAllCells();
            this.myDataTable.showCellEditor(nextCell);
            this.myDataTable.selectCell(nextCell);
          }
        }
        finally
        {
          MojoGrid.cellLock = false;
        }
      }

    },


    // Save edits back to the original data array
    _saveHandler : function(oArgs) {

      var record = oArgs.editor.getRecord();
      var editor = oArgs.editor;
      var index = this.myDataTable.getRecordIndex(record);
      var key = oArgs.editor.getColumn().key;
      
      //do nothing if nothing changed
      if(oArgs.newData == oArgs.oldData )
      {
        return;
      }

      if (editor instanceof YAHOO.widget.DropdownCellEditor && window[oArgs.editor.getColumn().key + "Labels"])
      {
        var i = window[key + "Labels"].indexOf(oArgs.newData);
        var id = window[key + "Ids"][i];
                  
        this._model.setData(index, key, id);
      }
      else if (editor instanceof YAHOO.widget.OntologyTermEditor )
      {
        var id = oArgs.newData;
        var displayLabel = editor.getInputDisplayLabel();
          
        this._model.setData(index, key, id);
        this.myDataTable.updateCell(record, editor.getColumn(), displayLabel);       
      }
      else
      {
        this._model.setData(index, key, oArgs.newData);
        
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
    
    _persistHandler : function() {
      // save any open editors before we send the ajax request
      this.myDataTable.saveCellEditor();
      
      // Disable the save button until the request has been executed
      this.disableSaveButton();
    
      this._model.save();
    },   
    
  //calculate totals
    _sumColumn : function(oArgs) {

      if (oArgs.editor.getColumn().sum  && this._model.length() > 1) {
        var editor = oArgs.editor;
        var record = oeditor.getRecord();
        var key = editor.getColumn().key;
        var cellValue = record.getData(key);
        var lastIndex = this._model.length() - 1;
        var lastRecord = this.myDataTable.getRecord(lastIndex);
        var index = this.myDataTable.getRecordIndex(record);
        var dt = this.myDataTable;
        var manualLastRowData = this._model.getData(lastIndex, key);

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
            
    toggleSaveButton : function(disabled) {
      this.btnSaveRows.set("disabled", disabled);       
    },
    
    enableSaveButton : function() {
      this.toggleSaveButton(false);
    },
    
    disableSaveButton : function() {
      this.toggleSaveButton(true);
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
          var row_index = this.myDataTable.getRecordIndex(record);
          
          if (confirm(MDSS.Localized.Confirm_Delete_Row + ' ' + (row_index + 1) + '?')) {
            this._model.deleteRow(row_index);
          }
          break;

        default:
          this.myDataTable.onEventShowCellEditor(oArgs);
          break;
      }
    }    
  }

});


MojoGrid.createDataTable = function(data){
  var type = data.data_type.substr(7);
  var saveHandler = (data.saveHandler == null ? MDSS.DataGridModel.getDefaultSaveHandler(data.saveFunction, type) : data.saveHandler);
  
  var keys = [];
  
  for(var i in data.columnDefs) {
    var column = data.columnDefs[i];
    var key = column.key;

    if(key != "delete") {
      keys.push(key);
    }
  }

  var metadata = new MDSS.ModelMetadata({start:0, end:keys.length, type:type, keys:keys});
  
  var model = new MDSS.DataGridModel(metadata, data.rows, saveHandler);

  return new MDSS.DataGrid(model, data);
};

