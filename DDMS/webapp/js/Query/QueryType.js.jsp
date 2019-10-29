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
