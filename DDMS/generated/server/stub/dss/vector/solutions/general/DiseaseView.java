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
package dss.vector.solutions.general;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.dataaccess.MdTreeDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.metadata.MdTreeDAO;
import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.logging.LogLevel;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OrderBy.SortOrder;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdDimension;
import com.runwaysdk.system.metadata.MdWebAttribute;
import com.runwaysdk.system.metadata.MdWebField;
import com.runwaysdk.system.metadata.MdWebForm;
import com.runwaysdk.system.metadata.MdWebFormQuery;
import com.runwaysdk.system.metadata.MdWebMultipleTerm;
import com.runwaysdk.system.metadata.MdWebSingleTermGrid;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.PropertyQuery;
import dss.vector.solutions.form.DDMSFieldBuilders;
import dss.vector.solutions.generator.FormSystemURLBuilder;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.ontology.BrowserField;
import dss.vector.solutions.ontology.BrowserRoot;
import dss.vector.solutions.ontology.BrowserRootQuery;
import dss.vector.solutions.ontology.InactiveProperty;
import dss.vector.solutions.ontology.InactivePropertyQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.permission.PermissionImporter;
import dss.vector.solutions.report.ReportItem;
import dss.vector.solutions.report.ReportItemQuery;

public class DiseaseView extends DiseaseViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1948354243;

  private static final Log  log              = LogFactory.getLog(DiseaseView.class);

  public DiseaseView()
  {
    super();
  }

  public void populateView(Disease concrete, MdDimension dimension)
  {
    this.setConcreteId(concrete.getId());

    if (dimension != null)
    {
      this.setDiseaseName(dimension.getDisplayLabel().getValue());
    }
  }

  private void populateConcrete(Disease concrete, MdDimension dimension)
  {
    if (dimension != null)
    {
      if (dimension.isNew() && !dimension.isAppliedToDB())
      {
        String diseaseName = this.getDiseaseName().replaceAll("\\s", "_");

        dimension.setName(diseaseName);
      }

      dimension.getDisplayLabel().setValue(this.getDiseaseName());
    }
  }

  private void buildAttributeMap(Disease concrete, MdDimension dimension)
  {
    new AttributeNotificationMap(concrete, Disease.ID, this, DiseaseView.CONCRETEID);
    new AttributeNotificationMap(dimension, MdDimension.DISPLAYLABEL, this, DiseaseView.DISEASENAME);
  }

  @Override
  @Authenticate
  @Transaction
  @com.runwaysdk.logging.Log(level = LogLevel.DEBUG)
  public void applyConcrete()
  {
    this.applyNoPersist();

    Disease concrete = this.getConcrete();
    MdDimension dimension = this.getDimension(concrete);

    boolean firstApply = ( concrete.isNew() && !concrete.isAppliedToDB() );

    // Build the attribute map between Disease and
    // DiseaseView for error handling
    this.buildAttributeMap(concrete, dimension);

    this.populateConcrete(concrete, dimension);

    dimension.apply();

    concrete.setDimension(dimension);
    concrete.apply();

    if (firstApply)
    {
      log.info("Disease - first apply");
      /*
       * STEP 1: Copy over the InactiveProperties from the Malaria disease
       */
      log.info("STEP 1: Copy over the InactiveProperties from the Malaria disease");
      this.cloneInactiveProperties(concrete);

      /*
       *  STEP 2: Create the menu structure for the new disease
       */
      log.info("STEP 2: Create the menu structure for the new disease");
      this.cloneMenuItems(concrete);

      /*
       * STEP 3: Create the browser roots for the new disease
       */
      log.info("STEP 3: Create the browser roots for the new disease");
      this.cloneBrowserRoots(concrete);

      /*
       * STEP 4: Add default case period property #3026 [value of 4 was recommended by Miguel)
       */
      log.info("STEP 4: Add default case period property");
      this.addDefaultCasePeriod(concrete);

      /*
       * STEP 5: Add a threshold alert calculation type for this disease #3026.
       */
      log.info("STEP 5: Add default threshold alert calculation type for the disease.");
      this.addThresholdAlertCalcType(concrete);

      /*
       * STEP 6: Add system alerts type for this disease #3072.
       */
      log.info("STEP 6: Add system alerts for the disease.");
      this.addSystemAlerts(concrete);

      /*
       *  STEP 7: Create permissions for the new disease and dimension
       */
      log.info("STEP 7: Create permissions for the new disease and dimension");
      this.addPermissions(concrete);

    }

    this.populateView(concrete, dimension);
  }

  public boolean hasDefaultCasePeriod(Disease concrete)
  {
    return Property.getByPackageAndName(PropertyInfo.MONITOR_PACKAGE, PropertyInfo.NEW_CASE_PERIOD, concrete) != null;
  }

  public boolean hasThresholdAlertCalcType(Disease concrete)
  {
    try
    {
      ThresholdAlertCalculationType.getCurrent(concrete);
      return true;
    }
    catch (DataNotFoundException ex)
    {
      return false;
    }
  }

  public boolean hasSystemAlerts(Disease concrete)
  {
    SystemAlertQuery query = new SystemAlertQuery(new QueryFactory());
    query.WHERE(query.getDisease().EQ(concrete));

    return ( query.getCount() > 0 );
  }

  @AbortIfProblem
  public void addThresholdAlertCalcType(Disease d)
  {
    if (this.hasThresholdAlertCalcType(d))
    {
      return;
    }

    ThresholdAlertCalculationType threshold = new ThresholdAlertCalculationType();
    threshold.setClinicalPositivePercentage(100);
    threshold.setKeyName(d.getKeyName());
    threshold.addCountingMethod(OutbreakCalculation.EPI_WEEK);
    threshold.setDisease(d);
    threshold.setEpidemicUniversal(GeoHierarchy.getGeoHierarchyFromType(Earth.CLASS));
    threshold.apply();

  }

  public void addSystemAlerts(Disease d)
  {
    if (this.hasSystemAlerts(d))
    {
      return;

    }
    // Copy the system alerts of malaria
    SystemAlertQuery query = new SystemAlertQuery(new QueryFactory());
    query.WHERE(query.getDisease().EQ(Disease.getMalaria()));

    OIterator<? extends SystemAlert> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        SystemAlert existing = iterator.next();

        SystemAlert alert = new SystemAlert();
        alert.setDisease(d);
        alert.addAlertType(existing.getAlertType().get(0));
        alert.setIsOnscreenActive(existing.getIsOnscreenActive());
        alert.setIsEmailActive(existing.getIsEmailActive());
        alert.setEmailToAddresses(existing.getEmailToAddresses());
        alert.setEmailFromAddress(existing.getEmailFromAddress());
        alert.setEmailCcAddresses(existing.getEmailCcAddresses());
        alert.setEmailBccAddresses(existing.getEmailBccAddresses());
        alert.getEmailBodyText().setValue(existing.getEmailBodyText().getValue());
        alert.getEmailBodyText().setValue(existing.getEmailBodyText().getValue());
        alert.getEmailSubjectText().setValue(existing.getEmailSubjectText().getValue());
        alert.apply();
      }
    }
    finally
    {
      iterator.close();
    }
  }

  /**
   * Adds a default newCasePeriod property, which is required to make individual
   * cases work after a new disease is added. Ideally we could clone an existing
   * case period and change the disease but that might have unknown behavior so
   * we do it manually here.
   * 
   * @param concrete
   */
  @AbortIfProblem
  public void addDefaultCasePeriod(Disease concrete)
  {
    if (this.hasDefaultCasePeriod(concrete))
    {
      // Nothing to do here.
      return;
    }

    Property prop = new Property();
    prop.setPropertyPackage(PropertyInfo.MONITOR_PACKAGE);
    prop.setPropertyName(PropertyInfo.NEW_CASE_PERIOD);
    prop.setDisease(concrete);
    prop.setKeyName(PropertyInfo.NEW_CASE_PERIOD + "." + concrete.getKeyName()); // eg,
                                                                                 // newCasePerson.Visceral
                                                                                 // Leishmaniasis
    prop.setPropertyValue("4");
    prop.setPropertyType("Integer");
    prop.setPropertyValidator("^[1-9][0-9]*$");
    prop.setValidValues("4, 6, 10");
    prop.getDisplayLabel().setDefaultValue("New Case Period (weeks)");
    prop.getDescription().setDefaultValue("Number of weeks (from case diagnosis date) after which to create a new case");
    prop.apply();

    /*
     * For Reference:
    
    -- JAVA accessor
    Property casePeriod = Property.getByPackageAndName(PropertyInfo.MONITOR_PACKAGE, PropertyInfo.NEW_CASE_PERIOD);
    
    -- XML definitino
    <object type="dss.vector.solutions.Property" key="newCasePeriod.MALARIA">
      <attribute name="propertyPackage" value="dss.vector.solutions.intervention.monitor" />
      <attribute name="propertyName" value="newCasePeriod" />
      <attributeReference name="disease" key="MALARIA" />
      <attributeStruct
         name="displayLabel">
        <attribute name="defaultLocale" value="New Case Period (weeks)" />
      </attributeStruct>
      <attribute name="propertyValue" value="4" />
      <attribute name="propertyType" value="Integer" />
      <attribute name="propertyValidator" value="^[1-9][0-9]*$" />
      <attribute name="validValues" value="4, 6, 10" />
      <attributeStruct
         name="description">
        <attribute name="defaultLocale" value="Number of weeks (from case diagnosis date) after which to create a new case" />
      </attributeStruct>
    </object>
     * 
     */
  }

  @AbortIfProblem
  public void addPermissions(Disease concrete)
  {
    // Define the static permissions
    PermissionImporter importer = new PermissionImporter(true, concrete);
    importer.read(new BufferedInputStream(this.getClass().getResourceAsStream("/Permissions.xlsx")));

    // Define permissions for all the existing reports
    this.addReportItemPermissions(concrete);

    // Define permissions for all the existing reports
    this.addFormPermissions(concrete);
  }

  @AbortIfProblem
  public void addFormPermissions(Disease concrete)
  {
    MdWebFormQuery query = new MdWebFormQuery(new QueryFactory());
    OIterator<? extends MdWebForm> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        MdWebForm form = it.next();

        FormSystemURLBuilder builder = new FormSystemURLBuilder(form);
        builder.addPermissions(concrete);

        OIterator<? extends MdWebField> fields = form.getAllMdFields();

        try
        {
          while (fields.hasNext())
          {
            MdWebField field = fields.next();

            if ( ( field instanceof MdWebMultipleTerm ) || ( field instanceof MdWebSingleTermGrid ))
            {
              String typeName = DDMSFieldBuilders.getTermRelationshipTypeName(form, (MdWebAttribute) field);
              MdTreeDAOIF mdTree = MdTreeDAO.getMdTreeDAO(MDSSInfo.GENERATED_FORM_TREE_PACKAGE + "." + typeName);

              builder.addPermissions(mdTree, concrete);
            }
          }
        }
        finally
        {
          fields.close();
        }
      }
    }
    finally
    {
      it.close();
    }
  }

  @AbortIfProblem
  public void addReportItemPermissions(Disease concrete)
  {
    ReportItemQuery reportQuery = new ReportItemQuery(new QueryFactory());
    OIterator<? extends ReportItem> iterator = reportQuery.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        ReportItem report = iterator.next();
        report.addPermissions(concrete);
      }
    }
    finally
    {
      iterator.close();
    }
  }

  @AbortIfProblem
  private void cloneBrowserRoots(Disease concrete)
  {
    BrowserRootQuery query = new BrowserRootQuery(new QueryFactory());
    query.WHERE(query.getDisease().EQ(Disease.getMalaria()));

    OIterator<? extends BrowserRoot> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        BrowserRoot existing = iterator.next();
        BrowserField field = existing.getBrowserField();

        BrowserRoot clone = new BrowserRoot();
        clone.setSelectable(existing.getSelectable());
        clone.setTerm(existing.getTerm());
        clone.setBrowserField(field);
        clone.setDisease(concrete);
        clone.apply();
      }
    }
    finally
    {
      iterator.close();
    }
  }

  @AbortIfProblem
  private void cloneMenuItems(Disease concrete)
  {
    /*
     * Clone the malaria menu term sub-tree
     */
    Term term = Disease.getMalaria().getMenuRoot();

    Map<String, Term> cache = new HashMap<String, Term>();
    Term parent = term.getAllParentTerm().getAll().get(0);
    Set<String> alreadyCloned = new HashSet<String>();
    this.cloneTerm(concrete, term, parent, this.getDiseaseName(), cache, alreadyCloned);

    // First specify the root menu item
    concrete.setMenuRoot(this.getCachedTerm(cache, this.getDiseaseName()));
    concrete.apply();

    // Second copy the Malaria menu structure over to the new disease
    MenuItemQuery query = new MenuItemQuery(new QueryFactory());
    query.WHERE(query.getDisease().EQ(Disease.getMalaria()));

    OIterator<? extends MenuItem> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        MenuItem existing = it.next();

        MenuItem clone = new MenuItem();
        clone.setDisease(concrete);
        clone.setUrl(existing.getUrl());
        clone.setTerm(this.getCachedTerm(cache, existing.getTerm().getName()));
        clone.apply();
      }
    }
    finally
    {
      it.close();
    }
  }

  public Term getCachedTerm(Map<String, Term> cache, String termName)
  {
    if (cache.containsKey(termName))
    {
      return cache.get(termName);
    }

    throw new ProgrammingErrorException("Unable to find a cached term with the term name [" + termName + "]");
  }

  private void cloneTerm(Disease concrete, Term existing, Term parent, String name, Map<String, Term> cache, Set<String> alreadyCloned)
  {
    Term term;
    if (!alreadyCloned.contains(existing.getKeyName()))
    {
      term = new Term();
      term.setTermId(this.getNewTermId(concrete, existing));
      term.setComment(existing.getComment());
      term.setDef(existing.getDef());
      term.setNamespace(existing.getName());
      term.setObsolete(existing.getObsolete());
      term.setOntology(existing.getOntology());
  
      if (name == null)
      {
        term.setName(existing.getName());
        term.getTermDisplayLabel().setValue(existing.getTermDisplayLabel().getValue());
      }
      else
      {
        term.setName(name);
        term.getTermDisplayLabel().setValue(name);
      }
    }
    else
    {
      term = Term.getByTermId(this.getNewTermId(concrete, existing));
    }

    term.applyWithParent(parent.getId(), false, null, false);

    OIterator<? extends Term> children = existing.getAllChildTerm();

    try
    {
      while (children.hasNext())
      {
        Term child = children.next();

        this.cloneTerm(concrete, child, term, null, cache, alreadyCloned);
      }
    }
    finally
    {
      children.close();
    }

    alreadyCloned.add(existing.getKeyName());
    cache.put(term.getName(), term); // TODO : Why is this cache using term.getName() ? That isn't guaranteed to be unique.
  }

  private String getNewTermId(Disease concrete, Term existing)
  {
    String termId = existing.getTermId();

    int index = termId.indexOf(":");
    
    String diseaseName = concrete.getDimension().getName();

    if (index != -1
        &&
        // The old prefix might be the only thing that makes this id unique (ticket 3033). If thats the case we have to include it in the new term id.
        (termId.substring(0, index).equals(Disease.getMalaria().getDimension().getName()))
       )
    {
      termId = diseaseName + termId.substring(index);
    }
    else
    {
      termId = diseaseName + ":" + termId;
    }

    if (termId.length() >= 64)
    {
      termId = termId.substring(0, 64);
    }

    return termId;
  }

  @AbortIfProblem
  private void cloneInactiveProperties(Disease concrete)
  {
    InactivePropertyQuery query = new InactivePropertyQuery(new QueryFactory());
    query.WHERE(query.getDisease().EQ(Disease.getMalaria()));

    OIterator<? extends InactiveProperty> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        InactiveProperty existing = it.next();

        InactiveProperty clone = new InactiveProperty();
        clone.setInactive(existing.getInactive());
        clone.setDisease(concrete);
        clone.apply();

        OIterator<? extends Term> terms = existing.getAllTerm();

        try
        {
          while (terms.hasNext())
          {
            terms.next().addInactiveProperties(clone).apply();
          }
        }
        finally
        {
          terms.close();
        }
      }
    }
    finally
    {
      it.close();
    }
  }

  private MdDimension getDimension(Disease concrete)
  {
    if (concrete.isNew() && !concrete.isAppliedToDB())
    {
      return new MdDimension();
    }
    else
    {
      return concrete.getDimension();
    }
  }

  private Disease getConcrete()
  {
    if (this.hasConcrete())
    {
      return Disease.lock(this.getConcreteId());
    }
    else
    {
      return new Disease();
    }
  }

  @Override
  @Transaction
  @Authenticate
  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      Disease disease = Disease.get(this.getConcreteId());
      MdDimension dimension = disease.getDimension();

      /*
       * Don't allow the user to delete the malaria or dengue disease
       */
      if (disease.getId().equals(Disease.getMalaria().getId()) || disease.getId().equals(Disease.getDengue().getId()))
      {
        UnableToDeleteDiseaseException e = new UnableToDeleteDiseaseException();
        e.setDiseaseName(dimension.getDisplayLabel().getValue());
        e.apply();

        throw e;
      }

      /*
       * Delete permissions
       */
      log.info("STEP 1: Delete Permissions");
      this.deletePermissions(disease);

      /*
       * Delete the browser roots
       */
      log.info("STEP 2: Delete Browser Roots");
      this.deleteBrowserRoots(disease);

      /*
       * Delete the menu items
       */
      log.info("STEP 3: Delete Menu Items");
      this.deleteMenuItems(disease);

      /*
       * Delete the inactive properties
       */
      log.info("STEP 4: Delete Inactive Properties");
      this.deleteInactiveProperties(disease);

      /*
       * Delete default case period
       */
      log.info("STEP 5: Delete Default Case Period");
      this.deleteDefaultCasePeriod(disease);

      /*
       * Delete the threshold alert calculation type 
       */
      log.info("STEP 6: Delete Threshold Alert Calculation Type");
      this.deleteThresholdAlertCalcType(disease);

      disease.delete();
      dimension.delete();
    }
  }

  @AbortIfProblem
  private void deletePermissions(Disease disease)
  {
    new PermissionImporter(true, disease).delete();
  }

  @AbortIfProblem
  private void deleteDefaultCasePeriod(Disease disease)
  {
    PropertyQuery q = new PropertyQuery(new QueryFactory());
    q.WHERE(q.getDisease().EQ(disease));

    OIterator<? extends Property> iter = q.getIterator();

    try
    {
      while (iter.hasNext())
      {
        iter.next().delete();
      }
    }
    finally
    {
      iter.close();
    }
  }

  @AbortIfProblem
  private void deleteThresholdAlertCalcType(Disease disease)
  {
    ThresholdAlertCalculationTypeQuery q = new ThresholdAlertCalculationTypeQuery(new QueryFactory());
    q.WHERE(q.getDisease().EQ(disease));

    OIterator<? extends ThresholdAlertCalculationType> iter = q.getIterator();

    try
    {
      while (iter.hasNext())
      {
        iter.next().delete();
      }
    }
    finally
    {
      iter.close();
    }
  }

  @AbortIfProblem
  private void deleteBrowserRoots(Disease disease)
  {
    BrowserRootQuery query = new BrowserRootQuery(new QueryFactory());
    query.WHERE(query.getDisease().EQ(disease));

    OIterator<? extends BrowserRoot> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        iterator.next().delete();
      }
    }
    finally
    {
      iterator.close();
    }
  }

  @AbortIfProblem
  private void deleteInactiveProperties(Disease disease)
  {
    InactivePropertyQuery query = new InactivePropertyQuery(new QueryFactory());
    query.WHERE(query.getDisease().EQ(disease));

    OIterator<? extends InactiveProperty> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        it.next().delete();
      }
    }
    finally
    {
      it.close();
    }
  }

  @AbortIfProblem
  private void deleteMenuItems(Disease disease)
  {
    MenuItemQuery query = new MenuItemQuery(new QueryFactory());
    query.WHERE(query.getDisease().EQ(disease));

    OIterator<? extends MenuItem> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        it.next().delete();
      }
    }
    finally
    {
      it.close();
    }

    Term root = disease.getMenuRoot();
    root.deleteTerm();
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  public static DiseaseView getDisease(String diseaseName)
  {
    DiseaseViewQuery query = new DiseaseViewQuery(new QueryFactory());
    query.WHERE(query.getDiseaseName().EQ(diseaseName));

    OIterator<? extends DiseaseView> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        DiseaseView view = iterator.next();

        if (iterator.hasNext())
        {
          AmbiguousDiseaseException e = new AmbiguousDiseaseException();
          e.setDiseaseName(diseaseName);
          e.apply();

          throw e;
        }

        return view;
      }
    }
    finally
    {
      iterator.close();
    }

    return null;
  }

  public static DiseaseViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    DiseaseViewQuery query = new DiseaseViewQuery(new QueryFactory());
    query.restrictRows(pageSize, pageNumber);

    SortOrder sortOrder = ( isAscending != null && isAscending ) ? SortOrder.ASC : SortOrder.DESC;

    if (sortAttribute != null && sortAttribute.equals(DiseaseView.CONCRETEID))
    {
      query.ORDER_BY(query.getConcreteId(), sortOrder);
    }
    else
    {
      query.ORDER_BY(query.getDiseaseName(), sortOrder);
    }

    return query;
  }

}
