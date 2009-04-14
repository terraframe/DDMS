<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<div class="navContainer" style="min-width: 1024px;">
<div id="mainNav" class="yuimenubar yuimenubarnav">
     <div class="bd">
         <ul class="first-of-type">
             <li class="yuimenubaritem first-of-type"><a class="yuimenubaritemlabel" href="#communication"><fmt:message key="Administration" /></a>

                 <div id="admin" class="yuimenu">
                     <div class="bd">
                         <ul>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#"><fmt:message key="Alerts"/></a></li>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#"><fmt:message key="User_Administration"/></a>
                                 <div id="pim" class="yuimenu">
                                     <div class="bd">
                                         <ul class="first-of-type">
                                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#"><fmt:message key="View_Users"/></a></li>
                                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#"><fmt:message key="Edit_Roles"/></a></li>
                                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#"><fmt:message key="Edit_Users"/></a></li>
                                         </ul>
                                     </div>
                                 </div>

                             </li>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#"><fmt:message key="Backup_Data"/></a></li>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="dss.vector.solutions.PropertyController.viewAll.mojo"><fmt:message key="Change_Settings"/></a></li>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="dss.vector.solutions.general.InsecticideController.viewAll.mojo"><fmt:message key="Manage_Insecticides"/></a></li>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="dss.vector.solutions.general.KnockDownTimePropertyController.search.mojo"><fmt:message key="Manage_Knock_Down_Properties"/></a></li>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="dss.vector.solutions.general.LethalTimePropertyController.search.mojo"><fmt:message key="Manage_Lethal_Properties"/></a></li>
                         </ul>
                     </div>
                 </div>

             </li>

             <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#"><fmt:message key="Intervention_Planning"/></a>

                 <div id="intervention_planning" class="yuimenu">
                     <div class="bd">
                         <ul>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#">Test</a></li>
                         </ul>
                     </div>
                 </div>

             </li>
             <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#"><fmt:message key="Entomology_Surveillance"/></a>

                 <div id="entomology" class="yuimenu">
                     <div class="bd">
                         <ul>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="dss.vector.solutions.entomology.MosquitoCollectionController.search.mojo"><fmt:message key="Search_Mosquito_Collections"/></a></li>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="dss.vector.solutions.entomology.MosquitoCollectionPointController.search.mojo"><fmt:message key="Mosquito_Collection_Points"/></a></li>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="dss.vector.solutions.entomology.QueryController.queryEntomology.mojo"><fmt:message key="Query_Entomology"/></a></li><!--
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="dss.vector.solutions.entomology.MosquitoCollectionController.viewAll.mojo"><fmt:message key="View_All_Mosquito_Collections"/></a></li>
                              --><li class="yuimenuitem"><a class="yuimenuitemlabel" href="#"><fmt:message key="Resistance_Monitoring"/></a>
                                 <div id="pim" class="yuimenu">
                                     <div class="bd">
                                         <h6 class="first-of-type"><fmt:message key="Bioassays"/></h6>
                                         <ul class="first-of-type">
                                              <li class="yuimenuitem"><a class="yuimenuitemlabel" href="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.viewAll.mojo"><fmt:message key="Adult_DDA"/></a></li>
                                              <li class="yuimenuitem"><a class="yuimenuitemlabel" href="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.viewAll.mojo"><fmt:message key="Larvae_DDA"/></a></li>
                                         </ul>
                                         <h6 class=""><fmt:message key="Efficacy_Assays"/></h6>
                                         <ul class="first-of-type">
                                              <li class="yuimenuitem"><a class="yuimenuitemlabel" href="dss.vector.solutions.entomology.assay.EfficacyAssayController.viewAll.mojo"><fmt:message key="View_All_EfficacyAssay"/></a></li>
                                              <li class="yuimenuitem"><a class="yuimenuitemlabel" href="dss.vector.solutions.entomology.assay.KnockDownAssayController.viewAll.mojo"><fmt:message key="Knock_Down_Assay"/></a></li>
                                          </ul>
                                     </div>
                                 </div>
                             </li>


                         </ul>
                     </div>
                 </div>

             </li>
             <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#"><fmt:message key="Case_Surveillance"/></a>

                 <div id="cases" class="yuimenu">
                     <div class="bd">
                         <ul>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="dss.vector.solutions.surveillance.AggregatedCaseController.search.mojo"><fmt:message key="Search_Aggregated_Cases"/></a></li>
                         </ul>
                     </div>

                 </div>

             </li>
             <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#"><fmt:message key="Intervention_Monitoring"/></a>

                 <div id="Intervention_Monitoring" class="yuimenu">
                     <div class="bd">
                         <ul>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#">Test</a></li>
                         </ul>
                     </div>

                 </div>

             </li>
             <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#"><fmt:message key="Malaria_Indicators"/></a>

                 <div id="malaria" class="yuimenu">
                     <div class="bd">
                         <ul>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#">Test</a></li>
                         </ul>
                     </div>

                 </div>

             </li>
               <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#"><fmt:message key="GIS"/></a>

                 <div id="gis" class="yuimenu">
                     <div class="bd">
                         <ul>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="dss.vector.solutions.geo.GeoEntityTypeController.viewHierarchyTree.mojo?rootGeoHierarchyId="><fmt:message key="View_GeoEntityTypes"/></a></li>
                             <li class="yuimenuitem"><a class="yuimenuitemlabel" href="dss.vector.solutions.geo.GeoEntityTreeController.displayTree.mojo?rootGeoEntityId="><fmt:message key="View_GeoEntities"/></a></li>
                         </ul>
                     </div>

                 </div>

             </li><!--

             <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#"><fmt:message key="Excel_Header"/></a>
               <div id="excel" class="yuimenu">
                 <div class="bd">
                   <ul>
                     <li class="yuimenuitem"><a class="yuimenuitemlabel" href="excelexport"><fmt:message key="Excel_Export_Nav"/></a></li>
                     <li class="yuimenuitem"><a class="yuimenuitemlabel" href="excelimport"><fmt:message key="Excel_Import_Nav"/></a></li>
                   </ul>
                 </div>
               </div>
             </li>


              --><li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="com.terraframe.mojo.defaults.LoginController.logout.mojo"><fmt:message key="Log_Out"/></a></li>
         </ul>
     </div>
 </div>
  </div>
