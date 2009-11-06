Mojo.Meta.newClass('MDSS.QueryIndividualIPT', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {


      this._individualIPT = Mojo.$.dss.vector.solutions.intervention.monitor.IndividualIPT;
      this._dateAttribute = new MDSS.QueryXML.Attribute(this._individualIPT.CLASS, this._individualIPT.STARTDATE, this._individualIPT.STARTDATE);
      
      var startDateAttr = new MDSS.QueryXML.Attribute(this._individualIPT.CLASS, this._individualIPT.STARTDATE, this._individualIPT.STARTDATE);
      this._startDateSelectable = new MDSS.QueryXML.Selectable(startDateAttr);
      
      var endDateAttr = new MDSS.QueryXML.Attribute(this._individualIPT.CLASS, this._individualIPT.ENDDATE, this._individualIPT.ENDDATE);
      this._endDateSelectable = new MDSS.QueryXML.Selectable(endDateAttr);

      this._mainQueryClass = this._individualIPT.CLASS;
      this._groupByClass = this._individualIPT;

      this._commonQueryClasses = [
                                  this._individualIPT.CLASS,
                                  "dss.vector.solutions.intervention.monitor.IndividualIPTCase",
                                  Mojo.$.dss.vector.solutions.Person.CLASS,
                                  ];

      this._exclusionClasses = [];

      
      this.$initialize(selectableGroups, queryList);   
      

      this._queryType = 'QueryIndividualIPT';

      this._reportQueryType = 'QueryIndividualIPT';
      }
    }
});
