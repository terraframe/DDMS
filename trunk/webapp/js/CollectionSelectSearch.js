MDSS.collectionSearch = function(config) {
  var searchEl = Mojo.Util.isString(config.search) ? document.getElementById(config.search) : config.search;
  var concreteEl = Mojo.Util.isString(config.concrete) ? document.getElementById(config.concrete) : config.concrete;

  var listFunction = function(view) {    

    var formattedDate = MDSS.Calendar.getLocalizedString(view.getCollectionDate());

    return view.getGeoEntityLabel() + ', ' + formattedDate + ' - ' + view.getCollectionId();
  };

  var idFunction = function(view) {
    return view.getConcreteId();
  };

  var displayFunction = function(view) {    
    return view.getCollectionId();
  };

  var searchFunction = Mojo.$.dss.vector.solutions.entomology.MosquitoCollectionView.searchCollection;

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

    

