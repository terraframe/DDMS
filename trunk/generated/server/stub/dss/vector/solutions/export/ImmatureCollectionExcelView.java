package dss.vector.solutions.export;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.RequiredAttributeProblem;
import dss.vector.solutions.entomology.CollectionContainer;
import dss.vector.solutions.entomology.CollectionContainerQuery;
import dss.vector.solutions.entomology.CollectionContainerView;
import dss.vector.solutions.entomology.ImmatureCollectionView;
import dss.vector.solutions.entomology.ImmatureCollectionViewQuery;
import dss.vector.solutions.entomology.PremiseTaxon;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
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
    
    String cid = this.getCollectionId();
    if (cid==null || cid.length()==0)
    {
      RequiredAttributeProblem rap = new RequiredAttributeProblem();
      rap.setAttributeName(COLLECTIONID);
      rap.setAttributeDisplayLabel(ImmatureCollectionExcelView.getCollectionIdMd().getDisplayLabel(Session.getCurrentLocale()));
      rap.throwIt();
    }
    
    Term premiseTypeTerm = Term.validateByDisplayLabel(this.getPremiseType(), ImmatureCollectionView.getPremiseTypeMd());
    if (premiseTypeTerm==null)
    {
      RequiredAttributeProblem rap = new RequiredAttributeProblem();
      rap.setAttributeName(PREMISETYPE);
      rap.setAttributeDisplayLabel(ImmatureCollectionExcelView.getPremiseTypeMd().getDisplayLabel(Session.getCurrentLocale()));
      rap.throwIt();
    }
    
    Term taxonTerm = Term.validateByDisplayLabel(this.getTaxon(), ImmatureCollectionView.getTaxonMd());
    if (taxonTerm==null)
    {
      RequiredAttributeProblem rap = new RequiredAttributeProblem();
      rap.setAttributeName(TAXON);
      rap.setAttributeDisplayLabel(ImmatureCollectionExcelView.getTaxonMd().getDisplayLabel(Session.getCurrentLocale()));
      rap.throwIt();
    }
    
    i.setCollectionId(cid);
    i.setPremiseType(premiseTypeTerm);
    i.setTaxon(taxonTerm);

    ImmatureCollectionViewQuery search = ImmatureCollectionViewQuery.searchCollections(i);
    OIterator<? extends ImmatureCollectionView> iterator = search.getIterator();
    if (iterator.hasNext())
    {
      i = PremiseTaxon.getView(iterator.next().getTaxonId());
    }
    iterator.close();
    
    updateView(i);
    
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

  private void updateView(ImmatureCollectionView i)
  {
    GeoEntity geo = this.getGeoEntity();
    if (geo != null)
    {
      i.setGeoEntity(geo);
    }

    Date start = this.getStartDate();
    if (start != null)
    {
      i.setStartDate(start);
    }

    Date end = this.getEndDate();
    if (end != null)
    {
      i.setEndDate(end);
    }

    String note = this.getNotes();
    if (note.length() > 0)
    {
      i.setNotes(note);
    }

    Integer examined = this.getNumberExamined();
    if (examined != null)
    {
      i.setNumberExamined(examined);
    }

    Integer larvae = this.getNumberWithLarvae();
    if (larvae != null)
    {
      i.setNumberWithLarvae(larvae);
    }

    Integer pupae = this.getNumberWithPupae();
    {
      i.setNumberWithPupae(pupae);
    }

    Integer immatures = this.getNumberWithImmatures();
    if (immatures != null)
    {
      i.setNumberWithImmatures(immatures);
    }

    BigDecimal size = this.getPremiseSize();
    if (size != null)
    {
      i.setPremiseSize(size);
    }

    Integer inhabitants = this.getNumberInhabitants();
    if (inhabitants != null)
    {
      i.setNumberInhabitants(inhabitants);
    }
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
    list.add(NUMBERWITHLARVAE);
    list.add(NUMBERWITHPUPAE);
    list.add(NUMBERWITHIMMATURES);
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

  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    context.addListener(createExcelGeoListener(importer));
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener(null));
  }

  private static DynamicGeoColumnListener createExcelGeoListener(ExcelImportManager importer)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllUrban())
    {
      builder.add(hierarchy);
    }
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder, importer);
  }
}
