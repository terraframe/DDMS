MDSS.collectionSearch = function(config) {
  var searchEl = Mojo.Util.isString(config.search) ? document.getElementById(config.search) : config.search;
  var concreteEl = Mojo.Util.isString(config.concrete) ? document.getElementById(config.concrete) : config.concrete;

  var listFunction = function(valueObject) {    
    var entityName = Mojo.$.dss.vector.solutions.geo.GeoEntityView.ENTITYNAME;
    var geoId = Mojo.$.dss.vector.solutions.geo.GeoEntityView.GEOID;
    var collectionId = Mojo.$.dss.vector.solutions.entomology.ConcreteMosquitoCollection.COLLECTIONID;
    var dateCollected = Mojo.$.dss.vector.solutions.entomology.ConcreteMosquitoCollection.DATECOLLECTED;

    var formattedDate = MDSS.Calendar.getLocalizedString(valueObject.getValue(dateCollected));

    return valueObject.getValue(entityName) + '('+ valueObject.getValue(geoId) + '), ' + formattedDate + ' - ' + valueObject.getValue(collectionId);
  };

  var idFunction = function(valueObject) {
    var id = Mojo.$.dss.vector.solutions.geo.GeoEntityView.ID;

    return valueObject.getValue(id);
  };

  var displayFunction = function(valueObject) {    
    var collectionId = Mojo.$.dss.vector.solutions.entomology.ConcreteMosquitoCollection.COLLECTIONID;

    return valueObject.getValue(collectionId);
  };

  var searchFunction = Mojo.$.dss.vector.solutions.entomology.AbstractMosquitoCollection.searchByCollectionId;

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

    Mojo.$.dss.vector.solutions.entomology.ConcreteMosquitoCollection.getByCollectionId(request, searchEl.value);
  }

  YAHOO.util.Event.on(searchEl, 'keyup', search.performSearch, search, search);
  YAHOO.util.Event.on(searchEl, 'blur', checkManualEntry, null, null);     
}

    

