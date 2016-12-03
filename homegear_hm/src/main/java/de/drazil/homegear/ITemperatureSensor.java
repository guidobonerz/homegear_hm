package de.drazil.homegear;

public interface ITemperatureSensor extends ISmartDevice
{
	public Double getCurrentTemperature() throws Exception;
}
