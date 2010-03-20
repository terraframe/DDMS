package dss.vector.solutions;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.constants.MdTypeInfo;
import com.runwaysdk.constants.ServerConstants;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.io.dataDefinition.DeployedMetadataUpdater;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdTypeQuery;

public class MdssDmu
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    List<String> packages = new LinkedList<String>();
    QueryFactory queryFactory = new QueryFactory();
    MdTypeQuery mdType = new MdTypeQuery(queryFactory);
    ValueQuery query = queryFactory.valueQuery();
    query.SELECT_DISTINCT(mdType.getPackageName(MdTypeInfo.PACKAGE));
    query.WHERE(mdType.getPackageName(MdTypeInfo.PACKAGE).LIKEi(args[1]));
    
    for (ValueObject object : query.getIterator())
      packages.add(object.getValue(MdTypeInfo.PACKAGE));
    packages.add("com.runwaysdk.defaults");
    
    DeployedMetadataUpdater.go("/datatype_gis.xsd", packages, ServerConstants.SYSTEM_USER_NAME, args[0]);
  }

}
