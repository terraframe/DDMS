package dss.vector.solutions.entomology.assay;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.dataaccess.MdAttributeReferenceDAOIF;
import com.terraframe.mojo.dataaccess.attributes.InvalidReferenceException;
import com.terraframe.mojo.query.AttributeMoment;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.Surface;
import dss.vector.solutions.query.ThematicLayer;
import dss.vector.solutions.util.QueryUtil;

public class EfficacyAssay extends EfficacyAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
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

    new AdultAgeRangeValidator(this).validate();
  }

  @Override
  public void validateGravid()
  {
    super.validateGravid();

    new GravidValidator(this).validate();
  }

  @Override
  public void validateFed()
  {
    super.validateFed();

    new FedValidator(this).validate();
  }

  @Override
  public void validateQuantityDead()
  {
    super.validateQuantityDead();

    new QuantityDeadValidator(this).validate();
  }
  
  @Override
  public void validateGeoEntity()
  {
    if(this.getGeoEntity() != null && !(this.getGeoEntity() instanceof Surface))
    {
      throw new InvalidReferenceException("[" + this.getGeoEntity().getId() + "] is not a valid Surface geo id", (MdAttributeReferenceDAOIF) EfficacyAssay.getGeoEntityMd());
    }
  }
  
  @Override
  public void apply()
  {
    validateGeoEntity();
    validateQuantityDead();
    validateAgeRange();
    validateFed();
    validateGravid();

    if (this.getQuantityDead() != null && this.getQuantityTested() != null && this.getQuantityDead() <= this.getQuantityTested())
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

    query.WHERE(query.getGeoEntity().EQ(geoEntity));
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

    for (EfficacyAssay assay : assays)
    {
      dead += assay.getQuantityDead();
      total += assay.getQuantityTested();
    }

    if (total == 0)
    {
      return 0.0F;
    }

    return calculateMortality(dead, total);
  }
  
  public EfficacyAssayView lockView()
  {
    this.lock();
    
    return this.getView();
  }
  
  public EfficacyAssayView unlockView()
  {
    this.unlock();
    
    return this.getView();
  }
  
  public EfficacyAssayView getView()
  {
    EfficacyAssayView view = new EfficacyAssayView();
    
    view.populateView(this);
    
    return view;
  }

  
  public static EfficacyAssayView getView(String id)
  {
    return EfficacyAssay.get(id).getView();
  }
  
  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   *
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String[] selectedUniversals, Boolean includeGeometry, ThematicLayer thematicLayer)
  {

    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, thematicLayer, includeGeometry, selectedUniversals, EfficacyAssay.CLASS, EfficacyAssay.GEOENTITY);   
   
    EfficacyAssayQuery efficacyAssayIPTQuery = (EfficacyAssayQuery) queryMap.get(EfficacyAssay.CLASS);

   
    AttributeMoment dateAttribute = efficacyAssayIPTQuery.getTestDate();

    return QueryUtil.setQueryDates(xml,valueQuery,dateAttribute);

  }

}
