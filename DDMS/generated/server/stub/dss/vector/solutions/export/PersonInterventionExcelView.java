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

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.intervention.monitor.ControlInterventionView;
import dss.vector.solutions.intervention.monitor.PersonInterventionMethodView;
import dss.vector.solutions.intervention.monitor.PersonInterventionView;
import dss.vector.solutions.ontology.Term;

public class PersonInterventionExcelView extends PersonInterventionExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 311164596;

  private List<Term>        interventionMethod;

  private List<Integer>     interventionMethodAmount;
  
  public PersonInterventionExcelView()
  {
    super();
    interventionMethod = new LinkedList<Term>();
    interventionMethodAmount = new LinkedList<Integer>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    ControlInterventionView controlPoint = this.getControlPoint();
    PersonInterventionView piv = new PersonInterventionView();

    PersonInterventionView[] existingPersonInterventions = controlPoint.getPersonInterventionViews();
    // There's no criteria to check against; we only expect 1 row
    if (existingPersonInterventions.length>0)
    {
      piv = existingPersonInterventions[0];
    }
    piv.setVehicleDays(this.getVehicleDays());

    PersonInterventionMethodView[] existingMethods = piv.getInterventionMethods();
    PersonInterventionMethodView[][] methodArray = new PersonInterventionMethodView[1][interventionMethod.size()];
    for (int i = 0; i < interventionMethod.size(); i++)
    {
      // Default to a new relationship
      methodArray[0][i] = new PersonInterventionMethodView();
      Term term = interventionMethod.get(i);
      methodArray[0][i].setTerm(term);
      
      // If a relationship already exists, use it instead
      for (PersonInterventionMethodView existing : existingMethods)
      {
        // Use IDs to avoid cost of instantiating the whole object
        if (existing.getValue(PersonInterventionMethodView.TERM).equals(term.getId()))
        {
          methodArray[0][i] = existing;
          break;
        }
      }

      // Set the amount
      methodArray[0][i].setAmount(interventionMethodAmount.get(i));
    }
    
    controlPoint.applyWithPersonInterventionViews(new PersonInterventionView[] {piv}, methodArray);
  }

  public static LinkedList<String> customAttributeOrder()
  {
    LinkedList<String> list = ControlInterventionExcelView.customAttributeOrder();
    list.add(VEHICLEDAYS);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    ControlInterventionExcelView.setupExportListener(exporter, params);
    exporter.addListener(new PersonInterventionListener());
  }
  
  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    ControlInterventionExcelView.setupImportListener(context, params, importer);
    context.addListener(new PersonInterventionListener());
  }

  public void addInterventionMethod(Term term, Integer amount)
  {
    interventionMethod.add(term);
    interventionMethodAmount.add(amount);
  }
}
