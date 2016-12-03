package de.drazil.homegear;

public interface IRemoteWallThermostat extends IBatteryPowered, IRemoteDevice,
		IHeatingDevice, IWeatherSensor {
	public void setWeekProgram(WeekProgram weekProgram) throws Exception;

	public void setWeekProgram(WeekProgram weekProgram, boolean activateProfile)
			throws Exception;
}
