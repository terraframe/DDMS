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
package dss.vector.solutions.manager.server;

import java.rmi.ConnectException;

import org.apache.catalina.Lifecycle;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import dss.vector.solutions.manager.Localizer;

public enum ServerStatus {
  STARTING(Lifecycle.START_EVENT), STARTED(Lifecycle.AFTER_START_EVENT), STOPPING(Lifecycle.STOP_EVENT), STOPPED(Lifecycle.AFTER_STOP_EVENT), UNAVAILABLE("ELSE");

  private String event;
  
  ServerStatus(String event)
  {
    this.event = event;
  }
  
  public String getEvent()
  {
    return this.event;
  }
  
  /**
   * Used by the DDMS CLI, allows for querying of the server's status.
   * 
   * @param args
   * @throws ParseException
   */
  public static void main(String[] args) throws ParseException
  {
    try
    {
      Options options = new Options();
      options.addOption("g", "getStatus", false, "Returns immediately with the current server status as an integer.");
      options.addOption("s", "waitStart", false, "Returns when the server is started.");
      options.addOption("o", "waitStop", false, "Returns when the server is stopped.");
  
      CommandLineParser parser = new PosixParser();
      CommandLine cmd = parser.parse(options, args);
  
      Server server = new Server();
      
      try 
      {
        server.registerServer();
        server.refresh();
      }
      catch (ConnectException e)
      {
        // This is normal
      }
      catch (Exception e) 
      {
        // This is not so normal
        e.printStackTrace();
      }
      
      // If you change the numbers here you'll need to change them in the manager CLI script as well.
      if (cmd.hasOption("g"))
      {
        if (server.getServerStatus().equals(ServerStatus.STARTED))
        {
          System.exit(10);
        }
        else if (server.getServerStatus().equals(ServerStatus.STARTING))
        {
          System.exit(11);
        }
        else if (server.getServerStatus().equals(ServerStatus.STOPPED))
        {
          System.exit(12);
        }
        else if (server.getServerStatus().equals(ServerStatus.STOPPING))
        {
          System.exit(13);
        }
        else
        {
          System.exit(14);
        }
        return;
      }
      
      ServerStatus desiredStatus = ServerStatus.STARTED;
      String err = "RMI_FAILED_TO_START";
      if (cmd.hasOption("o"))
      {
        desiredStatus = ServerStatus.STOPPED;
        err = "SERVER_NOT_STARTED";
      }
      
      ServerStatus actualStatus = null;
      
      int count = 0;
      while (count < (Server.MAX_ATTEMPTS*40) && !((actualStatus = server.getServerStatus()).equals(desiredStatus)))
      {
        count++;
  
        long wait = System.currentTimeMillis() + Server.WAIT_TIME;
  
        while (System.currentTimeMillis() < wait)
        {
          try
          {
            Thread.sleep(1000L);
          }
          catch (InterruptedException e)
          {
            // Do nothing
          }
        }
  
        try
        {
          server.registerServer();
          server.refresh();
        }
        catch (ConnectException e)
        {
          // This is normal
        }
        catch (Exception e) 
        {
          // This is not so normal
          e.printStackTrace();
        }
      }
  
      if (!actualStatus.equals(desiredStatus))
      {
        // There was a problem connecting to the RMI server even though it
        // should have already just been started. The server must not be
        // started or is being blocked.
        RuntimeException ex = new RuntimeException(Localizer.getMessage("RMI_FAILED_TO_START"));
        ex.printStackTrace();
        System.exit(2);
        return;
      }
      
      System.exit(0);
      return;
    }
    catch (Throwable t)
    {
      t.printStackTrace();
      System.exit(1);
      return;
    }
  }
    
  public static ServerStatus getStatus(String state)
  {
    if (state.equals(Lifecycle.AFTER_START_EVENT))
    {
      return ServerStatus.STARTED;
    }
    else if (state.equals(Lifecycle.AFTER_STOP_EVENT))
    {
      return ServerStatus.STOPPED;
    }
    else if (state.equals(Lifecycle.START_EVENT))
    {
      return ServerStatus.STARTING;
    }
    else if (state.equals(Lifecycle.STOP_EVENT))
    {
      return ServerStatus.STOPPING;
    }
    else
    {
      return ServerStatus.UNAVAILABLE;
    }

  }
}
