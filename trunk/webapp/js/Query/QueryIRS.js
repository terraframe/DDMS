Mojo.Meta.newClass('MDSS.QueryIRS', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {

    initialize : function(selectableGroups, queryList)
    {
 

      // list of columns that have been added before a call to render()
      this._preconfiguredColumns = [];

      // START: query objects that dictate state of the query.

      this._countSelectable = null;
      this._ratioSelectable = null;

      this._specieGroupSelectables = {};
      this._visibleSelectables = {};
      this._whereOptions = {};
      this._visibleAggregateSelectables = {};

      var abstractSpray = dss.vector.solutions.irs.AbstractSpray;

      //this screen can query two diffrent classes, so we have a place to store the selected class
      this._mainQueryClass = abstractSpray.CLASS;
      
    	this._groupByClass = abstractSpray;
    	
      var abstractSprayInstance = new dss.vector.solutions.irs.OperatorSpray();

      this._commonQueryClasses = [];
      
      this._geoEntityAttribs = [
                                {
                                  keyName :  abstractSpray.CLASS+'.' + abstractSpray.GEOENTITY,
                                  display :  abstractSprayInstance.getGeoEntityMd().getDisplayLabel()
                                },                                    
                              ];
      this._dateAttribs = [
                           {
                          	 klass :  dss.vector.solutions.irs.OperatorSpray,
                             accessor : abstractSpray.SPRAYDATE,
                           }
                          ];
      

      this._exclusionClasses = [];

      this._queryType = this._mainQueryClass;

      this.$initialize(selectableGroups, queryList); 
      
      var picker = this.getGeoPicker();      
      picker.setPolitical(false);
      picker.setSprayTargetAllowed(true);
    },
    
    _getBrowserRootClass : function(attribute)
    {
      var type = attribute.getType();
      if(type === 'dss.vector.solutions.irs.AbstractSpray' || attribute.getAttributeName() === 'surfaceType')
      {
        return 'dss.vector.solutions.irs.OperatorSprayView';
      }
      else if(type === 'dss.vector.solutions.irs.InsecticideBrand')
      {
        return type;
      }
      else
      {
        return this.$_getBrowserRootClass(attribute);
      }
    }
    
	}
    
});