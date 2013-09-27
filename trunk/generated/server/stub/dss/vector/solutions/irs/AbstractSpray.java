package dss.vector.solutions.irs;

import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.IRSQB;

public abstract class AbstractSpray extends AbstractSprayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 507539322;

  public AbstractSpray()
  {
    super();
  }

  @Override
  public void apply()
  {
	this.validateBrand();
	
    super.apply();
  }
  
	@Override
	public void validateBrand() {
		super.validateBrand();
		
		if (this.getBrand() != null) {
			if (!this.getBrand().getInsecticideUse().contains(InsecticideBrandUse.IRS)) {
				InvalidInsecticideBrandUseProblem p = new InvalidInsecticideBrandUseProblem();
				p.setNotification(this, BRAND);
				p.throwIt();
			}
		}
	}
	
  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize)
  {
    return new IRSQB(config, xml, layer, pageSize, pageSize).construct();
  }
}
