package dss.vector.solutions.entomology;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.Function;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MalariaSeasonDateProblem;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.PupalContainerCollectionQB;

public class PupalCollection extends PupalCollectionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1932493411;

  public PupalCollection()
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
    // DELETE ALL PupalPremise
    List<PupalPremise> premises = this.getPremises();

    for (PupalPremise premise : premises)
    {
      premise.delete();
    }

    this.delete();
  }

  public boolean hasPremises()
  {
    return ( this.getPremises().size() > 0 );
  }

  private List<PupalPremise> getPremises()
  {
    PupalPremiseQuery query = new PupalPremiseQuery(new QueryFactory());
    query.WHERE(query.getCollection().EQ(this));

    OIterator<? extends PupalPremise> it = query.getIterator();

    try
    {
      List<? extends PupalPremise> premises = it.getAll();

      return new LinkedList<PupalPremise>(premises);
    }
    finally
    {
      it.close();
    }
  }

  @Override
  @Transaction
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
      if (start.after(end))
      {
        MalariaSeasonDateProblem p = new MalariaSeasonDateProblem();
        p.apply();

        p.throwIt();
      }
    }
  }

  private void populateCollectionId()
  {
    if (this.getCollectionId() == null || this.getCollectionId().trim().length() == 0)
    {
      this.setCollectionId(LocalProperty.getNextId());
    }
  }

  public PupalCollectionView getView()
  {
    PupalCollectionView view = new PupalCollectionView();

    view.populateView(this, null);

    return view;
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    return new PupalContainerCollectionQB(xml, config, layer, pageSize, pageSize, disease).construct();
  }

  static boolean getSelectabeTermRelationSQL(ValueQuery valueQuery, String ref, String sql)
  {
    if (valueQuery.hasSelectableRef(ref))
    {
      Selectable s = valueQuery.getSelectableRef(ref);

      while (s instanceof Function)
      {
        Function f = (Function) s;
        s = f.getSelectable();
      }

      ( (SelectableSQL) s ).setSQL(sql);
      return true;
    }
    return false;
  }

}
