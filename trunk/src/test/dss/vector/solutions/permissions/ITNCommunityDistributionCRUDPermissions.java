package dss.vector.solutions.permissions;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.Test;
import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO;
import dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO;
import dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO;
import dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO;

public class ITNCommunityDistributionCRUDPermissions extends PermissionTest 
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(ITNCommunityDistributionCRUDPermissions.class, MDSSRoleInfo.MDSS_CORRDINATOR, MDSSRoleInfo.DATACAPTURER, MDSSRoleInfo.OPERATIONAL_MANAGER);
  }

  public void testITNCommunityDistribution()
  {
    ITNCommunityDistributionViewDTO view = new ITNCommunityDistributionViewDTO(request);
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setBatchNumber("55");
    view.setEntryType(true);
    view.setHouseholdAddress(siteGeoId);
    view.setHouseholdName("Justin");
    view.setHouseholdSurname("Smethie");
    view.setResidents(50);
    view.setSold(true);
    view.setCurrencyReceived(new BigDecimal(55.55));
    view.setRetrieved(true);
    view.setNumberRetrieved(30);
    view.setPretreated(true);

    ITNCommunityNetDTO[] nets = view.getITNCommunityNets();

    for (ITNCommunityNetDTO net : nets)
    {
      net.setAmount(40);
    }

    ITNCommunityTargetGroupDTO[] groups = view.getITNCommunityTargetGroups();

    for (ITNCommunityTargetGroupDTO group : groups)
    {
      group.setAmount(10);
    }

    view.applyAll(nets, groups);

    try
    {
      ITNCommunityDistributionViewDTO edit = ITNCommunityDistributionDTO.lockView(request, view.getConcreteId());
      edit.setResidents(50);

      ITNCommunityNetDTO[] lockedNets = edit.getITNCommunityNets();

      for (ITNCommunityNetDTO net : lockedNets)
      {
        net.setAmount(20);
      }

      ITNCommunityTargetGroupDTO[] lockedGroups = edit.getITNCommunityTargetGroups();

      for (ITNCommunityTargetGroupDTO group : lockedGroups)
      {
        group.setAmount(20);
      }

      edit.applyAll(lockedNets, lockedGroups);

      ITNCommunityDistributionViewDTO test = ITNCommunityDistributionDTO.getView(request, view.getConcreteId());

      assertEquals(edit.getConcreteId(), test.getConcreteId());
      assertEquals(edit.getResidents(), test.getResidents());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

}
