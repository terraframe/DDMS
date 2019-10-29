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
