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
package dss.vector.solutions.etl.dhis2;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dss.vector.solutions.etl.dhis2.response.HTTPResponse;

abstract public class AbstractDHIS2Connector
{
  HttpClient client;
  
  Logger logger = LoggerFactory.getLogger(AbstractDHIS2Connector.class);
  
  String serverurl;
  
  String username;
  
  String password;
  
  String apiVersion = "26"; // TODO : This should be configurable
  
  public void setCredentials(String username, String password)
  {
    this.username = username;
    this.password = password;
  }
  
  public String getServerUrl()
  {
    return serverurl;
  }
  
  public void setServerUrl(String url)
  {
    if (!url.endsWith("/"))
    {
      url = url + "/";
    }
    
    this.serverurl = url;
  }
  
  synchronized public void initialize()
  {
    this.client = new HttpClient();
  }
  
  public boolean isInitialized()
  {
    return client != null;
  }
  
  abstract public HTTPResponse httpGet(String url, NameValuePair[] params);
  
  abstract public HTTPResponse httpPost(String url, String body);
  
  public HTTPResponse apiGet(String url, NameValuePair[] params)
  {
    if (!url.endsWith(".json"))
    {
      url = url + ".json";
    }
    
    return httpGet("api/" + apiVersion + "/" + url, params);
  }
  
  public HTTPResponse apiPost(String url, String body)
  {
    if (!url.endsWith(".json"))
    {
      url = url + ".json";
    }
    
    return httpPost("api/" + apiVersion + "/" + url, body);
  }
  
  public void readConfigFromDB()
  {
    DHIS2HTTPConfiguration config = DHIS2HTTPConfiguration.getByKey("DEFAULT");
    this.setServerUrl(config.getUrl());
    this.setCredentials(config.getUsername(), config.getPazzword());
  }
  
  public HTTPResponse httpRequest(HttpClient client, HttpMethod method)
  {
    String sResponse = null;
    try
    {
      this.logger.info("Sending request to " + method.getURI());

      // Execute the method.
      int statusCode = client.executeMethod(method);
      
      // Follow Redirects
      if (statusCode == HttpStatus.SC_MOVED_TEMPORARILY || statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_TEMPORARY_REDIRECT || statusCode == HttpStatus.SC_SEE_OTHER)
      {
        this.logger.info("Redirected [" + statusCode + "] to [" + method.getResponseHeader("location").getValue() + "].");
        method.setURI(new URI(method.getResponseHeader("location").getValue(), true, method.getParams().getUriCharset()));
        method.releaseConnection();
        return httpRequest(client, method);
      }
      
      // TODO : we might blow the memory stack here, read this as a stream somehow if possible.
      Header contentTypeHeader = method.getResponseHeader("Content-Type");
      if (contentTypeHeader == null)
      {
        sResponse = new String(method.getResponseBody(), "UTF-8");
      }
      else
      {
        sResponse = method.getResponseBodyAsString();
      }
      
      if (sResponse.length() < 1000)
      {
        this.logger.info("Response string = '" + sResponse + "'.");
      }
      else
      {
        this.logger.info("Receieved a very large response.");
      }

      return new HTTPResponse(sResponse, statusCode);
    }
    catch (HttpException e)
    {
      throw new RuntimeException(e);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      method.releaseConnection();
    }
  }
}
