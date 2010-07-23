package dss.vector.solutions.ontology;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Savepoint;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.runwaysdk.SystemException;
import com.runwaysdk.dataaccess.DuplicateGraphPathException;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.StartSession;

import dss.vector.solutions.export.ExcelVersionException;

public class OntologyExcelImporter
{
  private Map<String, TermNode> terms;
  private Deque<TermNode> stack;
  private static Ontology ontology;
  private static OntologyRelationship ontologyRelationship;
  
  @StartSession
  public static void main(String[] args) throws FileNotFoundException
  {
    long start = System.currentTimeMillis();
    
    String fileName = "Ontology.xls";
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
    OntologyExcelImporter importer = new OntologyExcelImporter();
    importer.read(new BufferedInputStream(new FileInputStream(file)));

    long end = System.currentTimeMillis();
    System.out.println("Imported in " + (end-start)/1000.0 + " seconds");
  }
  
  public OntologyExcelImporter()
  {
    terms = new HashMap<String, TermNode>();
    stack = new LinkedList<TermNode>();
  }

  @Transaction
  public void read(InputStream stream)
  {
    Iterator<HSSFRow> iterator = openStream(stream);

    // Skip the header row
    iterator.next();
    
    RootTerm rootTerm = new RootTerm();
    rootTerm.setTermId("ROOT");
    rootTerm.setName("ROOT");
    rootTerm.getTermDisplayLabel().setValue("Root");
    rootTerm.setOntology(getOntology());
    rootTerm.apply();
    
    TermNode root = new TermNode(rootTerm);
    // indent=0 means this will always be on the stack
    root.setIndent(0);
    stack.addFirst(root);

    while (iterator.hasNext())
    {
      readRow(iterator.next());
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

  @SuppressWarnings("unchecked")
  private void readRow(HSSFRow row)
  {
    Iterator<HSSFCell> iterator = row.cellIterator();
    
    HSSFCell cell = iterator.next();
    
    // The first item should be the term Id.  If it's not present, we need to skip the row
    if (cell.getColumnIndex()!=0)
    {
      return;
    }
    
    String id = ExcelUtil.getString(cell);
    TermNode node = getNodeById(id);
    if (node==null)
    {
      node = new TermNode();
      node.setId(id);
    }
    cell = iterator.next();
    
    if (cell.getColumnIndex()==1)
    {
      node.setActiveMalaria(ExcelUtil.getBoolean(cell));
      cell = iterator.next();
    }
    
    if (cell.getColumnIndex()==2)
    {
      node.setActiveDengue(ExcelUtil.getBoolean(cell));
      cell = iterator.next();
    }
    
    TermNode parentNode = null;
    if (cell.getColumnIndex()==3)
    {
      parentNode = getNodeById(ExcelUtil.getString(cell));
      cell = iterator.next();
    }
    
    // By here, we know that index >=4, and should contain the name
    String nodeName = ExcelUtil.getString(cell);
    
    // It's possible there are blank cells.  Ignore them.
    while (nodeName.length()==0)
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
    
    // If a parent wasn't specified explicitly, use the indentation to assign one
    if (parentNode==null)
    {
      parentNode = peek;
    }
    node.addParent(parentNode);
    
    terms.put(id, node);
  }
  
  private static boolean greaterIndent(TermNode node, Integer index)
  {
    if (node==null)
    {
      return false;
    }
    
    return node.getIndent()>=index;
  }
  
  /**
   * Tries to fetch a TermNode first from the cache, then from the db.  If found in neither, null is returned.
   * 
   * @param id
   * @return
   */
  private TermNode getNodeById(String id)
  {
    TermNode node = terms.get(id);
    
    if (node==null)
    {
      TermQuery query = new TermQuery(new QueryFactory());
      query.WHERE(query.getTermId().EQ(id));
      OIterator<? extends Term> iterator = query.getIterator();
      
      if (iterator.hasNext())
      {
        Term term = iterator.next();
        node = new TermNode(term);
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
  @SuppressWarnings("unchecked")
  private Iterator<HSSFRow> openStream(InputStream stream)
  {
    try
    {
      POIFSFileSystem fileSystem = new POIFSFileSystem(stream);
      HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
      HSSFSheet sheet = workbook.getSheetAt(0);
      Iterator<HSSFRow> rowIterator = sheet.rowIterator();

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
  }
  
  public static Ontology getOntology()
  {
    if (ontology==null)
    {
      setup();
    }
    
    return ontology;
  }
  
  public static OntologyRelationship getOntologyRelationship()
  {
    if (ontologyRelationship==null)
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
