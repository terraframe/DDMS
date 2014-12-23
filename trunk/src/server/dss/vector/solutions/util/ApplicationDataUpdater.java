package dss.vector.solutions.util;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import com.runwaysdk.constants.MdBusinessInfo;
import com.runwaysdk.constants.MdEntityInfo;
import com.runwaysdk.constants.RelationshipInfo;
import com.runwaysdk.dataaccess.EntityDAO;
import com.runwaysdk.dataaccess.EntityDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDimensionDAOIF;
import com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.MdEnumerationDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.cache.ObjectCache;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.database.DatabaseException;
import com.runwaysdk.dataaccess.metadata.MdAttributeConcreteDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeDimensionDAO;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.metadata.MetadataDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.dataaccess.transaction.TransactionCache;
import com.runwaysdk.dataaccess.transaction.TransactionCacheIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.EntityQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;
import com.runwaysdk.util.IDGenerator;

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
import dss.vector.solutions.geo.ExtraFieldUniversal;
import dss.vector.solutions.geo.GeoField;
import dss.vector.solutions.geo.GeoHierarchy;
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
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.LayerQuery;
import dss.vector.solutions.query.RenderTypes;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchQuery;
import dss.vector.solutions.query.WellKnownNamesMaster;
import dss.vector.solutions.report.OutputFormatMaster;
import dss.vector.solutions.surveillance.PeriodTypeMaster;

public class ApplicationDataUpdater implements Reloadable, Runnable
{
  private boolean updateKeys;

  public ApplicationDataUpdater(boolean _updateKeys)
  {
    this.updateKeys = _updateKeys;
  }

  public void run()
  {
    if (this.updateKeys)
    {
      this.updateMdEntityRootIds();

      this.updateKeys();

      this.updateSavedSearchKeys();

      this.updateDeterminsticIdsMetadata();
    }
    else
    {
      this.updateBasicData();
    }
  }

  private void updateMdEntityRootIds()
  {
    List<String> types = getTypesToUpdate();

    for (String type : types)
    {
      MdEntityDAOIF mdEntityIF = MdEntityDAO.getMdEntityDAO(type);

      List<? extends MdEntityDAOIF> subClasses = mdEntityIF.getAllSubClasses();

      this.updateMdEntityRootId(mdEntityIF);

      for (MdEntityDAOIF subClass : subClasses)
      {
        updateMdEntityRootId(subClass);
      }
    }
  }

  @Transaction
  public void updateMdEntityRootId(MdEntityDAOIF mdEntityIF)
  {
    MdEntityDAO mdEntity = mdEntityIF.getBusinessDAO();
    mdEntity.getAttribute(BusinessInfo.KEY).setModified(true);
    mdEntity.apply();

    TransactionCacheIF cache = TransactionCache.getCurrentTransactionCache();

    String oldId = cache.getOriginalId(mdEntity.getId());

    if (oldId != null)
    {
      String oldRootId = oldId.substring(0, 32);
      String newRootId = mdEntity.getId().substring(0, 32);

      this.changeRootId(mdEntityIF, oldRootId, newRootId);

      if (mdEntity instanceof MdBusinessDAOIF)
      {
        // Float all of the references
        this.updateAttributeReferences((MdBusinessDAOIF) mdEntity, oldRootId, newRootId);

        this.updateCachedAttributeEnumerations((MdBusinessDAOIF) mdEntity, oldRootId, newRootId);

        this.updateRelationshipReferences((MdBusinessDAOIF) mdEntity, oldRootId, newRootId);

        this.updateEnumerations((MdBusinessDAOIF) mdEntity, oldRootId, newRootId);

        // Float any custom reference fields the query xml and json references
        this.updateSavedSearchRootIds(oldRootId, newRootId);
      }
    }

    ObjectCache.refreshTheEntireCache();
  }

