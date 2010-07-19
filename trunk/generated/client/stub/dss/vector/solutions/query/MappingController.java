package dss.vector.solutions.query;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;

import com.runwaysdk.ClientException;
import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.transport.conversion.json.JSONReturnObject;
import com.runwaysdk.util.FileIO;
import com.runwaysdk.web.json.JSONRunwayExceptionDTO;
import com.runwaysdk.web.json.JSONProblemExceptionDTO;

import dss.vector.solutions.sld.SLDWriter;
import dss.vector.solutions.util.FileDownloadUtil;
import dss.vector.solutions.util.MDSSProperties;

public class MappingController extends MappingControllerBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long   serialVersionUID     = 1241150593672L;

  private static final String JSP_DIR              = "/WEB-INF/mapScreens/";

  private static final String ADD_TEXT_JSP         = JSP_DIR+"addText.jsp";

  public static final String GENERATE_MAPS = JSP_DIR+"generateMaps.jsp";

  public MappingController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }

  @Override
  public void addText() throws IOException, ServletException
  {
    try
    {
      FreeTextDTO text = new FreeTextDTO(this.getClientRequest());
      req.setAttribute("freeText", text);

      StylesController.setFontStylesAndFamiles(req, this.getClientRequest());

      req.getRequestDispatcher(ADD_TEXT_JSP).forward(req, resp);
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  @Override
  public void exportShapefile(String mapId, String namedMapId) throws IOException, ServletException
  {
    try
    {
      SavedMapDTO savedMap = SavedMapDTO.get(this.getClientRequest(), namedMapId);
      InputStream stream = SavedMapDTO.exportShapefile(this.getClientRequest(), mapId);

      String mapName;
      if(savedMap instanceof DefaultSavedMapDTO)
      {
        mapName = MDSSProperties.getString("Shapefile");
      }
      else
      {
        mapName = savedMap.getMapName();
      }

      FileDownloadUtil.writeZIP(resp, mapName, stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
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
      throw new ClientException(t);
    }
  }

  @Override
  public void refreshMap(String savedMapId) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      SavedMapDTO map = SavedMapDTO.get(request, savedMapId);


      // Regenerate the database views
      String mapData = map.refreshMap();

      // Re-print all SLD files for the layers
      for(LayerDTO layer : map.getAllLayer())
      {
        new SLDWriter(layer).write();
      }

      JSONReturnObject json = new JSONReturnObject(mapData);
      json.setInformation(request.getInformation());

      resp.getWriter().print(json.toString());
    }
    catch(ProblemExceptionDTO e)
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
  public void uploadMapImage() throws IOException, ServletException
  {
    boolean success = false;
    String message = "";
    try
    {
      // Create a factory for disk-based file items
      FileItemFactory factory = new DiskFileItemFactory();

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);

      FileItem file = null;
      List<FileItem> items = upload.parseRequest(this.req);
      for (FileItem item : items)
      {
        if (item.getFieldName().equals("imageFile"))
        {
          file = item;
        }
      }

      if(file != null)
      {
        String ext = file.getName().length() == 0 ? "" : file.getName().substring(file.getName().lastIndexOf("."));

        if(ext.length() == 0 || !ext.matches("\\.(?i)(jpg|jpeg|bmp|png|tiff|gif)"))
        {
          ImageUploadOnlyExceptionDTO ex = new ImageUploadOnlyExceptionDTO(this.getClientRequest(), this.req.getLocale());
          resp.setStatus(500);
          message = ex.getLocalizedMessage();
          return;
        }

        String name = QueryConstants.MAP_IMAGES_DIR+System.currentTimeMillis()+ext;
        String deploy = DeployProperties.getDeployPath();
        if(!deploy.endsWith("/"))
        {
          deploy += "/";
        }

        String path = deploy+name;

        FileIO.write(path, file.get());

        success = true;
        message = name;
      }
      else
      {
        ImageUploadOnlyExceptionDTO ex = new ImageUploadOnlyExceptionDTO(this.getClientRequest(), this.req.getLocale());
        resp.setStatus(500);
        message = ex.getLocalizedMessage();
      }
    }
    catch(Throwable t)
    {
      ClientException ex = new ClientException(t);
      message = ex.getLocalizedMessage();
    }
    finally
    {
      message = JSONObject.quote(message);
      resp.setContentType("text/html");
      resp.getWriter().write("{\"success\":"+success+", \"message\":"+message+"}");
    }
  }

  /**
   * Gets the legend of a saved search as a JSON object.
   *
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
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }
   */

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
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
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
      LayerDTO layer = LayerDTO.get(this.getClientRequest(), layerId);
      layer.delete();
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
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