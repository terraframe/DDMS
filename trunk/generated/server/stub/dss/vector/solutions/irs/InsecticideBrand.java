package dss.vector.solutions.irs;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.MDSSProperties;

public class InsecticideBrand extends InsecticideBrandBase implements com.runwaysdk.generation.loader.Reloadable {
	private static final long serialVersionUID = 1240597944432L;

	public InsecticideBrand() {
		super();
	}

	@Override
	public void apply() {
		if (this.isNew() && this.getDisease() == null) {
			this.setDisease(Disease.getCurrent());
		}

		this.validateUnitsPerApplication();
		this.validateUnitQuantifier();
		this.validateUnitQualifier();
		this.validateConcentrationQualifier();

		super.apply();
	}

	@Override
	public void validateUnitsPerApplication() {
		if (this.getInsecticideUse() != null && this.getInsecticideUse().contains(InsecticideBrandUse.IRS) && this.getUnitsPerApplication() == null) {
			InsecticideBrandUseProblem p = new InsecticideBrandUseProblem();
			p.setNotification(this, UNITSPERAPPLICATION);
			p.setInsecticideUseLabel(getInsecticideUseMd().getDisplayLabel(Session.getCurrentLocale()));
			p.setInsecticideUseValue(InsecticideBrandUse.IRS.getDisplayLabel());
			p.setAttributeLabel(getUnitsPerApplicationMd().getDisplayLabel(Session.getCurrentLocale()));
			p.setAttributeValue(MDSSProperties.getString("specified"));
			p.apply();

			p.throwIt();
		}
		super.validateUnitsPerApplication();
	}

	@Override
	public void validateUnitQuantifier() {
		if (this.getInsecticideUse() != null && this.getInsecticideUse().contains(InsecticideBrandUse.IRS) && this.getUnitQuantifier() == null) {
			InsecticideBrandUseProblem p = new InsecticideBrandUseProblem();
			p.setNotification(this, UNITQUANTIFIER);
			p.setInsecticideUseLabel(getInsecticideUseMd().getDisplayLabel(Session.getCurrentLocale()));
			p.setInsecticideUseValue(InsecticideBrandUse.IRS.getDisplayLabel());
			p.setAttributeLabel(getUnitQuantifierMd().getDisplayLabel(Session.getCurrentLocale()));
			p.setAttributeValue(MDSSProperties.getString("specified"));
			p.apply();

			p.throwIt();
		}
		super.validateUnitQuantifier();
	}

	@Override
	public void validateUnitQualifier() {
		if (this.getInsecticideUse() != null && this.getInsecticideUse().contains(InsecticideBrandUse.IRS) && !this.getUnitQualifier().contains(InsecticideBrandUnitQualifier.GRAMS)) {
			InsecticideBrandUseProblem p = new InsecticideBrandUseProblem();
			p.setNotification(this, UNITQUALIFIER);
			p.setInsecticideUseLabel(getInsecticideUseMd().getDisplayLabel(Session.getCurrentLocale()));
			p.setInsecticideUseValue(InsecticideBrandUse.IRS.getDisplayLabel());
			p.setAttributeLabel(getUnitQualifierMd().getDisplayLabel(Session.getCurrentLocale()));
			p.setAttributeValue(InsecticideBrandUnitQualifier.GRAMS.getDisplayLabel());
			p.apply();

			p.throwIt();
		}
		super.validateUnitQualifier();
	}

	@Override
	public void validateConcentrationQualifier() {
		if (this.getInsecticideUse() != null && this.getInsecticideUse().contains(InsecticideBrandUse.IRS) && !this.getConcentrationQualifier().contains(InsecticideBrandConcentrationQualifier.PERCENT)) {
			InsecticideBrandUseProblem p = new InsecticideBrandUseProblem();
			p.setNotification(this, CONCENTRATIONQUALIFIER);
			p.setInsecticideUseLabel(getInsecticideUseMd().getDisplayLabel(Session.getCurrentLocale()));
			p.setInsecticideUseValue(InsecticideBrandUse.IRS.getDisplayLabel());
			p.setAttributeLabel(getConcentrationQualifierMd().getDisplayLabel(Session.getCurrentLocale()));
			p.setAttributeValue(InsecticideBrandConcentrationQualifier.PERCENT.getDisplayLabel());
			p.apply();

			p.throwIt();
		}
		super.validateConcentrationQualifier();
	}

	@Override
	public String toString() {
		if (this.isNew()) {
			return "New: " + this.getClassDisplayLabel();
		} else if (this.getProductName() != null) {
			return this.getProductName().getTermDisplayLabel().getValue();
		}

		return super.toString();
	}

