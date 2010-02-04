package dss.vector.solutions.entomology;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.business.generation.GenerationUtil;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.dataaccess.attributes.ClientReadAttributePermissionException;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.RequiredCollectionProblemDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.util.ColumnSetup;
import dss.vector.solutions.util.ErrorUtility;

public class AssayController extends AssayControllerBase implements Reloadable
{
  private static final long  serialVersionUID    = 1425826077;

  public static final String JSP_DIR             = "WEB-INF/dss/vector/solutions/entomology/Assays/";

  public static final String LAYOUT              = "/layout.jsp";

  public static final String INFECTION           = "infection";

  public static final String INFECTION_ROWS      = "infectionAssays";

  public static final String INFECTION_KEYS      = "keys";

  public static final String INFECTION_COLUMNS   = "columns";

  public static final String INFECTION_SEX       = "sex";

  public static final String INFECTION_INFECTED  = "infected";

  public static final String POOLED              = "pooled";

  public static final String POOLED_ROWS         = "pooledAssays";

  public static final String POOLED_KEYS         = "pooledKeys";

  public static final String POOLED_COLUMNS      = "pooledColumns";

  public static final String POOLED_SEX          = "pooledSex";

  public static final String POOLED_INFECTED     = "pooledInfected";

  public static final String BIOCHEMICAL         = "biochemical";

  public static final String BIOCHEMICAL_ROWS    = "biochemicalAssays";

  public static final String BIOCHEMICAL_KEYS    = "biochemicalKeys";

  public static final String BIOCHEMICAL_COLUMNS = "biochemicalColumns";

  public static final String MOLECULAR           = "molecular";

  public static final String MOLECULAR_ROWS      = "molecularAssays";

  public static final String MOLECULAR_KEYS      = "molecularKeys";

  public static final String MOLECULAR_COLUMNS   = "molecularColumns";

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

    try
    {
      validateParameters(collectionId);

      ClientRequestIF clientRequest = this.getClientRequest();

      MosquitoCollectionViewDTO view = MosquitoCollectionDTO.getView(clientRequest, collectionId);

      this.setupInfection(clientRequest, view);
      this.setupPooledInfection(clientRequest, view);

      req.setAttribute("entity", view.getGeoEntity());
      req.setAttribute("collectionMethod", view.getCollectionMethod());
      req.setAttribute("item", view);

      render("viewInfectionComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failGetInfectionAssays(collectionId);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failGetInfectionAssays(collectionId);
    }
  }

  private void setupInfection(ClientRequestIF clientRequest, MosquitoCollectionViewDTO view)
  {
    InfectionAssayViewDTO infection = new InfectionAssayViewDTO(clientRequest);
    infection.setValue(InfectionAssayViewDTO.COLLECTION, view.getConcreteId());
    String[] keys = this.getInfectionKeys();

    req.setAttribute(INFECTION, infection);
    req.setAttribute(INFECTION_ROWS, view.getInfectionAssays());
    req.setAttribute(INFECTION_KEYS, keys);
    req.setAttribute(INFECTION_COLUMNS, this.getColumns(keys));
    req.setAttribute(INFECTION_SEX, this.getSex(infection));
    req.setAttribute(INFECTION_INFECTED, this.getInfected(infection));
  }

  private void setupPooledInfection(ClientRequestIF clientRequest, MosquitoCollectionViewDTO view)
  {
    PooledInfectionAssayViewDTO pooled = new PooledInfectionAssayViewDTO(clientRequest);
    pooled.setValue(PooledInfectionAssayViewDTO.COLLECTION, view.getConcreteId());
    String[] keys = this.getPooledKeys();

    req.setAttribute(POOLED, pooled);
    req.setAttribute(POOLED_ROWS, view.getPooledInfectionAssays());
    req.setAttribute(POOLED_KEYS, keys);
    req.setAttribute(POOLED_COLUMNS, this.getColumns(keys));
    req.setAttribute(POOLED_SEX, this.getSex(pooled));
    req.setAttribute(POOLED_INFECTED, this.getInfected(pooled));
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
    catch (ClientReadAttributePermissionException e)
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
    catch (ClientReadAttributePermissionException e)
    {
      return null;
    }
  }

