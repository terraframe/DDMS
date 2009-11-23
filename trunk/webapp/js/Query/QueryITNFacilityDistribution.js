Mojo.Meta.newClass('MDSS.QueryITNFacilityDistribution', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

      this._itn = Mojo.$.dss.vector.solutions.intervention.monitor.ITNDistribution;
      this._dateAttribute = new MDSS.QueryXML.Attribute(this._itn.CLASS, this._itn.STARTDATE, this._itn.STARTDATE);
      
      var startDateAttr = new MDSS.QueryXML.Attribute(this._itn.CLASS, this._itn.STARTDATE, this._itn.STARTDATE);
      this._startDateSelectable = new MDSS.QueryXML.Selectable(startDateAttr);
      
      var endDateAttr = new MDSS.QueryXML.Attribute(this._itn.CLASS, this._itn.ENDDATE, this._itn.ENDDATE);
      this._endDateSelectable = new MDSS.QueryXML.Selectable(endDateAttr);

      this._mainQueryClass = this._itn.CLASS;
      
      this._groupByClass = Mojo.$.dss.vector.solutions.intervention.monitor.ITNDistribution;

      this._commonQueryClasses = [
                                  Mojo.$.dss.vector.solutions.intervention.monitor.ITNNet.CLASS,
                                  Mojo.$.dss.vector.solutions.intervention.monitor.ITNTargetGroup.CLASS,
                                  Mojo.$.dss.vector.solutions.intervention.monitor.ITNService.CLASS,
                                  ];

      this._exclusionClasses = [];
      
      
      
      var instance = new this._itn();
      
      
      this._geoEntityAttribs = [
                                {
                                  keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,
                                  display : instance.getGeoEntityMd().getDisplayLabel()
                                }        
                              ];
      
      

      /**
       * Returns the type of query.
       */
      this._queryType = 'QueryITNFacilityDistribution';
      

      this._reportQueryType = 'QueryITNFacilityDistribution';
      this.$initialize(selectableGroups, queryList);   
      

      }
    }
});
