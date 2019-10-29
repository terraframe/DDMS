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
package dss.vector.solutions.general;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class KnockDownTimePropertyController extends KnockDownTimePropertyControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/general/KnockDownTimeProperty/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1237411066733L;

  public KnockDownTimePropertyController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void delete(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();
      this.search();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failDelete(dto);
      }
    }
  }

  public void failDelete(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void update(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failUpdate(dto);
      }
    }
  }

  public void failUpdate(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      KnockDownTimePropertyDTO dto = KnockDownTimePropertyDTO.lock(super.getClientRequest(), id);
      req.setAttribute("item", dto);

      render("editComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failEdit(id);
      }
    }

  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    KnockDownTimePropertyDTO dto = new KnockDownTimePropertyDTO(clientRequest);
    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void create(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(dto);
      }
    }
  }

  public void failCreate(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }

  public void cancel(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.unlock();
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCancel(dto);
      }
    }
  }

  public void failCancel(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      this.view(KnockDownTimePropertyDTO.get(super.getClientRequest(), id));
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

  public void view(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    req.setAttribute("item", dto);

    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.search();
  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    KnockDownTimePropertyQueryDTO query = KnockDownTimePropertyDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    KnockDownTimePropertyQueryDTO query = KnockDownTimePropertyDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void search() throws IOException, ServletException
  {
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));

    render("searchComponent.jsp");
  }

  public void searchByInsecticide(String insecticideId) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = super.getClientRequest();
      InsecticideDTO insecticide = InsecticideDTO.get(request, insecticideId);

      try
      {
        this.view(KnockDownTimePropertyDTO.searchByInsecticide(request, insecticide));
      }
      catch (UndefinedKnockDownPropertyExceptionDTO e)
      {
        KnockDownTimePropertyDTO property = new KnockDownTimePropertyDTO(request);
        property.setInsecticide(insecticide);

        req.setAttribute("item", property);

        render("createComponent.jsp");
      }
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failSearchByInsecticide(insecticideId);
      }
    }
  }

  @Override
  public void failSearchByInsecticide(String insecticideId) throws IOException, ServletException
  {
    req.setAttribute("insecticideId", insecticideId);

    this.search();
  }
}
