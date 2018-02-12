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
(function(){

  Mojo.Meta.newClass('dss.vector.solutions.generator.WebIndicatorPlugin$WebIndicator', {
    Extends : Mojo.FORM_PACKAGE.FIELD + 'WebAttribute',
    Instance : {
      initialize : function(obj){
        this.$initialize(obj);
      },
      accept : function(visitor){
        visitor.visitDouble(this);
      }
    }
  });

  Mojo.Meta.newClass('dss.vector.solutions.generator.WebIndicatorPlugin$WebIndicatorMd', {
    Extends : Mojo.FORM_PACKAGE.METADATA + 'WebAttributeMd',
    Instance : {
      initialize : function(obj){
        this.$initialize(obj);
      }
    }
  });

  //Indicator
  Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeIndicatorDTO', {

    Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',

    Instance : {
      initialize : function(obj) {
        this.$initialize(obj);
      }
    }
  });
  
  Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeIndicatorMdDTO', {

    Extends : Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',
    
    Instance : {
    
      initialize : function(obj)
      {
        this.$initialize(obj);
      }
    }
  });
  

})();
