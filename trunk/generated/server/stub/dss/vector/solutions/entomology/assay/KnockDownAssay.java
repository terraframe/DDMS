package dss.vector.solutions.entomology.assay;

import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.general.KnockDownTimeProperty;

public class KnockDownAssay extends KnockDownAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237230639050L;

  public KnockDownAssay()
  {
    super();
  }

  public static String getResistanceSQL(String[] labels)
  {

    String resistance_result = "resistance_result";

    String susceptibleLabel = labels[0];
    String potentialyResistantLabel = labels[1];
    String resistantLabel = labels[2];

    String assayTable = MdBusiness.getMdBusiness(KnockDownAssay.CLASS).getTableName();
    String abstractTable = MdBusiness.getMdBusiness(AbstractAssay.CLASS).getTableName();
    String knockDownTimeTable = MdBusiness.getMdBusiness(KnockDownTimeProperty.CLASS).getTableName();

    String kd50 = KnockDownAssay.KD50;
    String kd95 = KnockDownAssay.KD95;
    String insectidce = KnockDownAssay.INSECTICIDE;
    String lowerTime = KnockDownTimeProperty.LOWERTIME;
    String upperTime = KnockDownTimeProperty.UPPERTIME;


    String select = "SELECT assay.id AS id,\n";
    String from = "FROM " + abstractTable + " AS abstract, " + assayTable + " AS assay, " + knockDownTimeTable + " AS cutoff \n";
    String where = "AND (abstract."+insectidce+" = cutoff."+insectidce+" AND abstract.id = assay.id) \n";


    select += "(CASE WHEN (assay." + kd50 + " > " + upperTime + " OR assay." + kd95 + " > " + upperTime + ") THEN '" + resistantLabel + "'\n";
    select += "WHEN (assay." + kd50 + " > " + lowerTime + " OR assay." + kd95 + " > " + lowerTime + ")   THEN '" + potentialyResistantLabel + "'\n";
    select += "ELSE '" + susceptibleLabel + "' END) AS " + resistance_result + ",\n";


    select = select.substring(0, select.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);
    from = from.substring(0, from.length() - 2);

    return select + "\n" + from + "\n" + where;
  }
}
