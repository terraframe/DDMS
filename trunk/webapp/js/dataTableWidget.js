var MojoGrid = YAHOO.namespace('MojoGrid');
MojoGrid.cellLock = false;
MojoGrid.limitTab = false;

Mojo.Meta.newClass('MDSS.ArrayMetadata', {
  Instance : {
    initialize : function(metadata) {
      this._metadata = metadata;
    },
    
    getMetadata : function() {
      return this._metadata;
    },
    
    getKeys : function() {
      var keys = new Array();
      
      for(var i in this._metadata) {
        var _keys = this._metadata[i].getKeys();

        keys = keys.concat(_keys);
      }     
      
      return keys;
    },
  }
});

Mojo.Meta.newClass('MDSS.CellValidator', {
  Static : {    
    validatePercentage : function(oData) {
      var number = oData * 1;
  
      if(Mojo.Util.isNumber(number)) {
        if(number >= 0 && number <= 100) {
          return oData;
        }
      }
  
      return undefined;
    }
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
  },
  
  Static : {
    init : function(array) {
      var metadata = [];
      
      for(var i in array) {
        var prop = array[i];
    
        if(prop.metadata == null){    
          metadata.push(new MDSS.ModelMetadata(prop));
        }
        else {
          var _metadata = MDSS.ModelMetadata.init(prop.metadata);
          metadata.push(new MDSS.ArrayMetadata(_metadata));
        }
      }
      
      return metadata;
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
      // IMPORTANT: It is assumed that the function only returns objects for the first data type
      var keys = this._metadata[0].getKeys();
      var length = this.length();
  
      // Refresh the values of all the columns in the first object.
      for( var i = 0; i < length; i++) {

        for(var j = 0; j < keys.length; j++) {
          var key = keys[j];
            
          var attributeName = (key.substr(0, 1).toLowerCase() + key.substr(1)).split('^^')[0];
          
          var attributeDTO = rows[i].getAttributeDTO(attributeName);
                    
          var value = ""; 
          
          if(attributeDTO instanceof com.runwaysdk.transport.attributes.AttributeEnumerationDTO) {
        // IMPORTANT: The datagrid can only support single select enumerations
            var enumNames = attributeDTO.getEnumNames();
            
            if(enumNames.length == 1) {
              value = enumNames[0];
            }
          }
          else {
            value = attributeDTO.getValue();
          }
          
          this.setData(i, key, value);
          
          this.fireEvent(new MDSS.Event(MDSS.Event.RELOAD_VALUE, {row:i, col:key, value:value}));
        }
      }
      
      this.fireEvent(new MDSS.Event(MDSS.Event.AFTER_SAVE, {}));
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

      var objects = this.getParameters();
          
      // Save the table
      this._saveHandler(request, objects);
    },
        
    getParameters : function() {
      var parameters = new Array();
      
      for (var m in this._metadata) {
        var objects = new Array();        
        for ( var r = 0; r < this._rows.length; r++) {
          var object = this.getParameter(this._metadata[m], r);
        
          objects.push(object);
        }
        
        parameters.push(objects)
      }
        
      return parameters;
    },
    
    getParameter : function(metadata, index) {          
      if(metadata instanceof MDSS.ArrayMetadata) {
        var array = [];
        var components = metadata.getMetadata();
    
        for(var c in components) {
          var object = this.getParameter(components[c], index);
      
          array.push(object);
        }
        
        return array;
      }
      else {
        var type = metadata.getType();       
        var start = metadata.getStart();
        var end = metadata.getEnd() + 1;
        var row = this._rows[index];
      
        var _contructor = Mojo.Meta.findClass(type);
        var object = new _contructor();
              
        for ( var i = start; i < end; i++) {
          var key = metadata.getKey(i-start);
          var value = row[key];

          var attribute = key.split('^^')[0];
          
          this._setObjectValue(object, attribute, value);
        }
        return object;
      }
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
      
      this.fireEvent(new MDSS.Event(MDSS.Event.AFTER_SET_DATA, {row:row, col:col, value:value}));
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
        var key = this._getIdKey();
        var id = this.getData(index, key);

        var request = new MDSS.Request( {
          id : id,
          index : index,
          that : this,
          onSuccess : function() {
            this.that.removeRow(this.index, this.id);
          }
        });

        if(id != null && id != "") {
          Mojo.Facade.deleteEntity(request, id);
        }
        else {
          this.removeRow(index, id);
        }
      }
    },
    
    _getIdKey : function() {
      for(i in this._metadata) {
        return this._metadata[i].getKey(0);
      }
      
      return null;
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
            
        parameters.unshift(request); // put the request as the first parameter
        
        // Invoke the save method
        saveMethod.apply(this, parameters);
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

      this.myDataSource = null;
      this.myDataTable = null;
      this.bReverseSorted = false;
      this.btnSaveRows = null;
      this.disableButton = !Mojo.Util.isBoolean(data.cleanDisable) ? true : data.cleanDisable;
      this.after_row_load = data.after_row_load;
      this._disabled = false;
      this._columnDefs = data.columnDefs;
      this._copyFromAbove = ((typeof data.copy_from_above === 'undefined') ? [] : data.copy_from_above);
      this._default = data.defaults;
      this._div = data.div_id;
      this._afterRowEdit = data.after_row_edit;
      this._doNotReload = (data.doNotReload != null ? data.doNotReload : []);

      // set the fields
      if (typeof data.fields === 'undefined') {
        this._fields = new Array();
    
        this._columnDefs.map(this._initializeField, this);
      }
      else {
        this._fields = data.fields;
      }
        
      this._dirty = false;
        
      // load the data
      this.myDataSource = new YAHOO.util.DataSource(this._model.getRows());
      this.myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
      this.myDataSource.responseSchema = {
        fields : this._fields
      };
        
      // Scrolling Data Table is slow, so we use regular data table if possible
      if(data.width){
        this.myDataTable = new YAHOO.widget.ScrollingDataTable(this._div, this._columnDefs, this.myDataSource, {width : data.width});
      }
      else {
        this.myDataTable = new YAHOO.widget.DataTable(this._div, this._columnDefs, this.myDataSource, {});
      }          
                             
      // the data comes from the server as ids, we need to set the labels      
      this._initializeRecords();
      
      if(this._div != null)
      {         
        this.myDataTable.set("selectionMode","singlecell");
          
        this.myDataTable.render();
          
        this.myDataTable.subscribe("columnSortEvent", this._trackReverseSorts);

        this.myDataTable.subscribe("cellMouseoverEvent", this._highlightEditableCell);

        this.myDataTable.subscribe("cellMouseoutEvent", this.myDataTable.onEventUnhighlightCell);

        this.myDataTable.subscribe("cellClickEvent", this.onCellClick, null, this);
         
        this.myDataTable.subscribe("editorKeydownEvent", this.editorKeyEvent, null, this);

        this.myDataTable.subscribe("tableKeyEvent", this.tableKeyEvent, null, this);

        this.myDataTable.subscribe("editorSaveEvent", this._saveHandler, null, this);
       
        this._setUpButtons(data);

        this._model.addListener(Mojo.Util.bind(this, this._modelEventHandler));
      }
      
      //set this so it accessable by other methods in the jsp
      this.myDataTable.dataGrid = this;          
    },
    
    _initializeField : function(c) {
      if(c.key && c.key != "delete") {
        this._fields.push(c.key);
      }         
      else if(c.children) {
        c.children.map(this._initializeField, this);
      }
    },
    
    disable : function() {
      this._disabled = true; 
      
      this.enableSaveButton();
    },
    
    enable : function() {
      this._disabled = false; 
    
      this.disableSaveButton();
    },
    
    _getDisableButton : function() {
      return this.disableButton;
    },
  
    _getLabelFromId : function(field, id) {
    
      var options = window[field + "Options"]
                          
      for(var i in options) {
        var option = options[i];
        
        if(option.value == id) {
          return option.label;
        }
      }
      
      return id;
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
      else if(event.getType() == MDSS.Event.AFTER_SAVE) {
        this.success();
      }
      else if(event.getType() == MDSS.Event.AFTER_DELETE) {
        var map = event.getValue();
        
        this.myDataTable.deleteRow(map.index);      
      }
    },
    
    getDataTable : function () {
      return this.myDataTable;
    },
    
    getModel : function() {
      return this._model;
    },
    
    getColumnDefinitions : function() {
      return this._columnDefs;
    },
    
    getParameters : function() {
      return this.getModel().getParameters();
    },
    
    setData : function(row, col, value) {
      this._model.setData(row, col, value);
      
      var column = this.myDataTable.getColumn(col);
      var record = this.myDataTable.getRecord(row);
      
      if(column != null && record != null) {
        var editor = column.editor;
      
        if (editor && editor instanceof YAHOO.widget.DropdownCellEditor){           
          //data comes in as value instead of label, so we fix this.    
          for( var i = 0; i < editor.dropdownOptions.length; i++) {
            var optionValue = editor.dropdownOptions[i].value;
            var label = editor.dropdownOptions[i].label;
            
            if (value === optionValue) {
              this.getDataTable().updateCell(record, col, label);
            }
          }
        }      
        else if (editor && editor instanceof YAHOO.widget.DateCellEditor) {
          var date = MDSS.Calendar.parseDate(record.getData(field.key));
          this.getDataTable().updateCell(record, field.key, date);
        }
        else {
          this.getDataTable().updateCell(record, col, value);
        }
      }
    },
    
    getData : function(row, col) {
      return this._model.getData(row, col);    
    },
    
    updateValue : function(row, col, value) {
      if(this._doNotReload.indexOf(col) == -1) {
        var record = this.myDataTable.getRecord(row);
                      
        var column = this.myDataTable.getColumn(col);

        // Do not update the value of the last row on summed columns
        if(column.hidden) {
          record.setData(col, value);        
        }
        else if (!(column.sum  && this._model.length() > row)) {
          var editor = column.editor;
        
          // Do not update the value for drop down or ontology editors
          if (!editor || ! (editor instanceof YAHOO.widget.DropdownCellEditor || editor instanceof YAHOO.widget.OntologyTermEditor)) {
            record.setData(col, value);                  
          }
        }
      }
    },
    
    success : function() {
      this._dirty = false;
                 
      this.toggleSaveButton(this._getDisableButton());
                
      this.myDataTable.render();

      if (this.myDataTable.after_save) {
        this.myDataTable.after_save();
      }
                
      this.myDataTable.fireEvent("tableSaveEvent");    
    },
    
    save : function() {
      // save any open editors before we send the ajax request
      this.myDataTable.saveCellEditor();
        
      // Disable the save button until the request has been executed
      this.disableSaveButton();
    
      this.getModel().save();
    },
    
    hasChanges : function() {
      return this._dirty;
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
        
        this._copyFromAbove.map( function(field) {
          new_data_row[field] = this._model.getData(last_row_index, field);
          
          var label = this.myDataTable.getRecord(last_row_index).getData(field);
          new_label_row[field] = label;
        },this);
      }
      
      this._model.addRow(new_data_row);

      this.myDataTable.addRow(new_label_row);
      
      this._dirty = true;
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
        
      var keys = Mojo.Util.getKeys(this._default);
        
      for(var i = 0; i < keys.length; i++) {
        var key = keys[i];
          
        var value = ((this._default[key].value != null) ? this._default[key].value : this._default[key]) ;
          
        row[key] = value;
      }
        
      return row;
    },
      
    getDefaultLabels : function() {
      var row = new Object();
          
      var keys = Mojo.Util.getKeys(this._default);
          
      for(var i = 0; i < keys.length; i++) {
        var key = keys[i];
            
        var value = ((this._default[key].value != null) ? this._default[key].value : this._default[key]) ;
        var label = this._default[key].label;
            
        row[key] = ((label != null ) ? label : value);
      }
        
      return row;
    },    
    
    _initializeRecords : function() {
      var records = this.myDataTable.getRecordSet().getRecords()
                  
      for(var index = 0; index < records.length; index++) {
        var record = records[index];
        
        var func = Mojo.Util.bind(this, this._initializeRecordColumn, record, index);
        
        Mojo.Iter.map(this._columnDefs, func, this);
        
        if (this.after_row_load) {
          this.after_row_load(record);
        }
      }
    },
    
    _initializeRecordColumn : function(record, index, field) {
      if(field.children != null) {
        for(var c in field.children) {
          var child = field.children[c];
          
          this._initializeRecordColumn(record, index, child);
        }
      }
      else {
        var initialized = this._initializeLabeledValue(index, field.key, record);
        
        if(!initialized) {
      
          var editor = field.editor;
        
          if(!editor) {
            var column = this.myDataTable.getColumn(field.key);
           
            editor = column.editor;
          }
        
          if (field.save_as_id) {
            var label = this._getLabelFromId(field.key, record.getData(field.key));
            record.setData(field.key, label);
          }        
          else if(editor != null && editor instanceof YAHOO.widget.DateCellEditor) {
            var date = MDSS.Calendar.parseDate(record.getData(field.key));

            this.myDataTable.updateCell(record, field.key, date);
          }
          else if (editor instanceof YAHOO.widget.DropdownCellEditor){           
            //data comes in as value instead of label, so we fix this.
        
            for( var i = 0; i < editor.dropdownOptions.length; i++) {
              var recordValue = record.getData(field.key);
             
              var optionValue = editor.dropdownOptions[i].value;
              var label = editor.dropdownOptions[i].label;
                
              if (recordValue === optionValue){
                record.setData(field.key, label);
              }
            }
          }          
        }
      }
    },
    
    _initializeLabeledValue : function(row, col, record) {
      var data = record.getData(col);
      
      if(data) {
        var split = data.split('^^^^');
        
        if(split.length == 2) {
          var id = split[1];
          var displayLabel = split[0];
          
          if(this._model.hasRow(row)) {
            this._model.setData(row, col, id);
          
            record.setData(col, displayLabel);
          }
        
          return true;
        }
      }
      
      return false;
    },
    
    _setUpButtons : function(data) {
      if (YAHOO.util.Dom.get('buttons') === null) {
        var tableDiv = YAHOO.util.Dom.get(this._div);
        var buttons = document.createElement('span');
        
        if(!data.saveLabelKey){
          data.saveLabelKey = 'Save_Rows_To_DB';
        }
        
        buttons.id = this._div + 'Buttons';
        YAHOO.util.Dom.addClass(buttons, 'noprint');
        YAHOO.util.Dom.addClass(buttons, 'dataTableButtons');
        buttons.innerHTML = '';

        if (data.addButton !== false) {
          buttons.innerHTML += '<button type="button" id="' + this._div + 'Addrow">' + MDSS.localize('New_Row') + '</button>';
        }

        if (data.saveButton !== false) {
          buttons.innerHTML += '<button type="button" id="' + this._div + 'Saverows">' + MDSS.localize(data.saveLabelKey) + '</button>';
        }

        if (data.excelButtons !== false) {
          buttons.innerHTML += '<form method="get" action="excelimport" style="display: inline;"><input type="hidden" name="excelType" value="' + data.excelType + '" /><span class="yui-button yui-push-button"> <span class="first-child"><button type="submit">' + MDSS.localize('Excel_Import_Header') + '</button></span></span></form>';
          buttons.innerHTML += '<form method="post" action="excelexport" style="display: inline;"><input type="hidden" name="excelType" value="' + data.excelType + '" /><span class="yui-button yui-push-button"> <span class="first-child"><button type="submit">' + MDSS.localize('Excel_Export_Header') + '</button></span></span></form>';
        }

        // Setup the custom buttons
        if(Mojo.Util.isArray(data.customButtons)) {
          for(var i = 0; i < data.customButtons.length; i++) {
            var config = data.customButtons[i];
            
            // Create the button and add it next to the previous button 
            buttons.innerHTML += '<button type="button" id="' + this._div + '.' + config.id + '">' + config.label + '</button>';
          }
        }

        YAHOO.util.Dom.insertAfter(buttons, tableDiv);
      }
    
      if (YAHOO.util.Dom.get(this._div + 'Addrow')) {
        var btnAddRow = new YAHOO.widget.Button(this._div + "Addrow");
        btnAddRow.on("click", this.addRow, null, this);
      } 
    
      if (YAHOO.util.Dom.get(this._div + 'Saverows')) {
        // set up the button that saves the rows to the db
        this.btnSaveRows = new YAHOO.widget.Button(this._div + "Saverows");
        this.btnSaveRows.on("click", this.save, null, this);
        this.toggleSaveButton(this._getDisableButton());
      }
  
      // Setup the custom button actions
      if(Mojo.Util.isArray(data.customButtons)) {
        for(var i = 0; i < data.customButtons.length; i++) {
          var config = data.customButtons[i];
            
          // set up the button that saves the rows to the db
          var customButton = new YAHOO.widget.Button(this._div + "." + config.id);
          customButton.on("click", config.action, null, this);
        }
      }     
    },
    
    _getNextCell : function(cell, e) {
      var nKey = e.keyCode;
      
      if(nKey === 13 )//Enter
      {
        if(e.shiftKey) {
          return this.myDataTable.getAboveTdEl(cell);
        }
        else { 
          return this.myDataTable.getBelowTdEl(cell);
        }
      }
      else if(nKey === 9) //Tab
      {
        if (e.shiftKey) {
          return this.myDataTable.getPreviousTdEl(cell);
        }
        else {
          return this.myDataTable.getNextTdEl(cell);
        }
      }
    },
    
    findNext : function(cell, e, includeCurrent) {
      var nextCell = (includeCurrent ? cell : this._getNextCell(cell, e));
 
      while (nextCell !== null && (this.myDataTable.getColumn(nextCell).editor === null || this.myDataTable.getColumn(nextCell).hidden === true)) {
        nextCell = this._getNextCell(nextCell, e);
      }
      
      return nextCell;
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
          var editor = this.myDataTable.getCellEditor();
          var cell = editor.getTdEl();
          
          var nextCell = this.findNext(cell, e, false);

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
    
    tableKeyEvent : function(obj) {    
      var e = obj.event;
    
      // 9 = tab, 13 = enter
      if (e.keyCode === 9  || e.keyCode === 13) {    
        e.preventDefault();
        YAHOO.util.Event.stopEvent(e);
    
        if(MojoGrid.cellLock) {
          // Avoids multiple Tabs in rapid succession.
          return;
        }
        else {
          MojoGrid.cellLock = true;
        }
    
        try {
          var cell = this.getDataTable().getLastSelectedCell();
    
          var nextCell = this.findNext(cell, e, true);
    
          if (nextCell) {
            this.myDataTable.unselectAllCells();
            this.myDataTable.showCellEditor(nextCell);
            this.myDataTable.selectCell(nextCell);
          }
        }
        finally {
          MojoGrid.cellLock = false;
        }
      }
    },

    _saveHandler : function(oArgs) {

      // Get the current selected cell
      var cell = this.getDataTable().getLastSelectedCell();
      
      this.saveCell(oArgs);
   
      // Refocus on the selected cell
      this.getDataTable().focus();
      this.getDataTable().selectCell(cell);
    },

    // Save edits back to the original data array
    saveCell : function(oArgs) {

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

      this._dirty = true;
      
      this.enableSaveButton();
      
      if (this._afterRowEdit) {
        this._afterRowEdit(record);
      }

      this._sumColumn.apply(this,[oArgs]);

      //this.myDataTable.unselectCell(editor.getTdEl());
      //YAHOO.log("Saved Cell:" + editor._oColumn.label, "warn", "Widget");
    },
    
    addListener : function(listener) {
      this._model.addListener(listener);
    },
    
  //calculate totals
    _sumColumn : function(oArgs) {

      if (oArgs.editor.getColumn().sum  && this._model.length() > 1) {
        var editor = oArgs.editor;
        var record = editor.getRecord();
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
        
        var newData = parseFloat(oArgs.newData);
        newData = newData || 0;
        
        var oldData = parseFloat(oArgs.oldData);
        oldData = oldData || 0;

        var oldTotal = parseFloat(lastRecord.getData(editor.getColumn().key));
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
          var x = parseFloat(row.getData(editor.getColumn().key));
          if (x && dt.getRecordIndex(row) !== lastIndex){
            sum += x;
          }
        });
                
        
        if (parseFloat(lastRecord.getData(editor.getColumn().key)) != sum) {
          YAHOO.util.Dom.addClass(lastTd, "dataTableSumError");
        } else {
          YAHOO.util.Dom.removeClass(lastTd, "dataTableSumError");
        }

      }      
    },
            
    toggleSaveButton : function(disabled) {
      if(this.btnSaveRows != null) {
        this.btnSaveRows.set("disabled", (this._disabled || disabled));
      }
    },
    
    enableSaveButton : function() {
      this.toggleSaveButton(false);
    },
    
    disableSaveButton : function() {
      this.toggleSaveButton(true);
    },
    
    refresh : function() {
      this.getDataTable().render();
    },
    
    onCellClick : function(oArgs) {
      var target = oArgs.target;
      //this.myDataTable.focusTbodyEl();
      this.myDataTable.unselectAllCells();
      this.myDataTable.selectCell(target);
      var column = this.myDataTable.getColumn(target);

      // don't allow editing if column is hidden
      if(column == null || column.hidden)
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

  var metadata = new MDSS.ModelMetadata.init([{start:0, end:(keys.length - 1), type:type, keys:keys}]);
  
  var model = new MDSS.DataGridModel(metadata, data.rows, saveHandler);

  return new MDSS.DataGrid(model, data);
};

