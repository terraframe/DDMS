package dss.vector.solutions.form;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeCharacter;
import com.runwaysdk.system.metadata.MdAttributeDate;
import com.runwaysdk.system.metadata.MdAttributeDec;
import com.runwaysdk.system.metadata.MdWebAttribute;
import com.runwaysdk.system.metadata.MdWebBoolean;
import com.runwaysdk.system.metadata.MdWebBreak;
import com.runwaysdk.system.metadata.MdWebCharacter;
import com.runwaysdk.system.metadata.MdWebComment;
import com.runwaysdk.system.metadata.MdWebDate;
import com.runwaysdk.system.metadata.MdWebDec;
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
import com.runwaysdk.system.metadata.MdWebSingleTerm;
import com.runwaysdk.system.metadata.MdWebSingleTermGrid;
import com.runwaysdk.system.metadata.MdWebText;

import dss.vector.solutions.generator.MdFormUtil;
import dss.vector.solutions.geo.GeoField;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyView;
import dss.vector.solutions.ontology.BrowserField;

public class MdFormUtilTest
{
  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  private MdWebForm createForm()
  {
    MdWebForm original = new MdWebForm();
    original.setFormName("TestJunitForm");
    original.getDisplayLabel().setValue("Test Junit Form");

    return MdFormUtil.apply(original);
  }

  private void assertWebField(MdWebField actual, MdWebField expected)
  {
    assertNotNull(expected);
    assertEquals(expected.getDisplayLabel().getValue(), actual.getDisplayLabel().getValue());
    assertEquals(expected.getFieldOrder(), actual.getFieldOrder());
  }

  private void assertWebAttribute(MdWebAttribute actual, MdWebAttribute expected)
  {
    this.assertWebField(actual, expected);

    MdAttribute mdAttribute = expected.getDefiningMdAttribute();
    assertNotNull(mdAttribute);
    assertEquals(mdAttribute.getDisplayLabel().getValue(), actual.getDisplayLabel().getValue());
  }

  private void assertWebDec(MdWebDec actual, MdWebDec expected)
  {
    this.assertWebAttribute(actual, expected);

    assertEquals(expected.getDecPrecision(), actual.getDecPrecision());
    assertEquals(expected.getDecScale(), actual.getDecScale());

    MdAttributeDec mdAttribute = (MdAttributeDec) expected.getDefiningMdAttribute();
    assertEquals(mdAttribute.getDatabaseLength(), actual.getDecPrecision());
    assertEquals(mdAttribute.getDatabaseDecimal(), actual.getDecScale());
  }

  private void assertWebTerm(MdWebAttribute actual, MdWebAttribute expected)
  {
    this.assertWebAttribute(actual, expected);

    MdAttribute mdAttribute = actual.getDefiningMdAttribute();
    assertNotNull(BrowserField.getBrowserField(mdAttribute));
  }

  private void assertWebMultiTerm(MdWebAttribute actual, MdWebAttribute expected)
  {
    this.assertWebTerm(actual, expected);

    assertNotNull(MdFormUtil.getMdRelationship(actual));
  }

  @Request
  @Test
  public void testCreateForm()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebForm test = MdWebForm.get(update.getId());

      assertNotNull(test);
      assertNotNull(test.getFormMdClass());
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testBooleanField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebBoolean mdWebBoolean = new MdWebBoolean();
      mdWebBoolean.getDisplayLabel().setValue("Test Boolean");
      mdWebBoolean.setFieldOrder(0);

      DDMSFieldBuilders.create(mdWebBoolean, update.getId());

      try
      {
        MdWebBoolean test = MdWebBoolean.get(mdWebBoolean.getId());

        this.assertWebAttribute(mdWebBoolean, test);
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebBoolean);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testBreakField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebBreak mdWebBreak = new MdWebBreak();
      mdWebBreak.getDisplayLabel().setValue("Test Break");
      mdWebBreak.setFieldOrder(0);

      DDMSFieldBuilders.create(mdWebBreak, update.getId());

      try
      {
        MdWebBreak test = MdWebBreak.get(mdWebBreak.getId());

        this.assertWebField(mdWebBreak, test);
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebBreak);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testCharacterField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebCharacter mdWebCharacter = new MdWebCharacter();
      mdWebCharacter.getDisplayLabel().setValue("Test Character");
      mdWebCharacter.setFieldOrder(0);
      mdWebCharacter.setDisplayLength(16);
      mdWebCharacter.setMaxLength(255);

