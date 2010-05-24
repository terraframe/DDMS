package dss.vector.solutions.ontology;

import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.constants.RelationshipInfo;
import com.runwaysdk.dataaccess.DuplicateGraphPathException;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.BasicCondition;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.GeneratedViewQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.ViewQueryBuilder;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeReference;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.UnknownTermProblem;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.QueryBuilder;
import dss.vector.solutions.surveillance.OptionComparator;
import dss.vector.solutions.surveillance.OptionIF;

public class Term extends TermBase implements Reloadable, OptionIF
{
  private static final long serialVersionUID = 1253040031928L;

  public Term()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else if (this.getName() != null && this.getTermId() != null)
    {
      return this.getName() + " (" + this.getTermId() + ")";
    }

    return super.toString();
  }

  @Override
  protected String buildKey()
  {
    return this.getTermId();
  }

  /**
   * Throws a localized Exception to alert the user that he is trying to modify
   * the parent of a Term.
   * 
   * @throws ConfirmParentChangeException
   *           always.
   */
  @Override
  public void confirmChangeParent(String parentId)
  {
    Term parent = Term.get(parentId);

    String msg = "Attempting to change the parent of [" + this + "] to [" + parent + "].";

    ConfirmParentChangeException ex = new ConfirmParentChangeException(msg);
    ex.setParentTerm(parent.toString());

    throw ex;
  }

  @Override
  @Transaction
  public void deleteTerm()
  {
    OntologyRelationship rel = OntologyRelationship.getByKey(OBO.IS_A);

    // Rebuild the all paths
    if (this.isSingleLeafNode(rel.getId()))
    {
      AllPaths.deleteLeafFromAllPaths(this.getId());

      this.delete();
    }
    else
    {
      MdBusiness mdBusiness = MdBusiness.getMdBusiness(AllPaths.CLASS);
      mdBusiness.deleteAllTableRecords();

      this.delete();

      AllPaths.rebuildAllPaths();
    }
  }

  @Override
  @Transaction
  public void delete()
  {
    List<? extends Term> children = this.getAllChildTerm().getAll();

    for (Term child : children)
    {
      if (child.hasSingleParent())
      {
        child.delete();
      }
    }

    super.delete();
  }

  private boolean hasSingleParent()
  {
    QueryFactory f = new QueryFactory();
    TermRelationshipQuery q = new TermRelationshipQuery(f);

    q.WHERE(q.childId().EQ(this.getId()));

    return q.getCount() == 1;
  }

  /**
   * Deletes the TermRElationship between this Term and the Term with the given
   * parent id. This method should only be called if this Term has more than one
   * parent.
   */
  @Override
  @Transaction
  public void deleteRelationship(String parentId)
  {
    QueryFactory f = new QueryFactory();
    TermRelationshipQuery q = new TermRelationshipQuery(f);

    q.WHERE(q.childId().EQ(this.getId()));
    q.AND(q.parentId().EQ(parentId));

    OIterator<? extends TermRelationship> iter = q.getIterator();

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

      AllPaths.rebuildAllPaths();
    }
  }

  /**
   * Throws an exception to alert the user before they try to delete a Term.
   * 
   * @throws ConfirmDeleteTermException
   *           If the Term has more than one parent.
   * @throws
   */
  @Override
  @Transaction
  public void confirmDeleteTerm(String parentId)
  {
    QueryFactory f = new QueryFactory();
    TermRelationshipQuery q = new TermRelationshipQuery(f);

    q.WHERE(q.childId().EQ(this.getId()));
    q.AND(q.parentId().NE(parentId));

    if (q.getCount() > 0)
    {
      // The Term has more than one parent, so prompt the user to delete either
      // the
      // Term or just the relationship with the current parent.
      Term parent = Term.get(parentId);

      ConfirmDeleteTermException ex = new ConfirmDeleteTermException();
      ex.setTerm(parent.getTermDisplayLabel().getValue());
      throw ex;
    }
    else
    {
      this.deleteTerm();
    }
  }

  @Override
  public void apply()
  {
    // Use the name as the display label if no value is given
    String display = this.getTermDisplayLabel().getValue();
    if (display == null || display.length() == 0)
    {
      this.getTermDisplayLabel().setValue(this.getName());
//      this.getDisplayLabel().setDefaultValue(this.getName()); Does this need to be called?
    }

    // If this is new, set the Ontology value to the MO ontology.
    // FIXME this will be removed once Term subclasses are refactored
    // out and Term will be come concrete.
    boolean isNew = this.isNew();
    if (isNew)
    {
      Ontology moOntology = Ontology.getByKey(MO.KEY);
      // There WILL be one record; otherwise, the application was not set up
      // properly
      this.setOntology(moOntology);
    }

    super.apply();
    
    if(isNew)
    {
      // set inactive for all diseases by default
      for(Disease disease : Disease.getAllDiseases())
      {
        InactiveProperty prop = new InactiveProperty();
        prop.setInactive(false);
        prop.setDisease(disease);
        prop.apply();
        
        this.addInactiveProperties(prop).apply();
      }
    }
  }

  /**
   * Gets all the TermRelationship children of this Term.
   * 
   * FIXME parameterize to pass in the relationship type.
   */
  @Override
  public TermViewQuery getOntologyChildren(Boolean filterObsolete)
  {
    QueryFactory f = new QueryFactory();

    GetChildrenQueryBuilder builder = new GetChildrenQueryBuilder(f, this, filterObsolete);
    TermViewQuery q = new TermViewQuery(f, builder);

    return q;
  }

  public static TermViewQuery searchTerms(String searchValue, String[] parentTermIds)
  {
    QueryFactory f = new QueryFactory();

    SearchQueryBuilder builder = new SearchQueryBuilder(f, searchValue, parentTermIds);
    TermViewQuery q = new TermViewQuery(f, builder);

    q.restrictRows(15, 1);

    return q;
  }

  public static ValueQuery termQuery(String value, String[] parentTermIds)
  {
    QueryFactory factory = new QueryFactory();

    ValueQuery query = new ValueQuery(factory);
    AllPathsQuery pathsQuery = new AllPathsQuery(query);
    TermQuery termQuery = new TermQuery(query);

    SelectablePrimitive[] selectables = new SelectablePrimitive[] { termQuery.getId(Term.ID), termQuery.getTermDisplayLabel().localize(Term.TERMDISPLAYLABEL), termQuery.getTermId(Term.TERMID) };

    List<Condition> conditions = new LinkedList<Condition>();

    // Restrict the search by parent terms. There are three options:
    // 1) If the parentIds array is null, don't restrict anything
    // 2) If the parentIds array is empty, don't allow searching
    // 3) If the parentIds array has ids, restrict by those ids
    if (parentTermIds != null && parentTermIds.length > 0)
    {
      conditions.add(pathsQuery.getChildTerm().EQ(termQuery));
      conditions.add(pathsQuery.getParentTerm().IN(parentTermIds));
    }
    else if (parentTermIds != null)
    {
      // There are no Parent terms meaning no roots have been set. Searching
      // is not allowed without roots.
      conditions.add(termQuery.getId().EQ(""));
    }

    conditions.add(Disease.getInactiveCriteria(factory, termQuery, false));
//    conditions.add(Disease.getInactive(termQuery).EQ(false));

    Condition[] conditionArray = conditions.toArray(new Condition[conditions.size()]);

    if (value != null && !value.equals(""))
    {
      String[] tokens = value.split(" ");
      SelectablePrimitive[] searchables = new SelectablePrimitive[] { termQuery.getTermDisplayLabel().localize(Term.TERMDISPLAYLABEL), termQuery.getTermId(Term.TERMID) };

      QueryBuilder.textLookup(query, factory, tokens, searchables, selectables, conditionArray);
    }
    else
    {
      SelectablePrimitive orderBy = selectables[1];
      QueryBuilder.orderedLookup(query, factory, orderBy, selectables, conditionArray);
    }

    query.restrictRows(20, 1);
    return query;
  }

  @Override
  @Transaction
  public TermView applyWithParent(String parentTermId, Boolean cloneOperation, String oldParentId, Boolean inactive)
  {
    OntologyRelationship ontRel = OntologyRelationship.getByKey(OBO.IS_A);
    Term parent = Term.get(parentTermId);

    boolean isNew = this.isNew();
    if (isNew)
    {
      this.apply(); // has no children
    }

    // make sure a child cannot be applied to itself
    if (this.getId().equals(parentTermId))
    {
      String error = "The Term [" + this + "] cannot be its own parent.";

      SameChildParentTermException e = new SameChildParentTermException(error);
      e.setTerm(this.toString());
      throw e;
    }

    if (!cloneOperation && !isNew)
    {
      // Remove the old relationship on this Term and parent
      QueryFactory f = new QueryFactory();
      TermRelationshipQuery q = new TermRelationshipQuery(f);

      q.WHERE(q.parentId().EQ(oldParentId));
      q.AND(q.childId().EQ(this.getId()));

      OIterator<? extends TermRelationship> iter = q.getIterator();

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
    }
    else if (!isNew)
    {
      // confirm this entity can't be applied to the same
      // parent more than once.
      QueryFactory f = new QueryFactory();
      TermRelationshipQuery q = new TermRelationshipQuery(f);
      q.WHERE(q.childId().EQ(this.getId()));
      q.AND(q.parentId().EQ(parentTermId));

      if (q.getCount() > 0)
      {
        String childDL = this.toString();
        String parentDL = this.toString();

        String error = "The child [" + childDL + "] already has a direct relationship with parent [" + parentDL + "].";
        DuplicateParentException e = new DuplicateParentException(error);
        e.setChildTerm(this.toString());
        e.setParentTerm(parent.toString());

        throw e;
      }
    }

    TermRelationship termRelationship = this.addParentTerm(parent);
    termRelationship.setOntologyRelationship(ontRel);

    // create save point
    Savepoint savepoint = Database.setSavepoint();
    try
    {
      termRelationship.apply();
    }
    catch (DuplicateGraphPathException e)
    {
      // a relationship between this typedef and the parent and the child
      // already exists
      Database.rollbackSavepoint(savepoint);
    }
    finally
    {
      Database.releaseSavepoint(savepoint);
    }

    // update the AllPaths table
    if (cloneOperation)
    {
      AllPaths.copyTermFast(parentTermId, this.getId(), ontRel.getId());
    }
    else if (!isNew)
    {
      AllPaths.rebuildAllPaths();
    }
    
    if(inactive != null)
    {
      InactiveProperty prop = this.getInactiveByDisease();
      
      prop.appLock();
      prop.setInactive(inactive);
      prop.apply();
    }

    TermViewQuery query = getByIds(new String[] { this.getId() });
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
  
  @Override
  @Transaction
  public void updateFromTree(Boolean inactive)
  {
    InactiveProperty prop = this.getInactiveByDisease();
    prop.appLock();
    prop.setInactive(inactive);
    prop.apply();

    this.apply();
  }
  
  /**
   * Returns the InactiveProperty associated with this Term
   * for the current disease of the session. If this Term is a
   * new instance then a new instance of InactiveProperty is returned,
   * which can be used for metadata purposes and default values.
   */
  @Override
  public InactiveProperty getInactiveByDisease()
  {
    Disease disease = Disease.getCurrent();
    
    if(this.isNew() && !this.isAppliedToDB())
    {
      InactiveProperty prop = new InactiveProperty();
      prop.setInactive(false);
      prop.setDisease(disease);
      return prop;
    }
    else
    {
      QueryFactory f = new QueryFactory();
      TermQuery tq = new TermQuery(f);
      InactivePropertyQuery ipQ = new InactivePropertyQuery(f);
      
      tq.WHERE(tq.getId().EQ(this.getId()));
      
      ipQ.WHERE(ipQ.getDisease().EQ(disease));
      ipQ.AND(ipQ.term(tq));
      
      OIterator<? extends InactiveProperty> iter = ipQ.getIterator();
      
      try
      {
        return iter.next();  
      }
      finally
      {
        iter.close();
      }
    }
  }

  /**
   * Returns all default roots (Terms without parents). This method WILL return
   * all Terms regardless of obsolete status. To return the terms with obsolete
   * marked as false, use BrowserRoot.getDefaultRoot().
   * 
   * @param filterObsolete
   * @return
   */
  public static TermViewQuery getDefaultRoots()
  {
    return getDefaultRoots(false);
  }

  public static TermViewQuery getDefaultRoots(boolean filterObsolete)
  {
    return Term.getDefaultRoots(new QueryFactory(), filterObsolete);
  }

  private static TermViewQuery getDefaultRoots(QueryFactory f, boolean filterObsolete)
  {
    // FIXME pass in the ontology and rel and switch on that
    TermQuery termQuery;
    TermRelationshipQuery termRelQuery;
    if (true /* || Ontology === MO */)
    {
      termQuery = new TermQuery(f);
      termRelQuery = new TermRelationshipQuery(f);
    }

    DefaultRootQueryBuilder builder = new DefaultRootQueryBuilder(f, termQuery, termRelQuery, filterObsolete);
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

  public static ValueQuery termQueryByIds(String[] termIds)
  {
    QueryFactory factory = new QueryFactory();

    ValueQuery query = new ValueQuery(factory);
    TermQuery termQuery = new TermQuery(query);

    SelectablePrimitive[] selectables = new SelectablePrimitive[] { termQuery.getId(Term.ID), termQuery.getTermDisplayLabel().localize(Term.TERMDISPLAYLABEL), termQuery.getTermId(Term.TERMID) };

    query.SELECT(selectables);

    // restrict by the term ids (ordering will be done client-side)
    if (termIds != null && termIds.length > 0)
    {
      query.WHERE(termQuery.getId().IN(termIds));
    }
    else
    {
      // Use empty string to avoid SQL syntax error.
      query.WHERE(termQuery.getId().IN(""));
    }

    return query;
  }

  public static Term getByTermId(String termId)
  {
    TermQuery query = new TermQuery(new QueryFactory());
    query.WHERE(query.getTermId().EQ(termId));

    if (query.getCount() == 0)
    {
      InvalidTermIdException invalidTermIdException = new InvalidTermIdException("No term found with id [" + termId + "]");
      invalidTermIdException.setTermId(termId);
      throw invalidTermIdException;
    }

    OIterator<? extends Term> iterator = query.getIterator();
    Term term = iterator.next();
    iterator.close();

    return term;
  }

  private static class FetchQueryBuilder extends ViewQueryBuilder implements Reloadable
  {
    private String[]  termIds;

    private TermQuery termQuery;
    
    private InactivePropertyQuery inactivePropQuery;

    protected FetchQueryBuilder(QueryFactory queryFactory, String[] termIds)
    {
      super(queryFactory);

      this.termIds = termIds;
      this.termQuery = new TermQuery(queryFactory);
      this.inactivePropQuery = new InactivePropertyQuery(queryFactory);
    }

    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      query.map(TermView.TERMID, termQuery.getId());
      // query.map(TermView.TERMNAME, termQuery.getName());
      query.map(TermView.TERMNAME, termQuery.getTermDisplayLabel().localize());
      query.map(TermView.TERMONTOLOGYID, termQuery.getTermId());
      query.map(TermView.INACTIVE, this.inactivePropQuery.getInactive());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      Disease disease = Disease.getCurrent();
      this.inactivePropQuery.AND(this.inactivePropQuery.getDisease().EQ(disease));

      query.AND(termQuery.inactiveProperties(this.inactivePropQuery));
      
      // restrict by the term ids (ordering will be done client-side)
      if (this.termIds != null && this.termIds.length > 0)
      {
        query.WHERE(termQuery.getId().IN(this.termIds));
      }
      else
      {
        // Use empty string to avoid SQL syntax error.
        query.WHERE(termQuery.getId().IN(""));
      }
    }
  }

  private static class SearchRootQueryBuilder extends ViewQueryBuilder implements Reloadable
  {
    private TermQuery        termQuery;

    // AllPaths is used to restrict the query by parent term Ids.
    private AllPathsQuery    pathsQuery;

    private BrowserRootQuery rootQuery;

    private BrowserRootQuery unselectableRootQuery;

    private String           searchValue;

    protected SearchRootQueryBuilder(QueryFactory queryFactory, String searchValue, BrowserRootQuery rootQuery, BrowserRootQuery unselectableRootQuery)
    {
      super(queryFactory);

      this.rootQuery = rootQuery;
      this.unselectableRootQuery = unselectableRootQuery;
      this.searchValue = searchValue;
      this.termQuery = new TermQuery(queryFactory);
      this.pathsQuery = new AllPathsQuery(queryFactory);
    }

    protected SearchRootQueryBuilder(QueryFactory queryFactory, String searchValue)
    {
      super(queryFactory);

      this.rootQuery = null;
      this.unselectableRootQuery = null;
      this.searchValue = searchValue;
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

      String search = this.searchValue.replace(" ", "% ") + "%";

      if (search.length() > 0)
      {
        query.WHERE(OR.get(termQuery.getName().LIKEi(search), termQuery.getTermId().LIKEi(search)));
      }

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
//      query.AND(Disease.getInactive(termQuery).EQ(false));

      query.ORDER_BY_ASC(this.termQuery.getTermDisplayLabel().localize());
    }

  }

  /**
   * Query builder for searching on Terms by name and id.
   */
  private static class SearchQueryBuilder extends ViewQueryBuilder implements Reloadable
  {
    private String[]      parentIds;

    private TermQuery     termQuery;

    // AllPaths is used to restrict the query by parent term Ids.
    private AllPathsQuery pathsQuery;

    private String        searchValue;

    protected SearchQueryBuilder(QueryFactory queryFactory, String searchValue, String[] parentIds)
    {
      super(queryFactory);

      this.parentIds = parentIds;
      this.searchValue = searchValue;
      this.termQuery = new TermQuery(queryFactory);
      this.pathsQuery = new AllPathsQuery(queryFactory);
    }

    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      query.map(TermView.TERMID, termQuery.getId());
      // query.map(TermView.TERMNAME, termQuery.getName());
      query.map(TermView.TERMNAME, termQuery.getTermDisplayLabel().localize());
      query.map(TermView.TERMONTOLOGYID, termQuery.getTermId());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      String search = this.searchValue.replace(" ", "% ") + "%";

      query.WHERE(OR.get(termQuery.getName().LIKEi(search), termQuery.getTermId().LIKEi(search)));

      // Restrict the search by parent terms. There are three options:
      // 1) If the parentIds array is null, don't restrict anything
      // 2) If the parentIds array is empty, don't allow searching
      // 3) If the parentIds array has ids, restrict by those ids
      if (this.parentIds != null && this.parentIds.length > 0)
      {
        query.AND(this.pathsQuery.getChildTerm().EQ(this.termQuery));
        query.AND(this.pathsQuery.getParentTerm().IN(this.parentIds));
      }
      else if (this.parentIds != null)
      {
        // There are no Parent terms meaning no roots have been set. Searching
        // is not
        // allowed without roots.
        query.AND(termQuery.getId().EQ(""));
      }

      query.AND(Disease.getInactiveCriteria(this.getQueryFactory(), termQuery, false));
//      query.AND(Disease.getInactive(termQuery).EQ(false));

      query.ORDER_BY_ASC(this.termQuery.getTermDisplayLabel().localize());
    }
  }

  /**
   * Query builder to fetch all children terms for a given parent.
   */
  private static class GetChildrenQueryBuilder extends ViewQueryBuilder implements Reloadable
  {
    private Term                  parent;

    private TermQuery             termQuery;

    private TermRelationshipQuery termRelQuery;

    private Boolean               filterObsolete;
    
    private InactivePropertyQuery inactivePropQuery;

    protected GetChildrenQueryBuilder(QueryFactory queryFactory, Term parent, Boolean filterObsolete)
    {
      super(queryFactory);

      this.parent = parent;
      this.termQuery = new TermQuery(queryFactory);
      this.termRelQuery = new TermRelationshipQuery(queryFactory);
      this.filterObsolete = filterObsolete;
      this.inactivePropQuery = new InactivePropertyQuery(queryFactory);
    }

    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      query.map(TermView.TERMID, termQuery.getId());
      // query.map(TermView.TERMNAME, termQuery.getName());
      query.map(TermView.TERMNAME, termQuery.getTermDisplayLabel().localize());
      query.map(TermView.TERMONTOLOGYID, termQuery.getTermId());
      query.map(TermView.INACTIVE, this.inactivePropQuery.getInactive());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      query.WHERE(this.termRelQuery.parentId().EQ(this.parent.getId()));
      query.AND(termQuery.parentTerm(this.termRelQuery));
      
      Disease disease = Disease.getCurrent();
      
      if (this.filterObsolete)
      {
        this.inactivePropQuery.AND(this.inactivePropQuery.getInactive().EQ(false));

      }
      
      this.inactivePropQuery.AND(this.inactivePropQuery.getDisease().EQ(disease));

      query.AND(termQuery.inactiveProperties(this.inactivePropQuery));

      // query.ORDER_BY_ASC(this.termQuery.getName());
      query.ORDER_BY_ASC(this.termQuery.getTermDisplayLabel().localize());
    }

  }

  /**
   * Queries for the root Term of a given ontology.
   */
  private static class DefaultRootQueryBuilder extends ViewQueryBuilder implements Reloadable
  {
    private TermQuery             termQuery;

    // private ValueQuery valueQuery;

    private TermRelationshipQuery termRelQuery;

    private boolean               filterObsolete;
    
    protected DefaultRootQueryBuilder(QueryFactory queryFactory, TermQuery termQuery, TermRelationshipQuery termRelQuery, boolean filterObsolete)
    {
      super(queryFactory);

      this.termQuery = termQuery;
      // this.valueQuery = queryFactory.valueQuery();
      this.termRelQuery = termRelQuery;
      this.filterObsolete = filterObsolete;
    }

    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      query.map(TermView.TERMID, this.termQuery.getId());
      query.map(TermView.TERMNAME, termQuery.getTermDisplayLabel().localize());
      query.map(TermView.TERMONTOLOGYID, termQuery.getTermId());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      String rootId = RootTerm.getRootInstance().getId();

      // When filtering is enabled, we know the context is the ontology browser,
      // which means we want the roots that are the children of the single
      // instance of RootTerm. When filtering is not enabled, we know the context
      // is to fetch the single instance of RootTerm as the root (for the term tree admin
      // screen).
      if (this.filterObsolete)
      {
        query.WHERE(this.termRelQuery.parentId().EQ(rootId));
        query.AND(this.termRelQuery.hasChild(termQuery));

        query.AND(Disease.getInactiveCriteria(this.getQueryFactory(), termQuery, false));
      }
      else
      {
        query.WHERE(termQuery.getId().EQ(rootId));
      }

      query.ORDER_BY_ASC(this.termQuery.getTermDisplayLabel().localize());
    }
  }

  @Transaction
  public static TermView getTermById(String termId, String[] parameters)
  {
    QueryFactory f = new QueryFactory();

    String className = parameters[0];
    String attribute = parameters[1];

    ViewQueryBuilder builder = new TermQueryFactory(f, termId, className, attribute).getBuilder();

    TermViewQuery q = new TermViewQuery(f, builder);

    OIterator<? extends TermView> it = q.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      String msg = "The selected term [" + termId + "] is not selectable for the given parameters";
      TermSelectionProblem p = new TermSelectionProblem(msg);
      p.setTermId(termId);
      p.apply();

      p.throwIt();

      return null;
    }
    finally
    {
      it.close();
    }
  }

  @Transaction
  public static Term validateByDisplayLabel(String displayLabel, MdAttributeDAOIF mdAttribute)
  {
    // No value means they didn't specify one. Don't throw a problem; just
    // return null
    if (displayLabel.trim().length() == 0)
    {
      return null;
    }

    QueryFactory factory = new QueryFactory();

    BrowserFieldQuery bfq = new BrowserFieldQuery(factory);
    bfq.WHERE(bfq.getMdAttribute().EQ(mdAttribute));

    BrowserRootQuery brq = new BrowserRootQuery(factory);
    brq.WHERE(brq.field(bfq));

    AllPathsQuery apq = new AllPathsQuery(factory);
    apq.WHERE(apq.getParentTerm().EQ(brq.getTerm()));

    TermQuery tq = new TermQuery(factory);
    tq.WHERE(OR.get(tq.getName().EQi(displayLabel), tq.getTermDisplayLabel().localize().EQi(displayLabel), tq.getTermId().EQ(displayLabel)));
    tq.WHERE(tq.getId().EQ(apq.getChildTerm().getId()));

    OIterator<? extends Term> iterator = tq.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      String attributeLabel = mdAttribute.getDisplayLabel(Session.getCurrentLocale());
      String msg = "Unknown " + attributeLabel + " with the given name [" + displayLabel + "]";

      UnknownTermProblem e = new UnknownTermProblem(msg);
      e.setTermName(displayLabel);
      e.setAttributeLabel(attributeLabel);
      e.throwIt();

      // We expect to return nothing, as we're throwing a problem, but include
      // this to satisfy the compile time requirement
      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  protected static List<String> getRecursiveParentIds(String childId, String ontologyRelationshipId)
  {
    QueryFactory queryFactory = new QueryFactory();

    OntologyRelationship ontologyRelationship = OntologyRelationship.get(ontologyRelationshipId);
    TermRelationshipQuery termRelationshipQuery = new TermRelationshipQuery(queryFactory);
    termRelationshipQuery.WHERE(termRelationshipQuery.getOntologyRelationship().EQ(ontologyRelationship));

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    valueQuery.SELECT(termRelationshipQuery.parentId(RelationshipInfo.PARENT_ID, RelationshipInfo.PARENT_ID));
    valueQuery.WHERE(termRelationshipQuery.childId().EQ(childId));

    List<ValueObject> valueObjectList = valueQuery.getIterator().getAll();

    List<String> parentIdList = new LinkedList<String>();

    for (ValueObject valueObject : valueObjectList)
    {
      String parentId = valueObject.getValue(RelationshipInfo.PARENT_ID);
      parentIdList.add(parentId);
      parentIdList.addAll(getRecursiveParentIds(parentId, ontologyRelationshipId));
    }

    return parentIdList;
  }

  protected static List<String> getChildIds(String parentId, String ontologyRelationshipId)
  {
    QueryFactory queryFactory = new QueryFactory();

    OntologyRelationship ontologyRelationship = OntologyRelationship.get(ontologyRelationshipId);
    TermRelationshipQuery termRelationshipQuery = new TermRelationshipQuery(queryFactory);
    termRelationshipQuery.WHERE(termRelationshipQuery.getOntologyRelationship().EQ(ontologyRelationship));

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    valueQuery.SELECT(termRelationshipQuery.childId(RelationshipInfo.CHILD_ID, RelationshipInfo.CHILD_ID));
    valueQuery.WHERE(termRelationshipQuery.parentId().EQ(parentId));

    List<ValueObject> valueObjectList = valueQuery.getIterator().getAll();

    List<String> childOfChildIdList = new LinkedList<String>();

    for (ValueObject valueObject : valueObjectList)
    {
      String childId = valueObject.getValue(RelationshipInfo.CHILD_ID);
      childOfChildIdList.add(childId);
    }

    return childOfChildIdList;
  }

  /**
   * Gets all selectable Term objects that are the first descendents of the
   * field described by the given class and attribute names. Inheritance is
   * already factored into the method such that if B extends A and A defines
   * attribute m, the following calls are valid:
   * 
   * 1) Term.getAllTermsForField("A", "m") 2) Term.getAllTermsForField("B", "m")
   * 
   * @param className
   * @param attributeName
   * @return
   */
  public static Term[] getAllTermsForField(String className, String attributeName)
  {
    return Term.getRootChildren(className, attributeName, true);
  }

  public static Term[] getAllTermsByAttribute(MdAttribute mdAttribute)
  {
    return Term.getRootChildren(MdAttributeDAO.getByKey(mdAttribute.getKey()), true);
  }

  public static Term[] getRootChildren(MdAttributeDAOIF mdAttribute)
  {
    return Term.getRootChildren(mdAttribute, true);
  }

  public static Term[] getRootChildren(String className, String attributeName, Boolean returnOnlySelectable)
  {
    BrowserRootView[] roots = BrowserRoot.getAttributeRoots(className, attributeName);
    Set<Term> children = new TreeSet<Term>(new TermComparator());
    for (BrowserRootView view : roots)
    {
      Term term = Term.get(view.getTermId());

      if (returnOnlySelectable)
      {
        if (view.getSelectable())
        {
          children.add(term);
        }
      }
      else
      {
        children.add(term);
      }

      for (Term child : term.getAllChildTerm())
      {
        children.add(child);
      }
    }

    List<Term> sorted = new ArrayList<Term>(children);
    Collections.sort(sorted, new OptionComparator());

    return sorted.toArray(new Term[sorted.size()]);
  }

  /**
   * @param mdAttribute
   * @return Returns selectable roots and every roots direct descendants for a
   *         given MdAttribute
   */
  public static Term[] getRootChildren(MdAttributeDAOIF mdAttribute, Boolean returnOnlySelectable)
  {
    String className = mdAttribute.definedByClass().definesType();

    return Term.getRootChildren(className, mdAttribute.definesAttribute(), returnOnlySelectable);
  }

  public String getOptionName()
  {
    return this.getName();
  }

  public boolean isLeaf()
  {
    return this.getAllChildTerm().getAll().size() == 0;
  }

  /**
   * Returns all attributes that reference the Term class.
   * 
   * @param className
   * @return
   */
  public static String[] getTermAttributes(String className)
  {
    MdBusiness md = MdBusiness.getMdBusiness(className);
    List<String> list = new LinkedList<String>();

    for (MdAttribute mdAttr : md.getAllAttribute())
    {
      if (mdAttr instanceof MdAttributeReference && ( (MdAttributeReference) mdAttr ).getMdBusiness().definesType().equals(Term.CLASS))
      {
        list.add( ( (MdAttributeReference) mdAttr ).getAttributeName());
      }
    }

    return list.toArray(new String[list.size()]);
  }

  /**
   * A single leaf node has no children and has one or fewer parents.
   * 
   * @param ontologyRelationshipId
   * @return true if a single leaf node, false otherwise.
   */
  public boolean isSingleLeafNode(String ontologyRelationshipId)
  {
    // It is a leaf node if the term has no children and only has one parent.
    if (this.getAllChildTerm().getAll().size() == 0 && this.getAllParentTerm().getAll().size() <= 1)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  public static TermViewQuery searchTermsWithRoots(String value, String[] parameters)
  {
    QueryFactory f = new QueryFactory();

    String className = parameters[0];
    String attribute = parameters[1];
    SearchRootQueryBuilder builder = null;

    if (className == null && attribute == null)
    {
      builder = new SearchRootQueryBuilder(f, value);
    }
    else
    {
      BrowserRootQuery rootQuery = BrowserRoot.getAttributeRoots(className, attribute, f);

      BrowserRootQuery unselectableRootQuery = BrowserRoot.getAttributeRoots(className, attribute, f);
      unselectableRootQuery.WHERE(unselectableRootQuery.getSelectable().EQ(false));

      builder = new SearchRootQueryBuilder(f, value, rootQuery, unselectableRootQuery);
    }

    TermViewQuery q = new TermViewQuery(f, builder);

    q.ORDER_BY_ASC(q.getTermName());

    q.restrictRows(15, 1);

    return q;
  }

  /**
   * @param value
   *          term value
   * 
   * @param parameters
   *          [0] = className
   * @param parameters
   *          [1] = attributeName
   * @return
   */
  public static ValueQuery searchByRoots(String value, String[][] roots)
  {
    QueryFactory factory = new QueryFactory();
    ValueQuery query = new ValueQuery(factory);
    
    AllPathsQuery pathsQuery = new AllPathsQuery(query);
    TermQuery termQuery = new TermQuery(query);
    
    SelectablePrimitive[] selectables = new SelectablePrimitive[] {
        termQuery.getId(Term.ID),
        termQuery.getTermDisplayLabel().localize(Term.TERMDISPLAYLABEL),
        termQuery.getTermId(Term.TERMID)
    };
    
    List<Condition> list = new LinkedList<Condition>();

    Condition condition = null;
    
    for(String[] root : roots)
    {
      String id = root[0];
      Boolean selectable = Boolean.parseBoolean(root[1]);
      
      BasicCondition eq = pathsQuery.getParentTerm().EQ(id);
      
      condition = (condition != null ) ? OR.get(condition, eq) : eq;
      
      if(!selectable)
      {
        list.add(termQuery.getId().NEi(id));
      }
    }
    
    list.add(pathsQuery.getChildTerm().EQ(termQuery));
    
    if(condition != null)
    {
      list.add(condition);
    }
    
    Condition[] conditions = list.toArray(new Condition[list.size()]);
    
    if (value != null && !value.equals(""))
    {
      String[] tokens = value.split(" ");
      SelectablePrimitive[] searchables = new SelectablePrimitive[] { termQuery.getTermDisplayLabel().localize(Term.TERMDISPLAYLABEL), termQuery.getTermId(Term.TERMID) };
      
      QueryBuilder.textLookup(query, factory, tokens, searchables, selectables, conditions);
    }
    else
    {
      SelectablePrimitive orderBy = selectables[1];
      
      QueryBuilder.orderedLookup(query, factory, orderBy, selectables, conditions);
    }
    
    query.restrictRows(20, 1);
    
    return query;
  }
  /**
   * @param value
   *          term value
   * 
   * @param parameters
   *          [0] = className
   * @param parameters
   *          [1] = attributeName
   * @return
   */
  public static ValueQuery termQueryWithRoots(String value, String[] parameters)
  {
    String className = parameters[0];
    String attribute = parameters[1];

    QueryFactory factory = new QueryFactory();
    ValueQuery query = new ValueQuery(factory);

    BrowserFieldQuery fieldQuery = new BrowserFieldQuery(query);
    BrowserRootQuery rootQuery = new BrowserRootQuery(query);
    BrowserRootQuery unselectableRootQuery = new BrowserRootQuery(query);
    AllPathsQuery pathsQuery = new AllPathsQuery(query);
    TermQuery termQuery = new TermQuery(query);
    
    SelectablePrimitive[] selectables = new SelectablePrimitive[] { termQuery.getId(Term.ID), termQuery.getTermDisplayLabel().localize(Term.TERMDISPLAYLABEL), termQuery.getTermId(Term.TERMID) };

    List<Condition> conditionList = Term.getConditions(className, attribute, fieldQuery, rootQuery, pathsQuery, termQuery);
    
    conditionList.addAll(getUnselectableConditions(className, attribute, fieldQuery, unselectableRootQuery, termQuery));

    Condition[] conditions = conditionList.toArray(new Condition[conditionList.size()]);

    if (value != null && !value.equals(""))
    {
      String[] tokens = value.split(" ");
      SelectablePrimitive[] searchables = new SelectablePrimitive[] { termQuery.getTermDisplayLabel().localize(Term.TERMDISPLAYLABEL), termQuery.getTermId(Term.TERMID) };

      QueryBuilder.textLookup(query, factory, tokens, searchables, selectables, conditions);
    }
    else
    {
      SelectablePrimitive orderBy = selectables[1];

      QueryBuilder.orderedLookup(query, factory, orderBy, selectables, conditions);
    }
    
    query.restrictRows(20, 1);
    
    System.out.println(query.getSQL());

    return query;
  }

  private static List<Condition> getUnselectableConditions(String className, String attribute, BrowserFieldQuery fieldQuery, BrowserRootQuery unselectableRootQuery, TermQuery termQuery)
  {
    List<Condition> conditions = new LinkedList<Condition>();

    if (attribute != null)
    {

      BrowserRootQuery countQuery = BrowserRoot.getAttributeRoots(className, attribute, new QueryFactory());
      countQuery.AND(countQuery.getSelectable().EQ(false));

      long count = countQuery.getCount();

      if (count > 0)
      {
//        termQuery.AND(Disease.getInactiveCriteria(unselectableRootQuery.getQueryFactory(), unselectableRootQuery.getTerm(), termQuery, false));
        
        conditions.add(unselectableRootQuery.getDisease().EQ(Disease.getCurrent()));
        conditions.add(unselectableRootQuery.getSelectable().EQ(false));
        conditions.add(unselectableRootQuery.field(fieldQuery));
        conditions.add(termQuery.getId().NEi(unselectableRootQuery.getTerm().getId()));
      }
    }

    return conditions;
  }

  private static List<Condition> getConditions(String className, String attribute, BrowserFieldQuery fieldQuery, BrowserRootQuery rootQuery, AllPathsQuery pathsQuery, TermQuery termQuery)
  {
    List<Condition> list = new LinkedList<Condition>();

    list.add(Disease.getInactiveCriteria(termQuery.getQueryFactory(), termQuery, false));

    if (className == null && attribute == null)
    {
      return list;
    }

    if (className != null && attribute != null)
    {
      String keyName = BrowserField.buildKey(className, attribute);

      Condition fieldCondition = fieldQuery.getKeyName().EQ(keyName);

      MdClassDAOIF mdClass = MdClassDAO.getMdClassDAO(className);

      for (MdClassDAOIF superClass : mdClass.getSuperClasses())
      {
        String key = BrowserField.buildKey(superClass.definesType(), attribute);

        fieldCondition = OR.get(fieldCondition, fieldQuery.getKeyName().EQ(key));
      }

      list.add(fieldCondition);
      list.add(Disease.getInactiveCriteria(rootQuery.getQueryFactory(), rootQuery.getTerm(), false));
      list.add(rootQuery.getDisease().EQ(Disease.getCurrent()));
      list.add(rootQuery.field(fieldQuery));
    }
    else if (className != null)
    {
      return Term.getGeoEntityTermConditions(className, pathsQuery, termQuery);
    }
    else if (attribute != null)
    {
      list.add(fieldQuery.getMdAttribute().EQ(attribute));
      list.add(rootQuery.getDisease().EQ(Disease.getCurrent()));
      list.add(rootQuery.field(fieldQuery));
    }

    list.add(pathsQuery.getChildTerm().EQ(termQuery));
    list.add(pathsQuery.getParentTerm().EQ(rootQuery.getTerm()));

    return list;
  }

  private static List<Condition> getGeoEntityTermConditions(String className, AllPathsQuery pathsQuery, TermQuery termQuery)
  {
    List<Condition> conditions = new LinkedList<Condition>();

    BrowserRootView[] views = BrowserRoot.getDefaultGeoRoots(className);

    if (views.length == 1)
    {
      conditions.add(pathsQuery.getChildTerm().EQ(termQuery));
      conditions.add(pathsQuery.getParentTerm().EQ(views[0].getTermId()));
    }
    else
    {
      conditions.add(pathsQuery.getChildTerm().EQ(termQuery));
      conditions.add(pathsQuery.getParentTerm().EQ(""));
    }

    return conditions;
  }
  
}
