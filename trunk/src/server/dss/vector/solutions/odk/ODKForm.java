package dss.vector.solutions.odk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.constants.ElementInfo;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributePrimitiveDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdAttributeStructDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.PersonView;
import dss.vector.solutions.entomology.ImmatureCollectionView;
import dss.vector.solutions.entomology.MosquitoCollectionView;
import dss.vector.solutions.entomology.PupalCollectionView;
import dss.vector.solutions.entomology.SubCollectionView;
import dss.vector.solutions.entomology.assay.EfficacyAssayView;
import dss.vector.solutions.export.AggregatedCaseExcelView;
import dss.vector.solutions.export.AggregatedCaseReferralsExcelView;
import dss.vector.solutions.export.AggregatedCaseTreatmentsExcelView;
import dss.vector.solutions.export.AggregatedIPTExcelView;
import dss.vector.solutions.export.AggregatedITNExcelView;
import dss.vector.solutions.export.AggregatedPremiseExcelView;
import dss.vector.solutions.export.CaseDiagnosisTypeExcelView;
import dss.vector.solutions.export.CaseDiseaseManifestationExcelView;
import dss.vector.solutions.export.CasePatientTypeExcelView;
import dss.vector.solutions.export.ControlInterventionExcelView;
import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.export.EfficacyAssayExcelView;
import dss.vector.solutions.export.ITNCommunityExcelView;
import dss.vector.solutions.export.ITNDistributionExcelView;
import dss.vector.solutions.export.ImmatureCollectionExcelView;
import dss.vector.solutions.export.IndividualCaseExcelView;
import dss.vector.solutions.export.IndividualIPTExcelView;
import dss.vector.solutions.export.IndividualPremiseExcelView;
import dss.vector.solutions.export.InsecticideInterventionExcelView;
import dss.vector.solutions.export.MosquitoCollectionExcelView;
import dss.vector.solutions.export.OperatorSprayExcelView;
import dss.vector.solutions.export.PersonExcelView;
import dss.vector.solutions.export.PersonInterventionExcelView;
import dss.vector.solutions.export.PopulationDataExcelView;
import dss.vector.solutions.export.PupalCollectionExcelView;
import dss.vector.solutions.export.SurveyExcelView;
import dss.vector.solutions.export.TeamSprayExcelView;
import dss.vector.solutions.export.ThresholdDataExcelView;
import dss.vector.solutions.export.ZoneSprayExcelView;
import dss.vector.solutions.general.PopulationDataView;
import dss.vector.solutions.general.ThresholdDataView;
import dss.vector.solutions.geo.GeoFilterCriteria;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.intervention.monitor.AggregatedIPTView;
import dss.vector.solutions.intervention.monitor.ITNCommunityDistributionView;
import dss.vector.solutions.intervention.monitor.ITNDataView;
import dss.vector.solutions.intervention.monitor.ITNDistributionView;
import dss.vector.solutions.intervention.monitor.IndividualCaseView;
import dss.vector.solutions.intervention.monitor.IndividualIPTView;
import dss.vector.solutions.intervention.monitor.PersonInterventionView;
import dss.vector.solutions.irs.OperatorSprayView;
import dss.vector.solutions.irs.TeamSprayView;
import dss.vector.solutions.irs.ZoneSprayView;
import dss.vector.solutions.ontology.Term;

public class ODKForm implements Reloadable
{
  protected MdClassDAOIF        viewMd;

  protected GeoFilterCriteria   gfc;

  protected LinkedList<ODKAttribute> attrs;
  
  protected LinkedList<ODKFormJoin> joins;
  
  public ODKForm(MdClassDAOIF base)
  {
    this(base, null, null);
  }
  
  public ODKForm(String viewMdType, GeoFilterCriteria gfc)
  {
    this(MdClassDAO.getMdClassDAO(viewMdType), null, gfc);
  }
  
  public ODKForm(String viewMdType)
  {
    this(MdClassDAO.getMdClassDAO(viewMdType), null, null);
  }

  public ODKForm(MdClassDAOIF base, GeoFilterCriteria gfc)
  {
    this(base, null, gfc);
  }

  public ODKForm(MdClassDAOIF base, MdClassDAOIF target, GeoFilterCriteria gfc)
  {
    this.viewMd = base;
    this.gfc = gfc;
    this.joins = new LinkedList<ODKFormJoin>();
  }
  
