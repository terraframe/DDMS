
  MDSS.collectionSearch = function(type){
    var collectionInput = document.getElementById('collectionInput');                

    var collectionResults = document.createElement('div');
    collectionResults.id = 'collectionInput_results';
    collectionResults.className = "yui-panel-container show-scrollbars shadow";
    
    YAHOO.util.Dom.insertAfter(collectionResults,collectionInput);

    var panel = new YAHOO.widget.Panel(collectionResults, {
      width:'400px',
      height:'200px',
      zindex:15,
      draggable: false,
      close: true
    });

    var selectHandler = function(selected) {
      var collectionInput = document.getElementById('collectionInput');
      var collectionId = document.getElementById('collectionId');

      if(selected != null) {
         collectionInput.value = selected.collectionId;
         
         if(collectionId) {
           collectionId.value = selected.id;
         }
      }
      else {
        if(collectionId)
        {
            collectionId.value = '';
        }
      }
    }

    var checkManualEntry = function() {
      var collectionInput = document.getElementById('collectionInput');

      var request = new MDSS.Request({
          selectHandler: selectHandler,
          onSend: function(){},
          onComplete: function(){},
          onFailure: function(){},
          onSuccess : function(result){
        	if(result !== null) {
        		selectHandler({id:result.getId(), collectionId:result.getCollectionId()});
        	}
        }
      });

      Mojo.$.dss.vector.solutions.entomology.ConcreteMosquitoCollection.getByCollectionId(request, collectionInput.value);
    }
    

     /**
     * Performs an ajax search based on the entity
     * name and type.
     */
    var ajaxSearch = function(e)
    {
      var input = document.getElementById('collectionInput');
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
        type: type,
        
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
          var typeAttr = "type";
          var collectionIdAttr = "collectionId";
          var dateAttr = "dateCollected";
          var entityNameAttr = "entityName";
          var geoIdAttr = "geoId";
          
          for(var i=0; i<resultSet.length; i++)
          {
            var valueObj = resultSet[i];

            var li = document.createElement('li');

            li.id = valueObj.getValue(idAttr);
            li.collectionId = valueObj.getValue(collectionIdAttr);

            var formattedDate = MDSS.Calendar.getLocalizedString(valueObj.getValue(dateAttr));
            var displayStr = valueObj.getValue(entityNameAttr) + '('+ valueObj.getValue(geoIdAttr) + '), ' + formattedDate + ' - ' + valueObj.getValue(collectionIdAttr);
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

      Mojo.$.dss.vector.solutions.entomology.AbstractMosquitoCollection.searchByCollectionId(request, value, type);
    }
    
     YAHOO.util.Event.on(collectionInput, 'keyup', ajaxSearch, null, null);    
     YAHOO.util.Event.on(collectionInput, 'blur', checkManualEntry, null, null);     
  }
