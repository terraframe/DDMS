package dss.vector.solutions.odk;

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
import com.runwaysdk.dataaccess.FieldConditionDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF;
import com.runwaysdk.dataaccess.MdAttributePrimitiveDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdAttributeStructDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdFieldDAOIF;
import com.runwaysdk.dataaccess.MdFormDAOIF;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdFormDAO;
import com.runwaysdk.dataaccess.metadata.MdWebAttributeDAO;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.Person;
import dss.vector.solutions.PersonView;
import dss.vector.solutions.entomology.BiochemicalAssay;
import dss.vector.solutions.entomology.BiochemicalAssayView;
import dss.vector.solutions.entomology.CollectionContainerView;
import dss.vector.solutions.entomology.DiagnosticAssay;
import dss.vector.solutions.entomology.DiagnosticAssayView;
import dss.vector.solutions.entomology.ImmatureCollection;
import dss.vector.solutions.entomology.ImmatureCollectionView;
import dss.vector.solutions.entomology.InfectionAssay;
import dss.vector.solutions.entomology.InfectionAssayView;
import dss.vector.solutions.entomology.MolecularAssay;
import dss.vector.solutions.entomology.MolecularAssayView;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionView;
import dss.vector.solutions.entomology.PooledInfectionAssay;
import dss.vector.solutions.entomology.PooledInfectionAssayView;
import dss.vector.solutions.entomology.PupalCollection;
import dss.vector.solutions.entomology.PupalCollectionView;
import dss.vector.solutions.entomology.PupalContainerView;
import dss.vector.solutions.entomology.SubCollectionView;
import dss.vector.solutions.entomology.TimeResponseAssay;
import dss.vector.solutions.entomology.TimeResponseAssayView;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayView;
import dss.vector.solutions.entomology.assay.EfficacyAssay;
import dss.vector.solutions.entomology.assay.EfficacyAssayView;
import dss.vector.solutions.entomology.assay.KnockDownAssay;
import dss.vector.solutions.entomology.assay.KnockDownAssayView;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayView;
import dss.vector.solutions.export.AggregatedCaseExcelView;
import dss.vector.solutions.export.AggregatedCaseReferralsExcelView;
import dss.vector.solutions.export.AggregatedCaseTreatmentsExcelView;
import dss.vector.solutions.export.AggregatedIPTExcelView;
import dss.vector.solutions.export.AggregatedITNExcelView;
import dss.vector.solutions.export.AggregatedPremiseExcelView;
import dss.vector.solutions.export.BiochemicalAssayExcelView;
import dss.vector.solutions.export.CaseDiagnosisTypeExcelView;
import dss.vector.solutions.export.CaseDiseaseManifestationExcelView;
import dss.vector.solutions.export.CasePatientTypeExcelView;
import dss.vector.solutions.export.ControlInterventionExcelView;
import dss.vector.solutions.export.DiagnosticAssayExcelView;
import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.export.EfficacyAssayExcelView;
import dss.vector.solutions.export.GeoTargetExcelView;
import dss.vector.solutions.export.ITNCommunityExcelView;
import dss.vector.solutions.export.ITNDistributionExcelView;
import dss.vector.solutions.export.ImmatureCollectionExcelView;
import dss.vector.solutions.export.IndividualCaseExcelView;
import dss.vector.solutions.export.IndividualIPTExcelView;
import dss.vector.solutions.export.IndividualPremiseExcelView;
import dss.vector.solutions.export.InfectionAssayExcelView;
import dss.vector.solutions.export.InsecticideInterventionExcelView;
import dss.vector.solutions.export.KnockDownAssayExcelView;
import dss.vector.solutions.export.LarvacideExcelView;
import dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView;
import dss.vector.solutions.export.MolecularAssayExcelView;
import dss.vector.solutions.export.MosquitoCollectionExcelView;
import dss.vector.solutions.export.OperatorSprayExcelView;
import dss.vector.solutions.export.PersonExcelView;
import dss.vector.solutions.export.PersonInterventionExcelView;
import dss.vector.solutions.export.PooledInfectionAssayExcelView;
import dss.vector.solutions.export.PopulationDataExcelView;
import dss.vector.solutions.export.PupalCollectionExcelView;
import dss.vector.solutions.export.ResourceTargetExcelView;
import dss.vector.solutions.export.SurveyExcelView;
import dss.vector.solutions.export.TeamSprayExcelView;
import dss.vector.solutions.export.ThresholdDataExcelView;
import dss.vector.solutions.export.TimeResponseAssayExcelView;
import dss.vector.solutions.export.ZoneSprayExcelView;
import dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView;
import dss.vector.solutions.form.business.FormBedNet;
import dss.vector.solutions.form.business.FormHousehold;
import dss.vector.solutions.form.business.FormPerson;
import dss.vector.solutions.form.business.FormSurvey;
import dss.vector.solutions.general.PopulationData;
import dss.vector.solutions.general.ThresholdData;
import dss.vector.solutions.general.ThresholdDataView;
import dss.vector.solutions.generator.MdFormUtil;
import dss.vector.solutions.geo.GeoFilterCriteria;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.intervention.monitor.AggregatedIPT;
import dss.vector.solutions.intervention.monitor.AggregatedIPTView;
import dss.vector.solutions.intervention.monitor.ControlIntervention;
import dss.vector.solutions.intervention.monitor.ITNCommunityDistribution;
import dss.vector.solutions.intervention.monitor.ITNCommunityDistributionView;
import dss.vector.solutions.intervention.monitor.ITNData;
import dss.vector.solutions.intervention.monitor.ITNDataView;
import dss.vector.solutions.intervention.monitor.ITNDistribution;
import dss.vector.solutions.intervention.monitor.ITNDistributionView;
import dss.vector.solutions.intervention.monitor.IndividualCase;
import dss.vector.solutions.intervention.monitor.IndividualIPT;
import dss.vector.solutions.intervention.monitor.IndividualIPTView;
import dss.vector.solutions.intervention.monitor.IndividualInstance;
import dss.vector.solutions.intervention.monitor.Larvacide;
import dss.vector.solutions.intervention.monitor.LarvacideInstanceView;
import dss.vector.solutions.intervention.monitor.SurveyedPersonView;
import dss.vector.solutions.irs.GeoTarget;
import dss.vector.solutions.irs.GeoTargetView;
import dss.vector.solutions.irs.HouseholdSprayStatusView;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.OperatorSpray;
import dss.vector.solutions.irs.OperatorSprayStatusView;
import dss.vector.solutions.irs.OperatorSprayView;
import dss.vector.solutions.irs.ResourceTarget;
import dss.vector.solutions.irs.ResourceTargetView;
import dss.vector.solutions.irs.SprayTeam;
import dss.vector.solutions.irs.SprayTeamExcelView;
import dss.vector.solutions.irs.SprayTeamView;
import dss.vector.solutions.irs.TeamSpray;
import dss.vector.solutions.irs.TeamSprayStatusView;
import dss.vector.solutions.irs.TeamSprayView;
import dss.vector.solutions.irs.ZoneSpray;
import dss.vector.solutions.irs.ZoneSprayView;
import dss.vector.solutions.mobile.MobileUtil;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.AggregatedCase;
import dss.vector.solutions.surveillance.AggregatedCaseView;
import dss.vector.solutions.surveillance.CaseDiagnosisTypeView;
import dss.vector.solutions.surveillance.CaseDiseaseManifestationView;
import dss.vector.solutions.surveillance.CasePatientTypeView;

