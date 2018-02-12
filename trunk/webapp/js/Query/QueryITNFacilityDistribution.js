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
Mojo.Meta.newClass('MDSS.QueryITNFacilityDistribution', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

      this._itn = Mojo.$.dss.vector.solutions.intervention.monitor.ITNDistribution;

      this._mainQueryClass = this._itn.CLASS;
      
      this._groupByClass = Mojo.$.dss.vector.solutions.intervention.monitor.ITNDistribution;

      this._dateAttribs = [
                           {
                          	 klass :  this._groupByClass,
                          	 accessor : this._groupByClass.DISTRIBUTIONDATE,
                           }
                           ];
      
      this._commonQueryClasses = [
                                  Mojo.$.dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroup.CLASS,
                                  Mojo.$.dss.vector.solutions.Person.CLASS,
                                  ];

      this._exclusionClasses = [];
      
      
      
      var instance = new this._itn();
      
      var tmpPerson = new  Mojo.$.dss.vector.solutions.Person();

      this._geoEntityAttribs = [
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.FACILITY,
                               display : instance.getFacilityMd().getDisplayLabel()
                             },
                             {
                               keyName :  Mojo.$.dss.vector.solutions.Person.CLASS+'.'+Mojo.$.dss.vector.solutions.Person.RESIDENTIALGEOENTITY,
                               display : tmpPerson.getResidentialGeoEntityMd().getDisplayLabel()
                             },
                             {
                               keyName :  Mojo.$.dss.vector.solutions.Person.CLASS+'.'+Mojo.$.dss.vector.solutions.Person.WORKGEOENTITY,
                               display : tmpPerson.getWorkGeoEntityMd().getDisplayLabel()
                             }        
                             
                           ];
      

      /**
       * Returns the type of query.
       */
      this._queryType = this._mainQueryClass;
      
      this.$initialize(selectableGroups, queryList);   

      var picker = this.getGeoPicker();      
      picker.setPolitical(true);
      picker.setSprayTargetAllowed(false);
      picker.addExtraUniversal(Mojo.$.dss.vector.solutions.geo.generated.HealthFacility.CLASS);            
    }
  }
});
