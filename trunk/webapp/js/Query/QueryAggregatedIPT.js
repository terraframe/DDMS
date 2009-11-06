Mojo.Meta.newClass('MDSS.QueryAggreatedIPT', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

      this._aggregatedIPT = Mojo.$.dss.vector.solutions.intervention.monitor.AggregatedIPT;
      this._dateAttribute = new MDSS.QueryXML.Attribute(this._aggregatedIPT.CLASS, this._aggregatedIPT.STARTDATE, this._aggregatedIPT.STARTDATE);
      
      var startDateAttr = new MDSS.QueryXML.Attribute(this._aggregatedIPT.CLASS, this._aggregatedIPT.STARTDATE, this._aggregatedIPT.STARTDATE);
      this._startDateSelectable = new MDSS.QueryXML.Selectable(startDateAttr);
      
      var endDateAttr = new MDSS.QueryXML.Attribute(this._aggregatedIPT.CLASS, this._aggregatedIPT.ENDDATE, this._aggregatedIPT.ENDDATE);
      this._endDateSelectable = new MDSS.QueryXML.Selectable(endDateAttr);

      this._mainQueryClass = this._aggregatedIPT.CLASS;
      
      this._groupByClass = Mojo.$.dss.vector.solutions.intervention.monitor.AggregatedIPT;

      this._commonQueryClasses = [
                                  Mojo.$.dss.vector.solutions.intervention.monitor.IPTDose.CLASS,
                                  Mojo.$.dss.vector.solutions.intervention.monitor.IPTPatients.CLASS,
                                  Mojo.$.dss.vector.solutions.intervention.monitor.IPTTreatment.CLASS,
                                  Mojo.$.dss.vector.solutions.intervention.monitor.IPTANCVisit.CLASS
                                  ];

      this._exclusionClasses = [];
      
      this.$initialize(selectableGroups, queryList);   
      

      /**
       * Returns the type of query.
       */
      this._queryType = 'QueryAggregatedIPT';
      

      this._reportQueryType = 'QueryAggregatedIPT';
      }
    }
});
