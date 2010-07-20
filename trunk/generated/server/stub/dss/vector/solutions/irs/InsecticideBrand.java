package dss.vector.solutions.irs;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.EnumerationMaster;
import com.runwaysdk.system.metadata.MetadataDisplayLabel;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.MDSSProperties;
import dss.vector.solutions.util.QueryUtil;

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

	public static String getTempTableSQL() {
		MdEntityDAOIF insectNozzleMd = MdEntityDAO.getMdEntityDAO(InsecticideNozzle.CLASS);
		String insectNozzleTable = insectNozzleMd.getTableName();
		String configDateCol = QueryUtil.getColumnName(insectNozzleMd, InsecticideNozzle.CONFIGURATIONDATE);
		String enabledCol = QueryUtil.getColumnName(insectNozzleMd, InsecticideNozzle.ENABLED);

		MdEntityDAOIF enumMasterMD = MdEntityDAO.getMdEntityDAO(EnumerationMaster.CLASS);
		String enumMasterTable = enumMasterMD.getTableName();
		String enumNameCol = QueryUtil.getColumnName(enumMasterMD, EnumerationMaster.ENUMNAME);
		String disLabelCol = QueryUtil.getColumnName(enumMasterMD, EnumerationMaster.DISPLAYLABEL);

		MdEntityDAOIF disLabelMd = MdEntityDAO.getMdEntityDAO(MetadataDisplayLabel.CLASS);
		String disLabelTable = disLabelMd.getTableName();
		String defaultLocaleCol = QueryUtil.getColumnName(disLabelMd, MetadataDisplayLabel.DEFAULTLOCALE);

		MdEntityDAOIF insectBrandMd = MdEntityDAO.getMdEntityDAO(InsecticideBrand.CLASS);
		String insectBrandTable = insectBrandMd.getTableName();
		String unitsPerApplicationCol = QueryUtil.getColumnName(insectBrandMd, InsecticideBrand.UNITSPERAPPLICATION);
		String unitQuantifierCol = QueryUtil.getColumnName(insectBrandMd, InsecticideBrand.UNITQUANTIFIER);
		String concentrationQuantifierCol = QueryUtil.getColumnName(insectBrandMd, InsecticideBrand.CONCENTRATIONQUANTIFIER);
    String diseaseCol = QueryUtil.getColumnName(InsecticideBrand.getDiseaseMd());

		MdEntityDAOIF nozzleMd = MdEntityDAO.getMdEntityDAO(Nozzle.CLASS);
		String nozzleTable = nozzleMd.getTableName();
		String ratioCol = QueryUtil.getColumnName(nozzleMd, Nozzle.RATIO);
		String nozzleDisLabelCol = QueryUtil.getColumnName(nozzleMd, Nozzle.DISPLAYLABEL);

		MdEntityDAOIF areaStandardsMd = MdEntityDAO.getMdEntityDAO(AreaStandards.CLASS);
		String areaStandardsTable = areaStandardsMd.getTableName();
		String structureAreaCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.STRUCTUREAREA);
		String roomCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.ROOM);
		String householdCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.HOUSEHOLD);
		String unitAreaNozzleCovCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.UNITNOZZLEAREACOVERAGE);
		String targetUnitCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.TARGETUNIT);

		
		
		String select = "SELECT " + insectBrandTable + ".id,\n";
		select += "COALESCE(start_date,'1900-01-01'::date) start_date,\n";
		select += insectBrandTable+"."+diseaseCol +" disease,\n";
		select += "COALESCE(end_Date,'2100-01-01'::date) end_date, \n";
		select += "COALESCE((SELECT i." + configDateCol + " FROM " + insectNozzleTable + " i WHERE " + insectNozzleTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " = i." + RelationshipDAOIF.PARENT_ID_COLUMN + " \n";
		select += "AND " + insectNozzleTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = i." + RelationshipDAOIF.CHILD_ID_COLUMN + "  AND " + insectNozzleTable + "." + configDateCol + " < i." + configDateCol + " ORDER BY i." + configDateCol + " DESC LIMIT 1 ),'1900-01-01'::date) nozzleStart, \n";
		select += "COALESCE((SELECT i." + configDateCol + " FROM " + insectNozzleTable + " i WHERE " + insectNozzleTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " = i." + RelationshipDAOIF.PARENT_ID_COLUMN + " \n";
		select += "AND " + insectNozzleTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = i." + RelationshipDAOIF.CHILD_ID_COLUMN + "  AND " + insectNozzleTable + "." + configDateCol + " > i." + configDateCol + "  ORDER BY i." + configDateCol + " ASC LIMIT 1),'2100-01-01'::date) nozzleEnd, \n";
		// --% active ingredient in sachet (2) * weight of sachet (3) * number
		// of sachets in can refill using nozzle 8002 (4) * Nozzle type ratio
		// (6)
		// select += "insecticidebrand.brandname,\n";
		select += "" + unitQuantifierCol + "*" + unitsPerApplicationCol + "*ratio*(" + concentrationQuantifierCol + "/100.0) AS active_ingredient_per_can,\n";
		select += "" + nozzleTable + "." + ratioCol + " AS nozzle_ratio,\n";
		select += "" + nozzleTable + "." + nozzleDisLabelCol + " AS nozzle_defaultLocale,\n";
		select += "" + insectNozzleTable + "." + enabledCol + ",\n";
		select += "" + enumNameCol + " spray_unit,\n";
		select += "(SELECT " + defaultLocaleCol + " FROM " + disLabelTable + " md WHERE " + enumMasterTable + "." + disLabelCol + " = md.id) targetUnit_displayLabel,\n";

		select += "(CASE WHEN " + enumNameCol + " = '" + TargetUnit.ROOM.name() + "' THEN " + roomCol + "  WHEN " + enumNameCol + " = '" + TargetUnit.STRUCTURE.name() + "' THEN " + structureAreaCol + " WHEN " + enumNameCol + " = '" + TargetUnit.HOUSEHOLD.name() + "' THEN " + householdCol + " END ) AS unitarea,\n";
		select += "" + unitAreaNozzleCovCol + " " + unitAreaNozzleCovCol + ",\n";
		select += "((" + unitQuantifierCol + "*" + unitsPerApplicationCol + "*(" + concentrationQuantifierCol + "/100.0)) / " + unitAreaNozzleCovCol + " )  AS standard_application_rate,\n";
		select += "(1000.0 * (" + unitQuantifierCol + "*" + unitsPerApplicationCol + "*(" + concentrationQuantifierCol + "/100.0)) / " + unitAreaNozzleCovCol + " ) AS standard_application_rate_mg,\n";
		select += "ratio * " + unitAreaNozzleCovCol + "/(CASE WHEN " + enumNameCol + " = '" + TargetUnit.ROOM.name() + "' THEN " + roomCol + " WHEN " + enumNameCol + " = '" + TargetUnit.STRUCTURE.name() + "' THEN " + structureAreaCol + " WHEN " + enumNameCol + " = '" + TargetUnit.HOUSEHOLD.name() + "' THEN " + householdCol + " END ) AS units_per_can,\n";

		String from = "FROM ";
		from += areaStandardsTable + " AS " + areaStandardsTable + ",\n";
		from += insectBrandTable + " AS " + insectBrandTable + ",\n";
		from += nozzleTable + " AS " + nozzleTable + ",\n";
		from += insectNozzleTable + " AS " + insectNozzleTable + "\n,";
		from += "" + enumMasterTable + " " + enumMasterTable + ",\n";

		String where = "";
		where += "AND " + insectNozzleTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " = " + insectBrandTable + ".id \n";
		where += "AND " + insectNozzleTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = " + nozzleTable + ".id \n";
		where += "AND " + enumMasterTable + ".id = " + targetUnitCol + "_c \n";

		select = select.substring(0, select.length() - 2);
		where = "WHERE " + where.substring(3, where.length() - 2);
		from = from.substring(0, from.length() - 2);

		return select + "\n" + from + "\n" + where;
		// --nozzle.enabled,
		// --nozzle.lastupdatedate,
	}

}
