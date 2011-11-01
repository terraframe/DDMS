package dss.vector.solutions.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.constants.IndexTypes;
import com.runwaysdk.constants.MdAttributeBooleanInfo;
import com.runwaysdk.constants.MdAttributeCharacterInfo;
import com.runwaysdk.constants.MdAttributeReferenceInfo;
import com.runwaysdk.constants.MdWebCharacterInfo;
import com.runwaysdk.dataaccess.EntityDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.attributes.EmptyValueProblem;
import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.system.metadata.MdAttributeBoolean;
import com.runwaysdk.system.metadata.MdAttributeCharacter;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeDate;
import com.runwaysdk.system.metadata.MdAttributeDateTime;
import com.runwaysdk.system.metadata.MdAttributeDecimal;
import com.runwaysdk.system.metadata.MdAttributeDouble;
import com.runwaysdk.system.metadata.MdAttributeFloat;
import com.runwaysdk.system.metadata.MdAttributeInteger;
import com.runwaysdk.system.metadata.MdAttributeLong;
import com.runwaysdk.system.metadata.MdAttributeReference;
import com.runwaysdk.system.metadata.MdAttributeText;
import com.runwaysdk.system.metadata.MdAttributeTime;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdField;
import com.runwaysdk.system.metadata.MdWebAttribute;
import com.runwaysdk.system.metadata.MdWebBoolean;
import com.runwaysdk.system.metadata.MdWebBreak;
import com.runwaysdk.system.metadata.MdWebCharacter;
import com.runwaysdk.system.metadata.MdWebComment;
import com.runwaysdk.system.metadata.MdWebDate;
import com.runwaysdk.system.metadata.MdWebDateTime;
import com.runwaysdk.system.metadata.MdWebDecimal;
import com.runwaysdk.system.metadata.MdWebDouble;
import com.runwaysdk.system.metadata.MdWebField;
import com.runwaysdk.system.metadata.MdWebFloat;
import com.runwaysdk.system.metadata.MdWebForm;
import com.runwaysdk.system.metadata.MdWebGeo;
import com.runwaysdk.system.metadata.MdWebGroup;
import com.runwaysdk.system.metadata.MdWebHeader;
import com.runwaysdk.system.metadata.MdWebInteger;
import com.runwaysdk.system.metadata.MdWebLong;
import com.runwaysdk.system.metadata.MdWebMultipleTerm;
import com.runwaysdk.system.metadata.MdWebPrimitive;
import com.runwaysdk.system.metadata.MdWebReference;
import com.runwaysdk.system.metadata.MdWebSingleTerm;
import com.runwaysdk.system.metadata.MdWebSingleTermGrid;
import com.runwaysdk.system.metadata.MdWebText;
import com.runwaysdk.system.metadata.MdWebTime;

import dss.vector.solutions.generator.MdFormUtil;
import dss.vector.solutions.geo.ExtraFieldUniversal;
import dss.vector.solutions.geo.GeoField;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;

public class DDMSFieldBuilders implements Reloadable
{
  private static Map<String, WebFieldBuilder> builders;

  static
  {
    builders = new HashMap<String, WebFieldBuilder>();
    builders.put(MdWebBoolean.CLASS, new WebBooleanBuilder());
    builders.put(MdWebBreak.CLASS, new WebBreakBuilder());
    builders.put(MdWebCharacter.CLASS, new WebCharacterBuilder());
    builders.put(MdWebComment.CLASS, new WebCommentBuilder());
    builders.put(MdWebDate.CLASS, new WebDateBuilder());
    builders.put(MdWebDateTime.CLASS, new WebDateTimeBuilder());
    builders.put(MdWebTime.CLASS, new WebTimeBuilder());
    builders.put(MdWebDouble.CLASS, new WebDoubleBuilder());
    builders.put(MdWebDecimal.CLASS, new WebDecimalBuilder());
    builders.put(MdWebFloat.CLASS, new WebFloatBuilder());
    builders.put(MdWebInteger.CLASS, new WebIntegerBuilder());
    builders.put(MdWebLong.CLASS, new WebLongBuilder());
    builders.put(MdWebHeader.CLASS, new WebHeaderBuilder());
    builders.put(MdWebMultipleTerm.CLASS, new WebMultipleTermBuilder());
    builders.put(MdWebSingleTerm.CLASS, new WebSingleTermBuilder());
    builders.put(MdWebSingleTermGrid.CLASS, new WebSingleTermGridBuilder());
    builders.put(MdWebText.CLASS, new WebTextBuilder());
    builders.put(MdWebReference.CLASS, new WebReferenceBuilder());
    builders.put(MdWebGroup.CLASS, new WebGroupBuilder());
  }

