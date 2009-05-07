package dss.vector.solutions.sld;

import java.io.ByteArrayInputStream;

import com.terraframe.mojo.business.BusinessDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.GeometryStyleDTO;
import dss.vector.solutions.query.LayerDTO;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.TextStyleDTO;
import dss.vector.solutions.query.ThematicLayerDTO;
import dss.vector.solutions.query.UniversalLayerDTO;

public abstract class SLDWriter implements Reloadable
{
  private LayerDTO      layer;

  private StringBuilder builder;

  /**
   * Constructs a new SLDWriter for the given Layer.
   *
   * @param layer
   */
  protected SLDWriter(LayerDTO layer)
  {
    this.layer = layer;
    builder = new StringBuilder();
  }

  protected LayerDTO getLayer()
  {
    return layer;
  }

  public void write()
  {
    GeometryStyleDTO geoStyle = layer.getGeometryStyle();
    TextStyleDTO textStyle = layer.getTextStyle();

    String viewName = layer.getViewName();
    writeHeader(viewName);
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

  private void writeHeader(String viewName)
  {
    writeln("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
    writeln("<StyledLayerDescriptor version=\"1.0.0\" xmlns=\"http://www.opengis.net/sld\" xmlns:ogc=\"http://www.opengis.net/ogc\"");
    writeln("xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
    writeln("xsi:schemaLocation=\"http://www.opengis.net/sld http://schemas.opengis.net/sld/1.0.0/StyledLayerDescriptor.xsd\">");
    writeln("<NamedLayer>");
    writeln("<Name>" + QueryConstants.MDSS_NAMESPACE + ":" + viewName + "</Name>");
    writeln("<UserStyle>");
    writeln("<Title>Layer Style for " + viewName + "</Title>");
    writeln("<Abstract>Layer Style for " + viewName + "</Abstract>");
    writeln("<FeatureTypeStyle>");
  }

  private void writeGeometryStyle(GeometryStyleDTO geoStyle)
  {
    Symbolizer symbolizer = Symbolizer.getGeometrySymbolizer(layer, geoStyle);
    symbolizer.write(this);
  }

  protected void writeTextStyle(TextStyleDTO textStyle)
  {
    TextSymbolizer textSymbolizer = new TextSymbolizer(layer, textStyle, false);
    textSymbolizer.write(this);
  }

  private void writeFooter()
  {
    writeln("</FeatureTypeStyle>");
    writeln("</UserStyle>");
    writeln("</NamedLayer>");
    writeln("</StyledLayerDescriptor>");
  }

  protected void write(String line)
  {
    builder.append(line);
  }

  protected void writeln(String line)
  {
    builder.append(line + "\n");
  }

  public static SLDWriter getSLDWriter(LayerDTO layer)
  {
    if(layer instanceof ThematicLayerDTO)
    {
      return new ThematicSLDWriter((ThematicLayerDTO) layer);
    }
    else
    {
      return new UniversalSLDWriter((UniversalLayerDTO) layer);
    }
  }
}
