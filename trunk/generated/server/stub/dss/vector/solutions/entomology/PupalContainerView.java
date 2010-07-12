package dss.vector.solutions.entomology;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.SortedGridComparator;

public class PupalContainerView extends PupalContainerViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 348141061;

  public PupalContainerView()
  {
    super();
  }

  public void populateView(PupalContainer concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setPremise(concrete.getPremise());
    this.setContainerId(concrete.getContainerId());
    this.setContainerType(concrete.getContainerType());
    this.setHeight(concrete.getHeight());
    this.setWidth(concrete.getWidth());
    this.setContainerLength(concrete.getContainerLength());
    this.setOpeningWidth(concrete.getOpeningWidth());
    this.setOpeningLength(concrete.getOpeningLength());
    this.setDiameter(concrete.getDiameter());
    this.setOpeningDiameter(concrete.getOpeningDiameter());
    this.setShading(concrete.getShading());
    this.setLid(concrete.getLid());
    this.setRoof(concrete.getRoof());
    this.setFillMethod(concrete.getFillMethod());
    this.setFillFrequency(concrete.getFillFrequency());
    this.setDrawdownFrequency(concrete.getDrawdownFrequency());
    this.setDrawdownPercent(concrete.getDrawdownPercent());

    this.clearShape();

    for (ContainerShape shape : concrete.getShape())
    {
      this.addShape(shape);
    }
  }

  private void populateConcrete(PupalContainer concrete)
  {
    concrete.setPremise(this.getPremise());
    concrete.setContainerId(this.getContainerId());
    concrete.setContainerType(this.getContainerType());
    concrete.setHeight(this.getHeight());
    concrete.setWidth(this.getWidth());
    concrete.setContainerLength(this.getContainerLength());
    concrete.setOpeningWidth(this.getOpeningWidth());
    concrete.setOpeningLength(this.getOpeningLength());
    concrete.setDiameter(this.getDiameter());
    concrete.setOpeningDiameter(this.getOpeningDiameter());
    concrete.setShading(this.getShading());
    concrete.setLid(this.getLid());
    concrete.setRoof(this.getRoof());
    concrete.setFillMethod(this.getFillMethod());
    concrete.setFillFrequency(this.getFillFrequency());
    concrete.setDrawdownFrequency(this.getDrawdownFrequency());
    concrete.setDrawdownPercent(this.getDrawdownPercent());

    concrete.clearShape();

    for (ContainerShape shape : this.getShape())
    {
      concrete.addShape(shape);
    }
  }

  private void buildAttributeMap(PupalContainer concrete)
  {
    new AttributeNotificationMap(concrete, PupalContainer.ID, this, PupalContainerView.CONCRETEID);
    new AttributeNotificationMap(concrete, PupalContainer.PREMISE, this, PupalContainerView.PREMISE);
    new AttributeNotificationMap(concrete, PupalContainer.CONTAINERTYPE, this, PupalContainerView.CONTAINERTYPE);
    new AttributeNotificationMap(concrete, PupalContainer.HEIGHT, this, PupalContainerView.HEIGHT);
    new AttributeNotificationMap(concrete, PupalContainer.WIDTH, this, PupalContainerView.WIDTH);
    new AttributeNotificationMap(concrete, PupalContainer.CONTAINERLENGTH, this, PupalContainerView.CONTAINERLENGTH);
    new AttributeNotificationMap(concrete, PupalContainer.OPENINGWIDTH, this, PupalContainerView.OPENINGWIDTH);
    new AttributeNotificationMap(concrete, PupalContainer.OPENINGLENGTH, this, PupalContainerView.OPENINGLENGTH);
    new AttributeNotificationMap(concrete, PupalContainer.DIAMETER, this, PupalContainerView.DIAMETER);
    new AttributeNotificationMap(concrete, PupalContainer.OPENINGDIAMETER, this, PupalContainerView.OPENINGDIAMETER);
    new AttributeNotificationMap(concrete, PupalContainer.SHADING, this, PupalContainerView.SHADING);
    new AttributeNotificationMap(concrete, PupalContainer.LID, this, PupalContainerView.LID);
    new AttributeNotificationMap(concrete, PupalContainer.ROOF, this, PupalContainerView.ROOF);
    new AttributeNotificationMap(concrete, PupalContainer.FILLMETHOD, this, PupalContainerView.FILLMETHOD);
    new AttributeNotificationMap(concrete, PupalContainer.FILLFREQUENCY, this, PupalContainerView.FILLFREQUENCY);
    new AttributeNotificationMap(concrete, PupalContainer.DRAWDOWNFREQUENCY, this, PupalContainerView.DRAWDOWNFREQUENCY);
    new AttributeNotificationMap(concrete, PupalContainer.DRAWDOWNPERCENT, this, PupalContainerView.DRAWDOWNPERCENT);
    new AttributeNotificationMap(concrete, PupalContainer.SHAPE, this, PupalContainerView.SHAPE);
  }

  @Override
  @Transaction
  public void apply()
  {
    PupalContainer concrete = new PupalContainer();

    if (this.hasConcrete())
    {
      concrete = PupalContainer.lock(this.getConcreteId());
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Transaction
  public PupalContainerAmountView[] applyWithAmounts(PupalContainerAmountView[] amounts)
  {
    this.apply();

    PupalContainer concrete = PupalContainer.get(this.getConcreteId());

    for (PupalContainerAmountView amount : amounts)
    {
      amount.setContainer(concrete);
      amount.apply();
    }

    return amounts;
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Override
  public PupalContainerAmountView[] getAmounts()
  {
    Term[] terms = Term.getSortedRootChildren(PupalContainerView.getPupaeAmountMd());

    return this.getAmounts(terms);
  }

  public PupalContainerAmountView[] getAmounts(Term[] terms)
  {
    List<PupalContainerAmountView> list = new LinkedList<PupalContainerAmountView>();
    Set<PupalContainerAmount> set = new TreeSet<PupalContainerAmount>(new SortedGridComparator());

    for (Term d : terms)
    {
      set.add(new PupalContainerAmount(this.getId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      PupalContainer c = PupalContainer.get(this.getConcreteId());

      for (PupalContainerAmount d : c.getAllPupalRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    for (PupalContainerAmount container : set)
    {
      list.add(container.getView());
    }

    return list.toArray(new PupalContainerAmountView[set.size()]);
  }

  public static PupalContainerAmountView[][] getAmountsForViews(PupalContainerView[] views)
  {
    Term[] terms = Term.getSortedRootChildren(PupalContainerView.getPupaeAmountMd());

    PupalContainerAmountView[][] amounts = new PupalContainerAmountView[views.length][];

    for (int i = 0; i < views.length; i++)
    {
      amounts[i] = views[i].getAmounts(terms);
    }

    return amounts;
  }
}
