package dss.vector.solutions;

import com.terraframe.mojo.business.generation.ProviderBuilder;
import com.terraframe.mojo.business.generation.ProviderBuilderIF;
import com.terraframe.mojo.business.generation.facade.ControllerStubGeneratorIF;
import com.terraframe.mojo.business.generation.view.ContentProvider;
import com.terraframe.mojo.business.generation.view.ContentProviderIF;
import com.terraframe.mojo.business.generation.view.NewRelationshipComponentListener;
import com.terraframe.mojo.dataaccess.MdControllerDAOIF;
import com.terraframe.mojo.dataaccess.MdEntityDAOIF;
import com.terraframe.mojo.dataaccess.MdRelationshipDAOIF;
import com.terraframe.mojo.generation.loader.Reloadable;

public class MDSSProviderBuilder extends ProviderBuilder implements ProviderBuilderIF, Reloadable
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
    ContentProviderIF provider = new ContentProvider();

    provider.registerContentListener(new MDSSViewAllComponentListener(mdEntity));
    provider.registerContentListener(new MDSSViewComponentListener(mdEntity));
    provider.registerContentListener(new MDSSFormListener(mdEntity));
    provider.registerContentListener(new MDSSCreateComponentListener(mdEntity));
    provider.registerContentListener(new MDSSUpdateComponentListener(mdEntity));

    if (mdEntity instanceof MdRelationshipDAOIF)
    {
      provider.registerContentListener(new NewRelationshipComponentListener((MdRelationshipDAOIF) mdEntity));
    }

    return provider;
  }
}
