Mojo.Meta.newClass('MDSS.QueryPupalContainerCollection', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

  		
  		this._groupByClass = dss.vector.solutions.entomology.PupalCollection;
  		this._mainQueryClass = this._groupByClass.CLASS;
	
      this._larvacide = new this._groupByClass();
      this._dateAttribute = new MDSS.QueryXML.Attribute(this._groupByClass.CLASS, this._groupByClass.STARTDATE, this._groupByClass.STARTDATE);
      
      var startDateAttr = new MDSS.QueryXML.Attribute(this._groupByClass.CLASS, this._groupByClass.STARTDATE, this._groupByClass.STARTDATE);
      this._startDateSelectable = new MDSS.QueryXML.Selectable(startDateAttr);
      
      var endDateAttr = new MDSS.QueryXML.Attribute(this._groupByClass.CLASS, this._groupByClass.ENDDATE, this._groupByClass.ENDDATE);
      this._endDateSelectable = new MDSS.QueryXML.Selectable(endDateAttr);

      this._commonQueryClasses = [
                                  this._groupByClass.CLASS,
                                  ];

      this._exclusionClasses = [];
      

      this._geoEntityAttribs = [
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,
                               display : this._larvacide.getGeoEntityMd().getDisplayLabel()
                             },
                             
                           ];
      
      this._queryType = this._mainQueryClass;

      this.$initialize(selectableGroups, queryList);   
 
      var picker = this.getGeoPicker();      
      picker.setPolitical(false);
      picker.setSprayTargetAllowed(false);
      },
     
      _getBrowserRootClass : function(attribute)
      {
        var type = attribute.getType();
        if(type === 'dss.vector.solutions.entomology.PupalPremise')
        {
          return 'dss.vector.solutions.entomology.PupalCollectionView';
        }
        else
        {
          return this.$_getBrowserRootClass(attribute);
        }
      }
    }
});
