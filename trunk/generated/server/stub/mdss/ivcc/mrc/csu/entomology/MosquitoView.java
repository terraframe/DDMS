package mdss.ivcc.mrc.csu.entomology;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mdss.ivcc.mrc.csu.entomology.assay.AssayTestResult;

import com.terraframe.mojo.business.generation.GenerationUtil;
import com.terraframe.mojo.constants.MdAttributeConcreteInfo;
import com.terraframe.mojo.constants.MdAttributeVirtualInfo;
import com.terraframe.mojo.dataaccess.MdAttributeConcreteDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeVirtualDAOIF;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.metadata.MdAttributeConcreteDAO;
import com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO;
import com.terraframe.mojo.generation.loader.LoaderDecorator;

public class MosquitoView extends MosquitoViewBase implements
    com.terraframe.mojo.generation.loader.Reloadable
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

    if (id == null || id.equals(""))
    {
      List<Sex> list = this.getSex();
      Mosquito mosquito = new Mosquito();

      mosquito.setSampleId(this.getSampleId());
      mosquito.setIdentificationMethod(this.getIdentificationMethod());
      mosquito.setIsofemale(this.getIsofemale());
      mosquito.setGeneration(this.getGeneration());
      mosquito.setSpecie(this.getSpecie());
      mosquito.setTestDate(this.getTestDate());
      mosquito.setCollection(this.getCollection());

      if (list.size() > 0)
      {
        mosquito.addSex(list.get(0));
      }

      mosquito.apply();

      try
      {
        applyAssays(mosquito);
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }

      this.setMosquitoId(mosquito.getId());
    }
    else
    {

    }

    super.apply();
  }

  @Override
  public void delete()
  {
    String id = this.getMosquitoId();

    if (id != null && !id.equals(""))
    {
      Mosquito.get(id).delete();
    }
  }

  /**
   * @return A mapping between a virtual attribute and the class of its concrete
   *         attribute. The class must extend from AssayTestResult.
   */
  @SuppressWarnings("unchecked")
  private Map<MdAttributeVirtualDAOIF, Class<?>> getAssayMap()
  {
    Map<MdAttributeVirtualDAOIF, Class<?>> map = new HashMap<MdAttributeVirtualDAOIF, Class<?>>();
    List<MdAttributeDAOIF> mdAttributeDAOs = (List<MdAttributeDAOIF>) this.getMdAttributeDAOs();

    for (MdAttributeDAOIF mdAttribute : mdAttributeDAOs)
    {
      if (mdAttribute instanceof MdAttributeVirtualDAOIF)
      {
        MdAttributeVirtualDAOIF virtual = (MdAttributeVirtualDAOIF) mdAttribute;
        String concreteId = virtual.getValue(MdAttributeVirtualInfo.MD_ATTRIBUTE_CONCRETE);
        MdAttributeConcreteDAOIF concrete = MdAttributeConcreteDAO.get(concreteId);

        MdBusinessDAOIF mdClass = MdBusinessDAO.get(concrete
            .getValue(MdAttributeConcreteInfo.DEFINING_MD_CLASS));

        Class<?> c = LoaderDecorator.load(mdClass.definesType());

        if (AssayTestResult.class.isAssignableFrom(c))
        {
          map.put(virtual, c);
        }
      }
    }

    return map;
  }

  private void applyAssays(Mosquito mosquito) throws InstantiationException, IllegalAccessException,
      IllegalArgumentException, SecurityException, InvocationTargetException, NoSuchMethodException
  {
    Map<MdAttributeVirtualDAOIF, Class<?>> assayMap = this.getAssayMap();

    for (MdAttributeVirtualDAOIF mdAttribute : assayMap.keySet())
    {
      // Get the result
      Class<?> c = assayMap.get(mdAttribute);
      String methodName = "get" + GenerationUtil.upperFirstCharacter(mdAttribute.getAccessorName());
      Object testResult = MosquitoView.class.getMethod(methodName).invoke(this);

      if (testResult != null)
      {
        Object newInstance = c.newInstance();
        c.getMethod("setMosquito", Mosquito.class).invoke(newInstance, mosquito);
        c.getMethod("setTestResult", testResult.getClass()).invoke(newInstance, testResult);
        c.getMethod("apply").invoke(newInstance);
      }
    }
  }
}
