/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.ontology;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.runwaysdk.SystemException;
import com.runwaysdk.constants.MdAttributeDimensionInfo;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeRefDAOIF;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeDimensionDAO;
import com.runwaysdk.dataaccess.metadata.MdDimensionDAO;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdAttribute;

import dss.vector.solutions.export.ExcelVersionException;
import dss.vector.solutions.general.Disease;

/**
 * Reads in an excel file that maps Mo Attriutes to term roots. Note that if you
 * run this from a development environment but want results to appear in a
 * deployed container (Run in Eclipse, see in Tomcat), you'll need a profile
 * with database.properties set up for deploy but environment=develop or you'll
 * get ClassCastExceptions. Unfortunately, this cannot be helped.
 *
 * @author Eric Grunzke
 */
public class AttributeRootImporter implements Reloadable
{
  private List<MdDimensionDAOIF> mdDimensions;

  private Disease[]              diseases;

  public AttributeRootImporter()
  {
    this.mdDimensions = MdDimensionDAO.getAllMdDimensions();
    this.diseases = Disease.getAllDiseases();
  }

  @Transaction
  public void read(InputStream stream)
  {
    Iterator<Row> iterator = openStream(stream);

    // Skip the header row
    iterator.next();

    while (iterator.hasNext())
    {
      readRow(iterator.next());
    }
  }

  private void readRow(Row row)
  {
    this.importDefault(row);
    this.importRoots(row);
  }

  private void importDefault(Row row)
  {
    String key = ExcelUtil.getString(row.getCell(0));
    if (key == null)
    {
      return;
    }

    MdAttributeDAOIF mdAttribute = MdAttributeDAO.getByKey(key);
    if (mdAttribute == null)
    {
      throw new DataNotFoundException("Attribute key [" + key + "] not found.", MdTypeDAO.getMdTypeDAO(MdAttribute.CLASS));
    }

    // Iterate over all remaining columns. Each should have a Mo Term ID
    Cell cell = row.getCell(3);

    if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING)
    {
      String termId = ExcelUtil.getString(cell);
      if (termId.length() == 0)
      {
        return;
      }
      Term term = Term.getByTermId(termId);

      // FieldDefaultView view = new FieldDefaultView();
      // view.setMdAttribute(MdAttribute.get(mdAttribute.getId()));
      // view.setDefaultValue(term);
      // view.apply();

      if (term != null)
      {
        MdAttributeConcreteDAOIF mdAttributeConcrete = mdAttribute.getMdAttributeConcrete();

        if (mdAttributeConcrete instanceof MdAttributeRefDAOIF)
        {
          for (MdDimensionDAOIF mdDimension : mdDimensions)
          {
            MdAttributeDimensionDAO mdAttributeDimension = mdAttributeConcrete.getMdAttributeDimension(mdDimension).getBusinessDAO();
            mdAttributeDimension.setValue(MdAttributeDimensionInfo.DEFAULT_VALUE, term.getId());
            mdAttributeDimension.apply();
          }
        }
      }
    }
  }

  private void importRoots(Row row)
  {
    String key = ExcelUtil.getString(row.getCell(0));
    if (key == null)
    {
      return;
    }

    MdAttributeDAOIF mdAttribute = MdAttributeDAO.getByKey(key);

    if (mdAttribute == null)
    {
      throw new DataNotFoundException("Attribute key [" + key + "] not found.", MdTypeDAO.getMdTypeDAO(MdAttribute.CLASS));
    }

    BrowserField browserField = BrowserField.getFieldForAttribute(mdAttribute.definedByClass().definesType(), mdAttribute.definesAttribute());
    List<? extends BrowserRoot> allRoots = browserField.getAllroot().getAll();
    String diseaseName = ExcelUtil.getString(row.getCell(4));

    int i = 5;

    // Iterate over all remaining columns. Each should have a Mo Term ID
    while (row.getCell(i) != null && row.getCell(i + 1) != null)
    {
      String termId = ExcelUtil.getString(row.getCell(i++));
      Term term = Term.getByTermId(termId);
      Boolean selectable = ExcelUtil.getBoolean(row.getCell(i++));
      // String diseaseName = ExcelUtil.getString(row.getCell(i++));

      if (diseaseName == null || diseaseName.length() == 0)
      {
        for (Disease disease : diseases)
        {
          BrowserRoot browserRoot = new BrowserRoot();
          browserRoot.setTerm(term);
          browserRoot.setDisease(disease);

          int index = allRoots.indexOf(browserRoot);

          if (index != -1)
          {
            browserRoot = allRoots.get(index);
            browserRoot.setSelectable(selectable);
            browserRoot.apply();
          }
          else
          {
            browserRoot.setSelectable(selectable);
            browserField.addBrowserRoot(browserRoot);
          }
        }
      }
      else
      {
        Disease disease = Disease.getByKey(diseaseName);
        BrowserRoot browserRoot = new BrowserRoot();
        browserRoot.setTerm(term);
        browserRoot.setDisease(disease);

        int index = allRoots.indexOf(browserRoot);
        if (index != -1)
        {
          browserRoot = allRoots.get(index);
          browserRoot.setSelectable(selectable);
          browserRoot.apply();
        }
        else
        {
          browserRoot.setSelectable(selectable);
          browserField.addBrowserRoot(browserRoot);
        }
      }
    }

    browserField.apply();
  }

  /**
   * Opens the stream and returns an initialized row iterator
   *
   * @param stream
   * @return
   * @throws IOException
   */
  private Iterator<Row> openStream(InputStream stream)
  {
    try
    {
      Workbook workbook = WorkbookFactory.create(stream);
      Sheet sheet = workbook.getSheetAt(0);
      Iterator<Row> rowIterator = sheet.rowIterator();

      return rowIterator;
    }
    catch (OfficeXmlFileException e)
    {
      throw new ExcelVersionException(e);
    }
    catch (IOException e)
    {
      throw new SystemException(e);
    }
    catch (InvalidFormatException e)
    {
      throw new ExcelVersionException(e);
    }
  }

  @Request
  public static void main(String[] args) throws Exception
  {
    String fileName = "AttributeRoots.xls";
    File file = new File(fileName);
    if (args.length == 0)
    {
      if (file.exists())
      {
        System.out.println("No file name specified. Using default location: " + file.getAbsoluteFile());
      }
      else
      {
        System.out.println("No file name specified. Add file name as a comand line argument.");
        return;
      }
    }
    else
    {
      fileName = args[0];
      file = new File(fileName);
    }

    AttributeRootImporter importer = new AttributeRootImporter();
    importer.read(new BufferedInputStream(new FileInputStream(file)));
  }

}
