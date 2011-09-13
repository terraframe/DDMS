package dss.vector.solutions.generator;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import com.runwaysdk.constants.MdAttributeConcreteInfo;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeRefDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.MdTypeDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdAttributeBooleanDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeCharacterDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeDateDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeTextDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdFormDAO;
import com.runwaysdk.dataaccess.metadata.MdRelationshipDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.SessionFacade;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdField;
import com.runwaysdk.system.metadata.MdFieldDisplayLabel;
import com.runwaysdk.system.metadata.MdFieldQuery;
import com.runwaysdk.system.metadata.MdRelationship;
import com.runwaysdk.system.metadata.MdType;
import com.runwaysdk.system.metadata.MdTypeQuery;
import com.runwaysdk.system.metadata.MdWebBoolean;
import com.runwaysdk.system.metadata.MdWebCharacter;
import com.runwaysdk.system.metadata.MdWebDate;
import com.runwaysdk.system.metadata.MdWebDecimal;
import com.runwaysdk.system.metadata.MdWebDouble;
import com.runwaysdk.system.metadata.MdWebField;
import com.runwaysdk.system.metadata.MdWebForm;
import com.runwaysdk.system.metadata.MdWebFormQuery;
import com.runwaysdk.system.metadata.MdWebInteger;
import com.runwaysdk.system.metadata.MdWebLong;
import com.runwaysdk.system.metadata.MdWebPrimitive;
import com.runwaysdk.system.metadata.MdWebText;
import com.runwaysdk.system.metadata.MetadataDisplayLabel;

import dss.vector.solutions.MDSSInfo;
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
String sql = q.getSQL();
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
  public static com.runwaysdk.system.metadata.MdField createMdField(
      com.runwaysdk.system.metadata.MdField mdField, java.lang.String mdFormId)
  {
    if (mdField instanceof MdWebPrimitive)
    {
      String fieldName = mdField.getFieldName();
      MdFieldDisplayLabel displayLabel = mdField.getDisplayLabel();
      Boolean required = mdField.getRequired();
      MetadataDisplayLabel description = mdField.getDescription();

      MdWebForm webForm = MdWebForm.get(mdFormId);
      String mdClassId = webForm.getFormMdClassId();

      // First define an MdAttribute based on the incoming MdField
      MdWebPrimitive webPrimitive = (MdWebPrimitive) mdField;
      String mdAttributeType = webPrimitive.getExpectedMdAttributeType();

      Locale locale = SessionFacade.getPublicSession().getLocale();

      // generic attribute info
      MdAttributeDAO mdAttr = (MdAttributeDAO) MdAttributeDAO.newInstance(mdAttributeType);
      mdAttr.setValue(MdAttributeConcreteInfo.NAME, fieldName);
      mdAttr.setValue(MdAttributeConcreteInfo.REQUIRED, required.toString());
      mdAttr.setValue(MdAttributeConcreteInfo.DEFINING_MD_CLASS, mdClassId);
      // FIXME use current locale
      mdAttr.setStructValue(MdAttributeConcreteInfo.DISPLAY_LABEL, MetadataDisplayLabel.DEFAULTLOCALE,
          displayLabel.getDefaultValue());
      mdAttr.setStructValue(MdAttributeConcreteInfo.DISPLAY_LABEL, MetadataDisplayLabel.DEFAULTLOCALE,
          description.getDefaultValue());

      if (mdAttr instanceof MdAttributeCharacterDAO)
      {

      }
      else if (mdAttr instanceof MdAttributeTextDAO)
      {

      }
      else if (mdAttr instanceof MdAttributeBooleanDAO)
      {

      }
      else if (mdAttr instanceof MdAttributeDateDAO)
      {

      }
      // else if(mdAttr instanceof MdAttributeNumberDAO)
      // {
      //        
      // }

      mdAttr.apply();

      // now apply the Mdfield
      webPrimitive.setDefiningMdForm(webForm);

      MdAttribute mdAttrBus = MdAttribute.get(mdAttr.getId());
      webPrimitive.setDefiningMdAttribute(mdAttrBus);
    }
    else
    {
      throw new ProgrammingErrorException("The MdField is currently not supported.");
    }

    // Applying a new MdAttribute can cause a ClassCastException due to a
    // reload, so get a fresh copy
    return MdField.get(mdField.getId());
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
    String relationshipType = mdClass.definesType() + fieldName.substring(0, 1).toUpperCase()
        + fieldName.substring(1);
    return relationshipType;
  }

  public static MdAttributeConcrete[] definesAttributes(MdRelationship mdRelationship)
  {
    List<MdAttributeConcrete> definedAttributes = new LinkedList<MdAttributeConcrete>();
    MdRelationshipDAOIF mdRelationshipDAO = MdRelationshipDAO.get(mdRelationship.getId());

    List<? extends MdAttributeConcreteDAOIF> mdAttributes = mdRelationshipDAO
        .getAllDefinedMdAttributes();

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
  public static MdWebForm apply(MdWebForm mdForm)
  {
    if (mdForm.isNew() && !mdForm.isAppliedToDB())
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

    return mdForm;
  }
}
