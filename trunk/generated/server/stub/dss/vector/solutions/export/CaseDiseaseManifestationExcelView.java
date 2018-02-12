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
import com.runwaysdk.session.Session;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.RequiredAttributeProblem;
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

public class CaseDiseaseManifestationExcelView extends CaseDiseaseManifestationExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1359771966;
  
  private List<Term>        diseaseCategory;
  
  private List<Integer>     diseaseCategoryAmount;
  
  public CaseDiseaseManifestationExcelView()
  {
    super();
    diseaseCategory = new LinkedList<Term>();
    diseaseCategoryAmount = new LinkedList<Integer>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    AggregatedCaseView acv = this.getAggregatedCaseView();
    
    CaseDiseaseManifestationView[] diseaseArray;
    CaseDiseaseManifestationAmountView[][] diseaseAmountArray;
    // Default to a new CaseDiseaseManifestationView
    CaseDiseaseManifestationView cdmv = new CaseDiseaseManifestationView();
    Term diseaseTerm = Term.validateByDisplayLabel(this.getDiseaseManifestation(), AggregatedCaseView.getCaseDiseaseManifestationMd());
    if (diseaseTerm!=null)
    {
      cdmv.setTerm(diseaseTerm);
      
      // If a CaseDiseaseManifestationView already exists, use it instead
      for (CaseDiseaseManifestationView existing : acv.getDiseaseManifestations())
      {
        // Use IDs to avoid cost of instantiating the whole object
        if (existing.getValue(CaseDiseaseManifestationView.TERM).equals(diseaseTerm.getId()))
        {
          cdmv = existing;
        }
      }
      
      CaseDiseaseManifestationAmountView[] existingAmounts = cdmv.getAmounts();
      diseaseArray = new CaseDiseaseManifestationView[]{cdmv};
      diseaseAmountArray = new CaseDiseaseManifestationAmountView[1][diseaseCategory.size()];

      for (int i=0; i<diseaseCategory.size(); i++)
      {
        diseaseAmountArray[0][i] = new CaseDiseaseManifestationAmountView();
        Term category = diseaseCategory.get(i);
        diseaseAmountArray[0][i].setTerm(category);
        // If a CaseDiseaseManifestationAmountView already exists, use it instead
        for (CaseDiseaseManifestationAmountView existing : existingAmounts)
        {
          // Use IDs to avoid cost of instantiating the whole object
          if (existing.getValue(CaseDiseaseManifestationAmountView.TERM).equals(category.getId()))
          {
            diseaseAmountArray[0][i] = existing;
          }
        }
        
        // Set the amount
        diseaseAmountArray[0][i].setAmount(diseaseCategoryAmount.get(i));
      }
      acv.applyAll(new CaseTreatmentView[0], new CaseTreatmentMethodView[0], new CaseTreatmentStockView[0], new CaseDiagnosticView[0], new CaseReferralView[0], new CaseStockReferralView[0], new CaseDiagnosisTypeView[0], new CaseDiagnosisTypeAmountView[0][0], diseaseArray, diseaseAmountArray, new CasePatientTypeView[0], new CasePatientTypeAmountView[0][0]);
    }
    else
    {
      RequiredAttributeProblem rap = new RequiredAttributeProblem();
      rap.setAttributeName(DISEASEMANIFESTATION);
      rap.setAttributeDisplayLabel(CaseDiseaseManifestationExcelView.getDiseaseManifestationMd().getDisplayLabel(Session.getCurrentLocale()));
      rap.throwIt();
    }
  }
  
  public static List<String> customAttributeOrder()
  {
    List<String> list = AggregatedCaseExcelView.customAttributeOrder();
    list.add(DISEASEMANIFESTATION);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    AggregatedCaseExcelView.setupExportListener(exporter, params);
    exporter.addListener(new CaseDiseaseManifestationListener());
  }
  
  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    AggregatedCaseExcelView.setupImportListener(context, params, importer);
    context.addListener(new CaseDiseaseManifestationListener());
  }
  
  public void addDiseaseCategory(Term term, Integer amount)
  {
    diseaseCategory.add(term);
    diseaseCategoryAmount.add(amount);
  }
  
}
