<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Roles_Edit" scope="request"/>

<mjl:form id="saveForm" name="dss.vector.solutions.RoleController.save.form" method="POST">
<mjl:input type="hidden" param="id" value="${id}"/>

<div class="workarea">
  <h3><fmt:message key="Roles_Assigned"/></h3>
  <ul id="ul1" class="draglist">
    <c:forEach var="role" items="${assigned}">
      <li id="${role.id}">
        ${role.displayLabel}
        <mjl:input id="input_${role.id}" param="assigned" type="hidden" value="${role.roleName}"/>
      </li>
    </c:forEach>
  </ul>
</div>

<div class="workarea">
  <h3><fmt:message key="Roles_Revoked"/></h3>
  <ul id="ul2" class="draglist">
    <c:forEach var="role" items="${revoked}">
      <li id="${role.id}">
        ${role.displayLabel}
        <mjl:input id="input_${role.id}" param="revoked" type="hidden" value="${role.roleName}"/>
      </li>
    </c:forEach>
  </ul>
</div>
</mjl:form>

<div id="user_actions">
  <fmt:message key="save" var="saveButton"/>
  <input type="button" id="saveButton" value="${saveButton}" />
</div>

<script type="text/javascript">

(function() {

var Dom = YAHOO.util.Dom;
var Event = YAHOO.util.Event;
var DDM = YAHOO.util.DragDropMgr;

//////////////////////////////////////////////////////////////////////////////
// example app
//////////////////////////////////////////////////////////////////////////////
YAHOO.example.DDApp = {
    init: function() {
        new YAHOO.util.DDTarget("ul1");
        new YAHOO.util.DDTarget("ul2");
        <c:forEach var="role" items="${assigned}">
          new YAHOO.example.DDList("${role.id}");
        </c:forEach>
        <c:forEach var="role" items="${revoked}">
          new YAHOO.example.DDList("${role.id}");
        </c:forEach>

        Event.on("saveButton", "click", this.saveRoles);
    },

    saveRoles: function() {
        var updateAttributes = function(ul, value) {
            var items = ul.getElementsByTagName("li");
            for (i=0;i<items.length;i=i+1) {
                var inputId = "input_" + items[i].id;
                var ele = document.getElementById(inputId);
                ele.setAttribute("name", value);
            }
        };

        var ul1=Dom.get("ul1"), ul2=Dom.get("ul2");
        updateAttributes(ul1, "assigned");
        updateAttributes(ul2, "revoked");
        roleForm=document.getElementById("saveForm");
        roleForm.action="dss.vector.solutions.RoleController.save.mojo";
        roleForm.submit();
    },

    switchStyles: function() {
        Dom.get("ul1").className = "draglist_alt";
        Dom.get("ul2").className = "draglist_alt";
    }
};

//////////////////////////////////////////////////////////////////////////////
// custom drag and drop implementation
//////////////////////////////////////////////////////////////////////////////

YAHOO.example.DDList = function(id, sGroup, config) {

    YAHOO.example.DDList.superclass.constructor.call(this, id, sGroup, config);

    this.logger = this.logger || YAHOO;
    var el = this.getDragEl();
    Dom.setStyle(el, "opacity", 0.67); // The proxy is slightly transparent

    this.goingUp = false;
    this.lastY = 0;
};

YAHOO.extend(YAHOO.example.DDList, YAHOO.util.DDProxy, {

    startDrag: function(x, y) {
        this.logger.log(this.id + " startDrag");

        // make the proxy look like the source element
        var dragEl = this.getDragEl();
        var clickEl = this.getEl();
        Dom.setStyle(clickEl, "visibility", "hidden");

        // Bug Fix: DDProxy cannot handle children Element nodes correctly,
        // so only copy the text content.
        //dragEl.innerHTML = clickEl.innerHTML;
        dragEl.innerHTML = clickEl.textContent ? clickEl.textContent : clickEl.innerText;


        Dom.setStyle(dragEl, "color", Dom.getStyle(clickEl, "color"));
        Dom.setStyle(dragEl, "backgroundColor", Dom.getStyle(clickEl, "backgroundColor"));
        Dom.setStyle(dragEl, "border", "2px solid gray");
    },

    endDrag: function(e) {

        var srcEl = this.getEl();
        var proxy = this.getDragEl();

        // Show the proxy element and animate it to the src element's location
        Dom.setStyle(proxy, "visibility", "");
        var a = new YAHOO.util.Motion( 
            proxy, { 
                points: { 
                    to: Dom.getXY(srcEl)
                }
            }, 
            0.2, 
            YAHOO.util.Easing.easeOut 
        )
        var proxyid = proxy.id;
        var thisid = this.id;

        // Hide the proxy and show the source element when finished with the animation
        a.onComplete.subscribe(function() {
                Dom.setStyle(proxyid, "visibility", "hidden");
                Dom.setStyle(thisid, "visibility", "");
            });
        a.animate();
    },

    onDragDrop: function(e, id) {

        // If there is one drop interaction, the li was dropped either on the list,
        // or it was dropped on the current location of the source element.
        if (DDM.interactionInfo.drop.length === 1) {

            // The position of the cursor at the time of the drop (YAHOO.util.Point)
            var pt = DDM.interactionInfo.point; 

            // The region occupied by the source element at the time of the drop
            var region = DDM.interactionInfo.sourceRegion; 

            // Check to see if we are over the source element's location.  We will
            // append to the bottom of the list once we are sure it was a drop in
            // the negative space (the area of the list without any list items)
            if (!region.intersect(pt)) {
                var destEl = Dom.get(id);
                var destDD = DDM.getDDById(id);
                destEl.appendChild(this.getEl());
                destDD.isEmpty = false;
                DDM.refreshCache();
            }

        }
    },

    onDrag: function(e) {

        // Keep track of the direction of the drag for use during onDragOver
        var y = Event.getPageY(e);

        if (y < this.lastY) {
            this.goingUp = true;
        } else if (y > this.lastY) {
            this.goingUp = false;
        }

        this.lastY = y;
    },

    onDragOver: function(e, id) {
    
        var srcEl = this.getEl();
        var destEl = Dom.get(id);

        // We are only concerned with list items, we ignore the dragover
        // notifications for the list.
        if (destEl.nodeName.toLowerCase() == "li") {
            var orig_p = srcEl.parentNode;
            var p = destEl.parentNode;

            if (this.goingUp) {
                p.insertBefore(srcEl, destEl); // insert above
            } else {
                p.insertBefore(srcEl, destEl.nextSibling); // insert below
            }

            DDM.refreshCache();
        }
    }
});

Event.onDOMReady(YAHOO.example.DDApp.init, YAHOO.example.DDApp, true);

})();


</script>
