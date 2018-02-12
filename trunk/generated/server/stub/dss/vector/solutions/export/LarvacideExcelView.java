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

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.intervention.monitor.Larvacide;
import dss.vector.solutions.intervention.monitor.LarvacideInstance;
import dss.vector.solutions.intervention.monitor.LarvacideInstanceView;
import dss.vector.solutions.intervention.monitor.LarvacideQuery;
import dss.vector.solutions.irs.TeamMember;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class LarvacideExcelView extends LarvacideExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 2093506272;

  public LarvacideExcelView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    Larvacide larvacide = getLarvacide();

    LarvacideInstance instance = new LarvacideInstance();
    instance.setTarget(Term.validateByDisplayLabel(this.getTarget(), LarvacideInstanceView.getTargetMd()));
    instance.setTreated(this.getTreated());
    instance.setControlMethod(Term.validateByDisplayLabel(this.getControlMethod(), LarvacideInstanceView.getControlMethodMd()));
    instance.setSubstance(Term.validateByDisplayLabel(this.getSubstance(), LarvacideInstanceView.getSubstanceMd()));
    instance.setUnit(Term.validateByDisplayLabel(this.getUnit(), LarvacideInstanceView.getUnitMd()));
    instance.setUnitsUsed(this.getUnitsUsed());
    instance.apply();

    larvacide.addInstances(instance).apply();
  }

  private Larvacide getLarvacide()
  {
    Larvacide larvacide = new Larvacide();
    
    LarvacideQuery larvacideQuery = new LarvacideQuery(new QueryFactory());
    larvacideQuery.WHERE(larvacideQuery.getStartDate().EQ(this.getStartDate()));
    larvacideQuery.WHERE(larvacideQuery.getCompletionDate().EQ(this.getCompletionDate()));
    larvacideQuery.WHERE(larvacideQuery.getGeoEntity().EQ(this.getGeoEntity()));
    larvacideQuery.WHERE(larvacideQuery.getNatureOfControl().EQ(this.getNatureOfControl()));
    OIterator<? extends Larvacide> larvacideIterator = larvacideQuery.getIterator();

    if (larvacideIterator.hasNext())
    {
      larvacide = larvacideIterator.next();
      larvacide.lock();
    }
    else
    {
      larvacide.setStartDate(this.getStartDate());
      larvacide.setCompletionDate(this.getCompletionDate());
      larvacide.setGeoEntity(this.getGeoEntity());
      larvacide.setNatureOfControl(this.getNatureOfControl());
    }
    larvacideIterator.close();

    String description = this.getGeoDescription();
    if (description.length() > 0)
    {
      larvacide.setGeoDescription(description);
    }

    Integer count = this.getPersonCount();
    if (count != null)
    {
      larvacide.setPersonCount(count);
    }

    String leaderId = this.getTeamLeaderId();
    if (leaderId.length() > 0)
    {
      larvacide.setTeamLeader(TeamMember.getSprayLeaderById(leaderId));
    }
    
    larvacide.apply();
    return larvacide;
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(STARTDATE);
    list.add(COMPLETIONDATE);
    list.add(NATUREOFCONTROL);
    list.add(PERSONCOUNT);
    list.add(TEAMLEADERID);
    list.add(TARGET);
    list.add(TREATED);
    list.add(CONTROLMETHOD);
    list.add(SUBSTANCE);
    list.add(UNIT);
    list.add(UNITSUSED);
    list.add(GEODESCRIPTION);
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
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllPoliticals())
    {
      builder.add(hierarchy);
    }
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder, importer);
  }
}
