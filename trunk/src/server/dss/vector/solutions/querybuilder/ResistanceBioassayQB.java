package dss.vector.solutions.querybuilder;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AggregateFunction;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.entomology.DiagnosticAssay;
import dss.vector.solutions.entomology.DiagnosticAssayQuery;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.entomology.TimeResponseAssay;
import dss.vector.solutions.entomology.TimeResponseAssayQuery;
import dss.vector.solutions.entomology.TimeResponseAssayView;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class ResistanceBioassayQB extends AbstractQB implements Reloadable
{

  public ResistanceBioassayQB(String xml, String config, Layer layer)
  {
    super(xml, config, layer);
  }
  
  @Override
  protected String getAuditClassAlias()
  {
    return MosquitoCollection.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    MosquitoCollectionQuery mosquitoCollectionQuery = (MosquitoCollectionQuery) queryMap
        .get(MosquitoCollection.CLASS);
    DiagnosticAssayQuery diagnosticAssayQuery = (DiagnosticAssayQuery) queryMap
        .get(DiagnosticAssay.CLASS);
    TimeResponseAssayQuery timeResponseQuery = (TimeResponseAssayQuery) queryMap
        .get(TimeResponseAssay.CLASS);

    // We always include mosquito collection because it is required for both
    // time response and diagnostic assays. If it was included explicitely then
    // join on any terms and display labels.
    if (mosquitoCollectionQuery != null)
    {
      this.addGeoDisplayLabelQuery(mosquitoCollectionQuery);
      QueryUtil.joinTermAllpaths(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);
    }
    else
    {
      mosquitoCollectionQuery = new MosquitoCollectionQuery(valueQuery);
    }

    this.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil
        .setQueryDates(xml, valueQuery, queryConfig, queryMap, mosquitoCollectionQuery.getDisease());

    MdEntityDAOIF timeMd = MdEntityDAO.getMdEntityDAO(TimeResponseAssay.CLASS);

    String lifeStageCol = QueryUtil.getColumnName(timeMd, TimeResponseAssay.LIFESTAGE);
    String assayCol = QueryUtil.getColumnName(timeMd, TimeResponseAssay.ASSAY);
    String referenceStrainResultCol = QueryUtil.getColumnName(timeMd,
        TimeResponseAssay.REFERENCESTRAINRESULT);
    String testStrainResult = QueryUtil.getColumnName(timeMd, TimeResponseAssay.TESTSTRAINRESULT);
    String timeResponseTable = null;

    for (Term assay : TermRootCache.getRoots(TimeResponseAssayView.getAssayMd()))
    {
      for (Term lifeStage : TermRootCache.getRoots(TimeResponseAssayView.getLifeStageMd()))
      {
        String stageAmmountCol = QueryUtil.aliasTerms(assay, lifeStage);
        String idStr = assay.getId() + lifeStage.getId();

        if (valueQuery.hasSelectableRef(stageAmmountCol))
        {
          // As a precaution, since the TimeResponseAssay may not have been
          // explicitely specified
          // in the query, we force it here since we KNOW it's needed
          // at this point to gather the terms.
          if (timeResponseQuery == null)
          {
            timeResponseQuery = new TimeResponseAssayQuery(valueQuery);
          }

          timeResponseTable = timeResponseQuery.getTableAlias();
          String sql = "AVG(" + "CASE " + timeResponseTable + "." + assayCol + " || "
              + timeResponseTable + "." + lifeStageCol + " WHEN '" + idStr + "' THEN "
              + timeResponseTable + "." + testStrainResult + " / " + timeResponseTable + "."
              + referenceStrainResultCol + " ELSE NULL END)";

          QueryUtil.setSelectabeSQL(valueQuery, stageAmmountCol, sql);
        }
      }
    }

    if (valueQuery.hasSelectableRef("Resistance_Ratio"))
    {
      SelectableSQL calc;
      Selectable sel = valueQuery.getSelectableRef("Resistance_Ratio");
      if (sel instanceof AggregateFunction)
      {
        calc = (SelectableSQL) ( (AggregateFunction) sel ).getSelectable();
      }
      else
      {
        calc = (SelectableSQL) sel;
      }

      String testCol = QueryUtil.getColumnName(TimeResponseAssay.getTestStrainResultMd());
      String refCol = QueryUtil.getColumnName(TimeResponseAssay.getReferenceStrainResultMd());

      String sql = testCol + "/NULLIF(" + refCol + ",0.0)";
      calc.setSQL(sql);

      // Again, as a precaution, since the TimeResponseAssay may not have been
      // explicitely specified
      // in the query, we force it here since its needed for the ratio
      // calculation.
      if (timeResponseQuery == null)
      {
        timeResponseQuery = new TimeResponseAssayQuery(valueQuery);
      }
    }

    // Join on the MosquitoCollection with the proper assay type. If no assay
    // type is
    // specified then join on both to get the whole set.
    if (diagnosticAssayQuery != null)
    {
      valueQuery.WHERE(diagnosticAssayQuery.getCollection().EQ(mosquitoCollectionQuery.getId()));
      QueryUtil.joinTermAllpaths(valueQuery, DiagnosticAssay.CLASS, diagnosticAssayQuery);
    }
    else if (timeResponseQuery != null)
    {
      valueQuery.WHERE(timeResponseQuery.getCollection().EQ(mosquitoCollectionQuery.getId()));
      QueryUtil.joinTermAllpaths(valueQuery, TimeResponseAssay.CLASS, timeResponseQuery);
    }
    else
    {
      // Top of union
      MosquitoCollectionQuery mosquitoSubSel = new MosquitoCollectionQuery(queryFactory);
      ValueQuery subSelect = new ValueQuery(queryFactory);
      diagnosticAssayQuery = new DiagnosticAssayQuery(subSelect);

      Selectable[] selectables = new Selectable[] { mosquitoSubSel.getId(MosquitoCollection.ID),
          mosquitoSubSel.getCollectionId(MosquitoCollection.COLLECTIONID),
          mosquitoSubSel.getGeoEntity(MosquitoCollection.GEOENTITY),
          mosquitoSubSel.getCollectionDate(MosquitoCollection.COLLECTIONDATE),
          mosquitoSubSel.getCollectionMethod(MosquitoCollection.COLLECTIONMETHOD) };
      setColumnAsAttribute(selectables);
      subSelect.SELECT(selectables);
      subSelect.WHERE(diagnosticAssayQuery.getCollection().EQ(mosquitoSubSel.getId()));

      // Bottom of union2
      MosquitoCollectionQuery mosquitoSubSel2 = new MosquitoCollectionQuery(queryFactory);
      ValueQuery subSelect2 = new ValueQuery(queryFactory);
      timeResponseQuery = new TimeResponseAssayQuery(subSelect);

      Selectable[] selectables2 = new Selectable[] { mosquitoSubSel2.getId(MosquitoCollection.ID),
          mosquitoSubSel2.getCollectionId(MosquitoCollection.COLLECTIONID),
          mosquitoSubSel2.getGeoEntity(MosquitoCollection.GEOENTITY),
          mosquitoSubSel2.getCollectionDate(MosquitoCollection.COLLECTIONDATE),
          mosquitoSubSel2.getCollectionMethod(MosquitoCollection.COLLECTIONMETHOD) };
      setColumnAsAttribute(selectables2);
      subSelect2.SELECT(selectables2);
      subSelect2.WHERE(timeResponseQuery.getCollection().EQ(mosquitoSubSel2));

      ValueQuery unioned = new ValueQuery(queryFactory);
      unioned.UNION_ALL(subSelect, subSelect2);

      valueQuery.FROM("(" + unioned.getSQL() + ")", mosquitoCollectionQuery.getTableAlias());
    }

    return valueQuery;
  }

  private static void setColumnAsAttribute(Selectable... selectables)
  {
    for (Selectable sel : selectables)
    {
      sel.setColumnAlias(sel.getDbColumnName());
    }
  }

}
