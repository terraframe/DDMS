package dss.vector.solutions.mo;


import java.util.LinkedList;
import java.util.List;


import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.mo.MolecularAssayResultBase;
import dss.vector.solutions.mo.MolecularAssayResultQuery;

public class MolecularAssayResult extends MolecularAssayResultBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235599936777L;
  
  public MolecularAssayResult()
  {
    super();
  }
  
  public static MolecularAssayResult[] getAll()
  {
    List<MolecularAssayResult> list = new LinkedList<MolecularAssayResult>();   
    MolecularAssayResultQuery query = new MolecularAssayResultQuery(new QueryFactory());
    OIterator<? extends MolecularAssayResult> it = query.getIterator();
    
    while(it.hasNext())
    {
      list.add(it.next());
    }
    
    it.close();
    
    return list.toArray(new MolecularAssayResult[list.size()]);
  }  
}
