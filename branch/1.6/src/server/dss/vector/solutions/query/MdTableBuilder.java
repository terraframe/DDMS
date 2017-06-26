package dss.vector.solutions.query;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.constants.EntityInfo;
import com.runwaysdk.constants.MdAttributeBooleanInfo;
import com.runwaysdk.constants.MdAttributeCharacterInfo;
import com.runwaysdk.constants.MdAttributeConcreteInfo;
import com.runwaysdk.constants.MdAttributeDateInfo;
import com.runwaysdk.constants.MdAttributeDateTimeInfo;
import com.runwaysdk.constants.MdAttributeDecimalInfo;
import com.runwaysdk.constants.MdAttributeDoubleInfo;
import com.runwaysdk.constants.MdAttributeFloatInfo;
import com.runwaysdk.constants.MdAttributeIntegerInfo;
import com.runwaysdk.constants.MdAttributeLocalInfo;
import com.runwaysdk.constants.MdAttributeLongInfo;
import com.runwaysdk.constants.MdAttributeReferenceInfo;
import com.runwaysdk.constants.MdAttributeTextInfo;
import com.runwaysdk.constants.MdAttributeTimeInfo;
import com.runwaysdk.constants.MdTableInfo;
import com.runwaysdk.constants.MetadataInfo;
import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;
import com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDateDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDecimalDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF;
import com.runwaysdk.dataaccess.MdAttributeFloatDAOIF;
import com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF;
import com.runwaysdk.dataaccess.MdAttributeLongDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdAttributeTextDAOIF;
import com.runwaysdk.dataaccess.MdAttributeTimeDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdTableDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.metadata.MdAttributeBooleanDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeCharacterDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeDateDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeDateTimeDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeDecimalDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeDoubleDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeFloatDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeIntegerDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeLongDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeReferenceDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeTextDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeTimeDAO;
import com.runwaysdk.dataaccess.metadata.MdTableDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableAggregate;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeReference;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdTable;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.kaleidoscope.MappableAttribute;
import dss.vector.solutions.kaleidoscope.MappableClass;
import dss.vector.solutions.kaleidoscope.geo.GeoNode;
import dss.vector.solutions.kaleidoscope.geo.GeoNodeEntity;
import dss.vector.solutions.ontology.BrowserField;
import dss.vector.solutions.ontology.BrowserFieldQuery;
import dss.vector.solutions.ontology.BrowserRoot;
import dss.vector.solutions.ontology.BrowserRootQuery;
import dss.vector.solutions.ontology.Term;

public class MdTableBuilder implements Reloadable
{
  private static class ClassDefinition implements Reloadable
  {
    private GeoHierarchy         lowest;

    private List<GeoNode>        nodes;

    private List<MdAttributeDAO> attributes;

    public ClassDefinition(GeoHierarchy lowest)
    {
      this.lowest = lowest;
      this.nodes = new LinkedList<GeoNode>();
      this.attributes = new LinkedList<MdAttributeDAO>();
    }

    public void addAttribute(MdAttributeDAO mdAttribute)
    {
      this.attributes.add(mdAttribute);
    }

    public void addNode(GeoNode node)
    {
      this.nodes.add(node);
    }

    public List<MdAttributeDAO> getAttributes()
    {
      return attributes;
    }

    public void setLowest(GeoHierarchy lowest)
    {
      this.lowest = lowest;
    }

    public GeoHierarchy getLowest()
    {
      return lowest;
    }

    public List<GeoNode> getNodes()
    {
      return nodes;
    }
  }

  public MdTable build(String label, String viewName, ValueQuery query) throws JSONException
  {
    // Create the MdTable
    MdTableDAO mdTableDAO = MdTableDAO.newInstance();
    mdTableDAO.setValue(MdTableInfo.NAME, viewName);
    mdTableDAO.setValue(MdTableInfo.PACKAGE, MDSSInfo.GENERATED_TABLE_PACKAGE);
    mdTableDAO.setStructValue(MdTableInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, label);
    mdTableDAO.setValue(MdTableInfo.TABLE_NAME, viewName);
    mdTableDAO.apply();

    return build(mdTableDAO, query);
  }

