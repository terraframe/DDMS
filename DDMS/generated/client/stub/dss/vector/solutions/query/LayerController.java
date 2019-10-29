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
package dss.vector.solutions.query;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import org.json.JSONObject;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ValueObjectDTO;
import com.runwaysdk.business.ValueQueryDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.web.json.JSONProblemExceptionDTO;
import com.runwaysdk.web.json.JSONRunwayExceptionDTO;

public class LayerController extends LayerControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/query/Layer/";

  public static final String LAYOUT           = JSP_DIR + "layout.jsp";

  public static final String QUERY_INFO_JSP   = JSP_DIR + "queryInfo.jsp";

  public static final String CATEGORY_GEN_JSP = JSP_DIR + "categoryGen.jsp";

  private static final long  serialVersionUID = 1240900964253L;

  public LayerController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  private void populateRequestForLayer(LayerDTO layer, StylesDTO styles)
  {
    ClientRequestIF request = this.getClientRequest();

    // Set the available legend color attributes
    LegendColorOption[] legendColors = new LegendColorOption[5];
    legendColors[0] = new LegendColorOption(styles.getFillMd());
    legendColors[1] = new LegendColorOption(styles.getLabelHaloFillMd());
    legendColors[2] = new LegendColorOption(styles.getPointStrokeMd());
    legendColors[3] = new LegendColorOption(styles.getPolygonFillMd());
    legendColors[4] = new LegendColorOption(styles.getPolygonStrokeMd());
    req.setAttribute("legendColors", legendColors);

    req.setAttribute("layer", layer);
    req.setAttribute("isNewInstance", layer.isNewInstance());
    StylesController.populateRequestForStyles(req, styles, false);

    // fetch queries
    SavedSearchViewDTO[] results = SavedSearchDTO.getMappableSearches(request);

    this.req.setAttribute("queryList", Arrays.asList(results));
    this.req.setAttribute("renderAsOptions", AllRenderTypesDTO.allItems(request));

    // fetch categories
    String mdAttributeId = "";
    String geoHierarchyId = "";
    String currentLegendColor;
    if (layer.isNewInstance())
    {
      // The polygon fill color is the default since it is the most widely used.
      currentLegendColor = styles.getPolygonFillMd().getId();

      req.setAttribute("categories", null);

      // Check if there is at least one valid SavedSearch which has geometry in
      // the query.
      if (results.length > 0)
      {
        SavedSearchViewDTO ssView = results[0];
        String ssId = ssView.getSavedQueryId();

        // Add any of available universal geometries
        AttributeGeoHierarchyDTO[] attrGeos = SavedSearchDTO.getAttributeGeoHierarchies(layer.getRequest(), ssId);
        req.setAttribute("attrGeos", attrGeos);

        if (attrGeos.length > 0)
        {
          // Automatically provide the layer with the first available universal
          // geometry
          AttributeGeoHierarchyDTO first = attrGeos[0];
          mdAttributeId = first.getMdAttributeId();
          geoHierarchyId = first.getGeoHierarchyId();
        }

        // Add any thematic variables defined by the SavedSearch
        ThematicVariableDTO[] thematicVars = SavedSearchDTO.getThematicVariables(layer.getRequest(), ssId);
        req.setAttribute("thematicVars", thematicVars);
      }
      else
      {
        req.setAttribute("attrGeos", null);
        req.setAttribute("thematicVars", null);
      }

      req.setAttribute("currentAttributeGeoHierarchy", null);
    }
    else
    {
      currentLegendColor = layer.getValue(LayerDTO.LEGENDCOLOR);

      req.setAttribute("hasThematic", layer.getThematicUserAlias() != null && layer.getThematicUserAlias().length() > 0);
      String ssId = layer.getValue(LayerDTO.SAVEDSEARCH);

      mdAttributeId = layer.getValue(LayerDTO.MDATTRIBUTE);
      geoHierarchyId = layer.getValue(LayerDTO.GEOHIERARCHY);

      List<? extends AbstractCategoryDTO> categories = layer.getAllHasCategory();
      CategorySorter.sort(categories);

      req.setAttribute("categories", categories);

      AttributeGeoHierarchyDTO[] attrGeos = SavedSearchDTO.getAttributeGeoHierarchies(layer.getRequest(), ssId);
      req.setAttribute("attrGeos", attrGeos);

      String currAttrGeo = layer.getValue(LayerDTO.MDATTRIBUTE) + ":" + layer.getValue(LayerDTO.GEOHIERARCHY);
      req.setAttribute("currentAttributeGeoHierarchy", currAttrGeo);

      ThematicVariableDTO[] thematicVars = SavedSearchDTO.getThematicVariables(request, ssId);
      req.setAttribute("thematicVars", thematicVars);
    }

    this.req.setAttribute("mdAttributeId", mdAttributeId);
    this.req.setAttribute("geoHierarchyId", geoHierarchyId);
    this.req.setAttribute("currentLegendColor", currentLegendColor);
  }

  @Override
  public void generateCategories(CategoryGenDTO categoryGen, LayerDTO layer) throws IOException, ServletException
  {
    try
    {
      String layerId = categoryGen.getLayerId();
      AbstractCategoryDTO[] categories = LayerDTO.generateCategories(this.getClientRequest(), layerId, categoryGen, layer);

      for (AbstractCategoryDTO category : categories)
      {
        req.setAttribute("category", category);
        if (category instanceof NonRangeCategoryDTO)
        {
          req.getRequestDispatcher(NonRangeCategoryController.SUMMARY_VIEW).include(req, resp);
        }
        else
        {
          req.getRequestDispatcher(RangeCategoryController.SUMMARY_VIEW).include(req, resp);
        }
      }
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  @Override
  public void requestGenerate(LayerDTO layer, String factoryType) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      CategoryGenDTO gen = new CategoryGenDTO(request);
      gen.setLayerId(layer.getId());

      req.setAttribute("item", gen);

      ValueQueryDTO vqDTO = AbstractCategoryFactoryDTO.getSubclassInfo(request);
      List<CategoryFactoryOption> factories = new LinkedList<CategoryFactoryOption>();
      for (ValueObjectDTO vo : vqDTO.getResultSet())
      {
        factories.add(new CategoryFactoryOption(vo));
      }

      req.setAttribute("factories", factories);

      String required = AbstractCategoryFactoryDTO.getAllRequiredAttributes(request);
      req.setAttribute("required", required);

      req.getRequestDispatcher(CATEGORY_GEN_JSP).forward(req, resp);
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  /**
   * Calculates basic information for the query represented by the given Layer.
   * The layer object is not persisted and is only used as a temporary mechanism
   * in which hold query information.
   */
  @Override
  public void calculateQueryInfo(LayerDTO layer) throws IOException, ServletException
  {
    try
    {
      QueryInfoDTO info = layer.calculateQueryInfo();

      req.setAttribute("info", info);
      req.getRequestDispatcher(QUERY_INFO_JSP).forward(req, resp);
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  @Override
  public void newInstance() throws IOException, ServletException
  {
    try
    {
      LayerDTO layer = new LayerDTO(this.getClientRequest());
      StylesDTO styles = new StylesDTO(this.getClientRequest());

      this.populateRequestForLayer(layer, styles);

      render("createComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  @Override
  public void saveLayer(LayerDTO layer, StylesDTO styles, String savedMapId) throws IOException, ServletException
  {
    try
    {
      layer.applyWithStyles(styles, savedMapId);

      JSONObject object = new JSONObject();
      object.put("layerId", layer.getId());
      object.put("semanticId", layer.getSemanticId());

      resp.getWriter().print(object.toString());
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  @Override
  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      LayerDTO layer = LayerDTO.lock(this.getClientRequest(), id);
      StylesDTO styles = layer.getDefaultStyles();

      this.populateRequestForLayer(layer, styles);

      render("editComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void delete(dss.vector.solutions.query.LayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failDelete(dto);
    }
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.LayerQueryDTO query = dss.vector.solutions.query.LayerDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All LayerController Objects");
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.LayerQueryDTO query = dss.vector.solutions.query.LayerDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All LayerController Objects");
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void cancel(dss.vector.solutions.query.LayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.unlock();
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void failCancel(dss.vector.solutions.query.LayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void update(dss.vector.solutions.query.LayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }

  public void create(dss.vector.solutions.query.LayerDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }
}
