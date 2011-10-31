
<%@page import="java.util.Set"%>
<%@page import="dss.vector.solutions.query.GeoFieldIF"%>
<%@page import="java.util.List"%>

Mojo.Meta.newClass('MDSS.QueryType', {

  Extends: MDSS.QueryBaseNew,
  
  Instance : {
  
    initialize : function(selectableGroups, queryList)
    {
      this._item = Mojo.$.<%=(String) request.getAttribute("type")%>;
       
      this._dateAttribs = [<%=(String) request.getAttribute("dateAttributes")%>].filter(function(val) {return (val != null);});
      this._groupByClass = Mojo.$.<%=(String) request.getAttribute("type")%>;

      this._mainQueryClass = '<%=(String) request.getAttribute("type")%>';            
      this._commonQueryClasses = [];
      this._exclusionClasses = [];      
      this._geoEntityAttribs = [<%=(String) request.getAttribute("geoAttributes")%>];      
      this._showRatioSelectable = false;
      
      /**
       * Determine if the date range widget should be rendered
       */
      var renderDateRange = (this._dateAttribs.length > 0);
      
      this.$initialize(selectableGroups, queryList, renderDateRange);   
      
      this._xmlToValueQueryClass = '<%=(String) request.getAttribute("queryClass") %>';
            
      /**
       * Restrict the geo picker to the proper universals
       */
      <%
        GeoFieldIF field = (GeoFieldIF) request.getAttribute("geoField");
      %>
      var picker = this.getGeoPicker();
      picker.setPopulated(<%=field.getIsPopulationHierarchy()%>);
      picker.setPolitical(<%=field.getIsPoliticalHierarchy()%>);
      picker.setSprayTargetAllowed(<%=field.getIsSprayHierarchy()%>);
      picker.setUrban(<%=field.getIsUrbanHierarchy()%>);
      picker.setFilter(<%=field.getFilterType()%>);        
      
      <%
        String[] extraUniversals = field.getExtraUniversals();
      
        for(String extraUniversal : extraUniversals)
        {
          out.write("picker.addExtraUniversal('" + extraUniversal + "');");
        }
      %>
      
    },

    /**
     * Returns the root class to use when looking up term roots for term fields.
     */
    _getBrowserRootClass : function(attribute)
    {
      return attribute.getType();
    },    
  }
});
