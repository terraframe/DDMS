package dss.vector.solutions.entomology.assay;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.dataaccess.MdAttributeReferenceDAOIF;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.attributes.InvalidReferenceException;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.InnerJoin;
import com.terraframe.mojo.query.Join;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectableMoment;
import com.terraframe.mojo.query.ValueQuery;

import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.general.InsecticideQuery;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.Surface;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class EfficacyAssay extends EfficacyAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236363373386L;

  public EfficacyAssay()
  {
    super();
  }  
  
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else if(this.getGeoEntity() != null && this.getInsecticide() != null)
    {
      return "(" + this.getGeoEntity().getLabel() + ", " + this.getInsecticide().toString() + ")";
    }
    
    return super.toString();
  }

  @Override
  public void validateAgeRange()
  {
    super.validateAgeRange();

    new AdultAgeRangeValidator(this).validate();
  }

  @Override
  public void validateGravid()
  {
    super.validateGravid();

    new GravidValidator(this).validate();
  }

  @Override
  public void validateFed()
  {
    super.validateFed();

    new FedValidator(this).validate();
  }

  @Override
  public void validateQuantityDead()
  {
    super.validateQuantityDead();

    new QuantityDeadValidator(this).validate();
  }
  
  @Override
  public void validateGeoEntity()
  {
    if(this.getGeoEntity() != null && !(this.getGeoEntity() instanceof Surface))
    {
      throw new InvalidReferenceException("[" + this.getGeoEntity().getId() + "] is not a valid Surface geo id", (MdAttributeReferenceDAOIF) EfficacyAssay.getGeoEntityMd());
    }
  }
  
  @Override
  public void apply()
  {
    validateGeoEntity();
    validateQuantityDead();
    validateAgeRange();
    validateFed();
    validateGravid();

    if (this.getQuantityDead() != null && this.getQuantityTested() != null && this.getQuantityDead() <= this.getQuantityTested())
    {
      float mortality = calculateMortality(this.getQuantityDead(), this.getQuantityTested());

      this.setQuantityLive(this.getQuantityTested() - this.getQuantityDead());
      this.setMortality(mortality);
    }
    else
    {
      this.setQuantityLive(0);
      this.setMortality(new Float(0));
    }

    super.apply();
  }

  private static float calculateMortality(Integer dead, Integer total)
  {
    return ( dead * 100F / total );
  }

  public static EfficacyAssay[] searchByGeoEntityAndDate(GeoEntity geoEntity, Date collectionDate)
  {
    QueryFactory factory = new QueryFactory();
    EfficacyAssayQuery query = new EfficacyAssayQuery(factory);

    query.WHERE(query.getGeoEntity().EQ(geoEntity));
    query.AND(query.getTestDate().EQ(collectionDate));

    OIterator<? extends EfficacyAssay> iterator = query.getIterator();

    try
    {
      List<EfficacyAssay> list = new LinkedList<EfficacyAssay>();

      while (iterator.hasNext())
      {
        list.add(iterator.next());
      }

      return list.toArray(new EfficacyAssay[list.size()]);
    }
    finally
    {
      iterator.close();
    }
  }

  @Override
  public Float getOverallMortalityRate()
  {
    int dead = 0;
    int total = 0;
    EfficacyAssay[] assays = searchByGeoEntityAndDate(this.getGeoEntity(), this.getTestDate());

    for (EfficacyAssay assay : assays)
    {
      dead += assay.getQuantityDead();
      total += assay.getQuantityTested();
    }

    if (total == 0)
    {
      return 0.0F;
    }

    return calculateMortality(dead, total);
  }
  
  public EfficacyAssayView lockView()
  {
    this.lock();
    
    return this.getView();
  }
  
  public EfficacyAssayView unlockView()
  {
    this.unlock();
    
    return this.getView();
  }
  
  public EfficacyAssayView getView()
  {
    EfficacyAssayView view = new EfficacyAssayView();
    
    view.populateView(this);
    
    return view;
  }

  
  public static EfficacyAssayView getView(String id)
  {
    return EfficacyAssay.get(id).getView();
  }
  
  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   *
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer)
  {
    JSONObject queryConfig;
    try
    {
      queryConfig = new JSONObject(config);
    }
    catch (JSONException e1)
    {
      throw new ProgrammingErrorException(e1);
    }
    
    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, queryConfig, layer);   
   
    EfficacyAssayQuery efficacyAssayQuery = (EfficacyAssayQuery) queryMap.get(EfficacyAssay.CLASS);
    
    AbstractAssayQuery abstractAssayQuery = (AbstractAssayQuery) queryMap.get(AbstractAssay.CLASS);
    
    if (efficacyAssayQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery,EfficacyAssay.CLASS,efficacyAssayQuery);
      QueryUtil.joinTermAllpaths(valueQuery, AbstractAssay.CLASS,  efficacyAssayQuery.getTestDate().getDefiningTableAlias());
      if(abstractAssayQuery != null)
      {
        valueQuery.WHERE(abstractAssayQuery.getId().EQ(efficacyAssayQuery.getId()));
      }
      else
      {
        //ensure date is allways joined
        SelectableMoment dateAttribute = efficacyAssayQuery.getTestDate();
        for (Join join : dateAttribute.getJoinStatements())
        {
          valueQuery.WHERE((InnerJoin) join);
        }
      }
    }
    
    InsecticideQuery insecticideQuery = (InsecticideQuery) queryMap.get(Insecticide.CLASS);
    
    if(insecticideQuery != null)
    {
      valueQuery.WHERE(efficacyAssayQuery.getInsecticide().EQ(insecticideQuery));
      QueryUtil.joinTermAllpaths(valueQuery,Insecticide.CLASS,insecticideQuery);
    }
    
    QueryUtil.setTermRestrictions(valueQuery, queryMap);
    
    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);
    
    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);
    
    QueryUtil.setQueryRatio(xml, valueQuery, "COUNT(*)");
    
    return valueQuery; 

  }

}
