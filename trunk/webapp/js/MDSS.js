// Turn on Inspector
if(/^(127\.0\.0\.1)|(localhost)$/.test(window.location.hostname))
{
  YAHOO.util.Event.onDOMReady(function(){
//    com.terraframe.mojo.inspector.Inspector.launch();
  });
}

// Alias all AttributeDTOs to the window
Mojo.Meta.shorthand('com.terraframe.mojo.transport.attributes.*', window);

/**
 * Root MDSS namespace.
 */
var MDSS = {

  wait_for_ajax : null,
  
  getDisplayLabel : function(type)
  {
    var display = MDSS._displayLabelCache[type];
    if(display == null)
    {
      var klass = Mojo.Meta.findClass(type);
      var temp = new klass();
      display = temp.getMd().getDisplayLabel();
      
      MDSS._displayLabelCache[type] = display;
    }
    
    return display;
  },
  
  _displayLabelCache : {},

  util : {
  
    /**
     * Extracts all script tag contents and returns
     * a string of executable code that can be evaluated.
     */
    extractScripts : function(html)
    {
      var scripts = html.match(/<script\b[^>]*>[\s\S]*?<\/script>/img);
      var executables = [];
      for(var i=0; i<scripts.length; i++)
      {
        var scriptM = scripts[i].match(/<script\b[^>]*>([\s\S]*?)<\/script>/im);
        executables.push(scriptM[1]);
      }

      return executables.join('');
    },

    /**
     * Removes all scripts from the HTML and returns
     * a string of the cleansed HTML.
     */
    removeScripts : function(html)
    {
      return html.replace(/<script\b[^>]*>[\s\S]*?<\/script>/img, '');
    },

    /**
     * Strips the leading and trailing whitespace from the string.
     */
    stripWhitespace : function(str)
    {
      return str.replace(/^\s+/, '').replace(/\s+$/, '');
    }
  },

  Effect : {

    /**
     * Toggles the visibility of an element by clicking
     * on the given toggle element.
     */
    toggleVisibility : function(elementId, toggleId, hiddenId)
    {
      YAHOO.util.Event.on(toggleId, 'click', function(e, obj){

        var el = new YAHOO.util.Element(obj.element);
        var toggle = document.getElementById(obj.toggle);
        var hidden = document.getElementById(obj.hidden);
        if(el.getStyle('display') === 'block')
        {
          el.setStyle('display', 'none');
          toggle.innerHTML = MDSS.Localized.Click_to_Add;
          hidden.value = 'false';
        }
        else
        {
          el.setStyle('display', 'block');
          toggle.innerHTML = MDSS.Localized.Click_to_Remove;
          hidden.value = 'true';
        }

      }, {toggle: toggleId, element: elementId, hidden: hiddenId}, this);
    }
    
  },
  
  ErrorModal : function(content)
  {
    var length = Mojo.Util.trim(content).length;
    
    if(length == 0)
    {
      return;
    }
    
      var modal = new YAHOO.widget.Panel("errorModal" + Mojo.Util.generateId(),
       {
          width:"400px",
          height: "200px",
          fixedcenter:true,
          close:true,
          draggable:false,
          zindex:9999,
          modal:true,
          visible:true
        }
      );

      var div = document.createElement('div');
      YAHOO.util.Dom.addClass(div, 'alert alertbox modalAlertBox');
      div.innerHTML = content;

      modal.setBody(div);
      modal.render(document.body);
      modal.bringToTop();
      
      modal.subscribe('hide', function(){
        var that = this;
        setTimeout(function(){
          that.destroy();
        }, 15);
      });
  },

  /**
   * Provides a default request implementation.
   */
  Request : function(handler)
  {
    this.createModal = function(content)
    {
      new MDSS.ErrorModal(content);
    };

    this.onSend = function()
    {
        //Show a modal wait screen to prevent user from clicking an ajax link twice
        if(MDSS.util.wait_for_ajax != null)
        {
          MDSS.util.wait_for_ajax.show();
          MDSS.util.wait_for_ajax.bringToTop();
        }
        else
        {
        MDSS.util.wait_for_ajax =
          new YAHOO.widget.Panel("wait_for_ajax",
            { width:"240px",
              fixedcenter:true,
              close:false,
              draggable:false,
              zindex:999,
              modal:true,
              visible:true
            }
          );

        MDSS.util.wait_for_ajax.setHeader(MDSS.Localized.Ajax_Loading);
        MDSS.util.wait_for_ajax.setBody('<img src="imgs/rel_interstitial_loading.gif" />');
        MDSS.util.wait_for_ajax.render(document.body);
        MDSS.util.wait_for_ajax.bringToTop();
      }
    }

    this.onComplete = function()
    {
      MDSS.util.wait_for_ajax.hide();
    }

    // provide default error handler
    this.onFailure = function(e)
    {
      // Firefox cancels requests when esc is pressed which causes a status code
      // of 0. In this case, to avoid an empty error modal, do nothing.
      if(this.getTransport().status === 0)
      {
        return;
      }
    
      this.createModal(e.getLocalizedMessage());
    };

    this.onProblemExceptionDTO = function(e)
    {
      var content = '';
      if(e instanceof Mojo.$.com.terraframe.mojo.ProblemExceptionDTO)
      {
        var problems = e.getProblems();
        for(var i=0; i<problems.length; i++)
        {
          content += problems[i].getLocalizedMessage()+"<br />";
        }
      }
      else
      {
        content = e.getLocalizedMessage();
      }

      this.createModal(content);
    };

    this.onInvalidSessionException = function(e)
    {
      window.location = '/';
    };
    
    Mojo.Util.copy(new Mojo.ClientRequest(handler), this);
  }
};

