Mojo.Meta.newClass('MDSS.QueryMosquitoCollections', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

  		this._groupByClass = Mojo.$.dss.vector.solutions.entomology.MosquitoCollection;
  		this._mainQueryClass = this._groupByClass.CLASS;
	
      this._mosquitoCollection = new this._groupByClass();
      
      this._showRatioSelectable = true;

      this._commonQueryClasses = [
                                  Mojo.$.dss.vector.solutions.entomology.SubCollection.CLASS,
                                  ];

      this._exclusionClasses = [];
      

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
      
      this._queryType = this._mainQueryClass;

      this.$initialize(selectableGroups, queryList);   
 
      },

      /**
       * Handler to toggle visible attributes as selectables
       */
      _visibleAttributeHandler : function(e, attribute)
      {
        var check = e.target;
        var liTarget = YAHOO.util.Dom.getAncestorByTagName(check, "LI");
        if(check.checked)
        {
          this._uncheckAllNotInGroup(check);
          if(check.id.indexOf('abundance_1') > -1)
          {
          	this._checkBox('taxon');
          	this._checkBox('collectionMethod_ab');
          }
        	
          this._addVisibleAttribute(attribute);
          var select = check.nextSibling;
          select.selectedIndex = 0;
          select.disabled = false;
        
        }
        else
        {
          this._removeVisibleAttribute(attribute, true, true, true);
          var select = check.nextSibling;
          select.selectedIndex = 0;
          select.disabled = true;
          var menus = this._menus[liTarget.id];
          if(menus)
          {
            Mojo.Iter.forEach(menus, function(ck){
              //for display
            	if(ck.checked) ck.checked = false;
            }, this); 
          }
          
          this.clearBrowserTerms(attribute);
          
          if(check.id == 'taxon')
          {
          	_uncheckAllByClass(liTarget,'ab')
          }
        }
      },
      

    }
});
