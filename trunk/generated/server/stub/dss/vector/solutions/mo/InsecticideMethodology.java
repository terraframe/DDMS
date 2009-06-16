package dss.vector.solutions.mo;


import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

public class InsecticideMethodology extends InsecticideMethodologyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235957080309L;
  
  public InsecticideMethodology()
  {
    super();
  }

  public static InsecticideMethodology[] getAll()
  {
    InsecticideMethodologyQuery query = new InsecticideMethodologyQuery(new QueryFactory());
    List<InsecticideMethodology> list = AbstractTerm.getAll(query, InsecticideMethodology.class);

    return list.toArray(new InsecticideMethodology[list.size()]);
  }
  
  public static InsecticideMethodology[] getAllActive()
  {
    InsecticideMethodologyQuery query = new InsecticideMethodologyQuery(new QueryFactory());
    List<InsecticideMethodology> list = AbstractTerm.getAllActive(query, InsecticideMethodology.class);

    return list.toArray(new InsecticideMethodology[list.size()]);
  }  
}