      DDMSFieldBuilders.create(mdWebCharacter, update.getId());

      try
      {
        MdWebCharacter test = MdWebCharacter.get(mdWebCharacter.getId());

        this.assertWebAttribute(test, mdWebCharacter);

        assertEquals(mdWebCharacter.getMaxLength(), test.getMaxLength());
        assertEquals(mdWebCharacter.getDisplayLength(), test.getDisplayLength());

        MdAttributeCharacter mdAttribute = (MdAttributeCharacter) mdWebCharacter.getDefiningMdAttribute();
        assertEquals(mdAttribute.getDatabaseSize(), test.getMaxLength());
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebCharacter);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testCommentField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebComment mdWebComment = new MdWebComment();
      mdWebComment.getDisplayLabel().setValue("Test Comment");
      mdWebComment.setFieldOrder(0);
      mdWebComment.getCommentText().setValue("Comment text value");

      DDMSFieldBuilders.create(mdWebComment, update.getId());

      try
      {
        MdWebComment test = MdWebComment.get(mdWebComment.getId());

        this.assertWebField(mdWebComment, test);

        assertEquals(test.getCommentText().getValue(), mdWebComment.getCommentText().getValue());
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebComment);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testDateField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebDate mdWebDate = new MdWebDate();
      mdWebDate.getDisplayLabel().setValue("Test Date");
      mdWebDate.setFieldOrder(0);
      mdWebDate.setAfterTodayExclusive(true);
      mdWebDate.setAfterTodayInclusive(false);
      mdWebDate.setBeforeTodayExclusive(false);
      mdWebDate.setBeforeTodayInclusive(false);

      DDMSFieldBuilders.create(mdWebDate, update.getId());

      try
      {
        MdWebDate test = MdWebDate.get(mdWebDate.getId());

        this.assertWebAttribute(test, mdWebDate);

        assertEquals(mdWebDate.getAfterTodayExclusive(), test.getAfterTodayExclusive());
        assertEquals(mdWebDate.getAfterTodayInclusive(), test.getAfterTodayInclusive());
        assertEquals(mdWebDate.getBeforeTodayExclusive(), test.getBeforeTodayExclusive());
        assertEquals(mdWebDate.getBeforeTodayInclusive(), test.getBeforeTodayInclusive());
        assertEquals(mdWebDate.getStartDate(), test.getStartDate());
        assertEquals(mdWebDate.getEndDate(), test.getEndDate());

        MdAttributeDate mdAttribute = (MdAttributeDate) mdWebDate.getDefiningMdAttribute();

        assertEquals(mdWebDate.getAfterTodayExclusive(), mdAttribute.getAfterTodayExclusive());
        assertEquals(mdWebDate.getAfterTodayInclusive(), mdAttribute.getAfterTodayInclusive());
        assertEquals(mdWebDate.getBeforeTodayExclusive(), mdAttribute.getBeforeTodayExclusive());
        assertEquals(mdWebDate.getBeforeTodayInclusive(), mdAttribute.getBeforeTodayInclusive());
        assertEquals(mdWebDate.getStartDate(), mdAttribute.getStartDate());
        assertEquals(mdWebDate.getEndDate(), mdAttribute.getEndDate());
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebDate);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testDecimalField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebDecimal mdWebDecimal = new MdWebDecimal();
      mdWebDecimal.getDisplayLabel().setValue("Test Decimal");
      mdWebDecimal.setFieldOrder(0);
      mdWebDecimal.setDecPrecision(10);
      mdWebDecimal.setDecScale(1);

      DDMSFieldBuilders.create(mdWebDecimal, update.getId());

      try
      {
        MdWebDecimal test = MdWebDecimal.get(mdWebDecimal.getId());

        this.assertWebDec(test, mdWebDecimal);
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebDecimal);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testDoubleField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebDouble mdWebDouble = new MdWebDouble();
      mdWebDouble.getDisplayLabel().setValue("Test Double");
      mdWebDouble.setFieldOrder(0);
      mdWebDouble.setDecPrecision(10);
      mdWebDouble.setDecScale(1);

      DDMSFieldBuilders.create(mdWebDouble, update.getId());

      try
      {
        MdWebDouble test = MdWebDouble.get(mdWebDouble.getId());

        this.assertWebDec(test, mdWebDouble);
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebDouble);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testFloatField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebFloat mdWebFloat = new MdWebFloat();
      mdWebFloat.getDisplayLabel().setValue("Test Float");
      mdWebFloat.setFieldOrder(0);
      mdWebFloat.setDecPrecision(10);
      mdWebFloat.setDecScale(1);

