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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Stack;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.runwaysdk.SystemException;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.session.Request;

public class OntologyExcelExporter
{
  private static final int NAME_COLUMN = 3;

  private Workbook workbook;
  private Sheet sheet;
  private OutputStream os;
  private int rowCount = 0;
  private Term root;

  @Request
  public static void main(String[] args) throws IOException
  {
    String fileName = "OntologyExport2.xls";
    File file = new File(fileName);
    if (args.length==0)
    {
      System.out.println("No file name specified.  Using default location: " + file.getAbsoluteFile());
    }
    else
    {
      fileName = args[0];
      file = new File(fileName);
    }
    
    long start = System.currentTimeMillis();
    
    exportToFile(file, RootTerm.getRootInstance());

    long end = System.currentTimeMillis();
    System.out.println("Exported in " + (end-start)/1000.0 + " seconds");
  }
  
  public static void exportToFile(File file, Term root)
  {
    BufferedOutputStream bos = null;
    try
    {
      FileOutputStream fos = new FileOutputStream(file);
      bos = new BufferedOutputStream(fos);
      
      new OntologyExcelExporter(root, bos).export();
    }
    catch (IOException e1)
    {
      e1.printStackTrace();
    }
    finally
    {
      try
      {
        bos.close();
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }
  }

  public OntologyExcelExporter(Term root, OutputStream os)
  {
    this.root = root;
    workbook = new XSSFWorkbook();
    sheet = workbook.createSheet();
    this.os = os;
  }
  
  private void exportTerm(Term t, int indent, boolean active)
  {
    Row row = sheet.createRow(rowCount++);
    row.createCell(0).setCellValue(new HSSFRichTextString(t.getTermId()));
    row.createCell(1).setCellValue(active);
    row.createCell(indent++).setCellValue(new HSSFRichTextString(t.getName()));
  }
  
  private void open()
  {
    Row header = sheet.createRow(rowCount++);
    header.createCell(0).setCellValue(new HSSFRichTextString("ID"));
    header.createCell(1).setCellValue(new HSSFRichTextString("Active"));
    header.createCell(2).setCellValue(new HSSFRichTextString("ParentId"));
    header.createCell(NAME_COLUMN).setCellValue(new HSSFRichTextString("Name"));
  }
  
  private void close()
  {
    for (short s=0; s<NAME_COLUMN; s++)
    {
      sheet.autoSizeColumn(s);
    }
    for (int i=NAME_COLUMN; i<40; i++)
    {
      sheet.setColumnWidth(i, 1280);
    }
    
    try
    {
      workbook.write(os);
    }
    catch (IOException e)
    {
      throw new SystemException(e);
    }
  }
  
  private class StackEntry
  {
    private int depth;
    private String id;
    
    private StackEntry(String id, int depth)
    {
      this.depth = depth;
      this.id = id;
    }
    
    public int getDepth()
    {
      return depth;
    }

    public void setDepth(int depth)
    {
      this.depth = depth;
    }

    public String getId()
    {
      return id;
    }

    public void setId(String id)
    {
      this.id = id;
    }
  }
  
  /**
   * Exports the root term and all children.
   * 
   * @param term
   */
  public void export()
  {
    open();

    try
    {
      // Stack results in a depth first traverse
      Stack<StackEntry> sNext = new Stack<StackEntry>();
      sNext.push(new StackEntry(root.getId(), NAME_COLUMN));
      
      while (sNext.size() > 0)
      {
        StackEntry seCurrent = sNext.pop();
        Term tCurrent = Term.get(seCurrent.getId());
        int dCurrent = seCurrent.getDepth();
        
        InactiveProperty inactive = tCurrent.getInactiveByDisease();
        exportTerm(tCurrent, dCurrent, !inactive.getInactive());
        
        OIterator<? extends Term> children = tCurrent.getAllChildTerm();
        for (Term child: children)
        {
          sNext.push(new StackEntry(child.getId(), dCurrent + 1));
        }
      }
    }
    finally
    {
      close();
    }
  }
}