  private MdTable build(MdTableDAO mdTableDAO, ValueQuery query)
  {
    Disease disease = Disease.getCurrent();

    List<Selectable> selectables = query.getSelectableRefs();
    ClassDefinition definition = this.defineMdAttributes(mdTableDAO, disease, null, selectables);

    MdTable mdTable = (MdTable) BusinessFacade.get(mdTableDAO);

    /*
     * Create a new refresh job
     */
    RefreshViewJob job = new RefreshViewJob();
    job.setMaterializedTable(mdTable);
    job.apply();

    /*
     * Define the dataset
     */
    MappableClass mClass = new MappableClass();
    mClass.setWrappedMdClass(mdTable);
    mClass.setDisease(disease);
    mClass.setRemovable(false);
    mClass.apply();

    List<MdAttributeDAO> attributes = definition.getAttributes();
    GeoHierarchy lowest = definition.getLowest();
    List<GeoNode> nodes = definition.getNodes();

    for (MdAttributeDAO mdAttributeDAO : attributes)
    {
      MdAttribute mdAttribute = MdAttribute.get(mdAttributeDAO.getId());

      MappableAttribute mAttribute = new MappableAttribute();
      mAttribute.setWrappedMdAttribute(mdAttribute);
      mAttribute.setAggregatable(true);
      mAttribute.apply();
    }

    if (lowest != null)
    {
      mClass.addUniversal(lowest).apply();
    }

    for (GeoNode node : nodes)
    {
      mClass.addGeoNode(node).apply();
    }

    return mdTable;
  }

