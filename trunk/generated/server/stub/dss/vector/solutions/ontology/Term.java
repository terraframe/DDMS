package dss.vector.solutions.ontology;

import com.terraframe.mojo.query.GeneratedViewQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ViewQueryBuilder;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.geo.DuplicateParentException;
import dss.vector.solutions.geo.LocatedInException;
import dss.vector.solutions.geo.LocatedInQuery;
import dss.vector.solutions.query.ActionNotAllowedException;

public abstract class Term extends TermBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1253040031928L;
  
  public Term()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    OntologyDefinition ontology = this.getOntology();
    return ontology.getKeyName()+":"+this.getTermName();
  }
  
  /**
   * Throws a localized Exception to alert the user
   * that he is trying to modify the parent of a Term.
   * 
   * @throws ConfirmParentChangeException always.
   */
  @Override
  public void confirmChangeParent(String parentId)
  {
    Term parent = Term.get(parentId);
    
    String msg = "Attempting to change the parent of ["+this+"] to ["+parent+"].";
    
    ConfirmParentChangeException ex = new ConfirmParentChangeException(msg);
    ex.setParentTerm(parent.toString());
    
    throw ex;
  }
  
  public String toString()
  {
    return this.getTermName()+ " ("+this.getTermId()+")";
  }
  
  /**
   * Gets all the TermRelationship children of this Term.
   * 
   * FIXME parameterize to pass in the relationship type.
   */
  @Override
  public TermViewQuery getOntologyChildren()
  {
    QueryFactory f = new QueryFactory();
    
    GetChildrenQueryBuilder builder = new GetChildrenQueryBuilder(f, this);
    TermViewQuery q = new TermViewQuery(f, builder);
    
    return q;
  }
  
  public static TermViewQuery searchTerms(String searchValue, String parentTermId)
  {
    QueryFactory f = new QueryFactory();
    
    Term parent = Term.get(parentTermId);
    
    SearchQueryBuilder builder = new SearchQueryBuilder(f, searchValue, parent);
    TermViewQuery q = new TermViewQuery(f, builder);
    
    return q;
  }
  
  @Override
  public TermView applyWithParent(String parentTermId, Boolean cloneOperation)
  {
    boolean isNew = this.isNew();
    if (isNew)
    {
      this.apply(); // has no children
    }
    
    // make sure a child cannot be applied to itself
    if (this.getId().equals(parentTermId))
    {
//      String error = "The child [" + this.getEntityName() + "] cannot be its own parent.";
//
//      LocatedInException e = new LocatedInException(error);
//      e.setEntityName(this.getEntityName());
//      e.setParentDisplayLabel(this.getEntityName());
//      throw e;
    }

    if (!cloneOperation)
    {
      // V1 Restriction
      if(!isNew)
      {
        throw new ActionNotAllowedException();
      }
      /*
      OIterator<? extends LocatedIn> iter = this.getAllLocatedInGeoEntityRel();
      try
      {
        while (iter.hasNext())
        {
          iter.next().delete();
        }
      }
      finally
      {
        iter.close();
      }
      */
    }
    else
    {
      // confirm this entity can't be applied to the same
      // parent more than once.
      QueryFactory f = new QueryFactory();
      IsAQuery q = new IsAQuery(f);
      q.WHERE(q.childId().EQ(this.getId()));
      q.WHERE(q.parentId().EQ(parentTermId));

      if (q.getCount() > 0)
      {
        String childDL = this.toString();
        String parentDL = this.toString();

        String error = "The child [" + childDL + "] already has a direct relationship with parent [" + parentDL
            + "].";
        DuplicateParentException e = new DuplicateParentException(error);
        e.setChildEntityName(childDL);
        e.setParentEntityName(parentDL);

        throw e;
      }
    }    
    
    Term parent = Term.get(parentTermId);
    this.addIsA(parent).apply();

    TermViewQuery query = getByIds(new String[]{this.getId()});
    OIterator<? extends TermView> iter = query.getIterator();
    
    try
    {
      return iter.next();
    }
    finally
    {
      iter.close();
    }
  }
  
  public static TermViewQuery getDefaultRoots()
  {
    QueryFactory f = new QueryFactory(); 

    // FIXME pass in the ontology and rel and switch on that
    TermQuery termQuery;
    TermRelationshipQuery termRelQuery;
    if(true /* || Ontology === MO */)
    {
      termQuery = new MOQuery(f);
      termRelQuery = new IsAQuery(f);
    }
    
    DefaultRootQueryBuilder builder = new DefaultRootQueryBuilder(f, termQuery, termRelQuery);
    TermViewQuery query = new TermViewQuery(f, builder);
    
    return query;
  }
  
  public static TermViewQuery getByIds(String[] termIds)
  {
    QueryFactory f = new QueryFactory();
    
    FetchQueryBuilder builder = new FetchQueryBuilder(f, termIds);
    
    TermViewQuery q = new TermViewQuery(f, builder);
    
    return q;
  }
  
  private static class FetchQueryBuilder extends ViewQueryBuilder implements Reloadable
  {
    private String[] termIds;
    private TermQuery termQuery;
    
    protected FetchQueryBuilder(QueryFactory queryFactory, String[] termIds)
    {
      super(queryFactory);
      
      this.termIds = termIds;
      this.termQuery = new TermQuery(queryFactory);
    }
    
    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery query = this.getViewQuery();
      
      query.map(TermView.TERMID, termQuery.getId());
      query.map(TermView.TERMNAME, termQuery.getTermName());
      query.map(TermView.TERMONTOLOGYID, termQuery.getTermId());
    }
    
    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery query = this.getViewQuery();
       
      // restrict by the term ids (ordering will be done client-side)
      query.WHERE(termQuery.getId().IN(this.termIds));
    }
  }
  
  /**
   * Query builder for searching on Terms by name and id.
   */
  private static class SearchQueryBuilder extends ViewQueryBuilder implements Reloadable
  {
    private Term parent;
    private TermQuery termQuery;
    private String searchValue;
    
    protected SearchQueryBuilder(QueryFactory queryFactory, String searchValue, Term parent)
    {
      super(queryFactory);
      
      this.parent = parent;
      this.searchValue = searchValue;
      this.termQuery = new TermQuery(queryFactory);
    }
    
    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery query = this.getViewQuery();
      
      query.map(TermView.TERMID, termQuery.getId());
      query.map(TermView.TERMNAME, termQuery.getTermName());
      query.map(TermView.TERMONTOLOGYID, termQuery.getTermId());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery query = this.getViewQuery();
      
      String search = this.searchValue+"%";
      query.WHERE(OR.get(termQuery.getTermName().LIKEi(search),
          termQuery.getTermId().LIKEi(search)));
      
      query.ORDER_BY_ASC(this.termQuery.getTermName());
    }
  }
  
  /**
   * Query builder to fetch all children terms for a given parent.
   */
  private static class GetChildrenQueryBuilder extends ViewQueryBuilder implements Reloadable
  {
    private Term parent;
    private TermQuery termQuery;
    private TermRelationshipQuery termRelQuery;
    
    protected GetChildrenQueryBuilder(QueryFactory queryFactory, Term parent)
    {
      super(queryFactory);
      
      this.parent = parent;
      this.termQuery = new TermQuery(queryFactory);
      this.termRelQuery = new TermRelationshipQuery(queryFactory);
    }

    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery query = this.getViewQuery();
      
      query.map(TermView.TERMID, termQuery.getId());
      query.map(TermView.TERMNAME, termQuery.getTermName());
      query.map(TermView.TERMONTOLOGYID, termQuery.getTermId());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery query = this.getViewQuery();
      
      query.WHERE(this.termRelQuery.parentId().EQ(this.parent.getId()));
      query.AND(termQuery.parentTerm(this.termRelQuery)); // FIXME parent-child label reversed
      
      query.ORDER_BY_ASC(this.termQuery.getTermName());
    }
    
  }
  
  /**
   * Queries for the root Term of a given ontology.
   */
  private static class DefaultRootQueryBuilder extends ViewQueryBuilder implements Reloadable
  {
    private TermQuery termQuery;
    private ValueQuery valueQuery;
    private TermRelationshipQuery termRelQuery;
    
    protected DefaultRootQueryBuilder(QueryFactory queryFactory, TermQuery termQuery, TermRelationshipQuery termRelQuery)
    {
      super(queryFactory);
      
      this.termQuery = termQuery;
      this.valueQuery = queryFactory.valueQuery();
      this.termRelQuery = termRelQuery;
    }
    
    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery query = this.getViewQuery();  
      
      query.map(TermView.TERMID, this.termQuery.getId());
      query.map(TermView.TERMNAME, this.termQuery.getTermName());
      query.map(TermView.TERMONTOLOGYID, termQuery.getTermId());
    }
    
    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery query = this.getViewQuery();  
      
      // the root is not a child of any other term
      Selectable childId = this.termRelQuery.childId();
      this.valueQuery.SELECT(childId);
      
      query.WHERE(this.termQuery.NOT_IN(this.termQuery.getId(), this.valueQuery));
      
      query.ORDER_BY_ASC(this.termQuery.getTermName());
    }
  }
}
