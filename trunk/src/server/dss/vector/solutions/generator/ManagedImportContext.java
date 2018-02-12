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
package dss.vector.solutions.generator;

import org.apache.poi.ss.usermodel.Sheet;

import com.runwaysdk.ProblemIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.UnknownTermProblem;
import dss.vector.solutions.ontology.UnknownTerm;

public class ManagedImportContext extends ImportContext implements Reloadable
{
  private ExcelImportManager manager;

  public ManagedImportContext(Sheet importSheet, String sheetName, Sheet errorSheet, MdClassDAOIF mdClass, ExcelImportManager manager)
  {
    super(importSheet, sheetName, errorSheet, mdClass);

    this.manager = manager;
  }

  @Override
  public void addProblem(ProblemIF problem)
  {
    super.addProblem(problem);

    if (problem instanceof UnknownTermProblem)
    {
      this.handleTermProblem((UnknownTermProblem) problem);
    }
  }

  @Override
  public void addProblem(String column, MdAttributeDAOIF mdAttribute, ProblemIF problem)
  {
    super.addProblem(column, mdAttribute, problem);

    if (problem instanceof UnknownTermProblem)
    {
      this.handleTermProblem((UnknownTermProblem) problem);
    }
  }

  private void handleTermProblem(UnknownTermProblem p)
  {
    String termName = p.getTermName();

    MdAttributeDAOIF mdAttribute = MdAttributeDAO.get(p.getTermAttributeId());
    MdClassDAOIF mdClass = mdAttribute.definedByClass();

    UnknownTerm unknownTerm = new UnknownTerm();
    unknownTerm.setTermName(termName);
    unknownTerm.setBrowserAttribute(mdAttribute.definesAttribute());
    unknownTerm.setBrowserClass(mdClass.definesType());
    unknownTerm.setAttributeLabel(mdAttribute.getDisplayLabel(Session.getCurrentLocale()));
    unknownTerm.setMdAttributeId(p.getTermAttributeId());

    this.manager.addUnknownTerm(unknownTerm);
  }

}
