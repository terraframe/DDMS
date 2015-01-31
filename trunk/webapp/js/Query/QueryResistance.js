Mojo.Meta.newClass('MDSS.QueryResistance', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

  		this._groupByClass = Mojo.$.dss.vector.solutions.entomology.MosquitoCollection;
  		this._mainQueryClass = this._groupByClass.CLASS;
	
      this._mosquitoCollection = new this._groupByClass();

      this._commonQueryClasses = [
                                 // Mojo.$.dss.vector.solutions.general.Insecticide.CLASS,
                                 dss.vector.solutions.entomology.assay.AbstractAssay.CLASS
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
                             accessor : this._groupByClass.COLLECTIONDATE
                           },
                           {
                             klass :  this._groupByClass,
                             accessor : this._groupByClass.DATELASTSPRAYED
                           },
                           {
                          	 klass :  dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay,
                             accessor : dss.vector.solutions.entomology.assay.AbstractAssay.TESTDATE
                           }
                          ];
      
      this._queryType = this._mainQueryClass;
      
      this.$initialize(selectableGroups, queryList);   
      
      //remove collection from exclusion classes so collection selectables will not be unchecked when switching assay types
      this._exclusionClasses.shift();
       
      //we overide the xmlToValueQueryClass since it is not the same as the mainQueryClass for this page 
      this._xmlToValueQueryClass = dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS;
      
        var picker = this.getGeoPicker();
        picker.setPolitical(true);
        picker.setPopulated(false);
        picker.setSprayTargetAllowed(false);
        picker.setUrban(false);
        picker.addExtraUniversal('dss.vector.solutions.geo.generated.CollectionSite');
        picker.addExtraUniversal('dss.vector.solutions.geo.generated.SentinelSite');
      },
      
      _getBrowserRootClass : function(attribute)
      {
        var type = attribute.getType();
        if(type === 'dss.vector.solutions.entomology.MosquitoCollection')
        {
          return 'dss.vector.solutions.entomology.SearchMosquitoCollectionView';
        }
        else
        {
          return attribute.getType();
        }
      },
      
      /**
       * Due to limitations in the Query API, we need to not set WHERE criteria
       * as normal on the assay_type CharacterSQL. By temporarily setting the
       * selectable.attribute value to null, we bypass criteria addition in
       * the parent class. Instead we add it manually to the JSON config object.
       * This is a horrible hack and it should be replaced!
       */
      _constructQuery : function(formapping)
      {
        var attr;
        var assayType = this._visibleSelectables['assay_type'];
        var selected = [];
        if(assayType)
        {
          attr = assayType.attribute;
          assayType.attribute = null;
          
          var items = this._menus['assay_type_li'];
          for(var i=0; i<items.length; i++)
          {
            var item = items[i];
            if(item.checked)
            {
              selected.push(item.uuid);
            }
          }
          
        }
        this._config.setProperty('assay_type', selected);
        
        var query = this.$_constructQuery(formapping);
        
        if(assayType)
        {
          assayType.attribute = attr;
        }
        
        return query;
      },
      
      _loadQueryState : function(view)
      {
        this.$_loadQueryState(view);
        
        var types = this._config.getProperty('assay_type');
        for(var i=0; i<types.length; i++)
        {
          var item = this._menuItems['assay_type-'+types[i]];
          item.checked = true;
          display = item.onclick.obj.display;
          this._queryPanel.addWhereCriteria('assay_type', item.uuid, display);
        }
      },
      
      ensureExclusion : function(adult, larvae, knockdown, triggerId)
      {
        var toCheck = null;
        if(adult.contains(triggerId))
        {
          toCheck = 'dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay';
        }
        else if(larvae.contains(triggerId))
        {
          toCheck = 'dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay';
        }
        else if(knockdown.contains(triggerId))
        {
          toCheck = 'dss.vector.solutions.entomology.assay.KnockDownAssay';
        }
        
        if(toCheck != null)
        {
          var display = null;
          var items = this._menus['assay_type_li'];
          for(var i=0; i<items.length; i++)
          {
            var item = items[i];
            if(item.uuid === toCheck)
            {
              item.checked = true;
              display = item.onclick.obj.display;
              this._queryPanel.addWhereCriteria('assay_type', toCheck, display);
            }
            else
            {
              item.checked = false;
              this._queryPanel.removeWhereCriteria('assay_type', item.uuid);
            }
          }
          
        }
      }
    }
});
