package dss.vector.solutions;

import it.geosolutions.geoserver.rest.GeoServerRESTReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.apache.log4j.BasicConfigurator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.json.JSONException;
import org.json.JSONObject;

import au.com.bytecode.opencsv.CSVWriter;

import com.runwaysdk.RunwayExceptionDTO;
import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.constants.ComponentInfo;
import com.runwaysdk.constants.MdAttributeBooleanInfo;
import com.runwaysdk.constants.MdAttributeLocalInfo;
import com.runwaysdk.constants.MdBusinessInfo;
import com.runwaysdk.constants.MdTypeInfo;
import com.runwaysdk.constants.RelationshipInfo;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.attributes.value.Attribute;
import com.runwaysdk.dataaccess.cache.ObjectCache;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.metadata.MdLocalStructDAO;
import com.runwaysdk.dataaccess.metadata.MdStructDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AttributePrimitive;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.F;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Request;
import com.runwaysdk.session.RequestType;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdController;
import com.runwaysdk.system.metadata.MdLocalStruct;
import com.runwaysdk.system.metadata.MdStruct;
import com.runwaysdk.web.WebClientSession;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.GeoEntityView;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyViewQuery;
import dss.vector.solutions.geo.LocatedInQuery;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.query.MapUtil;
import dss.vector.solutions.querybuilder.IRSQB;

public class Sandbox
{
  static
  {
    BasicConfigurator.configure();
  }

  private static final String  NAME_ATTRIBUTE        = "EntityName";

  private static final String  ID_ATTRIBUTE          = "ID_ATT";

  private static final String  GEOMETRY_ATTRIBUTE    = "Location";

  private static final String  PARENT_ATTRIBUTE      = "Parent";

  private static final String  PARENT_TYPE_ATTRIBUTE = "ParentType";

  public static java.util.Date startTime             = new java.util.Date();

  private static int           feedbackMod           = 50;

  private static class A
  {
    private String id;

    private A()
    {
      this.id = new String("foo");
    }

    private String getId()
    {
      return this.id;
    }
  }

  private static void access(A a)
  {
    synchronized (a.getId())
    {
      System.out.println("ACCESS: [" + a.getId().hashCode() + "] : [" + Thread.currentThread() + "]");
    }
  }
  
  private static Iterator<HSSFRow> load(FileInputStream file) throws Exception
  {
    POIFSFileSystem fileSystem = new POIFSFileSystem(file);
    HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
    HSSFSheet sheet = workbook.getSheetAt(0);
    Iterator<HSSFRow> rowI = sheet.rowIterator();
    
    return rowI;
  }

  public static void importFlorida()
  {
    Pattern p = Pattern.compile(".*?(\\{.*\\}).*");

    try
    {
      // FileInputStream file = new
      // FileInputStream("/Users/justin/projects/work/terraframe/hospital/data_old.xls");
      FileInputStream file = new FileInputStream(
          "/Users/justin/projects/work/terraframe/hospital/clean.xls");
      FileInputStream contrib = new FileInputStream(
          "/Users/justin/projects/work/terraframe/hospital/rec_contrib.xls");
      
      // --- CSV
      
      // File out = new
      // File("/Users/justin/projects/work/terraframe/hospital/output.csv");
//      File clean = new File("/Users/justin/projects/work/terraframe/hospital/clean.csv");
//      File locs = new File("/Users/justin/projects/work/terraframe/hospital/location0.csv");
      File imprted = new File("/Users/justin/projects/work/terraframe/hospital/import.csv");
      // File err = new
      // File("/Users/justin/projects/work/terraframe/hospital/errors.csv");
      System.out.println("AV: " + file.available());

      Iterator<HSSFRow> rowI = load(contrib);
      
      Map<String, String> amount = new HashMap<String, String>();
      while(rowI.hasNext())
      {
        HSSFRow row = rowI.next();
        Iterator<HSSFCell> cellI = row.cellIterator();
        String record = getValue(cellI.next());
        String dollar = getValue(cellI.next());
        
        if(record.trim().length() > 0 && dollar.trim().length() > 0)
        {
          String a = dollar.replaceAll("[^\\d]", "");
          if(a.length() == 0)
          {
            continue;
          }
          
          amount.put(record, a);
        }
      }

//      HttpClient client = new HttpClient();

      rowI = load(file);
      
      int count = 0;
      Writer w = new FileWriter(imprted);
      CSVWriter csv = new CSVWriter(w);
      List<String[]> rows = new LinkedList<String[]>();
      Set<String> added = new HashSet<String>();
      Set<String> ids = new HashSet<String>();
      while (rowI.hasNext())
      {
        boolean processed = false;

        HSSFRow row = rowI.next();
        if (count != 0)
        {

          
          Iterator<HSSFCell> cellI = row.cellIterator();

          String record = getValue(cellI.next());

          String msDRG = getValue(cellI.next());

          String msDRGAndDesc = getValue(cellI.next());

          String productLine = getValue(cellI.next());

          String address = getValue(cellI.next());

          String city = getValue(cellI.next());

          String state = getValue(cellI.next());

          String zip = getValue(cellI.next());

          String fullAddr = getValue(cellI.next());
          
          String contrb = amount.containsKey(record) ? amount.get(record) : "";

          String point = getValue(cellI.next());
          point = point.replaceFirst("POINT\\(", "");
          point = point.replace(")", "");
          String[] coords = point.split(" ");
          // swap the x/y
          point = coords[1] + " " + coords[0];

          

          MessageDigest md = MessageDigest.getInstance("MD5");
          
          String id = (new HexBinaryAdapter()).marshal(md.digest(point.getBytes()));
          
          id = id.substring(0, 14);
          
          String[] rowArr = new String[]{
              //Form ID
              record,
              // record
              record,
              
              //MS DRG  
              msDRG,
              
              //MS DRG Desc 
              msDRGAndDesc,
              
              //Product Line   
              productLine,
              
              //Address 
              address,
              
              //State   
              state,
              
              //Zip 
              zip,
              
              //City   
              city,
              
              //Display  Address 
              fullAddr,
              
              // amount contrib
              contrb,
              
              //Location
              "e"+id
          };
          
          csv.writeNext(rowArr);
          
          /*
          if (!added.contains(point))
          {
                    if(ids.contains(id))
          {
            throw new RuntimeException(id);
          }
          else
          {
            ids.add(id);
          }
          
            String[] rowArr = new String[] {
                // entity name
                "e" + id,
                // geo id
                "g" + id,
                // geo type
                "dss.vector.solutions.geo.generated.Location",
                // sub-type
                "",
                // Located In Name/ID
                "Florida",
                // Located In Type
                "dss.vector.solutions.geo.generated.State",
                // activated
                "TRUE",
                // WKT
                "POINT("+point+")"
            };

            csv.writeNext(rowArr);
            added.add(point);

          }
          */

          // if (record.length() != 0 && msDRG.length() != 0 &&
          // msDRGAndDesc.length() != 0
          // && productLine.length() != 0 && address.length() != 0 &&
          // city.length() != 0
          // && state.length() != 0 && zip.length() != 0
          // && ( !point.equals("ERROR") && !point.equals("NULL") &&
          // point.length() != 0 ))
          // {
          // address = address.replaceAll(",", " ");// remove extra commas
          // // that'll trip the CSV
          // String fullAddr = address + ", " + city + ", " + "Florida " + zip;
          //
          // csv.writeNext(new String[] { record, msDRG, msDRGAndDesc,
          // productLine, address, city, state, zip,
          // fullAddr, "POINT(" + point + ")" });
          // // String rowStr =
          // // String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, POINT(%s)\n",
          // // record, msDRG, msDRGAndDesc, productLine, address, city, state,
          // // zip, fullAddr, point);
          // // FileIO.write(clean, rowStr, true);
          // }

          // try
          // {
          //
          // GetMethod get = new GetMethod();
          // get.setPath("http://dev.virtualearth.net/REST/v1/Locations");
          // get.setQueryString(new NameValuePair[] {
          // new NameValuePair("key",
          // "Ap0fqhkPL7lSRlB79pq74ZmW-i91HHd6y8MsQ--0GoD7MzCp8v9SOQJJuXG8sguW"),
          // new NameValuePair("jsonp", "GeocodeCallback"), new
          // NameValuePair("jsono", fullAddr),
          // new NameValuePair("noCacheIE",
          // Long.toString(System.currentTimeMillis())),
          // new NameValuePair("o", "json"), new NameValuePair("q", fullAddr)
          // });
          //
          //
          // int result = client.executeMethod(get);
          // if (result == 200)
          // {
          // String response = get.getResponseBodyAsString();
          // Matcher m = p.matcher(response);
          // if (m.matches())
          // {
          // String json = m.group(1);
          // JSONObject o = new JSONObject(json);
          // JSONArray coords = o.getJSONArray("resourceSets").getJSONObject(0)
          // .getJSONArray("resources").getJSONObject(0).getJSONObject("point")
          // .getJSONArray("coordinates");
          // String x = coords.getString(0);
          // String y = coords.getString(1);
          //
          // String coord = x + " " + y;
          // FileIO.write(out, record + ", \"" + displayAddr + "\", " + coord +
          // "\n", true);
          // processed = true;
          // }
          // }
          // else
          // {
          // FileIO.write(out, record + ", \"" + displayAddr + "\", NULL\n",
          // true);
          // FileIO.write(err, record + ", \"" + displayAddr +
          // "\", "+result+"\n", true);
          // }
          // }
          // catch (Throwable t)
          // {
          // FileIO.write(out, record + ", \"" + displayAddr + "\", ERROR\n",
          // true);
          // FileIO.write(err, record + ", \"" + displayAddr +
          // "\", "+t.getLocalizedMessage()+"\n", true);
          // }
        }

        count++;
      }

      // csv.writeAll(rows);
      csv.close();
    }
    catch (Throwable t)
    {
      t.printStackTrace(System.out);
    }

  }
  
