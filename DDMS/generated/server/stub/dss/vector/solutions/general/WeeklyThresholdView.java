/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.general;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.dataaccess.MdViewDAOIF;
import com.runwaysdk.dataaccess.metadata.MdViewDAO;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ViewArrayExcelExporter;

public class WeeklyThresholdView extends WeeklyThresholdViewBase implements com.runwaysdk.generation.loader.Reloadable
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

    QueryFactory factory = new QueryFactory();
    
    MalariaSeasonQuery seasonQuery = new MalariaSeasonQuery(factory);
    ThresholdDataQuery thresholdQuery = new ThresholdDataQuery(factory);
    WeeklyThresholdQuery query = new WeeklyThresholdQuery(factory);
    
    seasonQuery.WHERE(seasonQuery.getDisease().EQ(Disease.getCurrent()));
    thresholdQuery.WHERE(thresholdQuery.getSeason().EQ(seasonQuery));
    query.WHERE(query.parentId().EQ(thresholdQuery.getId()));
    
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
      
      attributes.add(WeeklyThresholdView.DISEASELABEL);
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
