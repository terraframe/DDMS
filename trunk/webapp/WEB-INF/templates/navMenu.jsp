<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>

<div id="navmenu" class="yuimenubar yuimenubarnav">
     <div class="bd">

         <ul class="first-of-type">
             <li class="yuimenubaritem first-of-type"><a class="yuimenubaritemlabel" href="#communication"><f:message key="Administration" /></a>

                 <div id="admin" class="yuimenu">
                     <div class="bd">
                         <ul>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#"><f:message key="Alerts"/></a></li>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#"><f:message key="User_Administration"/></a>                             
                                 <div id="pim" class="yuimenu">
                                     <div class="bd">
                                         <ul class="first-of-type">
                                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#"><f:message key="View_Users"/></a></li>
                                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#"><f:message key="Edit_Roles"/></a></li>
                                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#"><f:message key="Edit_Users"/></a></li>
                                         </ul>            
                                     </div>
                                 </div>                    
                             
                             </li>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#"><f:message key="Backup_Data"/></a></li>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#"><f:message key="Change_Settings"/></a></li>                             
                         </ul>
                     </div>
                 </div>      
             
             </li>

             <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#"><f:message key="Intervention_Planning"/></a>

                 <div id="intervention" class="yuimenu">
                     <div class="bd">                    
                         <ul>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#">Test</a></li>
                         </ul>
                     </div>
                 </div>                    
             
             </li>
             <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#"><f:message key="Entomology_Surveillance"/></a>

                 <div id="entomology" class="yuimenu">
                     <div class="bd">                    
                         <ul>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="mdss.entomology.MosquitoCollectionController.search.mojo"><f:message key="Search_Mosquito_Collections"/></a></li>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="mdss.entomology.MosquitoCollectionController.viewAll.mojo"><f:message key="View_All_Mosquito_Collections"/></a></li>                          
                         </ul>                    
                     </div>
                 </div>                                        
             
             </li>
             <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#"><f:message key="Case_Surveillance"/></a>

                 <div id="cases" class="yuimenu">
                     <div class="bd">                                        
                         <ul>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#">Test</a></li>
                         </ul>                    
                     </div>

                 </div>                                        
             
             </li>
             <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#"><f:message key="Intervention_Monitoring"/></a>

                 <div id="intervention" class="yuimenu">
                     <div class="bd">                                        
                         <ul>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#">Test</a></li>
                         </ul>                    
                     </div>

                 </div>                                        
             
             </li>
             <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#"><f:message key="Malaria_Indicators"/></a>

                 <div id="malaria" class="yuimenu">
                     <div class="bd">                                        
                         <ul>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#">Test</a></li>
                         </ul>                    
                     </div>

                 </div>                                        
             
             </li>
               <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#"><f:message key="GIS"/></a>

                 <div id="gis" class="yuimenu">
                     <div class="bd">                                        
                         <ul>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="mdss.test.GeoEntityController.viewAll.mojo"><f:message key="View_GeoEntites"/></a></li>
                         </ul>                    
                     </div>

                 </div>                                        
             
             </li>
              <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="com.terraframe.mojo.defaults.LoginController.logout.mojo"><f:message key="Log_Out"/></a></li>
         </ul>            
     </div>
 </div>
 
