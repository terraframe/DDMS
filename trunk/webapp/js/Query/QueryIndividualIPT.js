Mojo.Meta.newClass('MDSS.QueryIndividualIPT', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

  		
  		this._groupByClass = Mojo.$.dss.vector.solutions.intervention.monitor.IndividualIPT;
  		this._mainQueryClass = this._groupByClass.CLASS;
	
      this._individualIPT = new this._groupByClass();
      this._dateAttribute = new MDSS.QueryXML.Attribute(this._groupByClass.CLASS, this._groupByClass.STARTDATE, this._groupByClass.STARTDATE);
      
      var startDateAttr = new MDSS.QueryXML.Attribute(this._groupByClass.CLASS, this._groupByClass.STARTDATE, this._groupByClass.STARTDATE);
      this._startDateSelectable = new MDSS.QueryXML.Selectable(startDateAttr);
      
      var endDateAttr = new MDSS.QueryXML.Attribute(this._groupByClass.CLASS, this._groupByClass.ENDDATE, this._groupByClass.ENDDATE);
      this._endDateSelectable = new MDSS.QueryXML.Selectable(endDateAttr);



      this._commonQueryClasses = [
                                  this._groupByClass.CLASS,
                                  "dss.vector.solutions.intervention.monitor.IndividualIPTCase",
                                  Mojo.$.dss.vector.solutions.Person.CLASS,
                                  ];

      this._exclusionClasses = [];

      
      this._geoEntityAttribs = [
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.FACILITY,
                               display : this._individualIPT.getFacilityMd().getDisplayLabel()
                             }        
                           ];
      
      this._queryType = 'QueryIndividualIPT';

      this._reportQueryType = 'QueryIndividualIPT';
      
      this.$initialize(selectableGroups, queryList);   
 
      }
    }
});
