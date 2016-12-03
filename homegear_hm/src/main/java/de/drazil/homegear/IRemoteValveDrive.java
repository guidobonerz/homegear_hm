package de.drazil.homegear;

public interface IRemoteValveDrive extends IBatteryPowered, IRemoteDevice
{
	public void setValveState(Integer percentage) throws Exception;

	public void setErrorValvePosition(Integer percentage) throws Exception;

	public Integer getValveState() throws Exception;

	public Integer getErrorState() throws Exception;

	public void resetErrorState() throws Exception;

}
