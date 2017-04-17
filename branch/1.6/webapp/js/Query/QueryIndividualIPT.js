Mojo.Meta.newClass('MDSS.QueryIndividualIPT', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

  		
  		this._groupByClass = Mojo.$.dss.vector.solutions.intervention.monitor.IndividualIPT;
  		this._mainQueryClass = this._groupByClass.CLASS;
	
      this._individualIPT = new this._groupByClass();


      this._dateAttribs = [
                           {
                          	 klass :  this._groupByClass,
                             accessor : this._groupByClass.SERVICEDATE,
                           }
                          ];


      this._commonQueryClasses = [
                                  "dss.vector.solutions.intervention.monitor.IndividualIPTCase",
                                  Mojo.$.dss.vector.solutions.Person.CLASS,
                                  ];

      this._exclusionClasses = [];
      
      var tmpPerson = new  Mojo.$.dss.vector.solutions.Person();

      this._geoEntityAttribs = [
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.FACILITY,
                               display : this._individualIPT.getFacilityMd().getDisplayLabel()
                             },
                             {
                               keyName :  Mojo.$.dss.vector.solutions.Person.CLASS+'.'+Mojo.$.dss.vector.solutions.Person.RESIDENTIALGEOENTITY,
                               display : tmpPerson.getResidentialGeoEntityMd().getDisplayLabel()
                             },
                             {
                               keyName :  Mojo.$.dss.vector.solutions.Person.CLASS+'.'+Mojo.$.dss.vector.solutions.Person.WORKGEOENTITY,
                               display : tmpPerson.getWorkGeoEntityMd().getDisplayLabel()
                             }        
                             
                           ];
      
      this._queryType = this._mainQueryClass;

      this.$initialize(selectableGroups, queryList);   

      var picker = this.getGeoPicker();      
      picker.setPolitical(false);
      picker.setSprayTargetAllowed(false);
      }
      

    }
});
