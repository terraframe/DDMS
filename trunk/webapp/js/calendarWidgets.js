
// If you include this file it will add calandar popups to all elements with the
// class "DatePick"
// HOW TO USE
// 1. Set the class of the input to "DATE PICK"
// 2. MAKE SURE THE ELEMENT HAS A DOM ID !!!!!!!
var MojoCal= YAHOO.namespace('MojoCal');

(function() {
    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event,
        cal1,
        init_not_done = true,
        over_cal = false,
        cur_field = '',
        java_date_format = MDSS.DateSettings.java_date_format,
        db_datetime_format = MDSS.DateSettings.db_datetime_format,
        db_date_format = MDSS.DateSettings.db_date_format;


      cfg = {DATE_FIELD_DELIMITER:MDSS.DateSettings.DATE_FIELD_DELIMITER,
			DATE_RANGE_DELIMITER:MDSS.DateSettings.DATE_RANGE_DELIMITER,
			MDY_DAY_POSITION:MDSS.DateSettings.MDY_DAY_POSITION,
			MDY_MONTH_POSITION:MDSS.DateSettings.MDY_MONTH_POSITION,
			MDY_YEAR_POSITION:MDSS.DateSettings.MDY_YEAR_POSITION,
			MONTHS_LONG:MDSS.DateSettings.MONTHS_LONG,
	        WEEKDAYS_SHORT:MDSS.DateSettings.WEEKDAYS_SHORT,
      		};

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

            cur_field.value = calDate.format(java_date_format);
            over_cal = false;
            hideCal();
    }

    var var_to_date = function(date_str) {
    	if(date_str instanceof Date) return date_str;

    	date = Date.parseString(date_str,java_date_format);
    	if(date == null) date = Date.parseString(date_str,db_datetime_format);
    	if(date == null) date = Date.parseString(date_str,db_date_format);
    	if(date == null) date = Date.parseString(date_str);
    	if(date == null && date_str.length > 16) date = new Date(date_str);
    	return date;
    }

    MojoCal.parseDate = var_to_date;

    var var_to_db_string = function(date_str) {
    	date = var_to_date(date_str);
    	if(date == null) return null;
    	return date.format(db_date_format);
    }

    MojoCal.getMojoDateString = var_to_db_string;

    var var_to_localized_string = function(date_str) {
    	date = var_to_date(date_str);
    	if(date == null) return null;
    	return date.format(java_date_format);
    }

    MojoCal.getLocalizedString = var_to_localized_string;

    var showCal = function(ev) {
        var tar = Event.getTarget(ev);
        cur_field = tar;
        var xy = Dom.getXY(tar);

        var date_str = Dom.get(tar).value;
        var date =  var_to_date(date_str);


        if (date_str && (date != null)) {
            cal1.cfg.setProperty('selected', date_str);
            cal1.cfg.setProperty('pagedate', date, true);
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

  /*
	 * MojoCal.getConfig = function(){ return cfg; } ;
	 *
	 * MojoCal.getCal = function(){return cal;};
	 */


    var init = function() {

    	if(Dom.getElementsByClassName("formatDate").length > 0 && init_not_done)
    	{
    		for each (el in Dom.getElementsByClassName("formatDate"))
	        {
	          el.innerHTML = var_to_localized_string(el.innerHTML);
	        }
    	}


    	if(Dom.getElementsByClassName("DatePick").length > 0 && init_not_done)
        {

    		if(! Dom.get('cal1Container'))
    		{
	    		caldiv = document.createElement('div');
	    		caldiv.id="cal1Container";
	    		document.getElementsByTagName('body')[0].appendChild(caldiv);
	    		YAHOO.util.Dom.addClass('cal1Container', 'yui-skin-sam');
    		}

    		cal1 = new YAHOO.widget.Calendar("cal1","cal1Container",cfg);

	        cal1.selectEvent.subscribe(getDate, cal1, true);
	        cal1.renderEvent.subscribe(setupListeners, cal1, true);

	        for each (el in Dom.getElementsByClassName("DatePick"))
	        {
	          Event.addListener(el.id, 'focus', showCal);
	          Event.addListener(el.id, 'blur', hideCal);
	          el.value = var_to_localized_string(el.value);
	        }
	        cal1.render();
        }
        init_not_done = false;
        return cal1;
    }

    MojoCal.init = init;

    Event.addListener(window, 'load', init);
    // YAHOO.util.Event.onContentReady("cal1Container", init);

})();