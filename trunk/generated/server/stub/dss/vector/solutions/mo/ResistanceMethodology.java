package dss.vector.solutions.mo;


import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

public class ResistanceMethodology extends ResistanceMethodologyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234731975869L;
  
  public ResistanceMethodology()
  {
    super();
  }
  
  public static ResistanceMethodology[] getAll()
  {
    ResistanceMethodologyQuery query = new ResistanceMethodologyQuery(new QueryFactory());
    List<ResistanceMethodology> list = AbstractTerm.getAll(query, ResistanceMethodology.class);

    return list.toArray(new ResistanceMethodology[list.size()]);
  }
  
  public static ResistanceMethodology[] getAllActive()
  {
    ResistanceMethodologyQuery query = new ResistanceMethodologyQuery(new QueryFactory());
    List<ResistanceMethodology> list = AbstractTerm.getAllActive(query, ResistanceMethodology.class);

    return list.toArray(new ResistanceMethodology[list.size()]);
  } 
}
