package dss.vector.solutions.general;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.query.SelectableBoolean;
import com.runwaysdk.session.Session;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF;

public class DiseaseWrapper extends DiseaseWrapperBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -619081050;
  
  public DiseaseWrapper()
  {
    super();
  }
  
  /**
   * Returns the name of the Term attribute for the inactive flag based on the current
   * disease selected for the session user.
   * 
   * @return
   */
  public static String getTermInactiveAttribute()
  {
    Disease disease = getDisease();
    
    if(disease == Disease.DENGUE)
    {
      return Term.INACTIVEDENGUE;
    }
    else if(disease == Disease.MALARIA)
    {
      return Term.INACTIVEMALARIA;
    }
    else
    {
      String error = "The disease ["+disease+"] is currently not supported.";
      throw new ProgrammingErrorException(error);
    }
  }
  
  /**
   * Returns the correct SelectableBoolean for the active flag depending on which
   * disease is being used by the current user.
   * 
   * @param termQuery
   * @return
   */
  public static SelectableBoolean getInactive(TermQuery termQuery)
  {
    Disease disease = getDisease();
    
    if(disease == Disease.DENGUE)
    {
      return termQuery.getInactiveDengue();
    }
    else if(disease == Disease.MALARIA)
    {
      return termQuery.getInactiveMalaria();
    }
    else
    {
      String error = "The disease ["+disease+"] is currently not supported.";
      throw new ProgrammingErrorException(error);
    }
  }
  
  /**
   * Returns the correct SelectableBoolean for the active flag depending on which
   * disease is being used by the current user.
   * 
   * @param termQuery
   * @return
   */
  public static SelectableBoolean getInactive(TermQueryReferenceIF termQueryRefIF)
  {
    Disease disease = getDisease();
    
    if(disease == Disease.DENGUE)
    {
      return termQueryRefIF.getInactiveDengue();
    }
    else if(disease == Disease.MALARIA)
    {
      return termQueryRefIF.getInactiveMalaria();
    }
    else
    {
      String error = "The disease ["+disease+"] is currently not supported.";
      throw new ProgrammingErrorException(error);
    }
  }
  
  /**
   * Returns the Disease enum item of the user in the current session.
   * 
   * @return
   */
  public static Disease getDisease()
  {
    String id = Session.getCurrentSession().getUser().getId();
    MDSSUser user = MDSSUser.get(id);
    
    String name = user.getDiseaseName();
    return Disease.valueOf(name);
  }
}