  /**
   * Creates the MdField and the underlying MdAttribute if one exists for the
   * field type.
   * 
   * @param mdField
   * @param mdClassId
   */
  public static void create(MdField mdField, String mdFormId /* FIXME FieldData */)
  {
    WebFieldBuilder builder = builders.get(mdField.getType());
    MdWebForm webForm = MdWebForm.get(mdFormId);
    builder.create(mdField, webForm);
  }

  public static void createGeoField(MdWebGeo mdField, String mdFormId, GeoField geoField, String[] extraUniversals)
  {
    WebFieldBuilder builder = new WebGeoBuilder(geoField, extraUniversals);
    MdWebForm webForm = MdWebForm.get(mdFormId);
    builder.create(mdField, webForm);
  }

  public static void updateGeoField(MdWebGeo mdField, GeoField geoField, String[] extraUniversals)
  {
    WebFieldBuilder builder = new WebGeoBuilder(geoField, extraUniversals);
    builder.update(mdField);
  }

  /**
   * Updates the MdField and the underlying MdAttribute if one exists for the
   * field type.
   * 
   * @param mdField
   */
  public static void update(MdField mdField)
  {
    WebFieldBuilder builder = builders.get(mdField.getType());
    builder.update(mdField);
  }

  /**
   * Builder class to construct a WebField from an MdWebFieldDTO.
   * 
   */
  private static abstract class WebFieldBuilder implements Reloadable
  {

    /**
     * Validates an MdField and sets the field name.
     * 
     * @param mdField
     * @return
     */
    protected void setupAndValidateMdField(MdField mdField)
    {
      // FIXME how to validate non-new display labels? The old one is just being
      // used

      if (mdField.isNew())
      {
        // the display label is required because it is used to generate the
        // field
        // name, and it is the only UI label available to the user.
        String display = mdField.getDisplayLabel().getValue();
        if (display == null || display.trim().length() == 0)
        {
          String msg = "";
          MdAttributeDAOIF md = MdField.getDisplayLabelMd();
          EntityDAOIF field = BusinessFacade.getEntityDAO(mdField);
          EmptyValueProblem p = new EmptyValueProblem(mdField.getId(), mdField.getMdClass(), md, msg, field.getAttributeIF(MdField.DISPLAYLABEL));
          p.throwIt();
        }

        convertDisplayLabelToFieldName(mdField);
      }
    }

    @AbortIfProblem
    private void convertDisplayLabelToFieldName(MdField mdField)
    {
      String displayLabel = mdField.getDisplayLabel().getValue();
      String fieldName = GeoHierarchy.getSystemName(displayLabel, "Attr", false);
      mdField.setFieldName(fieldName);
    }

    /**
     * Builds a new MdAttributeDAO based on the MdField type. Subclasses should
     * override and call this method to set attribute specific information.
     * 
     * @param mdField
     * @param mdClassId
     * @return
     */
    protected void create(MdField mdField, MdWebForm webForm)
    {
      // update the field order to one greater than the last field (to simply
      // append it)

      this.setupAndValidateMdField(mdField);

      Integer order = MdFormUtil.getHighestOrder(webForm);
      mdField.setFieldOrder(order);
      mdField.apply();
    }

    protected void update(MdField mdField)
    {
      mdField.apply();
    }
  }

  public static abstract class WebAttributeBuilder extends WebFieldBuilder implements Reloadable
  {

    /**
     * Creates the MdAttribute that maps directly to the MdField.
     * 
     * @param mdField
     * @param webForm
     * @return
     */
    @AbortIfProblem
    protected void updateMdAttribute(MdAttributeConcrete mdAttr, MdWebField mdField)
    {
      if (mdAttr.isNew())
      {
        mdAttr.setDefiningMdClass(mdField.getDefiningMdForm().getFormMdClass());
        mdAttr.setAttributeName(mdField.getFieldName());
      }

      mdAttr.setRequired(mdField.getRequired());

      String displayLabel = mdField.getDisplayLabel().getValue();
      mdAttr.getDisplayLabel().setValue(displayLabel);

      String description = mdField.getDescription().getValue();
      mdAttr.getDescription().setValue(description);
    }

