package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;

public class CanvasInformation implements Reloadable
{
  private double northBound;

  private double southBound;

  private int    savedMapWidth;

  private int    savedMapHeight;

  private int    leftOffset;

  private int    topOffset;

  private int    width;

  private int    height;
  
  public CanvasInformation()
  {
    this.leftOffset = 0;
    this.topOffset = 0;
  }

  public double getNorthBound()
  {
    return northBound;
  }

  public void setNorthBound(double northBound)
  {
    this.northBound = northBound;
  }

  public double getSouthBound()
  {
    return southBound;
  }

  public void setSouthBound(double southBound)
  {
    this.southBound = southBound;
  }

  public int getSavedMapWidth()
  {
    return savedMapWidth;
  }

  public void setSavedMapWidth(int savedMapWidth)
  {
    this.savedMapWidth = savedMapWidth;
  }

  public int getSavedMapHeight()
  {
    return savedMapHeight;
  }

  public void setSavedMapHeight(int savedMapHeight)
  {
    this.savedMapHeight = savedMapHeight;
  }

  public int getLeftOffset()
  {
    return leftOffset;
  }

  public void setLeftOffset(int leftOffset)
  {
    this.leftOffset = leftOffset;
  }

  public int getTopOffset()
  {
    return topOffset;
  }

  public void setTopOffset(int topOffset)
  {
    this.topOffset = topOffset;
  }

  public int getWidth()
  {
    return width;
  }

  public void setWidth(int width)
  {
    this.width = width;
  }

  public int getHeight()
  {
    return height;
  }

  public void setHeight(int height)
  {
    this.height = height;
  }

}
