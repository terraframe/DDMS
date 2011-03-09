package dss.vector.solutions.irs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.generated.GeoEntity;

public class OperatorSpray extends OperatorSprayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long          serialVersionUID = 1240853391117L;

  public OperatorSpray()
  {
    super();
  }
  
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    
    return this.getClassDisplayLabel();
  }

  @Override
  protected String buildKey()
  {
    if (this.getBrand() != null && this.getGeoEntity() != null && this.getSprayDate() != null && this.getSprayMethodForIndex() != null  && this.getSprayOperator() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      String dateFormat = format.format(this.getSprayDate());
      String methodName = this.getSprayMethodForIndex();

      return this.getBrand().getKey() + "." + this.getGeoEntity().getGeoId() + "." + dateFormat + "." + methodName + "." + this.getSprayOperator().getKey();
    }

    return this.getId();
  }
  
  @Override
  public void apply()
  {
    List<SprayMethod> _sprayMethod = this.getSprayMethod();

    if(_sprayMethod.size() > 0)
    {
      this.setSprayMethodForIndex(_sprayMethod.get(0).getEnumName());      
    }
    
    this.setGeoEntityForIndex(this.getGeoEntity());
    this.setBrandForIndex(this.getBrand());
    this.setSprayDateForIndex(this.getSprayDate());
    
    
	if (this.isNew() && this.getDisease() == null) {
		this.setDisease(Disease.getCurrent());
	}
	
    super.apply();
  }

  public OperatorSprayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public OperatorSprayView lockView()
  {
    this.lock();

    return this.getView();
  }

  public OperatorSprayView getView()
  {
    OperatorSprayView view = new OperatorSprayView();

    view.populateView(this);

    return view;
  }
  
  public static OperatorSpray get(Date sprayDate, GeoEntity geoEntity, InsecticideBrand brand, TeamMember operator, SprayMethod... sprayMethod)
  {
    OperatorSprayQuery query = new OperatorSprayQuery(new QueryFactory());
    query.WHERE(query.getSprayDate().EQ(sprayDate));
    query.WHERE(query.getGeoEntity().EQ(geoEntity));
    query.WHERE(query.getBrand().EQ(brand));
    query.WHERE(query.getSprayMethod().containsAll(sprayMethod));
    query.AND(query.getSprayOperator().EQ(operator));

    OIterator<? extends OperatorSpray> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }
}
