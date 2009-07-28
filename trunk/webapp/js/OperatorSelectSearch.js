  MDSS.operatorSearch = function(addButton, onTeam, notOnTeam){
	  
    var operatorInput = document.getElementById('operatorInput');                

    var collectionResults = document.createElement('div');
    collectionResults.id = 'operatorInput_results';
    collectionResults.className = "yui-panel-container show-scrollbars shadow";
    
    YAHOO.util.Dom.insertAfter(collectionResults,operatorInput);

    var panel = new YAHOO.widget.Panel(collectionResults, {
      width:'400px',
      height:'200px',
      zindex:15,
      draggable: false,
      close: true
    });

    var selectHandler = function(selected) {
      var operatorInput = document.getElementById('operatorInput');
      var operatorId = document.getElementById('operatorId');
      var operatorLabel = document.getElementById('operatorLabel');

      if(selected != null) {
         operatorInput.value = selected.operatorId;
         
         if(operatorId) {
           operatorId.value = selected.id;
         }
         
         if(operatorLabel) {
           operatorLabel.value = selected.operatorLabel;
         }
      }
      else {
        if(operatorId)
        {
            operatorId.value = '';
            operatorLabel.value = '';
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
      var input = document.getElementById('operatorInput');
      var value = input.value;
      var resultPanel = panel; //document.getElementById('geoIdEl'+'_results');

      // must have at least 2 characters ready
      if(value.length < 2)
      {
        return;
      }


      
          var resultSet = getResultSet(notOnTeam.options, value);

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
            li.operatorLabel = valueObj.text;
            li.operatorId = valueObj.text;

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
    
    var removeOption = function(selectbox, operatorId)
    {
      var i;

      for(i=0; i < selectbox.options.length; i++)
      {
        if(selectbox.options[i].value === operatorId)          
          selectbox.remove(i);
      }
    }   

    var addOption =  function(selectbox, text,value)
    {
      var optn = document.createElement("OPTION");

      optn.text = text;
      optn.value = value;

      if(!Selectbox.containsOption(selectbox, optn))
      {
        selectbox.options.add(optn);
      }
    }
    
    var addClick = function()
    {
      var operatorId = document.getElementById('operatorId');      
      var operatorInput = document.getElementById('operatorInput');      
      var operatorLabel = document.getElementById('operatorLabel');      

      if(operatorId.value !== null && operatorLabel.value !== null)
      {
          // Remove the selected operator from the notOnTeam list
          removeOption(notOnTeam, operatorId.value);                        

          // Add the selected operator to the onTeam list
          addOption(onTeam, operatorLabel.value, operatorId.value);

          // Clear values of the drop down box
          operatorId.value=null;
          operatorLabel.value=null;
          operatorInput.value=null;
      }          
    }

    
     YAHOO.util.Event.on(operatorInput, 'keyup', ajaxSearch, null, null);    
     YAHOO.util.Event.on(addButton, 'click', addClick, null, null);    
  }
