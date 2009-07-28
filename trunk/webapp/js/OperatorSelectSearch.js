  MDSS.operatorSearch = function(){
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

    var checkManualEntry = function() {
      var operatorInput = document.getElementById('operatorInput');

      var request = new MDSS.Request({
          selectHandler: selectHandler,
          onSend: function(){},
          onComplete: function(){},
          onFailure: function(){},
          onSuccess : function(result){
        	if(result !== null) {
        		selectHandler({id:result.getId(), operatorId:result.getoperatorId()});
        	}
        }
      });

      Mojo.$.dss.vector.solutions.irs.SprayOperator.get(request, operatorInput.value);
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
          var operatorIdAttr = "operatorId";
          var firstNameAttr = "firstName";
          var lastNameAttr = "lastName";
                    
          for(var i=0; i<resultSet.length; i++)
          {
            var valueObj = resultSet[i];

            var li = document.createElement('li');

            li.id = valueObj.getValue(idAttr);
            li.operatorLabel = valueObj.getValue(firstNameAttr) + ' ' + valueObj.getValue(lastNameAttr);
            li.operatorId = valueObj.getValue(lastNameAttr) + ', '+ valueObj.getValue(firstNameAttr) + ' - ' + valueObj.getValue(operatorIdAttr);

            var displayStr = valueObj.getValue(lastNameAttr) + ', '+ valueObj.getValue(firstNameAttr) + ' - ' + valueObj.getValue(operatorIdAttr);
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

      Mojo.$.dss.vector.solutions.irs.SprayOperator.searchForOperators(request, value);
    }
    
     YAHOO.util.Event.on(operatorInput, 'keyup', ajaxSearch, null, null);    
  }
