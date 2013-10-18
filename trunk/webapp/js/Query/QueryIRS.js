Mojo.Meta.newClass('MDSS.QueryIRS', {

  Extends: MDSS.QueryBaseNew,
  
  Constants : {
    DATE_GROUP : 'DATE_GROUP'
  },
  
  Instance : {

    initialize : function(selectableGroups, queryList)
    {
 

      // list of columns that have been added before a call to render()
      this._preconfiguredColumns = [];

      // START: query objects that dictate state of the query.

      this._countSelectable = null;
      this._ratioSelectable = null;

      this._specieGroupSelectables = {};
      this._visibleSelectables = {};
      this._whereOptions = {};
      this._visibleAggregateSelectables = {};

      var abstractSpray = dss.vector.solutions.irs.AbstractSpray;

      //this screen can query two diffrent classes, so we have a place to store the selected class
      this._mainQueryClass = abstractSpray.CLASS;
      
    	this._groupByClass = abstractSpray;
    	
      var abstractSprayInstance = new dss.vector.solutions.irs.OperatorSpray();

      this._commonQueryClasses = [];
      
      this._geoEntityAttribs = [
                                {
                                  keyName :  abstractSpray.CLASS+'.' + abstractSpray.GEOENTITY,
                                  display :  abstractSprayInstance.getGeoEntityMd().getDisplayLabel()
                                },                                    
                              ];
      this._dateAttribs = [
                           {
                          	 klass :  dss.vector.solutions.irs.OperatorSpray,
                             accessor : abstractSpray.SPRAYDATE,
                           },
                           {
                             klass : dss.vector.solutions.Person,
                             accessor : dss.vector.solutions.Person.DATEOFBIRTH
                           }
                          ];
      

      this._exclusionClasses = [];

      this._queryType = this._mainQueryClass;

      this.$initialize(selectableGroups, queryList); 
      
      var picker = this.getGeoPicker();      
      picker.setPolitical(false);
      picker.setSprayTargetAllowed(true);
    },
    
    _sqlCharacterHandler : function(entityAlias, attributeName, userAlias, operator, value)
    {
      if(userAlias === 'aggregation_level')
      {
        var item = this._menuItems[userAlias+'-'+value];
        item.checked = true;
        var attribute = item.onclick.obj.attribute;
        var display = item.onclick.obj.display;
        this._queryPanel.addWhereCriteria(attribute.getKey(), value, display);
      }
    },
    
    _getBrowserRootClass : function(attribute)
    {
      var type = attribute.getType();
      if(type === 'dss.vector.solutions.irs.AbstractSpray' || attribute.getAttributeName() === 'surfaceType')
      {
        return 'dss.vector.solutions.irs.OperatorSprayView';
      }
      else if(type === 'dss.vector.solutions.irs.InsecticideBrand')
      {
        return type;
      }
      else
      {
        return this.$_getBrowserRootClass(attribute);
      }
    },
    
    _getBrowserRootAttribute : function(attribute)
    {
      var type = attribute.getType();
      var name = attribute.getAttributeName();
      
      if(type === dss.vector.solutions.Person.CLASS)
      {
        if(name === 'sprayoperator_sex' || name === 'sprayleader_sex' || name === 'zone_supervisor_sex')
        {
          return dss.vector.solutions.Person.SEX; 
        }
      }
      
      return this.$_getBrowserRootAttribute(attribute);
    },
    
	  /**
	   * At least one date group checkbox must be checked when any of the target management
	   * calculations are selected. This method is the callback used when the DependencyManager
	   * is done firing on the IRS query checkboxes, and the target management dependencies
	   * need to be filtered out.
	   */
	  ensureDateGroupChecked : function(targets, dateGroups, triggerId, independents)
	  {
      if(targets.contains(triggerId) && document.getElementById(triggerId).checked)
      {
        // if no date-groups are selected automatically group by epi-week
        var epi_week = 'DATEGROUP_EPIWEEK'; // FIXME should be a constant
        if(Mojo.Util.getKeys(this._dateGroupSelectables).length === 0)
        {
          document.getElementById(epi_week.toLowerCase()).click();
        }
        
        // always snap to epi-week
        var dateGroup = this._queryPanel.getDateGroupBy();
        
        dateGroup.value = epi_week;
        var option = dateGroup.options[dateGroup.selectedIndex];
        this._fireClickOnOption(option);
      }
      else if(dateGroups.contains(triggerId) && Mojo.Util.getKeys(this._dateGroupSelectables).length === 0)
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