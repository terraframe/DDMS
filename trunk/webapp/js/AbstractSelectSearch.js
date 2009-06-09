MDSS.AbstractSelectSearch = Mojo.Class.create();
MDSS.AbstractSelectSearch.SelectSearchRootId = null; // must be set before instantiating a subclass.
MDSS.AbstractSelectSearch.ExtraUniversals = []; // must be set before instantiating a subclass.
MDSS.AbstractSelectSearch.Political = true;
MDSS.AbstractSelectSearch.SprayTargetAllowed = false;
MDSS.AbstractSelectSearch.prototype = {

  /**
   * Constructor.
   */
  initialize : function()
  {
    // handler for when a new geo entity is selected
    this._selectHandler = null;

    // handler for when a geo entity is selected via the tree
    this._treeSelectHandler = null;

    // handler to be executed when the search is closed.
    this._hideHandler = null;

    // The id of the element that contains the select lists.
    this._SELECT_CONTAINER_ID = "selectSearchComponent";

    // cache of GeoEntities with key/value = geoEntity.getId()/geoEntity
    this._geoEntityViewCache = {};

    // reference to modal that contains the select search
    this._searchModal = null;

    // reference to the Yahoo Panel that contains the search tree.
    this._geoTreePanel = null;

    // the GeoEntity subtype class by which to filter results
    this._filterType = '';

    // list of select elements
    this._selectLists = [];

    // map between type and select list index
    // where key/value is type/select index in this._selectLists
    this._typeAndSelectMap = {};

    // map between ajax search input id and Yahoo Panel
    this._resultPanels = {};

    // the current type of GeoEntity for ajax searching
    this._currentSearchType = null;

    this._political = true;

    this._sprayTargetAllowed = false;
  },

  /**
   * Copies a GeoEntity to a GeoEntityView (used for caching).
   */
  _copyEntityToView : function(geoEntity)
  {
    var view = new Mojo.$.dss.vector.solutions.geo.GeoEntityView();

    view.setGeoEntityId(geoEntity.getId());
    view.setGeoId(geoEntity.getGeoId());
    view.setActivated(geoEntity.getActivated());
    view.setEntityName(geoEntity.getEntityName());
    view.setEntityType(geoEntity.getType());
    view.setTypeDisplayLabel(geoEntity.getTypeMd().getDisplayLabel());

    return view;
  },

  /**
   * Checks if the searching has been enabled (i.e., the modal has
   * been initialized).
   */
  isInitialized : function()
  {
    return this._searchModal != null;
  },

  /**
   * Shows the select modal if it is hidden.
   */
  show : function()
  {
    this._searchModal.show();
  },

  /**
   * Hides the modal if it visibile.
   */
  hide : function()
  {
    this._searchModal.hide();
  },

  /**
   * Sets the handler function to be called when
   * a user selects a GeoEntity.
   */
  setSelectHandler : function(handler)
  {
    this._selectHandler = handler;
  },

  /**
   * Sets the handler function to be called when
   * a user selects a GeoEntity from the tree.
   */
  setTreeSelectHandler : function(handler)
  {
    this._treeSelectHandler = handler;
  },

  /**
   * Sets the handler to be called when this modal is hidden.
   */
  setHideHandler : function(handler)
  {
  	this._hideHandler = handler;
  },

  /**
   * Sets the root entity
   */
  _createRoot : function()
  {
    // Populate the root
    var request = new MDSS.Request({
      searchRef: this,
      onSuccess : function(results){

        this.searchRef._clearAndAddAll(0, results);
      }
    });

    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.collectAllLocatedIn(request, MDSS.SelectSearchRootId, false, '');
  },

  /**
   * Closes all the result panels.
   */
  _closeAllResultPanels : function()
  {
    var panels = Mojo.util.getValues(this._resultPanels);
    for(var i=0; i<panels.length; i++)
    {
      var panel = panels[i];
      //if(Mojo.util.isNumber(excludeIndex) && i !== excludeIndex)
      var visible = panel.cfg.getProperty('visible');
      if(visible)
      {
        panel.hide();
      }
    }
  },

  /**
   * Renders the select search component.
   */
  render : function()
  {
    var request = new MDSS.Request({
      searchRef: this,
      onSuccess : function(html){

        // use modal to contain MDSS101
        this.searchRef._searchModal = new YAHOO.widget.Panel("searchSelectModal",  {
          width:"100%",
          height: "100%",
          fixedcenter:true,
          close:true,
          draggable:false,
          zindex:4,
          modal:true,
          visible:true
        });

        // hide all panels spawned by the search modal
        this.searchRef._searchModal.subscribe('beforeHide', function(){

          this._closeAllResultPanels();

          if(this._geoTreePanel != null)
          {
            this._geoTreePanel.hide();
          }

          this._notifyHideHandler();

        }, null, this.searchRef);

        this.searchRef._searchModal.setBody(html);
        this.searchRef._searchModal.render(document.body);

        this.searchRef._doFilter();

        // hook event to open tree
        var treeOpener = new YAHOO.util.Element("treeOpener");
        treeOpener.on('click', this.searchRef._openTree, null, this.searchRef);

        var selects = YAHOO.util.Selector.query('select.typeSelect', this.searchRef._SELECT_CONTAINER_ID);
        for(var i=0; i<selects.length; i++)
        {
          var select = selects[i];

          // create mapping
          this.searchRef._typeAndSelectMap[select.name] = i; // select name is the type
          this.searchRef._selectLists.push(select);

          YAHOO.util.Event.on(select, this._getChildren, null, this.searchRef);
        }

        // hook all entity name search events
        var ajaxSearches = YAHOO.util.Selector.query('input.ajaxSearch', this._SELECT_CONTAINER_ID);
        for(var i=0; i<ajaxSearches.length; i++)
        {
          var search = ajaxSearches[i];
          YAHOO.util.Event.on(search, 'keyup', this.searchRef._ajaxSearch, null, this.searchRef);
        }

        // create a panel for each ajax result set
        var ajaxResults = YAHOO.util.Selector.query('div.ajaxResults', this._SELECT_CONTAINER_ID);
        for(var i=0; i<ajaxResults.length; i++)
        {
          var div = ajaxResults[i];
          var panel = new YAHOO.widget.Panel(div, {
            width:'400px',
            height:'200px',
            zindex:15,
            draggable: false,
            close: true
          });

          this.searchRef._resultPanels[div.id] = panel;
        }

        // hook all search events for manual entry
        var manualSearches = YAHOO.util.Selector.query('input.manualSearch', this._SELECT_CONTAINER_ID);
        for(var i=0; i<manualSearches.length; i++)
        {
          var search = manualSearches[i];
          YAHOO.util.Event.on(search, 'click', this.searchRef._manualSearch, null, this.searchRef);
        }

        this.searchRef._postRender();

        this.searchRef._createRoot();
      }
    });

    var method = this._getControllerAction();
    method(request, MDSS.SelectSearchRootId, MDSS.AbstractSelectSearch.Political, MDSS.AbstractSelectSearch.SprayTargetAllowed, MDSS.AbstractSelectSearch.ExtraUniversals);
  },

  /**
   * Calls after render() but before _createRoot(). Subclasses
   * may override this method to do any post render processing.
   */
  _postRender : function()
  {
  	// Abstract
  },

  /**
   * Creates the mapping between the given GeoEntity
   * and an option for the select element that represents
   * the GeoEntity's type.
   */
  _setEntityOption : function(geoEntityView)
  {
    var select = document.getElementById(geoEntityView.getEntityType());

    if(select && !this._geoEntityViewCache[geoEntityView.getGeoEntityId()])
    {
      if(this._disableAllowed())
      {
        select.disabled = false;
      }

      var optionRaw = document.createElement('option');
      optionRaw.value = geoEntityView.getGeoEntityId();
      optionRaw.id = geoEntityView.getGeoEntityId();
      optionRaw.innerHTML = geoEntityView.getEntityName();

      select.appendChild(optionRaw);

      var option = new YAHOO.util.Element(optionRaw);
      option.on('click', this._getChildren, null, this);

      this._geoEntityViewCache[geoEntityView.getGeoEntityId()] = geoEntityView;
    }
  },

  /**
   * Calls the handler when the user hides the search modal.
   */
  _notifyHideHandler : function()
  {
    // abstract
  },

  /**
   * Abstract method to notify the select
   * handler that a GeoEntity has been selected.
   */
  _notifySelectHandler : function(geoEntityView, updateSelection)
  {
    // abstract
  },

  /**
   * Invokes the appropriate controller action to
   * render the select search component.
   */
  _getControllerAction : function(request, rootId)
  {
    // abstract
  },

  /**
   * Updates the HTML area with information about the most recently selected GeoEntity.
   */
  _updateSelection : function(geoEntity)
  {
    // abstract
  },

  /**
   * Opens the tree for extra search functionality.
   */
  _openTree : function()
  {
    var containerId = "treeViewContainer";

    if(this._geoTreePanel == null)
    {
      this._geoTreePanel = new YAHOO.widget.Panel(containerId, {width:'400px', height:'400px', zindex:9});

      // Bug Workaround: The Yahoo ContextMenu loses its event handlers in
      // the tree, so destroy the tree every time the panel is closed, then
      // use a new tree per request.
      this._geoTreePanel.subscribe('beforeHide', function(e, obj){
        YAHOO.util.Dom.setStyle(obj.containerId, 'overflow', 'none');
        MDSS.GeoEntityTree.destroyAll();
      }, {containerId:containerId});
      this._geoTreePanel.render();
      this._geoTreePanel.bringToTop();
    }
    else
    {
      this._geoTreePanel.show();
      this._geoTreePanel.bringToTop();
    }

    // always create a new tree per request
    var wrappedHandler = (function(searchRef)
    {
      return function(geoEntity){
        searchRef._treeSelectHandler(geoEntity);
        searchRef.hide();
      }
    })(this);

    MDSS.GeoEntityTree.initializeTree("treeView", wrappedHandler);


    YAHOO.util.Dom.setStyle(containerId, 'overflow', 'scroll');
  },

  /**
   * Changes the GeoEntity subtype filter and clears
   * the select search and tree.
   */
  setFilter : function(filter)
  {
    this._filterType = filter;

    if(this.isInitialized())
    {
      this._doFilter();
    }
  },

  /**
   * Performs the DOM level filtering by hiding/showing
   * the proper select lists.
   */
  _doFilter : function()
  {
    // show all if no filter exists
    if(this._filterType == null || this._filterType === '')
    {
      var selects = YAHOO.util.Selector.query('select', this._SELECT_CONTAINER_ID);
      for(var i=0; i<selects.length; i++)
      {
        var type = selects[i].id;
        var dt = new YAHOO.util.Element(type+"_dt");
        var dd = new YAHOO.util.Element(type+"_dd");

        dt.setStyle('display', 'block');
        dd.setStyle('display', 'block');
      }

      return;
    }

    function collectParents(tree, allowedArr, typeEntry)
    {
      var parentType = typeEntry.parent;

      if(Mojo.util.isString(parentType))
      {
        var parentEntry = tree.types[parentType];
        allowedArr.push(parentType);
        collectParents(tree, allowedArr, parentEntry);
      }
    }

    function collectChildren(tree, allowedArr, typeEntry)
    {
      var children = typeEntry.children;
      for(var i=0; i<children.length; i++)
      {
        var childEntry = tree.types[children[i]];
        allowedArr.push(children[i]);
        collectChildren(tree, allowedArr, childEntry);
      }
    }


    var allowed = [];
    var tree = MDSS.GeoTreeSelectables;
    var typeEntry = tree.types[this._filterType];
    allowed.push(this._filterType);
    collectParents(tree, allowed, typeEntry);
    collectChildren(tree, allowed, typeEntry);

    // hide all sections not in the allowed array
    var selects = YAHOO.util.Selector.query('select', this._SELECT_CONTAINER_ID);
    for(var i=0; i<selects.length; i++)
    {
      var type = selects[i].id;
      var dt = new YAHOO.util.Element(type+"_dt");
      var dd = new YAHOO.util.Element(type+"_dd");

      if(allowed.indexOf(type) === -1)
      {
        dt.setStyle('display', 'none');
        dd.setStyle('display', 'none');
      }
      else
      {
        dt.setStyle('display', 'block');
        dd.setStyle('display', 'block');
      }
    }
  },

  /**
   * Performs an ajax search based on the entity
   * name and type.
   */
  _ajaxSearch : function(e)
  {
    var input = e.target;
    var value = input.value;
    var type = input.id.replace(/_search/, '');
    var resultPanel = this._resultPanels[type+"_results"];

    // must have at least 2 characters ready
    if(value.length < 2)
    {
      return;
    }

    // close all other search panels
    if(this._currentSearchType !== type)
    {
      this._closeAllResultPanels();
    }

    this._currentSearchType = type;

    var request = new MDSS.Request({
      resultPanel: resultPanel,
      searchValue: value,
      input: input,
      searchRef: this,
      type: type,
      // don't paint a loading bar. It's too slow for this
      // type of call
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

          var geoEntityId = li.id;

          obj.input.value = '';
          this._resetWithSelection(type, obj.panel, geoEntityId);

        }, {input: this.input, panel: this.resultPanel}, this.searchRef);

        var idAttr = Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.ID;
        var entityNameAttr = Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.ENTITYNAME;
        for(var i=0; i<resultSet.length; i++)
        {
          var valueObj = resultSet[i];

          var li = document.createElement('li');
          li.id = valueObj.getValue(idAttr);
          var entityName = valueObj.getValue(entityNameAttr);
          var matched = entityName.replace(new RegExp("(.*?)("+this.searchValue+")(.*?)", "gi"),
            "$1<span class='searchMatch'>$2</span>$3");
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

    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.searchByEntityName(request, type, value);
  },

  /**
   * Clears all select lists and adds the given results
   * as new select list options.
   */
  _clearAndAddAll : function(selectIndex, results)
  {
    // clear all select lists
    this._clearSelectLists(0);

    for(var i=0; i<results.length; i++)
    {
      var childView = results[i];
      this._setEntityOption(childView);
    }

    for(var i=selectIndex; i>=0; i--)
    {
      var select = this._selectLists[i];
      if(select.options.length > this._getStartIndex())
      {
        select.selectedIndex = this._getStartIndex();
      }
    }

    var select = this._selectLists[selectIndex];

    // the root won't have a default root entry
    var firstInd = selectIndex == 0? 0 : this._getStartIndex();
    var firstEntry = select.options[firstInd];
    var geoEntityView = this._geoEntityViewCache[firstEntry.id];
    this._notifySelectHandler(geoEntityView, true);
  },

  /**
   * Resets all select lists with the given GeoEntity
   * and its parents (recursively) and children (immediate).
   */
  _resetWithSelection : function(type, resultPanel, geoEntityId)
  {
    var request = new MDSS.Request({
      searchRef: this,
      resultPanel: resultPanel,
      type: type,
      onSuccess: function(results)
      {
        // close the search result panel
        resultPanel.hide();

        var selectIndex = this.searchRef._typeAndSelectMap[this.type];

        this.searchRef._clearAndAddAll(selectIndex, results);
      }
    });

    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.collectAllLocatedIn(request, geoEntityId, true, this._filterType);
  },

  /**
   * Searches for a specific GeoEntity.
   */
  _manualSearch : function(e)
  {
    var button = e.target;
    var input = button.previousSibling;

    var geoId = input.value;
    var type = input.id.replace(/_manualEntry/, '');

    var request = new MDSS.Request({
      searchRef: this,
      type: type,
      input: input,
      onSuccess : function(results)
      {
        this.input.value = "";

        var selectIndex = this.searchRef._typeAndSelectMap[this.type];
        this.searchRef._clearAndAddAll(selectIndex, results);
      }
    });

    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.searchAndCollectByGeoId(request, geoId, this._filterType);
  },

  /**
   * Gets the children for a given GeoEntity or
   * does nothing if the event was triggered by
   * an unselect. In either case, all unselected
   * options are cleared of children.
   */
  _getChildren : function(e)
  {
    var currentOption = e.target;
    var select = currentOption.parentNode;


    var parentEntityView = this._geoEntityViewCache[currentOption.id];


    // get the children
    var request = new MDSS.Request({
    searchRef: this,
    currentType: parentEntityView.getEntityType(),
    parentEntityView: parentEntityView,
    onSuccess : function(query){

      // clear nodes below this type to make way for new children
      this.searchRef._clearSelectLists.call(this.searchRef, this.currentType);

      // these are GeoEntityView objects
      var geoEntities = query.getResultSet();

      for(var i=0; i<geoEntities.length; i++)
      {
        var childView = geoEntities[i];
        this.searchRef._setEntityOption(childView);
      }

      this.searchRef._notifySelectHandler(this.parentEntityView, true);
    }
    });

    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getOrderedChildren(request, parentEntityView.getGeoEntityId(), this._filterType);
  },

  /**
   * Clears all select options in the select lists
   * below the given type. This is called to clear the
   * way for new children.
   */
  _clearSelectLists : function(typeIndex, inclusive)
  {
    var index = Mojo.util.isString(typeIndex) ? this._typeAndSelectMap[typeIndex] : typeIndex;

    // don't do anything for the last select list
    if(index == this._selectLists.length-1)
    {
      return;
    }

    var startIndex = inclusive ? index : index+1;
    for(var i=startIndex; i<this._selectLists.length; i++)
    {
      var select = this._selectLists[i];

      // remove option node and mappings
      this._clearOptionsOnSelect(i, select);
    }
  },

  /**
   * Clears options on the given select list.
   */
  _clearOptionsOnSelect : function(selectIndex, select)
  {
    var options = select.options;
    var oLength = options.length;

    var startInd = selectIndex == 0 ? 0 : this._getStartIndex()-1;

    for(var i=oLength-1; i>startInd; i--)
    {
      var option = options[i];

      this._removeOptionNode(option);
    }
  },


  /**
   * Subclasses must override this method to return the index
   * at which non-root select lists start listing GeoEntities.
   */
  _getStartIndex : function()
  {
    // abstract
  },

  /**
   * Removes the given option from the DOM and
   * clears the GeoEntity cache of the object
   * with the same id.
   */
  _removeOptionNode : function(option)
  {
    var optionEl = Mojo.util.isString(option) ? document.getElementById(option) : option;
    var select = optionEl.parentNode;
    select.removeChild(optionEl);

    delete this._geoEntityViewCache[optionEl.id];

    if(select.options.length == this._getStartIndex())
    {
      select.selectedIndex = 0;

      if(this._disableAllowed())
      {
        select.disabled = true;
      }
    }
  },

  /**
   * Subclasses must override this to denote if empty select
   * lists can be disabled.
   */
  _disableAllowed : function()
  {
  	// abstract
  }
}