package de.drazil.homegear;

public interface IRemoteRadiatorThermostat extends IBatteryPowered,
		IRemoteDevice, IHeatingDevice {
	public Integer getValveState() throws Exception;

	public void setValveState(Integer value) throws Exception;

	public void setBacklightDisabled(Boolean state) throws Exception;
}
