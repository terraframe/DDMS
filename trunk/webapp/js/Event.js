#-------------------------------------------------------------------------------
# Copyright (C) 2018 IVCC
# 
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
#-------------------------------------------------------------------------------
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
