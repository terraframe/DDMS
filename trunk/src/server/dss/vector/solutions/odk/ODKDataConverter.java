package dss.vector.solutions.odk;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.Mutable;
import com.runwaysdk.business.MutableWithStructs;
import com.runwaysdk.constants.Constants;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDateDAOIF;
import com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF;
import com.runwaysdk.dataaccess.MdAttributePrimitiveDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdAttributeStructDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdStructDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.ExcelExportSheet;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.export.GeoExcelColumn;
import dss.vector.solutions.export.GridExcelColumn;
import dss.vector.solutions.ontology.Term;

public class ODKDataConverter implements Reloadable
{
  public static class ODKRow implements Reloadable
  {
    private Mutable             mutable;

    private Map<String, String> overrides;

    public ODKRow(Mutable mutable)
    {
      this.mutable = mutable;
      this.overrides = new HashMap<>();
    }

    public Mutable getMutable()
    {
      return mutable;
    }

    public Map<String, String> getOverrides()
    {
      return overrides;
    }

    public void setValue(String name, String value)
    {
      this.mutable.setValue(name, value);
    }

    public void setStructValue(String structName, String attributeName, String value)
    {
      ( (MutableWithStructs) this.mutable ).setStructValue(structName, attributeName, value);
    }

    public void setOverride(String name, String value)
    {
      this.overrides.put(name, value);
    }

    private void addItem(String name, String value)
    {
      this.mutable.addEnumItem(name, value);
    }

    public MdAttributeDAOIF getMdAttributeDAO(String attributeName)
    {
      return this.mutable.getMdAttributeDAO(attributeName);
    }

    public MdAttributeDAOIF getStructMdAttributeDAO(String structName, String sourceAttribute)
    {
      MdAttributeStructDAOIF mdAttributeStruct = (MdAttributeStructDAOIF) this.mutable.getMdAttributeDAO(structName).getMdAttributeConcrete();
      MdStructDAOIF mdStruct = mdAttributeStruct.getMdStructDAOIF();

      return mdStruct.definesAttribute(sourceAttribute);
    }

    public boolean hasAttribute(String attributeName)
    {
      return this.mutable.hasAttribute(attributeName);
    }

    public ODKRow clone()
    {
      ODKRow clone = new ODKRow(BusinessFacade.newMutable(mutable.getType()));

      List<? extends MdAttributeDAOIF> mdAttributes = this.mutable.getMdAttributeDAOs();

      for (MdAttributeDAOIF mdAttribute : mdAttributes)
      {
        MdAttributeConcreteDAOIF mdAttributeConcrete = mdAttribute.getMdAttributeConcrete();

        if (!mdAttributeConcrete.isSystem())
        {
          String name = mdAttribute.definesAttribute();

          if (mdAttributeConcrete instanceof MdAttributeEnumerationDAOIF)
          {
            clone.addItem(name, this.mutable.getValue(name));
          }
          else
          {
            clone.setValue(name, this.mutable.getValue(name));
          }
        }
      }

      Set<Entry<String, String>> entries = this.overrides.entrySet();

      for (Entry<String, String> entry : entries)
      {
        clone.setOverride(entry.getKey(), entry.getValue());
      }

      return clone;
    }

    public String getType()
    {
      return this.mutable.getType();
    }
  }

  private DateFormat odkFormat;

  private DateFormat runwayFormat;

  public ODKDataConverter()
  {
    this.odkFormat = new SimpleDateFormat("yyyy-MM-dd");
    this.runwayFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
  }

  public List<ODKRow> convert(String uuid, ODKForm form, Node node, Map<String, ExcelExportSheet> sheets)
  {
    MdClassDAOIF mdType = form.getViewMd();

    ODKRow root = new ODKRow(BusinessFacade.newMutable(mdType.definesType()));
    root.setOverride("_UUID_", uuid);

    return convert(root, form, node, sheets);
  }

