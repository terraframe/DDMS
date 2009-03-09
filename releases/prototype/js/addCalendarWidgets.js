// If you include this file it will add calandar popups to all elements with the class "DatePick"
// HOW TO USE
// 1. Put this at the bottom of the page <div id="cal1Container" class="yui-skin-sam"></div> 
// 2. Set the class of the input to "DATE PICK"
// 3. MAKE SURE THE ELEMENT HAS A DOM ID !!!!!!!
var MojoCal= YAHOO.namespace('MojoCal');

(function() {
    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event,
        cal1,
        init_not_done = true,
        cfg,
        over_cal = false,
        cur_field = '';

    if(locale == "en_US")
    {
      cfg = {DATE_FIELD_DELIMITER:'/', 
			DATE_RANGE_DELIMITER:'-',
			MDY_YEAR_POSITION:3,
			MDY_MONTH_POSITION:1,
			MDY_DAY_POSITION:2
      };
    }

    var setupListeners = function() {
        Event.addListener('cal1Container', 'mouseover', function() {
            over_cal = true;
        });
        Event.addListener('cal1Container', 'mouseout', function() {
            over_cal = false;
        });
    }

    var getDate = function() {
            var calDate = this.getSelectedDates()[0];
            if(locale == "en_US")
            {
            calDate = (calDate.getMonth() + 1) + '/' + calDate.getDate() + '/' + calDate.getFullYear();
            //calDate = calDate.getFullYear() + '-' + calDate.getMonth() + '-' + calDate.getDate();
            }
            cur_field.value = calDate;            
            over_cal = false;
            hideCal();
    }
    
    
    var showCal = function(ev) {
        var tar = Event.getTarget(ev);
        cur_field = tar;
        var xy = Dom.getXY(tar);
        	
        if(locale == "en_US")
        {
        	var date = Dom.get(tar).value;
        	//switch the dashes to slashes to make date format compatable with js
        	//var date = Dom.get(tar).value.replace(/-/g,"/");
        }
        
        
        
        if (date && (new Date(date) != null)) {
            cal1.cfg.setProperty('selected', date);
            cal1.cfg.setProperty('pagedate', new Date(date), true);
        } else {
            cal1.cfg.setProperty('selected', '');
            cal1.cfg.setProperty('pagedate', new Date(), true);
        }
        cal1.render();
        Dom.setStyle('cal1Container', 'display', 'block');
        xy[1] = xy[1] + 20;
        Dom.setXY('cal1Container', xy);
    }

    var hideCal = function() {
        if (!over_cal) {
            Dom.setStyle('cal1Container', 'display', 'none');
        }
    }
    
  /*  MojoCal.getConfig = function(){ return cfg; } ;
    
    MojoCal.getCal = function(){return cal;};*/
    
    
    var init = function() {
    
        if(Dom.getElementsByClassName("DatePick").length > 0 && init_not_done)
        {
	        cal1 = new YAHOO.widget.Calendar("cal1","cal1Container",cfg);
	        
	        cal1.selectEvent.subscribe(getDate, cal1, true);
	        cal1.renderEvent.subscribe(setupListeners, cal1, true);
	
	        for each (el in Dom.getElementsByClassName("DatePick"))
	        {	        	
	          Event.addListener(el.id, 'focus', showCal);
	          Event.addListener(el.id, 'blur', hideCal);
	          if(/[1-2]\d\d\d-[0-1][0-9]-[0-3]\d/.test(el.value))
	          {
	        	  d = new Date(el.value.replace(/-/g,"/"));
	        	 if(locale == "en_US")
	  	         {        		  
	        		 newdate = (d.getMonth() + 1) + '/' + d.getDate() + '/' + d.getFullYear();
	        		 el.value = newdate;
	            }
	          }
	        }
	        
	        cal1.render();
        }
        init_not_done = false;
        return cal1;
    }

    MojoCal.init = init;
    
    Event.addListener(window, 'load', init);
    //YAHOO.util.Event.onContentReady("cal1Container", init);

})();