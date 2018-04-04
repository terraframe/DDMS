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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Savepoint;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.business.SmartException;
import com.runwaysdk.dataaccess.DuplicateGraphPathException;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;
import com.runwaysdk.session.Session;

import dss.vector.solutions.export.ExcelVersionException;

/**
 * Imports an excel (xls) ontology file into the DDMS.
 *
 * @author rrowlands
 *
 */
public class OntologyExcelImporter implements Reloadable
{
  private static final Logger         logger = LoggerFactory.getLogger(OntologyExcelImporter.class);

  private Map<String, TermNode>       terms;

  private Deque<TermNode>             stack;

  private static Ontology             ontology;

  private static OntologyRelationship ontologyRelationship;

  private boolean                     activeByDisease;

  private String                      fileName;

  public static void main(String[] args) throws FileNotFoundException
  {
    long start = System.currentTimeMillis();

    String fileName = "Ontology.xls";
    File file = new File(fileName);
    if (args.length == 0)
    {
      if (file.exists())
      {
        logger.info("No file name specified. Using default location: " + file.getAbsoluteFile());
      }
      else
      {
        logger.info("No file name specified. Add file name as a comand line argument.");
        return;
      }
    }
    else
    {
      fileName = args[0];
      file = new File(fileName);
    }

    try
    {
      logger.info("Importing ontology file [" + file.getAbsolutePath() + "]");

      readRequest(file);

      long end = System.currentTimeMillis();
      logger.info("Imported in " + ( end - start ) / 1000.0 + " seconds");
    }
    finally
    {
      CacheShutdown.shutdown();
    }
  }

  @Request
  public static void readRequest(File file) throws FileNotFoundException
  {
    try
    {
      Class<?> thisClass = LoaderDecorator.load("dss.vector.solutions.ontology.OntologyExcelImporter");
      Object inst = thisClass.getConstructor(new Class<?>[] { String.class }).newInstance(file.getAbsolutePath());
      thisClass.getMethod("read", new Class<?>[] { InputStream.class }).invoke(inst, new BufferedInputStream(new FileInputStream(file)));
    }
    catch (InstantiationException e)
    {
      throw new RuntimeException(e);
    }
    catch (IllegalAccessException e)
    {
      throw new RuntimeException(e);
    }
    catch (SecurityException e)
    {
      throw new RuntimeException(e);
    }
    catch (NoSuchMethodException e)
    {
      throw new RuntimeException(e);
    }
    catch (IllegalArgumentException e)
    {
      throw new RuntimeException(e);
    }
    catch (InvocationTargetException e)
    {
      throw new RuntimeException(e);
    }
  }

  public OntologyExcelImporter(String fileName)
  {
    terms = new HashMap<String, TermNode>();
    stack = new LinkedList<TermNode>();
    this.fileName = fileName;
  }

  @Transaction
  public void read(InputStream stream)
  {
    Iterator<Row> iterator = openStream(stream);

    // Skip the header row
    readHeader(iterator.next());

    RootTerm rootTerm = new RootTerm();

    QueryFactory f = new QueryFactory();
    RootTermQuery q = new RootTermQuery(f);
    OIterator<? extends RootTerm> rootIterator = q.getIterator();

    try
    {
      if (rootIterator.hasNext())
      {
        rootTerm = rootIterator.next();
      }
      else
      {
        rootTerm.setTermId("ROOT");
        rootTerm.setName("ROOT");
        rootTerm.getTermDisplayLabel().setValue("Root");
        rootTerm.setOntology(getOntology());
        rootTerm.apply();
      }
    }
    finally
    {
      rootIterator.close();
    }

    TermNode root = new TermNode(rootTerm, activeByDisease);

    // indent=0 means this will always be on the stack
    root.setIndent(0);
    stack.addFirst(root);

    while (iterator.hasNext())
    {
      Row row = iterator.next();

      try
      {
        readRow(row);
      }
      catch (Exception e)
      {
        String message = e.getLocalizedMessage();

        if (e instanceof SmartException)
        {
          message = ( (SmartException) e ).localize(Session.getCurrentLocale());
        }

        TermImportException ex = new TermImportException(e);
        ex.setRow(row.getRowNum());
        ex.setExceptionMessage(message);
        ex.apply();

        throw ex;
      }

    }

    for (TermNode node : terms.values())
    {
      node.apply();
    }

    for (TermNode node : terms.values())
    {
      node.applyRelationships();
    }
  }

  private void readHeader(Row row)
  {
    Iterator<Cell> iterator = row.cellIterator();
    Cell cell;

    while (iterator.hasNext())
    {
      cell = iterator.next();
      String header = ExcelUtil.getString(cell);

      if (cell.getColumnIndex() == 1)
      {
        activeByDisease = header.toLowerCase().contains("malaria");
      }
    }
  }

