package dss.vector.solutions.general;

import com.runwaysdk.query.Condition;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.ontology.InactivePropertyQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF;
import dss.vector.solutions.util.MenuGenerator;

public class DiseaseWrapper extends DiseaseWrapperBase implements com.runwaysdk.generation.loader.Reloadable {
	private static final long serialVersionUID = -619081050;

	public DiseaseWrapper() {
		super();
	}
	
	public static Condition getInactiveCriteria(QueryFactory f, TermQuery termQuery, Boolean inactive)
	{
	  Disease disease = getDisease();
	  
	  InactivePropertyQuery ipQ = new InactivePropertyQuery(f);
	  
    ipQ.AND(ipQ.getDisease().containsExactly(disease));
    ipQ.AND(ipQ.getInactive().EQ(inactive));
	  
    return termQuery.inactiveProperties(ipQ);
	}
	
	
	
	public static Condition getInactiveCriteria(QueryFactory f, TermQueryReferenceIF termQueryRef, Boolean inactive)
	{
// The following has a bug in the query API
//	  Disease disease = getDisease();
//	  
//	  InactivePropertyQuery ipQ = new InactivePropertyQuery(f);
//	  
//	  ipQ.AND(ipQ.getDisease().containsExactly(disease));
//	  ipQ.AND(ipQ.getInactive().EQ(inactive));
//	  
//	  return termQueryRef.inactiveProperties(ipQ);
	  
    TermQuery t = new TermQuery(f);
    InactivePropertyQuery ip = new InactivePropertyQuery(f);
    
    
    Disease disease = DiseaseWrapper.getDisease();
    ip.WHERE(ip.getDisease().containsExactly(disease));
    ip.WHERE(ip.getInactive().EQ(inactive));
    
    t.AND(t.inactiveProperties(ip));

    return termQueryRef.EQ(t);
	}

	/**
	 * Checks if the given Term is inactive for the given Disease.
	 * 
	 * @param term
	 * @param disease
	 * @return true if the Term is inactive for the Disease; otherwise, false.
	 */
	public static boolean isInactive(Term term, Disease disease) {
	  
	  QueryFactory f = new QueryFactory();
	  
	  TermQuery tq = new TermQuery(f);
	  InactivePropertyQuery ipQ = new InactivePropertyQuery(f);
	  
	  tq.WHERE(tq.getId().EQ(term.getId()));
	  
	  ipQ.WHERE(ipQ.getDisease().containsExactly(disease));
	  ipQ.AND(ipQ.term(tq));
	  ipQ.AND(ipQ.getInactive().EQ(true));
	  
	  return ipQ.getCount() == 1;
	}
	
	 /**
   * Returns the Disease enum item of the user in the current session.
   * 
   * @return
   */
  public static Disease getDisease() {
    String id = Session.getCurrentSession().getUser().getId();
    MDSSUser user = MDSSUser.get(id);

    String name = user.getDiseaseName();
    return Disease.valueOf(name);
  }
  
  public static String getMenuJson() {
    MenuGenerator menuGenerator = new MenuGenerator(getDisease());
    
    menuGenerator.generateMenu();
    return menuGenerator.getJson();
  }
}