  public static void main(String[] args) throws Exception
  {
    try
    {
     WebClientSession s = WebClientSession.createUserSession("ddms", "ddms",
     new Locale[]{Locale.ENGLISH});
     ClientRequestIF r = s.getRequest();

     
     irs(r.getSessionId()); 
    }
    catch(Throwable t)
    {
      if(t instanceof RunwayExceptionDTO)
      {
        String dev = ((RunwayExceptionDTO)t).getDeveloperMessage();
        System.out.println(dev);
      }
      t.printStackTrace();
    }
  }

  private static String getValue(HSSFCell cell)
  {
    try
    {
      return ExcelUtil.getString(cell);
    }
    catch (IllegalStateException e)
    {
      return "";
    }
  }

  @Request(RequestType.SESSION)
  private static void irs(String sessionId)
  {
    Disease.setCurrentDimension();

    Integer page = 1;
    Integer size = 100;
    // test vq.getCountSQL(); // efficient?
    String config = "{\"selectedUniversals\":{\"dss.vector.solutions.irs.AbstractSpray.geoEntity\":[]},\"criteriaEntities\":{\"dss.vector.solutions.irs.AbstractSpray.geoEntity\":[]},\"terms\":{},\"date_attribute\":{\"date_attribute\":\"sprayDate\",\"klass\":\"dss.vector.solutions.irs.OperatorSpray\",\"start\":null,\"end\":null},\"sortOrder\":[\"sprayDate\",\"sprayedHouseholds\",\"room_unsprayed\",\"sprayedunits\",\"surfaceType_spray\",\"geoEntity\"]}";

    // spray details
    String minXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>aggregation_level</name>\n<userAlias>aggregation_level</userAlias>\n<userDisplayLabel>Aggregation level</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>\n";

    String partialXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqldate><name>sprayDate</name>\n<userAlias>sprayDate</userAlias>\n<userDisplayLabel>Spray date</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqldate>\n</selectable>\n<selectable><sqlinteger><name>sprayedHouseholds</name>\n<userAlias>sprayedHouseholds</userAlias>\n<userDisplayLabel>Households sprayed #</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>room_unsprayed</name>\n<userAlias>room_unsprayed</userAlias>\n<userDisplayLabel>Unsprayed rooms</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>sprayedunits</name>\n<userAlias>sprayedunits</userAlias>\n<userDisplayLabel>Units sprayed #</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlcharacter><name>surfaceType_displayLabel</name>\n<userAlias>surfaceType_spray</userAlias>\n<userDisplayLabel>Surface type</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>geoEntity</name>\n<userAlias>geoEntity</userAlias>\n<userDisplayLabel>Geo entity</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>\n";

    String maxXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqldate><name>sprayDate</name>\n<userAlias>sprayDate</userAlias>\n<userDisplayLabel>Spray date</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqldate>\n</selectable>\n<selectable><sqlinteger><name>sprayedHouseholds</name>\n<userAlias>sprayedHouseholds</userAlias>\n<userDisplayLabel>Households sprayed #</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>room_unsprayed</name>\n<userAlias>room_unsprayed</userAlias>\n<userDisplayLabel>Unsprayed rooms</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>sprayedunits</name>\n<userAlias>sprayedunits</userAlias>\n<userDisplayLabel>Units sprayed #</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlcharacter><name>surfaceType_displayLabel</name>\n<userAlias>surfaceType_spray</userAlias>\n<userDisplayLabel>Surface type</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>geoEntity</name>\n<userAlias>geoEntity</userAlias>\n<userDisplayLabel>Geo entity</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>aggregation_level</name>\n<userAlias>aggregation_level</userAlias>\n<userDisplayLabel>Aggregation level</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>sprayMethod_displayLabel</name>\n<userAlias>sprayMethod_spray</userAlias>\n<userDisplayLabel>Spray method</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlinteger><name>households</name>\n<userAlias>households</userAlias>\n<userDisplayLabel>Households #</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>structures</name>\n<userAlias>structures</userAlias>\n<userDisplayLabel>Structures #</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>sprayedStructures</name>\n<userAlias>sprayedStructures</userAlias>\n<userDisplayLabel>Structures sprayed #</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>rooms</name>\n<userAlias>rooms</userAlias>\n<userDisplayLabel>Rooms #</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>sprayedRooms</name>\n<userAlias>sprayedRooms</userAlias>\n<userDisplayLabel>Rooms sprayed #</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>locked</name>\n<userAlias>locked</userAlias>\n<userDisplayLabel>Not sprayed (locked) #</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>household_unsprayed</name>\n<userAlias>household_unsprayed</userAlias>\n<userDisplayLabel>Unsprayed households</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>structure_unsprayed</name>\n<userAlias>structure_unsprayed</userAlias>\n<userDisplayLabel>Unsprayed structures</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>unit_unsprayed</name>\n<userAlias>unit_unsprayed</userAlias>\n<userDisplayLabel>Unsprayed units</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>refused</name>\n<userAlias>refused</userAlias>\n<userDisplayLabel>Not sprayed (refused) #</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>other</name>\n<userAlias>other</userAlias>\n<userDisplayLabel>Not sprayed (other) #</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>wrongSurface</name>\n<userAlias>wrongSurface</userAlias>\n<userDisplayLabel>Not sprayed (wrong surface) #</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>\n";

    String unsprayed = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlinteger><name>household_unsprayed</name>\n<userAlias>household_unsprayed</userAlias>\n<userDisplayLabel>Unsprayed households</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>structure_unsprayed</name>\n<userAlias>structure_unsprayed</userAlias>\n<userDisplayLabel>Unsprayed structures</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>room_unsprayed</name>\n<userAlias>room_unsprayed</userAlias>\n<userDisplayLabel>Unsprayed rooms</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>unit_unsprayed</name>\n<userAlias>unit_unsprayed</userAlias>\n<userDisplayLabel>Unsprayed units</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>\n";

    // team details
    String teamDetails = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.Person</type>\n<alias>dss.vector.solutions.Person</alias>\n<criteria></criteria>\n</entity>\n<entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>sprayoperator_defaultLocale</name>\n<userAlias>sprayoperator_defaultLocale</userAlias>\n<userDisplayLabel>Spray operator</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>sprayoperator_personId</name>\n<userAlias>sprayoperator_personId</userAlias>\n<userDisplayLabel>???sprayoperator_personId???</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqldate><name>sprayoperator_birthdate</name>\n<userAlias>sprayoperator_birthdate</userAlias>\n<userDisplayLabel>???sprayoperator_birthdate???</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqldate>\n</selectable>\n<selectable><sqlcharacter><name>sprayoperator_sex_displayLabel</name>\n<userAlias>sprayoperator_sex</userAlias>\n<userDisplayLabel>???sprayoperator_sex???</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>sprayteam_defaultLocale</name>\n<userAlias>sprayteam_defaultLocale</userAlias>\n<userDisplayLabel>Spray team</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>sprayleader_defaultLocale</name>\n<userAlias>sprayleader_defaultLocale</userAlias>\n<userDisplayLabel>Spray team leader</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>sprayleader_personId</name>\n<userAlias>sprayleader_personId</userAlias>\n<userDisplayLabel>???sprayleader_personId???</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqldate><name>sprayleader_birthdate</name>\n<userAlias>sprayleader_birthdate</userAlias>\n<userDisplayLabel>???sprayleader_birthdate???</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqldate>\n</selectable>\n<selectable><sqlcharacter><name>sprayleader_sex_displayLabel</name>\n<userAlias>sprayleader_sex</userAlias>\n<userDisplayLabel>???sprayleader_sex???</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>zone_supervisor_defaultLocale</name>\n<userAlias>zone_supervisor_defaultLocale</userAlias>\n<userDisplayLabel>Supervisor</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>zone_supervisor_personId</name>\n<userAlias>zone_supervisor_personId</userAlias>\n<userDisplayLabel>???zone_supervisor_personId???</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqldate><name>zone_supervisor_birthdate</name>\n<userAlias>zone_supervisor_birthdate</userAlias>\n<userDisplayLabel>???zone_supervisor_birthdate???</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqldate>\n</selectable>\n<selectable><sqlcharacter><name>zone_supervisor_sex_displayLabel</name>\n<userAlias>zone_supervisor_sex</userAlias>\n<userDisplayLabel>???zone_supervisor_sex???</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>\n";

    // TODO test aggregates
    String insecticide = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.InsecticideBrand</type>\n<alias>dss.vector.solutions.irs.InsecticideBrand</alias>\n<criteria></criteria>\n</entity>\n<entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>productName_displayLabel</name>\n<userAlias>productName_spray</userAlias>\n<userDisplayLabel>Product name</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>activeIngredient_displayLabel</name>\n<userAlias>activeIngredient_spray</userAlias>\n<userDisplayLabel>Active ingredient</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><attribute><entityAlias>dss.vector.solutions.irs.InsecticideBrand</entityAlias>\n<name>concentrationQuantifier.displayLabel.currentValue</name>\n<userAlias>concentrationQuantifier_spray</userAlias>\n<userDisplayLabel>Concentration quantifier</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><sqlcharacter><name>concentrationQualifier_displayLabel</name>\n<userAlias>concentrationQualifier_spray</userAlias>\n<userDisplayLabel>Concentration qualifier</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>insecticideUse_displayLabel</name>\n<userAlias>insecticideUse_spray</userAlias>\n<userDisplayLabel>Use</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>useDetail_displayLabel</name>\n<userAlias>useDetail_spray</userAlias>\n<userDisplayLabel>Use detail</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><attribute><entityAlias>dss.vector.solutions.irs.InsecticideBrand</entityAlias>\n<name>unitsPerApplication.displayLabel.currentValue</name>\n<userAlias>unitsPerApplication_spray</userAlias>\n<userDisplayLabel>Units per application</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><attribute><entityAlias>dss.vector.solutions.irs.InsecticideBrand</entityAlias>\n<name>unitQuantifier.displayLabel.currentValue</name>\n<userAlias>unitQuantifier_spray</userAlias>\n<userDisplayLabel>Unit quantifier</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><sqlcharacter><name>unitQualifier_displayLabel</name>\n<userAlias>unitQualifier_spray</userAlias>\n<userDisplayLabel>Unit qualifier</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqldouble><name>active_ingredient_per_can</name>\n<userAlias>active_ingredient_per_can</userAlias>\n<userDisplayLabel>Active ingredient per can (g)</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqldouble><name>standard_application_rate</name>\n<userAlias>standard_application_rate</userAlias>\n<userDisplayLabel>Recommended application rate (g/m&amp;sup2;)</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqldouble><name>standard_application_rate_mg</name>\n<userAlias>standard_application_rate_mg</userAlias>\n<userDisplayLabel>Recommended application rate (mg/m&amp;sup2;)</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqldouble><name>units_per_can</name>\n<userAlias>units_per_can</userAlias>\n<userDisplayLabel>Sprayable units per can</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqlinteger><name>received</name>\n<userAlias>received</userAlias>\n<userDisplayLabel>Number of sachets received</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>used</name>\n<userAlias>used</userAlias>\n<userDisplayLabel>Number of sachets used</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>refills</name>\n<userAlias>refills</userAlias>\n<userDisplayLabel>Number of pump/can refills</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>returned</name>\n<userAlias>returned</userAlias>\n<userDisplayLabel>Number of sachets returned</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>\n";

    // calculations
    String calcs = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.InsecticideBrand</type>\n<alias>dss.vector.solutions.irs.InsecticideBrand</alias>\n<criteria></criteria>\n</entity>\n<entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlfloat><name>unit_application_rate</name>\n<userAlias>unit_application_rate</userAlias>\n<userDisplayLabel>Unit application rate (g/m&amp;sup2;)(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlfloat>\n</selectable>\n<selectable><sqlcharacter><name>productName_displayLabel</name>\n<userAlias>productName_spray</userAlias>\n<userDisplayLabel>Product name</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlfloat><name>unit_application_rate_mg</name>\n<userAlias>unit_application_rate_mg</userAlias>\n<userDisplayLabel>Unit application rate (mg/m&amp;sup2;)(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlfloat>\n</selectable>\n<selectable><sqldouble><name>unit_application_ratio</name>\n<userAlias>unit_application_ratio</userAlias>\n<userDisplayLabel>Unit application ratio(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqldouble><name>unit_operational_coverage</name>\n<userAlias>unit_operational_coverage</userAlias>\n<userDisplayLabel>Unit operational coverage(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqldouble><name>calculated_rooms_sprayed</name>\n<userAlias>calculated_rooms_sprayed</userAlias>\n<userDisplayLabel>Calculated # rooms sprayed(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqldouble><name>calculated_structures_sprayed</name>\n<userAlias>calculated_structures_sprayed</userAlias>\n<userDisplayLabel>Calculated # structures sprayed(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqldouble><name>calculated_households_sprayed</name>\n<userAlias>calculated_households_sprayed</userAlias>\n<userDisplayLabel>Calculated # households sprayed(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";

    // audit
    String audit = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqldate><name>audit_createDate</name>\n<userAlias>audit_createDate</userAlias>\n<userDisplayLabel>Create date</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqldate>\n</selectable>\n<selectable><sqldate><name>audit_lastUpdateDate</name>\n<userAlias>audit_lastUpdateDate</userAlias>\n<userDisplayLabel>Last Update Date</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqldate>\n</selectable>\n<selectable><sqlcharacter><name>audit_createdBy</name>\n<userAlias>audit_createdBy</userAlias>\n<userDisplayLabel>Created By</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>audit_lastUpdatedBy</name>\n<userAlias>audit_lastUpdatedBy</userAlias>\n<userDisplayLabel>Last Updated By</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>audit_imported</name>\n<userAlias>audit_imported</userAlias>\n<userDisplayLabel>Imported</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>\n";

    // error: sprayable units per can
    String supc = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqldouble><name>units_per_can</name>\n<userAlias>units_per_can</userAlias>\n<userDisplayLabel>Sprayable units per can</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqldouble>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";

    String time = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>DATEGROUP_SEASON</name>\n<userAlias>dategroup_season</userAlias>\n<userDisplayLabel>Transmission season</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_YEAR</name>\n<userAlias>dategroup_year</userAlias>\n<userDisplayLabel>Calendar year</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_QUARTER</name>\n<userAlias>dategroup_quarter</userAlias>\n<userDisplayLabel>Quarter</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_MONTH</name>\n<userAlias>dategroup_month</userAlias>\n<userDisplayLabel>Month</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIYEAR</name>\n<userAlias>dategroup_epiyear</userAlias>\n<userDisplayLabel>Epi year</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><count><selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray</entityAlias>\n<name>id</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray__id</userAlias>\n<userDisplayLabel>Count</userDisplayLabel>\n</attribute>\n</selectable>\n<userAlias>dss_vector_solutions_irs_AbstractSpray__id</userAlias>\n<userDisplayLabel>Count</userDisplayLabel>\n</count>\n</selectable>\n<selectable><sqlcharacter><name>ratio_of_this_row_to_total_count</name>\n<userAlias>ratio_of_this_row_to_total_count</userAlias>\n<userDisplayLabel>Ratio</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";

    // Operator
    String operAll = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>sprayoperator_defaultLocale</name>\n<userAlias>sprayoperator_defaultLocale</userAlias>\n<userDisplayLabel>Spray operator</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlinteger><name>operator_actual_target</name>\n<userAlias>operator_actual_target</userAlias>\n<userDisplayLabel>Operator Actual Target(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlinteger><name>operator_planned_target</name>\n<userAlias>operator_planned_target</userAlias>\n<userDisplayLabel>Operator Planned Target(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqldouble><name>operator_planned_coverage</name>\n<userAlias>operator_planned_coverage</userAlias>\n<userDisplayLabel>Operator Planned Coverage(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqldouble><name>operator_target_divergence</name>\n<userAlias>operator_target_divergence</userAlias>\n<userDisplayLabel>Operator Target Divergence(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqldouble><name>operator_targeted_coverage</name>\n<userAlias>operator_targeted_coverage</userAlias>\n<userDisplayLabel>Operator Targeted Coverage(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sum><selectable><sqlinteger><name>sprayedunits</name>\n<userAlias>sprayedunits</userAlias>\n<userDisplayLabel>Units sprayed #</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<userAlias>sprayedunits</userAlias>\n<userDisplayLabel>(SUM) Units sprayed #</userDisplayLabel>\n</sum>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    
    // planned
    String operPlanned = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>sprayoperator_defaultLocale</name>\n<userAlias>sprayoperator_defaultLocale</userAlias>\n<userDisplayLabel>Spray operator</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlinteger><name>operator_planned_target</name>\n<userAlias>operator_planned_target</userAlias>\n<userDisplayLabel>Operator Planned Target(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    
    // actual
    String operActual = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>sprayoperator_defaultLocale</name>\n<userAlias>sprayoperator_defaultLocale</userAlias>\n<userDisplayLabel>Spray operator</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlinteger><name>operator_actual_target</name>\n<userAlias>operator_actual_target</userAlias>\n<userDisplayLabel>Operator Actual Target(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    
    // units/planned
    String operPlannedCover = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>sprayoperator_defaultLocale</name>\n<userAlias>sprayoperator_defaultLocale</userAlias>\n<userDisplayLabel>Spray operator</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqldouble><name>operator_planned_coverage</name>\n<userAlias>operator_planned_coverage</userAlias>\n<userDisplayLabel>Operator Planned Coverage(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    
    // actual/planned
    String operTargetDiver = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>sprayoperator_defaultLocale</name>\n<userAlias>sprayoperator_defaultLocale</userAlias>\n<userDisplayLabel>Spray operator</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqldouble><name>operator_target_divergence</name>\n<userAlias>operator_target_divergence</userAlias>\n<userDisplayLabel>Operator Target Divergence(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    
    // units/actual
    String operTargetCover = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>sprayoperator_defaultLocale</name>\n<userAlias>sprayoperator_defaultLocale</userAlias>\n<userDisplayLabel>Spray operator</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqldouble><name>operator_targeted_coverage</name>\n<userAlias>operator_targeted_coverage</userAlias>\n<userDisplayLabel>Operator Targeted Coverage(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    
    
    // Teams
    
    // actual
    String teamActuals = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>sprayteam_defaultLocale</name>\n<userAlias>sprayteam_defaultLocale</userAlias>\n<userDisplayLabel>Spray team</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlinteger><name>team_actual_target</name>\n<userAlias>team_actual_target</userAlias>\n<userDisplayLabel>Team Actual Target(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    
    // planned
    String teamPlanned = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlinteger><name>team_planned_target</name>\n<userAlias>team_planned_target</userAlias>\n<userDisplayLabel>Team Planned Target(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlcharacter><name>sprayteam_defaultLocale</name>\n<userAlias>sprayteam_defaultLocale</userAlias>\n<userDisplayLabel>Spray team</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    
    // units/planned
    String teamPlannedCoverage = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>sprayteam_defaultLocale</name>\n<userAlias>sprayteam_defaultLocale</userAlias>\n<userDisplayLabel>Spray team</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqldouble><name>team_planned_coverage</name>\n<userAlias>team_planned_coverage</userAlias>\n<userDisplayLabel>Team Planned Coverage(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    
    // actual/planned    
    String teamTargetDivergence = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>sprayteam_defaultLocale</name>\n<userAlias>sprayteam_defaultLocale</userAlias>\n<userDisplayLabel>Spray team</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqldouble><name>team_target_divergence</name>\n<userAlias>team_target_divergence</userAlias>\n<userDisplayLabel>Team Target Divergence(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    
    // units/actual
    String teamTargetCoverage = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>sprayteam_defaultLocale</name>\n<userAlias>sprayteam_defaultLocale</userAlias>\n<userDisplayLabel>Spray team</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqldouble><name>team_targeted_coverage</name>\n<userAlias>team_targeted_coverage</userAlias>\n<userDisplayLabel>Team Targeted Coverage(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
      
    // planned + universal
    String teamPlannedUni = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>entityLabel</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_entityLabel</userAlias>\n<userDisplayLabel>Country Entity name (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>geoId</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_geoId</userAlias>\n<userDisplayLabel>Country Geo entity ID (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><sqlcharacter><name>sprayteam_defaultLocale</name>\n<userAlias>sprayteam_defaultLocale</userAlias>\n<userDisplayLabel>Spray team</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlinteger><name>team_planned_target</name>\n<userAlias>team_planned_target</userAlias>\n<userDisplayLabel>Team Planned Target(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    String teamPLannedUniConfig = "{\"selectedUniversals\":{\"dss.vector.solutions.irs.AbstractSpray.geoEntity\":[\"dss.vector.solutions.geo.generated.Country\"]},\"criteriaEntities\":{\"dss.vector.solutions.irs.AbstractSpray.geoEntity\":[]},\"terms\":{},\"date_attribute\":{\"date_attribute\":\"sprayDate\",\"klass\":\"dss.vector.solutions.irs.OperatorSpray\",\"start\":null,\"end\":null},\"sortOrder\":[\"dategroup_epiweek\",\"sprayteam_defaultLocale\",\"dss_vector_solutions_irs_AbstractSpray_geoEntity__country_entityLabel\",\"dss_vector_solutions_irs_AbstractSpray_geoEntity__country_geoId\",\"team_planned_target\"]}";
    
    
    String areaComplex = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>entityLabel</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_entityLabel</userAlias>\n<userDisplayLabel>Country Entity name (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>geoId</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_geoId</userAlias>\n<userDisplayLabel>Country Geo entity ID (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><sqlinteger><name>area_planned_target</name>\n<userAlias>area_planned_target</userAlias>\n<userDisplayLabel>Area Planned Target(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqldouble><name>area_planned_coverage</name>\n<userAlias>area_planned_coverage</userAlias>\n<userDisplayLabel>Area Planned Coverage(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqldouble><name>team_targeted_coverage</name>\n<userAlias>team_targeted_coverage</userAlias>\n<userDisplayLabel>Team Targeted Coverage(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqlcharacter><name>sprayteam_defaultLocale</name>\n<userAlias>sprayteam_defaultLocale</userAlias>\n<userDisplayLabel>Spray team</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    String areaSimple = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>entityLabel</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_entityLabel</userAlias>\n<userDisplayLabel>Country Entity name (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>geoId</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_geoId</userAlias>\n<userDisplayLabel>Country Geo entity ID (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><sqlinteger><name>area_planned_target</name>\n<userAlias>area_planned_target</userAlias>\n<userDisplayLabel>Area Planned Target(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    String areaTime = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>entityLabel</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_entityLabel</userAlias>\n<userDisplayLabel>Country Entity name (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>geoId</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_geoId</userAlias>\n<userDisplayLabel>Country Geo entity ID (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><sqlinteger><name>area_planned_target</name>\n<userAlias>area_planned_target</userAlias>\n<userDisplayLabel>Area Planned Target(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_SEASON</name>\n<userAlias>dategroup_season</userAlias>\n<userDisplayLabel>Transmission season</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_YEAR</name>\n<userAlias>dategroup_year</userAlias>\n<userDisplayLabel>Calendar year</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_MONTH</name>\n<userAlias>dategroup_month</userAlias>\n<userDisplayLabel>Month</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_QUARTER</name>\n<userAlias>dategroup_quarter</userAlias>\n<userDisplayLabel>Quarter</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIYEAR</name>\n<userAlias>dategroup_epiyear</userAlias>\n<userDisplayLabel>Epi year</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><count><selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray</entityAlias>\n<name>id</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray__id</userAlias>\n<userDisplayLabel>Count</userDisplayLabel>\n</attribute>\n</selectable>\n<userAlias>dss_vector_solutions_irs_AbstractSpray__id</userAlias>\n<userDisplayLabel>Count</userDisplayLabel>\n</count>\n</selectable>\n<selectable><sqlcharacter><name>ratio_of_this_row_to_total_count</name>\n<userAlias>ratio_of_this_row_to_total_count</userAlias>\n<userDisplayLabel>Ratio</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    String areaAudit = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>entityLabel</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_entityLabel</userAlias>\n<userDisplayLabel>Country Entity name (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>geoId</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_geoId</userAlias>\n<userDisplayLabel>Country Geo entity ID (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable><selectable><sqlinteger><name>area_planned_target</name>\n<userAlias>area_planned_target</userAlias>\n<userDisplayLabel>Area Planned Target(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlinteger>\n</selectable><selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable><selectable><sqldate><name>audit_createDate</name>\n<userAlias>audit_createDate</userAlias>\n<userDisplayLabel>Create date</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqldate>\n</selectable>\n<selectable><sqldate><name>audit_lastUpdateDate</name>\n<userAlias>audit_lastUpdateDate</userAlias>\n<userDisplayLabel>Last Update Date</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqldate>\n</selectable>\n<selectable><sqlcharacter><name>audit_createdBy</name>\n<userAlias>audit_createdBy</userAlias>\n<userDisplayLabel>Created By</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>audit_lastUpdatedBy</name>\n<userAlias>audit_lastUpdatedBy</userAlias>\n<userDisplayLabel>Last Updated By</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>audit_imported</name>\n<userAlias>audit_imported</userAlias>\n<userDisplayLabel>Imported</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";

    String areaWeek = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>entityLabel</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_entityLabel</userAlias>\n<userDisplayLabel>Country Entity name (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>geoId</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_geoId</userAlias>\n<userDisplayLabel>Country Geo entity ID (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><sqlinteger><name>area_planned_target</name>\n<userAlias>area_planned_target</userAlias>\n<userDisplayLabel>Area Planned Target(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_YEAR</name>\n<userAlias>dategroup_year</userAlias>\n<userDisplayLabel>Calendar year</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIYEAR</name>\n<userAlias>dategroup_epiyear</userAlias>\n<userDisplayLabel>Epi year</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    String areaYear = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>entityLabel</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_entityLabel</userAlias>\n<userDisplayLabel>Country Entity name (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>geoId</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_geoId</userAlias>\n<userDisplayLabel>Country Geo entity ID (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><sqlinteger><name>area_planned_target</name>\n<userAlias>area_planned_target</userAlias>\n<userDisplayLabel>Area Planned Target(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_YEAR</name>\n<userAlias>dategroup_year</userAlias>\n<userDisplayLabel>Calendar year</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    
    String coverageYear = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>entityLabel</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_entityLabel</userAlias>\n<userDisplayLabel>Country Entity name (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>geoId</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_geoId</userAlias>\n<userDisplayLabel>Country Geo entity ID (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><sqldouble><name>area_planned_coverage</name>\n<userAlias>area_planned_coverage</userAlias>\n<userDisplayLabel>Area Planned Coverage(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIYEAR</name>\n<userAlias>dategroup_epiyear</userAlias>\n<userDisplayLabel>Epi year</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    String coverageWeek = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>entityLabel</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_entityLabel</userAlias>\n<userDisplayLabel>Country Entity name (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>geoId</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_geoId</userAlias>\n<userDisplayLabel>Country Geo entity ID (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><sqldouble><name>area_planned_coverage</name>\n<userAlias>area_planned_coverage</userAlias>\n<userDisplayLabel>Area Planned Coverage(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    String coverageAndSprayedUnits = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>entityLabel</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_entityLabel</userAlias>\n<userDisplayLabel>Country Entity name (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>geoId</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_geoId</userAlias>\n<userDisplayLabel>Country Geo entity ID (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><sqldouble><name>area_planned_coverage</name>\n<userAlias>area_planned_coverage</userAlias>\n<userDisplayLabel>Area Planned Coverage(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sqlinteger><name>sprayedunits</name>\n<userAlias>sprayedunits</userAlias>\n<userDisplayLabel>Units sprayed #</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    String coverageAndSumSprayedUnits = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>entityLabel</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_entityLabel</userAlias>\n<userDisplayLabel>Country Entity name (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><attribute><entityAlias>dss.vector.solutions.irs.AbstractSpray.geoEntity__dss.vector.solutions.geo.generated.Country</entityAlias>\n<name>geoId</name>\n<userAlias>dss_vector_solutions_irs_AbstractSpray_geoEntity__country_geoId</userAlias>\n<userDisplayLabel>Country Geo entity ID (Geo entity)</userDisplayLabel>\n</attribute>\n</selectable>\n<selectable><sqldouble><name>area_planned_coverage</name>\n<userAlias>area_planned_coverage</userAlias>\n<userDisplayLabel>Area Planned Coverage(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqldouble>\n</selectable>\n<selectable><sum><selectable><sqlinteger><name>sprayedunits</name>\n<userAlias>sprayedunits</userAlias>\n<userDisplayLabel>Units sprayed #</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlinteger>\n</selectable>\n<userAlias>sprayedunits</userAlias>\n<userDisplayLabel>(SUM) Units sprayed #</userDisplayLabel>\n</sum>\n</selectable>\n<selectable><sqlcharacter><name>DATEGROUP_EPIWEEK</name>\n<userAlias>dategroup_epiweek</userAlias>\n<userDisplayLabel>Epi week</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";
    
    String areaJSON = "{\"selectedUniversals\":{\"dss.vector.solutions.irs.AbstractSpray.geoEntity\":[\"dss.vector.solutions.geo.generated.Country\"]},\"criteriaEntities\":{\"dss.vector.solutions.irs.AbstractSpray.geoEntity\":[]},\"terms\":{},\"date_attribute\":{\"date_attribute\":\"sprayDate\",\"klass\":\"dss.vector.solutions.irs.OperatorSpray\",\"start\":null,\"end\":null},\"sortOrder\":[\"area_planned_target\",\"dategroup_epiweek\",\"area_planned_coverage\",\"team_targeted_coverage\",\"sprayteam_defaultLocale\",\"dss_vector_solutions_irs_AbstractSpray_geoEntity__country_entityLabel\",\"dss_vector_solutions_irs_AbstractSpray_geoEntity__country_geoId\"]}";

    // criteria
    String crit = "{\"selectedUniversals\":{\"dss.vector.solutions.irs.AbstractSpray.geoEntity\":[]},\"criteriaEntities\":{\"dss.vector.solutions.irs.AbstractSpray.geoEntity\":[]},\"terms\":{},\"date_attribute\":{\"date_attribute\":\"sprayDate\",\"klass\":\"dss.vector.solutions.irs.OperatorSpray\",\"start\":\"2013-11-04\",\"end\":null},\"sortOrder\":[\"aggregation_level\"]}";
    String agg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>aggregation_level</name>\n<userAlias>aggregation_level</userAlias>\n<userDisplayLabel>Aggregation level</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";

    String crit2 = "{\"selectedUniversals\":{\"dss.vector.solutions.irs.AbstractSpray.geoEntity\":[]},\"criteriaEntities\":{\"dss.vector.solutions.irs.AbstractSpray.geoEntity\":[]},\"terms\":{},\"date_attribute\":{\"date_attribute\":\"sprayDate\",\"klass\":\"dss.vector.solutions.irs.OperatorSpray\",\"start\":\"2013-11-04\",\"end\":\"2013-11-16\"},\"sortOrder\":[\"aggregation_level\",\"operator_actual_target\",\"sprayoperator_defaultLocale\"]}";
    String agg2 = "<query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>aggregation_level</name>\n<userAlias>aggregation_level</userAlias>\n<userDisplayLabel>Aggregation level</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlinteger><name>operator_actual_target</name>\n<userAlias>operator_actual_target</userAlias>\n<userDisplayLabel>Operator Actual Target(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlinteger>\n</selectable>\n<selectable><sqlcharacter><name>sprayoperator_defaultLocale</name>\n<userAlias>sprayoperator_defaultLocale</userAlias>\n<userDisplayLabel>Spray operator</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";

    String crit3 = "{\"selectedUniversals\":{\"dss.vector.solutions.irs.AbstractSpray.geoEntity\":[]},\"criteriaEntities\":{\"dss.vector.solutions.irs.AbstractSpray.geoEntity\":[]},\"terms\":{},\"date_attribute\":{\"date_attribute\":\"dateOfBirth\",\"klass\":\"dss.vector.solutions.Person\",\"start\":\"2013-11-04\",\"end\":\"2013-11-16\"},\"sortOrder\":[\"aggregation_level\"]}";
    String agg3 = "<query><entities><entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>aggregation_level</name>\n<userAlias>aggregation_level</userAlias>\n<userDisplayLabel>Aggregation level</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";

    String crit4 = "{\"selectedUniversals\":{\"dss.vector.solutions.irs.AbstractSpray.geoEntity\":[]},\"criteriaEntities\":{\"dss.vector.solutions.irs.AbstractSpray.geoEntity\":[\"xcva1btynviw1nhnnfn84ur13882y896rcv631grg9yz1ar1yuyvi02hfp3pfdfz\"]},\"terms\":{},\"date_attribute\":{\"date_attribute\":\"dateOfBirth\",\"klass\":\"dss.vector.solutions.Person\",\"start\":null,\"end\":null},\"sortOrder\":[\"sprayDate\"]}";
    String agg4 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.geo.AllPaths</type>\n<alias>dss.vector.solutions.geo.AllPaths_dss.vector.solutions.irs.AbstractSpray.geoEntity</alias>\n<criteria><compositeCondition><or><basicCondition><selectable><attribute><entityAlias>dss.vector.solutions.geo.AllPaths_dss.vector.solutions.irs.AbstractSpray.geoEntity</entityAlias>\n<name>parentGeoEntity</name>\n<userAlias></userAlias>\n<userDisplayLabel></userDisplayLabel>\n</attribute>\n</selectable>\n<operator>EQ</operator>\n<value>xcva1btynviw1nhnnfn84ur13882y896rcv631grg9yz1ar1yuyvi02hfp3pfdfz</value>\n</basicCondition>\n</or>\n</compositeCondition>\n</criteria>\n</entity>\n<entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqldate><name>sprayDate</name>\n<userAlias>sprayDate</userAlias>\n<userDisplayLabel>Spray date</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqldate>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";

    // sex
    String e1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.Person</type>\n<alias>dss.vector.solutions.Person</alias>\n<criteria></criteria>\n</entity>\n<entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>sprayleader_sex_displayLabel</name>\n<userAlias>sprayleader_sex</userAlias>\n<userDisplayLabel>???sprayleader_sex???</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";

    // uar
    String e2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query><entities><entity><type>dss.vector.solutions.irs.InsecticideBrand</type>\n<alias>dss.vector.solutions.irs.InsecticideBrand</alias>\n<criteria></criteria>\n</entity>\n<entity><type>dss.vector.solutions.irs.AbstractSpray</type>\n<alias>dss.vector.solutions.irs.AbstractSpray</alias>\n<criteria></criteria>\n</entity>\n</entities>\n<select><selectable><sqlcharacter><name>productName_displayLabel</name>\n<userAlias>productName_spray</userAlias>\n<userDisplayLabel>Product name</userDisplayLabel>\n<isaggregate>false</isaggregate>\n</sqlcharacter>\n</selectable>\n<selectable><sqlfloat><name>unit_application_rate</name>\n<userAlias>unit_application_rate</userAlias>\n<userDisplayLabel>Unit application rate (g/m&amp;sup2;)(AG)</userDisplayLabel>\n<isaggregate>true</isaggregate>\n</sqlfloat>\n</selectable>\n</select>\n<groupby></groupby>\n<having></having>\n<orderby></orderby>\n</query>";

    long s = System.currentTimeMillis();
    ValueQuery v = new IRSQB(areaJSON, areaSimple, null, page, size).construct();

    System.out.println(v.getIterator().getAll().size());

    long e = System.currentTimeMillis();
    System.out.println(e - s);
    System.out.println(v.getSQL());
    
    List<String> names = null;
    for(ValueObject o : v.getIterator().getAll())
    {
      if(names == null)
      {
        names = new LinkedList<String>();
        Map<String, Attribute> m = o.getAttributeMap();
        for(String key : m.keySet())
        {
          String d = v.getSelectableRef(key).getUserDefinedDisplayLabel();
          if(d.length() > 20)
          {
            d = d.substring(0, 20);
          }
          System.out.printf("| %-20s ", d);
          names.add(key);
        }
        
        System.out.print("\n");
      }
      
      for(String name : names)
      {
        String value = o.getValue(name);
        System.out.printf("| %-20s ", value);
      }
      
      System.out.format("\n");
    }
    
  }

  @Request(RequestType.SESSION)
  private static void viewname(String sessionId)
  {
    GeoEntityView v = new GeoEntityView();
    v.setEntityLabel("test");
    v.setGeoEntityId("test123");
    v.setEntityType("test_type");
    v.setTypeDisplayLabel("type_label");
    v.setGeoId("geo123");
    v.apply();

    String id = v.getId();
    GeoEntityView f = GeoEntityView.get(id);
    System.out.println(f);
  }

  private static void json() throws JSONException
  {
    String working = "{title:'TestForm',group:'root',klass:'dss.vector.solutions.form.business.Testform',values:[{attributeName:'oid',dtoType:'com.runwaysdk.transport.attributes.AttributeCharacterDTO',displayLabel:'Form ID',type:'dss.vector.solutions.form.business.Testform',key:'oid_testform'},{attributeName:'anInt',dtoType:'com.runwaysdk.transport.attributes.AttributeIntegerDTO',displayLabel:'An Int',type:'dss.vector.solutions.form.business.Testform',key:'anInt_testform'},{attributeName:'aBool',dropDownMap:{'true':'true','false':'false'},dtoType:'com.runwaysdk.transport.attributes.AttributeBooleanDTO',displayLabel:'A Bool',type:'dss.vector.solutions.form.business.Testform',key:'aBool_testform'}]}";
    String broken = "{\"title\":[\"TestForm\"],\"values\":[[{\"attributeName\":\"oid\",\"dtoType\":\"com.runwaysdk.transport.attributes.AttributeCharacterDTO\",\"displayLabel\":\"Form ID\",\"type\":\"dss.vector.solutions.form.business.Testform\",\"key\":\"oid_testform\"},{\"attributeName\":\"anInt\",\"dtoType\":\"com.runwaysdk.transport.attributes.AttributeIntegerDTO\",\"displayLabel\":\"An Int\",\"type\":\"dss.vector.solutions.form.business.Testform\",\"key\":\"anInt_testform\"},{\"attributeName\":\"aBool\",\"dropDownMap\":{\"false\":\"false\",\"true\":\"true\"},\"dtoType\":\"com.runwaysdk.transport.attributes.AttributeBooleanDTO\",\"displayLabel\":\"A Bool\",\"type\":\"dss.vector.solutions.form.business.Testform\",\"key\":\"aBool_testform\"}]],\"klass\":[\"dss.vector.solutions.form.business.Testform\"],\"group\":[\"root\"]}";

    JSONObject workingObj = new JSONObject(working);
    JSONObject brokenObj = new JSONObject(broken);

    System.out.println(workingObj.toString(4));
    System.out.println(brokenObj.toString(4));
  }

  private static void printCache()
  {
    Iterator<String> iter = ObjectCache.getCollectionMapKeys();

    System.out.println("SUPPORTED: ");

    while (iter.hasNext())
    {
      String k = iter.next();
      System.out.println("[" + ObjectCache.getCachedEntityDAOs(k).size() + "] " + k);
    }

    // System.out.println();
    // System.out.println("OBJECTS: ");
    // for(EntityDAOIF e : ObjectCache.getCachedEntityDAOs("Struct"))
    // {
    // System.out.println(e.getKey());
    // }
  }

  private static void print(String name)
  {
    System.out.println("--------- [" + name + "] -----------");

    System.out.println("Business: "
        + ObjectCache.contains(MdBusiness.CLASS, "com.test." + name + "Business"));
    System.out.println("GET: " + ObjectCache.getMdBusinessDAO("com.test." + name + "Business"));
    System.out.println("Business Controller: "
        + ObjectCache.contains(MdController.CLASS, "com.test." + name + "BusinessController"));
    System.out.println("GET: "
        + ObjectCache.getMdControllerDAO("com.test." + name + "BusinessController"));

    System.out.println("Struct: " + ObjectCache.contains(MdStruct.CLASS, "com.test." + name + "Struct"));
    System.out.println("GET: " + ObjectCache.getMdStructDAO("com.test." + name + "Struct"));
    System.out.println("Struct Controller: "
        + ObjectCache.contains(MdController.CLASS, "com.test." + name + "StructController"));
    System.out
        .println("GET: " + ObjectCache.getMdControllerDAO("com.test." + name + "StructController"));

    System.out.println();

    System.out.println("LocalStruct: "
        + ObjectCache.contains(MdLocalStruct.CLASS, "com.test." + name + "LocalStruct"));
    System.out.println("GET: " + ObjectCache.getMdStructDAO("com.test." + name + "LocalStruct"));
    System.out.println("LocalStruct Controller: "
        + ObjectCache.contains(MdController.CLASS, "com.test." + name + "LocalStructController"));
    System.out.println("GET: "
        + ObjectCache.getMdControllerDAO("com.test." + name + "LocalStructController"));

  }

  @Transaction
  private static void create(String name)
  {
    MdBusinessDAO md = MdBusinessDAO.newInstance();
    md.getAttribute(MdBusinessInfo.DISPLAY_LABEL).setValue(name + " LABEL");
    md.getAttribute(MdBusinessInfo.DESCRIPTION).setValue(name + " DESC");
    md.getAttribute(MdBusinessInfo.NAME).setValue(name + "Business");
    md.getAttribute(MdBusinessInfo.PACKAGE).setValue("com.test");
    md.getAttribute(MdBusinessInfo.REMOVE).setValue(MdAttributeBooleanInfo.TRUE);
    md.setGenerateMdController(true);
    md.apply();

    MdStructDAO struct1 = MdStructDAO.newInstance();
    struct1.setValue(MdTypeInfo.NAME, name + "Struct");
    struct1.setValue(MdTypeInfo.PACKAGE, "com.test");
    struct1.setStructValue(MdTypeInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE,
        "Struct to hold Localized Phrases");
    struct1.setGenerateMdController(true);
    struct1.apply();

    MdLocalStructDAO struct = MdLocalStructDAO.newInstance();
    struct.setValue(MdTypeInfo.NAME, name + "LocalStruct");
    struct.setValue(MdTypeInfo.PACKAGE, "com.test");
    struct.setStructValue(MdTypeInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE,
        "Struct to hold Localized Phrases");
    struct.setGenerateMdController(true);
    struct.apply();

    // MdAttributeLocalTextDAO formal = MdAttributeLocalTextDAO.newInstance();
    // formal.setValue(MdAttributeLocalTextInfo.NAME, "formal");
    // formal.setStructValue(MdAttributeLocalTextInfo.DISPLAY_LABEL,
    // MdAttributeLocalInfo.DEFAULT_LOCALE, "Formal Usage");
    // formal.setValue(MdAttributeLocalTextInfo.DEFINING_MD_CLASS, md.getId());
    // formal.setValue(MdAttributeLocalTextInfo.MD_STRUCT, struct.getId());
    // formal.apply();
  }

  private static String concatenate(Selectable[] selectableArray)
  {
    StringBuilder sb = new StringBuilder();
    sb.append("LOWER(' ' || ");
    for (int i = 0; i < selectableArray.length; i++)
    {
      if (i > 0)
      {
        sb.append(" || ' ' || ");
      }
      sb.append(selectableArray[i].getDbQualifiedName());
    }
    sb.append(")");
    return sb.toString();
  }

  private static ValueQuery textLookup(QueryFactory qf, String[] tokenArray,
      SelectablePrimitive[] selectableArray, Condition[] conditionArray)
  {
    long WEIGHT = 256;

    ValueQuery uQ = qf.valueQuery();

    ValueQuery[] valueQueryArray = new ValueQuery[tokenArray.length];

    if (tokenArray.length > 1)
    {
      for (int i = 0; i < tokenArray.length; i++)
      {
        String token = tokenArray[i].toLowerCase();
        valueQueryArray[i] = buildQueryForToken(qf, token, selectableArray, conditionArray, WEIGHT, i);
      }
      uQ.UNION(valueQueryArray);
    }
    else
    {
      uQ = buildQueryForToken(qf, tokenArray[0].toLowerCase(), selectableArray, conditionArray, WEIGHT,
          0);
    }

    // Build outermost select clause. This would be cleaner if the API supported
    // incrementally adding
    // to the select clause. One day that will be supported.
    ValueQuery resultQuery = qf.valueQuery();
    Selectable[] selectClauseArray = new Selectable[selectableArray.length + 2];
    for (int k = 0; k < selectableArray.length; k++)
    {
      selectClauseArray[k] = uQ.get(selectableArray[k].getResultAttributeName());
    }
    selectClauseArray[selectableArray.length] = F.COUNT(uQ.get("weight"), "weight");
    selectClauseArray[selectableArray.length + 1] = F.SUM(uQ.get("weight"), "sum");

    resultQuery.SELECT(selectClauseArray);
    resultQuery.ORDER_BY_DESC(F.COUNT(uQ.get("weight"), "weight"));
    resultQuery.ORDER_BY_DESC(F.SUM(uQ.get("weight"), "sum"));
    for (SelectablePrimitive selectable : selectableArray)
    {
      resultQuery.ORDER_BY_ASC((AttributePrimitive) uQ.get(selectable.getResultAttributeName()));
    }
    resultQuery.HAVING(F.COUNT(uQ.get("weight")).EQ(tokenArray.length));
    System.out.println(resultQuery.getSQL());

    for (ValueObject valueObject : resultQuery.getIterator())
    {
      valueObject.printAttributes();
    }

    return resultQuery;
  }

  private static ValueQuery buildQueryForToken(QueryFactory qf, String token,
      SelectablePrimitive[] selectableArray, Condition[] conditionArray, long WEIGHT, int i)
  {
    ValueQuery vQ = qf.valueQuery();

    // Build select clause. This would be cleaner if the API supported
    // incrementally adding
    // to the select clause. One day that will be supported.
    Selectable[] selectClauseArray = new Selectable[selectableArray.length + 1];
    for (int k = 0; k < selectableArray.length; k++)
    {
      selectClauseArray[k] = selectableArray[k];
    }
    selectClauseArray[selectableArray.length] = vQ.aSQLDouble("weight", "1.0 / (" + Math.pow(WEIGHT, i)
        + " * STRPOS(" + concatenate(selectableArray) + ", ' " + token + "'))");

    vQ.SELECT(selectClauseArray);
    vQ.WHERE(vQ.aSQLCharacter("fields", concatenate(selectableArray)).LIKE("% " + token + "%"));

    for (Condition condition : conditionArray)
    {
      vQ.AND(condition);
    }
    return vQ;
  }

  @Request(RequestType.SESSION)
  public static void testGeoEntityQuery(String sessionId)
  {
    new CleanupContextListener().contextInitialized(null);

    String geoId = Property.getStr(PropertyInfo.INSTALL_PACKAGE, PropertyInfo.COUNTRY_GEO_ID);
    System.out.println(geoId);
  }

  @Request
  public static void testNoLogin()
  {

    QueryFactory qf = new QueryFactory();

    GeoHierarchyViewQuery q = new GeoHierarchyViewQuery(qf, "", true, 20, 1);
    q.WHERE(q.getIsADisplayLabel().EQ("Geo Entity"));

    System.out.println(q.getSQL());

    // ValueQuery valueQuery = new ValueQuery(factory);
    // AggregatedCaseQuery query = new AggregatedCaseQuery(factory);
    //
    // Condition condition = caseQuery.getGeoEntity().EQ(entityQuery);
    // condition = AND.get(condition,
    // caseQuery.getStartDate().GE(initialWeek.getStartDate()));
    // condition = AND.get(condition,
    // caseQuery.getEndDate().LE(finalWeek.getEndDate()));
    // //condition = AND.get(condition,
    // caseQuery.getEndDate().EQ(caseQuery.getStartDate() + 7));
    // valueQuery.WHERE(caseQuery.getGeoEntity().EQ(entityQuery));
    // valueQuery.AND(caseQuery.getStartDate().GE(initialWeek.getStartDate()));
    // valueQuery.AND(caseQuery.getEndDate().LE(finalWeek.getEndDate()));
    // valueQuery.AND(caseQuery.getEndDate().EQ(valueQuery.aSQLDate("startDate",
    // caseQuery.getStartDate().getQualifiedName() + "+ interval '7 days'")));
    // valueQuery.FROM(caseQuery.getStartDate().getDefiningTableName(),
    // caseQuery.getStartDate().getDefiningTableAlias());

    /*
     * select name, count(weight) c, sum(weight) s from ( select name, 1.0 /
     * (1.0 * strpos(lower(' ' || name), ' b')) as weight from term where
     * lower(' ' || name) like '% b%' union all select name, 1.0 / (256.0 *
     * strpos(lower(' ' || name), ' a')) as weight from term where lower(' ' ||
     * name) like '% a%' ) as foo group by name --having count(weight) = 2 order
     * by c desc, s desc, name
     */

    // String[] tokenArray = new String[]{"Plasmodium"};
    // String[] tokenArray = new String[]{"Plasmodium", "falciparum"};
    //
    //
    // QueryFactory qf = new QueryFactory();
    // TermQuery tQ = new TermQuery(qf);
    // GeoEntityQuery gQ = new GeoEntityQuery(qf);
    //
    // SelectablePrimitive[] selectableArray = new SelectablePrimitive[2];
    // selectableArray[0] = tQ.getName();
    // selectableArray[1] = tQ.getTermId();
    //
    // // This is a COMPLETELY contrived example that makes no sense in real
    // ife.
    // Condition joinCondition = tQ.getName().EQ(gQ.getEntityName());
    // // textLookup(qf, tokenArray, selectableArray, new
    // Condition[]{joinCondition});
    //
    // textLookup(qf, tokenArray, selectableArray, new Condition[]{});

    // GeoEntity.buildAllPathsFast();

    // GeoEntity.copyTermFast("xs8cxvues3ab3m879rgl4tu70gy0s3r72a0u0u4o9c8ol44kb9vl8tf3ieviu8co",
    // "rekttx1h9ehy40ubzbxg2fd044owmwzp2a0u0u4o9c8ol44kb9vl8tf3ieviu8co");

    // AllPaths.rebuildAllPaths();

    // AllPaths.copyTermFast("05brck3zaer5w5vbcee16ry28lzao1e3xeklfgy3bklheiiulqszhfay163qlpta",
    // "zbdda5r3mmkkwyuhla67ymkn551jmtpnxeklfgy3bklheiiulqszhfay163qlpta",
    // "05kt1jddumk5qm8juvcxseyist5klwhrzm1g3udb394gw9tn8ca9qvefl9ncyubd");

    // try
    // {
    // FileInputStream fileInputStream = new FileInputStream(new
    // File("/Users/nathan/workspace3.5/MDSS/PersonExcelView.xls"));
    //
    // GeoEntitySearcher geoSynonymMatcher = new GeoEntitySearcher();
    // geoSynonymMatcher.checkExcelGeoHierarchy(fileInputStream);
    // }
    // catch (FileNotFoundException e)
    // {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    /*
     * Mocambique Mozambique
     * 
     * select metaphone('Mocambique', 255); select metaphone('Mozambique', 255);
     * 
     * select dmetaphone('Mocambique'); select dmetaphone_alt('Mocambique');
     * 
     * select dmetaphone('Mozambique'); select dmetaphone_alt('Mozambique');
     * 
     * SELECT levenshtein('Mocambique', 'Mozambique');
     * 
     * Bilene Bellene
     */
    // QueryFactory qf = new QueryFactory();
    // GeoEntityQuery geoEntityQuery = new GeoEntityQuery(qf);
    //
    // geoEntityQuery.WHERE(geoEntityQuery.getEntityName().EQ("Gaza"));
    //
    // OIterator<? extends GeoEntity> i = geoEntityQuery.getIterator();
    //
    // try
    // {
    // GeoEntity geoEntity = i.next();
    // System.out.println(geoEntity.getEntityName());
    //
    // GeoSynonym geoSynonym = new GeoSynonym();
    // geoSynonym.setEntityName("Gaaza");
    // geoSynonym.apply();
    //
    // geoEntity.addSynonyms(geoSynonym).apply();
    //
    // }
    // finally
    // {
    // i.close();
    // }
  }

  @Request(RequestType.SESSION)
  public static void test(String sessionId)
  {

    // QueryFactory f = new QueryFactory();
    //
    // ValueQuery v = new ValueQuery(f);
    // ValueQuery v2 = new ValueQuery(f);
    //
    // PersonQuery p1 = new PersonQuery(f); // Original PersonQuery
    // PersonQuery p2 = new PersonQuery(f); // PersonQuery for Prevalence
    //
    // // total tested
    // Condition or = OR.get(p2.getPerformedRDT().containsAny(RDTResponse.YES),
    // p2.getBloodslide().containsAny(BloodslideResponse.DONE));
    // p2.WHERE(or);
    //
    // // total positive
    // p2.AND(p2.getRDTResult().containsAny(RDTResult.MALARIAE_POSITIVE,
    // RDTResult.MIXED_POSITIVE,
    // RDTResult.OVALE_POSITIVE, RDTResult.PF_POSITIVE,
    // RDTResult.VIVAX_POSITIVE));
    //
    // v2.SELECT(F.COUNT(p2.getId()));
    //
    // SelectableSQLDouble precision = v.aSQLAggregateDouble("precision", "");
    // precision.setSQL("100 * AVG( ("+v2.getSQL()+" WHERE "+p2.getTableAlias()+".id = "+p1.getTableAlias()+".id))");
    //
    //
    // v.SELECT(precision);
    // v.FROM(p1);
    //
    // System.out.println(v.getSQL());
  }

  @Request
  private static void testAllPaths()
  {
    // defineDatatypesForAllPaths();

    // GeoHierarchy earthGeoHierarchy =
    // GeoHierarchy.getGeoHierarchyFromType(Earth.CLASS);
    // updateAllPaths(earthGeoHierarchy);
    //
    // updateAllPaths();
    //
    // java.util.Date endTime = new java.util.Date();
    // long totalTime = endTime.getTime() - startTime.getTime();
    // System.out.println("\nTotal Time: " + totalTime);
  }

  private static void defineDatatypesForAllPaths()
  {
    MdBusinessDAOIF allPathsMdBusinessDAO = MdBusinessDAO.getMdBusinessDAO(PropertyInfo.GEO_PACKAGE
        + "." + "AllPaths");
    allPathsMdBusinessDAO.getBusinessDAO().delete();

    // MdBusinessDAOIF geoEntityMdBusiness =
    // MdBusinessDAO.getMdBusinessDAO(GeoEntity.CLASS);
    //
    // MdBusinessDAO allPathsMdBusinessDAO = MdBusinessDAO.newInstance();
    // allPathsMdBusinessDAO.setValue(MdBusinessInfo.NAME, "AllPaths");
    // allPathsMdBusinessDAO.setValue(MdBusinessInfo.PACKAGE,
    // PropertyInfo.GEO_PACKAGE);
    // allPathsMdBusinessDAO.setStructValue(MdBusinessInfo.DISPLAY_LABEL,
    // MdAttributeLocalInfo.DEFAULT_LOCALE, "All Paths");
    // allPathsMdBusinessDAO.setValue(MdBusinessInfo.EXTENDABLE,
    // MdAttributeBooleanInfo.FALSE);
    // allPathsMdBusinessDAO.setGenerateMdController(false);
    // allPathsMdBusinessDAO.apply();
    //
    // MdAttributeReferenceDAO parentMdAttributeReferenceDAO =
    // MdAttributeReferenceDAO.newInstance();
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.NAME,
    // "parentGeoEntity");
    // parentMdAttributeReferenceDAO.setStructValue(MdAttributeReferenceInfo.DISPLAY_LABEL,
    // MdAttributeLocalInfo.DEFAULT_LOCALE, "Parent Geo Entity");
    // parentMdAttributeReferenceDAO.setStructValue(MdAttributeReferenceInfo.DESCRIPTION,
    // MdAttributeLocalInfo.DEFAULT_LOCALE, "Parent Geo Entity");
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REQUIRED,
    // MdAttributeBooleanInfo.TRUE);
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REMOVE,
    // MdAttributeBooleanInfo.TRUE);
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.INDEX_TYPE,
    // IndexTypes.NON_UNIQUE_INDEX.getId());
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REF_MD_BUSINESS,
    // geoEntityMdBusiness.getId());
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.DEFINING_MD_CLASS,
    // allPathsMdBusinessDAO.getId());
    // parentMdAttributeReferenceDAO.apply();
    //
    // MdAttributeReferenceDAO childMdAttributeReferenceDAO =
    // MdAttributeReferenceDAO.newInstance();
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.NAME,
    // "childGeoEntity");
    // childMdAttributeReferenceDAO.setStructValue(MdAttributeReferenceInfo.DISPLAY_LABEL,
    // MdAttributeLocalInfo.DEFAULT_LOCALE, "Child Geo Entity");
    // childMdAttributeReferenceDAO.setStructValue(MdAttributeReferenceInfo.DESCRIPTION,
    // MdAttributeLocalInfo.DEFAULT_LOCALE, "Child Geo Entity");
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REQUIRED,
    // MdAttributeBooleanInfo.TRUE);
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REMOVE,
    // MdAttributeBooleanInfo.TRUE);
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.INDEX_TYPE,
    // IndexTypes.NON_UNIQUE_INDEX.getId());
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REF_MD_BUSINESS,
    // geoEntityMdBusiness.getId());
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.DEFINING_MD_CLASS,
    // allPathsMdBusinessDAO.getId());
    // childMdAttributeReferenceDAO.apply();
    //
    //
    // MdIndexDAO mdIndex = MdIndexDAO.newInstance();
    // mdIndex.setValue(MdIndexInfo.MD_ENTITY, allPathsMdBusinessDAO.getId());
    // mdIndex.setValue(MdIndexInfo.UNIQUE, MdAttributeBooleanInfo.TRUE);
    // mdIndex.setStructValue(MdIndexInfo.DISPLAY_LABEL,
    // MdAttributeLocalInfo.DEFAULT_LOCALE,
    // "Parent Child Geo Entity Unique Index");
    // mdIndex.apply();
    //
    // mdIndex.addAttribute(parentMdAttributeReferenceDAO, 0);
    // mdIndex.addAttribute(childMdAttributeReferenceDAO, 1);
    //
    // mdIndex.setValue(MdIndexInfo.ACTIVE, MdAttributeBooleanInfo.TRUE);
    // mdIndex.apply();
  }

  private static void updateAllPaths(GeoHierarchy parentGeoHierarchy)
  {
    updateAllPathsForUniversal(parentGeoHierarchy);

    List<? extends GeoHierarchy> geoHierarchyChildren = parentGeoHierarchy.getAllAcceptsGeoEntity()
        .getAll();

    for (GeoHierarchy childGeoHierarchy : geoHierarchyChildren)
    {
      updateAllPaths(childGeoHierarchy);
    }
  }

  private static void createPath(String parentId, String childId)
  {
    // try
    // {
    // AllPaths allPaths = new AllPaths();
    // allPaths.setValue(AllPaths.PARENTGEOENTITY, parentId);
    // allPaths.setValue(AllPaths.CHILDGEOENTITY, childId);
    // allPaths.apply();
    // }
    // catch(DuplicateDataDatabaseException ex)
    // {
    // // This might happen. Relationship already exists.
    // }
  }

  private static void updateAllPathsForUniversal(GeoHierarchy geoHierarchy)
  {
    MdBusiness mdBusiness = geoHierarchy.getGeoEntityClass();

    // We only need to do this for the parent most types.
    // The child types will be included when we update paths for
    // the parent type.
    if (!mdBusiness.getSuperMdBusiness().definesType().equals(GeoEntity.CLASS))
    {
      return;
    }

    String geoEntityType = mdBusiness.definesType();

    QueryFactory qf = new QueryFactory();

    BusinessQuery businessQ = qf.businessQuery(geoEntityType);

    ValueQuery q = new ValueQuery(qf);
    q.SELECT(businessQ.aCharacter(ComponentInfo.ID, ComponentInfo.ID));

    OIterator<ValueObject> i = q.getIterator();

    try
    {
      int applyCount = 0;

      for (ValueObject valueObject : i)
      {
        String childId = valueObject.getValue(ComponentInfo.ID);
        createPath(childId, childId);
        System.out.print(".");

        applyCount++;

        if (applyCount % feedbackMod == 0)
        {
          System.out.println();
        }

        List<String> parentIdList = getParentIds(childId);

        for (String parentId : parentIdList)
        {
          createPath(parentId, childId);
          System.out.print(".");

          applyCount++;

          if (applyCount % feedbackMod == 0)
          {
            System.out.println();
          }
        }

      }
    }
    finally
    {
      i.close();
    }
  }

  private static void updateAllPaths()
  {
    QueryFactory qf = new QueryFactory();

    GeoEntityQuery geoEntityQ = new GeoEntityQuery(qf);

    ValueQuery q = new ValueQuery(qf);
    q.SELECT(geoEntityQ.getId(ComponentInfo.ID));

    OIterator<ValueObject> i = q.getIterator();

    try
    {
      int applyCount = 0;

      for (ValueObject valueObject : i)
      {
        String childId = valueObject.getValue(ComponentInfo.ID);
        createPath(childId, childId);
        System.out.print(".");

        applyCount++;

        if (applyCount % feedbackMod == 0)
        {
          System.out.println();
        }

        List<String> parentIdList = getParentIds(childId);

        for (String parentId : parentIdList)
        {
          createPath(parentId, childId);
          System.out.print(".");

          applyCount++;

          if (applyCount % feedbackMod == 0)
          {
            System.out.println();
          }
        }

      }
    }
    finally
    {
      i.close();
    }
  }

  // private static boolean parentPathsExist(String parentId)
  // {
  // QueryFactory queryFactory = new QueryFactory();
  //
  // AllPathsQuery allPathsQuery = new AllPathsQuery(queryFactory);
  //
  // ValueQuery valueQuery = new ValueQuery(queryFactory);
  //
  // valueQuery.SELECT(allPathsQuery.getParentGeoEntity(AllPaths.PARENTGEOENTITY));
  // valueQuery.WHERE(allPathsQuery.getChildGeoEntity().EQ(parentId));
  //
  // for (ValueObject valueObject : valueQuery.getIterator())
  // {
  // return true;
  // }
  //
  // return false;
  // }

  private static List<String> getParentIds(String childId)
  {
    QueryFactory queryFactory = new QueryFactory();

    LocatedInQuery locatedInQuery = new LocatedInQuery(queryFactory);

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    valueQuery.SELECT(locatedInQuery.parentId(RelationshipInfo.PARENT_ID));
    valueQuery.WHERE(locatedInQuery.childId().EQ(childId));

    List<ValueObject> valueObjectList = valueQuery.getIterator().getAll();

    List<String> parentIdList = new LinkedList<String>();

    for (ValueObject valueObject : valueObjectList)
    {
      String parentId = valueObject.getValue(RelationshipInfo.PARENT_ID);
      parentIdList.add(parentId);
      parentIdList.addAll(getParentIds(parentId));
    }

    return parentIdList;
  }

  @Request
  private static void testQueries()
  {
    // QueryFactory qf = new QueryFactory();
    //
    // ValueQuery valueQuery = new ValueQuery(qf);
    //
    // AggregatedCaseQuery acq = new AggregatedCaseQuery(qf);
    //
    // CaseTreatmentMethodQuery ctmq1 = new CaseTreatmentMethodQuery(qf);
    // CaseTreatmentMethodQuery ctmq2 = new CaseTreatmentMethodQuery(qf);
    //
    // TreatmentMethodGridQuery tmgq1 = new TreatmentMethodGridQuery(qf);
    // tmgq1.WHERE(tmgq1.getOptionName().EQ("Tablets"));
    // TreatmentMethodGridQuery tmgq2 = new TreatmentMethodGridQuery(qf);
    // tmgq2.WHERE(tmgq2.getOptionName().EQ("Syrup"));
    //
    //
    // valueQuery.SELECT(acq.getStartAge(), acq.getEndAge(),
    // F.SUM(ctmq1.getAmount(), "Tablets"), F.SUM(ctmq2.getAmount(), "Syrup"));
    // valueQuery.WHERE(acq.treatmentMethod(ctmq1));
    // valueQuery.AND(acq.treatmentMethod(ctmq2));
    // valueQuery.AND(ctmq1.hasChild(tmgq1));
    // valueQuery.AND(ctmq2.hasChild(tmgq2));
    //
    //
    // System.out.println(valueQuery.getSQL());
    //
    //
    // for (ValueObject valueObject : valueQuery.getIterator())
    // {
    // valueObject.printAttributes();
    // for (com.runwaysdk.dataaccess.AttributeIF attributeIF :
    // valueObject.getAttributeArrayIF())
    // {
    // System.out.println(attributeIF.getDisplayLabel(Locale.ENGLISH));
    // }
    // }

  }

}
