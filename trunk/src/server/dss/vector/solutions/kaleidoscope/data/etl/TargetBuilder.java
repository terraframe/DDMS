/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.kaleidoscope.data.etl;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.AttributeDateParseException;
import com.runwaysdk.AttributeDecimalParseException;
import com.runwaysdk.constants.MdAttributeCharacterInfo;
import com.runwaysdk.constants.MdAttributeDoubleInfo;
import com.runwaysdk.constants.MdAttributeLocalInfo;
import com.runwaysdk.constants.MdAttributeReferenceInfo;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdAttributeCharacterDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeReferenceDAO;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.gis.constants.MdAttributeMultiPolygonInfo;
import com.runwaysdk.gis.constants.MdAttributePointInfo;
import com.runwaysdk.gis.dataaccess.metadata.MdAttributeMultiPolygonDAO;
import com.runwaysdk.gis.dataaccess.metadata.MdAttributePointDAO;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.gis.metadata.MdAttributeMultiPolygon;
import com.runwaysdk.system.gis.metadata.MdAttributePoint;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeReference;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdEntity;
import com.runwaysdk.system.metadata.MdField;
import com.runwaysdk.system.metadata.MdWebAttribute;
import com.runwaysdk.system.metadata.MdWebBoolean;
import com.runwaysdk.system.metadata.MdWebCharacter;
import com.runwaysdk.system.metadata.MdWebDate;
import com.runwaysdk.system.metadata.MdWebDouble;
import com.runwaysdk.system.metadata.MdWebField;
import com.runwaysdk.system.metadata.MdWebForm;
import com.runwaysdk.system.metadata.MdWebGeo;
import com.runwaysdk.system.metadata.MdWebLong;
import com.runwaysdk.system.metadata.MdWebNumber;
import com.runwaysdk.system.metadata.MdWebSingleTerm;
import com.runwaysdk.system.metadata.MdWebText;

import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.LocalizationUtil;
import dss.vector.solutions.RequiredAttributeException;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.generator.MdFormUtil;
import dss.vector.solutions.geo.GeoField;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.kaleidoscope.MappableAttribute;
import dss.vector.solutions.kaleidoscope.MappableClass;
import dss.vector.solutions.kaleidoscope.geo.GeoNode;
import dss.vector.solutions.kaleidoscope.geo.GeoNodeEntity;
import dss.vector.solutions.kaleidoscope.geo.GeoNodeGeometry;
import dss.vector.solutions.ontology.BrowserField;
import dss.vector.solutions.ontology.BrowserRoot;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.MDSSProperties;

public class TargetBuilder implements Reloadable
{
  public static final String PACKAGE_NAME = "dss.vector.solutions.kaleidoscope.data.business";

  public static final String EXLUDE       = "EXCLUDE";

  public static final String DERIVE       = "DERIVE";

  public static final String ROOT_TERM_ID = "DDMS:0000001";

  private JSONObject         configuration;

  private SourceContextIF    source;

  private TargetContext      target;

  public TargetBuilder(JSONObject configuration, SourceContextIF source, TargetContext target)
  {
    this.configuration = configuration;
    this.source = source;
    this.target = target;
  }

