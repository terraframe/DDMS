
YAHOO.widget.DateMath={DAY:"D",WEEK:"W",YEAR:"Y",MONTH:"M",ONE_DAY_MS:1000*60*60*24,WEEK_ONE_JAN_DATE:1,add:function(date,field,amount){var d=new Date(date.getTime());switch(field){case this.MONTH:var newMonth=date.getMonth()+amount;var years=0;if(newMonth<0){while(newMonth<0){newMonth+=12;years-=1;}}else if(newMonth>11){while(newMonth>11){newMonth-=12;years+=1;}}
d.setMonth(newMonth);d.setFullYear(date.getFullYear()+years);break;case this.DAY:this._addDays(d,amount);break;case this.YEAR:d.setFullYear(date.getFullYear()+amount);break;case this.WEEK:this._addDays(d,(amount*7));break;}
return d;},_addDays:function(d,nDays){if(YAHOO.env.ua.webkit&&YAHOO.env.ua.webkit<420){if(nDays<0){for(var min=-128;nDays<min;nDays-=min){d.setDate(d.getDate()+min);}}else{for(var max=96;nDays>max;nDays-=max){d.setDate(d.getDate()+max);}}}
d.setDate(d.getDate()+nDays);},subtract:function(date,field,amount){return this.add(date,field,(amount*-1));},before:function(date,compareTo){var ms=compareTo.getTime();if(date.getTime()<ms){return true;}else{return false;}},after:function(date,compareTo){var ms=compareTo.getTime();if(date.getTime()>ms){return true;}else{return false;}},between:function(date,dateBegin,dateEnd){if(this.after(date,dateBegin)&&this.before(date,dateEnd)){return true;}else{return false;}},getJan1:function(calendarYear){return this.getDate(calendarYear,0,1);},getDayOffset:function(date,calendarYear){var beginYear=this.getJan1(calendarYear);var dayOffset=Math.ceil((date.getTime()-beginYear.getTime())/this.ONE_DAY_MS);return dayOffset;},getWeekNumber:function(date,firstDayOfWeek,janDate){firstDayOfWeek=firstDayOfWeek||0;janDate=janDate||this.WEEK_ONE_JAN_DATE;var targetDate=this.clearTime(date),startOfWeek,endOfWeek;if(targetDate.getDay()===firstDayOfWeek){startOfWeek=targetDate;}else{startOfWeek=this.getFirstDayOfWeek(targetDate,firstDayOfWeek);}
var startYear=startOfWeek.getFullYear();endOfWeek=new Date(startOfWeek.getTime()+6*this.ONE_DAY_MS);var weekNum;if(startYear!==endOfWeek.getFullYear()&&endOfWeek.getDate()>=janDate){weekNum=1;}else{var weekOne=this.clearTime(this.getDate(startYear,0,janDate)),weekOneDayOne=this.getFirstDayOfWeek(weekOne,firstDayOfWeek);var daysDiff=Math.round((targetDate.getTime()-weekOneDayOne.getTime())/this.ONE_DAY_MS);var rem=daysDiff%7;var weeksDiff=(daysDiff-rem)/7;weekNum=weeksDiff+1;}
return weekNum;},getFirstDayOfWeek:function(dt,startOfWeek){startOfWeek=startOfWeek||0;var dayOfWeekIndex=dt.getDay(),dayOfWeek=(dayOfWeekIndex-startOfWeek+7)%7;return this.subtract(dt,this.DAY,dayOfWeek);},isYearOverlapWeek:function(weekBeginDate){var overlaps=false;var nextWeek=this.add(weekBeginDate,this.DAY,6);if(nextWeek.getFullYear()!=weekBeginDate.getFullYear()){overlaps=true;}
return overlaps;},isMonthOverlapWeek:function(weekBeginDate){var overlaps=false;var nextWeek=this.add(weekBeginDate,this.DAY,6);if(nextWeek.getMonth()!=weekBeginDate.getMonth()){overlaps=true;}
return overlaps;},findMonthStart:function(date){var start=this.getDate(date.getFullYear(),date.getMonth(),1);return start;},findMonthEnd:function(date){var start=this.findMonthStart(date);var nextMonth=this.add(start,this.MONTH,1);var end=this.subtract(nextMonth,this.DAY,1);return end;},clearTime:function(date){date.setHours(12,0,0,0);return date;},getDate:function(y,m,d){var dt=null;if(YAHOO.lang.isUndefined(d)){d=1;}
if(y>=100){dt=new Date(y,m,d);}else{dt=new Date();dt.setFullYear(y);dt.setMonth(m);dt.setDate(d);dt.setHours(0,0,0,0);}
return dt;}};YAHOO.register("datemath",YAHOO.widget.DateMath,{version:"2.8.0r4",build:"2449"});