  public void setGeoFilterCriteria(GeoFilterCriteria geoFilters)
  {
    this.gfc = geoFilters;
  }

  public GeoFilterCriteria getGeoFilterCriteria()
  {
    return this.gfc;
  }

  public MdClassDAOIF getViewMd()
  {
    return viewMd;
  }

  public void setBase(MdClassDAOIF base)
  {
    this.viewMd = base;
  }
  
  public static String generateFormName(String seed)
  {
    seed = seed.replaceAll("\\.", "_");
    
    if (seed.endsWith("ExcelView"))
    {
      seed.substring(0, seed.length() - "ExcelView".length());
    }
    
    return seed;
  }

  public String getFormName()
  {
    return ODKForm.generateFormName(this.getViewMd().definesType());
  }

  public void writeTranslation(Element parent, Document document, String context, int maxDepth)
  {
    for (ODKAttribute attr : this.attrs)
    {
      attr.writeTranslation(parent, document, context, maxDepth);
    }
    
    for (ODKFormJoin join : joins)
    {
      join.writeTranslation(parent, document, context, maxDepth);
    }
  }

  public void writeBind(Element parent, Document document, String context, int maxDepth)
  {
    for (ODKAttribute attr : this.attrs)
    {
      attr.writeBind(parent, document, context, maxDepth);
    }
    
    for (ODKFormJoin join : joins)
    {
      join.writeBind(parent, document, context, maxDepth);
    }
  }

  public void writeBody(Element parent, Document document, String context, int maxDepth)
  {
    for (ODKAttribute attr : this.attrs)
    {
      attr.writeBody(parent, document, context, maxDepth);
    }

    for (ODKFormJoin join : joins)
    {
      join.writeBody(parent, document, context, maxDepth);
    }
  }
  
  public void writeInstance(Element parent, Document document, String context, int maxDepth)
  {
    for (ODKAttribute attr : this.attrs)
    {
      attr.writeInstance(parent, document, context, maxDepth);
    }
    
    for (ODKFormJoin join : joins)
    {
      join.writeInstance(parent, document, context, maxDepth);
    }
  }
  
  public void join(ODKFormJoin join)
  {
    this.joins.add(join);
  }
  
  /**
   * Builds the form's attributes from the attributes on the source type. Orders the attributes based on the orderList.
   * 
   * @param sourceType
   * @param orderList
   */
  public void buildAttributes(String sourceType, LinkedList<String> orderList, ODKAttributeMapper mapper)
  {
    this.attrs = new LinkedList<ODKAttribute>();
    
    MdClassDAOIF sourceMdc = MdClassDAO.getMdClassDAO(sourceType);
    
    if (mapper == null)
    {
      mapper = new DefaultODKAttributeMapper();
    }
    
    /*
     * Global Map of all the exported term ids. This is used to prevent the same
     * term from being translated multiple times even if the term is an item of
     * multiple different attributes.
     */
    Set<String> exportedTerms = new TreeSet<String>();
    
    List<? extends MdAttributeDAOIF> mdAttributeDAOs = sourceMdc.getAllDefinedMdAttributes();
    
    for (MdAttributeDAOIF sourceAttr : mdAttributeDAOs)
    {
      MdAttributeDAOIF viewAttr = mapper.map(sourceAttr, this.viewMd);
      
      if (viewAttr == null) { continue; }

      attrs.add(ODKAttribute.factory(sourceAttr, viewAttr, exportedTerms));
    }
    
    Comparator<ODKAttribute> sorter = new Comparator<ODKAttribute>(){
      @Override
      public int compare(ODKAttribute one,ODKAttribute two)
      {
        int oneI = orderList.indexOf(one.getAttributeName());
        int twoI = orderList.indexOf(two.getAttributeName());
        
        if (oneI < twoI) { return -1; }
        else if (oneI == twoI) { return 0; }
        else { return 1; }
      }
    };
    
    this.attrs.sort(sorter);
  }
  
