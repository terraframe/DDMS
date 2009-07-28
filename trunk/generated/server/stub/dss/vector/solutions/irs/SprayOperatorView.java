package dss.vector.solutions.irs;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.Person;

public class SprayOperatorView extends SprayOperatorViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241483300982L;
  
  public SprayOperatorView()
  {
    super();
  }
  
  public static SprayOperatorView[] getAll()
  {
    SprayOperatorQuery query = new SprayOperatorQuery(new QueryFactory());
    
    return getViewsFromQuery(query);
  }
  
  public static SprayOperatorView[] getAllForLocation(String geoId)
  {
    QueryFactory queryFactory = new QueryFactory();
    
    SprayTeamQuery sprayTeamQuery = new SprayTeamQuery(queryFactory);
    sprayTeamQuery.WHERE(sprayTeamQuery.getSprayZone().getGeoId().EQ(geoId));
    
    InTeamQuery inTeamQuery = new InTeamQuery(queryFactory);
    inTeamQuery.WHERE(inTeamQuery.hasParent(sprayTeamQuery));
    
    SprayOperatorQuery sprayOperatorQuery = new SprayOperatorQuery(queryFactory);
    sprayOperatorQuery.WHERE(sprayOperatorQuery.sprayTeam(inTeamQuery));
    
    return getViewsFromQuery(sprayOperatorQuery);    
  }
  
  public static SprayOperatorView[] getAllForTeam(SprayTeam sprayTeam)
  {
    QueryFactory queryFactory = new QueryFactory();
    
    SprayTeamQuery sprayTeamQuery = new SprayTeamQuery(queryFactory);
    sprayTeamQuery.WHERE(sprayTeamQuery.getId().EQ(sprayTeam.getId()));
    
    InTeamQuery inTeamQuery = new InTeamQuery(queryFactory);
    inTeamQuery.WHERE(inTeamQuery.hasParent(sprayTeamQuery));
    
    SprayOperatorQuery sprayOperatorQuery = new SprayOperatorQuery(queryFactory);
    sprayOperatorQuery.WHERE(sprayOperatorQuery.sprayTeam(inTeamQuery));
    
    return getViewsFromQuery(sprayOperatorQuery);
  }

  private static SprayOperatorView[] getViewsFromQuery(SprayOperatorQuery query)
  {
    OIterator<? extends SprayOperator> iterator = query.getIterator();
    int count = (int)query.getCount();
    query.ORDER_BY_ASC(query.getPerson().getLastName());
    SprayOperatorView[] views = new SprayOperatorView[count];

    for (int i=0; i<count; i++)
    {
      SprayOperator next = iterator.next();
      Person person = next.getPerson();

      SprayOperatorView view = new SprayOperatorView();
      view.setActorId(next.getId());
      view.setOperatorId(next.getOperatorId());
      view.setFirstName(person.getFirstName());
      view.setLastName(person.getLastName());
      
      OIterator<? extends SprayTeam> sprayTeam = next.getAllSprayTeam();
      if (sprayTeam.hasNext())
      {
        view.setIsAssigned(true);
        view.setTeamId(sprayTeam.next().getTeamId());
      }
      else
      {
        view.setIsAssigned(false);
      }
      
      views[i] = view;
    }
    
    return views;
  }
  
  @Override
  public String toString()
  {
    return getFirstName() + " " + getLastName() + " " + getOperatorId();
  }
}
