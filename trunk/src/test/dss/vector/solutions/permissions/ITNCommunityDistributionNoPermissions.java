package dss.vector.solutions.permissions;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO;
import dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO;
import dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO;

public abstract class ITNCommunityDistributionNoPermissions extends TestCase
{

  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static String          geoId;
  
  public void testITNCommunityDistribution()
  {
    try
    {
      ITNCommunityDistributionViewDTO view = new ITNCommunityDistributionViewDTO(request);
      view.setStartDate(new Date());
      view.setEndDate(new Date());
      view.setAgentFirstName("Justin");
      view.setAgentSurname("Smethie");
      view.setBatchNumber("55");
      view.setEntryType(true);
      view.setHouseholdAddress(geoId);
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
      view.deleteConcrete();

      fail("Able to create an ITNCommunityDistribution without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }
}
