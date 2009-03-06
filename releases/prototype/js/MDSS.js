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
    }
  }
};
