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
package dss.vector.solutions.export;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttribute;

import dss.vector.solutions.ExcelImportManager;
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
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.odk.MobileImportViewIF;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class MosquitoCollectionExcelView extends MosquitoCollectionExcelViewBase implements com.runwaysdk.generation.loader.Reloadable, MobileImportViewIF
{
  private static final long serialVersionUID = -9941268;

  public MosquitoCollectionExcelView()
  {
    super();
  }
  
  @Override
  public Map<String,String[]> getAttributeSourceMap()
  {
    Map<String,String[]> map = new HashMap<String,String[]>();
    
    map.put(MosquitoCollectionView.CLASS, new String[]{ABUNDANCE, COLLECTIONDATE, COLLECTIONID, COLLECTIONMETHOD, COLLECTIONROUND, COLLECTIONTYPE, DATELASTSPRAYED, GEOENTITY, INSECTICIDEBRAND, LIFESTAGE, NUMBEROFANIMALOCCUPANTS, NUMBEROFHUMANOCCUPANTS, NUMBEROFLLINS, WALLTYPE});
    map.put(SubCollectionView.CLASS, new String[]{SUBCOLLECTIONID, TAXON, EGGS, FEMALESFED, FEMALESGRAVID, FEMALESHALFGRAVID, FEMALESUNFED, DISECTED, IDENTMETHOD, LARVAE, MALE, PAROUS, PUPAE, UNKNOWNS });
    
    return map;
  }
  
  @Override
  public void apply()
  {
    MosquitoCollectionView view = getCollection();
    SubCollectionView[] subcollections = new SubCollectionView[] {};

    if (this.hasSubCollectionValues())
    {
      String subId = this.getSubCollectionId();
      Term taxonTerm = Term.validateByDisplayLabel(this.getTaxon(), SubCollectionView.getTaxonMd());
      Term idMethod = Term.validateByDisplayLabel(this.getIdentMethod(), SubCollectionView.getIdentMethodMd());

      SubCollectionQuery query = new SubCollectionQuery(new QueryFactory());
      query.WHERE(query.getCollection().getId().EQ(view.getConcreteId()));

      if (subId != null && subId.length() > 0)
      {
        query.WHERE(query.getSubCollectionId().EQ(subId));
      }

      if (this.getIdentMethod() != null && this.getIdentMethod().length() > 0)
      {

        query.WHERE(query.getIdentMethod().EQ(idMethod));
      }

      if (this.getTaxon() != null && this.getTaxon().length() > 0)
      {

        query.WHERE(query.getTaxon().EQ(taxonTerm));
      }

      OIterator<? extends SubCollection> iterator = query.getIterator();

      try
      {
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

        sub.setEggs(this.getEggs());
        sub.setMale(this.getMale());
        sub.setLarvae(this.getLarvae());
        sub.setPupae(this.getPupae());
        sub.setUnknowns(this.getUnknowns());
        sub.setFemalesFed(this.getFemalesFed());
        sub.setFemalesGravid(this.getFemalesGravid());
        sub.setFemalesHalfGravid(this.getFemalesHalfGravid());
        sub.setFemalesUnfed(this.getFemalesUnfed());
        sub.setFemalesUnknown(this.getFemalesUnknown());
        sub.setParous(this.getParous());
        sub.setDisected(this.getDisected());

        subcollections = new SubCollectionView[] { sub };
      }
      finally
      {
        iterator.close();
      }
    }

    view.applyAll(subcollections);
  }

  private boolean hasSubCollectionValues()
  {
    List<String> attributes = new LinkedList<String>();
    attributes.add(SUBCOLLECTIONID);
    attributes.add(TAXON);
    attributes.add(IDENTMETHOD);
    attributes.add(EGGS);
    attributes.add(MALE);
    attributes.add(LARVAE);
    attributes.add(PUPAE);
    attributes.add(UNKNOWNS);
    attributes.add(FEMALESFED);
    attributes.add(FEMALESGRAVID);
    attributes.add(FEMALESHALFGRAVID);
    attributes.add(FEMALESUNFED);
    attributes.add(UNKNOWNS);
    attributes.add(PAROUS);
    attributes.add(DISECTED);

    for (String attribute : attributes)
    {
      if (this.getValue(attribute) != null && this.getValue(attribute).length() > 0)
      {
        return true;
      }
    }

    return false;
  }

  private MosquitoCollectionView getCollection()
  {
    MosquitoCollectionView view = new MosquitoCollectionView();
    String cid = this.getCollectionId();

    MosquitoCollectionQuery query = new MosquitoCollectionQuery(new QueryFactory());
    query.WHERE(query.getCollectionId().EQ(cid));
    OIterator<? extends MosquitoCollection> iterator = query.getIterator();
    try
    {
      if (iterator.hasNext())
      {
        view = iterator.next().getView();
      }
      else
      {
        if (cid.length() == 0)
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
    }
    finally
    {
      iterator.close();
    }

    Term colMethod = Term.validateByDisplayLabel(this.getCollectionMethod(), MosquitoCollectionView.getCollectionMethodMd());
    if (colMethod != null)
    {
      view.setCollectionMethod(colMethod);
    }

    Date colDate = this.getCollectionDate();
    if (colDate != null)
    {
      view.setCollectionDate(colDate);
    }

    GeoEntity geo = this.getGeoEntity();
    if (geo != null)
    {
      view.setGeoEntity(geo);
    }

    Boolean abund = this.getAbundance();
    if (abund != null)
    {
      view.setAbundance(abund);
    }

    LifeStage life = ExcelEnums.getLifeStage(this.getLifeStage());
    if (life != null)
    {
      view.addLifeStage(life);
    }

    Term collectionRound = Term.validateByDisplayLabel(this.getCollectionRound(), MosquitoCollectionView.getCollectionRoundMd());

    if (collectionRound != null)
    {
      view.setCollectionRound(collectionRound);
    }

    Term collectionType = Term.validateByDisplayLabel(this.getCollectionType(), MosquitoCollectionView.getCollectionTypeMd());

    if (collectionType != null)
    {
      view.setCollectionType(collectionType);
    }

    if (this.getInsecticideBrand() != null && this.getInsecticideBrand().length() > 0)
    {
      InsecticideBrand insecticideBrand = InsecticideBrand.validateByName(this.getInsecticideBrand());

      if (insecticideBrand != null)
      {
        view.setInsecticideBrand(insecticideBrand);
      }
    }
    else
    {
      view.setInsecticideBrand(null);
    }

    Date dateLastSprayed = this.getDateLastSprayed();
    if (dateLastSprayed != null)
    {
      view.setDateLastSprayed(dateLastSprayed);
    }

    Term wallType = Term.validateByDisplayLabel(this.getWallType(), MosquitoCollectionView.getWallTypeMd());

    if (wallType != null)
    {
      view.setWallType(wallType);
    }

    Integer numberOfHumanOccupants = this.getNumberOfHumanOccupants();
    if (numberOfHumanOccupants != null)
    {
      view.setNumberOfHumanOccupants(numberOfHumanOccupants);
    }

    Integer numberOfAnimalOccupants = this.getNumberOfAnimalOccupants();
    if (numberOfAnimalOccupants != null)
    {
      view.setNumberOfAnimalOccupants(numberOfAnimalOccupants);
    }

    Integer numberOfLLINs = this.getNumberOfLLINs();

    if (numberOfLLINs != null)
    {
      view.setNumberOfLLINs(numberOfLLINs);
    }

    return view;
  }

  public LinkedList<String> getAttributeOrder()
  {
    return customAttributeOrder();
  }
  
  public static LinkedList<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(COLLECTIONMETHOD);
    list.add(COLLECTIONDATE);
    list.add(COLLECTIONID);
    list.add(ABUNDANCE);
    list.add(LIFESTAGE);
    list.add(COLLECTIONROUND);
    list.add(COLLECTIONTYPE);
    list.add(DATELASTSPRAYED);
    list.add(WALLTYPE);
    list.add(INSECTICIDEBRAND);
    list.add(NUMBEROFHUMANOCCUPANTS);
    list.add(NUMBEROFANIMALOCCUPANTS);
    list.add(NUMBEROFLLINS);
    list.add(SUBCOLLECTIONID);
    list.add(IDENTMETHOD);
    list.add(TAXON);
    list.add(EGGS);
    list.add(LARVAE);
    list.add(PUPAE);
    list.add(FEMALESUNFED);
    list.add(FEMALESFED);
    list.add(FEMALESHALFGRAVID);
    list.add(FEMALESGRAVID);
    list.add(FEMALESUNKNOWN);
    list.add(MALE);
    list.add(UNKNOWNS);
    list.add(DISECTED);
    list.add(PAROUS);
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
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllPoliticals())
    {
      builder.add(hierarchy);
    }
    builder.add(GeoHierarchy.getGeoHierarchyFromType(CollectionSite.CLASS));
    builder.add(GeoHierarchy.getGeoHierarchyFromType(SentinelSite.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder, importer);
  }  
}
