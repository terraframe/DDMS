Mojo.Meta.newClass('MDSS.QueryResistance', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

  		this._groupByClass = Mojo.$.dss.vector.solutions.entomology.MosquitoCollection;
  		this._mainQueryClass = this._groupByClass.CLASS;
	
      this._mosquitoCollection = new this._groupByClass();

      this._commonQueryClasses = [
                                  Mojo.$.dss.vector.solutions.general.Insecticide.CLASS,
                                  Mojo.$.dss.vector.solutions.entomology.assay.AbstractAssay.CLASS,
                                 ];
      this._geoEntityAttribs = [
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,
                               display : this._mosquitoCollection.getGeoEntityMd().getDisplayLabel()
                             },
                             
                           ];
      
      this._dateAttribs = [
                           {
                          	 klass :  this._groupByClass,
                             accessor : this._groupByClass.COLLECTIONDATE,
                           }
                          ];
      
      this._queryType = 'QueryResistance';

      this._reportQueryType = 'QueryResistance';
      
      this.$initialize(selectableGroups, queryList);   
      
      //remove collection from exclusion classes so collection selectables will not be unchecked when switching assay types
      this._exclusionClasses.shift();
       
      //we overide the xmlToValueQueryClass since it is not the same as the mainQueryClass for this page 
      this._xmlToValueQueryClass = Mojo.$.dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS;
      
      var picker = this.getGeoPicker();      
      picker.setPolitical(false);
      picker.setSprayTargetAllowed(false);
 
      }
				
      

    }
});
