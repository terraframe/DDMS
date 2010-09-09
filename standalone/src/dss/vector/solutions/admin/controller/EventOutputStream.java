package dss.vector.solutions.admin.controller;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.core.runtime.IProgressMonitor;

public class EventOutputStream extends OutputStream
{
  public StringBuilder builder;
  
  private IProgressMonitor monitor;
  
  public EventOutputStream(IProgressMonitor monitor)
  {
    super();

    this.monitor = monitor;
    this.builder = new StringBuilder();
  }

  private synchronized void append(String string)
  {
    if(string.equals("\n") || string.startsWith("\n"))
    {
      monitor.subTask(builder.toString());
      builder = new StringBuilder();
    }
    else
    {
      builder.append(string);
    }
  }

  @Override
  public void write(byte[] b) throws IOException
  {
    this.append(new String(b));
  }

  @Override
  public void write(int b) throws IOException
  {
    this.append(new String(new byte[] { (byte) b }));
  }
}
