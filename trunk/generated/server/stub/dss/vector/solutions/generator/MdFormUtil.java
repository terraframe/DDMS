package dss.vector.solutions.generator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.io.IOUtils;
import org.apache.tools.ant.filters.StringInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.business.Relationship;
import com.runwaysdk.business.RelationshipQuery;
import com.runwaysdk.business.generation.EntityQueryAPIGenerator;
import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.constants.BusinessInfo;
import com.runwaysdk.constants.ComponentInfo;
import com.runwaysdk.constants.MdWebFormInfo;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeRefDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdFieldDAOIF;
import com.runwaysdk.dataaccess.MdFormDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.MdTypeDAOIF;
import com.runwaysdk.dataaccess.MdWebAttributeDAOIF;
import com.runwaysdk.dataaccess.MdWebFieldDAOIF;
import com.runwaysdk.dataaccess.MdWebFormDAOIF;
import com.runwaysdk.dataaccess.MdWebGeoDAOIF;
import com.runwaysdk.dataaccess.MdWebMultipleTermDAOIF;
import com.runwaysdk.dataaccess.MdWebSingleTermDAOIF;
import com.runwaysdk.dataaccess.MdWebSingleTermGridDAOIF;
import com.runwaysdk.dataaccess.MetadataDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.FormExcelExporter;
import com.runwaysdk.dataaccess.io.ImportManager;
import com.runwaysdk.dataaccess.io.StringMarkupWriter;
import com.runwaysdk.dataaccess.io.StringStreamSource;
import com.runwaysdk.dataaccess.io.XMLParseException;
import com.runwaysdk.dataaccess.io.dataDefinition.ExportMetadata;
import com.runwaysdk.dataaccess.io.dataDefinition.SAXExporter;
import com.runwaysdk.dataaccess.io.dataDefinition.SAXImporter;
import com.runwaysdk.dataaccess.io.excel.ImportApplyListener;
import com.runwaysdk.dataaccess.metadata.MdAttributeConcreteDAO;
import com.runwaysdk.dataaccess.metadata.MdFormDAO;
import com.runwaysdk.dataaccess.metadata.MdRelationshipDAO;
import com.runwaysdk.dataaccess.metadata.MdWebFieldDAO;
import com.runwaysdk.dataaccess.metadata.MdWebFormDAO;
import com.runwaysdk.dataaccess.metadata.MetadataCannotBeDeletedException;
import com.runwaysdk.dataaccess.metadata.MetadataDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Attribute;
import com.runwaysdk.query.AttributePrimitive;
import com.runwaysdk.query.AttributeReference;
import com.runwaysdk.query.GeneratedBusinessQuery;
import com.runwaysdk.query.GeneratedComponentQuery;
import com.runwaysdk.query.GeneratedViewQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OrderBy.SortOrder;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ViewQueryBuilder;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionFacade;
import com.runwaysdk.system.metadata.AndFieldCondition;
import com.runwaysdk.system.metadata.CharacterCondition;
import com.runwaysdk.system.metadata.CompositeFieldCondition;
import com.runwaysdk.system.metadata.FieldCondition;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeCharacter;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeIndices;
import com.runwaysdk.system.metadata.MdAttributeReference;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdField;
import com.runwaysdk.system.metadata.MdForm;
import com.runwaysdk.system.metadata.MdRelationship;
import com.runwaysdk.system.metadata.MdWebAttribute;
import com.runwaysdk.system.metadata.MdWebAttributeQuery;
import com.runwaysdk.system.metadata.MdWebBreak;
import com.runwaysdk.system.metadata.MdWebCharacter;
import com.runwaysdk.system.metadata.MdWebComment;
import com.runwaysdk.system.metadata.MdWebField;
import com.runwaysdk.system.metadata.MdWebFieldQuery;
import com.runwaysdk.system.metadata.MdWebForm;
import com.runwaysdk.system.metadata.MdWebFormQuery;
import com.runwaysdk.system.metadata.MdWebGeo;
import com.runwaysdk.system.metadata.MdWebGroup;
import com.runwaysdk.system.metadata.MdWebHeader;
import com.runwaysdk.system.metadata.MdWebMultipleTerm;
import com.runwaysdk.system.metadata.MdWebPrimitive;
import com.runwaysdk.system.metadata.MdWebReference;
import com.runwaysdk.system.metadata.MdWebSingleTermGrid;
import com.runwaysdk.system.metadata.WebGridField;
import com.runwaysdk.system.metadata.WebGridFieldQuery;
import com.runwaysdk.system.metadata.WebGroupField;
import com.runwaysdk.system.metadata.WebGroupFieldQuery;

import dss.vector.solutions.InstallProperties;
import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.form.ConfirmDeleteMdFieldException;
import dss.vector.solutions.form.ConfirmDeleteMdFormException;
import dss.vector.solutions.form.DDMSFieldBuilders;
import dss.vector.solutions.form.FormNameNotBlankException;
import dss.vector.solutions.form.MdFieldTypeQuery;
import dss.vector.solutions.form.MdFormHasInstancesException;
import dss.vector.solutions.form.business.FormBedNet;
import dss.vector.solutions.form.business.FormHousehold;
import dss.vector.solutions.form.business.FormPerson;
import dss.vector.solutions.form.business.FormSurvey;
import dss.vector.solutions.form.business.HumanBloodIndex;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.EpiCache;
import dss.vector.solutions.geo.ExtraFieldUniversal;
import dss.vector.solutions.geo.GeoField;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.BrowserField;
import dss.vector.solutions.ontology.InactivePropertyQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.ontology.TermView;
import dss.vector.solutions.ontology.TermViewQuery;
import dss.vector.solutions.util.HierarchyBuilder;

