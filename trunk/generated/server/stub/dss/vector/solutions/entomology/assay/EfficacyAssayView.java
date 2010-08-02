package dss.vector.solutions.entomology.assay;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.attributes.InvalidReferenceException;
import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectablePrimitive;

import dss.vector.solutions.MdssLog;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.Surface;

public class EfficacyAssayView extends EfficacyAssayViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242609508153L;

  public EfficacyAssayView()
  {
    super();
  }

  private void populateConcrete(EfficacyAssay concrete)
  {
    GeoEntity entity = GeoEntity.searchByGeoId(this.getGeoId());

    if (entity == null || ! ( entity instanceof Surface ))
    {
      throw new InvalidReferenceException("[" + this.getGeoId() + "] is not a valid Surface GeoId", (MdAttributeReferenceDAOIF) EfficacyAssay.getGeoEntityMd());
    }

    concrete.getAgeRange().setEndPoint(this.getAgeRange().getEndPoint());
    concrete.getAgeRange().setStartPoint(this.getAgeRange().getStartPoint());
    concrete.setColonyName(this.getColonyName());
    concrete.setExposureTime(this.getExposureTime());
    concrete.setFed(this.getFed());
    concrete.setGeoEntity((Surface) entity);
    concrete.setGravid(this.getGravid());
    concrete.setHoldingTime(this.getHoldingTime());
    concrete.setInsecticideBrand(this.getInsecticideBrand());
    concrete.setMortality(this.getMortality());
    concrete.setQuantityDead(this.getQuantityDead());
    concrete.setQuantityLive(this.getQuantityLive());
    concrete.setQuantityTested(this.getQuantityTested());
    concrete.setSpecie(this.getSpecie());
    concrete.setTestDate(this.getTestDate());
    concrete.setTestMethod(this.getTestMethod());
    concrete.setTimeOnSurface(this.getTimeOnSurface());
    concrete.setSex(this.getSex());
    concrete.setSurfacePostion(this.getSurfacePostion());
    concrete.setSurfaceType(this.getSurfaceType());
    if (this.isNew() && this.getDisease() != null)
    {
      concrete.setDisease(this.getDisease());
    }
  }

  public void populateView(EfficacyAssay assay)
  {
    this.setConcreteId(assay.getId());
    this.getAgeRange().setEndPoint(assay.getAgeRange().getEndPoint());
    this.getAgeRange().setStartPoint(assay.getAgeRange().getStartPoint());
    this.setColonyName(assay.getColonyName());
    this.setExposureTime(assay.getExposureTime());
    this.setFed(assay.getFed());
    this.setGeoId(assay.getGeoEntity().getGeoId());
    this.setGravid(assay.getGravid());
    this.setHoldingTime(assay.getHoldingTime());
    this.setInsecticideBrand(assay.getInsecticideBrand());
    this.setMortality(assay.getMortality());
    this.setQuantityDead(assay.getQuantityDead());
    this.setQuantityLive(assay.getQuantityLive());
    this.setQuantityTested(assay.getQuantityTested());
    this.setSpecie(assay.getSpecie());
    this.setTestDate(assay.getTestDate());
    this.setTestMethod(assay.getTestMethod());
    this.setTimeOnSurface(assay.getTimeOnSurface());
    this.setSex(assay.getSex());
    this.setSurfacePostion(assay.getSurfacePostion());
    this.setSurfaceType(assay.getSurfaceType());
    this.setDisease(assay.getDisease());
  }

  @Override
  @Transaction
  public void apply()
  {
    EfficacyAssay concrete = new EfficacyAssay();

    if (this.hasConcreteId())
    {
      concrete = EfficacyAssay.lock(this.getConcreteId());
    }
    this.populateMapping(concrete);
    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  private void populateMapping(EfficacyAssay concrete)
  {
    new AttributeNotificationMap(concrete.getAgeRange(), AdultAgeRange.STARTPOINT, this, EfficacyAssayView.AGERANGE);
    new AttributeNotificationMap(concrete.getAgeRange(), AdultAgeRange.ENDPOINT, this, EfficacyAssayView.AGERANGE);
    new AttributeNotificationMap(concrete, EfficacyAssay.COLONYNAME, this, EfficacyAssayView.COLONYNAME);
    new AttributeNotificationMap(concrete, EfficacyAssay.EXPOSURETIME, this, EfficacyAssayView.EXPOSURETIME);
    new AttributeNotificationMap(concrete, EfficacyAssay.FED, this, EfficacyAssayView.FED);
    new AttributeNotificationMap(concrete, EfficacyAssay.GEOENTITY, this, EfficacyAssayView.GEOID);
    new AttributeNotificationMap(concrete, EfficacyAssay.GRAVID, this, EfficacyAssayView.GRAVID);
    new AttributeNotificationMap(concrete, EfficacyAssay.HOLDINGTIME, this, EfficacyAssayView.HOLDINGTIME);
    new AttributeNotificationMap(concrete, EfficacyAssay.INSECTICIDEBRAND, this, EfficacyAssayView.INSECTICIDEBRAND);
    new AttributeNotificationMap(concrete, EfficacyAssay.MORTALITY, this, EfficacyAssayView.MORTALITY);
    new AttributeNotificationMap(concrete, EfficacyAssay.QUANTITYDEAD, this, EfficacyAssayView.QUANTITYDEAD);
    new AttributeNotificationMap(concrete, EfficacyAssay.QUANTITYLIVE, this, EfficacyAssayView.QUANTITYLIVE);
    new AttributeNotificationMap(concrete, EfficacyAssay.QUANTITYTESTED, this, EfficacyAssayView.QUANTITYTESTED);
    new AttributeNotificationMap(concrete, EfficacyAssay.SPECIE, this, EfficacyAssayView.SPECIE);
    new AttributeNotificationMap(concrete, EfficacyAssay.TESTDATE, this, EfficacyAssayView.TESTDATE);
    new AttributeNotificationMap(concrete, EfficacyAssay.TESTMETHOD, this, EfficacyAssayView.TESTMETHOD);
    new AttributeNotificationMap(concrete, EfficacyAssay.TIMEONSURFACE, this, EfficacyAssayView.TIMEONSURFACE);
    new AttributeNotificationMap(concrete, EfficacyAssay.SEX, this, EfficacyAssayView.SEX);
    new AttributeNotificationMap(concrete, EfficacyAssay.SURFACEPOSTION, this, EfficacyAssayView.SURFACEPOSTION);
    new AttributeNotificationMap(concrete, EfficacyAssay.SURFACETYPE, this, EfficacyAssayView.SURFACETYPE);
    new AttributeNotificationMap(concrete, EfficacyAssay.DISEASE, this, EfficacyAssayView.DISEASE);
  }

  private boolean hasConcreteId()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Override
  public void deleteConcrete()
  {
    if (this.hasConcreteId())
    {
      EfficacyAssay.get(this.getConcreteId()).delete();
    }
  }

  public Float getOverallMortalityRate()
  {
    if (this.hasConcreteId())
    {
      return EfficacyAssay.get(this.getConcreteId()).getOverallMortalityRate();
    }

    return 0.0F;
  }

  public static EfficacyAssayView[] searchByGeoEntityAndDate(String geoId, Date collectionDate)
  {
    QueryFactory factory = new QueryFactory();
    EfficacyAssayQuery query = new EfficacyAssayQuery(factory);

    query.WHERE(query.getGeoEntity().getGeoId().EQ(geoId));
    query.AND(query.getTestDate().EQ(collectionDate));

    OIterator<? extends EfficacyAssay> iterator = query.getIterator();

    try
    {
      List<EfficacyAssayView> list = new LinkedList<EfficacyAssayView>();

      while (iterator.hasNext())
      {
        EfficacyAssay next = iterator.next();

        list.add(next.getView());
      }

      return list.toArray(new EfficacyAssayView[list.size()]);
    }
    finally
    {
      iterator.close();
    }
  }

  public static EfficacyAssayViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    EfficacyAssayViewQuery query = new EfficacyAssayViewQuery(new QueryFactory());

    if (sortAttribute == null)
    {
      sortAttribute = COLONYNAME;
    }

    SelectablePrimitive selectable = (SelectablePrimitive) query.getComponentQuery().getSelectableRef(sortAttribute);

    if (isAscending)
    {
      query.ORDER_BY_ASC(selectable, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC(selectable, sortAttribute);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;
  }

  public static EfficacyAssayViewQuery getPageForDisease(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    EfficacyAssayViewQuery query = getPage(sortAttribute, isAscending, pageSize, pageNumber);
    query.WHERE(query.getDisease().EQ(Disease.getCurrent()));
    return query;
  }
}