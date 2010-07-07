package dss.vector.solutions.intervention.monitor;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.InsecticideBrandView;
import dss.vector.solutions.surveillance.ChildOption;
import dss.vector.solutions.surveillance.OptionIF;

public class InsecticideInterventionView extends InsecticideInterventionViewBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = -1003698351;
  
  public InsecticideInterventionView()
  {
    super();
  }

  public OptionIF getChild()
  {
    return this.getInterventionMethod();
  }
 
  public void populateView(InsecticideIntervention concrete)
  {
    InsecticideBrand _insecticide = concrete.getInsecticide();
    
    this.setConcreteId(concrete.getId());
    this.setIntervention(concrete.getIntervention());
    this.setInterventionMethod(concrete.getInterventionMethod());
    this.setInsecticide(_insecticide);
    this.setQuantity(concrete.getQuantity());
    this.setUnit(concrete.getUnit());
    
    if(_insecticide != null)
    {
      this.setActiveIngredient(_insecticide.getActiveIngredient().getTermDisplayLabel().getValue());
      this.setConcentrationQuantifier(_insecticide.getConcentrationQuantifier());
      this.setConcentrationQualifier(_insecticide.getConcentrationQualifier().get(0).getDisplayLabel());
    }
  }

  private void populateConcrete(InsecticideIntervention concrete)
  {
    concrete.setIntervention(this.getIntervention());
    concrete.setInterventionMethod(this.getInterventionMethod());
    concrete.setInsecticide(this.getInsecticide());
    concrete.setQuantity(this.getQuantity());
    concrete.setUnit(this.getUnit());
  }

  private void buildAttributeMap(InsecticideIntervention concrete)
  {
    new AttributeNotificationMap(concrete, InsecticideIntervention.ID, this, InsecticideInterventionView.CONCRETEID);
    new AttributeNotificationMap(concrete, InsecticideIntervention.INTERVENTION, this, InsecticideInterventionView.INTERVENTION);
    new AttributeNotificationMap(concrete, InsecticideIntervention.INTERVENTIONMETHOD, this, InsecticideInterventionView.INTERVENTIONMETHOD);
    new AttributeNotificationMap(concrete, InsecticideIntervention.INSECTICIDE, this, InsecticideInterventionView.INSECTICIDE);
    new AttributeNotificationMap(concrete, InsecticideIntervention.QUANTITY, this, InsecticideInterventionView.QUANTITY);
    new AttributeNotificationMap(concrete, InsecticideIntervention.UNIT, this, InsecticideInterventionView.UNIT);
  }

  @Override
  @Transaction
  public void apply()
  {
    InsecticideIntervention concrete = new InsecticideIntervention();

    if (this.hasConcrete())
    {
      concrete = InsecticideIntervention.lock(this.getConcreteId());
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }
  
  @Override
  public InsecticideBrandView getInsecticideView()
  {
    InsecticideBrand _insecticide = this.getInsecticide();
    
    if(_insecticide != null)
    {
      return _insecticide.getView();
    }
    
    return null;
  }
  
  public InsecticideInterventionView clone()
  {
    InsecticideBrand _insecticide = this.getInsecticide();
    
    InsecticideInterventionView view = new InsecticideInterventionView();
    view.setConcreteId(this.getConcreteId());
    view.setIntervention(this.getIntervention());
    view.setInterventionMethod(this.getInterventionMethod());
    view.setInsecticide(_insecticide);
    view.setQuantity(this.getQuantity());
    view.setUnit(this.getUnit());    
    
    if(_insecticide != null)
    {
      view.setActiveIngredient(_insecticide.getActiveIngredient().getTermDisplayLabel().getValue());
      view.setConcentrationQuantifier(_insecticide.getConcentrationQuantifier());
      view.setConcentrationQualifier(_insecticide.getConcentrationQualifier().get(0).getDisplayLabel());
    }

    return view;    
  }
}
