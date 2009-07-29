  MDSS.operatorSearch = function(to, from, button, input, id){
    
    var searchResults = document.createElement('div');
    searchResults.id = input.id + '_results';
    searchResults.className = "yui-panel-container show-scrollbars shadow";
    
    YAHOO.util.Dom.insertAfter(searchResults,input);

    var panel = new YAHOO.widget.Panel(searchResults, {
      width:'400px',
      height:'200px',
      zindex:15,
      draggable: false,
      close: true
    });

    var selectHandler = function(selected) {

      if(selected != null) {
         input.value = selected.label;
         
         if(id) {
           id.value = selected.id;
         }         
      }
      else {
        if(id)
        {
            id.value = '';
        }
      }
    }

    var getResultSet = function(array, value)
    {
      var filtered = [];

      for(var i=0; i<array.length;i++)
      {
        var element = array[i];
        var text = element.text.toUpperCase();
        
        if(text.search(value.toUpperCase()) !== -1)
        {
          filtered.push(element);
        }
      }
      
      return filtered;
    }

    
     /**
     * Performs an ajax search based on the entity
     * name and type.
     */
    var ajaxSearch = function(e)
    {
      id.value = ''
      
      var value = input.value;
      var resultPanel = panel;

      // must have at least 2 characters ready
      if(value.length < 2)
      {
        return;
      }
      
      var resultSet = getResultSet(from.options, value);

      var outer = document.createElement('div');

      var header = document.createElement('div');
      header.innerHTML = '<h3>'+MDSS.Localized.Search_Results+'</h3><hr />';
      outer.appendChild(header);

      var inner = document.createElement('div');
      YAHOO.util.Dom.addClass(inner, 'entitySearchResults');
      outer.appendChild(inner);

      var ul = document.createElement('ul');
      YAHOO.util.Dom.addClass(ul, 'selectableList')
      YAHOO.util.Event.on(ul, 'mouseover', function(e, obj){
        
        var li = e.target;
        var ul = e.currentTarget;
        if(li.nodeName === 'SPAN')
        {
          li = li.parentNode;
        }

        if(li.nodeName !== 'LI')
        {
          return;
        }

        // clear all lis of their current class
        var lis = YAHOO.util.Selector.query('li.currentSelection', ul);
        for(var i=0; i<lis.length; i++)
        {
          YAHOO.util.Dom.removeClass(lis[i], 'currentSelection');
        }

        YAHOO.util.Dom.addClass(li, 'currentSelection');
      });

      YAHOO.util.Event.on(ul, 'click', function(e, obj){

        var li = e.target;
        var ul = e.currentTarget;
        if(li.nodeName === 'SPAN')
        {
          li = li.parentNode;
        }

        if(li.nodeName !== 'LI')
        {
          return;
        }

        resultPanel.hide();
        selectHandler(li);

      }, {input: this.input, panel: this.resultPanel}, this.searchRef);

      for(var i=0; i<resultSet.length; i++)
      {
        var valueObj = resultSet[i];

        var li = document.createElement('li');

        li.id = valueObj.value;
        li.label = valueObj.text;

        var displayStr = valueObj.text;
        var matched = displayStr.replace(new RegExp("(.*?)("+this.value+")(.*?)", "gi"), "$1<span class='searchMatch'>$2</span>$3");
        li.innerHTML = matched;
            
        ul.appendChild(li);
      }

      inner.appendChild(ul);

      resultPanel.setBody(outer);
      resultPanel.render();
      resultPanel.show();
      resultPanel.bringToTop();

      // refocus the input field
      input.focus();
    }
    
    var removeOption = function(selectbox, id)
    {
      var i;

      for(i=0; i < selectbox.options.length; i++)
      {
        if(selectbox.options[i].value === id)          
          selectbox.remove(i);
      }
    }   

    var addOption =  function(selectbox, text,value)
    {
      if(!Selectbox.containsOption(selectbox, value))
      {
        Selectbox.addOption(selectbox, text, value, false);
      }
    }
    
    var addClick = function()
    {
      if(id.value !== null && input.value !== null && id.value !== '' && input.value !== '')
      {
        // Remove the selected operator from the from list
        removeOption(from, id.value);                        

        // Add the selected operator to the to list
        addOption(to, input.value, id.value);

        // Clear values of the drop down box
        id.value=null;
        input.value=null;
      }          
    }
    
    YAHOO.util.Event.on(input, 'keyup', ajaxSearch, null, null);    
    YAHOO.util.Event.on(button, 'click', addClick, null, null);    
  }
