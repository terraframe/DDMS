package dss.vector.solutions.entomology.assay;

import java.io.InputStream;
import java.util.Map;

import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.InnerJoin;
import com.terraframe.mojo.query.InnerJoinEq;
import com.terraframe.mojo.query.Join;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectableMoment;
import com.terraframe.mojo.query.SelectableSQL;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryCSVExporter;
import com.terraframe.mojo.query.ValueQueryExcelExporter;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.entomology.ConcreteMosquitoCollection;
import dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery;
import dss.vector.solutions.entomology.ControlMortalityException;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.query.MapUtil;
import dss.vector.solutions.query.NoThematicLayerException;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchRequiredException;
import dss.vector.solutions.query.ThematicLayer;
import dss.vector.solutions.util.QueryConfig;
import dss.vector.solutions.util.QueryUtil;

public class AdultDiscriminatingDoseAssay extends AdultDiscriminatingDoseAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234543769104L;

  public AdultDiscriminatingDoseAssay()
  {
    super();
  }

  @Override
  public void validateQuantityDead()
  {
    super.validateQuantityDead();

    new QuantityDeadValidator(this).validate();
  }

  @Override
  public void validateControlTestMortality()
  {
    if (this.getControlTestMortality() != null && this.getControlTestMortality() > 20)
    {
      String msg = "The mortality rate of the control collection exceeds 20% invalidating this test";

      ControlMortalityException e = new ControlMortalityException(msg);
      e.apply();

      throw e;
    }
  }

  @Override
  public void apply()
  {
    validateControlTestMortality();
    validateQuantityDead();

    float mortality = 0.0F;
    int live = 0;

    if (this.getQuantityDead() != null && this.getQuantityTested() != null && this.getQuantityDead() <= this.getQuantityTested())
    {
      mortality = (float) ( this.getQuantityDead() ) * 100 / this.getQuantityTested();
      live = this.getQuantityTested() - this.getQuantityDead();
    }

    this.setQuantityLive(live);
    this.setMortality(mortality);

    if (this.getControlTestMortality() != null && this.getControlTestMortality() > 5)
    {
      // Use abbots formula to correct the mortality rate
      // Corrected % = 100 * (T - C) / (100 - C)
      // (WHO/CDC/NTD/WHOPES/GCDPP/2006.3)
      // T = % mortality of the treated population
      // C = % mortality of the control population

      float corrected = 100.0F * ( mortality - this.getControlTestMortality() ) / ( 100.0F - this.getControlTestMortality() );
      this.setMortality(corrected);
    }

    super.apply();

    if (this.isSusceptible())
    {
      SusceptibleCollection info = new SusceptibleCollection();
      info.throwIt();
    }
    else if (this.isPotentiallyResistant())
    {
      PotentiallyResistantCollection info = new PotentiallyResistantCollection();
      info.throwIt();
    }
    else if (this.isResistant())
    {
      ResistantCollection info = new ResistantCollection();
      info.throwIt();
    }
  }

  protected boolean isResistant()
  {
    Integer resistant = Property.getInt(PropertyInfo.RESISTANCE_PACKAGE, PropertyInfo.ADULT_DDA_RESISTANCE);

    return ( this.getMortality() < resistant );
  }

  protected boolean isPotentiallyResistant()
  {
    Integer susceptible = Property.getInt(PropertyInfo.RESISTANCE_PACKAGE, PropertyInfo.ADULT_DDA_SUSCEPTIBILE);
    Integer resistant = Property.getInt(PropertyInfo.RESISTANCE_PACKAGE, PropertyInfo.ADULT_DDA_RESISTANCE);

    return ( resistant < this.getMortality() && this.getMortality() <= susceptible );
  }

  protected boolean isSusceptible()
  {
    Integer susceptible = Property.getInt(PropertyInfo.RESISTANCE_PACKAGE, PropertyInfo.ADULT_DDA_SUSCEPTIBILE);

    return ( this.getMortality() > susceptible );
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   *
   * @param xml
   * @return
   */
  private static ValueQuery xmlToValueQuery(String xml, String[] selectedUniversals, boolean includeGeometry, ThematicLayer thematicLayer)
  {

    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, thematicLayer, includeGeometry, selectedUniversals, MosquitoCollection.CLASS, MosquitoCollection.GEOENTITY);

    // join Mosquito with mosquito collection
    MosquitoCollectionQuery mosquitoCollectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);

    CollectionAssayQuery joinResults = null;
    String joinTable = null;

    // join Mosquito with mosquito collection
    AdultDiscriminatingDoseAssayQuery adultQuery = (AdultDiscriminatingDoseAssayQuery) queryMap.get(AdultDiscriminatingDoseAssay.CLASS);
    if (adultQuery != null)
    {
      valueQuery.WHERE(adultQuery.getCollection().getId().EQ(mosquitoCollectionQuery.getId()));
      joinResults = adultQuery;
      // joinTable = adultQuery.getMdClassIF().getTableName();
    }

    LarvaeDiscriminatingDoseAssayQuery larvaeQuery = (LarvaeDiscriminatingDoseAssayQuery) queryMap.get(LarvaeDiscriminatingDoseAssay.CLASS);
    if (larvaeQuery != null)
    {
      valueQuery.WHERE(larvaeQuery.getCollection().getId().EQ(mosquitoCollectionQuery.getId()));
      joinResults = larvaeQuery;
    }

    KnockDownAssayQuery kdQuery = (KnockDownAssayQuery) queryMap.get(KnockDownAssay.CLASS);
    if (kdQuery != null)
    {
      valueQuery.WHERE(kdQuery.getCollection().getId().EQ(mosquitoCollectionQuery.getId()));
      joinResults = kdQuery;
    }

    CollectionAssayQuery collectionAssayQuery = (CollectionAssayQuery) queryMap.get(CollectionAssay.CLASS);
    if (collectionAssayQuery != null)
    {
      valueQuery.WHERE(collectionAssayQuery.getCollection().getId().EQ(mosquitoCollectionQuery.getId()));
      joinResults = collectionAssayQuery;
    }

    SelectableMoment dateAttribute = mosquitoCollectionQuery.getDateCollected();

    // this ensures that the date attribute is joined correctly
    ConcreteMosquitoCollectionQuery concreteCollectionQuery = (ConcreteMosquitoCollectionQuery) queryMap.get(ConcreteMosquitoCollection.CLASS);
    if (concreteCollectionQuery == null)
    {
      for (Join join : dateAttribute.getJoinStatements())
      {
        valueQuery.WHERE((InnerJoin) join);
      }
    }

    String susceptibleLabel = "Susceptible";
    String resistantLabel = "Resistant";
    String potentialyResistantLabel = "Potentially Resistant";
    String tableName = "resistance_table";

    String result = "resistance_result";

    if(xml.indexOf(">"+result+"<") > 0)
    {
      SelectableSQL s = (SelectableSQL) valueQuery.getSelectable(result);
      s.setSQL(result);

      String[] labels = { susceptibleLabel, potentialyResistantLabel, resistantLabel };
      String sql = AdultDiscriminatingDoseAssay.getTempTableSQL(tableName, labels);
      System.out.println(sql);
      Database.parseAndExecute(sql);
      valueQuery.FROM(tableName, tableName);
      valueQuery.WHERE(new InnerJoinEq("id", joinResults.getMdClassIF().getTableName(), joinResults.getTableAlias(), "id", tableName, tableName));
    }

    valueQuery = QueryUtil.setQueryDates(xml, valueQuery, dateAttribute);
    valueQuery = QueryUtil.setQueryRatio(xml, valueQuery, "COUNT(*)");
    return valueQuery;

  }

  public static String getTempTableSQL(String tableName, String[] labels)
  {
    String sql = "DROP TABLE IF EXISTS " + tableName + ";\n";
    sql += "CREATE TEMP TABLE " + tableName + " AS ";
    sql += AdultDiscriminatingDoseAssay.getResistanceSQL(labels);
    sql += " UNION \n";
    sql += LarvaeDiscriminatingDoseAssay.getResistanceSQL(labels);
    sql += " UNION \n";
    sql += KnockDownAssay.getResistanceSQL(labels);
    sql += ";\n";
    return sql;
  }

  public static String getResistanceSQL(String[] labels)
  {
    String assayTable = MdBusiness.getMdBusiness(AdultDiscriminatingDoseAssay.CLASS).getTableName();
    Integer resistant = Property.getInt(PropertyInfo.RESISTANCE_PACKAGE, PropertyInfo.ADULT_DDA_RESISTANCE);
    Integer susceptible = Property.getInt(PropertyInfo.RESISTANCE_PACKAGE, PropertyInfo.ADULT_DDA_SUSCEPTIBILE);
    String mortality = AdultDiscriminatingDoseAssay.MORTALITY;

    return CollectionAssay.getCollectionResistanceSQL(assayTable, mortality, resistant.toString(), susceptible.toString(), labels);
  }

  /**
   * Queries for Mosquitos.
   *
   * @param xml
   */
  @Transaction
  @Authenticate
  public static com.terraframe.mojo.query.ValueQuery queryResistance(String queryXML, String config, String sortBy, Boolean ascending, Integer pageNumber, Integer pageSize)
  {
    QueryConfig queryConfig = new QueryConfig(config);
    String[] selectedUniversals = queryConfig.getSelectedUniversals();

    ValueQuery valueQuery = xmlToValueQuery(queryXML, selectedUniversals, false, null);

    valueQuery.restrictRows(pageSize, pageNumber);

    System.out.println(valueQuery.getSQL());

    return valueQuery;
  }

  @Transaction
  public static String mapQuery(String xml, String config, String[] universalLayers, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot map a query without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);
    QueryConfig queryConfig = new QueryConfig(config);

    ThematicLayer thematicLayer = search.getThematicLayer();

    if (thematicLayer == null || thematicLayer.getGeoHierarchy() == null)
    {
      String error = "Cannot create a map for search [" + search.getQueryName()
          + "] without having selected a thematic layer.";
      NoThematicLayerException ex = new NoThematicLayerException(error);
      throw ex;
    }

    // Update ThematicLayer if the thematic layer type has changed or
    // if one has not yet been defined.
    String thematicLayerType = thematicLayer.getGeoHierarchy().getGeoEntityClass().definesType();
    if (thematicLayer.getGeometryStyle() == null
        || !thematicLayer.getGeoHierarchy().getQualifiedType().equals(thematicLayerType))
    {
      thematicLayer.changeLayerType(thematicLayerType);
    }

    String[] selectedUniversals = queryConfig.getSelectedUniversals();
    ValueQuery query = xmlToValueQuery(xml, selectedUniversals, true, thematicLayer);

    System.out.println(query.getSQL());

    String layers = MapUtil.generateLayers(universalLayers, query, search, thematicLayer);

    return layers;
  }


  @Transaction
  public static InputStream exportQueryToExcel(String queryXML, String config, String savedSearchId)
  {
    QueryConfig queryConfig = new QueryConfig(config);
    String[] selectedUniversals = queryConfig.getSelectedUniversals();

    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to Excel without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);

    ValueQuery query = xmlToValueQuery(queryXML, selectedUniversals, false, null);

    ValueQueryExcelExporter exporter = new ValueQueryExcelExporter(query, search.getQueryName());
    return exporter.exportStream();
  }

  @Transaction
  public static InputStream exportQueryToCSV(String queryXML, String config, String savedSearchId)
  {
    QueryConfig queryConfig = new QueryConfig(config);
    String[] selectedUniversals = queryConfig.getSelectedUniversals();

    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to CSV without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    ValueQuery query = xmlToValueQuery(queryXML, selectedUniversals, false, null);

    ValueQueryCSVExporter exporter = new ValueQueryCSVExporter(query);
    return exporter.exportStream();
  }
}
