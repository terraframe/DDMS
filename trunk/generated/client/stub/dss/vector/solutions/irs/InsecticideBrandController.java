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
package dss.vector.solutions.irs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class InsecticideBrandController extends InsecticideBrandControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/InsecticideBrand/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = -1928367294;

  public InsecticideBrandController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(InsecticideBrandDTO dto) throws java.io.IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(InsecticideBrandDTO dto) throws java.io.IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void create(InsecticideBrandDTO dto) throws java.io.IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failCreate(dto);
      }
    }
  }

  public void failCreate(InsecticideBrandDTO dto) throws java.io.IOException, ServletException
  {
    this.newInstance(dto);
  }

  public void delete(InsecticideBrandDTO dto) throws java.io.IOException, ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failDelete(dto);
      }
    }
  }

  public void failDelete(InsecticideBrandDTO dto) throws java.io.IOException, ServletException
  {
    req.setAttribute("activeIngredient", AttributeUtil.getValue(InsecticideBrandDTO.ACTIVEINGREDIENT, dto));
    req.setAttribute("_concentrationQualifier", InsecticideBrandConcentrationQualifierDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("disease", dss.vector.solutions.general.DiseaseDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("_insecticideUse", InsecticideBrandUseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("productName", AttributeUtil.getValue(InsecticideBrandDTO.PRODUCTNAME, dto));
    req.setAttribute("_unitQualifier", InsecticideBrandUnitQualifierDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("useDetail", AttributeUtil.getValue(InsecticideBrandDTO.USEDETAIL, dto));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void edit(String id) throws java.io.IOException, ServletException
  {
    try
    {
      InsecticideBrandDTO dto = InsecticideBrandDTO.lock(super.getClientRequest(), id);
      req.setAttribute("activeIngredient", AttributeUtil.getValue(InsecticideBrandDTO.ACTIVEINGREDIENT, dto));
      req.setAttribute("_concentrationQualifier", InsecticideBrandConcentrationQualifierDTO.allItems(super.getClientSession().getRequest()));
      req.setAttribute("disease", dss.vector.solutions.general.DiseaseDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("_insecticideUse", InsecticideBrandUseDTO.allItems(super.getClientSession().getRequest()));
      req.setAttribute("productName", AttributeUtil.getValue(InsecticideBrandDTO.PRODUCTNAME, dto));
      req.setAttribute("_unitQualifier", InsecticideBrandUnitQualifierDTO.allItems(super.getClientSession().getRequest()));
      req.setAttribute("useDetail", AttributeUtil.getValue(InsecticideBrandDTO.USEDETAIL, dto));
      req.setAttribute("item", dto);
      render("editComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failEdit(id);
      }
    }
  }

  public void failEdit(String id) throws java.io.IOException, ServletException
  {
    this.view(id);
  }

  public void newInstance() throws java.io.IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      InsecticideBrandDTO dto = new InsecticideBrandDTO(clientRequest);
      this.newInstance(dto);
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failNewInstance();
      }
    }
  }

  private void newInstance(InsecticideBrandDTO dto) throws IOException, ServletException
  {
    try
    {
      this.setupRequest(dto);
      req.setAttribute("item", dto);
      render("createComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failNewInstance();
      }
    }
  }

  public void failNewInstance() throws java.io.IOException, ServletException
  {
    this.viewAll();
  }

  public void update(InsecticideBrandDTO dto) throws java.io.IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failUpdate(dto);
      }
    }
  }

  public void failUpdate(InsecticideBrandDTO dto) throws java.io.IOException, ServletException
  {
    req.setAttribute("activeIngredient", AttributeUtil.getValue(InsecticideBrandDTO.ACTIVEINGREDIENT, dto));
    req.setAttribute("_concentrationQualifier", InsecticideBrandConcentrationQualifierDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("disease", dss.vector.solutions.general.DiseaseDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("_insecticideUse", InsecticideBrandUseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("productName", AttributeUtil.getValue(InsecticideBrandDTO.PRODUCTNAME, dto));
    req.setAttribute("_unitQualifier", InsecticideBrandUnitQualifierDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("useDetail", AttributeUtil.getValue(InsecticideBrandDTO.USEDETAIL, dto));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void view(String id) throws java.io.IOException, ServletException
  {
    try
    {
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.put("id", id);
      utility.checkURL(this.getClass().getSimpleName(), "view");

      ClientRequestIF clientRequest = super.getClientRequest();
      InsecticideBrandDTO dto = InsecticideBrandDTO.get(clientRequest, id);
      this.setupRequest(dto);

      req.setAttribute("item", dto);
      render("viewComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failView(id);
      }
    }
  }

  private void setupRequest(InsecticideBrandDTO dto)
  {
    req.setAttribute("activeIngredient", AttributeUtil.getValue(InsecticideBrandDTO.ACTIVEINGREDIENT, dto));
    req.setAttribute("productName", AttributeUtil.getValue(InsecticideBrandDTO.PRODUCTNAME, dto));
    req.setAttribute("useDetail", AttributeUtil.getValue(InsecticideBrandDTO.USEDETAIL, dto));
    req.setAttribute("_unitQualifier", InsecticideBrandUnitQualifierDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("_concentrationQualifier", InsecticideBrandConcentrationQualifierDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("_insecticideUse", InsecticideBrandUseDTO.allItems(super.getClientSession().getRequest()));
  }

  public void failView(String id) throws java.io.IOException, ServletException
  {
    this.viewAll();
  }

  public void viewAll() throws java.io.IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    InsecticideBrandQueryDTO query = InsecticideBrandDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, ServletException
  {
    this.req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws java.io.IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    InsecticideBrandQueryDTO query = InsecticideBrandDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws java.io.IOException, ServletException
  {
    this.viewAll();
  }
}
