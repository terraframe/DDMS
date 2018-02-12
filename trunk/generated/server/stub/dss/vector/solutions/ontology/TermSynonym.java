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
package dss.vector.solutions.ontology;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

public class TermSynonym extends TermSynonymBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1827568707;

  public TermSynonym()
  {
    super();
  }

  public static void createTermSynonym(String termName, String id)
  {
    Term.get(id).addSynonym(termName);
  }

  public static void createSynonym(String termName, String termId)
  {
    Term.getByTermId(termId).addSynonym(termName);
  }

  public static TermSynonym getByNameAndTerm(String termId, String name)
  {
    QueryFactory qf = new QueryFactory();

    TermQuery tq = new TermQuery(qf);

    TermSynonymQuery tsq = new TermSynonymQuery(qf);
    tsq.WHERE(tsq.getTermName().EQ(name));
    tsq.AND(tsq.term(tq));

    tq.WHERE(tq.getTermId().EQ(termId));

    OIterator<? extends TermSynonym> it = tsq.getIterator();
    if (!it.hasNext())
    {
      return null;
    }
    else
    {
      return it.next();
    }
  }
}
