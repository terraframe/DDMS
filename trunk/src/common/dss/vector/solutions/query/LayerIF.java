package dss.vector.solutions.query;

import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;

public interface LayerIF extends Reloadable
{
  public String getId();

  public String getSldFile();

  public boolean hasThematicVariable();

  public String getViewName();

  public boolean isPoint();

  public StylesIF getDefaultStyles();

  public List<AbstractCategoryIF> getAllCategories();

  public String getThematicColumnAlias();

  public Boolean getShowThematicValue();

  public void updateFile(String fileId);

  public RequestFacadeIF getRequestFacade();
}
