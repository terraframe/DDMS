
Mojo.Meta.newClass('com.terraframe.mojo.inspector.Inspector',{Alias:Mojo.$,IsSingleton:true,Instance:{initialize:function()
{var prefix=this.$class.getQualifiedName()+'_';this._mainWindowId=prefix+'mainWindow';this._mainMinId=prefix+'mainMin';this._mainExitId=prefix+'mainExit';this._mainDrag=prefix+'mainDrag';this._secWindowId=prefix+'secWindow';this._secMaxId=prefix+'secMax';this._secExitId=prefix+'secExit';this._secDrag=prefix+'secDrag';this._explorerTab=prefix+'explorerTab';this._loggerTab=prefix+'loggerTab';this._tracerTab=prefix+'tracerTab';this._explorerContent=prefix+'explorerContent';this._loggerContent=prefix+'loggerContent';this._tracerContent=prefix+'tracerContent';this._mainWindow=null;this._secWindow=null;this._dragEvent=null;this._clientX=null;this._clientY=null;this._classRE=/^(?:(?!(Mojo.aspect)|(Mojo.Util)|(Mojo.Meta)|(com\.terraframe\.mojo\.((Class)|(Method)|(Constant)|(Base)|(inspector\.)))).)*$/;this._methodRE=/^(?!toString).*$/;this._explorer=new com.terraframe.mojo.inspector.Explorer(this,this._explorerTab,this._explorerContent);this._logger=new com.terraframe.mojo.inspector.Logger(this,this._loggerTab,this._loggerContent);this._tracer=new com.terraframe.mojo.inspector.Tracer(this,this._tracerTab,this._tracerContent);this._currentContent=this._explorer;this.addAroundAdvice(this._classRE);var addNew=Mojo.Util.curry(this.addNewClass,this);var capture=new Mojo.aspect.AfterAdvice(/Mojo\.Meta/,/newClass/,addNew,Mojo.aspect.Advice.MATCH_STATIC);capture.weave();this._rendered=false;},isRendered:function()
{return this._rendered;},addNewClass:function(inspector,args,returnObj,klass,method)
{var className=returnObj.$class.getQualifiedName();var r='^'+className.replace('.','\\.')+'$';var re=new RegExp(r);var methodRE=/^(?!toString).*$/;inspector.addAroundAdvice(re);inspector._explorer.render();},addAroundAdvice:function(classRE)
{var aroundWrapper=Mojo.Util.curry(this.aroundWrapper,this);var around=new Mojo.aspect.AroundAdvice(classRE,this._methodRE,aroundWrapper);around.weave();},aroundWrapper:function(mainWin,args,proceed,klass,method)
{var id=mainWin._tracer.beforeTrace(this,args,klass,method);try
{var obj=proceed.apply(this,(args));}
catch(e)
{mainWin._logger.logError(this,klass,method,e,id);throw e;}
mainWin._tracer.afterTrace(this,obj,klass,method);return obj;},render:function()
{this._buildHTML();this._hookEvents();this._explorer.render();this._logger.render();this._tracer.render();this._rendered=true;},_buildHTML:function()
{var tStyle='display: inline; list-style-type: none; width: 50px; height: 30px; cursor: pointer; border: 1px solid black; background-color: #eee; padding: 4px;';var cStyle='padding: 5px 0px 5px 5px;';var html='';html+='<div style="border: 1px solid black; position: absolute; width: 800px; height: 600px; background-color: white">';html+='  <div id="'+this._mainDrag+'" style="cursor: move; height: 30px; width: 100%; border-bottom: 1px solid black;">';html+='    <div id="'+this._mainDrag+'_label" style="float: left; margin: 3px 3px 3px 5px">JS Inspector</div>';html+='    <div id="'+this._mainExitId+'" style="cursor: pointer; float: right; width: 20px; height: 20px; border: 1px solid black; text-align: center; margin: 3px 3px">x</div>';html+='    <div id="'+this._mainMinId+'" style="cursor: pointer; float: right; width: 20px; height: 20px; border: 1px solid black; text-align: center; margin: 3px 3px">-</div>';html+='  </div>';html+='  <div stle="height: 100%; width: 100%">';html+='    <ul style="margin: 15px 0px 4px 0px; padding-left: 5px">';html+='      <li id="'+this._explorerTab+'" style="'+tStyle+' background-color: #00ffff;">Explorer</li>';html+='      <li id="'+this._loggerTab+'" style="'+tStyle+'">Logger</li>';html+='      <li id="'+this._tracerTab+'" style="'+tStyle+'">Tracer</li>';html+='    </ul>';html+='    <div style="border-top: 1px solid black">';html+='      <div id="'+this._explorerContent+'" style="display: block; '+cStyle+'">';html+='      </div>';html+='      <div id="'+this._loggerContent+'" style="display: none; '+cStyle+'">';html+='      </div>';html+='      <div id="'+this._tracerContent+'" style="display: none; '+cStyle+'">';html+='      </div>';html+='    </div>';html+='  </div>';html+='</div>';this._mainWindow=document.createElement('div');this._mainWindow.style.position='absolute';this._mainWindow.style.display='none';this._mainWindow.style.left='10px';this._mainWindow.style.top='10px';this._mainWindow.style.zIndex='999999999';this._mainWindow.style.fontFamily='Arial';this._mainWindow.style.fontSize='10pt';this._mainWindow.id=this._mainWindowId;this._mainWindow.innerHTML=html;html='';html+='<div id="'+this._secDrag+'" style="cursor: move; height: 30px; width: 150px; border: 1px solid black; background-color: white">';html+='  <div id="'+this._secDrag+'_label" style="float: left; margin: 3px 3px 3px 5px">JS Inspector</div>';html+='  <div id="'+this._secExitId+'" style="cursor: pointer; float: right; width: 20px; height: 20px; border: 1px solid black; text-align: center; margin: 3px 3px">x</div>';html+='  <div id="'+this._secMaxId+'" style="cursor: pointer; float: right; width: 20px; height: 20px; border: 1px solid black; text-align: center; margin: 3px 3px">+</div>';html+='</div>';this._secWindow=document.createElement('div');this._secWindow.style.position='absolute';this._secWindow.style.display='block';this._secWindow.style.left='10px';this._secWindow.style.top='10px';this._secWindow.id=this._secWindowId;this._secWindow.style.zIndex='999999999';this._secWindow.innerHTML=html;var body=document.getElementsByTagName('body')[0];body.appendChild(this._mainWindow);body.appendChild(this._secWindow);},_hookEvents:function()
{var manager=com.terraframe.mojo.inspector.EventManager.getInstance();var IEvent=com.terraframe.mojo.inspector.IEvent;manager.addEvent(new IEvent(this._mainWindowId,'click',this._delegateClick,this));manager.addEvent(new IEvent(this._mainMinId,'click',this.hide,this));manager.addEvent(new IEvent(this._mainExitId,'click',this.destroy,this));manager.addEvent(new IEvent(this._secMaxId,'click',this.show,this));manager.addEvent(new IEvent(this._secExitId,'click',this.destroy,this));manager.addEvent(new IEvent(this._mainDrag,'mousedown',this.startDrag,this));manager.addEvent(new IEvent(this._secDrag,'mousedown',this.startDrag,this));manager.addEvent(new IEvent(this._mainDrag,'mouseup',this.endDrag,this));manager.addEvent(new IEvent(this._secDrag,'mouseup',this.endDrag,this));manager.addEvent(new IEvent(this._explorerTab,'click',this.doTab,this,this._explorer));manager.addEvent(new IEvent(this._loggerTab,'click',this.doTab,this,this._logger));manager.addEvent(new IEvent(this._tracerTab,'click',this.doTab,this,this._tracer));},doTab:function(e,content)
{if(this._currentContent===content)
{return;}
this._currentContent.hide();content.show();if(!content.rendered())
{content.render();}
document.getElementById(this._explorerTab).style.backgroundColor='#eee';document.getElementById(this._loggerTab).style.backgroundColor='#eee';document.getElementById(this._tracerTab).style.backgroundColor='#eee';content.getTab().style.backgroundColor='#00ffff';this._currentContent=content;},isDragging:function()
{return this._dragEvent!=null;},startDrag:function(e)
{var el=e.target;var win;if(el.id===this._mainDrag||el.id===this._mainDrag+'_label')
{win=this._mainWindow;}
else if(el.id===this._secDrag||el.id===this._secDrag+'_label')
{win=this._secWindow;}
else
{return;}
this._dragEvent=new com.terraframe.mojo.inspector.IEvent(window,'mousemove',this.doDrag,this,win);var manager=com.terraframe.mojo.inspector.EventManager.getInstance();manager.addEvent(this._dragEvent);},endDrag:function(e)
{if(this._dragEvent!=null)
{var manager=com.terraframe.mojo.inspector.EventManager.getInstance();manager.removeEvent(this._dragEvent);this._dragEvent=null;this._clientX=null;this._clientY=null;}},doDrag:function(e,win)
{if(this._clientX!=null&&this._clientY!=null)
{var dX=this._clientX-e.clientX;var dY=this._clientY-e.clientY;var wX=parseInt(win.style.left);var wY=parseInt(win.style.top);var nX=(wX-dX)+'px';var nY=(wY-dY)+'px';this._secWindow.style.left=nX;this._secWindow.style.top=nY;this._mainWindow.style.left=nX;this._mainWindow.style.top=nY;}
this._clientX=e.clientX;this._clientY=e.clientY;},show:function()
{this._secWindow.style.display='none';this._mainWindow.style.display='block';},hide:function()
{this._secWindow.style.display='block';this._mainWindow.style.display='none';},destroy:function()
{var manager=com.terraframe.mojo.inspector.EventManager.getInstance();manager.removeAll();this._mainWindow.parentNode.removeChild(this._mainWindow);this._secWindow.parentNode.removeChild(this._secWindow);this._mainWindow=null;this._secWindow=null;this._explorer=null;this._logger=null;this._tracer=null;this._rendered=false;},_delegateClick:function(e)
{var el=e.target;if(el.nodeName==='A'&&el.name&&el.name.match(/\w+:/))
{var pieces=el.name.split(':');var action=pieces[0];var data=pieces[1];if(action==='viewClass')
{this.doTab(null,this._explorer);this._explorer.getClassDefinition(data);}
else if(action==='viewMethod')
{this.doTab(null,this._explorer);this._explorer.getMethodDefinition(data);}
else if(action==='viewTracer')
{this.doTab(null,this._tracer);}
else if(action==='viewHierarchy')
{this.doTab(null,this._explorer);this._explorer.getHierarchy();}}}},Static:{launch:function()
{var inspector=Mojo.$.com.terraframe.mojo.inspector.Inspector.getInstance();inspector.isRendered()?inspector.show():inspector.render();}}});Mojo.Meta.newClass('com.terraframe.mojo.inspector.Content',{Alias:Mojo.$,IsAbstract:true,Instance:{initialize:function(mainWin,tabId,contentId)
{this._mainWin=mainWin;this._tabId=tabId;this._contentId=contentId;this._rendered=false;},getTab:function()
{return document.getElementById(this._tabId);},getEl:function()
{return document.getElementById(this._contentId);},newEl:function(type)
{return document.createElement(type);},rendered:function()
{return this._rendered;},show:function()
{this.getEl().style.display='block';},hide:function()
{this.getEl().style.display='none';},render:function()
{this._render();this._rendered=true;}},Static:{makeA:function(content,name,useDefault,href)
{var p=useDefault?'default':'pointer';var h='onmouseover="this.style.color=\'#0000ff\'" onmouseout="this.style.color=\'#000000\'"';return'<a href="'+(href!=null?href:'#')+'" name="'+name+'" style="color: black; text-decoration: none; cursor: '+
p+';" '+(useDefault?'':h)+'>'+content+'</a>';},viewClassAction:function(klass,content)
{var className=klass.$class.getQualifiedName();return com.terraframe.mojo.inspector.Content.makeA((content?content:className),'viewClass:'+className);},viewMethodAction:function(klass,method)
{var m=method.getName();return com.terraframe.mojo.inspector.Content.makeA(m,'viewMethod:'+klass.$class.getQualifiedName()+'.'+m);},viewTracerAction:function(id)
{return com.terraframe.mojo.inspector.Content.makeA('GOTO','viewTracer:'+id,false,'#'+id);},viewHierarchyAction:function()
{return com.terraframe.mojo.inspector.Content.makeA('Hierarchy','viewHierarchy:');}}});Mojo.Meta.newClass('com.terraframe.mojo.inspector.Explorer',{Extends:com.terraframe.mojo.inspector.Content,Instance:{initialize:function(mainWin,tabId,contentId)
{this.$initialize(mainWin,tabId,contentId);var prefix=this.$class.getQualifiedName()+'_';this._classList=prefix+'classList';this._definition=prefix+'definition';this._method=prefix+'method';},_render:function()
{var el=this.getEl();var classes=Mojo.Meta.getClasses();classes.sort();var classLis='';var pcks={};for(var i=0;i<classes.length;i++)
{var className=classes[i];var klass=Mojo.Meta.findClass(className);var pckName=klass.$class.getPackage();if(pckName!==''&&!pcks[pckName])
{var a=this.constructor.makeA(pckName,pckName,true);classLis+='<li style="list-style-type: none;"><strong>'+a+'</strong></li>';pcks[pckName]={};}
var a=this.constructor.viewClassAction(klass);classLis+='<li style="list-style-type: none;">'+a+'</li>';}
var pckNames=Mojo.Util.getKeys(pcks);var pckLis='';for(var i=0;i<pckNames.length;i++)
{var pckName=pckNames[i];var a=this.constructor.makeA(pckName,'',false,'#'+pckName);pckLis+='<li style="list-style-type: none;">'+a+'</li>';}
var html='';html+='<div style="float: left; width: 250px; height: 527px; overflow: scroll;">';html+='  '+this.constructor.viewHierarchyAction()+'<hr />';html+='  Packages';html+='  <ul style="padding: 5px; margin-top: 0px;">';html+=pckLis;html+='  </ul><hr />';html+='  Classes';html+='  <ul id="'+this._classList+'" style="padding: 5px; margin-top: 0px">';html+=classLis;html+='  </ul>';html+='</div>';html+='<div id="'+this._definition+'" style="float: left; width: 545px; height: 527px; overflow: scroll;">';html+='</div>';html+='<div id="'+this._method+'" style="float: left; width: 540px; height: 527px; overflow: scroll; display: none">';html+='</div>';el.innerHTML=html;this.getHierarchy();},getHierarchy:function()
{var root=Mojo.$.com.terraframe.mojo.Base;that=this;function getChildren(parent)
{var node='<li style="list-style-type: none">';node+='|--'+that.constructor.viewClassAction(parent);node+='<ul style="margin:0px; padding-left:1em">';var subs=parent.$class.getSubClasses(true);var names=Mojo.Util.getKeys(subs);names.sort();for(var i=0;i<names.length;i++)
{node+=getChildren(subs[names[i]]);}
node+='</ul>';return node;}
var nodeLi=getChildren(root);var html='';html+='<div style="margin: 10px">';html+='<div style="font-weight: bold;">Hierarchy</div><hr />';html+='<ul style="margin:0px; padding:0px">'+nodeLi+'</ul>';html+='</div>';var el=document.getElementById(this._definition);el.innerHTML=html;},getMethodDefinition:function(qualifiedMethod)
{var ind=qualifiedMethod.lastIndexOf('.');var className=qualifiedMethod.substring(0,ind);var methodName=qualifiedMethod.substring(ind+1);var klass=Mojo.Meta.findClass(className);var method=klass.$class.getMethod(methodName);var back=this.constructor.viewClassAction(klass,'&#8656;');var body=method.getMethod().toString();body=body.replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;");var highlighter=new Mojo.$.com.terraframe.mojo.inspector.SyntaxHighlighter();var src=highlighter.parse(body);var html='';html+='<div style="margin: 10px">';html+='<div style="font-weight: bold;">'+back+' '+qualifiedMethod+'</div><hr />';html+='<pre>';html+=src;html+='</pre>';var defPane=document.getElementById(this._definition);var methPane=document.getElementById(this._method);methPane.innerHTML=html;defPane.style.display='none';methPane.style.display='block';},getClassDefinition:function(qualifiedName)
{var klass=Mojo.Meta.findClass(qualifiedName);var $class=klass.$class;var html='';html+='<div style="margin: 10px">';html+='<div style="font-weight: bold;">'+$class.toString()+'</div><hr />';var extendsName;if($class.getSuperClass()!==Object)
{extendsName=this.constructor.viewClassAction($class.getSuperClass());}
else
{extendsName='Object [JS Native]';}
var tAttrs='cellpadding="3" cellspacing="0" border="1" style="font-size: 10pt; margin-bottom: 15px; white-space: nowrap"';var pckName=$class.getPackage();if(pckName==='')
{pckName='&nbsp;';}
var alias=$class.getAlias();var subclasses=$class.getSubClasses();var sublinks=[];for(var i=0;i<subclasses.length;i++)
{var a=this.constructor.viewClassAction(subclasses[i]);sublinks.push(a);}
html+='Class Definition:<br />';html+='<table '+tAttrs+'>';html+='  <tr><th>Property</th><th>Value</th></tr>';html+='  <tr><td>Package</td><td>'+pckName+'</td></tr>';html+='  <tr><td>Class Name</td><td>'+$class.getName()+'</td></tr>';html+='  <tr><td>Abstract</td><td>'+$class.isAbstract()+'</td></tr>';html+='  <tr><td>Extends</td><td>'+extendsName+'</td></tr>';html+='  <tr><td>Sub Classes</td><td>'+(sublinks.length>0?sublinks.join('<br />'):'&nbsp;')+'</td></tr>';html+='</table>';html+='Constants:<br />';html+='<table '+tAttrs+'>';html+='  <tr><th>Name</th><th>Value</th><th>Defined On</th></tr>';var constants=$class.getConstants(true);var names=Mojo.Util.getKeys(constants);names.sort();for(var i=0;i<names.length;i++)
{var constObj=constants[names[i]];var cClass=constObj.getDefiningClass();var defining;if(cClass===klass)
{defining='[this class]';}
else
{defining=this.constructor.viewClassAction(cClass);}
html+='<tr><td>'+constObj.getName()+'</td><td>'+constObj.getValue()+'</td><td>'+defining+'</td></tr>';}
html+='</table>';var mRows='';var methods=$class.getInstanceMethods(true);var names=Mojo.Util.getKeys(methods);names.sort();for(var i=0;i<names.length;i++)
{var m=names[i]
var method=methods[m];var mClass=method.getDefiningClass();var defining;if(mClass===klass)
{defining='[this class]';}
else
{defining=this.constructor.viewClassAction(mClass);}
var nameA=this.constructor.viewMethodAction(klass,method);if(method.isConstructor())
{nameA='<strong>'+nameA+'</strong>';}
mRows+='<tr><td>'+nameA+'</td>';mRows+='<td>'+method.isOverride()+'</td>';mRows+='<td>'+method.getArity()+'</td>';mRows+='<td>'+defining+'</td>';mRows+=this._listAspects(method);m
mRows+='</tr>';}
html+='Instance Methods:<br />';html+='<table '+tAttrs+'>';html+='  <tr><th>Name</th><th>Override</th><th>Arity</th><th>Defined On</th><th>Aspects</th></tr>';html+=mRows;html+='</table>';mRows='';methods=$class.getStaticMethods(true);names=Mojo.Util.getKeys(methods);names.sort();for(var i=0;i<names.length;i++)
{var m=names[i]
var method=methods[m];var mClass=method.getDefiningClass();var defining;if(mClass===klass)
{defining='[this class]';}
else
{defining=this.constructor.viewClassAction(mClass);}
mRows+='<tr><td>'+this.constructor.viewMethodAction(klass,method)+'</td>';mRows+='<td>'+method.isHiding()+'</td>';mRows+='<td>'+method.getArity()+'</td>';mRows+='<td>'+defining+'</td>';mRows+=this._listAspects(method);mRows+='</tr>';}
html+='Static Methods:<br />';html+='<table '+tAttrs+'>';html+='  <tr><th>Name</th><th>Hiding</th><th>Arity</th><th>Defined On</th><th>Aspects</th></tr>';html+=mRows;html+='</table>';html+='</div>';var defPane=document.getElementById(this._definition);defPane.innerHTML=html;document.getElementById(this._method).style.display='none';defPane.style.display='block';},_listAspects:function(method)
{var html='<td>';var aspects=method.getAspects();if(aspects.length!==0)
{for(var i=0;i<aspects.length;i++)
{html+='<strong>'+i.toString()+'</strong>: '+aspects[i].toString()+'<br />';}}
else
{html+='&nbsp;'}
html+='</td>';return html;}},});Mojo.Meta.newClass('com.terraframe.mojo.inspector.Logger',{Extends:com.terraframe.mojo.inspector.Content,Constants:{MAX_ROWS:500,},Instance:{initialize:function(mainWin,tabId,contentId)
{this.$initialize(mainWin,tabId,contentId);this._rowCount=0;this._logTable=this.$class.getQualifiedName()+'_logTable';},_render:function()
{var el=this.getEl();var html='';html+='<div style="overflow: scroll; height: 527px;">';var tAttrs='cellpadding="3" cellspacing="0" border="1" style="font-size: 10pt; margin-bottom: 15px; white-space: nowrap"';html+='Latest 500 Log Entries:<br />';html+='<table id="'+this._logTable+'" '+tAttrs+'>';html+='  <tr><th>#</th><th>Type</th><th>Time</th><th>Error</th><th>File</th><th>Line</th><th>Tracer</th></tr>';html+='</table>';html+='</div>';el.innerHTML=html;this._logger=document.getElementById(this._logTable);},logWarning:function()
{},logError:function(context,klass,method,error,id)
{var toStr=method.getName()==='initialize'?Mojo.$.com.terraframe.mojo.Base.prototype.toString.call(context):context.toString();var traceA=this.constructor.viewTracerAction(id);document.getElementById(id+'_row').style.backgroundColor='red';var html='';html+='<td>'+this._rowCount+'</td>';html+='<td><span style="color: red; font-weight: bold">Error</span></td>';html+='<td>'+Mojo.Util.toISO8601(new Date())+'</td>';html+='<td>'+error+'</td>';html+='<td>'+error.fileName+'</td>';html+='<td>'+error.lineNumber+'</td>';html+='<td>'+traceA+'</td>';this.addRow(html);},addRow:function(html)
{if(this._rowCount>this.constructor.MAX_ROWS)
{this.removeOldest();}
var tr=this._logger.insertRow(1);tr.innerHTML=html;this._rowCount++;},removeOldest:function()
{this._tracer.deleteRow(this.constructor.MAX_ROWS+1);}}});Mojo.Meta.newClass('com.terraframe.mojo.inspector.Tracer',{Extends:com.terraframe.mojo.inspector.Content,Constants:{MAX_ROWS:500,},Instance:{initialize:function(mainWin,tabId,contentId)
{this.$initialize(mainWin,tabId,contentId);this._rowCount=0;this._tracerTable=this.$class.getQualifiedName()+'_tracerTable';},_render:function()
{var el=this.getEl();var html='';html+='<div style="overflow: scroll; height: 527px;">';var tAttrs='cellpadding="3" cellspacing="0" border="1" style="font-size: 10pt; margin-bottom: 15px; white-space: nowrap"';html+='Latest 500 Traces:<br />';html+='<table id="'+this._tracerTable+'" '+tAttrs+'>';html+='  <tr><th>#</th><th>Time</th><th>Call</th><th>Class Name</th><th>Method</th><th>Context</th><th>Parameters</th><th>Return</th></tr>';html+='</table>';html+='</div>';el.innerHTML=html;this._tracer=document.getElementById(this._tracerTable);},addRow:function(html,id)
{if(this._rowCount>this.constructor.MAX_ROWS)
{this.removeOldest();}
var tr=this._tracer.insertRow(1);tr.id=id+'_row';tr.innerHTML=html;this._rowCount++;},removeOldest:function()
{this._tracer.deleteRow(this.constructor.MAX_ROWS+1);},beforeTrace:function(context,args,klass,method)
{var id=new String(Math.random()).substring(2);var toStr=!method.isStatic()&&method.getName()==='initialize'?Mojo.$.com.terraframe.mojo.Base.prototype.toString.call(context):context.toString();var traceA=this.constructor.makeA('BEFORE',id,true);var html='';html+='<td>'+this._rowCount+'</td>';html+='<td>'+Mojo.Util.toISO8601(new Date())+'</td>';html+='<td>'+traceA+'</td>';html+='<td>'+this.constructor.viewClassAction(klass)+'</td>';html+='<td>'+this.constructor.viewMethodAction(klass,method)+'</td>';html+='<td>'+toStr+'</td>';var argCell='';if(args.length===0)
{argCell='&nbsp;'}
else
{for(var i=0;i<args.length;i++)
{argCell+='<strong>'+i.toString()+':</strong> ';var arg=args[0];if(arg==null)
{argCell+='[null] null<br />';}
else if(arg instanceof Mojo.$.com.terraframe.mojo.Base)
{argCell+='['+this.constructor.viewClassAction(arg.constructor)+'] '+arg.toString()+'<br />';}
else if(Mojo.Util.isObject(arg)||Mojo.Util.isArray(arg)||Mojo.Util.isFunction(arg))
{argCell+='['+(typeof arg)+'] '+(typeof arg)+'<br />';}
else
{var value;if(Mojo.Util.isString(arg)&&arg.length>25)
{value=arg.substring(0,25)+' ...';}
else
{value=arg;}
argCell+='['+(typeof arg)+'] '+value+'<br />';}}}
html+='<td>'+argCell+'</td>';html+='<td>n/a</td>';this.addRow(html,id);return id;},afterTrace:function(context,retObj,klass,method)
{var toStr=!method.isStatic()&&method.getName()==='initialize'?Mojo.$.com.terraframe.mojo.Base.prototype.toString.call(context):context.toString();var html='';html+='<td>'+this._rowCount+'</td>';html+='<td>'+Mojo.Util.toISO8601(new Date())+'</td>';html+='<td>AFTER</td>';html+='<td>'+this.constructor.viewClassAction(klass)+'</td>';html+='<td>'+this.constructor.viewMethodAction(klass,method)+'</td>';html+='<td>'+toStr+'</td>';var retCell='';if(typeof retObj==='undefined')
{retCell+='&nbsp;';}
else if(retObj===null)
{retCell+='[null] null<br />';}
else if(retObj instanceof Mojo.$.com.terraframe.mojo.Base)
{retCell+='['+this.constructor.viewClassAction(retObj.constructor)+'] '+retObj.toString()+'<br />';}
else if(Mojo.Util.isObject(retObj)||Mojo.Util.isArray(retObj)||Mojo.Util.isFunction(retObj))
{retCell+='['+(typeof retObj)+'] '+(typeof retObj)+'<br />';}
else
{retCell+='['+(typeof retObj)+'] '+retObj+'<br />';}
html+='<td>n/a</td>';html+='<td>'+retCell+'</td>';this.addRow(html);}}});Mojo.Meta.newClass('com.terraframe.mojo.inspector.IEvent',{Instance:{initialize:function(element,type,handler,context,obj)
{this._id=new String(Math.random()).substring(2);this._element=Mojo.Util.isString(element)?document.getElementById(element):element;this._type=type;this._handler=handler;this._context=context;this._obj=obj||{};this._wrapper=Mojo.Util.bind(this,function(e){this._handler.call(this._context,e,this._obj);});},getId:function()
{return this._id;},getElement:function()
{return this._element;},getType:function()
{return this._type;},getWrapper:function()
{return this._wrapper;}}});Mojo.Meta.newClass('com.terraframe.mojo.inspector.EventManager',{IsSingleton:true,Instance:{initialize:function()
{this._eventMap={};},_addListener:function(event)
{var wrapper=event.getWrapper();var type=event.getType();var el=event.getElement();if(el.addEventListener)
{el.addEventListener(type,wrapper,false);}
else
{el.attachEvent(type,wrapper);}},_removeListener:function(event)
{var wrapper=event.getWrapper();var type=event.getType();var el=event.getElement();if(el.removeEventListener)
{el.removeEventListener(type,wrapper,false);}
else
{el.detachEvent(type,wrapper);}},addEvent:function(event)
{this._addListener(event);this._eventMap[event.getId()]=event;},removeEvent:function(event)
{this._removeListener(event);delete this._eventMap[event.getId()];},removeAll:function()
{var ids=Mojo.Util.getKeys(this._eventMap);for(var i=0;i<ids.length;i++)
{this._removeListener(this._eventMap[ids[i]]);}
this._eventMap={};}}});Mojo.Meta.newClass('com.terraframe.mojo.inspector.SyntaxHighlighter',{Alias:Mojo.$,Constants:{KEYWORDS:["break","case","comment","continue","default","delete","do","document","else","export","for","function","if","import","in","label","new","null","prototype","return","switch","this","typeof","var","void","while","window","with","goto","true","false","try","catch","throw","throws","finally","Array","Boolean","Date","Error","Math","Number","RegExp","String"]},Instance:{initialize:function()
{},parse:function(src)
{var exp=new RegExp();var reg1=new RegExp();var that=this;src=src.replace(/</g,"&lt;").replace(/>/g,"&gt;");exp.compile("/\\*","g");src=src.replace(exp,function(str,index,s2){var bRet=that._withinString(s2,index);if(bRet){return str;}else{return"<c>"+str;}});exp.compile("\\*/","g");src=src.replace(exp,function(str,index,s2){var bRet=that._withinString(s2,index);if(bRet){return str;}else{return str+"</c>";}});src=src.replace(/\/\/(.*)/g,function(str,s,index,s2){var bRet=that._withinString(s2,index);if(bRet){return"//"+s;}else{return"<c>//"+s+"</c>";}});var index=src.indexOf("<c>");if(index!=-1){var s1=src.substr(0,index);var s2=src.substring(index,src.length);s1=this._processKeyword(reg1,s1);s1=this._processString(s1);src=s1+s2;}else{src=this._processKeyword(reg1,src);src=this._processString(src);}
exp.compile("</c>([^<]*)<c>","g");src=src.replace(exp,function(strOrg,sMatch){sMatch="</c>"+sMatch+"<c>";sMatch=that._processKeyword(reg1,sMatch);sMatch=that._processString(sMatch);return sMatch;});var index=src.lastIndexOf("</c>");if(index!=-1){var s1=src.substr(0,index+4);var s2=src.substring(index+4,src.length);s2=this._processKeyword(reg1,s2);s2=this._processString(s2);src=s1+s2;}
src=src.replace(/ /g,"&nbsp;").replace(/\t/g,"&nbsp;&nbsp;&nbsp;&nbsp;").replace(/\n/g,"<br />");src=src.replace(/<k>/g,"<span style='color: #0000FF'>").replace(/<\/k>/g,"</span>").replace(/<c>/g,"<span style='color: #008200'>").replace(/<\/c>/g,"</span>").replace(/<s>/g,"<span style='color: #A51410'>").replace(/<\/s>/g,"</span>");return src;},_withinString:function(str,index){var len=str.length;var rq=0,rq1=0;for(var i=index;i<len;i++){var ch=str.charAt(i);if(ch=="\""){rq++;}
if(ch=="'"){rq1++;}
if(ch=="\n"){break;}}
if(rq%2!=0){return true;}
if(rq1%2!=0){return true;}
return false;},_processKeyword:function(exp,sMatch){var js_keyword=this.constructor.KEYWORDS;for(var i=0;i<js_keyword.length;i++){exp.compile("\\b"+js_keyword[i]+"\\b","g");sMatch=sMatch.replace(exp,"<k>"+js_keyword[i]+"</k>");}
return sMatch;},_processString:function(sMatch){var that=this;sMatch=sMatch.replace(/(.*)(\".*\")(.*)/g,function(sOrg,s0,s1,s2){s0=that._processString(s0);s1="<s>"+s1+"</s>";s2=that._processString(s2);return s0+s1+s2;});sMatch=sMatch.replace(/(.*)(\'.*\')(.*)/g,function(sOrg,s0,s1,s2){s0=that._processString(s0);s1="<s>"+s1+"</s>";s2=that._processString(s2);return s0+s1+s2;});return sMatch;}}});