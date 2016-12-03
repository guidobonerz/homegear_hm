package de.drazil.homegear;

import java.util.ArrayList;
import java.util.List;

import de.drazil.homegear.bean.Device;
import de.drazil.homegear.bean.DeviceId;

public class HomegearDeviceFactory {

	public static IRemoteRadiatorThermostat getRemoteRadiatorThermostatBySerialNo(String serialNo) throws Exception {
		Device device = HomegearXmlRpcController.getDeviceBySerialNo(serialNo);
		IRemoteRadiatorThermostat smartDevice = (IRemoteRadiatorThermostat) Class.forName(device.getAdapterClassName())
				.newInstance();
		smartDevice.setSerialNo(device.getSerialNo());
		return smartDevice;
	}

	public static IRemoteWallThermostat getRemoteWallThermostatBySerialNo(String serialNo) throws Exception {
		Device device = HomegearXmlRpcController.getDeviceBySerialNo(serialNo);
		IRemoteWallThermostat smartDevice = (IRemoteWallThermostat) Class.forName(device.getAdapterClassName())
				.newInstance();
		smartDevice.setSerialNo(device.getSerialNo());

		return smartDevice;
	}

	public static IRemoteOutdoorWeatherSensor getRemoteOutdoorWeatherSensorBySerialNo(String serialNo)
			throws Exception {
		Device device = HomegearXmlRpcController.getDeviceBySerialNo(serialNo);
		IRemoteOutdoorWeatherSensor smartDevice = (IRemoteOutdoorWeatherSensor) Class
				.forName(device.getAdapterClassName()).newInstance();
		smartDevice.setSerialNo(device.getSerialNo());
		return smartDevice;
	}

	public static IRemoteValveDrive getRemoteValveDriveBySerialNo(String serialNo) throws Exception {
		Device device = HomegearXmlRpcController.getDeviceBySerialNo(serialNo);
		IRemoteValveDrive smartDevice = (IRemoteValveDrive) Class.forName(device.getAdapterClassName()).newInstance();
		smartDevice.setSerialNo(device.getSerialNo());
		return smartDevice;
	}

	public static IRemoteSwitch getRemoteSwitchBySerialNo(String serialNo) throws Exception {
		Device device = HomegearXmlRpcController.getDeviceBySerialNo(serialNo);
		IRemoteSwitch smartDevice = (IRemoteSwitch) Class.forName(device.getAdapterClassName()).newInstance();
		smartDevice.setSerialNo(device.getSerialNo());
		return smartDevice;
	}

	public static IRemoteSmokeDetector getRemoteRemoteSmokeDetectorBySerialNo(String serialNo) throws Exception {
		Device device = HomegearXmlRpcController.getDeviceBySerialNo(serialNo);
		IRemoteSmokeDetector smartDevice = (IRemoteSmokeDetector) Class.forName(device.getAdapterClassName())
				.newInstance();
		smartDevice.setSerialNo(device.getSerialNo());
		return smartDevice;
	}

	@SuppressWarnings("unchecked")
	public static <D extends ISmartDevice> List<D> getSmartDeviceList(Class<? super D> deviceClass) throws Exception {
		List<D> deviceList = new ArrayList<>();
		List<DeviceId> deviceIdList = HomegearXmlRpcController.getDeviceIdList(deviceClass);
		for (DeviceId deviceId : deviceIdList) {
			Device device = HomegearXmlRpcController.getDeviceBySerialNo(deviceId.getAddress());
			D smartDevice = (D) Class.forName(device.getAdapterClassName()).newInstance();
			smartDevice.setSerialNo(device.getSerialNo());
			deviceList.add(smartDevice);
		}
		return deviceList;
	}

	public static Object getAllValues() throws Exception {
		return HomegearXmlRpcController.executeMethod("getAllValues", new Object[] { new Boolean(true) });
	}

	@SuppressWarnings("unchecked")
	public static <D extends ISmartDevice> D getSmartDeviceBySerialNo(String serialNo, Class<? super D> deviceClass)
			throws Exception {
		Device device = HomegearXmlRpcController.getDeviceBySerialNo(serialNo);
		D smartDevice = (D) Class.forName(device.getAdapterClassName()).newInstance();
		smartDevice.setSerialNo(device.getSerialNo());
		return smartDevice;

	}

}