import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.obofoundry.Component;
import org.obofoundry.ComponentQuery;
import org.obofoundry.OBORelationship;
import org.obofoundry.Term;
import org.obofoundry.Typedef;

import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.MdRelationshipDAOIF;
import com.terraframe.mojo.dataaccess.StaleEntityException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.system.metadata.MdBusiness;
import com.terraframe.mojo.system.metadata.MdRelationship;

public class OBOImportRelationships 
{

  /**
   * @param args
   * @throws IOException 
   */
   @StartSession
   public static void main(String[] args) throws IOException
   {
     importTypeDefs(args[0]);
   }
	  
   @Transaction
   public static void importTypeDefs(String oboRelFile) throws IOException
   {   
     // deleteAllRelationships();
	    
	 createRelationships(oboRelFile);
   }
   
   private static String namespace = "relationship";
   
   private static String packageName = OBOUtil.rootPackageName+"."+namespace;


   private static Map<String, Typedef> typeDefMap = new HashMap<String, Typedef>();
   
   private static Set<String> createdRelationships = new HashSet<String>();
   
   private static void createRelationships(String oboRelFile) throws IOException
   {
     File file = new File(oboRelFile);
     BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
     
     String line = null;
     
     // Get all of the attributes
     while((line=bufferedReader.readLine())!=null)
     {
       if (line.trim().equals("[Typedef]"))
       {
         Typedef typedef = new Typedef();   
         
         createRelationship(bufferedReader, typedef);
         
         typedef.setNameSpace(namespace);
         
         typedef.apply();
         typeDefMap.put(typedef.getOboId(), typedef);
       } 
     }
     
     bufferedReader.close();
     
     // Is A relationship
     file = new File(oboRelFile);
     bufferedReader = new BufferedReader(new FileReader(file));
     while((line=bufferedReader.readLine())!=null)
     {
       if (line.trim().equals("[Typedef]"))
       {
         createIsARelationship(bufferedReader);
       }
     }
     
     buildInverseRelationships();
     buildSingleRelationships();
   }

   private static void buildSingleRelationships()
   {
 	Iterator<Typedef> typeDefIterator = typeDefMap.values().iterator();

     while (typeDefIterator.hasNext())
     {
       Typedef typedef = typeDefIterator.next();
       
       if (typedef.getComponentName().equals("is_a") ||
   		  typedef.getComponentName().equals("relationship"))
       {
         continue;
       }
       // relationship has already been defined
       if (createdRelationships.contains(typedef.getOboId()))
       {
         continue;
       }   

       buildSingleRelationship(typedef);

     }      
   }

   private static void buildSingleRelationship(Typedef typedef)
   {
     MdBusinessDAOIF mdBusinessIF = com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO.getMdBusinessDAO(Term.CLASS); 
     MdBusiness mdBusinessTerm = MdBusiness.get(mdBusinessIF.getId());
     	    
     MdRelationship mdRelationship = new MdRelationship();
       
     String oboId = typedef.getOboId().replace(":", "_");
     String upperRelationshipName = oboId.substring(0,1).toUpperCase() + oboId.substring(1);
       
     mdRelationship.setPackageName(packageName);
     mdRelationship.setTypeName(upperRelationshipName);
     mdRelationship.setIsAbstract(true);
     mdRelationship.setExtendable(true);
     mdRelationship.setDisplayLabel("Relation "+typedef.getComponentName());

     MdRelationshipDAOIF superMdRelationshipIF = com.terraframe.mojo.dataaccess.metadata.MdRelationshipDAO.getMdRelationshipDAO(OBORelationship.CLASS); 
 	 MdRelationship superMdRelationship = MdRelationship.get(superMdRelationshipIF.getId());	
 	 mdRelationship.setSuperMdRelationship(superMdRelationship);

     mdRelationship.setChildMdBusiness(mdBusinessTerm);
     mdRelationship.setChildCardinality("*");
     mdRelationship.setChildDisplayLabel("Child "+typedef.getComponentName());
     mdRelationship.setChildMethod("Child"+upperRelationshipName);
       
     mdRelationship.setParentMdBusiness(mdBusinessTerm);
     mdRelationship.setParentCardinality("*");
     mdRelationship.setParentDisplayLabel("Parent "+typedef.getComponentName());
     mdRelationship.setParentMethod("Parent"+upperRelationshipName);
       
     mdRelationship.apply();
  
     typedef.setMdRelationship(mdRelationship);
     typedef.apply();
   
     createdRelationships.add(typedef.getOboId());
   }
   
   
   
   private static void buildInverseRelationships()
   {
 	Iterator<Typedef> typeDefIterator = typeDefMap.values().iterator();

     while (typeDefIterator.hasNext())
     {
       Typedef typedef = typeDefIterator.next();
       
       if (typedef.getComponentName().equals("is_a") ||
   		  typedef.getComponentName().equals("relationship"))
       {
         continue;
       }
       
       Typedef inverseTypedef = null;

       // Heads up: make sure this returns null instead of throwing an exception
       inverseTypedef = typedef.getInverseof();
       
       if (inverseTypedef != null && 
           !createdRelationships.contains(typedef.getOboId()) &&
           !createdRelationships.contains(inverseTypedef.getOboId()))
       {
         buildInverseRelationship(typedef, inverseTypedef);
       }      
     }
   }

