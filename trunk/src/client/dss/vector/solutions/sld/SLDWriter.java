package dss.vector.solutions.sld;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.terraframe.mojo.business.BusinessDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.LocalProperties;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.util.FileIO;

import dss.vector.solutions.query.AbstractCategoryDTO;
import dss.vector.solutions.query.AllRenderTypesDTO;
import dss.vector.solutions.query.LayerDTO;
import dss.vector.solutions.query.NonRangeCategoryDTO;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.RangeCategoryDTO;
import dss.vector.solutions.query.SavedMapDTO;
import dss.vector.solutions.query.StylesDTO;

public class SLDWriter implements Reloadable
{
  private SavedMapDTO map;
  
  private LayerDTO      layer;

  private StringBuilder builder;

  /**
   * Constructs a new SLDWriter for the given Layer.
   * 
   * @param layer
   */
  public SLDWriter(SavedMapDTO map, LayerDTO layer)
  {
    this.map = map;
    this.layer = layer;
    builder = new StringBuilder();
  }

  protected LayerDTO getLayer()
  {
    return layer;
  }
  
  private void writeSequence()
  {
    writeHeader(this.layer.getViewName());
    
    boolean asPoint = this.layer.getRenderAs().get(0).equals(AllRenderTypesDTO.POINT);

    String thematicUserAlias = layer.getThematicUserAlias();
    if (thematicUserAlias != null && thematicUserAlias.length() > 0)
    {
      // The layer is thematic, so write all the category styles/ranges.
      List<? extends AbstractCategoryDTO> categories = this.layer.getAllHasCategory();
      for(AbstractCategoryDTO category : categories)
      {
        Filter tFilter = new ThematicLabelFilter();
        Filter filter;
        if (category instanceof RangeCategoryDTO)
        {
          filter = new RangeFilter((RangeCategoryDTO) category);
        }
        else
        {
          filter = new NonRangeFilter((NonRangeCategoryDTO) category);
        }
        Filter cFilter = new CompositeFilter(tFilter, filter);

        StylesDTO categoryStyle = category.getStyles();
        
        Symbolizer sym = getSymbolizer(asPoint, categoryStyle);
        
        Symbolizer tSym;
        if(layer.getShowThematicValue())
        {
          tSym = new ThematicTextSymbolizer(categoryStyle);
        }
        else
        {
          tSym = new TextSymbolizer(categoryStyle);
        }
        
        // Write one rule for having the thematic value. If the
        // thematic value is null then it will get default styles
        Rule categoryRule = new Rule(cFilter, sym, tSym);
        categoryRule.write(this);
      }
      
      StylesDTO defaultStyle = layer.getDefaultStyles();
      Symbolizer sym = getSymbolizer(asPoint, defaultStyle);

      // Write the default styles for when the thematic value
      // is null. THIS MUST COME BEFORE NON-NULL THEMATIC VALUES
      TextSymbolizer textSym = new TextSymbolizer(defaultStyle);
      ThematicNullLabelFilter nFilter = new ThematicNullLabelFilter();
      Filter cFilter = new CompositeFilter(nFilter);
      Rule rule = new Rule(cFilter, sym, textSym);
      rule.write(this);

      // Write the rule that will include the thematic label.
      Symbolizer tSymbolizer;
      if(layer.getShowThematicValue())
      {
        tSymbolizer = new ThematicTextSymbolizer(defaultStyle);
      }
      else
      {
        tSymbolizer = new TextSymbolizer(defaultStyle);
      }
      ElseFilter elseFilter = new ElseFilter();
      Rule thematicRule = new Rule(elseFilter, sym, tSymbolizer);
      thematicRule.write(this);
    }
    else
    {
      // Default layer styles
      StylesDTO defaultStyle = layer.getDefaultStyles();
      Symbolizer sym = getSymbolizer(asPoint, defaultStyle);
      
      Rule rule = new Rule(null, sym, new TextSymbolizer(defaultStyle));
      rule.write(this);
    }
    
    writeFooter();
  }
  
  private Symbolizer getSymbolizer(boolean asPoint, StylesDTO style)
  {
    if(asPoint)
    {
      return new PointSymbolizer(style);
    }
    else
    {
      return new PolygonSymbolizer(style);
    }
  }

  public void write()
  {
    this.layer.lock();

    ClientRequestIF requestIF = this.layer.getRequest();

    writeSequence();
    
    String fileName = QueryConstants.createSLDName(layer.getId());
    String oldFileId = this.layer.getSldFile();
    
    // delete the previous file if it exists
    deleteExistingSLD(requestIF, QueryConstants.SLD_WEB_DIR, fileName, QueryConstants.SLD_EXTENSION, oldFileId);

    ByteArrayInputStream stream = new ByteArrayInputStream(builder.toString().getBytes());
    BusinessDTO webFile = requestIF.newFile(QueryConstants.SLD_WEB_DIR, fileName, QueryConstants.SLD_EXTENSION, stream);

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
