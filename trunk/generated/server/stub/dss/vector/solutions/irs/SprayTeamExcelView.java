package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;

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
      
      if (leaderId != null && !leaderId.equals(""))
      {
        team.addTeamLeader(TeamMember.getMemberById(leaderId)).apply();
      }
    }
    // Edit an existing team.
    else
    {
      // Override existing values
      if (zone != null)
      {
        team.setSprayZone(zone);
      }
      if (leaderId != null && !leaderId.equals(""))
      {
        // Integrity check: a team can only have 1 leader
        OIterator<? extends LeadTeam> rels = team.getAllTeamLeaderRel();
        try {
          for (LeadTeam rel : rels)
          {
            rel.delete();
          }
        }
        finally
        {
          rels.close();
        }
        
        team.addTeamLeader(TeamMember.getMemberById(leaderId)).apply();
      }
    }

    // If there's an operatorId then add the operator to the team.
    String operatorId = this.getOperatorId();
    if (operatorId != null && !operatorId.equals(""))
    {
      TeamMember operator = TeamMember.getMemberById(operatorId);
      
      // Integrity check: operators can only be assigned to one team.
      OIterator<? extends InTeam> rels = operator.getAllSprayTeamRel();
      try {
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
    exporter.addListener(createExcelGeoListener());
  }

  public static void setupImportListener(ImportContext context, String... params)
  {
    context.addListener(createExcelGeoListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(SprayZone.CLASS));
    return new DynamicGeoColumnListener(CLASS, SprayTeamView.SPRAYZONE, builder);
  }
}