   private static void buildInverseRelationship(Typedef typedef, Typedef inverseTypedef) 
   {
 	MdBusinessDAOIF mdBusinessIF = com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO.getMdBusinessDAO(Term.CLASS); 
 	MdBusiness mdBusinessTerm = MdBusiness.get(mdBusinessIF.getId());
 	    
 	MdRelationship mdRelationship = new MdRelationship();
 	    
 	String typedefName = typedef.getComponentName();
 	typedefName = typedefName.substring(0,1).toUpperCase() + typedefName.substring(1);
 	
 	String inverseTypedefName = inverseTypedef.getComponentName();
 	inverseTypedefName = inverseTypedefName.substring(0,1).toUpperCase() + inverseTypedefName.substring(1);
 	
 	String oboId = typedef.getOboId().replace(":", "_");
 	String relationshipName = oboId.substring(0,1).toUpperCase() + oboId.substring(1);
 	    
 	mdRelationship.setPackageName(packageName);
 	mdRelationship.setTypeName(relationshipName);
 	mdRelationship.setIsAbstract(true);
 	mdRelationship.setExtendable(true);
 	mdRelationship.setDisplayLabel("Relation "+typedef.getComponentName()+" Has Inverse "+inverseTypedef.getComponentName());
 	
 	MdRelationshipDAOIF superMdRelationshipIF = com.terraframe.mojo.dataaccess.metadata.MdRelationshipDAO.getMdRelationshipDAO(OBORelationship.CLASS); 
 	MdRelationship superMdRelationship = MdRelationship.get(superMdRelationshipIF.getId());	
 	mdRelationship.setSuperMdRelationship(superMdRelationship);

 	mdRelationship.setChildMdBusiness(mdBusinessTerm);
 	mdRelationship.setChildCardinality("*");
 	mdRelationship.setChildDisplayLabel(inverseTypedef.getComponentName());
 	mdRelationship.setChildMethod(typedefName);
 	
 	mdRelationship.setParentMdBusiness(mdBusinessTerm);
 	mdRelationship.setParentCardinality("*");
 	mdRelationship.setParentDisplayLabel(typedef.getComponentName());
 	mdRelationship.setParentMethod(inverseTypedefName);
 	
 	mdRelationship.apply();
 	
 	typedef.setMdRelationship(mdRelationship);
 	typedef.apply();

 	inverseTypedef.setMdRelationship(mdRelationship);
 	inverseTypedef.apply();
 	
 	createdRelationships.add(typedef.getOboId());
 	createdRelationships.add(inverseTypedef.getOboId());
   } 
   
   private static void createIsARelationship(BufferedReader bufferedReader) throws IOException
   {
     String line = bufferedReader.readLine();
         
     String name = null;
     String isA = null;
     String inverseOf = null;
     
     while(line != null && !line.trim().equals(""))
     { 
       String attributeName = OBOUtil.getAttributeName(line);
       String attributeValue = OBOUtil.getAttributeValue(line);
         
       if (attributeName.equals("id"))
       {
         name = attributeValue;
       }
       else if (attributeName.equals("is_a"))
       {
     	  isA = attributeValue;
       }
       else if (attributeName.equals("inverse_of"))
       {
    	    inverseOf = attributeValue;
       }
       
       line = bufferedReader.readLine();
     }
     
     if (name != null && isA != null)
     {
   	  Typedef subTypedef = typeDefMap.get(name);
   	  Typedef superTypedef = typeDefMap.get(isA);

   	  if (subTypedef != null && superTypedef != null)
   	  {
         subTypedef.setIsChild(true);
    	    subTypedef.apply();

         superTypedef.setIsParent(true);
   	    superTypedef.apply();

     	subTypedef.addIsASuperClasses(superTypedef).apply();  
   	  }   
     }
     
     if (name != null && inverseOf != null)
     {
   	  Typedef typedef = typeDefMap.get(name);
   	  Typedef iverseTypedef = typeDefMap.get(inverseOf);

   	  if (typedef != null && iverseTypedef != null)
   	  {
   		typedef.setInverseof(iverseTypedef);
   		typedef.apply();

   		iverseTypedef.setInverseof(typedef);
   		iverseTypedef.apply();
   	  }
         
     }
     
   }
   
   protected static void createRelationship(BufferedReader bufferedReader, Typedef typedef) throws IOException
   {
     String line = bufferedReader.readLine();
     
     System.out.println("DEFINING NEW RELATIONSHIP!!!!!");
     System.out.println("------------------------------");
     
     while(line != null && !line.trim().equals(""))
     {   
       String attributeName = OBOUtil.getAttributeName(line);
       String attributeValue = OBOUtil.getAttributeValue(line);

       System.out.println(attributeName+"::"+attributeValue);

       if (attributeName.equals("id"))
       {
         typedef.setOboId(attributeValue);
       }
       else if (attributeName.equals("name"))
       {
         typedef.setComponentName(attributeValue);
       }
       else if (attributeName.equals("def"))
       {
         typedef.setDef(attributeValue);
       }
       else if (attributeName.equals("comment"))
       {
         typedef.setComponentComment(attributeValue);
       }
       else if (attributeName.equals("is_reflexive"))
       {
         typedef.setIsReflexive(Boolean.parseBoolean(attributeValue));
       }
       else if (attributeName.equals("is_transitive"))
       {
         typedef.setIsTransitive(Boolean.parseBoolean(attributeValue));
       }
       else if (attributeName.equals("is_anti_symmetric"))
       {
         typedef.setIsAntiSymmetric(Boolean.parseBoolean(attributeValue));
       }
       
       line=bufferedReader.readLine();
     }

     System.out.println("------------------------------\n\n");
     
   }


   private static void deleteAllRelationships()
   {
//     TypedefQuery rq = new TypedefQuery(new QueryFactory());
 //    
//     for (Typedef typedef : rq.getIterator())
//     {
//       typedef.getMdRelationship().delete();
//       typedef.delete();
//     }
     
     ComponentQuery cq = new ComponentQuery(new QueryFactory());
     
     for (Component component : cq.getIterator())
     {
       try
       {
         component.delete();
       }
       catch (StaleEntityException e)
       {
         continue;
       }
     }
   }
   
}
