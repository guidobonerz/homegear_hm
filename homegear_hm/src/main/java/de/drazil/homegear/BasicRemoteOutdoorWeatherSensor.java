package de.drazil.homegear;

import de.drazil.homegear.util.VentilationCalcUtil;

public class BasicRemoteOutdoorWeatherSensor extends BasicSmartDevice
{

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

	public Double getCurrentTemperature() throws Exception
	{
		return getValue("TEMPERATURE");
	}

	public Integer getHumidity() throws Exception
	{
		return getValue("HUMIDITY");
	}

	public Double getBatteryValue() throws Exception
	{
		throw new UnsupportedOperationException("function not supported by this device");
	}

	public Double getHumidityLevel() throws Exception
	{
		return VentilationCalcUtil.getAbsoluteHumidity(getCurrentTemperature(), getHumidity());
	}
}
