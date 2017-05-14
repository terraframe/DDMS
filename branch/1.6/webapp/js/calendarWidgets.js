// If you include this file it will add calandar popups to all elements with the
// class "DatePick"
// HOW TO USE
// 1. Set the class of the input to "DATE PICK"
// 2. MAKE SURE THE ELEMENT HAS A DOM ID !!!!!!!
MDSS.Calendar = {
  Config : {
    BEFORE_TODAY_INCLUSIVE : 'beforeTodayInc',
    BEFORE_TODAY_EXCLUSIVE : 'beforeTodayExc',
    AFTER_TODAY_INCLUSIVE : 'afterTodayInc',
    AFTER_TODAY_EXCLUSIVE : 'afterTodayExc',
    START_DATE : 'startDate',
    END_DATE : 'endDate'
  }
};

(function() {

  // Make sure the date settings have been initialized to avoid errors.
  // The initialization will be completed once the user is logged in.
  if(!Mojo.Util.isObject(MDSS.DateSettings))
  {
    return;
  }

    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event,
        cal1,
        init_not_done = true,
        over_cal = false,
        cur_field = '',
        java_date_format = MDSS.DateSettings.java_date_format,
        java_date_time_format = MDSS.DateSettings.java_date_time_format,
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
      
    var calendars = {};

    var setupListeners = function(calendar) {
      if(calendar == null) {
        calendar = cal1;
      }
      
      Event.addListener(calendar.containerId, 'mouseover', function() {
        over_cal = true;
      });
      Event.addListener(calendar.containerId, 'mouseout', function() {
        over_cal = false;
      });
    }

    var getDate = function() {
            var calDate = this.getSelectedDates()[0];

            cur_field.value = calDate.format(java_date_format);
            over_cal = false;
            hideCal();
            fireOnblur(cur_field);
            
            /*
             * This is so dumb but without redesigning how the
             * calendar widgets are instatiated this is the only
             * way I can see to add additional listeners to the
             * calendar widget.
             */
            if(Mojo.Util.isFunction(MDSS.GlobalDateListener))
            {
              MDSS.GlobalDateListener(cur_field);
            }
            
    }

    var parseISO8601 = function (date_string){
      var tempDate = new Date();

      var success = Mojo.Util.setISO8601(tempDate, date_string, false);
      
      if(success)
      { 
        return tempDate;
      }
      
      return null;
    }

    var var_to_date = function(date_str, offset) {      
      if(date_str instanceof Date) return date_str;
      
      if(Mojo.Util.isString(date_str)) {
          date_str = date_str.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
      }
      if(date_str == null || date_str == '') return date_str; 

      var date = parseISO8601(date_str);
      if(date == null) date = Date.parseString(date_str,java_date_format);
      if(date == null) date = Date.parseString(date_str,db_datetime_format);
      if(date == null) date = Date.parseString(date_str,db_date_format);
      if(date == null) date = Date.parseString(date_str);
      //remove the timezone and the day
      date_str = date_str.replace(/^(\w+ )(\w+ \d\d \d\d:\d\d:\d\d )(\w+ )(\d\d\d\d)$/, '$2$4');
      if(date == null && date_str.length > 16)
      {
        date = new Date(date_str);    
      }
      
      // We need to offset the timezone difference such that when its serialized to JSON the date is the same
      if(offset != null && offset)
      {
        // Timezone offset is in minutes.  Get time is in milliseconds
        date = new Date(date.getTime() + (-1 * date.getTimezoneOffset() * 60 * 1000));        
      }
      
      return date;
    }
    
    var var_to_java_date = function (date_str) {
      if(date_str instanceof Date) return date_str;
      
      if(Mojo.Util.isString(date_str)) {
        date_str = date_str.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
      }
      
      if(date_str == null || date_str == '') return date_str; 

      return Date.parseString(date_str,java_date_format);      
    }

    MDSS.Calendar.parseDate = var_to_date;
    MDSS.Calendar.parseJavaFormatDate = var_to_java_date;

    var var_to_db_string = function(date_str) {
      var date = var_to_date(date_str);
      if(date == null || date == '') return null;
      return date.format(db_date_format);
    }

    var var_to_localized_string = function(date_str) {
      var date = var_to_date(date_str);
      if(date == null  || date == '') return null;
      return date.format(java_date_format);
    }
    
    var var_to_localized_date_time_string = function(date_str) {
      var date = var_to_date(date_str);
      if(date == null  || date == '') return null;
      return date.format(java_date_time_format);
    }
    
    var el_to_localized_el = function(el) {
        el.value = var_to_localized_string(el.value);      
    }
    
    MDSS.Calendar.getMojoDateString = var_to_db_string;

    MDSS.Calendar.getLocalizedString = var_to_localized_string;
    
    MDSS.Calendar.getLocalizedDateTime = var_to_localized_date_time_string;
    
    MDSS.Calendar.localizeDateElement = el_to_localized_el;


    /**
     * Shows (renders) the calendar.
     * @param the DOM Event that triggered the operation
     * @param config The Object configuration detailing how to render the calendar.
     */
    var showCal = function(ev, config, cal) {
      
      
      if(!cal) {
          cal = cal1;
      }
      
        var tar = Event.getTarget(ev);
        cur_field = tar;
        var xy = Dom.getXY(tar);
        var date_str = Dom.get(tar).value;
        var date =  var_to_date(date_str);

        if(Dom.hasClass(tar ,'NoFuture'))
        {
          cal.cfg.setProperty('maxdate', new Date());
        }
        else
        {
          cal.cfg.setProperty('maxdate', null); // clear the previous restriction
        }
        
        cal.cfg.setProperty('mindate', null); // clear the previous restriction


        if (date_str && (date != null)) {
            cal.cfg.setProperty('selected', date_str);
            cal.cfg.setProperty('pagedate', date, true);
        } else {
            cal.cfg.setProperty('selected', '');
            cal.cfg.setProperty('pagedate', new Date(), true);
        }
        
        // apply any configuration settings
        if(config){
          var date = new Date();
          if(config[MDSS.Calendar.Config.BEFORE_TODAY_INCLUSIVE]){
            cal.cfg.setProperty('maxdate', date);
          }
          if(config[MDSS.Calendar.Config.BEFORE_TODAY_EXCLUSIVE]){
            date.setDate(date.getDate()-1);
            cal.cfg.setProperty('maxdate', date);
          }
          if(config[MDSS.Calendar.Config.AFTER_TODAY_INCLUSIVE]){
            cal.cfg.setProperty('mindate', date);
          }
          if(config[MDSS.Calendar.Config.AFTER_TODAY_EXCLUSIVE]){
            date.setDate(date.getDate()+1);
            cal.cfg.setProperty('mindate', date);
          }
          if(config[MDSS.Calendar.Config.START_DATE]){
            date = MDSS.Calendar.parseDate(config[MDSS.Calendar.Config.START_DATE]);
            cal.cfg.setProperty('mindate', date);
          }
          if(config[MDSS.Calendar.Config.END_DATE]){
            date = MDSS.Calendar.parseDate(config[MDSS.Calendar.Config.END_DATE]);
            cal.cfg.setProperty('maxdate', date);
          }
        }
        
        cal.render();
        Dom.setStyle(cal.containerId, 'display', 'block');
        xy[1] = xy[1] + 20;
        Dom.setXY(cal.containerId, xy);
    }

    var hideCal = function(ev, cal) {

      if(!cal) {
        cal = cal1;  
      }
      
        if (!over_cal) {
            Dom.setStyle(cal.containerId, 'display', 'none');
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

  function addCalendarListeners(el, config)
  {
    Event.addListener(el.id, 'focus', showCal, config);
    Event.addListener(el.id, 'blur', hideCal);
    el.value = var_to_localized_string(el.value);
  }
  
  MDSS.Calendar.addCalendarListeners = addCalendarListeners;

    var init = function() {
      var el;
      if(init_not_done)
      {
        var els = Dom.getElementsByClassName("formatDate");
        for(var i=0, len=els.length; i<len; i++)
        {
          var el = els[i];
          el.innerHTML = var_to_localized_string(el.innerHTML);
        }
      }

      if(init_not_done)
      {
        var els = Dom.getElementsByClassName("NoFutureYear");
        for(var i=0, len=els.length; i<len; i++)
        {
          var el = els[i];
          Event.addListener(el, 'blur', validateYear);
        }
      }

      if(init_not_done)
      {
        var els = Dom.getElementsByClassName("NumbersOnly");
        for(var i=0, len=els.length; i<len; i++)
        {
          var el = els[i];
          Event.addListener(el, 'blur', validateNumber);
        }
      }

      if(init_not_done){
        if(! Dom.get('cal1Container'))
        {
          caldiv = document.createElement('div');
          caldiv.id="cal1Container";
          caldiv.style.zIndex = "15";
          document.getElementsByTagName('body')[0].appendChild(caldiv);
          YAHOO.util.Dom.addClass('cal1Container', 'yui-skin-sam');
        }

        cal1 = new YAHOO.widget.Calendar("cal1","cal1Container",cfg);
        cal1.selectEvent.subscribe(getDate, cal1, true);
        cal1.renderEvent.subscribe(function() {
          setupListeners(cal1); 
        }, cal1, true);


        var els = Dom.getElementsByClassName("DatePick");
        for(var i=0, len=els.length; i<len; i++)
        {
            var el = els[i];
            Event.addListener(el.id, 'focus', showCal);
            Event.addListener(el.id, 'blur', hideCal);
            el.value = var_to_localized_string(el.value);
        }
        cal1.render();
        init_not_done = false;
       }
      
       hideCal();      
       return cal1;
    }
    
    MDSS.Calendar.addCalendar = function(el, callback) {
      if(! Dom.get("calContainer-" + el.id))
      {    	
        var caldiv = document.createElement('div');
        caldiv.id = "calContainer-" + el.id;
        caldiv.style.zIndex = "15";
        document.getElementsByTagName('body')[0].appendChild(caldiv);
      
        YAHOO.util.Dom.addClass("calContainer-" + el.id, 'yui-skin-sam');      
      }
      
      var calendar = new YAHOO.widget.Calendar("cal-" + el.id ,"calContainer-" + el.id,cfg);
      calendar.selectEvent.subscribe(function(type,args,obj){
        var calDate = this.getSelectedDates()[0];
        var value = calDate.format(java_date_format);
        
        if(callback) {
          callback(value);          
        }

        over_cal = false;
        hideCal(null, calendar);        
      }, calendar, true);
      calendar.renderEvent.subscribe(function() {
        setupListeners(calendar); 
      }, calendar, true);
      
      
      Event.addListener(el.id, 'focus', function(ev, config) {
        showCal(ev, config, calendar); 
      });
      Event.addListener(el.id, 'blur', function(ev) {
        hideCal(ev, calendar); 
      });
      
      el.value = var_to_localized_string(el.value);
      
      calendar.render();
      hideCal(null, calendar);
      
      calendars[el.id] = calendar;
    }
    
    MDSS.Calendar.destroyCalendar = function(el) {
      if(calendars[el.id] !== undefined) {
        calendars[el.id].destroy();        
      }  
      
      delete calendars[el.id];
    }

    
    MDSS.Calendar.init = init;

    Event.addListener(window, 'load', init);

})();