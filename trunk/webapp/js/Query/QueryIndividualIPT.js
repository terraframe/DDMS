Mojo.Meta.newClass('MDSS.QueryIndividualIPT', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {


      this._individualIPT = Mojo.$.dss.vector.solutions.intervention.monitor.IndividualIPT();
      this._dateAttribute = new MDSS.QueryXML.Attribute(this._individualIPT.CLASS, this._individualIPT.STARTDATE, this._individualIPT.STARTDATE);
      
      var startDateAttr = new MDSS.QueryXML.Attribute(this._individualIPT.CLASS, this._individualIPT.STARTDATE, this._individualIPT.STARTDATE);
      this._startDateSelectable = new MDSS.QueryXML.Selectable(startDateAttr);
      
      var endDateAttr = new MDSS.QueryXML.Attribute(this._individualIPT.CLASS, this._individualIPT.ENDDATE, this._individualIPT.ENDDATE);
      this._endDateSelectable = new MDSS.QueryXML.Selectable(endDateAttr);

      this._mainQueryClass = this._individualIPT.CLASS;
      this._groupByClass = Mojo.$.dss.vector.solutions.intervention.monitor.IndividualIPT();

      this._commonQueryClasses = [
                                  Mojo.$.dss.vector.solutions.intervention.monitor.IndividualIPT().CLASS,
                                  ];

      this._exclusionClasses = [];

      this._dataQueryFunction = Mojo.$.dss.vector.solutions.query.QueryBuilder.getQueryResults;
      this._mapQueryFunction  = Mojo.$.dss.vector.solutions.query.QueryBuilder.mapQuery;
      
      this.$initialize(selectableGroups, queryList);   
      

      /**
       * Returns the type of query.
       */
      this._queryType = 'QueryIndividualIPT';
      

      this._reportQueryType = 'QueryIndividualIPT';
      }
    }
});
