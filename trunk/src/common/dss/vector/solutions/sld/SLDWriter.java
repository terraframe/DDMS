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
package dss.vector.solutions.sld;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.constants.LocalProperties;
import com.runwaysdk.dataaccess.io.MarkupWriter;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.util.FileIO;

import dss.vector.solutions.query.AbstractCategoryIF;
import dss.vector.solutions.query.LayerIF;
import dss.vector.solutions.query.MapConfigurationIF;
import dss.vector.solutions.query.NonRangeCategoryIF;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.RangeCategoryIF;
import dss.vector.solutions.query.RequestFacadeIF;
import dss.vector.solutions.query.StylesIF;

public class SLDWriter extends MarkupWriter implements Reloadable
{
  private LayerIF            layer;

  private MapConfigurationIF configuration;

  /**
   * Constructs a new SLDWriter for the given Layer.
   * 
   * @param layer
   */
  public SLDWriter(LayerIF layer)
  {
    this(layer, null);
  }

  public SLDWriter(LayerIF layer, MapConfigurationIF configuration)
  {
    super(new StringWriter());

    this.layer = layer;
    this.configuration = configuration;
  }

  protected LayerIF getLayer()
  {
    return layer;
  }

  @Override
  protected StringWriter getWriter()
  {
    return (StringWriter) super.getWriter();
  }

  /**
   * Merges the properties of the default and overriden styles.
   * 
   * @param mergedStyle
   * @param categoryStyle
   * @param defaultStyle
   */
  private void mergeStyles(StylesIF mergedStyle, StylesIF categoryStyle, StylesIF defaultStyle)
  {
    for (String name : mergedStyle.getAttributeNames())
    {
      if (name.startsWith(QueryConstants.CATEGORY_OVERRIDE_PREPEND))
      {
        String styleName = name.substring(QueryConstants.CATEGORY_OVERRIDE_PREPEND.length());
        Boolean doOverride = Boolean.parseBoolean(categoryStyle.getValue(name));

        if (mergedStyle.isEnumerationAttribute(styleName))
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
    writeHeader(this.getViewName());

    boolean asPoint = this.layer.isPoint();

    if (layer.hasThematicVariable())
    {
      // The layer is thematic, so write all the category styles/ranges.
      StylesIF defaultStyle = layer.getDefaultStyles();

      // This mergedStyle object will be reused to represent the styles
      // defined by the layer (the default) and the overridden styles on
      // the category.

      StylesIF mergedStyle = defaultStyle.createMergedStyle();

      List<AbstractCategoryIF> categories = this.layer.getAllCategories();

      for (AbstractCategoryIF category : categories)
      {
        StylesIF categoryStyle = category.getStyles();
        mergeStyles(mergedStyle, categoryStyle, defaultStyle);

        Filter tFilter = new ThematicLabelFilter(layer);
        Filter filter;

        if (category instanceof RangeCategoryIF)
        {
          filter = new RangeFilter(layer, (RangeCategoryIF) category);
        }
        else
        {
          filter = new NonRangeFilter(layer, (NonRangeCategoryIF) category);
        }
        Filter cFilter = new CompositeFilter(layer, tFilter, filter);

        Symbolizer sym = getSymbolizer(asPoint, mergedStyle);

        Symbolizer tSym;
        if (layer.getShowThematicValue())
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
      if (layer.getShowThematicValue())
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
      StylesIF defaultStyle = layer.getDefaultStyles();
      Symbolizer sym = getSymbolizer(asPoint, defaultStyle);

      Rule rule = new Rule(null, sym, new TextSymbolizer(defaultStyle));
      rule.write(this);
    }

    writeFooter();
  }

  private Symbolizer getSymbolizer(boolean asPoint, StylesIF style)
  {
    if (asPoint)
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
    RequestFacadeIF requestIF = this.layer.getRequestFacade();

    writeSequence();

    String fileName = QueryConstants.createSLDName(layer.getId());
    String oldFileId = this.layer.getSldFile();

    // delete the previous file if it exists
    deleteExistingSLD(requestIF, QueryConstants.SLD_WEB_DIR, fileName, QueryConstants.SLD_EXTENSION, oldFileId);

    byte[] bytes = getWriter().toString().getBytes();

    ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
    String fileId = requestIF.newFile(QueryConstants.SLD_WEB_DIR, fileName, QueryConstants.SLD_EXTENSION, stream);

    // Lock this layer. This lock all objects used by this layer
    this.layer.updateFile(fileId);
  }

  private void deleteExistingSLD(RequestFacadeIF requestIF, String path, String fileName, String extension, String oldFileId)
  {
    if (oldFileId != null && oldFileId.trim().length() > 0)
    {
      requestIF.delete(oldFileId);

      // Ensure that the existing file artifact is deleted
      String rootPath = LocalProperties.getWebDirectory();
      String filepath = rootPath + path + fileName + "." + extension;

      File file = new File(filepath);

      if (file.exists())
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

  public void writeEmptyTagWithValue(String tag, String value)
  {
    writeTagSingleValue(tag, value);
  }

  public void writeTagWithValue(String tag, HashMap<String, String> attributes, String value)
  {
    writeTagSingleValue(tag, value, attributes);
  }

  public void writeTagWithValue(String tag, String attributeName, String attributeValue, String value)
  {
    HashMap<String, String> attributes = new HashMap<String, String>();
    attributes.put(attributeName, attributeValue);

    writeTagWithValue(tag, attributes, value);
  }

  private void writeHeader(String viewName)
  {
    this.writeValue("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");

    HashMap<String, String> attributes = new HashMap<String, String>();
    attributes.put("version", "1.0.0");
    attributes.put("xmlns", "http://www.opengis.net/sld");
    attributes.put("xmlns:ogc", "http://www.opengis.net/ogc");
    attributes.put("xmlns:xlink", "http://www.w3.org/1999/xlink");
    attributes.put("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
    attributes.put("xsi:schemaLocation", "http://www.opengis.net/sld http://schemas.opengis.net/sld/1.0.0/StyledLayerDescriptor.xsd");

    openTag("StyledLayerDescriptor", attributes);
    openTag("NamedLayer");
    writeEmptyTagWithValue("Name", CommonProperties.getDeployAppName() + ":" + viewName);
    openTag("UserStyle");
    writeEmptyTagWithValue("Title", "Layer Style for " + viewName);
    writeEmptyTagWithValue("Abstract", "Layer Style for " + viewName);
    openTag("FeatureTypeStyle");
  }

  private void writeFooter()
  {
    // Close FeatureTypeStyle
    closeTag();
    // Close UserStyle
    closeTag();
    // Close NamedLayer
    closeTag();
    // Close StyledLayerDescriptor
    closeTag();
  }

  @Override
  protected void throwIOException(IOException e)
  {
    throw new RuntimeException(e);
  }

  public String getViewName()
  {
    String viewName = this.layer.getViewName();

    if (this.configuration != null)
    {
      viewName = this.configuration.getViewName(this.layer);
    }

    return viewName;
  }
}
