package dss.vector.solutions.entomology;

import java.util.Date;
import java.util.List;

import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdWebField;
import com.runwaysdk.system.metadata.MdWebForm;
import com.runwaysdk.system.metadata.MdWebFormQuery;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.entomology.assay.CollectionAssay;
import dss.vector.solutions.entomology.assay.CollectionAssayQuery;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.MosquitoCollectionQB;

public class MosquitoCollection extends MosquitoCollectionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1894808272;

  public MosquitoCollection()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else if (this.getCollectionId() != null)
    {
      return this.getCollectionId();
    }

    return super.toString();
  }

  @Override
  protected String buildKey()
  {
    return this.getCollectionId();
  }

  @Override
  @Transaction
  public void delete()
  {
    // Delete all of the sub collections
    MosquitoCollectionView view = this.getView();

    SubCollectionView[] collections = view.getSubCollections();

    for (SubCollectionView collection : collections)
    {
      collection.deleteConcrete();
    }

    // Delete all of the Assays
    List<? extends CollectionAssay> list = this.getAssays();

    for (CollectionAssay assay : list)
    {
      assay.delete();
    }

    // Delete all infection and pooled infection assays
    InfectionAssayView[] infectionAssays = view.getInfectionAssays();

    for (InfectionAssayView assay : infectionAssays)
    {
      assay.deleteConcrete();
    }

    PooledInfectionAssayView[] pooledInfectionAssays = view.getPooledInfectionAssays();

    for (PooledInfectionAssayView assay : pooledInfectionAssays)
    {
      assay.deleteConcrete();
    }

    // DELETE ALL MECHANISM ASSAYS
    BiochemicalAssayView[] biochemicalAssays = view.getBiochemicalAssays();

    for (BiochemicalAssayView assay : biochemicalAssays)
    {
      assay.deleteConcrete();
    }

    MolecularAssayView[] molecularAssays = view.getMolecularAssays();

    for (MolecularAssayView assay : molecularAssays)
    {
      assay.deleteConcrete();
    }

    // DELETE ALL BIOASSAYS
    DiagnosticAssayView[] diagnosticAssays = view.getDiagnosticAssays();

    for (DiagnosticAssayView assay : diagnosticAssays)
    {
      assay.deleteConcrete();
    }

    TimeResponseAssayView[] timeResponseAssays = view.getTimeResponseAssays();

    for (TimeResponseAssayView assay : timeResponseAssays)
    {
      assay.deleteConcrete();
    }
    
    // Delete all generated forms that reference a mosquito collection (ticket 3358)
    QueryFactory qf = new QueryFactory();
    MdWebFormQuery formQ = new MdWebFormQuery(qf);
    OIterator<? extends MdWebForm> formIt = formQ.getIterator();
    while (formIt.hasNext())
    {
      MdWebForm form = formIt.next();
      OIterator<? extends MdWebField> fieldIt = form.getAllMdFields();
      while (fieldIt.hasNext())
      {
        MdWebField field = fieldIt.next();
        if (field.getFieldName().equals(MosquitoCollection.COLLECTIONID))
        {
          BusinessQuery bizq = qf.businessQuery(form.getFormMdClass().definesType());
          bizq.WHERE(bizq.aCharacter(MosquitoCollection.COLLECTIONID).EQ(this.getCollectionId()));
          OIterator<? extends Business> bizIt = bizq.getIterator();
          while (bizIt.hasNext())
          {
            Business biz = bizIt.next();
            biz.delete();
          }
        }
      }
    }

    super.delete();
  }

  private List<? extends CollectionAssay> getAssays()
  {
    CollectionAssayQuery query = new CollectionAssayQuery(new QueryFactory());
    query.WHERE(query.getCollection().EQ(this));
    OIterator<? extends CollectionAssay> it = query.getIterator();

    try
    {
      return it.getAll();
    }
    finally
    {
      it.close();
    }
  }

  @Override
  public void apply()
  {
    this.populateCollectionId();
    this.populateLifeStageName();
    this.validateCollectionDate();
    this.validateDateLastSprayed();

    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    super.apply();
  }

  @Override
  public void validateCollectionDate()
  {
    if (this.getCollectionDate() != null && this.getCollectionDate().after(new Date()))
    {
      String msg = "It is impossible to have a test date after the current date";

      CurrentDateProblem p = new CurrentDateProblem(msg);
      p.setGivenDate(this.getCollectionDate());
      p.setCurrentDate(new Date());
      p.setNotification(this, COLLECTIONDATE);
      p.apply();
      p.throwIt();
    }
  }

  @Override
  public void validateDateLastSprayed()
  {
    if (this.getDateLastSprayed() != null && this.getDateLastSprayed().after(new Date()))
    {
      String msg = "It is impossible to have a test date after the current date";

      CurrentDateProblem p = new CurrentDateProblem(msg);
      p.setGivenDate(this.getDateLastSprayed());
      p.setCurrentDate(new Date());
      p.setNotification(this, DATELASTSPRAYED);
      p.apply();
      p.throwIt();
    }
  }

  private void populateLifeStageName()
  {
    for (LifeStage stage : this.getLifeStage())
    {
      this.setLifeStageName(stage.getEnumName());
    }
  }

  private void populateCollectionId()
  {
    if (this.getCollectionId() == null || this.getCollectionId().equals(""))
    {
      this.setCollectionId(LocalProperty.getNextId());
    }
  }

  @Override
  @Transaction
  public MosquitoCollectionView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public MosquitoCollectionView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public MosquitoCollectionView getView()
  {
    MosquitoCollectionView view = new MosquitoCollectionView();

    view.populateView(this);

    return view;
  }

  public static MosquitoCollection getByCollectionId(String collectionId)
  {
    MosquitoCollectionQuery query = new MosquitoCollectionQuery(new QueryFactory());
    query.WHERE(query.getCollectionId().EQ(collectionId));
    OIterator<? extends MosquitoCollection> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      MosquitoCollection collection = iterator.next();
      iterator.close();
      return collection;
    }
    else
    {
      throw new DataNotFoundException("No mosquito collection with collection id [" + collectionId + "] found", MdTypeDAO.getMdTypeDAO(CLASS));
    }
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    MosquitoCollectionQB query = new MosquitoCollectionQB(xml, config, layer, pageSize, pageSize, disease);
    return query.construct();
  }

}
