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
/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
package dss.vector.solutions.etl.dhis2.exporter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdTable;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.etl.dhis2.AbstractDHIS2Connector;
import dss.vector.solutions.etl.dhis2.DHIS2ExportableDataset;
import dss.vector.solutions.etl.dhis2.DHIS2HTTPCredentialConnector;

public class DHIS2DataExporter implements Reloadable
{
  private AbstractDHIS2Connector dhis2;
  
  public static void main(String[] args)
  {
    CommandLineParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption(Option.builder("url").hasArg().argName("url").longOpt("url").desc("URL of the DHIS2 server to connect to, including the port. Defaults to: http://127.0.0.1:8085/").optionalArg(true).build());
    options.addOption(Option.builder("username").hasArg().argName("username").longOpt("username").desc("The username of the root (admin) DHIS2 user.").required().build());
    options.addOption(Option.builder("password").hasArg().argName("password").longOpt("password").desc("The password for the root (admin) DHIS2 user.").required().build());
    options.addOption(Option.builder("dataset").hasArg().argName("dataset").longOpt("dataset").desc("The name of the dataset to export.").required().build());
    
    try {
      CommandLine line = parser.parse( options, args );
      
      String url = line.getOptionValue("url");
      String username = line.getOptionValue("username");
      String password = line.getOptionValue("password");
      String dataset = line.getOptionValue("dataset").toLowerCase();
      
      if (url == null)
      {
        url = "http://127.0.0.1:8085/";
      }
      
      mainInRequest(url, username, password, dataset);
    }
    catch (ParseException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  @Request
  private static void mainInRequest(String url, String username, String password, String dataset)
  {
    MdClass mdClass = MdClass.getMdClass(MDSSInfo.GENERATED_TABLE_PACKAGE + "." + dataset);
    
    DHIS2ExportableDataset exportable = new DHIS2ExportableDataset();
    exportable.setDhis2Name(mdClass.getTypeName());
    exportable.setQueryRef((MdTable) mdClass);
    
    DHIS2DataExporter exporter = new DHIS2DataExporter();
    exporter.exportWithCredentials(exportable, "CREATE_AND_UPDATE", url, username, password);
  }
  
  // Our constructor must be 0 arguments because it conforms to Java service loader paradigm.
  public DHIS2DataExporter()
  {
    dhis2 = new DHIS2HTTPCredentialConnector();
  }
  
  public void exportWithCredentials(DHIS2ExportableDataset exportable, String strategy, String url, String username, String password)
  {
    dhis2.setServerUrl(url);
    dhis2.setCredentials(username, password);
    
    actuallyDoExport(exportable, strategy);
  }
  
  public DHIS2ExportResults export(DHIS2ExportableDataset exportable, String strategy)
  {
    // TODO : Maybe some day we'll re-enable this (when DIHS2 gets their act together)
//    if (ExternalProfile.getAccessToken() == null)
//    {
//      OAuthLoginRequiredException ex = new OAuthLoginRequiredException();
//      throw ex;
//    }
    
    dhis2.readConfigFromDB();
    
    return actuallyDoExport(exportable, strategy);
  }
  
  private DHIS2ExportResults actuallyDoExport(DHIS2ExportableDataset exportable, String strategy)
  {
    DHIS2ExportHandler exporter = new DHIS2ExportHandler(exportable, dhis2);
    return exporter.export(strategy);
  }
}
