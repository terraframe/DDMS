package dss.vector.solutions;

import com.runwaysdk.business.generation.ProviderBuilder;
import com.runwaysdk.business.generation.ProviderBuilderIF;
import com.runwaysdk.business.generation.facade.ControllerStubGeneratorIF;
import com.runwaysdk.business.generation.view.ContentProvider;
import com.runwaysdk.business.generation.view.ContentProviderIF;
import com.runwaysdk.business.generation.view.NewRelationshipComponentListener;
import com.runwaysdk.dataaccess.MdControllerDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.generation.loader.Reloadable;

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
