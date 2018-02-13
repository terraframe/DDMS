<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.runwaysdk.ClientSession"%>
<%@ page import="com.runwaysdk.constants.ClientConstants"%>
<%@ page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@ page import="dss.vector.solutions.util.OrientationTypeDTO"%>
<%@ page import="dss.vector.solutions.util.LocalizationFacadeDTO"%>
<div class="navContainer" style="min-width: 1152px;">
 <div id="mainNav" class="yuimenubar yuimenubarnav">
  </div>
</div>
<script>
YAHOO.util.Event.onContentReady("mainNav", function () {
 
	//	Instantiate a Menu:  The first argument passed to the constructor
	//	is the id for the Menu element to be created, the second is an 
	//	object literal of configuration properties.
 
	//	Add items to the Menu instance by passing an array of object literals 
	//	(each of which represents a set of YAHOO.widget.MenuItem 
	//	configuration properties) to the "addItems" method.
 
    var aMenuItems = [${menus[diseaseName]}];
    
    var oMenuBar = new YAHOO.widget.MenuBar("menuBar",{
        itemdata : aMenuItems,
        <%
        ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
        OrientationTypeDTO orientation = LocalizationFacadeDTO.getSessionLocaleOrientation(clientRequest);

        if (orientation.equals(OrientationTypeDTO.RTL)) { 
        %>   
          submenualignment: ["tr", "br"],
        <%
        }
        %>
		autosubmenudisplay: true,
		hidedelay: 750,
		lazyload: true });
 
 
	var ua = YAHOO.env.ua,
	oAnim;  // Animation instance


	/*
	 * "beforeshow" event handler for each submenu of the MenuBar instance, used to
	 * setup certain style properties before the menu is animated.
	 */

	function onSubmenuBeforeShow(p_sType, p_sArgs) {

		var oBody,
		oElement,
		oShadow,
		oUL;


		if (this.parent) {

			oElement = this.element;

			/*
			 * Get a reference to the Menu's shadow element and set its "height"
			 * property to "0px" to syncronize it with the height of the Menu
			 * instance.
			 */

			oShadow = oElement.lastChild;
			oShadow.style.height = "0px";


			/*
			 * Stop the Animation instance if it is currently animating a Menu.
			 */

			if (oAnim && oAnim.isAnimated()) {

				oAnim.stop();
				oAnim = null;

			}


			/*
			 * Set the body element's "overflow" property to "hidden" to clip the
			 * display of its negatively positioned <ul> element.
			 */

			oBody = this.body;


			// Check if the menu is a submenu of a submenu.

			if (this.parent &&
					!(this.parent instanceof YAHOO.widget.MenuBarItem)) {


				/*
				 * There is a bug in gecko-based browsers and Opera where an element
				 * whose "position" property is set to "absolute" and "overflow"
				 * property is set to "hidden" will not render at the correct width
				 * when its offsetParent's "position" property is also set to
				 * "absolute." It is possible to work around this bug by specifying
				 * a value for the width property in addition to overflow.
				 */

				if (ua.gecko || ua.opera) {

					oBody.style.width = oBody.clientWidth + "px";

				}


				/*
				 * Set a width on the submenu to prevent its width from growing when
				 * the animation is complete.
				 */

				if (ua.ie == 7) {

					oElement.style.width = oElement.clientWidth + "px";

				}

			}


			oBody.style.overflow = "hidden";


			/*
			 * Set the <ul> element's "marginTop" property to a negative value so
			 * that the Menu's height collapses.
			 */

			oUL = oBody.getElementsByTagName("ul")[0];

			oUL.style.marginTop = ("-" + oUL.offsetHeight + "px");

		}

	}


	/*
	 * "tween" event handler for the Anim instance, used to syncronize the size and
	 * position of the Menu instance's shadow and iframe shim (if it exists) with
	 * its changing height.
	 */

	function onTween(p_sType, p_aArgs, p_oShadow) {

		if (this.cfg.getProperty("iframe")) {

			this.syncIframe();

		}

		if (p_oShadow) {

			p_oShadow.style.height = this.element.offsetHeight + "px";

		}

	}


	/*
	 * "complete" event handler for the Anim instance, used to remove style
	 * properties that were animated so that the Menu instance can be displayed at
	 * its final height.
	 */

	function onAnimationComplete(p_sType, p_aArgs, p_oShadow) {

		var oBody = this.body,
		oUL = oBody.getElementsByTagName("ul")[0];

		if (p_oShadow) {

			p_oShadow.style.height = this.element.offsetHeight + "px";

		}


		oUL.style.marginTop = "";
		oBody.style.overflow = "";


		// Check if the menu is a submenu of a submenu.

		if (this.parent &&
				!(this.parent instanceof YAHOO.widget.MenuBarItem)) {


			// Clear widths set by the "beforeshow" event handler

			if (ua.gecko || ua.opera) {

				oBody.style.width = "";

			}

			if (ua.ie == 7) {

				this.element.style.width = "";

			}

		}

	}


	/*
	 * "show" event handler for each submenu of the MenuBar instance - used to kick
	 * off the animation of the <ul> element.
	 */

	function onSubmenuShow(p_sType, p_sArgs) {

		var oElement,
		oShadow,
		oUL;

		if (this.parent) {

			oElement = this.element;
			oShadow = oElement.lastChild;
			oUL = this.body.getElementsByTagName("ul")[0];


			/*
			 * Animate the <ul> element's "marginTop" style property to a value of
			 * 0.
			 */

			oAnim = new YAHOO.util.Anim(oUL,
					{ marginTop: { to: 0 } },
					.5, YAHOO.util.Easing.easeOut);


			oAnim.onStart.subscribe(function () {

				oShadow.style.height = "100%";

			});


			oAnim.animate();


			/*
			 * Subscribe to the Anim instance's "tween" event for IE to syncronize
			 * the size and position of a submenu's shadow and iframe shim (if it
			 * exists) with its changing height.
			 */

			if (YAHOO.env.ua.ie) {

				oShadow.style.height = oElement.offsetHeight + "px";


				/*
				 * Subscribe to the Anim instance's "tween" event, passing a
				 * reference Menu's shadow element and making the scope of the event
				 * listener the Menu instance.
				 */

				oAnim.onTween.subscribe(onTween, oShadow, this);

			}


			/*
			 * Subscribe to the Anim instance's "complete" event, passing a
			 * reference Menu's shadow element and making the scope of the event
			 * listener the Menu instance.
			 */

			oAnim.onComplete.subscribe(onAnimationComplete, oShadow, this);

		}

	}

	/*
	 * Subscribe to the "beforeShow" and "show" events for each submenu of the
	 * MenuBar instance.
	 */

	oMenuBar.subscribe("beforeShow", onSubmenuBeforeShow);
	oMenuBar.subscribe("show", onSubmenuShow);

	//	Since this Menu instance is built completely from script, call the 
	//	"render" method passing in the DOM element that it should be 
	//	appended to.
 
    oMenuBar.render("mainNav");
 
 
    // Show the Menu instance    
 
    oMenuBar.show();

});
 </script>