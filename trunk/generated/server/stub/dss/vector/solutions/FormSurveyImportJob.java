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
package dss.vector.solutions;

import java.io.InputStream;
import java.util.List;

import com.runwaysdk.dataaccess.MdWebFormDAOIF;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.io.excel.ContextBuilderIF;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.dataaccess.metadata.MdFormDAO;
import com.runwaysdk.system.scheduler.ExecutionContext;

import dss.vector.solutions.form.business.FormBedNet;
import dss.vector.solutions.form.business.FormHousehold;
import dss.vector.solutions.form.business.FormPerson;
import dss.vector.solutions.form.business.FormSurvey;
import dss.vector.solutions.generator.ContextBuilderFacade;
import dss.vector.solutions.generator.DefaultContextBuilder;
import dss.vector.solutions.generator.FormBedNetContextBuilder;
import dss.vector.solutions.generator.FormContextBuilder;
import dss.vector.solutions.generator.FormHouseholdContextBuilder;
import dss.vector.solutions.generator.FormImportFilter;
import dss.vector.solutions.generator.FormPersonContextBuilder;
import dss.vector.solutions.generator.MdFormUtil;

public class FormSurveyImportJob extends FormSurveyImportJobBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 842464214;
  
  public FormSurveyImportJob()
  {
    super();
  }
  
  public FormSurveyImportJob(ExcelImportManager manager, InputStream inputStream, String[] params, String fileName)
  {
    super();
    
    this.sharedState = new SharedState();
    this.sharedState.manager = manager;
    this.sharedState.inputStreamIn = inputStream;
    this.sharedState.params = params;
    this.sharedState.fileName = fileName;
  }
  
  @Override
  protected ContextBuilderIF constructContextBuilder()
  {
    MdWebFormDAOIF formSurvey = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormSurvey.FORM_TYPE);
    MdWebFormDAOIF formHousehold = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormHousehold.FORM_TYPE);
    MdWebFormDAOIF formBedNet = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormBedNet.FORM_TYPE);
    MdWebFormDAOIF formPerson = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormPerson.FORM_TYPE);

    ContextBuilderFacade builder = new ContextBuilderFacade(new DefaultContextBuilder(new String[] {}, this.sharedState.manager), this.sharedState.manager);

    builder.add(FormSurvey.CLASS, new FormContextBuilder(formSurvey, new FormImportFilter(), this.sharedState.manager));
    builder.add(FormHousehold.CLASS, new FormHouseholdContextBuilder(this.sharedState.manager));
    builder.add(FormBedNet.CLASS, new FormBedNetContextBuilder(this.sharedState.manager));
    builder.add(FormPerson.CLASS, new FormPersonContextBuilder(this.sharedState.manager));

    MdFormUtil.addGridContexts(formSurvey, builder);
    MdFormUtil.addGridContexts(formHousehold, builder);
    MdFormUtil.addGridContexts(formBedNet, builder);
    MdFormUtil.addGridContexts(formPerson, builder);
    
    return builder;
  }
  
  @Override
  protected void configureImporter(ExcelImporter importer, ExecutionContext execContext)
  {
    super.configureImporter(importer, execContext);
    
    List<ImportContext> contexts = importer.getContexts();

    for (ImportContext context : contexts)
    {
      List<ImportListener> listeners = FormSurvey.getImportListeners(context.getMdClassType());

      for (ImportListener listener : listeners)
      {
        context.addListener(listener);
      }
    }
  }
}
