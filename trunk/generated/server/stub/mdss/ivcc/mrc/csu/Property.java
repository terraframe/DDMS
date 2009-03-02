package mdss.ivcc.mrc.csu;


import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class Property extends PropertyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235777070211L;
  
  public Property()
  {
    super();
  }
  
  public static mdss.ivcc.mrc.csu.Property getByPackageAndName(java.lang.String pkg, java.lang.String name)
  {

	Property prop = null;
    
    QueryFactory factory = new QueryFactory();
    PropertyQuery query = new PropertyQuery(factory);    

    query.AND(query.getPropertyPackage().EQ(pkg));
    query.AND(query.getPropertyName().EQ(name));
    OIterator<? extends Property> iterator = query.getIterator();
    
    if(iterator.hasNext())
    {
      prop = iterator.next();
    }
    
    iterator.close();
    
    return prop;
  }
  
  public static java.lang.String getStr(java.lang.String pkg, java.lang.String name)
  {
	mdss.ivcc.mrc.csu.Property prop = getByPackageAndName(pkg,name);
	if(prop == null)
	{
		return null;
	}
	else
	{
    return prop.getPropertyValue();
	}
  }
  
  public static java.lang.Integer getInt(java.lang.String pkg, java.lang.String name)
  {
    String value = mdss.ivcc.mrc.csu.Property.getStr(pkg,name);

	if(value == null)
	{
		return null;
	}
	else
	{
		return new Integer(value);
	}
    
  }
  
 
}
