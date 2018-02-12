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
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.AggregatedCaseView;
import dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView;
import dss.vector.solutions.surveillance.CaseDiagnosisTypeView;
import dss.vector.solutions.surveillance.CaseDiagnosticView;
import dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView;
import dss.vector.solutions.surveillance.CaseDiseaseManifestationView;
import dss.vector.solutions.surveillance.CasePatientTypeAmountView;
import dss.vector.solutions.surveillance.CasePatientTypeView;
import dss.vector.solutions.surveillance.CaseReferralView;
import dss.vector.solutions.surveillance.CaseStockReferralView;
import dss.vector.solutions.surveillance.CaseTreatmentMethodView;
import dss.vector.solutions.surveillance.CaseTreatmentStockView;
import dss.vector.solutions.surveillance.CaseTreatmentView;

public class AggregatedCaseTreatmentsExcelView extends AggregatedCaseTreatmentsExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1333048936;

  private List<Term>        treatments;

  private List<Integer>     treatmentAmounts;

  private List<Term>        methods;

  private List<Integer>     methodAmounts;
  
  private List<Term>        stock;
  
  private List<Boolean>     stockValues;
  
  public AggregatedCaseTreatmentsExcelView()
  {
    super();
    treatments = new LinkedList<Term>();
    treatmentAmounts = new LinkedList<Integer>();
    methods = new LinkedList<Term>();
    methodAmounts = new LinkedList<Integer>();
    stock = new LinkedList<Term>();
    stockValues = new LinkedList<Boolean>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    AggregatedCaseView acv = this.getAggregatedCaseView();
    
    CaseTreatmentView[] existingTreatments = acv.getTreatments();
    CaseTreatmentView[] treatmentArray = new CaseTreatmentView[treatments.size()];
    for (int i=0; i< treatments.size(); i++)
    {
      // Default to a new record
      treatmentArray[i] = new CaseTreatmentView();
      Term treatment = treatments.get(i);
      treatmentArray[i].setTerm(treatment);
      
      // If a record already exists, use it instead
      for (CaseTreatmentView existing : existingTreatments)
      {
        // Use IDs to avoid cost of instantiating the whole object
        if (existing.getValue(CaseTreatmentView.TERM).equals(treatment.getId()))
        {
          treatmentArray[i] = existing;
        }
      }
      
      // Set the amount
      treatmentArray[i].setAmount(treatmentAmounts.get(i));
    }
    
    CaseTreatmentMethodView[] existingMethods = acv.getTreatmentMethods();
    CaseTreatmentMethodView[] methodArray = new CaseTreatmentMethodView[methods.size()];
    for (int i=0; i< methods.size(); i++)
    {
      // Default to a new record
      methodArray[i] = new CaseTreatmentMethodView();
      Term method = methods.get(i);
      methodArray[i].setTerm(method);
      
      // If a record already exists, use it instead
      for (CaseTreatmentMethodView existing : existingMethods)
      {
        // Use IDs to avoid cost of instantiating the whole object
        if (existing.getValue(CaseTreatmentMethodView.TERM).equals(method.getId()))
        {
          methodArray[i] = existing;
        }
      }
      
      // Set the amount
      methodArray[i].setAmount(methodAmounts.get(i));
    }
    
    CaseTreatmentStockView[] existingStocks = acv.getTreatmentStocks();
    CaseTreatmentStockView[] stockArray = new CaseTreatmentStockView[stock.size()];
    for (int i=0; i< stock.size(); i++)
    {
      // Default to a new record
      stockArray[i] = new CaseTreatmentStockView();
      Term term = stock.get(i);
      stockArray[i].setTerm(term);
      
      // If a record already exists, use it instead
      for (CaseTreatmentStockView existing : existingStocks)
      {
        // Use IDs to avoid cost of instantiating the whole object
        if (existing.getValue(CaseTreatmentStockView.TERM).equals(term.getId()))
        {
          stockArray[i] = existing;
        }
      }
      
      // Set out of stock
      stockArray[i].setOutOfStock(stockValues.get(i));
    }
    
    acv.applyAll(treatmentArray, methodArray, stockArray, new CaseDiagnosticView[0], new CaseReferralView[0], new CaseStockReferralView[0], new CaseDiagnosisTypeView[0], new CaseDiagnosisTypeAmountView[0][0], new CaseDiseaseManifestationView[0], new CaseDiseaseManifestationAmountView[0][0], new CasePatientTypeView[0], new CasePatientTypeAmountView[0][0]);
  }
  
  public static List<String> customAttributeOrder()
  {
    List<String> list = AggregatedCaseExcelView.customAttributeOrder();
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    AggregatedCaseExcelView.setupExportListener(exporter, params);
    exporter.addListener(new AggregatedCaseTreatmentListener());
  }
  
  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    AggregatedCaseExcelView.setupImportListener(context, params, importer);
    context.addListener(new AggregatedCaseTreatmentListener());
  }

  public void addStock(Term grid, Boolean inStock)
  {
    stock.add(grid);
    stockValues.add(inStock);
  }

  public void addTreatment(Term grid, Integer count)
  {
    treatments.add(grid);
    treatmentAmounts.add(count);
  }

  public void addMethod(Term grid, Integer count)
  {
    methods.add(grid);
    methodAmounts.add(count);
  }
  
}
