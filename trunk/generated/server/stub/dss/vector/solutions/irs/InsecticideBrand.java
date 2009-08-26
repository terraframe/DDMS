package dss.vector.solutions.irs;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.dataaccess.metadata.MdTypeDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.metadata.MdBusiness;
import com.terraframe.mojo.system.metadata.MdRelationship;

public class InsecticideBrand extends InsecticideBrandBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597944432L;

  public InsecticideBrand()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getBrandName();
  }

  public void populateView(InsecticideBrandView view)
  {
    view.setBrandName(this.getBrandName());
    view.setActiveIngredient(this.getActiveIngredient());
    view.setAmount(this.getAmount());
    view.setWeight(this.getWeight());
    view.setSachetsPerRefill(this.getSachetsPerRefill());
    view.setInsecticdeId(this.getId());
    view.setEnabled(this.getEnabled());
  }

  public static InsecticideBrand getByName(String name)
  {
    InsecticideBrandQuery query = new InsecticideBrandQuery(new QueryFactory());
    query.WHERE(query.getBrandName().EQ(name));
    OIterator<? extends InsecticideBrand> iterator = query.getIterator();
    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
    }
    finally
    {
      iterator.close();
    }
    return null;
  }
  
  public static InsecticideBrand validateByName(String name)
  {
    InsecticideBrand brand = InsecticideBrand.getByName(name);

    if(brand == null)
    {
      String msg = "An insecticide brand with the name [" + name + "] does not exist";

      throw new DataNotFoundException(msg, MdTypeDAO.getMdTypeDAO(InsecticideBrand.CLASS));
    }
    
    return brand;
  }

  
  public InsecticideBrandView getView()
  {
    InsecticideBrandView view = new InsecticideBrandView();

    this.populateView(view);

    return view;
  }

  public static InsecticideBrandView lockView(String id)
  {
    return InsecticideBrand.lock(id).getView();
  }

  public static InsecticideBrandView unlockView(String id)
  {
    return InsecticideBrand.unlock(id).getView();
  }

  public static InsecticideBrandView getView(String id)
  {
    return InsecticideBrand.get(id).getView();
  }

  @Transaction
  public static InsecticideBrand[] getAll()
  {
    List<InsecticideBrand> list = new LinkedList<InsecticideBrand>();
    InsecticideBrandQuery query = new InsecticideBrandQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends InsecticideBrand> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        list.add(it.next());
      }
      return list.toArray(new InsecticideBrand[list.size()]);
    }
    finally
    {
      it.close();
    }
  }

  @Transaction
  public static InsecticideBrand[] getAllActive()
  {
    List<InsecticideBrand> list = new LinkedList<InsecticideBrand>();
    InsecticideBrandQuery query = new InsecticideBrandQuery(new QueryFactory());
    query.WHERE(query.getEnabled().EQ(true));
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends InsecticideBrand> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        list.add(it.next());
      }
      return list.toArray(new InsecticideBrand[list.size()]);
    }
    finally
    {
      it.close();
    }
  }
/*
  public static void createTempTable(String tableName)
  {
    String sql = "DROP TABLE IF EXISTS " + tableName + ";\n";
    sql += "CREATE TEMP TABLE " + tableName + " AS ";
    sql += InsecticideBrand.getTempTableSQL() + ";\n";
    System.out.println(sql);
    Database.parseAndExecute(sql);
  }
*/
  public static String getTempTableSQL()
  {

    String select = "SELECT insecticidebrand.id,\n";
    // --% active ingredient in sachet (2) * weight of sachet (3) * number of sachets in can refill using nozzle 8002 (4) * Nozzle type ratio (6)
    //select += "insecticidebrand.brandname,\n";
    select += "weight*sachetsperrefill*ratio*(amount/100.0) AS active_ingredient_per_can,\n";
    select += "nozzle.ratio AS nozzle_ratio,\n";
    select += "nozzle.displaylabel AS nozzle_defaultLocale,\n";
    select += "insecticidenozzle.enabled,\n";

    String from = "FROM ";
    from += MdBusiness.getMdBusiness(InsecticideBrand.CLASS).getTableName() + " AS insecticidebrand,\n";
    from += MdBusiness.getMdBusiness(Nozzle.CLASS).getTableName() +  " AS nozzle,\n";
    from += MdRelationship.getMdElement(InsecticideNozzle.CLASS).getTableName() + " AS insecticidenozzle\n,";

    String where = "";
    where += "AND insecticidenozzle.parent_id = insecticidebrand.id \n";
    where += "AND insecticidenozzle.child_id = nozzle.id \n";

    select = select.substring(0, select.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);
    from = from.substring(0, from.length() - 2);

    return select + "\n" + from + "\n" + where;
    // --nozzle.enabled,
    // --nozzle.lastupdatedate,
  }

}
