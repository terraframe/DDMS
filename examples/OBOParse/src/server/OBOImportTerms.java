import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.obofoundry.Term;
import org.obofoundry.TermQuery;
import org.obofoundry.TermSynonym;
import org.obofoundry.TermSynonymQuery;
import org.obofoundry.Typedef;

import com.terraframe.mojo.business.Relationship;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.generation.loader.LoaderDecorator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.system.metadata.MdBusiness;
import com.terraframe.mojo.system.metadata.MdRelationship;

public class OBOImportTerms 
{
  /**
   * @param args
   * @throws IOException 
   */
   @StartSession
   public static void main(String[] args) throws IOException
   {
     importTypeDefs(args[0], args[1]);
   }
	
   private static String namespace;
   
   private static String packageName;
   
   private static String termClassName;
   
   private static String CLASS;
   
   private static Class<?> termClass;
   
   private static Map<String, Term> createdTerms = new HashMap<String, Term>();
   
   public static void importTypeDefs(String oboTermFile, String _termClassName) throws IOException
   {
     termClassName = _termClassName;

     File file = new File(oboTermFile);
     BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
     
     getMetaData(bufferedReader);
     bufferedReader.close();
     
     createTermClass();
//    deleteTermClass();
     
     defineNewRelationshipsTerms(file);

     importTerms(file);
     
     
//     deleteTerms();
   }
   
   private static void deleteTermClass()
   {
     MdBusinessDAOIF mdBusinessIF = com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO.getMdBusinessDAO(CLASS); 
     mdBusinessIF.getBusinessDAO().delete();
   }
   
   private static void deleteTerms()
   {
     TermQuery tq = new TermQuery(new QueryFactory());
   
     for (Term term : tq.getIterator())
     {
       term.delete();
     }
     
     TermSynonymQuery sq = new TermSynonymQuery(new QueryFactory());
     
     for (TermSynonym termSynonym : sq.getIterator())
     {
       termSynonym.delete();
     }
   }
   
   private static void getMetaData(BufferedReader bufferedReader) throws IOException
   {
     String line = null;
     
     // Get all of the attributes
     while((line=bufferedReader.readLine())!=null)
     {
       String attributeName = "";
       
       if (line.contains(":"))
       {
         attributeName = OBOUtil.getAttributeName(line);
         
         if (attributeName.equals("default-namespace"))
         {
           namespace = OBOUtil.getAttributeValue(line);
           
           packageName = OBOUtil.rootPackageName+"."+namespace;
           
           CLASS = packageName+"."+termClassName;
           
           break;
         }
       }
     }
   }
   
   @Transaction
   private static void createTermClass()
   {
     MdBusinessDAOIF mdBusinessIF = com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO.getMdBusinessDAO(Term.CLASS); 
     MdBusiness mdBusinessTerm = MdBusiness.get(mdBusinessIF.getId());
     
     MdBusiness mdBusiness = new MdBusiness();
     mdBusiness.setPackageName(packageName);
     mdBusiness.setTypeName(termClassName);
     mdBusiness.setIsAbstract(false);
     mdBusiness.setDisplayLabel("Term "+termClassName);
     mdBusiness.setSuperMdBusiness(mdBusinessTerm);
     
     mdBusiness.apply();
     
     System.out.println("Class created! "+CLASS);  
   }

   private static Set<String> addedRelationships = new HashSet<String>();
   
   @Transaction
   public static void defineNewRelationshipsTerms(File file) throws IOException
   {
     BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
     
     String line = null;
     
     while((line=bufferedReader.readLine())!=null)
     {
       if (line.trim().equals("[Typedef]"))
       {
         line = bufferedReader.readLine();

//         System.out.println("DEFINING NEW TERM!!!!!");
//         System.out.println("------------------------------");
         
         String name = null;
         
         // Get all of the attributes
         while(line != null && !line.trim().equals(""))
         {
           String attributeName = OBOUtil.getAttributeName(line);
           String attributeValue = OBOUtil.getAttributeValue(line);
           
           System.out.println(attributeName+"::"+attributeValue);
           
           if (attributeName.equals("id"))
           {
             name = attributeValue;
           }
           
           line = bufferedReader.readLine();
         }
         
         if (name != null)
         {
           Typedef typedef = Typedef.getByName(name);
           
           if (typedef != null)
           {
             if (addedRelationships.contains(typedef.getComponentName()))
             {
               continue;
             }
             
             Typedef inverseTypedef = typedef.getInverseof();
             
             if (inverseTypedef != null)
             {
               if (addedRelationships.contains(inverseTypedef.getComponentName()))
               {
                 continue;
               }
               
               addedRelationships.add(typedef.getComponentName());
               addedRelationships.add(inverseTypedef.getComponentName());
               
               buildInverseRelationship(typedef, inverseTypedef);
             }
             else
             {
               addedRelationships.add(typedef.getComponentName());
               buildSingleRelationship(typedef);
             }
           }
         }
       }
     }
   }
   
