package dss.vector.solutions.entomology.assay;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.attributes.InvalidReferenceException;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.Surface;
import dss.vector.solutions.irs.InsecticideBrandUse;
import dss.vector.solutions.irs.InvalidInsecticideBrandUseProblem;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.EfficacyAssayQB;

public class EfficacyAssay extends EfficacyAssayBase implements com.runwaysdk.generation.loader.Reloadable {
	private static final long serialVersionUID = 1236363373386L;

	public EfficacyAssay() {
		super();
	}

	@Override
	public String toString() {
		if (this.isNew()) {
			return "New: " + this.getClassDisplayLabel();
		} else if (this.getGeoEntity() != null && this.getInsecticideBrand() != null) {
			return "(" + this.getGeoEntity().getLabel() + ", " + this.getInsecticideBrand().toString() + ")";
		}

		return super.toString();
	}

	@Override
	public void validateAgeRange() {
		super.validateAgeRange();

		new AdultAgeRangeValidator(this).validate();
	}

	@Override
	public void validateGravid() {
		super.validateGravid();

		new GravidValidator(this).validate();
	}

	@Override
	public void validateFed() {
		super.validateFed();

		new FedValidator(this).validate();
	}

	@Override
	public void validateQuantityDead() {
		super.validateQuantityDead();

		new QuantityDeadValidator(this).validate();
	}

	@Override
	public void validateGeoEntity() {
		if (this.getGeoEntity() != null && !(this.getGeoEntity() instanceof Surface)) {
			throw new InvalidReferenceException("[" + this.getGeoEntity().getId() + "] is not a valid Surface geo id", (MdAttributeReferenceDAOIF) EfficacyAssay.getGeoEntityMd());
		}
	}

	@Override
	public void validateInsecticideBrand() {
		super.validateInsecticideBrand();
		
		if (this.getInsecticideBrand() != null) {
			if (!this.getInsecticideBrand().getInsecticideUse().contains(InsecticideBrandUse.ITM) &&
				!this.getInsecticideBrand().getInsecticideUse().contains(InsecticideBrandUse.IRS)) {
				InvalidInsecticideBrandUseProblem p = new InvalidInsecticideBrandUseProblem();
				p.setNotification(this, INSECTICIDEBRAND);
				p.throwIt();
			}
		}
	}

	@Override
	public void apply() {
		validateGeoEntity();
		validateQuantityDead();
		validateAgeRange();
		validateFed();
		validateGravid();
		validateInsecticideBrand();

		if (this.getQuantityDead() != null && this.getQuantityTested() != null && this.getQuantityDead() <= this.getQuantityTested()) {
			float mortality = calculateMortality(this.getQuantityDead(), this.getQuantityTested());

			this.setQuantityLive(this.getQuantityTested() - this.getQuantityDead());
			this.setMortality(mortality);
		} else {
			this.setQuantityLive(0);
			this.setMortality(new Float(0));
		}

		super.apply();
	}

	private static float calculateMortality(Integer dead, Integer total) {
		return (dead * 100F / total);
	}

	public static EfficacyAssay[] searchByGeoEntityAndDate(GeoEntity geoEntity, Date collectionDate) {
		QueryFactory factory = new QueryFactory();
		EfficacyAssayQuery query = new EfficacyAssayQuery(factory);

		query.WHERE(query.getGeoEntity().EQ(geoEntity));
		query.AND(query.getTestDate().EQ(collectionDate));

		OIterator<? extends EfficacyAssay> iterator = query.getIterator();

		try {
			List<EfficacyAssay> list = new LinkedList<EfficacyAssay>();

			while (iterator.hasNext()) {
				list.add(iterator.next());
			}

			return list.toArray(new EfficacyAssay[list.size()]);
		} finally {
			iterator.close();
		}
	}

	@Override
	public Float getOverallMortalityRate() {
		int dead = 0;
		int total = 0;
		EfficacyAssay[] assays = searchByGeoEntityAndDate(this.getGeoEntity(), this.getTestDate());

		for (EfficacyAssay assay : assays) {
			dead += assay.getQuantityDead();
			total += assay.getQuantityTested();
		}

		if (total == 0) {
			return 0.0F;
		}

		return calculateMortality(dead, total);
	}

	public EfficacyAssayView lockView() {
		this.lock();

		return this.getView();
	}

	public EfficacyAssayView unlockView() {
		this.unlock();

		return this.getView();
	}

	public EfficacyAssayView getView() {
		EfficacyAssayView view = new EfficacyAssayView();

		view.populateView(this);

		return view;
	}

	public static EfficacyAssayView getView(String id) {
		return EfficacyAssay.get(id).getView();
	}

	/**
	 * Takes in an XML string and returns a ValueQuery representing the
	 * structured query in the XML.
	 * 
	 * @param xml
	 * @return
	 */
	public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer) {
    return new EfficacyAssayQB(xml, config, layer).construct();
	}

}
