package de.drazil.homegear;

public interface ISmartMeter {

	public Double getCurrent() throws Exception;

	public Double getFrequency() throws Exception;

	public Double getVoltage() throws Exception;

	public Double getPower() throws Exception;
}
