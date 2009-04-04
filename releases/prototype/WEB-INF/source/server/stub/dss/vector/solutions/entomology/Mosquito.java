package dss.vector.solutions.entomology;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryParser;

import dss.vector.solutions.entomology.assay.AssayTestResult;
import dss.vector.solutions.entomology.assay.AssayTestResultQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.SentinalSite;
import dss.vector.solutions.geo.generated.SentinalSiteQuery;
import dss.vector.solutions.mo.SpecieQuery;

public class Mosquito extends MosquitoBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288152336L;
  
  public Mosquito()
  {
    super();
  }
  
  public List<AssayTestResult> getTestResults()
  {
    List<AssayTestResult> list = new LinkedList<AssayTestResult>();
    AssayTestResultQuery query = new AssayTestResultQuery(new QueryFactory());
    
    query.WHERE(query.getMosquito().EQ(this));
    
    OIterator<? extends AssayTestResult> iterator = query.getIterator();
    
    while(iterator.hasNext())
    {
      list.add(iterator.next());
    }
    
    iterator.close();
    
    return list;    
  }
    
  @Override
  public void delete()
  {
    //DELETE all of the mosquito test results first
    for(AssayTestResult result : this.getTestResults())
    {
      result.delete();
    }
        
    super.delete();
  }
  
  public MosquitoView getView()
  {
    MosquitoView view = new MosquitoView();
    
    view.setSpecie(this.getSpecie());
    view.setCollection(this.getCollection());
    view.setGeneration(this.getGeneration());
    view.setIsofemale(this.getIsofemale());
    view.setIdentificationMethod(this.getIdentificationMethod());
    view.setTestDate(this.getTestDate());
    view.setMosquitoId(this.getId());
    view.setSampleId(this.getSampleId());

    if(this.getSex().size() > 0)
    {
      view.addSex(this.getSex().get(0));
    }
    
    try
    {
      view.setAssays(this.getTestResults());
    }
    catch(Exception e)
    {
      throw new RuntimeException(e);
    }
    
    view.applyNoPersist();
    
    return view;
  }
  
  public AssayTestResult getTestResult(Class<AssayTestResult> c)
  {
    for(AssayTestResult result : this.getTestResults())
    {
      if(c.isInstance(result))
      {
        return result;
      }
    }
    
    return null;
  }
  
  /**
   * Queries for Mosquitos.
   * 
   * @param xml
   */
  public static com.terraframe.mojo.query.ValueQuery queryEntomology(String xml)
  {
    QueryFactory queryFactory = new QueryFactory();
    
    ValueQuery valueQuery = new ValueQuery(queryFactory);
    
    ValueQueryParser valueQueryParser = new ValueQueryParser(xml, valueQuery);

    Map<String, GeneratedEntityQuery> queryMap = valueQueryParser.parse();

    GeoHierarchy.addGeoHierarchyJoinConditions(valueQuery, queryMap);
    
    MosquitoQuery mosquitoQuery = (MosquitoQuery) queryMap.get(Mosquito.CLASS);

    // join Mosquito with mosquito collection
    MosquitoCollectionQuery collectionQuery = new MosquitoCollectionQuery(queryFactory);
    valueQuery.WHERE(mosquitoQuery.getCollection().EQ(collectionQuery));
    
    // join collection with geo entity
    SentinalSiteQuery ssQuery = (SentinalSiteQuery) queryMap.get(SentinalSite.CLASS);
    valueQuery.WHERE(collectionQuery.getGeoEntity().EQ(ssQuery));
    
    String sql = valueQuery.getSQL();
    System.out.println(sql);
    
    return valueQuery;
  }
  
  public static String mapQuery(String xml)
  {
    return null; // return DB::View
  }
}