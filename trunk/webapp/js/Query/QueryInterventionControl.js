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
Mojo.Meta.newClass('MDSS.QueryInterventionControl', {

  Extends: MDSS.QueryBaseNew,
  
  Constants : {
    INDIVIDUALS_GROUP : 'individuals_group',
    AGGREGATES_GROUP : 'aggregates_group',
    VEHICLES_GROUP : 'vehicles_group',
    GEO_INDEX : 0,
    INDIVIDUALS_GEO_INDEX : 1,
    AGGREGATES_GEO_INDEX : 2
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
                             }
                               ,
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
      
        this._indOption = null;
        this._aggOption = null;
        
        this._usingAgg = false;
        this._usingInd = false;
        
        this._geoFilters = [];
        this._universalFilters = [];
        
        var picker = this.getGeoPicker();
        picker.setUrban(true);
        picker.clearAfterFilter(true);
        picker.setValidator(Mojo.Util.bind(this, this.validateGeoEntity));
      },
      
      _customPostRender : function()
      {
        var select = document.getElementById(MDSS.QueryBase.GEO_ATTRIBUTES);
        this._indOption = select.options[this.constructor.INDIVIDUALS_GEO_INDEX];
        this._aggOption = select.options[this.constructor.AGGREGATES_GEO_INDEX];
      },
      
      _resetToDefault : function()
      {
        this._aggOption.disabled = false;
        this._indOption.disabled = false;
        
        this.$_resetToDefault();
      },
      
      _displaySearch : function()
      {
        // don't open the picker if the select box is on a disabled option
        var select = document.getElementById(MDSS.QueryBase.GEO_ATTRIBUTES);
        if(!select.options[select.selectedIndex].disabled)
        {
          this.$_displaySearch();
        }
        
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
        span.innerHTML = MDSS.localize('Select_All');

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
      
      _toggleCount : function(e, attribute)
      {
        this.$_toggleCount(e, attribute);

        if(e.target.checked)
        {
          this._toggleGeoOptions();
        }
      },
      
      _toggleRatio : function(e, attribute)
      {
        this.$_toggleRatio(e, attribute);
        
        if(e.target.checked)
        {
          this._toggleGeoOptions();
        }
      },
      
      _toggleGeoOptions : function()
      {
        // re-enable all geo selection/criteria
        if(!this._usingAgg)
        {
          this._indOption.disabled = false;
        }
        
        if(!this._usingInd)
        {
          this._aggOption.disabled = false;
        }
      },
      
      /**
       * The geo selection/criteria is mutually exclusive for individuals and aggregated/vehicle
       * premises, so this method is executed after any checkbox selection to toggle the selection/criteria
       * appropriately.
       */
      togglePremises : function(triggerId, independents)
      {
        if(independents.length === 1)
        {
          var select = document.getElementById(MDSS.QueryBase.GEO_ATTRIBUTES);
          
          var ind = independents[0];
          var name = ind.getName();
          
          if(name === this.constructor.INDIVIDUALS_GROUP)
          {
            this._usingInd = true;
            this._usingAgg = false;
            
            this._indOption.disabled = false;
            this._indOption.selected = true;
            
            this._aggOption.disabled = true;
            this._hideHandler([],[],this._aggPremiseGeo);
            
            return;
          }
          else if(name === this.constructor.AGGREGATES_GROUP || name === this.constructor.VEHICLES_GROUP)
          {
            this._usingAgg = true;
            this._usingInd = false;
            
            this._aggOption.disabled = false;
            this._aggOption.selected = true;
            
            this._indOption.disabled = true;
            this._hideHandler([],[],this._indPremiseGeo);

            return;
          }
        }          
        
        this._toggleGeoOptions();
      },
      
      _displaySearch : function()
      {
        var currentAttribute = this._getCurrentGeoAttribute();
        if(currentAttribute === this._indPremiseGeo || currentAttribute === this._aggPremiseGeo)
        {
          // restrict the sub-geo entity universals
          if(this._universalFilters.length > 0)
          {
            this.getGeoPicker().setFilter(this._universalFilters);
          }
        }
        else
        {
          // don't show a filter for the main geo entity
          this.getGeoPicker().setFilter(null);
        }
        
        this.$_displaySearch();
      },
      
      _hideHandler : function(views, selectedUniversals, attributeKey)
      {
        var current = attributeKey || this._getCurrentGeoAttribute();
        if(current !== this._indPremiseGeo && current !== this._aggPremiseGeo)
        {
          // define the filters based on the main geo entity
          this._geoFilters = views;
          this._universalFilters = selectedUniversals;
        }
        
        this.$_hideHandler(views, selectedUniversals, attributeKey);
      },
      
      _resetToDefault : function()
      {
        this._universalFilters = [];
        this._geoFilters = [];
        
        this.$_resetToDefault();
      },
      
      validateGeoEntity : function(cb, geoEntityView, entityIds)
      {
        var current = this._getCurrentGeoAttribute();
        
        var childIds;
        var parentIds;
        var usingMain = false;
        if(current === this._indPremiseGeo || current === this._aggPremiseGeo)
        {
          // Adding a new sub geo entity, so make sure it is located_in one of the main geo entities.
          var childId = geoEntityView.getGeoEntityId();
          childIds = [childId].concat(entityIds);
          
          parentIds = [];
        }
        else
        {
          usingMain = true;
          
          // Adding a new main geo entity, so make sure we don't invalidate any existing sub geo entities.
          parentIds = [geoEntityView.getGeoEntityId()];
          
          var childCriteria = this._criteriaEntities[this._indPremiseGeo].concat(this._criteriaEntities[this._aggPremiseGeo]);
          childIds = Mojo.Iter.map(childCriteria, function(geo){ return geo.getGeoEntityId(); });
        }

        if((usingMain && childIds.length > 0) || (!usingMain && this._geoFilters.length > 0))
        {
          var request = new MDSS.Request({
            onSuccess : function(ids)
            {
              var warnings = this.getWarnings();
              if(warnings.length > 0)
              {
                new MDSS.ErrorModal(warnings[0].getMessage());
              }
              else
              {
                cb();
              }
            }
          });
          
          parentIds = parentIds.concat(Mojo.Iter.map(this._geoFilters, function(geo){ return geo.getGeoEntityId(); })); 
          Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.isChildOfParents(request, childIds, parentIds);
        }
        else
        {
          cb();
        }
      }
  }
});
