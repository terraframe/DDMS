Mojo.Meta.newClass('MDSS.QuerySurvey', {

      Extends: MDSS.QueryBaseNew,
      
      Instance : {
      
        initialize : function(selectableGroups, queryList)
        {

      		this._groupByClass = dss.vector.solutions.intervention.monitor.SurveyPoint;
      		this._mainQueryClass = this._groupByClass.CLASS;
    	
          this._surveyPoint = new this._groupByClass;
          
          this._commonQueryClasses = [
                                     ];
          this._geoEntityAttribs = [
                                 {
                                   keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,
                                   display : this._surveyPoint.getGeoEntityMd().getDisplayLabel()
                                 },
                                 
                               ];
          
          this._dateAttribs = [
                               {
                              	 klass :  this._groupByClass,
                                 accessor : this._groupByClass.SURVEYDATE,
                               }
                              ];
          
          this._queryType = this._mainQueryClass;

          this.$initialize(selectableGroups, queryList);   
          
          var picker = this.getGeoPicker();      
          picker.setPolitical(true);
          picker.setSprayTargetAllowed(false);
          picker.addExtraUniversal('dss.vector.solutions.geo.generated.SentinelSite');
          
     
          }
    				


        }
 });
