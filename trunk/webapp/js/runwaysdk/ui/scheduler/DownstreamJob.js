#-------------------------------------------------------------------------------
# Copyright (C) 2018 IVCC
# 
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
#-------------------------------------------------------------------------------
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
      initialize : function(name, jobViewDTO)
      {
        this._jobViewDTO = jobViewDTO;
        
        this.$initialize("div", null, name, {});
      },
      accept : function(visitor)
      {
        visitor.visitDefaultInput(this);
        visitor._values.put("triggerOnFailure", this._tofCheckbox.isChecked());
      },
      getValue : function()
      {
        if (this._checkbox.isChecked())
        {
          return this._jobIdEl.getValue();
        }
        else
        {
          return "";
        }
      },
      setValue : function(val) {
        this._jobIdEl.setValue(val);
        
        if (val == null || val == "") {
          this.setEnabled(false);
        }
        else {
          this.setEnabled(true);
        }
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
          this.searchInput.setStyle("display", "inline");
          this.tofLabel.setStyle("display", "table-cell");
          this._tofCheckbox.setStyle("display", "inline-block");
        }
        else
        {
          this.searchInput.setStyle("display", "none");
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
        var label = this.getFactory().newElement("label", {innerHTML: this._jobViewDTO.getDownstreamJobMd().getDisplayLabel()});
        label.setStyle("display", "inline-block");
        enableDiv.appendChild(label);
        
        enableDiv.appendChild(this.getFactory().newElement("br"));
        
        // Search box
        this.searchInput = this.getFactory().newElement("input");
        enableDiv.appendChild(this.searchInput);
        
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
        this.tofLabel = this.getFactory().newElement("label", {innerHTML: this._jobViewDTO.getTriggerOnFailureMd().getDisplayLabel()});
        this.tofLabel.setStyle("vertical-align", "middle");
        enableDiv.appendChild(this.tofLabel);
        
        // Populate forms with existing data
        var downstreamJobId = this._jobViewDTO.getValue("downstreamJob");
        this.setValue(downstreamJobId);
        if (downstreamJobId != null && downstreamJobId != "")
        {
          this._tofCheckbox.setChecked(this._jobViewDTO.getValue("triggerOnFailure"));
          this.searchInput.setValue(this._jobViewDTO.getValue("downstreamJobDisplayLabel"));
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
