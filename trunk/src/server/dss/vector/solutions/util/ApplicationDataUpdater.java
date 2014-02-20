package dss.vector.solutions.util;

import java.io.FileNotFoundException;

import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.constants.MdBusinessInfo;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.metadata.MetadataDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;

import dss.vector.solutions.entomology.BiochemicalAssay;
import dss.vector.solutions.entomology.DiagnosticAssay;
import dss.vector.solutions.entomology.InfectionAssay;
import dss.vector.solutions.entomology.LifeStage;
import dss.vector.solutions.entomology.MolecularAssay;
import dss.vector.solutions.entomology.PooledInfectionAssay;
import dss.vector.solutions.entomology.SubCollection;
import dss.vector.solutions.entomology.SubCollectionQuery;
import dss.vector.solutions.entomology.TimeResponseAssay;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery;
import dss.vector.solutions.entomology.assay.EfficacyAssay;
import dss.vector.solutions.entomology.assay.KnockDownAssay;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.UniqueAssay;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.general.MalariaSeasonQuery;
import dss.vector.solutions.general.MalariaSeasonSeasonLabel;

public class ApplicationDataUpdater implements Reloadable, Runnable
{
  @Transaction
  public void run()
  {
    // Force the cache to boot so it's not included in our timing
    MetadataDAO.get(MdBusinessInfo.CLASS, MdBusinessInfo.CLASS);

    this.updateMalariaSeasonLabels();

    this.updateSubCollections();

    this.updateAssayIds();

    // For ticket #2922
    this.updateAdultDiscriminatingDoseAssays();
  }

  private void updateAdultDiscriminatingDoseAssays()
  {
    /*
     * Default hard-coded control number.  It is 10000 because we most derive
     * the control test number from the existing control test mortality and
     * the control number.  Existing control test mortality values have
     * relevant decimal values up to the hunderth decimal spot.
     */
    int controlNumber = 10000;

    AdultDiscriminatingDoseAssayQuery query = new AdultDiscriminatingDoseAssayQuery(new QueryFactory());
    query.WHERE(query.getSiteMaster().EQ(CommonProperties.getDomain()));
    query.AND(query.getControlTestMortality().NE((Float) null));
    query.AND(query.getControlTestNumberExposed().EQ((Integer) null));
    query.AND(query.getControlTestNumberDead().EQ((Integer) null));

    OIterator<? extends AdultDiscriminatingDoseAssay> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        AdultDiscriminatingDoseAssay assay = iterator.next();
        assay.appLock();
        assay.setControlTestNumberExposed(controlNumber);
        assay.setControlTestNumberDead((int) ( controlNumber * assay.getControlTestMortality() / 100 ));
        assay.apply();
      }
    }
    finally
    {
      iterator.close();
    }
  }

  private void updateAssayIds()
  {
    String[] types = new String[] { EfficacyAssay.CLASS, InfectionAssay.CLASS, PooledInfectionAssay.CLASS, MolecularAssay.CLASS, BiochemicalAssay.CLASS, KnockDownAssay.CLASS, AdultDiscriminatingDoseAssay.CLASS, LarvaeDiscriminatingDoseAssay.CLASS, DiagnosticAssay.CLASS, TimeResponseAssay.CLASS };

    for (String type : types)
    {
      QueryFactory f = new QueryFactory();
      BusinessQuery q = f.businessQuery(type);

      q.WHERE(q.get(UniqueAssay.UNIQUE_ASSAY_ID).EQ(null));

      OIterator<Business> iter = q.getIterator();

      try
      {
        while (iter.hasNext())
        {
          Business b = iter.next();

          b.appLock();
          UniqueAssayUtil.setUniqueAssayId((UniqueAssay) b);
          b.apply();
        }
      }
      finally
      {
        iter.close();
      }
    }

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