      DDMSFieldBuilders.create(mdWebFloat, update.getId());

      try
      {
        MdWebFloat test = MdWebFloat.get(mdWebFloat.getId());

        this.assertWebDec(test, mdWebFloat);
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebFloat);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testGeoField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebGeo mdWebGeo = new MdWebGeo();
      mdWebGeo.getDisplayLabel().setValue("Test Geo");
      mdWebGeo.setFieldOrder(0);

      GeoField field = new GeoField();
      field.setIsPoliticalHierarchy(true);
      field.setIsPopulationHierarchy(true);
      field.setIsUrbanHierarchy(false);
      field.setIsSprayHierarchy(true);

      GeoHierarchyView[] views = GeoHierarchy.getAllViews();

      String[] extraUniversals = new String[] { views[views.length - 1].getGeoHierarchyId() };

      DDMSFieldBuilders.createGeoField(mdWebGeo, update.getId(), field, extraUniversals);

      try
      {
        MdWebGeo actualWebField = MdWebGeo.get(mdWebGeo.getId());

        this.assertWebAttribute(actualWebField, mdWebGeo);

        GeoField actualGeoField = GeoField.getGeoField(actualWebField.getDefiningMdAttribute());

        assertEquals(field.getIsPoliticalHierarchy(), actualGeoField.getIsPoliticalHierarchy());
        assertEquals(field.getIsPopulationHierarchy(), actualGeoField.getIsPopulationHierarchy());
        assertEquals(field.getIsSprayHierarchy(), actualGeoField.getIsSprayHierarchy());
        assertEquals(field.getIsUrbanHierarchy(), actualGeoField.getIsUrbanHierarchy());
        assertEquals(field.getFilter(), actualGeoField.getFilter());
        assertEquals(extraUniversals.length, actualGeoField.getUniversals().size());
        assertEquals(extraUniversals[0], actualGeoField.getUniversals().get(0).getId());
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebGeo);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testHeaderField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebHeader mdWebHeader = new MdWebHeader();
      mdWebHeader.getDisplayLabel().setValue("Test Header");
      mdWebHeader.setFieldOrder(0);
      mdWebHeader.getHeaderText().setValue("Header text value");

      DDMSFieldBuilders.create(mdWebHeader, update.getId());

      try
      {
        MdWebHeader test = MdWebHeader.get(mdWebHeader.getId());

        this.assertWebField(mdWebHeader, test);

        assertEquals(test.getHeaderText().getValue(), mdWebHeader.getHeaderText().getValue());
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebHeader);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testIntegerField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebInteger mdWebInteger = new MdWebInteger();
      mdWebInteger.getDisplayLabel().setValue("Test Integer");
      mdWebInteger.setFieldOrder(0);

      DDMSFieldBuilders.create(mdWebInteger, update.getId());

      try
      {
        MdWebInteger test = MdWebInteger.get(mdWebInteger.getId());

        this.assertWebAttribute(mdWebInteger, test);
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebInteger);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testLongField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebLong mdWebLong = new MdWebLong();
      mdWebLong.getDisplayLabel().setValue("Test Long");
      mdWebLong.setFieldOrder(0);

      DDMSFieldBuilders.create(mdWebLong, update.getId());

      try
      {
        MdWebLong test = MdWebLong.get(mdWebLong.getId());

        this.assertWebAttribute(mdWebLong, test);
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebLong);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testMultipleTermField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebMultipleTerm mdWebMultipleTerm = new MdWebMultipleTerm();
      mdWebMultipleTerm.getDisplayLabel().setValue("Test Multiple Term");
      mdWebMultipleTerm.setFieldOrder(0);

      DDMSFieldBuilders.create(mdWebMultipleTerm, update.getId());

      try
      {
        MdWebMultipleTerm actual = MdWebMultipleTerm.get(mdWebMultipleTerm.getId());

        this.assertWebMultiTerm(actual, mdWebMultipleTerm);
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebMultipleTerm);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testSingleTermField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebSingleTerm mdWebSingleTerm = new MdWebSingleTerm();
      mdWebSingleTerm.getDisplayLabel().setValue("Test Single Term");
      mdWebSingleTerm.setFieldOrder(0);

      DDMSFieldBuilders.create(mdWebSingleTerm, update.getId());

