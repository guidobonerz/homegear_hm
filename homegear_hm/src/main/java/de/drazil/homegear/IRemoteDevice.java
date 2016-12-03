package de.drazil.homegear;

public interface IRemoteDevice extends ISmartDevice
{
	public Integer getSignalStrength() throws Exception;

	public Boolean isUnreachable() throws Exception;

}
