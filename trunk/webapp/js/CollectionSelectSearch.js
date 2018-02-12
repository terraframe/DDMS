#-------------------------------------------------------------------------------
# Copyright (C) 2018 IVCC
# 
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
#-------------------------------------------------------------------------------
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

  var selectEventHandler = function(selected) {
    if(config.handler != null) {
      config.handler.handleEvent(selected);
    }
  };
   
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

    