  public static class DefaultODKAttributeMapper implements ODKAttributeMapper
  {
    public MdAttributeDAOIF map(MdAttributeDAOIF source, MdClassDAOIF model)
    {
      MdAttributeConcreteDAOIF concrete = source.getMdAttributeConcrete();
      
      if (concrete instanceof MdAttributeReferenceDAOIF)
      {
        MdBusinessDAOIF referenceMdBusiness = ( (MdAttributeReferenceDAOIF) concrete ).getReferenceMdBusinessDAO();

        String type = referenceMdBusiness.definesType();

        return type.equals(Term.CLASS) || type.equals(GeoEntity.CLASS) ? model.definesAttribute(source.definesAttribute()) : null;
      }

      if (concrete.isSystem() || 
          concrete.definesAttribute().equals(ElementInfo.DOMAIN) || 
          concrete.definesAttribute().equals(ElementInfo.OWNER) || 
          concrete.definesAttribute().equals(ElementInfo.KEY) ||          
          !( concrete instanceof MdAttributePrimitiveDAOIF ||           
              concrete instanceof MdAttributeStructDAOIF))
      {
        return null;
      }
      else
      {
        return model.definesAttribute(source.definesAttribute());
      }
    }
  }
  
  public static interface ODKAttributeMapper
  {
    /**
     * Maps the provided source mdAttribute to a MdAttribute on the model. Return null to exclude the attribute.
     * 
     * @param mdAttribute
     * @return
     */
    public MdAttributeDAOIF map(MdAttributeDAOIF mdAttribute, MdClassDAOIF model);
  }

  public boolean hasViewAttribute(String attributeName)
  {
    for (ODKAttribute attr : attrs)
    {
      if (attr.getAttributeName().equals(attributeName))
      {
        return true;
      }
    }
    
    return false;
  }
  
  public boolean isGeoAttribute(String attributeName)
  {
    return attributeName.contains("_geolist_");
  }

  public boolean isRepeatable(String attributeName)
  {
    return ( this.getRepeatable(attributeName) != null );
  }
  
  public ODKForm getRepeatable(String attributeName)
  {
    if (attributeName.contains("_"))
    {
      String type = attributeName.replaceAll("_", ".");

      for (ODKFormJoin join : joins)
      {
        if (join.getChild().getViewMd().definesType().equals(type))
        {
          return join.getChild();
        }
      }
    }

    return null;
  }
  
