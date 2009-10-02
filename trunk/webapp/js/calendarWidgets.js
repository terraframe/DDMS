// If you include this file it will add calandar popups to all elements with the
// class "DatePick"
// HOW TO USE
// 1. Set the class of the input to "DATE PICK"
// 2. MAKE SURE THE ELEMENT HAS A DOM ID !!!!!!!
MDSS.Calendar = {};

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


      var cfg = {DATE_FIELD_DELIMITER:MDSS.DateSettings.DATE_FIELD_DELIMITER,
			DATE_RANGE_DELIMITER:MDSS.DateSettings.DATE_RANGE_DELIMITER,
			MDY_DAY_POSITION:MDSS.DateSettings.MDY_DAY_POSITION,
			MDY_MONTH_POSITION:MDSS.DateSettings.MDY_MONTH_POSITION,
			MDY_YEAR_POSITION:MDSS.DateSettings.MDY_YEAR_POSITION,
			MONTHS_LONG:MDSS.DateSettings.MONTHS_LONG,
	        WEEKDAYS_SHORT:MDSS.DateSettings.WEEKDAYS_SHORT,
	        navigator:true
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
            fireOnblur(cur_field);
    }

    var parseISO8601 = function (date_string){
    	
      if(!Mojo.Util.isString(date_string) || date_string === '')
      {
        return null;
      }
      
      var regexp = new RegExp("^([0-9]{4})(-([0-9]{2})(-([0-9]{2})" +
          "(T([0-9]{2}):([0-9]{2})(:([0-9]{2})(\.([0-9]+))?)?" +
          "(Z|(([-+])([0-9]{2}):([0-9]{2})))?)?)?)?$");
      
      if(! regexp.test(date_string)){
      	return null;
			}
      
      var d = date_string.match(regexp);

      var offset = 0;
      var tempDate = new Date(d[1], 0, 1);

      if (d[3]) { tempDate.setMonth(d[3] - 1); }
      if (d[5]) { tempDate.setDate(d[5]); }
      if (d[7]) { tempDate.setHours(d[7]); }
      if (d[8]) { tempDate.setMinutes(d[8]); }
      if (d[10]) { tempDate.setSeconds(d[10]); }
      if (d[12]) { tempDate.setMilliseconds(Number("0." + d[12]) * 1000); }
      if (d[14]) {
          offset = (Number(d[16]) * 60) + Number(d[17]);
          offset *= ((d[15] == '-') ? 1 : -1);
      }

      offset -= tempDate.getTimezoneOffset();
      time = (Number(tempDate) + (offset * 60 * 1000));
      return tempDate;
    }

    var var_to_date = function(date_str) {
    	if(date_str instanceof Date) return date_str;

    	var date = parseISO8601(date_str);
    	if(date == null) date = Date.parseString(date_str,java_date_format);
    	if(date == null) date = Date.parseString(date_str,db_datetime_format);
    	if(date == null) date = Date.parseString(date_str,db_date_format);
    	if(date == null) date = Date.parseString(date_str);
    	if(date == null && date_str.length > 16) date = new Date(date_str);
    	return date;
    }

    MDSS.Calendar.parseDate = var_to_date;

    var var_to_db_string = function(date_str) {
    	var date = var_to_date(date_str);
    	if(date == null) return null;
    	return date.format(db_date_format);
    }

    MDSS.Calendar.getMojoDateString = var_to_db_string;

    var var_to_localized_string = function(date_str) {
    	var date = var_to_date(date_str);
    	if(date == null) return null;
    	return date.format(java_date_format);
    }

    MDSS.Calendar.getLocalizedString = var_to_localized_string;

    var showCal = function(ev) {
        var tar = Event.getTarget(ev);
        cur_field = tar;
        var xy = Dom.getXY(tar);
        var date_str = Dom.get(tar).value;
        var date =  var_to_date(date_str);

        if(Dom.hasClass(tar ,'NoFuture'))
        {
        	cal1.cfg.setProperty('maxdate', new Date());
        }
        else
        {
        	cal1.cfg.setProperty('maxdate', null);
        }


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

    var hideCal = function(ev) {
        if (!over_cal) {
            Dom.setStyle('cal1Container', 'display', 'none');
        }
        if(ev){
        	validate(ev);
        }

    }

    function fireOnblur(target) {
    	//var target=document.getElementById(objID);
    	if(document.dispatchEvent) { // W3C
    	    var oEvent = document.createEvent( "MouseEvents" );
    	    oEvent.initMouseEvent("blur", true, true,window, 1, 1, 1, 1, 1, false, false, false, false, 0, target);
    	    target.dispatchEvent( oEvent );
    	    }
    	else if(document.fireEvent) { // IE
    	    target.fireEvent("onblur");
    	    }
    	}


    var validate = function(ev) {
        var tar = Event.getTarget(ev);
        var date_str = Dom.get(tar).value;
        var today = new Date();

        //clear any errors before we move foward
        removeError(tar);

        if(date_str.length == 0)
        {
        	return;
        }

        var date = Date.parseString(date_str,java_date_format);

        if (date_str.length > 0  && (date == null))
        {
        	addError(tar, MDSS.localize('Invalid_Date_Format'));
        }

        if(Dom.hasClass(tar ,'NoFuture') && date > today)
        {
        	addError(tar, MDSS.localize('Future_Dates_Not_Allowed'));
        }



    }

    var validateYear = function(ev) {
        var tar = Event.getTarget(ev);
        var date_str = tar.value;
        var today = new Date();

        var re = /^(19|20)[0-9]{2}$/;
        //clear any errors before we move foward
        removeError(tar);

        if(date_str.length == 0)
        {
        	return;
        }

        var date = Date.parseString(date_str,java_date_format);

        if ( ! re.test(date_str))
        {
        	addError(tar, MDSS.localize('Invalid_Year'));
        }

        if(parseInt(date_str,10) > parseInt(today.getFullYear(),10))
        {
        	addError(tar, MDSS.localize('Future_Dates_Not_Allowed'));
        }
    }

    var validateNumber = function(ev) {
    	var tar = Event.getTarget(ev);
    	var number_str = tar.value;

    	var re = /^[0-9]+$/;
    	//clear any errors before we move foward
    	removeError(tar);

    	if(number_str.length == 0)
    	{
    		return;
    	}

    	if ( ! re.test(number_str))
    	{
    		addError(tar, MDSS.localize('Invalid_Number'));
    	}
    }



	function addError(tar,errorMessage)
	{
		if(tar instanceof String)
		{
		  tar = document.getElementById(tar);
		}

		var errorInfo = document.createElement('span');
	    errorInfo.id = tar.id +'errorInfo';
	    errorInfo.innerHTML = ' '+ errorMessage;
	    Dom.insertAfter(errorInfo,tar);
		Dom.addClass(errorInfo,'alert');
	}

	function removeError(tar)
	{
		if(tar instanceof String)
		{
		  tar = document.getElementById(tar);
		}

		 var delMe = Dom.get(tar.id +'errorInfo');
		 if(delMe)
		 {
			 var parent = delMe.parentNode;
			 parent.removeChild(delMe);
		 }
	}

	MDSS.Calendar.addError = addError;
	MDSS.Calendar.removeError = removeError;
	
	function addCalendarListeners(el)
	{
	  Event.addListener(el.id, 'focus', showCal);
	  Event.addListener(el.id, 'blur', hideCal);
	  el.value = var_to_localized_string(el.value);
	}
	
	MDSS.Calendar.addCalendarListeners = addCalendarListeners;

    var init = function() {
      var el;
    	if(init_not_done)
    	{
    		for each (el in Dom.getElementsByClassName("formatDate"))
	        {
	          el.innerHTML = var_to_localized_string(el.innerHTML);
	        }
    	}

    	if(init_not_done)
    	{
    		for each (el in Dom.getElementsByClassName("NoFutureYear"))
	        {
    			Event.addListener(el, 'blur', validateYear);
	        }
    	}

    	if(init_not_done)
    	{
    		for each (el in Dom.getElementsByClassName("NumbersOnly"))
    		{
    			Event.addListener(el, 'blur', validateNumber);
    		}
    	}

    	if(init_not_done){
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
	        init_not_done = false;
       }
       return cal1;
    }

    MDSS.Calendar.init = init;

    Event.addListener(window, 'load', init);

})();