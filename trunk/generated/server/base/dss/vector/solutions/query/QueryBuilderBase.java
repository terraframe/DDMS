package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -1622176008)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to QueryBuilder.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class QueryBuilderBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.QueryBuilder";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -1622176008;
  
  public QueryBuilderBase()
  {
    super();
  }
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.QueryBuilder.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static QueryBuilder get(String id)
  {
    return (QueryBuilder) com.terraframe.mojo.business.View.get(id);
  }
  
  public static java.io.InputStream exportQueryToCSV(java.lang.String queryType, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryBuilder.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.io.InputStream exportQueryToExcel(java.lang.String queryType, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryBuilder.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.terraframe.mojo.query.ValueQuery getQueryResults(java.lang.String queryType, java.lang.String queryXML, java.lang.String config, java.lang.String sortBy, java.lang.Boolean ascending, java.lang.Integer pageNumber, java.lang.Integer pageSize)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryBuilder.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String mapQuery(java.lang.String queryType, java.lang.String queryXML, java.lang.String thematicLayerType, java.lang.String[] universalLayers, java.lang.String savedSearchId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.QueryBuilder.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
