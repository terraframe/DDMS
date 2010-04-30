package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.business.generation.GenerationUtil;
import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.CompositeDataGrid;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.DynamicTermDataGrid;
import dss.vector.solutions.util.yui.TermSetup;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class ControlInterventionController extends ControlInterventionControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long  serialVersionUID = -905723824;

  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/ControlIntervention/";

  public static final String LAYOUT           = "/layout.jsp";

  public ControlInterventionController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void searchIndivudalPremiseVisits() throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();
        
    DataGrid grid = this.getGrid(request);
    
    req.setAttribute("grid", grid);
    
    render("viewIndividualPremiseComponent.jsp");
  }

  private CompositeDataGrid getGrid(ClientRequestIF request)
  {
    IndividualPremiseVisitViewDTO[] views = IndividualPremiseVisitViewDTO.getViews(request);
    IndividualPremiseVisitMethodViewDTO[][] methods = IndividualPremiseVisitViewDTO.getInterventionMethodsForViews(request, views);    

    IndividualPremiseVisitViewDTO view = new IndividualPremiseVisitViewDTO(request);
    IndividualPremiseVisitMethodViewDTO method = new IndividualPremiseVisitMethodViewDTO(request);
    method.setUsed(false);

    String[] viewKeys = this.getViewKeys();
    Map<String, ColumnSetup> viewColumns = this.getColumns(viewKeys, 2, false);

    String[] methodKeys = this.getMethodKeys();
    Map<String, ColumnSetup> methodColumns = this.getColumns(methodKeys, 1, true);
    
    ViewDataGrid viewGenerator = new ViewDataGrid(view, viewColumns, viewKeys, views);
    
    String label = view.getInterventionMethodMd().getDisplayLabel();
    TermSetup setup = new TermSetup(IndividualPremiseVisitMethodViewDTO.USED);

    DynamicTermDataGrid dynamicGenerator = new DynamicTermDataGrid(method, methodColumns, methodKeys, setup, IndividualPremiseVisitViewDTO.CLASS, IndividualPremiseVisitViewDTO.INTERVENTIONMETHOD, label, methods);
            
    CompositeDataGrid generator = new CompositeDataGrid(new DataGrid[]{viewGenerator, dynamicGenerator});
    
    return generator;
  }
  
  private String[] getViewKeys()
  {
    String[] keys = new String[] { IndividualPremiseVisitViewDTO.CONCRETEID, IndividualPremiseVisitViewDTO.GEOENTITY, IndividualPremiseVisitViewDTO.ENTITYLABEL, IndividualPremiseVisitViewDTO.VISITED, IndividualPremiseVisitViewDTO.TREATED, IndividualPremiseVisitViewDTO.REASONSFORNOTTREATED};

    this.upperFirstCharacter(keys);

    return keys;
  }
  
  private String[] getMethodKeys()
  {
    String[] keys = new String[] {IndividualPremiseVisitMethodViewDTO.TERM, IndividualPremiseVisitMethodViewDTO.USED};
    
    this.upperFirstCharacter(keys);
    
    return keys;
  }
  
  private Map<String, ColumnSetup> getColumns(String[] keys, int hidden, boolean editable)
  {
    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();

    for (int i = 0; i < keys.length; i++)
    {
      ColumnSetup setup = ( i < hidden ? new ColumnSetup(true, editable) : new ColumnSetup(false, true) );

      map.put(keys[i], setup);
    }

    return map;
  }

  private void upperFirstCharacter(String[] array)
  {
    for (int i = 0; i < array.length; i++)
    {
      array[i] = GenerationUtil.upperFirstCharacter(array[i]);
    }
  }

}
