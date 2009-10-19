package dss.vector.solutions.export;

import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.dataaccess.metadata.MdTypeDAO;

import dss.vector.solutions.irs.AbstractSprayView;
import dss.vector.solutions.irs.SprayData;
import dss.vector.solutions.irs.SprayMethod;
import dss.vector.solutions.irs.SurfaceType;
import dss.vector.solutions.ontology.Term;

public class AbstractSprayExcelView extends AbstractSprayExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1246598143605L;
  
  public AbstractSprayExcelView()
  {
    super();
  }
  
  public void populate(AbstractSprayView abstractSprayView)
  {
    abstractSprayView.setSurfaceType(Term.validateByDisplayLabel(this.getSurfaceType(), SprayData.getSurfaceTypeMd()));
  }
  
  public SurfaceType getSurfaceTypeByLabel(String label)
  {
    if(label == null || label.equals(""))
    {
      return null;
    }

    for (SurfaceType e : SurfaceType.values())
    {
      if (e.getDisplayLabel().equals(label))
      {
        return e;
      }
    }
    String message = "[" + label + "] is not a valid display label for [" + SurfaceType.CLASS + "]";
    throw new DataNotFoundException(message, MdTypeDAO.getMdTypeDAO(SurfaceType.CLASS));
  }

  public SprayMethod getSprayMethodByLabel(String label)
  {
    for (SprayMethod e : SprayMethod.values())
    {
      if (e.getDisplayLabel().equals(label))
      {
        return e;
      }
    }
    String message = "[" + label + "] is not a valid display label for [" + SprayMethod.CLASS + "]";
    throw new DataNotFoundException(message, MdTypeDAO.getMdTypeDAO(SprayMethod.CLASS));
  }
}
