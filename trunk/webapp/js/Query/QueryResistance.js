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
                                 dss.vector.solutions.entomology.assay.AbstractAssay.CLASS,
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
                           },
                           {
                          	 klass :  dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay,
                             accessor : dss.vector.solutions.entomology.assay.AbstractAssay.TESTDATE,
                           }
                          ];
      
      this._queryType = this._mainQueryClass;

      this.$initialize(selectableGroups, queryList);   
      
      //remove collection from exclusion classes so collection selectables will not be unchecked when switching assay types
      this._exclusionClasses.shift();
       
      //we overide the xmlToValueQueryClass since it is not the same as the mainQueryClass for this page 
      this._xmlToValueQueryClass = dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS;
      
      var picker = this.getGeoPicker();      
      picker.setPolitical(false);
      picker.setSprayTargetAllowed(false);
 
      },
				
       /**
       * Helper method to add Entomology attributes to selectables and as a column.
       */
      _addVisibleAttribute : function(attribute)
      {
        var attributeName = attribute.getAttributeName();

        if(attribute.mainQueryClass)
        {
          this._mainQueryClass = attribute.mainQueryClass;
        }

        if(attribute.getType() == 'sqlcharacter'){
          var selectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlcharacter('', attributeName, attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));
          selectable.attribute = attribute;
          var column = new YAHOO.widget.Column({ key: attribute.getKey(),label: attribute.getDisplayLabel()});
           column.attribute = attribute;
        }else
        if(attribute.getType() == 'sqlinteger'){
          var selectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlinteger('', attributeName, attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));
          selectable.attribute = attribute;
          var column = new YAHOO.widget.Column({ key: attribute.getKey(),label: attribute.getDisplayLabel()});
           column.attribute = attribute;
      	}else
        if(attribute.getType() == 'sqlfloat'){
          var selectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlfloat('', attributeName, attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));
          selectable.attribute = attribute;
          var column = new YAHOO.widget.Column({ key: attribute.getKey(),label: attribute.getDisplayLabel()});
           column.attribute = attribute;
        }else
        if(attribute.getType() == 'sqldouble'){
          var selectable = new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqldouble('', attributeName, attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));
          selectable.attribute = attribute;
          var column = new YAHOO.widget.Column({ key: attribute.getKey(),label: attribute.getDisplayLabel()});
           column.attribute = attribute;
        }
        else
        {
          var selectable = attribute.getSelectable(true);
          selectable.attribute = attribute;
          var column = new YAHOO.widget.Column(attribute.getColumnObject());
           column.attribute = attribute;
        }

        column = this._queryPanel.insertColumn(column);

        this._visibleSelectables[attribute.getKey()] = selectable;
      },

    }
});
