package dss.vector.solutions.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdBusinessQuery;
import com.runwaysdk.system.metadata.MdViewQuery;

import dss.vector.solutions.odk.ODKForm;
import dss.vector.solutions.ontology.AllPaths;

public class ODKFormMetadata extends ODKFormMetadataBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1665542107;
  
  public ODKFormMetadata()
  {
    super();
  }
  
  public static Boolean getPopInstanceFormName(ODKForm form)
  {
    MdClassDAOIF mdClass = form.getViewMd();
    
    ODKFormMetadata meta = ODKFormMetadata.getMetadata(mdClass);
    
    if (meta == null && mdClass.getSuperClass() != null)
    {
      meta = ODKFormMetadata.getMetadata(mdClass.getSuperClass());
    }
    
    if (meta != null)
    {
      return meta.getPopFormInstName();
    }
    
    return false;
  }
  
  public static ODKFormMetadata getMetadata(MdClassDAOIF mdClass)
  {
    // Runway Query TODO : This is how I thought the query should have been done:
    // Unfortunately it didn't generate working SQL so I had to manually write it.
    
//    QueryFactory qf = new QueryFactory();
//    MdViewQuery mdvq = new MdViewQuery(qf);
//    ODKFormMetadataQuery query = new ODKFormMetadataQuery(qf);
//    query.WHERE(query.getReferencedMdClass().EQ(mdvq));
//    query.WHERE(mdvq.getId().EQ(mdClass.getId()).OR(mdvq.getSuperMdView().getId().EQ(mdClass.getId())));
//    
//    OIterator<? extends ODKFormMetadata> it = query.getIterator();
//
//    try
//    {
//      if (it.hasNext())
//      {
//        return it.next();
//      }
//    }
//    finally
//    {
//      it.close();
//    }
    
    
    String mdcId = mdClass.getId();
    
    ResultSet resultSet = Database.query("select odkf.id odkId\n" + 
        "from \n" + 
        "  odkf_orm_metadata odkf\n" + 
        "  left join md_view mdv on odkf.referenced_md_class = mdv.id\n" + 
        "  left join md_business mdb on odkf.referenced_md_class = mdb.id\n" + 
        "where\n" + 
        "  mdv.id = '" + mdcId + "'\n" + 
        "  OR mdb.id = '" + mdcId + "'\n" + 
        "  OR mdv.super_md_view = '" + mdcId + "'\n" + 
        "  OR mdb.super_md_business = '" + mdcId + "'");

    try
    {
      while (resultSet.next())
      {
        String odkId = resultSet.getString("odkId");

        return ODKFormMetadata.get(odkId);
      }
    }
    catch (SQLException sqlEx1)
    {
      Database.throwDatabaseException(sqlEx1);
    }
    finally
    {
      try
      {
        java.sql.Statement statement = resultSet.getStatement();
        resultSet.close();
        statement.close();
      }
      catch (SQLException sqlEx2)
      {
        Database.throwDatabaseException(sqlEx2);
      }
    }

    return null;
  }
}
