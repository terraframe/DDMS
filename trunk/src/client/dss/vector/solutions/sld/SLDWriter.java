package dss.vector.solutions.sld;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import com.terraframe.mojo.business.BusinessDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.LocalProperties;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.util.FileIO;

import dss.vector.solutions.query.LayerDTO;
import dss.vector.solutions.query.QueryConstants;

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

  protected LayerDTO getLayer()
  {
    return layer;
  }

  public void write()
  {
    this.layer.lock();

    /* FIXME MAP
    
    TextStyleDTO textStyle = layer.getTextStyle();
    GeometryStyleDTO geoStyle = layer.getGeometryStyle();
    

    String viewName = layer.getViewName();
    writeHeader(viewName);
    writeGeometryStyle(geoStyle);
    writeTextStyle(textStyle);
    writeFooter();
    */

    ClientRequestIF requestIF = this.layer.getRequest();
    
    String path = "styles/";
    String fileName = "style_" + this.layer.getId().substring(0, 32);
    String extension = "sld";
    String oldFileId = this.layer.getSldFile();
    
    // delete the previous file if it exists
    deleteExistingSLD(requestIF, path, fileName, extension, oldFileId);

    // SLD name is the LayerDTO id
    // FIXME how to figure out path?
    ByteArrayInputStream stream = new ByteArrayInputStream(builder.toString().getBytes());
    BusinessDTO webFile = requestIF.newFile(path, fileName, extension, stream);

    // Lock this layer. This lock all objects used by this layer
    this.layer.setSldFile(webFile.getId());
    this.layer.apply();

    // Applying a layer only unlocks the individual layer not all of the objects
    // used in a layer. Therefore we must call the unlock method so that all of
    // the objects used in a layer are also unlocked.
    this.layer.unlock();
  }

  private void deleteExistingSLD(ClientRequestIF requestIF, String path, String fileName,
      String extension, String oldFileId)
  {
    if (oldFileId != null && oldFileId.trim().length() > 0)
    {
      requestIF.delete(oldFileId);

      // Ensure that the existing file artifact is deleted
      String rootPath = LocalProperties.getWebDirectory();
      String filepath = rootPath + path + fileName + "." + extension;
      
      File file = new File(filepath);
      
      if(file.exists())
      {
        try
        {
          FileIO.deleteFile(file);
        }
        catch (IOException e)
        {
          // TODO fix this
          throw new RuntimeException(e);
        }
      }
    }
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

  /*
  private void writeGeometryStyle(GeometryStyleDTO geoStyle)
  {
    Symbolizer symbolizer = Symbolizer.getGeometrySymbolizer(layer, geoStyle);
    symbolizer.write(this);
  }

  private void writeTextStyle(TextStyleDTO textStyle)
  {
    TextSymbolizer textSymbolizer = new TextSymbolizer(layer, textStyle);
    textSymbolizer.write(this);
  }
  */

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
}
