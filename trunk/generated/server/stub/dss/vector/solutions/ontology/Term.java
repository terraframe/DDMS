package dss.vector.solutions.ontology;

import java.sql.Savepoint;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.terraframe.mojo.constants.RelationshipInfo;
import com.terraframe.mojo.dataaccess.DuplicateGraphPathException;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.dataaccess.metadata.MdAttributeDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.query.GeneratedViewQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ViewQueryBuilder;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.system.metadata.MdAttribute;
import com.terraframe.mojo.system.metadata.MdAttributeReference;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.UnknownTermProblem;
import dss.vector.solutions.query.ActionNotAllowedException;
import dss.vector.solutions.surveillance.OptionIF;

public class Term extends TermBase implements Reloadable, OptionIF
{
  private static final long serialVersionUID = 1253040031928L;

  public Term()
  {
    super();
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

  /**
   * Throws an exception to alert the user before they try to delete a Term.
   *
   * @throws ConfirmDeleteTermException
   *           If the Term has more than one parent.
   * @throws
   */
  @Override
  public void confirmDeleteTerm(String parentId)
  {
    // V1 Restriction
    throw new ActionNotAllowedException();

    /*
     * List<GeoEntity> parents = this.getImmediateParents(); if (parents.size()
     * > 1) { GeoEntity parent = GeoEntity.get(parentId);
     * ConfirmDeleteEntityException ex = new ConfirmDeleteEntityException();
     * ex.setEntityName(parent.getEntityName());
     *
     * throw ex; } else { this.delete(); }
     */
  }

  @Override
  public void apply()
  {
    // Use the name as the display label if no value is given
    String display= this.getDisplay();
    if(display== null || display.length() == 0)
    {
      this.setDisplay(this.getName());
    }
    
    // If this is new, set the Ontology value to the MO ontology.
    // FIXME this will be removed once Term subclasses are refactored
    // out and Term will be come concrete.
    if(this.isNew())
    {
      Ontology moOntology = Ontology.getByKey(MO.KEY);
      // There WILL be one record; otherwise, the application was not set up properly
      this.setOntology(moOntology);
    }

    super.apply();
  }


  public String toString()
  {
    return this.getName() + " (" + this.getTermId() + ")";
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

  @Override
  @Transaction
  public TermView applyWithParent(String parentTermId, Boolean cloneOperation)
  {
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

    if (!cloneOperation)
    {
      // V1 Restriction
      if (!isNew)
      {
        throw new ActionNotAllowedException();
      }
      /*
       * OIterator<? extends LocatedIn> iter =
       * this.getAllLocatedInGeoEntityRel(); try { while (iter.hasNext()) {
       * iter.next().delete(); } } finally { iter.close(); }
       */
    }
    else
    {
      // Heads up: is this check even necessary?  Doesn't the Graph already enforce this?

      // confirm this entity can't be applied to the same
      // parent more than once.
      QueryFactory f = new QueryFactory();
      TermRelationshipQuery q = new TermRelationshipQuery(f);
      q.WHERE(q.childId().EQ(this.getId()).
          AND(q.parentId().EQ(parentTermId)));

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
    termRelationship.setOntologyRelationship(OntologyRelationship.getByKey(OBO.IS_A));

    // create save point
    Savepoint savepoint = Database.setSavepoint();
    try
    {
      termRelationship.apply();
    }
    catch (DuplicateGraphPathException e)
    {
      // a relationship between this typedef and the parent and the child already exists
      Database.rollbackSavepoint(savepoint);
    }
    finally
    {
      Database.releaseSavepoint(savepoint);
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

  /**
   * Returns all default roots (Terms without parents). This method
   * WILL return all Terms regardless of obsolete status. To return the
   * terms with obsolete marked as false, use BrowserRoot.getDefaultRoot().
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
    QueryFactory f = new QueryFactory();

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
//      query.map(TermView.TERMNAME, termQuery.getName());
      query.map(TermView.TERMNAME, termQuery.getDisplay());
      query.map(TermView.TERMONTOLOGYID, termQuery.getTermId());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      // restrict by the term ids (ordering will be done client-side)
      if(this.termIds != null && this.termIds.length > 0)
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

  /**
   * Query builder for searching on Terms by name and id.
   */
  private static class SearchQueryBuilder extends ViewQueryBuilder implements Reloadable
  {
    private String[] parentIds;

    private TermQuery termQuery;

    // AllPaths is used to restrict the query by parent term Ids.
    private AllPathsQuery pathsQuery;

    private String    searchValue;

    protected SearchQueryBuilder(QueryFactory queryFactory, String searchValue, String[] parentIds)
    {
      super(queryFactory);

      this.parentIds = parentIds;
      this.searchValue = searchValue;
      this.termQuery = new TermQuery(queryFactory);
      this.pathsQuery= new AllPathsQuery(queryFactory);
    }

    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      query.map(TermView.TERMID, termQuery.getId());
//      query.map(TermView.TERMNAME, termQuery.getName());
      query.map(TermView.TERMNAME, termQuery.getDisplay());
      query.map(TermView.TERMONTOLOGYID, termQuery.getTermId());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      String search = this.searchValue + "%";
      query.WHERE(OR.get(termQuery.getName().LIKEi(search), termQuery.getTermId().LIKEi(search)));

      if(this.parentIds.length > 0)
      {
        query.AND(this.pathsQuery.getChildTerm().EQ(this.termQuery));
        query.AND(this.pathsQuery.getParentTerm().IN(this.parentIds));
      }
      else
      {
        // There are no Parent terms meaning no roots have been set. Searching is not
        // allowed without roots.
        query.AND(termQuery.getId().EQ(""));
      }
      
      query.ORDER_BY_ASC(this.termQuery.getDisplay());
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
    
    private Boolean filterObsolete;

    protected GetChildrenQueryBuilder(QueryFactory queryFactory, Term parent, Boolean filterObsolete)
    {
      super(queryFactory);

      this.parent = parent;
      this.termQuery = new TermQuery(queryFactory);
      this.termRelQuery = new TermRelationshipQuery(queryFactory);
      this.filterObsolete = filterObsolete;
    }

    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      query.map(TermView.TERMID, termQuery.getId());
//      query.map(TermView.TERMNAME, termQuery.getName());
      query.map(TermView.TERMNAME, termQuery.getDisplay());
      query.map(TermView.TERMONTOLOGYID, termQuery.getTermId());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      query.WHERE(this.termRelQuery.parentId().EQ(this.parent.getId()));
      query.AND(termQuery.parentTerm(this.termRelQuery)); // FIXME parent-child
      // label reversed
      
      if(this.filterObsolete)
      {
        query.AND(termQuery.getObsolete().EQ(false));
      }

//      query.ORDER_BY_ASC(this.termQuery.getName());
      query.ORDER_BY_ASC(this.termQuery.getDisplay());
    }

  }

  /**
   * Queries for the root Term of a given ontology.
   */
  private static class DefaultRootQueryBuilder extends ViewQueryBuilder implements Reloadable
  {
    private TermQuery             termQuery;

    private ValueQuery            valueQuery;

    private TermRelationshipQuery termRelQuery;
    
    private boolean filterObsolete;

    protected DefaultRootQueryBuilder(QueryFactory queryFactory, TermQuery termQuery, TermRelationshipQuery termRelQuery, boolean filterObsolete)
    {
      super(queryFactory);

      this.termQuery = termQuery;
      this.valueQuery = queryFactory.valueQuery();
      this.termRelQuery = termRelQuery;
      this.filterObsolete = filterObsolete;
    }

    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      query.map(TermView.TERMID, this.termQuery.getId());
//      query.map(TermView.TERMNAME, this.termQuery.getName());
      query.map(TermView.TERMNAME, termQuery.getDisplay());
      query.map(TermView.TERMONTOLOGYID, termQuery.getTermId());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery query = this.getViewQuery();

      // the root is not a child of any other term
      Selectable childId = this.termRelQuery.childId();
      this.valueQuery.SELECT(childId);

      // query.WHERE(this.termQuery.NOT_IN(this.termQuery.getId(),
      // this.valueQuery));
      query.WHERE(this.termQuery.getId().SUBSELECT_NOT_IN(this.valueQuery));
      
      if(this.filterObsolete)
      {
        query.AND(termQuery.getObsolete().EQ(false));
      }

//      query.ORDER_BY_ASC(this.termQuery.getName());
      query.ORDER_BY_ASC(this.termQuery.getDisplay());
    }
  }

  @Transaction
  public static Term validateByDisplayLabel(String displayLabel, MdAttributeDAOIF mdAttribute)
  {
    QueryFactory factory = new QueryFactory();
    TermQuery query = new TermQuery(factory);

    query.WHERE(query.getName().EQ(displayLabel));

    OIterator<? extends Term> iterator = query.getIterator();

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

      // We expect to return nothing, as we're throwing a problem, but include this to satisfy the compile time requirement
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
   * field described by the given class and attribute names. Inheritance is already
   * factored into the method such that if B extends A and A defines attribute m, the
   * following calls are valid:
   *
   * 1) Term.getAllTermsForField("A", "m")
   * 2) Term.getAllTermsForField("B", "m")
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

    return children.toArray(new Term[children.size()]);
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

    for(MdAttribute mdAttr : md.getAllAttribute())
    {
      if(mdAttr instanceof MdAttributeReference
           && ((MdAttributeReference)mdAttr).getMdBusiness().definesType().equals(Term.CLASS))
      {
        list.add(( (MdAttributeReference) mdAttr ).getAttributeName());
      }
    }

    return list.toArray(new String[list.size()]);
  }


}
