package dss.vector.solutions.form;

import java.util.HashMap;
import java.util.Map;

import com.runwaysdk.dataaccess.MdFieldDAOIF;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
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
import dss.vector.solutions.geo.GeoField;

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
    builders.put(MdWebGeo.CLASS, new WebGeoBuilder());
    builders.put(MdWebGroup.CLASS, new WebGroupBuilder());
  }

  private static WebFieldBuilder getBuilder(MdField mdField)
  {
    return builders.get(mdField.getType()).copy();
  }

  public static String getTermRelationshipTypeName(MdWebForm webForm, MdWebAttribute mdWebAttribute)
  {
    MdClass mdClass = webForm.getFormMdClass();
    MdAttributeConcrete mdAttribute = (MdAttributeConcrete) mdWebAttribute.getDefiningMdAttribute();

    return mdClass.getTypeName() + CommonGenerationUtil.upperFirstCharacter(mdAttribute.getAttributeName());
  }

  public static String getTermRelationshipTypeName(MdWebAttribute mdWebAttribute)
  {
    return DDMSFieldBuilders.getTermRelationshipTypeName(mdWebAttribute.getDefiningMdForm(), mdWebAttribute);
  }

  public static String getTermRelationshipTypeName(MdFieldDAOIF mdField)
  {
    return DDMSFieldBuilders.getTermRelationshipTypeName(MdWebAttribute.get(mdField.getId()));
  }

  /**
   * Creates the MdField and the underlying MdAttribute if one exists for the
   * field type.
   * 
   * @param mdField
   * @param mdClassId
   */
  public static void create(MdField mdField, String mdFormId)
  {
    MdWebForm webForm = MdWebForm.get(mdFormId);

    WebFieldBuilder builder = DDMSFieldBuilders.getBuilder(mdField);
    builder.setMdField((MdWebField) mdField);
    builder.setMdWebForm(webForm);
    builder.create();    
  }

  public static void create(MdWebPrimitive mdField, MdWebSingleTermGrid mdWebGrid)
  {
    WebPrimitiveBuilder builder = (WebPrimitiveBuilder) DDMSFieldBuilders.getBuilder(mdField);
    builder.setMdField((MdWebField) mdField);
    builder.setMdWebSingleTermGrid(mdWebGrid);
    builder.setMdClass(MdFormUtil.getMdRelationship(mdWebGrid));
    builder.create();
  }

  public static void createGeoField(MdWebGeo mdField, String mdFormId, GeoField geoField, String[] extraUniversals)
  {
    MdWebForm mdWebForm = MdWebForm.get(mdFormId);

    WebGeoBuilder builder = (WebGeoBuilder) DDMSFieldBuilders.getBuilder(mdField);
    builder.setMdField((MdWebField) mdField);
    builder.setMdWebForm(mdWebForm);
    builder.setGeoField(geoField);
    builder.setExtraUniversals(extraUniversals);
    builder.create();
  }

  public static void updateGeoField(MdWebGeo mdField, GeoField geoField, String[] extraUniversals)
  {
    WebGeoBuilder builder = (WebGeoBuilder) DDMSFieldBuilders.getBuilder(mdField);
    builder.setMdField((MdWebField) mdField);
    builder.setGeoField(geoField);
    builder.setExtraUniversals(extraUniversals);
    builder.update();
  }

  /**
   * Updates the MdField and the underlying MdAttribute if one exists for the
   * field type.
   * 
   * @param mdField
   */
  public static void update(MdField mdField)
  {
    WebFieldBuilder builder = DDMSFieldBuilders.getBuilder(mdField);
    builder.setMdField((MdWebField) mdField);
    builder.update();
  }

  public static void delete(MdWebField mdField)
  {
    WebFieldBuilder builder = DDMSFieldBuilders.getBuilder(mdField);
    builder.setMdField((MdWebField) mdField);
    builder.delete();
  }
}
