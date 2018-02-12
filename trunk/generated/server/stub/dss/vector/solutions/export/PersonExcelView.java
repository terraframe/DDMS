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
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.PersonView;
import dss.vector.solutions.PersonWithDelegatesView;
import dss.vector.solutions.PersonWithDelegatesViewQuery;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class PersonExcelView extends PersonExcelViewBase implements Reloadable
{
  private static final long serialVersionUID = 1246241921437L;

  public PersonExcelView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    GeoEntity residentialEntity = this.getResidentialGeoEntity();
    GeoEntity workEntity = this.getWorkGeoEntity();

    PersonView personView = new PersonView();

    personView.setFirstName(this.getFirstName());
    personView.setLastName(this.getLastName());
    personView.setDateOfBirth(this.getDateOfBirth());
    personView.setIdentifier(this.getIdentifier());
    personView.setSex(Term.validateByDisplayLabel(this.getSex(), PersonView.getSexMd()));

    /*
     * Ticket #2736: The check on name, birth date and geo residence should only happen if there is no person ID
     */
    if (this.getIdentifier() != null && this.getIdentifier().length() > 0)
    {
      PersonWithDelegatesView existing = PersonView.getViewFromIdentifier(this.getIdentifier());

      if (existing != null)
      {
        personView = existing;
      }
    }
    else
    {
      PersonWithDelegatesViewQuery query = personView.searchForDuplicates();
      OIterator<? extends PersonWithDelegatesView> iterator = query.getIterator();

      try
      {
        if (iterator.hasNext())
        {
          personView = iterator.next();
        }
      }
      finally
      {
        iterator.close();
      }
    }

    if (residentialEntity != null)
    {
      personView.setResidentialGeoId(residentialEntity.getGeoId());
    }

    if (workEntity != null)
    {
      personView.setWorkGeoId(workEntity.getGeoId());
    }

    personView.setIsMDSSUser(this.getIsMDSSUser() != null && this.getIsMDSSUser());
    personView.setUsername(this.getUsername());
    personView.setPassword(this.getPassword());
    String diseaseName = this.getDisease();
    if (diseaseName.equalsIgnoreCase(Disease.MALARIA))
    {
      personView.setDisease(Disease.getMalaria());
    }
    else if (diseaseName.equalsIgnoreCase(Disease.DENGUE))
    {
      personView.setDisease(Disease.getDengue());
    }

    personView.setIsPatient(this.getIsPatient() != null && this.getIsPatient());
    personView.setIsIPTRecipient(this.getIsIPTRecipient() != null && this.getIsIPTRecipient());
    personView.setIsITNRecipient(this.getIsITNRecipient() != null && this.getIsITNRecipient());

    personView.setIsSprayLeader(this.getIsSprayLeader() != null && this.getIsSprayLeader());
    personView.setIsSprayOperator(this.getIsSprayOperator() != null && this.getIsSprayOperator());
    personView.setMemberId(this.getMemberId());
    personView.setCode(this.getSupervisorCode());

    personView.setIsStockStaff(this.getIsStockStaff() != null && this.getIsStockStaff());
    personView.setIsSupervisor(this.getIsSupervisor() != null && this.getIsSupervisor());

    personView.apply();
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(FIRSTNAME);
    list.add(LASTNAME);
    list.add(DATEOFBIRTH);
    list.add(SEX);
    list.add(ISMDSSUSER);
    list.add(USERNAME);
    list.add(PASSWORD);
    list.add(DISEASE);
    list.add(ISSPRAYLEADER);
    list.add(MEMBERID);
    list.add(ISSPRAYOPERATOR);
    list.add(ISSTOCKSTAFF);
    list.add(ISSUPERVISOR);
    list.add(SUPERVISORCODE);
    list.add(ISIPTRECIPIENT);
    list.add(ISITNRECIPIENT);
    list.add(ISPATIENT);
    return list;
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener(RESIDENTIALGEOENTITY, null));
    exporter.addListener(createExcelGeoListener(WORKGEOENTITY, null));
  }

  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    context.addListener(createExcelGeoListener(RESIDENTIALGEOENTITY, importer));
    context.addListener(createExcelGeoListener(WORKGEOENTITY, importer));
  }

  private static DynamicGeoColumnListener createExcelGeoListener(String attributeName, ExcelImportManager importer)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllPoliticals())
    {
      builder.add(hierarchy);
    }
    return new DynamicGeoColumnListener(CLASS, attributeName, builder, importer);
  }
}
