package dss.vector.solutions.entomology;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MalariaSeasonDateProblem;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.ImmatureContainerCollectionQB;

public class ImmatureCollection extends ImmatureCollectionBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 746053127;

  public ImmatureCollection()
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

  @Transaction
  public void deleteAll()
  {
    // DELETE ALL CollectionPremise
    List<CollectionPremise> premises = this.getPremises();

    for (CollectionPremise premise : premises)
    {
      premise.deleteAll();
    }

    this.delete();
  }

  public boolean hasPremises()
  {
    return ( this.getPremises().size() > 0 );
  }

  private List<CollectionPremise> getPremises()
  {
    CollectionPremiseQuery query = new CollectionPremiseQuery(new QueryFactory());
    query.WHERE(query.getCollection().EQ(this));

    OIterator<? extends CollectionPremise> it = query.getIterator();

    try
    {
      List<? extends CollectionPremise> premises = it.getAll();

      return new LinkedList<CollectionPremise>(premises);
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
    this.validateStartDate();
    this.validateEndDate();
    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }
    super.apply();
  }

  @Override
  public void validateStartDate()
  {
    Date start = this.getStartDate();

    if (start != null && start.after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(start);
      p.setCurrentDate(new Date());
      p.setNotification(this, STARTDATE);
      p.apply();
      p.throwIt();
    }

    Date end = this.getEndDate();
    if (start != null && end != null)
    {
      if (start.after(this.getEndDate()))
      {
        MalariaSeasonDateProblem p = new MalariaSeasonDateProblem();
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validateEndDate()
  {
    Date end = this.getEndDate();

    if (end != null && end.after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(end);
      p.setCurrentDate(new Date());
      p.setNotification(this, ENDDATE);
      p.apply();
      p.throwIt();
    }
  }

  private void populateCollectionId()
  {
    if (this.getCollectionId() == null || this.getCollectionId().equals(""))
    {
      this.setCollectionId(LocalProperty.getNextId());
    }
  }

  public ImmatureCollectionView getView()
  {
    ImmatureCollectionView view = new ImmatureCollectionView();

    view.populateView(this, null, null);

    return view;
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize)
  {
    return new ImmatureContainerCollectionQB(xml, config, layer, pageSize, pageSize).construct();
  }
}
