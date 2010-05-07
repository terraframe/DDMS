package dss.vector.solutions.intervention.monitor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlInterventionController extends ControlInterventionControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long  serialVersionUID = -905723824;

  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/ControlIntervention/";

  public static final String LAYOUT           = "/layout.jsp";

  public ControlInterventionController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
//  public void update(ControlInterventionViewDTO dto) throws IOException, ServletException
//  {
//    try
//    {
//      dto.apply();
//      this.view(dto);
//    }
//    catch (ProblemExceptionDTO e)
//    {
//      ErrorUtility.prepareProblems(e, req);
//
//      this.failUpdate(dto);
//    }
//    catch (Throwable t)
//    {
//      ErrorUtility.prepareThrowable(t, req);
//
//      this.failUpdate(dto);
//    }
//  }
//
//  public void failUpdate(ControlInterventionViewDTO dto) throws IOException, ServletException
//  {
//    req.setAttribute("item", dto);
//    render("editComponent.jsp");
//  }
//
//  public void create(ControlInterventionViewDTO dto) throws IOException, ServletException
//  {
//    try
//    {
//      dto.apply();
//      this.view(dto);
//    }
//    catch (ProblemExceptionDTO e)
//    {
//      ErrorUtility.prepareProblems(e, req);
//
//      this.failCreate(dto);
//    }
//    catch (Throwable t)
//    {
//      ErrorUtility.prepareThrowable(t, req);
//
//      this.failCreate(dto);
//    }
//  }
//
//  public void failCreate(ControlInterventionViewDTO dto) throws IOException, ServletException
//  {
//    this.newInstance(dto);
//  }
//
//  public void newInstance() throws IOException, ServletException
//  {
//    try
//    {
//      ClientRequestIF clientRequest = super.getClientRequest();
//
//      // Ensure the user has permissions to create a survey point
//      new ControlInterventionDTO(clientRequest);
//
//      this.newInstance(new ControlInterventionViewDTO(clientRequest));
//    }
//    catch (ProblemExceptionDTO e)
//    {
//      ErrorUtility.prepareProblems(e, req);
//
//      this.failNewInstance();
//    }
//    catch (Throwable t)
//    {
//      ErrorUtility.prepareThrowable(t, req);
//
//      this.failNewInstance();
//    }
//  }
//
//  private void newInstance(ControlInterventionViewDTO dto) throws IOException, ServletException
//  {
//    req.setAttribute("item", dto);
//    
//    render("createComponent.jsp");
//  }
//
//  public void failNewInstance() throws IOException, ServletException
//  {
//    this.viewAll();
//  }
//
//  public void view(String id) throws IOException, ServletException
//  {
//    try
//    {
//      view(ControlInterventionDTO.getView(super.getClientRequest(), id));
//    }
//    catch (ProblemExceptionDTO e)
//    {
//      ErrorUtility.prepareProblems(e, req);
//      this.failView(id);
//    }
//    catch (Throwable t)
//    {
//      ErrorUtility.prepareThrowable(t, req);
//      this.failView(id);
//    }
//
//  }
//
//  public void view(ControlInterventionViewDTO dto) throws IOException, ServletException
//  {
//    RedirectUtility utility = new RedirectUtility(req, resp);
//    utility.put("id", dto.getConcreteId());
//    utility.checkURL(this.getClass().getSimpleName(), "view");
//
//    req.setAttribute("item", dto);
//    render("viewComponent.jsp");
//  }
//
//  public void failView(String id) throws IOException, ServletException
//  {
//    this.viewAll();
//  }
//
//  public void cancel(ControlInterventionViewDTO dto) throws IOException, ServletException
//  {
//    try
//    {
//      this.view(ControlInterventionDTO.unlockView(dto.getRequest(), dto.getConcreteId()));
//    }
//    catch (Throwable t)
//    {
//      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
//
//      if (!redirected)
//      {
//        this.failCancel(dto);
//      }
//    }
//  }
//  
//  @Override
//  public void failCancel(ControlInterventionViewDTO dto) throws IOException, ServletException
//  {
//    this.edit(dto.getConcreteId());
//  }
//
//  public void delete(ControlInterventionViewDTO dto) throws IOException, ServletException
//  {
//    try
//    {
//      dto.deleteConcrete();
//      this.viewAll();
//    }
//    catch (ProblemExceptionDTO e)
//    {
//      ErrorUtility.prepareProblems(e, req);
//
//      this.failDelete(dto);
//    }
//    catch (Throwable t)
//    {
//      ErrorUtility.prepareThrowable(t, req);
//
//      this.failDelete(dto);
//    }
//  }
//
//  public void failDelete(ControlInterventionViewDTO dto) throws IOException, ServletException
//  {
//    List<String> entityUniversals = Arrays.asList(new String[] { SentinelSiteDTO.CLASS });
//    req.setAttribute("entityUniversals", entityUniversals);
//    req.setAttribute("SentinelSite", SentinelSiteDTO.CLASS);
//    req.setAttribute("item", dto);
//
//    render("editComponent.jsp");
//  }
//
//  public void edit(String id) throws IOException, ServletException
//  {
//    try
//    {
//      ControlInterventionViewDTO dto = ControlInterventionDTO.lockView(super.getClientRequest(), id);
//
//      req.setAttribute("item", dto);
//
//      render("editComponent.jsp");
//    }
//    catch (ProblemExceptionDTO e)
//    {
//      ErrorUtility.prepareProblems(e, req);
//
//      this.failEdit(id);
//    }
//    catch (Throwable t)
//    {
//      ErrorUtility.prepareThrowable(t, req);
//
//      this.failEdit(id);
//    }
//
//  }
//
//  public void failEdit(String id) throws IOException, ServletException
//  {
//    this.view(id);
//  }
//
//  
//  @Override
//  public void search() throws IOException, ServletException
//  {    
//    RedirectUtility utility = new RedirectUtility(req, resp);
//    utility.checkURL(this.getClass().getSimpleName(), "search");
//
//    ClientRequestIF clientRequest = this.getClientRequest();
//    
//    this.search(new ControlInterventionViewDTO(clientRequest));
//  }
//
//  private void search(ControlInterventionViewDTO item) throws IOException, ServletException
//  {
//    GeoEntityDTO entity = item.getGeoEntity();
//
//    List<GeoHierarchyViewDTO> list = new LinkedList<GeoHierarchyViewDTO>();
//
//    if(entity != null)
//    {
//      ClientRequestIF request = this.getClientSession().getRequest();
//      
//      GeoHierarchyViewDTO[] universals = GeoHierarchyViewDTO.getUrbanHierarchies(request, entity.getType());
//      list = Arrays.asList(universals);
//    }
//    
//    req.setAttribute("universals", list);      
//    req.setAttribute("item", item);
//    render("searchComponent.jsp");
//  }
//  
//  @Override
//  public void searchByParameters(ControlInterventionViewDTO view) throws IOException, ServletException
//  {
//    ClientRequestIF request = this.getClientRequest();
//
//    ControlInterventionViewDTO item = ControlInterventionViewDTO.search(request, view);
//    
//    if(item.getConcreteId() != null && item.getConcreteId().length() != 0)
//    {
//      this.newInstance(item);
//    }
//    else
//    {
//      this.view(item);
//    }
//  }
//  
//  @Override
//  public void getIndividualPremise(String id, GeoHierarchyDTO universal) throws IOException, ServletException
//  {
//    ClientRequestIF request = this.getClientRequest();
//    
//    DataGrid grid = this.getGrid(request, id, universal);
//    
//    req.setAttribute("grid", grid);
//    
//    render("viewIndividualPremiseComponent.jsp");
//  }
//  
//  private DataGrid getGrid(ClientRequestIF request, String id, GeoHierarchyDTO universal)
//  {
//    IndividualPremiseVisitViewDTO[] views = ControlInterventionViewDTO.getIndividualPremiseViews(request, id, universal);
//    IndividualPremiseVisitMethodViewDTO[][] methods = IndividualPremiseVisitViewDTO.getInterventionMethodsForViews(request, views);    
//
//    IndividualPremiseVisitViewDTO view = new IndividualPremiseVisitViewDTO(request);
//    IndividualPremiseVisitMethodViewDTO method = new IndividualPremiseVisitMethodViewDTO(request);
//    method.setUsed(false);
//
//    String[] viewKeys = this.getViewKeys();
//    Map<String, ColumnSetup> viewColumns = this.getColumns(viewKeys, 2, false);
//
//    String[] methodKeys = this.getMethodKeys();
//    Map<String, ColumnSetup> methodColumns = this.getColumns(methodKeys, 1, true);
//    
//    ViewDataGrid viewGenerator = new ViewDataGrid(view, viewColumns, viewKeys, views);
//    
//    String label = view.getInterventionMethodMd().getDisplayLabel();
//    TermSetup setup = new TermSetup(IndividualPremiseVisitMethodViewDTO.USED, IndividualPremiseVisitMethodViewDTO.TERM);
//
//    DynamicTermDataGrid dynamicGenerator = new DynamicTermDataGrid(method, methodColumns, methodKeys, setup, IndividualPremiseVisitViewDTO.CLASS, IndividualPremiseVisitViewDTO.INTERVENTIONMETHOD, label, methods);
//            
//    CompositeDataGrid generator = new CompositeDataGrid(new DataGrid[]{viewGenerator, dynamicGenerator});
//    
//    return generator;
//  }
//  
//  private String[] getViewKeys()
//  {
//    String[] keys = new String[] { IndividualPremiseVisitViewDTO.CONCRETEID, IndividualPremiseVisitViewDTO.GEOENTITY, IndividualPremiseVisitViewDTO.ENTITYLABEL, IndividualPremiseVisitViewDTO.VISITED, IndividualPremiseVisitViewDTO.TREATED, IndividualPremiseVisitViewDTO.REASONSFORNOTTREATED};
//
//    this.upperFirstCharacter(keys);
//
//    return keys;
//  }
//  
//  private String[] getMethodKeys()
//  {
//    String[] keys = new String[] {IndividualPremiseVisitMethodViewDTO.TERM, IndividualPremiseVisitMethodViewDTO.USED};
//    
//    this.upperFirstCharacter(keys);
//    
//    return keys;
//  }
//  
//  private Map<String, ColumnSetup> getColumns(String[] keys, int hidden, boolean editable)
//  {
//    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
//
//    for (int i = 0; i < keys.length; i++)
//    {
//      ColumnSetup setup = ( i < hidden ? new ColumnSetup(true, editable) : new ColumnSetup(false, true) );
//
//      map.put(keys[i], setup);
//    }
//
//    return map;
//  }
//
//  private void upperFirstCharacter(String[] array)
//  {
//    for (int i = 0; i < array.length; i++)
//    {
//      array[i] = GenerationUtil.upperFirstCharacter(array[i]);
//    }
//  }

}
