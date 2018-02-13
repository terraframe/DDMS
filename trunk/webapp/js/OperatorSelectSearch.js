/*
 * Copyright (C) 2008 IVCC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
MDSS.operatorSearch = function(config) {
  var to = Mojo.Util.isString(config.to) ? document.getElementById(config.to) : config.to;
  var from = Mojo.Util.isString(config.from) ? document.getElementById(config.from) : config.from;
  var button = Mojo.Util.isString(config.button) ? document.getElementById(config.button) : config.button;
  var display = Mojo.Util.isString(config.display) ? document.getElementById(config.display) : config.display;
  var id = Mojo.Util.isString(config.from) ? document.getElementById(config.from) : config.from;
  
  var listFunction = function(valueObject) {
    return valueObject.text;
  };

  var idFunction = function(valueObject) {
    return valueObject.value;
  };

  var displayFunction = function(valueObject) {    
    return valueObject.text;
  };
  
  var searchFunction = function(request, value) {
    var filtered = [];

    for(var i=0; i< from.options.length;i++) {
      var element = from.options[i];
      var text = element.text.toUpperCase();
        
      if(text.search(value.toUpperCase()) !== -1)
      {
        filtered.push(element);
      }
    }

    var query = {
      resultSet : filtered,
      getResultSet : function(){
        return this.resultSet;
      }
    };        
    
    request.onSuccess(query);        
  };

  var selectEventHandler = function() {};
  
  
  var removeOption = function(selectbox, value) {
    for(var i=0; i < selectbox.options.length; i++) {
      if(selectbox.options[i].value === value)          
        selectbox.remove(i);
    }
  }   

  var addOption =  function(selectbox, text, value) {
    if(!Selectbox.containsOption(selectbox, value)) {
      Selectbox.addOption(selectbox, text, value, false);
    }
  }
  
  var addClick = function() {
    var displayValue = display.value;  
    var idValue = id.value;

    if(idValue !== null && display.value !== null && idValue !== '' && display.value !== '') {
      // Remove the selected operator from the from list
      removeOption(from, idValue);                        

      // Add the selected operator to the to list
      addOption(to, display.value, idValue);

      // Clear values of the drop down box
      id.value=null;
      display.value=null;
    }          
  }  

  var search = new MDSS.GenericSearch(display, id, listFunction, displayFunction, idFunction, searchFunction, selectEventHandler);
  
  YAHOO.util.Event.on(button, 'click', addClick, null, null);    
}