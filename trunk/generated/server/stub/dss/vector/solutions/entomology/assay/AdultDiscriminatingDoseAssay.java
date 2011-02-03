package dss.vector.solutions.entomology.assay;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.InnerJoin;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.Join;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.RawLeftJoinEq;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableMoment;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.entomology.ControlMortalityException;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.entomology.ResistanceProperty;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.general.InsecticideQuery;
import dss.vector.solutions.localization.MultiBundle;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.util.QueryUtil;

public class AdultDiscriminatingDoseAssay extends AdultDiscriminatingDoseAssayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234543769104L;

  public AdultDiscriminatingDoseAssay()
  {
    super();
  }
  
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else if(this.getCollection() != null && this.getInsecticide() != null)
    {
      return "(" + this.getCollection().getCollectionId() + ", " + this.getInsecticide().toString() + ")";
    }
    
    return super.toString();
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

    if (this.isResistant())
    {
      Term activeIngredient = this.getInsecticide().getActiveIngredient();
      String label = activeIngredient.getTermDisplayLabel().getValue(Session.getCurrentLocale());
      String collectionId = this.getCollection().getCollectionId();

      ResistantCollection info = new ResistantCollection();
      info.setActiveIngredient(label);
      info.setCollectionId(collectionId);
      info.throwIt();
    }
  }

  protected boolean isResistant()
  {
    Integer resistant = ResistanceProperty.getPropertyValue(PropertyInfo.ADULT_DDA_RESISTANCE);

    return ( this.getMortality() < resistant );
  }

  protected boolean isPotentiallyResistant()
  {
    Integer susceptible = ResistanceProperty.getPropertyValue(PropertyInfo.ADULT_DDA_SUSCEPTIBILE);
    Integer resistant = ResistanceProperty.getPropertyValue(PropertyInfo.ADULT_DDA_RESISTANCE);

    return ( resistant < this.getMortality() && this.getMortality() <= susceptible );
  }

  protected boolean isSusceptible()
  {
    Integer susceptible = ResistanceProperty.getPropertyValue(PropertyInfo.ADULT_DDA_SUSCEPTIBILE);
    
    return ( this.getMortality() > susceptible );
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
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
    if (mosquitoCollectionQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);
    }
    else
    {
      // this ensures that the date attribute is joined correctly
      mosquitoCollectionQuery = new MosquitoCollectionQuery(valueQuery);
      SelectableMoment dateAttribute = mosquitoCollectionQuery.getCollectionDate();
      for (Join join : dateAttribute.getJoinStatements())
      {
        valueQuery.WHERE((InnerJoin) join);
      }
      
      // manually put MosquitoCollectionQuery into the query map so QueryUtil.setQueryDates() can
      // correctly find any date attributes on that class. THIS IS A HACK.
      queryMap.put(MosquitoCollection.CLASS, mosquitoCollectionQuery);
    }
    
    AbstractAssayQuery abstractAssayQuery = (AbstractAssayQuery) queryMap.get(AbstractAssay.CLASS);
    CollectionAssayQuery collectionAssayQuery = (CollectionAssayQuery) queryMap.get(CollectionAssay.CLASS);
    AdultAssayQuery adultAssayQuery = (AdultAssayQuery) queryMap.get(AdultAssay.CLASS);
    
    AdultDiscriminatingDoseAssayQuery adultQuery = (AdultDiscriminatingDoseAssayQuery) queryMap.get(AdultDiscriminatingDoseAssay.CLASS);
    LarvaeDiscriminatingDoseAssayQuery larvaeQuery = (LarvaeDiscriminatingDoseAssayQuery) queryMap.get(LarvaeDiscriminatingDoseAssay.CLASS);
    KnockDownAssayQuery kdQuery = (KnockDownAssayQuery) queryMap.get(KnockDownAssay.CLASS);

    if (abstractAssayQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, AbstractAssay.CLASS, abstractAssayQuery);
    }
    
    if(collectionAssayQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, CollectionAssay.CLASS,  collectionAssayQuery.getIdentificationMethod().getDefiningTableAlias());
    }

    // We must force join on CollectionAssay to avoid mixing results with EfficacyAssay which also
    // extends AbstractAssay.
    if(collectionAssayQuery == null)
    {
      collectionAssayQuery = new CollectionAssayQuery(valueQuery);
    }
    valueQuery.WHERE(abstractAssayQuery.getId().EQ(collectionAssayQuery.getId()));
    
    
    InsecticideQuery insecticideQuery = (InsecticideQuery) queryMap.get(Insecticide.CLASS);
    if (insecticideQuery != null)
    {
      valueQuery.WHERE(collectionAssayQuery.getInsecticide().EQ(insecticideQuery));
      QueryUtil.joinTermAllpaths(valueQuery, Insecticide.CLASS, insecticideQuery);
    }
    

    // join Mosquito with mosquito collection
    CollectionAssayQuery joinResults = null;
    if (adultQuery != null || kdQuery != null)
    {
      joinResults = adultQuery != null ? adultQuery : kdQuery;
      
      if(adultAssayQuery == null)
      {
        adultAssayQuery = new AdultAssayQuery(valueQuery);
      }
      
      // force these joins to avoid a cross-product caused by QueryUtil.joinTermAllPaths.
      valueQuery.WHERE(abstractAssayQuery.getId().EQ(adultAssayQuery.getId()));
      valueQuery.WHERE(joinResults.getId().EQ(adultAssayQuery.getId()));
      
      boolean found = QueryUtil.joinTermAllpaths(valueQuery, AdultAssay.CLASS, adultAssayQuery);
      if(found)
      {
        String id = QueryUtil.getIdColumn();
        String table = adultAssayQuery.getMdClassIF().getTableName();
        String alias = adultQuery != null ? adultQuery.getSex().getDefiningTableAlias() : kdQuery.getSex().getDefiningTableAlias();
        valueQuery.WHERE(new InnerJoinEq(id, table, adultAssayQuery.getTableAlias(), id, table, alias));
      }
    }
    else if (larvaeQuery != null)
    {
      joinResults = larvaeQuery;
      QueryUtil.joinTermAllpaths(valueQuery, LarvaeAssay.CLASS, larvaeQuery.getStartPoint().getDefiningTableAlias());
    }
    else
    {
      joinResults = collectionAssayQuery;
    }

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    if(joinResults!= null)
    {
      valueQuery.WHERE(joinResults.getCollection().EQ(mosquitoCollectionQuery));
      valueQuery.WHERE(abstractAssayQuery.getId().EQ(joinResults.getId()));
    }

    String susceptibleLabel = "Susceptible";
    String resistantLabel = "Resistant";
    String potentialyResistantLabel = "Potentially Resistant";
    String tableName = "resistance_table";

    String result = "resistance_result";

    for(Selectable selectable : valueQuery.getSelectableRefs())
    {
      if(selectable.getColumnAlias().equals(result))
      {
        ((SelectableSQL) selectable).setSQL(result);
        
        String id = QueryUtil.getIdColumn();
        
        String[] labels = { susceptibleLabel, potentialyResistantLabel, resistantLabel };
        valueQuery.setSqlPrefix(AdultDiscriminatingDoseAssay.getResistanceWithQuerySQL(tableName, labels));
        valueQuery.FROM(tableName, tableName);
        valueQuery.WHERE(new RawLeftJoinEq(id, joinResults.getMdClassIF().getTableName(), joinResults.getTableAlias(), id, tableName, tableName));
      }
    }
    // Type discriminator
    if(valueQuery.hasSelectableRef(QueryConstants.ASSAY_TYPE))
    {
      valueQuery.FROM(abstractAssayQuery.getMdClassIF().getTableName(), abstractAssayQuery.getTableAlias()); // ensure AbstractAssay is included
      SelectableSQLCharacter sel = (SelectableSQLCharacter) valueQuery.getSelectableRef(QueryConstants.ASSAY_TYPE);
          
      String typeCol = abstractAssayQuery.getTableAlias()+"."+QueryUtil.getColumnName(AbstractAssay.getTypeMd());
      String sql = "CASE \n";
      sql += "WHEN "+typeCol+" = '"+AdultDiscriminatingDoseAssay.CLASS+"' THEN '"+MultiBundle.get("adult_diagnostic")+"' \n";
      sql += "WHEN "+typeCol+" = '"+LarvaeDiscriminatingDoseAssay.CLASS+"' THEN '"+MultiBundle.get("larval_diagnostic")+"' \n";
      sql += "ELSE '"+MultiBundle.get("adult_time_response")+"' \n";
      sql += " END";
      sel.setSQL(sql);
      
      if(queryConfig.has(QueryConstants.ASSAY_TYPE))
      {
        try
        {
          JSONArray types = queryConfig.getJSONArray(QueryConstants.ASSAY_TYPE);
          if(types.length() > 0)
          {
            String[] typesCrit = new String[types.length()];
            for(int i=0; i<types.length(); i++)
            {
              typesCrit[i] = types.getString(i);
            }
            
            valueQuery.WHERE(abstractAssayQuery.getType().IN(typesCrit));
          }
        }
        catch (JSONException e)
        {
          throw new ProgrammingErrorException(e);
        }
      }
    }
    
    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, mosquitoCollectionQuery.getDisease());
    return valueQuery;

  }

  public static String getResistanceWithQuerySQL(String tableName, String[] labels)
  {
    String sql = "WITH " + tableName + " AS (";
    sql += AdultDiscriminatingDoseAssay.getResistanceSQL(labels);
    sql += " UNION \n";
    sql += LarvaeDiscriminatingDoseAssay.getResistanceSQL(labels);
    sql += " UNION \n";
    sql += KnockDownAssay.getResistanceSQL(labels);
    sql += ")\n";
    return sql;
  }

  public static String getResistanceSQL(String[] labels)
  {
    String assayTable = MdBusiness.getMdBusiness(AdultDiscriminatingDoseAssay.CLASS).getTableName();
    Integer resistant = ResistanceProperty.getPropertyValue(PropertyInfo.ADULT_DDA_RESISTANCE);
    Integer susceptible = ResistanceProperty.getPropertyValue(PropertyInfo.ADULT_DDA_SUSCEPTIBILE);
    String mortality = AdultDiscriminatingDoseAssay.MORTALITY;

    return CollectionAssay.getCollectionResistanceSQL(assayTable, mortality, resistant.toString(), susceptible.toString(), labels);
  }

}
