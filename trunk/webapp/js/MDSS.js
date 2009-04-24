/**
 * Root MDSS namespace.
 */
var MDSS = {

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
        func.apply(this, args.concat([].splice.call(arguments, 0)))
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
  	toggleVisibility : function(elementId, toggleId)
  	{
      YAHOO.util.Event.on(toggleId, 'click', function(e, obj){

        var el = new YAHOO.util.Element(obj.element);
        var toggle = document.getElementById(obj.toggle);
        if(el.getStyle('display') === 'block')
        {
          el.setStyle('display', 'none');
          toggle.innerHTML = MDSS.Localized.Toggle.Show;
        }
        else
        {
          el.setStyle('display', 'block');
          toggle.innerHTML = MDSS.Localized.Toggle.Hide;
        }

      }, {toggle: toggleId, element: elementId}, this);
  	}

  },

  /**
   * Provides a default request implementation.
   */
  Request : function(handler)
  {
  	this.createModal = function(content)
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
      modal.bringToTop();
      modal.render(document.body);
  	};

  	this.onSend = function()
  	{
        //Show a modal wait screen to prevent user from clicking an ajax link twice
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
    }

    Mojo.util.copy(new Mojo.ClientRequest(handler), this);
  }
};
