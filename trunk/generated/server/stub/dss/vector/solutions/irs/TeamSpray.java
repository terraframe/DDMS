package dss.vector.solutions.irs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.generated.GeoEntity;

public class TeamSpray extends TeamSprayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860702014L;

  public TeamSpray()
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
    else 
    {
      return this.buildKey();
    }
  }

  @Override
  protected String buildKey()
  {
    if (this.getBrand() != null && this.getGeoEntity() != null && this.getSprayDate() != null && this.getSprayMethodForIndex() != null  && this.getSprayTeam() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      String dateFormat = format.format(this.getSprayDate());
      String methodName = this.getSprayMethodForIndex();

      return this.getBrand().getKey() + "." + this.getGeoEntity().getGeoId() + "." + dateFormat + "." + methodName + "." + this.getSprayTeam().getKey();
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
  
  public TeamSprayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public TeamSprayView lockView()
  {
    this.lock();

    return this.getView();
  }

  public TeamSprayView getView()
  {
    TeamSprayView view = new TeamSprayView();

    view.populateView(this);

    return view;
  }

  public static TeamSpray get(Date sprayDate, GeoEntity geoEntity, InsecticideBrand brand, SprayTeam sprayTeam, SprayMethod[] sprayMethod)
  {
    TeamSprayQuery query = new TeamSprayQuery(new QueryFactory());
    query.WHERE(query.getSprayDate().EQ(sprayDate));
    query.WHERE(query.getGeoEntity().EQ(geoEntity));
    query.WHERE(query.getBrand().EQ(brand));
    query.WHERE(query.getSprayMethod().containsAll(sprayMethod));
    query.AND(query.getSprayTeam().EQ(sprayTeam));

    OIterator<? extends TeamSpray> it = query.getIterator();

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
