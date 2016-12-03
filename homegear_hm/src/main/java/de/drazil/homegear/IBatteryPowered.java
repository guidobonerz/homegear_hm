package de.drazil.homegear;

public interface IBatteryPowered extends ISmartDevice
{
	public Boolean hasLowBattery() throws Exception;

	public Double getBatteryValue() throws Exception;
}
