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
package dss.vector.solutions.util;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import com.runwaysdk.ComponentIF;
import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.business.Entity;
import com.runwaysdk.constants.BusinessInfo;
import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.constants.MdAttributeBooleanInfo;
import com.runwaysdk.constants.MdAttributeConcreteInfo;
import com.runwaysdk.constants.MdAttributeDimensionInfo;
import com.runwaysdk.constants.MdAttributeEnumerationInfo;
import com.runwaysdk.constants.MdAttributeReferenceInfo;
import com.runwaysdk.constants.MdBusinessInfo;
import com.runwaysdk.constants.MdEntityInfo;
import com.runwaysdk.constants.RelationshipInfo;
import com.runwaysdk.dataaccess.EntityDAO;
import com.runwaysdk.dataaccess.EntityDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.MdEnumerationDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.cache.ObjectCache;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.database.DatabaseException;
import com.runwaysdk.dataaccess.database.ServerIDGenerator;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.metadata.MetadataDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.dataaccess.transaction.TransactionCache;
import com.runwaysdk.dataaccess.transaction.TransactionCacheIF;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.EntityQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdWebForm;
import com.runwaysdk.system.metadata.MdWebFormQuery;
import com.runwaysdk.util.IDGenerator;
import com.runwaysdk.util.IdParser;

import dss.vector.solutions.MonthOfYearMaster;
import dss.vector.solutions.ResponseMaster;
import dss.vector.solutions.SurfacePositionMaster;
import dss.vector.solutions.entomology.BiochemicalAssay;
import dss.vector.solutions.entomology.ContainerShapeMaster;
import dss.vector.solutions.entomology.DiagnosticAssay;
import dss.vector.solutions.entomology.InfectionAssay;
import dss.vector.solutions.entomology.LifeStage;
import dss.vector.solutions.entomology.LifeStageMaster;
import dss.vector.solutions.entomology.MolecularAssay;
import dss.vector.solutions.entomology.PooledInfectionAssay;
import dss.vector.solutions.entomology.SubCollection;
import dss.vector.solutions.entomology.SubCollectionQuery;
import dss.vector.solutions.entomology.TimeResponseAssay;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery;
import dss.vector.solutions.entomology.assay.EfficacyAssay;
import dss.vector.solutions.entomology.assay.KnockDownAssay;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.UniqueAssay;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.DiseaseQuery;
import dss.vector.solutions.general.DiseaseView;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.general.MalariaSeasonQuery;
import dss.vector.solutions.general.MalariaSeasonSeasonLabel;
import dss.vector.solutions.general.OutbreakCalculationMaster;
import dss.vector.solutions.general.SystemAlertTypeMaster;
import dss.vector.solutions.general.ThresholdCalculationCaseTypesMaster;
import dss.vector.solutions.general.ThresholdCalculationMethodMaster;
import dss.vector.solutions.generator.MdFormUtil;
import dss.vector.solutions.geo.ExtraFieldUniversal;
import dss.vector.solutions.geo.GeoField;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.intervention.monitor.DiagnosisTypeMaster;
import dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierMaster;
import dss.vector.solutions.irs.InsecticideBrandUnitQualifierMaster;
import dss.vector.solutions.irs.InsecticideBrandUseMaster;
import dss.vector.solutions.irs.SprayMethodMaster;
import dss.vector.solutions.irs.SurfaceTypeMaster;
import dss.vector.solutions.irs.TargetUnitMaster;
import dss.vector.solutions.ontology.FieldRoot;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.LayerQuery;
import dss.vector.solutions.query.RenderTypes;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.WellKnownNamesMaster;
import dss.vector.solutions.report.OutputFormatMaster;
import dss.vector.solutions.surveillance.PeriodTypeMaster;

public class ApplicationDataUpdater implements Reloadable, Runnable
{
  private boolean             updateKeys;

  private boolean             updateRootIds;

  private boolean             countTermsRemaining;

  private boolean             customRun;

  /**
   * This updater will first do a "dry run" in which it will count how many records need to be updated.
   */
  private int                 total                 = 0;

  /**
   * A running tally of how many records we've updated so far.
   */
  private int                 count                 = 0;

  private long                lastProgressTimestamp = 0L;

  private int                 lastProgressRecords   = 0;

  private static final String LOG_TABLE_NAME        = "id_migration_log";

  /**
   * The progress interval controls how many progress updates will be printed. When set to 100, it means 100 "processing record" updates will be
   * printed throughout the lifecycle of the program.
   */
  private static final int    PROGRESS_INTERVAL     = 6000;

  public ApplicationDataUpdater(boolean _updateKeys, boolean _updateRootIds, boolean _countTermsRemaining, boolean _customRun)
  {
    this.updateKeys = _updateKeys;
    this.updateRootIds = _updateRootIds;
    this.countTermsRemaining = _countTermsRemaining;
    this.customRun = _customRun;
  }

  private void logIt(String msg)
  {
    System.out.println(msg);
  }

