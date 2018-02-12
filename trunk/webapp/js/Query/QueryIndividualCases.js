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
Mojo.Meta.newClass('MDSS.QueryIndividualCases', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

  		
  		this._groupByClass = Mojo.$.dss.vector.solutions.intervention.monitor.IndividualCase;
      this._individualCase = new this._groupByClass();
  		this._mainQueryClass = this._groupByClass.CLASS;
  		this._individualInstance = Mojo.$.dss.vector.solutions.intervention.monitor.IndividualInstance;
  		


      this._commonQueryClasses = [
                                  this._groupByClass.CLASS,
                                  this._individualInstance.CLASS,
                                  Mojo.$.dss.vector.solutions.Person.CLASS,
                                  ];

      this._exclusionClasses = [];
      
      var tmpPerson = new  Mojo.$.dss.vector.solutions.Person();
      
      var tmpCase = new this._groupByClass;
      
      var tmpInstance = new this._individualInstance();
      
      this._dateAttribs = [
                           {
                          	 klass :  this._groupByClass,
                             accessor : this._groupByClass.DIAGNOSISDATE,
                           },
                           {
                          	 klass :  this._groupByClass,
                             accessor : this._groupByClass.CASEREPORTDATE,
                           },
                           {
                          	 klass :  this._groupByClass,
                             accessor : this._groupByClass.CASEENTRYDATE,
                           },
                           {
                             klass :  this._groupByClass,
                             accessor : this._groupByClass.SYMPTOMONSET,                       
                           },
                           {
                             klass :  this._individualInstance,
                             accessor : this._individualInstance.FACILITYVISIT,                       
                           },
                           {
                             klass :  this._individualInstance,
                             accessor : this._individualInstance.ADMISSIONDATE,                       
                           },
                           {
                             klass :  this._individualInstance,
                             accessor : this._individualInstance.RELEASEDATE,                       
                           },
                           {
                             klass :  this._individualInstance,
                             accessor : this._individualInstance.TESTSAMPLEDATE,                       
                           },
                           {
                             klass :  this._individualInstance,
                             accessor : this._individualInstance.LABTESTDATE,                       
                           },
                           
                         ];

      
      

      this._geoEntityAttribs = [
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.PROBABLESOURCE,
                               display : tmpCase.getProbableSourceMd().getDisplayLabel()
                             },
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.RESIDENCE,
                               display : tmpCase.getResidenceMd().getDisplayLabel()
                             },
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.WORKPLACE,
                               display : tmpCase.getWorkplaceMd().getDisplayLabel()
                             },
                             {
                               keyName :  this._individualInstance.CLASS+'.'+this._individualInstance.HEALTHFACILITY,
                               display : tmpInstance.getHealthFacilityMd().getDisplayLabel()
                             },     
                             
                           ];
      
      this._queryType = this._mainQueryClass;
      
      this.$initialize(selectableGroups, queryList);
      
      var picker = this.getGeoPicker();      
      picker.setPolitical(true);
      picker.setSprayTargetAllowed(false);
      picker.addExtraUniversal('dss.vector.solutions.geo.generated.HealthFacility*');  
 
      },
      
      _getBrowserRootClass : function(attribute)
      {
        var type = attribute.getType();
        if(type === 'dss.vector.solutions.Person')
        {
          return 'dss.vector.solutions.PersonView';
        }
        else
        {
          return attribute.getType();
        }
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
