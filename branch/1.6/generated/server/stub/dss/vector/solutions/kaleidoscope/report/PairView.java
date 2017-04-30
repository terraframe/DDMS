package dss.vector.solutions.kaleidoscope.report;

import dss.vector.solutions.util.LocalizationFacade;

public class PairView extends PairViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -960208576;

  public PairView()
  {
    super();
  }

  public static PairView create(String value, String key)
  {
    PairView view = new PairView();
    view.setValue(value);
    view.setLabel(LocalizationFacade.getFromBundles(key));

    return view;
  }

  public static PairView createWithLabel(String value, String label)
  {
    PairView view = new PairView();
    view.setValue(value);
    view.setLabel(label);

    return view;
  }
}
