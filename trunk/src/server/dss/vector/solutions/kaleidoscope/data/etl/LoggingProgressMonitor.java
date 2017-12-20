package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.generation.loader.Reloadable;

public class LoggingProgressMonitor implements ProgressMonitorIF, Reloadable
{
//  private static Logger logger = LoggerFactory.getLogger(ImportRunnable.class);
  
  private DataImportState state;
  
  private int currentRow;
  
  private String filename;
  
  public LoggingProgressMonitor(String filename)
  {
    this.currentRow = 0;
    this.setFilename(filename);
    this.setState(DataImportState.INITIAL);
  }
  
  public void setFilename(String filename)
  {
    this.filename = filename;
  }
  
  public void setState(DataImportState state)
  {
    this.state = state;
    
    System.out.println("Spreadsheet importer entering state [" + state.toString() + "] on file [" + filename + "].");
  }
  
  public DataImportState getState()
  {
    return this.state;
  }
  
  public void setCurrentRow(int rowNum)
  {
    this.currentRow = rowNum;
    
    if (rowNum % 50 == 0)
    {
      System.out.println("Spreadsheet importer processing row [" + rowNum + "]");
    }
  }
  
  public int getCurrentRow()
  {
    return this.currentRow;
  }

  @Override
  public void setTotal(int total)
  {
    
  }

  @Override
  public void finished()
  {
    
  }

  @Override
  public void entityImported(TargetDefinitionIF entity)
  {
    
  }
}