  public void updateSavedSearchRootIds(String oldRootId, String newRootId)
  {
    MdBusinessDAOIF mdSavedSearch = MdBusinessDAO.getMdBusinessDAO(SavedSearch.CLASS);
    MdAttributeConcreteDAOIF queryXml = mdSavedSearch.definesAttribute(SavedSearch.QUERYXML);
    MdAttributeConcreteDAOIF config = mdSavedSearch.definesAttribute(SavedSearch.CONFIG);

    Connection connection = Database.getConnection();
    List<PreparedStatement> preparedStatementList = new LinkedList<PreparedStatement>();
    preparedStatementList.add(this.getPreparedStatement(connection, mdSavedSearch, queryXml.getColumnName(), oldRootId, newRootId));
    preparedStatementList.add(this.getPreparedStatement(connection, mdSavedSearch, config.getColumnName(), oldRootId, newRootId));

    Database.executeStatementBatch(preparedStatementList);
  }

  private void changeRootId(MdEntityDAOIF mdEntityDAOIF, String oldRootId, String newRootId)
  {
    Connection connection = Database.getConnection();
    List<PreparedStatement> preparedStatementList = new LinkedList<PreparedStatement>();
    List<? extends MdEntityDAOIF> superMdEntityList = mdEntityDAOIF.getSuperClasses();

    for (MdEntityDAOIF currMdEntity : superMdEntityList)
    {
      PreparedStatement statement = this.getPreparedStatement(connection, currMdEntity, EntityDAOIF.ID_COLUMN, oldRootId, newRootId);
      preparedStatementList.add(statement);
    }

    Database.executeStatementBatch(preparedStatementList);
  }

  private void updateEnumerations(MdBusinessDAOIF mdBusinessIF, String oldRootId, String newRootId)
  {
    Connection conn = Database.getConnection();
    List<PreparedStatement> preparedStatementList = new LinkedList<PreparedStatement>();

    List<MdEnumerationDAOIF> mdEnums = mdBusinessIF.getMdEnumerationDAOs();

    for (MdEnumerationDAOIF mdEnumerationDAOIF : mdEnums)
    {
      preparedStatementList.add(this.getPreparedStatement(conn, mdEnumerationDAOIF.getTableName(), MdEnumerationDAOIF.ITEM_ID_COLUMN, oldRootId, newRootId));
    }

    Database.executeStatementBatch(preparedStatementList);
  }

