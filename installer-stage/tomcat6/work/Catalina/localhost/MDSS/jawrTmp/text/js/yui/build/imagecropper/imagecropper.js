
(function(){var Dom=YAHOO.util.Dom,Event=YAHOO.util.Event,Lang=YAHOO.lang;var Crop=function(el,config){var oConfig={element:el,attributes:config||{}};Crop.superclass.constructor.call(this,oConfig.element,oConfig.attributes);};Crop._instances={};Crop.getCropperById=function(id){if(Crop._instances[id]){return Crop._instances[id];}
return false;};YAHOO.extend(Crop,YAHOO.util.Element,{CSS_MAIN:'yui-crop',CSS_MASK:'yui-crop-mask',CSS_RESIZE_MASK:'yui-crop-resize-mask',_image:null,_active:null,_resize:null,_resizeEl:null,_resizeMaskEl:null,_wrap:null,_mask:null,_createWrap:function(){this._wrap=document.createElement('div');this._wrap.id=this.get('element').id+'_wrap';this._wrap.className=this.CSS_MAIN;var el=this.get('element');this._wrap.style.width=el.width?el.width+'px':Dom.getStyle(el,'width');this._wrap.style.height=el.height?el.height+'px':Dom.getStyle(el,'height');var par=this.get('element').parentNode;par.replaceChild(this._wrap,this.get('element'));this._wrap.appendChild(this.get('element'));Event.on(this._wrap,'mouseover',this._handleMouseOver,this,true);Event.on(this._wrap,'mouseout',this._handleMouseOut,this,true);Event.on(this._wrap,'click',function(ev){Event.stopEvent(ev);},this,true);},_createMask:function(){this._mask=document.createElement('div');this._mask.className=this.CSS_MASK;this._wrap.appendChild(this._mask);},_createResize:function(){this._resizeEl=document.createElement('div');this._resizeEl.className=YAHOO.util.Resize.prototype.CSS_RESIZE;this._resizeEl.style.position='absolute';this._resizeEl.innerHTML='<div class="'+this.CSS_RESIZE_MASK+'"></div>';this._resizeMaskEl=this._resizeEl.firstChild;this._wrap.appendChild(this._resizeEl);this._resizeEl.style.top=this.get('initialXY')[1]+'px';this._resizeEl.style.left=this.get('initialXY')[0]+'px';this._resizeMaskEl.style.height=Math.floor(this.get('initHeight'))+'px';this._resizeMaskEl.style.width=Math.floor(this.get('initWidth'))+'px';this._resize=new YAHOO.util.Resize(this._resizeEl,{knobHandles:true,handles:'all',draggable:true,status:this.get('status'),minWidth:this.get('minWidth'),minHeight:this.get('minHeight'),ratio:this.get('ratio'),autoRatio:this.get('autoRatio'),height:this.get('initHeight'),width:this.get('initWidth')});this._setBackgroundImage(this.get('element').getAttribute('src',2));this._setBackgroundPosition(-(this.get('initialXY')[0]),-(this.get('initialXY')[1]));this._resize.on('startResize',this._handleStartResizeEvent,this,true);this._resize.on('endResize',this._handleEndResizeEvent,this,true);this._resize.on('dragEvent',this._handleDragEvent,this,true);this._resize.on('beforeResize',this._handleBeforeResizeEvent,this,true);this._resize.on('resize',this._handleResizeEvent,this,true);this._resize.dd.on('b4StartDragEvent',this._handleB4DragEvent,this,true);},_handleMouseOver:function(ev){var evType='keydown';if(YAHOO.env.ua.gecko||YAHOO.env.ua.opera){evType='keypress';}
if(!this._active){this._active=true;if(this.get('useKeys')){Event.on(document,evType,this._handleKeyPress,this,true);}}},_handleMouseOut:function(ev){var evType='keydown';if(YAHOO.env.ua.gecko||YAHOO.env.ua.opera){evType='keypress';}
this._active=false;if(this.get('useKeys')){Event.removeListener(document,evType,this._handleKeyPress);}},_moveEl:function(dir,inc){var t=0,l=0,region=this._setConstraints(),resize=true;switch(dir){case'down':t=-(inc);if((region.bottom-inc)<0){resize=false;this._resizeEl.style.top=(region.top+region.bottom)+'px';}
break;case'up':t=(inc);if((region.top-inc)<0){resize=false;this._resizeEl.style.top='0px';}
break;case'right':l=-(inc);if((region.right-inc)<0){resize=false;this._resizeEl.style.left=(region.left+region.right)+'px';}
break;case'left':l=inc;if((region.left-inc)<0){resize=false;this._resizeEl.style.left='0px';}
break;}
if(resize){this._resizeEl.style.left=(parseInt(this._resizeEl.style.left,10)-l)+'px';this._resizeEl.style.top=(parseInt(this._resizeEl.style.top,10)-t)+'px';this.fireEvent('moveEvent',{target:'keypress'});}else{this._setConstraints();}
this._syncBackgroundPosition();},_handleKeyPress:function(ev){var kc=Event.getCharCode(ev),stopEvent=false,inc=((ev.shiftKey)?this.get('shiftKeyTick'):this.get('keyTick'));switch(kc){case 0x25:this._moveEl('left',inc);stopEvent=true;break;case 0x26:this._moveEl('up',inc);stopEvent=true;break;case 0x27:this._moveEl('right',inc);stopEvent=true;break;case 0x28:this._moveEl('down',inc);stopEvent=true;break;default:}
if(stopEvent){Event.preventDefault(ev);}},_handleB4DragEvent:function(){this._setConstraints();},_handleDragEvent:function(){this._syncBackgroundPosition();this.fireEvent('dragEvent',arguments);this.fireEvent('moveEvent',{target:'dragevent'});},_handleBeforeResizeEvent:function(args){var region=Dom.getRegion(this.get('element')),c=this._resize._cache,ch=this._resize._currentHandle,h=0,w=0;if(args.top&&(args.top<region.top)){h=(c.height+c.top)-region.top;Dom.setY(this._resize.getWrapEl(),region.top);this._resize.getWrapEl().style.height=h+'px';this._resize._cache.height=h;this._resize._cache.top=region.top;this._syncBackgroundPosition();return false;}
if(args.left&&(args.left<region.left)){w=(c.width+c.left)-region.left;Dom.setX(this._resize.getWrapEl(),region.left);this._resize._cache.left=region.left;this._resize.getWrapEl().style.width=w+'px';this._resize._cache.width=w;this._syncBackgroundPosition();return false;}
if(ch!='tl'&&ch!='l'&&ch!='bl'){if(c.left&&args.width&&((c.left+args.width)>region.right)){w=(region.right-c.left);Dom.setX(this._resize.getWrapEl(),(region.right-w));this._resize.getWrapEl().style.width=w+'px';this._resize._cache.left=(region.right-w);this._resize._cache.width=w;this._syncBackgroundPosition();return false;}}
if(ch!='t'&&ch!='tr'&&ch!='tl'){if(c.top&&args.height&&((c.top+args.height)>region.bottom)){h=(region.bottom-c.top);Dom.setY(this._resize.getWrapEl(),(region.bottom-h));this._resize.getWrapEl().style.height=h+'px';this._resize._cache.height=h;this._resize._cache.top=(region.bottom-h);this._syncBackgroundPosition();return false;}}},_handleResizeMaskEl:function(){var a=this._resize._cache;this._resizeMaskEl.style.height=Math.floor(a.height)+'px';this._resizeMaskEl.style.width=Math.floor(a.width)+'px';},_handleResizeEvent:function(ev){this._setConstraints(true);this._syncBackgroundPosition();this.fireEvent('resizeEvent',arguments);this.fireEvent('moveEvent',{target:'resizeevent'});},_syncBackgroundPosition:function(){this._handleResizeMaskEl();this._setBackgroundPosition(-(parseInt(this._resizeEl.style.left,10)),-(parseInt(this._resizeEl.style.top,10)));},_setBackgroundPosition:function(l,t){var bl=parseInt(Dom.getStyle(this._resize.get('element'),'borderLeftWidth'),10);var bt=parseInt(Dom.getStyle(this._resize.get('element'),'borderTopWidth'),10);if(isNaN(bl)){bl=0;}
if(isNaN(bt)){bt=0;}
var mask=this._resize.getWrapEl().firstChild;var pos=(l-bl)+'px '+(t-bt)+'px';this._resizeMaskEl.style.backgroundPosition=pos;},_setBackgroundImage:function(url){var mask=this._resize.getWrapEl().firstChild;this._image=url;mask.style.backgroundImage='url('+url+'#)';},_handleEndResizeEvent:function(){this._setConstraints(true);},_handleStartResizeEvent:function(){this._setConstraints(true);var h=this._resize._cache.height,w=this._resize._cache.width,t=parseInt(this._resize.getWrapEl().style.top,10),l=parseInt(this._resize.getWrapEl().style.left,10),maxH=0,maxW=0;switch(this._resize._currentHandle){case'b':maxH=(h+this._resize.dd.bottomConstraint);break;case'l':maxW=(w+this._resize.dd.leftConstraint);break;case'r':maxH=(h+t);maxW=(w+this._resize.dd.rightConstraint);break;case'br':maxH=(h+this._resize.dd.bottomConstraint);maxW=(w+this._resize.dd.rightConstraint);break;case'tr':maxH=(h+t);maxW=(w+this._resize.dd.rightConstraint);break;}
if(maxH){}
if(maxW){}
this.fireEvent('startResizeEvent',arguments);},_setConstraints:function(inside){var resize=this._resize;resize.dd.resetConstraints();var height=parseInt(resize.get('height'),10),width=parseInt(resize.get('width'),10);if(inside){height=resize._cache.height;width=resize._cache.width;}
var region=Dom.getRegion(this.get('element'));var el=resize.getWrapEl();var xy=Dom.getXY(el);var left=xy[0]-region.left;var right=region.right-xy[0]-width;var top=xy[1]-region.top;var bottom=region.bottom-xy[1]-height;if(top<0){top=0;}
resize.dd.setXConstraint(left,right);resize.dd.setYConstraint(top,bottom);return{top:top,right:right,bottom:bottom,left:left};},getCropCoords:function(){var coords={top:parseInt(this._resize.getWrapEl().style.top,10),left:parseInt(this._resize.getWrapEl().style.left,10),height:this._resize._cache.height,width:this._resize._cache.width,image:this._image};return coords;},reset:function(){this._resize.resize(null,this.get('initHeight'),this.get('initWidth'),0,0,true);this._resizeEl.style.top=this.get('initialXY')[1]+'px';this._resizeEl.style.left=this.get('initialXY')[0]+'px';this._syncBackgroundPosition();return this;},getEl:function(){return this.get('element');},getResizeEl:function(){return this._resizeEl;},getWrapEl:function(){return this._wrap;},getMaskEl:function(){return this._mask;},getResizeMaskEl:function(){return this._resizeMaskEl;},getResizeObject:function(){return this._resize;},init:function(p_oElement,p_oAttributes){Crop.superclass.init.call(this,p_oElement,p_oAttributes);var id=p_oElement;if(!Lang.isString(id)){if(id.tagName&&(id.tagName.toLowerCase()=='img')){id=Dom.generateId(id);}else{return false;}}else{var el=Dom.get(id);if(el.tagName&&el.tagName.toLowerCase()=='img'){}else{return false;}}
Crop._instances[id]=this;this._createWrap();this._createMask();this._createResize();this._setConstraints();},initAttributes:function(attr){Crop.superclass.initAttributes.call(this,attr);this.setAttributeConfig('initialXY',{writeOnce:true,validator:YAHOO.lang.isArray,value:attr.initialXY||[10,10]});this.setAttributeConfig('keyTick',{validator:YAHOO.lang.isNumber,value:attr.keyTick||1});this.setAttributeConfig('shiftKeyTick',{validator:YAHOO.lang.isNumber,value:attr.shiftKeyTick||10});this.setAttributeConfig('useKeys',{validator:YAHOO.lang.isBoolean,value:((attr.useKeys===false)?false:true)});this.setAttributeConfig('status',{validator:YAHOO.lang.isBoolean,value:((attr.status===false)?false:true),method:function(status){if(this._resize){this._resize.set('status',status);}}});this.setAttributeConfig('minHeight',{validator:YAHOO.lang.isNumber,value:attr.minHeight||50,method:function(h){if(this._resize){this._resize.set('minHeight',h);}}});this.setAttributeConfig('minWidth',{validator:YAHOO.lang.isNumber,value:attr.minWidth||50,method:function(w){if(this._resize){this._resize.set('minWidth',w);}}});this.setAttributeConfig('ratio',{validator:YAHOO.lang.isBoolean,value:attr.ratio||false,method:function(r){if(this._resize){this._resize.set('ratio',r);}}});this.setAttributeConfig('autoRatio',{validator:YAHOO.lang.isBoolean,value:((attr.autoRatio===false)?false:true),method:function(a){if(this._resize){this._resize.set('autoRatio',a);}}});this.setAttributeConfig('initHeight',{writeOnce:true,validator:YAHOO.lang.isNumber,value:attr.initHeight||(this.get('element').height/4)});this.setAttributeConfig('initWidth',{validator:YAHOO.lang.isNumber,writeOnce:true,value:attr.initWidth||(this.get('element').width/4)});},destroy:function(){this._resize.destroy();this._resizeEl.parentNode.removeChild(this._resizeEl);this._mask.parentNode.removeChild(this._mask);Event.purgeElement(this._wrap);this._wrap.parentNode.replaceChild(this.get('element'),this._wrap);for(var i in this){if(Lang.hasOwnProperty(this,i)){this[i]=null;}}},toString:function(){if(this.get){return'ImageCropper (#'+this.get('id')+')';}
return'Image Cropper';}});YAHOO.widget.ImageCropper=Crop;})();YAHOO.register("imagecropper",YAHOO.widget.ImageCropper,{version:"2.8.0r4",build:"2449"});