Mojo.Meta.newClass('MDSS.QueryIndividualCases', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

  		
  		this._groupByClass = Mojo.$.dss.vector.solutions.intervention.monitor.IndividualCase;
      this._individualCase = new this._groupByClass();
  		this._mainQueryClass = this._groupByClass.CLASS;
  		this._individualInstance = Mojo.$.dss.vector.solutions.intervention.monitor.IndividualInstance;
  		


      this._commonQueryClasses = [
                                  this._groupByClass.CLASS,
                                  this._individualInstance.CLASS,
                                  Mojo.$.dss.vector.solutions.Person.CLASS,
                                  ];

      this._exclusionClasses = [];
      
      var tmpPerson = new  Mojo.$.dss.vector.solutions.Person();
      
      var tmpCase = new this._groupByClass;
      
      var tmpInstance = new this._individualInstance();
      
      this._dateAttribs = [
                           {
                          	 klass :  this._groupByClass,
                             accessor : this._groupByClass.DIAGNOSISDATE,
                           },
                           {
                          	 klass :  this._groupByClass,
                             accessor : this._groupByClass.CASEREPORTDATE,
                           },
                           {
                          	 klass :  this._groupByClass,
                             accessor : this._groupByClass.CASEENTRYDATE,
                           },
                           {
                             klass :  this._groupByClass,
                             accessor : this._groupByClass.SYMPTOMONSET,                       
                           },
                           {
                             klass :  this._individualInstance,
                             accessor : this._individualInstance.FACILITYVISIT,                       
                           },
                           {
                             klass :  this._individualInstance,
                             accessor : this._individualInstance.ADMISSIONDATE,                       
                           },
                           {
                             klass :  this._individualInstance,
                             accessor : this._individualInstance.RELEASEDATE,                       
                           },
                           {
                             klass :  this._individualInstance,
                             accessor : this._individualInstance.TESTSAMPLEDATE,                       
                           },
                           {
                             klass :  this._individualInstance,
                             accessor : this._individualInstance.LABTESTDATE,                       
                           },
                           
                         ];

      
      

      this._geoEntityAttribs = [
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.PROBABLESOURCE,
                               display : tmpCase.getProbableSourceMd().getDisplayLabel()
                             },
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.RESIDENCE,
                               display : tmpCase.getResidenceMd().getDisplayLabel()
                             },
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.WORKPLACE,
                               display : tmpCase.getWorkplaceMd().getDisplayLabel()
                             },
                             {
                               keyName :  this._individualInstance.CLASS+'.'+this._individualInstance.HEALTHFACILITY,
                               display : tmpInstance.getHealthFacilityMd().getDisplayLabel()
                             },     
                             
                           ];
      
      this._queryType = this._mainQueryClass;
      
      this.$initialize(selectableGroups, queryList);
      
      var picker = this.getGeoPicker();      
      picker.setPolitical(true);
      picker.setSprayTargetAllowed(false);
      picker.addExtraUniversal('dss.vector.solutions.geo.generated.HealthFacility*');  
 
      },
      
      _getBrowserRootClass : function(attribute)
      {
        var type = attribute.getType();
        if(type === 'dss.vector.solutions.Person')
        {
          return 'dss.vector.solutions.PersonView';
        }
        else
        {
          return attribute.getType();
        }
      }

      
	}
});
