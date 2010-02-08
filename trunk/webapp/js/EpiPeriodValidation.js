
Mojo.Meta.newClass('MDSS.DateSearchValidator', {
  Instance:{
    initialize: function(prop) {
      this._button = Mojo.Util.isString(prop.button) ? document.getElementById(prop.button) : prop.button;
      this._geoId = Mojo.Util.isString(prop.geoId) ? document.getElementById(prop.geoId) : prop.geoId;
      this._startDate = Mojo.Util.isString(prop.startDate) ? document.getElementById(prop.startDate) : prop.startDate;
      this._endDate = Mojo.Util.isString(prop.endDate) ? document.getElementById(prop.endDate) : prop.endDate;	
      
      YAHOO.util.Event.on(this._geoId, 'blur', this.validate, this, this);
      YAHOO.util.Event.on(this._startDate, 'blur', this.validate, this, this);
      YAHOO.util.Event.on(this._endDate, 'blur', this.validate, this, this);
      YAHOO.util.Event.on(this._startDate, 'keyup', this.validate, this, this);
      YAHOO.util.Event.on(this._endDate, 'keyup', this.validate, this, this);
    },

    disableButton : function() {
      this._button.disabled = true;
    },
      
    enableButton : function() {
      this._button.disabled = false;
    },      
    
    validate : function() {
      this.disableButton();
      
      if(this._startDate.value != '' && this._endDate.value != '')
      {
        var startDate = MDSS.Calendar.parseDate(this._startDate.value);
        var endDate = MDSS.Calendar.parseDate(this._endDate.value);
        
        if(startDate instanceof Date && endDate instanceof Date) {
          if(startDate > endDate) {
            alert(MDSS.localize('Invalid_Dates'));
          }
          else if(this._geoId.value != ''){
            this.enableButton();
          }
        }        	
      }
    }
  }
});

Mojo.Meta.newClass('MDSS.EpiSearchValidator', {
  Instance:{
    initialize: function(prop) {
      this._button = Mojo.Util.isString(prop.button) ? document.getElementById(prop.button) : prop.button;
      this._geoId = Mojo.Util.isString(prop.geoId) ? document.getElementById(prop.geoId) : prop.geoId;
      this._year = Mojo.Util.isString(prop.year) ? document.getElementById(prop.year) : prop.year;
      this._period = Mojo.Util.isString(prop.period) ? document.getElementById(prop.period) : prop.period;
      this._periodType = Mojo.Util.isString(prop.periodType) ? YAHOO.util.Selector.query('.' + prop.periodType) : prop.periodType;
      
      // Initially disable the search button
      this.disableButton();

      YAHOO.util.Event.on(this._geoId, 'blur', this.validate, this, this);
      YAHOO.util.Event.on(this._periodType, 'click', this.validate, this, this);
      YAHOO.util.Event.on(this._period, 'keyup', this.validate, this, this);
      YAHOO.util.Event.on(this._year, 'keyup', this.validate, this, this);
    },
    
    disableButton : function() {
      this._button.disabled = true;
    },
    
    enableButton : function() {
      this._button.disabled = false;
    },
    
    _hasValidEpiInput : function() {
      var re = /^[0-9]+$/;

      if ( !re.test(this._year.value) || !re.test(this._period.value)) {
        return false;
      }
          
      var yearValue = this._year.value;
      var selectedType = this._getSelectedEpiType();
      
      var hasGeoEntity = (this._geoId.value != '');
      var hasYear = (yearValue != '' && yearValue.length == 4);
      var hasPeriod = (this._period.value != '');
      var hasPeriodType = (selectedType != '');
          
      return ( hasGeoEntity && hasYear && hasPeriod && hasPeriodType );	
    },
    
    _getSelectedEpiType : function() {
      for(var i=0; i < this._periodType.length; i++) {
        var radio = this._periodType[i];
            
        if(radio.checked) {
          return radio.value;
        }
      }
      
      return '';
    },
    
    _clearMessages : function() {
      MDSS.Calendar.removeError(this._geoId);
      MDSS.Calendar.removeError(this._year);
      MDSS.Calendar.removeError(this._period);   	
    },
    
    validate : function() {
      this.disableButton();
                
      if(this._hasValidEpiInput()) {
        var request = new MDSS.Request({
          that : this,
          onSuccess : function(){
        	this.that._clearMessages();
            this.that.enableButton();
            
            if(this.e && e.keyCode === 9 ){
              this.that._button.focus();
            }
          },
          onFailure : function(e){
        	this.that._clearMessages();
        	
            MDSS.Calendar.addError(this.that._geoId,e.getLocalizedMessage());            
          },
          onProblemExceptionDTO : function(e){
          	this.that._clearMessages();        	
        	  
            var problems = e.getProblems();

            for each (p in problems) {
              if(p.getType() == "dss.vector.solutions.FuturePeriodProblem") {
                MDSS.Calendar.addError(this.that._year,p.getLocalizedMessage());
              }
              else {
                MDSS.Calendar.addError(this.that._period,p.getLocalizedMessage());
              }
            }
          }
        });
                  
        var periodValue = parseInt(this._period.value);
        var yearValue = parseInt(this._year.value);
        var selectedType = this._getSelectedEpiType();

        Mojo.$.dss.vector.solutions.surveillance.AggregatedCaseView.validateSearchCriteria(request, this._geoId.value, selectedType, periodValue, yearValue);    
      }
    }
  }
});