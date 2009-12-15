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

      this._sprayData = Mojo.$.dss.vector.solutions.irs.SprayData;
      var sprayStatus = Mojo.$.dss.vector.solutions.irs.SprayStatus;
      this._dateAttribute = new MDSS.QueryXML.Attribute(this._sprayData.CLASS, this._sprayData.SPRAYDATE, this._sprayData.SPRAYDATE);
      this._startDateSelectable = new MDSS.QueryXML.Selectable(this._dateAttribute);
      this._endDateSelectable = new MDSS.QueryXML.Selectable(this._dateAttribute);

      //this screen can query two diffrent classes, so we have a place to store the selected class
      this._mainQueryClass = Mojo.$.dss.vector.solutions.irs.SprayStatus.CLASS;
      
    	this._groupByClass = Mojo.$.dss.vector.solutions.irs.SprayStatus;
    	
    	//this._sprayStatus = new this._groupByClass;

      this._commonQueryClasses = [Mojo.$.dss.vector.solutions.irs.SprayData.CLASS,
                                  Mojo.$.dss.vector.solutions.irs.InsecticideBrand.CLASS,
                                  Mojo.$.dss.vector.solutions.irs.AbstractSpray.CLASS,
                                  Mojo.$.dss.vector.solutions.irs.ActorSpray.CLASS];
      
      this._geoEntityAttribs = [
                                {
                                  keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,
                                  display : "GeoEntitny"//this._sprayStatus.getGeoentityMd().getDisplayLabel()
                                },    
                                
                              ];

      this._exclusionClasses = [];

      //this._dataQueryFunction = Mojo.$.dss.vector.solutions.irs.AbstractSpray.queryIRS;

      this._queryType = 'QueryIRS';

      this._reportQueryType = 'QueryIRS';
      

      this.$initialize(selectableGroups, queryList); 
    }
	}
    
});