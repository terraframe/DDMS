package dss.vector.solutions;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdForm;
import com.runwaysdk.system.metadata.MdFormQuery;

public class VectorCollectionMigrator
{
  public static void main(String[] args)
  {
    QueryFactory qf = new QueryFactory();
    MdFormQuery mfq = new MdFormQuery(qf);
    
    mfq.WHERE(mfq.getId().EQ("ifmcsknrdxllp1j9h6hfv0tpt6cut17kzo86ttx2qfhw4ayiqelfr3igdjhd90eu"));
    
    OIterator<? extends MdForm> it = mfq.getIterator();
    for (MdForm form : it)
    {
      // TODO : Add a geo reference field to MosquitoCollection.GeoEntity
      
    }
  }
}
