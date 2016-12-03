package de.drazil.homegear;

public class BasicRemoteSwitch extends BasicSmartDevice {
	public Double getCurrent() throws Exception {
		return getValue("CURRENT");
	}

	public Double getFrequency() throws Exception {
		return getValue("FREQUENCY");
	}

	public Double getVoltage() throws Exception {
		return getValue("VOLTAGE");
	}

	public Double getPower() throws Exception {
		return getValue("POWER");
	}

	public void setState(Boolean state) throws Exception {
		setValue("STATE", state);
	}

	public Boolean getState() throws Exception {
		return getValue("STATE");
	}

	public Integer getSignalStrength() throws Exception {

		return getValue("RSSI_DEVICE");
	}

	public Boolean hasLowBattery() throws Exception {

		return getValue("LOWBAT");
	}

	public Boolean isUnreachable() throws Exception {

		return getValue("UNREACH");
	}
}
