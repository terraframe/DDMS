package dss.vector.solutions.sld;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.runwaysdk.business.BusinessDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.constants.LocalProperties;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO;
import com.runwaysdk.util.FileIO;

import dss.vector.solutions.query.AbstractCategoryDTO;
import dss.vector.solutions.query.AllRenderTypesDTO;
import dss.vector.solutions.query.LayerDTO;
import dss.vector.solutions.query.NonRangeCategoryDTO;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.RangeCategoryDTO;
import dss.vector.solutions.query.StylesDTO;

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
  
  /**
   * Merges the properties of the default and overriden styles.
   * 
   * @param mergedStyle
   * @param categoryStyle
   * @param defaultStyle
   */
  private void mergeStyles(StylesDTO mergedStyle, StylesDTO categoryStyle, StylesDTO defaultStyle)
  {
    for(String name : mergedStyle.getAttributeNames())
    {
      if(name.startsWith(QueryConstants.CATEGORY_OVERRIDE_PREPEND))
      {
        String styleName = name.substring(QueryConstants.CATEGORY_OVERRIDE_PREPEND.length());
        Boolean doOverride = Boolean.parseBoolean(categoryStyle.getValue(name));
        
        if(mergedStyle.getAttributeMd(styleName) instanceof AttributeEnumerationMdDTO)
        {
          String enumName = doOverride ? categoryStyle.getEnumNames(styleName).get(0) : defaultStyle.getEnumNames(styleName).get(0);
          mergedStyle.addEnumItem(styleName, enumName);
        }
        else
        {
          String value = doOverride ? categoryStyle.getValue(styleName) : defaultStyle.getValue(styleName);
          mergedStyle.setValue(styleName, value);
        }
      }
    }
  }
  
  private void writeSequence()
  {
    writeHeader(this.layer.getViewName());
    
    boolean asPoint = this.layer.getRenderAs().get(0).equals(AllRenderTypesDTO.POINT);

    if (layer.hasThematicVariable())
    {
      // The layer is thematic, so write all the category styles/ranges.
      StylesDTO defaultStyle = layer.getDefaultStyles();
      
      // This mergedStyle object will be reused to represent the styles
      // defined by the layer (the default) and the overridden styles on
      // the category.
      StylesDTO mergedStyle = new StylesDTO(defaultStyle.getRequest());
      List<? extends AbstractCategoryDTO> categories = this.layer.getAllHasCategory();
      for(AbstractCategoryDTO category : categories)
      {
        StylesDTO categoryStyle = category.getStyles();
        mergeStyles(mergedStyle, categoryStyle, defaultStyle);
        
        Filter tFilter = new ThematicLabelFilter(layer);
        Filter filter;
        if (category instanceof RangeCategoryDTO)
        {
          filter = new RangeFilter(layer, (RangeCategoryDTO) category);
        }
        else
        {
          filter = new NonRangeFilter(layer, (NonRangeCategoryDTO) category);
        }
        Filter cFilter = new CompositeFilter(layer, tFilter, filter);

        
        Symbolizer sym = getSymbolizer(asPoint, mergedStyle);
        
        Symbolizer tSym;
        if(layer.getShowThematicValue())
        {
          tSym = new ThematicTextSymbolizer(layer, mergedStyle);
        }
        else
        {
          tSym = new TextSymbolizer(mergedStyle);
        }
        
        // Write one rule for having the thematic value. If the
        // thematic value is null then it will get default styles
        Rule categoryRule = new Rule(cFilter, sym, tSym);
        categoryRule.write(this);
      }
      
      Symbolizer sym = getSymbolizer(asPoint, defaultStyle);

      // Write the default styles for when the thematic value
      // is null. THIS MUST COME BEFORE NON-NULL THEMATIC VALUES
      TextSymbolizer textSym = new TextSymbolizer(defaultStyle);
      ThematicNullLabelFilter nFilter = new ThematicNullLabelFilter(layer);
      Filter cFilter = new CompositeFilter(layer, nFilter);
      Rule rule = new Rule(cFilter, sym, textSym);
      rule.write(this);

      // Write the rule that will include the thematic label.
      Symbolizer tSymbolizer;
      if(layer.getShowThematicValue())
      {
        tSymbolizer = new ThematicTextSymbolizer(layer, defaultStyle);
      }
      else
      {
        tSymbolizer = new TextSymbolizer(defaultStyle);
      }
      ElseFilter elseFilter = new ElseFilter(layer);
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
    ClientRequestIF requestIF = this.layer.getRequest();

    writeSequence();
    
    String fileName = QueryConstants.createSLDName(layer.getId());
    String oldFileId = this.layer.getSldFile();
    
    // delete the previous file if it exists
    deleteExistingSLD(requestIF, QueryConstants.SLD_WEB_DIR, fileName, QueryConstants.SLD_EXTENSION, oldFileId);

    ByteArrayInputStream stream = new ByteArrayInputStream(builder.toString().getBytes());
    BusinessDTO webFile = requestIF.newFile(QueryConstants.SLD_WEB_DIR, fileName, QueryConstants.SLD_EXTENSION, stream);

    // Lock this layer. This lock all objects used by this layer
    this.layer.updateSLDFile(webFile.getId());
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
