package mdss.ivcc.mrc.csu.entomology;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mdss.ivcc.mrc.csu.entomology.assay.AssayTestResult;
import mdss.ivcc.mrc.csu.mo.AbstractTerm;

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
    Mosquito mosquito = null;
    String id = this.getMosquitoId();

    if (id == null || id.equals(""))
    {
      mosquito = new Mosquito();
    }
    else
    {
      mosquito = Mosquito.lock(id);
    }

    try
    {
      applyMosquito(mosquito);
      applyAssays(mosquito);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }

    this.setMosquitoId(mosquito.getId());
  }

  private void applyMosquito(Mosquito mosquito)
  {
    List<Sex> list = this.getSex();
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
  private Map<Class<AssayTestResult>, MdAttributeVirtualDAOIF> getAssayMap()
  {
    Map<Class<AssayTestResult>, MdAttributeVirtualDAOIF> map = new HashMap<Class<AssayTestResult>, MdAttributeVirtualDAOIF>();
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

        if (AssayTestResult.class.isAssignableFrom(c) && !virtual.definesAttribute().contains("Method"))
        {
          map.put((Class<AssayTestResult>) c, virtual);
        }
      }
    }

    return map;
  }

  private void applyAssays(Mosquito mosquito) throws InstantiationException, IllegalAccessException,
      IllegalArgumentException, SecurityException, InvocationTargetException, NoSuchMethodException
  {
    Map<Class<AssayTestResult>, MdAttributeVirtualDAOIF> assayMap = this.getAssayMap();

    for (Class<AssayTestResult> c : assayMap.keySet())
    {
      // Get the result
      MdAttributeVirtualDAOIF mdAttribute = assayMap.get(c);
      String attributeName = GenerationUtil.upperFirstCharacter(mdAttribute.getAccessorName());

      String resultName = "get" + attributeName;
      String methodName = "get" + attributeName + "Method";

      Object testResult = MosquitoView.class.getMethod(resultName).invoke(this);
      Object testMethod = MosquitoView.class.getMethod(methodName).invoke(this);

      if (testResult != null && testMethod != null)
      {
        Object result = mosquito.getTestResult(c);

        if (result == null)
        {
          result = c.newInstance();
        }

        c.getMethod("setMosquito", Mosquito.class).invoke(result, mosquito);
        c.getMethod("setTestResult", testResult.getClass()).invoke(result, testResult);
        c.getMethod("setTestMethod", testMethod.getClass()).invoke(result, testMethod);
        c.getMethod("apply").invoke(result);
      }
    }
  }

  public void setAssays(List<AssayTestResult> list) throws IllegalArgumentException, SecurityException,
      IllegalAccessException, InvocationTargetException, NoSuchMethodException
  {
    Map<Class<AssayTestResult>, MdAttributeVirtualDAOIF> assayMap = this.getAssayMap();

    for (AssayTestResult result : list)
    {
      Class<? extends AssayTestResult> c = result.getClass();
      MdAttributeVirtualDAOIF mdAttribute = assayMap.get(c);

      if (mdAttribute != null)
      {
        String attributeName = GenerationUtil.upperFirstCharacter(mdAttribute.getAccessorName());

        Object testResult = result.getTestResult();
        AbstractTerm testMethod = result.getTestMethod();

        String resultName = "set" + attributeName;
        String methodName = "set" + attributeName + "Method";

        MosquitoView.class.getMethod(resultName, testResult.getClass()).invoke(this, testResult);
        MosquitoView.class.getMethod(methodName, testMethod.getClass()).invoke(this, testMethod);
      }
    }
  }
}
