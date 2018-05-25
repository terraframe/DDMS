package dss.vector.solutions.odk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.constants.ElementInfo;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF;
import com.runwaysdk.dataaccess.MdAttributePrimitiveDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdAttributeStructDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.PersonView;
import dss.vector.solutions.entomology.CollectionContainerView;
import dss.vector.solutions.entomology.ImmatureCollectionView;
import dss.vector.solutions.entomology.MosquitoCollectionView;
import dss.vector.solutions.entomology.PupalCollectionView;
import dss.vector.solutions.entomology.PupalContainerView;
import dss.vector.solutions.entomology.SubCollectionView;
import dss.vector.solutions.entomology.assay.EfficacyAssay;
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
import dss.vector.solutions.export.LarvacideExcelView;
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
import dss.vector.solutions.general.PopulationData;
import dss.vector.solutions.general.ThresholdDataView;
import dss.vector.solutions.geo.GeoFilterCriteria;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.intervention.monitor.ControlInterventionView;
import dss.vector.solutions.intervention.monitor.HouseholdView;
import dss.vector.solutions.intervention.monitor.ITNCommunityDistributionView;
import dss.vector.solutions.intervention.monitor.ITNDistributionView;
import dss.vector.solutions.intervention.monitor.ITNInstanceView;
import dss.vector.solutions.intervention.monitor.IndividualCase;
import dss.vector.solutions.intervention.monitor.IndividualIPTView;
import dss.vector.solutions.intervention.monitor.IndividualInstance;
import dss.vector.solutions.intervention.monitor.Larvacide;
import dss.vector.solutions.intervention.monitor.SurveyedPersonView;
import dss.vector.solutions.irs.HouseholdSprayStatusView;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.OperatorSprayStatusView;
import dss.vector.solutions.irs.OperatorSprayView;
import dss.vector.solutions.irs.TeamSprayStatusView;
import dss.vector.solutions.irs.TeamSprayView;
import dss.vector.solutions.irs.ZoneSprayView;
import dss.vector.solutions.mobile.MobileUtil;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.AggregatedCaseView;
import dss.vector.solutions.surveillance.CaseDiagnosisTypeView;

public class ODKForm implements Reloadable
{
  public static final Logger logger = LoggerFactory.getLogger(ODKForm.class);
  
//  public static class ViewMapper extends DefaultODKAttributeMapper implements Reloadable
//  {
//    public MdAttributeDAOIF getViewAttr(MdAttributeDAOIF mdAttribute, MdClassDAOIF sourceMdc, MdClassDAOIF viewMdc)
//    {
//      return mdAttribute.definesAttribute().equals(MosquitoCollectionView.CONCRETEID) ? null : super.getViewAttr(mdAttribute, sourceMdc, viewMdc);
//    }
//  }

  /*
   * Global Map of all the exported term ids. This is used to prevent the same
   * term from being translated multiple times even if the term is an item of
   * multiple different attributes.
   */
  Set<String> exportedTerms = new TreeSet<String>();
  
  public static class AttributeComparator implements Comparator<ODKAttribute>, Reloadable
  {
    private List<String> orderList;

    public AttributeComparator(List<String> orderList)
    {
      this.orderList = orderList;
    }

    @Override
    public int compare(ODKAttribute one, ODKAttribute two)
    {
      int oneI = orderList.indexOf(one.getAttributeName());
      int twoI = orderList.indexOf(two.getAttributeName());

      if (oneI < twoI)
      {
        return -1;
      }
      else if (oneI == twoI)
      {
        return 0;
      }
      else
      {
        return 1;
      }
    }
  };

  protected MdClassDAOIF             viewMd;

  protected GeoFilterCriteria        gfc;

  protected LinkedList<ODKAttribute> attrs;

  protected LinkedList<ODKFormJoin>  joins;

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
    this.attrs = new LinkedList<ODKAttribute>();
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
  
  public void validate()
  {
    if (this.attrs.isEmpty())
    {
      logger.error("Form [" + this.getFormName() + "] has no attributes!");
    }
    
    for (ODKFormJoin join : joins)
    {
      join.getChild().validate();
    }
  }

