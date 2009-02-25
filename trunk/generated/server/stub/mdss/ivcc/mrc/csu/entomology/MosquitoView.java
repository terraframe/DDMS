package mdss.ivcc.mrc.csu.entomology;

import mdss.ivcc.mrc.csu.mo.IdentificationMethod;

public class MosquitoView extends MosquitoViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235599942174L;
  
  public MosquitoView()
  {
    super();
  }  
  
  @Override
  public void apply()
  {
    String id = this.getMosquitoId();
    
    if(id == null || id.equals(""))
    {
      Mosquito mosquito = new Mosquito();
      
      mosquito.setSampleId(this.getSampleId());
//      mosquito.set
//      mosquito.setIdentificationMethod(IdentificationMethod.get(this.get))
    }
    else
    {
      
    }
    
    super.apply();
  }
}
