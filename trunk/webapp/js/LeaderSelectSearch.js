
Mojo.Meta.newClass('MDSS.TeamMemberSearch', { // Implements CallBack
  Instance: {
    initialize : function(){
    },

    _listFunction : function(valueObject) {
      var firstName = valueObject.getValue(Mojo.$.dss.vector.solutions.irs.TeamMemberView.FIRSTNAME);
      var lastName = valueObject.getValue(Mojo.$.dss.vector.solutions.irs.TeamMemberView.LASTNAME);
      var memberId = valueObject.getValue(Mojo.$.dss.vector.solutions.irs.TeamMemberView.MEMBERID);

      return firstName + ' ' + lastName + ' - ' + memberId;
    },

    _idFunction : function(valueObject) {
      var id = valueObject.getValue(Mojo.$.dss.vector.solutions.irs.TeamMemberView.ID);

      return id;
    },

    _displayFunction : function(valueObject) {
      var firstName = valueObject.getValue(Mojo.$.dss.vector.solutions.irs.TeamMemberView.FIRSTNAME);
      var lastName = valueObject.getValue(Mojo.$.dss.vector.solutions.irs.TeamMemberView.LASTNAME);

      return firstName + ' ' + lastName;
    }    
  }
});

Mojo.Meta.newClass('MDSS.SprayLeaderSearch', { // Implements CallBack
  Extends : MDSS.TeamMemberSearch,
  Instance: {
    initialize : function(config){
      var searchEl = Mojo.Util.isString(config.search) ? document.getElementById(config.search) : config.search;
      var concreteEl = Mojo.Util.isString(config.concrete) ? document.getElementById(config.concrete) : config.concrete;
    
      var dF = Mojo.Util.bind(this, this._displayFunction);
      var iF = Mojo.Util.bind(this, this._idFunction);
      var lF = Mojo.Util.bind(this, this._listFunction);
      var sF = Mojo.Util.bind(this, this._searchFunction);
      var sEH = Mojo.Util.bind(this, this._selectEventHandler);
    
      this.search = new MDSS.GenericSearch(searchEl, concreteEl, lF, dF, iF, sF, sEH);
    },
    
    _searchFunction : function(request, value) {
       Mojo.$.dss.vector.solutions.irs.TeamMember.searchForLeader(request, value);
    },
    
    _selectEventHandler : function() {
    }
  }
});

Mojo.Meta.newClass('MDSS.OperatorSearch', { // Implements CallBack
  Extends : MDSS.TeamMemberSearch,
  Instance: {
    initialize : function(config){
      var searchEl = Mojo.Util.isString(config.search) ? document.getElementById(config.search) : config.search;
      var concreteEl = Mojo.Util.isString(config.concrete) ? document.getElementById(config.concrete) : config.concrete;
    
      var dF = Mojo.Util.bind(this, this._displayFunction);
      var iF = Mojo.Util.bind(this, this._idFunction);
      var lF = Mojo.Util.bind(this, this._listFunction);
      var sF = Mojo.Util.bind(this, this._searchFunction);
      var sEH = Mojo.Util.bind(this, this._selectEventHandler);
    
      this.search = new MDSS.GenericSearch(searchEl, concreteEl, lF, dF, iF, sF, sEH);
    },
    
    _searchFunction : function(request, value) {
       Mojo.$.dss.vector.solutions.irs.TeamMemberView.searchOperators(request, value);
    },
    
    _selectEventHandler : function() {
    }
  }
});


Mojo.Meta.newClass('MDSS.UnassignedOperatorsSearch', { // Implements CallBack
  Extends : MDSS.TeamMemberSearch,
  Instance: {
    initialize : function(config){
      this._labelEl = Mojo.Util.isString(config.label) ? document.getElementById(config.label) : config.label;
      this._searchEl = Mojo.Util.isString(config.search) ? document.getElementById(config.search) : config.search;      
      this._concreteEl = Mojo.Util.isString(config.concrete) ? document.getElementById(config.concrete) : config.concrete;
      this._assigned = Mojo.Util.isString(config.assigned) ? document.getElementById(config.assigned) : config.assigned;
      this._teamOps = Mojo.Util.isString(config.teamOps) ? document.getElementById(config.teamOps) : config.teamOps;
      
      if(this._searchEl != null) {
        var dF = Mojo.Util.bind(this, this._displayFunction);
        var iF = Mojo.Util.bind(this, this._idFunction);
        var lF = Mojo.Util.bind(this, this._listFunction);
        var sF = Mojo.Util.bind(this, this._searchFunction);
        var sEH = Mojo.Util.bind(this, this._selectEventHandler);
      
        this.search = new MDSS.GenericSearch(this._searchEl, this._concreteEl, lF, dF, iF, sF, sEH);
      }
    },
      
    _searchFunction : function(request, value) {
       Mojo.$.dss.vector.solutions.irs.TeamMemberView.getUnassignedOperators(request, value);
    },
    
    _selectEventHandler : function(selected) {
      if(this._labelEl != null) {
        this._labelEl.value = selected.label;
      }
      
      if(this._teamOps != null) {
        this._teamOps.selectedIndex = 0;
      }
        
      if(this._assigned != null) {
        this._assigned.value = '';
      }
    }   
  }
});

Mojo.Meta.newClass('MDSS.AssignedOperatorsSearch', { // Implements CallBack
 Extends : MDSS.TeamMemberSearch,
 Instance: {
   initialize : function(config){
     this._teamEl = Mojo.Util.isString(config.team) ? document.getElementById(config.team) : config.team;
     this._labelEl = Mojo.Util.isString(config.label) ? document.getElementById(config.label) : config.label;
     this._searchEl = Mojo.Util.isString(config.search) ? document.getElementById(config.search) : config.search;      
     this._concreteEl = Mojo.Util.isString(config.concrete) ? document.getElementById(config.concrete) : config.concrete;
     this._unassigned = Mojo.Util.isString(config.unassigned) ? document.getElementById(config.unassigned) : config.unassigned;
     this._teamOps = Mojo.Util.isString(config.teamOps) ? document.getElementById(config.teamOps) : config.teamOps;
 
     if(this._searchEl != null) {
       var dF = Mojo.Util.bind(this, this._displayFunction);
       var iF = Mojo.Util.bind(this, this._idFunction);
       var lF = Mojo.Util.bind(this, this._listFunction);
       var sF = Mojo.Util.bind(this, this._searchFunction);
       var sEH = Mojo.Util.bind(this, this._selectEventHandler);
 
       this._search = new MDSS.GenericSearch(this._searchEl, this._concreteEl, lF, dF, iF, sF, sEH);
     }

     if(this._teamEl != null) {
       YAHOO.util.Event.on(this._teamEl, 'change', this._resetCache, this, this);
     }
   },
   
   _resetCache : function() {
     this._search.resetCache();
   },

   _searchFunction : function(request, value) {
     if(this._teamEl != null) {
       var teamId = this._teamEl.value;
      
       if(teamId) {
         Mojo.$.dss.vector.solutions.irs.TeamMemberView.getOtherOperators(request, value, teamId);
       }
     }
   },
   
   _selectEventHandler : function(selected) {
     if(this._labelEl != null) {
       this._labelEl.value = selected.label;
     }

     if(this._teamOps != null) {
       this._teamOps.selectedIndex = 0;
     }
     
     if(this._unassigned != null) {
       this._unassigned.value = '';
     }
   }   
 }
});