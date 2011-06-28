
YAHOO.namespace("yui.examples");if((YAHOO.widget.LogReader)&&(YAHOO.util.Dom.get("loggerDiv"))){YAHOO.yui.examples.exampleLogger=new YAHOO.widget.LogReader("loggerDiv");YAHOO.yui.examples.loggerInit=function(){YAHOO.util.Dom.setStyle("loggerDiv","height","auto");YAHOO.util.Dom.setStyle("loggerDiv","visibility","visible");}
YAHOO.util.Event.onDOMReady(YAHOO.yui.examples.loggerInit);}
YAHOO.yui.examples.onLinkButtonsMarkupReady=function(){if(YAHOO.util.Dom.get("loggerLink")){var loggerButton=new YAHOO.widget.Button("loggerLink");}
if(YAHOO.util.Dom.get("newWindowLink")){var newWindowButton=new YAHOO.widget.Button("newWindowLink");}}
YAHOO.util.Event.onDOMReady(YAHOO.yui.examples.onLinkButtonsMarkupReady);