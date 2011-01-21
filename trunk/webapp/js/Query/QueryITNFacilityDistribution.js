Mojo.Meta.newClass('MDSS.QueryITNFacilityDistribution', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

      this._itn = Mojo.$.dss.vector.solutions.intervention.monitor.ITNDistribution;

      this._mainQueryClass = this._itn.CLASS;
      
      this._groupByClass = Mojo.$.dss.vector.solutions.intervention.monitor.ITNDistribution;

      this._dateAttribs = [
                           {
                          	 klass :  this._groupByClass,
                          	 accessor : this._groupByClass.DISTRIBUTIONDATE,
                           }
                           ];
      
      this._commonQueryClasses = [
                                  Mojo.$.dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroup.CLASS,
                                  Mojo.$.dss.vector.solutions.Person.CLASS,
                                  ];

      this._exclusionClasses = [];
      
      
      
      var instance = new this._itn();
      
      var tmpPerson = new  Mojo.$.dss.vector.solutions.Person();

      this._geoEntityAttribs = [
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.FACILITY,
                               display : instance.getFacilityMd().getDisplayLabel()
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
      

      /**
       * Returns the type of query.
       */
      this._queryType = this._mainQueryClass;
      
      this.$initialize(selectableGroups, queryList);   

      var picker = this.getGeoPicker();      
      picker.setPolitical(true);
      picker.setSprayTargetAllowed(false);
      picker.addExtraUniversal(Mojo.$.dss.vector.solutions.geo.generated.HealthFacility.CLASS);            
    }
  }
});
