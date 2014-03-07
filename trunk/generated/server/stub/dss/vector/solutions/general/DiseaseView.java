package dss.vector.solutions.general;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Map;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.dataaccess.MdTreeDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdTreeDAO;
import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
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
import dss.vector.solutions.form.DDMSFieldBuilders;
import dss.vector.solutions.generator.FormSystemURLBuilder;
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
      /*
       * STEP 1: Copy over the InactiveProperties from the Malaria disease
       */
      this.cloneInactiveProperties(concrete);

      /*
       *  STEP 2: Create the menu structure for the new disease
       */
      this.cloneMenuItems(concrete);

      /*
       * STEP 3: Create the browser roots for the new disease
       */
      this.cloneBrowserRoots(concrete);

      /*
       *  STEP 3: Create permissions for the new disease and dimension
       */
      this.addPermissions(concrete);

    }

    this.populateView(concrete, dimension);
  }

  public void addPermissions(Disease concrete)
  {
    // Define the static permissions
    PermissionImporter importer = new PermissionImporter(true, concrete);
    importer.read(new BufferedInputStream(this.getClass().getResourceAsStream("/Permissions.xls")));

    // Define permissions for all the existing reports
    this.addReportItemPermissions(concrete);

    // Define permissions for all the existing reports
    this.addFormPermissions(concrete);
  }

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

  private void cloneMenuItems(Disease concrete)
  {
    /*
     * Clone the malaria menu term sub-tree
     */
    Term term = Disease.getMalaria().getMenuRoot();

    Map<String, Term> cache = new HashMap<String, Term>();
    Term parent = term.getAllParentTerm().getAll().get(0);
    this.cloneTerm(concrete, term, parent, this.getDiseaseName(), cache);

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

  private void cloneTerm(Disease concrete, Term existing, Term parent, String name, Map<String, Term> cache)
  {
    Term term = new Term();
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

    term.applyWithParent(parent.getId(), false, null, false);

    OIterator<? extends Term> children = existing.getAllChildTerm();

    try
    {
      while (children.hasNext())
      {
        Term child = children.next();

        this.cloneTerm(concrete, child, term, null, cache);
      }
    }
    finally
    {
      children.close();
    }

    cache.put(term.getName(), term);
  }

  private String getNewTermId(Disease concrete, Term existing)
  {
    String termId = existing.getTermId();

    int index = termId.indexOf(":");

    if (index != -1)
    {
      termId = concrete.getDimension().getName() + termId.substring(index);
    }
    else
    {
      termId = concrete.getDimension().getName() + ":" + termId;
    }

    if (termId.length() >= 64)
    {
      termId = termId.substring(0, 64);
    }

    return termId;
  }

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
       *  Delete permissions
       */
      new PermissionImporter(true, disease).delete();

      /*
       * Delete the browser roots
       */
      this.deleteBrowserRoots(disease);

      /*
       * Delete the menu items
       */
      this.deleteMenuItems(disease);

      /*
       * Delete the inactive properties
       */
      this.deleteInactiveProperties(disease);

      disease.delete();
      dimension.delete();
    }
  }

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
