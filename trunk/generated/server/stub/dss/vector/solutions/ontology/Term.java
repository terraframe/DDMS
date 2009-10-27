package dss.vector.solutions.ontology;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.terraframe.mojo.business.RelationshipQuery;
import com.terraframe.mojo.constants.RelationshipInfo;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.metadata.MdAttributeDAO;
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
import com.terraframe.mojo.system.metadata.MdRelationship;

import dss.vector.solutions.UnknownTermException;
import dss.vector.solutions.query.ActionNotAllowedException;
import dss.vector.solutions.surveillance.OptionIF;

public abstract class Term extends TermBase implements Reloadable, OptionIF
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
    return ontology.getKeyName() + ":" + this.getTermName();
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

  public String toString()
  {
    return this.getTermName() + " (" + this.getTermId() + ")";
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

    q.restrictRows(15, 1);

    return q;
  }

  @Override
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

        String error = "The child [" + childDL + "] already has a direct relationship with parent [" + parentDL + "].";
        DuplicateParentException e = new DuplicateParentException(error);
        e.setChildTerm(this.toString());
        e.setParentTerm(parent.toString());

        throw e;
      }
    }

    this.addIsA(parent).apply();

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

  public static TermViewQuery getDefaultRoots()
  {
    QueryFactory f = new QueryFactory();

    // FIXME pass in the ontology and rel and switch on that
    TermQuery termQuery;
    TermRelationshipQuery termRelQuery;
    if (true /* || Ontology === MO */)
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
    private Term      parent;

    private TermQuery termQuery;

    private String    searchValue;

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

      String search = this.searchValue + "%";
      query.WHERE(OR.get(termQuery.getTermName().LIKEi(search), termQuery.getTermId().LIKEi(search)));

      query.ORDER_BY_ASC(this.termQuery.getTermName());
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
      query.AND(termQuery.parentTerm(this.termRelQuery)); // FIXME parent-child
      // label reversed

      query.ORDER_BY_ASC(this.termQuery.getTermName());
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

      // query.WHERE(this.termQuery.NOT_IN(this.termQuery.getId(),
      // this.valueQuery));
      query.WHERE(this.termQuery.getId().SUBSELECT_NOT_IN(this.valueQuery));

      query.ORDER_BY_ASC(this.termQuery.getTermName());
    }
  }

  public static Term validateByDisplayLabel(String displayLabel, MdAttributeDAOIF mdAttribute)
  {
    QueryFactory factory = new QueryFactory();
    TermQuery query = new TermQuery(factory);

    query.WHERE(query.getTermName().EQ(displayLabel));

    OIterator<? extends Term> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      String attributeLabel = mdAttribute.getDisplayLabel(Session.getCurrentLocale());
      String msg = "Unknown " + attributeLabel + " with the given name [" + displayLabel + "]";

      UnknownTermException e = new UnknownTermException(msg);
      e.setTermName(displayLabel);
      e.setAttributeLabel(attributeLabel);
      e.apply();

      throw e;
    }
    finally
    {
      iterator.close();
    }
  }

  protected static List<String> getRecursiveParentIds(String childId, String ontologyMdRelationshipId)
  {
    QueryFactory queryFactory = new QueryFactory();

    MdRelationship ontologyMdRelationship = MdRelationship.get(ontologyMdRelationshipId);
    RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(ontologyMdRelationship.definesType());

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    valueQuery.SELECT(relationshipQuery.parentId(RelationshipInfo.PARENT_ID, RelationshipInfo.PARENT_ID));
    valueQuery.WHERE(relationshipQuery.childId().EQ(childId));

    List<ValueObject> valueObjectList = valueQuery.getIterator().getAll();

    List<String> parentIdList = new LinkedList<String>();

    for (ValueObject valueObject : valueObjectList)
    {
      String parentId = valueObject.getValue(RelationshipInfo.PARENT_ID);
      parentIdList.add(parentId);
      parentIdList.addAll(getRecursiveParentIds(parentId, ontologyMdRelationshipId));
    }

    return parentIdList;
  }

  protected static List<String> getChildIds(String parentId, String ontologyMdRelationshipId)
  {
    QueryFactory queryFactory = new QueryFactory();

    MdRelationship ontologyMdRelationship = MdRelationship.get(ontologyMdRelationshipId);
    RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(ontologyMdRelationship.definesType());

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    valueQuery.SELECT(relationshipQuery.childId(RelationshipInfo.CHILD_ID, RelationshipInfo.CHILD_ID));
    valueQuery.WHERE(relationshipQuery.parentId().EQ(parentId));

    List<ValueObject> valueObjectList = valueQuery.getIterator().getAll();

    List<String> childOfChildIdList = new LinkedList<String>();

    for (ValueObject valueObject : valueObjectList)
    {
      String childId = valueObject.getValue(RelationshipInfo.CHILD_ID);
      childOfChildIdList.add(childId);
    }

    return childOfChildIdList;
  }

  public static Term[] getAllTermsByAttribute(MdAttribute mdAttribute)
  {
    return Term.getRootChildren(MdAttributeDAO.getByKey(mdAttribute.getKey()), true);
  }

  public static Term[] getRootChildren(MdAttributeDAOIF mdAttribute)
  {
    return Term.getRootChildren(mdAttribute, true);
  }

  /**
   * @param mdAttribute
   * @return Returns selectable roots and every roots direct descendants for a
   *         given MdAttribute
   */
  public static Term[] getRootChildren(MdAttributeDAOIF mdAttribute, Boolean returnOnlySelectable)
  {
    Set<Term> children = new TreeSet<Term>(new TermComparator());

    String className = mdAttribute.definedByClass().definesType();
    BrowserRootView[] roots = BrowserRoot.getAttributeRoots(className, mdAttribute.definesAttribute());

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

  public String getOptionName()
  {
    return this.getTermName();
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