  public String getFormName()
  {
    return MobileUtil.convertToOdkId(this.getViewMd().definesType());
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

  public void buildAttributes(String sourceType, List<String> orderList, ODKAttributeMapper mapper)
  {
    this.buildAttributes(sourceType, this.viewMd.definesType(), orderList, mapper);
  }
  
  /**
   * Builds the form's attributes from the attributes on the source type. Orders
   * the attributes based on the orderList.
   * 
   * @param sourceType
   * @param orderList
   */
  public void buildAttributes(String sourceType, String viewType, List<String> orderList, ODKAttributeMapper mapper)
  {
    MdClassDAOIF sourceMdc = MdClassDAO.getMdClassDAO(sourceType);
    MdClassDAOIF viewMdc = MdClassDAO.getMdClassDAO(viewType);

    if (mapper == null)
    {
      mapper = new DefaultODKAttributeMapper();
    }

    List<? extends MdAttributeDAOIF> mdAttributeDAOs = sourceMdc.getAllDefinedMdAttributes();

    for (MdAttributeDAOIF sourceAttr : mdAttributeDAOs)
    {
      MdAttributeDAOIF viewAttr = mapper.getViewAttr(sourceAttr, sourceMdc, viewMdc);

      if (viewAttr == null)
      {
        continue;
      }

      attrs.add(ODKAttribute.factory(sourceAttr, viewAttr, exportedTerms));
    }

    this.attrs.sort(new AttributeComparator(orderList));
  }
  
  public void buildAttributes(Map<String,String> attrMap, List<String> orderList)
  {
    for (String sSourceAttr : attrMap.keySet())
    {
      MdAttributeDAOIF sourceAttr = MdAttributeDAO.getByKey(sSourceAttr);
      MdAttributeDAOIF viewAttr = MdAttributeDAO.getByKey(attrMap.get(sSourceAttr));
      
      attrs.add(ODKAttribute.factory(sourceAttr, viewAttr, exportedTerms));
    }
    
    this.attrs.sort(new AttributeComparator(orderList));
  }

  public static class DefaultODKAttributeMapper implements ODKAttributeMapper
  {
    public MdAttributeDAOIF getViewAttr(MdAttributeDAOIF sourceAttr, MdClassDAOIF sourceMdc, MdClassDAOIF viewMdc)
    {
      MdAttributeConcreteDAOIF concrete = sourceAttr.getMdAttributeConcrete();

      if (viewMdc.definesAttribute(sourceAttr.definesAttribute()) == null)
      {
        return null;
      }
      
      if (concrete instanceof MdAttributeReferenceDAOIF)
      {
        MdBusinessDAOIF referenceMdBusiness = ( (MdAttributeReferenceDAOIF) concrete ).getReferenceMdBusinessDAO();

        String type = referenceMdBusiness.definesType();

        return type.equals(Term.CLASS) || type.equals(GeoEntity.CLASS) ? viewMdc.definesAttribute(sourceAttr.definesAttribute()) : null;
      }

      if (concrete.isSystem() || concrete.definesAttribute().equals(ElementInfo.DOMAIN) || concrete.definesAttribute().equals(ElementInfo.OWNER) || concrete.definesAttribute().equals(ElementInfo.KEY) || ! ( concrete instanceof MdAttributePrimitiveDAOIF || concrete instanceof MdAttributeEnumerationDAOIF || concrete instanceof MdAttributeStructDAOIF ))
      {
        return null;
      }
      else
      {
        return viewMdc.definesAttribute(sourceAttr.definesAttribute());
      }
    }
  }
  
  public static class MapODKAttributeMapper extends DefaultODKAttributeMapper
  {
    private Map<String, String> map;
    
    public MapODKAttributeMapper(Map<String, String> map)
    {
      this.map = map;
    }

    @Override
    public MdAttributeDAOIF getViewAttr(MdAttributeDAOIF sourceAttr, MdClassDAOIF sourceMdc, MdClassDAOIF viewMdc)
    {
      String sViewAttr = this.map.get(sourceAttr.definesAttribute());
      
      if (sViewAttr != null && viewMdc.definesAttribute(sViewAttr) != null)
      {
        return viewMdc.definesAttribute(sViewAttr);
      }
        
      return super.getViewAttr(sourceAttr, sourceMdc, viewMdc);
    }
  }

  public static interface ODKAttributeMapper
  {
    /**
     * Maps the provided source mdAttribute to a MdAttribute on the model.
     * Return null to exclude the attribute.
     * 
     * @param mdAttribute
     * @return
     */
    public MdAttributeDAOIF getViewAttr(MdAttributeDAOIF sourceAttr, MdClassDAOIF sourceMdc, MdClassDAOIF viewMdc);
  }

  public boolean hasViewAttribute(String attributeName)
  {
    return ( this.viewMd.definesAttribute(attributeName) != null );
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
    // TODO : Form generator
    
    ODKForm master = null;
    GeoFilterCriteria gfc = getGeoCriteriaFromListeners(mobileType);

    if (mobileType.equals(AggregatedCaseExcelView.CLASS))
    {
      master = new ODKForm(AggregatedCaseExcelView.CLASS, gfc);

      Map<String,String> sharedAttrs = new HashMap<String, String>();
      sharedAttrs.put(AggregatedCaseExcelView.CLASS + "." + AggregatedCaseExcelView.STARTDATE, AggregatedCaseExcelView.CLASS + "." + AggregatedCaseExcelView.STARTDATE);
      sharedAttrs.put(AggregatedCaseExcelView.CLASS + "." + AggregatedCaseExcelView.ENDDATE, AggregatedCaseExcelView.CLASS + "." + AggregatedCaseExcelView.ENDDATE);
      sharedAttrs.put(AggregatedCaseExcelView.CLASS + "." + AggregatedCaseExcelView.DISPLAYLABEL, AggregatedCaseExcelView.CLASS + "." + AggregatedCaseExcelView.DISPLAYLABEL);
      sharedAttrs.put(AggregatedCaseExcelView.CLASS + "." + AggregatedCaseExcelView.GEOENTITY, AggregatedCaseExcelView.CLASS + "." + AggregatedCaseExcelView.GEOENTITY);
      
      ODKForm aggCaseRefer = new ODKForm(AggregatedCaseReferralsExcelView.CLASS);
      Map<String,String> aggCaseAttrs = new HashMap<String, String>(sharedAttrs);
      aggCaseAttrs.put(AggregatedCaseReferralsExcelView.CLASS + "." + AggregatedCaseReferralsExcelView.CASES, AggregatedCaseReferralsExcelView.CLASS + "." + AggregatedCaseReferralsExcelView.CASES);
      aggCaseAttrs.put(AggregatedCaseReferralsExcelView.CLASS + "." + AggregatedCaseReferralsExcelView.POSITIVECASES, AggregatedCaseReferralsExcelView.CLASS + "." + AggregatedCaseReferralsExcelView.POSITIVECASES);
      aggCaseAttrs.put(AggregatedCaseReferralsExcelView.CLASS + "." + AggregatedCaseReferralsExcelView.NEGATIVECASES, AggregatedCaseReferralsExcelView.CLASS + "." + AggregatedCaseReferralsExcelView.NEGATIVECASES);
      aggCaseAttrs.put(AggregatedCaseReferralsExcelView.CLASS + "." + AggregatedCaseReferralsExcelView.DEATHS, AggregatedCaseReferralsExcelView.CLASS + "." + AggregatedCaseReferralsExcelView.DEATHS);
      aggCaseRefer.buildAttributes(aggCaseAttrs, AggregatedCaseReferralsExcelView.customAttributeOrder());
      master.join(new RepeatFormJoin(master, aggCaseRefer));
      
      ODKForm caseTreats = new ODKForm(AggregatedCaseTreatmentsExcelView.CLASS);
      caseTreats.buildAttributes(AggregatedCaseView.CLASS, AggregatedCaseExcelView.CLASS, AggregatedCaseExcelView.customAttributeOrder(), null);
      caseTreats.buildAttributes(CaseDiagnosisTypeView.CLASS, CaseDiagnosisTypeExcelView.customAttributeOrder(), null);
      master.join(new RepeatFormJoin(master, caseTreats));
      
      ODKForm caseDiag = new ODKForm(CaseDiagnosisTypeExcelView.CLASS);
      Map<String,String> caseDiagAttrMappings = new HashMap<String, String>();
      caseDiagAttrMappings.put(AggregatedCaseView.CASEDIAGNOSISTYPE, CaseDiagnosisTypeExcelView.DIAGNOSISTYPE);
      caseDiag.buildAttributes(AggregatedCaseView.CLASS, AggregatedCaseExcelView.CLASS, AggregatedCaseExcelView.customAttributeOrder(), null);
      caseDiag.buildAttributes(AggregatedCaseView.CLASS, CaseDiagnosisTypeExcelView.customAttributeOrder(), new MapODKAttributeMapper(caseDiagAttrMappings));
      master.join(new RepeatFormJoin(master, caseDiag));

      ODKForm caseDisease = new ODKForm(CaseDiseaseManifestationExcelView.CLASS);
      Map<String,String> caseDiseaseAttrMappings = new HashMap<String, String>();
      caseDiseaseAttrMappings.put(AggregatedCaseView.CASEDISEASEMANIFESTATION, CaseDiseaseManifestationExcelView.DISEASEMANIFESTATION);
      caseDisease.buildAttributes(AggregatedCaseView.CLASS, AggregatedCaseExcelView.CLASS, AggregatedCaseExcelView.customAttributeOrder(), null);
      caseDisease.buildAttributes(AggregatedCaseView.CLASS, CaseDiseaseManifestationExcelView.customAttributeOrder(), new MapODKAttributeMapper(caseDiseaseAttrMappings));
      master.join(new RepeatFormJoin(master, caseDisease));

      ODKForm casePatient = new ODKForm(CasePatientTypeExcelView.CLASS);
      Map<String,String> casePatientAttrMappings = new HashMap<String, String>();
      casePatientAttrMappings.put(AggregatedCaseView.CASEPATIENTTYPE, CasePatientTypeExcelView.PATIENTTYPE);
      casePatient.buildAttributes(AggregatedCaseView.CLASS, AggregatedCaseExcelView.CLASS, AggregatedCaseExcelView.customAttributeOrder(), null);
      casePatient.buildAttributes(AggregatedCaseView.CLASS, CasePatientTypeExcelView.customAttributeOrder(), new MapODKAttributeMapper(casePatientAttrMappings));
      master.join(new RepeatFormJoin(master, casePatient));
    }
    else if (mobileType.equals(ControlInterventionExcelView.CLASS))
    {
      master = new ODKForm(ControlInterventionExcelView.CLASS, gfc);
      master.buildAttributes(ControlInterventionView.CLASS, ControlInterventionExcelView.customAttributeOrder(), null);
      
      ODKForm aggPremise = new ODKForm(AggregatedPremiseExcelView.CLASS);
      aggPremise.buildAttributes(AggregatedPremiseExcelView.CLASS, AggregatedPremiseExcelView.customAttributeOrder(), null);
      master.join(new RepeatFormJoin(master, aggPremise));
      
      ODKForm individPremise = new ODKForm(IndividualPremiseExcelView.CLASS);
      individPremise.buildAttributes(IndividualPremiseExcelView.CLASS, IndividualPremiseExcelView.customAttributeOrder(), null);
      master.join(new RepeatFormJoin(master, individPremise));
      
      ODKForm insecticide = new ODKForm(InsecticideInterventionExcelView.CLASS);
      insecticide.buildAttributes(InsecticideInterventionExcelView.CLASS, InsecticideInterventionExcelView.customAttributeOrder(), null);
      master.join(new RepeatFormJoin(master, insecticide));
      
      ODKForm person = new ODKForm(PersonInterventionExcelView.CLASS);
      person.buildAttributes(PersonInterventionExcelView.CLASS, PersonInterventionExcelView.customAttributeOrder(), null);
      master.join(new RepeatFormJoin(master, person));
    }
    else if (mobileType.equals(MosquitoCollectionExcelView.CLASS))
    {
      master = new ODKForm(MosquitoCollectionExcelView.CLASS, gfc);
      master.buildAttributes(MosquitoCollectionView.CLASS, MosquitoCollectionExcelView.customAttributeOrder(), null);

      ODKForm subc = new ODKForm(MosquitoCollectionExcelView.CLASS);
      subc.buildAttributes(SubCollectionView.CLASS, MosquitoCollectionExcelView.customAttributeOrder(), null);

      master.join(new RepeatFormJoin(master, subc));
    }
     else if (mobileType.equals(AggregatedIPTExcelView.CLASS))
     {
       master = new ODKForm(AggregatedIPTExcelView.CLASS, gfc);
       master.buildAttributes(AggregatedIPTExcelView.CLASS, AggregatedIPTExcelView.customAttributeOrder(), null);
     }
     else if (mobileType.equals(AggregatedITNExcelView.CLASS))
     {
       master = new ODKForm(AggregatedITNExcelView.CLASS, gfc);
       master.buildAttributes(AggregatedITNExcelView.CLASS, AggregatedITNExcelView.customAttributeOrder(), null);
     }
     else if (mobileType.equals(EfficacyAssayExcelView.CLASS))
     {
       Map<String,String> attrMappings = new HashMap<String, String>();
       attrMappings.put(EfficacyAssayExcelView.CLASS + "." + EfficacyAssayExcelView.GEOENTITY, EfficacyAssayExcelView.CLASS + "." + EfficacyAssayExcelView.GEOENTITY);
       attrMappings.put(InsecticideBrand.CLASS + "." + InsecticideBrand.PRODUCTNAME, EfficacyAssayExcelView.CLASS + "." + EfficacyAssayExcelView.INSECTICIDETERM);
       
       master = new ODKForm(EfficacyAssayExcelView.CLASS, gfc);
       master.buildAttributes(EfficacyAssayView.CLASS, EfficacyAssayExcelView.customAttributeOrder(), null);
       master.buildAttributes(attrMappings, EfficacyAssayExcelView.customAttributeOrder());
     }
     else if (mobileType.equals(IndividualCaseExcelView.CLASS))
     {
       master = new ODKForm(IndividualCaseExcelView.CLASS, gfc);
       
       ODKForm individPremise = new ODKForm(IndividualCaseExcelView.CLASS);
       individPremise.buildAttributes(IndividualCase.CLASS, IndividualCaseExcelView.customAttributeOrder(), null);
       master.join(new MergeFormJoin(master, individPremise));
       
       ODKForm individInst = new ODKForm(IndividualCaseExcelView.CLASS);
       individInst.buildAttributes(IndividualInstance.CLASS, IndividualCaseExcelView.customAttributeOrder(), null);
       master.join(new MergeFormJoin(master, individInst));
     }
     else if (mobileType.equals(IndividualIPTExcelView.CLASS))
     {
       master = new ODKForm(IndividualIPTExcelView.CLASS, gfc);
       master.buildAttributes(IndividualIPTView.CLASS, IndividualIPTExcelView.customAttributeOrder(), null);
     }
     else if (mobileType.equals(ITNCommunityExcelView.CLASS))
     {
       master = new ODKForm(ITNCommunityExcelView.CLASS, gfc);
       master.buildAttributes(ITNCommunityDistributionView.CLASS, ITNCommunityExcelView.customAttributeOrder(), null);
     }
     else if (mobileType.equals(ITNDistributionExcelView.CLASS))
     {
       master = new ODKForm(ITNDistributionExcelView.CLASS, gfc);
       master.buildAttributes(ITNDistributionView.CLASS, ITNDistributionExcelView.customAttributeOrder(), null);
     }
     else if (mobileType.equals(LarvacideExcelView.CLASS))
     {
       master = new ODKForm(LarvacideExcelView.CLASS, gfc);
       master.buildAttributes(Larvacide.CLASS, LarvacideExcelView.customAttributeOrder(), null);
     }
     else if (mobileType.equals(OperatorSprayExcelView.CLASS))
     {
       master = new ODKForm(OperatorSprayExcelView.CLASS, gfc);
       master.buildAttributes(OperatorSprayView.CLASS, OperatorSprayExcelView.customAttributeOrder(), null);
       
       ODKForm individInst = new ODKForm(OperatorSprayExcelView.CLASS);
       individInst.buildAttributes(HouseholdSprayStatusView.CLASS, OperatorSprayExcelView.customAttributeOrder(), null);
       master.join(new RepeatFormJoin(master, individInst));
     }
     else if (mobileType.equals(TeamSprayExcelView.CLASS))
     {
       master = new ODKForm(TeamSprayExcelView.CLASS, gfc);
       master.buildAttributes(TeamSprayView.CLASS, OperatorSprayExcelView.customAttributeOrder(), null);
       
       ODKForm individInst = new ODKForm(TeamSprayExcelView.CLASS);
       individInst.buildAttributes(OperatorSprayStatusView.CLASS, OperatorSprayExcelView.customAttributeOrder(), null);
       master.join(new RepeatFormJoin(master, individInst));
     }
     else if (mobileType.equals(ZoneSprayExcelView.CLASS))
     {
       master = new ODKForm(ZoneSprayExcelView.CLASS, gfc);
       master.buildAttributes(ZoneSprayView.CLASS, ZoneSprayExcelView.customAttributeOrder(), null);
       
       ODKForm individInst = new ODKForm(ZoneSprayExcelView.CLASS);
       individInst.buildAttributes(TeamSprayStatusView.CLASS, ZoneSprayExcelView.customAttributeOrder(), null);
       master.join(new RepeatFormJoin(master, individInst));
     }
     else if (mobileType.equals(PersonExcelView.CLASS))
     {
       master = new ODKForm(PersonExcelView.CLASS, gfc);
       master.buildAttributes(PersonView.CLASS, PersonExcelView.customAttributeOrder(), null);
     }
     else if (mobileType.equals(PopulationDataExcelView.CLASS))
     {
       master = new ODKForm(PopulationDataExcelView.CLASS, gfc);
       master.buildAttributes(PopulationData.CLASS, PopulationDataExcelView.customAttributeOrder(), null);
     }
     else if (mobileType.equals(PupalCollectionExcelView.CLASS))
     {
       master = new ODKForm(PupalCollectionExcelView.CLASS, gfc);
       master.buildAttributes(PupalCollectionView.CLASS, PupalCollectionExcelView.customAttributeOrder(), null);
       
       ODKForm container = new ODKForm(PupalCollectionExcelView.CLASS);
       container.buildAttributes(PupalContainerView.CLASS, PupalCollectionExcelView.customAttributeOrder(), null);
       master.join(new RepeatFormJoin(master, container));
     }
     else if (mobileType.equals(SurveyExcelView.CLASS))
     {
       master = new ODKForm(SurveyExcelView.CLASS, gfc);
       
       ODKForm household = new ODKForm(SurveyExcelView.CLASS);
       household.buildAttributes(HouseholdView.CLASS, SurveyExcelView.customAttributeOrder(), null);
       master.join(new MergeFormJoin(master, household));
       
       ODKForm itn = new ODKForm(SurveyExcelView.CLASS);
       itn.buildAttributes(ITNInstanceView.CLASS, SurveyExcelView.customAttributeOrder(), null);
       master.join(new MergeFormJoin(master, itn));
       
       ODKForm person = new ODKForm(SurveyExcelView.CLASS);
       person.buildAttributes(SurveyedPersonView.CLASS, SurveyExcelView.customAttributeOrder(), null);
       master.join(new MergeFormJoin(master, person));
     }
     else if (mobileType.equals(ThresholdDataExcelView.CLASS))
     {
       master = new ODKForm(ThresholdDataExcelView.CLASS, gfc);
       master.buildAttributes(ThresholdDataView.CLASS, ThresholdDataExcelView.customAttributeOrder(), null);
     }
     else if (mobileType.equals(ImmatureCollectionExcelView.CLASS))
     {
       master = new ODKForm(ImmatureCollectionExcelView.CLASS, gfc);
       master.buildAttributes(ImmatureCollectionView.CLASS, ImmatureCollectionExcelView.customAttributeOrder(), null);
       
       ODKForm container = new ODKForm(PupalCollectionExcelView.CLASS);
       container.buildAttributes(CollectionContainerView.CLASS, PupalCollectionExcelView.customAttributeOrder(), null);
       master.join(new RepeatFormJoin(master, container));
     }
     else
     {
       throw new UnsupportedOperationException();
     }

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
      method.invoke(null, listenerCollector, new String[] {});
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
        List<GeoHierarchy> list = ( (DynamicGeoColumnListener) listener ).getHierarchyList();
        ghl.addAll(list);
      }
    }

    return new GeoFilterCriteria(ghl);
  }
}
