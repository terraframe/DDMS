package dss.vector.solutions.util;

import java.io.FileNotFoundException;

import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.constants.MdBusinessInfo;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.metadata.MetadataDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;

import dss.vector.solutions.entomology.LifeStage;
import dss.vector.solutions.entomology.SubCollection;
import dss.vector.solutions.entomology.SubCollectionQuery;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.general.MalariaSeasonQuery;
import dss.vector.solutions.general.MalariaSeasonSeasonLabel;

public class ApplicationDataUpdater implements Reloadable, Runnable
{
  public void run()
  {
    // Force the cache to boot so it's not included in our timing
    MetadataDAO.get(MdBusinessInfo.CLASS, MdBusinessInfo.CLASS);

    this.updateMalariaSeasonLabels();

    this.updateSubCollections();
  }

  private void updateMalariaSeasonLabels()
  {
    MalariaSeasonQuery query = new MalariaSeasonQuery(new QueryFactory());
    query.WHERE(query.getSiteMaster().EQ(CommonProperties.getDomain()));

    OIterator<? extends MalariaSeason> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        MalariaSeason season = iterator.next();

        MalariaSeasonSeasonLabel seasonLabel = season.getSeasonLabel();
        String defaultValue = seasonLabel.getDefaultValue();

        if (season.getSeasonName() != null && ( defaultValue == null || defaultValue.length() == 0 ))
        {
          season.appLock();

          seasonLabel.setDefaultValue(season.getSeasonName());

          season.apply();
        }
      }
    }
    finally
    {
      iterator.close();
    }
  }

  private void updateSubCollections()
  {
    SubCollectionQuery query = new SubCollectionQuery(new QueryFactory());
    query.WHERE(query.getSiteMaster().EQ(CommonProperties.getDomain()));
    query.AND(query.getFemalesTotal().EQ((Integer) null));
    query.AND(query.getCollection().getLifeStage().containsAll(LifeStage.ADULT));

    OIterator<? extends SubCollection> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        SubCollection collection = iterator.next();
        collection.appLock();

        collection.populateTotal();
        collection.apply();
      }
    }
    finally
    {
      iterator.close();
    }
  }

  public static void main(String[] args) throws FileNotFoundException
  {
    try
    {
      ApplicationDataUpdater.start(args);
    }
    finally
    {
      CacheShutdown.shutdown();
    }
  }

  @Request
  private static void start(String[] args)
  {
    new ApplicationDataUpdater().run();
  }
}
