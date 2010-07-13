package dss.vector.solutions.general;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.RequiredAttributeProblem;

public class ThresholdCalculationType extends ThresholdCalculationTypeBase implements com.runwaysdk.generation.loader.Reloadable {
	private static final long serialVersionUID = 1258003583251L;

	public static final String WEIGHT = "weight";

	public ThresholdCalculationType() {
		super();
	}

	public ThresholdCalculationTypeView getView() {
		ThresholdCalculationTypeView view = new ThresholdCalculationTypeView();
		view.populateView(this);

		return view;
	}

	@Transaction
	public static ThresholdCalculationType getCurrent() {
		ThresholdCalculationTypeQuery query = new ThresholdCalculationTypeQuery(new QueryFactory());
		query.WHERE(query.getDisease().EQ(Disease.getCurrent()));
		query.ORDER_BY_DESC(query.getCreateDate());

		OIterator<? extends ThresholdCalculationType> iterator = query.getIterator();

		try {
			while (iterator.hasNext()) {
				return iterator.next();
			}

			// No ThresholdCalculationType has been created before. Therefore
			// return one containing the default values.
			return null;
		} finally {
			iterator.close();
		}
	}

	@Override
	public void apply() {
		if (this.isDifferent()) {
			this.validateWeights();
		    if (this.isNew() && this.getDisease() == null) {
		    	this.setDisease(Disease.getCurrent());
		    }
			super.apply();
		}
	}

	private boolean isDifferent() {
		ThresholdCalculationTypeView recent = ThresholdCalculationTypeView.getCalculationThreshold();

		if (!this.getT1Method().containsAll(recent.getT1Method())) {
			return true;
		}

		if (!this.getT2Method().containsAll(recent.getT2Method())) {
			return true;
		}

		if (this.getWeeksBefore() == null || !this.getWeeksBefore().equals(recent.getWeeksBefore())) {
			return true;
		}

		if (this.getWeeksAfter() == null || !this.getWeeksAfter().equals(recent.getWeeksAfter())) {
			return true;
		}

		if (this.getPriorYears() == null || !this.getPriorYears().equals(recent.getPriorYears())) {
			return true;
		}

		for (int i = 0; i < 10; i++) {
			if (this.getWeight(i) == null || this.getWeight(i).trim().length() == 0) {
				if (recent.getWeight(i) != null && recent.getWeight(i).trim().length() != 0) {
					// New weight doesn't exist, old weight exists
					return true;
				}
			} else {
				if (recent.getWeight(i) == null || recent.getWeight(i).trim().length() == 0) {
					// New weight exists, old weight doesn't exist
					return true;
				} else {
					// New weight exists, old weight exists
					if (!this.getWeight(i).equals(recent.getWeight(i))) {
						return true;
					}
				}
			}
		}

		if (this.getNotificationMinimum() == null || !this.getNotificationMinimum().equals(recent.getNotificationMinimum())) {
			return true;
		}
		
		if (this.getIdentificationMinimum() == null || !this.getIdentificationMinimum().equals(recent.getIdentificationMinimum())) {
			return true;
		}
		return false;
	}

	public void validateWeights() {
		if (this.getPriorYears() != null) {
			for (int i = 0; i < this.getPriorYears(); i++) {
				if (this.getWeight(i) == null || this.getWeight(i).trim().length() == 0) {
					String msg = "[Weight " + i + "] requires a value.";

					RequiredAttributeProblem p = new RequiredAttributeProblem(msg);
					p.setNotification(this, WEIGHT + i);
					p.apply();
					p.throwIt();
				}
			}
		}
	}

	public String getWeight(int i) {
		return this.getValue(WEIGHT + i);
	}

	public double[] getWeights() {
		double[] weights = new double[this.getPriorYears()];
		switch (this.getPriorYears()) {
		case 10:
			weights[9] = this.getWeight9();
		case 9:
			weights[8] = this.getWeight8();
		case 8:
			weights[7] = this.getWeight7();
		case 7:
			weights[6] = this.getWeight6();
		case 6:
			weights[5] = this.getWeight5();
		case 5:
			weights[4] = this.getWeight4();
		case 4:
			weights[3] = this.getWeight3();
		case 3:
			weights[2] = this.getWeight2();
		case 2:
			weights[1] = this.getWeight1();
		case 1:
			weights[0] = this.getWeight0();
		}
		return weights;
	}

	public void setWeights(double[] weights) {
		switch (this.getPriorYears()) {
		case 10:
			this.setWeight9(weights[9]);
		case 9:
			this.setWeight8(weights[8]);
		case 8:
			this.setWeight7(weights[7]);
		case 7:
			this.setWeight6(weights[6]);
		case 6:
			this.setWeight5(weights[5]);
		case 5:
			this.setWeight4(weights[4]);
		case 4:
			this.setWeight3(weights[3]);
		case 3:
			this.setWeight2(weights[2]);
		case 2:
			this.setWeight1(weights[1]);
		case 1:
			this.setWeight0(weights[0]);
		}
	}
}
