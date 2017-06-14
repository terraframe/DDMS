Mojo.Meta.newClass('MDSS.QueryAggregatedCases', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

  		this._groupByClass = dss.vector.solutions.surveillance.AggregatedCase;
  		this._mainQueryClass = this._groupByClass.CLASS;
	
      this._larvacide = new this._groupByClass();
      this._dateAttribute = new MDSS.QueryXML.Attribute(this._groupByClass.CLASS, this._groupByClass.STARTDATE, this._groupByClass.STARTDATE);
      
      var startDateAttr = new MDSS.QueryXML.Attribute(this._groupByClass.CLASS, this._groupByClass.STARTDATE, this._groupByClass.STARTDATE);
      this._startDateSelectable = new MDSS.QueryXML.Selectable(startDateAttr);
      
      var endDateAttr = new MDSS.QueryXML.Attribute(this._groupByClass.CLASS, this._groupByClass.ENDDATE, this._groupByClass.ENDDATE);
      this._endDateSelectable = new MDSS.QueryXML.Selectable(endDateAttr);

      this._commonQueryClasses = [
                                  this._groupByClass.CLASS,
                                  ];

      this._exclusionClasses = [];
      

      this._geoEntityAttribs = [
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,
                               display : com.runwaysdk.Localize.get("Geo_Entity")
                             },
                             
                           ];
      
      this._queryType = this._mainQueryClass;

      this.$initialize(selectableGroups, queryList);   
 
      var picker = this.getGeoPicker();      
      picker.setPolitical(true);
      picker.setSprayTargetAllowed(false);
      picker.addExtraUniversal('dss.vector.solutions.geo.generated.HealthFacility');

    },

    /**
     * Either calendar year or transmission season must be checked when population or incidence calculations are checked.
     */
    ensureDateGroupChecked : function(targets, dateGroups, triggerId, independents)
    {
      var dategroup_year = 'DATEGROUP_YEAR'; // FIXME should be a constant
      var dategroup_season = 'DATEGROUP_SEASON'; // FIXME should be a constant
      
      // They clicked on either population or incidence. Ensure that a date is also selected.
      if(targets.contains(triggerId) && document.getElementById(triggerId).checked)
      {
        // if no date-groups are selected automatically group by calendar year
        if(this._dateGroupSelectables[dategroup_year] == null && this._dateGroupSelectables[dategroup_season] == null)
        {
          document.getElementById(dategroup_year.toLowerCase()).click();
        }
        
        // always snap to calendar year
        var dateGroup = this._queryPanel.getDateGroupBy();
        
        dateGroup.value = dategroup_year;
        var option = dateGroup.options[dateGroup.selectedIndex];
        this._fireClickOnOption(option);
      }
      // They unchecked a date. If population or incidence is checked (and no other dates are checked) uncheck it.
      else if(dateGroups.contains(triggerId) && this._dateGroupSelectables[dategroup_year] == null && this._dateGroupSelectables[dategroup_season] == null)
      {
        var ids = targets.values();
        for(var i=0; i<ids.length; i++)
        {
          var check = document.getElementById(ids[i]);
          if(check.checked)
          {
            check.click();
          }
        }
      }
    }
  }
});
