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
package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.SprayZone;
import dss.vector.solutions.util.HierarchyBuilder;


public class SprayTeamExcelView extends SprayTeamExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 786340377;
  
  public SprayTeamExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    // Validates teamId and also operatorId
    this.applyNoPersist();
    
    SprayTeam team = SprayTeam.getByTeamId(this.getTeamId());
    SprayZone zone = this.getSprayZone();
    String leaderId = this.getTeamLeaderId();
    
    // Create a new team.
    if (team == null)
    {
      if (zone == null)
      {
        RequiredGeoIdProblem p = new RequiredGeoIdProblem();
        p.throwIt();
      }
      
      team = new SprayTeam();
      team.setTeamId(this.getTeamId());
      team.setSprayZone(zone);
      team.apply();
    }
    // Edit an existing team.
    else
    {
      // Override existing values
      if (zone != null)
      {
        team.appLock();
        team.setSprayZone(zone);
        team.apply();
      }
    }
    
    // Set the team leader
    if (leaderId != null && !leaderId.equals(""))
    {
      TeamMember leader = TeamMember.getMemberById(leaderId);
      
      // Integrity check: a team can only have 1 leader
      OIterator<? extends LeadTeam> rels = team.getAllTeamLeaderRel();
      try
      {
        for (LeadTeam rel : rels)
        {
          rel.delete();
        }
      }
      finally
      {
        rels.close();
      }
      
      // Integrity check: A TeamMember can only be the leader of one team.
      OIterator<? extends LeadTeam> leaderRels = leader.getAllLeadsTeamRel();
      try
      {
        for (LeadTeam rel : leaderRels)
        {
          rel.delete();
        }
      }
      finally
      {
        leaderRels.close();
      }
      
      team.addTeamLeader(leader).apply();
    }

    // If there's an operatorId then add the operator to the team.
    String operatorId = this.getOperatorId();
    if (operatorId != null && !operatorId.equals(""))
    {
      TeamMember operator = TeamMember.getMemberById(operatorId);
      
      // Integrity check: operators can only be assigned to one team.
      OIterator<? extends InTeam> rels = operator.getAllSprayTeamRel();
      try
      {
        for (InTeam rel : rels)
        {
          rel.delete();
        }
      }
      finally
      {
        rels.close();
      }
      
      // Add the operator to the team
      team.addSprayTeamMembers(operator).apply();
    }
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
    builder.add(GeoHierarchy.getGeoHierarchyFromType(SprayZone.CLASS));
    return new DynamicGeoColumnListener(CLASS, SprayTeamView.SPRAYZONE, builder, importer);
  }
}
