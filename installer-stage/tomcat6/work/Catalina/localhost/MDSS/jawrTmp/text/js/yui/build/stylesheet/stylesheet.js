
(function(){var d=document,p=d.createElement('p'),workerStyle=p.style,lang=YAHOO.lang,selectors={},sheets={},ssId=0,floatAttr=('cssFloat'in workerStyle)?'cssFloat':'styleFloat',_toCssText,_unsetOpacity,_unsetProperty;_unsetOpacity=('opacity'in workerStyle)?function(style){style.opacity='';}:function(style){style.filter='';};workerStyle.border="1px solid red";workerStyle.border='';_unsetProperty=workerStyle.borderLeft?function(style,prop){var p;if(prop!==floatAttr&&prop.toLowerCase().indexOf('float')!=-1){prop=floatAttr;}
if(typeof style[prop]==='string'){switch(prop){case'opacity':case'filter':_unsetOpacity(style);break;case'font':style.font=style.fontStyle=style.fontVariant=style.fontWeight=style.fontSize=style.lineHeight=style.fontFamily='';break;default:for(p in style){if(p.indexOf(prop)===0){style[p]='';}}}}}:function(style,prop){if(prop!==floatAttr&&prop.toLowerCase().indexOf('float')!=-1){prop=floatAttr;}
if(lang.isString(style[prop])){if(prop==='opacity'){_unsetOpacity(style);}else{style[prop]='';}}};function StyleSheet(seed,name){var head,node,sheet,cssRules={},_rules,_insertRule,_deleteRule,i,r,sel;if(!(this instanceof StyleSheet)){return new StyleSheet(seed,name);}
node=seed&&(seed.nodeName?seed:d.getElementById(seed));if(seed&&sheets[seed]){return sheets[seed];}else if(node&&node.yuiSSID&&sheets[node.yuiSSID]){return sheets[node.yuiSSID];}
if(!node||!/^(?:style|link)$/i.test(node.nodeName)){node=d.createElement('style');node.type='text/css';}
if(lang.isString(seed)){if(seed.indexOf('{')!=-1){if(node.styleSheet){node.styleSheet.cssText=seed;}else{node.appendChild(d.createTextNode(seed));}}else if(!name){name=seed;}}
if(!node.parentNode||node.parentNode.nodeName.toLowerCase()!=='head'){head=(node.ownerDocument||d).getElementsByTagName('head')[0];head.appendChild(node);}
sheet=node.sheet||node.styleSheet;_rules=sheet&&('cssRules'in sheet)?'cssRules':'rules';_deleteRule=('deleteRule'in sheet)?function(i){sheet.deleteRule(i);}:function(i){sheet.removeRule(i);};_insertRule=('insertRule'in sheet)?function(sel,css,i){sheet.insertRule(sel+' {'+css+'}',i);}:function(sel,css,i){sheet.addRule(sel,css,i);};for(i=sheet[_rules].length-1;i>=0;--i){r=sheet[_rules][i];sel=r.selectorText;if(cssRules[sel]){cssRules[sel].style.cssText+=';'+r.style.cssText;_deleteRule(i);}else{cssRules[sel]=r;}}
node.yuiSSID='yui-stylesheet-'+(ssId++);StyleSheet.register(node.yuiSSID,this);if(name){StyleSheet.register(name,this);}
lang.augmentObject(this,{getId:function(){return node.yuiSSID;},node:node,enable:function(){sheet.disabled=false;return this;},disable:function(){sheet.disabled=true;return this;},isEnabled:function(){return!sheet.disabled;},set:function(sel,css){var rule=cssRules[sel],multi=sel.split(/\s*,\s*/),i,idx;if(multi.length>1){for(i=multi.length-1;i>=0;--i){this.set(multi[i],css);}
return this;}
if(!StyleSheet.isValidSelector(sel)){return this;}
if(rule){rule.style.cssText=StyleSheet.toCssText(css,rule.style.cssText);}else{idx=sheet[_rules].length;css=StyleSheet.toCssText(css);if(css){_insertRule(sel,css,idx);cssRules[sel]=sheet[_rules][idx];}}
return this;},unset:function(sel,css){var rule=cssRules[sel],multi=sel.split(/\s*,\s*/),remove=!css,rules,i;if(multi.length>1){for(i=multi.length-1;i>=0;--i){this.unset(multi[i],css);}
return this;}
if(rule){if(!remove){if(!lang.isArray(css)){css=[css];}
workerStyle.cssText=rule.style.cssText;for(i=css.length-1;i>=0;--i){_unsetProperty(workerStyle,css[i]);}
if(workerStyle.cssText){rule.style.cssText=workerStyle.cssText;}else{remove=true;}}
if(remove){rules=sheet[_rules];for(i=rules.length-1;i>=0;--i){if(rules[i]===rule){delete cssRules[sel];_deleteRule(i);break;}}}}
return this;},getCssText:function(sel){var rule,css;if(lang.isString(sel)){rule=cssRules[sel.split(/\s*,\s*/)[0]];return rule?rule.style.cssText:null;}else{css=[];for(sel in cssRules){if(cssRules.hasOwnProperty(sel)){rule=cssRules[sel];css.push(rule.selectorText+" {"+rule.style.cssText+"}");}}
return css.join("\n");}}},true);}
_toCssText=function(css,base){var f=css.styleFloat||css.cssFloat||css['float'],prop;workerStyle.cssText=base||'';if(lang.isString(css)){workerStyle.cssText+=';'+css;}else{if(f&&!css[floatAttr]){css=lang.merge(css);delete css.styleFloat;delete css.cssFloat;delete css['float'];css[floatAttr]=f;}
for(prop in css){if(css.hasOwnProperty(prop)){try{workerStyle[prop]=lang.trim(css[prop]);}
catch(e){}}}}
return workerStyle.cssText;};lang.augmentObject(StyleSheet,{toCssText:(('opacity'in workerStyle)?_toCssText:function(css,cssText){if(lang.isObject(css)&&'opacity'in css){css=lang.merge(css,{filter:'alpha(opacity='+(css.opacity*100)+')'});delete css.opacity;}
return _toCssText(css,cssText);}),register:function(name,sheet){return!!(name&&sheet instanceof StyleSheet&&!sheets[name]&&(sheets[name]=sheet));},isValidSelector:function(sel){var valid=false;if(sel&&lang.isString(sel)){if(!selectors.hasOwnProperty(sel)){selectors[sel]=!/\S/.test(sel.replace(/\s+|\s*[+~>]\s*/g,' ').replace(/([^ ])\[.*?\]/g,'$1').replace(/([^ ])::?[a-z][a-z\-]+[a-z](?:\(.*?\))?/ig,'$1').replace(/(?:^| )[a-z0-6]+/ig,' ').replace(/\\./g,'').replace(/[.#]\w[\w\-]*/g,''));}
valid=selectors[sel];}
return valid;}},true);YAHOO.util.StyleSheet=StyleSheet;})();YAHOO.register("stylesheet",YAHOO.util.StyleSheet,{version:"2.8.0r4",build:"2449"});