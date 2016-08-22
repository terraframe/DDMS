
(function(){

  var Forms = Mojo.Meta.alias(Mojo.RW_PACKAGE+"*");
  
  MDSS.jobSearch = function(config) {
    var searchEl = Mojo.Util.isString(config.search) ? document.getElementById(config.search) : config.search;
    var concreteEl = Mojo.Util.isString(config.concrete) ? document.getElementById(config.concrete) : config.concrete;
  
    var listFunction = function(valueObject) {    
      var jobId = valueObject.getValue(Mojo.$.com.runwaysdk.system.scheduler.ExecutableJob.JOBID);    
  
      return jobId;
    };
  
    var idFunction = function(valueObject) {
      return valueObject.getValue("id");    
    };
  
    var displayFunction = function(valueObject) {    
      return valueObject.getValue(Mojo.$.com.runwaysdk.system.scheduler.ExecutableJob.JOBID);    
    };
    
    var searchFunction = Mojo.$.dss.vector.solutions.report.SchedulerUtil.searchByValueQuery;
    
    var selectEventHandler = function(selected) {
      if(config.handler != null) {
        config.handler.handleEvent(selected);
      }
    };
    
    var search = new MDSS.GenericSearch(searchEl, concreteEl, listFunction, displayFunction, idFunction, searchFunction, selectEventHandler);
    
    search.addParameter(config.type);
      
    var checkManualEntry = function() {
      var request = new MDSS.Request({
        searchEl : searchEl,
        concreteEl : concreteEl,
        onSend: function(){},
        onComplete: function(){},
        onFailure: function(){},
        onSuccess : function(result){
          if(result !== null) {
            searchEl.value = result.getJobId();
            concreteEl.value = result.getId();
          }
        }
      });
    }
  
    YAHOO.util.Event.on(searchEl, 'blur', checkManualEntry, null, null);     
  }
  
  var DownstreamJobInput = Mojo.Meta.newClass('com.runwaysdk.ui.DownstreamJobInput', {
    Extends : Forms.FormInput,
    Instance : {
      initialize : function(name, config)
      {
        this._config = config || {};
        
        this.searchInput = this.getFactory().newElement("input");
        
        this.$initialize("div", null, name, this._config);
      },
      accept : function(visitor)
      {
        visitor.visitDefaultInput(this);
        visitor._values.put("triggerOnFailure", this._tofCheckbox.isChecked());
      },
      getValue : function()
      {
        return this._jobIdEl.getValue();
      },
      setValue : function(val) {
        this.searchInput.setValue(val);
      },
      
      _onClickEnableCheckbox : function(checkEvent)
      {
        this.setEnabled(checkEvent.getCheckBox().isChecked());
      },
      
      setEnabled : function(enabled)
      {
        this._enabled = enabled;
        
        this._checkbox.setChecked(this._enabled, false);
        
        if (this._enabled)
        {
          this.searchContainer.setStyle("display", "inline");
          this.tofLabel.setStyle("display", "table-cell");
          this._tofCheckbox.setStyle("display", "inline-block");
        }
        else
        {
          this.searchContainer.setStyle("display", "none");
          this.tofLabel.setStyle("display", "none");
          this._tofCheckbox.setStyle("display", "none");
        }
      },
      
      _writeHtml : function() {
        // Enable/disable functionality
        var enableDiv = this.getFactory().newElement("div", null, {display: "inline"});
        this.appendChild(enableDiv);
        this._checkbox = this.getFactory().newCheckBox();
        this._checkbox.addOnCheckListener(Mojo.Util.bind(this, this._onClickEnableCheckbox));
        this._checkbox.setStyle("display", "inline-block");
        enableDiv.appendChild(this._checkbox);
        
        // Downstream job label
        var label = this.getFactory().newElement("label", {innerHTML: this._config.localizedLabel});
        label.setStyle("display", "inline-block");
        enableDiv.appendChild(label);
        
        enableDiv.appendChild(this.getFactory().newElement("br"));
        
        // Search box
        this.searchContainer = this.getFactory().newElement("div");
        this.searchContainer.appendChild(this.searchInput);
        enableDiv.appendChild(this.searchContainer);
        
        // Hidden element that will store the id of the selected job
        this._jobIdEl = this.getFactory().newElement("div");
        this._jobIdEl.setStyle("display", "none");
        enableDiv.appendChild(this._jobIdEl);
        
        enableDiv.appendChild(this.getFactory().newElement("br"));
        
        // Trigger on failure checkbox
        this._tofCheckbox = this.getFactory().newCheckBox();
        this._tofCheckbox.setStyle("float", "none");
        this._tofCheckbox.setStyle("margin-left", "30px");
        this._tofCheckbox.setStyle("margin-top", "5px");
        this._tofCheckbox.getRawEl().style.setProperty("margin-top", "5px", "important");
        enableDiv.appendChild(this._tofCheckbox);
        
        // TOF label
        this.tofLabel = this.getFactory().newElement("label", {innerHTML: this._config.tofLabel});
        this.tofLabel.setStyle("vertical-align", "middle");
        enableDiv.appendChild(this.tofLabel);
        
        if (this.getValue() == null || this.getValue() == "") {
          this.setEnabled(false);
        }
        else {
          this.setEnabled(true);
        }
      },
      render: function(p) {
        this._writeHtml();
        this.$render(p);
        MDSS.jobSearch({search:this.searchInput.getRawEl(), concrete:this._jobIdEl.getRawEl(), handler:MDSS.ValidationBridge.getInstance(), type:'com.runwaysdk.system.scheduler.ExecutableJob'});
      }
    }
  });
  
  Mojo.Meta.newClass("MDSS.ValidationBridge", {

    IsSingleton : true,
    
    Instance : {
    
      initialize : function()
      {
      },
      
      setHandler : function(handler) {
        this._handler = handler;
      },

      handleEvent : function(evt){      
        if(this._handler != null)
        {
//          if(evt instanceof dss.vector.solutions.ontology.TermSelectedEvent)
//          {        
//            if(!evt.getOnOpen())
//            {
//              this._handler(evt);          
//            }
//          }
//          else
//          {        
//            this._handler(evt);
//          }
        }
      }    
    },
  });

})();
