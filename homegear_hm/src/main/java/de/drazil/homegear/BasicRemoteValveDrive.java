package de.drazil.homegear;

import java.util.LinkedHashMap;
import java.util.Map;

public class BasicRemoteValveDrive extends BasicSmartDevice
{

	public void setValveState(Integer percentage) throws Exception
	{
		setValue("VALVE_STATE", percentage);

		// Map<String, Object> parameterSet = new LinkedHashMap<>();
		// parameterSet.put("VALVE_STATE", new Integer(percentage));
		// putParamset("CHANNEL1", "master", parameterSet);

	}

	public Integer getValveState() throws Exception
	{
		return getValue("VALVE_STATE");
	}

	public void setErrorValvePosition(Integer percentage) throws Exception
	{
		Map<String, Object> parameterSet = new LinkedHashMap<>();
		parameterSet.put("VALVE_ERROR_POSITION", new Integer(percentage));
		putParamset("CHANNEL1", "master", parameterSet);
	}

	public Double getBatteryValue() throws Exception
	{
		return getValue("BATTERY_STATE");
	}

	public Integer getSignalStrength() throws Exception
	{

		return getValue("RSSI_DEVICE");
	}

	public Boolean hasLowBattery() throws Exception
	{

		return getValue("LOWBAT");
	}

	public Boolean isUnreachable() throws Exception
	{

		return getValue("UNREACH");
	}

	public void resetErrorState() throws Exception
	{
		setValue("ERROR", 0);
	}

	public Integer getErrorState() throws Exception
	{
		return getValue("ERROR");
	}
}
