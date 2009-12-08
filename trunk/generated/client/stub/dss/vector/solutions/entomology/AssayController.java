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

import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.surveillance.RequiredGeoIdProblemDTO;
import dss.vector.solutions.util.ColumnSetup;
import dss.vector.solutions.util.ErrorUtility;

public class AssayController extends AssayControllerBase implements Reloadable
{
  private static final long  serialVersionUID   = 1425826077;

  public static final String JSP_DIR            = "WEB-INF/dss/vector/solutions/entomology/Assays/";

  public static final String LAYOUT             = "/layout.jsp";

  public static final String INFECTION          = "infection";

  public static final String INFECTION_ROWS     = "infectionAssays";

  public static final String INFECTION_KEYS     = "keys";

  public static final String INFECTION_COLUMNS  = "columns";

  public static final String INFECTION_SEX      = "sex";

  public static final String INFECTION_INFECTED = "infected";

  public static final String POOLED             = "pooled";

  public static final String POOLED_ROWS        = "pooledAssays";

  public static final String POOLED_KEYS        = "pooledKeys";

  public static final String POOLED_COLUMNS     = "pooledColumns";

  public static final String POOLED_SEX         = "pooledSex";

  public static final String POOLED_INFECTED    = "pooledInfected";

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

    req.setAttribute(INFECTION, infection);
    req.setAttribute(INFECTION_ROWS, view.getInfectionAssays());
    req.setAttribute(INFECTION_KEYS, this.getInfectionKeys());
    req.setAttribute(INFECTION_COLUMNS, this.getInfectionColumns());
    req.setAttribute(INFECTION_SEX, this.getSex(infection));
    req.setAttribute(INFECTION_INFECTED, this.getInfected(infection));    
  }

  private void setupPooledInfection(ClientRequestIF clientRequest, MosquitoCollectionViewDTO view)
  {
    PooledInfectionAssayViewDTO pooled = new PooledInfectionAssayViewDTO(clientRequest);
    pooled.setValue(PooledInfectionAssayViewDTO.COLLECTION, view.getConcreteId());

    req.setAttribute(POOLED, pooled);
    req.setAttribute(POOLED_ROWS, view.getPooledInfectionAssays());
    req.setAttribute(POOLED_KEYS, this.getPooledKeys());
    req.setAttribute(POOLED_COLUMNS, this.getPooledColumns());
    req.setAttribute(POOLED_SEX, this.getSex(pooled));
    req.setAttribute(POOLED_INFECTED, this.getInfected(pooled));
  }

  private Boolean getInfected(InfectionAssayIF assay)
  {
    try
    {
      Boolean infected = assay.getInfected();
      
      if(infected != null)
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

  private Map<String, ColumnSetup> getPooledColumns()
  {
    String[] keys = this.getPooledKeys();

    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();

    for (int i = 0; i < keys.length; i++)
    {
      ColumnSetup setup = ( i < 2 ? new ColumnSetup(true, false) : new ColumnSetup(false, true) );

      map.put(keys[i], setup);
    }

    return map;
  }

  private String[] getPooledKeys()
  {
    String[] keys = new String[] { PooledInfectionAssayViewDTO.CONCRETEID, PooledInfectionAssayViewDTO.COLLECTION, PooledInfectionAssayViewDTO.POOLID, PooledInfectionAssayViewDTO.SPECIES, PooledInfectionAssayViewDTO.IDENTMETHOD, PooledInfectionAssayViewDTO.SEX, PooledInfectionAssayViewDTO.PARASITE,
        PooledInfectionAssayViewDTO.TESTMETHOD, PooledInfectionAssayViewDTO.INFECTED, PooledInfectionAssayViewDTO.MOSQUITOSTESTED, PooledInfectionAssayViewDTO.POOLSTESTED, PooledInfectionAssayViewDTO.NUMBERPOSITIVE };

    this.upperFirstCharacter(keys);

    return keys;
  }

  private Map<String, ColumnSetup> getInfectionColumns()
  {
    String[] keys = this.getInfectionKeys();

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
    req.setAttribute("item", new InfectionAssayDTO(this.getClientRequest()));
    render("searchInfectionComponent.jsp");
  }

  private void validateParameters(String collectionId)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (collectionId == null || collectionId.equals(""))
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredGeoIdProblemDTO(clientRequest, req.getLocale()));
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }
}
