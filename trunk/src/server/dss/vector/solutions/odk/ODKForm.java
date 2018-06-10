package dss.vector.solutions.odk;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
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
import com.runwaysdk.dataaccess.MdTreeDAOIF;
import com.runwaysdk.dataaccess.MdWebMultipleTermDAOIF;
import com.runwaysdk.dataaccess.MdWebSingleTermGridDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdFormDAO;
import com.runwaysdk.dataaccess.metadata.MdTreeDAO;
import com.runwaysdk.dataaccess.metadata.MdWebAttributeDAO;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdWebPrimitive;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.Person;
import dss.vector.solutions.PersonView;
import dss.vector.solutions.RangeValueProblem;
import dss.vector.solutions.RelativeValueProblem;
import dss.vector.solutions.ValueGreaterLimitProblem;
import dss.vector.solutions.entomology.BiochemicalAssay;
import dss.vector.solutions.entomology.BiochemicalAssayView;
import dss.vector.solutions.entomology.CollectionContainerView;
import dss.vector.solutions.entomology.ContainerShape;
import dss.vector.solutions.entomology.ControlMortalityException;
import dss.vector.solutions.entomology.DiagnosticAssay;
import dss.vector.solutions.entomology.DiagnosticAssayView;
import dss.vector.solutions.entomology.ImmatureCollection;
import dss.vector.solutions.entomology.ImmatureCollectionView;
import dss.vector.solutions.entomology.InfectionAssay;
import dss.vector.solutions.entomology.InfectionAssayView;
import dss.vector.solutions.entomology.LifeStage;
import dss.vector.solutions.entomology.MolecularAssay;
import dss.vector.solutions.entomology.MolecularAssayView;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionView;
import dss.vector.solutions.entomology.PooledInfectionAssay;
import dss.vector.solutions.entomology.PooledInfectionAssayView;
import dss.vector.solutions.entomology.PupalCollection;
import dss.vector.solutions.entomology.PupalCollectionView;
import dss.vector.solutions.entomology.PupalContainer;
import dss.vector.solutions.entomology.PupalContainerAmountView;
import dss.vector.solutions.entomology.PupalContainerView;
import dss.vector.solutions.entomology.SubCollection;
import dss.vector.solutions.entomology.SubCollectionView;
import dss.vector.solutions.entomology.TimeResponseAssay;
import dss.vector.solutions.entomology.TimeResponseAssayView;
import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayView;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval;
import dss.vector.solutions.entomology.assay.EfficacyAssay;
import dss.vector.solutions.entomology.assay.EfficacyAssayView;
import dss.vector.solutions.entomology.assay.InvalidAgeProblem;
import dss.vector.solutions.entomology.assay.InvalidAgeRangeProblem;
import dss.vector.solutions.entomology.assay.InvalidDeadQuantityProblem;
import dss.vector.solutions.entomology.assay.InvalidFedQuantityProblem;
import dss.vector.solutions.entomology.assay.InvalidGravidQuantityProblem;
import dss.vector.solutions.entomology.assay.KnockDownAssay;
import dss.vector.solutions.entomology.assay.KnockDownAssayView;
import dss.vector.solutions.entomology.assay.KnockDownInterval;
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
import dss.vector.solutions.form.DDMSFieldBuilders;
import dss.vector.solutions.form.business.FormBedNet;
import dss.vector.solutions.form.business.FormHousehold;
import dss.vector.solutions.form.business.FormPerson;
import dss.vector.solutions.form.business.FormSurvey;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.general.PopulationData;
import dss.vector.solutions.general.PopulationDataView;
import dss.vector.solutions.general.PopulationProblem;
import dss.vector.solutions.general.ThresholdData;
import dss.vector.solutions.general.ThresholdDataView;
import dss.vector.solutions.generator.GridExcelAdapter;
import dss.vector.solutions.generator.MdFormUtil;
import dss.vector.solutions.geo.GeoFilterCriteria;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.intervention.monitor.AggregatedIPT;
import dss.vector.solutions.intervention.monitor.AggregatedIPTView;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodView;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonView;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitView;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewBase;
import dss.vector.solutions.intervention.monitor.ControlIntervention;
import dss.vector.solutions.intervention.monitor.ControlInterventionView;
import dss.vector.solutions.intervention.monitor.HouseholdView;
import dss.vector.solutions.intervention.monitor.IPTANCVisit;
import dss.vector.solutions.intervention.monitor.IPTDose;
import dss.vector.solutions.intervention.monitor.IPTPatients;
import dss.vector.solutions.intervention.monitor.IPTTreatment;
import dss.vector.solutions.intervention.monitor.ITNCommunityDistribution;
import dss.vector.solutions.intervention.monitor.ITNCommunityDistributionView;
import dss.vector.solutions.intervention.monitor.ITNCommunityNet;
import dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroup;
import dss.vector.solutions.intervention.monitor.ITNData;
import dss.vector.solutions.intervention.monitor.ITNDataView;
import dss.vector.solutions.intervention.monitor.ITNDistribution;
import dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroup;
import dss.vector.solutions.intervention.monitor.ITNDistributionView;
import dss.vector.solutions.intervention.monitor.ITNInstanceView;
import dss.vector.solutions.intervention.monitor.ITNNet;
import dss.vector.solutions.intervention.monitor.ITNService;
import dss.vector.solutions.intervention.monitor.ITNTargetGroup;
import dss.vector.solutions.intervention.monitor.IndividualCase;
import dss.vector.solutions.intervention.monitor.IndividualIPT;
import dss.vector.solutions.intervention.monitor.IndividualIPTView;
import dss.vector.solutions.intervention.monitor.IndividualInstance;
import dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView;
import dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView;
import dss.vector.solutions.intervention.monitor.InsecticideInterventionView;
import dss.vector.solutions.intervention.monitor.Larvacide;
import dss.vector.solutions.intervention.monitor.LarvacideInstanceView;
import dss.vector.solutions.intervention.monitor.NumberSoldProblem;
import dss.vector.solutions.intervention.monitor.PersonInterventionMethodView;
import dss.vector.solutions.intervention.monitor.PersonInterventionView;
import dss.vector.solutions.intervention.monitor.SurveyPointView;
import dss.vector.solutions.intervention.monitor.SurveyedPersonView;
import dss.vector.solutions.irs.GeoTarget;
import dss.vector.solutions.irs.GeoTargetView;
import dss.vector.solutions.irs.HouseholdSprayStatus;
import dss.vector.solutions.irs.HouseholdSprayStatusView;
import dss.vector.solutions.irs.HouseholdValueProblem;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.OperatorSpray;
import dss.vector.solutions.irs.OperatorSprayStatus;
import dss.vector.solutions.irs.OperatorSprayStatusView;
import dss.vector.solutions.irs.OperatorSprayView;
import dss.vector.solutions.irs.PrevSprayedHouseholdValueProblem;
import dss.vector.solutions.irs.PrevSprayedStructureValueProblem;
import dss.vector.solutions.irs.ResourceTarget;
import dss.vector.solutions.irs.ResourceTargetView;
import dss.vector.solutions.irs.SprayMethod;
import dss.vector.solutions.irs.SprayTeam;
import dss.vector.solutions.irs.SprayTeamExcelView;
import dss.vector.solutions.irs.SprayedHouseholdValueProblem;
import dss.vector.solutions.irs.SprayedSumProblem;
import dss.vector.solutions.irs.StructureValueProblem;
import dss.vector.solutions.irs.TeamSpray;
import dss.vector.solutions.irs.TeamSprayStatus;
import dss.vector.solutions.irs.TeamSprayStatusView;
import dss.vector.solutions.irs.TeamSprayView;
import dss.vector.solutions.irs.ZoneSpray;
import dss.vector.solutions.irs.ZoneSprayView;
import dss.vector.solutions.mobile.MobileUtil;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.surveillance.AggregatedAgeGroup;
import dss.vector.solutions.surveillance.AggregatedCase;
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
import dss.vector.solutions.util.LocalizationFacade;
import dss.vector.solutions.util.MDSSProperties;
import dss.vector.solutions.util.ReadableAttributeView;

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

  private ODKForm                    parentForm;

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
    this.setFormTitle(this.getViewMd().getDisplayLabel(Session.getCurrentLocale()));
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
    return MobileUtil.convertToOdkId(this.getViewMd().definesType() + "_" + Disease.getCurrent().getKey());
  }

  public String getFormTitle()
  {
    return this.formTitle;
  }

  public void setFormTitle(String title)
  {
    this.formTitle = title + " (" + Disease.getCurrent().getDisplayLabel() + ")";
  }

  public void writeTranslation(Element parent, Document document, String context, int maxDepth)
  {
    for (ODKAttribute attr : this.attrs)
    {
      if (attr.isValid())
      {
        attr.writeTranslation(parent, document, context, maxDepth);
      }
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
      if (attr.isValid())
      {
        attr.writeBind(parent, document, context, maxDepth);
      }
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
      if (attr.isValid())
      {
        attr.writeBody(parent, document, context, maxDepth);
      }
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
      if (attr.isValid())
      {
        attr.writeInstance(parent, document, context, maxDepth);
      }
    }

    for (ODKFormJoin join : joins)
    {
      join.writeInstance(parent, document, context, maxDepth);
    }
  }

  public ODKForm getParent()
  {
    return this.parentForm;
  }

  public void setParent(ODKForm parent)
  {
    this.parentForm = parent;
  }

  public void join(ODKFormJoin join)
  {
    this.joins.add(join);

    join.getChild().setParent(this);
  }

  public LinkedList<ODKAttribute> getAttributes()
  {
    return attrs;
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

      addAttribute(ODKAttribute.factory(this, sourceAttr, viewAttr, exportedTerms));
    }

    if (orderList != null)
    {
      this.attrs.sort(new AttributeComparator(orderList));
    }
  }

  public void buildAttributes(Map<MdAttributeDAOIF, MdAttributeDAOIF> attrMap, List<String> orderList)
  {
    for (MdAttributeDAOIF viewAttr : attrMap.keySet())
    {
      MdAttributeDAOIF sourceAttr = attrMap.get(viewAttr);

      addAttribute(ODKAttribute.factory(this, sourceAttr, viewAttr, exportedTerms));
    }

    this.attrs.sort(new AttributeComparator(orderList));
  }

  /**
   * Adds the attribute to the end of the attribute list. If an attribute
   * already exists with the same name, it will be replaced.
   * 
   * @param attr
   */
  public void addAttribute(ODKAttribute attr)
  {
    if (attr instanceof ODKMetadataAttribute)
    {
      ODKAttributeConstraint.addConstraintsToAttribute( ( (ODKMetadataAttribute) attr ).getSourceMdAttribute(), attr);
    }

    int existingIndex = -1;
    for (int i = 0; i < attrs.size(); ++i)
    {
      ODKAttribute existingAttr = attrs.get(i);

      if (existingAttr.getAttributeName().equals(attr.getAttributeName()))
      {
        existingIndex = i;
      }
    }

    if (existingIndex != -1)
    {
      this.attrs.remove(existingIndex);
      this.attrs.add(existingIndex, attr);
    }
    else
    {
      this.attrs.add(attr);
    }
  }

  public ODKAttribute addAttribute(MdAttributeDAOIF sourceAttr, MdAttributeDAOIF viewAttr)
  {
    ODKAttribute attr = ODKAttribute.factory(this, sourceAttr, viewAttr, exportedTerms);

    addAttribute(attr);

    return attr;
  }

  public void removeAttribute(String attributeName)
  {
    Iterator<ODKAttribute> it = attrs.iterator();

    while (it.hasNext())
    {
      ODKAttribute attribute = it.next();

      String name = attribute.getAttributeName();
      if (name.equals(attributeName))
      {
        it.remove();
      }
    }
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

      return type.equals(Term.CLASS) || type.equals(GeoEntity.CLASS) || type.equals(InsecticideBrand.CLASS) || type.equals(AggregatedAgeGroup.CLASS);
    }
  }

  public static class MapODKAttributeMapper extends DefaultODKAttributeMapper implements Reloadable
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

  public static interface ODKAttributeMapper extends Reloadable
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

  public boolean isSubForm(String attributeName)
  {
    return ( this.getSubForm(attributeName) != null );
  }

  public ODKForm getSubForm(String attributeName)
  {
    ODKFormJoin join = this.getJoin(attributeName);

    if (join != null)
    {
      return join.getChild();
    }

    return null;
  }

  public ODKFormJoin getJoin(String attributeName)
  {
    for (ODKFormJoin join : joins)
    {
      ODKForm form = join.getChild();

      if (form.getFormId().equals(attributeName))
      {
        return join;
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
    ODKFormJoin join = this.getJoin(attributeName);

    return ( join != null && join.isStandalone() );
  }

  public LinkedList<ODKFormJoin> getJoins()
  {
    return joins;
  }
  
  public void addBasicConstraint(ODKAttribute definingAttr, ODKAttribute comparativeAttr, ODKAttributeConditionOperation operation, Object comparative, String msg)
  {
    if (comparative instanceof MdAttributeDAOIF)
    {
      comparative = this.getAttributeByName( ( (MdAttributeDAOIF) comparative ).definesAttribute());
      if (comparative == null)
      {
        throw new ProgrammingErrorException("Unable to find attribute.");
      }
    }

    definingAttr.addConstraint(new ODKAttributeConstraintBasic(definingAttr, comparativeAttr, operation, new ODKConditionComparative(comparative), msg));
  }

  public void addBasicConstraint(ODKAttribute attr1, ODKAttributeConditionOperation operation, Object comparative, String msg)
  {
    if (comparative instanceof MdAttributeDAOIF)
    {
      comparative = this.getAttributeByName( ( (MdAttributeDAOIF) comparative ).definesAttribute());
      if (comparative == null)
      {
        throw new ProgrammingErrorException("Unable to find attribute.");
      }
    }

    attr1.addConstraint(new ODKAttributeConstraintBasic(attr1, operation, new ODKConditionComparative(comparative), msg));
  }

  public void addBasicRelevancy(ODKAttribute definingAttr, ODKAttribute comparativeAttr, ODKAttributeConditionOperation operation, Object comparative)
  {
    definingAttr.addRelevancy(new ODKAttributeRelevancyBasic(definingAttr, comparativeAttr, operation, new ODKConditionComparative(comparative)));
  }

  public void addBasicRelevancy(MdAttributeDAOIF definingAttr, MdAttributeDAOIF comparativeAttr, ODKAttributeConditionOperation operation, Object comparative)
  {
    ODKAttribute odkDefiningAttr = this.getAttributeByName(definingAttr.definesAttribute());
    if (odkDefiningAttr == null)
    {
      throw new ProgrammingErrorException("Unable to find attribute [" + definingAttr.getKey() + "].");
    }

    ODKAttribute odkComparativeAttr = this.getAttributeByName(comparativeAttr.definesAttribute());
    if (odkComparativeAttr == null)
    {
      throw new ProgrammingErrorException("Unable to find attribute [" + comparativeAttr.getKey() + "].");
    }

    odkDefiningAttr.addRelevancy(new ODKAttributeRelevancyBasic(odkDefiningAttr, odkComparativeAttr, operation, new ODKConditionComparative(comparative)));
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
      master.addAttribute(AggregatedCaseView.getAgeGroupMd(), AggregatedCaseExcelView.getDisplayLabelMd());
      master.buildAttributes(AggregatedCaseView.CLASS, AggregatedCaseExcelView.customAttributeOrder(), null);

      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(null);
      p.setCurrentDate(null);
      p.setNotification(new AggregatedCase(), AggregatedCase.STARTDATE);
      master.addBasicConstraint(master.getAttributeByName(AggregatedCase.STARTDATE), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p.getLocalizedMessage());

      CurrentDateProblem p2 = new CurrentDateProblem();
      p2.setGivenDate(null);
      p2.setCurrentDate(null);
      p2.setNotification(new AggregatedCase(), AggregatedCase.ENDDATE);
      master.addBasicConstraint(master.getAttributeByName(AggregatedCase.ENDDATE), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p2.getLocalizedMessage());

      LinkedList<ODKAttribute> attributes = master.getAttributes();

      for (ODKAttribute attribute : attributes)
      {
        attribute.setIsOverride(true);
      }

      ODKForm aggCaseRefer = new ODKForm(AggregatedCaseReferralsExcelView.CLASS);
      Map<MdAttributeDAOIF, MdAttributeDAOIF> aggCaseAttrs = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      aggCaseAttrs.put(AggregatedCaseReferralsExcelView.getCasesMd(), AggregatedCaseReferralsExcelView.getCasesMd());
      aggCaseAttrs.put(AggregatedCaseReferralsExcelView.getPositiveCasesMd(), AggregatedCaseReferralsExcelView.getPositiveCasesMd());
      aggCaseAttrs.put(AggregatedCaseReferralsExcelView.getNegativeCasesMd(), AggregatedCaseReferralsExcelView.getNegativeCasesMd());
      aggCaseAttrs.put(AggregatedCaseReferralsExcelView.getDeathsMd(), AggregatedCaseReferralsExcelView.getDeathsMd());
      aggCaseRefer.buildAttributes(aggCaseAttrs, AggregatedCaseReferralsExcelView.customAttributeOrder());
      aggCaseRefer.addAttribute(new ODKCompositeGridAttribute(aggCaseRefer, AggregatedCaseView.getCaseStockReferralMd(), AggregatedCaseView.getCaseStockReferralMd(), CaseStockReferralView.getAmountMd()));
      aggCaseRefer.addAttribute(new ODKCompositeGridAttribute(aggCaseRefer, AggregatedCaseView.getCaseReferralsMd(), AggregatedCaseView.getCaseReferralsMd(), CaseReferralView.getAmountMd()));
      aggCaseRefer.addAttribute(new ODKCompositeGridAttribute(aggCaseRefer, AggregatedCaseView.getCaseDiagnosticMd(), AggregatedCaseView.getCaseDiagnosticMd(), CaseDiagnosticView.getAmountMd(), CaseDiagnosticView.getAmountPositiveMd()));
      master.join(new GroupFormJoin(master, aggCaseRefer, true));

      ODKForm caseTreats = new ODKForm(AggregatedCaseTreatmentsExcelView.CLASS);
      caseTreats.addAttribute(new ODKCompositeGridAttribute(caseTreats, AggregatedCaseView.getCaseTreatmentsMd(), AggregatedCaseView.getCaseTreatmentsMd(), CaseTreatmentView.getAmountMd()));
      caseTreats.addAttribute(new ODKCompositeGridAttribute(caseTreats, AggregatedCaseView.getCaseTreatmentMethodMd(), AggregatedCaseView.getCaseTreatmentMethodMd(), CaseTreatmentMethodView.getAmountMd()));
      caseTreats.addAttribute(new ODKCompositeGridAttribute(caseTreats, AggregatedCaseView.getCaseStocksMd(), AggregatedCaseView.getCaseStocksMd(), CaseTreatmentStockView.getOutOfStockMd()));
      master.join(new GroupFormJoin(master, caseTreats, true));

      if (ReadableAttributeView.isVisible(AggregatedCaseView.getCaseDiagnosisTypeMd()))
      {
        Term[] roots = TermRootCache.getRoots(AggregatedCaseView.getCaseDiagnosisTypeMd());

        if (roots.length > 0)
        {
          ODKForm caseDiag = new ODKTermForm(CaseDiagnosisTypeExcelView.CLASS, CaseDiagnosisTypeExcelView.getDiagnosisTypeMd(), roots);
          caseDiag.addAttribute(new ODKCompositeGridAttribute(caseDiag, CaseDiagnosisTypeView.getDiagnosisCategoryMd(), CaseDiagnosisTypeView.getDiagnosisCategoryMd(), CaseDiagnosisTypeAmountView.getAmountMd()));
          master.join(new GroupFormJoin(master, caseDiag, true));
        }
      }

      if (ReadableAttributeView.isVisible(AggregatedCaseView.getCaseDiseaseManifestationMd()))
      {
        Term[] roots = TermRootCache.getRoots(AggregatedCaseView.getCaseDiseaseManifestationMd());

        if (roots.length > 0)
        {
          ODKForm caseDisease = new ODKTermForm(CaseDiseaseManifestationExcelView.CLASS, CaseDiseaseManifestationExcelView.getDiseaseManifestationMd(), roots);
          caseDisease.addAttribute(new ODKCompositeGridAttribute(caseDisease, CaseDiseaseManifestationView.getDiseaseCategoryMd(), CaseDiseaseManifestationView.getDiseaseCategoryMd(), CaseDiseaseManifestationAmountView.getAmountMd()));
          master.join(new GroupFormJoin(master, caseDisease, true));
        }
      }

      if (ReadableAttributeView.isVisible(AggregatedCaseView.getCasePatientTypeMd()))
      {
        Term[] roots = TermRootCache.getRoots(AggregatedCaseView.getCasePatientTypeMd());

        if (roots.length > 0)
        {
          ODKForm casePatient = new ODKTermForm(CasePatientTypeExcelView.CLASS, CasePatientTypeExcelView.getPatientTypeMd(), roots);
          casePatient.addAttribute(new ODKCompositeGridAttribute(casePatient, CasePatientTypeView.getPatientCategoryMd(), CasePatientTypeView.getPatientCategoryMd(), CasePatientTypeAmountView.getAmountMd()));
          master.join(new GroupFormJoin(master, casePatient, true));
        }
      }
    }
    else if (mobileType.equals(ControlInterventionExcelView.CLASS))
    {
      master = new ODKForm(ControlInterventionExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(ControlIntervention.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.setExport(false);
      master.buildAttributes(ControlInterventionView.CLASS, ControlInterventionExcelView.customAttributeOrder(), null);

      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(null);
      p.setCurrentDate(null);
      p.setNotification(new ControlIntervention(), ControlIntervention.STARTDATE);
      master.addBasicConstraint(master.getAttributeByName(ControlIntervention.STARTDATE), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p.getLocalizedMessage());
      
      CurrentDateProblem p2 = new CurrentDateProblem();
      p2.setGivenDate(null);
      p2.setCurrentDate(null);
      p2.setNotification(new ControlIntervention(), ControlIntervention.ENDDATE);
      master.addBasicConstraint(master.getAttributeByName(ControlIntervention.ENDDATE), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p2.getLocalizedMessage());
      
      LinkedList<ODKAttribute> attributes = master.getAttributes();

      for (ODKAttribute attribute : attributes)
      {
        attribute.setIsOverride(true);
      }

      ODKForm aggPremise = new ODKForm(AggregatedPremiseExcelView.CLASS);
      aggPremise.addAttribute(new ODKGridAttribute(aggPremise, AggregatedPremiseVisitView.getInterventionMethodMd(), AggregatedPremiseVisitView.getInterventionMethodMd(), AggregatedPremiseMethodView.getAmountMd()));
      aggPremise.addAttribute(new ODKGridAttribute(aggPremise, AggregatedPremiseVisitViewBase.getNonTreatmentReasonMd(), AggregatedPremiseVisitViewBase.getNonTreatmentReasonMd(), AggregatedPremiseReasonView.getAmountMd()));
      aggPremise.addAttribute(AggregatedPremiseExcelView.getPremiseGeoEntityMd(), AggregatedPremiseExcelView.getPremiseGeoEntityMd());
      aggPremise.buildAttributes(AggregatedPremiseVisitView.CLASS, AggregatedPremiseExcelView.customAttributeOrder(), null);
      master.join(new RepeatFormJoin(master, aggPremise, true));

      ODKForm individPremise = new ODKForm(IndividualPremiseExcelView.CLASS);
      individPremise.addAttribute(new ODKGridAttribute(individPremise, IndividualPremiseVisitView.getInterventionMethodMd(), IndividualPremiseVisitView.getInterventionMethodMd(), IndividualPremiseVisitMethodView.getUsedMd()));
      individPremise.addAttribute(IndividualPremiseExcelView.getPremiseGeoEntityMd(), IndividualPremiseExcelView.getPremiseGeoEntityMd());
      individPremise.buildAttributes(IndividualPremiseVisitView.CLASS, IndividualPremiseExcelView.customAttributeOrder(), null);
      master.join(new RepeatFormJoin(master, individPremise, true));

      if (ReadableAttributeView.isVisible(ControlInterventionView.getInsecticideInterventionMd()))
      {
        Term[] roots = TermRootCache.getRoots(ControlInterventionView.getInsecticideInterventionMd());

        if (roots.length > 0)
        {
          ODKForm insecticide = new ODKTermForm(InsecticideInterventionExcelView.CLASS, InsecticideInterventionExcelView.getInterventionMethodMd(), roots);
          insecticide.addAttribute(InsecticideInterventionView.getInsecticideMd(), InsecticideInterventionExcelView.getInsecticideMd());
          insecticide.addAttribute(InsecticideInterventionView.getQuantityMd(), InsecticideInterventionExcelView.getQuantityMd());
          insecticide.addAttribute(InsecticideInterventionView.getUnitMd(), InsecticideInterventionExcelView.getUnitMd());
          master.join(new GroupFormJoin(master, insecticide, true));
        }
      }

      ODKForm person = new ODKForm(PersonInterventionExcelView.CLASS);
      person.addAttribute(new ODKGridAttribute(person, PersonInterventionView.getInterventionMethodMd(), PersonInterventionView.getInterventionMethodMd(), PersonInterventionMethodView.getAmountMd()));
      person.buildAttributes(PersonInterventionView.CLASS, PersonInterventionExcelView.customAttributeOrder(), null);
      master.join(new GroupFormJoin(master, person, true));
    }
    else if (mobileType.equals(MosquitoCollectionExcelView.CLASS))
    {
      master = new ODKForm(MosquitoCollectionExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(MosquitoCollection.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(MosquitoCollectionView.CLASS, MosquitoCollectionExcelView.customAttributeOrder(), null);

      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(null);
      p.setCurrentDate(null);
      p.setNotification(new MosquitoCollection(), MosquitoCollectionExcelView.COLLECTIONDATE);
      master.addBasicConstraint(master.getAttributeByName(MosquitoCollectionExcelView.COLLECTIONDATE), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p.getLocalizedMessage());
      
      CurrentDateProblem p2 = new CurrentDateProblem();
      p2.setGivenDate(null);
      p2.setCurrentDate(null);
      p2.setNotification(new MosquitoCollection(), MosquitoCollectionExcelView.DATELASTSPRAYED);
      master.addBasicConstraint(master.getAttributeByName(MosquitoCollectionExcelView.DATELASTSPRAYED), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p2.getLocalizedMessage());
      
      ODKForm subc = new ODKForm(MosquitoCollectionExcelView.CLASS);
      subc.addAttribute(SubCollection.getFemalesUnknownMd(), SubCollection.getFemalesUnknownMd());
      subc.buildAttributes(SubCollectionView.CLASS, MosquitoCollectionExcelView.customAttributeOrder(), null);

      ValueGreaterLimitProblem problem = new ValueGreaterLimitProblem();
      problem.setValueAttributeLabel(SubCollection.getParousMd().getDisplayLabel(Session.getCurrentLocale()));
      problem.setLimitAttributeLabel(SubCollection.getDisectedMd().getDisplayLabel(Session.getCurrentLocale()));
      subc.addBasicConstraint(subc.getAttributeByName(SubCollection.PAROUS), ODKAttributeConditionOperation.LESS_THAN_EQUALS, subc.getAttributeByName(SubCollection.DISECTED), problem.getLocalizedMessage());
      subc.addBasicRelevancy(subc.getAttributeByName(SubCollection.MALE), master.getAttributeByName(MosquitoCollection.LIFESTAGE), ODKAttributeConditionOperation.EQUALS, LifeStage.ADULT);
      subc.addBasicRelevancy(subc.getAttributeByName(SubCollection.PUPAE), master.getAttributeByName(MosquitoCollection.LIFESTAGE), ODKAttributeConditionOperation.EQUALS, LifeStage.IMMATURE);
      subc.addBasicRelevancy(subc.getAttributeByName(SubCollection.UNKNOWNS), master.getAttributeByName(MosquitoCollection.LIFESTAGE), ODKAttributeConditionOperation.NOT_EQUALS, LifeStage.EGG);
      subc.addBasicRelevancy(subc.getAttributeByName(SubCollection.EGGS), master.getAttributeByName(MosquitoCollection.LIFESTAGE), ODKAttributeConditionOperation.EQUALS, LifeStage.EGG);
      subc.addBasicRelevancy(subc.getAttributeByName(SubCollectionView.FEMALESFED), master.getAttributeByName(MosquitoCollection.LIFESTAGE), ODKAttributeConditionOperation.EQUALS, LifeStage.ADULT);
      subc.addBasicRelevancy(subc.getAttributeByName(SubCollectionView.FEMALESGRAVID), master.getAttributeByName(MosquitoCollection.LIFESTAGE), ODKAttributeConditionOperation.EQUALS, LifeStage.ADULT);
      subc.addBasicRelevancy(subc.getAttributeByName(SubCollectionView.FEMALESHALFGRAVID), master.getAttributeByName(MosquitoCollection.LIFESTAGE), ODKAttributeConditionOperation.EQUALS, LifeStage.ADULT);
      subc.addBasicRelevancy(subc.getAttributeByName(SubCollectionView.FEMALESUNFED), master.getAttributeByName(MosquitoCollection.LIFESTAGE), ODKAttributeConditionOperation.EQUALS, LifeStage.ADULT);
      subc.addBasicRelevancy(subc.getAttributeByName(SubCollectionView.FEMALESUNKNOWN), master.getAttributeByName(MosquitoCollection.LIFESTAGE), ODKAttributeConditionOperation.EQUALS, LifeStage.ADULT);

      master.join(new RepeatFormJoin(master, subc));
    }
    else if (mobileType.equals(AggregatedIPTExcelView.CLASS))
    {
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(AggregatedIPTExcelView.getGeoEntityMd(), AggregatedIPTExcelView.getGeoEntityMd());

      master = new ODKForm(AggregatedIPTExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(AggregatedIPT.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.addAttribute(new ODKGridAttribute(master, AggregatedIPTView.getDisplayPatientsMd(), AggregatedIPTView.getDisplayPatientsMd(), IPTPatients.getAmountMd()));
      master.addAttribute(new ODKGridAttribute(master, AggregatedIPTView.getDisplayVisitsMd(), AggregatedIPTView.getDisplayVisitsMd(), IPTANCVisit.getAmountMd()));
      master.addAttribute(new ODKGridAttribute(master, AggregatedIPTView.getDisplayDoseMd(), AggregatedIPTView.getDisplayDoseMd(), IPTDose.getAmountMd()));
      master.addAttribute(new ODKGridAttribute(master, AggregatedIPTView.getDisplayTreatmentsMd(), AggregatedIPTView.getDisplayTreatmentsMd(), IPTTreatment.getAmountMd()));
      master.buildAttributes(AggregatedIPTView.CLASS, AggregatedIPTExcelView.customAttributeOrder(), null);
      master.removeAttribute(AggregatedIPTExcelView.getGeoEntityMd().definesAttribute());
      master.buildAttributes(map, AggregatedIPTExcelView.customAttributeOrder());
    }
    else if (mobileType.equals(AggregatedITNExcelView.CLASS))
    {
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(AggregatedITNExcelView.getGeoEntityMd(), AggregatedITNExcelView.getGeoEntityMd());

      master = new ODKForm(AggregatedITNExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(ITNData.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.addAttribute(new ODKGridAttribute(master, ITNDataView.getDisplayServicesMd(), ITNDataView.getDisplayServicesMd(), ITNService.getAmountMd()));
      master.addAttribute(new ODKGridAttribute(master, ITNDataView.getDisplayTargetGroupsMd(), ITNDataView.getDisplayTargetGroupsMd(), ITNTargetGroup.getAmountMd()));
      master.addAttribute(new ODKGridAttribute(master, ITNDataView.getDisplayNetsMd(), ITNDataView.getDisplayNetsMd(), ITNNet.getAmountMd()));
      master.buildAttributes(ITNDataView.CLASS, AggregatedITNExcelView.customAttributeOrder(), null);

      master.removeAttribute(AggregatedITNExcelView.getGeoEntityMd().definesAttribute());

      master.buildAttributes(map, AggregatedIPTExcelView.customAttributeOrder());
      
      NumberSoldProblem problem = new NumberSoldProblem();
      problem.setNotification(new ITNData(), AggregatedITNExcelView.NUMBERSOLD);
      master.addBasicConstraint(master.getAttributeByName(AggregatedITNExcelView.NUMBERSOLD), ODKAttributeConditionOperation.LESS_THAN_EQUALS, master.getAttributeByName(AggregatedITNExcelView.NUMBERDISTRIBUTED), problem.getLocalizedMessage());
      master.addBasicRelevancy(master.getAttributeByName(AggregatedITNExcelView.CURRENCYRECEIVED), master.getAttributeByName(AggregatedITNExcelView.NUMBERSOLD), ODKAttributeConditionOperation.NOT_EQUALS, 0);
    }
    else if (mobileType.equals(EfficacyAssayExcelView.CLASS))
    {
      Map<MdAttributeDAOIF, MdAttributeDAOIF> attrMappings = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      attrMappings.put(EfficacyAssayExcelView.getGeoEntityMd(), EfficacyAssayExcelView.getGeoEntityMd());
      attrMappings.put(EfficacyAssayExcelView.getInsecticideTermMd(), EfficacyAssayView.getInsecticideBrandMd());
      attrMappings.put(EfficacyAssayExcelView.getSurfacePositionMd(), EfficacyAssayView.getSurfacePostionMd());

      master = new ODKForm(EfficacyAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(EfficacyAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(EfficacyAssayView.CLASS, EfficacyAssayExcelView.customAttributeOrder(), null);

      master.removeAttribute(EfficacyAssayExcelView.getGeoEntityMd().definesAttribute());

      master.buildAttributes(attrMappings, EfficacyAssayExcelView.customAttributeOrder());
      
      ODKStructAttribute structAttr = (ODKStructAttribute) master.getAttributeByName(EfficacyAssayExcelView.AGERANGE);
      
      InvalidAgeProblem p = new InvalidAgeProblem();
      p.setAge(null);
      p.setNotification(new AdultAgeRange(), AdultAgeRange.STARTPOINT);
      master.addBasicConstraint(structAttr.getAttribute(AdultAgeRange.STARTPOINT), ODKAttributeConditionOperation.LESS_THAN_EQUALS, 20, p.getLocalizedMessage());
      master.addBasicConstraint(structAttr.getAttribute(AdultAgeRange.STARTPOINT), ODKAttributeConditionOperation.GREATER_THAN_EQUALS, 0, "");
      
      InvalidAgeProblem p2 = new InvalidAgeProblem();
      p2.setAge(null);
      p2.setNotification(new AdultAgeRange(), AdultAgeRange.ENDPOINT);
      master.addBasicConstraint(structAttr.getAttribute(AdultAgeRange.ENDPOINT), ODKAttributeConditionOperation.LESS_THAN_EQUALS, 20, p2.getLocalizedMessage());
      master.addBasicConstraint(structAttr.getAttribute(AdultAgeRange.ENDPOINT), ODKAttributeConditionOperation.GREATER_THAN_EQUALS, 0, "");
      
      InvalidAgeRangeProblem p3 = new InvalidAgeRangeProblem();
      p3.setStartPoint(null);
      p3.setEndPoint(null);
      p3.setNotification(new AdultAgeRange(), AdultAgeRange.STARTPOINT);
      master.addBasicConstraint(structAttr.getAttribute(AdultAgeRange.STARTPOINT), ODKAttributeConditionOperation.LESS_THAN_EQUALS, structAttr.getAttribute(AdultAgeRange.ENDPOINT), p3.getLocalizedMessage());
      
      ControlMortalityException cme = new ControlMortalityException();
      master.addBasicConstraint(master.getAttributeByName(EfficacyAssayExcelView.CONTROLTESTMORTALITY), ODKAttributeConditionOperation.LESS_THAN_EQUALS, 20, cme.getLocalizedMessage());
      
      InvalidDeadQuantityProblem idq = new InvalidDeadQuantityProblem();
      idq.setQuantityDead(null);
      idq.setQuantityTested(null);
      idq.setNotification(new EfficacyAssay(), EfficacyAssayExcelView.QUANTITYDEAD);
      master.addBasicConstraint(master.getAttributeByName(EfficacyAssayExcelView.QUANTITYDEAD), ODKAttributeConditionOperation.LESS_THAN_EQUALS, master.getAttributeByName(EfficacyAssayExcelView.QUANTITYTESTED), idq.getLocalizedMessage());
      
      InvalidFedQuantityProblem qfp = new InvalidFedQuantityProblem();
      qfp.setFed(null);
      qfp.setNotification(new EfficacyAssay(), EfficacyAssayExcelView.FED);
      master.addBasicConstraint(master.getAttributeByName(EfficacyAssayExcelView.QUANTITYTESTED), ODKAttributeConditionOperation.LESS_THAN_EQUALS, master.getAttributeByName(EfficacyAssayExcelView.FED), qfp.getLocalizedMessage());
      
      InvalidGravidQuantityProblem igq = new InvalidGravidQuantityProblem();
      igq.setGravid(null);
      igq.setNotification(new EfficacyAssay(), EfficacyAssayExcelView.GRAVID);
      master.addBasicConstraint(master.getAttributeByName(EfficacyAssayExcelView.GRAVID), ODKAttributeConditionOperation.LESS_THAN_EQUALS, master.getAttributeByName(EfficacyAssayExcelView.QUANTITYTESTED), igq.getLocalizedMessage());
    }
    else if (mobileType.equals(IndividualCaseExcelView.CLASS))
    {
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(IndividualCaseExcelView.getIdentifierMd(), PersonView.getIdentifierMd());
      map.put(IndividualCaseExcelView.getFirstNameMd(), PersonView.getFirstNameMd());
      map.put(IndividualCaseExcelView.getLastNameMd(), PersonView.getLastNameMd());
      map.put(IndividualCaseExcelView.getDateOfBirthMd(), PersonView.getDateOfBirthMd());
      map.put(IndividualCaseExcelView.getSexMd(), PersonView.getSexMd());

      map.put(IndividualCaseExcelView.getBirthLocationMd(), PersonView.getBirthLocationMd());
      map.put(IndividualCaseExcelView.getPhysicianIdentifierMd(), PersonView.getIdentifierMd());
      map.put(IndividualCaseExcelView.getPhysicianFirstNameMd(), PersonView.getFirstNameMd());
      map.put(IndividualCaseExcelView.getPhysicianLastNameMd(), PersonView.getLastNameMd());
      map.put(IndividualCaseExcelView.getPhysicianDateOfBirthMd(), PersonView.getDateOfBirthMd());
      map.put(IndividualCaseExcelView.getPhysicianSexMd(), PersonView.getSexMd());

      master = new ODKForm(IndividualCaseExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(IndividualCase.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(IndividualCase.CLASS, IndividualCaseExcelView.customAttributeOrder(), null);
      master.buildAttributes(map, IndividualCaseExcelView.customAttributeOrder());

      ODKForm instance = new ODKForm(IndividualCaseExcelView.CLASS);
      instance.addAttribute(new ODKMultiTermAttribute(instance, IndividualInstance.getSymptomMd(), IndividualInstance.getSymptomMd()));
      instance.buildAttributes(IndividualInstance.CLASS, IndividualCaseExcelView.customAttributeOrder(), null);

      master.join(new RepeatFormJoin(master, instance));
      
      
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(null);
      p.setCurrentDate(null);
      p.setNotification(new IndividualCase(), IndividualCase.SYMPTOMONSET);
      master.addBasicConstraint(master.getAttributeByName(IndividualCase.SYMPTOMONSET), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p.getLocalizedMessage());
      
      RelativeValueProblem p2 = new RelativeValueProblem();
      p2.setNotification(new IndividualCase(), IndividualCase.SYMPTOMONSET);
      p2.setAttributeDisplayLabel(IndividualCase.getSymptomOnsetMd().getDisplayLabel(Session.getCurrentLocale()));
      p2.setRelation(MDSSProperties.getString("Compare_AE"));
      p2.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
      master.addBasicConstraint(master.getAttributeByName(IndividualCaseExcelView.DATEOFBIRTH), ODKAttributeConditionOperation.LESS_THAN_EQUALS, master.getAttributeByName(IndividualCaseExcelView.SYMPTOMONSET), p2.getLocalizedMessage());
      
      CurrentDateProblem p3 = new CurrentDateProblem();
      p3.setGivenDate(null);
      p3.setCurrentDate(null);
      p3.setNotification(new IndividualCase(), IndividualCase.DIAGNOSISDATE);
      master.addBasicConstraint(master.getAttributeByName(IndividualCase.DIAGNOSISDATE), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p3.getLocalizedMessage());
      
      RelativeValueProblem p4 = new RelativeValueProblem();
      p4.setNotification(new IndividualCase(), IndividualCase.DIAGNOSISDATE);
      p4.setAttributeDisplayLabel(IndividualCase.getDiagnosisDateMd().getDisplayLabel(Session.getCurrentLocale()));
      p4.setRelation(MDSSProperties.getString("Compare_AE"));
      p4.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
      master.addBasicConstraint(master.getAttributeByName(IndividualCaseExcelView.DATEOFBIRTH), ODKAttributeConditionOperation.LESS_THAN_EQUALS, master.getAttributeByName(IndividualCaseExcelView.DIAGNOSISDATE), p4.getLocalizedMessage());
      
      CurrentDateProblem p5 = new CurrentDateProblem();
      p5.setGivenDate(null);
      p5.setCurrentDate(null);
      p5.setNotification(new IndividualCase(), IndividualCase.CASEREPORTDATE);
      master.addBasicConstraint(master.getAttributeByName(IndividualCase.CASEREPORTDATE), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p5.getLocalizedMessage());
      
      RelativeValueProblem p6 = new RelativeValueProblem();
      p6.setNotification(new IndividualCase(), IndividualCase.CASEREPORTDATE);
      p6.setAttributeDisplayLabel(IndividualCase.getCaseReportDateMd().getDisplayLabel(Session.getCurrentLocale()));
      p6.setRelation(MDSSProperties.getString("Compare_AE"));
      p6.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
      master.addBasicConstraint(master.getAttributeByName(IndividualCaseExcelView.DATEOFBIRTH), ODKAttributeConditionOperation.LESS_THAN_EQUALS, master.getAttributeByName(IndividualCaseExcelView.CASEREPORTDATE), p6.getLocalizedMessage());
      
      CurrentDateProblem p7 = new CurrentDateProblem();
      p7.setGivenDate(null);
      p7.setCurrentDate(null);
      p7.setNotification(new IndividualCase(), IndividualCase.HEMORRHAGICONSET);
      master.addBasicConstraint(master.getAttributeByName(IndividualCase.HEMORRHAGICONSET), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p7.getLocalizedMessage());
      
      RelativeValueProblem p8 = new RelativeValueProblem();
      p8.setNotification(new IndividualCase(), IndividualCase.HEMORRHAGICONSET);
      p8.setAttributeDisplayLabel(IndividualCase.getHemorrhagicOnsetMd().getDisplayLabel(Session.getCurrentLocale()));
      p8.setRelation(MDSSProperties.getString("Compare_AE"));
      p8.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
      master.addBasicConstraint(master.getAttributeByName(IndividualCaseExcelView.DATEOFBIRTH), ODKAttributeConditionOperation.LESS_THAN_EQUALS, master.getAttributeByName(IndividualCaseExcelView.HEMORRHAGICONSET), p8.getLocalizedMessage());
      
      CurrentDateProblem p9 = new CurrentDateProblem();
      p9.setGivenDate(null);
      p9.setCurrentDate(null);
      p9.setNotification(new IndividualCase(), IndividualCase.PLASMALEAKAGEONSET);
      master.addBasicConstraint(master.getAttributeByName(IndividualCase.PLASMALEAKAGEONSET), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p9.getLocalizedMessage());
      
      RelativeValueProblem p10 = new RelativeValueProblem();
      p10.setNotification(new IndividualCase(), IndividualCase.PLASMALEAKAGEONSET);
      p10.setAttributeDisplayLabel(IndividualCase.getPlasmaLeakageOnsetMd().getDisplayLabel(Session.getCurrentLocale()));
      p10.setRelation(MDSSProperties.getString("Compare_AE"));
      p10.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
      master.addBasicConstraint(master.getAttributeByName(IndividualCaseExcelView.DATEOFBIRTH), ODKAttributeConditionOperation.LESS_THAN_EQUALS, master.getAttributeByName(IndividualCaseExcelView.PLASMALEAKAGEONSET), p10.getLocalizedMessage());
    }
    else if (mobileType.equals(IndividualIPTExcelView.CLASS))
    {
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(IndividualIPTExcelView.getFacilityMd(), IndividualIPT.getFacilityMd());
      map.put(IndividualIPTExcelView.getWorkGeoEntityMd(), Person.getWorkGeoEntityMd());
      map.put(IndividualIPTExcelView.getResidentialLocationMd(), Person.getResidentialGeoEntityMd());
      map.put(IndividualIPTExcelView.getPatientFirstNameMd(), PersonView.getFirstNameMd());
      map.put(IndividualIPTExcelView.getPatientLastNameMd(), PersonView.getLastNameMd());
      map.put(IndividualIPTExcelView.getPatientDOBMd(), PersonView.getDateOfBirthMd());
      map.put(IndividualIPTExcelView.getPatientSexMd(), PersonView.getSexMd());
      map.put(IndividualIPTExcelView.getWorkInformationMd(), PersonView.getWorkInformationMd());

      master = new ODKForm(IndividualIPTExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(IndividualCase.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(IndividualIPTView.CLASS, IndividualIPTExcelView.customAttributeOrder(), null);

      master.removeAttribute(IndividualIPTExcelView.getFacilityMd().definesAttribute());
      master.removeAttribute(IndividualIPTExcelView.getWorkGeoEntityMd().definesAttribute());
      master.buildAttributes(map, AggregatedIPTExcelView.customAttributeOrder());
      
      master.addBasicRelevancy(master.getAttributeByName(IndividualIPTExcelView.NUMBEROFRECEIVEDITNS), master.getAttributeByName(IndividualIPTExcelView.RECEIVEDITN), ODKAttributeConditionOperation.EQUALS, true);
      
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(null);
      p.setCurrentDate(null);
      p.setNotification(new IndividualIPT(), IndividualIPTExcelView.SERVICEDATE);
      master.addBasicConstraint(master.getAttributeByName(IndividualIPTExcelView.SERVICEDATE), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p.getLocalizedMessage());
    }
    else if (mobileType.equals(ITNCommunityExcelView.CLASS))
    {
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(ITNCommunityExcelView.getDistributionLocationMd(), ITNCommunityExcelView.getDistributionLocationMd());
      map.put(ITNCommunityExcelView.getHouseholdAddressMd(), ITNCommunityExcelView.getHouseholdAddressMd());

      master = new ODKForm(ITNCommunityExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(ITNCommunityDistribution.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.addAttribute(new ODKGridAttribute(master, ITNCommunityDistributionView.getDisplayTargetGroupsMd(), ITNCommunityDistributionView.getDisplayTargetGroupsMd(), ITNCommunityTargetGroup.getAmountMd()));
      master.addAttribute(new ODKGridAttribute(master, ITNCommunityDistributionView.getDisplayNetsMd(), ITNCommunityDistributionView.getDisplayNetsMd(), ITNCommunityNet.getAmountMd()));
      master.buildAttributes(ITNCommunityDistributionView.CLASS, ITNCommunityExcelView.customAttributeOrder(), null);

      master.removeAttribute(ITNCommunityExcelView.getDistributionLocationMd().definesAttribute());
      master.removeAttribute(ITNCommunityExcelView.getHouseholdAddressMd().definesAttribute());

      master.buildAttributes(map, ITNCommunityExcelView.customAttributeOrder());
      
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(null);
      p.setCurrentDate(null);
      p.setNotification(new ITNCommunityDistribution(), ITNCommunityExcelView.STARTDATE);
      master.addBasicConstraint(master.getAttributeByName(ITNCommunityExcelView.STARTDATE), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p.getLocalizedMessage());
      
      CurrentDateProblem p2 = new CurrentDateProblem();
      p2.setGivenDate(null);
      p2.setCurrentDate(null);
      p2.setNotification(new ITNCommunityDistribution(), ITNCommunityExcelView.ENDDATE);
      master.addBasicConstraint(master.getAttributeByName(ITNCommunityExcelView.ENDDATE), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p2.getLocalizedMessage());
      
      // TODO : Geo relevancies
//      master.addBasicRelevancy(master.getAttributeByName(ITNCommunityDistributionView.HOUSEHOLDADDRESS), master.getAttributeByName(ITNCommunityDistributionView.ENTRYTYPE), ODKAttributeConditionOperation.EQUALS, true);
//      master.addBasicRelevancy(master.getAttributeByName(ITNCommunityDistributionView.DISTRIBUTIONLOCATION), master.getAttributeByName(ITNCommunityDistributionView.ENTRYTYPE), ODKAttributeConditionOperation.EQUALS, true);
      
      master.addBasicRelevancy(master.getAttributeByName(ITNCommunityDistributionView.HOUSEHOLDNAME), master.getAttributeByName(ITNCommunityDistributionView.ENTRYTYPE), ODKAttributeConditionOperation.EQUALS, true);
      master.addBasicRelevancy(master.getAttributeByName(ITNCommunityDistributionView.HOUSEHOLDSURNAME), master.getAttributeByName(ITNCommunityDistributionView.ENTRYTYPE), ODKAttributeConditionOperation.EQUALS, true);
      master.addBasicRelevancy(master.getAttributeByName(ITNCommunityDistributionView.RESIDENTS), master.getAttributeByName(ITNCommunityDistributionView.ENTRYTYPE), ODKAttributeConditionOperation.EQUALS, true);
      
      master.addBasicRelevancy(master.getAttributeByName(ITNCommunityDistributionView.CURRENCYRECEIVED), master.getAttributeByName(ITNCommunityDistributionView.SOLD), ODKAttributeConditionOperation.EQUALS, true);
      master.addBasicRelevancy(master.getAttributeByName(ITNCommunityDistributionView.NUMBERRETRIEVED), master.getAttributeByName(ITNCommunityDistributionView.RETRIEVED), ODKAttributeConditionOperation.EQUALS, true);
    }
    else if (mobileType.equals(ITNDistributionExcelView.CLASS))
    {
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(ITNDistributionExcelView.getFacilityMd(), ITNDistributionExcelView.getFacilityMd());
      map.put(ITNDistributionExcelView.getRecipientFirstNameMd(), PersonView.getFirstNameMd());
      map.put(ITNDistributionExcelView.getRecipientLastNameMd(), PersonView.getLastNameMd());
      map.put(ITNDistributionExcelView.getRecipientDOBMd(), PersonView.getDateOfBirthMd());
      map.put(ITNDistributionExcelView.getRecipientSexMd(), PersonView.getSexMd());

      master = new ODKForm(ITNDistributionExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(ITNDistribution.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.addAttribute(new ODKGridAttribute(master, ITNDistributionView.getTargetGroupsMd(), ITNDistributionView.getTargetGroupsMd(), ITNDistributionTargetGroup.getAmountMd()));
      master.buildAttributes(ITNDistributionView.CLASS, ITNDistributionExcelView.customAttributeOrder(), null);

      master.removeAttribute(ITNDistributionExcelView.getFacilityMd().definesAttribute());

      master.buildAttributes(map, ITNDistributionExcelView.customAttributeOrder());
      
      master.addBasicRelevancy(master.getAttributeByName(ITNDistributionExcelView.CURRENCYRECEIVED), master.getAttributeByName(ITNDistributionExcelView.NUMBERSOLD), ODKAttributeConditionOperation.GREATER_THAN, 0);
    }
    else if (mobileType.equals(LarvacideExcelView.CLASS))
    {
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(LarvacideExcelView.getTeamLeaderIdMd(), LarvacideExcelView.getTeamLeaderIdMd());

      master = new ODKForm(LarvacideExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(Larvacide.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(Larvacide.CLASS, LarvacideExcelView.customAttributeOrder(), null);
      master.buildAttributes(map, LarvacideExcelView.customAttributeOrder());

      ODKForm subc = new ODKForm(LarvacideExcelView.CLASS);
      subc.buildAttributes(LarvacideInstanceView.CLASS, LarvacideExcelView.customAttributeOrder(), null);

      master.join(new RepeatFormJoin(master, subc));
    }
    else if (mobileType.equals(OperatorSprayExcelView.CLASS))
    {
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(OperatorSprayExcelView.getInsecticideTermMd(), OperatorSprayView.getBrandMd());
      map.put(OperatorSprayExcelView.getSprayTeamMd(), OperatorSprayExcelView.getSprayTeamMd());
      map.put(OperatorSprayExcelView.getOperatorIdMd(), OperatorSprayExcelView.getOperatorIdMd());
      map.put(OperatorSprayExcelView.getLeaderIdMd(), OperatorSprayExcelView.getLeaderIdMd());
      map.put(OperatorSprayExcelView.getSupervisorNameMd(), OperatorSprayExcelView.getSupervisorNameMd());
      map.put(OperatorSprayExcelView.getSupervisorSurnameMd(), OperatorSprayExcelView.getSupervisorSurnameMd());
      map.put(OperatorSprayExcelView.getSupervisorCodeMd(), OperatorSprayExcelView.getSupervisorCodeMd());

      master = new ODKForm(OperatorSprayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(OperatorSpray.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(OperatorSprayView.CLASS, OperatorSprayExcelView.customAttributeOrder(), null);
      master.buildAttributes(map, OperatorSprayExcelView.customAttributeOrder());

      ODKForm individInst = new ODKForm(OperatorSprayExcelView.CLASS);
      individInst.buildAttributes(HouseholdSprayStatusView.CLASS, OperatorSprayExcelView.customAttributeOrder(), null);
      master.join(new RepeatFormJoin(master, individInst));
      
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.BEDNETS), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      
      HouseholdValueProblem p = new HouseholdValueProblem();
      p.setHouseholdId(null);
      p.setStructureId(null);
      p.setNotification(new HouseholdSprayStatus(), HouseholdSprayStatusView.HOUSEHOLDS);
      individInst.addBasicConstraint(individInst.getAttributeByName(HouseholdSprayStatusView.HOUSEHOLDS), ODKAttributeConditionOperation.GREATER_THAN_EQUALS, 0, p.getLocalizedMessage());
      individInst.addBasicConstraint(individInst.getAttributeByName(HouseholdSprayStatusView.HOUSEHOLDS), ODKAttributeConditionOperation.LESS_THAN_EQUALS, 1, "");
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.HOUSEHOLDS), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      
      StructureValueProblem p2 = new StructureValueProblem();
      p2.setHouseholdId(null);
      p2.setStructureId(null);
      p2.setNotification(new HouseholdSprayStatus(), HouseholdSprayStatusView.STRUCTURES);
      individInst.addBasicConstraint(individInst.getAttributeByName(HouseholdSprayStatusView.STRUCTURES), ODKAttributeConditionOperation.GREATER_THAN_EQUALS, 0, p2.getLocalizedMessage());
      individInst.addBasicConstraint(individInst.getAttributeByName(HouseholdSprayStatusView.STRUCTURES), ODKAttributeConditionOperation.LESS_THAN_EQUALS, 1, "");
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.STRUCTURES), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      
      SprayedHouseholdValueProblem p3 = new SprayedHouseholdValueProblem();
      p3.setHouseholdId(null);
      p3.setStructureId(null);
      p3.setNotification(new HouseholdSprayStatus(), HouseholdSprayStatus.SPRAYEDHOUSEHOLDS);
      individInst.addBasicConstraint(individInst.getAttributeByName(HouseholdSprayStatusView.SPRAYEDHOUSEHOLDS), ODKAttributeConditionOperation.GREATER_THAN_EQUALS, 0, p3.getLocalizedMessage());
      individInst.addBasicConstraint(individInst.getAttributeByName(HouseholdSprayStatusView.SPRAYEDHOUSEHOLDS), ODKAttributeConditionOperation.LESS_THAN_EQUALS, 1, "");
      
      SprayedSumProblem p4 = new SprayedSumProblem();
      p4.setNotification(new HouseholdSprayStatus(), HouseholdSprayStatus.SPRAYEDSTRUCTURES);
      p4.setObjectLabel(HouseholdSprayStatus.getStructuresMd().getDisplayLabel(Session.getCurrentLocale()));
      p4.setSprayedObjectLabel(HouseholdSprayStatus.getSprayedStructuresMd().getDisplayLabel(Session.getCurrentLocale()));
      p4.setObjects(null);
      p4.setSprayedObjects(null);
      individInst.addBasicConstraint(individInst.getAttributeByName(HouseholdSprayStatusView.SPRAYEDSTRUCTURES), ODKAttributeConditionOperation.LESS_THAN_EQUALS, individInst.getAttributeByName(HouseholdSprayStatusView.STRUCTURES), p4.getLocalizedMessage());
      
      PrevSprayedHouseholdValueProblem p5 = new PrevSprayedHouseholdValueProblem();
      p5.setNotification(new HouseholdSprayStatus(), HouseholdSprayStatus.PREVSPRAYEDHOUSEHOLDS);
      p5.setHouseholdId(null);
      p5.setStructureId(null);
      individInst.addBasicConstraint(individInst.getAttributeByName(HouseholdSprayStatusView.PREVSPRAYEDHOUSEHOLDS), ODKAttributeConditionOperation.GREATER_THAN_EQUALS, 0, p5.getLocalizedMessage());
      individInst.addBasicConstraint(individInst.getAttributeByName(HouseholdSprayStatusView.PREVSPRAYEDHOUSEHOLDS), ODKAttributeConditionOperation.LESS_THAN_EQUALS, 1, "");
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.PREVSPRAYEDHOUSEHOLDS), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      
      PrevSprayedStructureValueProblem p6 = new PrevSprayedStructureValueProblem();
      p6.setNotification(new HouseholdSprayStatus(), HouseholdSprayStatus.PREVSPRAYEDSTRUCTURES);
      p6.setHouseholdId(null);
      p6.setStructureId(null);
      individInst.addBasicConstraint(individInst.getAttributeByName(HouseholdSprayStatusView.PREVSPRAYEDSTRUCTURES), ODKAttributeConditionOperation.GREATER_THAN_EQUALS, 0, p6.getLocalizedMessage());
      individInst.addBasicConstraint(individInst.getAttributeByName(HouseholdSprayStatusView.PREVSPRAYEDSTRUCTURES), ODKAttributeConditionOperation.LESS_THAN_EQUALS, 1, "");
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.PREVSPRAYEDSTRUCTURES), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.ROOMS), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.VERANDAS), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.CATTLESHEDS), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      
      SprayedSumProblem p7 = new SprayedSumProblem();
      p7.setNotification(new HouseholdSprayStatus(), HouseholdSprayStatus.SPRAYEDROOMS);
      p7.setObjectLabel(HouseholdSprayStatus.getRoomsMd().getDisplayLabel(Session.getCurrentLocale()));
      p7.setSprayedObjectLabel(HouseholdSprayStatus.getSprayedRoomsMd().getDisplayLabel(Session.getCurrentLocale()));
      p7.setObjects(null);
      p7.setSprayedObjects(null);
      individInst.addBasicConstraint(individInst.getAttributeByName(HouseholdSprayStatusView.SPRAYEDROOMS), ODKAttributeConditionOperation.LESS_THAN_EQUALS, individInst.getAttributeByName(HouseholdSprayStatusView.ROOMS), p7.getLocalizedMessage());
      
      SprayedSumProblem p8 = new SprayedSumProblem();
      p8.setNotification(new HouseholdSprayStatus(), HouseholdSprayStatus.VERANDASSPRAYED);
      p8.setObjectLabel(HouseholdSprayStatus.getVerandasMd().getDisplayLabel(Session.getCurrentLocale()));
      p8.setSprayedObjectLabel(HouseholdSprayStatus.getVerandasSprayedMd().getDisplayLabel(Session.getCurrentLocale()));
      p8.setObjects(null);
      p8.setSprayedObjects(null);
      individInst.addBasicConstraint(individInst.getAttributeByName(HouseholdSprayStatusView.VERANDASSPRAYED), ODKAttributeConditionOperation.LESS_THAN_EQUALS, individInst.getAttributeByName(HouseholdSprayStatusView.VERANDAS), p8.getLocalizedMessage());
      
      SprayedSumProblem p9 = new SprayedSumProblem();
      p9.setNotification(new HouseholdSprayStatus(), HouseholdSprayStatus.CATTLESHEDSSPRAYED);
      p9.setObjectLabel(HouseholdSprayStatus.getCattleShedsMd().getDisplayLabel(Session.getCurrentLocale()));
      p9.setSprayedObjectLabel(HouseholdSprayStatus.getCattleShedsSprayedMd().getDisplayLabel(Session.getCurrentLocale()));
      p9.setObjects(null);
      p9.setSprayedObjects(null);
      individInst.addBasicConstraint(individInst.getAttributeByName(HouseholdSprayStatusView.CATTLESHEDSSPRAYED), ODKAttributeConditionOperation.LESS_THAN_EQUALS, individInst.getAttributeByName(HouseholdSprayStatusView.CATTLESHEDS), p9.getLocalizedMessage());
      
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.OTHER), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.VERANDASOTHER), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.CATTLESHEDSOTHER), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.REFUSED), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.VERANDASREFUSED), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.CATTLESHEDSREFUSED), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.LOCKED), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.CATTLESHEDSLOCKED), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.WRONGSURFACE), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.ROOMSWITHBEDNETS), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.BEDNETS), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.NUMBEROFPEOPLE), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(HouseholdSprayStatusView.PEOPLE), master.getAttributeByName(OperatorSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
    }
    else if (mobileType.equals(TeamSprayExcelView.CLASS))
    {
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(TeamSprayExcelView.getInsecticideTermMd(), TeamSprayView.getBrandMd());
      map.put(TeamSprayExcelView.getSprayTeamMd(), TeamSprayExcelView.getSprayTeamMd());
      map.put(TeamSprayExcelView.getLeaderIdMd(), TeamSprayExcelView.getLeaderIdMd());
      map.put(TeamSprayExcelView.getSupervisorNameMd(), TeamSprayExcelView.getSupervisorNameMd());
      map.put(TeamSprayExcelView.getSupervisorSurnameMd(), TeamSprayExcelView.getSupervisorSurnameMd());
      map.put(TeamSprayExcelView.getSupervisorCodeMd(), TeamSprayExcelView.getSupervisorCodeMd());

      master = new ODKForm(TeamSprayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(TeamSpray.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(TeamSprayView.CLASS, TeamSprayExcelView.customAttributeOrder(), null);
      master.buildAttributes(map, TeamSprayExcelView.customAttributeOrder());

      map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(TeamSprayExcelView.getOperatorIdMd(), TeamSprayExcelView.getOperatorIdMd());

      ODKForm individInst = new ODKForm(TeamSprayExcelView.CLASS);
      individInst.buildAttributes(OperatorSprayStatusView.CLASS, TeamSprayExcelView.customAttributeOrder(), null);
      individInst.buildAttributes(map, TeamSprayExcelView.customAttributeOrder());
      master.join(new RepeatFormJoin(master, individInst));
      
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.HOUSEHOLDS), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.PREVSPRAYEDSTRUCTURES), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.STRUCTURES), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.PREVSPRAYEDHOUSEHOLDS), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      
      SprayedSumProblem p1 = new SprayedSumProblem();
      p1.setNotification(new OperatorSprayStatus(), OperatorSprayStatus.SPRAYEDHOUSEHOLDS);
      p1.setObjectLabel(OperatorSprayStatus.getHouseholdsMd().getDisplayLabel(Session.getCurrentLocale()));
      p1.setSprayedObjectLabel(OperatorSprayStatus.getSprayedHouseholdsMd().getDisplayLabel(Session.getCurrentLocale()));
      p1.setObjects(null);
      p1.setSprayedObjects(null);
      individInst.addBasicConstraint(individInst.getAttributeByName(TeamSprayExcelView.SPRAYEDHOUSEHOLDS), ODKAttributeConditionOperation.LESS_THAN_EQUALS, individInst.getAttributeByName(TeamSprayExcelView.HOUSEHOLDS), p1.getLocalizedMessage());
      
      SprayedSumProblem p2 = new SprayedSumProblem();
      p2.setNotification(new OperatorSprayStatus(), OperatorSprayStatus.SPRAYEDSTRUCTURES);
      p2.setObjectLabel(OperatorSprayStatus.getStructuresMd().getDisplayLabel(Session.getCurrentLocale()));
      p2.setSprayedObjectLabel(OperatorSprayStatus.getSprayedStructuresMd().getDisplayLabel(Session.getCurrentLocale()));
      p2.setObjects(null);
      p2.setSprayedObjects(null);
      individInst.addBasicConstraint(individInst.getAttributeByName(TeamSprayExcelView.SPRAYEDSTRUCTURES), ODKAttributeConditionOperation.LESS_THAN_EQUALS, individInst.getAttributeByName(TeamSprayExcelView.STRUCTURES), p2.getLocalizedMessage());
      
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.ROOMS), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.VERANDAS), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.CATTLESHEDS), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      
      SprayedSumProblem p3 = new SprayedSumProblem();
      p3.setNotification(new OperatorSprayStatus(), OperatorSprayStatus.VERANDASSPRAYED);
      p3.setObjectLabel(OperatorSprayStatus.getVerandasMd().getDisplayLabel(Session.getCurrentLocale()));
      p3.setSprayedObjectLabel(OperatorSprayStatus.getVerandasSprayedMd().getDisplayLabel(Session.getCurrentLocale()));
      p3.setObjects(null);
      p3.setSprayedObjects(null);
      individInst.addBasicConstraint(individInst.getAttributeByName(TeamSprayExcelView.VERANDASSPRAYED), ODKAttributeConditionOperation.LESS_THAN_EQUALS, individInst.getAttributeByName(TeamSprayExcelView.VERANDAS), p3.getLocalizedMessage());
      
      SprayedSumProblem p4 = new SprayedSumProblem();
      p4.setNotification(new OperatorSprayStatus(), OperatorSprayStatus.CATTLESHEDSSPRAYED);
      p4.setObjectLabel(OperatorSprayStatus.getCattleShedsMd().getDisplayLabel(Session.getCurrentLocale()));
      p4.setSprayedObjectLabel(OperatorSprayStatus.getCattleShedsSprayedMd().getDisplayLabel(Session.getCurrentLocale()));
      p4.setObjects(null);
      p4.setSprayedObjects(null);
      individInst.addBasicConstraint(individInst.getAttributeByName(TeamSprayExcelView.CATTLESHEDSSPRAYED), ODKAttributeConditionOperation.LESS_THAN_EQUALS, individInst.getAttributeByName(TeamSprayExcelView.CATTLESHEDS), p4.getLocalizedMessage());
      
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.OTHER), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.VERANDASOTHER), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.CATTLESHEDSOTHER), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.REFUSED), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.VERANDASREFUSED), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.CATTLESHEDSREFUSED), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.LOCKED), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.VERANDASLOCKED), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.CATTLESHEDSLOCKED), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.WRONGSURFACE), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.ROOMSWITHBEDNETS), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.BEDNETS), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.NUMBEROFPEOPLE), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(OperatorSprayStatusView.PEOPLE), master.getAttributeByName(TeamSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
    }
    else if (mobileType.equals(ZoneSprayExcelView.CLASS))
    {
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(ZoneSprayExcelView.getInsecticideTermMd(), ZoneSprayView.getBrandMd());
      map.put(ZoneSprayExcelView.getSupervisorNameMd(), ZoneSprayExcelView.getSupervisorNameMd());
      map.put(ZoneSprayExcelView.getSupervisorSurnameMd(), ZoneSprayExcelView.getSupervisorSurnameMd());
      map.put(ZoneSprayExcelView.getSupervisorCodeMd(), ZoneSprayExcelView.getSupervisorCodeMd());

      master = new ODKForm(ZoneSprayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(ZoneSpray.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(ZoneSprayView.CLASS, ZoneSprayExcelView.customAttributeOrder(), null);
      master.buildAttributes(map, ZoneSprayExcelView.customAttributeOrder());

      map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(ZoneSprayExcelView.getSprayTeamMd(), ZoneSprayExcelView.getSprayTeamMd());
      map.put(ZoneSprayExcelView.getLeaderIdMd(), ZoneSprayExcelView.getLeaderIdMd());
      map.put(ZoneSprayExcelView.getTeamTargetMd(), TeamSprayStatusView.getTargetMd());
      map.put(ZoneSprayExcelView.getTeamReceivedMd(), TeamSprayStatusView.getReceivedMd());
      map.put(ZoneSprayExcelView.getTeamRefillsMd(), TeamSprayStatusView.getRefillsMd());
      map.put(ZoneSprayExcelView.getTeamReturnedMd(), TeamSprayStatusView.getReturnedMd());
      map.put(ZoneSprayExcelView.getTeamUsedMd(), TeamSprayStatusView.getUsedMd());

      ODKForm individInst = new ODKForm(ZoneSprayExcelView.CLASS);
      individInst.buildAttributes(TeamSprayStatusView.CLASS, ZoneSprayExcelView.customAttributeOrder(), null);
      individInst.buildAttributes(map, ZoneSprayExcelView.customAttributeOrder());

      master.join(new RepeatFormJoin(master, individInst));
      
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.HOUSEHOLDS), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.PREVSPRAYEDSTRUCTURES), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.STRUCTURES), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.PREVSPRAYEDHOUSEHOLDS), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.ROOMS), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.VERANDAS), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.CATTLESHEDS), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      
      SprayedSumProblem p1 = new SprayedSumProblem();
      p1.setNotification(new TeamSprayStatus(), TeamSprayStatus.SPRAYEDROOMS);
      p1.setObjectLabel(TeamSprayStatus.getRoomsMd().getDisplayLabel(Session.getCurrentLocale()));
      p1.setSprayedObjectLabel(TeamSprayStatus.getSprayedRoomsMd().getDisplayLabel(Session.getCurrentLocale()));
      p1.setObjects(null);
      p1.setSprayedObjects(null);
      individInst.addBasicConstraint(individInst.getAttributeByName(ZoneSprayExcelView.SPRAYEDROOMS), ODKAttributeConditionOperation.LESS_THAN_EQUALS, individInst.getAttributeByName(ZoneSprayExcelView.ROOMS), p1.getLocalizedMessage());
      
      SprayedSumProblem p2 = new SprayedSumProblem();
      p2.setNotification(new TeamSprayStatus(), TeamSprayStatus.VERANDASSPRAYED);
      p2.setObjectLabel(TeamSprayStatus.getVerandasMd().getDisplayLabel(Session.getCurrentLocale()));
      p2.setSprayedObjectLabel(TeamSprayStatus.getVerandasSprayedMd().getDisplayLabel(Session.getCurrentLocale()));
      p2.setObjects(null);
      p2.setSprayedObjects(null);
      individInst.addBasicConstraint(individInst.getAttributeByName(ZoneSprayExcelView.VERANDASSPRAYED), ODKAttributeConditionOperation.LESS_THAN_EQUALS, individInst.getAttributeByName(ZoneSprayExcelView.VERANDAS), p2.getLocalizedMessage());
      
      SprayedSumProblem p3 = new SprayedSumProblem();
      p3.setNotification(new TeamSprayStatus(), TeamSprayStatus.CATTLESHEDSSPRAYED);
      p3.setObjectLabel(TeamSprayStatus.getCattleShedsMd().getDisplayLabel(Session.getCurrentLocale()));
      p3.setSprayedObjectLabel(TeamSprayStatus.getCattleShedsSprayedMd().getDisplayLabel(Session.getCurrentLocale()));
      p3.setObjects(null);
      p3.setSprayedObjects(null);
      individInst.addBasicConstraint(individInst.getAttributeByName(ZoneSprayExcelView.CATTLESHEDSSPRAYED), ODKAttributeConditionOperation.LESS_THAN_EQUALS, individInst.getAttributeByName(ZoneSprayExcelView.CATTLESHEDS), p3.getLocalizedMessage());
      
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.OTHER), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.VERANDASOTHER), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.CATTLESHEDSOTHER), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.REFUSED), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.VERANDASREFUSED), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.CATTLESHEDSREFUSED), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.LOCKED), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.VERANDASLOCKED), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.CATTLESHEDSLOCKED), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.WRONGSURFACE), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      
      SprayedSumProblem p4 = new SprayedSumProblem();
      p4.setNotification(new TeamSprayStatus(), TeamSprayStatus.SPRAYEDHOUSEHOLDS);
      p4.setObjectLabel(TeamSprayStatus.getHouseholdsMd().getDisplayLabel(Session.getCurrentLocale()));
      p4.setSprayedObjectLabel(TeamSprayStatus.getSprayedHouseholdsMd().getDisplayLabel(Session.getCurrentLocale()));
      p4.setObjects(null);
      p4.setSprayedObjects(null);
      individInst.addBasicConstraint(individInst.getAttributeByName(ZoneSprayExcelView.SPRAYEDHOUSEHOLDS), ODKAttributeConditionOperation.LESS_THAN_EQUALS, individInst.getAttributeByName(ZoneSprayExcelView.HOUSEHOLDS), p4.getLocalizedMessage());
      
      SprayedSumProblem p5 = new SprayedSumProblem();
      p5.setNotification(new TeamSprayStatus(), TeamSprayStatus.SPRAYEDSTRUCTURES);
      p5.setObjectLabel(TeamSprayStatus.getStructuresMd().getDisplayLabel(Session.getCurrentLocale()));
      p5.setSprayedObjectLabel(TeamSprayStatus.getSprayedStructuresMd().getDisplayLabel(Session.getCurrentLocale()));
      p5.setObjects(null);
      p5.setSprayedObjects(null);
      individInst.addBasicConstraint(individInst.getAttributeByName(ZoneSprayExcelView.SPRAYEDSTRUCTURES), ODKAttributeConditionOperation.LESS_THAN_EQUALS, individInst.getAttributeByName(ZoneSprayExcelView.STRUCTURES), p5.getLocalizedMessage());
      
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.ROOMSWITHBEDNETS), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.NUMBEROFPEOPLE), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
      individInst.addBasicRelevancy(individInst.getAttributeByName(ZoneSprayExcelView.PEOPLE), master.getAttributeByName(ZoneSprayExcelView.SPRAYMETHOD), ODKAttributeConditionOperation.NOT_EQUALS, SprayMethod.MOP_UP);
    }
    else if (mobileType.equals(PersonExcelView.CLASS))
    {
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(PersonExcelView.getDiseaseMd(), PersonExcelView.getDiseaseMd());
      map.put(PersonExcelView.getSupervisorCodeMd(), PersonView.getCodeMd());

      master = new ODKForm(PersonExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(Person.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(PersonView.CLASS, PersonExcelView.customAttributeOrder(), null);
      master.buildAttributes(map, PersonExcelView.customAttributeOrder());
      
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(null);
      p.setCurrentDate(null);
      p.setNotification(new Person(), PersonExcelView.DATEOFBIRTH);
      master.addBasicConstraint(master.getAttributeByName(PersonExcelView.DATEOFBIRTH), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p.getLocalizedMessage());
    }
    else if (mobileType.equals(PopulationDataExcelView.CLASS))
    {
      master = new ODKForm(PopulationDataExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(PopulationData.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(PopulationDataView.CLASS, PopulationDataExcelView.customAttributeOrder(), null);
      master.addAttribute(PopulationData.getGeoEntityMd(), PopulationDataExcelView.getGeoEntityMd());
      
      PopulationProblem p = new PopulationProblem();
      p.setNotification(new PopulationData(), PopulationDataExcelView.POPULATION);
      master.addBasicConstraint(master.getAttributeByName(PopulationDataExcelView.POPULATION), ODKAttributeConditionOperation.GREATER_THAN_EQUALS, 0, p.getLocalizedMessage());
      
      Calendar calendar = Calendar.getInstance();
      int max = calendar.get(Calendar.YEAR);
      int min = calendar.getActualMinimum(Calendar.YEAR);
      String msg = "The year must be between [" + min + "] and [" + max + "]";
      RangeValueProblem p2 = new RangeValueProblem(msg);
      p2.setNotification(new PopulationData(), PopulationData.YEAROFDATA);
      p2.setLowerLimit(min);
      p2.setUpperLimit(max);
      p2.setInvalidValue(null);
      master.addBasicConstraint(master.getAttributeByName(PopulationDataExcelView.YEAROFDATA), ODKAttributeConditionOperation.LESS_THAN_EQUALS, max, msg);
      master.addBasicConstraint(master.getAttributeByName(PopulationDataExcelView.YEAROFDATA), ODKAttributeConditionOperation.GREATER_THAN_EQUALS, min, msg);
    }
    else if (mobileType.equals(PupalCollectionExcelView.CLASS))
    {
      master = new ODKForm(PupalCollectionExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(PupalCollection.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(PupalCollectionView.CLASS, PupalCollectionExcelView.customAttributeOrder(), null);

      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(null);
      p.setCurrentDate(null);
      p.setNotification(new PupalCollection(), PupalCollectionExcelView.STARTDATE);
      master.addBasicConstraint(master.getAttributeByName(PupalCollectionExcelView.STARTDATE), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p.getLocalizedMessage());
      
      CurrentDateProblem p2 = new CurrentDateProblem();
      p2.setGivenDate(null);
      p2.setCurrentDate(null);
      p2.setNotification(new PupalCollection(), PupalCollectionExcelView.ENDDATE);
      master.addBasicConstraint(master.getAttributeByName(PupalCollectionExcelView.ENDDATE), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p2.getLocalizedMessage());
      
      ODKForm container = new ODKForm(PupalCollectionExcelView.CLASS);
      container.addAttribute(new ODKGridAttribute(container, PupalContainerView.getPupaeAmountMd(), PupalContainerView.getPupaeAmountMd(), PupalContainerAmountView.getAmountMd()));
      container.buildAttributes(PupalContainerView.CLASS, PupalCollectionExcelView.customAttributeOrder(), null);

      container.addBasicRelevancy(container.getAttributeByName(PupalContainerView.HEIGHT), container.getAttributeByName(PupalContainerView.SHAPE), ODKAttributeConditionOperation.NOT_EQUALS, null);
      container.addBasicRelevancy(container.getAttributeByName(PupalContainerView.WIDTH), container.getAttributeByName(PupalContainerView.SHAPE), ODKAttributeConditionOperation.EQUALS, ContainerShape.RECTANGLE);
      container.addBasicRelevancy(container.getAttributeByName(PupalContainerView.CONTAINERLENGTH), container.getAttributeByName(PupalContainerView.SHAPE), ODKAttributeConditionOperation.EQUALS, ContainerShape.RECTANGLE);
      container.addBasicRelevancy(container.getAttributeByName(PupalContainerView.OPENINGWIDTH), container.getAttributeByName(PupalContainerView.SHAPE), ODKAttributeConditionOperation.EQUALS, ContainerShape.RECTANGLE);
      container.addBasicRelevancy(container.getAttributeByName(PupalContainerView.OPENINGLENGTH), container.getAttributeByName(PupalContainerView.SHAPE), ODKAttributeConditionOperation.EQUALS, ContainerShape.RECTANGLE);
      container.addBasicRelevancy(container.getAttributeByName(PupalContainerView.DIAMETER), container.getAttributeByName(PupalContainerView.SHAPE), ODKAttributeConditionOperation.EQUALS, ContainerShape.CIRCLE);
      container.addBasicRelevancy(container.getAttributeByName(PupalContainerView.OPENINGDIAMETER), container.getAttributeByName(PupalContainerView.SHAPE), ODKAttributeConditionOperation.EQUALS, ContainerShape.CIRCLE);

      RangeValueProblem p3 = new RangeValueProblem();
      p3.setNotification(new PupalContainer(), PupalContainer.DRAWDOWNPERCENT);
      p3.setAttributeDisplayLabel(PupalContainer.getDrawdownPercentMd().getDisplayLabel(Session.getCurrentLocale()));
      p3.setLowerLimit(0);
      p3.setUpperLimit(100);
      container.addBasicConstraint(container.getAttributeByName(PupalContainer.DRAWDOWNPERCENT), ODKAttributeConditionOperation.GREATER_THAN_EQUALS, 0, p3.getLocalizedMessage());
      container.addBasicConstraint(container.getAttributeByName(PupalContainer.DRAWDOWNPERCENT), ODKAttributeConditionOperation.LESS_THAN_EQUALS, 100, p3.getLocalizedMessage());

      master.join(new RepeatFormJoin(master, container));
    }
    else if (mobileType.equals(SurveyExcelView.CLASS))
    {
      /*
       * Probably needs feedback from Miguel, the CRUD screen follows the merge
       * survey layout: survey point -> survey household -> survey person / bed
       * net
       */
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(SurveyExcelView.getGeoEntityMd(), SurveyExcelView.getGeoEntityMd());
      map.put(SurveyExcelView.getWallSurfaceMd(), HouseholdView.getWallMd());
      map.put(SurveyExcelView.getRoofSurfaceMd(), HouseholdView.getRoofMd());
      map.put(SurveyExcelView.getSleptUnderNetIdMd(), SurveyExcelView.getSleptUnderNetIdMd());

      master = new ODKForm(SurveyExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(FormSurvey.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(SurveyPointView.CLASS, SurveyExcelView.customAttributeOrder(), null);
      master.buildAttributes(HouseholdView.CLASS, SurveyExcelView.customAttributeOrder(), null);
      master.buildAttributes(ITNInstanceView.CLASS, SurveyExcelView.customAttributeOrder(), null);
      master.buildAttributes(SurveyedPersonView.CLASS, SurveyExcelView.customAttributeOrder(), null);
      master.buildAttributes(map, SurveyExcelView.customAttributeOrder());
      master.addAttribute(new ODKMultiTermAttribute(master, SurveyedPersonView.getDisplayLocationsMd(), SurveyedPersonView.getDisplayLocationsMd()));
      master.addAttribute(new ODKMultiTermAttribute(master, SurveyedPersonView.getDisplayTreatmentsMd(), SurveyedPersonView.getDisplayTreatmentsMd()));
    }
    else if (mobileType.equals(ThresholdDataExcelView.CLASS))
    {
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(ThresholdDataExcelView.getSeasonNameMd(), ThresholdDataExcelView.getSeasonNameMd());
      map.put(ThresholdDataExcelView.getGeoEntityMd(), ThresholdData.getGeoEntityMd());

      master = new ODKForm(ThresholdDataExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(ThresholdData.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(ThresholdDataView.CLASS, ThresholdDataExcelView.customAttributeOrder(), null);
      master.removeAttribute(ThresholdDataExcelView.getGeoEntityMd().definesAttribute());
      master.buildAttributes(map, ThresholdDataExcelView.customAttributeOrder());
    }
    else if (mobileType.equals(ImmatureCollectionExcelView.CLASS))
    {
      master = new ODKForm(ImmatureCollectionExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(ImmatureCollection.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(ImmatureCollectionView.CLASS, ImmatureCollectionExcelView.customAttributeOrder(), null);

      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(null);
      p.setCurrentDate(null);
      p.setNotification(new ImmatureCollection(), ImmatureCollectionExcelView.STARTDATE);
      master.addBasicConstraint(master.getAttributeByName(ImmatureCollectionExcelView.STARTDATE), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p.getLocalizedMessage());
      
      CurrentDateProblem p2 = new CurrentDateProblem();
      p2.setGivenDate(null);
      p2.setCurrentDate(null);
      p2.setNotification(new ImmatureCollection(), ImmatureCollectionExcelView.ENDDATE);
      master.addBasicConstraint(master.getAttributeByName(ImmatureCollectionExcelView.ENDDATE), ODKAttributeConditionOperation.LESS_THAN_EQUALS, "today()", p2.getLocalizedMessage());
      
      Map<MdAttributeDAOIF, MdAttributeDAOIF> map = new HashMap<MdAttributeDAOIF, MdAttributeDAOIF>();
      map.put(ImmatureCollectionExcelView.getContainerTermMd(), ImmatureCollectionView.getContainerGridMd());

      ODKForm container = new ODKForm(ImmatureCollectionExcelView.CLASS);
      container.buildAttributes(CollectionContainerView.CLASS, ImmatureCollectionExcelView.customAttributeOrder(), null);
      container.buildAttributes(map, ImmatureCollectionExcelView.customAttributeOrder());

      master.join(new RepeatFormJoin(master, container));
    }
    else if (mobileType.equals(SprayTeamExcelView.CLASS))
    {
      master = new ODKForm(SprayTeamExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(SprayTeam.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.addAttribute(SprayTeamExcelView.getOperatorIdMd(), SprayTeamExcelView.getOperatorIdMd());
      master.addAttribute(SprayTeamExcelView.getTeamIdMd(), SprayTeamExcelView.getTeamIdMd());
      master.addAttribute(SprayTeamExcelView.getTeamLeaderIdMd(), SprayTeamExcelView.getTeamLeaderIdMd());
      master.addAttribute(SprayTeamExcelView.getSprayZoneMd(), SprayTeamExcelView.getSprayZoneMd());
    }
    else if (mobileType.equals(DiagnosticAssayExcelView.CLASS))
    {
      master = new ODKForm(DiagnosticAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(DiagnosticAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.addAttribute(DiagnosticAssayExcelView.getCollectionIdMd(), DiagnosticAssayExcelView.getCollectionIdMd());
      master.buildAttributes(DiagnosticAssayView.CLASS, DiagnosticAssayExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(AdultDiscriminatingDoseAssayExcelView.CLASS))
    {
      master = new ODKForm(AdultDiscriminatingDoseAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(AdultDiscriminatingDoseAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(AdultDiscriminatingDoseAssayExcelView.CLASS, AdultDiscriminatingDoseAssayExcelView.customAttributeOrder(), null);
      master.buildAttributes(AdultDiscriminatingDoseAssayView.CLASS, AdultDiscriminatingDoseAssayExcelView.customAttributeOrder(), null);
      master.removeAttribute(AdultDiscriminatingDoseAssayExcelView.INTERVALTIME);
      master.removeAttribute(AdultDiscriminatingDoseAssayExcelView.AMOUNT);

      ODKForm interval = new ODKForm(AdultDiscriminatingDoseAssayExcelView.CLASS);
      interval.buildAttributes(AdultDiscriminatingDoseInterval.CLASS, AdultDiscriminatingDoseAssayExcelView.customAttributeOrder(), null);

      master.join(new RepeatFormJoin(master, interval));
      
      InvalidDeadQuantityProblem p = new InvalidDeadQuantityProblem();
      p.setQuantityDead(null);
      p.setQuantityTested(null);
      p.setNotification(new AdultDiscriminatingDoseAssay(), AdultDiscriminatingDoseAssay.QUANTITYDEAD);
      master.addBasicConstraint(master.getAttributeByName(AdultDiscriminatingDoseAssayExcelView.QUANTITYDEAD), ODKAttributeConditionOperation.LESS_THAN_EQUALS, master.getAttributeByName(AdultDiscriminatingDoseAssayExcelView.QUANTITYTESTED), p.getLocalizedMessage());
    }
    else if (mobileType.equals(BiochemicalAssayExcelView.CLASS))
    {
      master = new ODKForm(BiochemicalAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(BiochemicalAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(BiochemicalAssayExcelView.CLASS, BiochemicalAssayExcelView.customAttributeOrder(), null);
      master.buildAttributes(BiochemicalAssayView.CLASS, BiochemicalAssayExcelView.customAttributeOrder(), null);
      
      String msg = getLTEMsg(BiochemicalAssay.getNumberElevatedMd(), BiochemicalAssay.getNumberTestedMd());
      master.addBasicConstraint(master.getAttributeByName(BiochemicalAssay.NUMBERELEVATED), ODKAttributeConditionOperation.LESS_THAN_EQUALS, master.getAttributeByName(BiochemicalAssay.NUMBERTESTED), msg);
    }
    else if (mobileType.equals(InfectionAssayExcelView.CLASS))
    {
      master = new ODKForm(InfectionAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(InfectionAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(InfectionAssayExcelView.CLASS, InfectionAssayExcelView.customAttributeOrder(), null);
      master.buildAttributes(InfectionAssayView.CLASS, InfectionAssayExcelView.customAttributeOrder(), null);
      
      String msg = getLTEMsg(InfectionAssayExcelView.getNumberPositiveMd(), InfectionAssayExcelView.getNumberTestedMd());
      master.addBasicConstraint(master.getAttributeByName(InfectionAssayExcelView.NUMBERPOSITIVE), ODKAttributeConditionOperation.LESS_THAN_EQUALS, master.getAttributeByName(InfectionAssayExcelView.NUMBERTESTED), msg);
    }
    else if (mobileType.equals(KnockDownAssayExcelView.CLASS))
    {
      master = new ODKForm(KnockDownAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(KnockDownAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(KnockDownAssayExcelView.CLASS, KnockDownAssayExcelView.customAttributeOrder(), null);
      master.addAttribute(KnockDownAssay.getGenerationMd(), KnockDownAssayExcelView.getGenerationMd());
      master.addAttribute(Insecticide.getActiveIngredientMd(), KnockDownAssayExcelView.getInsecticideActiveIngredientMd());
      master.addAttribute(Insecticide.getUnitsMd(), KnockDownAssayExcelView.getInsecticideUnitsMd());
      master.buildAttributes(KnockDownAssayView.CLASS, KnockDownAssayExcelView.customAttributeOrder(), null);
      master.removeAttribute(KnockDownAssayExcelView.INTERVALTIME);
      master.removeAttribute(KnockDownAssayExcelView.AMOUNT);

      ODKForm interval = new ODKForm(KnockDownAssayExcelView.CLASS);
      interval.buildAttributes(KnockDownInterval.CLASS, KnockDownAssayExcelView.customAttributeOrder(), null);

      master.join(new RepeatFormJoin(master, interval));
    }
    else if (mobileType.equals(LarvaeDiscriminatingDoseAssayExcelView.CLASS))
    {
      master = new ODKForm(LarvaeDiscriminatingDoseAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(LarvaeDiscriminatingDoseAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(LarvaeDiscriminatingDoseAssayExcelView.CLASS, LarvaeDiscriminatingDoseAssayExcelView.customAttributeOrder(), null);
      master.addAttribute(LarvaeDiscriminatingDoseAssay.getGenerationMd(), LarvaeDiscriminatingDoseAssayExcelView.getGenerationMd());
      master.addAttribute(Insecticide.getActiveIngredientMd(), LarvaeDiscriminatingDoseAssayExcelView.getInsecticideActiveIngredientMd());
      master.addAttribute(Insecticide.getUnitsMd(), LarvaeDiscriminatingDoseAssayExcelView.getInsecticideUnitsMd());
      master.buildAttributes(LarvaeDiscriminatingDoseAssayView.CLASS, LarvaeDiscriminatingDoseAssayExcelView.customAttributeOrder(), null);
      
      ControlMortalityException cme = new ControlMortalityException();
      master.addBasicConstraint(master.getAttributeByName(LarvaeDiscriminatingDoseAssayExcelView.CONTROLTESTMORTALITY), ODKAttributeConditionOperation.LESS_THAN_EQUALS, 20, cme.getLocalizedMessage());
      
      InvalidDeadQuantityProblem idq = new InvalidDeadQuantityProblem();
      idq.setQuantityDead(null);
      idq.setQuantityTested(null);
      idq.setNotification(new LarvaeDiscriminatingDoseAssay(), LarvaeDiscriminatingDoseAssayExcelView.QUANTITYDEAD);
      master.addBasicConstraint(master.getAttributeByName(LarvaeDiscriminatingDoseAssayExcelView.QUANTITYDEAD), ODKAttributeConditionOperation.LESS_THAN_EQUALS, master.getAttributeByName(LarvaeDiscriminatingDoseAssayExcelView.QUANTITYTESTED), idq.getLocalizedMessage());
    }
    else if (mobileType.equals(MolecularAssayExcelView.CLASS))
    {
      master = new ODKForm(MolecularAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(MolecularAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(MolecularAssayExcelView.CLASS, MolecularAssayExcelView.customAttributeOrder(), null);
      master.buildAttributes(MolecularAssayView.CLASS, MolecularAssayExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(PooledInfectionAssayExcelView.CLASS))
    {
      master = new ODKForm(PooledInfectionAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(PooledInfectionAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(PooledInfectionAssayExcelView.CLASS, PooledInfectionAssayExcelView.customAttributeOrder(), null);
      master.buildAttributes(PooledInfectionAssayView.CLASS, PooledInfectionAssayExcelView.customAttributeOrder(), null);
      
      String msg = getLTEMsg(PooledInfectionAssayExcelView.getNumberPositiveMd(), PooledInfectionAssayExcelView.getPoolsTestedMd());
      master.addBasicConstraint(master.getAttributeByName(PooledInfectionAssayExcelView.NUMBERPOSITIVE), ODKAttributeConditionOperation.LESS_THAN_EQUALS, master.getAttributeByName(PooledInfectionAssayExcelView.POOLSTESTED), msg);
    }
    else if (mobileType.equals(TimeResponseAssayExcelView.CLASS))
    {
      master = new ODKForm(TimeResponseAssayExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(TimeResponseAssay.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(TimeResponseAssayExcelView.CLASS, TimeResponseAssayExcelView.customAttributeOrder(), null);
      master.buildAttributes(TimeResponseAssayView.CLASS, TimeResponseAssayExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(GeoTargetExcelView.CLASS))
    {
      master = new ODKForm(GeoTargetExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(GeoTarget.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(GeoTargetExcelView.CLASS, GeoTargetExcelView.customAttributeOrder(), null);
      master.buildAttributes(GeoTargetView.CLASS, GeoTargetExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(ResourceTargetExcelView.CLASS))
    {
      master = new ODKForm(ResourceTargetExcelView.CLASS, gfc);
      master.setFormTitle(MdClassDAO.getMdClassDAO(ResourceTarget.CLASS).getDisplayLabel(Session.getCurrentLocale()));
      master.buildAttributes(ResourceTargetExcelView.CLASS, ResourceTargetExcelView.customAttributeOrder(), null);
      master.buildAttributes(ResourceTargetView.CLASS, ResourceTargetExcelView.customAttributeOrder(), null);
    }
    else if (mobileType.equals(FormSurvey.CLASS))
    {
      master = createODKFromForm(FormSurvey.CLASS);

      ODKForm household = createODKFromForm(FormHousehold.CLASS);
      household.removeAttribute("survey");
      master.join(new RepeatFormJoin(master, household, true));

      ODKForm net = createODKFromForm(FormBedNet.CLASS);
      net.removeAttribute("survey");
      net.removeAttribute("household");
      household.join(new RepeatFormJoin(household, net, true));

      ODKForm person = createODKFromForm(FormPerson.CLASS);
      person.removeAttribute("survey");
      person.removeAttribute("household");
      ODKAttribute attribute = person.getAttributeByName(FormPerson.NET);
      attribute.setIsOverride(true);

      household.join(new RepeatFormJoin(household, person, true));
    }
    else if (mobileType.startsWith(MDSSInfo.GENERATED_FORM_BUSINESS_PACKAGE))
    {
      master = createODKFromForm(mobileType);
    }
    else
    {
      throw new UnsupportedOperationException("Unable to construct an ODK form of type [" + mobileType + "]. The type is unsupported.");
    }

    return master;
  }
  
  private static String getLTEMsg(MdAttributeDAOIF lessAttr, MdAttributeDAOIF highAttr)
  {
    String msg = LocalizationFacade.getFromBundles("odk_constraint_number_less_than_or_equal");
    msg = msg.replace("{0}", lessAttr.getDisplayLabel(Session.getCurrentLocale()));
    msg = msg.replace("{1}", highAttr.getDisplayLabel(Session.getCurrentLocale()));
    return msg;
  }

  public static ODKForm createODKFromForm(String mobileType)
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

    GeoFilterCriteria gfc = new GeoFilterCriteria(ghl);

    ODKForm master = new ODKForm(mobileType, gfc);

    List<? extends MdFieldDAOIF> mdFields = mdForm.getOrderedMdFields();

    for (MdFieldDAOIF mdField : mdFields)
    {
      if ( ( mdField instanceof MdWebMultipleTermDAOIF ))
      {
        MdAttributeDAOIF mdAttribute = ( (MdWebMultipleTermDAOIF) mdField ).getDefiningMdAttribute();

        master.addAttribute(new ODKMultiTermAttribute(master, mdAttribute, mdAttribute));
      }
      else if ( ( mdField instanceof MdWebSingleTermGridDAOIF ))
      {
        String typeName = DDMSFieldBuilders.getTermRelationshipTypeName(mdField);
        MdTreeDAOIF mdTree = MdTreeDAO.getMdTreeDAO(MDSSInfo.GENERATED_FORM_TREE_PACKAGE + "." + typeName);
        MdAttributeDAOIF mdAttribute = ( (MdWebSingleTermGridDAOIF) mdField ).getDefiningMdAttribute();

        MdWebPrimitive[] fields = MdFormUtil.getCompositeFields(mdField.getId());

        ODKForm grid = new ODKForm(mdTree.definesType());
        grid.addAttribute(new ODKCompositeGridAttribute(grid, mdAttribute, mdAttribute, fields));

        master.join(new GroupFormJoin(master, grid, true));
      }
      else if (mdField instanceof MdWebAttributeDAO)
      {
        MdWebAttributeDAO dao = ( (MdWebAttributeDAO) mdField );

        ODKAttribute attribute = master.addAttribute(dao.getDefiningMdAttribute(), dao.getDefiningMdAttribute());

        if (attribute.getAttributeName().equals(MdFormUtil.OID))
        {
          if (mobileType.equals(FormSurvey.CLASS))
          {
            attribute.setCopyAttribute(FormHousehold.SURVEY);
          }
          else if (mobileType.equals(FormHousehold.CLASS))
          {
            attribute.setCopyAttribute(FormPerson.HOUSEHOLD);
          }
          else
          {
            attribute.setCopyAttribute(GridExcelAdapter.PARENT_COLUMN_NAME);
          }
        }
      }
    }

    for (MdFieldDAOIF mdField : mdFields)
    {
      if (mdField instanceof MdWebAttributeDAO)
      {
        MdWebAttributeDAO dao = ( (MdWebAttributeDAO) mdField );

        ODKAttribute odkAttr = master.getAttributeByName(dao.getDefiningMdAttribute().definesAttribute());

        if (odkAttr != null)
        {
          List<FieldConditionDAOIF> conditions = mdField.getConditions();

          for (FieldConditionDAOIF condition : conditions)
          {
            ODKAttributeRelevancy loopCond = ODKAttributeRelevancy.factory(condition, odkAttr, master);

            odkAttr.addRelevancy(loopCond);
          }
        }
      }
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

  public boolean hasGeoAttribute()
  {
    for (ODKAttribute attr : this.attrs)
    {
      if (attr instanceof ODKGeoAttribute)
      {
        return true;
      }
    }

    for (ODKFormJoin join : joins)
    {
      if (join.getChild().hasGeoAttribute())
      {
        return true;
      }
    }

    return false;
  }

  public String getInstancePath()
  {
    if (this.parentForm != null)
    {
      return this.parentForm.getInstancePath() + "/" + this.getFormId();
    }

    return "/" + this.getFormId();
  }

  public boolean isExport(String type)
  {
    if (type.equals(this.viewMd.definesType()))
    {
      return this.isExport();
    }

    for (ODKFormJoin join : joins)
    {
      if (!join.getChild().isExport(type))
      {
        return false;
      }
    }

    return true;
  }
}
