<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<jwr:script src="/bundles/yuiBundle.js" useRandomParam="false"/>

<select id='s'>
  <option id=''></option>
  <option id='a'>A</option>
</select>


<script type="text/javascript">

var evt = new YAHOO.util.CustomEvent('My Event');
evt.subscribe(function(type, args){
  alert(type+' : '+args[0]);
});
evt.subscribe(function(type, args){
  alert(type+' : '+args[0]);
});

var s = document.getElementById('s');
s.addEventListener('change', function(e){

  evt.fire(e);

}, false);

s.selectedIndex = 1;

</script>