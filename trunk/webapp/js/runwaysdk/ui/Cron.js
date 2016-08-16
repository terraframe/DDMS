/*
 * Copyright (c) 2013 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */

//define(["../../ClassFramework", "../../Util", "../RunwaySDK_UI", "../factory/runway/widget/Widget", "../factory/runway/form/Form", "jquery-cron"], function(ClassFramework, Util, UI, Widget, Forms) {
(function(){  

  var ClassFramework = Mojo.Meta;
  var Util = Mojo.Util;
  var Widget = com.runwaysdk.ui.factory.runway.Widget;
  var Forms = Mojo.Meta.alias(Mojo.RW_PACKAGE+"*");
  
  var pack = "com.runwaysdk.ui.";
  var widgetName = pack+'CronPicker';
  var cronUtilName = pack+"CronUtil";
  
  /**
   * LANGUAGE
   */
  com.runwaysdk.Localize.defineLanguage(widgetName, {
    "disabled" : "Disabled",
    "enabled" : "Enabled"
  });
  com.runwaysdk.Localize.defineLanguage(cronUtilName, {
    "never" : "Never",
    "everyMinute" : "Every ${minute}",
    "everyHour" : "Every ${hour} at ${actualMinute} minutes past the hour.",
    "everyDay" : "Every ${day} at ${actualHour}:${actualMinute}.",
    "everyWeek" : "Every ${week} on ${actualDayOfWeek} at ${actualHour}:${actualMinute}.",
    "everyMonth" : "Every ${month} on the ${actualDay} at ${actualHour}:${actualMinute}.",
    
    "minute" : "minute",
    "hour" : "hour",
    "day" : "day",
    "week" : "week",
    "month" : "month",
    
    "sunday" : "Sunday",
    "monday" : "Monday",
    "tuesday" : "Tuesday",
    "wednesday" : "Wednesday",
    "thursday" : "Thursday",
    "friday" : "Friday",
    "saturday" : "Saturday"
  });
  
  var CronPicker = ClassFramework.newClass(widgetName, {
    
    Extends : Widget,
    
    Constants: {
    	SECONDS : 0,
    	MINUTES : 1,
    	HOURS : 2,
    	DOM : 3,
    	MONTH : 4,
    	DOW : 5
    },
    	 
    Instance : {
      
      initialize : function(cronStr, config) {
        
        this.$initialize("div");
        
        this._cronStr = cronStr || null;
        this._enabled = false;
        this._config = config || {};
        
        var utilLang = com.runwaysdk.Localize.getLanguage(cronUtilName);
        this._utilLang = utilLang;
        
        var fac = this.getFactory();
        
        this._rangePicker = fac.newElement("select");
        this._rangePicker.appendChild(fac.newElement("option", {value: "everyMinute", innerHTML: utilLang.get("minute")}));
        this._rangePicker.appendChild(fac.newElement("option", {value: "everyHour", innerHTML: utilLang.get("hour")}));
        this._rangePicker.appendChild(fac.newElement("option", {value: "everyDay", innerHTML: utilLang.get("day")}));
        this._rangePicker.appendChild(fac.newElement("option", {value: "everyWeek", innerHTML: utilLang.get("week")}));
        this._rangePicker.appendChild(fac.newElement("option", {value: "everyMonth", innerHTML: utilLang.get("month")}));
        this._rangePicker.setValue(CronUtil.getEveryStrFromCron(this.getCronString() || "0 * * * * ?"));
        
        this._minutePicker = this._generateNumberPicker(0, 59, function(index){if (index < 10) {return "0"+index;} return index; });
        this._hourPicker = this._generateNumberPicker(0, 23);
        this._dayOfWeekPicker = this._generateNumberPicker(1, 7, function(index){return CronUtil.convertDayOfWeekNumberToLocalizedWeek(index)});
        this._dayOfWeekPicker.setValue(1);
        this._dayPicker = this._generateNumberPicker(1, 31, CronUtil.formatDayValue);
        this._dayPicker.setValue(1);
      },
      
      _generateNumberPicker : function(startNum, endNum, formatter) {
        var fac = this.getFactory();
        
        var picker = fac.newElement("select");
        for (var i = startNum; i <= endNum; ++i) {
          var localized = i;
          if (formatter != null) {
            localized = formatter(i);
          }
          
          picker.appendChild(fac.newElement("option", {value: i, innerHTML: localized}));
        }
        return picker;
      },
      
      getImpl : function() {
        return this._impl;
      },
      
      _onClickEnableCheckbox : function(checkEvent)
      {
        this.setEnabled(checkEvent.getCheckBox().isChecked());
      },
      
      setEnabled : function(enabled)
      {
        this._enabled = enabled;
        
        if (this._enabled)
        {
          this._cron.setStyle("display", "inline");
          
          if (this._cronStr == null) {
            this._cronStr = "0 * * * * ?";
            this._rangePicker.setValue("everyMinute");
          }
          this._writeCronHtml();
        }
        else
        {
          this._cron.setStyle("display", "none");
          this._cronStr = null;
        }
      },
      
      getCronString : function() {
        return this._cronStr;
      },
      
      setCronString : function(cronStr) {
        if (cronStr === "") {
          cronStr = null;
        }
        
        this._cronStr = cronStr;
        if (cronStr != null) {
          this._rangePicker.setValue(CronUtil.getEveryStrFromCron(this._cronStr));
        }
        
        if (this.isRendered()) {
          if (this._cronStr == null) {
            this.setEnabled(false);
            this._writeCronHtml();
          }
          else {
            this.setEnabled(true);
          }
        }
      },
      
      _writeHtml : function() {
        // Write Enable/Disable checkbox
        var enableDiv = this.getFactory().newElement("div", null, {display: "inline"});
        this._checkbox = this.getFactory().newCheckBox();
        this._checkbox.addOnCheckListener(Mojo.Util.bind(this, this._onClickEnableCheckbox));
        this._checkbox.setStyle("display", "inline-block");
        enableDiv.appendChild(this._checkbox);
        var label = this.getFactory().newElement("label", {innerHTML: this._config.localizedLabel});
        label.setStyle("display", "inline-block");
        enableDiv.appendChild(label);
        this.appendChild(enableDiv);
        
        this.appendChild(this.getFactory().newElement("br"));
        
        // Create the div that holds the cron picker html and either show or hide it based on if we're enabled or diasbled.
        this._cron = this.getFactory().newElement("div");
        this.appendChild(this._cron);
        
        if (this._cronStr == null) {
          this.setEnabled(false);
          this._writeCronHtml();
        }
        else {
          this.setEnabled(true);
        }
      },
      
      _calcCronStr : function(minute, hour, dayNum, month, dayOfTheWeek) {
        var everyStrName = this._rangePicker.getValue();
        
        if (everyStrName === "everyMinute") {
          minute = null;
          hour = null;
          dayNum = null;
          dayOfTheWeek = null;
          month = null;
        }
        else if (everyStrName === "everyHour") {
          minute = minute || 0;
          hour = null;
          dayNum = null;
          dayOfTheWeek = null;
          month = null;
        }
        else if (everyStrName == "everyDay") {
          minute = minute || 0;
          hour = hour || 0;
          dayNum = null;
          dayOfTheWeek = null;
          month = null;
        }
        else if (everyStrName == "everyWeek") {
          minute = minute || 0;
          hour = hour || 0;
          dayOfTheWeek = dayOfTheWeek || 1;
          dayNum = null;
          month = null;
        }
        else if (everyStrName === "everyMonth") {
          minute = minute || 0;
          hour = hour || 0;
          dayNum = dayNum || 1;
          month = null;
          dayOfTheWeek = null;
        }
        
        minute = minute == null ? "*" : minute;
        hour = hour == null ? "*" : hour;
        dayNum = dayNum == null ? (dayOfTheWeek == null ? "*" : "?") : dayNum;
        month = month == null ? "*" : month;
        dayOfTheWeek = dayOfTheWeek == null ? "?" : dayOfTheWeek;
        
        this._cronStr = "0 " + minute + " " + hour + " " + dayNum + " " + month + " " + dayOfTheWeek;
        this._writeCronHtml();
      },
      
      _writeCronHtml : function() {
        if (this.getCronString() != null) {
          var fac = this.getFactory();
          this._cron.setInnerHTML("");
          
          var everyStrName = this._rangePicker.getValue();
          var everyStr = this._utilLang.get(everyStrName);
          
          var cronStrParts = this.getCronString().split(" ");
          
          if (everyStrName === "everyMinute") {
            this._cron.setInnerHTML(everyStr.replace("${minute}", this._rangePicker.getOuterHTML()));
            
            this._rangePicker = this._cron.getChildWithId(this._rangePicker.getId());
            this._rangePicker.setValue("everyMinute");
            this._rangePicker.addEventListener("change", Mojo.Util.bind(this, function(){this._calcCronStr();}));
          }
          else if (everyStrName === "everyHour") {
            this._rangePicker.setValue("everyHour");
            this._cron.setInnerHTML(everyStr.replace("${hour}", this._rangePicker.getOuterHTML()).replace("${actualMinute}", this._minutePicker.getOuterHTML()));
            
            this._rangePicker = this._cron.getChildWithId(this._rangePicker.getId());
            this._rangePicker.setValue("everyHour");
            
            this._minutePicker = this._cron.getChildWithId(this._minutePicker.getId());
            this._minutePicker.setValue(cronStrParts[this.constructor.MINUTES]);
            
            var changeFunc = Mojo.Util.bind(this, function(){this._calcCronStr(this._minutePicker.getValue())});
            this._minutePicker.addEventListener("change", changeFunc);
            this._rangePicker.addEventListener("change", changeFunc);
          }
          else if (everyStrName == "everyDay") {
            this._rangePicker.setValue("everyDay");
            
            var cronHTML = everyStr.replace("${day}", this._rangePicker.getOuterHTML());
            cronHTML = cronHTML.replace("${actualHour}", this._hourPicker.getOuterHTML());
            cronHTML = cronHTML.replace("${actualMinute}", this._minutePicker.getOuterHTML());
            this._cron.setInnerHTML(cronHTML);
            
            this._rangePicker = this._cron.getChildWithId(this._rangePicker.getId());
            this._rangePicker.setValue("everyDay");
            
            this._minutePicker = this._cron.getChildWithId(this._minutePicker.getId());
            this._minutePicker.setValue(cronStrParts[this.constructor.MINUTES]);
            
            this._hourPicker = this._cron.getChildWithId(this._hourPicker.getId());
            this._hourPicker.setValue(cronStrParts[this.constructor.HOURS]);
            
            var changeFunc = Mojo.Util.bind(this, function(){this._calcCronStr(this._minutePicker.getValue(), this._hourPicker.getValue())});
            this._minutePicker.addEventListener("change", changeFunc);
            this._hourPicker.addEventListener("change", changeFunc);
            this._rangePicker.addEventListener("change", changeFunc);
          }
          else if (everyStrName == "everyWeek") {
            this._rangePicker.setValue("everyWeek");
            
            var cronHTML = everyStr.replace("${week}", this._rangePicker.getOuterHTML());
            cronHTML = cronHTML.replace("${actualMinute}", this._minutePicker.getOuterHTML());
            cronHTML = cronHTML.replace("${actualHour}", this._hourPicker.getOuterHTML());
            cronHTML = cronHTML.replace("${actualDayOfWeek}", this._dayOfWeekPicker.getOuterHTML());
            this._cron.setInnerHTML(cronHTML);
            
            this._rangePicker = this._cron.getChildWithId(this._rangePicker.getId());
            this._rangePicker.setValue("everyWeek");
            
            this._minutePicker = this._cron.getChildWithId(this._minutePicker.getId());
            this._minutePicker.setValue(cronStrParts[this.constructor.MINUTES]);
            
            this._hourPicker = this._cron.getChildWithId(this._hourPicker.getId());
            this._hourPicker.setValue(cronStrParts[this.constructor.HOURS]);
            
            this._dayOfWeekPicker = this._cron.getChildWithId(this._dayOfWeekPicker.getId());
            this._dayOfWeekPicker.setValue(cronStrParts[this.constructor.DOW]);
            
            var changeFunc = Mojo.Util.bind(this, function(){this._calcCronStr(this._minutePicker.getValue(), this._hourPicker.getValue(), null, null, this._dayOfWeekPicker.getValue())});
            this._minutePicker.addEventListener("change", changeFunc);
            this._hourPicker.addEventListener("change", changeFunc);
            this._dayOfWeekPicker.addEventListener("change", changeFunc);
            this._rangePicker.addEventListener("change", changeFunc);
          }
          else if (everyStrName == "everyMonth") {
            this._rangePicker.setValue("everyMonth");
            
            var cronHTML = everyStr.replace("${month}", this._rangePicker.getOuterHTML());
            cronHTML = cronHTML.replace("${actualMinute}", this._minutePicker.getOuterHTML());
            cronHTML = cronHTML.replace("${actualHour}", this._hourPicker.getOuterHTML());
            cronHTML = cronHTML.replace("${actualDay}", this._dayPicker.getOuterHTML());
            this._cron.setInnerHTML(cronHTML);
            
            this._rangePicker = this._cron.getChildWithId(this._rangePicker.getId());
            this._rangePicker.setValue("everyMonth");
            
            this._minutePicker = this._cron.getChildWithId(this._minutePicker.getId());
            this._minutePicker.setValue(cronStrParts[this.constructor.MINUTES]);
            
            this._hourPicker = this._cron.getChildWithId(this._hourPicker.getId());
            this._hourPicker.setValue(cronStrParts[this.constructor.HOURS]);
            
            this._dayPicker = this._cron.getChildWithId(this._dayPicker.getId());
            this._dayPicker.setValue(cronStrParts[this.constructor.DOM]);
            
            var changeFunc = Mojo.Util.bind(this, function(){this._calcCronStr(this._minutePicker.getValue(), this._hourPicker.getValue(), this._dayPicker.getValue(), null, null)});
            this._minutePicker.addEventListener("change", changeFunc);
            this._hourPicker.addEventListener("change", changeFunc);
            this._dayPicker.addEventListener("change", changeFunc);
            this._rangePicker.addEventListener("change", changeFunc);
          }
        }
      },
      
      render : function(p) {
        this._writeHtml();
        
        this.$render(p);
        
//        this._impl = $(this._cron.getRawEl()).cron(this._config);
      }
    }
  });
  
  var regexMapper = [
     {regex: /0 \* \* \* \* \?/, name: "everyMinute"},
     {regex: /0 \d+ \* \* \* \?/, name: "everyHour"},
     {regex: /0 \d+ \d+ \* \* \?/, name: "everyDay"},
     {regex: /0 \d+ \d+ \? \* ./, name: "everyWeek"},
     {regex: /0 \d+ \d+ \d+ \* \?/, name: "everyMonth"}
   ];
  
  var CronUtil = Mojo.Meta.newClass(pack+"CronUtil", {
    
    IsSingleton: true,
    
    Static : {
      getEveryStrFromCron : function(cronStr) {
        for (var i = 0; i < regexMapper.length; ++i) {
          if (cronStr.match(regexMapper[i].regex)) {
            return regexMapper[i].name;
          }
        }
        
        return "everyMinute";
      },
      
      cronToHumanReadable : function(cronStr) {
        var language = com.runwaysdk.Localize.getLanguage(cronUtilName);
        
        if (cronStr == null || cronStr == "") {
          return language.get("never");
        }
        
        var everyStrName = CronUtil.getEveryStrFromCron(cronStr);
        if (everyStrName == null) { return cronStr; }
        var everyStr = language.get(everyStrName);
        
        var cronStrParts = cronStr.split(" ");
        
        if (cronStrParts[com.runwaysdk.ui.CronPicker.MINUTES].toString().length === 1) {
          cronStrParts[com.runwaysdk.ui.CronPicker.MINUTES] = "0" + cronStrParts[com.runwaysdk.ui.CronPicker.MINUTES].toString();
        }
        
        if (everyStrName == "everyMinute") {
          return everyStr.replace("${minute}", language.get("minute"));
        }
        else if (everyStrName == "everyHour") {
          return everyStr.replace("${hour}", language.get("hour")).replace("${actualMinute}", cronStrParts[com.runwaysdk.ui.CronPicker.MINUTES]);
        }
        else if (everyStrName == "everyDay") {
          return everyStr.replace("${day}", language.get("day")).replace("${actualHour}", cronStrParts[com.runwaysdk.ui.CronPicker.HOURS]).replace("${actualMinute}", cronStrParts[com.runwaysdk.ui.CronPicker.MINUTES]);
        }
        else if (everyStrName == "everyWeek") {
          var actualWeek = CronUtil.convertDayOfWeekNumberToLocalizedWeek(cronStrParts[com.runwaysdk.ui.CronPicker.DOW]);
          return everyStr.replace("${week}", language.get("week")).replace("${actualDayOfWeek}", actualWeek).replace("${actualHour}", cronStrParts[com.runwaysdk.ui.CronPicker.HOURS]).replace("${actualMinute}", cronStrParts[com.runwaysdk.ui.CronPicker.MINUTES]);
        }
        else if (everyStrName == "everyMonth") {
          var day = this.formatDayValue(cronStrParts[com.runwaysdk.ui.CronPicker.DOM]);
          return everyStr.replace("${month}", language.get("month")).replace("${actualDay}", day).replace("${actualHour}", cronStrParts[com.runwaysdk.ui.CronPicker.HOURS]).replace("${actualMinute}", cronStrParts[com.runwaysdk.ui.CronPicker.MINUTES]);
        }
        else {
          return cronStr;
        }
      },
      
      formatDayValue : function(index) {
        index = parseInt(index,10);
        
        if (index === 2 || index === 22) {
          return index + "nd";
        }
        else if (index === 3 || index === 23) {
          return index + "rd";
        }
        else if (index === 1 || index === 21 || index === 31) {
          return index + "st";
        }
        
        return index + "th";
      },
      
      convertDayOfWeekNumberToLocalizedWeek : function(weekNum) {
        var language = com.runwaysdk.Localize.getLanguage(cronUtilName);
        
        if (weekNum == 1) {
          return language.get("sunday");
        }
        else if (weekNum == 2) {
          return language.get("monday");
        }
        else if (weekNum == 3) {
          return language.get("tuesday");
        }
        else if (weekNum == 4) {
          return language.get("wednesday");
        }
        else if (weekNum == 5) {
          return language.get("thursday");
        }
        else if (weekNum == 6) {
          return language.get("friday");
        }
        else if (weekNum == 7) {
          return language.get("saturday");
        }
      }
    }
    
  });
  
  var CronInput = Mojo.Meta.newClass(pack+'CronInput', {
    Extends : Forms.FormInput,
    Instance : {
      initialize : function(name, config)
      {
        config = config || {};
        
        this._impl = new CronPicker(config.cronStr, config);
        
        this.$initialize(this._impl, null, name, config);
      },
      accept : function(visitor)
      {
        visitor.visitDefaultInput(this);
      },
      getValue : function()
      {
        return this._impl.getCronString();
      },
      setValue : function(val) {
        this._impl.setCronString(val);
      },
      render: function(p) {
        this._impl.render(p);
      }
    }
  });
  
  return Mojo.Meta.alias(pack+"*");
})();
  