	@Override
	protected String buildKey() {
		return this.getProductName().getKey();
	}

	public void populateView(InsecticideBrandView view) {
		view.setInsecticdeId(this.getId());
		view.setProductName(this.getProductName());
		for (InsecticideBrandUse insecticideUse : this.getInsecticideUse()) {
			view.addInsecticideUse(insecticideUse);
		}
		view.setUseDetail(this.getUseDetail());
		view.setActiveIngredient(this.getActiveIngredient());
		view.setConcentrationQuantifier(this.getConcentrationQuantifier());
		for (InsecticideBrandConcentrationQualifier concentrationQualifier : this.getConcentrationQualifier()) {
			view.addConcentrationQualifier(concentrationQualifier);
		}
		view.setUnitQuantifier(this.getUnitQuantifier());
		for (InsecticideBrandUnitQualifier unitQualifier : this.getUnitQualifier()) {
			view.addUnitQualifier(unitQualifier);
		}
		view.setUnitsPerApplication(this.getUnitsPerApplication());
		view.setEnabled(this.getEnabled());
	}

	public static InsecticideBrand getByTerm(Term productTerm) {
		InsecticideBrandQuery query = new InsecticideBrandQuery(new QueryFactory());
		query.WHERE(query.getProductName().EQ(productTerm));
		OIterator<? extends InsecticideBrand> iterator = query.getIterator();
		try {
			if (iterator.hasNext()) {
				return iterator.next();
			}
		} finally {
			iterator.close();
		}
		return null;
	}

	/**
	 * Converts the given string into an insecticide product term and then fetches the corresponding InsecticideBrand
	 * 
	 * @param name name, display label, or termId of an insecticide
	 * @return InsecticideBrand
	 */
	public static InsecticideBrand validateByName(String name) {
  	    Term term = Term.validateByDisplayLabel(name, getProductNameMd());
		InsecticideBrand brand = InsecticideBrand.getByTerm(term);

		if (brand == null) {
			String msg = "An insecticide brand with the name [" + name + "] does not exist";

			throw new DataNotFoundException(msg, MdTypeDAO.getMdTypeDAO(InsecticideBrand.CLASS));
		}

		return brand;
	}

	public InsecticideBrandView getView() {
		InsecticideBrandView view = new InsecticideBrandView();

		this.populateView(view);

		return view;
	}

	public static InsecticideBrandView lockView(String id) {
		return InsecticideBrand.lock(id).getView();
	}

	public static InsecticideBrandView unlockView(String id) {
		return InsecticideBrand.unlock(id).getView();
	}

	public static InsecticideBrandView getView(String id) {
		return InsecticideBrand.get(id).getView();
	}

	@Transaction
	public static InsecticideBrand[] getAll() {
		List<InsecticideBrand> list = new LinkedList<InsecticideBrand>();
		InsecticideBrandQuery query = new InsecticideBrandQuery(new QueryFactory());
		query.ORDER_BY_ASC(query.getCreateDate());

		OIterator<? extends InsecticideBrand> it = query.getIterator();

		try {
			while (it.hasNext()) {
				list.add(it.next());
			}
			return list.toArray(new InsecticideBrand[list.size()]);
		} finally {
			it.close();
		}
	}

	@Transaction
	public static InsecticideBrand[] getAllActive() {
		List<InsecticideBrand> list = new LinkedList<InsecticideBrand>();
		InsecticideBrandQuery query = new InsecticideBrandQuery(new QueryFactory());
		query.WHERE(query.getEnabled().EQ(true));
		query.ORDER_BY_ASC(query.getCreateDate());

		OIterator<? extends InsecticideBrand> it = query.getIterator();

		try {
			while (it.hasNext()) {
				list.add(it.next());
			}
			return list.toArray(new InsecticideBrand[list.size()]);
		} finally {
			it.close();
		}
	}
	

	@Transaction
	public static InsecticideBrand[] getAll(Boolean activeOnly, InsecticideBrandUse[] uses) {
		List<InsecticideBrand> list = new LinkedList<InsecticideBrand>();
		InsecticideBrandQuery query = new InsecticideBrandQuery(new QueryFactory());
		query.WHERE(query.getInsecticideUse().containsAny(uses));
		if (activeOnly) {
			query.WHERE(query.getEnabled().EQ(true));
		}
		query.ORDER_BY_ASC(query.getCreateDate());

		OIterator<? extends InsecticideBrand> it = query.getIterator();

		try {
			while (it.hasNext()) {
				list.add(it.next());
			}
			return list.toArray(new InsecticideBrand[list.size()]);
		} finally {
			it.close();
		}
	}

}
