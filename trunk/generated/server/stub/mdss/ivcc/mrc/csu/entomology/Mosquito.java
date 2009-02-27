package mdss.ivcc.mrc.csu.entomology;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import mdss.ivcc.mrc.csu.entomology.MosquitoBase;
import mdss.ivcc.mrc.csu.entomology.assay.AssayTestResult;
import mdss.ivcc.mrc.csu.entomology.assay.AssayTestResultQuery;

public class Mosquito extends MosquitoBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288152336L;
  
  public Mosquito()
  {
    super();
  }
  
  public List<AssayTestResult> getTestResults()
  {
    List<AssayTestResult> list = new LinkedList<AssayTestResult>();
    AssayTestResultQuery query = new AssayTestResultQuery(new QueryFactory());
    
    query.WHERE(query.getMosquito().EQ(this));
    
    OIterator<? extends AssayTestResult> iterator = query.getIterator();
    
    while(iterator.hasNext())
    {
      list.add(iterator.next());
    }
    
    iterator.close();
    
    return list;    
  }
  
  @Override
  public void delete()
  {
    //DELETE all of the mosquito test results first
    for(AssayTestResult result : this.getTestResults())
    {
      result.delete();
    }
        
    super.delete();
  }
}
