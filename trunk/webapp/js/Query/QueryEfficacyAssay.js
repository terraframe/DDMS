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
                                  display : efficacyInstance.getGeoEntityMd().getDisplayLabel()
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