    @Override
    protected void create(MdField mdField, MdWebForm webForm)
    {
      this.setupAndValidateMdField(mdField);

      MdWebAttribute mdWebAttribute = (MdWebAttribute) mdField;
      mdWebAttribute.setDefiningMdForm(webForm);

      MdAttributeConcrete mdAttribute = this.newMdAttribute(mdWebAttribute);
      this.updateMdAttribute(mdAttribute, mdWebAttribute);
      mdAttribute.apply();

      mdWebAttribute.setValue(MdWebPrimitive.DEFININGMDATTRIBUTE, mdAttribute.getId());

      super.create(mdField, webForm);
    }

    protected abstract MdAttributeConcrete newMdAttribute(MdWebAttribute mdWebAttribute);

    protected void update(MdField mdField)
    {
      MdWebAttribute webAttribute = (MdWebAttribute) mdField;
      MdAttributeConcrete mdAttr = (MdAttributeConcrete) webAttribute.getDefiningMdAttribute();

      mdAttr.appLock();
      this.setupAndValidateMdField(mdField);
      this.updateMdAttribute(mdAttr, webAttribute);
      mdAttr.apply();

      super.update(mdField);
    }
  }

  public static class WebGroupBuilder extends WebFieldBuilder implements Reloadable
  {
    @Override
    protected void create(MdField mdField, MdWebForm webForm)
    {
      MdWebGroup field = (MdWebGroup) mdField;
      field.setDefiningMdForm(webForm);
      super.create(mdField, webForm);
    }
  }

  // display related

  public static class WebBreakBuilder extends WebFieldBuilder implements Reloadable
  {
    @Override
    protected void create(MdField mdField, MdWebForm webForm)
    {
      MdWebBreak field = (MdWebBreak) mdField;
      field.setDefiningMdForm(webForm);
      super.create(mdField, webForm);
    }
  }

  public static class WebCommentBuilder extends WebFieldBuilder implements Reloadable
  {
    @Override
    protected void create(MdField mdField, MdWebForm webForm)
    {
      MdWebComment field = (MdWebComment) mdField;
      field.setDefiningMdForm(webForm);
      super.create(mdField, webForm);
    }
  }

  public static class WebHeaderBuilder extends WebFieldBuilder implements Reloadable
  {

    @Override
    protected void create(MdField mdField, MdWebForm webForm)
    {
      MdWebHeader field = (MdWebHeader) mdField;
      field.setDefiningMdForm(webForm);
      super.create(mdField, webForm);
    }

    @Override
    protected void update(MdField mdField)
    {
      // TODO Auto-generated method stub
      super.update(mdField);
    }
  }

  // complex attributes

  public static class WebGeoBuilder extends WebAttributeBuilder implements Reloadable
  {
    private GeoField geoField;

    private String[] extraUniversals;

    public WebGeoBuilder(GeoField geoField, String[] extraUniversals)
    {
      this.geoField = geoField;
      this.extraUniversals = extraUniversals;
    }

    @Override
    protected void create(MdField mdField, MdWebForm webForm)
    {
      super.create(mdField, webForm);

      MdAttributeReference mdAttributeReference = (MdAttributeReference) ( (MdWebAttribute) mdField ).getDefiningMdAttribute();
      this.geoField.setGeoAttribute(mdAttributeReference);
      this.geoField.apply();

      if (this.extraUniversals != null)
      {
        for (String extraUniversal : extraUniversals)
        {
          this.geoField.addGeoHierarchies(GeoHierarchy.get(extraUniversal)).apply();
        }
      }
    }

    @Override
    protected MdAttributeConcrete newMdAttribute(MdWebAttribute mdWebAttribute)
    {
      return new MdAttributeReference();
    }

    @Override
    protected void update(MdField mdField)
    {
      super.update(mdField);

      this.geoField.apply();

      this.deleteExistingExtraUniversals();

      if (this.extraUniversals != null)
      {
        for (String extraUniversal : extraUniversals)
        {
          this.geoField.addGeoHierarchies(GeoHierarchy.get(extraUniversal)).apply();
        }
      }
    }

    private void deleteExistingExtraUniversals()
    {
      OIterator<? extends ExtraFieldUniversal> it = this.geoField.getAllGeoHierarchiesRel();

      try
      {
        List<? extends ExtraFieldUniversal> list = it.getAll();

        for (ExtraFieldUniversal universal : list)
        {
          universal.delete();
        }
      }
      finally
      {
        it.close();
      }
    }

