package dss.vector.solutions.entomology.assay;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.attributes.InvalidReferenceException;
import com.runwaysdk.query.AggregateFunction;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.InnerJoin;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.Join;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableMoment;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.Surface;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.InsecticideBrandQuery;
import dss.vector.solutions.irs.InsecticideBrandUse;
import dss.vector.solutions.irs.InvalidInsecticideBrandUseProblem;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.util.QueryUtil;

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
		JSONObject queryConfig;
		try {
			queryConfig = new JSONObject(config);
		} catch (JSONException e1) {
			throw new ProgrammingErrorException(e1);
		}

		QueryFactory queryFactory = new QueryFactory();

		ValueQuery valueQuery = new ValueQuery(queryFactory);

		// IMPORTANT: Required call for all query screens.
		Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, queryConfig, layer);

		EfficacyAssayQuery efficacyAssayQuery = (EfficacyAssayQuery) queryMap.get(EfficacyAssay.CLASS);

		AbstractAssayQuery abstractAssayQuery = (AbstractAssayQuery) queryMap.get(AbstractAssay.CLASS);

		if (efficacyAssayQuery != null) {
			QueryUtil.joinTermAllpaths(valueQuery, EfficacyAssay.CLASS, efficacyAssayQuery);

			// There are terms defined on the parent class as well, so grab
			QueryUtil.joinTermAllpaths(valueQuery, AbstractAssay.CLASS, efficacyAssayQuery.getSpecie().getDefiningTableAlias());
			if (abstractAssayQuery != null) {
				valueQuery.WHERE(abstractAssayQuery.getId().EQ(efficacyAssayQuery.getId()));
			} else {
				// ensure date is allways joined
				SelectableMoment dateAttribute = efficacyAssayQuery.getTestDate();
				for (Join join : dateAttribute.getJoinStatements()) {
					valueQuery.WHERE((InnerJoin) join);
				}
			}

			QueryUtil.joinGeoDisplayLabels(valueQuery, EfficacyAssay.CLASS, efficacyAssayQuery);
		}

		InsecticideBrandQuery insecticideBrandQuery = (InsecticideBrandQuery) queryMap.get(InsecticideBrand.CLASS);

		if (insecticideBrandQuery != null) {
			valueQuery.WHERE(efficacyAssayQuery.getInsecticideBrand().EQ(insecticideBrandQuery));

			QueryUtil.joinEnumerationDisplayLabels(valueQuery, InsecticideBrand.CLASS, insecticideBrandQuery);
			QueryUtil.joinTermAllpaths(valueQuery, InsecticideBrand.CLASS, insecticideBrandQuery);
		}
		
		boolean needsAgeRange = false;
		if(valueQuery.hasSelectableRef(QueryConstants.AGE_LOWEST))
		{
		   needsAgeRange = true; 
		   SelectableSQLInteger ageSel;
		   Selectable sel = valueQuery.getSelectableRef(QueryConstants.AGE_LOWEST);
		   if(sel instanceof AggregateFunction)
		   {
		     ageSel = (SelectableSQLInteger) ((AggregateFunction)sel).getSelectable();
		   }
		   else
		   {
		     ageSel = (SelectableSQLInteger) sel;
		   }
		   ageSel.setSQL(QueryUtil.getColumnName(AdultAgeRange.getStartPointMd()));
		}
		
		if(valueQuery.hasSelectableRef(QueryConstants.AGE_HIGHEST))
		{
		   needsAgeRange = true;
	      SelectableSQLInteger ageSel;
	      Selectable sel = valueQuery.getSelectableRef(QueryConstants.AGE_HIGHEST);
	      if(sel instanceof AggregateFunction)
	      {
	        ageSel = (SelectableSQLInteger) ((AggregateFunction)sel).getSelectable();
	      }
	      else
	      {
	        ageSel = (SelectableSQLInteger) sel;
	      }
	      ageSel.setSQL(QueryUtil.getColumnName(AdultAgeRange.getEndPointMd()));
		}
		
		if(needsAgeRange)
		{
		  AdultAgeRangeQuery arQuery = new AdultAgeRangeQuery(valueQuery);
		  valueQuery.FROM(arQuery);
		  valueQuery.WHERE(new InnerJoinEq(efficacyAssayQuery.getAgeRange(), arQuery.getId()));
		}
		

		QueryUtil.setTermRestrictions(valueQuery, queryMap);

		QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

		QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, efficacyAssayQuery.getDisease());

		return valueQuery;

	}

}
