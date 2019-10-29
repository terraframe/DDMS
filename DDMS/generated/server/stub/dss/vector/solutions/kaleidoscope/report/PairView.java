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
