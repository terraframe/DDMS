package dss.vector.solutions.entomology;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class TimeResponseAssay extends TimeResponseAssayBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1580911879;

  public TimeResponseAssay()
  {
    super();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }
    super.apply();
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
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory,
        valueQuery, xml, queryConfig, layer);

    MosquitoCollectionQuery mosquitoCollectionQuery = (MosquitoCollectionQuery) queryMap
        .get(MosquitoCollection.CLASS);
    DiagnosticAssayQuery diagnosticAssayQuery = (DiagnosticAssayQuery) queryMap
        .get(DiagnosticAssay.CLASS);

    if (diagnosticAssayQuery != null)
    {
      if (mosquitoCollectionQuery == null)
      {
        mosquitoCollectionQuery = new MosquitoCollectionQuery(queryFactory);
      }

      valueQuery.WHERE(diagnosticAssayQuery.getCollection().EQ(mosquitoCollectionQuery.getId()));
      QueryUtil.joinTermAllpaths(valueQuery, DiagnosticAssay.CLASS, diagnosticAssayQuery);
    }

    TimeResponseAssayQuery timeResponseQuery = (TimeResponseAssayQuery) queryMap
        .get(TimeResponseAssay.CLASS);

    if (timeResponseQuery != null)
    {
      if (mosquitoCollectionQuery == null)
      {
        mosquitoCollectionQuery = new MosquitoCollectionQuery(queryFactory);
      }

      valueQuery.WHERE(timeResponseQuery.getCollection().EQ(mosquitoCollectionQuery.getId()));
      QueryUtil.joinTermAllpaths(valueQuery, TimeResponseAssay.CLASS, timeResponseQuery);
    }

    if (mosquitoCollectionQuery != null)
    {
      QueryUtil.joinGeoDisplayLabels(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);
      QueryUtil.joinTermAllpaths(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);
      // QueryUtil.joinEnumerationDisplayLabels(valueQuery,
      // MosquitoCollection.CLASS, mosquitoCollectionQuery);
    }
    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);

    MdEntityDAOIF timeMd = MdEntityDAO.getMdEntityDAO(TimeResponseAssay.CLASS);

    String lifeStageCol = QueryUtil.getColumnName(timeMd, TimeResponseAssay.LIFESTAGE);
    String assayCol = QueryUtil.getColumnName(timeMd, TimeResponseAssay.ASSAY);
    String referenceStrainResultCol = QueryUtil.getColumnName(timeMd,
        TimeResponseAssay.REFERENCESTRAINRESULT);
    String testStrainResult = QueryUtil.getColumnName(timeMd, TimeResponseAssay.TESTSTRAINRESULT);
    String timeResponseTable = null;

    boolean needsJoin = false;

    for (Term assay : Term.getRootChildren(TimeResponseAssayView.getAssayMd()))
    {

      for (Term lifeStage : Term.getRootChildren(TimeResponseAssayView.getLifeStageMd()))
      {

        String stageAmmountCol = assay.getTermId().replace(":", "")
            + lifeStage.getTermId().replace(":", "");

        needsJoin = valueQuery.hasSelectableRef(stageAmmountCol);

      }
    }

    for (Term assay : Term.getRootChildren(TimeResponseAssayView.getAssayMd()))
    {

      for (Term lifeStage : Term.getRootChildren(TimeResponseAssayView.getLifeStageMd()))
      {

        String stageAmmountCol = assay.getTermId().replace(":", "")
            + lifeStage.getTermId().replace(":", "");
        String idStr = assay.getId() + lifeStage.getId();

        if(valueQuery.hasSelectableRef(stageAmmountCol))
        {
          needsJoin = true;
          
          if(timeResponseQuery == null)
          {
            timeResponseQuery = new TimeResponseAssayQuery(queryFactory);
          }
        
          timeResponseTable = timeResponseQuery.getTableAlias();
          String sql = "AVG(" + "CASE " + timeResponseTable + "." + assayCol + " || " + timeResponseTable
            + "." + lifeStageCol + " WHEN '" + idStr + "' THEN " + timeResponseTable + "."
            + testStrainResult + " / " + timeResponseTable + "." + referenceStrainResultCol
            + " ELSE NULL END)";

          needsJoin = QueryUtil.setSelectabeSQL(valueQuery, stageAmmountCol, sql) || needsJoin;
        }
      }
    }
    
    if(valueQuery.hasSelectableRef("Resistance_Ratio"))
    {
      needsJoin = true;
      
      SelectableSQL calc = (SelectableSQL) valueQuery.getSelectableRef("Resistance_Ratio");
      
      String testCol = QueryUtil.getColumnName(TimeResponseAssay.getTestStrainResultMd());
      String refCol = QueryUtil.getColumnName(TimeResponseAssay.getReferenceStrainResultMd());
      
      String sql = testCol+"/NULLIF("+refCol+",0.0)";
      calc.setSQL(sql);
    }

    if (needsJoin)
    {
      if (timeResponseQuery == null)
      {
        timeResponseQuery = new TimeResponseAssayQuery(valueQuery);
      }

      valueQuery.WHERE(timeResponseQuery.getId().NE("0"));

      if (mosquitoCollectionQuery != null)
      {
        valueQuery.WHERE(timeResponseQuery.getCollection().EQ(mosquitoCollectionQuery.getId()));
      }
    }

    return valueQuery;
  }

}
