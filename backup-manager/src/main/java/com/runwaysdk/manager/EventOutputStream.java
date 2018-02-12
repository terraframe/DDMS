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
package com.runwaysdk.manager;

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
