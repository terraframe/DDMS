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
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.intervention.monitor.ITNData;
import dss.vector.solutions.intervention.monitor.ITNDataView;
import dss.vector.solutions.intervention.monitor.ITNNet;
import dss.vector.solutions.intervention.monitor.ITNService;
import dss.vector.solutions.intervention.monitor.ITNTargetGroup;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.PeriodType;
import dss.vector.solutions.util.HierarchyBuilder;

public class AggregatedITNExcelView extends AggregatedITNExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1914412224;
  
  private List<Term>        services;
  private List<Integer>     serviceAmounts;
  private List<Term>        targetGroups;
  private List<Integer>     targetGroupAmounts;
  private List<Term>        itnTypes;
  private List<Integer>     itnTypeAmounts;
  
  public AggregatedITNExcelView()
  {
    super();
    services = new LinkedList<Term>();
    serviceAmounts = new LinkedList<Integer>();
    targetGroups = new LinkedList<Term>();
    targetGroupAmounts = new LinkedList<Integer>();
    itnTypes = new LinkedList<Term>();
    itnTypeAmounts = new LinkedList<Integer>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    GeoEntity geoEntity = getGeoEntity();

    PeriodType periodType = ExcelEnums.getPeriodType(this.getPeriodType());
    
    ITNDataView data = ITNData.searchByGeoEntityAndEpiDate(geoEntity, periodType, this.getPeriod(), this.getPeriodYear());

    // Lock the instance if it exists
    if(data.hasConcrete())
    {
      data = ITNData.lockView(data.getConcreteId());
    }
    
    data.setBatchNumber(this.getBatchNumber());
    data.setReceivedForTargetGroups(this.getReceivedForTargetGroups());
    data.setReceivedForCommunityResponse(this.getReceivedForCommunityResponse());
    data.setNumberDistributed(this.getNumberDistributed());
    data.setNumberSold(this.getNumberSold());
    data.setCurrencyReceived(this.getCurrencyReceived());
    
    ITNService[] existingServices = data.getITNServices();
    ITNService[] serviceArray = new ITNService[services.size()];
    for (int i = 0; i < serviceArray.length; i++)
    {
      // Default to a new record
      String termId = services.get(i).getId();
      serviceArray[i] = new ITNService(data.getConcreteId(), termId);

      // If a record already exists, use it instead
      for (ITNService existing : existingServices)
      {
        // Use IDs to avoid cost of instantiating the whole object
        if (existing.getChildId().equals(termId))
        {
          if (!existing.isNew())
          {
            existing.lock();
          }
          serviceArray[i] = existing;
        }
      }
      
      // Set the amount
      if (i < serviceAmounts.size())
      {
        serviceArray[i].setAmount((serviceAmounts.get(i)));
      }
    }
    
    ITNTargetGroup[] existingGroups = data.getITNTargetGroups();
    ITNTargetGroup[] targetGroupArray = new ITNTargetGroup[targetGroups.size()];
    for (int i = 0; i < targetGroupArray.length; i++)
    {
      // Default to a new record
      String termId = targetGroups.get(i).getId();
      targetGroupArray[i] = new ITNTargetGroup(data.getConcreteId(), termId);
      
      // If a record already exists, use it instead
      for (ITNTargetGroup existing : existingGroups)
      {
        // Use IDs to avoid cost of instantiating the whole object
        if (existing.getChildId().equals(termId))
        {
          if (!existing.isNew())
          {
            existing.lock();
          }
          targetGroupArray[i] = existing;
        }
      }
      
      // Set the amount
      if (i < targetGroupAmounts.size())
      {
        targetGroupArray[i].setAmount((targetGroupAmounts.get(i)));
      }
    }
    
    ITNNet[] existingNets = data.getITNNets();
    ITNNet[] itnTypeArray = new ITNNet[itnTypes.size()];
    for (int i = 0; i < itnTypeArray.length; i++)
    {
      // Default to a new record
      String termId = itnTypes.get(i).getId();
      itnTypeArray[i] = new ITNNet(data.getConcreteId(), termId);
      
      // If a record already exists, use it instead
      for (ITNNet existing : existingNets)
      {
        // Use IDs to avoid cost of instantiating the whole object
        if (existing.getChildId().equals(termId))
        {
          if (!existing.isNew())
          {
            existing.lock();
          }
          itnTypeArray[i] = existing;
        }
      }
      
      // Set the amount
      if (i < itnTypeAmounts.size())
      {
        itnTypeArray[i].setAmount((itnTypeAmounts.get(i)));
      }
    }
    
    data.applyAll(itnTypeArray, targetGroupArray, serviceArray);
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(PERIODTYPE);
    list.add(PERIOD);
    list.add(PERIODYEAR);
    list.add(BATCHNUMBER);
    list.add(RECEIVEDFORTARGETGROUPS);
    list.add(RECEIVEDFORCOMMUNITYRESPONSE);
    list.add(NUMBERDISTRIBUTED);
    list.add(NUMBERSOLD);
    list.add(CURRENCYRECEIVED);
    return list;
  }

  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    context.addListener(new AggregatedITNListener());
    context.addListener(createExcelGeoListener(importer));
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener(null));
    exporter.addListener(new AggregatedITNListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener(ExcelImportManager importer)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllPoliticals())
    {
      builder.add(hierarchy);
    }
    builder.add(GeoHierarchy.getGeoHierarchyFromType(HealthFacility.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder, importer);
  }
  
  public void addService(Term grid, Integer amount)
  {
    services.add(grid);
    serviceAmounts.add(amount);
  }

  public void addTargetGroup(Term grid, Integer amount)
  {
    targetGroups.add(grid);
    targetGroupAmounts.add(amount);
  }

  public void addITNType(Term grid, Integer amount)
  {
    itnTypes.add(grid);
    itnTypeAmounts.add(amount);
  }
}
