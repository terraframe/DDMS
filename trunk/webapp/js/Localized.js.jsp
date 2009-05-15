<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@page import="java.util.ResourceBundle"%>
<%@page import="org.apache.taglibs.standard.tag.common.fmt.BundleSupport"%>
<%@page import="java.util.Enumeration"%>

/**
 * Constants used for localization in javascript.
 */
MDSS.Localized = {
<%
   //Nifty automatic localizer!
   ResourceBundle rb = BundleSupport.getLocalizationContext(pageContext).getResourceBundle();
   for( Enumeration en = rb.getKeys(); en.hasMoreElements(); )
   {
       String key = (String)en.nextElement();
          out.println( "'" + key + "' : '" + rb.getString(key) + "',");
   }
%>

  // tree widget options (per node)
  Tree: {
    Create: '<fmt:message key="Tree_Create"/>',
    Edit: '<fmt:message key="Tree_Edit"/>',
    Delete: '<fmt:message key="Tree_Delete"/>',
    Select: '<fmt:message key="Tree_Select"/>'
  },

  // Basic choices
  Choice: {
    Yes: '<fmt:message key="Choice_Yes"/>',
    No: '<fmt:message key="Choice_No"/>'
  },

  // Delete GeoEntity/GeoHierarchy choices
  Delete: {
    Entity: '<fmt:message key="Delete_Entity" />',
    Universal: '<fmt:message key="Delete_Universal" />',
    Relationship: '<fmt:message key="Delete_Relationship" />'
  },

  Query:
  {
    Start_Date: '<fmt:message key="Query_Start_Date" />',
    End_Date: '<fmt:message key="Query_End_Date" />',
    Map: '<fmt:message key="Query_Map" />',
    Run: '<fmt:message key="Query_Run" />',
    Save: '<fmt:message key="Query_Save" />',
    Load: '<fmt:message key="Query_Load" />',
    Refresh: '<fmt:message key="Query_Refresh" />'
  },

  Toggle:
  {
    Show: '<fmt:message key="Toggle_Show" />',
    Hide: '<fmt:message key="Toggle_Hide" />'
  },

  Select_Universal_Type: '<fmt:message key="Select_Universal_Type" />',

  New_Universal_Located_In: '<fmt:message key="New_Universal_Located_In" />',

  Search_Results: '<fmt:message key="Search_Results" />',

  Add: '<fmt:message key="Add" />',

  Submit: '<fmt:message key="Submit" />',

  Cancel: '<fmt:message key="Cancel" />',

  Update: '<fmt:message key="Update" />',

  Available_Layers : '<fmt:message key="Available_Layers" />',

  Defined_Layers: '<fmt:message key="Defined_Layers" />',

  Thematic:
  {
    Layer: '<fmt:message key="Thematic_Layer" />',
    Edit_Default_Style: '<fmt:message key="Thematic_Edit_Default_Style" />',
    Edit_Variable_Styles: '<fmt:message key="Thematic_Edit_Variable_Styles" />'
  },

  Target_Search: '<fmt:message key="Target_Search" />',

  Age_Group: '<fmt:message key="Age_Group" />',
  All_Ages: '<fmt:message key="All_Ages" />',

  Remove_Column: '<fmt:message key="Remove_Column" />',

  Aggregated_Case: '<fmt:message key="Aggregated_Case" />',

  Treatment_out_of_Stock: '<fmt:message key="Treatment_out_of_Stock"/>',

  Facility_referred: '<fmt:message key="Facility_referred"/>',

  Diagnostic_methods: '<fmt:message key="Diagnostic_methods"/>',

  Treatment_methods: '<fmt:message key="Treatment_methods"/>',

  Treatments: '<fmt:message key="Treatments"/>'
};



MDSS.localize = function(key){
  var s = MDSS.Localized[key];
  if( !s ) return(  "???" + key + "???"  );
  return s;
}