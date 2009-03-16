package dss.vector.solutions.entomology;


import java.util.LinkedList;
import java.util.List;


import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.entomology.MosquitoBase;
import dss.vector.solutions.entomology.assay.AssayTestResultQuery;
import dss.vector.solutions.entomology.assay.AssayTestResult;

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
  
  public MosquitoView getView()
  {
    MosquitoView view = new MosquitoView();
    
    view.setSpecie(this.getSpecie());
    view.setCollection(this.getCollection());
    view.setGeneration(this.getGeneration());
    view.setIsofemale(this.getIsofemale());
    view.setIdentificationMethod(this.getIdentificationMethod());
    view.setTestDate(this.getTestDate());
    view.setMosquitoId(this.getId());

    if(this.getSex().size() > 0)
    {
      view.addSex(this.getSex().get(0));
    }
    
    try
    {
      view.setAssays(this.getTestResults());
    }
    catch(Exception e)
    {
      throw new RuntimeException(e);
    }
    
    view.applyNoPersist();
    
    return view;
  }
  
  public AssayTestResult getTestResult(Class<AssayTestResult> c)
  {
    for(AssayTestResult result : this.getTestResults())
    {
      if(c.isInstance(result))
      {
        return result;
      }
    }
    
    return null;
  }
}