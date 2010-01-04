Mojo.Meta.newClass('MDSS.QuerySurvey', {

      Extends: MDSS.QueryBaseNew,
      
      Instance : {
      
        initialize : function(selectableGroups, queryList)
        {

      		this._groupByClass = Mojo.$.dss.vector.solutions.intervention.monitor.SurveyPoint;
      		this._mainQueryClass = this._groupByClass.CLASS;
    	

          this._SurveyPoint = Mojo.$.dss.vector.solutions.intervention.monitor.SurveyPoint;
          this._surveyPoint = new this._SurveyPoint();
          
          this._Household = Mojo.$.dss.vector.solutions.intervention.monitor.Household;
          this._household = new this._Household();
          
          this._Person = Mojo.$.dss.vector.solutions.intervention.monitor.SurveyedPerson;
          this._person = new this._Person();
          
          
          this._commonQueryClasses = [
                                     ];
          this._geoEntityAttribs = [
                                 {
                                   keyName :  this._surveyPoint.CLASS+'.'+this._surveyPoint.GEOENTITY,
                                   display : this._surveyPoint.getGeoEntityMd().getDisplayLabel()
                                 },
                                 
                               ];
          
          this._dateAttribs = [
                               {
                              	 klass :  this._groupByClass,
                                 accessor : this._groupByClass.SURVEYDATE,
                               }
                              ];
          
          this._queryType = 'QueryResistance';

          this._reportQueryType = 'QueryResistance';
          
          this.$initialize(selectableGroups, queryList);   
          
          var picker = this.getGeoPicker();      
          picker.setPolitical(false);
          picker.setSprayTargetAllowed(true);
          picker.addExtraUniversal('dss.vector.solutions.geo.generated.SentinelSite');
     
          }
    				
          

        }
 });
