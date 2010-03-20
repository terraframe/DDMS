package dss.vector.solutions.synchronization;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.MdTypeDAOIF;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdAttributeReferenceQuery;
import com.runwaysdk.system.metadata.MdType;

public class SynchronizedTypeView extends SynchronizedTypeViewBase implements Reloadable, Comparable<SynchronizedTypeView>
{
  private static final long serialVersionUID = -1875728697;

  public SynchronizedTypeView()
  {
    super();
  }

  @Override
  public void apply()
  {
    MdType mdType = MdType.lock(this.getMdTypeId());
    Boolean oldValue = mdType.getExported();
    Boolean newValue = this.getExported();
    if (!oldValue.equals(newValue))
    {
      mdType.setExported(newValue);
      mdType.apply();
    }
    else
    {
      mdType.unlock();
    }
  }

  @Transaction
  public static void confirmAll(SynchronizedTypeView[] views)
  {
    for (SynchronizedTypeView view : views)
    {
      view.apply();
    }
  }

  public static SynchronizedTypeView[] getAll()
  {
    SynchronizedTypeViewQuery query = new SynchronizedTypeViewQuery(new QueryFactory());
    query.WHERE(query.getExported().EQ(true));
    List<? extends SynchronizedTypeView> list = query.getIterator().getAll();
    return list.toArray(new SynchronizedTypeView[list.size()]);
  }

  public static SynchronizedTypeView[] getDependencies(String[] rootTypes)
  {
    Set<SynchronizedTypeView> set = new TreeSet<SynchronizedTypeView>();
    for (String root : rootTypes)
    {
      SynchronizedTypeView.getByMdTypeId(root).recurse(set);
    }
    return set.toArray(new SynchronizedTypeView[set.size()]);
  }

  public static SynchronizedTypeView getByMdTypeId(String id)
  {
    MdType mdType = MdType.get(id);
    SynchronizedTypeView view = new SynchronizedTypeView();
    view.setMdTypeId(id);
    view.setExported(mdType.getExported());
    view.setQualifiedType(mdType.definesType());
    view.setDisplayLabel(mdType.getDisplayLabel().getValue());
    view.setDescription(mdType.getDescription().getValue());
    return view;
  }

  private void recurse(Set<SynchronizedTypeView> set)
  {
    if (!set.add(this))
    {
      // The type has already been added
      return;
    }

    MdTypeDAOIF mdTypeDAOIF = MdTypeDAO.get(this.getMdTypeId());

    // First, add all children
    if (mdTypeDAOIF instanceof MdClassDAOIF)
    {
      MdClassDAOIF mdClassDAOIF = (MdClassDAOIF) mdTypeDAOIF;
      for (MdClassDAOIF subClass : mdClassDAOIF.getSubClasses())
      {
        SynchronizedTypeView.getByMdTypeId(subClass.getId()).recurse(set);
      }
    }

    // Second, include all relationships on this type
    if (mdTypeDAOIF instanceof MdBusinessDAOIF)
    {
      MdBusinessDAOIF mdBusinessDAOIF = (MdBusinessDAOIF) mdTypeDAOIF;
      for (MdRelationshipDAOIF rel : mdBusinessDAOIF.getParentMdRelationshipsOrdered())
      {
        SynchronizedTypeView.getByMdTypeId(rel.getId()).recurse(set);
        SynchronizedTypeView.getByMdTypeId(rel.getChildMdBusiness().getId()).recurse(set);
      }
      for (MdRelationshipDAOIF rel : mdBusinessDAOIF.getChildMdRelationshipsOrdered())
      {
        SynchronizedTypeView.getByMdTypeId(rel.getId()).recurse(set);
        SynchronizedTypeView.getByMdTypeId(rel.getParentMdBusiness().getId()).recurse(set);
      }
    }
    // Finally, add all types that are referenced by attributes
    QueryFactory qf = new QueryFactory();
    SynchronizedTypeViewQuery vQuery = new SynchronizedTypeViewQuery(qf);
    MdAttributeReferenceQuery arq = new MdAttributeReferenceQuery(qf);

    ValueQuery value = new ValueQuery(qf);
    String thisId = "t1";
    String referencedId = "t2";
    value.SELECT_DISTINCT(arq.getMdBusiness().getId(referencedId));
    value.WHERE(arq.getDefiningMdClass().getId(thisId).EQ(this.getMdTypeId()));

    vQuery.WHERE(vQuery.getQualifiedTypeConcat().LIKE("dss.*"));
    vQuery.WHERE(vQuery.getExported().EQ(false));
    vQuery.WHERE(vQuery.getMdTypeId().SUBSELECT_IN(value.get(referencedId)));

    for (SynchronizedTypeView dependency : vQuery.getIterator())
    {
      dependency.recurse(set);
    }
  }

