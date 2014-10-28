package dss.vector.solutions.query;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.constants.EnumerationMasterInfo;
import com.runwaysdk.dataaccess.BusinessDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF;
import com.runwaysdk.dataaccess.MdEnumerationDAOIF;

public class Styles extends StylesBase implements com.runwaysdk.generation.loader.Reloadable, StylesIF
{
  private static final long serialVersionUID = -1877137072;

  public Styles()
  {
    super();
  }

  @Override
  public boolean isEnumerationAttribute(String attributeName)
  {
    MdAttributeConcreteDAOIF mdAttribute = this.getMdAttributeDAO(attributeName);

    return ( mdAttribute instanceof MdAttributeEnumerationDAOIF );
  }

  @Override
  public StylesIF createMergedStyle()
  {
    return new Styles();
  }

  @Override
  public String getFontStylesName()
  {
    return this.getFontStyles().get(0).name().toLowerCase();
  }

  @Override
  public String getPointMarkerName()
  {
    WellKnownNames wkn = this.getPointMarker().get(0);
    String name = wkn.name().toLowerCase();

    return name;
  }

  @Override
  public List<String> getAttributeNames()
  {
    List<? extends MdAttributeConcreteDAOIF> mdAttributeDAOs = this.getMdAttributeDAOs();

    List<String> list = new LinkedList<String>();

    for (MdAttributeConcreteDAOIF mdAttribute : mdAttributeDAOs)
    {
      list.add(mdAttribute.definesAttribute());
    }

    return list;
  }

  @Override
  public List<String> getEnumNames(String attributeName)
  {
    MdAttributeEnumerationDAOIF mdAttribute = (MdAttributeEnumerationDAOIF) this.getMdAttributeDAO(attributeName);

    MdEnumerationDAOIF mdEnumerationDAO = mdAttribute.getMdEnumerationDAO();

    List<BusinessDAOIF> items = mdEnumerationDAO.getAllEnumItemsOrdered();

    List<String> list = new LinkedList<String>();

    for (BusinessDAOIF item : items)
    {
      list.add(item.getValue(EnumerationMasterInfo.NAME));
    }

    return list;
  }

}
