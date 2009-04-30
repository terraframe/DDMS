package dss.vector.solutions.sld;

import java.io.ByteArrayInputStream;

import com.terraframe.mojo.business.BusinessDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.system.metadata.MdBusinessDTO;

import dss.vector.solutions.geo.GeoHierarchyDTO;
import dss.vector.solutions.query.GeometryStyleDTO;
import dss.vector.solutions.query.LayerDTO;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.TextStyleDTO;

public class SLDWriter implements Reloadable
{
  private LayerDTO      layer;

  private StringBuilder builder;

  /**
   * Constructs a new SLDWriter for the given Layer.
   *
   * @param layer
   */
  public SLDWriter(LayerDTO layer)
  {
    this.layer = layer;
    builder = new StringBuilder();
  }

  public void write()
  {
    // FIXME user view to avoid multiple trips to server
    GeoHierarchyDTO geoH = layer.getGeoHierarchy();
    MdBusinessDTO md = geoH.getGeoEntityClass();
    GeometryStyleDTO geoStyle = layer.getGeometryStyle();
    TextStyleDTO textStyle = layer.getTextStyle();

    String layerName = QueryConstants.MDSS_NAMESPACE + ":" + md.getTypeName().toLowerCase()
        + QueryConstants.VIEW_NAME_SUFFIX;
    writeHeader(layerName);
    writeGeometryStyle(geoStyle);
    writeTextStyle(textStyle);
    writeFooter();

    ClientRequestIF requestIF = this.layer.getRequest();

    // SLD name is the LayerDTO id
    // FIXME how to figure out path?
    String path = "styles/";
    String fileName = "style_" + this.layer.getId().substring(0, 32);
    ByteArrayInputStream stream = new ByteArrayInputStream(builder.toString().getBytes());
    BusinessDTO file = requestIF.newFile(path, fileName, "sld", stream);

    this.layer.lock();
    this.layer.setSldFile(file.getId());
    this.layer.apply();
  }

  private void writeHeader(String layerName)
  {
    writeln("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
    writeln("<StyledLayerDescriptor version=\"1.0.0\" xmlns=\"http://www.opengis.net/sld\" xmlns:ogc=\"http://www.opengis.net/ogc\"");
    writeln("xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
    writeln("xsi:schemaLocation=\"http://www.opengis.net/sld http://schemas.opengis.net/sld/1.0.0/StyledLayerDescriptor.xsd\">");
    writeln("<NamedLayer>");
    writeln("<Name>" + layerName + "</Name>");
    writeln("<UserStyle>");
    writeln("<Title>Layer Style for " + layerName + "</Title>");
    writeln("<Abstract>Layer Style for " + layerName + "</Abstract>");
    writeln("<FeatureTypeStyle>");
  }

  private void writeGeometryStyle(GeometryStyleDTO geoStyle)
  {
    Symbolizer symbolizer = Symbolizer.getSymbolizer(geoStyle);
    symbolizer.write(this);
  }

  private void writeTextStyle(TextStyleDTO textStyle)
  {
    TextSymbolizer textSymbolizer = new TextSymbolizer(textStyle);
    textSymbolizer.write(this);
  }

  private void writeFooter()
  {
    writeln("</FeatureTypeStyle>");
    writeln("</UserStyle>");
    writeln("</NamedLayer>");
    writeln("</StyledLayerDescriptor>");
  }

  protected void writeln(String line)
  {
    builder.append(line + "\n");
  }
}
