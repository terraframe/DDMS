MDSS.collectionSearch = function(config) {
  var searchEl = Mojo.Util.isString(config.search) ? document.getElementById(config.search) : config.search;
  var concreteEl = Mojo.Util.isString(config.concrete) ? document.getElementById(config.concrete) : config.concrete;

  var listFunction = function(valueObject) {    
    var entityLabel = valueObject.getValue(Mojo.$.dss.vector.solutions.geo.GeoEntityView.ENTITYLABEL);    
    var geoId = valueObject.getValue(Mojo.$.dss.vector.solutions.geo.GeoEntityView.GEOID);    
    var moSubType = valueObject.getValue(Mojo.$.dss.vector.solutions.geo.GeoEntityView.MOSUBTYPE);    
    var typeDisplayLabel = valueObject.getValue("displayLabel");    
    var geoEntityLabel = MDSS.AbstractSelectSearch.formatDisplay2(entityLabel, typeDisplayLabel, geoId, moSubType)
  
    var collectionDate = valueObject.getValue(Mojo.$.dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONDATE);    
    var collectionId = valueObject.getValue(Mojo.$.dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONID);    

    var formattedDate = MDSS.Calendar.getLocalizedString(collectionDate);

    return geoEntityLabel + ', ' + formattedDate + ' - ' + collectionId;
  };

  var idFunction = function(valueObject) {
    return valueObject.getValue(Mojo.$.dss.vector.solutions.entomology.MosquitoCollectionView.ID);    
  };

  var displayFunction = function(valueObject) {    
    return valueObject.getValue(Mojo.$.dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONID);    
  };

  var searchFunction = Mojo.$.dss.vector.solutions.entomology.MosquitoCollectionView.searchByValueQuery;

  var selectEventHandler = function() {};
   
  var search = new MDSS.GenericSearch(searchEl, concreteEl, listFunction, displayFunction, idFunction, searchFunction, selectEventHandler);
  
  search.addParameter(config.type);
    
  var checkManualEntry = function() {
    var request = new MDSS.Request({
      searchEl : searchEl,
      concreteEl : concreteEl,
      onSend: function(){},
      onComplete: function(){},
      onFailure: function(){},
      onSuccess : function(result){
        if(result !== null) {
          searchEl.value = result.getCollectionId();
          concreteEl.value = result.getId();
        }
      }
    });

//    Mojo.$.dss.vector.solutions.entomology.ConcreteMosquitoCollection.getByCollectionId(request, searchEl.value);
  }

  YAHOO.util.Event.on(searchEl, 'blur', checkManualEntry, null, null);     
}

    