   private static void buildSingleRelationship(Typedef typedef)
   {
     MdBusinessDAOIF mdBusinessIF = com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO.getMdBusinessDAO(CLASS); 
     MdBusiness mdBusinessTerm = MdBusiness.get(mdBusinessIF.getId());

     MdRelationship superMdRelationship = typedef.getMdRelationship();

     MdRelationship mdRelationship = new MdRelationship();
       
     String oboId = typedef.getOboId().replace(":", "_");
     String upperRelationshipName = oboId.substring(0,1).toUpperCase() + oboId.substring(1);
       
     mdRelationship.setPackageName(packageName);
     mdRelationship.setTypeName(upperRelationshipName);
     mdRelationship.setIsAbstract(false);
     mdRelationship.setExtendable(true);
     mdRelationship.setDisplayLabel("Relation "+typedef.getComponentName());
 
     mdRelationship.setSuperMdRelationship(superMdRelationship);

     mdRelationship.setChildMdBusiness(mdBusinessTerm);
     mdRelationship.setChildCardinality("*");
     mdRelationship.setChildDisplayLabel("Child "+typedef.getComponentName());
     mdRelationship.setChildMethod("getChild"+upperRelationshipName);
       
     mdRelationship.setParentMdBusiness(mdBusinessTerm);
     mdRelationship.setParentCardinality("*");
     mdRelationship.setParentDisplayLabel("Parent "+typedef.getComponentName());
     mdRelationship.setParentMethod("getParent"+upperRelationshipName);
       
     mdRelationship.apply();
   }
   
