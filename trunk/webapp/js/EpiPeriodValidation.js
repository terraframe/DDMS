MDSS.validateEpiDate = function(button, geoId, yearSearch, period, periodType){
  var year = yearSearch.getDisplayElement();

  var keyHandler = function(e, obj) {
    if ( this.e && e.keyCode !== 9) {
      return;
    }
    else {
      validate();
    }
  };
  
  var validate = function() {        
    button.disabled=true;
    
    var selectedType;

    for(var i=0; i < periodType.length; i++)
    {
      var radio = periodType[i];

      if(radio.checked)
      {
        selectedType = radio.value;
      }
    }

    var re = /^[0-9]+$/;

    if ( !re.test(year.value) || !re.test(period.value)) {
      return;
    }
    
    if(geoId.value != '' && year.value != '' && period.value != '' && selectedType != '') {
      var request = new MDSS.Request({
        onSuccess : function(){
          button.disabled=false; 
          if(this.e && e.keyCode === 9 ){
            button.focus();
          }
        },
        onFailure : function(e){
          MDSS.Calendar.addError(geoId,e.getLocalizedMessage());            
        },
        onProblemExceptionDTO : function(e){
          var problems = e.getProblems();

          for each (p in problems) {
            if(p.getType() == "dss.vector.solutions.FuturePeriodProblem") {
              MDSS.Calendar.addError(year,p.getLocalizedMessage());
            }
            else {
              MDSS.Calendar.addError(period,p.getLocalizedMessage());
            }
          }
        }
      });

      MDSS.Calendar.removeError(geoId);
      MDSS.Calendar.removeError(year);
      MDSS.Calendar.removeError(period);

      Mojo.$.dss.vector.solutions.surveillance.AggregatedCaseView.validateSearchCriteria(request, geoId.value, selectedType, parseInt(period.value), parseInt(year.value));
    }
  }
  
  Mojo.GLOBAL.onValidGeoEntitySelected = function() {
    validate();  
  }
  
  var disableButton = function() {
    button.disabled=true;
  };
  
  // Initially disable the search button
  disableButton();


  YAHOO.util.Event.on(geoId, 'blur', validate);
  YAHOO.util.Event.on(periodType, 'click', validate);
  YAHOO.util.Event.on(period, 'blur', validate);
  YAHOO.util.Event.on(year, 'blur', validate);
}