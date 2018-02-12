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
package dss.vector.solutions.entomology.assay;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import dss.vector.solutions.ontology.Term;

public abstract class CollectionAssay extends CollectionAssayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236893774938L;

  public CollectionAssay()
  {
    super();
  }

  @Override
  public void validateIsofemale()
  {
    super.validateIsofemale();

    if (this.getIsofemale() && this.isGenerationF0())
    {
      String msg = "Isofemale line cannot be selected if the generation is F0.";

      InvalidGenerationProblem p = new InvalidGenerationProblem(msg);
      p.setNotification(this, ISOFEMALE);
      p.apply();
      p.throwIt();
    }
  }

  private boolean isGenerationF0()
  {
    Term gen = this.getGeneration();

    if (gen != null)
    {
      List<String> ids = new LinkedList<String>();
      ids.add("MIRO_343458349");

      return ids.contains(gen.getTermId());
    }

    return false;
  }

  @Override
  public void validateTestDate()
  {
    if (this.getTestDate() != null && this.getCollection() != null)
    {
      super.validateTestDate();

      Date collectionDate = this.getCollection().getCollectionDate();

      if (this.getTestDate().before(collectionDate))
      {
        String msg = "It is impossible to have a test date before the mosquito collection date";

        InvalidTestDateProblem p = new InvalidTestDateProblem(msg);
        p.setTestDate(this.getTestDate());
        p.setCollectionDate(collectionDate);
        p.setNotification(this, TESTDATE);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void apply()
  {
    validateIsofemale();

    super.apply();
  }

  public static String getCollectionResistanceSQL(String assayTable, String mortality, String resistant, String susceptible, String[] labels)
  {

    String resistance_result = "resistance_result";

    String susceptibleLabel = labels[0];
    String potentialyResistantLabel = labels[1];
    String resistantLabel = labels[2];

    String select = "SELECT assay.id AS id,\n";
    String from = "FROM  " + assayTable + " AS assay,\n";
    String where = "";

    select += "(CASE WHEN (assay." + mortality + " < " + resistant + ") THEN '" + resistantLabel + "'\n";
    select += "WHEN (assay." + mortality + " < " + susceptible + ")  THEN '" + potentialyResistantLabel + "'\n";
    select += "ELSE '" + susceptibleLabel + "' END) AS " + resistance_result + ",\n";

    select = select.substring(0, select.length() - 2);
    // where = "WHERE " + where.substring(3, where.length() - 2);
    from = from.substring(0, from.length() - 2);

    return select + "\n" + from + "\n" + where;
  }

}
