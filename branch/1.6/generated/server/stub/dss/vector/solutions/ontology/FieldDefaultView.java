package dss.vector.solutions.ontology;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.constants.MdAttributeDimensionInfo;
import com.runwaysdk.dataaccess.BusinessDAO;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeRefDAOIF;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeDimensionDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttribute;

public class FieldDefaultView extends FieldDefaultViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1257265959240L;

  public FieldDefaultView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    applyDefaultValue();
  }

  @Override
  @Transaction
  public void delete()
  {
    deleteConcrete();
  }

  @Override
  @Authenticate
  public void applyDefaultValue()
  {
    MdAttribute mdAttribute = this.getMdAttribute();

    if (mdAttribute != null)
    {
      String attributeId = mdAttribute.getId();
      MdAttributeDAOIF mdAttributeDAOIF = (MdAttributeDAOIF) MdAttributeDAO.get(attributeId).getMdAttributeConcrete();

      if (mdAttributeDAOIF instanceof MdAttributeRefDAOIF)
      {
        MdDimensionDAOIF mdDimension = Session.getCurrentDimension();
        MdAttributeDimensionDAO mdAttributeDimension = mdAttributeDAOIF.getMdAttributeDimension(mdDimension).getBusinessDAO();

        Term term = this.getDefaultValue();

        if (term != null)
        {
          mdAttributeDimension.setValue(MdAttributeDimensionInfo.DEFAULT_VALUE, term.getId());
        }
        else
        {
          mdAttributeDimension.setValue(MdAttributeDimensionInfo.DEFAULT_VALUE, null);
        }

        mdAttributeDimension.apply();
      }
    }
  }

  @Override
  @Authenticate
  public void deleteConcrete()
  {
    String attributeId = this.getMdAttribute().getId();
    MdAttributeDAOIF businessDAOIF = (MdAttributeDAOIF) MdAttributeDAO.get(attributeId);
    BusinessDAO mdAttributeDAO = businessDAOIF.getMdAttributeConcrete().getBusinessDAO();

    if (mdAttributeDAO instanceof MdAttributeRefDAOIF)
    {
      MdAttributeDAOIF mdAttributeDAOIF = (MdAttributeDAOIF) mdAttributeDAO;
      MdDimensionDAOIF mdDimension = Session.getCurrentDimension();

      MdAttributeDimensionDAO mdAttributeDimension = mdAttributeDAOIF.getMdAttributeDimension(mdDimension).getBusinessDAO();
      mdAttributeDimension.setValue(MdAttributeDimensionInfo.DEFAULT_VALUE, "");
      mdAttributeDimension.apply();
    }
  }

}
