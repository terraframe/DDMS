package dss.vector.solutions.entomology;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.business.generation.GenerationUtil;
import com.terraframe.mojo.constants.MdAttributeConcreteInfo;
import com.terraframe.mojo.constants.MdAttributeVirtualInfo;
import com.terraframe.mojo.dataaccess.MdAttributeConcreteDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeVirtualDAOIF;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.metadata.MdAttributeConcreteDAO;
import com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.generation.loader.LoaderDecorator;

import dss.vector.solutions.entomology.MosquitoViewBase;

import dss.vector.solutions.entomology.assay.AssayTestResult;
import dss.vector.solutions.mo.AbstractTerm;

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

    applyMosquito(mosquito);

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

  private void applyMosquito(Mosquito mosquito)
  {
    List<Sex> list = this.getSex();
    mosquito.setIdentificationMethod(this.getIdentificationMethod());
    mosquito.setIsofemale(this.getIsofemale());
    mosquito.setGeneration(this.getGeneration());
    mosquito.setSpecie(this.getSpecie());
    mosquito.setTestDate(this.getTestDate());
    mosquito.setCollection(this.getCollection());
    mosquito.setSampleId(this.getSampleId());

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
  public Map<Class<AssayTestResult>, MdAttributeVirtualDAOIF> getAssayMap()
  {
    Map<Class<AssayTestResult>, MdAttributeVirtualDAOIF> map = new HashMap<Class<AssayTestResult>, MdAttributeVirtualDAOIF>();
    List<MdAttributeDAOIF> mdAttributeDAOs = (List<MdAttributeDAOIF>) this.getMdAttributeDAOs();

    for (MdAttributeDAOIF mdAttribute : mdAttributeDAOs)
    {
      // We want to return a map for all virtual attributes which
      if (mdAttribute instanceof MdAttributeVirtualDAOIF)
      {
        MdAttributeVirtualDAOIF virtual = (MdAttributeVirtualDAOIF) mdAttribute;
        String concreteId = virtual.getValue(MdAttributeVirtualInfo.MD_ATTRIBUTE_CONCRETE);
        MdAttributeConcreteDAOIF concrete = MdAttributeConcreteDAO.get(concreteId);

        MdBusinessDAOIF mdClass = MdBusinessDAO.get(concrete
            .getValue(MdAttributeConcreteInfo.DEFINING_MD_CLASS));

        Class<?> c = LoaderDecorator.load(mdClass.definesType());

        // We filter all abstract classes because we do not want to add the
        // virtual attributes which represent test methodology and these
        // virtual attributes are defined on abstract classes
        if (AssayTestResult.class.isAssignableFrom(c) && !Modifier.isAbstract(c.getModifiers()))
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
      Object testMethod = null;
      Object result = mosquito.getTestResult(c);

      try
      {
        testMethod = MosquitoView.class.getMethod(methodName).invoke(this);
      }
      catch (NoSuchMethodException e)
      {
        testMethod = null;
      }

      if (testResult != null)
      {
        if (result == null)
        {
          result = c.newInstance();
        }
        else
        {
          c.getMethod("lock").invoke(result);
        }

        c.getMethod("setMosquito", Mosquito.class).invoke(result, mosquito);

        c.getMethod("setTestResult", testResult.getClass()).invoke(result, testResult);

        if (testMethod != null)
        {
          c.getMethod("setTestMethod", testMethod.getClass()).invoke(result, testMethod);
        }

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

        MosquitoView.class.getMethod(resultName, testResult.getClass()).invoke(this, testResult);

        if (result.hasTestMethod())
        {
          String methodName = "set" + attributeName + "Method";
          MosquitoView.class.getMethod(methodName, testMethod.getClass()).invoke(this, testMethod);
        }
      }
    }
  }

  @Transaction
  public static MosquitoView[] saveAll(MosquitoView[] array)
  {
    for (MosquitoView view : array)
    {
      view.apply();
    }

    return array;
  }

}