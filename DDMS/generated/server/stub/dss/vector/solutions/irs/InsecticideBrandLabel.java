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
package dss.vector.solutions.irs;

public class InsecticideBrandLabel extends InsecticideBrandLabelBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1906755034;

  public InsecticideBrandLabel()
  {
    super();
  }

  public static InsecticideBrandLabel getLabel(String id)
  {
    if (id != null && id.length() > 0)
    {
      InsecticideBrand concrete = InsecticideBrand.get(id);

      if (concrete != null)
      {
        InsecticideBrandLabel label = new InsecticideBrandLabel();
        label.setConcreteId(concrete.getId());
        label.setProductName(concrete.getProductName().getTermDisplayLabel().getValue());
        label.setActiveIngredient(concrete.getActiveIngredient().getTermDisplayLabel().getValue());
        label.setConcentrationQuantifier(concrete.getConcentrationQuantifier());
        label.setConcentrationQualifier(concrete.getConcentrationQualifier().get(0).getDisplayLabel());

        return label;
      }
    }

    return null;
  }

}