  private List<ODKRow> convert(ODKRow root, ODKForm form, Node node, Map<String, ExcelExportSheet> sheets)
  {
    List<ODKRow> rows = new LinkedList<ODKRow>();
    List<Node> repeats = new LinkedList<Node>();

    List<ExcelColumn> extraColumns = this.getExtraColumns(root, form, sheets);

    NodeList children = node.getChildNodes();

    for (int i = 0; i < children.getLength(); i++)
    {
      Node child = children.item(i);
      String sourceAttribute = child.getNodeName();

      if (root.hasAttribute(sourceAttribute))
      {
        MdAttributeDAOIF mdAttribute = root.getMdAttributeDAO(sourceAttribute);
        String value = this.getValue(mdAttribute, child.getTextContent());

        root.setValue(sourceAttribute, value);
      }
      else if (form.isStructAttribute(sourceAttribute))
      {
        ODKStructAttribute struct = form.getStructAttribute(sourceAttribute);
        String structName = struct.getAttributeName();

        MdAttributeDAOIF mdAttribute = root.getStructMdAttributeDAO(structName, sourceAttribute);
        String value = this.getValue(mdAttribute, child.getTextContent());

        root.setStructValue(structName, sourceAttribute, value);
      }
      else if (form.isGridAttribute(sourceAttribute))
      {
        String value = child.getTextContent();

        if (value != null && value.length() > 0)
        {
          String[] split = sourceAttribute.replaceFirst(ODKGridAttribute.GRID_ATTR_PREFIX, "").split("\\.");

          String gridAttribute = split[0];
          String termId = split[1];
          String subAttribute = ( split.length > 2 ) ? split[2] : null;

          ExcelColumn column = getGridColumn(extraColumns, gridAttribute, termId, subAttribute);

          root.setOverride(column.getAttributeName(), value);
        }
      }
      else if (form.isGeoAttribute(sourceAttribute))
      {
        String[] split = sourceAttribute.split(ODKGeoAttribute.PREFIX);

        String base = split[0];
        String value = child.getTextContent();

        if (value != null && value.length() > 0)
        {
          int index = value.lastIndexOf("##");
          String geoId = value.substring(0, index);
          String universalId = value.substring(index + 2);

          ExcelColumn column = getGeoColumn(extraColumns, base, universalId);

          root.setOverride(column.getAttributeName(), geoId);
        }
      }
      else if (form.isStandalone(sourceAttribute))
      {
        ODKForm standalone = form.getRepeatable(sourceAttribute);
        String uuid = root.getOverrides().get("_UUID_");

        rows.addAll(this.convert(uuid, standalone, child, sheets));
      }
      else if (form.isRepeatable(sourceAttribute))
      {
        repeats.add(child);
      }
    }

    if (repeats.size() == 0 && form.isExport())
    {
      rows.add(root);
    }
    else if (form.isExport())
    {
      for (Node repeat : repeats)
      {
        String sourceAttribute = repeat.getNodeName();

        ODKForm repeatable = form.getRepeatable(sourceAttribute);

        rows.addAll(this.convert(root.clone(), repeatable, repeat, sheets));
      }
    }

    return rows;
  }

  private List<ExcelColumn> getExtraColumns(ODKRow root, ODKForm form, Map<String, ExcelExportSheet> sheets)
  {
    if (form.isExport())
    {
      ExcelExportSheet sheet = sheets.get(root.getType());

      return sheet.getExtraColumns();
    }

    return new LinkedList<>();
  }

  private ExcelColumn getGeoColumn(List<ExcelColumn> extraColumns, String base, String universalId)
  {
    for (ExcelColumn column : extraColumns)
    {
      if (column instanceof GeoExcelColumn)
      {
        GeoExcelColumn geoColumn = (GeoExcelColumn) column;

        if (geoColumn.getBaseAttribute().equals(base) && geoColumn.getUniversalId().equals(universalId))
        {
          return column;
        }
      }
    }

    return null;
  }

  private ExcelColumn getGridColumn(List<ExcelColumn> extraColumns, String gridAttribute, String termId, String subAttribute)
  {
    String desanitize = termId.replaceAll("_", ":");

    for (ExcelColumn column : extraColumns)
    {
      if (column instanceof GridExcelColumn)
      {
        GridExcelColumn geoColumn = (GridExcelColumn) column;

        if (geoColumn.isColumn(gridAttribute, termId, subAttribute) || geoColumn.isColumn(gridAttribute, desanitize, subAttribute))
        {
          return column;
        }
      }
    }

    return null;
  }

  private String getValue(MdAttributeDAOIF mdAttribute, String textContent)
  {
    if (textContent != null && textContent.length() > 0)
    {
      MdAttributeConcreteDAOIF mdAttributeConcrete = mdAttribute.getMdAttributeConcrete();

      if (mdAttributeConcrete instanceof MdAttributeDateDAOIF)
      {
        try
        {
          Date date = this.odkFormat.parse(textContent);

          return this.runwayFormat.format(date);
        }
        catch (ParseException e)
        {
          throw new ProgrammingErrorException(e);
        }
      }
      else if (mdAttributeConcrete instanceof MdAttributePrimitiveDAOIF)
      {
        return textContent;
      }
      else if (mdAttributeConcrete instanceof MdAttributeReferenceDAOIF)
      {
        MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttributeConcrete;
        MdBusinessDAOIF referenceMdBusiness = mdAttributeReference.getReferenceMdBusinessDAO();

        if (referenceMdBusiness.definesType().equals(Term.CLASS))
        {
          return Term.getByTermId(textContent).getId();
        }
        else
        {
          return null;
        }
      }
      else
      {
        throw new ProgrammingErrorException("Unsupported attribute type [" + mdAttribute.definesAttribute() + ", " + mdAttribute.getClass().getName() + "]");
      }
    }

    return textContent;
  }
}
