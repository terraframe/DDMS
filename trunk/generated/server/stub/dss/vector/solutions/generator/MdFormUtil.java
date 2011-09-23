package dss.vector.solutions.generator;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeRefDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.MdTypeDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdFormDAO;
import com.runwaysdk.dataaccess.metadata.MdRelationshipDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdRelationship;
import com.runwaysdk.system.metadata.MdWebAttribute;
import com.runwaysdk.system.metadata.MdWebField;
import com.runwaysdk.system.metadata.MdWebForm;
import com.runwaysdk.system.metadata.MdWebFormQuery;
import com.runwaysdk.system.metadata.MdWebPrimitive;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.form.DDMSFieldBuilders;
import dss.vector.solutions.form.MdFieldTypeQuery;
import dss.vector.solutions.geo.GeoHierarchy;

public class MdFormUtil extends MdFormUtilBase implements com.runwaysdk.generation.loader.Reloadable
{
  static class FieldComparator implements Reloadable, Comparator<MdWebField>
  {
    @Override
    public int compare(MdWebField field1, MdWebField field2)
    {
      Integer order1 = field1.getFieldOrder();
      Integer order2 = field2.getFieldOrder();

      return order1.compareTo(order2);
    }
  }

  private static final long serialVersionUID = 1220565977;

  public MdFormUtil()
  {
    super();
  }

  /**
   * Returns all available MdField types that can be created in MDSS.
   * 
   * @return
   */
  public static MdFieldTypeQuery getAvailableFields()
  {
    QueryFactory f = new QueryFactory();
    MdFieldTypeQuery q = new MdFieldTypeQuery(f);
    return q;
  }

  /**
   * Creates an MdField and the associated MdAttribute in DDMS. The mapping is
   * one-to-one.
   * 
   * @param mdField
   * @param mdFormId
   * @return
   */
  @Transaction
  public static com.runwaysdk.system.metadata.MdField createMdField(com.runwaysdk.system.metadata.MdField mdField, java.lang.String mdFormId)
  {
    DDMSFieldBuilders.create(mdField, mdFormId);
    
    return mdField;
  }
  
  public static MdWebForm[] getAllForms()
  {
    MdWebFormQuery query = new MdWebFormQuery(new QueryFactory());

    OIterator<? extends MdWebForm> it = query.getIterator();

    try
    {
      List<? extends MdWebForm> forms = it.getAll();

      return forms.toArray(new MdWebForm[forms.size()]);
    }
    finally
    {
      it.close();
    }
  }

  public static MdWebForm getForm(String type)
  {
    MdTypeDAOIF mdTypeDAO = MdFormDAO.getMdTypeDAO(type);

    return MdWebForm.get(mdTypeDAO.getId());
  }

  public static MdWebField[] getFields(MdWebForm form)
  {
    OIterator<? extends MdWebField> iterator = form.getAllMdFields();

    try
    {
      List<? extends MdWebField> fields = iterator.getAll();

      Collections.sort(fields, new FieldComparator());

      return fields.toArray(new MdWebField[fields.size()]);
    }
    finally
    {
      iterator.close();
    }
  }

  public static MdRelationship getMdRelationship(MdWebField field)
  {
    String relationshipType = MdFormUtil.getRelationshipType(field);

    MdRelationshipDAOIF mdRelationship = MdRelationshipDAO.getMdRelationshipDAO(relationshipType);

    return MdRelationship.get(mdRelationship.getId());
  }

  private static String getRelationshipType(MdWebField field)
  {
    MdWebForm mdForm = field.getDefiningMdForm();
    MdClass formMdClass = mdForm.getFormMdClass();
    MdTypeDAOIF mdClass = MdClassDAO.get(formMdClass.getId());

    String fieldName = field.getFieldName();
    String relationshipType = mdClass.definesType() + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    return relationshipType;
  }

  public static MdAttributeConcrete[] definesAttributes(MdRelationship mdRelationship)
  {
    List<MdAttributeConcrete> definedAttributes = new LinkedList<MdAttributeConcrete>();
    MdRelationshipDAOIF mdRelationshipDAO = MdRelationshipDAO.get(mdRelationship.getId());

    List<? extends MdAttributeConcreteDAOIF> mdAttributes = mdRelationshipDAO.getAllDefinedMdAttributes();

    for (MdAttributeConcreteDAOIF mdAttribute : mdAttributes)
    {
      if (!mdAttribute.isSystem() && ! ( mdAttribute instanceof MdAttributeRefDAOIF ))
      {
        definedAttributes.add(MdAttributeConcrete.get(mdAttribute.getId()));
      }
    }

    return definedAttributes.toArray(new MdAttributeConcrete[definedAttributes.size()]);
  }

  @Transaction
  @Authenticate
  public static MdWebForm apply(MdWebForm mdForm)
  {
    boolean first = mdForm.isNew() && !mdForm.isAppliedToDB();

    if (first)
    {
      String typeName = GeoHierarchy.getSystemName(mdForm.getFormName());
      String label = mdForm.getDisplayLabel().getValue();
      String description = mdForm.getDescription().getValue();

      MdBusiness mdBusiness = new MdBusiness();
      mdBusiness.setPackageName(MDSSInfo.GENERATED_FORM_BUSINESS_PACKAGE);
      mdBusiness.setTypeName(typeName);
      mdBusiness.getDisplayLabel().setValue(label);
      mdBusiness.getDescription().setValue(description);
      mdBusiness.setIsAbstract(false);
      mdBusiness.setExtendable(true);
      mdBusiness.setPublish(true);
      mdBusiness.apply();

      mdForm.setPackageName(MDSSInfo.GENERATED_FORM_PACKAGE);
      mdForm.setTypeName(typeName);
      mdForm.setFormMdClass(mdBusiness);
    }

    mdForm.apply();

    if (first)
    {
      new FormSystemURLBuilder(mdForm).generate();
    }

    return mdForm;
  }

  @Transaction
  @Authenticate
  public static void delete(MdWebForm mdForm)
  {
    new FormSystemURLBuilder(mdForm).delete();

    MdClass mdClass = mdForm.getFormMdClass();

    mdForm.delete();

    mdClass.delete();
  }
  
  @Transaction
  @Authenticate
  public static void deleteField(MdWebForm mdForm, MdWebField mdField)
  {
    try {
    MdWebAttribute attr = (MdWebAttribute) mdField;
    MdAttribute definingAttr = attr.getDefiningMdAttribute();
    mdForm.removeMdFields(attr);
    attr.delete();
    definingAttr.delete();
    } catch (Throwable t)
    {
      throw new ProgrammingErrorException(t);
    }
  }
}
