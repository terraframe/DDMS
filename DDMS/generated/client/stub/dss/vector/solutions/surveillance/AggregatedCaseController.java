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
package dss.vector.solutions.surveillance;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.geo.generated.HealthFacilityDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.FacadeDTO;
import dss.vector.solutions.util.FileDownloadUtil;
import dss.vector.solutions.util.RedirectUtility;

public class AggregatedCaseController extends AggregatedCaseControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/surveillance/AggregatedCase/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1239022146651L;

  public AggregatedCaseController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void create(AggregatedCaseViewDTO dto) throws IOException, ServletException
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

  public void failCreate(AggregatedCaseViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest(dto);

    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  private void setupRequest(AggregatedCaseViewDTO dto)
  {
    ClientRequestIF request = this.getClientSession().getRequest();
    AggregatedAgeGroupDTO[] ageGroups = AggregatedAgeGroupDTO.getAll(request);

    req.setAttribute("ageGroups", Arrays.asList(ageGroups));
    req.setAttribute("search", dto.getSearchDTO());
  }

  public void update(AggregatedCaseViewDTO dto) throws IOException, ServletException
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

  public void failUpdate(AggregatedCaseViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest(dto);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void cancel(AggregatedCaseViewDTO dto) throws IOException, ServletException
  {
    try
    {
      this.view(AggregatedCaseDTO.unlockView(this.getClientRequest(), dto.getConcreteId()));
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

  public void failCancel(AggregatedCaseViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getConcreteId());
  }

  public void delete(AggregatedCaseViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.deleteConcrete();
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

  public void failDelete(AggregatedCaseViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getConcreteId());
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      AggregatedCaseViewDTO view = AggregatedCaseDTO.lockView(this.getClientRequest(), id);

      this.setupRequest(view);
      this.setupGrids(view, false);
      req.setAttribute("item", view);
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

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      this.view(AggregatedCaseDTO.getView(super.getClientRequest(), id));
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

  public void view(AggregatedCaseViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.setupRequest(dto);
    this.setupGrids(dto, true);

    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.search();
  }

  @Override
  public void search() throws IOException, ServletException
  {
    this.search(new AggregatedCaseSearchViewDTO(this.getClientRequest()));
  }

  private void search(AggregatedCaseSearchViewDTO item) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientSession().getRequest();
    AggregatedAgeGroupDTO[] ageGroups = AggregatedAgeGroupDTO.getAll(request);
    List<String> entityUniversals = Arrays.asList(new String[] { HealthFacilityDTO.CLASS });

    req.setAttribute("ageGroups", Arrays.asList(ageGroups));
    req.setAttribute("periodType", PeriodTypeDTO.allItems(request));
    req.setAttribute("checkedType", PeriodTypeDTO.MONTH.getName());
    req.setAttribute("item", item);
    req.setAttribute("entityUniversals", entityUniversals);

    render("searchComponent.jsp");
  }

  @Override
  public void searchByView(AggregatedCaseSearchViewDTO view) throws IOException, ServletException
  {
    try
    {
      validateParameters(view.getGeoEntity(), view.getAgeGroup());

      AggregatedCaseViewDTO concrete = view.searchByView();

      if (concrete.hasConcreteId())
      {
        this.view(concrete);
      }
      else
      {
        // Ensure the user has permissions to create a Aggregated Case
        new AggregatedCaseDTO(this.getClientRequest());

        // Load all of the corresponding grid values
        this.setupRequest(concrete);
        this.setupGrids(view, false);

        req.setAttribute("item", concrete);

        render("editComponent.jsp");
      }
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failSearchByView(view);
      }
    }
  }

  private void setupGrids(AggregatedCaseViewDTO view, boolean readonly)
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    req.setAttribute("treatment", new CaseTreatmentGridBuilder(clientRequest, view, readonly).build());
    req.setAttribute("method", new CaseTreatmentMethodGridBuilder(clientRequest, view, readonly).build());
    req.setAttribute("stock", new CaseStockGridBuilder(clientRequest, view, readonly).build());
    req.setAttribute("referral", new CaseReferralGridBuilder(clientRequest, view, readonly).build());
    req.setAttribute("diagnostic", new CaseDiagnosticGridBuilder(clientRequest, view, readonly).build());
    req.setAttribute("stockReferral", new CaseStockReferralGridBuilder(clientRequest, view, readonly).build());
    req.setAttribute("diagnosisType", new CaseDiagnosisTypeGridBuilder(clientRequest, view, readonly).build());
    req.setAttribute("diseaseManifestation", new CaseDiseaseManifestationGridBuilder(clientRequest, view, readonly).build());
    req.setAttribute("patientType", new CasePatientTypeGridBuilder(clientRequest, view, readonly).build());
  }

  @Override
  public void failSearchByView(AggregatedCaseSearchViewDTO dto) throws IOException, ServletException
  {
    this.search(dto);
  }

  private void validateParameters(GeoEntityDTO geoEntityDTO, AggregatedAgeGroupDTO ageGroup)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (geoEntityDTO == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredGeoIdProblemDTO(clientRequest, req.getLocale()));
    }

    if (ageGroup == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredAgeGroupProblemDTO(clientRequest, req.getLocale()));
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

  @Override
  public void failSearch() throws IOException, ServletException
  {
    // This should never occur
    super.failSearch();
  }

  @Override
  public void exportExcelTemplate() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = this.getClientRequest();

      InputStream stream = FacadeDTO.exportAggregatedCases(clientRequest);

      FileDownloadUtil.writeXLS(resp, "AggregatedCaseExcelView", stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }
}
