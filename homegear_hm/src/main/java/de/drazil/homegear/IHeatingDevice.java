package de.drazil.homegear;

import de.drazil.homegear.bean.HeatingProfile;

public interface IHeatingDevice extends ITemperatureSensor {
	public enum HeatingMode {
		AUTO, MANUAL, BOOST, PARTY
	}

	public Double getDesiredTemperature() throws Exception;

	public Integer getControlMode() throws Exception;

	public void setControlMode(HeatingMode mode, Double temperature) throws Exception;

	public void setControlMode(HeatingMode mode) throws Exception;

	public void setHeatingProfile(HeatingProfile... profile) throws Exception;

	public void setHeatingProfile(WeekProgram weekProgram, boolean activateProfile, HeatingProfile... profile)
			throws Exception;

	public Boolean isLocked(Boolean global) throws Exception;

	public void setLocked(Boolean state) throws Exception;

	public void setLocked(Boolean state, Boolean global) throws Exception;

	public void setWakeOnRadioEnabled(Boolean state) throws Exception;
}
