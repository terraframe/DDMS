
YAHOO.util.Connect={_msxml_progid:['Microsoft.XMLHTTP','MSXML2.XMLHTTP.3.0','MSXML2.XMLHTTP'],_http_headers:{},_has_http_headers:false,_use_default_post_header:true,_default_post_header:'application/x-www-form-urlencoded; charset=UTF-8',_default_form_header:'application/x-www-form-urlencoded',_use_default_xhr_header:true,_default_xhr_header:'XMLHttpRequest',_has_default_headers:true,_default_headers:{},_poll:{},_timeOut:{},_polling_interval:50,_transaction_id:0,startEvent:new YAHOO.util.CustomEvent('start'),completeEvent:new YAHOO.util.CustomEvent('complete'),successEvent:new YAHOO.util.CustomEvent('success'),failureEvent:new YAHOO.util.CustomEvent('failure'),abortEvent:new YAHOO.util.CustomEvent('abort'),_customEvents:{onStart:['startEvent','start'],onComplete:['completeEvent','complete'],onSuccess:['successEvent','success'],onFailure:['failureEvent','failure'],onUpload:['uploadEvent','upload'],onAbort:['abortEvent','abort']},setProgId:function(id)
{this._msxml_progid.unshift(id);YAHOO.log('ActiveX Program Id  '+id+' added to _msxml_progid.','info','Connection');},setDefaultPostHeader:function(b)
{if(typeof b=='string'){this._default_post_header=b;YAHOO.log('Default POST header set to  '+b,'info','Connection');}
else if(typeof b=='boolean'){this._use_default_post_header=b;}},setDefaultXhrHeader:function(b)
{if(typeof b=='string'){this._default_xhr_header=b;YAHOO.log('Default XHR header set to  '+b,'info','Connection');}
else{this._use_default_xhr_header=b;}},setPollingInterval:function(i)
{if(typeof i=='number'&&isFinite(i)){this._polling_interval=i;YAHOO.log('Default polling interval set to '+i+'ms','info','Connection');}},createXhrObject:function(transactionId)
{var obj,http,i;try
{http=new XMLHttpRequest();obj={conn:http,tId:transactionId,xhr:true};YAHOO.log('XHR object created for transaction '+transactionId,'info','Connection');}
catch(e)
{for(i=0;i<this._msxml_progid.length;++i){try
{http=new ActiveXObject(this._msxml_progid[i]);obj={conn:http,tId:transactionId,xhr:true};YAHOO.log('ActiveX XHR object created for transaction '+transactionId,'info','Connection');break;}
catch(e1){}}}
finally
{return obj;}},getConnectionObject:function(t)
{var o,tId=this._transaction_id;try
{if(!t){o=this.createXhrObject(tId);}
else{o={tId:tId};if(t==='xdr'){o.conn=this._transport;o.xdr=true;}
else if(t==='upload'){o.upload=true;}}
if(o){this._transaction_id++;}}
catch(e){}
return o;},asyncRequest:function(method,uri,callback,postData)
{var o,t,args=(callback&&callback.argument)?callback.argument:null;if(this._isFileUpload){t='upload';}
else if(callback.xdr){t='xdr';}
o=this.getConnectionObject(t);if(!o){YAHOO.log('Unable to create connection object.','error','Connection');return null;}
else{if(callback&&callback.customevents){this.initCustomEvents(o,callback);}
if(this._isFormSubmit){if(this._isFileUpload){this.uploadFile(o,callback,uri,postData);return o;}
if(method.toUpperCase()=='GET'){if(this._sFormData.length!==0){uri+=((uri.indexOf('?')==-1)?'?':'&')+this._sFormData;}}
else if(method.toUpperCase()=='POST'){postData=postData?this._sFormData+"&"+postData:this._sFormData;}}
if(method.toUpperCase()=='GET'&&(callback&&callback.cache===false)){uri+=((uri.indexOf('?')==-1)?'?':'&')+"rnd="+new Date().valueOf().toString();}
if(this._use_default_xhr_header){if(!this._default_headers['X-Requested-With']){this.initHeader('X-Requested-With',this._default_xhr_header,true);YAHOO.log('Initialize transaction header X-Request-Header to XMLHttpRequest.','info','Connection');}}
if((method.toUpperCase()==='POST'&&this._use_default_post_header)&&this._isFormSubmit===false){this.initHeader('Content-Type',this._default_post_header);YAHOO.log('Initialize header Content-Type to application/x-www-form-urlencoded; UTF-8 for POST transaction.','info','Connection');}
if(o.xdr){this.xdr(o,method,uri,callback,postData);return o;}
o.conn.open(method,uri,true);if(this._has_default_headers||this._has_http_headers){this.setHeader(o);}
this.handleReadyState(o,callback);o.conn.send(postData||'');YAHOO.log('Transaction '+o.tId+' sent.','info','Connection');if(this._isFormSubmit===true){this.resetFormState();}
this.startEvent.fire(o,args);if(o.startEvent){o.startEvent.fire(o,args);}
return o;}},initCustomEvents:function(o,callback)
{var prop;for(prop in callback.customevents){if(this._customEvents[prop][0]){o[this._customEvents[prop][0]]=new YAHOO.util.CustomEvent(this._customEvents[prop][1],(callback.scope)?callback.scope:null);YAHOO.log('Transaction-specific Custom Event '+o[this._customEvents[prop][1]]+' created.','info','Connection');o[this._customEvents[prop][0]].subscribe(callback.customevents[prop]);YAHOO.log('Transaction-specific Custom Event '+o[this._customEvents[prop][1]]+' subscribed.','info','Connection');}}},handleReadyState:function(o,callback)
{var oConn=this,args=(callback&&callback.argument)?callback.argument:null;if(callback&&callback.timeout){this._timeOut[o.tId]=window.setTimeout(function(){oConn.abort(o,callback,true);},callback.timeout);}
this._poll[o.tId]=window.setInterval(function(){if(o.conn&&o.conn.readyState===4){window.clearInterval(oConn._poll[o.tId]);delete oConn._poll[o.tId];if(callback&&callback.timeout){window.clearTimeout(oConn._timeOut[o.tId]);delete oConn._timeOut[o.tId];}
oConn.completeEvent.fire(o,args);if(o.completeEvent){o.completeEvent.fire(o,args);}
oConn.handleTransactionResponse(o,callback);}},this._polling_interval);},handleTransactionResponse:function(o,callback,isAbort)
{var httpStatus,responseObject,args=(callback&&callback.argument)?callback.argument:null,xdrS=(o.r&&o.r.statusText==='xdr:success')?true:false,xdrF=(o.r&&o.r.statusText==='xdr:failure')?true:false,xdrA=isAbort;try
{if((o.conn.status!==undefined&&o.conn.status!==0)||xdrS){httpStatus=o.conn.status;}
else if(xdrF&&!xdrA){httpStatus=0;}
else{httpStatus=13030;}}
catch(e){httpStatus=13030;}
if((httpStatus>=200&&httpStatus<300)||httpStatus===1223||xdrS){responseObject=o.xdr?o.r:this.createResponseObject(o,args);if(callback&&callback.success){if(!callback.scope){callback.success(responseObject);YAHOO.log('Success callback. HTTP code is '+httpStatus,'info','Connection');}
else{callback.success.apply(callback.scope,[responseObject]);YAHOO.log('Success callback with scope. HTTP code is '+httpStatus,'info','Connection');}}
this.successEvent.fire(responseObject);if(o.successEvent){o.successEvent.fire(responseObject);}}
else{switch(httpStatus){case 12002:case 12029:case 12030:case 12031:case 12152:case 13030:responseObject=this.createExceptionObject(o.tId,args,(isAbort?isAbort:false));if(callback&&callback.failure){if(!callback.scope){callback.failure(responseObject);YAHOO.log('Failure callback. Exception detected. Status code is '+httpStatus,'warn','Connection');}
else{callback.failure.apply(callback.scope,[responseObject]);YAHOO.log('Failure callback with scope. Exception detected. Status code is '+httpStatus,'warn','Connection');}}
break;default:responseObject=(o.xdr)?o.response:this.createResponseObject(o,args);if(callback&&callback.failure){if(!callback.scope){callback.failure(responseObject);YAHOO.log('Failure callback. HTTP status code is '+httpStatus,'warn','Connection');}
else{callback.failure.apply(callback.scope,[responseObject]);YAHOO.log('Failure callback with scope. HTTP status code is '+httpStatus,'warn','Connection');}}}
this.failureEvent.fire(responseObject);if(o.failureEvent){o.failureEvent.fire(responseObject);}}
this.releaseObject(o);responseObject=null;},createResponseObject:function(o,callbackArg)
{var obj={},headerObj={},i,headerStr,header,delimitPos;try
{headerStr=o.conn.getAllResponseHeaders();header=headerStr.split('\n');for(i=0;i<header.length;i++){delimitPos=header[i].indexOf(':');if(delimitPos!=-1){headerObj[header[i].substring(0,delimitPos)]=YAHOO.lang.trim(header[i].substring(delimitPos+2));}}}
catch(e){}
obj.tId=o.tId;obj.status=(o.conn.status==1223)?204:o.conn.status;obj.statusText=(o.conn.status==1223)?"No Content":o.conn.statusText;obj.getResponseHeader=headerObj;obj.getAllResponseHeaders=headerStr;obj.responseText=o.conn.responseText;obj.responseXML=o.conn.responseXML;if(callbackArg){obj.argument=callbackArg;}
return obj;},createExceptionObject:function(tId,callbackArg,isAbort)
{var COMM_CODE=0,COMM_ERROR='communication failure',ABORT_CODE=-1,ABORT_ERROR='transaction aborted',obj={};obj.tId=tId;if(isAbort){obj.status=ABORT_CODE;obj.statusText=ABORT_ERROR;}
else{obj.status=COMM_CODE;obj.statusText=COMM_ERROR;}
if(callbackArg){obj.argument=callbackArg;}
return obj;},initHeader:function(label,value,isDefault)
{var headerObj=(isDefault)?this._default_headers:this._http_headers;headerObj[label]=value;if(isDefault){this._has_default_headers=true;}
else{this._has_http_headers=true;}},setHeader:function(o)
{var prop;if(this._has_default_headers){for(prop in this._default_headers){if(YAHOO.lang.hasOwnProperty(this._default_headers,prop)){o.conn.setRequestHeader(prop,this._default_headers[prop]);YAHOO.log('Default HTTP header '+prop+' set with value of '+this._default_headers[prop],'info','Connection');}}}
if(this._has_http_headers){for(prop in this._http_headers){if(YAHOO.lang.hasOwnProperty(this._http_headers,prop)){o.conn.setRequestHeader(prop,this._http_headers[prop]);YAHOO.log('HTTP header '+prop+' set with value of '+this._http_headers[prop],'info','Connection');}}
this._http_headers={};this._has_http_headers=false;}},resetDefaultHeaders:function(){this._default_headers={};this._has_default_headers=false;},abort:function(o,callback,isTimeout)
{var abortStatus,args=(callback&&callback.argument)?callback.argument:null;o=o||{};if(o.conn){if(o.xhr){if(this.isCallInProgress(o)){o.conn.abort();window.clearInterval(this._poll[o.tId]);delete this._poll[o.tId];if(isTimeout){window.clearTimeout(this._timeOut[o.tId]);delete this._timeOut[o.tId];}
abortStatus=true;}}
else if(o.xdr){o.conn.abort(o.tId);abortStatus=true;}}
else if(o.upload){var frameId='yuiIO'+o.tId;var io=document.getElementById(frameId);if(io){YAHOO.util.Event.removeListener(io,"load");document.body.removeChild(io);YAHOO.log('File upload iframe destroyed. Id is:'+frameId,'info','Connection');if(isTimeout){window.clearTimeout(this._timeOut[o.tId]);delete this._timeOut[o.tId];}
abortStatus=true;}}
else{abortStatus=false;}
if(abortStatus===true){this.abortEvent.fire(o,args);if(o.abortEvent){o.abortEvent.fire(o,args);}
this.handleTransactionResponse(o,callback,true);YAHOO.log('Transaction '+o.tId+' aborted.','info','Connection');}
return abortStatus;},isCallInProgress:function(o)
{o=o||{};if(o.xhr&&o.conn){return o.conn.readyState!==4&&o.conn.readyState!==0;}
else if(o.xdr&&o.conn){return o.conn.isCallInProgress(o.tId);}
else if(o.upload===true){return document.getElementById('yuiIO'+o.tId)?true:false;}
else{return false;}},releaseObject:function(o)
{if(o&&o.conn){o.conn=null;YAHOO.log('Connection object for transaction '+o.tId+' destroyed.','info','Connection');o=null;}}};YAHOO.register("connection_core",YAHOO.util.Connect,{version:"2.8.0r4",build:"2449"});