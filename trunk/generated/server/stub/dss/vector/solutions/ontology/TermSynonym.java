package dss.vector.solutions.ontology;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.geo.GeoSynonym;
import dss.vector.solutions.geo.GeoSynonymQuery;
import dss.vector.solutions.geo.generated.GeoEntityQuery;



public class TermSynonym extends TermSynonymBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1827568707;
  
  public TermSynonym()
  {
    super();
  }
  
  public static void createSynonym(java.lang.String termName, java.lang.String termId)
  {
	  Term.getByTermId(termId).addSynonym(termName);
  }
  
  public static TermSynonym getByNameAndTerm(String termId, String name)
  {
	    QueryFactory qf = new QueryFactory();
	    
	    TermQuery tq = new TermQuery(qf);
	    
	    TermSynonymQuery tsq = new TermSynonymQuery(qf);
	    tsq.WHERE(tsq.getTermName().EQ(name));
	    tsq.AND(tsq.term(tq));
	    
	    tq.WHERE(tq.getTermId().EQ(termId));
	    
	    OIterator<? extends TermSynonym> it = tsq.getIterator();
	    if (!it.hasNext())
	    {
	      return null;
	    }
	    else
	    {
	      return it.next();
	    }
  }
}
