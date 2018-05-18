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
import com.runwaysdk.constants.Constants;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDateDAOIF;
import com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF;
import com.runwaysdk.dataaccess.MdAttributePrimitiveDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.export.GeoExcelColumn;
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
  }

  private DateFormat odkFormat;

  private DateFormat runwayFormat;

  public ODKDataConverter()
  {
    this.odkFormat = new SimpleDateFormat("yyyy-MM-dd");
    this.runwayFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
  }

  public List<ODKRow> convert(ODKForm form, Node node, List<ExcelColumn> extraColumns)
  {
    MdClassDAOIF mdType = form.getViewMd();
    ODKRow root = new ODKRow(BusinessFacade.newMutable(mdType.definesType()));

    return convert(root, form, node, extraColumns);
  }

  private List<ODKRow> convert(ODKRow root, ODKForm form, Node node, List<ExcelColumn> extraColumns)
  {
    List<ODKRow> rows = new LinkedList<ODKRow>();
    List<Node> repeats = new LinkedList<Node>();

    NodeList children = node.getChildNodes();

    for (int i = 0; i < children.getLength(); i++)
    {
      Node child = children.item(i);
      String sourceAttribute = child.getNodeName();

      if (form.hasViewAttribute(sourceAttribute))
      {
        String attributeName = sourceAttribute;

        MdAttributeDAOIF mdAttribute = root.getMdAttributeDAO(attributeName);
        String value = this.getValue(mdAttribute, child.getTextContent());

        root.setValue(attributeName, value);
      }
      else if (form.isGeoAttribute(sourceAttribute))
      {
        String[] split = sourceAttribute.split("_geolist_");

        String base = split[0];
        int index = Integer.parseInt(split[1]);
        String value = child.getTextContent();

        ExcelColumn column = getColumn(extraColumns, base, index);

        root.setOverride(column.getAttributeName(), value);
      }
      else if (form.isRepeatable(sourceAttribute))
      {
        repeats.add(child);
      }
    }

    if (repeats.size() == 0)
    {
      rows.add(root);
    }
    else
    {
      for (Node repeat : repeats)
      {
        String sourceAttribute = repeat.getNodeName();

        ODKForm repeatable = form.getRepeatable(sourceAttribute);

        rows.addAll(this.convert(root.clone(), repeatable, repeat, extraColumns));
      }
    }

    return rows;
  }

  private ExcelColumn getColumn(List<ExcelColumn> extraColumns, String base, int index)
  {
    for (int i = 0; i < extraColumns.size(); i++)
    {
      ExcelColumn column = extraColumns.get(i);

      if (column instanceof GeoExcelColumn && ( (GeoExcelColumn) column ).getBaseAttribute().equals(base))
      {
        return extraColumns.get(i + index);
      }
    }

    return extraColumns.get(index);
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
