/*
 * Copyright (C) 2008 IVCC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
Mojo.Meta.newClass('MDSS.QueryMosquitoCollections', {
  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {
      this._groupByClass = Mojo.$.dss.vector.solutions.entomology.MosquitoCollection;
      this._mainQueryClass = this._groupByClass.CLASS;  
      this._mosquitoCollection = new this._groupByClass();      
      this._showRatioSelectable = true;
      this._commonQueryClasses = []; //[Mojo.$.dss.vector.solutions.entomology.SubCollection.CLASS];
      this._exclusionClasses = [];
      this._geoEntityAttribs = [{
        keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,
        display : com.runwaysdk.Localize.get("Geo_Entity")
      }];
      
      this._dateAttribs = [{
        klass :  this._groupByClass,
        accessor : this._groupByClass.COLLECTIONDATE,
      }];
      
      this._queryType = this._mainQueryClass;
      this.$initialize(selectableGroups, queryList);   
    },
    _getBrowserRootClass : function(attribute)
    {
      if(attribute.getKey() === 'collectionRound')
      {
        return 'dss.vector.solutions.entomology.MosquitoCollectionView';
      }
      else if(attribute.getKey() === 'collectionType')
      {
        return 'dss.vector.solutions.entomology.MosquitoCollectionView';
      }
      else if(attribute.getKey() === 'taxon')
      {
        return 'dss.vector.solutions.entomology.SubCollectionView';
      }
      else
      {
        return this.$_getBrowserRootClass(attribute); 
      }
    },
    _getBrowserRootAttribute : function(attribute)
    {
      if(attribute.getKey() === 'collectionRound')
      {
        return this._mosquitoCollection.getCollectionRoundMd().getName()
      }
      else if(attribute.getKey() === 'collectionType')
      {
        return this._mosquitoCollection.getCollectionTypeMd().getName()
      }
      
      return attribute.getKey() === 'taxon' ? 'taxon' : 
        this.$_getBrowserRootAttribute(attribute);
    }
  }
});
