Mojo.Meta.newClass('MDSS.QueryMosquitoCollections', {
  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {
      this._groupByClass = Mojo.$.dss.vector.solutions.entomology.MosquitoCollection;
      this._mainQueryClass = this._groupByClass.CLASS;  
      this._mosquitoCollection = new this._groupByClass();      
      this._showRatioSelectable = true;
      this._commonQueryClasses = [Mojo.$.dss.vector.solutions.entomology.SubCollection.CLASS];
      this._exclusionClasses = [];
      this._geoEntityAttribs = [{
        keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,
        display : this._mosquitoCollection.getGeoEntityMd().getDisplayLabel()
      }];
      
      this._dateAttribs = [{
        klass :  this._groupByClass,
        accessor : this._groupByClass.COLLECTIONDATE,
      }];
      
      this._queryType = this._mainQueryClass;
      this.$initialize(selectableGroups, queryList);   
    }
  }
});
