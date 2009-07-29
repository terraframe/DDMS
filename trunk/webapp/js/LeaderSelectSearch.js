
  MDSS.leaderSearch = function(input, id){
	  
    var leaderResults = document.createElement('div');
    leaderResults.id = input.id + '_results';
    leaderResults.className = "yui-panel-container show-scrollbars shadow";
    
    YAHOO.util.Dom.insertAfter(leaderResults,input);

    var panel = new YAHOO.widget.Panel(leaderResults, {
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

     /**
     * Performs an ajax search based on the entity
     * name and type.
     */
    var ajaxSearch = function(e)
    {
      var value = input.value;
      var resultPanel = panel; 

      // must have at least 2 characters ready
      if(value.length < 2)
      {
        return;
      }

      var request = new MDSS.Request({
        resultPanel: resultPanel,
        searchValue: value,
        selectHandler: selectHandler,
        input: input,
        searchRef: this,
        
        // don't paint a loading bar. It's too slow for this type of call
        onSend: function(){},
        onComplete: function(){},
        onSuccess: function(query)
        {
          var resultSet = query.getResultSet();

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

          var idAttr = "id";
          var firstName = "firstName";
          var lastName = "lastName";
          var leaderId = "leaderId";
          
          for(var i=0; i<resultSet.length; i++)
          {
            var valueObj = resultSet[i];

            var li = document.createElement('li');

            li.id = valueObj.getValue(idAttr);
            li.label = valueObj.getValue(firstName) + ' '+ valueObj.getValue(lastName) + ' - ' + valueObj.getValue(leaderId);

            var displayStr = valueObj.getValue(firstName) + ' '+ valueObj.getValue(lastName) + ' - ' + valueObj.getValue(leaderId);
            var matched = displayStr.replace(new RegExp("(.*?)("+this.searchValue+")(.*?)", "gi"), "$1<span class='searchMatch'>$2</span>$3");
            li.innerHTML = matched;
            
            ul.appendChild(li);
          }

          inner.appendChild(ul);

          this.resultPanel.setBody(outer);
          this.resultPanel.render();
          this.resultPanel.show();
          this.resultPanel.bringToTop();

          // refocus the input field
          this.input.focus();
        }
      });

      Mojo.$.dss.vector.solutions.irs.SprayLeader.searchForLeader(request, value);
    }
    
     YAHOO.util.Event.on(input, 'keyup', ajaxSearch, null, null);    
  }
