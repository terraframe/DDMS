package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.intervention.monitor.ControlInterventionView;
import dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView;
import dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class IndividualPremiseExcelView extends IndividualPremiseExcelViewBase implements Reloadable
{
  private static final long serialVersionUID = 914851654;

  private List<Term>        interventionMethod;

  private List<Boolean>     interventionMethodUsed;
  
  public IndividualPremiseExcelView()
  {
    super();
    interventionMethod = new LinkedList<Term>();
    interventionMethodUsed = new LinkedList<Boolean>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    ControlInterventionView controlPoint = this.getControlPoint();
    IndividualPremiseVisitView ipv = new IndividualPremiseVisitView();
    ipv.setGeoEntity(this.getPremiseGeoEntity());
    ipv.setVisited(this.getVisited());
    ipv.setTreated(this.getTreated());
    ipv.setReasonsForNotTreated(Term.validateByDisplayLabel(this.getReasonsForNotTreated(), IndividualPremiseVisitView.getReasonsForNotTreatedMd()));
    
    IndividualPremiseVisitMethodView[][] methodArray = new IndividualPremiseVisitMethodView[1][interventionMethod.size()];
    for (int i = 0; i < interventionMethod.size(); i++)
    {
      methodArray[0][i] = new IndividualPremiseVisitMethodView();
      methodArray[0][i].setTerm(interventionMethod.get(i));
      methodArray[0][i].setUsed(interventionMethodUsed.get(i));
    }
    
    controlPoint.applyWithIndividualPremiseViews(new IndividualPremiseVisitView[] {ipv}, methodArray);
  }

  public static List<String> customAttributeOrder()
  {
    List<String> list = ControlInterventionExcelView.customAttributeOrder();
    list.add(VISITED);
    list.add(TREATED);
    list.add(REASONSFORNOTTREATED);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    ControlInterventionExcelView.setupExportListener(exporter, params);
    exporter.addListener(createExcelGeoListener());
    exporter.addListener(new IndividualPremiseListener());
  }
  
  public static void setupImportListener(ImportContext context, String... params)
  {
    ControlInterventionExcelView.setupImportListener(context, params);
    context.addListener(createExcelGeoListener());
    context.addListener(new IndividualPremiseListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllUrban())
    {
      builder.add(hierarchy);
    }
    return new DynamicGeoColumnListener(CLASS, PREMISEGEOENTITY, builder);
  }

  public void addInterventionMethod(Term term, Boolean used)
  {
    interventionMethod.add(term);
    interventionMethodUsed.add(used);
  }
  
}
