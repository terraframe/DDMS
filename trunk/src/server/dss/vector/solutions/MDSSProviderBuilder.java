package dss.vector.solutions;

import com.terraframe.mojo.business.generation.ProviderBuilder;
import com.terraframe.mojo.business.generation.ProviderBuilderIF;
import com.terraframe.mojo.business.generation.facade.ControllerStubGeneratorIF;
import com.terraframe.mojo.business.generation.view.ContentProviderIF;
import com.terraframe.mojo.dataaccess.MdControllerDAOIF;
import com.terraframe.mojo.dataaccess.MdEntityDAOIF;

public class MDSSProviderBuilder extends ProviderBuilder implements ProviderBuilderIF
{
  public MDSSProviderBuilder()
  {
    // Must implement a default constructor
  }

  @Override
  public ControllerStubGeneratorIF getControllerStubGenerator(MdControllerDAOIF mdController)
  {
    return new MDSSControllerStubGenerator(mdController);
  }
  
  @Override
  public ContentProviderIF getProvider(MdEntityDAOIF mdEntity)
  {
    // Use the default implementation
    
    return super.getProvider(mdEntity);
  }
}
