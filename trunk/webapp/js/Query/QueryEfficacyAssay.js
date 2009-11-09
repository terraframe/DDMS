Mojo.Meta.newClass('MDSS.QueryEfficacyAssay', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

      this._efficacyAssay = Mojo.$.dss.vector.solutions.entomology.assay.EfficacyAssay;
      
      
      this._dateAttribute = new MDSS.QueryXML.Attribute(this._efficacyAssay.CLASS, this._efficacyAssay.TESTDATE, this._efficacyAssay.TESTDATE);
      
      this._startDateSelectable = new MDSS.QueryXML.Selectable(this._dateAttribute);
      this._endDateSelectable = new MDSS.QueryXML.Selectable(this._dateAttribute);
      

      this._mainQueryClass = this._efficacyAssay.CLASS;
      this._groupByClass = this._efficacyAssay;

      this._commonQueryClasses = [
                                  ];

      this._exclusionClasses = [];

      this._dataQueryFunction = Mojo.$.dss.vector.solutions.query.QueryBuilder.getQueryResults;
      this._mapQueryFunction  = Mojo.$.dss.vector.solutions.query.QueryBuilder.mapQuery;
      
      
      var efficacyInstance = new this._efficacyAssay();
      
      
      this._geoEntityAttribs = [
                                {
                                  keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,
                                  display : efficacyInstance.getGeoEntityMd().getDisplayLabel()
                                }        
                              ];
      
      this.$initialize(selectableGroups, queryList);   
      

      /**
       * Returns the type of query.
       */
      this._queryType = 'QueryEfficacyAssay';
      

      this._reportQueryType = 'QueryEfficacyAssay';
      }
    }
});