  public static SynchronizedTypeViewQuery getQuery(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    SynchronizedTypeViewQuery query = new SynchronizedTypeViewQuery(new QueryFactory(), sortAttribute, isAscending, pageSize, pageNumber);
    query.WHERE(query.getExported().EQ(true));
    
    Selectable selectable = query.getComponentQuery().getSelectableRef(sortAttribute);

    if (isAscending)
    {
      query.ORDER_BY_ASC((SelectablePrimitive) selectable, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC((SelectablePrimitive) selectable, sortAttribute);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }
        
    return query;
  }

  public static SynchronizedTypeViewQuery search(String query)
  {
    String wildcard = "*" + query + "*";
    QueryFactory queryFactory = new QueryFactory();
    SynchronizedTypeViewQuery vQuery = new SynchronizedTypeViewQuery(queryFactory);
    vQuery.WHERE(OR.get(vQuery.getDescription().LIKEi(wildcard), vQuery.getDisplayLabel().LIKEi(wildcard), vQuery.getQualifiedTypeConcat().LIKEi(wildcard)));
    vQuery.AND(vQuery.getExported().EQ(false));
    return vQuery;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (! ( obj instanceof SynchronizedTypeView ))
    {
      return super.equals(obj);
    }

    SynchronizedTypeView view = (SynchronizedTypeView) obj;
    return view.getQualifiedType().equals(this.getQualifiedType());
  }

  public MdType getMdType()
  {
    return MdType.get(this.getMdTypeId());
  }

  public int compareTo(SynchronizedTypeView o)
  {
    return this.getQualifiedType().compareTo(o.getQualifiedType());
  }

  // static class DisabledSynchronizedTypeViewBuilder extends ViewQueryBuilder
  // implements Reloadable
  // {
  // private MdTypeQuery mdTypeQuery;
  // private SynchronizedTypeQuery stq;
  // public DisabledSynchronizedTypeViewBuilder(QueryFactory queryFactory)
  // {
  // super(queryFactory);
  //      
  // mdTypeQuery = new MdTypeQuery(queryFactory);
  // stq = new SynchronizedTypeQuery(queryFactory);
  // }
  //
  // protected SynchronizedTypeViewQuery getViewQuery()
  // {
  // return (SynchronizedTypeViewQuery)super.getViewQuery();
  // }
  //
  // /**
  // * build the select clause
  // */
  // protected void buildSelectClause()
  // {
  // SynchronizedTypeViewQuery vQuery = this.getViewQuery();
  //
  // vQuery.map(SynchronizedTypeView.MDTYPEID, mdTypeQuery.getId());
  // vQuery.map(SynchronizedTypeView.QUALFIFIEDTYPE,
  // F.CONCAT(F.CONCAT(mdTypeQuery.getPackageName(), "."),
  // mdTypeQuery.getTypeName()));
  // vQuery.map(SynchronizedTypeView.DISPLAYLABEL,
  // mdTypeQuery.getDisplayLabel().currentLocale());
  // vQuery.map(SynchronizedTypeView.DESCRIPTION,
  // mdTypeQuery.getDescription().currentLocale());
  // }
  //
  // /**
  // * Implement only if additional join criteria is required.
  // */
  // protected void buildWhereClause()
  // {
  // SynchronizedTypeViewQuery vQuery = this.getViewQuery();
  //      
  // vQuery.WHERE(mdTypeQuery.getId().SUBSELECT_NOT_IN(stq.getMdssType()));
  // }
  // }
}
