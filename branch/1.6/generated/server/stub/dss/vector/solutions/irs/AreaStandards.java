package dss.vector.solutions.irs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.general.Disease;

public class AreaStandards extends AreaStandardsBase implements Reloadable
{
  class StandardComparator implements Comparator<AreaStandards>, Reloadable
  {
    public int compare(AreaStandards o1, AreaStandards o2)
    {
      Date s1 = o1.getStartDate();
      Date s2 = o2.getStartDate();

      if (s1 == null)
      {
        return -1;
      }

      if (s2 == null)
      {
        return 1;
      }

      int compareTo = s1.compareTo(s2);

      return compareTo;
    }
  }

  private static final long serialVersionUID = 1240597940844L;

  public AreaStandards()
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
    else
    {
      return this.getClassDisplayLabel();
    }
  }

  @Override
  protected String buildKey()
  {
    return this.getId();
  }

  @Override
  @Transaction
  public void apply()
  {
    List<AreaStandards> list = this.getAreaStandards(this.getId());
    list.add(this);

    this.setDates(list);

    for (AreaStandards standards : list)
    {
      standards.directApply();
    }
  }

  @Override
  @Transaction
  public void delete()
  {
    List<AreaStandards> list = this.getAreaStandards(this.getId());

    this.setDates(list);

    for (AreaStandards standards : list)
    {
      standards.directApply();
    }

    super.delete();
  }

  private void setDates(List<AreaStandards> list)
  {
    Collections.sort(list, new StandardComparator());
    Collections.reverse(list);

    for (int i = 0; i < list.size(); i++)
    {
      AreaStandards standard = list.get(i);

      if (!standard.isNew() && !standard.getId().equals(this.getId()))
      {
        standard.lock();
      }

      if (i == 0)
      {
        standard.setEndDate(null);
      }
      else
      {
        AreaStandards previous = list.get(i - 1);

        if (previous.getStartDate() != null)
        {
          Calendar calendar = Calendar.getInstance();
          calendar.clear();
          calendar.setTime(previous.getStartDate());
          calendar.add(Calendar.DAY_OF_YEAR, -1);
          Date _endDate = calendar.getTime();

          standard.setEndDate(_endDate);
        }
      }

      if (i == list.size() - 1)
      {
        standard.setStartDate(null);
      }
    }
  }

  private void directApply()
  {

	if (this.isNew() && this.getDisease() == null) {
	 	this.setDisease(Disease.getCurrent());
	}
	  
    super.apply();
  }

  public void populateView(AreaStandardsView view)
  {
    view.setRoom(this.getRoom());
    view.setStructureArea(this.getStructureArea());
    view.setHousehold(this.getHousehold());
    view.setUnitNozzleAreaCoverage(this.getUnitNozzleAreaCoverage());
    view.setAreaStandardsId(this.getId());
    view.setStartDate(this.getStartDate());
    view.setEndDate(this.getEndDate());

    view.clearTargetUnit();

    for (TargetUnit unit : this.getTargetUnit())
    {
      view.addTargetUnit(unit);
    }
  }

  public AreaStandardsView getView()
  {
    AreaStandardsView view = new AreaStandardsView();

    this.populateView(view);

    return view;
  }

  public static AreaStandardsView getView(String id)
  {
    return AreaStandards.get(id).getView();
  }

  public static AreaStandardsView lockView(String id)
  {
    return AreaStandards.lock(id).getView();
  }

  public static AreaStandardsView unlockView(String id)
  {
    return AreaStandards.unlock(id).getView();
  }

  private List<AreaStandards> getAreaStandards(String id)
  {
    List<AreaStandards> list = new ArrayList<AreaStandards>();
    AreaStandardsQuery query = new AreaStandardsQuery(new QueryFactory());

    query.WHERE(query.getId().NE(id));
    query.ORDER_BY_ASC(query.getStartDate());

    OIterator<? extends AreaStandards> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());

      return list;
    }
    finally
    {
      it.close();
    }
  }

  public static AreaStandards get(Date startDate)
  {
    AreaStandardsQuery query = new AreaStandardsQuery(new QueryFactory());

    query.WHERE(query.getStartDate().EQ(startDate));

    OIterator<? extends AreaStandards> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }
      
      return null;
    }
    finally
    {
      it.close();
    }
  }
}