public class ODKForm implements Reloadable
{
  public static final Logger logger        = LoggerFactory.getLogger(ODKForm.class);

  /*
   * Global Map of all the exported term ids. This is used to prevent the same
   * term from being translated multiple times even if the term is an item of
   * multiple different attributes.
   */
  Set<String>                exportedTerms = new TreeSet<String>();

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

      // I don't know why, but the Geo attributes always end up at the front of
      // the list (even though we were returning -1).
      // This code here is just a dumb hack that honestly I don't even
      // understand why it works. (in theory it shouldn't work).
      if (oneI == -1 && twoI != -1)
      {
        return 1;
      }
      if (twoI == -1 && oneI != -1)
      {
        return -1;
      }

      return Integer.compare(oneI, twoI);
    }
  };

  protected MdClassDAOIF             viewMd;

  protected GeoFilterCriteria        gfc;

  protected LinkedList<ODKAttribute> attrs;

  protected LinkedList<ODKFormJoin>  joins;

  protected String                   formTitle;

  protected boolean                  export;

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
    this.export = true;
    this.formTitle = this.getViewMd().getDisplayLabel(Session.getCurrentLocale());
  }

  public void setExport(boolean export)
  {
    this.export = export;
  }

  public boolean isExport()
  {
    return export;
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
      logger.error("Form [" + this.getFormId() + "] has no attributes!");
    }

    for (ODKFormJoin join : joins)
    {
      join.getChild().validate();
    }
  }

  public boolean isStructAttribute(String sourceAttribute)
  {
    for (ODKAttribute attr : this.attrs)
    {
      if (attr instanceof ODKStructAttribute)
      {
        ODKStructAttribute structAttribute = (ODKStructAttribute) attr;

        if (structAttribute.hasAttribute(sourceAttribute))
        {
          return true;
        }
      }
    }

    return false;
  }
  
  public ODKAttribute getAttributeByName(String attributeName)
  {
    for (ODKAttribute attr : this.attrs)
    {
      if (attr.getAttributeName().equals(attributeName))
      {
        return attr;
      }
    }

    return null;
  }

  public ODKStructAttribute getStructAttribute(String sourceAttribute)
  {
    for (ODKAttribute attr : this.attrs)
    {
      if (attr instanceof ODKStructAttribute)
      {
        ODKStructAttribute structAttribute = (ODKStructAttribute) attr;

        if (structAttribute.hasAttribute(sourceAttribute))
        {
          return structAttribute;
        }
      }
    }

    return null;
  }

  public String getFormId()
  {
    return MobileUtil.convertToOdkId(this.getViewMd().definesType());
  }

  public String getFormTitle()
  {
    return this.formTitle;
  }

  public void setFormTitle(String title)
  {
    this.formTitle = title;
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

    if (orderList != null)
    {
      this.attrs.sort(new AttributeComparator(orderList));
    }
  }

  public void buildAttributes(Map<MdAttributeDAOIF, MdAttributeDAOIF> attrMap, List<String> orderList)
  {
    for (MdAttributeDAOIF sourceAttr : attrMap.keySet())
    {
      MdAttributeDAOIF viewAttr = attrMap.get(sourceAttr);

      attrs.add(ODKAttribute.factory(sourceAttr, viewAttr, exportedTerms));
    }

    this.attrs.sort(new AttributeComparator(orderList));
  }

  public void addAttribute(ODKAttribute attr)
  {
    this.attrs.add(attr);
  }

  public ODKAttribute addAttribute(MdAttributeDAOIF sourceAttr, MdAttributeDAOIF viewAttr)
  {
    ODKAttribute attr = ODKAttribute.factory(sourceAttr, viewAttr, exportedTerms);
    
    attrs.add(attr);
    
    return attr;
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

        boolean isValid = isValidReference(referenceMdBusiness);

        return isValid ? viewMdc.definesAttribute(sourceAttr.definesAttribute()) : null;
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

    private boolean isValidReference(MdBusinessDAOIF referenceMdBusiness)
    {
      String type = referenceMdBusiness.getRootMdClassDAO().definesType();

      return type.equals(Term.CLASS) || type.equals(GeoEntity.CLASS) || type.equals(InsecticideBrand.CLASS);
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
    return attributeName.contains(ODKGeoAttribute.PREFIX);
  }

  public boolean isRepeatable(String attributeName)
  {
    return ( this.getRepeatable(attributeName) != null );
  }

  public ODKForm getRepeatable(String attributeName)
  {
    ODKFormJoin join = this.getRepeatableJoin(attributeName);

    if (join != null)
    {
      return join.getChild();
    }

    return null;
  }

  public ODKFormJoin getRepeatableJoin(String attributeName)
  {
    if (attributeName.contains("_"))
    {
      String type = attributeName.replaceAll("_", ".");

      for (ODKFormJoin join : joins)
      {
        if (join.getChild().getViewMd().definesType().equals(type))
        {
          return join;
        }
      }
    }

    return null;
  }

  public boolean isGridAttribute(String attributeName)
  {
    return attributeName.contains(ODKGridAttribute.GRID_ATTR_PREFIX);
  }

  public boolean isStandalone(String attributeName)
  {
    RepeatFormJoin join = (RepeatFormJoin) this.getRepeatableJoin(attributeName);

    return ( join != null && join.isStandalone() );
  }

  public LinkedList<ODKFormJoin> getJoins()
  {
    return joins;
  }

  public static ODKForm factory(java.lang.String mobileType)
  {
    ODKForm master = null;
    GeoFilterCriteria gfc = getGeoCriteriaFromListeners(mobileType);

    if (mobileType.equals(AggregatedCaseExcelView.CLASS))
    {
      master = new ODKForm(AggregatedCaseExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(AggregatedCase.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.setExport(false);

      Map<MdAttributeDAOIF, MdAttributeDAOIF> sharedAttrs = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      sharedAttrs.put(AggregatedCaseExcelView.getStartDateMd(), AggregatedCaseExcelView.getStartDateMd());
      sharedAttrs.put(AggregatedCaseExcelView.getEndDateMd(), AggregatedCaseExcelView.getEndDateMd());
      sharedAttrs.put(AggregatedCaseExcelView.getDisplayLabelMd(), AggregatedCaseExcelView.getDisplayLabelMd());
      sharedAttrs.put(AggregatedCaseExcelView.getGeoEntityMd(), AggregatedCaseExcelView.getGeoEntityMd());

      ODKForm aggCaseRefer = new ODKForm(AggregatedCaseReferralsExcelView.CLASS);
      Map<MdAttributeDAOIF, MdAttributeDAOIF> aggCaseAttrs = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>(sharedAttrs);
      aggCaseAttrs.put(AggregatedCaseReferralsExcelView.getCasesMd(), AggregatedCaseReferralsExcelView.getCasesMd());
      aggCaseAttrs.put(AggregatedCaseReferralsExcelView.getPositiveCasesMd(), AggregatedCaseReferralsExcelView.getPositiveCasesMd());
      aggCaseAttrs.put(AggregatedCaseReferralsExcelView.getNegativeCasesMd(), AggregatedCaseReferralsExcelView.getNegativeCasesMd());
      aggCaseAttrs.put(AggregatedCaseReferralsExcelView.getDeathsMd(), AggregatedCaseReferralsExcelView.getDeathsMd());
      aggCaseRefer.buildAttributes(aggCaseAttrs, AggregatedCaseReferralsExcelView.customAttributeOrder());
      aggCaseRefer.addAttribute(new ODKGridAttribute(AggregatedCaseView.getCaseStockReferralMd(), AggregatedCaseView.getCaseStockReferralMd(), "int"));
      aggCaseRefer.addAttribute(new ODKGridAttribute(AggregatedCaseView.getCaseReferralsMd(), AggregatedCaseView.getCaseReferralsMd(), "int"));
      aggCaseRefer.addAttribute(new ODKCaseDiagnosticGridAttribute(AggregatedCaseView.getCaseDiagnosticMd(), AggregatedCaseView.getCaseDiagnosticMd(), "int"));
      master.join(new RepeatFormJoin(master, aggCaseRefer, true, false));

      ODKForm caseTreats = new ODKForm(AggregatedCaseTreatmentsExcelView.CLASS);
      caseTreats.buildAttributes(sharedAttrs, AggregatedCaseTreatmentsExcelView.customAttributeOrder());
      caseTreats.addAttribute(new ODKGridAttribute(AggregatedCaseView.getCaseTreatmentsMd(), AggregatedCaseView.getCaseTreatmentsMd(), "int"));
      caseTreats.addAttribute(new ODKGridAttribute(AggregatedCaseView.getCaseTreatmentMethodMd(), AggregatedCaseView.getCaseTreatmentMethodMd(), "int"));
      caseTreats.addAttribute(new ODKGridAttribute(AggregatedCaseView.getCaseStocksMd(), AggregatedCaseView.getCaseStocksMd(), "boolean"));
      master.join(new RepeatFormJoin(master, caseTreats, true, false));

      Map<MdAttributeDAOIF, MdAttributeDAOIF> caseDiagAttrs = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>(sharedAttrs);
      caseDiagAttrs.put(AggregatedCaseView.getCaseDiagnosisTypeMd(), CaseDiagnosisTypeExcelView.getDiagnosisTypeMd());

      ODKForm caseDiag = new ODKForm(CaseDiagnosisTypeExcelView.CLASS);
      caseDiag.buildAttributes(caseDiagAttrs, CaseDiagnosisTypeExcelView.customAttributeOrder());
      caseDiag.addAttribute(new ODKGridAttribute(CaseDiagnosisTypeView.getDiagnosisCategoryMd(), CaseDiagnosisTypeView.getDiagnosisCategoryMd(), "int"));
      master.join(new RepeatFormJoin(master, caseDiag, true, false));

      Map<MdAttributeDAOIF, MdAttributeDAOIF> caseDiseaseAttrs = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>(sharedAttrs);
      caseDiseaseAttrs.put(AggregatedCaseView.getCaseDiseaseManifestationMd(), CaseDiseaseManifestationExcelView.getDiseaseManifestationMd());

      ODKForm caseDisease = new ODKForm(CaseDiseaseManifestationExcelView.CLASS);
      caseDisease.buildAttributes(caseDiseaseAttrs, CaseDiseaseManifestationExcelView.customAttributeOrder());
      caseDisease.addAttribute(new ODKGridAttribute(CaseDiseaseManifestationView.getDiseaseCategoryMd(), CaseDiseaseManifestationView.getDiseaseCategoryMd(), "int"));
      master.join(new RepeatFormJoin(master, caseDisease, true, false));

      Map<MdAttributeDAOIF, MdAttributeDAOIF> casePatientAttrs = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>(sharedAttrs);
      casePatientAttrs.put(AggregatedCaseView.getCasePatientTypeMd(), CasePatientTypeExcelView.getPatientTypeMd());

      ODKForm casePatient = new ODKForm(CasePatientTypeExcelView.CLASS);
      casePatient.buildAttributes(casePatientAttrs, CasePatientTypeExcelView.customAttributeOrder());
      casePatient.addAttribute(new ODKGridAttribute(CasePatientTypeView.getPatientCategoryMd(), CasePatientTypeView.getPatientCategoryMd(), "int"));
      master.join(new RepeatFormJoin(master, casePatient, true, false));
    }
    else if (mobileType.equals(ControlInterventionExcelView.CLASS))
    {
      master = new ODKForm(ControlInterventionExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(ControlIntervention.CLASS).getDisplayLabel(Session.getCurrentLocale()));

      Map<MdAttributeDAOIF, MdAttributeDAOIF> sharedAttrs = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      sharedAttrs.put(ControlInterventionExcelView.getStartDateMd(), ControlInterventionExcelView.getStartDateMd());
      sharedAttrs.put(ControlInterventionExcelView.getEndDateMd(), ControlInterventionExcelView.getEndDateMd());
      sharedAttrs.put(ControlInterventionExcelView.getCommentsMd(), ControlInterventionExcelView.getCommentsMd());
      sharedAttrs.put(ControlInterventionExcelView.getGeoEntityMd(), ControlInterventionExcelView.getGeoEntityMd());

      ODKForm aggPremise = new ODKForm(AggregatedPremiseExcelView.CLASS);
      aggPremise.buildAttributes(sharedAttrs, AggregatedPremiseExcelView.customAttributeOrder());
      aggPremise.buildAttributes(AggregatedPremiseExcelView.CLASS, AggregatedPremiseExcelView.customAttributeOrder(), null);
      master.join(new RepeatFormJoin(master, aggPremise, true, true));

      ODKForm individPremise = new ODKForm(IndividualPremiseExcelView.CLASS);
      individPremise.buildAttributes(sharedAttrs, IndividualPremiseExcelView.customAttributeOrder());
      individPremise.buildAttributes(IndividualPremiseExcelView.CLASS, IndividualPremiseExcelView.customAttributeOrder(), null);
      master.join(new RepeatFormJoin(master, individPremise, true, true));

      ODKForm insecticide = new ODKForm(InsecticideInterventionExcelView.CLASS);
      insecticide.buildAttributes(sharedAttrs, InsecticideInterventionExcelView.customAttributeOrder());
      insecticide.buildAttributes(InsecticideInterventionExcelView.CLASS, InsecticideInterventionExcelView.customAttributeOrder(), null);
      master.join(new RepeatFormJoin(master, insecticide, true, true));

      ODKForm person = new ODKForm(PersonInterventionExcelView.CLASS);
      person.buildAttributes(sharedAttrs, PersonInterventionExcelView.customAttributeOrder());
      person.buildAttributes(PersonInterventionExcelView.CLASS, PersonInterventionExcelView.customAttributeOrder(), null);
      master.join(new RepeatFormJoin(master, person, true, true));
    }
    else if (mobileType.equals(MosquitoCollectionExcelView.CLASS))
    {
      master = new ODKForm(MosquitoCollectionExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(MosquitoCollection.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(MosquitoCollectionView.CLASS, MosquitoCollectionExcelView.customAttributeOrder(), null);

      ODKForm subc = new ODKForm(MosquitoCollectionExcelView.CLASS);
      subc.buildAttributes(SubCollectionView.CLASS, MosquitoCollectionExcelView.customAttributeOrder(), null);

      master.join(new RepeatFormJoin(master, subc));
    }
    else if (mobileType.equals(AggregatedIPTExcelView.CLASS))
    {
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(AggregatedIPTExcelView.getGeoEntityMd(), AggregatedIPTExcelView.getGeoEntityMd());

      master = new ODKForm(AggregatedIPTExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(AggregatedIPT.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.addAttribute(new ODKGridAttribute(AggregatedIPTView.getDisplayPatientsMd(), AggregatedIPTView.getDisplayPatientsMd(), "int"));
      master.addAttribute(new ODKGridAttribute(AggregatedIPTView.getDisplayVisitsMd(), AggregatedIPTView.getDisplayVisitsMd(), "int"));
      master.addAttribute(new ODKGridAttribute(AggregatedIPTView.getDisplayDoseMd(), AggregatedIPTView.getDisplayDoseMd(), "int"));
      master.addAttribute(new ODKGridAttribute(AggregatedIPTView.getDisplayTreatmentsMd(), AggregatedIPTView.getDisplayTreatmentsMd(), "int"));
      master.buildAttributes(map, AggregatedIPTExcelView.customAttributeOrder());
      master.buildAttributes(AggregatedIPTView.CLASS, AggregatedIPTExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(AggregatedITNExcelView.CLASS))
    {
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(AggregatedITNExcelView.getGeoEntityMd(), AggregatedITNExcelView.getGeoEntityMd());

      master = new ODKForm(AggregatedITNExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(ITNData.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.addAttribute(new ODKGridAttribute(ITNDataView.getDisplayServicesMd(), ITNDataView.getDisplayServicesMd(), "int"));
      master.addAttribute(new ODKGridAttribute(ITNDataView.getDisplayTargetGroupsMd(), ITNDataView.getDisplayTargetGroupsMd(), "int"));
      master.addAttribute(new ODKGridAttribute(ITNDataView.getDisplayNetsMd(), ITNDataView.getDisplayNetsMd(), "int"));
      master.buildAttributes(map, AggregatedIPTExcelView.customAttributeOrder());      
      master.buildAttributes(ITNDataView.CLASS, AggregatedITNExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(EfficacyAssayExcelView.CLASS))
    {
      Map<MdAttributeDAOIF,MdAttributeDAOIF> attrMappings = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      attrMappings.put(EfficacyAssayExcelView.getGeoEntityMd(), EfficacyAssayExcelView.getGeoEntityMd());
      attrMappings.put(EfficacyAssayView.getInsecticideBrandMd(), EfficacyAssayExcelView.getInsecticideTermMd());
      attrMappings.put(EfficacyAssayView.getSurfacePostionMd(), EfficacyAssayExcelView.getSurfacePositionMd());
      
      master = new ODKForm(EfficacyAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(EfficacyAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(EfficacyAssayView.CLASS, EfficacyAssayExcelView.customAttributeOrder(), null);
      master.buildAttributes(attrMappings, EfficacyAssayExcelView.customAttributeOrder());
    }
    else if (mobileType.equals(IndividualCaseExcelView.CLASS))
    {
      master = new ODKForm(IndividualCaseExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(IndividualCase.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      
      ODKForm individPremise = new ODKForm(IndividualCaseExcelView.CLASS);
      individPremise.buildAttributes(IndividualCase.CLASS, IndividualCaseExcelView.customAttributeOrder(), null);
      master.join(new MergeFormJoin(master, individPremise));
      
      ODKForm individInst = new ODKForm(IndividualCaseExcelView.CLASS);
      individInst.buildAttributes(IndividualInstance.CLASS, IndividualCaseExcelView.customAttributeOrder(), null);
      master.join(new MergeFormJoin(master, individInst));
    }
    else if (mobileType.equals(IndividualIPTExcelView.CLASS))
    {      
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(IndividualIPT.getFacilityMd(), IndividualIPTExcelView.getFacilityMd());
      map.put(Person.getWorkGeoEntityMd(), IndividualIPTExcelView.getWorkGeoEntityMd());
      map.put(Person.getResidentialGeoEntityMd(), IndividualIPTExcelView.getResidentialLocationMd());
      map.put(PersonView.getFirstNameMd(), IndividualIPTExcelView.getPatientFirstNameMd());
      map.put(PersonView.getLastNameMd(), IndividualIPTExcelView.getPatientLastNameMd());
      map.put(PersonView.getDateOfBirthMd(), IndividualIPTExcelView.getPatientDOBMd());
      map.put(PersonView.getSexMd(), IndividualIPTExcelView.getPatientSexMd());
      map.put(PersonView.getWorkInformationMd(), IndividualIPTExcelView.getWorkInformationMd());

      master = new ODKForm(IndividualIPTExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(IndividualCase.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(map, AggregatedIPTExcelView.customAttributeOrder());      
      master.buildAttributes(IndividualIPTView.CLASS, IndividualIPTExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(ITNCommunityExcelView.CLASS))
    {
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(ITNCommunityExcelView.getDistributionLocationMd(), ITNCommunityExcelView.getDistributionLocationMd());
      map.put(ITNCommunityExcelView.getHouseholdAddressMd(), ITNCommunityExcelView.getHouseholdAddressMd());

      master = new ODKForm(ITNCommunityExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(ITNCommunityDistribution.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.addAttribute(new ODKGridAttribute(ITNCommunityDistributionView.getDisplayTargetGroupsMd(), ITNCommunityDistributionView.getDisplayTargetGroupsMd(), "int"));
      master.addAttribute(new ODKGridAttribute(ITNCommunityDistributionView.getDisplayNetsMd(), ITNCommunityDistributionView.getDisplayNetsMd(), "int"));
      
      master.buildAttributes(ITNCommunityDistributionView.CLASS, ITNCommunityExcelView.customAttributeOrder(), null);
      master.buildAttributes(map, ITNCommunityExcelView.customAttributeOrder());
    }
    else if (mobileType.equals(ITNDistributionExcelView.CLASS))
    {
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(ITNDistributionExcelView.getFacilityMd(), ITNDistributionExcelView.getFacilityMd());
      map.put(PersonView.getFirstNameMd(), ITNDistributionExcelView.getRecipientFirstNameMd());
      map.put(PersonView.getLastNameMd(), ITNDistributionExcelView.getRecipientLastNameMd());
      map.put(PersonView.getDateOfBirthMd(), ITNDistributionExcelView.getRecipientDOBMd());
      map.put(PersonView.getSexMd(), ITNDistributionExcelView.getRecipientSexMd());

      master = new ODKForm(ITNDistributionExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(ITNDistribution.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.addAttribute(new ODKGridAttribute(ITNDistributionView.getTargetGroupsMd(), ITNDistributionView.getTargetGroupsMd(), "int"));
      
      master.buildAttributes(ITNDistributionView.CLASS, ITNDistributionExcelView.customAttributeOrder(), null);
      master.buildAttributes(map, ITNDistributionExcelView.customAttributeOrder());
    }
    else if (mobileType.equals(LarvacideExcelView.CLASS))
    {
      master = new ODKForm(LarvacideExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(Larvacide.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(Larvacide.CLASS, LarvacideExcelView.customAttributeOrder(), null);
      
      ODKForm subc = new ODKForm(LarvacideExcelView.CLASS);
      subc.buildAttributes(LarvacideInstanceView.CLASS, LarvacideExcelView.customAttributeOrder(), null);

      master.join(new RepeatFormJoin(master, subc));
    }
    else if (mobileType.equals(OperatorSprayExcelView.CLASS))
    {
      master = new ODKForm(OperatorSprayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(OperatorSpray.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(OperatorSprayView.CLASS, OperatorSprayExcelView.customAttributeOrder(), null);
      
      ODKForm individInst = new ODKForm(OperatorSprayExcelView.CLASS);
      individInst.buildAttributes(HouseholdSprayStatusView.CLASS, OperatorSprayExcelView.customAttributeOrder(), null);
      master.join(new RepeatFormJoin(master, individInst));
    }
    else if (mobileType.equals(TeamSprayExcelView.CLASS))
    {
      master = new ODKForm(TeamSprayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(TeamSpray.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(TeamSprayView.CLASS, OperatorSprayExcelView.customAttributeOrder(), null);
      
      ODKForm individInst = new ODKForm(TeamSprayExcelView.CLASS);
      individInst.buildAttributes(OperatorSprayStatusView.CLASS, OperatorSprayExcelView.customAttributeOrder(), null);
      master.join(new RepeatFormJoin(master, individInst));
    }
    else if (mobileType.equals(ZoneSprayExcelView.CLASS))
    {
      master = new ODKForm(ZoneSprayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(ZoneSpray.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(ZoneSprayView.CLASS, ZoneSprayExcelView.customAttributeOrder(), null);
      
      ODKForm individInst = new ODKForm(ZoneSprayExcelView.CLASS);
      individInst.buildAttributes(TeamSprayStatusView.CLASS, ZoneSprayExcelView.customAttributeOrder(), null);
      master.join(new RepeatFormJoin(master, individInst));
    }
    else if (mobileType.equals(PersonExcelView.CLASS))
    {
      master = new ODKForm(PersonExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(Person.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(PersonView.CLASS, PersonExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(PopulationDataExcelView.CLASS))
    {
      master = new ODKForm(PopulationDataExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(PopulationData.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(PopulationData.CLASS, PopulationDataExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(PupalCollectionExcelView.CLASS))
    {
      master = new ODKForm(PupalCollectionExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(PupalCollection.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(PupalCollectionView.CLASS, PupalCollectionExcelView.customAttributeOrder(), null);
      
      ODKForm container = new ODKForm(PupalCollectionExcelView.CLASS);
      container.addAttribute(new ODKGridAttribute(PupalContainerView.getPupaeAmountMd(), PupalContainerView.getPupaeAmountMd(), "int"));
      container.buildAttributes(PupalContainerView.CLASS, PupalCollectionExcelView.customAttributeOrder(), null);
      master.join(new RepeatFormJoin(master, container));
    }
    else if (mobileType.equals(SurveyExcelView.CLASS))
    {
      master = new ODKForm(SurveyExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(FormSurvey.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(SurveyExcelView.CLASS, SurveyExcelView.customAttributeOrder(), null);
      master.addAttribute(new ODKGridAttribute(SurveyedPersonView.getDisplayLocationsMd(), SurveyedPersonView.getDisplayLocationsMd(), "boolean"));
      master.addAttribute(new ODKGridAttribute(SurveyedPersonView.getDisplayTreatmentsMd(), SurveyedPersonView.getDisplayTreatmentsMd(), "boolean"));
    }
    else if (mobileType.equals(ThresholdDataExcelView.CLASS))
    {
      master = new ODKForm(ThresholdDataExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(ThresholdData.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(ThresholdDataView.CLASS, ThresholdDataExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(ImmatureCollectionExcelView.CLASS))
    {
      master = new ODKForm(ImmatureCollectionExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(ImmatureCollection.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(ImmatureCollectionView.CLASS, ImmatureCollectionExcelView.customAttributeOrder(), null);
      
      ODKForm container = new ODKForm(PupalCollectionExcelView.CLASS);
      container.buildAttributes(CollectionContainerView.CLASS, PupalCollectionExcelView.customAttributeOrder(), null);
      master.join(new RepeatFormJoin(master, container));
    }
    else if (mobileType.equals(SprayTeamExcelView.CLASS))
    {
      master = new ODKForm(SprayTeamExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(SprayTeam.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(SprayTeamView.CLASS, null, null);
    }
    else if (mobileType.equals(DiagnosticAssayExcelView.CLASS))
    {
      master = new ODKForm(DiagnosticAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(DiagnosticAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(DiagnosticAssayView.CLASS, DiagnosticAssayExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(AdultDiscriminatingDoseAssayExcelView.CLASS))
    {
      master = new ODKForm(AdultDiscriminatingDoseAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(AdultDiscriminatingDoseAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(AdultDiscriminatingDoseAssayView.CLASS, AdultDiscriminatingDoseAssayExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(BiochemicalAssayExcelView.CLASS))
    {
      master = new ODKForm(BiochemicalAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(BiochemicalAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(BiochemicalAssayView.CLASS, BiochemicalAssayExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(InfectionAssayExcelView.CLASS))
    {
      master = new ODKForm(InfectionAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(InfectionAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(InfectionAssayView.CLASS, InfectionAssayExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(KnockDownAssayExcelView.CLASS))
    {
      master = new ODKForm(KnockDownAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(KnockDownAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(KnockDownAssayView.CLASS, KnockDownAssayExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(LarvaeDiscriminatingDoseAssayExcelView.CLASS))
    {
      master = new ODKForm(LarvaeDiscriminatingDoseAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(LarvaeDiscriminatingDoseAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(LarvaeDiscriminatingDoseAssayView.CLASS, LarvaeDiscriminatingDoseAssayExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(MolecularAssayExcelView.CLASS))
    {
      master = new ODKForm(MolecularAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(MolecularAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(MolecularAssayView.CLASS, MolecularAssayExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(PooledInfectionAssayExcelView.CLASS))
    {
      master = new ODKForm(PooledInfectionAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(PooledInfectionAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(PooledInfectionAssayView.CLASS, PooledInfectionAssayExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(TimeResponseAssayExcelView.CLASS))
    {
      master = new ODKForm(TimeResponseAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(TimeResponseAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(TimeResponseAssayView.CLASS, TimeResponseAssayExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(GeoTargetExcelView.CLASS))
    {
      master = new ODKForm(GeoTargetExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(GeoTarget.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(GeoTargetView.CLASS, GeoTargetExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(ResourceTargetExcelView.CLASS))
    {
      master = new ODKForm(ResourceTargetExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(ResourceTarget.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(ResourceTargetView.CLASS, ResourceTargetExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.startsWith(MDSSInfo.GENERATED_FORM_BUSINESS_PACKAGE))
    {
      MdFormDAOIF mdForm = (MdFormDAOIF) MdFormDAO.get(MdFormUtil.getMdFormFromBusinessType(mobileType).getId());
      List<DynamicGeoColumnListener> geoListeners = MdFormUtil.getGeoListeners(mdForm, null);
      List<GeoHierarchy> ghl = new ArrayList<GeoHierarchy>();

      for (ExcelExportListener listener : geoListeners)
      {
        if (listener instanceof DynamicGeoColumnListener)
        {
          List<GeoHierarchy> list = ( (DynamicGeoColumnListener) listener ).getHierarchyList();
          ghl.addAll(list);
        }
      }
      
      gfc = new GeoFilterCriteria(ghl);
      
      master = new ODKForm(mobileType, gfc);
      
      List<? extends MdFieldDAOIF> mdFields = mdForm.getOrderedMdFields();
      
      for (MdFieldDAOIF mdField : mdFields)
      {
        if (mdField instanceof MdWebAttributeDAO)
        {
          MdWebAttributeDAO dao = ((MdWebAttributeDAO) mdField);
          
          master.addAttribute(dao.getDefiningMdAttribute(), dao.getDefiningMdAttribute());
        }
      }
      
      for (MdFieldDAOIF mdField : mdFields)
      {
        if (mdField instanceof MdWebAttributeDAO)
        {
          MdWebAttributeDAO dao = ((MdWebAttributeDAO) mdField);
          
          ODKAttribute odkAttr = master.getAttributeByName(dao.getDefiningMdAttribute().definesAttribute());
          
          List<FieldConditionDAOIF> conditions = mdField.getConditions();
          
          ODKAttributeCondition odkCond = null;
          for (FieldConditionDAOIF condition : conditions)
          {
            ODKAttributeCondition loopCond = ODKAttributeCondition.factory(condition, odkAttr, master);
            
            if (odkCond != null)
            {
              odkCond = new ODKAttributeConditionComposite(odkCond, ODKAttributeConditionOperation.AND, loopCond);
            }
            else
            {
              odkCond = loopCond;
            }
          }
          
          odkAttr.setCondition(odkCond);
        }
      }
      
      if (mobileType.equals(FormSurvey.CLASS))
      {
        ODKForm survey = master;
        master = new ODKForm(mobileType, gfc);
        master.join(new RepeatFormJoin(master, survey));
        
        ODKForm household = ODKForm.factory(FormHousehold.CLASS);
        master.join(new RepeatFormJoin(master, household));
        
        ODKForm net = ODKForm.factory(FormBedNet.CLASS);
        master.join(new RepeatFormJoin(master, net));
        
        ODKForm person = ODKForm.factory(FormPerson.CLASS);
        master.join(new RepeatFormJoin(master, person));
      }
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
    catch (Throwable e)
    {
      return null;
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
