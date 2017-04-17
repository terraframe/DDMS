Mojo.Meta.newClass('MDSS.QueryITNCommunityDistribution', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

      this._aggregatedITN = Mojo.$.dss.vector.solutions.intervention.monitor.ITNCommunityDistribution;
      this._dateAttribute = new MDSS.QueryXML.Attribute(this._aggregatedITN.CLASS, this._aggregatedITN.STARTDATE, this._aggregatedITN.STARTDATE);
      
      var startDateAttr = new MDSS.QueryXML.Attribute(this._aggregatedITN.CLASS, this._aggregatedITN.STARTDATE, this._aggregatedITN.STARTDATE);
      this._startDateSelectable = new MDSS.QueryXML.Selectable(startDateAttr);
      
      var endDateAttr = new MDSS.QueryXML.Attribute(this._aggregatedITN.CLASS, this._aggregatedITN.ENDDATE, this._aggregatedITN.ENDDATE);
      this._endDateSelectable = new MDSS.QueryXML.Selectable(endDateAttr);

      this._mainQueryClass = this._aggregatedITN.CLASS;
      
      this._groupByClass = Mojo.$.dss.vector.solutions.intervention.monitor.ITNCommunityDistribution;

      this._commonQueryClasses = [
                                  Mojo.$.dss.vector.solutions.intervention.monitor.ITNCommunityNet.CLASS,
                                  Mojo.$.dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroup.CLASS,
                                  ];

      this._exclusionClasses = [];
      
      
      
      var instance = new this._aggregatedITN();
      
      
      this._geoEntityAttribs = [
                                {
                                  keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.DISTRIBUTIONLOCATION,
                                  display : instance.getDistributionLocationMd().getDisplayLabel()
                                },
                                {
                                  keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.HOUSEHOLDADDRESS,
                                  display : instance.getHouseholdAddressMd().getDisplayLabel()
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
    }
  }
});
