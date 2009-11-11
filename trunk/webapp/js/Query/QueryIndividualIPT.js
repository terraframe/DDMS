Mojo.Meta.newClass('MDSS.QueryIndividualIPT', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {

  		
  		this._groupByClass = Mojo.$.dss.vector.solutions.intervention.monitor.IndividualIPT;
  		this._mainQueryClass = this._groupByClass.CLASS;
	
      this._individualIPT = new this._groupByClass();
      this._dateAttribute = new MDSS.QueryXML.Attribute(this._groupByClass.CLASS, this._groupByClass.STARTDATE, this._groupByClass.STARTDATE);
      
      var startDateAttr = new MDSS.QueryXML.Attribute(this._groupByClass.CLASS, this._groupByClass.STARTDATE, this._groupByClass.STARTDATE);
      this._startDateSelectable = new MDSS.QueryXML.Selectable(startDateAttr);
      
      var endDateAttr = new MDSS.QueryXML.Attribute(this._groupByClass.CLASS, this._groupByClass.ENDDATE, this._groupByClass.ENDDATE);
      this._endDateSelectable = new MDSS.QueryXML.Selectable(endDateAttr);



      this._commonQueryClasses = [
                                  this._groupByClass.CLASS,
                                  "dss.vector.solutions.intervention.monitor.IndividualIPTCase",
                                  Mojo.$.dss.vector.solutions.Person.CLASS,
                                  ];

      this._exclusionClasses = [];
      
      var tmpPerson = new  Mojo.$.dss.vector.solutions.Person();

      this._geoEntityAttribs = [
                             {
                               keyName :  this._groupByClass.CLASS+'.'+this._groupByClass.FACILITY,
                               display : this._individualIPT.getFacilityMd().getDisplayLabel()
                             },
                             {
                               keyName :  Mojo.$.dss.vector.solutions.Person.CLASS+'.'+Mojo.$.dss.vector.solutions.Person.RESIDENTIALGEOENTITY,
                               display : tmpPerson.getResidentialGeoEntityMd().getDisplayLabel()
                             },
                             {
                               keyName :  Mojo.$.dss.vector.solutions.Person.CLASS+'.'+Mojo.$.dss.vector.solutions.Person.WORKGEOENTITY,
                               display : tmpPerson.getWorkGeoEntityMd().getDisplayLabel()
                             }        
                             
                           ];
      
      this._queryType = 'QueryIndividualIPT';

      this._reportQueryType = 'QueryIndividualIPT';
      
      this.$initialize(selectableGroups, queryList);   
 
      },
      
      
      /*
       * Visible Attributes
       */
      _getVizDiv : function(that,visibleAttributes,divName,mainQueryClass,checkClass)
      {
        var visibleDiv = document.createElement('div');
        // YAHOO.util.Dom.addClass(visibleDiv, 'scrollable');

        var labelDiv = document.createElement('div');
        YAHOO.util.Dom.addClass(labelDiv, 'queryItemLabel');
        //labelDiv.innerHTML = MDSS.localize(divName);
        labelDiv.innerHTML = divName;

        var toggleDiv = document.createElement('div');
        YAHOO.util.Dom.addClass(toggleDiv, 'clickable');
        YAHOO.util.Dom.addClass(toggleDiv, 'queryItemLabel');
        toggleDiv.innerHTML = MDSS.Localized.Toggle_Show;

        visibleDiv.appendChild(labelDiv);
        visibleDiv.appendChild(toggleDiv);

        var visibleUl = document.createElement('ul');
        visibleUl.id = divName + "Li";
        YAHOO.util.Dom.addClass(visibleUl, 'gridList');
        YAHOO.util.Dom.setStyle(visibleUl, 'clear', 'both');
        YAHOO.util.Dom.setStyle(visibleUl, 'display', 'none');

        that._toggleVisibility(toggleDiv, visibleUl);

        that._attachSelectAll(visibleUl,checkClass);

        for(var i=0; i<visibleAttributes.length; i++)
        {
          var visibleObj = visibleAttributes[i];
          var attribute = new MDSS.BasicAttribute(visibleObj);
          attribute.mainQueryClass = mainQueryClass;

          var li = document.createElement('li');

          var check = document.createElement('input');
          YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
          //this is the marker for the mutual exculsion group
          YAHOO.util.Dom.addClass(check,checkClass);

          YAHOO.util.Event.on(check, 'click', that._visibleAttributeHandler, attribute, that);
          check.id = attribute.getKey();
          li.appendChild(check);
          this._defaults.push({element:check, checked:false});
          
          if(attribute.getKey().contains('Name'))
          {
          	li.id = attribute.getKey()+'_li';

            // Add single match and range
	        	var items = [];
	          var single = this._createSingleItem(check, li, attribute,'queryTextCriteria');
	          
	          this._menuItems[attribute.getKey()+'-single'] = single;        
	              
	          items.push(single);
	          
	          this._menus[li.id] = items;
            
          }
          
          if(visibleObj.dtoType && visibleObj.dtoType.contains('AttributeIntegerDTO'))
          {
          	li.id = attribute.getKey()+'_li';

              // Add single match and range
          	var items = [];
            var single = this._createSingleItem(check, li, attribute,'queryNumberCriteria');
            var range = this._createRangeItem(check, li, attribute);
            
            this._menuItems[attribute.getKey()+'-single'] = single;        
            this._menuItems[attribute.getKey()+'-range'] = range;
                
            items.push(single);
            items.push(range);
            
            this._menus[li.id] = items;
          	
            var select = document.createElement('select');

            var options = [''];
            options = options.concat(Mojo.Util.getValues(MDSS.QueryXML.Functions));


            for(var j=0; j<options.length; j++)
            {
              var option = options[j];
              var optionEl = document.createElement('option');
              optionEl.id = attribute.getKey()+'-'+option;
              optionEl.innerHTML = option;
              YAHOO.util.Dom.setAttribute(optionEl, 'value', option);

              YAHOO.util.Event.on(optionEl, 'click', this._visibleAggregateHandler, attribute, this);

              select.appendChild(optionEl);
            }
            select.disabled = true; // default (must be checked to enabled)
            this._defaults.push({element:select, index:0});
            li.appendChild(select);
          }

          var span = document.createElement('span');
          span.innerHTML = attribute.getDisplayLabel();
          li.appendChild(span);

          if (visibleObj.dropDownMap)
          {

            li.id = attribute.getKey()+'_li';

            var options = Mojo.Util.getKeys(visibleObj.dropDownMap);
            var displayLabels = Mojo.Util.getValues(visibleObj.dropDownMap);
            var items = [];
            for(var j=0; j<options.length; j++)
            {
              var item = {
                  checked : false,
                  text : displayLabels[j],
                  uuid:options[j],
                  myIndex:j,
                  onclick: {
                    fn: that._whereValueHandler,
                    obj: {attribute: attribute, value: options[j], display: displayLabels[j]},
                    scope: this
                }
              }
              items.push(item);
              this._menuItems[attribute.getKey()+'-'+options[j]] = item;
            }
            this._menus[li.id] = items;
          }
          else //Mo terms
            if(visibleObj.dtoType && visibleObj.dtoType.contains('AttributeReferenceDTO'))
            {
            	li.id = attribute.getKey()+'_li';
            	var n =  attribute.getAttributeName().replace(/.name/,'');
              this._attachBrowser(li.id, this._genericBrowserHandler, attribute, visibleObj.type + "View", n, true);
            }

          visibleUl.appendChild(li);
        }

        visibleDiv.appendChild(visibleUl);

        return visibleDiv;
      },

    }
});
