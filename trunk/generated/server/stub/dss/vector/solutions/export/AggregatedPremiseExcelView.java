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

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodView;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonView;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitView;
import dss.vector.solutions.intervention.monitor.ControlInterventionView;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class AggregatedPremiseExcelView extends AggregatedPremiseExcelViewBase implements Reloadable
{
  private static final long serialVersionUID = 1378016463;

  private List<Term>        interventionMethod;

  private List<Integer>     interventionMethodAmount;
  
  private List<Term>        nonTreatmentReason;
  
  private List<Integer>     nonTreatmentReasonAmount;
  
  public AggregatedPremiseExcelView()
  {
    super();
    interventionMethod = new LinkedList<Term>();
    interventionMethodAmount = new LinkedList<Integer>();
    nonTreatmentReason = new LinkedList<Term>();
    nonTreatmentReasonAmount = new LinkedList<Integer>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    ControlInterventionView controlPoint = this.getControlPoint();
    GeoEntity subGeo = this.getPremiseGeoEntity();
    controlPoint.setAggregatedPremiseUniversal(GeoHierarchy.getGeoHierarchyFromType(subGeo.getType()));

    AggregatedPremiseVisitView apv = new AggregatedPremiseVisitView();
    apv.setGeoEntity(subGeo);
    
    for (AggregatedPremiseVisitView existing : controlPoint.getAggregatedPremiseViews())
    {
      // Use IDs to avoid cost of instantiating the whole object
      if (existing.getValue(AggregatedPremiseVisitView.GEOENTITY).equals(subGeo.getId()))
      {
        apv = existing;
        break;
      }
    }
    
    apv.setPremisesAvailable(this.getPremisesAvailable());
    apv.setPremisesIncluded(this.getPremisesIncluded());
    apv.setPremises(this.getPremises());
    apv.setVisited(this.getVisited());
    apv.setTreated(this.getTreated());

    AggregatedPremiseMethodView[] existingMethods = apv.getInterventionMethods();
    AggregatedPremiseMethodView[][] methodArray = new AggregatedPremiseMethodView[1][interventionMethod.size()];
    for (int i = 0; i < interventionMethod.size(); i++)
    {
      // Default to a new relationship
      Term term = interventionMethod.get(i);
      methodArray[0][i] = new AggregatedPremiseMethodView();
      methodArray[0][i].setTerm(term);
      
      // If a relationship already exists, use it instead
      for (AggregatedPremiseMethodView existing : existingMethods)
      {
        // Use IDs to avoid cost of instantiating the whole object
        if (existing.getValue(AggregatedPremiseMethodView.TERM).equals(term.getId()))
        {
          methodArray[0][i] = existing;
          break;
        }
      }

      // Set the amount
      methodArray[0][i].setAmount(interventionMethodAmount.get(i));
    }
    
    AggregatedPremiseReasonView[] existingReasons = apv.getNonTreatmentReasons();
    AggregatedPremiseReasonView[][] reasonArray = new AggregatedPremiseReasonView[1][nonTreatmentReason.size()];
    for (int i = 0; i < nonTreatmentReason.size(); i++)
    {
      // Default to a new relationship
      Term term = nonTreatmentReason.get(i);
      reasonArray[0][i] = new AggregatedPremiseReasonView();
      reasonArray[0][i].setTerm(term);
      
      // If a relationship already exists, use it instead
      for (AggregatedPremiseReasonView existing : existingReasons)
      {
        // Use IDs to avoid cost of instantiating the whole object
        if (existing.getValue(AggregatedPremiseReasonView.TERM).equals(term.getId()))
        {
          reasonArray[0][i] = existing;
          break;
        }
      }

      // Set the amount
      reasonArray[0][i].setAmount(nonTreatmentReasonAmount.get(i));
    }
    
    controlPoint.applyWithAggregatedPremiseViews(new AggregatedPremiseVisitView[] {apv}, reasonArray, methodArray);
  }

  public static LinkedList<String> customAttributeOrder()
  {
    LinkedList<String> list = ControlInterventionExcelView.customAttributeOrder();
    list.add(PREMISESAVAILABLE);
    list.add(PREMISESINCLUDED);
    list.add(PREMISES);
    list.add(VISITED);
    list.add(TREATED);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    ControlInterventionExcelView.setupExportListener(exporter, params);
    exporter.addListener(createExcelGeoListener(null));
    exporter.addListener(new AggregatedPremiseListener());
  }
  
  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    ControlInterventionExcelView.setupImportListener(context, params, importer);
    context.addListener(createExcelGeoListener(importer));
    context.addListener(new AggregatedPremiseListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener(ExcelImportManager importer)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllUrban())
    {
      builder.add(hierarchy);
    }
    return new DynamicGeoColumnListener(CLASS, PREMISEGEOENTITY, builder, importer);
  }

  public void addInterventionMethod(Term term, Integer amount)
  {
    interventionMethod.add(term);
    interventionMethodAmount.add(amount);
  }

  public void addNonTreatmentReason(Term term, Integer amount)
  {
    nonTreatmentReason.add(term);
    nonTreatmentReasonAmount.add(amount);
  }
  
}
