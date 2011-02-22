package dss.vector.solutions.entomology.assay;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.general.KnockDownTimeProperty;
import dss.vector.solutions.general.KnockDownTimePropertyQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.QueryUtil;

public class KnockDownAssay extends KnockDownAssayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237230639050L;

  public KnockDownAssay()
  {
    super();
  }
  
  
  @Override
  public String toString()
  {    
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else if(this.getCollection() != null && this.getInsecticide() != null)
    {
      return "(" + this.getCollection().getCollectionId() + ", " + this.getInsecticide().toString() + ")";
    }
    
    return super.toString();
  }
  
  @Override
  public void apply()
  {    
    super.apply();
    
    if (this.isResistant() && this.getInsecticide() != null && this.getCollection() != null)
    {
      Term activeIngredient = this.getInsecticide().getActiveIngredient();
      String label = activeIngredient.getTermDisplayLabel().getValue(Session.getCurrentLocale());
      String collectionId = this.getCollection().getCollectionId();

      ResistantCollection info = new ResistantCollection();
      info.setActiveIngredient(label);
      info.setCollectionId(collectionId);
      info.throwIt();
    }
  }

  protected boolean isResistant()
  {
    Insecticide _insecticide = this.getInsecticide();
    Double _kd50 = this.getKd50();
    Double _kd95 = this.getKd95();
    
    if(_insecticide != null && _kd50 != null && _kd95 != null)
    {
      KnockDownTimePropertyQuery query = new KnockDownTimePropertyQuery(new QueryFactory());

      query.WHERE(query.getInsecticide().EQ(_insecticide));
      OIterator<? extends KnockDownTimeProperty> iterator = query.getIterator();
      
      try
      {
        if(iterator.hasNext())
        {
          KnockDownTimeProperty property = iterator.next();
          Integer lowerCutoff = property.getLowerTime();
          Integer upperCutoff = property.getUpperTime();
          
          return ((lowerCutoff < _kd50) && (upperCutoff < _kd95));
        }
      }
      finally
      {
        iterator.close();
      }      
    }

    return false;
  }

  public static String getResistanceSQL(String[] labels)
  {

    String resistance_result = "resistance_result";

    String susceptibleLabel = labels[0];
    String potentialyResistantLabel = labels[1];
    String resistantLabel = labels[2];

    String assayTable = MdBusiness.getMdBusiness(KnockDownAssay.CLASS).getTableName();
    String collectionAssayTable = MdBusiness.getMdBusiness(CollectionAssay.CLASS).getTableName();
    String knockDownTimeTable = MdBusiness.getMdBusiness(KnockDownTimeProperty.CLASS).getTableName();

    String kd50 = QueryUtil.getColumnName(KnockDownAssay.getKd50Md());
    String kd95 = QueryUtil.getColumnName(KnockDownAssay.getKd95Md());
    
    String insectidce = QueryUtil.getColumnName(KnockDownAssay.getInsecticideMd());
    String lowerTime = QueryUtil.getColumnName(KnockDownTimeProperty.getLowerTimeMd());
    String upperTime = QueryUtil.getColumnName(KnockDownTimeProperty.getUpperTimeMd());


    String select = "SELECT assay.id AS id,";


    select += "(CASE WHEN (assay." + kd50 + " >= " + lowerTime + " AND assay." + kd95 + " >= " + upperTime + ") THEN '" + resistantLabel + "' ";
    select += "WHEN (assay." + kd50 + " >= " + lowerTime + " OR assay." + kd95 + " >= " + upperTime + ")   THEN '" + potentialyResistantLabel + "' ";
    select += "WHEN (assay." + kd50 + " < " + lowerTime + " OR assay." + kd95 + " < " + upperTime + ")   THEN '" + susceptibleLabel + "' ";
    select += "ELSE '' END) AS " + resistance_result + "";

    String from = "FROM " + collectionAssayTable + " AS collectionAssay, " + assayTable + " AS assay, " + knockDownTimeTable + " AS cutoff ";

    String where = "WHERE (collectionAssay."+insectidce+" = cutoff."+insectidce+" AND collectionAssay.id = assay.id) ";

    return select + "\n" + from + "\n" + where;
  }
}
