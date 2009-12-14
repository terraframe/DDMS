package dss.vector.solutions.query;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.terraframe.mojo.ApplicationException;
import com.terraframe.mojo.web.json.JSONMojoExceptionDTO;

import dss.vector.solutions.sld.SLDWriter;

public class MappingController extends MappingControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long   serialVersionUID     = 1241150593672L;

  private static final String JSP_DIR              = "/WEB-INF/mapScreens/";

  public static final String  EDIT_LAYER           = JSP_DIR + "editLayer.jsp";

  public static final String  EDIT_VARIABLE_STYLES = JSP_DIR + "editVariableStyles.jsp";
  
  public static final String GENERATE_MAPS = JSP_DIR+"generateMaps.jsp";

  public MappingController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }
  
 
  
  public void generateMaps() throws IOException, ServletException
  {
    try
    {
      SavedMapDTO.cleanOldViews(this.getClientRequest());

      // fetch maps
      SavedMapQueryDTO mapQuery = SavedMapDTO.getAllSavedMaps(this.getClientRequest());
      JSONArray maps = new JSONArray();
      for (SavedMapDTO map : mapQuery.getResultSet())
      {
        JSONObject idAndName = new JSONObject();
        idAndName.put("id", map.getId());
        idAndName.put("name", map.getMapName());
        
        maps.put(idAndName);
      }
      this.req.setAttribute("mapList", maps.toString());
      
      this.req.getRequestDispatcher(GENERATE_MAPS).forward(req, resp);
    } 
    catch (Throwable t)
    {
      throw new ApplicationException(t);
    }
  }
  
  /**
   * Gets the legend of a saved search as a JSON object.
   * 
   */
  @Override
  public void getLegend(String savedMapId) throws IOException, ServletException
  {
    try
    {
      SavedMapDTO savedMap = SavedMapDTO.get(this.getClientRequest(), savedMapId);

      LayerDTO thematic = null; // FIXME MAP more than one thematic layer
//      LayerDTO thematic = search.getThematicLayer();

      ThematicVariableDTO variable = thematic.getThematicVariable();
      if (variable != null)
      {
        JSONObject legend = new JSONObject();
        JSONArray categoriesArr = new JSONArray();
        
        legend.put("thematicVariable", variable.getDisplayLabel());
        legend.put("categories", categoriesArr);
        
        List<? extends AbstractCategoryDTO> categories = thematic.getAllHasCategory();
        //Collections.sort(categories, new CategoryComparator());
        // FIXME need sorting again
        
        for(AbstractCategoryDTO category : categories)
        {
          JSONArray values = new JSONArray();
          if(category instanceof NonRangeCategoryDTO)
          {
            values.put(( (NonRangeCategoryDTO) category ).getExactValueStr());
          }
          else
          {
            values.put(( (RangeCategoryDTO) category ).getLowerBoundStr());
            values.put(( (RangeCategoryDTO) category ).getUpperBoundStr());
          }

          JSONObject categoryObj = new JSONObject();
          categoryObj.put("values", values);
          categoryObj.put("color", category.getThematicColor());
          
          categoriesArr.put(categoryObj);
        }
        
        resp.getWriter().print(legend.toString());
      }
      else
      {
        // no thematic variable so there can't be a legend 
        LegendWithoutVariableExceptionDTO ex = new LegendWithoutVariableExceptionDTO(this.getClientRequest(), this.req.getLocale());
        resp.getWriter().print(ex.getLocalizedMessage());
      }
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  /**
   * Writes all layers for the SavedSearch with the given id.
   * 
   * @param savedSearchId
   */
  private void writeLayers(String savedMapId)
  {
    SavedMapDTO savedMap = SavedMapDTO.get(this.getClientRequest(), savedMapId);
    List<? extends LayerDTO> layers = savedMap.getAllLayer();

    // Check that a thematic layer has been defined with valid a valid geometry style
    /* FIXME MAP
    LayerDTO thematicLayerDTO = savedMap.getThematicLayer();
    if (thematicLayerDTO.getGeometryStyle() == null)
    {
      NoThematicLayerExceptionDTO ex = new NoThematicLayerExceptionDTO(this.getClientRequest(), this.req
          .getLocale());
      throw ex;
    }

    SLDWriter sldWriter = SLDWriter.getSLDWriter(thematicLayerDTO);
    sldWriter.write();
     */

    for (LayerDTO layer : layers)
    {
      SLDWriter layerWriter = new SLDWriter(layer);
      layerWriter.write();
    }

  }

  @Override
  public void editThematicLayer(String thematicLayerId, ThematicVariableDTO[] thematicVariables)
      throws IOException, ServletException
  {
    /* FIXME MAP
    try
    {
      LayerDTO layer = ThematicLayerDTO.lock(this.getClientRequest(), thematicLayerId);

      List<? extends AbstractCategoryDTO> categories = layer.getAllDefinesCategory();
      Collections.sort(categories, new CategoryComparator());
      
      req.setAttribute("categories", categories);
      req.setAttribute("thematicVariable", layer.getThematicVariable());

      req.setAttribute("variables", thematicVariables);

      req.getRequestDispatcher(EDIT_VARIABLE_STYLES).forward(req, resp);

    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    */
  }

  @Override
  public void updateThematicVariable(String layerId, ThematicVariableDTO thematicVariable,
      AbstractCategoryDTO[] categories) throws IOException, ServletException
  {
    /* FIXME MAP
    try
    {
      LayerDTO.updateThematicVariable(this.getClientRequest(), layerId, thematicVariable,
          categories);
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    */
  }

  public void viewLayer(java.lang.String layerId) throws java.io.IOException,
      javax.servlet.ServletException
  {
  }

  public void failViewLayer(java.lang.String layerId) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }


  public void failEditLayer(java.lang.String layerId) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  @Override
  public void cancelLayer(String layerId, Boolean doUnlock) throws IOException, ServletException
  {
    try
    {
      if (doUnlock.booleanValue())
      {
        LayerDTO.unlock(this.getClientRequest(), layerId);
      }
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  @Override
  public void failCancelLayer(String layerId, String doUnlock) throws IOException, ServletException
  {
    // TODO Auto-generated method stub
    super.failCancelLayer(layerId, doUnlock);
  }

  @Override
  public void deleteLayer(String layerId) throws IOException, ServletException
  {
    try
    {
      // TODO clean up SLD file
      LayerDTO layer = LayerDTO.get(this.getClientRequest(), layerId);
      layer.delete();
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  @Override
  public void failDeleteLayer(String layerId) throws IOException, ServletException
  {
    // TODO Auto-generated method stub
    super.failDeleteLayer(layerId);
  }

}