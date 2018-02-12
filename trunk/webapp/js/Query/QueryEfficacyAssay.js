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
Mojo.Meta.newClass('MDSS.QueryEfficacyAssay', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

      this._efficacyAssay = dss.vector.solutions.entomology.assay.EfficacyAssay;
      
      this._mainQueryClass = this._efficacyAssay.CLASS;
      this._groupByClass = this._efficacyAssay;

      this._commonQueryClasses = [
                                  //	dss.vector.solutions.entomology.assay.AbstractAssay.CLASS,
                                  //	dss.vector.solutions.general.Insecticide.CLASS,
                                  ];

      this._exclusionClasses = [];

      this._dataQueryFunction = dss.vector.solutions.query.QueryBuilder.getQueryResults;
      this._mapQueryFunction  = dss.vector.solutions.query.QueryBuilder.mapQuery;
      
      var efficacyInstance = new this._efficacyAssay();
      
      
      this._geoEntityAttribs = [
                                {
                                  keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,
                                  display : com.runwaysdk.Localize.get("Geo_Entity")
                                }        
                              ];
      
      this._dateAttribs = [
                           {
                          	 klass :  this._efficacyAssay,
                             accessor : dss.vector.solutions.entomology.assay.AbstractAssay.TESTDATE,
                           },
                          
                          ];
      
      this.$initialize(selectableGroups, queryList);   
      

      /**
       * Returns the type of query.
       */
      this._queryType = this._mainQueryClass;
      

      var picker = this.getGeoPicker();      
      picker.setPolitical(true);
      picker.setSprayTargetAllowed(false);
      picker.addExtraUniversal(dss.vector.solutions.geo.generated.Surface.CLASS);
      },
      
      _getBrowserRootClass : function(attribute)
      {
        var type = attribute.getType();
        if(type === 'dss.vector.solutions.irs.InsecticideBrand')
        {
          return type;
        }
        else
        {
          return this.$_getBrowserRootClass(attribute);
        }
      }
    }
});
