package dss.vector.solutions.export;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.excel.ImportContext;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;

import dss.vector.solutions.RequiredAttributeProblem;
import dss.vector.solutions.entomology.LifeStage;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.entomology.MosquitoCollectionView;
import dss.vector.solutions.entomology.SubCollection;
import dss.vector.solutions.entomology.SubCollectionQuery;
import dss.vector.solutions.entomology.SubCollectionView;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.CollectionSite;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class MosquitoCollectionExcelView extends MosquitoCollectionExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -9941268;
  
  public MosquitoCollectionExcelView()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    MosquitoCollectionView view = getCollection();
    String subId = this.getSubCollectionId();
    Term idMethod = Term.validateByDisplayLabel(this.getIdentMethod(), SubCollectionView.getIdentMethodMd());
    Term taxonTerm = Term.validateByDisplayLabel(this.getTaxon(), SubCollectionView.getTaxonMd());
    
    SubCollectionQuery query = new SubCollectionQuery(new QueryFactory());
    query.WHERE(query.getCollection().getId().EQ(view.getConcreteId()));
    if (subId==null || subId.length()==0)
    {
      query.WHERE(query.getSubCollectionId().EQ(subId));
    }
    else
    {
      query.WHERE(query.getSubCollectionId().EQ(subId));
    }
    
    if (idMethod==null)
    {
      query.WHERE(query.getIdentMethod().EQ(idMethod));
    }
    else
    {
      query.WHERE(query.getIdentMethod().EQ(idMethod));
    }
    
    if (taxonTerm==null)
    {
      query.WHERE(query.getTaxon().EQ(taxonTerm));
    }
    else
    {
      query.WHERE(query.getTaxon().EQ(taxonTerm));
    }
    
    OIterator<? extends SubCollection> iterator = query.getIterator();
    
    SubCollectionView sub;
    if (iterator.hasNext())
    {
      SubCollection next = iterator.next();
      sub = next.lockView();
    }
    else
    {
      sub = new SubCollectionView();
      sub.setSubCollectionId(subId);
      sub.setIdentMethod(idMethod);
      sub.setTaxon(taxonTerm);
    }
    iterator.close();
    
    sub.setEggs(this.getEggs());
    sub.setMale(this.getMale());
    sub.setFemale(this.getFemale());
    sub.setLarvae(this.getLarvae());
    sub.setPupae(this.getPupae());
    sub.setUnknowns(this.getUnknowns());
    
    view.applyAll(new SubCollectionView[]{sub});
  }

  private MosquitoCollectionView getCollection()
  {
    MosquitoCollectionView view = new MosquitoCollectionView();
    String cid = this.getCollectionId();
    
    MosquitoCollectionQuery query = new MosquitoCollectionQuery(new QueryFactory());
    query.WHERE(query.getCollectionId().EQ(cid));
    OIterator<? extends MosquitoCollection> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      view = iterator.next().getView();
    }
    else
    {
      if (cid.length()==0)
      {
        RequiredAttributeProblem rap = new RequiredAttributeProblem();
        rap.setAttributeName(COLLECTIONID);
        rap.setAttributeDisplayLabel(MosquitoCollectionExcelView.getCollectionIdMd().getDisplayLabel(Session.getCurrentLocale()));
        rap.throwIt();
      }
      else
      {
        view.setCollectionId(cid);
      }
    }
    iterator.close();
    
    Term colMethod = Term.validateByDisplayLabel(this.getCollectionMethod(), MosquitoCollectionView.getCollectionMethodMd());
    if (colMethod!=null)
    {
      view.setCollectionMethod(colMethod);
    }
    
    Date colDate = this.getCollectionDate();
    if (colDate!=null)
    {
      view.setCollectionDate(colDate);
    }
    
    GeoEntity geo = this.getGeoEntity();
    if (geo!=null)
    {
      view.setGeoEntity(geo);
    }
    
    Boolean abund = this.getAbundance();
    if (abund!=null)
    {
      view.setAbundance(abund);
    }
    
    LifeStage life = ExcelEnums.getLifeStage(this.getLifeStage());
    if (life!=null)
    {
      view.addLifeStage(life);
    }
    
    return view;
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(COLLECTIONMETHOD);
    list.add(COLLECTIONDATE);
    list.add(COLLECTIONID);
    list.add(ABUNDANCE);
    list.add(LIFESTAGE);
    list.add(SUBCOLLECTIONID);
    list.add(IDENTMETHOD);
    list.add(TAXON);
    list.add(EGGS);
    list.add(LARVAE);
    list.add(PUPAE);
    list.add(FEMALE);
    list.add(MALE);
    list.add(UNKNOWNS);
    return list;
  }

  public static void setupImportListener(ImportContext context, String... params)
  {
    context.addListener(createExcelGeoListener());
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllPoliticals())
    {
      builder.add(hierarchy);
    }
    builder.add(GeoHierarchy.getGeoHierarchyFromType(CollectionSite.CLASS));
    builder.add(GeoHierarchy.getGeoHierarchyFromType(SentinelSite.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
  }
}
