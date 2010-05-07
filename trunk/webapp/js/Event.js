Mojo.Meta.newClass('MDSS.Event', {
  Instance : {
    initialize : function(type, value) {
      this.type = type;
      this.value = value;
    },
    
    getType : function() {
      return this.type;
    },
    
    getValue : function() {
      return this.value;
    }    
  },
  
  Static : {
    AFTER_ROW_ADD : 1,
    BEFORE_ROW_ADD : 2,
    AFTER_SAVE : 3,
    AFTER_PROBLEM : 4,
    AFTER_FAILURE : 5,
    AFTER_SELECTION : 6,
    AFTER_DELETE : 7,
    BEFORE_SEARCH : 8,
    RELOAD_VALUE : 9,
    AFTER_VALID_SELECTION : 10,
    AFTER_INVALID_SELECTION : 11,
    AFTER_SET_DATA : 12
  }
});
