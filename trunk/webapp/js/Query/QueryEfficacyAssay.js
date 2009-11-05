Mojo.Meta.newClass('MDSS.QueryEfficacyAssay', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

      this._efficacyAssay = Mojo.$.dss.vector.solutions.intervention.monitor.EfficacyAssay;
      this._dateAttribute = new MDSS.QueryXML.Attribute(this._efficacyAssay.CLASS, this._efficacyAssay.STARTDATE, this._efficacyAssay.STARTDATE);
      
      this._startDateSelectable = new MDSS.QueryXML.Selectable(this._dateAttribute);
      this._endDateSelectable = new MDSS.QueryXML.Selectable(this._dateAttribute);
      
      var endDateAttr = startDateAttr;

      this._mainQueryClass = this._efficacyAssay.CLASS;
      this._groupByClass = Mojo.$.dss.vector.solutions.intervention.monitor.EfficacyAssay;

      this._commonQueryClasses = [
                                  ];

      this._exclusionClasses = [];

      this._dataQueryFunction = Mojo.$.dss.vector.solutions.query.QueryBuilder.getQueryResults;
      this._mapQueryFunction  = Mojo.$.dss.vector.solutions.query.QueryBuilder.mapQuery;
      
      this.$initialize(selectableGroups, queryList);   
      

      /**
       * Returns the type of query.
       */
      this._queryType = 'QueryEfficacyAssay';
      

      this._reportQueryType = 'QueryEfficacyAssay';
      }
    }
});
