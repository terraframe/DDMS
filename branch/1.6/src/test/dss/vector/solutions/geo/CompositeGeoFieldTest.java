package dss.vector.solutions.geo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dss.vector.solutions.geo.generated.CountryDTO;
import dss.vector.solutions.geo.generated.StockDepotDTO;
import dss.vector.solutions.query.CompositeGeoField;
import dss.vector.solutions.query.SimpleGeoField;

public class CompositeGeoFieldTest
{
  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public void testDefault()
  {
    CompositeGeoField composite = new CompositeGeoField();

    assertFalse(composite.getIsPoliticalHierarchy());
    assertFalse(composite.getIsPopulationHierarchy());
    assertFalse(composite.getIsSprayHierarchy());
    assertFalse(composite.getIsUrbanHierarchy());
    assertNotNull(composite.getExtraUniversals());
    assertEquals(0, composite.getExtraUniversals().length);
    assertEquals("[]", composite.getFilterType());
  }

  @Test
  public void testAddSingle()
  {
    SimpleGeoField field = new SimpleGeoField();
    field.addExtraUniversal(CountryDTO.CLASS);
    field.setPoliticalHierarchy(true);
    field.setPopulationHierarchy(false);
    field.setSprayHierarchy(true);
    field.setUrbanHierarchy(true);

    CompositeGeoField composite = new CompositeGeoField();
    composite.addField(field);

    String[] fieldUniversals = field.getExtraUniversals();
    List<String> compositeUniversals = Arrays.asList(composite.getExtraUniversals());

    assertEquals(field.getIsPoliticalHierarchy(), composite.getIsPoliticalHierarchy());
    assertEquals(field.getIsPopulationHierarchy(), composite.getIsPopulationHierarchy());
    assertEquals(field.getIsSprayHierarchy(), composite.getIsSprayHierarchy());
    assertEquals(field.getIsUrbanHierarchy(), composite.getIsUrbanHierarchy());
    assertEquals("[]", composite.getFilterType());
    assertEquals(fieldUniversals.length, compositeUniversals.size());

    for (String universal : fieldUniversals)
    {
      assertTrue(compositeUniversals.contains(universal));
    }
  }

  @Test
  public void testAddSingleWithFilter()
  {
    SimpleGeoField field = new SimpleGeoField();
    field.setPoliticalHierarchy(true);
    field.setPopulationHierarchy(true);
    field.setSprayHierarchy(true);
    field.setUrbanHierarchy(true);
    field.addExtraUniversal(StockDepotDTO.CLASS);
    field.setFilterType(CountryDTO.CLASS);

    CompositeGeoField composite = new CompositeGeoField();
    composite.addField(field);

    List<String> compositeUniversals = Arrays.asList(composite.getExtraUniversals());

    assertTrue(composite.getIsPoliticalHierarchy());
    assertTrue(composite.getIsPopulationHierarchy());
    assertTrue(composite.getIsSprayHierarchy());
    assertTrue(composite.getIsUrbanHierarchy());
    assertEquals("[\'" + CountryDTO.CLASS + "\']", composite.getFilterType());
    assertEquals(1, compositeUniversals.size());
    assertTrue(compositeUniversals.contains(StockDepotDTO.CLASS));
  }

  @Test
  public void testMultipleGeoField()
  {
    SimpleGeoField field1 = new SimpleGeoField();
    field1.addExtraUniversal(CountryDTO.CLASS);
    field1.setPoliticalHierarchy(false);
    field1.setPopulationHierarchy(false);
    field1.setSprayHierarchy(true);
    field1.setUrbanHierarchy(true);

    SimpleGeoField field2 = new SimpleGeoField();
    field2.addExtraUniversal(CountryDTO.CLASS);
    field2.setPoliticalHierarchy(true);
    field2.setPopulationHierarchy(true);
    field2.setSprayHierarchy(false);
    field2.setUrbanHierarchy(false);

    CompositeGeoField composite = new CompositeGeoField();
    composite.addField(field1);
    composite.addField(field2);

    List<String> compositeUniversals = Arrays.asList(composite.getExtraUniversals());

    assertTrue(composite.getIsPoliticalHierarchy());
    assertTrue(composite.getIsPopulationHierarchy());
    assertTrue(composite.getIsSprayHierarchy());
    assertTrue(composite.getIsUrbanHierarchy());
    assertEquals("[]", composite.getFilterType());
    assertEquals(1, compositeUniversals.size());
    assertTrue(compositeUniversals.contains(CountryDTO.CLASS));
  }

  @Test
  public void testOverwriteFilter()
  {
    SimpleGeoField field1 = new SimpleGeoField();
    field1.setFilterType(CountryDTO.CLASS);

    SimpleGeoField field2 = new SimpleGeoField();
    field2.setPoliticalHierarchy(true);
    field2.setPopulationHierarchy(true);
    field2.setSprayHierarchy(false);
    field2.setUrbanHierarchy(false);

    CompositeGeoField composite = new CompositeGeoField();
    composite.addField(field1);
    composite.addField(field2);

    List<String> compositeUniversals = Arrays.asList(composite.getExtraUniversals());

    assertTrue(composite.getIsPoliticalHierarchy());
    assertTrue(composite.getIsPopulationHierarchy());
    assertFalse(composite.getIsSprayHierarchy());
    assertFalse(composite.getIsUrbanHierarchy());
    assertEquals("['" + CountryDTO.CLASS + "']", composite.getFilterType());
    assertEquals(0, compositeUniversals.size());
  }

  @Test
  public void testAddFilter()
  {
    SimpleGeoField field1 = new SimpleGeoField();
    field1.setPoliticalHierarchy(true);
    field1.setPopulationHierarchy(true);
    field1.setSprayHierarchy(false);
    field1.setUrbanHierarchy(false);

    SimpleGeoField field2 = new SimpleGeoField();
    field2.setFilterType(CountryDTO.CLASS);

    CompositeGeoField composite = new CompositeGeoField();
    composite.addField(field1);
    composite.addField(field2);

    List<String> compositeUniversals = Arrays.asList(composite.getExtraUniversals());

    assertTrue(composite.getIsPoliticalHierarchy());
    assertTrue(composite.getIsPopulationHierarchy());
    assertFalse(composite.getIsSprayHierarchy());
    assertFalse(composite.getIsUrbanHierarchy());
    assertEquals("['" + CountryDTO.CLASS + "']", composite.getFilterType());
    assertEquals(0, compositeUniversals.size());
  }

  @Test
  public void testAddMultipleFilter()
  {
    SimpleGeoField field1 = new SimpleGeoField();
    field1.setPoliticalHierarchy(true);
    field1.setPopulationHierarchy(true);
    field1.setSprayHierarchy(false);
    field1.setUrbanHierarchy(false);
    field1.setFilterType(StockDepotDTO.CLASS);

    SimpleGeoField field2 = new SimpleGeoField();
    field2.setFilterType(CountryDTO.CLASS);

    CompositeGeoField composite = new CompositeGeoField();
    composite.addField(field1);
    composite.addField(field2);

    List<String> compositeUniversals = Arrays.asList(composite.getExtraUniversals());

    assertTrue(composite.getIsPoliticalHierarchy());
    assertTrue(composite.getIsPopulationHierarchy());
    assertFalse(composite.getIsSprayHierarchy());
    assertFalse(composite.getIsUrbanHierarchy());
    assertEquals("['" + CountryDTO.CLASS + "','" + StockDepotDTO.CLASS + "']", composite.getFilterType());
    assertEquals(0, compositeUniversals.size());
  }
}
