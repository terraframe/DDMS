package dss.vector.solutions.form;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.runwaysdk.constants.MdAttributeConcreteInfo;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttribute;
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
import com.runwaysdk.system.metadata.MdWebMultipleTerm;
import com.runwaysdk.system.metadata.MdWebPrimitive;
import com.runwaysdk.system.metadata.MdWebSingleTerm;
import com.runwaysdk.system.metadata.MdWebSingleTermGrid;
import com.runwaysdk.system.metadata.MdWebText;
import com.runwaysdk.system.metadata.MdWebTime;
import com.runwaysdk.system.metadata.MetadataDisplayLabel;

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
     * Creates the MdAttribute that maps directly to the MdField.
     * 
     * @param mdField
     * @param webForm
     * @return
     */
    protected void updateMdAttribute(MdAttributeDAO mdAttr, MdWebField mdField)
    {
      if(mdAttr.isNew())
      {
        String mdClassId = mdField.getDefiningMdForm().getFormMdClassId();
        mdAttr.setValue(MdAttributeConcreteInfo.DEFINING_MD_CLASS, mdClassId);
      }
      
      mdAttr.setValue(MdAttributeConcreteInfo.NAME, mdField.getFieldName()); // FIXME use english generated fieldName
      mdAttr.setValue(MdAttributeConcreteInfo.REQUIRED, mdField.getRequired().toString());

      Locale locale = Session.getCurrentLocale(); // FIXME set proper locale
      mdAttr.setStructValue(MdAttributeConcreteInfo.DISPLAY_LABEL, MetadataDisplayLabel.DEFAULTLOCALE, mdField.getDisplayLabel().getValue(locale));
      mdAttr.setStructValue(MdAttributeConcreteInfo.DESCRIPTION, MetadataDisplayLabel.DEFAULTLOCALE, mdField.getDescription().getValue(locale));
    }
    
    @Override
    protected void create(MdField mdField, MdWebForm webForm)
    {
      MdWebPrimitive webPrimitive = (MdWebPrimitive) mdField;
      String mdAttributeType = webPrimitive.getExpectedMdAttributeType(); // FIXME move to MdWebAttribute
      
      MdAttributeDAO mdAttr = (MdAttributeDAO) MdAttributeDAO.newInstance(mdAttributeType);
      webPrimitive.setDefiningMdForm(webForm);
      this.updateMdAttribute(mdAttr, webPrimitive);
      mdAttr.apply();

      webPrimitive.setValue(MdWebPrimitive.DEFININGMDATTRIBUTE, mdAttr.getId());
      
      super.create(mdField, webForm);
    }
    
    protected void update(MdField mdField)
    {
      MdWebAttribute webAttribute = (MdWebAttribute) mdField;
      MdAttributeDAO mdAttr = (MdAttributeDAO) MdAttributeDAO.get(webAttribute.getDefiningMdAttributeId()).getBusinessDAO();
      
      this.updateMdAttribute(mdAttr, webAttribute);
      mdAttr.apply();
      
      super.update(mdField);
    }
  }

  // display related
  
  public static class WebBreakBuilder extends WebFieldBuilder implements Reloadable
  {

  }

  public static class WebCommentBuilder extends WebFieldBuilder implements Reloadable
  {

  }


  public static class WebHeaderBuilder extends WebFieldBuilder implements Reloadable
  {

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

  }

  public static class WebCharacterBuilder extends WebPrimitiveBuilder implements Reloadable
  {
 
  }

  public static class WebTextBuilder extends WebPrimitiveBuilder implements Reloadable
  {

  }

  public static class WebDoubleBuilder extends WebPrimitiveBuilder implements Reloadable
  {

  }

  public static class WebDecimalBuilder extends WebPrimitiveBuilder implements Reloadable
  {

  }

  public static class WebLongBuilder extends WebPrimitiveBuilder implements Reloadable
  {

  }

  public static class WebFloatBuilder extends WebPrimitiveBuilder implements Reloadable
  {

  }

  public static class WebIntegerBuilder extends WebPrimitiveBuilder implements Reloadable
  {

  }

  public static class WebDateBuilder extends WebPrimitiveBuilder implements Reloadable
  {

  }

  public static class WebDateTimeBuilder extends WebPrimitiveBuilder implements Reloadable
  {

  }

  public static class WebTimeBuilder extends WebPrimitiveBuilder implements Reloadable
  {

  }

}
