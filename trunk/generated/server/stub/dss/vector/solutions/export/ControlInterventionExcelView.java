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
package dss.vector.solutions.export;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.intervention.monitor.ControlInterventionView;
import dss.vector.solutions.util.HierarchyBuilder;

public class ControlInterventionExcelView extends ControlInterventionExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1186841377;
  private ControlInterventionView civ;
  
  public ControlInterventionExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    getControlPoint();
    civ.apply();
  }
  
  protected ControlInterventionView getControlPoint()
  {
    if (this.civ==null)
    {
      this.civ = new ControlInterventionView();
      
      populateView();
      this.civ = ControlInterventionView.getIntervention(this.civ);
      populateView();
    }
    return this.civ;
  }

  private void populateView()
  {
    GeoEntity geo = this.getGeoEntity();
    if (geo != null)
    {
      this.civ.setGeoEntity(geo);
    }

    Date start = this.getStartDate();
    if (start != null)
    {
      this.civ.setStartDate(start);
    }

    Date end = this.getEndDate();
    if (end != null)
    {
      this.civ.setEndDate(end);
    }

    String comment = this.getComments();
    if (comment.length() > 0)
    {
      this.civ.setComments(comment);
    }
  }

  public static LinkedList<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(STARTDATE);
    list.add(ENDDATE);
    list.add(COMMENTS);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener(null));
  }
  
  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    context.addListener(createExcelGeoListener(importer));
  }

  private static DynamicGeoColumnListener createExcelGeoListener(ExcelImportManager importer)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllUrban())
    {
      builder.add(hierarchy);
    }
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder, importer);
  }
}
