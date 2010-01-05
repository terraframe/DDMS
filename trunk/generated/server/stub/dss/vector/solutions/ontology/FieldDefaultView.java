package dss.vector.solutions.ontology;

import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.constants.MdAttributeConcreteInfo;
import com.terraframe.mojo.dataaccess.BusinessDAO;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF;
import com.terraframe.mojo.dataaccess.metadata.MdAttributeDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.system.metadata.MdAttribute;

public class FieldDefaultView extends FieldDefaultViewBase implements com.terraframe.mojo.generation.loader.Reloadable
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
      MdAttributeDAOIF businessDAOIF = (MdAttributeDAOIF) MdAttributeDAO.get(attributeId);
      BusinessDAO mdAttributeDAO = businessDAOIF.getMdAttributeConcrete().getBusinessDAO();

      if (mdAttributeDAO instanceof MdAttributeRefDAOIF)
      {
        Term term = this.getDefaultValue();

        if (term != null)
        {
          mdAttributeDAO.setValue(MdAttributeConcreteInfo.DEFAULT_VALUE, term.getId());
        }
        else
        {
          mdAttributeDAO.setValue(MdAttributeConcreteInfo.DEFAULT_VALUE, null);
        }

        mdAttributeDAO.apply();
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
      mdAttributeDAO.setValue(MdAttributeConcreteInfo.DEFAULT_VALUE, "");
      mdAttributeDAO.apply();
    }
  }

}
