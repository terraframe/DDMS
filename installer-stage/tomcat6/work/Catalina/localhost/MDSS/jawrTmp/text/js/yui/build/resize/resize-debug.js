
(function(){var D=YAHOO.util.Dom,Event=YAHOO.util.Event,Lang=YAHOO.lang;var Resize=function(el,config){YAHOO.log('Creating Resize Object','info','Resize');var oConfig={element:el,attributes:config||{}};Resize.superclass.constructor.call(this,oConfig.element,oConfig.attributes);};Resize._instances={};Resize.getResizeById=function(id){if(Resize._instances[id]){return Resize._instances[id];}
YAHOO.log('No Instance Found','error','Resize');return false;};YAHOO.extend(Resize,YAHOO.util.Element,{CSS_RESIZE:'yui-resize',CSS_DRAG:'yui-draggable',CSS_HOVER:'yui-resize-hover',CSS_PROXY:'yui-resize-proxy',CSS_WRAP:'yui-resize-wrap',CSS_KNOB:'yui-resize-knob',CSS_HIDDEN:'yui-resize-hidden',CSS_HANDLE:'yui-resize-handle',CSS_STATUS:'yui-resize-status',CSS_GHOST:'yui-resize-ghost',CSS_RESIZING:'yui-resize-resizing',_resizeEvent:null,dd:null,browser:YAHOO.env.ua,_locked:null,_positioned:null,_dds:null,_wrap:null,_proxy:null,_handles:null,_currentHandle:null,_currentDD:null,_cache:null,_active:null,_createProxy:function(){if(this.get('proxy')){YAHOO.log('Creating the Proxy Element','info','Resize');this._proxy=document.createElement('div');this._proxy.className=this.CSS_PROXY;this._proxy.style.height=this.get('element').clientHeight+'px';this._proxy.style.width=this.get('element').clientWidth+'px';this._wrap.parentNode.appendChild(this._proxy);}else{YAHOO.log('No proxy element, turn off animate config option','info','Resize');this.set('animate',false);}},_createWrap:function(){YAHOO.log('Create the wrap element','info','Resize');this._positioned=false;if(this.get('wrap')===false){switch(this.get('element').tagName.toLowerCase()){case'img':case'textarea':case'input':case'iframe':case'select':YAHOO.log('Auto-wrapping the element ('+this.get('element').tagName.toLowerCase()+')','warn','Resize');this.set('wrap',true);break;}}
if(this.get('wrap')===true){YAHOO.log('Creating the wrap element','info','Resize');this._wrap=document.createElement('div');this._wrap.id=this.get('element').id+'_wrap';this._wrap.className=this.CSS_WRAP;if(this.get('element').tagName.toLowerCase()=='textarea'){D.addClass(this._wrap,'yui-resize-textarea');}
D.setStyle(this._wrap,'width',this.get('width')+'px');D.setStyle(this._wrap,'height',this.get('height')+'px');D.setStyle(this._wrap,'z-index',this.getStyle('z-index'));this.setStyle('z-index',0);var pos=D.getStyle(this.get('element'),'position');D.setStyle(this._wrap,'position',((pos=='static')?'relative':pos));D.setStyle(this._wrap,'top',D.getStyle(this.get('element'),'top'));D.setStyle(this._wrap,'left',D.getStyle(this.get('element'),'left'));if(D.getStyle(this.get('element'),'position')=='absolute'){this._positioned=true;YAHOO.log('The element is positioned absolute','info','Resize');D.setStyle(this.get('element'),'position','relative');D.setStyle(this.get('element'),'top','0');D.setStyle(this.get('element'),'left','0');}
var par=this.get('element').parentNode;par.replaceChild(this._wrap,this.get('element'));this._wrap.appendChild(this.get('element'));}else{this._wrap=this.get('element');if(D.getStyle(this._wrap,'position')=='absolute'){this._positioned=true;}}
if(this.get('draggable')){this._setupDragDrop();}
if(this.get('hover')){D.addClass(this._wrap,this.CSS_HOVER);}
if(this.get('knobHandles')){D.addClass(this._wrap,this.CSS_KNOB);}
if(this.get('hiddenHandles')){D.addClass(this._wrap,this.CSS_HIDDEN);}
D.addClass(this._wrap,this.CSS_RESIZE);},_setupDragDrop:function(){YAHOO.log('Setting up the dragdrop instance on the element','info','Resize');D.addClass(this._wrap,this.CSS_DRAG);this.dd=new YAHOO.util.DD(this._wrap,this.get('id')+'-resize',{dragOnly:true,useShim:this.get('useShim')});this.dd.on('dragEvent',function(){this.fireEvent('dragEvent',arguments);},this,true);},_createHandles:function(){YAHOO.log('Creating the handles','info','Resize');this._handles={};this._dds={};var h=this.get('handles');for(var i=0;i<h.length;i++){YAHOO.log('Creating handle position: '+h[i],'info','Resize');this._handles[h[i]]=document.createElement('div');this._handles[h[i]].id=D.generateId(this._handles[h[i]]);this._handles[h[i]].className=this.CSS_HANDLE+' '+this.CSS_HANDLE+'-'+h[i];var k=document.createElement('div');k.className=this.CSS_HANDLE+'-inner-'+h[i];this._handles[h[i]].appendChild(k);this._wrap.appendChild(this._handles[h[i]]);Event.on(this._handles[h[i]],'mouseover',this._handleMouseOver,this,true);Event.on(this._handles[h[i]],'mouseout',this._handleMouseOut,this,true);this._dds[h[i]]=new YAHOO.util.DragDrop(this._handles[h[i]],this.get('id')+'-handle-'+h,{useShim:this.get('useShim')});this._dds[h[i]].setPadding(15,15,15,15);this._dds[h[i]].on('startDragEvent',this._handleStartDrag,this._dds[h[i]],this);this._dds[h[i]].on('mouseDownEvent',this._handleMouseDown,this._dds[h[i]],this);}
YAHOO.log('Creating the Status box','info','Resize');this._status=document.createElement('span');this._status.className=this.CSS_STATUS;document.body.insertBefore(this._status,document.body.firstChild);},_ieSelectFix:function(){return false;},_ieSelectBack:null,_setAutoRatio:function(ev){if(this.get('autoRatio')){YAHOO.log('Setting up AutoRatio','info','Resize');if(ev&&ev.shiftKey){YAHOO.log('Shift key presses, turning on ratio','info','Resize');this.set('ratio',true);}else{YAHOO.log('Resetting ratio back to default','info','Resize');this.set('ratio',this._configs.ratio._initialConfig.value);}}},_handleMouseDown:function(ev){if(this._locked){YAHOO.log('Resize Locked','info','Resize');return false;}
if(D.getStyle(this._wrap,'position')=='absolute'){this._positioned=true;}
if(ev){this._setAutoRatio(ev);}
if(this.browser.ie){this._ieSelectBack=document.body.onselectstart;document.body.onselectstart=this._ieSelectFix;}},_handleMouseOver:function(ev){if(this._locked){YAHOO.log('Resize Locked','info','Resize');return false;}
D.removeClass(this._wrap,this.CSS_RESIZE);if(this.get('hover')){D.removeClass(this._wrap,this.CSS_HOVER);}
var tar=Event.getTarget(ev);if(!D.hasClass(tar,this.CSS_HANDLE)){tar=tar.parentNode;}
if(D.hasClass(tar,this.CSS_HANDLE)&&!this._active){D.addClass(tar,this.CSS_HANDLE+'-active');for(var i in this._handles){if(Lang.hasOwnProperty(this._handles,i)){if(this._handles[i]==tar){D.addClass(tar,this.CSS_HANDLE+'-'+i+'-active');break;}}}}
D.addClass(this._wrap,this.CSS_RESIZE);},_handleMouseOut:function(ev){D.removeClass(this._wrap,this.CSS_RESIZE);if(this.get('hover')&&!this._active){D.addClass(this._wrap,this.CSS_HOVER);}
var tar=Event.getTarget(ev);if(!D.hasClass(tar,this.CSS_HANDLE)){tar=tar.parentNode;}
if(D.hasClass(tar,this.CSS_HANDLE)&&!this._active){D.removeClass(tar,this.CSS_HANDLE+'-active');for(var i in this._handles){if(Lang.hasOwnProperty(this._handles,i)){if(this._handles[i]==tar){D.removeClass(tar,this.CSS_HANDLE+'-'+i+'-active');break;}}}}
D.addClass(this._wrap,this.CSS_RESIZE);},_handleStartDrag:function(args,dd){YAHOO.log('startDrag','info','Resize');var tar=dd.getDragEl();if(D.hasClass(tar,this.CSS_HANDLE)){if(D.getStyle(this._wrap,'position')=='absolute'){this._positioned=true;}
this._active=true;this._currentDD=dd;if(this._proxy){YAHOO.log('Activate proxy element','info','Resize');this._proxy.style.visibility='visible';this._proxy.style.zIndex='1000';this._proxy.style.height=this.get('element').clientHeight+'px';this._proxy.style.width=this.get('element').clientWidth+'px';}
for(var i in this._handles){if(Lang.hasOwnProperty(this._handles,i)){if(this._handles[i]==tar){this._currentHandle=i;var handle='_handle_for_'+i;D.addClass(tar,this.CSS_HANDLE+'-'+i+'-active');dd.on('dragEvent',this[handle],this,true);dd.on('mouseUpEvent',this._handleMouseUp,this,true);YAHOO.log('Adding DragEvents to: '+i,'info','Resize');break;}}}
D.addClass(tar,this.CSS_HANDLE+'-active');if(this.get('proxy')){YAHOO.log('Posiiton Proxy Element','info','Resize');var xy=D.getXY(this.get('element'));D.setXY(this._proxy,xy);if(this.get('ghost')){YAHOO.log('Add Ghost Class','info','Resize');this.addClass(this.CSS_GHOST);}}
D.addClass(this._wrap,this.CSS_RESIZING);this._setCache();this._updateStatus(this._cache.height,this._cache.width,this._cache.top,this._cache.left);YAHOO.log('Firing startResize Event','info','Resize');this.fireEvent('startResize',{type:'startresize',target:this});}},_setCache:function(){YAHOO.log('Setting up property cache','info','Resize');this._cache.xy=D.getXY(this._wrap);D.setXY(this._wrap,this._cache.xy);this._cache.height=this.get('clientHeight');this._cache.width=this.get('clientWidth');this._cache.start.height=this._cache.height;this._cache.start.width=this._cache.width;this._cache.start.top=this._cache.xy[1];this._cache.start.left=this._cache.xy[0];this._cache.top=this._cache.xy[1];this._cache.left=this._cache.xy[0];this.set('height',this._cache.height,true);this.set('width',this._cache.width,true);},_handleMouseUp:function(ev){this._active=false;var handle='_handle_for_'+this._currentHandle;this._currentDD.unsubscribe('dragEvent',this[handle],this,true);this._currentDD.unsubscribe('mouseUpEvent',this._handleMouseUp,this,true);if(this._proxy){YAHOO.log('Hide Proxy Element','info','Resize');this._proxy.style.visibility='hidden';this._proxy.style.zIndex='-1';if(this.get('setSize')){YAHOO.log('Setting Size','info','Resize');this.resize(ev,this._cache.height,this._cache.width,this._cache.top,this._cache.left,true);}else{YAHOO.log('Firing Resize Event','info','Resize');this.fireEvent('resize',{ev:'resize',target:this,height:this._cache.height,width:this._cache.width,top:this._cache.top,left:this._cache.left});}
if(this.get('ghost')){YAHOO.log('Removing Ghost Class','info','Resize');this.removeClass(this.CSS_GHOST);}}
if(this.get('hover')){D.addClass(this._wrap,this.CSS_HOVER);}
if(this._status){D.setStyle(this._status,'display','none');}
if(this.browser.ie){YAHOO.log('Resetting IE onselectstart function','info','Resize');document.body.onselectstart=this._ieSelectBack;}
if(this.browser.ie){D.removeClass(this._wrap,this.CSS_RESIZE);}
for(var i in this._handles){if(Lang.hasOwnProperty(this._handles,i)){D.removeClass(this._handles[i],this.CSS_HANDLE+'-active');}}
if(this.get('hover')&&!this._active){D.addClass(this._wrap,this.CSS_HOVER);}
D.removeClass(this._wrap,this.CSS_RESIZING);D.removeClass(this._handles[this._currentHandle],this.CSS_HANDLE+'-'+this._currentHandle+'-active');D.removeClass(this._handles[this._currentHandle],this.CSS_HANDLE+'-active');if(this.browser.ie){D.addClass(this._wrap,this.CSS_RESIZE);}
this._resizeEvent=null;this._currentHandle=null;if(!this.get('animate')){this.set('height',this._cache.height,true);this.set('width',this._cache.width,true);}
YAHOO.log('Firing endResize Event','info','Resize');this.fireEvent('endResize',{ev:'endResize',target:this,height:this._cache.height,width:this._cache.width,top:this._cache.top,left:this._cache.left});},_setRatio:function(h,w,t,l){YAHOO.log('Setting Ratio','info','Resize');var oh=h,ow=w;if(this.get('ratio')){var orgH=this._cache.height,orgW=this._cache.width,nh=parseInt(this.get('height'),10),nw=parseInt(this.get('width'),10),maxH=this.get('maxHeight'),minH=this.get('minHeight'),maxW=this.get('maxWidth'),minW=this.get('minWidth');switch(this._currentHandle){case'l':h=nh*(w/nw);h=Math.min(Math.max(minH,h),maxH);w=nw*(h/nh);t=(this._cache.start.top-(-((nh-h)/2)));l=(this._cache.start.left-(-((nw-w))));break;case'r':h=nh*(w/nw);h=Math.min(Math.max(minH,h),maxH);w=nw*(h/nh);t=(this._cache.start.top-(-((nh-h)/2)));break;case't':w=nw*(h/nh);h=nh*(w/nw);l=(this._cache.start.left-(-((nw-w)/2)));t=(this._cache.start.top-(-((nh-h))));break;case'b':w=nw*(h/nh);h=nh*(w/nw);l=(this._cache.start.left-(-((nw-w)/2)));break;case'bl':h=nh*(w/nw);w=nw*(h/nh);l=(this._cache.start.left-(-((nw-w))));break;case'br':h=nh*(w/nw);w=nw*(h/nh);break;case'tl':h=nh*(w/nw);w=nw*(h/nh);l=(this._cache.start.left-(-((nw-w))));t=(this._cache.start.top-(-((nh-h))));break;case'tr':h=nh*(w/nw);w=nw*(h/nh);l=(this._cache.start.left);t=(this._cache.start.top-(-((nh-h))));break;}
oh=this._checkHeight(h);ow=this._checkWidth(w);if((oh!=h)||(ow!=w)){t=0;l=0;if(oh!=h){ow=this._cache.width;}
if(ow!=w){oh=this._cache.height;}}}
return[oh,ow,t,l];},_updateStatus:function(h,w,t,l){if(this._resizeEvent&&(!Lang.isString(this._resizeEvent))){YAHOO.log('Updating Status Box','info','Resize');h=((h===0)?this._cache.start.height:h);w=((w===0)?this._cache.start.width:w);var h1=parseInt(this.get('height'),10),w1=parseInt(this.get('width'),10);if(isNaN(h1)){h1=parseInt(h,10);}
if(isNaN(w1)){w1=parseInt(w,10);}
var diffH=(parseInt(h,10)-h1);var diffW=(parseInt(w,10)-w1);this._cache.offsetHeight=diffH;this._cache.offsetWidth=diffW;if(this.get('status')){YAHOO.log('Showing Status Box','info','Resize');D.setStyle(this._status,'display','inline');this._status.innerHTML='<strong>'+parseInt(h,10)+' x '+parseInt(w,10)+'</strong><em>'+((diffH>0)?'+':'')+diffH+' x '+((diffW>0)?'+':'')+diffW+'</em>';D.setXY(this._status,[Event.getPageX(this._resizeEvent)+12,Event.getPageY(this._resizeEvent)+12]);}}},lock:function(dd){this._locked=true;if(dd&&this.dd){D.removeClass(this._wrap,'yui-draggable');this.dd.lock();}
return this;},unlock:function(dd){this._locked=false;if(dd&&this.dd){D.addClass(this._wrap,'yui-draggable');this.dd.unlock();}
return this;},isLocked:function(){return this._locked;},reset:function(){YAHOO.log('Resetting to cached sizes and position','info','Resize');this.resize(null,this._cache.start.height,this._cache.start.width,this._cache.start.top,this._cache.start.left,true);return this;},resize:function(ev,h,w,t,l,force,silent){if(this._locked){YAHOO.log('Resize Locked','info','Resize');return false;}
YAHOO.log('Resize: '+h+','+w+','+t+','+l,'info','Resize');this._resizeEvent=ev;var el=this._wrap,anim=this.get('animate'),set=true;if(this._proxy&&!force){el=this._proxy;anim=false;}
this._setAutoRatio(ev);if(this._positioned){if(this._proxy){t=this._cache.top-t;l=this._cache.left-l;}}
var ratio=this._setRatio(h,w,t,l);h=parseInt(ratio[0],10);w=parseInt(ratio[1],10);t=parseInt(ratio[2],10);l=parseInt(ratio[3],10);if(t==0){t=D.getY(el);}
if(l==0){l=D.getX(el);}
if(this._positioned){if(this._proxy&&force){if(!anim){el.style.top=this._proxy.style.top;el.style.left=this._proxy.style.left;}else{t=this._proxy.style.top;l=this._proxy.style.left;}}else{if(!this.get('ratio')&&!this._proxy){t=this._cache.top+-(t);l=this._cache.left+-(l);}
if(t){if(this.get('minY')){if(t<this.get('minY')){t=this.get('minY');}}
if(this.get('maxY')){if(t>this.get('maxY')){t=this.get('maxY');}}}
if(l){if(this.get('minX')){if(l<this.get('minX')){l=this.get('minX');}}
if(this.get('maxX')){if((l+w)>this.get('maxX')){l=(this.get('maxX')-w);}}}}}
if(!silent){YAHOO.log('beforeResize','info','Resize');var beforeReturn=this.fireEvent('beforeResize',{ev:'beforeResize',target:this,height:h,width:w,top:t,left:l});if(beforeReturn===false){YAHOO.log('Resized cancelled because befireResize returned false','info','Resize');return false;}}
this._updateStatus(h,w,t,l);if(this._positioned){if(this._proxy&&force){}else{if(t){D.setY(el,t);this._cache.top=t;}
if(l){D.setX(el,l);this._cache.left=l;}}}
if(h){if(!anim){set=true;if(this._proxy&&force){if(!this.get('setSize')){set=false;}}
if(set){el.style.height=h+'px';}
if((this._proxy&&force)||!this._proxy){if(this._wrap!=this.get('element')){this.get('element').style.height=h+'px';}}}
this._cache.height=h;}
if(w){this._cache.width=w;if(!anim){set=true;if(this._proxy&&force){if(!this.get('setSize')){set=false;}}
if(set){el.style.width=w+'px';}
if((this._proxy&&force)||!this._proxy){if(this._wrap!=this.get('element')){this.get('element').style.width=w+'px';}}}}
if(anim){if(YAHOO.util.Anim){var _anim=new YAHOO.util.Anim(el,{height:{to:this._cache.height},width:{to:this._cache.width}},this.get('animateDuration'),this.get('animateEasing'));if(this._positioned){if(t){_anim.attributes.top={to:parseInt(t,10)};}
if(l){_anim.attributes.left={to:parseInt(l,10)};}}
if(this._wrap!=this.get('element')){_anim.onTween.subscribe(function(){this.get('element').style.height=el.style.height;this.get('element').style.width=el.style.width;},this,true);}
_anim.onComplete.subscribe(function(){YAHOO.log('Animation onComplete fired','info','Resize');this.set('height',h);this.set('width',w);this.fireEvent('resize',{ev:'resize',target:this,height:h,width:w,top:t,left:l});},this,true);_anim.animate();}}else{if(this._proxy&&!force){YAHOO.log('proxyResize','info','Resize');this.fireEvent('proxyResize',{ev:'proxyresize',target:this,height:h,width:w,top:t,left:l});}else{YAHOO.log('resize','info','Resize');this.fireEvent('resize',{ev:'resize',target:this,height:h,width:w,top:t,left:l});}}
return this;},_handle_for_br:function(args){YAHOO.log('Handle BR','info','Resize');var newW=this._setWidth(args.e);var newH=this._setHeight(args.e);this.resize(args.e,newH,newW,0,0);},_handle_for_bl:function(args){YAHOO.log('Handle BL','info','Resize');var newW=this._setWidth(args.e,true);var newH=this._setHeight(args.e);var l=(newW-this._cache.width);this.resize(args.e,newH,newW,0,l);},_handle_for_tl:function(args){YAHOO.log('Handle TL','info','Resize');var newW=this._setWidth(args.e,true);var newH=this._setHeight(args.e,true);var t=(newH-this._cache.height);var l=(newW-this._cache.width);this.resize(args.e,newH,newW,t,l);},_handle_for_tr:function(args){YAHOO.log('Handle TR','info','Resize');var newW=this._setWidth(args.e);var newH=this._setHeight(args.e,true);var t=(newH-this._cache.height);this.resize(args.e,newH,newW,t,0);},_handle_for_r:function(args){YAHOO.log('Handle R','info','Resize');this._dds.r.setYConstraint(0,0);var newW=this._setWidth(args.e);this.resize(args.e,0,newW,0,0);},_handle_for_l:function(args){YAHOO.log('Handle L','info','Resize');this._dds.l.setYConstraint(0,0);var newW=this._setWidth(args.e,true);var l=(newW-this._cache.width);this.resize(args.e,0,newW,0,l);},_handle_for_b:function(args){YAHOO.log('Handle B','info','Resize');this._dds.b.setXConstraint(0,0);var newH=this._setHeight(args.e);this.resize(args.e,newH,0,0,0);},_handle_for_t:function(args){YAHOO.log('Handle T','info','Resize');this._dds.t.setXConstraint(0,0);var newH=this._setHeight(args.e,true);var t=(newH-this._cache.height);this.resize(args.e,newH,0,t,0);},_setWidth:function(ev,flip){YAHOO.log('Set width based on Event','info','Resize');var xy=this._cache.xy[0],w=this._cache.width,x=Event.getPageX(ev),nw=(x-xy);if(flip){nw=(xy-x)+parseInt(this.get('width'),10);}
nw=this._snapTick(nw,this.get('xTicks'));nw=this._checkWidth(nw);return nw;},_checkWidth:function(w){YAHOO.log('Checking the min/max width','info','Resize');if(this.get('minWidth')){if(w<=this.get('minWidth')){YAHOO.log('Using minWidth','info','Resize');w=this.get('minWidth');}}
if(this.get('maxWidth')){if(w>=this.get('maxWidth')){YAHOO.log('Using Max Width','info','Resize');w=this.get('maxWidth');}}
return w;},_checkHeight:function(h){YAHOO.log('Checking the min/max height','info','Resize');if(this.get('minHeight')){if(h<=this.get('minHeight')){YAHOO.log('Using minHeight','info','Resize');h=this.get('minHeight');}}
if(this.get('maxHeight')){if(h>=this.get('maxHeight')){YAHOO.log('using maxHeight','info','Resize');h=this.get('maxHeight');}}
return h;},_setHeight:function(ev,flip){YAHOO.log('Setting the height based on the Event','info','Resize');var xy=this._cache.xy[1],h=this._cache.height,y=Event.getPageY(ev),nh=(y-xy);if(flip){nh=(xy-y)+parseInt(this.get('height'),10);}
nh=this._snapTick(nh,this.get('yTicks'));nh=this._checkHeight(nh);return nh;},_snapTick:function(size,pix){YAHOO.log('Snapping to ticks','info','Resize');if(!size||!pix){return size;}
var _s=size;var _x=size%pix;if(_x>0){if(_x>(pix/2)){_s=size+(pix-_x);}else{_s=size-_x;}}
return _s;},init:function(p_oElement,p_oAttributes){YAHOO.log('init','info','Resize');this._locked=false;this._cache={xy:[],height:0,width:0,top:0,left:0,offsetHeight:0,offsetWidth:0,start:{height:0,width:0,top:0,left:0}};Resize.superclass.init.call(this,p_oElement,p_oAttributes);this.set('setSize',this.get('setSize'));if(p_oAttributes.height){this.set('height',parseInt(p_oAttributes.height,10));}else{var h=this.getStyle('height');if(h=='auto'){this.set('height',parseInt(this.get('element').offsetHeight,10));}}
if(p_oAttributes.width){this.set('width',parseInt(p_oAttributes.width,10));}else{var w=this.getStyle('width');if(w=='auto'){this.set('width',parseInt(this.get('element').offsetWidth,10));}}
var id=p_oElement;if(!Lang.isString(id)){id=D.generateId(id);}
Resize._instances[id]=this;this._active=false;this._createWrap();this._createProxy();this._createHandles();},getProxyEl:function(){return this._proxy;},getWrapEl:function(){return this._wrap;},getStatusEl:function(){return this._status;},getActiveHandleEl:function(){return this._handles[this._currentHandle];},isActive:function(){return((this._active)?true:false);},initAttributes:function(attr){Resize.superclass.initAttributes.call(this,attr);this.setAttributeConfig('useShim',{value:((attr.useShim===true)?true:false),validator:YAHOO.lang.isBoolean,method:function(u){for(var i in this._dds){if(Lang.hasOwnProperty(this._dds,i)){this._dds[i].useShim=u;}}
if(this.dd){this.dd.useShim=u;}}});this.setAttributeConfig('setSize',{value:((attr.setSize===false)?false:true),validator:YAHOO.lang.isBoolean});this.setAttributeConfig('wrap',{writeOnce:true,validator:YAHOO.lang.isBoolean,value:attr.wrap||false});this.setAttributeConfig('handles',{writeOnce:true,value:attr.handles||['r','b','br'],validator:function(handles){if(Lang.isString(handles)&&handles.toLowerCase()=='all'){handles=['t','b','r','l','bl','br','tl','tr'];}
if(!Lang.isArray(handles)){handles=handles.replace(/, /g,',');handles=handles.split(',');}
this._configs.handles.value=handles;}});this.setAttributeConfig('width',{value:attr.width||parseInt(this.getStyle('width'),10),validator:YAHOO.lang.isNumber,method:function(width){width=parseInt(width,10);if(width>0){if(this.get('setSize')){this.setStyle('width',width+'px');}
this._cache.width=width;this._configs.width.value=width;}}});this.setAttributeConfig('height',{value:attr.height||parseInt(this.getStyle('height'),10),validator:YAHOO.lang.isNumber,method:function(height){height=parseInt(height,10);if(height>0){if(this.get('setSize')){this.setStyle('height',height+'px');}
this._cache.height=height;this._configs.height.value=height;}}});this.setAttributeConfig('minWidth',{value:attr.minWidth||15,validator:YAHOO.lang.isNumber});this.setAttributeConfig('minHeight',{value:attr.minHeight||15,validator:YAHOO.lang.isNumber});this.setAttributeConfig('maxWidth',{value:attr.maxWidth||10000,validator:YAHOO.lang.isNumber});this.setAttributeConfig('maxHeight',{value:attr.maxHeight||10000,validator:YAHOO.lang.isNumber});this.setAttributeConfig('minY',{value:attr.minY||false});this.setAttributeConfig('minX',{value:attr.minX||false});this.setAttributeConfig('maxY',{value:attr.maxY||false});this.setAttributeConfig('maxX',{value:attr.maxX||false});this.setAttributeConfig('animate',{value:attr.animate||false,validator:function(value){var ret=true;if(!YAHOO.util.Anim){ret=false;}
return ret;}});this.setAttributeConfig('animateEasing',{value:attr.animateEasing||function(){var easing=false;if(YAHOO.util.Easing&&YAHOO.util.Easing.easeOut){easing=YAHOO.util.Easing.easeOut;}
return easing;}()});this.setAttributeConfig('animateDuration',{value:attr.animateDuration||0.5});this.setAttributeConfig('proxy',{value:attr.proxy||false,validator:YAHOO.lang.isBoolean});this.setAttributeConfig('ratio',{value:attr.ratio||false,validator:YAHOO.lang.isBoolean});this.setAttributeConfig('ghost',{value:attr.ghost||false,validator:YAHOO.lang.isBoolean});this.setAttributeConfig('draggable',{value:attr.draggable||false,validator:YAHOO.lang.isBoolean,method:function(dd){if(dd&&this._wrap){this._setupDragDrop();}else{if(this.dd){D.removeClass(this._wrap,this.CSS_DRAG);this.dd.unreg();}}}});this.setAttributeConfig('hover',{value:attr.hover||false,validator:YAHOO.lang.isBoolean});this.setAttributeConfig('hiddenHandles',{value:attr.hiddenHandles||false,validator:YAHOO.lang.isBoolean});this.setAttributeConfig('knobHandles',{value:attr.knobHandles||false,validator:YAHOO.lang.isBoolean});this.setAttributeConfig('xTicks',{value:attr.xTicks||false});this.setAttributeConfig('yTicks',{value:attr.yTicks||false});this.setAttributeConfig('status',{value:attr.status||false,validator:YAHOO.lang.isBoolean});this.setAttributeConfig('autoRatio',{value:attr.autoRatio||false,validator:YAHOO.lang.isBoolean});},destroy:function(){YAHOO.log('Destroying Resize','info','Resize');for(var h in this._handles){if(Lang.hasOwnProperty(this._handles,h)){Event.purgeElement(this._handles[h]);this._handles[h].parentNode.removeChild(this._handles[h]);}}
if(this._proxy){this._proxy.parentNode.removeChild(this._proxy);}
if(this._status){this._status.parentNode.removeChild(this._status);}
if(this.dd){this.dd.unreg();D.removeClass(this._wrap,this.CSS_DRAG);}
if(this._wrap!=this.get('element')){this.setStyle('position','');this.setStyle('top','');this.setStyle('left','');this._wrap.parentNode.replaceChild(this.get('element'),this._wrap);}
this.removeClass(this.CSS_RESIZE);delete YAHOO.util.Resize._instances[this.get('id')];for(var i in this){if(Lang.hasOwnProperty(this,i)){this[i]=null;delete this[i];}}},toString:function(){if(this.get){return'Resize (#'+this.get('id')+')';}
return'Resize Utility';}});YAHOO.util.Resize=Resize;})();YAHOO.register("resize",YAHOO.util.Resize,{version:"2.8.0r4",build:"2449"});