package dss.vector.solutions.general;

import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionFacade;
import com.runwaysdk.system.metadata.MdDimension;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.ontology.InactivePropertyQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF;
import dss.vector.solutions.util.MenuGenerator;

public class Disease extends DiseaseBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long  serialVersionUID = -1180941084;

  public static final String MALARIA          = "MALARIA";

  public static final String DENGUE           = "DENGUE";

  public Disease()
  {
    super();
  }

  public static Disease getMalaria()
  {
    return Disease.getByKey(MALARIA);
  }

  public static Disease getDengue()
  {
    return Disease.getByKey(DENGUE);
  }

  public static Disease getCurrent()
  {
    String id = Session.getCurrentSession().getUser().getId();
    MDSSUser user = MDSSUser.get(id);

    String name = user.getDiseaseName();
    return Disease.getByKey(name);
  }

  public static Condition getInactiveCriteria(QueryFactory f, TermQuery termQuery, Boolean inactive)
  {
    Disease disease = getCurrent();

    InactivePropertyQuery ipQ = new InactivePropertyQuery(f);

    ipQ.AND(ipQ.getDisease().EQ(disease));
    ipQ.AND(ipQ.getInactive().EQ(inactive));

    return termQuery.inactiveProperties(ipQ);
  }

  public static Condition getInactiveCriteria(QueryFactory f, TermQueryReferenceIF termQueryRef, Boolean inactive)
  {
    // The following has a bug in the query API
    // Disease disease = getDisease();
    //
    // InactivePropertyQuery ipQ = new InactivePropertyQuery(f);
    //
    // ipQ.AND(ipQ.getDisease().containsExactly(disease));
    // ipQ.AND(ipQ.getInactive().EQ(inactive));
    //
    // return termQueryRef.inactiveProperties(ipQ);

    TermQuery t = new TermQuery(f);
    InactivePropertyQuery ip = new InactivePropertyQuery(f);

    Disease disease = getCurrent();
    ip.WHERE(ip.getDisease().EQ(disease));
    ip.WHERE(ip.getInactive().EQ(inactive));

    t.AND(t.inactiveProperties(ip));

    return termQueryRef.EQ(t);
  }

  public static Disease[] getAllDiseases()
  {
    DiseaseQuery query = Disease.getAllInstances(null, true, 0, 0);
    OIterator<? extends Disease> it = query.getIterator();
    return it.getAll().toArray(new Disease[(int) query.getCount()]);
  }

  /**
   * Checks if the given Term is inactive for the given Disease.
   * 
   * @param term
   * @param disease
   * @return true if the Term is inactive for the Disease; otherwise, false.
   */
  public static boolean isInactive(Term term, Disease disease)
  {

    QueryFactory f = new QueryFactory();

    TermQuery tq = new TermQuery(f);
    InactivePropertyQuery ipQ = new InactivePropertyQuery(f);

    tq.WHERE(tq.getId().EQ(term.getId()));

    ipQ.WHERE(ipQ.getDisease().EQ(disease));
    ipQ.AND(ipQ.term(tq));
    ipQ.AND(ipQ.getInactive().EQ(true));

    return ipQ.getCount() == 1;
  }

  public String getDisplayLabel()
  {
    return this.getDimension().getDisplayLabel().getValue(Session.getCurrentLocale());
  }

  public static String getMenuJson()
  {
    Disease current = Disease.getCurrent();
    MdDimension dimension = current.getDimension();
    
    // FIRST SET THE CURRENT DIMENSION IN THE SESSION
    String sessionId = Session.getCurrentSession().getId();
    
    SessionFacade.setDimension(dimension.getKey(), sessionId);
        
    MenuGenerator menuGenerator = new MenuGenerator(current);

    menuGenerator.generateMenu();
        
    return menuGenerator.getJson();
  }

  public String toString()
  {
    return this.getDisplayLabel();
  }
}
