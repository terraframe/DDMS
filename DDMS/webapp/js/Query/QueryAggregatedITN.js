/*
 * Copyright (C) 2008 IVCC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
Mojo.Meta.newClass('MDSS.QueryAggreatedITN', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

      this._aggregatedITN = Mojo.$.dss.vector.solutions.intervention.monitor.ITNData;
      this._dateAttribute = new MDSS.QueryXML.Attribute(this._aggregatedITN.CLASS, this._aggregatedITN.STARTDATE, this._aggregatedITN.STARTDATE);
      
      var startDateAttr = new MDSS.QueryXML.Attribute(this._aggregatedITN.CLASS, this._aggregatedITN.STARTDATE, this._aggregatedITN.STARTDATE);
      this._startDateSelectable = new MDSS.QueryXML.Selectable(startDateAttr);
      
      var endDateAttr = new MDSS.QueryXML.Attribute(this._aggregatedITN.CLASS, this._aggregatedITN.ENDDATE, this._aggregatedITN.ENDDATE);
      this._endDateSelectable = new MDSS.QueryXML.Selectable(endDateAttr);

      this._mainQueryClass = this._aggregatedITN.CLASS;
      
      this._groupByClass = Mojo.$.dss.vector.solutions.intervention.monitor.ITNData;

      this._commonQueryClasses = [
                                  Mojo.$.dss.vector.solutions.intervention.monitor.ITNNet.CLASS,
                                  Mojo.$.dss.vector.solutions.intervention.monitor.ITNTargetGroup.CLASS,
                                  Mojo.$.dss.vector.solutions.intervention.monitor.ITNService.CLASS,
                                  ];

      this._exclusionClasses = [];
      
      
      
      var instance = new this._aggregatedITN();
      
      
      this._geoEntityAttribs = [
                                {
                                  keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,
                                  display : com.runwaysdk.Localize.get("Geo_Entity")
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
