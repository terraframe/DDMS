package dss.vector.solutions.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.generation.loader.Reloadable;
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
import com.runwaysdk.system.metadata.MdAttributeText;
import com.runwaysdk.system.metadata.MdAttributeTime;
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
import com.runwaysdk.system.metadata.MdWebHeader;
import com.runwaysdk.system.metadata.MdWebInteger;
import com.runwaysdk.system.metadata.MdWebLong;
import com.runwaysdk.system.metadata.MdWebMultipleTerm;
import com.runwaysdk.system.metadata.MdWebPrimitive;
import com.runwaysdk.system.metadata.MdWebSingleTerm;
import com.runwaysdk.system.metadata.MdWebSingleTermGrid;
import com.runwaysdk.system.metadata.MdWebText;
import com.runwaysdk.system.metadata.MdWebTime;

import dss.vector.solutions.geo.GeoHierarchy;

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
    builders.put(MdWebGeo.CLASS, new WebGeoBuilder());
    builders.put(MdWebInteger.CLASS, new WebIntegerBuilder());
    builders.put(MdWebLong.CLASS, new WebLongBuilder());
    builders.put(MdWebHeader.CLASS, new WebHeaderBuilder());
    builders.put(MdWebMultipleTerm.CLASS, new WebMultipleTermBuilder());
    builders.put(MdWebSingleTerm.CLASS, new WebSingleTermBuilder());
    builders.put(MdWebSingleTermGrid.CLASS, new WebSingleTermGridBuilder());
    builders.put(MdWebText.CLASS, new WebTextBuilder());
  }
  
  /**
   * Creates the MdField and the underlying MdAttribute if one exists for the field type.
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
  
  /**
   * Updates the MdField and the underlying MdAttribute if one exists for the field type.
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
     * Builds a new MdAttributeDAO based on the MdField type. Subclasses should override and call
     * this method to set attribute specific information.
     * 
     * @param mdField
     * @param mdClassId
     * @return
     */
    protected void create(MdField mdField, MdWebForm webForm)
    {
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
     * Validates an MdField
     * 
     * @param mdField
     * @return
     */
    protected void validateMdField(MdField mdField)
    {
       mdField.validateFieldName();
    }     
    
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
      if(mdAttr.isNew())
      {
        mdAttr.setDefiningMdClass(mdField.getDefiningMdForm().getFormMdClass());
        mdAttr.setAttributeName(GeoHierarchy.getSystemName(mdField.getFieldName(), "Attr", false)); // FIXME auto-gen name?
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
      MdWebPrimitive webPrimitive = (MdWebPrimitive) mdField;
      String mdAttributeType = webPrimitive.getExpectedMdAttributeType(); // FIXME move to MdWebAttribute
      
      // We are not supporting virtual attributes right now ... so no need to complicate things
      MdAttributeConcrete mdAttr = (MdAttributeConcrete) BusinessFacade.newBusiness(mdAttributeType);
      webPrimitive.setDefiningMdForm(webForm);
      this.validateMdField(mdField);
      this.updateMdAttribute(mdAttr, webPrimitive);
      mdAttr.apply();

      webPrimitive.setValue(MdWebPrimitive.DEFININGMDATTRIBUTE, mdAttr.getId());
      
      super.create(mdField, webForm);
    }
    
    protected void update(MdField mdField)
    {
      MdWebAttribute webAttribute = (MdWebAttribute) mdField;
      MdAttributeConcrete mdAttr = (MdAttributeConcrete) webAttribute.getDefiningMdAttribute();
      
      mdAttr.appLock();
      this.validateMdField(mdField);
      this.updateMdAttribute(mdAttr, webAttribute);
      mdAttr.apply();
      
      super.update(mdField);
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
  }
  
  // complex attributes

  public static class WebGeoBuilder extends WebFieldBuilder implements Reloadable
  {
    
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

  }

  public static class WebBooleanBuilder extends WebPrimitiveBuilder implements Reloadable
  {
    @Override
    protected void updateMdAttribute(MdAttributeConcrete mdAttr, MdWebField mdField)
    {
      MdAttributeBoolean md = (MdAttributeBoolean) mdAttr;
      
      // FIXME allow user defined labels
      if(md.isNew())
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
      
      super.updateMdAttribute(md, mdField);
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
      if(start != null && start.trim().length() != 0)
      {
        md.setStartRange(Double.parseDouble(start));
      }

      String end = field.getEndRange();
      if(end != null && end.trim().length() != 0)
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
      if(start != null && start.trim().length() != 0)
      {
        md.setStartRange(new BigDecimal(start));
      }

      String end = field.getEndRange();
      if(end != null && end.trim().length() != 0)
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
      if(start != null && start.trim().length() != 0)
      {
        md.setStartRange(Float.parseFloat(start));
      }

      String end = field.getEndRange();
      if(end != null && end.trim().length() != 0)
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
      if(start != null && start.trim().length() != 0)
      {
        md.setStartRange(Long.parseLong(start));
      }

      String end = field.getEndRange();
      if(end != null && end.trim().length() != 0)
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
      if(start != null && start.trim().length() != 0)
      {
        md.setStartRange(Integer.parseInt(start));
      }

      String end = field.getEndRange();
      if(end != null && end.trim().length() != 0)
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
