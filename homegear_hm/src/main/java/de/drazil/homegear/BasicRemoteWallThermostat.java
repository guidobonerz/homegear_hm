package de.drazil.homegear;

import java.util.LinkedHashMap;
import java.util.Map;

import de.drazil.homegear.IHeatingDevice.HeatingMode;
import de.drazil.homegear.util.VentilationCalcUtil;

public class BasicRemoteWallThermostat extends BasicRemoteThermostat
{

	public Integer getHumidity() throws Exception
	{
		return getValue("HUMIDITY");
	}

	@Override
	public boolean isWallThermostat()
	{
		return true;
	}

	public void setWeekProgram(WeekProgram weekProgram) throws Exception
	{
		setWeekProgram(weekProgram, false);
	}

	public void setWeekProgram(WeekProgram weekProgram, boolean activateProfile) throws Exception
	{
		Map<String, Object> parameterSet = new LinkedHashMap<>();
		parameterSet.put("WEEK_PROGRAM_POINTER", weekProgram.getName());
		putParamset("CHANNEL0", "master", parameterSet);

		if (activateProfile)
		{
			setControlMode(HeatingMode.AUTO);
		}
	}

	public void setWakeOnRadioEnabled(Boolean state) throws Exception
	{
		Map<String, Object> parameterSet = new LinkedHashMap<>();
		parameterSet.put("BURST_RX", state);
		putParamset("CHANNEL0", "master", parameterSet);
	}

	public Double getHumidityLevel() throws Exception
	{
		return VentilationCalcUtil.getAbsoluteHumidity(getCurrentTemperature(), getHumidity());
	}
}
