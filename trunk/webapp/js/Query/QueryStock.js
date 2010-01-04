Mojo.Meta.newClass('MDSS.QueryStock', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

  		
  		this._groupByClass = Mojo.$.dss.vector.solutions.stock.StockEvent;
  		this._mainQueryClass = Mojo.$.dss.vector.solutions.stock.StockItem.CLASS,
	
      this._stock = new this._groupByClass();
      this._dateAttribute = new MDSS.QueryXML.Attribute(this._groupByClass.CLASS, this._groupByClass.EVENTDATE, this._groupByClass.EVENTDATE);
      
      this._startDateSelectable = new MDSS.QueryXML.Selectable(this._dateAttribute);
      
      this._endDateSelectable = new MDSS.QueryXML.Selectable(this._dateAttribute);



      this._commonQueryClasses = [
                                  //this._groupByClass.CLASS,
                                  Mojo.$.dss.vector.solutions.stock.StockItem.CLASS,
                                  Mojo.$.dss.vector.solutions.Person.CLASS,
                                  ];

      this._exclusionClasses = [];
      
      var tmpPerson = new  Mojo.$.dss.vector.solutions.Person();

      this._geoEntityAttribs = [
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.STOCKDEPOT,
                               display : this._stock.getStockDepotMd().getDisplayLabel()
                             },
                             
                           ];
      
      this._queryType = this._mainQueryClass;

      this.$initialize(selectableGroups, queryList);   
 
      var picker = this.getGeoPicker();
      picker.setPolitical(false);
      picker.setSprayTargetAllowed(false);
      },
      
    }
});