    @Override
    protected void updateMdAttribute(MdAttributeConcrete mdAttribute, MdWebField mdField)
    {
      super.updateMdAttribute(mdAttribute, mdField);

      mdAttribute.setValue(MdAttributeReferenceInfo.REF_MD_BUSINESS, MdClass.getMdClass(GeoEntity.CLASS).getId());
    }
  }

  public static class WebSingleTermBuilder extends WebFieldBuilder implements Reloadable
  {

  }

  public static class WebMultipleTermBuilder extends WebFieldBuilder implements Reloadable
  {

  }

  public static class WebSingleTermGridBuilder extends WebFieldBuilder implements Reloadable
  {

  }

  // Primitives

  private static abstract class WebPrimitiveBuilder extends WebAttributeBuilder implements Reloadable
  {
    @Override
    protected MdAttributeConcrete newMdAttribute(MdWebAttribute mdWebAttribute)
    {
      if (mdWebAttribute instanceof MdWebPrimitive)
      {
        String mdAttributeType = ( (MdWebPrimitive) mdWebAttribute ).getExpectedMdAttributeType();

        // We are not supporting virtual attributes right now ... so no need to
        // complicate things
        return (MdAttributeConcrete) BusinessFacade.newBusiness(mdAttributeType);
      }

      throw new ProgrammingErrorException("MdWebAttribute is not an instance of MdWebPrimitive");
    }
  }

  public static class WebBooleanBuilder extends WebPrimitiveBuilder implements Reloadable
  {
    @Override
    protected void updateMdAttribute(MdAttributeConcrete mdAttr, MdWebField mdField)
    {
      MdAttributeBoolean md = (MdAttributeBoolean) mdAttr;

      // FIXME allow user defined labels
      if (md.isNew())
      {
        md.getPositiveDisplayLabel().setDefaultValue("true");
        md.getNegativeDisplayLabel().setDefaultValue("false");
      }

      super.updateMdAttribute(md, mdField);
    }
  }

  public static class WebCharacterBuilder extends WebPrimitiveBuilder implements Reloadable
  {
    @Override
    protected void updateMdAttribute(MdAttributeConcrete mdAttr, MdWebField mdField)
    {
      MdAttributeCharacter md = (MdAttributeCharacter) mdAttr;
      MdWebCharacter field = (MdWebCharacter) mdField;

      md.setDatabaseSize(field.getMaxLength());

      String unique = mdField.getValue(MdWebCharacterInfo.UNIQUE);

      if (unique != null && unique.equalsIgnoreCase(MdAttributeBooleanInfo.TRUE))
      {
        md.setValue(MdAttributeCharacterInfo.INDEX_TYPE, IndexTypes.UNIQUE_INDEX.getId());
      }
      else
      {
        md.setValue(MdAttributeCharacterInfo.INDEX_TYPE, IndexTypes.NO_INDEX.getId());
      }

      super.updateMdAttribute(md, mdField);
    }
  }

  public static class WebReferenceBuilder extends WebAttributeBuilder implements Reloadable
  {
    @Override
    protected void updateMdAttribute(MdAttributeConcrete mdAttr, MdWebField mdField)
    {
      super.updateMdAttribute(mdAttr, mdField);
    }

    @Override
    public MdAttributeConcrete newMdAttribute(MdWebAttribute mdWebAttribute)
    {
      return null;
    }
  }

  public static class WebTextBuilder extends WebPrimitiveBuilder implements Reloadable
  {
    @Override
    protected void updateMdAttribute(MdAttributeConcrete mdAttr, MdWebField mdField)
    {
      MdAttributeText md = (MdAttributeText) mdAttr;

      super.updateMdAttribute(md, mdField);
    }
  }

  public static class WebDoubleBuilder extends WebPrimitiveBuilder implements Reloadable
  {
    @Override
    protected void updateMdAttribute(MdAttributeConcrete mdAttr, MdWebField mdField)
    {
      MdAttributeDouble md = (MdAttributeDouble) mdAttr;
      MdWebDouble field = (MdWebDouble) mdField;

      md.setDatabaseLength(field.getDecPrecision());
      md.setDatabaseDecimal(field.getDecScale());

      String start = field.getStartRange();
      if (start != null && start.trim().length() != 0)
      {
        md.setStartRange(Double.parseDouble(start));
      }

      String end = field.getEndRange();
      if (end != null && end.trim().length() != 0)
      {
        md.setEndRange(Double.parseDouble(end));
      }

      super.updateMdAttribute(md, mdField);
    }

  }

