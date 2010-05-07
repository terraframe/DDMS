package dss.vector.solutions.entomology;

import java.util.List;

import com.runwaysdk.session.Session;

import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.ResponseMaster;
import dss.vector.solutions.intervention.monitor.NotApplicableProblem;

public class PupalContainer extends PupalContainerBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1278907952;

  public PupalContainer()
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
    else if (this.getContainerId() != null)
    {
      return this.getClassDisplayLabel() + " (" + this.getContainerId() + ")";
    }

    return super.toString();
  }

  @Override
  protected String buildKey()
  {
    return this.getContainerId();
  }

  @Override
  public void apply()
  {
    this.populateContainerId();

    this.validateHeight();
    this.validateWidth();
    this.validateContainerLength();
    this.validateOpeningWidth();
    this.validateOpeningLength();
    this.validateDiameter();
    this.validateOpeningDiameter();

    super.apply();
  }

  private void populateContainerId()
  {
    if (this.getContainerId() == null || this.getContainerId().length() == 0)
    {
      this.setContainerId(LocalProperty.getNextId());
    }
  }

  @Override
  public void validateHeight()
  {
    if (this.getHeight() != null)
    {
      List<ContainerShape> _shape = this.getShape();

      if (_shape.size() == 0)
      {
        String msg = "Cannot have a height without a container shape";
        String value = ResponseMaster.getValueForErrorMsg(_shape);

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, HEIGHT);
        p.setInputAttribute(getHeightMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateWidth()
  {
    if (this.getWidth() != null)
    {
      List<ContainerShape> _shape = this.getShape();

      if (_shape.size() == 0 || !_shape.contains(ContainerShape.RECTANGLE))
      {
        String msg = "Cannot have a width without a container shape";
        String value = ResponseMaster.getValueForErrorMsg(_shape);

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, WIDTH);
        p.setInputAttribute(getWidthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateContainerLength()
  {
    if (this.getContainerLength() != null)
    {
      List<ContainerShape> _shape = this.getShape();

      if (_shape.size() == 0 || !_shape.contains(ContainerShape.RECTANGLE))
      {
        String msg = "Cannot have a container without a container shape";
        String value = ResponseMaster.getValueForErrorMsg(_shape);

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, CONTAINERLENGTH);
        p.setInputAttribute(getContainerLengthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }
  
  @Override
  public void validateOpeningWidth()
  {
    if (this.getOpeningWidth() != null)
    {
      List<ContainerShape> _shape = this.getShape();

      if (_shape.size() == 0 || !_shape.contains(ContainerShape.RECTANGLE))
      {
        String msg = "Cannot have an opening width without a container shape";
        String value = ResponseMaster.getValueForErrorMsg(_shape);

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, OPENINGWIDTH);
        p.setInputAttribute(getOpeningWidthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }
  
  @Override
  public void validateOpeningLength()
  {
    if (this.getOpeningLength() != null)
    {
      List<ContainerShape> _shape = this.getShape();

      if (_shape.size() == 0 || !_shape.contains(ContainerShape.RECTANGLE))
      {
        String msg = "Cannot have a opening length without a container shape";
        String value = ResponseMaster.getValueForErrorMsg(_shape);

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, OPENINGLENGTH);
        p.setInputAttribute(getOpeningLengthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }
  
  @Override
  public void validateDiameter()
  {
    if (this.getDiameter() != null)
    {
      List<ContainerShape> _shape = this.getShape();

      if (_shape.size() == 0 || !_shape.contains(ContainerShape.CIRCLE))
      {
        String msg = "Cannot have a diameter without a circular container";
        String value = ResponseMaster.getValueForErrorMsg(_shape);

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, DIAMETER);
        p.setInputAttribute(getDiameterMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }
  
  @Override
  public void validateOpeningDiameter()
  {
    if (this.getOpeningDiameter() != null)
    {
      List<ContainerShape> _shape = this.getShape();

      if (_shape.size() == 0 || !_shape.contains(ContainerShape.CIRCLE))
      {
        String msg = "Cannot have an opening diameter without a circular container";
        String value = ResponseMaster.getValueForErrorMsg(_shape);

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, OPENINGDIAMETER);
        p.setInputAttribute(getOpeningDiameterMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public PupalContainerView getView()
  {
    PupalContainerView view = new PupalContainerView();

    view.populateView(this);

    return view;
  }

}