public class MdFormUtil extends MdFormUtilBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long  serialVersionUID  = 1220565977;

  public static final String DISEASE           = "disease";

  public static final String OID               = "oid";

  public static final String DEFAULT_OID_LABEL = "Form ID";

  public static final String XSD_LOCATION      = "classpath:/datatype_gis.xsd";

  public MdFormUtil()
  {
    super();
  }

  /**
   * Returns all available MdField types that can be created in MDSS.
   * 
   * @return
   */
  @Authenticate
  public static MdFieldTypeQuery getAvailableFields()
  {
    InstallProperties.validateMasterOperation();

    QueryFactory f = new QueryFactory();
    MdFieldTypeQuery q = new MdFieldTypeQuery(f);
    return q;
  }

  /**
   * Creates or updates an existing
   * 
   * @param busObj
   * @return
   */
  @Transaction
  public static Business persistObject(Business busObj, String mutipleTermJSON, String singleTermGridJSON)
  {
    String sessionId = Session.getCurrentSession().getId();
    
    // A quick hack for ticket 3188. We can extend this mdForm stuff with listeners or something later if we find ourselves doing this a lot.
    if (busObj.hasAttribute(HumanBloodIndex.COLLECTIONID))
    {
      new CollectionIdValidationListener().beforeApply(busObj);
    }
    
    busObj.apply();

    try
    {
      // create the grid rows
      JSONArray entries = new JSONArray(singleTermGridJSON);
      for (int i = 0; i < entries.length(); i++)
      {
        JSONObject entry = entries.getJSONObject(i);

        String mdFieldId = entry.getString("mdField");
        MdWebSingleTermGrid grid = MdWebSingleTermGrid.get(mdFieldId);
        String relType = getRelationshipType(grid);
        MdWebPrimitive[] gridFields = MdFormUtil.getCompositeFields(mdFieldId);

        JSONArray rows = entry.getJSONArray("rows");
        for (int j = 0; j < rows.length(); j++)
        {
          JSONObject row = rows.getJSONObject(j);
          String childId = row.getString("childId");
          String relId = row.getString("relId");

          // convert the field name to the key sent by the grid (as created in
          // YUIColumn's constructor).
          Relationship rel;
          try
          {
            rel = Relationship.get(relId);
            rel.appLock();
          }
          catch (DataNotFoundException e)
          {
            rel = BusinessFacade.newRelationship(busObj.getId(), childId, relType);
          }

          for (MdWebPrimitive gridField : gridFields)
          {
            String key = CommonGenerationUtil.upperFirstCharacter(gridField.getFieldName());
            MdAttributeConcreteDAOIF attr = MdAttributeConcreteDAO.get(gridField.getDefiningMdAttributeId());
            String attrName = attr.definesAttribute();

            // null out any values that aren't in the JSON
            if (SessionFacade.checkAttributeAccess(sessionId, Operation.WRITE, attr))
            {
              if (row.has(key))
              {
                String value = row.getString(key);
                rel.setValue(attrName, value);
              }
              else
              {
                rel.setValue(attrName, null);
              }
            }
          }

          rel.apply();
        }
      }

    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }

    // Create the relationships between the MdBusiness and Term class for
    // the multiple term field.
    try
    {
      JSONArray entries = new JSONArray(mutipleTermJSON);
      for (int i = 0; i < entries.length(); i++)
      {
        JSONObject entry = entries.getJSONObject(i);
        String mdFieldId = entry.getString("mdField");
        JSONArray termIds = entry.getJSONArray("termIds");

        String[] terms = new String[termIds.length()];
        for (int j = 0; j < terms.length; j++)
        {
          terms[j] = termIds.getString(j);
        }

        MdWebField mdField = MdWebField.get(mdFieldId);
        String relType = getRelationshipType(mdField);
        createMultipleTermRelationships(busObj.getId(), terms, relType);
      }
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }

    return busObj;
  }

  @Authenticate
  public static MdFieldTypeQuery getAvailableCompositeFields()
  {
    InstallProperties.validateMasterOperation();

    QueryFactory factory = new QueryFactory();

    return new MdFieldTypeQuery(factory, new MdFieldTypeQuery.CompositeMdFieldTypeBuilder(factory));
  }

  /**
   * Returns all fields that are candidates for conditions that this field will reference. For
   * example, the given field is not allowed in the list to avoid circular references.
   */
  public static MdField[] getFieldsForConditions(String mdFieldId)
  {
    MdWebField field = MdWebField.get(mdFieldId);

    QueryFactory f = new QueryFactory();
    MdWebAttributeQuery q = new MdWebAttributeQuery(f);

    // exclude the given MdField and references
    q.WHERE(q.id().NE(field.getId()));
    q.AND(q.getType().NE(MdWebReference.CLASS));
    q.AND(q.getType().NE(MdWebBreak.CLASS));
    q.AND(q.getType().NE(MdWebHeader.CLASS));
    q.AND(q.getType().NE(MdWebComment.CLASS));
    q.AND(q.getType().NE(MdWebSingleTermGrid.CLASS));
    q.AND(q.getDefiningMdForm().EQ(field.getDefiningMdForm()));

    q.ORDER_BY_ASC(q.getFieldOrder());

    OIterator<? extends MdField> i = q.getIterator();

    try
    {
      List<? extends MdField> fields = i.getAll();
      return fields.toArray(new MdField[fields.size()]);
    }
    finally
    {
      i.close();
    }
  }

  @Transaction
  public static void createMultipleTermRelationships(String parentId, String childIds[], String relType)
  {
    // remove all old relationships instead of being too clever with diffing the
    // relationships
    QueryFactory f = new QueryFactory();
    RelationshipQuery q = f.relationshipQuery(relType);

    q.WHERE(q.parentId().EQ(parentId));

    OIterator<Relationship> iter = q.getIterator();
    try
    {
      while (iter.hasNext())
      {
        iter.next().delete();
      }
    }
    finally
    {
      iter.close();
    }

    for (String childId : childIds)
    {
      new Relationship(parentId, childId, relType).apply();
    }
  }

  /**
   * Adds the MdField to the MdWebGroup and adds the field as the last child of the group.
   * 
   * @param groupId
   * @param fieldId
   */
  @Transaction
  public static void addToGroup(String groupId, String fieldId)
  {
    InstallProperties.validateMasterOperation();

    MdWebGroup group = MdWebGroup.get(groupId);
    MdWebField field = MdWebField.get(fieldId);

    // ignore this transaction if the field already exists in the group.
    QueryFactory f = new QueryFactory();
    MdWebFieldQuery q = new MdWebFieldQuery(f);
    WebGroupFieldQuery relQ = new WebGroupFieldQuery(f);

    relQ.WHERE(relQ.parentId().EQ(groupId));
    q.WHERE(q.groupFields(relQ));
    q.AND(q.getId().EQ(fieldId));

    if (q.getCount() > 0)
    {
      return;
    }

    // remove any previous group associations
    for (WebGroupField rel : field.getAllGroupFieldsRel())
    {
      rel.delete();
    }

    /*
     * Set the order to one higher than the group if it is the first field or
     * add it to the highest order within the group's fields.
     */
    Integer order = getHighestOrder(group);
    field.appLock();
    field.setFieldOrder(order);
    field.apply();

    field.addGroupFields(group).apply();

  }

  /**
   * Returns the next highest Order number relative to the object with the given id, which can be an
   * MdForm or MdWebField.
   * 
   * @param id
   * @return
   */
  public static Integer getHighestOrder(Business obj)
  {
    QueryFactory f = new QueryFactory();
    MdWebFieldQuery q = new MdWebFieldQuery(f);

    if (obj instanceof MdWebForm)
    {
      q.WHERE(q.getDefiningMdForm().EQ(obj.getId()));
    }
    else if (obj instanceof MdWebGroup)
    {
      WebGroupFieldQuery relQ = new WebGroupFieldQuery(f);
      relQ.WHERE(relQ.parentId().EQ(obj.getId()));
      q.WHERE(q.groupFields(relQ));
    }
    else
    {
      // we should never land here.
      throw new ProgrammingErrorException("The object [" + obj + "] is not of type [" + MdWebForm.CLASS + "] or [" + MdWebGroup.CLASS + "] to retrieve the field order.");
    }

    q.ORDER_BY_DESC(q.getFieldOrder("fieldOrder"));
    q.restrictRows(1, 1);

    OIterator<? extends MdWebField> iter = q.getIterator();

    try
    {
      if (iter.hasNext())
      {
        Integer last = iter.next().getFieldOrder();
        return ++last;
      }
      else
      {
        return 0;
      }
    }
    finally
    {
      iter.close();
    }
  }

  /**
   * Creates an MdField and the associated MdAttribute in DDMS. The mapping is one-to-one.
   * 
   * @param mdField
   * @param mdFormId
   * @return
   */
  @Transaction
  @Authenticate
  public static MdField createMdField(MdField mdField, String mdFormId)
  {
    InstallProperties.validateMasterOperation();

    DDMSFieldBuilders.create(mdField, mdFormId);
    return mdField;
  }

  @Transaction
  @Authenticate
  public static MdWebGeo createGeoField(MdWebGeo mdField, String mdFormId, GeoField geoField, String[] extraUniversals)
  {
    InstallProperties.validateMasterOperation();

    DDMSFieldBuilders.createGeoField(mdField, mdFormId, geoField, extraUniversals);

    return mdField;
  }

  @Transaction
  @Authenticate
  public static MdWebPrimitive createFieldForComposite(MdWebPrimitive mdField, String mdCompositeFieldId)
  {
    InstallProperties.validateMasterOperation();

    MdWebSingleTermGrid mdWebSingleTermGrid = MdWebSingleTermGrid.get(mdCompositeFieldId);
    DDMSFieldBuilders.create(mdField, mdWebSingleTermGrid);

    return mdField;

  }

  private static void rebuildConditions(Stack<FieldCondition> conds, AndFieldCondition parent)
  {
    FieldCondition sec = conds.pop();
    parent.setSecondCondition(sec);

    FieldCondition first;
    if (conds.size() == 1)
    {
      // our final terminal node on the left branch
      first = conds.pop();
    }
    else
    {
      // continue building the left-side composite branches
      AndFieldCondition leftAnd = new AndFieldCondition();
      rebuildConditions(conds, leftAnd);
      first = leftAnd;
    }

    parent.setFirstCondition(first);
    parent.apply();
  }

  /**
   * Copies a non-composite condition and returns the applied object.
   * 
   */
  // FIXME push to clone() method?
  private static FieldCondition copyCondition(FieldCondition original)
  {
    FieldCondition copy;
    try
    {
      copy = (FieldCondition) LoaderDecorator.load(original.getClass().getName()).newInstance();
    }
    catch (Throwable t)
    {
      throw new ProgrammingErrorException(t);
    }

    if (original.hasAttribute(CharacterCondition.OPERATION))
    {
      copy.addEnumItem(CharacterCondition.OPERATION, original.getEnumValues(CharacterCondition.OPERATION).get(0).getId());
    }
    copy.setValue(CharacterCondition.DEFININGMDFIELD, original.getValue(CharacterCondition.DEFININGMDFIELD));
    copy.setValue(CharacterCondition.VALUE, original.getValue(CharacterCondition.VALUE));

    copy.apply();
    return copy;
  }

  @Transaction
  public static void deleteCondition(String mdFieldId, String conditionId)
  {
    InstallProperties.validateMasterOperation();

    MdField field = MdField.get(mdFieldId);
    FieldCondition root = field.getFieldCondition();
    FieldCondition cond = FieldCondition.get(conditionId);

    // collect all leaf and composite conditions. Delete all composites (these
    // will be rebuilt).
    Stack<FieldCondition> oldConds = new Stack<FieldCondition>();
    Stack<CompositeFieldCondition> composites = new Stack<CompositeFieldCondition>();
    getConditionsRecurse(oldConds, composites, root);

    // rebuild the condition composite without the deleted condition.
    Stack<FieldCondition> newConds = new Stack<FieldCondition>();
    for (FieldCondition c : oldConds)
    {
      if (!c.equals(cond))
      {
        FieldCondition copied = copyCondition(c);
        newConds.push(copied);
      }
    }

    root.delete(); // deletes the old root and all prior structures.

    FieldCondition newRoot = null;
    if (newConds.size() == 1)
    {
      newRoot = newConds.pop();
    }
    else if (newConds.size() > 1)
    {
      AndFieldCondition and = new AndFieldCondition();
      rebuildConditions(newConds, and);
      newRoot = and;
    }

    if (newRoot != null)
    {
      field.appLock();
      field.setFieldCondition(newRoot);
      field.apply();
    }
  }

  /**
   * Populates the given stacks with the primitive field conditions and composite conditions.
   * 
   * @param conditions
   * @param composites
   */
  private static void getConditionsRecurse(Stack<FieldCondition> conditions, Stack<CompositeFieldCondition> composites, FieldCondition parent)
  {
    if (parent == null)
    {
      return;
    }
    else if (parent instanceof CompositeFieldCondition)
    {
      CompositeFieldCondition com = (CompositeFieldCondition) parent;
      composites.add(com);

      getConditionsRecurse(conditions, composites, com.getFirstCondition());
      getConditionsRecurse(conditions, composites, com.getSecondCondition());
    }
    else
    {
      conditions.add(parent);
    }
  }

  private static FieldCondition[] getConditions(MdField field)
  {
    FieldCondition cond = field.getFieldCondition();
    Stack<FieldCondition> conds = new Stack<FieldCondition>();
    Stack<CompositeFieldCondition> coms = new Stack<CompositeFieldCondition>();
    getConditionsRecurse(conds, coms, cond);
    return conds.toArray(new FieldCondition[conds.size()]);
  }

  /**
   * Collapses the AND tree of an MdField condition into a linear list.
   * 
   * @param mdFieldId
   * @return
   */
  public static FieldCondition[] getConditions(String mdFieldId)
  {
    MdField field = MdField.get(mdFieldId);
    return getConditions(field);
  }

  @Transaction
  public static void createCondition(String mdFieldId, FieldCondition condition)
  {
    condition.apply();

    MdField field = MdField.get(mdFieldId);
    field.appLock();

    FieldCondition existing = field.getFieldCondition();
    FieldCondition root;
    if (existing != null)
    {
      AndFieldCondition and = new AndFieldCondition();
      and.setFirstCondition(existing);
      and.setSecondCondition(condition);
      and.apply();

      root = and;
    }
    else
    {
      root = condition;
    }

    field.setFieldCondition(root);

    field.apply();
  }

  @Transaction
  @Authenticate
  public static MdField updateMdField(MdField mdField)
  {
    InstallProperties.validateMasterOperation();

    DDMSFieldBuilders.update(mdField);

    return mdField;
  }

  @Transaction
  @Authenticate
  public static MdWebGeo updateGeoField(MdWebGeo mdField, GeoField geoField, String[] extraUniversals)
  {
    InstallProperties.validateMasterOperation();

    DDMSFieldBuilders.updateGeoField(mdField, geoField, extraUniversals);

    return mdField;
  }

  public static MdWebForm[] getAllForms()
  {
    MdWebFormQuery query = new MdWebFormQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getDisplayLabel().localize());

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

  private static void addFieldsToTree(JSONObject parent, MdWebField[] fields) throws JSONException
  {
    JSONArray fieldsArr = new JSONArray();
    parent.put("fields", fieldsArr);

    for (MdWebField field : fields)
    {
      JSONObject fieldJSON = new JSONObject();
      fieldsArr.put(fieldJSON);

      fieldJSON.put("label", field.toString());
      fieldJSON.put("id", field.getId());

      if (field instanceof MdWebGroup)
      {
        fieldJSON.put("nodeType", "groupNode");

        MdWebField[] gFields = getGroupFields(field.getId());
        addFieldsToTree(fieldJSON, gFields);
      }
      else
      {
        fieldJSON.put("nodeType", "fieldNode");
      }
    }
  }

  /**
   * Returns a JSON string representing the tree structure of the form and its fields.
   * 
   * @param mdFormId
   * @return
   */
  public static String getFormTree(String mdFormId)
  {
    JSONObject formJSON = new JSONObject();
    MdWebForm form = MdWebForm.get(mdFormId);
    try
    {
      formJSON.put("label", form.getDisplayLabel().getValue());
      formJSON.put("id", form.getId());
      formJSON.put("nodeType", "formNode");

      MdWebField[] fields = getFields(form);
      addFieldsToTree(formJSON, fields);

      return formJSON.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException("Could not get the JSON tree for an MdForm.", e);
    }
  }

  /**
   * Returns all fields in order for the MdWebGroup.
   * 
   * @param groupId
   * @return
   */
  public static MdWebField[] getGroupFields(String groupId)
  {
    QueryFactory f = new QueryFactory();
    MdWebFieldQuery q = new MdWebFieldQuery(f);
    WebGroupFieldQuery relQ = new WebGroupFieldQuery(f);

    relQ.WHERE(relQ.parentId().EQ(groupId));
    q.WHERE(q.groupFields(relQ));

    q.ORDER_BY_ASC(q.getFieldOrder());

    OIterator<? extends MdWebField> iterator = q.getIterator();

    try
    {
      List<? extends MdWebField> fields = iterator.getAll();
      return fields.toArray(new MdWebField[fields.size()]);
    }
    finally
    {
      iterator.close();
    }
  }

  /**
   * Returns all fields in order for the MdWebForm.
   * 
   * @param form
   * @return
   */
  public static MdWebField[] getFields(MdWebForm form)
  {
    QueryFactory f = new QueryFactory();
    MdWebFieldQuery q = new MdWebFieldQuery(f);
    MdWebFieldQuery q1 = new MdWebFieldQuery(f);
    WebGroupFieldQuery relQ = new WebGroupFieldQuery(f);

    // exclude fields that are directly beneath a group
    relQ.WHERE(relQ.childId().EQ(q1.getId()));
    q.AND(q.SUBSELECT_NOT_IN_groupFields(relQ));

    q.WHERE(q.getDefiningMdForm().EQ(form));
    q.ORDER_BY_ASC(q.getFieldOrder());

    OIterator<? extends MdWebField> iterator = q.getIterator();

    try
    {
      List<? extends MdWebField> fields = iterator.getAll();
      return fields.toArray(new MdWebField[fields.size()]);
    }
    finally
    {
      iterator.close();
    }
  }

  /**
   * Returns all non-group fields in order for the MdWebForm. All groups are collapsed and their
   * children are returned in relative order.
   * 
   * @param form
   * @return
   */
  public static MdWebField[] getAllFields(MdWebForm form)
  {
    // MdWebFieldQuery q = new MdWebFieldQuery(new QueryFactory());
    //
    // q.WHERE(q.getDefiningMdForm().EQ(form));
    // q.ORDER_BY_ASC(q.getFieldOrder());
    //
    // OIterator<? extends MdWebField> iterator = q.getIterator();
    //
    // try
    // {
    // List<? extends MdWebField> fields = iterator.getAll();
    // return fields.toArray(new MdWebField[fields.size()]);
    // }
    // finally
    // {
    // iterator.close();
    // }

    List<MdWebField> ordered = new LinkedList<MdWebField>();
    collectFields(ordered, MdFormUtil.getFields(form));

    return ordered.toArray(new MdWebField[] {});
  }

  /**
   * Recursive method to collect non-group fields in the given List.
   * 
   * @param ordered
   * @param fields
   */
  private static void collectFields(List<MdWebField> ordered, MdWebField[] fields)
  {
    for (MdWebField field : fields)
    {
      if (field instanceof MdWebGroup)
      {
        MdWebField[] groupFields = MdFormUtil.getGroupFields(field.getId());
        collectFields(ordered, groupFields);
      }
      else
      {
        ordered.add(field);
      }
    }
  }

  public static MdWebField[] getFieldsById(String id)
  {
    MdWebForm form = MdWebForm.get(id);
    return getFields(form);
  }

  @Transaction
  public static void reorderFields(String[] ids)
  {
    InstallProperties.validateMasterOperation();

    // FIXME: extract to a separate method
    String fieldId = ids[0];
    String previousId = ids[1];

    MdWebField field = MdWebField.get(fieldId);
    MdWebField prev = MdWebField.get(previousId);

    // remove the field from any prior groups and re-add it to the group of the
    // previous field--no point in being clever with diffing the groups.
    for (WebGroupField rel : field.getAllGroupFieldsRel())
    {
      rel.delete();
    }

    MdWebGroup group = null;
    for (MdWebGroup prevGroup : prev.getAllGroupFields())
    {
      field.addGroupFields(prevGroup).apply();
      group = prevGroup;
    }

    // set the order of the field to one higher than the previous
    // and shift the rest of the fields up.
    MdWebField[] fields; // fields in order
    if (group != null)
    {
      fields = getGroupFields(group.getId());
    }
    else
    {
      fields = getFields(field.getDefiningMdForm());
    }

    Integer prevOrder = null;
    for (MdWebField f : fields)
    {
      if (f.equals(field))
      {
        continue;
      }

      if (f.equals(prev))
      {
        prevOrder = f.getFieldOrder();
        field.appLock();
        field.setFieldOrder(++prevOrder);
        field.apply();
      }
      else if (prevOrder != null)
      {
        // increment the rest of the fields after the target field
        f.appLock();
        f.setFieldOrder(++prevOrder);
        f.apply();
      }
    }
  }

  public static TermViewQuery getTermsForMultiTermField(MdWebMultipleTerm mdField, String parentId)
  {
    QueryFactory f = new QueryFactory();

    String relType = getRelationshipType(mdField);
    MultipleTermValues builder = new MultipleTermValues(f, relType, parentId);

    TermViewQuery q = new TermViewQuery(f, builder);

    return q;
  }

  public static MdRelationship getMdRelationship(MdWebField field)
  {
    String relationshipType = MdFormUtil.getRelationshipType(field);

    MdRelationshipDAOIF mdRelationship = MdRelationshipDAO.getMdRelationshipDAO(relationshipType);

    return MdRelationship.get(mdRelationship.getId());
  }

  public static MdRelationshipDAOIF getMdRelationship(MdWebFieldDAOIF field)
  {
    String relationshipType = MdFormUtil.getRelationshipType(field);

    return MdRelationshipDAO.getMdRelationshipDAO(relationshipType);
  }

  private static String getRelationshipType(MdWebField field)
  {
    return MDSSInfo.GENERATED_FORM_TREE_PACKAGE + "." + DDMSFieldBuilders.getTermRelationshipTypeName((MdWebAttribute) field);
  }

  private static String getRelationshipType(MdWebFieldDAOIF field)
  {
    return MDSSInfo.GENERATED_FORM_TREE_PACKAGE + "." + DDMSFieldBuilders.getTermRelationshipTypeName((MdWebAttributeDAOIF) field);
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
  public static com.runwaysdk.system.metadata.MdWebForm clone(MdWebForm mdForm, MdClass mdClass, MdWebField[] fields, MdAttributeConcrete[] mdAttrs)
  {
    InstallProperties.validateMasterOperation();
    
    String typeName = GeoHierarchy.getSystemName(mdForm.getFormName(), "Business", true);
    if (typeName == null || typeName.trim().length() == 0)
    {
      throw new FormNameNotBlankException();
    }
    mdClass.setPackageName(MDSSInfo.GENERATED_FORM_BUSINESS_PACKAGE);
    mdClass.setTypeName(typeName);
    mdClass.apply();
    mdForm.setPackageName(MDSSInfo.GENERATED_FORM_PACKAGE);
    mdForm.setTypeName(typeName);
    mdForm.setFormMdClass(mdClass);
    mdForm.apply();
    
    for (MdAttributeConcrete attr : mdAttrs)
    {
      attr.setDefiningMdClass(mdClass);
      attr.apply();
    }
    
    for (MdWebField field : fields)
    {
      field.setDefiningMdForm(mdForm);
      if (field instanceof MdWebAttribute)
      {
        boolean found = false;
        for (int i = 0; i < mdAttrs.length; ++i)
        {
          if (mdAttrs[i].getAttributeName().equals(field.getFieldName()))
          {
            ((MdWebAttribute)field).setDefiningMdAttribute(mdAttrs[i]);
            found = true;
          }
        }
        if (!found) { throw new DataNotFoundException("There is no corresponding MdAttribute for the MdWebField [" + field.getKeyName() + "].", (MetadataDAOIF) field); }
      }
      field.apply();
    }
    
    new FormSystemURLBuilder(mdForm).generate();
    
    return mdForm;
  }

  @Transaction
  @Authenticate
  public static MdWebForm apply(MdWebForm mdForm)
  {
    InstallProperties.validateMasterOperation();

    MdBusiness mdBusiness = null;
    boolean first = mdForm.isNew() && !mdForm.isAppliedToDB();

    if (first)
    {
      String typeName = GeoHierarchy.getSystemName(mdForm.getFormName(), "Business", true);
      String label = mdForm.getDisplayLabel().getValue();
      if (label.length() == 0)
      {
        label = mdForm.getFormName();
        mdForm.getDisplayLabel().setValue(label);
      }

      String description = mdForm.getDescription().getValue();

      if (typeName == null || typeName.trim().length() == 0)
      {
        throw new FormNameNotBlankException();
      }

      mdBusiness = new MdBusiness();
      mdBusiness.setPackageName(MDSSInfo.GENERATED_FORM_BUSINESS_PACKAGE);
      mdBusiness.setTypeName(typeName);
      mdBusiness.getDisplayLabel().setValue(label);
      mdBusiness.getDescription().setValue(description);
      mdBusiness.setIsAbstract(false);
      mdBusiness.setExtendable(true);
      mdBusiness.setPublish(true);
      mdBusiness.apply();

      // Create the disease attribute
      MdAttributeReference disease = new MdAttributeReference();
      disease.setDefiningMdClass(mdBusiness);
      disease.setAttributeName(MdFormUtil.DISEASE);
      disease.getDisplayLabel().setValue("Disease");
      disease.setRequired(true);
      disease.setMdBusiness(MdBusiness.getMdBusiness(Disease.CLASS));
      disease.apply();

      mdForm.setPackageName(MDSSInfo.GENERATED_FORM_PACKAGE);
      mdForm.setTypeName(typeName);
      mdForm.setFormMdClass(mdBusiness);
    }

    mdForm.apply();

    if (first)
    {
      new FormSystemURLBuilder(mdForm).generate();

      MdAttributeCharacter mdAttributeCharacter = new MdAttributeCharacter();
      mdAttributeCharacter.setAttributeName(OID);
      mdAttributeCharacter.setDatabaseSize(16);
      mdAttributeCharacter.addIndexType(MdAttributeIndices.UNIQUE_INDEX);
      mdAttributeCharacter.setDefiningMdClass(mdBusiness);
      mdAttributeCharacter.setRemove(false);
      mdAttributeCharacter.setRequired(true);
      mdAttributeCharacter.getDisplayLabel().setValue(DEFAULT_OID_LABEL);
      mdAttributeCharacter.apply();

      MdWebCharacter mdWebCharacter = new MdWebCharacter();
      mdWebCharacter.setFieldName(OID);
      mdWebCharacter.setFieldOrder(0);
      mdWebCharacter.setDisplayLength(16);
      mdWebCharacter.setMaxLength(16);
      mdWebCharacter.setUnique(true);
      mdWebCharacter.setRemove(false);
      mdWebCharacter.setRequired(true);
      mdWebCharacter.getDisplayLabel().setValue(DEFAULT_OID_LABEL);
      mdWebCharacter.setDefiningMdAttribute(mdAttributeCharacter);
      mdWebCharacter.setDefiningMdForm(mdForm);
      mdWebCharacter.apply();
    }

    return mdForm;
  }

  @Transaction
  @Authenticate
  public static void delete(MdWebForm mdForm)
  {
    InstallProperties.validateMasterOperation();

    MdClass mdClass = mdForm.getFormMdClass();

    new FormSystemURLBuilder(mdForm).delete();

    /*
     * Delete all of the conditions which have been specified on fields of the
     * form. Conditions have required attribute which reference fields of the
     * form. If the conditions are not deleted first they will prevent the form
     * from being deleted.
     */
    OIterator<? extends MdWebField> it = mdForm.getAllMdFields();

    try
    {
      List<? extends MdWebField> fields = it.getAll();

      for (MdWebField mdField : fields)
      {
        FieldCondition condition = mdField.getFieldCondition();

        if (condition != null)
        {
          condition.delete();
        }
      }

      /*
       * We need to delete all of the GeoField objects which have been
       * associated with a geo field.
       */
      for (MdWebField mdField : fields)
      {
        if (mdField instanceof MdWebGeo)
        {
          MdFormUtil.deleteField(mdForm, mdField);
        }
      }
    }
    finally
    {
      it.close();
    }

    mdForm.delete();

    mdClass.delete();
  }

  public static void confirmDeleteForm(String mdFormId)
  {
    InstallProperties.validateMasterOperation();

    MdWebForm mdForm = MdWebForm.get(mdFormId);

    String type = mdForm.getFormMdClass().definesType();
    QueryFactory f = new QueryFactory();
    BusinessQuery q = f.businessQuery(type);
    if (q.getCount() > 0)
    {
      MdFormHasInstancesException ex = new MdFormHasInstancesException();
      ex.setMdFormDisplayLabel(mdForm.getDisplayLabel().getValue());
      throw ex;
    }
    else
    {
      ConfirmDeleteMdFormException ex = new ConfirmDeleteMdFormException();
      ex.setMdFormDisplayLabel(mdForm.getDisplayLabel().getValue());
      throw ex;
    }
  }

  public static void confirmDeleteMdField(String mdFormId, String mdFieldId)
  {
    InstallProperties.validateMasterOperation();

    MdWebForm mdForm = MdWebForm.get(mdFormId);
    MdWebField mdField = MdWebField.get(mdFieldId);
    ConfirmDeleteMdFieldException ex = new ConfirmDeleteMdFieldException();
    ex.setMdFormDisplayLabel(mdForm.getDisplayLabel().getValue());
    ex.setMdFieldDisplayLabel(mdField.getDisplayLabel().getValue());
    throw ex;
  }

  public static void confirmDeleteCompositeField(String mdCompositeFieldId, String mdFieldId)
  {
    InstallProperties.validateMasterOperation();

    MdWebSingleTermGrid mdWebGrid = MdWebSingleTermGrid.get(mdCompositeFieldId);
    MdWebField mdField = MdWebField.get(mdFieldId);

    ConfirmDeleteMdFieldException ex = new ConfirmDeleteMdFieldException();
    ex.setMdFormDisplayLabel(mdWebGrid.getDisplayLabel().getValue());
    ex.setMdFieldDisplayLabel(mdField.getDisplayLabel().getValue());
    throw ex;
  }

  @Transaction
  @Authenticate
  public static void deleteField(MdWebForm mdForm, MdWebField mdField)
  {
    InstallProperties.validateMasterOperation();

    try
    {
      // if this is a group then remove all of its children and append them to
      // the end of the form.
      if (mdField instanceof MdWebGroup)
      {
        Integer order = getHighestOrder(mdForm);

        for (MdWebField f : getGroupFields(mdField.getId()))
        {
          f.appLock();
          f.setFieldOrder(++order);
          f.apply();
        }
      }

      DDMSFieldBuilders.delete(mdField);
    }
    catch (MetadataCannotBeDeletedException e)
    {
      String msg = "Field cannont be deleted";
      MetadataDAO metadata = (MetadataDAO) MdWebFieldDAO.get(mdField.getId()).getBusinessDAO();

      throw new MetadataCannotBeDeletedException(msg, metadata);
    }
  }

  @Transaction
  @Authenticate
  public static void deleteCompositeField(MdWebPrimitive mdField)
  {
    InstallProperties.validateMasterOperation();

    try
    {
      DDMSFieldBuilders.delete(mdField);
    }
    catch (MetadataCannotBeDeletedException e)
    {
      String msg = "Field cannont be deleted";
      MetadataDAO metadata = (MetadataDAO) MdWebFieldDAO.get(mdField.getId()).getBusinessDAO();

      throw new MetadataCannotBeDeletedException(msg, metadata);
    }
  }

  public static InputStream excelExport(String type)
  {
    MdFormDAOIF mdForm = (MdFormDAOIF) MdFormDAO.getMdTypeDAO(type);

    ExcelExporter exporter = new FormExcelExporter(new FormImportFilter(), new FormColumnFactory());

    List<ExcelExportListener> listeners = new LinkedList<ExcelExportListener>();

    List<DynamicGeoColumnListener> geoListeners = MdFormUtil.getGeoListeners(mdForm);

    for (DynamicGeoColumnListener listener : geoListeners)
    {
      listeners.add(listener);
    }

    List<MultiTermListener> multiTermListeners = MdFormUtil.getMultiTermListeners(mdForm);

    for (MultiTermListener listener : multiTermListeners)
    {
      listeners.add(listener);
    }

    /*
     * IMPORTANT: Before adding a template all of the listeners must be added
     */
    exporter.addTemplate(type, listeners);

    MdFormUtil.addGridSheets(mdForm, exporter);

    return new ByteArrayInputStream(exporter.write());
  }

  public static InputStream excelImport(InputStream stream, String type)
  {
    // Start caching Broswer Roots for this Thread.
    TermRootCache.start();
    EpiCache.start();

    try
    {
      ContextBuilderFacade builder = new ContextBuilderFacade();

      ExcelImporter importer = new ExcelImporter(stream, builder);

      return new ByteArrayInputStream(importer.read());
    }
    finally
    {
      TermRootCache.stop();
      EpiCache.stop();
    }
  }

  public static List<MultiTermListener> getMultiTermListeners(MdFormDAOIF mdForm)
  {
    List<MultiTermListener> listeners = new LinkedList<MultiTermListener>();

    List<? extends MdFieldDAOIF> mdFields = mdForm.getOrderedMdFields();

    for (MdFieldDAOIF mdField : mdFields)
    {
      if (mdField instanceof MdWebMultipleTermDAOIF)
      {
        MdRelationship mdRelationship = MdFormUtil.getMdRelationship(MdWebField.get(mdField.getId()));

        listeners.add(new MultiTermListener((MdWebMultipleTermDAOIF) mdField, mdRelationship.getParentMethod()));
      }
    }

    return listeners;
  }
  
  public static List<ImportApplyListener> getImportApplyListeners(MdFormDAOIF mdForm)
  {
    List<ImportApplyListener> listeners = new LinkedList<ImportApplyListener>();

    List<? extends MdFieldDAOIF> mdFields = mdForm.getOrderedMdFields();

    for (MdFieldDAOIF mdField : mdFields)
    {
      if (mdField.getFieldName().equals(HumanBloodIndex.COLLECTIONID)) // quick hack for ticket 3188
      {
        listeners.add(new CollectionIdValidationListener());
      }
    }

    return listeners;
  }

  public static void addGridContexts(MdWebFormDAOIF mdForm, ContextBuilderFacade builder)
  {
    List<? extends MdFieldDAOIF> mdFields = mdForm.getOrderedMdFields();

    for (MdFieldDAOIF mdField : mdFields)
    {
      if (mdField instanceof MdWebSingleTermGridDAOIF)
      {
        MdRelationship mdRelationship = MdFormUtil.getMdRelationship(MdWebField.get(mdField.getId()));
        MdRelationshipDAOIF mdRelationshipDAO = (MdRelationshipDAOIF) BusinessFacade.getEntityDAO(mdRelationship);

        GridExcelAdapter context = new GridExcelAdapter(mdForm, (MdWebSingleTermGridDAOIF) mdField, mdRelationshipDAO);

        builder.add(mdRelationship.definesType(), context);
      }
    }
  }

  public static void addGridSheets(MdFormDAOIF mdForm, ExcelExporter exporter)
  {
    List<? extends MdFieldDAOIF> mdFields = mdForm.getOrderedMdFields();

    for (MdFieldDAOIF mdField : mdFields)
    {
      if (mdField instanceof MdWebSingleTermGridDAOIF)
      {
        MdRelationship mdRelationship = MdFormUtil.getMdRelationship(MdWebField.get(mdField.getId()));
        MdRelationshipDAOIF mdRelationshipDAO = (MdRelationshipDAOIF) BusinessFacade.getEntityDAO(mdRelationship);

        GridExcelAdapter sheet = new GridExcelAdapter(mdForm, (MdWebSingleTermGridDAOIF) mdField, mdRelationshipDAO);
        sheet.addTemplate(mdRelationship.definesType());
        exporter.addSheet(sheet);
      }
    }
  }

  public static void addGridContexts(MdFormDAOIF mdForm, ExcelExporter exporter)
  {
    List<? extends MdFieldDAOIF> mdFields = mdForm.getOrderedMdFields();

    for (MdFieldDAOIF mdField : mdFields)
    {
      if (mdField instanceof MdWebSingleTermGridDAOIF)
      {
        MdRelationship mdRelationship = MdFormUtil.getMdRelationship(MdWebField.get(mdField.getId()));
        MdRelationshipDAOIF mdRelationshipDAO = (MdRelationshipDAOIF) BusinessFacade.getEntityDAO(mdRelationship);

        GridExcelAdapter sheet = new GridExcelAdapter(mdForm, (MdWebSingleTermGridDAOIF) mdField, mdRelationshipDAO);
        sheet.addTemplate(mdRelationship.definesType());
        exporter.addSheet(sheet);
      }
    }
  }

  public static List<DynamicGeoColumnListener> getGeoListeners(MdFormDAOIF mdForm)
  {
    List<DynamicGeoColumnListener> listeners = new LinkedList<DynamicGeoColumnListener>();

    MdClassDAOIF mdClass = mdForm.getFormMdClass();
    List<? extends MdFieldDAOIF> mdFields = mdForm.getOrderedMdFields();

    for (MdFieldDAOIF mdField : mdFields)
    {
      // Setup all of the Geo Fields
      if (mdField instanceof MdWebGeoDAOIF)
      {
        GeoField geoField = GeoField.getGeoField(mdClass.definesType(), mdField.getFieldName());

        if (geoField != null)
        {
          HierarchyBuilder builder = new HierarchyBuilder();

          List<GeoHierarchy> universals = geoField.getUniversals();

          for (GeoHierarchy universal : universals)
          {
            builder.add(universal);
          }

          listeners.add(new DynamicGeoColumnListener(mdClass.definesType(), mdField.getFieldName(), builder));
        }
      }
    }

    return listeners;
  }

  private static class MultipleTermValues extends ViewQueryBuilder implements Reloadable
  {
    private String                parentId;

    private TermQuery             termQuery;

    private InactivePropertyQuery inactivePropQuery;

    private RelationshipQuery     mdRel;

    protected MultipleTermValues(QueryFactory queryFactory, String relType, String parentId)
    {
      super(queryFactory);

      this.parentId = parentId;
      this.mdRel = queryFactory.relationshipQuery(relType);
      this.termQuery = new TermQuery(queryFactory);
      this.inactivePropQuery = new InactivePropertyQuery(queryFactory);

    }

    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      query.map(TermView.TERMID, termQuery.getId());
      query.map(TermView.TERMNAME, termQuery.getTermDisplayLabel().localize());
      query.map(TermView.TERMONTOLOGYID, termQuery.getTermId());
      query.map(TermView.INACTIVE, this.inactivePropQuery.getInactive());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      Disease disease = Disease.getCurrent();
      this.inactivePropQuery.AND(this.inactivePropQuery.getDisease().EQ(disease));

      query.AND(termQuery.inactiveProperties(this.inactivePropQuery));

      this.mdRel.WHERE(this.mdRel.parentId().EQ(this.parentId));
      query.AND(termQuery.getId().EQ(this.mdRel.childId()));
    }

  }

  public static String getFieldsForComposite(String compositeFieldId)
  {
    JSONArray array = new JSONArray();
    try
    {
      MdWebPrimitive[] fields = MdFormUtil.getCompositeFields(compositeFieldId);

      for (MdWebPrimitive field : fields)
      {
        JSONObject fieldJSON = new JSONObject();
        array.put(fieldJSON);

        fieldJSON.put("label", field.toString());
        fieldJSON.put("id", field.getId());
        fieldJSON.put("nodeType", "fieldNode");
      }

      return array.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException("Could not get the JSON tree for an MdForm.", e);
    }
  }

  public static MdWebPrimitive[] getCompositeFields(String compositeFieldId)
  {
    QueryFactory factory = new QueryFactory();

    WebGridFieldQuery query = new WebGridFieldQuery(factory);
    query.WHERE(query.parentId().EQ(compositeFieldId));

    OIterator<? extends WebGridField> it = query.getIterator();

    List<MdWebPrimitive> collection = new LinkedList<MdWebPrimitive>();

    try
    {
      while (it.hasNext())
      {
        collection.add(it.next().getChild());
      }
    }
    finally
    {
      it.close();
    }

    Collections.sort(collection, new FieldComparator());

    return collection.toArray(new MdWebPrimitive[collection.size()]);
  }

  public static boolean isFormBusinessPackage(String type)
  {
    return type.startsWith(MDSSInfo.GENERATED_FORM_BUSINESS_PACKAGE);
  }

  public static boolean isFormRelationshipPackage(String type)
  {
    return type.startsWith(MDSSInfo.GENERATED_FORM_TREE_PACKAGE);
  }

  public static MdWebForm getMdFormFromBusinessType(String type)
  {
    int index = type.lastIndexOf(".");
    String packageName = type.substring(0, index);
    String typeName = type.substring(index + 1, type.length());

    MdWebFormQuery query = new MdWebFormQuery(new QueryFactory());
    query.WHERE(query.getFormMdClass().getPackageName().EQ(packageName));
    query.AND(query.getFormMdClass().getTypeName().EQ(typeName));

    OIterator<? extends MdWebForm> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }
  
  /**
   * MdMethod
   * Returns the id of the MdForm specified by formKey.
   * 
   * @param formKey The key of the MdForm.
   * @return The id of the specified MdForm.
   */
  public static String getFormByKey(String formKey)
  {
    return MdForm.getByKey(formKey).getId();
  }

  /**
   * Returns a query object of the given type, populated from the values specified in the criteria
   * object. It is assumed that criteria.getType() is equal to type. If criteria is null then all
   * objects are returned.
   * 
   * @param criteria
   * @param type
   * @param sortAttribute
   * @param isAscending
   * @param pageSize
   * @param pageNumber
   * @return
   */
  public static GeneratedComponentQuery searchObject(Business criteria, String type, String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    GeneratedBusinessQuery query = getAndPopulateQuery(criteria, type);

    String sort = sortAttribute != null ? sortAttribute : OID;

    if (sort != null && sort.length() > 0)
    {
      Attribute attribute = query.get(sort);

      if (attribute instanceof AttributePrimitive)
      {
        SortOrder order = isAscending != null && isAscending ? SortOrder.ASC : SortOrder.DESC;

        query.ORDER_BY((AttributePrimitive) attribute, order);
      }
    }

    if (pageSize != null && pageNumber != null)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;
  }

  @Transaction
  public static void deleteAll(Business criteria, String type)
  {
    if (!MDSSUser.canDeleteAll())
    {
      throw new DeleteAllAccessException();
    }

    GeneratedBusinessQuery query = getAndPopulateQuery(criteria, type);

    OIterator<? extends Business> iterator = null;

    try
    {
      iterator = getIterator(query);

      while (iterator.hasNext())
      {
        Business business = iterator.next();
        business.delete();
      }
    }
    finally
    {
      if (iterator != null)
      {
        iterator.close();
      }
    }
  }

  private static GeneratedBusinessQuery getAndPopulateQuery(Business criteria, String type)
  {
    QueryFactory factory = new QueryFactory();
    GeneratedBusinessQuery query = getQueryObject(type, factory);

    if (criteria != null)
    {
      List<? extends MdAttributeConcreteDAOIF> mdAttributes = criteria.getMdAttributeDAOs();

      for (MdAttributeConcreteDAOIF mdAttribute : mdAttributes)
      {
        if (!mdAttribute.isSystem())
        {
          String attributeName = mdAttribute.definesAttribute();

          /*
           * Skip the key and domain attributes, the are essentially system attributes.
           */
          if (! ( attributeName.equals(ComponentInfo.KEY) || attributeName.equals(BusinessInfo.DOMAIN) ))
          {
            String value = criteria.getValue(attributeName);

            if (value != null && value.length() > 0)
            {
              if (mdAttribute instanceof MdAttributeReferenceDAOIF)
              {
                // Possible reference attributes: disease, term, geo-entity
                MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttribute;
                MdBusinessDAOIF referenceMdBusiness = mdAttributeReference.getReferenceMdBusinessDAO();
                String referenceType = referenceMdBusiness.definesType();
                AttributeReference attribute = (AttributeReference) query.get(attributeName);

                Class<?> referenceClass = LoaderDecorator.load(referenceType);

                /*
                 * Handle geo entity references
                 */
                if (GeoEntity.class.isAssignableFrom(referenceClass))
                {
                  dss.vector.solutions.geo.AllPathsQuery apQuery = new dss.vector.solutions.geo.AllPathsQuery(factory);
                  apQuery.WHERE(apQuery.getParentGeoEntity().EQ(value));

                  query.WHERE(attribute.EQ(apQuery.getChildGeoEntity()));
                }
                /*
                 * Handle term references
                 */
                else if (Term.class.isAssignableFrom(referenceClass))
                {
                  dss.vector.solutions.ontology.AllPathsQuery apQuery = new dss.vector.solutions.ontology.AllPathsQuery(factory);
                  apQuery.WHERE(apQuery.getParentTerm().EQ(value));

                  query.WHERE(attribute.EQ(apQuery.getChildTerm()));
                }
                /*
                 * Handle disease, survey, household, bednet, and person references
                 */
                else if (Disease.class.isAssignableFrom(referenceClass) || FormSurvey.class.isAssignableFrom(referenceClass) || FormHousehold.class.isAssignableFrom(referenceClass) || FormPerson.class.isAssignableFrom(referenceClass) || FormBedNet.class.isAssignableFrom(referenceClass))
                {
                  query.WHERE(attribute.EQ(value));
                }
                else
                {
                  throw new ProgrammingErrorException("Form search query builder does not know how to handle the reference type [" + type + "]");
                }
              }
              else
              {
                Attribute attribute = query.get(attributeName);
                query.WHERE(attribute.EQ(value));
              }
            }
          }
        }
      }
    }
    return query;
  }

  @SuppressWarnings("unchecked")
  private static GeneratedBusinessQuery getQueryObject(String type, QueryFactory factory)
  {
    try
    {
      Class<? extends GeneratedBusinessQuery> clazz = (Class<? extends GeneratedBusinessQuery>) LoaderDecorator.load(type + "Query");
      Constructor<? extends GeneratedBusinessQuery> constructor = clazz.getConstructor(factory.getClass());

      GeneratedBusinessQuery query = constructor.newInstance(factory);
      return query;
    }
    catch (Exception e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @SuppressWarnings("unchecked")
  private static OIterator<? extends Business> getIterator(GeneratedBusinessQuery query)
  {
    Class<? extends GeneratedBusinessQuery> clazz = query.getClass();

    try
    {
      Method method = clazz.getMethod(EntityQueryAPIGenerator.ITERATOR_METHOD);
      OIterator<? extends Business> iterator = (OIterator<? extends Business>) method.invoke(query);

      return iterator;
    }
    catch (IllegalArgumentException e)
    {
      throw new ProgrammingErrorException(e);
    }
    catch (SecurityException e)
    {
      throw new ProgrammingErrorException(e);
    }
    catch (IllegalAccessException e)
    {
      throw new ProgrammingErrorException(e);
    }
    catch (InvocationTargetException e)
    {
      throw new ProgrammingErrorException(e);
    }
    catch (NoSuchMethodException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public static void isAvailable(String type, String oid)
  {
    if (type != null && type.length() > 0 && oid != null && oid.length() > 0)
    {
      QueryFactory factory = new QueryFactory();

      BusinessQuery query = factory.businessQuery(type);
      query.WHERE(query.aCharacter(OID).EQ(oid));

      long count = query.getCount();

      if (count > 0)
      {
        DuplicateFormInstanceException e = new DuplicateFormInstanceException();
        e.setFormId(oid);

        throw e;
      }
    }
  }

  @Authenticate
  public static InputStream exportDefinition(String mdFormId)
  {
    MdWebFormDAOIF mdForm = MdWebFormDAO.get(mdFormId);
    MdClassDAOIF mdClass = mdForm.getFormMdClass();

    ExportMetadata metadata = new ExportMetadata();
    metadata.addCreateOrUpdate(mdClass);

    List<? extends MdFieldDAOIF> mdFields = mdForm.getOrderedMdFields();

    for (MdFieldDAOIF mdField : mdFields)
    {
      if (mdField instanceof MdWebGeoDAOIF)
      {
        GeoField geoField = GeoField.getGeoFieldForMdWebGeo(mdField.getId());
        metadata.addCreateOrUpdate(geoField);

        OIterator<? extends ExtraFieldUniversal> iterator = geoField.getAllGeoHierarchiesRel();

        try
        {
          while (iterator.hasNext())
          {
            ExtraFieldUniversal hierarchy = iterator.next();
            metadata.addCreateOrUpdate(hierarchy);
          }
        }
        finally
        {
          iterator.close();
        }
      }
      else if (mdField instanceof MdWebSingleTermDAOIF)
      {
        MdAttributeDAOIF mdAttribute = ( (MdWebSingleTermDAOIF) mdField ).getDefiningMdAttribute();

        exportBrowserRoots(metadata, mdAttribute);
      }
      if (mdField instanceof MdWebSingleTermGridDAOIF)
      {
        MdAttributeDAOIF mdAttribute = ( (MdWebSingleTermGridDAOIF) mdField ).getDefiningMdAttribute();
        MdRelationship mdRelationship = MdFormUtil.getMdRelationship(MdWebField.get(mdField.getId()));
        MdRelationshipDAOIF mdRelationshipDAO = MdRelationshipDAO.get(mdRelationship.getId());

        metadata.addCreateOrUpdate(mdRelationshipDAO);

        exportBrowserRoots(metadata, mdAttribute);
      }
      else if (mdField instanceof MdWebMultipleTermDAOIF)
      {
        MdAttributeDAOIF mdAttribute = ( (MdWebMultipleTermDAOIF) mdField ).getDefiningMdAttribute();
        MdRelationship mdRelationship = MdFormUtil.getMdRelationship(MdWebField.get(mdField.getId()));
        MdRelationshipDAOIF mdRelationshipDAO = MdRelationshipDAO.get(mdRelationship.getId());

        metadata.addCreateOrUpdate(mdRelationshipDAO);
        exportBrowserRoots(metadata, mdAttribute);
      }
    }
    metadata.addCreateOrUpdate(mdForm);

    StringMarkupWriter writer = new StringMarkupWriter();

    SAXExporter exporter = new SAXExporter(writer, XSD_LOCATION, metadata);
    exporter.export();

    String xml = writer.getOutput();

    return new StringInputStream(xml);
  }

  public static void exportBrowserRoots(ExportMetadata metadata, MdAttributeDAOIF mdAttribute)
  {
    BrowserField field = BrowserField.getBrowserField(mdAttribute);

    metadata.addCreateOrUpdate(field);

    // OIterator<? extends FieldRoot> it = field.getAllrootRel();
    //
    // try
    // {
    // List<? extends FieldRoot> roots = it.getAll();
    //
    // for (FieldRoot root : roots)
    // {
    // BrowserRoot child = root.getChild();
    //
    // metadata.addCreateOrUpdate(child);
    // metadata.addCreateOrUpdate(root);
    // }
    // }
    // finally
    // {
    // it.close();
    // }
  }

  @Transaction
  @Authenticate
  public static void importDefinition(InputStream definition)
  {
    try
    {
      String xml = IOUtils.toString(definition, "UTF-8");

      StringStreamSource source = new StringStreamSource(xml);

      SAXImporter importer = new SAXImporter(source, XSD_LOCATION);
      importer.begin();

      // The form permissions cannot be exported because they are specific to the diseases specified
      // on the destination system. The diesease of the destination system will likely be different.
      // Thus we need to define/re-define the permissions for any imported form as part of the
      // import process.
      ImportManager manager = importer.getManager();
      Set<String> types = manager.getImportedTypes();

      for (String type : types)
      {
        if (type.startsWith(MDSSInfo.GENERATED_FORM_PACKAGE) && !type.startsWith(MDSSInfo.GENERATED_FORM_BUSINESS_PACKAGE) && !type.startsWith(MDSSInfo.GENERATED_FORM_TREE_PACKAGE))
        {
          MdWebFormDAOIF mdForm = (MdWebFormDAOIF) MdWebFormDAO.get(MdWebFormInfo.CLASS, type);

          new FormSystemURLBuilder(mdForm).generate();
        }
      }

    }
    catch (XMLParseException e)
    {
      if (e.getCause() != null && ( e.getCause() instanceof RuntimeException ))
      {
        throw ( (RuntimeException) e.getCause() );
      }

      throw e;
    }
    catch (SAXException e)
    {
      throw new XMLParseException(e);
    }
    catch (IOException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }
}
