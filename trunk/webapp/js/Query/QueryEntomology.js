Mojo.Meta.newClass('MDSS.QueryEntomology', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

  		this._groupByClass = Mojo.$.dss.vector.solutions.entomology.MosquitoCollection;
  		this._mainQueryClass = this._groupByClass.CLASS;
	
      this._mosquitoCollection = new this._groupByClass();
      
      this._showRatioSelectable = true;

      this._commonQueryClasses = [
                                  ];

      this._geoEntityAttribs = [
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,
                               display : com.runwaysdk.Localize.get("Geo_Entity")
                             },
                             
                           ];
      
      this._dateAttribs = [
                           {
                          	 klass :  this._groupByClass,
                             accessor : this._groupByClass.COLLECTIONDATE,
                           }
                          ];
      
      this._queryType = this._mainQueryClass;

      this.$initialize(selectableGroups, queryList);   
      
      //remove collection from exclusion classes so collection selectables will not be unchecked when switching assay types
      this._exclusionClasses.shift();
       
      //we overide the xmlToValueQueryClass since it is not the same as the mainQueryClass for this page 
      this._xmlToValueQueryClass = dss.vector.solutions.entomology.InfectionAssay.CLASS;
      
      var picker = this.getGeoPicker();
      picker.setPolitical(true);
      picker.setPopulated(false);
      picker.setSprayTargetAllowed(false);
      picker.setUrban(false);
      picker.addExtraUniversal('dss.vector.solutions.geo.generated.CollectionSite');
      picker.addExtraUniversal('dss.vector.solutions.geo.generated.SentinelSite');
      
      }
				
      

    }
});