      try
      {
        MdWebSingleTerm actual = MdWebSingleTerm.get(mdWebSingleTerm.getId());

        this.assertWebTerm(actual, mdWebSingleTerm);
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebSingleTerm);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testSingleTermGridField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebSingleTermGrid mdWebSingleTermGrid = new MdWebSingleTermGrid();
      mdWebSingleTermGrid.getDisplayLabel().setValue("Test Single Term Grid");
      mdWebSingleTermGrid.setFieldOrder(0);

      DDMSFieldBuilders.create(mdWebSingleTermGrid, update.getId());

      try
      {
        MdWebSingleTermGrid actual = MdWebSingleTermGrid.get(mdWebSingleTermGrid.getId());

        this.assertWebMultiTerm(actual, mdWebSingleTermGrid);
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebSingleTermGrid);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testTextField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebText mdWebText = new MdWebText();
      mdWebText.getDisplayLabel().setValue("Test Text");
      mdWebText.setFieldOrder(0);
      mdWebText.setHeight(5);
      mdWebText.setWidth(50);

      DDMSFieldBuilders.create(mdWebText, update.getId());

      try
      {
        MdWebText test = MdWebText.get(mdWebText.getId());

        this.assertWebAttribute(mdWebText, test);

        assertEquals(test.getHeight(), mdWebText.getHeight());
        assertEquals(test.getWidth(), mdWebText.getWidth());
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebText);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testCompositeBooleanField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebSingleTermGrid mdWebSingleTermGrid = new MdWebSingleTermGrid();
      mdWebSingleTermGrid.getDisplayLabel().setValue("Test Single Term Grid");
      mdWebSingleTermGrid.setFieldOrder(0);

      DDMSFieldBuilders.create(mdWebSingleTermGrid, update.getId());

      try
      {
        MdWebBoolean mdWebBoolean = new MdWebBoolean();
        mdWebBoolean.getDisplayLabel().setValue("Test Boolean");
        mdWebBoolean.setFieldOrder(0);

        DDMSFieldBuilders.create(mdWebBoolean, mdWebSingleTermGrid);

        try
        {
          MdWebBoolean test = MdWebBoolean.get(mdWebBoolean.getId());

          this.assertWebAttribute(mdWebBoolean, test);

          List<? extends MdWebSingleTermGrid> grids = test.getAllGrid().getAll();

          assertEquals(1, grids.size());
          assertEquals(mdWebSingleTermGrid.getId(), grids.get(0).getId());
        }
        finally
        {
          DDMSFieldBuilders.delete(mdWebBoolean);
        }
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebSingleTermGrid);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testCompositeDecimalField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebSingleTermGrid mdWebSingleTermGrid = new MdWebSingleTermGrid();
      mdWebSingleTermGrid.getDisplayLabel().setValue("Test Single Term Grid");
      mdWebSingleTermGrid.setFieldOrder(0);

      DDMSFieldBuilders.create(mdWebSingleTermGrid, update.getId());

      try
      {
        MdWebDecimal mdWebDecimal = new MdWebDecimal();
        mdWebDecimal.getDisplayLabel().setValue("Test Decimal");
        mdWebDecimal.setFieldOrder(0);
        mdWebDecimal.setDecPrecision(10);
        mdWebDecimal.setDecScale(2);

        DDMSFieldBuilders.create(mdWebDecimal, mdWebSingleTermGrid);

        try
        {
          MdWebDecimal test = MdWebDecimal.get(mdWebDecimal.getId());

          this.assertWebDec(mdWebDecimal, test);

          List<? extends MdWebSingleTermGrid> grids = test.getAllGrid().getAll();

          assertEquals(1, grids.size());
          assertEquals(mdWebSingleTermGrid.getId(), grids.get(0).getId());
        }
        finally
        {
          DDMSFieldBuilders.delete(mdWebDecimal);
        }
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebSingleTermGrid);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testCompositeDoubleField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebSingleTermGrid mdWebSingleTermGrid = new MdWebSingleTermGrid();
      mdWebSingleTermGrid.getDisplayLabel().setValue("Test Single Term Grid");
      mdWebSingleTermGrid.setFieldOrder(0);

      DDMSFieldBuilders.create(mdWebSingleTermGrid, update.getId());

