package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.entomology.MosquitoCollectionView;
import dss.vector.solutions.entomology.SubCollectionView;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.CollectionSite;
import dss.vector.solutions.geo.generated.HealthFacility;
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
    
    SubCollectionView sub = new SubCollectionView();
    sub.setSubCollectionId(this.getSubCollectionId());
    sub.setIdentMethod(Term.validateByDisplayLabel(this.getIdentMethod(), SubCollectionView.getIdentMethodMd()));
    sub.setTaxon(Term.validateByDisplayLabel(this.getTaxon(), SubCollectionView.getTaxonMd()));
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
    
    MosquitoCollectionQuery query = new MosquitoCollectionQuery(new QueryFactory());
    query.WHERE(query.getCollectionId().EQ(this.getCollectionId()));
    OIterator<? extends MosquitoCollection> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      view = iterator.next().getView();
      iterator.close();
    }
    else
    {
      view.setCollectionMethod(Term.validateByDisplayLabel(this.getCollectionMethod(), MosquitoCollectionView.getCollectionMethodMd()));
      view.setCollectionDate(this.getCollectionDate());
      view.setGeoEntity(this.getGeoEntity());
      view.setCollectionId(this.getCollectionId());
      view.setAbundance(this.getAbundance());
      view.addLifeStage(ExcelEnums.getLifeStage(this.getLifeStage()));
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