  public void build()
  {
    try
    {
      JSONArray cSheets = configuration.getJSONArray("sheets");

      // for (int i = 0; i < cSheets.length(); i++)
      {
        JSONObject sheet = cSheets.getJSONObject(0);

        if (sheet.has("existing"))
        {
          String bindingId = sheet.getString("existing");

          ExcelSourceBinding sBinding = ExcelSourceBinding.get(bindingId);
          TargetBinding tBinding = sBinding.getTargetBinding();

          this.target.addDefinition(tBinding.getDefinition());

          /*
           * Delete the existing data if required
           */
          boolean replace = sheet.has("replaceExisting") && sheet.getBoolean("replaceExisting");

          if (replace)
          {
            MdWebForm mdWebForm = tBinding.getTargetBusiness();
            MdClass mdClass = mdWebForm.getFormMdClass();

            if (mdClass instanceof MdEntity)
            {
              MdEntityDAO mdEntity = MdEntityDAO.getMdEntityDAO(mdClass.definesType()).getBusinessDAO();
              mdEntity.deleteAllRecords();
            }
          }
        }
        else
        {
          TargetDefinitionIF definition = this.createMdBusiness(sheet);

          this.target.addDefinition(definition);
        }
      }

      this.setupLocationExclusions();
      this.setupCategoryExclusions();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  private TargetDefinitionIF createMdBusiness(JSONObject cSheet) throws JSONException
  {
    String sheetName = cSheet.getString("name");
    String label = cSheet.getString("label");
    String description = ( cSheet.has("description") ? cSheet.getString("description") : "" );
    String dSource = ( cSheet.has("source") ? cSheet.getString("source") : "" );
    List<GeoNode> nodes = new LinkedList<GeoNode>();

    GeoHierarchy lowest = GeoHierarchy.getRoot();

    String sourceType = this.source.getType(sheetName);

    /*
     * Define the new MdForm and MdBusiness
     */
    MdWebForm mdForm = new MdWebForm();
    mdForm.getDisplayLabel().setValue(label);
    mdForm.getDescription().setValue(description);
    mdForm.setFormName(label);

    MdFormUtil.apply(mdForm, false);

    TargetDefinition definition = new TargetDefinition();
    definition.setSourceType(sourceType);
    definition.setTargetType(mdForm.definesType());

    /*
     * Create the default classifier root for the MdBusiness
     */
    Term root = Term.getByTermId(ROOT_TERM_ID);

    /*
     * Add all of the basic fields
     */
    if (cSheet.has("fields"))
    {
      JSONArray cFields = cSheet.getJSONArray("fields");

      for (int i = 0; i < cFields.length(); i++)
      {
        JSONObject cField = cFields.getJSONObject(i);

        if (this.isValid(cField))
        {
          TargetFieldIF field = this.createMdAttribute(mdForm, sheetName, cField, root);

          definition.addField(field);
        }
      }
    }

    /*
     * Add all of the text location fields
     */
    if (cSheet.has("attributes"))
    {
      JSONObject cAttributes = cSheet.getJSONObject("attributes");
      JSONObject values = cAttributes.getJSONObject("values");
      JSONArray ids = cAttributes.getJSONArray("ids");

      Set<String> references = this.getReferencedLocationAttributes(cSheet);

      for (int i = 0; i < ids.length(); i++)
      {
        String id = ids.getString(i);
        JSONObject cField = values.getJSONObject(id);
        String universalId = cField.getString("universal");

        lowest = this.setLowest(lowest, universalId);

        TargetFieldIF field = this.createMdGeoEntity(mdForm, sheetName, cField);

        definition.addField(field);

        // Create the geoNode
        if (!references.contains(field.getLabel()))
        {
          String key = field.getKey();
          MdWebGeo mdField = MdWebGeo.getByKey(key);
          MdAttributeReference mdAttribute = (MdAttributeReference) mdField.getDefiningMdAttribute();

          GeoNodeEntity node = new GeoNodeEntity();
          node.setKeyName(key);
          node.setGeoEntityAttribute(mdAttribute);
          node.apply();

          nodes.add(node);
        }
      }
    }

    /*
     * Add all of the coordinate fields
     */
    if (cSheet.has("coordinates"))
    {
      // Object object = cSheet.get("coordinates");
      //
      // if (object instanceof JSONObject)
      // {
      // JSONObject cCoordinates = (JSONObject) object;
      // JSONObject values = cCoordinates.getJSONObject("values");
      // JSONArray ids = cCoordinates.getJSONArray("ids");
      //
      // for (int i = 0; i < ids.length(); i++)
      // {
      // String id = ids.getString(i);
      // JSONObject cField = values.getJSONObject(id);
      //
      // lowest = this.createGeoNodeGeometry(sheetName, nodes, country, lowest, definition, mdBusiness, cField);
      // }
      // }
      // else
      // {
      // JSONArray cCoordinates = (JSONArray) object;
      //
      // for (int i = 0; i < cCoordinates.length(); i++)
      // {
      // JSONObject cField = cCoordinates.getJSONObject(i);
      //
      // lowest = this.createGeoNodeGeometry(sheetName, nodes, country, lowest, definition, mdBusiness, cField);
      // }
      // }
      //
    }

    /*
     * Create the MappableClass
     */
    MdClass mdClass = mdForm.getFormMdClass();

    MappableClass mClass = new MappableClass();
    mClass.setWrappedMdClass(mdClass);
    mClass.setDataSource(dSource);
    mClass.setDisease(Disease.getCurrent());
    mClass.setRemovable(true);
    mClass.apply();

    mClass.addUniversal(lowest).apply();

    for (GeoNode node : nodes)
    {
      mClass.addGeoNode(node).apply();
    }

    List<TargetFieldIF> fields = definition.getFields();

    for (TargetFieldIF field : fields)
    {
      MdWebAttribute mdField = MdWebAttribute.getByKey(field.getKey());
      MdAttribute mdAttribute = mdField.getDefiningMdAttribute();

      MappableAttribute mAttribute = new MappableAttribute();
      mAttribute.setWrappedMdAttribute(mdAttribute);
      mAttribute.setAggregatable(field.getAggregatable());
      mAttribute.apply();
    }

    /*
     * Create the persistence strategy
     */
    LocalPersistenceStrategy strategy = new LocalPersistenceStrategy();
    strategy.apply();

    definition.setStrategy(strategy);

    return definition;
  }

  private GeoHierarchy createGeoNodeGeometry(String sheetName, List<GeoNode> nodes, GeoEntity country, GeoHierarchy lowest, TargetDefinition definition, MdBusinessDAO mdBusiness, JSONObject cField) throws JSONException
  {
    String universalId = cField.getString("universal");

    if (universalId != null && universalId.length() > 0)
    {
      lowest = this.setLowest(lowest, universalId);
    }

    TargetFieldIF point = this.createMdPoint(mdBusiness, sheetName, cField);
    TargetFieldIF multiPolygon = this.createMdMultiPolygon(mdBusiness, sheetName, cField);
    TargetFieldIF featureId = this.createFeatureId(mdBusiness, sheetName, cField);
    TargetFieldIF location = this.createLocationField(mdBusiness, sheetName, cField, country, definition);
    TargetFieldIF featureLabel = definition.getFieldByLabel(cField.getString("featureLabel"));

    definition.addField(point);
    definition.addField(multiPolygon);
    definition.addField(featureId);
    definition.addField(location);

    // Create the geoNode
    GeoNodeGeometry node = new GeoNodeGeometry();
    node.setKeyName(point.getKey());
    node.setGeoEntityAttribute(MdAttributeReference.getByKey(location.getKey()));
    node.setIdentifierAttribute(MdAttribute.getByKey(featureId.getKey()));
    node.setDisplayLabelAttribute(MdAttribute.getByKey(featureLabel.getKey()));
    node.setGeometryAttribute(MdAttribute.getByKey(point.getKey()));
    node.setMultiPolygonAttribute(MdAttributeMultiPolygon.getByKey(multiPolygon.getKey()));
    node.setPointAttribute(MdAttributePoint.getByKey(point.getKey()));
    node.apply();

    nodes.add(node);

    return lowest;
  }

  private Set<String> getReferencedLocationAttributes(JSONObject cSheet) throws JSONException
  {
    Set<String> locations = new TreeSet<String>();

    if (cSheet.has("coordinates"))
    {
      Object object = cSheet.get("coordinates");

      if (object instanceof JSONObject)
      {
        JSONObject cCoordinates = (JSONObject) object;
        JSONObject values = cCoordinates.getJSONObject("values");
        JSONArray ids = cCoordinates.getJSONArray("ids");

        for (int i = 0; i < ids.length(); i++)
        {
          String id = ids.getString(i);
          JSONObject cField = values.getJSONObject(id);
          String location = cField.getString("location");

          locations.add(location);
        }
      }
      else
      {
        JSONArray cCoordinates = (JSONArray) object;

        for (int i = 0; i < cCoordinates.length(); i++)
        {
          JSONObject cField = cCoordinates.getJSONObject(i);
          String location = cField.getString("location");

          locations.add(location);
        }
      }
    }

    return locations;
  }

  private GeoHierarchy setLowest(GeoHierarchy current, String universalId)
  {
    GeoHierarchy universal = GeoHierarchy.get(universalId);

    if (GeoHierarchy.isAncestor(current.getQualifiedType(), universal.getQualifiedType()))
    {
      return universal;
    }

    return current;
  }

  private TargetFieldIF createLocationField(MdClassDAOIF mdClass, String sheetName, JSONObject cCoordinate, GeoEntity country, TargetDefinition definition) throws JSONException
  {
    String location = cCoordinate.getString("location");

    if (location.equals(DERIVE))
    {
      String label = cCoordinate.getString("label");
      String latitude = cCoordinate.getString("latitude");
      String longitude = cCoordinate.getString("longitude");
      String universalId = cCoordinate.getString("universal");
      Boolean aggregatable = this.getAggregatable(cCoordinate);

      String attributeName = this.generateAttributeName(label) + "Entity";

      MdAttributeReferenceDAO mdAttribute = MdAttributeReferenceDAO.newInstance();
      mdAttribute.setValue(MdAttributeReferenceInfo.NAME, attributeName);
      mdAttribute.setValue(MdAttributeReferenceInfo.DEFINING_MD_CLASS, mdClass.getId());
      mdAttribute.setStructValue(MdAttributeReferenceInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, label);
      mdAttribute.setValue(MdAttributeReferenceInfo.REF_MD_ENTITY, MdBusinessDAO.getMdBusinessDAO(GeoEntity.CLASS).getId());
      mdAttribute.apply();

      TargetFieldDerived field = new TargetFieldDerived();
      field.setName(attributeName);
      field.setLabel(label);
      field.setKey(mdClass.definesType() + "." + attributeName);
      field.setLatitudeSourceAttributeName(this.source.getFieldByLabel(sheetName, latitude).getAttributeName());
      field.setLongitudeSourceAttributeName(this.source.getFieldByLabel(sheetName, longitude).getAttributeName());
      field.setUniversal(GeoHierarchy.get(universalId));
      field.setCountry(country);
      field.setAggregatable(aggregatable);

      /*
       * Create the synonym restore attribute
       */
      // MdAttributeReferenceDAO synonymAttribute = MdAttributeReferenceDAO.newInstance();
      // synonymAttribute.setValue(MdAttributeReferenceInfo.NAME, attributeName + "Synonym");
      // synonymAttribute.setValue(MdAttributeReferenceInfo.DEFINING_MD_CLASS, mdClass.getId());
      // synonymAttribute.setStructValue(MdAttributeReferenceInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, label + " Synonym");
      // synonymAttribute.setValue(MdAttributeReferenceInfo.REF_MD_ENTITY, MdClassDAO.getMdTypeDAO(ClassifierSynonym.CLASS).getId());
      // synonymAttribute.apply();
      //
      // RelationshipDAO synonymRelationship = RelationshipDAO.newInstance(mdAttribute.getId(), synonymAttribute.getId(),
      // TermSynonymRelationship.CLASS);
      // synonymRelationship.setValue(RelationshipInfo.KEY, mdAttribute.getKey() + "-" + synonymAttribute.getKey());
      // synonymRelationship.apply();

      return field;
    }

    return definition.getFieldByLabel(location);
  }

  private boolean isValid(JSONObject cField) throws JSONException
  {
    String type = cField.getString("type");

    if (type.equals(ColumnType.BOOLEAN.name()))
    {
      return true;
    }
    else if (type.equals(ColumnType.DATE.name()))
    {
      return true;
    }
    else if (type.equals(ColumnType.DOUBLE.name()))
    {
      return true;
    }
    else if (type.equals(ColumnType.LONG.name()))
    {
      return true;
    }
    else if (type.equals(ColumnType.TEXT.name()))
    {
      return true;
    }
    else if (type.equals(ColumnType.CHARACTER.name()))
    {
      return true;
    }
    else if (type.equals(ColumnType.CATEGORY.name()))
    {
      return true;
    }
    else if (type.equals(ColumnType.DOMAIN.name()))
    {
      return true;
    }
    else if (type.equals(ColumnType.FORMID.name()))
    {
      return true;
    }

    return false;
  }

  private TargetFieldIF createMdAttribute(MdWebForm mdForm, String sheetName, JSONObject cField, Term root) throws JSONException
  {
    String columnType = cField.getString("type");
    String columnName = cField.getString("name");
    String label = cField.getString("label");
    String attributeName = this.generateAttributeName(label);
    String sourceAttributeName = this.source.getFieldByName(sheetName, columnName).getAttributeName();
    String key = mdForm.definesType() + "." + attributeName;
    Boolean aggregatable = this.getAggregatable(cField);

    // Create the attribute
    if (columnType.equals(ColumnType.CATEGORY.name()))
    {
      if (!cField.has("rootType") || cField.getString("rootType").length() == 0)
      {
        MdWebSingleTerm mdField = createMdAttributeTerm(mdForm, label, attributeName, cField);

        /*
         * Create the root term for the options
         */
        String categoryLabel = cField.getString("categoryLabel");

        Term classifier = new Term();
        classifier.setTermId(LocalProperty.getNextId());
        classifier.setNamespace("ddms");
        classifier.setName(categoryLabel);
        classifier.getTermDisplayLabel().setValue(categoryLabel);
        classifier.applyWithParent(root.getId(), false, "", false);

        /*
         * Add the root as an option to the MdAttributeTerm
         */
        BrowserRoot bRoot = new BrowserRoot();
        bRoot.setTerm(classifier);
        bRoot.setDisease(Disease.getCurrent());

        BrowserField bField = BrowserField.getBrowserField(mdField.getDefiningMdAttribute());
        bField.addBrowserRoot(bRoot);

        TargetFieldClassifier field = new TargetFieldClassifier();
        field.setName(attributeName);
        field.setLabel(label);
        field.setKey(key);
        field.setSourceAttributeName(sourceAttributeName);
        field.setAggregatable(aggregatable);

        return field;
      }
      else
      {
        MdWebSingleTerm mdField = createMdAttributeTerm(mdForm, label, attributeName, cField);

        JSONObject object = cField.getJSONObject("root");

        String classifierId = object.has("id") ? object.getString("id") : "";

        if (classifierId.length() > 0)
        {
          Term classifier = Term.get(classifierId);

          /*
           * Add the root as an option to the MdAttributeTerm
           */
          BrowserRoot bRoot = new BrowserRoot();
          bRoot.setTerm(classifier);
          bRoot.setDisease(Disease.getCurrent());

          BrowserField bField = BrowserField.getBrowserField(mdField.getDefiningMdAttribute());
          bField.addBrowserRoot(bRoot);

          TargetFieldDomain field = new TargetFieldDomain();
          field.setName(attributeName);
          field.setLabel(label);
          field.setKey(key);
          field.setSourceAttributeName(sourceAttributeName);
          field.setAggregatable(aggregatable);

          return field;
        }
        else
        {
          RequiredAttributeException ex = new RequiredAttributeException();
          ex.setAttributeLabel(MDSSProperties.getString("dataUploader.categoryLabel", Session.getCurrentLocale()));
          throw ex;
        }
      }
    }
    else if (columnType.equals(ColumnType.BOOLEAN.name()))
    {
      MdWebBoolean mdField = new MdWebBoolean();
      mdField.setFieldName(attributeName);
      mdField.getDisplayLabel().setValue(label);

      this.setValidations(mdField, cField);

      MdFormUtil.createMdField(mdField, mdForm.getId(), false);
    }
    else if (columnType.equals(ColumnType.DATE.name()))
    {
      MdWebDate mdField = new MdWebDate();
      mdField.setFieldName(attributeName);
      mdField.getDisplayLabel().setValue(label);

      this.setValidations(mdField, cField);

      MdFormUtil.createMdField(mdField, mdForm.getId(), false);
    }
    else if (columnType.equals(ColumnType.DOUBLE.name()))
    {
      int length = cField.getInt("precision");
      int decimal = cField.getInt("scale");

      MdWebDouble mdField = new MdWebDouble();
      mdField.setFieldName(attributeName);
      mdField.getDisplayLabel().setValue(label);
      mdField.setDecPrecision(length);
      mdField.setDecScale(decimal);

      this.setValidations(mdField, cField);

      MdFormUtil.createMdField(mdField, mdForm.getId(), false);
    }
    else if (columnType.equals(ColumnType.LONG.name()))
    {
      MdWebLong mdField = new MdWebLong();
      mdField.setFieldName(attributeName);
      mdField.getDisplayLabel().setValue(label);

      this.setValidations(mdField, cField);

      MdFormUtil.createMdField(mdField, mdForm.getId(), false);
    }
    else if (columnType.equals(ColumnType.TEXT.name()))
    {
      MdWebText mdField = new MdWebText();
      mdField.setFieldName(attributeName);
      mdField.getDisplayLabel().setValue(label);

      this.setValidations(mdField, cField);

      MdFormUtil.createMdField(mdField, mdForm.getId(), false);
    }
    else if (columnType.equals(ColumnType.CHARACTER.name()))
    {
      MdWebCharacter mdField = new MdWebCharacter();
      mdField.setFieldName(attributeName);
      mdField.getDisplayLabel().setValue(label);
      mdField.setDisplayLength(32);
      mdField.setMaxLength(128);
      
      this.setValidations(mdField, cField);
      
      MdFormUtil.createMdField(mdField, mdForm.getId(), false);
    }

    if (columnType.equals(ColumnType.FORMID.name()))
    {
      MdField mdField = mdForm.getField(MdFormUtil.OID);
      
      // Create the target field
      TargetFieldBasic field = new TargetFieldBasic();
      field.setName(mdField.getFieldName());
      field.setLabel(mdField.getDisplayLabel().getValue());
      field.setKey(mdField.getKey());
      field.setSourceAttributeName(sourceAttributeName);
      field.setAggregatable(aggregatable);

      return field;
    }
    else
    {
      // Create the target field
      TargetFieldBasic field = new TargetFieldBasic();
      field.setName(attributeName);
      field.setLabel(label);
      field.setKey(key);
      field.setSourceAttributeName(sourceAttributeName);
      field.setAggregatable(aggregatable);

      return field;
    }
  }

  private void setValidations(MdWebField mdField, JSONObject cField) throws JSONException
  {
    if (cField.has("hasValidation") && cField.getBoolean("hasValidation"))
    {
      if (cField.has("required"))
      {
        mdField.setRequired(cField.getBoolean("required"));
      }
    }
  }

  private void setValidations(MdWebDate mdField, JSONObject cField) throws JSONException
  {
    this.setValidations((MdWebField) mdField, cField);

    if (cField.has("hasValidation") && cField.getBoolean("hasValidation"))
    {
      String validation = cField.has("validation") ? cField.getString("validation") : "";

      if (validation.equalsIgnoreCase("bte"))
      {
        mdField.setBeforeTodayExclusive(true);
      }
      else if (validation.equalsIgnoreCase("bti"))
      {
        mdField.setBeforeTodayInclusive(true);
      }
      else if (validation.equalsIgnoreCase("ate"))
      {
        mdField.setAfterTodayExclusive(true);
      }
      else if (validation.equalsIgnoreCase("ati"))
      {
        mdField.setAfterTodayInclusive(true);
      }
      else if (validation.equalsIgnoreCase("range"))
      {
        SimpleDateFormat format = LocalizationUtil.getDateFormat();

        if (cField.has("validationStartRange"))
        {
          String value = cField.getString("validationStartRange");

          try
          {
            Date date = format.parse(value);
            mdField.setStartDate(date);
          }
          catch (ParseException e)
          {
            throw new AttributeDateParseException("", MDSSProperties.getString("dataUploader.startDate"), value, format.toPattern());
          }

        }

        if (cField.has("validationEndRange"))
        {
          String value = cField.getString("validationEndRange");

          try
          {
            Date date = format.parse(value);
            mdField.setEndDate(date);
          }
          catch (ParseException e)
          {
            throw new AttributeDateParseException("", MDSSProperties.getString("dataUploader.endDate"), value, format.toPattern());
          }
        }
      }
    }
  }

  private void setValidations(MdWebNumber mdField, JSONObject cField) throws JSONException
  {
    this.setValidations((MdWebField) mdField, cField);

    if (cField.has("hasValidation") && cField.getBoolean("hasValidation"))
    {
      if (cField.has("validationStartRange"))
      {
        String startRange = cField.getString("validationStartRange");
        NumberFormat format = NumberFormat.getInstance(Session.getCurrentLocale());

        try
        {
          Number value = format.parse(startRange);

          mdField.setStartRange(value.toString());
        }
        catch (ParseException e)
        {
          throw new AttributeDecimalParseException("", e, MdAttributeDoubleInfo.START_RANGE, startRange);
        }
      }

      if (cField.has("validationEndRange"))
      {
        String endRange = cField.getString("validationEndRange");
        NumberFormat format = NumberFormat.getInstance(Session.getCurrentLocale());

        try
        {
          Number value = format.parse(endRange);

          mdField.setEndRange(value.toString());
        }
        catch (ParseException e)
        {
          throw new AttributeDecimalParseException("", e, MdAttributeDoubleInfo.END_RANGE, endRange);
        }
      }
    }
  }

  private MdWebSingleTerm createMdAttributeTerm(MdWebForm mdForm, String label, String attributeName, JSONObject cField) throws JSONException
  {
    MdWebSingleTerm mdField = new MdWebSingleTerm();
    mdField.setFieldName(attributeName);
    mdField.getDisplayLabel().setValue(label);

    this.setValidations(mdField, cField);

    MdFormUtil.createMdField(mdField, mdForm.getId(), false);

    return mdField;
  }

  private Boolean getAggregatable(JSONObject cField) throws JSONException
  {
    return cField.has("ratio") ? !cField.getBoolean("ratio") : true;
  }

  private TargetFieldIF createMdGeoEntity(MdWebForm mdForm, String sheetName, JSONObject cAttribute) throws JSONException
  {
    String label = cAttribute.getString("label");
    String universalId = cAttribute.getString("universal");
    Boolean aggregatable = this.getAggregatable(cAttribute);
    String attributeName = this.generateAttributeName(label);

    MdWebGeo mdField = new MdWebGeo();
    mdField.setFieldName(attributeName);
    mdField.getDisplayLabel().setValue(label);

    MdFormUtil.createGeoField(mdField, mdForm.getId(), new GeoField(), new String[] { universalId }, false);

    GeoHierarchy lowest = GeoHierarchy.get(universalId);
    List<GeoHierarchy> universals = lowest.getAllParents();
    universals.add(0, lowest);

    Collections.reverse(universals);

    TargetFieldGeoEntity field = new TargetFieldGeoEntity();
    field.setName(attributeName);
    field.setLabel(label);
    field.setKey(mdForm.definesType() + "." + attributeName);
    field.setRoot(Earth.getEarthInstance());
    field.setAggregatable(aggregatable);

    JSONObject fields = cAttribute.getJSONObject("fields");

    for (GeoHierarchy universal : universals)
    {
      if (fields.has(universal.getId()))
      {
        String sLabel = fields.getString(universal.getId());

        if (!sLabel.equals(EXLUDE))
        {
          SourceFieldIF sField = source.getFieldByLabel(sheetName, sLabel);

          field.addUniversalAttribute(sField.getAttributeName(), sLabel, universal);
        }
      }
    }

    return field;
  }

  private TargetFieldIF createMdMultiPolygon(MdClassDAO mdClass, String sheetName, JSONObject cCoordinate) throws JSONException
  {
    String label = cCoordinate.getString("label");
    String latitude = cCoordinate.getString("latitude");
    String longitude = cCoordinate.getString("longitude");
    Boolean aggregatable = this.getAggregatable(cCoordinate);

    String attributeName = this.generateAttributeName(label) + "MultiPolygon";

    MdAttributeMultiPolygonDAO mdAttribute = MdAttributeMultiPolygonDAO.newInstance();
    mdAttribute.setValue(MdAttributeMultiPolygonInfo.NAME, attributeName);
    mdAttribute.setValue(MdAttributeMultiPolygonInfo.DEFINING_MD_CLASS, mdClass.getId());
    mdAttribute.setStructValue(MdAttributeMultiPolygonInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, label);
    mdAttribute.setValue(MdAttributeMultiPolygonInfo.DIMENSION, "2");
    mdAttribute.setValue(MdAttributeMultiPolygonInfo.SRID, "4326");
    mdAttribute.apply();

    TargetFieldMultiPolygon field = new TargetFieldMultiPolygon();
    field.setName(attributeName);
    field.setLabel(label);
    field.setKey(mdClass.definesType() + "." + attributeName);
    field.setLatitudeSourceAttributeName(this.source.getFieldByLabel(sheetName, latitude).getAttributeName());
    field.setLongitudeSourceAttributeName(this.source.getFieldByLabel(sheetName, longitude).getAttributeName());
    field.setAggregatable(aggregatable);

    return field;
  }

  private TargetFieldIF createMdPoint(MdClassDAO mdClass, String sheetName, JSONObject cCoordinate) throws JSONException
  {
    String label = cCoordinate.getString("label");
    String latitude = cCoordinate.getString("latitude");
    String longitude = cCoordinate.getString("longitude");
    Boolean aggregatable = this.getAggregatable(cCoordinate);

    String attributeName = this.generateAttributeName(label) + "Point";

    MdAttributePointDAO mdAttribute = MdAttributePointDAO.newInstance();
    mdAttribute.setValue(MdAttributePointInfo.NAME, attributeName);
    mdAttribute.setValue(MdAttributePointInfo.DEFINING_MD_CLASS, mdClass.getId());
    mdAttribute.setStructValue(MdAttributePointInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, label);
    mdAttribute.setValue(MdAttributePointInfo.DIMENSION, "2");
    mdAttribute.setValue(MdAttributePointInfo.SRID, "4326");
    mdAttribute.apply();

    SourceFieldIF latField = this.source.getFieldByLabel(sheetName, latitude);
    SourceFieldIF longField = this.source.getFieldByLabel(sheetName, longitude);

    TargetFieldPoint field = new TargetFieldPoint();
    field.setName(attributeName);
    field.setLabel(label);
    field.setKey(mdClass.definesType() + "." + attributeName);
    field.setLatitudeSourceAttributeName(latField.getAttributeName());
    field.setLatitudeLabel(latField.getLabel());
    field.setLongitudeSourceAttributeName(longField.getAttributeName());
    field.setLongitudeLabel(latField.getLabel());
    field.setAggregatable(aggregatable);

    return field;
  }

  private TargetFieldIF createFeatureId(MdClassDAO mdClass, String sheetName, JSONObject cCoordinate) throws JSONException
  {
    String label = cCoordinate.getString("label");

    String attributeName = this.generateAttributeName(label) + "FeatureId";

    MdAttributeCharacterDAO mdAttribute = MdAttributeCharacterDAO.newInstance();
    mdAttribute.setValue(MdAttributeCharacterInfo.NAME, attributeName);
    mdAttribute.setValue(MdAttributeCharacterInfo.DEFINING_MD_CLASS, mdClass.getId());
    mdAttribute.setValue(MdAttributeCharacterInfo.SIZE, "64");
    mdAttribute.setStructValue(MdAttributeCharacterInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, label);
    mdAttribute.apply();

    TargetFieldGenerated field = new TargetFieldGenerated();
    field.setName(attributeName);
    field.setLabel(label);
    field.setKey(mdClass.definesType() + "." + attributeName);

    return field;
  }

  private String generateAttributeName(String label)
  {
    return GeoHierarchy.getSystemName(label, "Attribute", false);
  }

  public void setupLocationExclusions()
  {
    try
    {
      Map<String, Set<String>> exclusions = new HashMap<String, Set<String>>();

      if (this.configuration.has("locationExclusions"))
      {
        JSONArray locationExclusionsArr = this.configuration.getJSONArray("locationExclusions");

        // convert to native Java data structure rather than JSON
        for (int i = 0; i < locationExclusionsArr.length(); i++)
        {
          JSONObject object = locationExclusionsArr.getJSONObject(i);

          String universal = object.getString("universal");
          String parentId = object.getString("parentId");
          String label = object.getString("locationLabel");

          String key = universal + "-" + parentId;

          exclusions.putIfAbsent(key, new HashSet<String>());
          exclusions.get(key).add(label);
        }
      }

      this.target.setLocationExclusions(exclusions);
    }
    catch (JSONException e)
    {
      String devMsg = "Could not parse dataset configuration string to JSON.";
      throw new ProgrammingErrorException(devMsg, e);
    }
  }

  @SuppressWarnings("unchecked")
  public void setupCategoryExclusions()
  {
    try
    {
      Map<String, Set<String>> exclusions = new HashMap<String, Set<String>>();

      if (this.configuration.has("categoryExclusion"))
      {
        JSONObject categoryExclusion = this.configuration.getJSONObject("categoryExclusion");

        Iterator<String> keys = categoryExclusion.keys();

        while (keys.hasNext())
        {
          String mdAttributeId = keys.next();

          exclusions.putIfAbsent(mdAttributeId, new HashSet<String>());

          JSONArray labels = categoryExclusion.getJSONArray(mdAttributeId);

          for (int i = 0; i < labels.length(); i++)
          {
            String label = labels.getString(i);

            exclusions.get(mdAttributeId).add(label);
          }
        }
      }

      this.target.setCategoryExclusions(exclusions);
    }
    catch (JSONException e)
    {
      String devMsg = "Could not parse dataset configuration string to JSON.";
      throw new ProgrammingErrorException(devMsg, e);
    }
  }
}
