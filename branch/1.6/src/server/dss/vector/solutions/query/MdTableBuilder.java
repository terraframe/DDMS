package dss.vector.solutions.query;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
import com.runwaysdk.system.metadata.MdTable;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.SelectableSQLKey;

public class MdTableBuilder implements Reloadable
{
  private String                                    viewName;

  private Map<Selectable, MdAttributeConcreteDAOIF> map;

  public MdTableBuilder(String viewName, Map<Selectable, MdAttributeConcreteDAOIF> map)
  {
    this.viewName = viewName;
    this.map = map;
  }

  public MdTable build()
  {
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
        if (mdAttributeIF.definesAttribute().contains("__") && mdAttributeIF.definesAttribute().endsWith("_id"))
        {
          // Geo entity column
          String key = mdAttributeIF.definesAttribute().split("__")[0].replaceAll("_", ".");
          MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) MdAttributeReferenceDAO.getByKey(key);
          MdBusinessDAOIF referenceMdBusinessDAO = mdAttributeReference.getReferenceMdBusinessDAO();

          MdAttributeReferenceDAO mdAttribute = MdAttributeReferenceDAO.newInstance();
          mdAttribute.setValue(MdAttributeConcreteInfo.NAME, attributeName);
          mdAttribute.setStructValue(MdAttributeConcreteInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdAttributeReference.getDisplayLabel(Session.getCurrentLocale()));
          mdAttribute.setValue(MdAttributeConcreteInfo.DEFINING_MD_CLASS, mdTableDAO.getId());
          mdAttribute.setValue(MdAttributeReferenceInfo.REF_MD_ENTITY, referenceMdBusinessDAO.getId());
          mdAttribute.getAttribute(MdAttributeConcreteInfo.COLUMN_NAME).setValueNoValidation(selectable.getDbColumnName());
          mdAttribute.apply();
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
    
    RefreshViewJob job = new RefreshViewJob();
    job.setMaterializedTable(mdTable);
    job.apply();
    
    return mdTable;
  }
}
