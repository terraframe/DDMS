/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
