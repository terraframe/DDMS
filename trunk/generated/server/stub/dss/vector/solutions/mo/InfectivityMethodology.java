package dss.vector.solutions.mo;


import java.util.List;

import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.query.QueryFactory;

public class InfectivityMethodology extends InfectivityMethodologyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235957068443L;
  
  public InfectivityMethodology()
  {
    super();
  }
  
  public static InfectivityMethodology[] getAll()
  {
    InfectivityMethodologyQuery query = new InfectivityMethodologyQuery(new QueryFactory());
    List<InfectivityMethodology> list = AbstractTerm.getAll(query, InfectivityMethodology.class);

    return list.toArray(new InfectivityMethodology[list.size()]);
  }
  
  public static InfectivityMethodology[] getAllActive()
  {
    InfectivityMethodologyQuery query = new InfectivityMethodologyQuery(new QueryFactory());
    List<InfectivityMethodology> list = AbstractTerm.getAllActive(query, InfectivityMethodology.class);

    return list.toArray(new InfectivityMethodology[list.size()]);
  }
  
  public static InfectivityMethodology validateByDisplayLabel(String displayLabel, MdAttributeDAOIF mdAttribute)
  {
    return (InfectivityMethodology)AbstractTerm.validateByDisplayLabel(displayLabel, mdAttribute);
  }
}