  private String[] getPooledKeys()
  {
    String[] keys = new String[] { PooledInfectionAssayViewDTO.CONCRETEID, PooledInfectionAssayViewDTO.COLLECTION, PooledInfectionAssayViewDTO.POOLID, PooledInfectionAssayViewDTO.SPECIES, PooledInfectionAssayViewDTO.IDENTMETHOD, PooledInfectionAssayViewDTO.SEX, PooledInfectionAssayViewDTO.PARASITE,
        PooledInfectionAssayViewDTO.TESTMETHOD, PooledInfectionAssayViewDTO.INFECTED, PooledInfectionAssayViewDTO.MOSQUITOSTESTED, PooledInfectionAssayViewDTO.POOLSTESTED, PooledInfectionAssayViewDTO.NUMBERPOSITIVE };

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
    String[] keys = new String[] { InfectionAssayViewDTO.CONCRETEID, InfectionAssayViewDTO.COLLECTION, InfectionAssayViewDTO.MOSQUITOID, InfectionAssayViewDTO.SPECIES, InfectionAssayViewDTO.IDENTMETHOD, InfectionAssayViewDTO.SEX, InfectionAssayViewDTO.PARASITE, InfectionAssayViewDTO.TESTMETHOD,
        InfectionAssayViewDTO.INFECTED, InfectionAssayViewDTO.NUMBERTESTED, InfectionAssayViewDTO.NUMBERPOSITIVE };

    this.upperFirstCharacter(keys);

    return keys;
  }

  private void upperFirstCharacter(String[] array)
  {
    for (int i = 0; i < array.length; i++)
    {
      array[i] = GenerationUtil.upperFirstCharacter(array[i]);
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

    try
    {
      validateParameters(collectionId);

      ClientRequestIF clientRequest = this.getClientRequest();

      MosquitoCollectionViewDTO view = MosquitoCollectionDTO.getView(clientRequest, collectionId);

      this.setupBiochemical(clientRequest, view);
      this.setupMolecular(clientRequest, view);

      req.setAttribute("entity", view.getGeoEntity());
      req.setAttribute("collectionMethod", view.getCollectionMethod());
      req.setAttribute("item", view);
      
      render("viewMechanismComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failGetMechanismAssays(collectionId);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failGetMechanismAssays(collectionId);
    }
  }
  
  @Override
  public void failGetMechanismAssays(String collectionId) throws IOException, ServletException
  {
    this.searchMechanismAssay();
  }

  private void setupBiochemical(ClientRequestIF clientRequest, MosquitoCollectionViewDTO view)
  {
    BiochemicalAssayViewDTO biochemical = new BiochemicalAssayViewDTO(clientRequest);
    biochemical.setValue(InfectionAssayViewDTO.COLLECTION, view.getConcreteId());
    String[] keys = this.getBiochemicalKeys();

    req.setAttribute(BIOCHEMICAL, biochemical);
    req.setAttribute(BIOCHEMICAL_ROWS, view.getBiochemicalAssays());
    req.setAttribute(BIOCHEMICAL_KEYS, keys);
    req.setAttribute(BIOCHEMICAL_COLUMNS, this.getColumns(keys));
  }

  private String[] getBiochemicalKeys()
  {
    String[] keys = new String[] { BiochemicalAssayViewDTO.CONCRETEID, BiochemicalAssayViewDTO.COLLECTION, BiochemicalAssayViewDTO.MOSQUITOID, BiochemicalAssayViewDTO.SPECIES, BiochemicalAssayViewDTO.IDENTMETHOD, BiochemicalAssayViewDTO.SEX, BiochemicalAssayViewDTO.GENERATION,
        BiochemicalAssayViewDTO.ISOFEMALE, BiochemicalAssayViewDTO.ASSAY, BiochemicalAssayViewDTO.NUMBERTESTED, BiochemicalAssayViewDTO.NUMBERELEVATED };

    this.upperFirstCharacter(keys);

    return keys;
  }

  private void setupMolecular(ClientRequestIF clientRequest, MosquitoCollectionViewDTO view)
  {
    MolecularAssayViewDTO molecular = new MolecularAssayViewDTO(clientRequest);
    molecular.setValue(PooledInfectionAssayViewDTO.COLLECTION, view.getConcreteId());
    String[] keys = this.getMolecularKeys();

    req.setAttribute(MOLECULAR, molecular);
    req.setAttribute(MOLECULAR_ROWS, view.getMolecularAssays());
    req.setAttribute(MOLECULAR_KEYS, keys);
    req.setAttribute(MOLECULAR_COLUMNS, this.getColumns(keys));
  }

  private String[] getMolecularKeys()
  {
    String[] keys = new String[] { MolecularAssayViewDTO.CONCRETEID, MolecularAssayViewDTO.COLLECTION, MolecularAssayViewDTO.MOSQUITOID, MolecularAssayViewDTO.SPECIES, MolecularAssayViewDTO.IDENTMETHOD, MolecularAssayViewDTO.SEX, MolecularAssayViewDTO.GENERATION, MolecularAssayViewDTO.ISOFEMALE,
        MolecularAssayViewDTO.ASSAYMETHOD, MolecularAssayViewDTO.TARGET, MolecularAssayViewDTO.NUMBERRR, MolecularAssayViewDTO.NUMBERRS, MolecularAssayViewDTO.NUMBERSS };

    this.upperFirstCharacter(keys);

    return keys;
  }

  private void validateParameters(String collectionId)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (collectionId == null || collectionId.equals(""))
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
