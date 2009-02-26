package mdss.ivcc.mrc.csu.entomology;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
      
      if(list.size() > 0)
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

  @SuppressWarnings("unchecked")
  private void applyAssays(Mosquito mosquito) throws InstantiationException, IllegalAccessException,
      IllegalArgumentException, SecurityException, InvocationTargetException, NoSuchMethodException
  {
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
          // Get the result
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
  }
}