  private void countRemaining()
  {
    List<String> types = getTypesToUpdate();

    count = 0;

    System.out.println("Counting remaining terms...");
    TermQuery tq = new TermQuery(new QueryFactory());
    OIterator<? extends Term> it = tq.getIterator();

    try
    {
      while (it.hasNext())
      {
        Term t = it.next();

        String mdTypeRootId = IdParser.parseMdTypeRootIdFromId(t.getId());
        String newRootId = ServerIDGenerator.hashedId(t.getKey());
        String newId = IdParser.buildId(newRootId, mdTypeRootId);
        String currentId = t.getId();

        if (!newId.equals(currentId))
        {
          count++;
        }
      }
    }
    finally
    {
      it.close();
    }
    System.out.println(count + " terms still need updating.");

    int typesLeft = 0;
    System.out.println("Counting remaining types..");
    for (String type : types)
    {
      MdEntityDAOIF typeMdEntityIF = MdEntityDAO.getMdEntityDAO(type);

      List<? extends MdEntityDAOIF> subClasses = typeMdEntityIF.getAllSubClasses();

      for (MdEntityDAOIF subClass : subClasses)
      {
        String checkMe = subClass.definesType();
        MdEntityDAOIF mdEntityIF = MdEntityDAO.getMdEntityDAO(checkMe);

        String mdTypeRootId = IdParser.parseMdTypeRootIdFromId(mdEntityIF.getId());
        String newRootId = ServerIDGenerator.hashedId(mdEntityIF.getKey());
        String newId = IdParser.buildId(newRootId, mdTypeRootId);
        String currentId = mdEntityIF.getId();

        if (!newId.equals(currentId))
        {
          typesLeft++;
        }
      }
    }
    System.out.println(typesLeft + " types still need updating.");
  }

  @Request
  public void run()
  {
    if (this.countTermsRemaining)
    {
      countRemaining();
      return;
    }

    logIt("Creating a logging database table by name '" + LOG_TABLE_NAME + "'. This table contains information about the currently running task.");

    executeArbitrarySQL("CREATE TABLE IF NOT EXISTS " + LOG_TABLE_NAME + " ( old_id varchar(255), new_id varchar(255), record_number varchar(255) )");
    executeArbitrarySQL("INSERT INTO " + LOG_TABLE_NAME + " values ('', '', '')");

    logIt("Performing dry run to calculate total records, on larger databases this will take a few hours...");

    if (this.customRun)
    {
      logIt("Custom run enabled.");
      updateDeterminsticIdsMetadata(true);
    }
    else
    {
      performUpdate(true);
    }
    total = count;
    count = 0;
    logIt("\nDry run completed. A total of [" + total + "] records will be processed.\n--------------------------------\n");

    if (this.customRun)
    {
      updateDeterminsticIdsMetadata(false);
    }
    else
    {
      performUpdate(false);
    }

    executeArbitrarySQL("DROP TABLE " + LOG_TABLE_NAME);
  }

  private void executeArbitrarySQL(String sql)
  {
    Connection conn = null;
    try
    {
      conn = Database.getDDLConnection();

      Statement statement = conn.createStatement();
      statement.executeUpdate(sql);
      statement.close();

      conn.commit();
    }
    catch (Exception ex)
    {
      String error = ex.getMessage() + " - SQL Statement That caused the error: [" + sql + "].";
      logIt(error);
      ex.printStackTrace();
    }
    finally
    {
      if (conn != null)
      {
        try
        {
          Database.closeConnection(conn);
        }
        catch (SQLException e)
        {
          e.printStackTrace();
        }
      }
    }
  }

