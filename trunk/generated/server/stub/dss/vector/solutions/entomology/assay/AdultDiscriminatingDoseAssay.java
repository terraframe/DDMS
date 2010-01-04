package dss.vector.solutions.entomology.assay;

import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.InnerJoin;
import com.terraframe.mojo.query.InnerJoinEq;
import com.terraframe.mojo.query.Join;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectableMoment;
import com.terraframe.mojo.query.SelectableSQL;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.entomology.ControlMortalityException;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.ontology.AllPathsQuery;
import dss.vector.solutions.query.Layer;
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
  @Authenticate
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer)
  {
    JSONObject queryConfig;
    try
    {
      queryConfig = new JSONObject(config);
    }
    catch (JSONException e1)
    {
      throw new ProgrammingErrorException(e1);
    }
    
    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, queryConfig, layer);

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

    SelectableMoment dateAttribute = mosquitoCollectionQuery.getCollectionDate();

    // this ensures that the date attribute is joined correctly
    MosquitoCollectionQuery concreteCollectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);
    if (concreteCollectionQuery == null)
    {
      for (Join join : dateAttribute.getJoinStatements())
      {
        valueQuery.WHERE((InnerJoin) join);
      }
    }
    for(Entry<String, GeneratedEntityQuery> e : queryMap.entrySet()) {
      if (e.getValue() instanceof AllPathsQuery)
      {
        String key = e.getKey();
        AllPathsQuery allPathsQuery = (AllPathsQuery) e.getValue();
        
        int index1 = key.indexOf("__");
        int index2 = key.lastIndexOf("__");
        String attrib = key.substring(0, index1);
        String klass = key.substring(index1+2, index2).replace("_", ".");
        String attrib2 = key.substring(index2+2,key.length());
        Selectable term  = valueQuery.getSelectable(attrib2);
        
        
        valueQuery.WHERE(new InnerJoinEq("id", term.getDefiningTableName(), term.getDefiningTableAlias(), "childTerm", allPathsQuery.getMdClassIF().getTableName(), allPathsQuery.getTableAlias()));
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

}
