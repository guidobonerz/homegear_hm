package de.drazil.homegear;

public class BasicRemoteSmokeDetector extends BasicSmartDevice
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

	public Boolean getState() throws Exception
	{
		return getValue("STATE");
	}

	public Boolean hasSmokeChamberError() throws Exception
	{
		return getValue("ERROR_SMOKE_CHAMBER");
	}

	public Boolean hasAlarmTestError() throws Exception
	{
		return getValue("ERROR_ALARM_TEST");
	}

	public Boolean getInstallTest() throws Exception
	{
		return getValue("INSTALL_TEST");
	}

}
