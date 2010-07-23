package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.entomology.CollectionContainer;
import dss.vector.solutions.entomology.CollectionContainerQuery;
import dss.vector.solutions.entomology.CollectionContainerView;
import dss.vector.solutions.entomology.ImmatureCollection;
import dss.vector.solutions.entomology.ImmatureCollectionQuery;
import dss.vector.solutions.entomology.ImmatureCollectionView;
import dss.vector.solutions.entomology.ImmatureCollectionViewQuery;
import dss.vector.solutions.entomology.PremiseTaxon;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.GridComparator;
import dss.vector.solutions.util.HierarchyBuilder;

public class ImmatureCollectionExcelView extends ImmatureCollectionExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1174815189;
  
  public ImmatureCollectionExcelView()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    ImmatureCollectionView i = new ImmatureCollectionView();
    i.setCollectionId(this.getCollectionId());
    i.setPremiseType(Term.validateByDisplayLabel(this.getPremiseType(), ImmatureCollectionView.getPremiseTypeMd()));
    i.setTaxon(Term.validateByDisplayLabel(this.getTaxon(), ImmatureCollectionView.getTaxonMd()));

    ImmatureCollectionViewQuery search = ImmatureCollectionViewQuery.searchCollections(i);
    OIterator<? extends ImmatureCollectionView> iterator = search.getIterator();
    if (iterator.hasNext())
    {
      i = PremiseTaxon.getView(iterator.next().getTaxonId());
    }
    else
    {
      i.setGeoEntity(this.getGeoEntity());
      i.setStartDate(this.getStartDate());
      i.setEndDate(this.getEndDate());
      i.setCollectionId(this.getCollectionId());
      i.setNotes(this.getNotes());
      i.setNumberExamined(this.getNumberExamined());
      i.setNumberWithLarvae(this.getNumberWithLarvae());
      i.setNumberWithPupae(this.getNumberWithPupae());
      i.setNumberWithImmatures(this.getNumberWithImmatures());
      i.setPremiseSize(this.getPremiseSize());
      i.setNumberInhabitants(this.getNumberInhabitants());
    }
    iterator.close();
    
    Term term = Term.validateByDisplayLabel(this.getContainerTerm(), ImmatureCollectionView.getContainerGridMd());
    CollectionContainerView c = getContainerForTerm(i, term);
    c.setNumberContainers(this.getNumberContainers());
    c.setNumberWithWater(this.getNumberWithWater());
    c.setNumberDestroyed(this.getNumberDestroyed());
    c.setNumberWithLarvicide(this.getNumberWithLarvicide());
    c.setNumberImmatures(this.getNumberImmatures());
    c.setNumberLarvae(this.getNumberLarvae());
    c.setNumberPupae(this.getNumberPupae());
    c.setNumberLarvaeCollected(this.getNumberLarvaeCollected());
    c.setNumberPupaeCollected(this.getNumberPupaeCollected());
    
    i.applyWithContainers(new CollectionContainerView[] {c});
  }
  
  private CollectionContainerView getContainerForTerm(ImmatureCollectionView immatureCollectionView, Term containerTerm)
  {
    CollectionContainerView ccv;
    String taxonId = immatureCollectionView.getTaxonId();
    String termId = containerTerm.getId();
    
    CollectionContainerQuery query = new CollectionContainerQuery(new QueryFactory());
    query.WHERE(query.parentId().EQ(taxonId));
    query.WHERE(query.childId().EQ(termId));
    OIterator<? extends CollectionContainer> iterator = query.getIterator();
    
    if (iterator.hasNext())
    {
      ccv = iterator.next().getView();
    }
    else
    {
      ccv = new CollectionContainerView();
      ccv.setTerm(containerTerm);
    }
    iterator.close();
    return ccv;
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(COLLECTIONID);
    list.add(STARTDATE);
    list.add(ENDDATE);
    list.add(PREMISETYPE);
    list.add(TAXON);
    list.add(NOTES);
    list.add(NUMBEREXAMINED);
    list.add(PREMISESIZE);
    list.add(NUMBERINHABITANTS);
    list.add(CONTAINERTERM);
    list.add(NUMBERCONTAINERS);
    list.add(NUMBERWITHWATER);
    list.add(NUMBERDESTROYED);
    list.add(NUMBERWITHLARVICIDE);
    list.add(NUMBERIMMATURES);
    list.add(NUMBERLARVAE);
    list.add(NUMBERPUPAE);
    list.add(NUMBERLARVAECOLLECTED);
    list.add(NUMBERPUPAECOLLECTED);
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
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
  }
}
