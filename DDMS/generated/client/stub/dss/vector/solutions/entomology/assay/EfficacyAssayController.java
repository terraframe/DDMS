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
package dss.vector.solutions.entomology.assay;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.geo.generated.SurfaceDTO;
import dss.vector.solutions.irs.InsecticideBrandViewDTO;
import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class EfficacyAssayController extends EfficacyAssayControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/entomology/assay/EfficacyAssay/";

  public static final String LAYOUT           = "/layout.jsp";

  public static final long   serialVersionUID = 1236363373105L;

  public EfficacyAssayController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void create(EfficacyAssayViewDTO dto) throws IOException, ServletException
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

  public void failCreate(EfficacyAssayViewDTO dto) throws IOException, ServletException
  {
    this.newInstance(dto);
  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    EfficacyAssayViewQueryDTO query = EfficacyAssayViewDTO.getPage(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void cancel(EfficacyAssayViewDTO dto) throws IOException, ServletException
  {
    try
    {
      this.view(EfficacyAssayDTO.unlockView(this.getClientRequest(), dto.getConcreteId()));
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

  public void failCancel(EfficacyAssayViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getConcreteId());
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      EfficacyAssayViewDTO dto = EfficacyAssayDTO.lockView(super.getClientRequest(), id);

      this.edit(dto);
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

  private void edit(EfficacyAssayViewDTO dto) throws IOException, ServletException
  {
    this.setupReferences(dto);
    this.setupRequest();
    req.setAttribute("item", dto);
    req.setAttribute("brands", Arrays.asList(InsecticideBrandViewDTO.getEfficacyAssayInsecticideBrands(dto.getRequest())));

    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void update(EfficacyAssayViewDTO dto) throws IOException, ServletException
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

  public void failUpdate(EfficacyAssayViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto);
  }

  public void view(String id) throws IOException, ServletException
  {
    this.view(EfficacyAssayDTO.getView(super.getClientRequest(), id));
  }

  public void view(EfficacyAssayViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.setupReferences(dto);
    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void delete(EfficacyAssayViewDTO dto) throws IOException, ServletException
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

  public void failDelete(EfficacyAssayViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();

    EfficacyAssayViewQueryDTO query = EfficacyAssayViewDTO.getPage(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void newInstance() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();

      // Ensure the user has permissions to create an Efficacy Assay
      new EfficacyAssayDTO(clientRequest);

      this.newInstance(new EfficacyAssayViewDTO(clientRequest));
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

  private void newInstance(EfficacyAssayViewDTO dto) throws IOException, ServletException
  {
    this.setupReferences(dto);
    this.setupRequest();
    req.setAttribute("item", dto);
    req.setAttribute("brands", Arrays.asList(InsecticideBrandViewDTO.getEfficacyAssayInsecticideBrands(dto.getRequest())));

    List<String> entityUniversals = Arrays.asList(new String[] { SurfaceDTO.CLASS });
    req.setAttribute("entityUniversals", entityUniversals);
    
    render("createComponent.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  @Override
  public void cloneAssay(String id) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      // Ensure the user has permissions to create an Efficacy Assay
      new EfficacyAssayDTO(request);

      this.cloneAssay(EfficacyAssayDTO.getView(request, id));
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCloneAssay(id);
      }
    }
  }

  @Override
  public void failCloneAssay(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void cloneAssay(EfficacyAssayViewDTO dto) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();

    EfficacyAssayViewDTO clone = new EfficacyAssayViewDTO(clientRequest);
    
    if (dto.isReadable(EfficacyAssayViewDTO.GEOID))
    {
      clone.setGeoId(dto.getGeoId());
    }
    
    if (dto.isReadable(EfficacyAssayViewDTO.SURFACETYPE))
    {
      clone.setSurfaceType(dto.getSurfaceType());
    }
    
    if (dto.isReadable(EfficacyAssayViewDTO.TESTDATE))
    {
      clone.setTestDate(dto.getTestDate());
    }
    
    if (dto.isReadable(EfficacyAssayViewDTO.TESTMETHOD))
    {
      clone.setTestMethod(dto.getTestMethod());
    }
    
    if (dto.isReadable(EfficacyAssayViewDTO.CONTROLTESTMORTALITY))
    {
      clone.setControlTestMortality(dto.getControlTestMortality());
    }
    
    if (dto.isReadable(EfficacyAssayViewDTO.SPECIE))
    {
      clone.setSpecie(dto.getSpecie());
    }
    
    if (dto.isReadable(EfficacyAssayViewDTO.COLONYNAME))
    {
      clone.setColonyName(dto.getColonyName());
    }
    
    if (dto.isReadable(EfficacyAssayViewDTO.TIMEONSURFACE))
    {
      clone.setTimeOnSurface(dto.getTimeOnSurface());
    }
    
    if (dto.isReadable(EfficacyAssayViewDTO.GRAVID))
    {
      clone.setGravid(dto.getGravid());
    }
    
    if (dto.isReadable(EfficacyAssayViewDTO.FED))
    {
      clone.setFed(dto.getFed());
    }
    
    if (dto.isReadable(EfficacyAssayViewDTO.INSECTICIDEBRAND))
    {
      clone.setInsecticideBrand(dto.getInsecticideBrand());
    }
    
    if (dto.isReadable(EfficacyAssayViewDTO.AGERANGE))
    {
      clone.getAgeRange().setStartPoint(dto.getAgeRange().getStartPoint());
      clone.getAgeRange().setEndPoint(dto.getAgeRange().getEndPoint());
    }
    
    if (dto.isReadable(EfficacyAssayViewDTO.SEX))
    {
      clone.setSex(dto.getSex());
    }
    
    if (dto.isReadable(EfficacyAssayViewDTO.QUANTITYTESTED))
    {
      clone.setQuantityTested(dto.getQuantityTested());
    }

    this.newInstance(clone);
  }

  private void setupReferences(EfficacyAssayViewDTO dto)
  {
    req.setAttribute("geoId", AttributeUtil.getGeoEntityFromGeoId(EfficacyAssayViewDTO.GEOID, dto));
    req.setAttribute("surfacePostion", AttributeUtil.getValue(EfficacyAssayViewDTO.SURFACEPOSTION, dto));
    req.setAttribute("sex", AttributeUtil.getValue(EfficacyAssayViewDTO.SEX, dto));
    req.setAttribute("specie", AttributeUtil.getValue(EfficacyAssayViewDTO.SPECIE, dto));
    req.setAttribute("testMethod", AttributeUtil.getValue(EfficacyAssayViewDTO.TESTMETHOD, dto));
  }

  private void setupRequest()
  {
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("surface", SurfaceDTO.CLASS);
  }

}
