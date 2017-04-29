package dss.vector.solutions.query;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.BusinessFacade;
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
import com.runwaysdk.constants.MdAttributeTimeInfo;
import com.runwaysdk.constants.MdTableInfo;
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
import com.runwaysdk.dataaccess.MdAttributeTimeDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.metadata.MdAttributeBooleanDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeCharacterDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeDateDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeDateTimeDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeDecimalDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeDoubleDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeFloatDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeIntegerDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeLongDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeReferenceDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeTimeDAO;
import com.runwaysdk.dataaccess.metadata.MdTableDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Selectable;
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
import dss.vector.solutions.kaleidoscope.data.etl.LocalPersistenceStrategy;
import dss.vector.solutions.kaleidoscope.geo.GeoNode;
import dss.vector.solutions.kaleidoscope.geo.GeoNodeEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.SelectableSQLKey;

public class MdTableBuilder implements Reloadable
{
  public MdTable build(String viewName, Map<Selectable, MdAttributeConcreteDAOIF> map, JSONObject config) throws JSONException
  {
    GeoHierarchy lowest = null;

    // Create the MdTable
    MdTableDAO mdTableDAO = MdTableDAO.newInstance();
    mdTableDAO.setValue(MdTableInfo.NAME, viewName);
    mdTableDAO.setValue(MdTableInfo.PACKAGE, MDSSInfo.GENERATED_TABLE_PACKAGE);
    mdTableDAO.setStructValue(MdTableInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, viewName);
    mdTableDAO.setValue(MdTableInfo.TABLE_NAME, viewName);
    mdTableDAO.apply();

    Set<Entry<Selectable, MdAttributeConcreteDAOIF>> entries = map.entrySet();

    for (Entry<Selectable, MdAttributeConcreteDAOIF> entry : entries)
    {
      MdAttributeConcreteDAOIF mdAttributeIF = entry.getValue();
      Selectable selectable = entry.getKey();

      String attributeName = mdAttributeIF.definesAttribute();
      attributeName = attributeName.substring(Math.max(0, attributeName.length() - 64));

      if (selectable instanceof SelectableSQLKey)
      {
        MdAttributeReferenceDAOIF mdAttributeReference = ( (SelectableSQLKey) selectable ).getMdAttribute();
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
        }
      }
      else if (mdAttributeIF instanceof MdAttributeBooleanDAOIF)
      {
        MdAttributeBooleanDAO mdAttribute = MdAttributeBooleanDAO.newInstance();
        mdAttribute.setValue(MdAttributeBooleanInfo.NAME, attributeName);
        mdAttribute.setStructValue(MdAttributeBooleanInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
        mdAttribute.setStructValue(MdAttributeBooleanInfo.POSITIVE_DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, MdAttributeBooleanInfo.TRUE);
        mdAttribute.setStructValue(MdAttributeBooleanInfo.NEGATIVE_DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, MdAttributeBooleanInfo.FALSE);
        mdAttribute.setValue(MdAttributeBooleanInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
        mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
        mdAttribute.apply();
      }
      else if (mdAttributeIF instanceof MdAttributeCharacterDAOIF)
      {
        String data = (String) selectable.getData();

        if (data != null)
        {
          // Geo entity column
          String[] split = data.split("__");

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

          if (lowest == null || lowest.isAncestor(mdGeoEntity.definesType()))
          {
            lowest = hierarchy;
          }
        }
        else
        {
          /*
           * SIZE is spoofed because the MdAttributeCharacterDAOIF is an instanceof MdAttributeCharacter_Q which doesn't support SIZE
           */

          MdAttributeCharacterDAO mdAttribute = MdAttributeCharacterDAO.newInstance();
          mdAttribute.setValue(MdAttributeCharacterInfo.NAME, attributeName);
          mdAttribute.setStructValue(MdAttributeCharacterInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
          mdAttribute.setValue(MdAttributeCharacterInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
          mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
          mdAttribute.setValue(MdAttributeCharacterInfo.SIZE, "4000");
          mdAttribute.apply();
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
      }
      else if (mdAttributeIF instanceof MdAttributeDateTimeDAOIF)
      {
        MdAttributeDateTimeDAO mdAttribute = MdAttributeDateTimeDAO.newInstance();
        mdAttribute.setValue(MdAttributeDateTimeInfo.NAME, attributeName);
        mdAttribute.setStructValue(MdAttributeDateTimeInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
        mdAttribute.setValue(MdAttributeDateTimeInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
        mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
        mdAttribute.apply();
      }
      else if (mdAttributeIF instanceof MdAttributeTimeDAOIF)
      {
        MdAttributeTimeDAO mdAttribute = MdAttributeTimeDAO.newInstance();
        mdAttribute.setValue(MdAttributeTimeInfo.NAME, attributeName);
        mdAttribute.setStructValue(MdAttributeTimeInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
        mdAttribute.setValue(MdAttributeTimeInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
        mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
        mdAttribute.apply();
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
      }
      else if (mdAttributeIF instanceof MdAttributeIntegerDAOIF)
      {
        MdAttributeIntegerDAO mdAttribute = MdAttributeIntegerDAO.newInstance();
        mdAttribute.setValue(MdAttributeIntegerInfo.NAME, attributeName);
        mdAttribute.setStructValue(MdAttributeIntegerInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
        mdAttribute.setValue(MdAttributeIntegerInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
        mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
        mdAttribute.apply();
      }
      else if (mdAttributeIF instanceof MdAttributeLongDAOIF)
      {
        MdAttributeLongDAO mdAttribute = MdAttributeLongDAO.newInstance();
        mdAttribute.setValue(MdAttributeLongInfo.NAME, attributeName);
        mdAttribute.setStructValue(MdAttributeLongInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeIF.getDisplayLabel(Session.getCurrentLocale()));
        mdAttribute.setValue(MdAttributeLongInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
        mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
        mdAttribute.apply();
      }
      else
      {
        throw new RuntimeException("Unsupported selectable type: " + selectable.getClass().getName());
      }
    }

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
    List<GeoNode> nodes = new LinkedList<GeoNode>();

    MappableClass mClass = new MappableClass();
    mClass.setWrappedMdClass(mdTable);
    mClass.setDisease(Disease.getCurrent());
    mClass.apply();

    List<? extends MdAttributeDAOIF> attributes = mdTableDAO.definesAttributes();

    for (MdAttributeDAOIF mdAttributeDAO : attributes)
    {
      MdAttribute mdAttribute = MdAttribute.get(mdAttributeDAO.getId());

      MappableAttribute mAttribute = new MappableAttribute();
      mAttribute.setWrappedMdAttribute(mdAttribute);
      mAttribute.setAggregatable(true);
      mAttribute.apply();

      // Build the GeoNode
      if (mdAttributeDAO instanceof MdAttributeReferenceDAOIF)
      {
        MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttributeDAO;
        MdBusinessDAOIF mdBusiness = mdAttributeReference.getReferenceMdBusinessDAO();

        if (mdBusiness.definesType().equals(GeoEntity.CLASS))
        {
          GeoNodeEntity node = new GeoNodeEntity();
          node.setKeyName(mdAttribute.getKey());
          node.setGeoEntityAttribute((MdAttributeReference) mdAttribute);
          node.apply();

          nodes.add(node);
        }
      }
    }

    if (lowest != null)
    {
      mClass.addUniversal(lowest).apply();
    }

    for (GeoNode node : nodes)
    {
      mClass.addGeoNode(node).apply();
    }

    /*
     * Create the persistence strategy
     */
    LocalPersistenceStrategy strategy = new LocalPersistenceStrategy();
    strategy.apply();

    return mdTable;
  }

  public void delete(MdTable mdTable)
  {
    /*
     * Delete the materialized table metadata
     */
    RefreshViewJob.getJob(mdTable).delete();

    /*
     * Delete the dataset build upon the MdTable
     */
    MappableClass mClass = MappableClass.getMappableClass(mdTable.definesType());

    if (mClass != null)
    {
      /*
       * Deleting the mappable class will delete the MdTable as well
       */
      mClass.delete();
    }
    else
    {
      mdTable.delete();
    }

    /*
     * Drop the materialized view
     */
    List<String> batch = new LinkedList<String>();
    batch.add("DROP MATERIALIZED VIEW IF EXISTS " + mdTable.getTableName() + " CASCADE");
    Database.executeBatch(batch);
  }
}
