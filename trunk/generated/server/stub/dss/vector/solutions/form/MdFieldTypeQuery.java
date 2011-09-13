package dss.vector.solutions.form;

import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdFieldQuery;
import com.runwaysdk.system.metadata.MdTypeQuery;
import com.runwaysdk.system.metadata.MdWebBoolean;
import com.runwaysdk.system.metadata.MdWebCharacter;
import com.runwaysdk.system.metadata.MdWebDate;
import com.runwaysdk.system.metadata.MdWebDecimal;
import com.runwaysdk.system.metadata.MdWebDouble;
import com.runwaysdk.system.metadata.MdWebInteger;
import com.runwaysdk.system.metadata.MdWebLong;
import com.runwaysdk.system.metadata.MdWebText;

/**
 *
 * @author Autogenerated by RunwaySDK
 */
public class MdFieldTypeQuery extends dss.vector.solutions.form.MdFieldTypeQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1649662279;

  private MdTypeQuery mdTypeQuery;

  public MdFieldTypeQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.mdTypeQuery = new MdTypeQuery(queryFactory);
    this.buildQuery(new DefaultMdFieldTypeBuilder(queryFactory));
  }

  public MdFieldTypeQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultMdFieldTypeBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    public DefaultMdFieldTypeBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected MdFieldTypeQuery getViewQuery()
    {
      return (MdFieldTypeQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      MdFieldTypeQuery query = this.getViewQuery();
      query.map(MdFieldType.MDFIELDID, mdTypeQuery.getId("mdFieldId"));
      query.map(MdFieldType.DISPLAYLABEL, mdTypeQuery.getDisplayLabel().localize("displayLabel"));
      query.map(MdFieldType.DESCRIPTION, mdTypeQuery.getDescription().localize("description"));
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {
      MdFieldTypeQuery query = this.getViewQuery();
      String[] mdFieldIds = new String[] { MdBusiness.getMdBusiness(MdWebBoolean.CLASS).getId(),
          MdBusiness.getMdBusiness(MdWebCharacter.CLASS).getId(),
          MdBusiness.getMdBusiness(MdWebText.CLASS).getId(),
          MdBusiness.getMdBusiness(MdWebInteger.CLASS).getId(),
          MdBusiness.getMdBusiness(MdWebLong.CLASS).getId(),
          MdBusiness.getMdBusiness(MdWebDouble.CLASS).getId(),
          MdBusiness.getMdBusiness(MdWebDecimal.CLASS).getId(),
          MdBusiness.getMdBusiness(MdWebDate.CLASS).getId() };

      query.WHERE(mdTypeQuery.getId().IN(mdFieldIds));
      
      query.ORDER_BY_ASC(mdTypeQuery.getDisplayLabel().localize("displayLabel"));
    }

  }
}