  public static ODKForm factory(java.lang.String mobileType)
  {
    ODKForm master = null;
    GeoFilterCriteria gfc = getGeoCriteriaFromListeners(mobileType);
    
    if (mobileType.equals(AggregatedCaseExcelView.CLASS))
    {
      master = new ODKForm(AggregatedCaseExcelView.CLASS, gfc);
      
      ODKForm aggCaseRefer = new ODKForm(AggregatedCaseReferralsExcelView.CLASS);
      aggCaseRefer.buildAttributes(AggregatedCaseReferralsExcelView.CLASS, AggregatedCaseExcelView.customAttributeOrder(), null);
      master.join(new MergeFormJoin(master, aggCaseRefer));
      
      ODKForm caseDiag = new ODKForm(CaseDiagnosisTypeExcelView.CLASS);
      caseDiag.buildAttributes(CaseDiagnosisTypeExcelView.CLASS, AggregatedCaseExcelView.customAttributeOrder(), null);
      master.join(new MergeFormJoin(master, caseDiag));
      
      ODKForm caseDisease = new ODKForm(CaseDiseaseManifestationExcelView.CLASS);
      caseDisease.buildAttributes(CaseDiseaseManifestationExcelView.CLASS, AggregatedCaseExcelView.customAttributeOrder(), null);
      master.join(new MergeFormJoin(master, caseDisease));
      
      ODKForm casePatient = new ODKForm(CasePatientTypeExcelView.CLASS);
      casePatient.buildAttributes(CasePatientTypeExcelView.CLASS, AggregatedCaseExcelView.customAttributeOrder(), null);
      master.join(new MergeFormJoin(master, casePatient));
    }
    else if (mobileType.equals(ControlInterventionExcelView.CLASS))
    {
//      odkExp.addForm(new ODKForm(AggregatedPremiseExcelView.CLASS, gfc));
//      odkExp.addForm(new ODKForm(IndividualPremiseExcelView.CLASS, gfc));
//      odkExp.addForm(new ODKForm(InsecticideInterventionExcelView.CLASS, gfc));
//      odkExp.addForm(new ODKForm(PersonInterventionExcelView.CLASS, gfc));
    }
    // TODO : Merg form, form gen types
    else if (mobileType.equals(MosquitoCollectionExcelView.CLASS))
    {
      master = new ODKForm(MosquitoCollectionExcelView.CLASS, gfc);
      master.buildAttributes(MosquitoCollectionView.CLASS, MosquitoCollectionExcelView.customAttributeOrder(), new ODKForm.DefaultODKAttributeMapper(){
          public MdAttributeDAOIF map(MdAttributeDAOIF mdAttribute, MdClassDAOIF model) {
            return mdAttribute.definesAttribute().equals(MosquitoCollectionView.CONCRETEID) ? null : super.map(mdAttribute, model);
          }
        }
      );
      
      ODKForm subc = new ODKForm(SubCollectionView.CLASS);
      subc.buildAttributes(SubCollectionView.CLASS, MosquitoCollectionExcelView.customAttributeOrder(), null);
      
      master.join(new RepeatFormJoin(master, subc));
    }
//    else if (mobileType.equals(AggregatedIPTExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(AggregatedIPTView.CLASS, gfc));
//    }
//    else if (mobileType.equals(AggregatedITNExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(ITNDataView.CLASS, gfc));
//    }
//    else if (mobileType.equals(EfficacyAssayExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(EfficacyAssayView.CLASS, gfc));
//    }
//    else if (mobileType.equals(IndividualCaseExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(IndividualCaseView.CLASS, gfc));
//    }
//    else if (mobileType.equals(IndividualIPTExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(IndividualIPTView.CLASS, gfc));
//    }
//    else if (mobileType.equals(ITNCommunityExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(ITNCommunityDistributionView.CLASS, gfc));
//    }
//    else if (mobileType.equals(ITNDistributionExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(ITNDistributionView.CLASS, gfc));
//    }
////    else if (mobileType.equals(LarvacideExcelView.CLASS))
////    {
////      odkExp.addForm(new ODKForm(LarvacideView.CLASS, gfc));
////    }
//    else if (mobileType.equals(OperatorSprayExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(OperatorSprayView.CLASS, gfc));
//    }
//    else if (mobileType.equals(TeamSprayExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(TeamSprayView.CLASS, gfc));
//    }
//    else if (mobileType.equals(ZoneSprayExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(ZoneSprayView.CLASS, gfc));
//    }
//    else if (mobileType.equals(PersonExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(PersonView.CLASS, gfc));
//    }
//    else if (mobileType.equals(PersonInterventionExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(PersonInterventionView.CLASS, gfc));
//    }
//    else if (mobileType.equals(PopulationDataExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(PopulationDataView.CLASS, gfc));
//    }
//    else if (mobileType.equals(PupalCollectionExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(PupalCollectionView.CLASS, gfc));
//    }
//    else if (mobileType.equals(SurveyExcelView.CLASS))
//    {
//      // TODO
////      odkExp.addForm(new ODKForm(Survey.CLASS, gfc));
//    }
//    else if (mobileType.equals(ThresholdDataExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(ThresholdDataView.CLASS, gfc));
//    }
//    else if (mobileType.equals(ImmatureCollectionExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(ImmatureCollectionView.CLASS, gfc));
//    }
//    else
//    {
//      odkExp.addForm(new ODKForm(mobileType, gfc));
//    }
    
    return master;
  }
  
  private static GeoFilterCriteria getGeoCriteriaFromListeners(String clazz)
  {
    ExcelExporter listenerCollector;
    
    try
    {
      listenerCollector = new ExcelExporter();
      
      // Load the type which is being exported
      Class<?> c = LoaderDecorator.load(clazz);
      
      // Get the listener method
      Method method = c.getMethod("setupExportListener", ExcelExporter.class, String[].class);
      
      // Invoke the method and get the ExcelExportListener
      method.invoke(null, listenerCollector, new String[]{});
    }
    catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e)
    {
      throw new ProgrammingErrorException(e);
    }
    
    List<ExcelExportListener> listeners = listenerCollector.getListeners();
    List<GeoHierarchy> ghl = new ArrayList<GeoHierarchy>();
    
    for (ExcelExportListener listener : listeners)
    {
      if (listener instanceof DynamicGeoColumnListener)
      {
         List<GeoHierarchy> list = ((DynamicGeoColumnListener)listener).getHierarchyList();
         ghl.addAll(list);
      }
    }
    
    return new GeoFilterCriteria(ghl);
  }
}
