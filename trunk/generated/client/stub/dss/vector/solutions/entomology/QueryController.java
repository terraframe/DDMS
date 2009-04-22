package dss.vector.solutions.entomology;

import java.io.IOException;

import javax.servlet.ServletException;

import dss.vector.solutions.entomology.assay.AbstractAssayDTO;
import dss.vector.solutions.geo.GeoEntityTreeController;
import dss.vector.solutions.geo.generated.EarthDTO;

public class QueryController extends QueryControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237863171352L;

  private static final String QUERY_ENTOMOLOGY = "/WEB-INF/queryScreens/queryEntomology.jsp";

  private static final String MAP_TEMPLATE = "/WEB-INF/queryScreens/mapTemplate.jsp";

  public QueryController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }

  @Override
  public void queryEntomology() throws IOException, ServletException
  {
    String json = AbstractAssayDTO.getAssayTree(this.getClientRequest());

    // The Earth is the root. FIXME use country's default root
    EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
    req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());

    req.setAttribute("assayTree", json);

    req.getRequestDispatcher(QUERY_ENTOMOLOGY).forward(req, resp);
  }

  @Override
  public void mapQuery(String queryXML, String geoEntityType) throws IOException, ServletException
  {
    String layers = MosquitoDTO.mapQuery(this.getClientRequest(), queryXML, geoEntityType);

    req.setAttribute("layers", layers);

    req.getRequestDispatcher(MAP_TEMPLATE).forward(req, resp);
  }

}
