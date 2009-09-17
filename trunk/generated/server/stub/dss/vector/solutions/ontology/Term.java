package dss.vector.solutions.ontology;

import com.terraframe.mojo.query.GeneratedViewQuery;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ViewQueryBuilder;
import com.terraframe.mojo.generation.loader.Reloadable;

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
  
  @Override
  public TermViewQuery getOntologyChildren()
  {
    QueryFactory f = new QueryFactory();
    
    GetChildrenQueryBuilder builder = new GetChildrenQueryBuilder(f, this);
    TermViewQuery q = new TermViewQuery(f, builder);
    
    return q;
  }
  
  
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
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery query = this.getViewQuery();
      
      query.WHERE(this.termRelQuery.parentId().EQ(this.parent.getId()));
      query.AND(termQuery.parentTerm(this.termRelQuery)); // FIXME parent-child label reversed
    }
    
  }
}