  public void onRecordUpdate(boolean dryRun, String old_id, String new_id)
  {
    try
    {
      count++;

      if (!dryRun)
      {
        executeArbitrarySQL("UPDATE " + LOG_TABLE_NAME + " SET old_id='" + old_id + "', new_id='" + new_id + "', record_number='" + count + "'");

        if (total == 0)
        {
          total = 1;
        } // Protect against divide by 0;

        int dividend = Math.round( ( (float) total ) / ( (float) PROGRESS_INTERVAL ));
        if (dividend == 0)
        {
          dividend = 1;
        } // Protect against divide by 0.

        if (count != 0 && ( count % dividend == 0 ))
        {
          int progressPercent = (int) ( ( ( (float) count ) / ( (float) total ) ) * 100 );

          double elapsed = ( System.nanoTime() - lastProgressTimestamp ) / 1000000000;
          if (elapsed == 0)
          {
            elapsed = 0.1F;
          } // Safeguard against divide by 0.

          int recordsProcessed = count - lastProgressRecords;
          if (recordsProcessed == 0)
          {
            recordsProcessed = 1;
          } // Safeguard against divide by 0.

          double velocity = ( ( (double) recordsProcessed ) / ( elapsed ) );

          int remainingRecords = total - count;

          String timeLeft = "";
          int secLeft = (int) ( remainingRecords / velocity );
          if (secLeft > 100)
          {
            int minLeft = secLeft / 60;

            if (minLeft > 100)
            {
              int hourLeft = minLeft / 60;

              timeLeft = hourLeft + " hours";
            }
            else
            {
              timeLeft = minLeft + " minutes.";
            }
          }
          else
          {
            timeLeft = secLeft + " seconds.";
          }

          Date date = new Date();
          SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
          String formattedDate = sdf.format(date);

          String msg = "Processing record " + count + " of " + total + ". Start time: " + formattedDate + ". Total progress: " + progressPercent + "%. Current velocity: " + velocity + ". Estimated time remaining: " + timeLeft;

          logIt(msg);

          lastProgressTimestamp = System.nanoTime();
          lastProgressRecords = count;
        }
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  private void performUpdate(boolean dryRun)
  {
    if (this.updateRootIds)
    {
      this.updateMdEntityRootIds(dryRun);
    }

    if (this.updateKeys)
    {
      this.updateKeys(dryRun);

      this.updateSavedSearchKeys(dryRun);

      this.updateDeterminsticIdsMetadata(dryRun);
    }

    if (!this.updateRootIds && !this.updateKeys)
    {
      this.updateBasicData(dryRun);
    }
  }

  private void updateMdEntityRootIds(boolean dryRun)
  {
    List<String> types = getTypesToUpdate();

    for (String type : types)
    {
      MdEntityDAOIF mdEntityIF = MdEntityDAO.getMdEntityDAO(type);

      List<? extends MdEntityDAOIF> subClasses = mdEntityIF.getAllSubClasses();

      for (MdEntityDAOIF subClass : subClasses)
      {
        updateMdEntityRootId(subClass.definesType(), dryRun);
      }
    }
  }

  @Transaction
  public void updateMdEntityRootId(String type, boolean dryRun)
  {
    logIt("Updating root ids for type: " + type);

    MdEntityDAOIF mdEntityIF = MdEntityDAO.getMdEntityDAO(type);
    MdEntityDAO mdEntity = mdEntityIF.getBusinessDAO();

    onRecordUpdate(dryRun, mdEntity.getId(), "0");
    if (!dryRun)
    {
      mdEntity.getAttribute(BusinessInfo.KEY).setModified(true);
      mdEntity.apply();
    }

    TransactionCacheIF cache = TransactionCache.getCurrentTransactionCache();

    String newId = mdEntity.getId();
    String oldId = cache.getOriginalId(newId);

    if (oldId != null)
    {
      String oldRootId = oldId.substring(0, 32);
      String newRootId = newId.substring(0, 32);

      this.changeRootId(mdEntityIF, oldRootId, newRootId, dryRun);

      if (mdEntity instanceof MdBusinessDAOIF)
      {
        // Float all of the references
        this.updateAttributeReferences((MdBusinessDAOIF) mdEntity, oldRootId, newRootId, dryRun);

        this.updateCachedAttributeEnumerations((MdBusinessDAOIF) mdEntity, oldRootId, newRootId, dryRun);

        this.updateRelationshipReferences((MdBusinessDAOIF) mdEntity, oldRootId, newRootId, dryRun);

        this.updateEnumerations((MdBusinessDAOIF) mdEntity, oldRootId, newRootId, dryRun);

        // Float any custom reference fields the query xml and json references
        this.updateSavedSearchRootIds(oldRootId, newRootId, dryRun);
      }
    }

    ObjectCache.refreshTheEntireCache();
  }

  public void updateSavedSearchRootIds(String oldRootId, String newRootId, boolean dryRun)
  {
    count++;
    onRecordUpdate(dryRun, oldRootId, newRootId);

    MdBusinessDAOIF mdSavedSearch = MdBusinessDAO.getMdBusinessDAO(SavedSearch.CLASS);
    MdAttributeConcreteDAOIF queryXml = mdSavedSearch.definesAttribute(SavedSearch.QUERYXML);
    MdAttributeConcreteDAOIF config = mdSavedSearch.definesAttribute(SavedSearch.CONFIG);

    Connection connection = Database.getConnection();
    List<PreparedStatement> preparedStatementList = new LinkedList<PreparedStatement>();
    preparedStatementList.add(this.getPreparedStatement(connection, mdSavedSearch, queryXml.getColumnName(), oldRootId, newRootId));
    preparedStatementList.add(this.getPreparedStatement(connection, mdSavedSearch, config.getColumnName(), oldRootId, newRootId));

    if (!dryRun)
    {
      Database.executeStatementBatch(preparedStatementList);
    }
  }

  private void changeRootId(MdEntityDAOIF mdEntityDAOIF, String oldRootId, String newRootId, boolean dryRun)
  {
    Connection connection = Database.getConnection();
    List<PreparedStatement> preparedStatementList = new LinkedList<PreparedStatement>();
    List<? extends MdEntityDAOIF> superMdEntityList = mdEntityDAOIF.getSuperClasses();

    for (MdEntityDAOIF currMdEntity : superMdEntityList)
    {
      PreparedStatement statement = this.getPreparedStatement(connection, currMdEntity, EntityDAOIF.ID_COLUMN, oldRootId, newRootId);
      preparedStatementList.add(statement);
      count++;
    }

    count--;
    onRecordUpdate(dryRun, oldRootId, newRootId);

    if (!dryRun)
    {
      Database.executeStatementBatch(preparedStatementList);
    }
  }

  private void updateEnumerations(MdBusinessDAOIF mdBusinessIF, String oldRootId, String newRootId, boolean dryRun)
  {
    Connection conn = Database.getConnection();
    List<PreparedStatement> preparedStatementList = new LinkedList<PreparedStatement>();

    List<MdEnumerationDAOIF> mdEnums = mdBusinessIF.getMdEnumerationDAOs();

    for (MdEnumerationDAOIF mdEnumerationDAOIF : mdEnums)
    {
      preparedStatementList.add(this.getPreparedStatement(conn, mdEnumerationDAOIF.getTableName(), MdEnumerationDAOIF.ITEM_ID_COLUMN, oldRootId, newRootId));
      count++;
    }

    count--;
    onRecordUpdate(dryRun, oldRootId, newRootId);

    if (!dryRun)
    {
      Database.executeStatementBatch(preparedStatementList);
    }
  }

  private void updateRelationshipReferences(MdBusinessDAOIF mdBusinessIF, String oldRootId, String newRootId, boolean dryRun)
  {
    Connection conn = Database.getConnection();
    List<PreparedStatement> preparedStatementList = new LinkedList<PreparedStatement>();

    List<MdRelationshipDAOIF> parentMdRelationshipList = mdBusinessIF.getAllParentMdRelationships();

    for (MdRelationshipDAOIF mdRelationshipDAOIF : parentMdRelationshipList)
    {
      if (mdRelationshipDAOIF.isAbstract())
      {
        continue;
      }

      List<MdRelationshipDAOIF> superMdRelationshipDAOIF = mdRelationshipDAOIF.getSuperClasses();

      for (MdRelationshipDAOIF parentMdRelationshipDAOIF : superMdRelationshipDAOIF)
      {
        preparedStatementList.add(this.getPreparedStatement(conn, parentMdRelationshipDAOIF, RelationshipInfo.PARENT_ID, oldRootId, newRootId));
        count++;
      }
    }

    List<MdRelationshipDAOIF> childMdRelationshipList = mdBusinessIF.getAllChildMdRelationships();

    // Update child ids
    for (MdRelationshipDAOIF mdRelationshipDAOIF : childMdRelationshipList)
    {
      if (mdRelationshipDAOIF.isAbstract())
      {
        continue;
      }

      List<MdRelationshipDAOIF> superMdRelationshipDAOIF = mdRelationshipDAOIF.getSuperClasses();

      for (MdRelationshipDAOIF childMdRelationshipDAOIF : superMdRelationshipDAOIF)
      {
        preparedStatementList.add(this.getPreparedStatement(conn, childMdRelationshipDAOIF, RelationshipInfo.CHILD_ID, oldRootId, newRootId));
        count++;
      }
    }

    count--;
    onRecordUpdate(dryRun, oldRootId, newRootId);

    if (!dryRun)
    {
      Database.executeStatementBatch(preparedStatementList);
    }
  }

  private void updateCachedAttributeEnumerations(MdBusinessDAOIF mdBusinessIF, String oldRootId, String newRootId, boolean dryRun)
  {
    Connection conn = Database.getConnection();

    List<PreparedStatement> preparedStatementList = new LinkedList<PreparedStatement>();

    List<MdAttributeEnumerationDAOIF> mdAttrEnumList = mdBusinessIF.getAllEnumerationAttributes();

    for (MdAttributeEnumerationDAOIF mdAttrEnumDAOIF : mdAttrEnumList)
    {
      MdClassDAOIF mdClassDAOIF = mdAttrEnumDAOIF.definedByClass();

      if (mdClassDAOIF instanceof MdEntityDAOIF)
      {
        PreparedStatement prepared = this.getPreparedStatement(conn, (MdEntityDAOIF) mdClassDAOIF, mdAttrEnumDAOIF.getCacheColumnName(), oldRootId, newRootId);
        preparedStatementList.add(prepared);
        count++;
      }
    }

    this.updateDefaultValues(conn, preparedStatementList, MdAttributeEnumerationInfo.CLASS, oldRootId, newRootId);
    onRecordUpdate(dryRun, oldRootId, newRootId);

    if (!dryRun)
    {
      Database.executeStatementBatch(preparedStatementList);
    }
  }

  private void updateAttributeReferences(MdBusinessDAOIF mdBusinessIF, String oldRootId, String newRootId, boolean dryRun)
  {
    Connection conn = Database.getConnection();

    List<PreparedStatement> preparedStatementList = new LinkedList<PreparedStatement>();

    List<MdAttributeReferenceDAOIF> mdAttrRefList = mdBusinessIF.getAllReferenceAttributes();

    for (MdAttributeReferenceDAOIF mdAttrRefDAOIF : mdAttrRefList)
    {
      MdAttributeReferenceDAOIF mdAttrRefDAO = (MdAttributeReferenceDAOIF) mdAttrRefDAOIF;
      MdClassDAOIF mdClassDAOIF = mdAttrRefDAO.definedByClass();

      if (mdClassDAOIF instanceof MdEntityDAOIF)
      {
        PreparedStatement prepared = this.getPreparedStatement(conn, (MdEntityDAOIF) mdClassDAOIF, mdAttrRefDAO, oldRootId, newRootId);
        preparedStatementList.add(prepared);

        count++;
      }
    }

    this.updateDefaultValues(conn, preparedStatementList, MdAttributeReferenceInfo.CLASS, oldRootId, newRootId);
    onRecordUpdate(dryRun, oldRootId, newRootId);

    if (!dryRun)
    {
      Database.executeStatementBatch(preparedStatementList);
    }
  }

  private void updateDefaultValues(Connection conn, List<PreparedStatement> preparedStatementList, String attributeType, String oldRootId, String newRootId)
  {
    // Update the default values
    {
      MdBusinessDAOIF mdBusinessDAO = MdBusinessDAO.getMdBusinessDAO(attributeType);
      MdAttributeConcreteDAOIF mdDefaultValue = mdBusinessDAO.definesAttribute(MdAttributeConcreteInfo.DEFAULT_VALUE);

      PreparedStatement prepared = this.getPreparedStatement(conn, mdBusinessDAO, mdDefaultValue, oldRootId, newRootId);
      preparedStatementList.add(prepared);

      count++;
    }

    // Update the dimension default values
    {
      MdBusinessDAOIF mdBusinessDAO = MdBusinessDAO.getMdBusinessDAO(MdAttributeDimensionInfo.CLASS);
      MdAttributeConcreteDAOIF mdDefaultValue = mdBusinessDAO.definesAttribute(MdAttributeConcreteInfo.DEFAULT_VALUE);

      PreparedStatement prepared = this.getPreparedStatement(conn, mdBusinessDAO, mdDefaultValue, oldRootId, newRootId);
      preparedStatementList.add(prepared);
    }
  }

  public PreparedStatement getPreparedStatement(Connection conn, MdEntityDAOIF mdEntityDAOIF, MdAttributeConcreteDAOIF mdAttribute, String oldRootId, String newRootId)
  {
    return this.getPreparedStatement(conn, mdEntityDAOIF.getTableName(), mdAttribute.getDefinedColumnName(), oldRootId, newRootId);
  }

  public PreparedStatement getPreparedStatement(Connection conn, MdEntityDAOIF mdEntityDAOIF, String columnName, String oldRootId, String newRootId)
  {
    return this.getPreparedStatement(conn, mdEntityDAOIF.getTableName(), columnName, oldRootId, newRootId);
  }

  public PreparedStatement getPreparedStatement(Connection conn, String tableName, String columnName, String oldRootId, String newRootId)
  {
    try
    {
      String statement = this.getUpdateStatement(tableName, columnName, oldRootId, newRootId);

      PreparedStatement prepared = conn.prepareStatement(statement);
      return prepared;
    }
    catch (SQLException e)
    {
      throw new DatabaseException(e);
    }
  }

  public String getUpdateStatement(String tableName, String columnName, String oldRootId, String newRootId)
  {
    StringBuffer statement = new StringBuffer();
    statement.append("UPDATE " + tableName);
    statement.append(" SET " + columnName + " =REPLACE(" + columnName + ", '" + oldRootId + "', '" + newRootId + "')");

    count++;

    return statement.toString();
  }

  @Transaction
  public void updateSavedSearchKeys(boolean dryRun)
  {
    MdEntityDAOIF mdEntity = MdEntityDAO.getMdEntityDAO("dss.vector.solutions.query.SavedSearch");
    EntityQuery query = new QueryFactory().entityQuery(mdEntity);
    OIterator<? extends ComponentIF> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        try
        {
          ComponentIF component = iterator.next();

          logIt("Updating saved search key of component [" + component.getId() + "].");

          onRecordUpdate(dryRun, component.getId(), "0");

          if (!dryRun)
          {
            Method method = component.getClass().getMethod("directApply");
            method.invoke(component);
          }
        }
        catch (Exception e)
        {
          throw new ProgrammingErrorException(e);
        }
      }
    }
    finally
    {
      iterator.close();
    }
  }

  @Transaction
  public void updateKeys(boolean dryRun)
  {
    String[] types = new String[] { GeoField.CLASS, ExtraFieldUniversal.CLASS, FieldRoot.CLASS };

    for (String type : types)
    {
      logIt("Updating keys for type: " + type);

      MdEntityDAOIF mdEntity = MdEntityDAO.getMdEntityDAO(type);
      EntityQuery query = new QueryFactory().entityQuery(mdEntity);
      OIterator<? extends ComponentIF> iterator = query.getIterator();

      try
      {
        while (iterator.hasNext())
        {
          Entity entity = (Entity) iterator.next();

          onRecordUpdate(dryRun, entity.getId(), "0");
          if (!dryRun)
          {
            entity.apply();
          }
        }
      }
      finally
      {
        iterator.close();
      }
    }
  }

  public void updateDeterminsticIdsMetadata(boolean dryRun)
  {
    List<String> types = getTypesToUpdate();

    for (String type : types)
    {
      MdEntityDAOIF mdEntityIF = null;
      if (!this.customRun)
      {
        mdEntityIF = this.updateMetadata(type, dryRun);
      }
      else
      {
        mdEntityIF = MdEntityDAO.getMdEntityDAO(type);
      }

      logIt("Updating deterministic ids for type: " + type);

      EntityQuery query = new QueryFactory().entityQuery(mdEntityIF);
      OIterator<? extends ComponentIF> iterator = query.getIterator();

      try
      {
        while (iterator.hasNext())
        {
          updateEntity(iterator, dryRun);
        }
      }
      finally
      {
        iterator.close();
      }
    }
  }

  public List<String> getTypesToUpdate()
  {
    if (this.customRun)
    {
      return Arrays.asList(new String[] { Term.CLASS });
    }

    List<String> types = new LinkedList<String>();
    types.add(Disease.CLASS);
    types.add(GeoEntity.CLASS);
    types.add(Term.CLASS);
    types.add(LifeStageMaster.CLASS);
    types.add(ContainerShapeMaster.CLASS);
    types.add(DiagnosisTypeMaster.CLASS);
    types.add(InsecticideBrandConcentrationQualifierMaster.CLASS);
    types.add(InsecticideBrandUnitQualifierMaster.CLASS);
    types.add(InsecticideBrandUseMaster.CLASS);
    types.add(MonthOfYearMaster.CLASS);
    types.add(OrientationTypeMaster.CLASS);
    types.add(OutbreakCalculationMaster.CLASS);
    types.add(OutputFormatMaster.CLASS);
    types.add(PeriodTypeMaster.CLASS);
    types.add(RenderTypes.CLASS);
    types.add(ResponseMaster.CLASS);
    types.add(SprayMethodMaster.CLASS);
    types.add(SurfacePositionMaster.CLASS);
    types.add(SurfaceTypeMaster.CLASS);
    types.add(SystemAlertTypeMaster.CLASS);
    types.add(TargetUnitMaster.CLASS);
    types.add(ThresholdCalculationCaseTypesMaster.CLASS);
    types.add(ThresholdCalculationMethodMaster.CLASS);
    types.add(WellKnownNamesMaster.CLASS);

    return types;
  }

  @Transaction
  public void updateEntity(OIterator<? extends ComponentIF> iterator, boolean dryRun)
  {
    Entity entity = (Entity) iterator.next();

    onRecordUpdate(dryRun, entity.getId(), "0");
    if (!dryRun)
    {
      EntityDAO entityDAO = (EntityDAO) BusinessFacade.getEntityDAO(entity);
      entityDAO.getAttribute(BusinessInfo.KEY).setModified(true);
      entity.apply();
    }
  }

  @Transaction
  public MdEntityDAOIF updateMetadata(String type, boolean dryRun)
  {
    MdEntityDAOIF mdEntityIF = MdEntityDAO.getMdEntityDAO(type);

    List<? extends MdEntityDAOIF> subClasses = mdEntityIF.getAllSubClasses();

    for (MdEntityDAOIF subClass : subClasses)
    {
      this.updateDeterministicIdsMetadata(subClass, dryRun);
    }
    return mdEntityIF;
  }

  public void updateDeterministicIdsMetadata(MdEntityDAOIF mdEntityIF, boolean dryRun)
  {
    logIt("Testing: " + mdEntityIF.getKey());

    if (!mdEntityIF.hasDeterministicIds() && mdEntityIF.getSiteMaster().equals(CommonProperties.getDomain()))
    {
      onRecordUpdate(dryRun, mdEntityIF.getId(), "0");

      if (!dryRun)
      {
        logIt("Updating: " + mdEntityIF.getKey());

        MdEntityDAO mdEntity = mdEntityIF.getBusinessDAO();
        mdEntity.setValue(MdEntityInfo.HAS_DETERMINISTIC_IDS, MdAttributeBooleanInfo.TRUE);
        mdEntity.apply();
      }
    }
  }

  @Transaction
  public void updateBasicData(boolean dryRun)
  {
    logIt("Updating basic data...");

    // // Force the cache to boot so it's not included in our timing
    MetadataDAO.get(MdBusinessInfo.CLASS, MdBusinessInfo.CLASS);

    this.updateMalariaSeasonLabels(dryRun);

    this.updateSubCollections(dryRun);

    this.updateAssayIds(dryRun);

    // For ticket #2922
    this.updateAdultDiscriminatingDoseAssays(dryRun);

    // Update the case period
    this.updateCasePeriod(dryRun);

    // Fort Ticket #3072
    this.updateSystemAlerts(dryRun);

    // For ticket #3050
    this.updateLayerSemanticId(dryRun);
    
    // For ticket #3673
    this.updateFormDatasets(dryRun);
  }

  private void updateLayerSemanticId(boolean dryRun)
  {
    logIt("Updating layer semantic ids.");

    LayerQuery query = new LayerQuery(new QueryFactory());
    query.WHERE(query.getSemanticId().EQ((String) null));

    OIterator<? extends Layer> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        Layer layer = it.next();

        onRecordUpdate(dryRun, layer.getId(), "0");
        if (!dryRun)
        {
          if (layer.getSemanticId() == null || layer.getSemanticId().length() == 0)
          {
            layer.setSemanticId(IDGenerator.nextID());
            layer.apply();
          }
        }
      }
    }
    finally
    {
      it.close();
    }
  }

  private void updateSystemAlerts(boolean dryRun)
  {
    logIt("Updating system alerts.");

    DiseaseQuery q = new DiseaseQuery(new QueryFactory());
    OIterator<? extends Disease> iter = q.getIterator();

    try
    {
      while (iter.hasNext())
      {
        Disease d = iter.next();

        onRecordUpdate(dryRun, d.getId(), "0");
        if (!dryRun)
        {
          DiseaseView v = d.getView();
          v.addSystemAlerts(d);
        }
      }
    }
    finally
    {
      iter.close();
    }
  }

  /**
   * Makes sure all diseases
   */
  private void updateCasePeriod(boolean dryRun)
  {
    logIt("Updating case period.");

    DiseaseQuery q = new DiseaseQuery(new QueryFactory());
    OIterator<? extends Disease> iter = q.getIterator();

    try
    {
      while (iter.hasNext())
      {
        Disease d = iter.next();

        onRecordUpdate(dryRun, d.getId(), "0");
        if (!dryRun)
        {
          DiseaseView v = d.getView();
          v.addDefaultCasePeriod(d);
          v.addThresholdAlertCalcType(d);
        }
      }
    }
    finally
    {
      iter.close();
    }
  }

  /**
   * Makes sure all diseases
   */
  private void updateFormDatasets(boolean dryRun)
  {
    logIt("Updating form datasets");
    
    
    // Copy some disease data around
//    QueryFactory qf = new QueryFactory();
//    
//    MdWebFormQuery webq = new MdWebFormQuery(qf);
//    MdClassQuery mdcq = new MdClassQuery(qf);
//    ValueQuery vq = new ValueQuery(qf);
//    
//    vq.WHERE(webq.getFormMdClass().getId().EQ(mdcq.getId()));
//    
//    vq.SELECT(mdcq.getKeyName("mdcType"));
//    vq.SELECT(webq.getId("webqId"));
//    
//    OIterator<ValueObject> it = vq.getIterator();

    ResultSet resultSet = Database.query("SELECT \n" + 
        "     metadata_8.key_name AS key_name_9,\n" + 
        "     md_web_form_3.id AS id_10\n" + 
        "FROM metadata metadata_8,\n" + 
        "     md_class md_class_6,\n" + 
        "     md_form md_form_2,\n" + 
        "     md_web_form md_web_form_3 \n" + 
        "WHERE md_web_form_3.id = md_form_2.id\n" + 
        "AND md_class_6.id = metadata_8.id\n" + 
        "AND md_form_2.form_md_class = md_class_6.id");

    try
    {
//      ValueObject obj = it.next();
//      
//      String mdcType = obj.getValue("mdcType");
//      String webqId = obj.getValue("webqId");
      
      if (resultSet.next())
      {
        String mdcType = resultSet.getString("key_name_9");
        String webqId = resultSet.getString("id_10");
        
        MdWebForm form = MdWebForm.get(webqId);
        
        if (form.getDimension() == null)
        {
          // It sucks that we have to do this in 2 different queries but you can't join a business query with a metadata query in runway.
          BusinessQuery bq = new QueryFactory().businessQuery(mdcType);
          OIterator<Business> bit = bq.getIterator();
          
          try
          {
            if (bit.hasNext())
            {
              Business biz = bit.next();
              String diseaseId = biz.getValue(MdFormUtil.DISEASE);
              
              form.appLock();
              form.setDimension(Disease.get(diseaseId).getDimension());
              form.apply();
            }
          }
          finally
          {
            bit.close();
          }
        }
      }
    }
    catch (SQLException sqlEx1)
    {
      Database.throwDatabaseException(sqlEx1);
    }
    finally
    {
//      it.close();
      
      try
      {
        java.sql.Statement statement = resultSet.getStatement();
        resultSet.close();
        statement.close();
      }
      catch (SQLException sqlEx2)
      {
        Database.throwDatabaseException(sqlEx2);
      }
    }
    
    

    MdWebFormQuery q = new MdWebFormQuery(new QueryFactory());
    OIterator<? extends MdWebForm> iter = q.getIterator();

    try
    {
      while (iter.hasNext())
      {
        MdWebForm form = iter.next();
        
        if (MdFormUtil.isDatasetValid(form))
        {
          onRecordUpdate(dryRun, form.getId(), "0");
          if (!dryRun)
          {
            Disease dz = MdFormUtil.getFormDisease(form);
            
            if (dz == null)
            {
              Disease[] diseases = Disease.getAllDiseases();
              
              for (Disease loopDz : diseases)
              {
                if (!MdFormUtil.hasDataset(form, loopDz))
                {
                  MdFormUtil.exportDataset(form.getId(), loopDz);
                }
              }
            }
            else
            {
              if (!MdFormUtil.hasDataset(form, dz))
              {
                MdFormUtil.exportDataset(form.getId(), dz);
              }
            }
          }
        }
      }
    }
    finally
    {
      iter.close();
    }
  }

  private void updateAdultDiscriminatingDoseAssays(boolean dryRun)
  {
    logIt("Updating AdultDiscriminatingDoseAssays.");

    /*
     * Default hard-coded control number. It is 10000 because we most derive the control test number from the existing control test mortality and the
     * control number. Existing control test mortality values have relevant decimal values up to the hunderth decimal spot.
     */
    int controlNumber = 10000;

    AdultDiscriminatingDoseAssayQuery query = new AdultDiscriminatingDoseAssayQuery(new QueryFactory());
    query.WHERE(query.getSiteMaster().EQ(CommonProperties.getDomain()));
    query.AND(query.getControlTestMortality().NE((Float) null));
    query.AND(query.getControlTestNumberExposed().EQ((Integer) null));
    query.AND(query.getControlTestNumberDead().EQ((Integer) null));

    OIterator<? extends AdultDiscriminatingDoseAssay> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        AdultDiscriminatingDoseAssay assay = iterator.next();

        onRecordUpdate(dryRun, assay.getId(), "0");
        if (!dryRun)
        {
          assay.appLock();
          assay.setControlTestNumberExposed(controlNumber);
          assay.setControlTestNumberDead((int) ( controlNumber * assay.getControlTestMortality() / 100 ));
          assay.apply();
        }
      }
    }
    finally
    {
      iterator.close();
    }
  }

  private void updateAssayIds(boolean dryRun)
  {
    String[] types = new String[] { EfficacyAssay.CLASS, InfectionAssay.CLASS, PooledInfectionAssay.CLASS, MolecularAssay.CLASS, BiochemicalAssay.CLASS, KnockDownAssay.CLASS, AdultDiscriminatingDoseAssay.CLASS, LarvaeDiscriminatingDoseAssay.CLASS, DiagnosticAssay.CLASS, TimeResponseAssay.CLASS };

    for (String type : types)
    {
      logIt("Updating assay type [" + type + "].");

      QueryFactory f = new QueryFactory();
      BusinessQuery q = f.businessQuery(type);

      q.WHERE(q.get(UniqueAssay.UNIQUE_ASSAY_ID).EQ(null));

      OIterator<Business> iter = q.getIterator();

      try
      {
        while (iter.hasNext())
        {
          Business b = iter.next();

          onRecordUpdate(dryRun, b.getId(), "0");
          if (!dryRun)
          {
            b.appLock();
            UniqueAssayUtil.setUniqueAssayId((UniqueAssay) b);
            b.apply();
          }
        }
      }
      finally
      {
        iter.close();
      }
    }

  }

  private void updateMalariaSeasonLabels(boolean dryRun)
  {
    logIt("Updating malaria season labels.");

    MalariaSeasonQuery query = new MalariaSeasonQuery(new QueryFactory());
    query.WHERE(query.getSiteMaster().EQ(CommonProperties.getDomain()));

    OIterator<? extends MalariaSeason> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        MalariaSeason season = iterator.next();

        MalariaSeasonSeasonLabel seasonLabel = season.getSeasonLabel();
        String defaultValue = seasonLabel.getDefaultValue();

        if (season.getSeasonName() != null && ( defaultValue == null || defaultValue.length() == 0 ))
        {
          onRecordUpdate(dryRun, season.getId(), "0");

          if (!dryRun)
          {
            season.appLock();
            seasonLabel.setDefaultValue(season.getSeasonName());
            season.apply();
          }
        }
      }
    }
    finally
    {
      iterator.close();
    }
  }

  private void updateSubCollections(boolean dryRun)
  {
    logIt("Updating sub collections.");

    SubCollectionQuery query = new SubCollectionQuery(new QueryFactory());
    query.WHERE(query.getSiteMaster().EQ(CommonProperties.getDomain()));
    query.AND(query.getFemalesTotal().EQ((Integer) null));
    query.AND(query.getCollection().getLifeStage().containsAll(LifeStage.ADULT));

    OIterator<? extends SubCollection> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        SubCollection collection = iterator.next();

        onRecordUpdate(dryRun, collection.getId(), "0");
        if (!dryRun)
        {
          collection.appLock();
          collection.populateTotal();
          collection.apply();
        }
      }
    }
    finally
    {
      iterator.close();
    }
  }

  public static void main(String[] args) throws FileNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
  {
    Options options = new Options();
    options.addOption(new Option("c", "custom-run", false, "Custom run"));
    options.addOption(new Option("k", "update-ids", false, "Run the update predictive ids algorithm"));
    options.addOption(new Option("r", "update-root-ids", false, "Run the update root ids"));
    options.addOption(new Option("v", "validate", false, "Counts the number of unpatched records. Useful for validating that the data updater ran successfully."));

    try
    {
      CommandLineParser parser = new PosixParser();
      CommandLine cmd = parser.parse(options, args);

      boolean updateKeys = cmd.hasOption("k");
      boolean updateRootIds = cmd.hasOption("r");
      boolean countTermsRemaining = cmd.hasOption("v");
      boolean customRun = cmd.hasOption("c");

      // I ran into a classloader issue, this seems to fix it.
      Class<?> clazz = LoaderDecorator.load("dss.vector.solutions.util.ApplicationDataUpdater");
      clazz.getMethod("start", new Class<?>[] { Boolean.class, Boolean.class, Boolean.class, Boolean.class }).invoke(null, new Object[] { updateKeys, updateRootIds, countTermsRemaining, customRun });
    }
    catch (ParseException e)
    {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("setup", options);

      e.printStackTrace();
    }
    finally
    {
      CacheShutdown.shutdown();
    }
  }

  @Request
  public static void start(Boolean updateDeterministicIds, Boolean updateRootIds, Boolean countTermsRemaining, Boolean customRun)
  {
    new ApplicationDataUpdater(updateDeterministicIds, updateRootIds, countTermsRemaining, customRun).run();
  }
}
