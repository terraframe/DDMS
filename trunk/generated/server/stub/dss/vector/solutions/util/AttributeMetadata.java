package dss.vector.solutions.util;

import com.runwaysdk.dataaccess.MdAttributeCharDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeNumberDAOIF;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

public class AttributeMetadata extends AttributeMetadataBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 2075220649;

  public AttributeMetadata()
  {
    super();
  }

  public static boolean isBarcode(MdAttributeDAOIF mdAttribute)
  {
    AttributeMetadata metadata = AttributeMetadata.getMetadata(mdAttribute);

    if (metadata != null)
    {
      return metadata.getBarcode();
    }

    return false;
  }

  public static boolean isValidBarcode(MdAttributeDAOIF mdAttribute)
  {
    if (AttributeMetadata.isBasic(mdAttribute))
    {
      return AttributeMetadata.isBarcode(mdAttribute);
    }

    return false;
  }

  public static AttributeMetadata getMetadata(MdAttributeDAOIF mdAttribute)
  {
    AttributeMetadataQuery query = new AttributeMetadataQuery(new QueryFactory());
    query.WHERE(query.getReferencedMdAttribute().EQ(mdAttribute.getId()));

    OIterator<? extends AttributeMetadata> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }
    }
    finally
    {
      it.close();
    }

    return null;
  }

  public static Boolean isBasic(MdAttributeDAOIF mdAttribute)
  {
    MdAttributeConcreteDAOIF mdAttributeConcrete = mdAttribute.getMdAttributeConcrete();

    return ( ( mdAttributeConcrete instanceof MdAttributeNumberDAOIF ) || ( mdAttributeConcrete instanceof MdAttributeCharDAOIF ) );
  }

}
