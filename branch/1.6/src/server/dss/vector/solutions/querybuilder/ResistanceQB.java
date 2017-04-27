package dss.vector.solutions.querybuilder;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.Reloadable;
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
import com.runwaysdk.query.SelectableSQLDouble;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.entomology.ResistanceProperty;
import dss.vector.solutions.entomology.assay.AbstractAssay;
import dss.vector.solutions.entomology.assay.AbstractAssayQuery;
import dss.vector.solutions.entomology.assay.AdultAssay;
import dss.vector.solutions.entomology.assay.AdultAssayQuery;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery;
import dss.vector.solutions.entomology.assay.CollectionAssay;
import dss.vector.solutions.entomology.assay.CollectionAssayQuery;
import dss.vector.solutions.entomology.assay.KnockDownAssay;
import dss.vector.solutions.entomology.assay.KnockDownAssayQuery;
import dss.vector.solutions.entomology.assay.LarvaeAssay;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQuery;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.general.InsecticideQuery;
import dss.vector.solutions.localization.MultiBundle;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.util.QueryUtil;

public class ResistanceQB extends AbstractQB implements Reloadable
{

  public ResistanceQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageSize, pageSize, disease);
  }

  @Override
  protected String getAuditClassAlias()
  {
    return MosquitoCollection.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    this.prepareQueryMap(queryFactory, valueQuery, queryMap);

    // join Mosquito with mosquito collection
    MosquitoCollectionQuery mosquitoCollectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);
    if (mosquitoCollectionQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery, this.getTermRestrictions(), this.getLayer());
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

      // manually put MosquitoCollectionQuery into the query map so
      // QueryUtil.setQueryDates() can
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
      QueryUtil.joinTermAllpaths(valueQuery, AbstractAssay.CLASS, abstractAssayQuery, this.getTermRestrictions(), this.getLayer());
    }

    if (collectionAssayQuery != null)
    {
      String alias = collectionAssayQuery.getIdentificationMethod().getDefiningTableAlias();
      
      QueryUtil.joinTermAllpaths(valueQuery, CollectionAssay.CLASS, alias, this.getTermRestrictions(), this.getLayer());
    }

    // We must force join on CollectionAssay to avoid mixing results with
    // EfficacyAssay which also
    // extends AbstractAssay.
    if (collectionAssayQuery == null)
    {
      collectionAssayQuery = new CollectionAssayQuery(valueQuery);
    }
    valueQuery.WHERE(abstractAssayQuery.getId().EQ(collectionAssayQuery.getId()));

    InsecticideQuery insecticideQuery = (InsecticideQuery) queryMap.get(Insecticide.CLASS);
    if (insecticideQuery != null)
    {
      valueQuery.WHERE(collectionAssayQuery.getInsecticide().EQ(insecticideQuery));
      QueryUtil.joinTermAllpaths(valueQuery, Insecticide.CLASS, insecticideQuery, this.getTermRestrictions(), this.getLayer());
    }

    // join Mosquito with mosquito collection
    CollectionAssayQuery joinResults = null;
    if (adultQuery != null || kdQuery != null)
    {
      joinResults = adultQuery != null ? adultQuery : kdQuery;

      if (adultAssayQuery == null)
      {
        adultAssayQuery = new AdultAssayQuery(valueQuery);
      }

      // force these joins to avoid a cross-product caused by
      // QueryUtil.joinTermAllPaths.
      valueQuery.WHERE(abstractAssayQuery.getId().EQ(adultAssayQuery.getId()));
      valueQuery.WHERE(joinResults.getId().EQ(adultAssayQuery.getId()));

      boolean found = QueryUtil.joinTermAllpaths(valueQuery, AdultAssay.CLASS, adultAssayQuery, this.getTermRestrictions(), this.getLayer());
      if (found)
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
      QueryUtil.joinTermAllpaths(valueQuery, LarvaeAssay.CLASS, larvaeQuery.getStartPoint().getDefiningTableAlias(), this.getTermRestrictions(), this.getLayer());
    }
    else
    {
      joinResults = collectionAssayQuery;
    }

    this.setNumericRestrictions(valueQuery, queryConfig);

    if (joinResults != null)
    {
      valueQuery.WHERE(joinResults.getCollection().EQ(mosquitoCollectionQuery));
      valueQuery.WHERE(abstractAssayQuery.getId().EQ(joinResults.getId()));
    }

    String susceptibleLabel = "Susceptible";
    String resistantLabel = "Resistant";
    String potentialyResistantLabel = "Potentially Resistant";
    String tableName = "resistance_table";

    String result = "resistance_result";

    for (Selectable selectable : valueQuery.getSelectableRefs())
    {
      if (selectable.getColumnAlias().equals(result))
      {
        ( (SelectableSQL) selectable ).setSQL(result);

        String id = QueryUtil.getIdColumn();

        String[] labels = { susceptibleLabel, potentialyResistantLabel, resistantLabel };
        this.addWITHEntry(new WITHEntry(tableName, this.getResistanceQuerySQL(labels)));
        // valueQuery.setSqlPrefix(this.getResistanceWithQuerySQL(tableName,
        // labels));
        valueQuery.FROM(tableName, tableName);
        valueQuery.WHERE(new RawLeftJoinEq(id, joinResults.getMdClassIF().getTableName(), joinResults.getTableAlias(), id, tableName, tableName));
      }
    }
    // Type discriminator
    if (valueQuery.hasSelectableRef(QueryConstants.ASSAY_TYPE))
    {
      valueQuery.FROM(abstractAssayQuery.getMdClassIF().getTableName(), abstractAssayQuery.getTableAlias()); // ensure
                                                                                                             // AbstractAssay
                                                                                                             // is
                                                                                                             // included
      SelectableSQLCharacter sel = (SelectableSQLCharacter) valueQuery.getSelectableRef(QueryConstants.ASSAY_TYPE);

      String typeCol = abstractAssayQuery.getTableAlias() + "." + QueryUtil.getColumnName(AbstractAssay.getTypeMd());
      String sql = "CASE \n";
      sql += "WHEN " + typeCol + " = '" + AdultDiscriminatingDoseAssay.CLASS + "' THEN '" + MultiBundle.get("adult_diagnostic") + "' \n";
      sql += "WHEN " + typeCol + " = '" + LarvaeDiscriminatingDoseAssay.CLASS + "' THEN '" + MultiBundle.get("larval_diagnostic") + "' \n";
      sql += "ELSE '" + MultiBundle.get("adult_time_response") + "' \n";
      sql += " END";
      sel.setSQL(sql);

      if (queryConfig.has(QueryConstants.ASSAY_TYPE))
      {
        try
        {
          JSONArray types = queryConfig.getJSONArray(QueryConstants.ASSAY_TYPE);
          if (types.length() > 0)
          {
            String[] typesCrit = new String[types.length()];
            for (int i = 0; i < types.length(); i++)
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

    if (valueQuery.hasSelectableRef(QueryConstants.OBSERVED_MORTALITY))
    {
      Selectable sel = valueQuery.getSelectableRef(QueryConstants.OBSERVED_MORTALITY);
      SelectableSQLDouble overall = (SelectableSQLDouble) sel;

      String dead = adultQuery.getTableAlias() + "." + QueryUtil.getColumnName(AdultDiscriminatingDoseAssay.getQuantityDeadMd());
      String tested = collectionAssayQuery.getTableAlias() + "." + QueryUtil.getColumnName(AdultDiscriminatingDoseAssay.getQuantityTestedMd());

      String sql = "(SUM(" + dead + ") / NULLIF(SUM(" + tested + "),0)::double precision * 100)";

      overall.setSQL(sql);
    }

    if (valueQuery.hasSelectableRef(QueryConstants.CORRECTED_MORTALITY))
    {
      Selectable sel = valueQuery.getSelectableRef(QueryConstants.CORRECTED_MORTALITY);
      SelectableSQLDouble overall = (SelectableSQLDouble) sel;

      String correctedDead = adultQuery.getTableAlias() + "." + QueryUtil.getColumnName(AdultDiscriminatingDoseAssay.getCorrectedQuantityDeadMd());
      String tested = collectionAssayQuery.getTableAlias() + "." + QueryUtil.getColumnName(AdultDiscriminatingDoseAssay.getQuantityTestedMd());

      String sql = "(SUM(" + correctedDead + ") / NULLIF(SUM(" + tested + "),0)::double precision * 100)";

      overall.setSQL(sql);
    }

    // Ticket 3200. This kind of a hack, but basically we just want to make sure setQueryDates uses the AbstractAssay and not any of the subclasses.
    GeneratedEntityQuery colAs = queryMap.get(CollectionAssay.CLASS);
    GeneratedEntityQuery adultAs = queryMap.get(AdultAssay.CLASS);
    GeneratedEntityQuery adultDisc = queryMap.get(AdultDiscriminatingDoseAssay.CLASS);
    if (valueQuery.hasSelectableRef(QueryConstants.OBSERVED_MORTALITY) || valueQuery.hasSelectableRef(QueryConstants.CORRECTED_MORTALITY))
    {
      queryMap.remove(CollectionAssay.CLASS);
      queryMap.remove(AdultAssay.CLASS);
      queryMap.remove(AdultDiscriminatingDoseAssay.CLASS);
    }
    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, mosquitoCollectionQuery.getDisease());
    if (valueQuery.hasSelectableRef(QueryConstants.OBSERVED_MORTALITY) || valueQuery.hasSelectableRef(QueryConstants.CORRECTED_MORTALITY))
    {
      queryMap.put(CollectionAssay.CLASS, colAs);
      queryMap.put(AdultAssay.CLASS, adultAs);
      queryMap.put(AdultDiscriminatingDoseAssay.CLASS, adultDisc);
    }
    
    return valueQuery;

  }

  private void prepareQueryMap(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap)
  {
    if (valueQuery.hasSelectableRef(QueryConstants.OBSERVED_MORTALITY) || valueQuery.hasSelectableRef(QueryConstants.CORRECTED_MORTALITY))
    {
      if (!queryMap.containsKey(AbstractAssay.CLASS))
      {
        queryMap.put(AbstractAssay.CLASS, new AbstractAssayQuery(queryFactory));
      }

      if (!queryMap.containsKey(CollectionAssay.CLASS))
      {
        queryMap.put(CollectionAssay.CLASS, new CollectionAssayQuery(queryFactory));
      }

      if (!queryMap.containsKey(AdultAssay.CLASS))
      {
        queryMap.put(AdultAssay.CLASS, new AdultAssayQuery(queryFactory));
      }

      if (!queryMap.containsKey(AdultDiscriminatingDoseAssay.CLASS))
      {
        queryMap.put(AdultDiscriminatingDoseAssay.CLASS, new AdultDiscriminatingDoseAssayQuery(queryFactory));
      }
    }
  }

  private String getResistanceQuerySQL(String[] labels)
  {
    String sql = this.getResistanceSQL(labels);
    sql += " UNION \n";
    sql += LarvaeDiscriminatingDoseAssay.getResistanceSQL(labels);
    sql += " UNION \n";
    sql += KnockDownAssay.getResistanceSQL(labels);
    return sql;
  }

  private String getResistanceSQL(String[] labels)
  {
    String assayTable = MdBusiness.getMdBusiness(AdultDiscriminatingDoseAssay.CLASS).getTableName();
    Integer resistant = ResistanceProperty.getPropertyValue(PropertyInfo.ADULT_DDA_RESISTANCE);
    Integer susceptible = ResistanceProperty.getPropertyValue(PropertyInfo.ADULT_DDA_SUSCEPTIBILE);
    String mortality = AdultDiscriminatingDoseAssay.MORTALITY;

    return CollectionAssay.getCollectionResistanceSQL(assayTable, mortality, resistant.toString(), susceptible.toString(), labels);
  }

}
