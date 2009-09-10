// Author: Justin Smethie
Mojo.Meta.newClass('MDSS.TeamSearch', {
  Instance: {

    initialize: function(geoId, teamSelect, operatorSelect, leaderSelect) {
      // Constructor code  
      this.geoId = geoId;
      this.teamSelect = teamSelect;
      this.operatorSelect = operatorSelect;
      this.leaderSelect = leaderSelect;
      this._pollingId = null;     
      
      YAHOO.util.Event.addListener(this.teamSelect, "click", this.populateTeamMembers, this, true); 
    },
  
    // Public getter functions
    getGeoId : function () {
      return this.geoId;
    },
    
    getTeamSelect : function () {
      return this.teamSelect;
    },

    getOperatorSelect : function () {
      return this.operatorSelect;
    },
    
    getLeaderSelect : function () {
      return this.leaderSelect;
    },
    
    getTeamId : function () {
      return this.teamId;
    },
    
    // Clear a select list of all options and disables it
    clearSelect : function(select) {
      if(select) {
    	Selectbox.removeAllOptions(select);                
        select.disabled=true;   
      }      
    },
    
    clearTeamMembers : function() {
      this.clearSelect(this.getOperatorSelect());
      this.clearSelect(this.getLeaderSelect());    	
    },
    
    clearAll : function() {
      this.clearSelect(this.getTeamSelect());    	
      this.clearSelect(this.getOperatorSelect());
      this.clearSelect(this.getLeaderSelect());    	
    },
        
    // Private methods
    _populateOperatorList : function (select, operators) {
      if(select) {
        // Remove all of the current options in the select list
        Selectbox.removeAllOptions(select);

        // Add the new options retrieved from the AJAX call
        for(var i=0; i< operators.length; i++) {
          var label = operators[i].getOperatorId() + " - " + operators[i].getLastName() + ", " + operators[i].getFirstName();

          Selectbox.addOption(select, label, operators[i].getActorId(), false);
        }          

        // Enable the select list
        select.disabled=false;
      }
    },
    
    populateTeamMembers : function (){
      this.clearTeamMembers();
    	
      if(this.getTeamSelect().value != '')
      {
        var request = new MDSS.Request({
          obj : this,
          onSend: function(){},
          onComplete: function(){},
          onFailure : function(){
        	this.obj.clearTeamMembers();
          },
          onProblemExceptionDTO : function(){
        	this.obj.clearTeamMembers();
          },          
          onSuccess : function(operators){
        	this.obj._populateOperatorList(this.obj.getOperatorSelect(), operators);
        	this.obj._populateOperatorList(this.obj.getLeaderSelect(), operators);
          }
        });

        Mojo.$.dss.vector.solutions.irs.SprayTeam.getTeamMemberViews(request, this.getTeamSelect().value);
      }  
    },
    
    populateSprayTeams : function(){ 
      this.clearAll();
    	
      if(this.getGeoId().value != '')
      {
    	var request = new MDSS.Request({
          obj : this,    		
          onSend: function(){},
          onComplete: function(){},
          onFailure : function(){
        	this.obj.clearAll();
          },
          onProblemExceptionDTO : function(){
        	this.obj.clearAll();
          },          
          onSuccess : function(teams){
            // Remove all of the current options in the select list
            Selectbox.removeAllOptions(this.obj.getTeamSelect());

            Selectbox.addOption(this.obj.getTeamSelect(), 'Select Team', '', false);
              
            // Add the new options retrieved from the AJAX call
            for(var i=0; i< teams.length; i++) {
              Selectbox.addOption(this.obj.getTeamSelect(), teams[i].getTeamId(), teams[i].getId(), false);
            }          

            // Enable the select list
            this.obj.getTeamSelect().disabled = false;
          }
        });

        Mojo.$.dss.vector.solutions.irs.SprayTeam.findByLocation(request, this.getGeoId().value);
      }
    }      
  }   
});
