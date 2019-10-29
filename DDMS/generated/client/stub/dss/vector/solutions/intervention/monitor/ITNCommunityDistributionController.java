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
package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Currency;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class ITNCommunityDistributionController extends ITNCommunityDistributionControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/ITNCommunityDistribution/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1252612170678L;

  public ITNCommunityDistributionController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    ITNCommunityDistributionViewQueryDTO query = ITNCommunityDistributionViewDTO.getPage(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void viewAll() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      ITNCommunityDistributionViewQueryDTO query = ITNCommunityDistributionViewDTO.getPage(clientRequest, null, true, 20, 1);
      req.setAttribute("query", query);
      render("viewAllComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failViewAll();
      }
    }
  }

  public void failViewAll() throws IOException, ServletException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      view(ITNCommunityDistributionDTO.getView(this.getClientRequest(), id));
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

  private void view(ITNCommunityDistributionViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.prepareRelationships(dto);
    this.getGeoEntities(dto);

    req.setAttribute("currency", Currency.getInstance(req.getLocale()).getCurrencyCode());
    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  private void getGeoEntities(ITNCommunityDistributionViewDTO dto)
  {
    req.setAttribute("distributionLocation", AttributeUtil.getGeoEntityFromGeoId(ITNCommunityDistributionViewDTO.DISTRIBUTIONLOCATION, dto));
    req.setAttribute("householdAddress", AttributeUtil.getGeoEntityFromGeoId(ITNCommunityDistributionViewDTO.HOUSEHOLDADDRESS, dto));
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void newInstance() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();

      // Ensure the user has permissions to create a new ITN Community
      // Distribution
      new ITNCommunityDistributionDTO(clientRequest);

      ITNCommunityDistributionViewDTO dto = new ITNCommunityDistributionViewDTO(clientRequest);

      this.prepareRelationships(dto);
      req.setAttribute("item", dto);
      render("createComponent.jsp");
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

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void create(ITNCommunityDistributionViewDTO dto, ITNCommunityNetDTO[] nets, ITNCommunityTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(nets, targetGroups);

      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(dto, nets, targetGroups);
      }
    }
  }

  public void failCreate(ITNCommunityDistributionViewDTO dto, ITNCommunityNetDTO[] nets, ITNCommunityTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    this.prepareRelationships(targetGroups, nets);

    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      ITNCommunityDistributionViewDTO dto = ITNCommunityDistributionDTO.lockView(super.getClientRequest(), id);

      this.prepareRelationships(dto);
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

  public void update(ITNCommunityDistributionViewDTO dto, ITNCommunityNetDTO[] nets, ITNCommunityTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(nets, targetGroups);
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failUpdate(dto, nets, targetGroups);
      }
    }
  }

  public void failUpdate(ITNCommunityDistributionViewDTO dto, ITNCommunityNetDTO[] nets, ITNCommunityTargetGroupDTO[] targetGroups) throws IOException, ServletException
  {
    this.prepareRelationships(targetGroups, nets);

    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void cancel(ITNCommunityDistributionViewDTO dto) throws IOException, ServletException
  {
    try
    {
      this.view(ITNCommunityDistributionDTO.unlockView(this.getClientRequest(), dto.getConcreteId()));
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

  public void failCancel(ITNCommunityDistributionViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getConcreteId());
  }

  public void delete(ITNCommunityDistributionViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.deleteConcrete();
      this.viewAll();
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

  public void failDelete(ITNCommunityDistributionViewDTO dto) throws IOException, ServletException
  {
    this.prepareRelationships(dto);

    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  private void prepareRelationships(ITNCommunityDistributionViewDTO dto)
  {
    prepareRelationships(dto.getITNCommunityTargetGroups(), dto.getITNCommunityNets());
  }

  private void prepareRelationships(ITNCommunityTargetGroupDTO[] targetGroups, ITNCommunityNetDTO[] nets)
  {
    req.setAttribute("targetGroups", Arrays.asList(targetGroups));
    req.setAttribute("nets", Arrays.asList(nets));
  }
}
