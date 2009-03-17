package dss.vector.solutions;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.constants.MdTypeInfo;
import com.terraframe.mojo.constants.ServerConstants;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.io.dataDefinition.DeployedMetadataUpdater;
import com.terraframe.mojo.query.DISTINCT;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.system.metadata.MdTypeQuery;

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
    query.SELECT(new DISTINCT(mdType.getPackageName(MdTypeInfo.PACKAGE)));
    query.WHERE(mdType.getPackageName(MdTypeInfo.PACKAGE).LIKEi(args[1]));
    
    for (ValueObject object : query.getIterator())
      packages.add(object.getValue(MdTypeInfo.PACKAGE));
    packages.add("com.terraframe.mojo.defaults");
    
    DeployedMetadataUpdater.go(packages, ServerConstants.SYSTEM_USER_NAME, args[0]);
  }

}