      try
      {
        MdWebDouble mdWebDouble = new MdWebDouble();
        mdWebDouble.getDisplayLabel().setValue("Test Double");
        mdWebDouble.setFieldOrder(0);
        mdWebDouble.setDecPrecision(10);
        mdWebDouble.setDecScale(2);

        DDMSFieldBuilders.create(mdWebDouble, mdWebSingleTermGrid);

        try
        {
          MdWebDouble test = MdWebDouble.get(mdWebDouble.getId());

          this.assertWebDec(mdWebDouble, test);

          List<? extends MdWebSingleTermGrid> grids = test.getAllGrid().getAll();

          assertEquals(1, grids.size());
          assertEquals(mdWebSingleTermGrid.getId(), grids.get(0).getId());
        }
        finally
        {
          DDMSFieldBuilders.delete(mdWebDouble);
        }
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebSingleTermGrid);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testCompositeFloatField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebSingleTermGrid mdWebSingleTermGrid = new MdWebSingleTermGrid();
      mdWebSingleTermGrid.getDisplayLabel().setValue("Test Single Term Grid");
      mdWebSingleTermGrid.setFieldOrder(0);

      DDMSFieldBuilders.create(mdWebSingleTermGrid, update.getId());

      try
      {
        MdWebFloat mdWebFloat = new MdWebFloat();
        mdWebFloat.getDisplayLabel().setValue("Test Float");
        mdWebFloat.setFieldOrder(0);
        mdWebFloat.setDecPrecision(10);
        mdWebFloat.setDecScale(2);

        DDMSFieldBuilders.create(mdWebFloat, mdWebSingleTermGrid);

        try
        {
          MdWebFloat test = MdWebFloat.get(mdWebFloat.getId());

          this.assertWebDec(mdWebFloat, test);

          List<? extends MdWebSingleTermGrid> grids = test.getAllGrid().getAll();

          assertEquals(1, grids.size());
          assertEquals(mdWebSingleTermGrid.getId(), grids.get(0).getId());
        }
        finally
        {
          DDMSFieldBuilders.delete(mdWebFloat);
        }
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebSingleTermGrid);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testCompositeIntegerField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebSingleTermGrid mdWebSingleTermGrid = new MdWebSingleTermGrid();
      mdWebSingleTermGrid.getDisplayLabel().setValue("Test Single Term Grid");
      mdWebSingleTermGrid.setFieldOrder(0);

      DDMSFieldBuilders.create(mdWebSingleTermGrid, update.getId());

      try
      {
        MdWebInteger mdWebInteger = new MdWebInteger();
        mdWebInteger.getDisplayLabel().setValue("Test Integer");
        mdWebInteger.setFieldOrder(0);

        DDMSFieldBuilders.create(mdWebInteger, mdWebSingleTermGrid);

        try
        {
          MdWebInteger test = MdWebInteger.get(mdWebInteger.getId());

          this.assertWebAttribute(mdWebInteger, test);

          List<? extends MdWebSingleTermGrid> grids = test.getAllGrid().getAll();

          assertEquals(1, grids.size());
          assertEquals(mdWebSingleTermGrid.getId(), grids.get(0).getId());
        }
        finally
        {
          DDMSFieldBuilders.delete(mdWebInteger);
        }
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebSingleTermGrid);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }

  @Request
  @Test
  public void testCompositeLongField()
  {
    MdWebForm update = this.createForm();

    try
    {
      MdWebSingleTermGrid mdWebSingleTermGrid = new MdWebSingleTermGrid();
      mdWebSingleTermGrid.getDisplayLabel().setValue("Test Single Term Grid");
      mdWebSingleTermGrid.setFieldOrder(0);

      DDMSFieldBuilders.create(mdWebSingleTermGrid, update.getId());

      try
      {
        MdWebLong mdWebLong = new MdWebLong();
        mdWebLong.getDisplayLabel().setValue("Test Long");
        mdWebLong.setFieldOrder(0);

        DDMSFieldBuilders.create(mdWebLong, mdWebSingleTermGrid);

        try
        {
          MdWebLong test = MdWebLong.get(mdWebLong.getId());

          this.assertWebAttribute(mdWebLong, test);

          List<? extends MdWebSingleTermGrid> grids = test.getAllGrid().getAll();

          assertEquals(1, grids.size());
          assertEquals(mdWebSingleTermGrid.getId(), grids.get(0).getId());
        }
        finally
        {
          DDMSFieldBuilders.delete(mdWebLong);
        }
      }
      finally
      {
        DDMSFieldBuilders.delete(mdWebSingleTermGrid);
      }
    }
    finally
    {
      MdFormUtil.delete(update);
    }
  }
}
