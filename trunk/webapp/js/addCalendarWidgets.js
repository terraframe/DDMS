// If you include this file it will add calandar popups to all elements with the class "DatePick"
// HOW TO USE
// 1. Put this at the bottom of the page <div id="cal1Container" class="yui-skin-sam"></div> 
// 2. Set the class of the input to "DATE PICK"
// 3. MAKE SURE THE ELEMENT HAS A DOM ID !!!!!!!
(function() {
    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event,
        cal1,
        over_cal = false,
        cur_field = '';

    var init = function() {
    
        if(Dom.getElementsByClassName("DatePick").length > 0)
        {
	        cal1 = new YAHOO.widget.Calendar("cal1","cal1Container");
	        cal1.cfg.setProperty("DATE_FIELD_DELIMITER", "-");
	        cal1.cfg.setProperty("DATE_RANGE_DELIMITER", "*");
	        cal1.cfg.setProperty("MDY_YEAR_POSITION", 1);
			cal1.cfg.setProperty("MDY_MONTH_POSITION", 2);
			cal1.cfg.setProperty("MDY_DAY_POSITION", 3);
	        
	        cal1.selectEvent.subscribe(getDate, cal1, true);
	        cal1.renderEvent.subscribe(setupListeners, cal1, true);
	
	        for each (el in Dom.getElementsByClassName("DatePick"))
	        {	        	
	          Event.addListener(el.id, 'focus', showCal);
	          Event.addListener(el.id, 'blur', hideCal);
	        }
	        
	        cal1.render();
        }
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
            //calDate = (calDate.getMonth() + 1) + '/' + calDate.getDate() + '/' + calDate.getFullYear();
            calDate = calDate.getFullYear() + '-' + calDate.getMonth() + '-' + calDate.getDate();
            cur_field.value = calDate;            
            over_cal = false;
            hideCal();
    }

    var showCal = function(ev) {
        var tar = Event.getTarget(ev);
        cur_field = tar;
        var xy = Dom.getXY(tar);
        	//switch the dashes to slashes to make date format compatable with js
        var date = Dom.get(tar).value.replace(/-/g,"/");
        if (date) {
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

    Event.addListener(window, 'load', init);
    //YAHOO.util.Event.onContentReady("cal1Container", init);

})();