  @SuppressWarnings("unchecked")
  private ClassDefinition defineMdAttributes(MdTableDAO mdTableDAO, Disease disease, GeoHierarchy lowest, List<Selectable> selectables)
  {
    ClassDefinition definition = new ClassDefinition(lowest);

    for (Selectable selectable : selectables)
    {
      Map<String, Object> data = (Map<String, Object>) selectable.getData();
      MdAttributeConcreteDAOIF mdAttributeIF = (MdAttributeConcreteDAOIF) data.get(MetadataInfo.CLASS);

      String attributeName = mdAttributeIF.definesAttribute();
      attributeName = attributeName.substring(Math.max(0, attributeName.length() - 64));

      if (mdAttributeIF instanceof MdAttributeReferenceDAOIF)
      {
        MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttributeIF;
        MdBusinessDAOIF mdBusiness = mdAttributeReference.getReferenceMdBusinessDAO();

        if (mdBusiness.definesType().equals(Term.CLASS))
        {
          MdAttributeReferenceDAO mdAttribute = MdAttributeReferenceDAO.newInstance();
          mdAttribute.setValue(MdAttributeConcreteInfo.NAME, attributeName);
          mdAttribute.setStructValue(MdAttributeConcreteInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeReference.getDisplayLabel(Session.getCurrentLocale()));
          mdAttribute.setValue(MdAttributeConcreteInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
          mdAttribute.setValue(MdAttributeReferenceInfo.REF_MD_ENTITY, mdBusiness.getId());
          mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
          mdAttribute.apply();

          definition.addAttribute(mdAttribute);

          // Create the browser field
          BrowserField tField = new BrowserField();
          tField.setMdAttribute(MdAttribute.get(mdAttribute.getId()));
          tField.apply();

          QueryFactory factory = new QueryFactory();
          BrowserField sField = BrowserField.getBrowserField(mdAttributeReference);

          BrowserFieldQuery bfQuery = new BrowserFieldQuery(factory);
          bfQuery.WHERE(bfQuery.getId().EQ(sField.getId()));

          BrowserRootQuery rootQuery = new BrowserRootQuery(factory);
          rootQuery.WHERE(rootQuery.field(bfQuery));
          rootQuery.AND(rootQuery.getDisease().EQ(disease));

          OIterator<? extends BrowserRoot> iterator = rootQuery.getIterator();

          try
          {
            while (iterator.hasNext())
            {
              BrowserRoot sRoot = iterator.next();

              BrowserRoot tRoot = new BrowserRoot();
              tRoot.setSelectable(sRoot.getSelectable());
              tRoot.setTerm(sRoot.getTerm());
              tRoot.setBrowserField(tField);
              tRoot.setDisease(disease);
              tRoot.apply();
            }
          }
          finally
          {
            iterator.close();
          }
        }
      }
      else if (mdAttributeIF instanceof MdAttributeBooleanDAOIF)
      {
        Boolean isAggregateFunction = data.containsKey(SelectableAggregate.class.getName()) ? (Boolean) data.get(SelectableAggregate.class.getName()) : false;

        if (!isAggregateFunction)
        {
          MdAttributeBooleanDAO mdAttribute = MdAttributeBooleanDAO.newInstance();
          mdAttribute.setValue(MdAttributeBooleanInfo.NAME, attributeName);
          mdAttribute.setStructValue(MdAttributeBooleanInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
          mdAttribute.setStructValue(MdAttributeBooleanInfo.POSITIVE_DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, MdAttributeBooleanInfo.TRUE);
          mdAttribute.setStructValue(MdAttributeBooleanInfo.NEGATIVE_DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, MdAttributeBooleanInfo.FALSE);
          mdAttribute.setValue(MdAttributeBooleanInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
          mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
          mdAttribute.apply();

          definition.addAttribute(mdAttribute);
        }
        else
        {
          MdAttributeDoubleDAO mdAttribute = MdAttributeDoubleDAO.newInstance();
          mdAttribute.setValue(MdAttributeDoubleInfo.NAME, attributeName);
          mdAttribute.setStructValue(MdAttributeDoubleInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
          mdAttribute.setValue(MdAttributeDoubleInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
          mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
          mdAttribute.getAttribute(MdAttributeDoubleInfo.LENGTH).setValue("20");
          mdAttribute.getAttribute(MdAttributeDoubleInfo.DECIMAL).setValue("2");
          mdAttribute.apply();

          definition.addAttribute(mdAttribute);
        }
      }
      else if (mdAttributeIF instanceof MdAttributeCharacterDAOIF || mdAttributeIF instanceof MdAttributeTextDAOIF)
      {
        if (data.containsKey(EntityInfo.CLASS))
        {
          String info = (String) data.get(EntityInfo.CLASS);

          if (! ( info.equals(Term.CLASS) || info.equals(GeoEntity.CLASS) ))
          {
            // Geo entity column
            String[] split = info.split("__");

            String key = split[0];
            String universal = split[1];

            GeoHierarchy hierarchy = GeoHierarchy.getGeoHierarchyFromType(universal);
            MdBusiness mdGeoEntity = hierarchy.getGeoEntityClass();

            MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) MdAttributeReferenceDAO.getByKey(key);
            MdBusinessDAOIF referenceMdBusinessDAO = mdAttributeReference.getReferenceMdBusinessDAO();

            MdAttributeReferenceDAO mdAttribute = MdAttributeReferenceDAO.newInstance();
            mdAttribute.setValue(MdAttributeConcreteInfo.NAME, attributeName);
            mdAttribute.setStructValue(MdAttributeConcreteInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeReference.getDisplayLabel(Session.getCurrentLocale()));
            mdAttribute.setValue(MdAttributeConcreteInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
            mdAttribute.setValue(MdAttributeReferenceInfo.REF_MD_ENTITY, referenceMdBusinessDAO.getId());
            mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
            mdAttribute.apply();

            definition.addAttribute(mdAttribute);

            if (definition.getLowest() == null || definition.getLowest().isAncestor(mdGeoEntity.definesType()))
            {
              definition.setLowest(hierarchy);
            }

            GeoNodeEntity node = new GeoNodeEntity();
            node.setKeyName(mdAttribute.getKey());
            node.setGeoEntityAttribute(MdAttributeReference.get(mdAttribute.getId()));
            node.apply();

            definition.addNode(node);
          }
        }
        else if (mdAttributeIF instanceof MdAttributeTextDAOIF)
        {
          MdAttributeTextDAO mdAttribute = MdAttributeTextDAO.newInstance();
          mdAttribute.setValue(MdAttributeTextInfo.NAME, attributeName);
          mdAttribute.setStructValue(MdAttributeTextInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
          mdAttribute.setValue(MdAttributeTextInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
          mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
          mdAttribute.apply();

          definition.addAttribute(mdAttribute);
        }
        else
        {
          /*
           * SIZE is spoofed because the MdAttributeCharacterDAOIF is an instanceof MdAttributeCharacter_Q which doesn't support SIZE
           */
          if (!attributeName.contains("__"))
          {
            MdAttributeCharacterDAO mdAttribute = MdAttributeCharacterDAO.newInstance();
            mdAttribute.setValue(MdAttributeCharacterInfo.NAME, attributeName);
            mdAttribute.setStructValue(MdAttributeCharacterInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
            mdAttribute.setValue(MdAttributeCharacterInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
            mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
            mdAttribute.setValue(MdAttributeCharacterInfo.SIZE, "4000");
            mdAttribute.apply();

            definition.addAttribute(mdAttribute);
          }
        }
      }
      else if (mdAttributeIF instanceof MdAttributeDateDAOIF)
      {
        MdAttributeDateDAO mdAttribute = MdAttributeDateDAO.newInstance();
        mdAttribute.setValue(MdAttributeDateInfo.NAME, attributeName);
        mdAttribute.setStructValue(MdAttributeDateInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
        mdAttribute.setValue(MdAttributeDateInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
        mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
        mdAttribute.apply();

        definition.addAttribute(mdAttribute);
      }
      else if (mdAttributeIF instanceof MdAttributeDateTimeDAOIF)
      {
        MdAttributeDateTimeDAO mdAttribute = MdAttributeDateTimeDAO.newInstance();
        mdAttribute.setValue(MdAttributeDateTimeInfo.NAME, attributeName);
        mdAttribute.setStructValue(MdAttributeDateTimeInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
        mdAttribute.setValue(MdAttributeDateTimeInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
        mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
        mdAttribute.apply();

        definition.addAttribute(mdAttribute);
      }
      else if (mdAttributeIF instanceof MdAttributeTimeDAOIF)
      {
        MdAttributeTimeDAO mdAttribute = MdAttributeTimeDAO.newInstance();
        mdAttribute.setValue(MdAttributeTimeInfo.NAME, attributeName);
        mdAttribute.setStructValue(MdAttributeTimeInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
        mdAttribute.setValue(MdAttributeTimeInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
        mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
        mdAttribute.apply();

        definition.addAttribute(mdAttribute);
      }
      else if (mdAttributeIF instanceof MdAttributeDecimalDAOIF)
      {
        MdAttributeDecimalDAO mdAttribute = MdAttributeDecimalDAO.newInstance();
        mdAttribute.setValue(MdAttributeDecimalInfo.NAME, attributeName);
        mdAttribute.setStructValue(MdAttributeDecimalInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
        mdAttribute.setValue(MdAttributeDecimalInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
        mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
        mdAttribute.getAttribute(MdAttributeDecimalInfo.LENGTH).setValue("20");
        mdAttribute.getAttribute(MdAttributeDecimalInfo.DECIMAL).setValue("2");
        mdAttribute.apply();

        definition.addAttribute(mdAttribute);
      }
      else if (mdAttributeIF instanceof MdAttributeDoubleDAOIF)
      {
        MdAttributeDoubleDAO mdAttribute = MdAttributeDoubleDAO.newInstance();
        mdAttribute.setValue(MdAttributeDoubleInfo.NAME, attributeName);
        mdAttribute.setStructValue(MdAttributeDoubleInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
        mdAttribute.setValue(MdAttributeDoubleInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
        mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
        mdAttribute.getAttribute(MdAttributeDoubleInfo.LENGTH).setValue("20");
        mdAttribute.getAttribute(MdAttributeDoubleInfo.DECIMAL).setValue("2");
        mdAttribute.apply();

        definition.addAttribute(mdAttribute);
      }
      else if (mdAttributeIF instanceof MdAttributeFloatDAOIF)
      {
        MdAttributeFloatDAO mdAttribute = MdAttributeFloatDAO.newInstance();
        mdAttribute.setValue(MdAttributeFloatInfo.NAME, attributeName);
        mdAttribute.setStructValue(MdAttributeFloatInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
        mdAttribute.setValue(MdAttributeFloatInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
        mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
        mdAttribute.getAttribute(MdAttributeFloatInfo.LENGTH).setValue("20");
        mdAttribute.getAttribute(MdAttributeFloatInfo.DECIMAL).setValue("2");
        mdAttribute.apply();

        definition.addAttribute(mdAttribute);
      }
      else if (mdAttributeIF instanceof MdAttributeIntegerDAOIF)
      {
        Boolean isAggregateFunction = data.containsKey(SelectableAggregate.class.getName()) ? (Boolean) data.get(SelectableAggregate.class.getName()) : false;

        if (!isAggregateFunction)
        {
          MdAttributeIntegerDAO mdAttribute = MdAttributeIntegerDAO.newInstance();
          mdAttribute.setValue(MdAttributeIntegerInfo.NAME, attributeName);
          mdAttribute.setStructValue(MdAttributeIntegerInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
          mdAttribute.setValue(MdAttributeIntegerInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
          mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
          mdAttribute.apply();

          definition.addAttribute(mdAttribute);
        }
        else
        {
          MdAttributeDoubleDAO mdAttribute = MdAttributeDoubleDAO.newInstance();
          mdAttribute.setValue(MdAttributeDoubleInfo.NAME, attributeName);
          mdAttribute.setStructValue(MdAttributeDoubleInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
          mdAttribute.setValue(MdAttributeDoubleInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
          mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
          mdAttribute.getAttribute(MdAttributeDoubleInfo.LENGTH).setValue("20");
          mdAttribute.getAttribute(MdAttributeDoubleInfo.DECIMAL).setValue("2");
          mdAttribute.apply();

          definition.addAttribute(mdAttribute);
        }
      }
      else if (mdAttributeIF instanceof MdAttributeLongDAOIF)
      {
        Boolean isAggregateFunction = data.containsKey(SelectableAggregate.class.getName()) ? (Boolean) data.get(SelectableAggregate.class.getName()) : false;

        if (!isAggregateFunction)
        {
          MdAttributeLongDAO mdAttribute = MdAttributeLongDAO.newInstance();
          mdAttribute.setValue(MdAttributeLongInfo.NAME, attributeName);
          mdAttribute.setStructValue(MdAttributeLongInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
          mdAttribute.setValue(MdAttributeLongInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
          mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
          mdAttribute.apply();

          definition.addAttribute(mdAttribute);
        }
        else
        {
          MdAttributeDoubleDAO mdAttribute = MdAttributeDoubleDAO.newInstance();
          mdAttribute.setValue(MdAttributeDoubleInfo.NAME, attributeName);
          mdAttribute.setStructValue(MdAttributeDoubleInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
          mdAttribute.setValue(MdAttributeDoubleInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
          mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
          mdAttribute.getAttribute(MdAttributeDoubleInfo.LENGTH).setValue("20");
          mdAttribute.getAttribute(MdAttributeDoubleInfo.DECIMAL).setValue("2");
          mdAttribute.apply();

          definition.addAttribute(mdAttribute);
        }
      }
      else
      {
        throw new ProgrammingErrorException("Unsupported selectable type: " + mdAttributeIF.getType());
      }
    }

    return definition;
  }

  public void overwrite(MdTable mdTable, ValueQuery query) throws JSONException
  {
    this.delete(mdTable, false);

    MdTableDAO mdTableDAO = MdTableDAO.get(mdTable.getId()).getBusinessDAO();

    this.build(mdTableDAO, query);
  }

  public void delete(MdTable mdTable)
  {
    this.delete(mdTable, true);
  }

  private void delete(MdTable mdTable, boolean includeTable)
  {
    this.deleteMaterializedTable(mdTable);

    /*
     * Delete the materialized table metadata
     */
    RefreshViewJob.getJob(mdTable).delete();

    /*
     * Delete the dataset build upon the MdTable
     */
    MappableClass mClass = null;

    try
    {
      mClass = MappableClass.getMappableClass(mdTable.definesType());
    }
    catch (DataNotFoundException e)
    {
      // Do nothing
    }

    this.deleteBrowserRoots(mdTable);

    if (mClass != null)
    {
      /*
       * Deleting the mappable class will delete the MdTable as well
       */
      mClass.delete(includeTable);
    }
    else
    {
      mdTable.delete();
    }

    if (!includeTable)
    {
      /*
       * Delete all of the mdAttributes on the table
       */
      MdTableDAOIF mdTableDAO = MdTableDAO.get(mdTable.getId());
      List<? extends MdAttributeDAOIF> mdAttributes = mdTableDAO.definesAttributes();

      for (MdAttributeDAOIF mdAttribute : mdAttributes)
      {
        mdAttribute.getBusinessDAO().delete();
      }
    }
  }

  public void deleteMaterializedTable(MdTable mdTable)
  {
    /*
     * Delete the materialized view
     */
    Database.executeStatement("DROP TABLE IF EXISTS " + mdTable.getTableName() + " CASCADE");
  }

  private void deleteBrowserRoots(MdTable mdTable)
  {
    MdTableDAOIF mdTableDAO = MdTableDAO.get(mdTable.getId());
    List<? extends MdAttributeDAOIF> mdAttributes = mdTableDAO.definesAttributes();

    for (MdAttributeDAOIF mdAttribute : mdAttributes)
    {
      if (mdAttribute instanceof MdAttributeReferenceDAOIF)
      {
        MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttribute;
        MdBusinessDAOIF mdBusiness = mdAttributeReference.getReferenceMdBusinessDAO();

        if (mdBusiness.definesType().equals(Term.CLASS))
        {
          BrowserField field = BrowserField.getBrowserField(mdAttributeReference);

          OIterator<? extends BrowserRoot> it = field.getAllroot();
          try
          {
            List<? extends BrowserRoot> roots = it.getAll();

            for (BrowserRoot root : roots)
            {
              root.delete();
            }
          }
          finally
          {
            it.close();
          }

          field.delete();
        }
      }
    }
  }

  @SuppressWarnings("unchecked")
  public void update(MdTable mdTable, ValueQuery query, List<String> attributesToAdd)
  {
    this.deleteMaterializedTable(mdTable);

    List<Selectable> filtered = new LinkedList<Selectable>();

    List<Selectable> selectables = query.getSelectableRefs();

    for (Selectable selectable : selectables)
    {
      Map<String, Object> data = (Map<String, Object>) selectable.getData();

      String alias = (String) data.get(SavedSearch.ALIAS);

      if (attributesToAdd.contains(alias))
      {
        filtered.add(selectable);
      }
    }

    MdTableDAO mdTableDAO = (MdTableDAO) BusinessFacade.getEntityDAO(mdTable).getEntityDAO();
    Disease disease = Disease.getCurrent();

    MappableClass mClass = MappableClass.getMappableClass(mdTableDAO);

    GeoHierarchy lowest = mClass.getAllUniversal().getAll().get(0);

    ClassDefinition definition = this.defineMdAttributes(mdTableDAO, disease, lowest, filtered);

    List<MdAttributeDAO> attributes = definition.getAttributes();
    GeoHierarchy nLowest = definition.getLowest();
    List<GeoNode> nodes = definition.getNodes();

    for (MdAttributeDAO mdAttributeDAO : attributes)
    {
      MdAttribute mdAttribute = MdAttribute.get(mdAttributeDAO.getId());

      MappableAttribute mAttribute = new MappableAttribute();
      mAttribute.setWrappedMdAttribute(mdAttribute);
      mAttribute.setAggregatable(true);
      mAttribute.apply();
    }

    if (nLowest != null && !nLowest.getId().equals(lowest.getId()))
    {
      /*
       * A new universal has been added lower down in the hierarchy
       */
      mClass.getUniversalRel(lowest).getAll().get(0).delete();

      mClass.addUniversal(nLowest).apply();
    }

    for (GeoNode node : nodes)
    {
      mClass.addGeoNode(node).apply();
    }
  }
}
