package dss.vector.solutions.general;

public class ThresholdAlertCalculationType extends ThresholdAlertCalculationTypeBase implements com.runwaysdk.generation.loader.Reloadable {
	private static final long serialVersionUID = 168898279;

	public ThresholdAlertCalculationType() {
		super();
	}

	@Override
	public void apply() {
		if (this.isNew() && this.getDisease() == null) {
			this.setDisease(Disease.getCurrent());
		}
		super.apply();
	}

	@Override
	protected String buildKey() {
		return this.getDisease().getKeyName();
	}

	public static ThresholdAlertCalculationType getCurrent() {
		return getByKey(Disease.getCurrent().getKeyName());
	}
}
