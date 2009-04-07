package dss.vector.solutions.util;

import com.terraframe.mojo.constants.MdBusinessInfo;
import com.terraframe.mojo.constants.MdViewInfo;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.system.metadata.MdClassQuery;
import com.terraframe.mojo.system.metadata.MdElementQuery;

import dss.vector.solutions.util.FacadeBase;

public abstract class Facade extends FacadeBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236374191440L;
  
  public Facade()
  {
    super();
  }
  
  public static MdClassQuery getMDSSClasses()
  {
    QueryFactory queryFactory = new QueryFactory();
    MdElementQuery elementQuery = new MdElementQuery(queryFactory);
    elementQuery.WHERE(elementQuery.getIsAbstract().EQ(true));
    
    ValueQuery valueQuery = queryFactory.valueQuery();
    valueQuery.SELECT(elementQuery.getId());
    valueQuery.WHERE(elementQuery.getIsAbstract().EQ(true));
    
    MdClassQuery classQuery = new MdClassQuery(queryFactory);
    classQuery.WHERE(classQuery.getPackageName().LIKEi("dss.vector.solutions.%"));
    Condition or = OR.get(classQuery.getType().EQ(MdBusinessInfo.CLASS), classQuery.getType().EQ(MdViewInfo.CLASS));
    classQuery.WHERE(or);
    classQuery.WHERE(classQuery.NOT_IN(classQuery.getId(), valueQuery));
    classQuery.ORDER_BY_ASC(classQuery.getDisplayLabel());
    
    return classQuery;
  }
}