/**
 * A basic set for String types only.
 */
MDSS.Set = function()
{
  this._set = {};
}
MDSS.Set.prototype = {

  clear : function()
  {
    this._set = {};
  },

  addAll : function(array)
  {
    for(var i=0; i<array.length; i++)
    {
      this.set(array[i]);
    }
  },
  
  remove : function(value)
  {
    delete this._set[value];
  },

  contains : function(value)
  {
    return this._set[value] != null;
  },

  set : function(value)
  {
    this._set[value] = {};
  },

  size : function()
  {
    return Mojo.Util.getKeys(this._set).length;
  },

  values : function()
  {
    return Mojo.Util.getKeys(this._set);
  }
};

// preload images used in 101, 061, and ontology browser
(function(){
  var imgs = ['imgs/icons/add.png', 'imgs/icons/delete.png', 'imgs/icons/wand.png'];
  Mojo.Iter.forEach(imgs, function(img){
    new Image().src=img;
  });
  
  MDSS.rotationCircle = new Image();
  MDSS.rotationCircle.src = 'imgs/rotationCircle.png';
})();

// Intercept all event listener calls and store them to be
// unhooked later.
(function(){
  var registered = [];
  var E = YAHOO.util.Event;
  var al = E.addListener;
  E.addListener = function(el, sType, fn, obj, overrideContext){
    
    registered.push(el);
    
    al.call(E, el, sType, fn, obj, overrideContext);
  };

  // Unattach all event handlers
  YAHOO.util.Event.on(window, 'unload', function(e){
    var E = YAHOO.util.Event;
    for(var i=0, len=registered.length; i<len; i++)
    {
      try
      {
        E.purgeElement(registered[i], false);
      }
      catch(e)
      {
        // No point in doing anything
      }
    }
    
    delete registered;
  });
})();

Mojo.Meta.newClass('MDSS.ProgressRequest', {
  Instance : {
    initialize : function(func, polling, key)
    {
      this._func = func;
      this._polling = polling;
      this._intervalId = null;
      this._modal = null;
      this._bar = null;
      this._key = Mojo.Util.isString(key) ? key : 'Ajax_Loading';
    },
    
    _initializeModal : function ()
    {
      //Show a modal wait screen to prevent user from clicking an ajax link twice
      this._modal = new YAHOO.widget.Panel("progress_modal",{
        width:"240px",
        fixedcenter:true,
        close:false,
        draggable:false,
        modal:true,
        visible:true
      });
            
      this._modal.setHeader(MDSS.localize(this._key));
      this._modal.setBody("<div id='progress_bar'></div>")
      this._modal.render(document.body);
      this._modal.bringToTop();    

      this._bar = new YAHOO.widget.ProgressBar({
          minValue: 0,
          maxValue: 100,
          value: 0,
          height: "15px",
          width: "220px"          
      }).render("progress_bar");
    },
    
    _destroyModal : function ()
    {
      if(this._modal != null)
      {
        this._modal.hide();
        this._modal == null;
      }
    },
    
    start : function()
    {        
      this._sendRequest();
      this._startPolling();
    },
    
    _sendRequest : function()
    {
      var request = new MDSS.Request({
        that : this,
        onComplete : function()
        {
          this.that._destroyModal();
        },
        onSend : function()
        {
          this.that._initializeModal();
        },
        onSuccess : function()
        {
          this.that._stopPolling();                
        }
      });
      
      // Re enable the save button widget when there is a problem
      var oldOnProblemExceptionDTO = request.onProblemExceptionDTO;
      var newOnProblemExceptionDTO = function(e) {
        oldOnProblemExceptionDTO.apply(request, [e]);

        this.that._destroyModal();
        this.that._stopPolling();
      }
      
      var oldOnFailure = request.onFailure;
      var newOnFailure = function(e) {
        oldOnFailure.apply(request, [e]);

        this.that._destroyModal();
        this.that._stopPolling();
      }

      request.onProblemExceptionDTO = newOnProblemExceptionDTO;
      request.onFailure = newOnFailure;
      
      this._func(request);
    },   
    
    _startPolling : function()
    {
      var bound = Mojo.Util.bind(this, this._pollServer);

      this._intervalId = setInterval(bound, 1000);
    },
    
    _pollServer : function()
    {
      var request = new MDSS.Request({
        that : this,
        onComplete : function() {},
        onSend : function() {},
        onFailure : function(){},
        onProblemExceptionDTO : function(){},
        onSuccess : function(percent)
        {
          if(percent == -1)
          {
            if(this.that._modal != null)
            {
              this.that._destroyModal();            	
              this.that._stopPolling();                            	
            }
          }
          else
          {
            this.that._setPercent(percent);
          }
        }
      });
          
      this._polling(request);    
    },
    
    _setPercent :  function(percent)
    {
      if(this._modal != null) {
        this._bar.set('value',percent);
      }
    },
    
    _stopPolling : function()
    {
      clearInterval(this._intervalId);
    }
  }  
});
