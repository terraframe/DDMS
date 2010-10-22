Mojo.Meta.newClass('MDSS.QueryInterventionControl', {

  Extends: MDSS.QueryBaseNew,
  
  Constants : {
    INDIVIDUALS_GROUP : 'individuals_group',
    AGGREGATES_GROUP : 'aggregates_group',
    VEHICLES_GROUP : 'vehicles_group'
  },
  
  Instance : {
  
    initialize : function(selectableGroups, queryList, calculationsSection)
    {
      this._calculationsSection = calculationsSection;
  		
  		this._groupByClass = dss.vector.solutions.intervention.monitor.ControlIntervention;
  		this._mainQueryClass = this._groupByClass.CLASS;
	
      this._larvacide = new this._groupByClass();
      this._dateAttribute = new MDSS.QueryXML.Attribute(this._groupByClass.CLASS, this._groupByClass.STARTDATE, this._groupByClass.STARTDATE);
      
      var startDateAttr = new MDSS.QueryXML.Attribute(this._groupByClass.CLASS, this._groupByClass.STARTDATE, this._groupByClass.STARTDATE);
      this._startDateSelectable = new MDSS.QueryXML.Selectable(startDateAttr);
      
      var endDateAttr = new MDSS.QueryXML.Attribute(this._groupByClass.CLASS, this._groupByClass.ENDDATE, this._groupByClass.ENDDATE);
      this._endDateSelectable = new MDSS.QueryXML.Selectable(endDateAttr);

      this._IndividualPremiseVisit = Mojo.$.dss.vector.solutions.intervention.monitor.IndividualPremiseVisit;
      this._AggregatedPremiseVisit = Mojo.$.dss.vector.solutions.intervention.monitor.AggregatedPremiseVisit;

      this._commonQueryClasses = [
                                  this._groupByClass.CLASS
                                  ];

      this._exclusionClasses = [];
      
      
      this._indPremiseGeo = this._IndividualPremiseVisit.CLASS + '.' + this._IndividualPremiseVisit.GEOENTITY;
      this._aggPremiseGeo = this._AggregatedPremiseVisit.CLASS + '.' + this._AggregatedPremiseVisit.GEOENTITY;
      
      this._geoEntityAttribs = [
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,
                               display : this._larvacide.getGeoEntityMd().getDisplayLabel()
                             },
                             {
                               keyName : this._indPremiseGeo,
                               display : MDSS.localize('sub_geoentity_individual_premises')
                             },
                             {
                               keyName : this._aggPremiseGeo,
                               display : MDSS.localize('sub_geoentity_aggregated_premises')
                             }
                           ];
      
      this._queryType = this._mainQueryClass;

      this.$initialize(selectableGroups, queryList);   
 
      var picker = this.getGeoPicker();      
      picker.setPolitical(false);
      picker.setSprayTargetAllowed(false);
      },
     
      _getBrowserRootClass : function(attribute)
      {
        var type = attribute.getType();
        if(type === 'dss.vector.solutions.irs.InsecticideBrand')
        {
          return type;
        }
        else if(attribute.getKey() === 'childId_tm')
        {
          return 'dss.vector.solutions.intervention.monitor.ControlInterventionView';
        }
        else if(attribute.getKey() === 'childId_r')
        {
          return 'dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView';
        }
        else if(type === 'dss.vector.solutions.intervention.monitor.InsecticideIntervention' 
          && attribute.getAttributeName() !== 'unit')
        {
          return 'dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitView';
        }
        else
        {
          return this.$_getBrowserRootClass(attribute);
        }
      },
      
      _getBrowserRootAttribute : function(attribute)
      {
        if(attribute.getKey() === 'childId_tm')
        {
          return 'insecticideIntervention';
        }
        else if(attribute.getKey() === 'childId_r')
        {
          return 'reasonsForNotTreated';
        }
        else
        {
          return this.$_getBrowserRootAttribute(attribute);
        }
      },
      
      /**
       * Override to omit the calculations section (which does not allow
       * all checkboxes to be checked due to mutual exclusion).
       */
      _attachSelectAll : function(ul,klass, divName)
      {
        // Match the div name against the calculations section
        if(divName === this._calculationsSection)
        {
          return;
        }
        
        var check = document.createElement('input');
        YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
        YAHOO.util.Dom.addClass(check,'selectAllCheck');
        YAHOO.util.Dom.addClass(check,klass);
        YAHOO.util.Event.on(check, 'click', this._toggleSelectAll, ul, this);
        this._defaults.push({element:check, checked:false, bypass:true});      

        var span = document.createElement('span');
        span.innerHTML = MDSS.Localized.Select_All;

        var li = document.createElement('li');
        li.appendChild(check);
        li.appendChild(span);

        ul.appendChild(li);
      },
    
      /**
       * This override adds temporary classes to the query if the geo entity attributes
       * of those classes are being selected or restricted.
       */
      _constructQuery : function(forMapping)
      {
        // add the individual/aggregated premise visit classes if any selectino/restriction
        // is being performed on them
        if(this._config.getSelectedUniversals(this._indPremiseGeo).length > 0 ||
            this._config.getCriteriaEntities(this._indPremiseGeo).length > 0)
        {
          this._commonQueryClasses.push(this._IndividualPremiseVisit.CLASS);
        }
        
        if(this._config.getSelectedUniversals(this._aggPremiseGeo).length > 0 ||
            this._config.getCriteriaEntities(this._aggPremiseGeo).length > 0)
        {
          this._commonQueryClasses.push(this._AggregatedPremiseVisit.CLASS);
        }
        
        var obj = this.$_constructQuery(forMapping);
        
        // reset the query classes
        this._commonQueryClasses = [
                                    this._groupByClass.CLASS
                                    ];
        
        return obj;
      },
      
      togglePremises : function(triggerId, independents)
      {
        if(independents.length === 1)
        {
          var ind = independents[0];
          var name = ind.getName();
          if(name === this.constructor.INDIVIDUALS_GROUP)
          {
            this._hideHandler([],[],this._aggPremiseGeo);
          }
          else if(name === this.constructor.AGGREGATES_GROUP || name)
          {
            this._hideHandler([],[],this._indPremiseGeo);
          }
        }
      }
  }
});
