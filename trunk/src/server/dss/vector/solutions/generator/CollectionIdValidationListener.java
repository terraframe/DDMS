package dss.vector.solutions.generator;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.excel.ImportAdapter;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.form.business.HumanBloodIndex;

/**
 * Implemented as part of ticket 3188, this performs validation on the HumanBloodIndex's 'collectionId' attribute.
 * This is written to still hold true even when the form is cloned, as such its hardcoded to kick in on any forms
 * with an attribute by name, 'collectionId'.
 * 
 * @author Richard Rowlands
 */
public class CollectionIdValidationListener extends ImportAdapter implements Reloadable
{
  @Override
  public void beforeApply(Mutable instance)
  {
    String collectionId = instance.getValue(HumanBloodIndex.COLLECTIONID);

    if (collectionId != null && collectionId.length() > 0)
    {
      MosquitoCollection collect = MosquitoCollection.getByCollectionId(collectionId);

      if (collect == null)
      {
        // We should never to get to this point, getByCollectionId should throw our exception for us, but just in case its modified in the future we'll have a fail safe because we're good little coders.
        throw new DataNotFoundException("No mosquito collection with collection id [" + collectionId + "] found.", MdTypeDAO.getMdTypeDAO(MosquitoCollection.CLASS));
      }
    }
  }
}