  @SuppressWarnings("unchecked")
  private void readRow(Row row)
  {
    Iterator<Cell> iterator = row.cellIterator();

    Cell cell = iterator.next();

    // The first item should be the term Id. If it's not present, we need to
    // skip the row
    if (cell.getColumnIndex() != 0)
    {
      return;
    }

    try
    {
      String id = ExcelUtil.getString(cell);
      TermNode node = getNodeById(id);
      if (node == null)
      {
        node = new TermNode(activeByDisease);
        node.setId(id);
      }
      cell = iterator.next();

      TermNode parentNode = null;
      if (!activeByDisease)
      {
        if (cell.getColumnIndex() == 1)
        {
          node.setActive(ExcelUtil.getBoolean(cell));
          cell = iterator.next();
        }

        if (cell.getColumnIndex() == 2)
        {
          parentNode = getNodeById(ExcelUtil.getString(cell));
          cell = iterator.next();
        }
      }
      else
      {
        if (cell.getColumnIndex() == 1)
        {
          node.setActiveMalaria(ExcelUtil.getBoolean(cell));
          cell = iterator.next();
        }

        if (cell.getColumnIndex() == 2)
        {
          node.setActiveDengue(ExcelUtil.getBoolean(cell));
          cell = iterator.next();
        }

        if (cell.getColumnIndex() == 3)
        {
          parentNode = getNodeById(ExcelUtil.getString(cell));
          cell = iterator.next();
        }
      }

      // By here, we know that index >=4, and should contain the name
      String nodeName = ExcelUtil.getString(cell);

      // It's possible there are blank cells. Ignore them.
      while (nodeName.length() == 0)
      {
        cell = iterator.next();
        nodeName = ExcelUtil.getString(cell);
      }
      node.setName(nodeName);
      node.setIndent(cell.getColumnIndex());

      // Pop the stack until the top has an indent less than the current row.
      TermNode peek = stack.peek();
      while (OntologyExcelImporter.greaterIndent(peek, cell.getColumnIndex()))
      {
        stack.removeFirst();
        peek = stack.peek();
      }

      // Push the current node onto the indentation stack
      stack.addFirst(node);

      // If a parent wasn't specified explicitly, use the indentation to assign
      // one
      if (parentNode == null)
      {
        parentNode = peek;
      }
      node.addParent(parentNode);

      terms.put(id, node);
    }
    catch (NoSuchElementException e)
    {
      throw new TermImportInvalidRowException(this.fileName, row.getRowNum(), cell.getColumnIndex(), e);
    }
  }

  private static boolean greaterIndent(TermNode node, Integer index)
  {
    if (node == null)
    {
      return false;
    }

    return node.getIndent() >= index;
  }

  /**
   * Tries to fetch a TermNode first from the cache, then from the db. If found
   * in neither, null is returned.
   * 
   * @param id
   * @return
   */
  private TermNode getNodeById(String id)
  {
    TermNode node = terms.get(id);

    if (node == null)
    {
      TermQuery query = new TermQuery(new QueryFactory());
      query.WHERE(query.getTermId().EQ(id));
      OIterator<? extends Term> iterator = query.getIterator();

      if (iterator.hasNext())
      {
        Term term = iterator.next();
        node = new TermNode(term, activeByDisease);
      }
      iterator.close();
    }

    return node;
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
      throw new TermImportFormatException(e);
    }
    catch (InvalidFormatException e)
    {
      throw new ExcelVersionException(e);
    }
  }

  public static Ontology getOntology()
  {
    if (ontology == null)
    {
      setup();
    }

    return ontology;
  }

  public static OntologyRelationship getOntologyRelationship()
  {
    if (ontologyRelationship == null)
    {
      setup();
    }

    return ontologyRelationship;
  }

  private static void setup()
  {
    try
    {
      ontology = Ontology.getByKey(MO.KEY);
    }
    catch (DataNotFoundException e)
    {
      ontology = new Ontology();
      ontology.setTitle(MO.TITLE);
      ontology.setDefaultNamespace(MO.DEFAULT_NAMESPACE);
      ontology.apply();
    }

    try
    {
      ontologyRelationship = OntologyRelationship.getByKey(OBO.IS_A);
    }
    catch (DataNotFoundException e)
    {
      ontologyRelationship = new OntologyRelationship();
      ontologyRelationship.setRelationshipId(OBO.IS_A);
      ontologyRelationship.setName("is_a");
      ontologyRelationship.apply();

      // create save point
      Savepoint savepoint = Database.setSavepoint();

      try
      {
        ontologyRelationship.addOntology(ontology).apply();
      }
      catch (DuplicateGraphPathException e2)
      {
        // a relationship between this typedef and the ontology already exists
        Database.rollbackSavepoint(savepoint);
      }
      finally
      {
        Database.releaseSavepoint(savepoint);
      }
    }
  }
}
