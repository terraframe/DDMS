/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.geo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;
import dss.vector.solutions.util.yui.DataGrid;


public class GeoSynonymController extends GeoSynonymControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/geo/GeoSynonym/";

  public static final String LAYOUT           = "/layout.jsp";
  
  public GeoSynonymController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void cancel(dss.vector.solutions.geo.GeoSynonymArrayViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      view.unlock();
      this.search();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCancel(view);
      }
    }
  }
  
  public void failCancel(dss.vector.solutions.geo.GeoSynonymArrayViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    this.search();
  }
  
  public void create(dss.vector.solutions.geo.GeoSynonymArrayViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      view.apply();
      
      viewGeoSyn(view);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(view);
      }
    }
  }
  
  public void failCreate(dss.vector.solutions.geo.GeoSynonymArrayViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    this.search();
  }
  
  public void delete(dss.vector.solutions.geo.GeoSynonymArrayViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      view.delete();
      this.search();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failDelete(view);
      }
    }
  }
  
  public void failDelete(dss.vector.solutions.geo.GeoSynonymArrayViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewGeoSyn(view);
  }
  
  public void newInstance(GeoSynonymArrayViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      if (view == null)
      {
        ClientRequestIF clientRequest = super.getClientRequest();
        view = new GeoSynonymArrayViewDTO(clientRequest);
      }
      
      req.setAttribute("item", view);
      req.setAttribute("newInstance", true);
      addGrid(view);
      
      render("viewComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failNewInstance();
      }
    }
  }
  
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.search();
  }
  
  public void search() throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      // go back to household view after entering person
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.checkURL(this.getClass().getSimpleName(), "search");

      ClientRequestIF request = this.getClientRequest();
      GeoSynonymArrayViewDTO view = new GeoSynonymArrayViewDTO(request);
      search(view);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failSearch();
      }
    }
  }
  
  private void search(GeoSynonymArrayViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    GeoSynonymArrayViewQueryDTO query = GeoSynonymArrayViewDTO.getMostRecent(this.getClientRequest());
    
    req.setAttribute("query", query);
    req.setAttribute("item", view);

    render("searchComponent.jsp");
  }
  
  public void failSearch() throws java.io.IOException, javax.servlet.ServletException
  {
    // Uhh.. well, search is the GeoSynonym homepage. If that's not working then... redirect to the DDMS homepage?
    this.resp.sendRedirect("/");
  }
  
  public void searchByDTO(GeoSynonymArrayViewDTO view, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      isAscending = ( isAscending == null ? true : isAscending );
      pageSize = ( pageSize == null ? 15 : pageSize );
      pageNumber = ( pageNumber == null ? 1 : pageNumber );
  
      ClientRequestIF request = this.getClientRequest();
  
      GeoSynonymArrayViewQueryDTO query = GeoSynonymArrayViewDTO.searchByView(request, view, sortAttribute, isAscending, pageSize, pageNumber);
  
      req.setAttribute("query", query);
      req.setAttribute("item", view);
  
      render("searchComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failSearchByDTO(sortAttribute, isAscending, pageSize, pageNumber);
      }
    }
  }
  
  public void failSearchByDTO(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    this.search();
  }
  
  public void searchByParameters(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber, java.lang.String geoEntityName, java.lang.String geoId, java.lang.String geoTypeDisplayLabel, java.lang.String synonymNames) throws java.io.IOException, javax.servlet.ServletException
  {
    GeoSynonymArrayViewDTO view = new GeoSynonymArrayViewDTO(this.getClientRequest());
    view.setGeoEntityName(geoEntityName);
    view.setGeoTypeDisplayLabel(geoTypeDisplayLabel);
    view.setSynonymNames(synonymNames);

    this.searchByDTO(view, sortAttribute, isAscending, pageSize, pageNumber);
  }
  
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      viewGeoSyn(GeoSynonymArrayViewDTO.getGeoSynonym(this.getClientRequest(), id));
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failView(id);
      }
    }
  }
  
  private void viewGeoSyn(GeoSynonymArrayViewDTO dto) throws IOException, ServletException
  {
    dto.lock();
    
    req.setAttribute("item", dto);
    req.setAttribute("newInstance", false);
    addGrid(dto);
    
    render("viewComponent.jsp");
  }
  
  private void addGrid(GeoSynonymArrayViewDTO arrayView)
  {
    // Create GeoSynonymDTOs from the concatented ids in the view. The grid will work off the GeoSynonyms.
    List<GeoSynonymViewDTO> data = new ArrayList<GeoSynonymViewDTO>();
    
    String concats = arrayView.getSynonymIds();
    if (concats.length() > 0)
    {
      String[] ids = concats.split(",");
      
      int i = 0;
      while (i < ids.length)
      {
        String id = ids[i];
        
        GeoSynonymDTO dto = GeoSynonymDTO.get(this.getClientRequest(), id);
        
        GeoSynonymViewDTO view = new GeoSynonymViewDTO(this.getClientRequest());
        view.setSynonymName(dto.getEntityName());
        view.setGeoSynonymId(id);
        
        data.add(view);
        
        ++i;
      }
    }
    
    DataGrid grid = new GeoSynonymGridBuilder(this.getClientRequest(), false, data.toArray(new GeoSynonymViewDTO[data.size()])).build();
    
    req.setAttribute("grid", grid);
  }
  
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.search();
  }
}
