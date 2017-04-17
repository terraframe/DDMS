package dss.vector.solutions.intervention.monitor;

public class IPTRecipient extends IPTRecipientBase implements com.runwaysdk.generation.loader.Reloadable {
	private static final long serialVersionUID = 1240792901414L;

	public IPTRecipient() {
		super();
	}

	@Override
	protected String buildKey() {
		return this.getId();
	}

	@Override
	public String toString() {
		if (this.isNew()) {
			return "New: " + this.getClassDisplayLabel();
		} else {
			return this.getPerson().toString();
		}
	}

}
