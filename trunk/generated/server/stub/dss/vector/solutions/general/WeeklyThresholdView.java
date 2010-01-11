package dss.vector.solutions.general;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.dataaccess.MdViewDAOIF;
import com.terraframe.mojo.dataaccess.metadata.MdViewDAO;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ViewArrayExcelExporter;

public class WeeklyThresholdView extends WeeklyThresholdViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = -431874784;
  
  public WeeklyThresholdView()
  {
    super();
  }

  @Authenticate
  public static InputStream exportHistory()
  {
    List<WeeklyThresholdView> views = new LinkedList<WeeklyThresholdView>();
    WeeklyThresholdQuery query = new WeeklyThresholdQuery(new QueryFactory());
    
    OIterator<? extends WeeklyThreshold> iterator = query.getIterator();
    
    try
    {
      while(iterator.hasNext())
      {
        WeeklyThreshold threshold = iterator.next();
        
        List<WeeklyThresholdView> list = threshold.export();
        
        views.addAll(list);       
      }
      
      List<String> attributes = new LinkedList<String>();
      
      attributes.add(WeeklyThresholdView.ENTITYLABEL);
      attributes.add(WeeklyThresholdView.PERIOD);
      attributes.add(WeeklyThresholdView.YEAROFWEEK);
      attributes.add(WeeklyThresholdView.THRESHOLDVALUE);
      attributes.add(WeeklyThresholdView.THRESHSOLDTYPE);
      attributes.add(WeeklyThresholdView.THRESHOLDDATE);
      
      WeeklyThresholdView[] array = views.toArray(new WeeklyThresholdView[views.size()]);
      MdViewDAOIF mdView = MdViewDAO.getMdViewDAO(WeeklyThresholdView.CLASS);
            
      return new ViewArrayExcelExporter(array, attributes, mdView, "ThresholdHistory").exportStream();
    }
    finally
    {
      iterator.close();
    }
  }  
}
