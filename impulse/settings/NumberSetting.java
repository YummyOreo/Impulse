package impulse.settings;

public class NumberSetting extends Setting {

	public int value, minimum, maximum, increment;

	public NumberSetting(String name, int value, int minimum, int maximum, int increment) {
		this.name = name;
		this.value = value;
		this.minimum = minimum;
		this.maximum = maximum;
		this.increment = increment;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		double precision = 1/ increment;
		this.value = value;
	}
	
	public double getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	public double getNumber() {
		return value;
	}

	public void setNumber(int value) {
		this.value = value;
		
	}

	public int getMaximumValue() {
		return maximum;
	}

	public int getMinimumValue() {
		return minimum;
	}

	public int getPrecision() {
		return (int) increment;
	}

	
}