  public static class WebDecimalBuilder extends WebPrimitiveBuilder implements Reloadable
  {
    @Override
    protected void updateMdAttribute(MdAttributeConcrete mdAttr, MdWebField mdField)
    {
      MdAttributeDecimal md = (MdAttributeDecimal) mdAttr;

      MdWebDecimal field = (MdWebDecimal) mdField;

      md.setDatabaseLength(field.getDecPrecision());
      md.setDatabaseDecimal(field.getDecScale());

      String start = field.getStartRange();
      if (start != null && start.trim().length() != 0)
      {
        md.setStartRange(new BigDecimal(start));
      }

      String end = field.getEndRange();
      if (end != null && end.trim().length() != 0)
      {
        md.setEndRange(new BigDecimal(end));
      }

      super.updateMdAttribute(mdAttr, mdField);
    }
  }

  public static class WebFloatBuilder extends WebPrimitiveBuilder implements Reloadable
  {
    @Override
    protected void updateMdAttribute(MdAttributeConcrete mdAttr, MdWebField mdField)
    {
      MdAttributeFloat md = (MdAttributeFloat) mdAttr;

      MdWebFloat field = (MdWebFloat) mdField;

      md.setDatabaseLength(field.getDecPrecision());
      md.setDatabaseDecimal(field.getDecScale());

      String start = field.getStartRange();
      if (start != null && start.trim().length() != 0)
      {
        md.setStartRange(Float.parseFloat(start));
      }

      String end = field.getEndRange();
      if (end != null && end.trim().length() != 0)
      {
        md.setEndRange(Float.parseFloat(end));
      }

      super.updateMdAttribute(md, mdField);
    }

  }

  public static class WebLongBuilder extends WebPrimitiveBuilder implements Reloadable
  {
    @Override
    protected void updateMdAttribute(MdAttributeConcrete mdAttr, MdWebField mdField)
    {
      MdAttributeLong md = (MdAttributeLong) mdAttr;
      MdWebLong field = (MdWebLong) mdField;

      String start = field.getStartRange();
      if (start != null && start.trim().length() != 0)
      {
        md.setStartRange(Long.parseLong(start));
      }

      String end = field.getEndRange();
      if (end != null && end.trim().length() != 0)
      {
        md.setEndRange(Long.parseLong(end));
      }

      super.updateMdAttribute(md, mdField);
    }
  }

  public static class WebIntegerBuilder extends WebPrimitiveBuilder implements Reloadable
  {
    @Override
    protected void updateMdAttribute(MdAttributeConcrete mdAttr, MdWebField mdField)
    {
      MdAttributeInteger md = (MdAttributeInteger) mdAttr;
      MdWebInteger field = (MdWebInteger) mdField;

      String start = field.getStartRange();
      if (start != null && start.trim().length() != 0)
      {
        md.setStartRange(Integer.parseInt(start));
      }

      String end = field.getEndRange();
      if (end != null && end.trim().length() != 0)
      {
        md.setEndRange(Integer.parseInt(end));
      }

      super.updateMdAttribute(md, mdField);
    }

  }

  public static class WebDateBuilder extends WebPrimitiveBuilder implements Reloadable
  {

    @Override
    protected void updateMdAttribute(MdAttributeConcrete mdAttr, MdWebField mdField)
    {
      MdAttributeDate md = (MdAttributeDate) mdAttr;

      MdWebDate field = (MdWebDate) mdField;

      md.setStartDate(field.getStartDate());
      md.setEndDate(field.getEndDate());
      md.setAfterTodayExclusive(field.getAfterTodayExclusive());
      md.setAfterTodayInclusive(field.getAfterTodayInclusive());
      md.setBeforeTodayExclusive(field.getBeforeTodayExclusive());
      md.setBeforeTodayInclusive(field.getBeforeTodayInclusive());

      super.updateMdAttribute(md, mdField);
    }
  }

  public static class WebDateTimeBuilder extends WebPrimitiveBuilder implements Reloadable
  {
    @Override
    protected void updateMdAttribute(MdAttributeConcrete mdAttr, MdWebField mdField)
    {
      MdAttributeDateTime md = (MdAttributeDateTime) mdAttr;

      super.updateMdAttribute(md, mdField);
    }

  }

  public static class WebTimeBuilder extends WebPrimitiveBuilder implements Reloadable
  {
    @Override
    protected void updateMdAttribute(MdAttributeConcrete mdAttr, MdWebField mdField)
    {
      MdAttributeTime md = (MdAttributeTime) mdAttr;

      super.updateMdAttribute(md, mdField);
    }
  }
}
