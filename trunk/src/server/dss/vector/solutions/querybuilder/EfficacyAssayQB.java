package dss.vector.solutions.querybuilder;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AggregateFunction;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.InnerJoin;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.Join;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableMoment;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.SelectableSQLDate;
import com.runwaysdk.query.SelectableSQLDouble;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.entomology.assay.AbstractAssay;
import dss.vector.solutions.entomology.assay.AbstractAssayQuery;
import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.AdultAgeRangeQuery;
import dss.vector.solutions.entomology.assay.EfficacyAssay;
import dss.vector.solutions.entomology.assay.EfficacyAssayQuery;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.InsecticideBrandQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.util.QueryUtil;

public class EfficacyAssayQB extends AbstractQB implements Reloadable
{

  public EfficacyAssayQB(String xml, String config, Layer layer)
  {
    super(xml, config, layer);
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    EfficacyAssayQuery efficacyAssayQuery = (EfficacyAssayQuery) queryMap.get(EfficacyAssay.CLASS);

    AbstractAssayQuery abstractAssayQuery = (AbstractAssayQuery) queryMap.get(AbstractAssay.CLASS);

    if (efficacyAssayQuery != null) {
      QueryUtil.joinTermAllpaths(valueQuery, EfficacyAssay.CLASS, efficacyAssayQuery);

      // There are terms defined on the parent class as well, so grab
      QueryUtil.joinTermAllpaths(valueQuery, AbstractAssay.CLASS, efficacyAssayQuery.getSpecie().getDefiningTableAlias());
      if (abstractAssayQuery != null) {
        valueQuery.WHERE(abstractAssayQuery.getId().EQ(efficacyAssayQuery.getId()));
      } else {
        // ensure date is allways joined
        SelectableMoment dateAttribute = efficacyAssayQuery.getTestDate();
        for (Join join : dateAttribute.getJoinStatements()) {
          valueQuery.WHERE((InnerJoin) join);
        }
      }

      this.addGeoDisplayLabelQuery(efficacyAssayQuery);
    }

    InsecticideBrandQuery insecticideBrandQuery = (InsecticideBrandQuery) queryMap.get(InsecticideBrand.CLASS);

    if (insecticideBrandQuery != null) {
      valueQuery.WHERE(efficacyAssayQuery.getInsecticideBrand().EQ(insecticideBrandQuery));

      QueryUtil.joinEnumerationDisplayLabels(valueQuery, InsecticideBrand.CLASS, insecticideBrandQuery);
      QueryUtil.joinTermAllpaths(valueQuery, InsecticideBrand.CLASS, insecticideBrandQuery);
    }
    
    if(valueQuery.hasSelectableRef(QueryConstants.OVERALL_MORTALITY))
    {
      Selectable sel = valueQuery.getSelectableRef(QueryConstants.OVERALL_MORTALITY);
      SelectableSQLDouble overall;
      if(sel.isAggregateFunction())
      {
        overall = (SelectableSQLDouble) ((AggregateFunction) sel).getSelectable();
      }
      else
      {
        overall = (SelectableSQLDouble) sel;
      }
      
      String controlTestMortality = QueryUtil.getColumnName(EfficacyAssay.getControlTestMortalityMd());
      String quantityDead = QueryUtil.getColumnName(EfficacyAssay.getQuantityDeadMd());
      String quantityLive = QueryUtil.getColumnName(EfficacyAssay.getQuantityLiveMd());
      String geoEntity = QueryUtil.getColumnName(EfficacyAssay.getGeoEntityMd());
      String testDate = QueryUtil.getColumnName(EfficacyAssay.getTestDateMd());
      String id = QueryUtil.getIdColumn();

      String sql = "((qd / NULLIF((qd + ql),0)::double precision * 100 - "+efficacyAssayQuery.getTableAlias()+"."+controlTestMortality+")";
      sql+= "/NULLIF((100 - "+efficacyAssayQuery.getTableAlias()+"."+controlTestMortality+"),0) * 100)";
      overall.setSQL(sql);
      
      String from = "(SELECT SUM("+quantityDead+") AS qd, SUM("+quantityLive+") ql, e."+geoEntity+", a."+testDate+"\n";
      from += "FROM "+efficacyAssayQuery.getMdClassIF().getTableName()+" AS e\n";
      from += "INNER JOIN "+abstractAssayQuery.getMdClassIF().getTableName()+" a ON a."+id+" = e."+id+"\n";
      from += "GROUP BY e."+geoEntity+", a."+testDate+")\n";

      valueQuery.FROM("("+from+")", "overall");
      
      SelectableSQLCharacter overallGeoEntity = valueQuery.aSQLCharacter("overall_geo_entity", "overall."+geoEntity);
      valueQuery.WHERE(efficacyAssayQuery.getGeoEntity().EQ(overallGeoEntity));    

      SelectableSQLDate overallTestDate = valueQuery.aSQLDate("overall_test_date", "overall."+testDate);
      valueQuery.WHERE(efficacyAssayQuery.getTestDate().EQ(overallTestDate));    
    }
    
    boolean needsAgeRange = false;
    if(valueQuery.hasSelectableRef(QueryConstants.AGE_LOWEST))
    {
       needsAgeRange = true; 
       SelectableSQLInteger ageSel;
       Selectable sel = valueQuery.getSelectableRef(QueryConstants.AGE_LOWEST);
       if(sel instanceof AggregateFunction)
       {
         ageSel = (SelectableSQLInteger) ((AggregateFunction)sel).getSelectable();
       }
       else
       {
         ageSel = (SelectableSQLInteger) sel;
       }
       ageSel.setSQL(QueryUtil.getColumnName(AdultAgeRange.getStartPointMd()));
    }
    
    if(valueQuery.hasSelectableRef(QueryConstants.AGE_HIGHEST))
    {
       needsAgeRange = true;
        SelectableSQLInteger ageSel;
        Selectable sel = valueQuery.getSelectableRef(QueryConstants.AGE_HIGHEST);
        if(sel instanceof AggregateFunction)
        {
          ageSel = (SelectableSQLInteger) ((AggregateFunction)sel).getSelectable();
        }
        else
        {
          ageSel = (SelectableSQLInteger) sel;
        }
        ageSel.setSQL(QueryUtil.getColumnName(AdultAgeRange.getEndPointMd()));
    }
    
    if(needsAgeRange)
    {
      AdultAgeRangeQuery arQuery = new AdultAgeRangeQuery(valueQuery);
      valueQuery.FROM(arQuery);
      valueQuery.WHERE(new InnerJoinEq(efficacyAssayQuery.getAgeRange(), arQuery.getId()));
    }

    this.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, efficacyAssayQuery.getDisease());

    return valueQuery;
  }

}
