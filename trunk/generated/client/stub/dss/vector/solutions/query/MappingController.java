package dss.vector.solutions.query;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.web.json.JSONMojoExceptionDTO;
import com.terraframe.mojo.web.json.JSONProblemExceptionDTO;

import dss.vector.solutions.PolygonStyleDTO;
import dss.vector.solutions.entomology.MosquitoDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointDTO;
import dss.vector.solutions.irs.AbstractSprayDTO;
import dss.vector.solutions.sld.SLDWriter;
import dss.vector.solutions.surveillance.AggregatedCaseDTO;

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
  
  private class CategoryComparator implements Comparator<AbstractCategoryDTO>, com.terraframe.mojo.generation.loader.Reloadable
  {

    public int compare(AbstractCategoryDTO c1, AbstractCategoryDTO c2)
    {
      Double min1;
      if(c1 instanceof RangeCategoryDTO)
      {
        min1 = ((RangeCategoryDTO)c1).getLowerBound();
      }
      else
      {
        min1 = ((NonRangeCategoryDTO)c1).getExactValue();
      }
      
      Double min2;
      if(c2 instanceof RangeCategoryDTO)
      {
        min2 = ((RangeCategoryDTO)c2).getLowerBound();
      }
      else
      {
        min2 = ((NonRangeCategoryDTO)c2).getExactValue();
      }
      
      if(min1 == min2)
      {
        return 0;
      }
      else if(min1 > min2)
      {
        return 1;
      }
      else
      {
        return -1;
      }
    }
    
  }
  
  public void generateMaps() throws IOException, ServletException
  {
    //
    
    this.req.getRequestDispatcher(GENERATE_MAPS).forward(req, resp);
  }
  
  /**
   * Gets the legend of a saved search as a JSON object.
   * 
   */
  @Override
  public void getLegend(String savedSearchId) throws IOException, ServletException
  {
    try
    {
      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      ThematicLayerDTO thematic = search.getThematicLayer();

      ThematicVariableDTO variable = thematic.getThematicVariable();
      if (variable != null)
      {
        JSONObject legend = new JSONObject();
        JSONArray categoriesArr = new JSONArray();
        
        legend.put("thematicVariable", variable.getDisplayLabel());
        legend.put("categories", categoriesArr);
        
        List<? extends AbstractCategoryDTO> categories = thematic.getAllDefinesCategory();
        Collections.sort(categories, new CategoryComparator());
        
        for(AbstractCategoryDTO category : categories)
        {
          JSONArray values = new JSONArray();
          if(category instanceof NonRangeCategoryDTO)
          {
            values.put(( (NonRangeCategoryDTO) category ).getExactValue());
          }
          else
          {
            values.put(( (RangeCategoryDTO) category ).getLowerBound());
            values.put(( (RangeCategoryDTO) category ).getUpperBound());
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
  private void writeLayers(String savedSearchId)
  {
    SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);
    List<? extends LayerDTO> layers = search.getAllDefinesLayers();

    // Check that a thematic layer has been defined with valid a valid geometry
    // style.
    ThematicLayerDTO thematicLayerDTO = search.getThematicLayer();
    if (thematicLayerDTO.getGeometryStyle() == null)
    {
      NoThematicLayerExceptionDTO ex = new NoThematicLayerExceptionDTO(this.getClientRequest(), this.req
          .getLocale());
      throw ex;
    }

    SLDWriter sldWriter = SLDWriter.getSLDWriter(thematicLayerDTO);
    sldWriter.write();

    for (LayerDTO layer : layers)
    {
      SLDWriter layerWriter = SLDWriter.getSLDWriter(layer);
      layerWriter.write();
    }

  }

  @Override
  public void mapSurveyQuery(String queryXML, String config, String[] universalLayers,
      String savedSearchId) throws IOException, ServletException
  {
    try
    {
      // must write layers first so the mapping has valid SLD files to
      // reference. If
      // the search id is null, skip this step so control flow continues and the
      // proper
      // error can be thrown.
      if (savedSearchId != null && savedSearchId.trim().length() > 0)
      {
        writeLayers(savedSearchId);
      }

      String layers = SurveyPointDTO.mapQuery(this.getClientRequest(), queryXML, config,
          universalLayers, savedSearchId);

      resp.getWriter().print(layers);
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  @Override
  public void mapIRSQuery(String queryXML, String config, String[] universalLayers,
      String savedSearchId) throws IOException, ServletException
  {
    try
    {
      // must write layers first so the mapping has valid SLD files to reference. If
      // the search id is null, skip this step so control flow continues and the proper
      // error can be thrown.
      if(savedSearchId != null && savedSearchId.trim().length() > 0)
      {
        writeLayers(savedSearchId);
      }

      String layers = AbstractSprayDTO.mapQuery(this.getClientRequest(), queryXML, config,
          universalLayers, savedSearchId);

      resp.getWriter().print(layers);
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  @Override
  public void mapAggregatedCaseQuery(String queryXML, String config,
      String[] universalLayers, String savedSearchId) throws IOException, ServletException
  {
    try
    {
      // must write layers first so the mapping has valid SLD files to
      // reference. If
      // the search id is null, skip this step so control flow continues and the
      // proper
      // error can be thrown.
      if (savedSearchId != null && savedSearchId.trim().length() > 0)
      {
        writeLayers(savedSearchId);
      }

      String layers = AggregatedCaseDTO.mapQuery(this.getClientRequest(), queryXML, config,
          universalLayers, savedSearchId);

      resp.getWriter().print(layers);
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  @Override
  public void mapEntomologyQuery(String queryXML, String config, String[] universalLayers,
      String savedSearchId) throws IOException, ServletException
  {
    try
    {
      // must write layers first so the mapping has valid SLD files to
      // reference. If
      // the search id is null, skip this step so control flow continues and the
      // proper
      // error can be thrown.
      if (savedSearchId != null && savedSearchId.trim().length() > 0)
      {
        writeLayers(savedSearchId);
      }

      String layers = MosquitoDTO.mapQuery(this.getClientRequest(), queryXML, config, universalLayers,
          savedSearchId);

      resp.getWriter().print(layers);
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  @Override
  public void mapResistanceQuery(String queryXML, String config, String[] universalLayers,
      String savedSearchId) throws IOException, ServletException
  {
    try
    {
      // must write layers first so the mapping has valid SLD files to
      // reference. If
      // the search id is null, skip this step so control flow continues and the
      // proper
      // error can be thrown.
      if (savedSearchId != null && savedSearchId.trim().length() > 0)
      {
        writeLayers(savedSearchId);
      }

      String layers = MosquitoDTO.mapQuery(this.getClientRequest(), queryXML, config, universalLayers,
          savedSearchId);

      resp.getWriter().print(layers);
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  @Override
  public void editThematicLayer(String thematicLayerId, ThematicVariableDTO[] thematicVariables)
      throws IOException, ServletException
  {
    try
    {
      ThematicLayerDTO layer = ThematicLayerDTO.lock(this.getClientRequest(), thematicLayerId);

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
  }

  @Override
  public void updateThematicVariable(String layerId, ThematicVariableDTO thematicVariable,
      AbstractCategoryDTO[] categories) throws IOException, ServletException
  {
    try
    {
      ThematicLayerDTO.updateThematicVariable(this.getClientRequest(), layerId, thematicVariable,
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

  /**
   * Updates the layer summary layer (the style components).
   */
  public void updateLayer(dss.vector.solutions.query.GeometryStyleDTO geometryStyle,
      dss.vector.solutions.query.TextStyleDTO textStyle, String layerId) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      LayerDTO layer = LayerDTO.updateLayer(this.getClientRequest(), geometryStyle, textStyle, layerId);

      // SLDWriter sldWriter = SLDWriter.getSLDWriter(layer);
      // sldWriter.write();

      resp.getWriter().print(layer.getId());
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
  }

  public void failUpdateLayer(dss.vector.solutions.query.GeometryStyleDTO geometryStyle,
      dss.vector.solutions.query.TextStyleDTO textStyle) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  /**
   * Creates a Layer with default styles.
   */
  public void createLayer(java.lang.String savedSearchId, java.lang.String layerClass)
      throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      LayerDTO layer = UniversalLayerDTO.createLayer(this.getClientRequest(), savedSearchId, layerClass);

      // SLDWriter sldWriter = SLDWriter.getSLDWriter(layer);
      // sldWriter.write();

      resp.getWriter().print(layer.getId());
    }
    catch (Throwable t)
    {
      // FIXME roll back file changes and delete Layer

      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void failCreateLayer(java.lang.String savedSearchId, java.lang.String layerClass)
      throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  /**
   * Edits the layer summary by locking all of its style components and
   * providing a jsp where their values can be edited.
   */
  public void editLayer(java.lang.String layerId) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      LayerDTO layer = LayerDTO.lock(this.getClientRequest(), layerId);

      GeometryStyleDTO geoStyle = layer.getGeometryStyle();
      req.setAttribute("hasFill", geoStyle.hasAttribute(PolygonStyleDTO.FILL));

      req.setAttribute("layerId", layerId);
      req.setAttribute("geoStyle", geoStyle);
      req.setAttribute("textStyle", layer.getTextStyle());

      req.getRequestDispatcher(EDIT_LAYER).forward(req, resp);
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
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