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
package dss.vector.solutions.entomology;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.format.AbstractFormatFactory;
import com.runwaysdk.format.Format;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.MDSSUserDTO;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO;
import dss.vector.solutions.entomology.assay.CollectionAssayDTO;
import dss.vector.solutions.entomology.assay.KnockDownAssayQueryDTO;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQueryDTO;
import dss.vector.solutions.general.SystemURLDTO;
import dss.vector.solutions.geo.generated.CollectionSiteDTO;
import dss.vector.solutions.geo.generated.SentinelSiteDTO;
import dss.vector.solutions.irs.InsecticideBrandViewDTO;
import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class MosquitoCollectionController extends MosquitoCollectionControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/entomology/MosquitoCollection/";

  public static final String LAYOUT           = "/layout.jsp";

  public static final String ITEM             = "item";

  public static final String ROWS             = "rows";

  public static final String KEYS             = "keys";

  public static final String COLUMNS          = "columns";

  public static final String COLLECTION       = "collection";

  public static final long   serialVersionUID = -579744080;

  public MosquitoCollectionController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(MosquitoCollectionViewDTO dto) throws IOException, ServletException
  {
    this.create(MosquitoCollectionDTO.unlockView(this.getClientRequest(), dto.getConcreteId()));
  }

  public void failCancel(MosquitoCollectionViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  @Override
  public void update(MosquitoCollectionViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.view(dto.getConcreteId());
      }
    }
  }

  @Override
  public void setResistanceAssayComment(String collectionId, String comments) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      MosquitoCollectionViewDTO view = MosquitoCollectionDTO.getView(request, collectionId);
      view.setResistanceAssayComments(comments);
      view.apply();
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
    }
  }

  public void create(MosquitoCollectionViewDTO dto) throws IOException, ServletException
  {
    try
    {
      if (dto.getLifeStage().size() == 0)
      {
        dto.addLifeStage(LifeStageDTO.ADULT);
      }

      AdultDiscriminatingDoseAssayQueryDTO ada = this.getADA(dto);
      LarvaeDiscriminatingDoseAssayQueryDTO lda = this.getLDA(dto);
      KnockDownAssayQueryDTO kda = this.getKDA(dto);

      this.create(dto, ada, lda, kda);
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

  private void create(MosquitoCollectionViewDTO dto, AdultDiscriminatingDoseAssayQueryDTO ada, LarvaeDiscriminatingDoseAssayQueryDTO lda, KnockDownAssayQueryDTO kda) throws IOException, ServletException
  {
    this.setupReferences(dto);

    List<String> entityUniversals = Arrays.asList(new String[] { CollectionSiteDTO.CLASS, SentinelSiteDTO.CLASS });

    SubCollectionViewDTO view = new SubCollectionViewDTO(this.getClientRequest());
    view.setTotal(0);

    String[] keys = this.getKeys();
    Map<String, ColumnSetup> map = this.getColumns(dto);
    SubCollectionViewDTO[] data = dto.getSubCollections();

    ViewDataGrid grid = new ViewDataGrid(view, map, keys, data);

    req.setAttribute("grid", grid);
    req.setAttribute("ada", ada);
    req.setAttribute("lda", lda);
    req.setAttribute("kda", kda);
    req.setAttribute("item", dto);
    req.setAttribute("entityUniversals", entityUniversals);

    req.setAttribute("adaFlag", SystemURLDTO.hasReadPermissions(this.getClientRequest(), "dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.viewAll.mojo"));
    req.setAttribute("ldaFlag", SystemURLDTO.hasReadPermissions(this.getClientRequest(), "dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.viewAll.mojo"));
    req.setAttribute("kdaFlag", SystemURLDTO.hasReadPermissions(this.getClientRequest(), "dss.vector.solutions.entomology.assay.KnockDownAssayController.viewAll.mojo"));
    req.setAttribute("raFlag", SystemURLDTO.hasReadPermissions(this.getClientRequest(), "dss.vector.solutions.entomology.AssayController.searchResistanceAssays.mojo"));
    req.setAttribute("iaFlag", SystemURLDTO.hasReadPermissions(this.getClientRequest(), "dss.vector.solutions.entomology.AssayController.searchInfectionAssay.mojo"));
    req.setAttribute("maFlag", SystemURLDTO.hasReadPermissions(this.getClientRequest(), "dss.vector.solutions.entomology.AssayController.searchMechanismAssay.mojo"));

    render("viewComponent.jsp");
  }

  private Map<String, ColumnSetup> getColumns(MosquitoCollectionViewDTO dto)
  {
    List<LifeStageDTO> stage = dto.getLifeStage();

    boolean adult = stage.contains(LifeStageDTO.ADULT);
    boolean immature = stage.contains(LifeStageDTO.IMMATURE);
    boolean egg = stage.contains(LifeStageDTO.EGG);

    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
    map.put("ConcreteId", new ColumnSetup(true, false));
    map.put("Collection", new ColumnSetup(true, false));
    map.put("SubCollectionId", new ColumnSetup(false, true));
    map.put("IdentMethod", new ColumnSetup(false, true));
    map.put("Taxon", new ColumnSetup(false, true));
    map.put("Male", new ColumnSetup(!adult, true));
    map.put("FemalesUnfed", new ColumnSetup(!adult, true));
    map.put("FemalesFed", new ColumnSetup(!adult, true));
    map.put("FemalesHalfGravid", new ColumnSetup(!adult, true));
    map.put("FemalesGravid", new ColumnSetup(!adult, true));
    map.put("FemalesUnknown", new ColumnSetup(!adult, true));
    map.put("FemalesTotal", new ColumnSetup(!adult, false));
    map.put("Larvae", new ColumnSetup(!immature, true));
    map.put("Pupae", new ColumnSetup(!immature, true));
    map.put("Unknowns", new ColumnSetup( ( !immature && !adult ), true));
    map.put("Eggs", new ColumnSetup(!egg, true));
    map.put("Total", new ColumnSetup(false, false));
    map.put("Disected", new ColumnSetup(false, true));
    map.put("Parous", new ColumnSetup(false, true));

    return map;
  }

  private String[] getKeys()
  {
    List<String> list = new LinkedList<String>();
    list.add("ConcreteId");
    list.add("Collection");
    list.add("SubCollectionId");
    list.add("IdentMethod");
    list.add("Taxon");
    list.add("FemalesUnfed");
    list.add("FemalesFed");
    list.add("FemalesHalfGravid");
    list.add("FemalesGravid");
    list.add("FemalesUnknown");
    list.add("FemalesTotal");
    list.add("Male");
    list.add("Larvae");
    list.add("Pupae");
    list.add("Unknowns");
    list.add("Eggs");
    list.add("Total");
    list.add("Disected");
    list.add("Parous");

    return list.toArray(new String[list.size()]);
  }

  public void failCreate(MosquitoCollectionViewDTO dto) throws IOException, ServletException
  {
    this.search();
  }

  public void delete(MosquitoCollectionViewDTO dto) throws IOException, ServletException
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

  public void failDelete(MosquitoCollectionViewDTO dto) throws IOException, ServletException
  {
    this.create(dto);
  }

  public void edit(String id) throws IOException, ServletException
  {
    MosquitoCollectionViewDTO dto = MosquitoCollectionDTO.lockView(super.getClientRequest(), id);
    setupReferences(dto);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    MosquitoCollectionViewDTO dto = new MosquitoCollectionViewDTO(clientRequest);
    setupReferences(dto);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void view(String id) throws IOException, ServletException
  {
    // go back to household view after entering person
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", id);

    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.create(MosquitoCollectionDTO.getView(super.getClientRequest(), id));
  }

  private void setupReferences(SearchMosquitoCollectionViewDTO dto)
  {
    this.setupReferences((MosquitoCollectionViewDTO) dto);

    Format<Date> f = AbstractFormatFactory.getFormatFactory().getFormat(Date.class);

    if (dto.getStartDate() != null)
    {
      String startDate = f.format(dto.getStartDate(), req.getLocale());
      req.setAttribute("startDate", startDate);
    }
    else if (dto.getCollectionDate() != null)
    {
      String startDate = f.format(dto.getCollectionDate(), req.getLocale());
      req.setAttribute("startDate", startDate);
    }

    if (dto.getEndDate() != null)
    {
      String endDate = f.format(dto.getEndDate(), req.getLocale());
      req.setAttribute("endDate", endDate);
    }
  }

  private void setupReferences(MosquitoCollectionViewDTO dto)
  {
    List<LifeStageDTO> stage = dto.getLifeStage();
    InsecticideBrandViewDTO[] brands = InsecticideBrandViewDTO.getEfficacyAssayInsecticideBrands(dto.getRequest());

    if (stage.size() > 0)
    {
      req.setAttribute("currentLifeStage", stage.get(0).getName());
    }

    req.setAttribute("collectionRound", AttributeUtil.getValue(MosquitoCollection.COLLECTIONROUND, dto));
    req.setAttribute("collectionType", AttributeUtil.getValue(MosquitoCollection.COLLECTIONTYPE, dto));
    req.setAttribute("wallType", AttributeUtil.getValue(MosquitoCollection.WALLTYPE, dto));
    req.setAttribute("collectionMethod", dto.getCollectionMethod());
    req.setAttribute("lifeStage", LifeStageDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("brands", Arrays.asList(brands));
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  @Override
  public void search() throws IOException, ServletException
  {
    try
    {
      // go back to household view after entering person
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.checkURL(this.getClass().getSimpleName(), "search");

      ClientRequestIF request = this.getClientRequest();
      SearchMosquitoCollectionViewDTO view = new SearchMosquitoCollectionViewDTO(request);
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

  private void search(SearchMosquitoCollectionViewDTO view) throws IOException, ServletException
  {
    MosquitoCollectionViewQueryDTO query = MosquitoCollectionViewDTO.getMostRecent(this.getClientRequest());
    List<String> entityUniversals = Arrays.asList(new String[] { CollectionSiteDTO.CLASS, SentinelSiteDTO.CLASS });

    this.setupReferences(view);

    req.setAttribute("entityUniversals", entityUniversals);
    req.setAttribute("query", query);
    req.setAttribute("item", view);
    req.setAttribute("canDeleteAll", MDSSUserDTO.canDeleteAll(this.getClientRequest()));

    render("searchComponent.jsp");
  }

  @Override
  public void failSearch() throws IOException, ServletException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  @Override
  public void searchByDTO(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, SearchMosquitoCollectionViewDTO dto) throws IOException, ServletException
  {
    isAscending = ( isAscending == null ? true : isAscending );
    pageSize = ( pageSize == null ? 15 : pageSize );
    pageNumber = ( pageNumber == null ? 1 : pageNumber );

    ClientRequestIF request = this.getClientRequest();

    MosquitoCollectionViewQueryDTO query = MosquitoCollectionViewDTO.searchCollections(request, dto, sortAttribute, isAscending, pageSize, pageNumber);

    this.setupReferences(dto);
    List<String> entityUniversals = Arrays.asList(new String[] { CollectionSiteDTO.CLASS, SentinelSiteDTO.CLASS });

    req.setAttribute("entityUniversals", entityUniversals);
    req.setAttribute("query", query);
    req.setAttribute("item", dto);
    req.setAttribute("canDeleteAll", MDSSUserDTO.canDeleteAll(request));

    render("searchComponent.jsp");
  }

  @Override
  public void deleteAllCollections(SearchMosquitoCollectionViewDTO dto) throws IOException, ServletException
  {
    try
    {
      MosquitoCollectionViewDTO.deleteAllCollections(this.getClientRequest(), dto);

      this.search(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.search(dto);
      }
    }

  }

  @Override
  public void failSearchByDTO(String sortAttribute, String isAscending, String pageSize, String pageNumber, SearchMosquitoCollectionViewDTO dto) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    MosquitoCollectionViewQueryDTO query = MosquitoCollectionViewDTO.getMostRecent(request);

    this.setupReferences(dto);
    req.setAttribute("query", query);
    req.setAttribute("item", dto);

    render("searchComponent.jsp");
  }

  @Override
  public void searchByParameters(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, Date startDate, Date endDate, String collectionMethod, String geoEntity, String collectionId, Boolean abundance, String lifeStage) throws IOException, ServletException
  {
    SearchMosquitoCollectionViewDTO view = new SearchMosquitoCollectionViewDTO(this.getClientRequest());
    view.setCollectionDate(startDate);
    view.setEndDate(endDate);
    view.setCollectionId(collectionId);
    view.setValue(SearchMosquitoCollectionViewDTO.GEOENTITY, geoEntity);
    view.setValue(SearchMosquitoCollectionViewDTO.COLLECTIONMETHOD, collectionMethod);
    view.setAbundance(abundance);

    if (lifeStage != null && !lifeStage.equals(""))
    {
      view.addLifeStage(LifeStageDTO.valueOf(lifeStage));
    }

    this.searchByDTO(sortAttribute, isAscending, pageSize, pageNumber, view);
  }

  @Override
  public void forward(MosquitoCollectionViewDTO dto) throws IOException, ServletException
  {
    try
    {
      MosquitoCollectionViewDTO collection = MosquitoCollectionViewDTO.getCollection(this.getClientRequest(), dto);

      String concreteId = collection.getConcreteId();

      if (concreteId == null || concreteId.equals(""))
      {
        // Ensure the user has permissions to create a MosquitoCollection
        new MosquitoCollectionDTO(this.getClientRequest());
      }

      this.create(collection);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failForward(dto);
      }
    }
  }

  @Override
  public void failForward(MosquitoCollectionViewDTO dto) throws IOException, ServletException
  {
    this.search();
  }

  @Override
  public void sortAssays(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, String collectionId, String assayType) throws IOException, ServletException
  {
    MosquitoCollectionViewDTO dto = MosquitoCollectionDTO.getView(super.getClientRequest(), collectionId);

    AdultDiscriminatingDoseAssayQueryDTO ada = this.getADA(sortAttribute, isAscending, pageSize, pageNumber, assayType, dto);
    LarvaeDiscriminatingDoseAssayQueryDTO lda = getLDA(sortAttribute, isAscending, pageSize, pageNumber, assayType, dto);
    KnockDownAssayQueryDTO kda = getKDA(sortAttribute, isAscending, pageSize, pageNumber, assayType, dto);

    this.create(dto, ada, lda, kda);
  }

  private KnockDownAssayQueryDTO getKDA(MosquitoCollectionViewDTO dto)
  {
    return dto.getKnockDownAssays(CollectionAssayDTO.TESTDATE, true, 5, 1);
  }

  private LarvaeDiscriminatingDoseAssayQueryDTO getLDA(MosquitoCollectionViewDTO dto)
  {
    return dto.getLarvaeDoseAssays(CollectionAssayDTO.TESTDATE, true, 5, 1);
  }

  private AdultDiscriminatingDoseAssayQueryDTO getADA(MosquitoCollectionViewDTO dto)
  {
    return dto.getAdultDoseAssays(CollectionAssayDTO.TESTDATE, true, 5, 1);
  }

  private KnockDownAssayQueryDTO getKDA(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, String assayType, MosquitoCollectionViewDTO dto)
  {
    if (assayType.equals("kda"))
    {
      return dto.getKnockDownAssays(sortAttribute, isAscending, pageSize, pageNumber);
    }
    else
    {
      return this.getKDA(dto);
    }
  }

  private LarvaeDiscriminatingDoseAssayQueryDTO getLDA(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, String assayType, MosquitoCollectionViewDTO dto)
  {
    if (assayType.equals("lda"))
    {
      return dto.getLarvaeDoseAssays(sortAttribute, isAscending, pageSize, pageNumber);
    }
    else
    {
      return this.getLDA(dto);
    }
  }

  private AdultDiscriminatingDoseAssayQueryDTO getADA(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, String assayType, MosquitoCollectionViewDTO dto)
  {
    if (assayType.equals("ada"))
    {
      return dto.getAdultDoseAssays(sortAttribute, isAscending, pageSize, pageNumber);
    }
    else
    {
      return this.getADA(dto);
    }
  }
}
