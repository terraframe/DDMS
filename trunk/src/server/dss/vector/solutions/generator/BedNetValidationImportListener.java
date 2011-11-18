package dss.vector.solutions.generator;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.io.excel.ImportAdapter;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.form.HouseholdMismatchException;
import dss.vector.solutions.form.business.FormBedNet;
import dss.vector.solutions.form.business.FormHousehold;
import dss.vector.solutions.form.business.FormPerson;

public class BedNetValidationImportListener extends ImportAdapter implements Reloadable
{
  @Override
  public void beforeApply(Mutable instance)
  {
    String householdId = instance.getValue(FormPerson.HOUSEHOLD);
    String netId = instance.getValue(FormPerson.NET);

    if (householdId != null && householdId.length() > 0 && netId != null && netId.length() > 0)
    {
      FormHousehold household = FormHousehold.get(householdId);
      FormBedNet bedNet = FormBedNet.get(netId);

      if (!bedNet.getHouseholdId().equals(householdId))
      {
        String msg = "The bednet [" + bedNet.getOid() + "] is not under the household [" + household.getOid() + "]";

        HouseholdMismatchException ex = new HouseholdMismatchException(msg);
        ex.setHouseholdId(household.getOid());
        ex.setNetId(bedNet.getOid());
        ex.setPersonId(instance.getValue(FormPerson.OID));
        ex.apply();

        throw ex;
      }
    }
  }
}