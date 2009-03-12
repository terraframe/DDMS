package csu.mrc.ivcc.mdss.entomology.assay;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import csu.mrc.ivcc.mdss.geo.generated.GeoEntity;

public class EfficacyAssay extends EfficacyAssayBase implements DiscriminatingDoseAssayIF, AdultAssayIF,
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236363373386L;

  public EfficacyAssay()
  {
    super();
  }
  
  @Override
  public void validateAgeRange()
  {
    super.validateAgeRange();
    
    AssayValidator.validateAdultAgeRange(this);
  }

  @Override
  public void validateGravid()
  {
    super.validateGravid();
    
    AssayValidator.validateGravid(this);
  }

  @Override
  public void validateFed()
  {
    super.validateFed();
    
    AssayValidator.validateFed(this);
  }
  
  @Override
  public void validateQuantityDead()
  {
    super.validateQuantityDead();

    AssayValidator.validateQuantityDead(this);
  }



  @Override
  public void apply()
  {
    validateQuantityDead();
    validateAgeRange();
    validateFed();
    validateGravid();

    if (this.getQuantityDead() <= this.getQuantityTested())
    {
      float mortality = calculateMortality(this.getQuantityDead(), this.getQuantityTested());

      this.setQuantityLive(this.getQuantityTested() - this.getQuantityDead());
      this.setMortality(mortality);
    }
    else
    {
      this.setQuantityLive(0);
      this.setMortality(new Float(0));
    }

    super.apply();
  }

  private static float calculateMortality(Integer dead, Integer total)
  {
    return ( dead * 100F / total );
  }

  public static EfficacyAssay[] searchByGeoEntityAndDate(GeoEntity geoEntity, Date collectionDate)
  {    
    QueryFactory factory = new QueryFactory();
    EfficacyAssayQuery query = new EfficacyAssayQuery(factory);

    query.WHERE(query.getGeoEntity().getId().EQ(geoEntity.getId()));
    query.AND(query.getTestDate().EQ(collectionDate));

    OIterator<? extends EfficacyAssay> iterator = query.getIterator();

    try
    {
      List<EfficacyAssay> list = new LinkedList<EfficacyAssay>();
      
      while (iterator.hasNext())
      {
        list.add(iterator.next());
      }
      
      return list.toArray(new EfficacyAssay[list.size()]);
    }
    finally
    {
      iterator.close();
    }
  }
  
  @Override
  public Float getOverallMortalityRate()
  {
    int dead = 0;
    int total = 0;
    EfficacyAssay[] assays = searchByGeoEntityAndDate(this.getGeoEntity(), this.getTestDate());
    
    for(EfficacyAssay assay : assays)
    {
      dead += assay.getQuantityDead();
      total += assay.getQuantityTested();
    }
      
    if(total == 0)
    {
      return 0.0F;
    }
    
    return calculateMortality(dead, total);    
  }
}
