package dss.vector.solutions.irs;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;

public class SprayTeam extends SprayTeamBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240342487755L;

  public SprayTeam()
  {
    super();
  }

  public static SprayTeam[] search(GeoEntity geoEntity)
  {
    List<SprayTeam> list = new LinkedList<SprayTeam>();
    SprayTeamQuery query = new SprayTeamQuery(new QueryFactory());
    
    query.WHERE(query.getSprayZone().EQ(geoEntity.getGeoId()));
    query.ORDER_BY_ASC(query.getTeamCode());
    OIterator<? extends SprayTeam> it = query.getIterator();
    
    try
    {
      while(it.hasNext())
      {
        list.add(it.next());
      }
      
      return list.toArray(new SprayTeam[list.size()]);
    }
    finally
    {
      it.close();
    }
  }
}
