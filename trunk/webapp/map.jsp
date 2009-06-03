<jsp:include page="/WEB-INF/templates/header.jsp" />

<select id='roleSelectList'>
  <option value="admin">Admin</option>
</select>
<span class="roleLink" id="dss.vector.solutions.Person">Go</span>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){

    YAHOO.util.Event.on(document.body, 'click', function(e){
      var target = e.target;
      if(target.nodeName === 'SPAN' && YAHOO.util.Dom.hasClass(target, 'roleLink'))
      {
        var select = document.getElementById('roleSelectList');
        var actor = select.options[select.selectedIndex].value;

        var newLocation = 'dss.vector.solutions.util.ReadableAttributeController.getAttributes.mojo?'
        newLocation += 'actor='+actor+'&universal='+target.id;

        window.location = newLocation;
      }
    });
  });
})();

</script>
<jsp:include page="/WEB-INF/templates/footer.jsp" />