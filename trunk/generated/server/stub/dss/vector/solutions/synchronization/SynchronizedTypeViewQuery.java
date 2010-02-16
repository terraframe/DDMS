package dss.vector.solutions.synchronization;

import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.query.CONCAT;
import com.terraframe.mojo.query.F;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ViewQueryBuilder;
import com.terraframe.mojo.query.OrderBy.SortOrder;
import com.terraframe.mojo.system.metadata.MdTypeQuery;

/**
 *
 * @author Autogenerated by TerraFrame
 */
public class SynchronizedTypeViewQuery extends SynchronizedTypeViewQueryBase  implements Reloadable
{
  private MdTypeQuery mdTypeQuery;
  
  private String sortAttribute;
  
  private SortOrder sortOrder;
  
  private Integer pageSize;
  
  private Integer pageNumber;

  public SynchronizedTypeViewQuery(QueryFactory queryFactory)
  {
    this(queryFactory, "", true, 20, 1);
  }
  
  public SynchronizedTypeViewQuery(QueryFactory queryFactory, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    super(queryFactory);

    this.sortAttribute = sortAttribute;
    this.sortOrder = ascending==null ? null : ascending ? SortOrder.ASC : SortOrder.DESC;
    this.pageSize = pageSize;
    this.pageNumber = pageNumber;
    
    mdTypeQuery = new MdTypeQuery(queryFactory);
    this.buildQuery(new DefaultSynchronizedTypeViewBuilder(queryFactory));
  }
  
  public CONCAT getQualifiedTypeConcat()
  {
    return (CONCAT)this.getSelectable(SynchronizedTypeView.QUALIFIEDTYPE, null, null);
  }

  class DefaultSynchronizedTypeViewBuilder extends ViewQueryBuilder implements Reloadable
  {
    public DefaultSynchronizedTypeViewBuilder(QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected SynchronizedTypeViewQuery getViewQuery()
    {
      return (SynchronizedTypeViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      SynchronizedTypeViewQuery vQuery = this.getViewQuery();

      vQuery.map(SynchronizedTypeView.MDTYPEID, mdTypeQuery.getId());
      vQuery.map(SynchronizedTypeView.EXPORTED, mdTypeQuery.getExported());
      vQuery.map(SynchronizedTypeView.QUALIFIEDTYPE, F.CONCAT(F.CONCAT(mdTypeQuery.getPackageName(), "."), mdTypeQuery.getTypeName()));
      vQuery.map(SynchronizedTypeView.DISPLAYLABEL, mdTypeQuery.getDisplayLabel().getSessionLocale());
      vQuery.map(SynchronizedTypeView.DESCRIPTION, mdTypeQuery.getDescription().getSessionLocale());
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {
      SynchronizedTypeViewQuery vQuery = this.getViewQuery();
      
      if (sortAttribute.equals(SynchronizedTypeView.DESCRIPTION)) vQuery.ORDER_BY(getDescription(), sortOrder);
      if (sortAttribute.equals(SynchronizedTypeView.DISPLAYLABEL)) vQuery.ORDER_BY(getDisplayLabel(), sortOrder);
      if (sortAttribute.equals(SynchronizedTypeView.QUALIFIEDTYPE)) vQuery.ORDER_BY(getQualifiedTypeConcat(), sortOrder);

      if(pageSize != null && pageNumber != null)
      {
        vQuery.restrictRows(pageSize, pageNumber);
      }
    }

  }
}
