package dss.vector.solutions.ontology;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedViewQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ViewQueryBuilder;

import dss.vector.solutions.general.Disease;

public class TermQueryFactory implements Reloadable
{
  private QueryFactory f;

  private String       termId;

  private String       className;

  private String       attribute;

  public TermQueryFactory(QueryFactory f, String termId, String className, String attribute)
  {
    this.f = f;
    this.termId = termId;
    this.className = className;
    this.attribute = attribute;
  }

  public ViewQueryBuilder getBuilder()
  {
    if (attribute != null)
    {
      return new TermByIdQueryBuilder(f, termId, className, attribute);
    }
    // else if(className != null)
    // {
    return new GeoEntityTermQueryBuilder(f, termId, className);
    // }
  }

  private static class TermByIdQueryBuilder extends ViewQueryBuilder implements Reloadable
  {
    private TermQuery        termQuery;

    // AllPaths is used to restrict the query by parent term Ids.
    private AllPathsQuery    pathsQuery;

    private BrowserRootQuery rootQuery             = null;

    private BrowserRootQuery unselectableRootQuery = null;

    private String           termId;

    protected TermByIdQueryBuilder(QueryFactory queryFactory, String termId, String className, String attribute)
    {
      super(queryFactory);

      this.termId = termId;
      this.termQuery = new TermQuery(queryFactory);
      this.pathsQuery = new AllPathsQuery(queryFactory);

      this.rootQuery = BrowserRoot.getAttributeRoots(className, attribute, queryFactory);
      this.unselectableRootQuery = BrowserRoot.getAttributeRoots(className, attribute, queryFactory);
      this.unselectableRootQuery.WHERE(unselectableRootQuery.getSelectable().EQ(false));
    }

    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      query.map(TermView.TERMID, termQuery.getId());
      query.map(TermView.TERMNAME, termQuery.getTermDisplayLabel().localize());
      query.map(TermView.TERMONTOLOGYID, termQuery.getTermId());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      query.WHERE(termQuery.getTermId().EQi(this.termId));

      if (this.rootQuery != null)
      {
        query.AND(this.pathsQuery.getChildTerm().EQ(this.termQuery));
        query.AND(this.pathsQuery.getParentTerm().EQ(rootQuery.getTerm()));

        long count = unselectableRootQuery.getCount();

        if (count > 0)
        {
          query.AND(this.termQuery.getId().NEi(unselectableRootQuery.getTerm().getId()));
        }
      }

      query.AND(Disease.getInactiveCriteria(this.getQueryFactory(), termQuery, false));
//      query.AND(DiseaseWrapper.getInactive(termQuery).EQ(false));
    }

  }

  private static class GeoEntityTermQueryBuilder extends ViewQueryBuilder implements Reloadable
  {
    private TermQuery     termQuery;

    // AllPaths is used to restrict the query by parent term Ids.
    private AllPathsQuery pathsQuery;

    private String        termId;

    private String        geoEntityType;

    protected GeoEntityTermQueryBuilder(QueryFactory queryFactory, String termId, String geoEntityType)
    {
      super(queryFactory);

      this.termId = termId;
      this.geoEntityType = geoEntityType;

      this.termQuery = new TermQuery(queryFactory);
      this.pathsQuery = new AllPathsQuery(queryFactory);
    }

    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      query.map(TermView.TERMID, termQuery.getId());
      query.map(TermView.TERMNAME, termQuery.getTermDisplayLabel().localize());
      query.map(TermView.TERMONTOLOGYID, termQuery.getTermId());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      query.WHERE(termQuery.getTermId().EQi(this.termId));

      BrowserRootView[] views = BrowserRoot.getDefaultGeoRoots(geoEntityType);

      if (views.length == 1)
      {
        query.AND(this.pathsQuery.getChildTerm().EQ(this.termQuery));
        query.AND(this.pathsQuery.getParentTerm().EQ(views[0].getTermId()));
      }
      else
      {
        query.AND(this.pathsQuery.getChildTerm().EQ(this.termQuery));
        query.AND(this.pathsQuery.getParentTerm().EQ(""));
      }

      query.AND(Disease.getInactiveCriteria(this.getQueryFactory(), termQuery, false));
//      query.AND(DiseaseWrapper.getInactive(termQuery).EQ(false));
    }

  }

}