   private static void buildInverseRelationship(Typedef typedef, Typedef inverseTypedef)
   {
     MdBusinessDAOIF mdBusinessIF = com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO.getMdBusinessDAO(CLASS); 
     MdBusiness mdBusinessTerm = MdBusiness.get(mdBusinessIF.getId());
        
     MdRelationship superMdRelationship = typedef.getMdRelationship();
     
     MdRelationship mdRelationship = new MdRelationship();
        
     String typedefName = typedef.getComponentName();
     typedefName = typedefName.substring(0,1).toUpperCase() + typedefName.substring(1);
    
     String inverseTypedefName = inverseTypedef.getComponentName();
     inverseTypedefName = inverseTypedefName.substring(0,1).toUpperCase() + inverseTypedefName.substring(1);
    
     String oboId = typedef.getOboId().replace(":", "_");
     String relationshipName = oboId.substring(0,1).toUpperCase() + oboId.substring(1);
        
     mdRelationship.setPackageName(packageName);
     mdRelationship.setTypeName(relationshipName);
     mdRelationship.setIsAbstract(false);
     mdRelationship.setExtendable(true);
     mdRelationship.setDisplayLabel("Relation "+typedef.getComponentName()+" Has Inverse "+inverseTypedef.getComponentName());
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
   } 
   
   
   @Transaction
   private static void importTerms(File file) throws IOException
   {
     BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
     
     termClass = LoaderDecorator.load(CLASS);
     
     String line = null;
     
     // Get all of the attributes
     while((line=bufferedReader.readLine())!=null)
     {
       if (line.trim().equals("[Term]"))
       {
         Term term = null;
         
         try
         {
           term = (Term)termClass.getConstructor().newInstance();
         }
         catch(Exception e)
         {
           e.printStackTrace();
           
           throw new RuntimeException(e);
         }
        
         createTerm(bufferedReader, term);
         
         term.setNameSpace(namespace);
         
         term.apply();
         
         createdTerms.put(term.getOboId(), term);
       }
     }
     
     bufferedReader.close();
     bufferedReader = new BufferedReader(new FileReader(file));
     
     // Create is_a and synonym relationships
     while((line=bufferedReader.readLine())!=null)
     {
       String id = null;
       String isA = null;
       String relationshipName = null;
       String relationshipWith = null;

       List<String> synonymList = new LinkedList<String>();
       
       if (line.trim().equals("[Term]"))
       {
         line = bufferedReader.readLine();

         while(line != null && !line.trim().equals(""))
         {
           String attributeName = OBOUtil.getAttributeName(line);
           String attributeValue = OBOUtil.getAttributeValue(line);
           
           if (attributeName.equals("id"))
           {
             id = attributeValue;
           }
           else if (attributeName.equals("is_a"))
           {
             isA = attributeValue;
             
             int whiteIndex = isA.indexOf(" ");
             
             isA = isA.substring(0, whiteIndex);
           }
           else if (attributeName.equals("relationship"))
           {             
             int firstWhiteIndex = attributeValue.indexOf(" ");
             int secondWhiteIndex = attributeValue.indexOf(" ", firstWhiteIndex+1);
             
             relationshipName = attributeValue.substring(0, firstWhiteIndex);
             
             relationshipWith = attributeValue.substring(firstWhiteIndex+1, secondWhiteIndex);

           }
           else if (attributeName.equals("synonym"))
           {
             synonymList.add(attributeValue);
           }
           
           line = bufferedReader.readLine();
         }
       }
       
       Term term = createdTerms.get(id);
       Term superTerm = createdTerms.get(isA);
       
       if (term != null)
       {
         if (superTerm != null)
         {
           term.addIsASuperClasses(superTerm).apply();
         }
         
         if (relationshipName != null && Typedef.getByName(relationshipName) != null)
         {
           Term relationshipWithTerm = createdTerms.get(relationshipWith);
         
           if (relationshipWithTerm != null)
           {
             
             String accessorName = "add"+ relationshipName.substring(0,1).toUpperCase() + relationshipName.substring(1);
             try
             {               
               System.out.println("\nAdding "+term.getComponentName()+" "+relationshipName+" "+relationshipWithTerm.getComponentName());
               Relationship relationship =  (Relationship)termClass.getMethod(accessorName, relationshipWithTerm.getClass()).invoke(term, relationshipWithTerm);
               relationship.apply(); 
               
//               BusinessDAO termDAO = BusinessDAO.get(term.getId()).getBusinessDAO();
//               BusinessDAO relationshipWithDAO = BusinessDAO.get(relationshipWithTerm.getId()).getBusinessDAO();
//               
//               
//               Typedef typedef = Typedef.getByName(relationship);
//               
//               MdRelationship mdRelationship = typedef.getMdRelationship();
//               MdRelationshipIF mdRelationshipIF = com.terraframe.mrsparkle.dataaccess.metadata.MdRelationship.get(mdRelationship.getId());
//               String relType = mdRelationshipIF.definesType();
//               termDAO.addParent(relationshipWithDAO, relType).apply();
               
               
             }
             catch (Exception e)
             {
               throw new RuntimeException(e);
             }
           }
         }
       }
       
       for (String synonymName : synonymList)
       {
         TermSynonym termSynonym = TermSynonym.getSynonymByName(synonymName);
         
         if (termSynonym == null)
         {
           termSynonym = new TermSynonym();
           termSynonym.setSynonymName(synonymName);
           termSynonym.apply();
         }
         
         if (term != null)
         {
           term.addSynonyms(termSynonym).apply();
         }
       }   
     }
   }
   
   private static void createTerm(BufferedReader bufferedReader, Term term) throws IOException
   {
     String line = bufferedReader.readLine();

     System.out.println("DEFINING NEW TERM!!!!!");
     System.out.println("------------------------------");
     
     // Get all of the attributes
     while(line != null && !line.trim().equals(""))
     {
       String attributeName = OBOUtil.getAttributeName(line);
       String attributeValue = OBOUtil.getAttributeValue(line);
       
       System.out.println(attributeName+"::"+attributeValue);
       
       if (attributeName.equals("id"))
       {
         term.setOboId(attributeValue);
       }
       else if (attributeName.equals("name"))
       {
         term.setComponentName(attributeValue);
       }
       else if (attributeName.equals("def"))
       {
         term.setDef(attributeValue);
       }
       else if (attributeName.equals("comment"))
       {
         term.setComponentComment(attributeValue);
       }
       
       line = bufferedReader.readLine();
     }
     
     System.out.println("------------------------------");
     
   }

   
//   private static void defineNewRelationshipsTerms(BufferedReader bufferedReader) throws IOException
//   {
//     String line = null;
//     
//     // Get all of the attributes
//     while((line=bufferedReader.readLine())!=null)
//     {
//       if (line.trim().equals("[Typedef]"))
//       {
//         Typedef typedef = new Typedef();   
//         
//         OBOImportRelationships.createRelationship(bufferedReader, typedef);
//         
//         typedef.apply();
//       } 
//     }
//   }
}
