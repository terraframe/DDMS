Mojo.Meta.newClass('MDSS.QueryEfficacyAssay', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

      this._efficacyAssay = Mojo.$.dss.vector.solutions.entomology.assay.EfficacyAssay;
      
      
      this._dateAttribute = new MDSS.QueryXML.Attribute(Mojo.$.dss.vector.solutions.entomology.assay.AbstractAssay.CLASS, Mojo.$.dss.vector.solutions.entomology.assay.AbstractAssay.TESTDATE, Mojo.$.dss.vector.solutions.entomology.assay.AbstractAssay.TESTDATE);
      
      this._startDateSelectable = new MDSS.QueryXML.Selectable(this._dateAttribute);
      this._endDateSelectable = new MDSS.QueryXML.Selectable(this._dateAttribute);
      

      this._mainQueryClass = this._efficacyAssay.CLASS;
      this._groupByClass = this._efficacyAssay;

      this._commonQueryClasses = [
                                  	Mojo.$.dss.vector.solutions.entomology.assay.AbstractAssay.CLASS,
                                  	Mojo.$.dss.vector.solutions.general.Insecticide.CLASS,
                                  ];

      this._exclusionClasses = [];

      this._dataQueryFunction = Mojo.$.dss.vector.solutions.query.QueryBuilder.getQueryResults;
      this._mapQueryFunction  = Mojo.$.dss.vector.solutions.query.QueryBuilder.mapQuery;
      
      this._typeOverride = {'specie_efficacy':Mojo.$.dss.vector.solutions.entomology.assay.AbstractAssay.CLASS};
      
      
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
      this._queryType = this._mainQueryClass;
      

      var picker = this.getGeoPicker();      
      picker.setPolitical(false);
      picker.setSprayTargetAllowed(false);
      }
    }
});
