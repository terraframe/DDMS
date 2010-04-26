<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript" src="js/disease/$diseaseName/navMenu.js"></script>
<div class="navContainer" style="min-width: 1152px;">
 <div id="mainNav" class="yuimenubar yuimenubarnav">
      <div class="bd">
          <ul class="first-of-type">
#set( $first = true)
#foreach($menuitem in $menu.getChildren().values())
#if ($first)
              <li class="yuimenubaritem first-of-type">
                  <a class="yuimenubaritemlabel" href="#" tabindex="-1">$menuitem.getLabel()</a>
              </li>	
#set ($first = false)
#else
              <li class="yuimenubaritem">
                  <a class="yuimenubaritemlabel" href="#" tabindex="-1">$menuitem.getLabel()</a>
              </li>
#end
#end
              <li class="yuimenubaritem">
                  <a class="yuimenubaritemlabel" href="com.runwaysdk.defaults.LoginController.logout.mojo" tabindex="-1"><fmt:message key="Log_Out"/></a>
              </li>
              <li class="yuimenubaritem">
                  <a class="yuimenubaritemlabel" href="about.jsp"  tabindex="-1"><fmt:message key="About"/></a>
              </li>
              <a href="javascript:window.print()" style="margin-left:10px;"  tabindex="-1"> 
                  <img src="./imgs/icons/printer.png">
              </a> 
          </ul>
      </div>
  </div>
</div>