MDSS.validateEpiDate = function(button, geoId, year, period, periodType){
  var validate = function(e, obj){
	button.disabled=true;
	  
    var selectedType;

    for(var i=0; i<periodType.length; i++)
    {
      var radio = periodType[i];

      if(radio.checked)
      {
        selectedType = radio.value;
      }
    }

	var re = /^[0-9]+$/;

	if ( !re.test(year.value) || !re.test(period.value))
	{
	  return;
	}

    if(geoId.value != '' && year.value != '' && period.value != '' && selectedType != '')
    {
      var request = new MDSS.Request({
          onSend: function(){},
          onComplete: function(){},
          onSuccess : function(){
        	  button.disabled=false;              
          },
          onFailure : function(e){
          	MDSS.Calendar.addError(geoId,e.getLocalizedMessage());            
          },
          onProblemExceptionDTO : function(e){
              var problems = e.getProblems();
    		  for each (p in problems)
    		  {
        		if(p.getType() == "dss.vector.solutions.FuturePeriodProblem")
            	{
                	MDSS.Calendar.addError(year,p.getLocalizedMessage());
        		}
        		else
        		{
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

  onValidGeoEntitySelected = function() {
	  validate();	  
  }

  // Initially disable the search button
  button.disabled=true;

  YAHOO.util.Event.on(geoId, 'blur', validate);
  YAHOO.util.Event.on(periodType, 'click', validate);
  YAHOO.util.Event.on(period, 'blur', validate);
  YAHOO.util.Event.on(year, 'blur', validate);
}