  private void updateRelationshipReferences(MdBusinessDAOIF mdBusinessIF, String oldRootId, String newRootId)
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
      }
    }

    Database.executeStatementBatch(preparedStatementList);
  }

  private void updateCachedAttributeEnumerations(MdBusinessDAOIF mdBusinessIF, String oldRootId, String newRootId)
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
      }

      this.updateDefaultValues(conn, preparedStatementList, mdAttrEnumDAOIF, oldRootId, newRootId);
    }

    Database.executeStatementBatch(preparedStatementList);
  }

  private void updateAttributeReferences(MdBusinessDAOIF mdBusinessIF, String oldRootId, String newRootId)
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
      }

      this.updateDefaultValues(conn, preparedStatementList, mdAttrRefDAOIF, oldRootId, newRootId);
    }

    Database.executeStatementBatch(preparedStatementList);
  }

  private void updateDefaultValues(Connection conn, List<PreparedStatement> preparedStatementList, MdAttributeDAOIF mdAttribute, String oldRootId, String newRootId)
  {
    // Update the default values
    if (mdAttribute.getDefaultValue() != null && mdAttribute.getDefaultValue().equals(oldRootId))
    {
      MdAttributeConcreteDAO mdAttributeConcrete = (MdAttributeConcreteDAO) mdAttribute.getMdAttributeConcrete().getBusinessDAO();
      MdBusinessDAOIF mdBusinessDAO = mdAttributeConcrete.getMdBusinessDAO();
      MdAttributeConcreteDAOIF mdDefaultValue = mdBusinessDAO.definesAttribute(MdAttributeConcreteInfo.DEFAULT_VALUE);

      PreparedStatement prepared = this.getPreparedStatement(conn, mdBusinessDAO, mdDefaultValue, oldRootId, newRootId);
      preparedStatementList.add(prepared);
    }

    List<MdAttributeDimensionDAOIF> mdAttributeDimensions = mdAttribute.getMdAttributeDimensions();

    for (MdAttributeDimensionDAOIF mdAttributeDimensionIF : mdAttributeDimensions)
    {
      if (mdAttributeDimensionIF.getDefaultValue() != null && mdAttributeDimensionIF.getDefaultValue().equals(oldRootId))
      {
        MdAttributeDimensionDAO mdAttributeDimension = (MdAttributeDimensionDAO) mdAttributeDimensionIF.getBusinessDAO();
        MdBusinessDAOIF mdBusinessDAO = mdAttributeDimension.getMdBusinessDAO();
        MdAttributeConcreteDAOIF mdDefaultValue = mdBusinessDAO.definesAttribute(MdAttributeConcreteInfo.DEFAULT_VALUE);

        PreparedStatement prepared = this.getPreparedStatement(conn, mdBusinessDAO, mdDefaultValue, oldRootId, newRootId);
        preparedStatementList.add(prepared);
      }
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

    return statement.toString();
  }

  @Transaction
  public void updateSavedSearchKeys()
  {
    SavedSearchQuery query = new SavedSearchQuery(new QueryFactory());
    OIterator<? extends SavedSearch> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        SavedSearch search = iterator.next();
        search.directApply();
      }
    }
    finally
    {
      iterator.close();
    }
  }

  @Transaction
  public void updateKeys()
  {
    String[] types = new String[] { GeoField.CLASS, ExtraFieldUniversal.CLASS, FieldRoot.CLASS };

    for (String type : types)
    {
      MdEntityDAOIF mdEntity = MdEntityDAO.getMdEntityDAO(type);
      EntityQuery query = new QueryFactory().entityQuery(mdEntity);
      OIterator<? extends ComponentIF> iterator = query.getIterator();

      try
      {
        while (iterator.hasNext())
        {
          Entity entity = (Entity) iterator.next();
          entity.apply();
        }
      }
      finally
      {
        iterator.close();
      }
    }
  }

  public void updateDeterminsticIdsMetadata()
  {
    List<String> types = getTypesToUpdate();

    for (String type : types)
    {
      MdEntityDAOIF mdEntityIF = this.updateMetadata(type);

      EntityQuery query = new QueryFactory().entityQuery(mdEntityIF);
      OIterator<? extends ComponentIF> iterator = query.getIterator();

      try
      {
        while (iterator.hasNext())
        {
          updateEntity(iterator);
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
    List<String> types = new LinkedList<String>();
    types.add(GeoEntity.CLASS);
    types.add(Term.CLASS);
    types.add(LifeStageMaster.CLASS);
    types.add(Disease.CLASS);
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
  public void updateEntity(OIterator<? extends ComponentIF> iterator)
  {
    Entity entity = (Entity) iterator.next();
    EntityDAO entityDAO = (EntityDAO) BusinessFacade.getEntityDAO(entity);
    entityDAO.getAttribute(BusinessInfo.KEY).setModified(true);
    entity.apply();
  }

  @Transaction
  public MdEntityDAOIF updateMetadata(String type)
  {
    MdEntityDAOIF mdEntityIF = MdEntityDAO.getMdEntityDAO(type);

    List<? extends MdEntityDAOIF> subClasses = mdEntityIF.getAllSubClasses();

    for (MdEntityDAOIF subClass : subClasses)
    {
      this.updateDeterministicIdsMetadata(subClass);
    }
    return mdEntityIF;
  }

  public void updateDeterministicIdsMetadata(MdEntityDAOIF mdEntityIF)
  {
    System.out.println("Testing: " + mdEntityIF.getKey());

    if (!mdEntityIF.hasDeterministicIds() && mdEntityIF.getSiteMaster().equals(CommonProperties.getDomain()))
    {
      System.out.println("Updating: " + mdEntityIF.getKey());

      MdEntityDAO mdEntity = mdEntityIF.getBusinessDAO();
      mdEntity.setValue(MdEntityInfo.HAS_DETERMINISTIC_IDS, MdAttributeBooleanInfo.TRUE);
      mdEntity.apply();
    }
  }

  @Transaction
  public void updateBasicData()
  {
    // // Force the cache to boot so it's not included in our timing
    MetadataDAO.get(MdBusinessInfo.CLASS, MdBusinessInfo.CLASS);

    this.updateMalariaSeasonLabels();

    this.updateSubCollections();

    this.updateAssayIds();

    // For ticket #2922
    this.updateAdultDiscriminatingDoseAssays();

    // Update the case period
    this.updateCasePeriod();

    // Fort Ticket #3072
    this.updateSystemAlerts();

    // For ticket #3050
    this.updateLayerSemanticId();
  }

  private void updateLayerSemanticId()
  {
    LayerQuery query = new LayerQuery(new QueryFactory());
    query.WHERE(query.getSemanticId().EQ((String) null));

    OIterator<? extends Layer> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        Layer layer = it.next();

        if (layer.getSemanticId() == null || layer.getSemanticId().length() == 0)
        {
          layer.setSemanticId(IDGenerator.nextID());
          layer.apply();
        }
      }
    }
    finally
    {
      it.close();
    }
  }

  private void updateSystemAlerts()
  {
    DiseaseQuery q = new DiseaseQuery(new QueryFactory());
    OIterator<? extends Disease> iter = q.getIterator();

    try
    {
      while (iter.hasNext())
      {
        Disease d = iter.next();
        DiseaseView v = d.getView();

        v.addSystemAlerts(d);
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
  private void updateCasePeriod()
  {
    DiseaseQuery q = new DiseaseQuery(new QueryFactory());
    OIterator<? extends Disease> iter = q.getIterator();

    try
    {
      while (iter.hasNext())
      {
        Disease d = iter.next();
        DiseaseView v = d.getView();

        v.addDefaultCasePeriod(d);
        v.addThresholdAlertCalcType(d);
      }
    }
    finally
    {
      iter.close();
    }
  }

  private void updateAdultDiscriminatingDoseAssays()
  {
    /*
     * Default hard-coded control number.  It is 10000 because we most derive
     * the control test number from the existing control test mortality and
     * the control number.  Existing control test mortality values have
     * relevant decimal values up to the hunderth decimal spot.
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
        assay.appLock();
        assay.setControlTestNumberExposed(controlNumber);
        assay.setControlTestNumberDead((int) ( controlNumber * assay.getControlTestMortality() / 100 ));
        assay.apply();
      }
    }
    finally
    {
      iterator.close();
    }
  }

  private void updateAssayIds()
  {
    String[] types = new String[] { EfficacyAssay.CLASS, InfectionAssay.CLASS, PooledInfectionAssay.CLASS, MolecularAssay.CLASS, BiochemicalAssay.CLASS, KnockDownAssay.CLASS, AdultDiscriminatingDoseAssay.CLASS, LarvaeDiscriminatingDoseAssay.CLASS, DiagnosticAssay.CLASS, TimeResponseAssay.CLASS };

    for (String type : types)
    {
      QueryFactory f = new QueryFactory();
      BusinessQuery q = f.businessQuery(type);

      q.WHERE(q.get(UniqueAssay.UNIQUE_ASSAY_ID).EQ(null));

      OIterator<Business> iter = q.getIterator();

      try
      {
        while (iter.hasNext())
        {
          Business b = iter.next();

          b.appLock();
          UniqueAssayUtil.setUniqueAssayId((UniqueAssay) b);
          b.apply();
        }
      }
      finally
      {
        iter.close();
      }
    }

  }

  private void updateMalariaSeasonLabels()
  {
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
          season.appLock();

          seasonLabel.setDefaultValue(season.getSeasonName());

          season.apply();
        }
      }
    }
    finally
    {
      iterator.close();
    }
  }

  private void updateSubCollections()
  {
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
        collection.appLock();

        collection.populateTotal();
        collection.apply();
      }
    }
    finally
    {
      iterator.close();
    }
  }

  public static void main(String[] args) throws FileNotFoundException
  {
    Option option = new Option("k", "update-ids", false, "Run the update predictive ids algorithm");

    Options options = new Options();
    options.addOption(option);

    try
    {
      CommandLineParser parser = new PosixParser();
      CommandLine cmd = parser.parse(options, args);

      boolean updateKeys = cmd.hasOption("k");

      ApplicationDataUpdater.start(updateKeys);
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
  private static void start(boolean updateDeterministicIds)
  {
    new ApplicationDataUpdater(updateDeterministicIds).run();
  }
}
