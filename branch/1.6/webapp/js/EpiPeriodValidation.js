Mojo.Meta.newClass('MDSS.SearchValidator', {
  Instance:{
    initialize : function(searchType, prop) {
      this._searchDate = document.getElementById(searchType + '.positive');
      this._searchPeriod = document.getElementById(searchType + '.negative');
      
      this._dateValidator = new MDSS.DateSearchValidator(prop);
      this._epiValidator = new MDSS.EpiSearchValidator(prop);
      
      this.validate();
      
      YAHOO.util.Event.on(this._searchDate, 'change', this.validate, this, this);    
      YAHOO.util.Event.on(this._searchPeriod, 'change', this.validate, this, this);    
    },
    
    validate : function() {
      if(this._searchDate.checked == true) {
        this._dateValidator.validate();          
      }
      else {
        this._epiValidator.validate();  
      }            
    },
    
    eventHandler : function(e) {
      if(e.getType() == MDSS.Event.AFTER_SELECTION) {
        this.validate(); 
      }
    }
  }
});


Mojo.Meta.newClass('MDSS.DateSearchValidator', {
  Instance:{
    initialize: function(prop) {
      this._button = Mojo.Util.isString(prop.button) ? document.getElementById(prop.button) : prop.button;
      this._geoId = Mojo.Util.isString(prop.geoId) ? document.getElementById(prop.geoId) : prop.geoId;
      this._startDate = Mojo.Util.isString(prop.startDate) ? document.getElementById(prop.startDate) : prop.startDate;
      this._endDate = Mojo.Util.isString(prop.endDate) ? document.getElementById(prop.endDate) : prop.endDate;
      this._startDateValue = MDSS.Calendar.parseJavaFormatDate(this._startDate.value);
      this._endDateValue = MDSS.Calendar.parseJavaFormatDate(this._endDate.value);

      
      YAHOO.util.Event.on(this._geoId, 'blur', this.validate, this, this);
      YAHOO.util.Event.on(this._startDate, 'blur', this.validate, this, this);
      YAHOO.util.Event.on(this._endDate, 'blur', this.validate, this, this);
      YAHOO.util.Event.on(this._startDate, 'keydown', this.keyHandler, this, this);
      YAHOO.util.Event.on(this._endDate, 'keydown', this.keyHandler, this, this);      
    },

    disableButton : function() {
      this._button.disabled = true;
    },
      
    enableButton : function() {
      this._button.disabled = false;
    },      
    
    keyHandler : function(e) {
      if (e.keyCode == 9 || e.keyCode == 39 || e.keyCode == 37) {
        this.validateButton();
      }
    },
    
    validateButton : function() {
      this.disableButton();
      
      if(this._startDate.value != '' && this._endDate.value != '')
      {
        var startDate = MDSS.Calendar.parseJavaFormatDate(this._startDate.value);
        var endDate = MDSS.Calendar.parseJavaFormatDate(this._endDate.value);
        
        if(startDate instanceof Date && endDate instanceof Date && this._geoId.value != ''){
          this.enableButton();
        }
        
        this._startDateValue = startDate;
        this._endDateValue = endDate;
      }      
    },
    
    validate : function() {
      this.disableButton();
      
      if(this._startDate.value != '' && this._endDate.value != '')
      {
        var startDate = MDSS.Calendar.parseJavaFormatDate(this._startDate.value);
        var endDate = MDSS.Calendar.parseJavaFormatDate(this._endDate.value);

        if(startDate instanceof Date && endDate instanceof Date) {
          if(startDate > endDate) {
        	  if (this._startDateValue.toString() != startDate.toString() || this._endDateValue.toString() != endDate.toString()) {
        		  alert(MDSS.localize('Invalid_Dates'));
        	  }
          }
          else if(this._geoId.value != ''){
            this.enableButton();
          }
        }
        
        this._startDateValue = startDate;
        this._endDateValue = endDate;
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
      
      YAHOO.util.Event.on(this._geoId, 'blur', this.validate, this, this);
      YAHOO.util.Event.on(this._periodType, 'change', this.validate, this, this);
      YAHOO.util.Event.on(this._period, 'keyup', this.keyUpHandler, this, this);
      YAHOO.util.Event.on(this._year, 'keyup', this.keyUpHandler, this, this);
      YAHOO.util.Event.on(this._startDate, 'keydown', this.keyDownHandler, this, this);
      YAHOO.util.Event.on(this._endDate, 'keydown', this.keyDownHandler, this, this);      
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
    
    keyDownHandler : function(e) {
      if (e.keyCode == 9 || e.keyCode == 39 || e.keyCode == 37) {
        this.validate();
      }
    },
    
    keyUpHandler : function(e) {
      // Do not do anything on arrow keys
      if (e.keyCode < 37 || e.keyCode > 40) {
        this.validate();
      }
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

            for (var i=0; i<problems.length; i++){
              var p = problems[i];
              if(p.getType() == "dss.vector.solutions.FuturePeriodProblem") {
                MDSS.Calendar.addError(this.that._year,p.getLocalizedMessage());
              }
              else {
                MDSS.Calendar.addError(this.that._period,p.getLocalizedMessage());
              }
            }
          }
        });
                  
        var periodValue = MDSS.parseNumber(this._period.value, true);
        var yearValue = MDSS.parseNumber(this._year.value, true);
        var selectedType = this._getSelectedEpiType();

        Mojo.$.dss.vector.solutions.surveillance.AggregatedCaseView.validateSearchCriteria(request, this._geoId.value, selectedType, periodValue, yearValue);    
      }
    }
  }
});