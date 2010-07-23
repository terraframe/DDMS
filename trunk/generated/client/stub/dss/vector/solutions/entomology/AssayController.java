package dss.vector.solutions.entomology;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.AttributeReadPermissionExceptionDTO;

import dss.vector.solutions.RequiredCollectionProblemDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class AssayController extends AssayControllerBase implements Reloadable
{
  private static final long  serialVersionUID   = 1425826077;

  public static final String JSP_DIR            = "WEB-INF/dss/vector/solutions/entomology/Assays/";

  public static final String LAYOUT             = "/layout.jsp";

  public static final String INFECTION          = "infection";

  public static final String INFECTION_GRID     = "infectionGrid";

  public static final String INFECTION_SEX      = "sex";

  public static final String INFECTION_INFECTED = "infected";

  public static final String POOLED             = "pooled";

  public static final String POOLED_GRID        = "pooledGrid";

  public static final String POOLED_SEX         = "pooledSex";

  public static final String POOLED_INFECTED    = "pooledInfected";

  public static final String BIOCHEMICAL        = "biochemical";

  public static final String BIOCHEMICAL_GRID   = "biochemicalGrid";

  public static final String MOLECULAR          = "molecular";

  public static final String MOLECULAR_GRID     = "molecularGrid";

  public static final String DIAGNOSTIC         = "diagnostic";

  public static final String DIAGNOSTIC_GRID    = "diagnosticGrid";

  public static final String TIME_RESPONSE      = "timeResponse";

  public static final String TIME_RESPONSE_GRID = "timeResponseGrid";

  public AssayController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void getInfectionAssays(String collectionId) throws IOException, ServletException
  {
    if (collectionId == null)
    {
      collectionId = req.getParameter("collection_id");
    }

    String collectionInput = req.getParameter("collectionInput");

    try
    {
      validateParameters(collectionId, collectionInput);

      ClientRequestIF clientRequest = this.getClientRequest();

      MosquitoCollectionViewDTO view = null;

      if (collectionId != null)
      {
        view = MosquitoCollectionDTO.getView(clientRequest, collectionId);
      }
      else
      {
        view = MosquitoCollectionViewDTO.getViewByCollectionId(clientRequest, collectionInput);
      }

      this.setupInfection(clientRequest, view);
      this.setupPooledInfection(clientRequest, view);

      req.setAttribute("entity", view.getGeoEntity());
      req.setAttribute("collectionMethod", view.getCollectionMethod());
      req.setAttribute("item", view);

      render("viewInfectionComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failGetInfectionAssays(collectionId);
      }
    }
  }

  private void setupInfection(ClientRequestIF clientRequest, MosquitoCollectionViewDTO view)
  {
    InfectionAssayViewDTO dto = new InfectionAssayViewDTO(clientRequest);
    dto.setValue(InfectionAssayViewDTO.COLLECTION, view.getConcreteId());

    InfectionAssayViewDTO[] data = view.getInfectionAssays();
    String[] keys = this.getInfectionKeys();
    Map<String, ColumnSetup> map = this.getColumns(keys);

    req.setAttribute(INFECTION, dto);
    req.setAttribute(INFECTION_GRID, new ViewDataGrid(dto, map, keys, data));
    req.setAttribute(INFECTION_SEX, this.getSex(dto));
    req.setAttribute(INFECTION_INFECTED, this.getInfected(dto));
  }

  private void setupPooledInfection(ClientRequestIF clientRequest, MosquitoCollectionViewDTO view)
  {
    PooledInfectionAssayViewDTO dto = new PooledInfectionAssayViewDTO(clientRequest);
    dto.setValue(PooledInfectionAssayViewDTO.COLLECTION, view.getConcreteId());

    PooledInfectionAssayViewDTO[] data = view.getPooledInfectionAssays();
    String[] keys = this.getPooledKeys();
    Map<String, ColumnSetup> map = this.getColumns(keys);

    req.setAttribute(POOLED, dto);
    req.setAttribute(POOLED_GRID, new ViewDataGrid(dto, map, keys, data));
    req.setAttribute(POOLED_SEX, this.getSex(dto));
    req.setAttribute(POOLED_INFECTED, this.getInfected(dto));
  }

  private Boolean getInfected(InfectionAssayIF assay)
  {
    try
    {
      Boolean infected = assay.getInfected();

      if (infected != null)
      {
        return infected;
      }

      return false;
    }
    catch (AttributeReadPermissionExceptionDTO e)
    {
      return false;
    }
  }

  private TermDTO getSex(InfectionAssayIF assay)
  {
    try
    {
      return assay.getSex();
    }
    catch (AttributeReadPermissionExceptionDTO e)
    {
      return null;
    }
  }

  private String[] getPooledKeys()
  {
    String[] keys = new String[] { PooledInfectionAssayViewDTO.CONCRETEID, PooledInfectionAssayViewDTO.COLLECTION, PooledInfectionAssayViewDTO.POOLID, PooledInfectionAssayViewDTO.SPECIES, PooledInfectionAssayViewDTO.IDENTMETHOD, PooledInfectionAssayViewDTO.SEX, PooledInfectionAssayViewDTO.PARASITE, PooledInfectionAssayViewDTO.TESTMETHOD, PooledInfectionAssayViewDTO.INFECTED, PooledInfectionAssayViewDTO.MOSQUITOSTESTED, PooledInfectionAssayViewDTO.POOLSTESTED,
        PooledInfectionAssayViewDTO.NUMBERPOSITIVE };

    this.upperFirstCharacter(keys);

    return keys;
  }

  private Map<String, ColumnSetup> getColumns(String[] keys)
  {
    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();

    for (int i = 0; i < keys.length; i++)
    {
      ColumnSetup setup = ( i < 2 ? new ColumnSetup(true, false) : new ColumnSetup(false, true) );

      map.put(keys[i], setup);
    }

    return map;
  }

  private String[] getInfectionKeys()
  {
    String[] keys = new String[] { InfectionAssayViewDTO.CONCRETEID, InfectionAssayViewDTO.COLLECTION, InfectionAssayViewDTO.MOSQUITOID, InfectionAssayViewDTO.SPECIES, InfectionAssayViewDTO.IDENTMETHOD, InfectionAssayViewDTO.SEX, InfectionAssayViewDTO.PARASITE, InfectionAssayViewDTO.TESTMETHOD, InfectionAssayViewDTO.INFECTED, InfectionAssayViewDTO.NUMBERTESTED, InfectionAssayViewDTO.NUMBERPOSITIVE };

    this.upperFirstCharacter(keys);

    return keys;
  }

  private void upperFirstCharacter(String[] array)
  {
    for (int i = 0; i < array.length; i++)
    {
      array[i] = CommonGenerationUtil.upperFirstCharacter(array[i]);
    }
  }

  @Override
  public void failGetInfectionAssays(String collectionId) throws IOException, ServletException
  {
    this.searchInfectionAssay();
  }

  @Override
  public void searchInfectionAssay() throws IOException, ServletException
  {
    req.setAttribute("item", new InfectionAssayViewDTO(this.getClientRequest()));
    render("searchInfectionComponent.jsp");
  }

  @Override
  public void searchMechanismAssay() throws IOException, ServletException
  {
    req.setAttribute("item", new BiochemicalAssayViewDTO(this.getClientRequest()));
    render("searchMechanismComponent.jsp");
  }

  @Override
  public void getMechanismAssays(String collectionId) throws IOException, ServletException
  {
    if (collectionId == null)
    {
      collectionId = req.getParameter("collection_id");
    }

    String collectionInput = req.getParameter("collectionInput");

    try
    {
      validateParameters(collectionId, collectionInput);

      ClientRequestIF clientRequest = this.getClientRequest();

      MosquitoCollectionViewDTO view = null;

      if (collectionId != null)
      {
        view = MosquitoCollectionDTO.getView(clientRequest, collectionId);
      }
      else
      {
        view = MosquitoCollectionViewDTO.getViewByCollectionId(clientRequest, collectionInput);
      }

      this.setupBiochemical(clientRequest, view);
      this.setupMolecular(clientRequest, view);

      req.setAttribute("entity", view.getGeoEntity());
      req.setAttribute("collectionMethod", view.getCollectionMethod());
      req.setAttribute("item", view);

      render("viewMechanismComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failGetMechanismAssays(collectionId);
      }
    }
  }

  @Override
  public void failGetMechanismAssays(String collectionId) throws IOException, ServletException
  {
    this.searchMechanismAssay();
  }

  @Override
  public void searchResistanceAssays() throws IOException, ServletException
  {
    req.setAttribute("diagnostic", new DiagnosticAssayViewDTO(this.getClientRequest()));
    render("searchResistanceComponent.jsp");
  }

  @Override
  public void getResistanceAssays(String collectionId) throws IOException, ServletException
  {
    String collectionInput = req.getParameter("collectionInput");

    if (collectionId == null)
    {
      collectionId = req.getParameter("collection_id");
    }

    try
    {
      validateParameters(collectionId, collectionInput);

      ClientRequestIF clientRequest = this.getClientRequest();

      MosquitoCollectionViewDTO view = null;

      if (collectionId != null)
      {
        view = MosquitoCollectionDTO.getView(clientRequest, collectionId);
      }
      else
      {
        view = MosquitoCollectionViewDTO.getViewByCollectionId(clientRequest, collectionInput);
      }

      this.setupDiagnostic(clientRequest, view);
      this.setupTimeResponse(clientRequest, view);

      req.setAttribute("entity", view.getGeoEntity());
      req.setAttribute("collectionMethod", view.getCollectionMethod());
      req.setAttribute("item", view);

      render("viewResistanceComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failGetResistanceAssays(collectionId);
      }
    }
  }

  @Override
  public void failGetResistanceAssays(String collectionId) throws IOException, ServletException
  {
    this.searchResistanceAssays();
  }

  private void setupBiochemical(ClientRequestIF clientRequest, MosquitoCollectionViewDTO view)
  {
    BiochemicalAssayViewDTO dto = new BiochemicalAssayViewDTO(clientRequest);
    dto.setValue(InfectionAssayViewDTO.COLLECTION, view.getConcreteId());

    BiochemicalAssayViewDTO[] data = view.getBiochemicalAssays();
    String[] keys = this.getBiochemicalKeys();
    Map<String, ColumnSetup> map = this.getColumns(keys);

    req.setAttribute(BIOCHEMICAL, dto);
    req.setAttribute(BIOCHEMICAL_GRID, new ViewDataGrid(dto, map, keys, data));
  }

  private String[] getBiochemicalKeys()
  {
    String[] keys = new String[] { BiochemicalAssayViewDTO.CONCRETEID, BiochemicalAssayViewDTO.COLLECTION, BiochemicalAssayViewDTO.MOSQUITOID, BiochemicalAssayViewDTO.SPECIES, BiochemicalAssayViewDTO.IDENTMETHOD, BiochemicalAssayViewDTO.SEX, BiochemicalAssayViewDTO.GENERATION, BiochemicalAssayViewDTO.ISOFEMALE, BiochemicalAssayViewDTO.ASSAY, BiochemicalAssayViewDTO.NUMBERTESTED, BiochemicalAssayViewDTO.NUMBERELEVATED };

    this.upperFirstCharacter(keys);

    return keys;
  }

  private void setupMolecular(ClientRequestIF clientRequest, MosquitoCollectionViewDTO view)
  {
    MolecularAssayViewDTO dto = new MolecularAssayViewDTO(clientRequest);
    dto.setValue(PooledInfectionAssayViewDTO.COLLECTION, view.getConcreteId());

    MolecularAssayViewDTO[] data = view.getMolecularAssays();
    String[] keys = this.getMolecularKeys();
    Map<String, ColumnSetup> map = this.getColumns(keys);

    req.setAttribute(MOLECULAR, dto);
    req.setAttribute(MOLECULAR_GRID, new ViewDataGrid(dto, map, keys, data));
  }

  private String[] getMolecularKeys()
  {
    String[] keys = new String[] { MolecularAssayViewDTO.CONCRETEID, MolecularAssayViewDTO.COLLECTION, MolecularAssayViewDTO.MOSQUITOID, MolecularAssayViewDTO.SPECIES, MolecularAssayViewDTO.IDENTMETHOD, MolecularAssayViewDTO.SEX, MolecularAssayViewDTO.GENERATION, MolecularAssayViewDTO.ISOFEMALE, MolecularAssayViewDTO.ASSAYMETHOD, MolecularAssayViewDTO.TARGET, MolecularAssayViewDTO.NUMBERRR, MolecularAssayViewDTO.NUMBERRS, MolecularAssayViewDTO.NUMBERSS };

    this.upperFirstCharacter(keys);

    return keys;
  }

  private void setupDiagnostic(ClientRequestIF clientRequest, MosquitoCollectionViewDTO view)
  {
    DiagnosticAssayViewDTO dto = new DiagnosticAssayViewDTO(clientRequest);
    dto.setValue(DiagnosticAssayViewDTO.COLLECTION, view.getConcreteId());

    String[] keys = this.getDiagnosticKeys();
    Map<String, ColumnSetup> map = this.getColumns(keys);
    DiagnosticAssayViewDTO[] data = view.getDiagnosticAssays();

    req.setAttribute(DIAGNOSTIC, dto);
    req.setAttribute(DIAGNOSTIC_GRID, new ViewDataGrid(dto, map, keys, data));
  }

  private String[] getDiagnosticKeys()
  {
    String[] keys = new String[] { DiagnosticAssayViewDTO.CONCRETEID, DiagnosticAssayViewDTO.COLLECTION, DiagnosticAssayViewDTO.ACTIVEINGREDIENT, DiagnosticAssayViewDTO.SPECIES, DiagnosticAssayViewDTO.LIFESTAGE, DiagnosticAssayViewDTO.SYNERGIST, DiagnosticAssayViewDTO.OUTCOME };

    this.upperFirstCharacter(keys);

    return keys;
  }

  private void setupTimeResponse(ClientRequestIF clientRequest, MosquitoCollectionViewDTO view)
  {
    TimeResponseAssayViewDTO dto = new TimeResponseAssayViewDTO(clientRequest);
    dto.setValue(TimeResponseAssayViewDTO.COLLECTION, view.getConcreteId());

    TimeResponseAssayViewDTO[] data = view.getTimeResponseAssays();
    String[] keys = this.getTimeResponseKeys();
    Map<String, ColumnSetup> map = this.getColumns(keys);

    req.setAttribute(TIME_RESPONSE, dto);
    req.setAttribute(TIME_RESPONSE_GRID, new ViewDataGrid(dto, map, keys, data));
  }

  private String[] getTimeResponseKeys()
  {
    String[] keys = new String[] { TimeResponseAssayViewDTO.CONCRETEID, TimeResponseAssayViewDTO.COLLECTION, TimeResponseAssayViewDTO.ASSAY, TimeResponseAssayViewDTO.ACTIVEINGREDIENT, TimeResponseAssayViewDTO.SPECIES, TimeResponseAssayViewDTO.LIFESTAGE, TimeResponseAssayViewDTO.SYNERGIST, TimeResponseAssayViewDTO.TESTSTRAINRESULT, TimeResponseAssayViewDTO.REFERENCESTRAINRESULT };

    this.upperFirstCharacter(keys);

    return keys;
  }

  private void validateParameters(String collectionId, String collectionInput)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if ( ( collectionId == null || collectionId.equals("") ) && ( collectionInput == null || collectionInput.equals("") ))
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredCollectionProblemDTO(clientRequest, req.getLocale()));
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }
}
