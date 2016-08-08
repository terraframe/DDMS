package dss.vector.solutions.geo;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.SelectablePrimitive;

import dss.vector.solutions.geo.generated.GeoEntity;

public class GeoSynonymArrayView extends GeoSynonymArrayViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -131700559;
  
  public GeoSynonymArrayView()
  {
    super();
  }
  
  public void lock()
  {
    String synonymIds = this.getSynonymIds();
    
    if (synonymIds.length() > 0)
    {
      for (String synonymId : synonymIds.split(","))
      {
        GeoSynonym gs = GeoSynonym.get(synonymId);
        gs.lock();
      }
    }
  }
  
  public void unlock()
  {
    String synonymIds = this.getSynonymIds();
    
    if (synonymIds.length() > 0)
    {
      for (String synonymId : synonymIds.split(","))
      {
        GeoSynonym gs = GeoSynonym.get(synonymId);
        gs.unlock();
      }
    }
  }
  
  public void applyWithSynonyms(GeoSynonymView[] synonymViews)
  {
    // Convert geoId to a geoentity Id
    // Why: The client only knows how to specify geoentities by their geoId, so we did a hack and stuffed the geoId into geoEntityName.
    String geoId = this.getValue(GeoSynonymArrayView.GEOENTITY);
    if (geoId == null || geoId.equals(""))
    {
      String name = this.getGeoEntityName();
      if (name.contains("GEOID="))
      {
        this.setGeoEntity(GeoEntity.getByKey(name.replace("GEOID=", "")));
      }
      else
      {
        return;
      }
    }
    
    
    // Apply all the synonyms they sent us.
    ArrayList<String> ids = new ArrayList<String>();
    for (GeoSynonymView view : synonymViews)
    {
      String synId = view.getGeoSynonymId();
      
      GeoSynonym synonym;
      if (synId == null || synId.equals(""))
      {
        synonym = new GeoSynonym();
      }
      else
      {
        synonym = GeoSynonym.get(view.getGeoSynonymId());
      }
      synonym.setEntityName(view.getSynonymName());
      synonym.apply();
      
      ids.add(synonym.getId());
    }
    this.setSynonymIds(StringUtils.join(ids, ","));
    
    
    // Update the relationships between the synonyms and the geoEntity
    GeoEntity geo = this.getGeoEntity();
    
    OIterator<? extends HasSynonym> geoRelsIt = geo.getAllSynonymsRel();
    while (geoRelsIt.hasNext())
    {
      HasSynonym rel = geoRelsIt.next();
      rel.delete();
    }
    
    for (String synonymId : ids)
    {
      GeoSynonym gs = GeoSynonym.get(synonymId);
      
      OIterator<? extends HasSynonym> it = gs.getAllGeoEntityRel();
      while(it.hasNext())
      {
        it.next().delete();
      }
      
      gs.addGeoEntity(geo).apply();
    }
  }
  
  /**
   * Applies the view to the client request, not the concretes.
   */
  public void applyToCR()
  {
    super.apply();
  }
  
  /**
   * Deletes all GeoSynonyms for a given GeoEntity.
   */
  public void delete()
  {
    for (String synonymId : this.getSynonymIds().split(","))
    {
      GeoSynonym gs = GeoSynonym.get(synonymId);
      gs.delete();
    }
  }
  
  /**
   * MdMethod
   * 
   * @return
   */
  public static dss.vector.solutions.geo.GeoSynonymArrayViewQuery getMostRecent()
  {
    return GeoSynonymArrayViewQuery.search(null);
  }
  
  /**
   * MdMethod
   * 
   * @return
   */
  public static GeoSynonymArrayView getGeoSynonym(String geoEntityId)
  {
    GeoSynonymArrayView searchView = new GeoSynonymArrayView();
    searchView.setGeoEntity(GeoEntity.get(geoEntityId));
    
    GeoSynonymArrayViewQuery query = GeoSynonymArrayViewQuery.search(searchView);
    
    OIterator<? extends GeoSynonymArrayView> it = query.getIterator();
    try
    {
      if (it.hasNext())
      {
        GeoSynonymArrayView view = it.next();
        view.applyToCR();
        return view;
      }
      else
      {
        return null;
      }
    }
    finally
    {
      it.close();
    }
  }
  
  /**
   * MdMethod
   * 
   * @return
   */
  public static dss.vector.solutions.geo.GeoSynonymArrayViewQuery searchByView(GeoSynonymArrayView view, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    GeoSynonymArrayViewQuery query = GeoSynonymArrayViewQuery.search(view);

    if (sortAttribute != null)
    {
      SelectablePrimitive attribute;

      if (sortAttribute.equalsIgnoreCase(GeoSynonymArrayView.GEOTYPEDISPLAYLABEL))
      {
        attribute = (SelectablePrimitive) query.getSelectableRef(GeoSynonymArrayView.GEOTYPEDISPLAYLABEL);
      }
      else if (sortAttribute.equalsIgnoreCase(GeoSynonymArrayView.GEOENTITYNAME))
      {
        attribute = (SelectablePrimitive) query.getSelectableRef(GeoSynonymArrayView.GEOENTITYNAME);
      }
      else if (sortAttribute.equalsIgnoreCase(GeoSynonymArrayView.SYNONYMNAMES))
      {
        attribute = (SelectablePrimitive) query.getSelectableRef(GeoSynonymArrayView.SYNONYMNAMES);
      }
      else
      {
        attribute = (SelectablePrimitive) query.getSelectableRef(GeoSynonymArrayView.GEOENTITY);
      }

      if (isAscending)
      {
        query.ORDER_BY_ASC((SelectablePrimitive) attribute, sortAttribute);
      }
      else
      {
        query.ORDER_BY_DESC((SelectablePrimitive) attribute, sortAttribute);
      }
    }

    query.restrictRows(pageSize, pageNumber);

    return query;
  }
}
