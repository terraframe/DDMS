Mojo.Meta.newClass('MDSS.QueryStock', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

  		
  		this._groupByClass = Mojo.$.dss.vector.solutions.stock.StockEvent;
  		this._mainQueryClass = Mojo.$.dss.vector.solutions.stock.StockItem.CLASS,
	
      this._stock = new this._groupByClass();
      

      this._dateAttribs = [
                           {
                          	 klass :  this._groupByClass,
                             accessor : this._groupByClass.EVENTDATE,
                           }
                          ];
      

      this._commonQueryClasses = [
                                  this._groupByClass.CLASS,
                                  Mojo.$.dss.vector.solutions.stock.StockItem.CLASS,
                                  Mojo.$.dss.vector.solutions.Person.CLASS,
                                  ];

      this._exclusionClasses = [];
      
      var tmpPerson = new  Mojo.$.dss.vector.solutions.Person();

      this._geoEntityAttribs = [
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.STOCKDEPOT,
                               display : com.runwaysdk.Localize.get("Geo_Entity") //this._stock.getStockDepotMd().getDisplayLabel()
                             },
                             
                           ];
      
      this._queryType = this._mainQueryClass;

      this.$initialize(selectableGroups, queryList);   
 
      var picker = this.getGeoPicker();
      picker.setPolitical(true);
      picker.setSprayTargetAllowed(false);
      picker.addExtraUniversal('dss.vector.solutions.geo.generated.StockDepot*');
      
      this._exclusionClasses.shift();
      }
      
    }
});
