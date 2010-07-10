package dss.vector.solutions.entomology.assay;

import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.general.KnockDownTimeProperty;
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


    select += "(CASE WHEN (assay." + kd50 + " > " + upperTime + " OR assay." + kd95 + " > " + upperTime + ") THEN '" + resistantLabel + "' ";
    select += "WHEN (assay." + kd50 + " > " + lowerTime + " OR assay." + kd95 + " > " + lowerTime + ")   THEN '" + potentialyResistantLabel + "' ";
    select += "ELSE '" + susceptibleLabel + "' END) AS " + resistance_result + "";

    String from = "FROM " + collectionAssayTable + " AS collectionAssay, " + assayTable + " AS assay, " + knockDownTimeTable + " AS cutoff ";

    String where = "WHERE (collectionAssay."+insectidce+" = cutoff."+insectidce+" AND collectionAssay.id = assay.id) ";

    return select + "\n" + from + "\n" + where;
  }
}
