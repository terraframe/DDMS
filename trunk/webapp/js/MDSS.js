/**
 * Root MDSS namespace.
 */
var MDSS = {

  wait_for_ajax : null,

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
     * Curries the given function with any given arguments.
     */
    curry : function(func)
    {
      var args = [].splice.call(arguments, 1);
      return function(){
        return func.apply(this, args.concat([].splice.call(arguments, 0)))
      }
    },

    bind : function(thisRef, func)
    {
      var args = [].splice.call(arguments, 2);
      return function(){
        return func.apply(thisRef, args.concat([].splice.call(arguments, 0)))
      }
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
      var modal = new YAHOO.widget.Panel("errorModal",
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
      this.createModal(e.getLocalizedMessage());
    };

    this.onProblemExceptionDTO = function(e)
    {
      var problems = e.getProblems();
      var content = '';
      for(var i=0; i<problems.length; i++)
      {
        content += problems[i].getLocalizedMessage()+"<br />";
      }

      this.createModal(content);
    };

    this.onInvalidSessionException = function(e)
    {
      window.location = '/';
    };

    Mojo.util.copy(new Mojo.ClientRequest(handler), this);
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
    return Mojo.util.getKeys(this._set).length;
  },

  values : function()
  {
    return Mojo.util.getKeys(this._set);
  }
};
