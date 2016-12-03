package de.drazil.homegear;

import java.util.Map;

import de.drazil.homegear.bean.Device;
import de.drazil.homegear.bean.DeviceField;
import de.drazil.homegear.bean.Type;

public class BasicSmartDevice
{
	private String serialNo;

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public String getLocation() throws Exception
	{
		return HomegearXmlRpcController.getDeviceIdBySerialNo(getSerialNo()).getLocation();
	}

	@SuppressWarnings("unchecked")
	protected <ValueType> ValueType getParamset(String name, String typeName) throws Exception
	{
		Device device = HomegearXmlRpcController.getDeviceBySerialNo(getSerialNo());
		Type type = device.getTypeMap().get(typeName);
		DeviceField field = type.getDeviceFieldMap().get(name);
		return (ValueType) HomegearXmlRpcController.executeMethod("getParamset", new Object[] { device.getPeerId(), field.getChannel(), typeName });
	}

	protected void putParamset(String name, String typeName, Map<String, Object> parameterMap) throws Exception
	{
		Device device = HomegearXmlRpcController.getDeviceBySerialNo(getSerialNo());
		Type type = device.getTypeMap().get(typeName);
		DeviceField field = type.getDeviceFieldMap().get(name);
		HomegearXmlRpcController.executeMethod("putParamset", new Object[] { device.getPeerId(), field.getChannel(), typeName, parameterMap });
	}

	@SuppressWarnings("unchecked")
	protected <ValueType> ValueType getValue(String valueName) throws Exception
	{
		Device device = HomegearXmlRpcController.getDeviceBySerialNo(getSerialNo());
		Type type = device.getTypeMap().get("value");
		DeviceField field = type.getDeviceFieldMap().get(valueName);
		return (ValueType) HomegearXmlRpcController.executeMethod("getValue", new Object[] { device.getPeerId(), field.getChannel(), valueName });
	}

	protected <ValueType> void setValue(String valueName, ValueType value) throws Exception
	{
		Device device = HomegearXmlRpcController.getDeviceBySerialNo(getSerialNo());
		Type type = device.getTypeMap().get("value");
		DeviceField field = type.getDeviceFieldMap().get(valueName);
		HomegearXmlRpcController.executeMethod("setValue", new Object[] { device.getPeerId(), field.getChannel(), valueName, value });
	}

}
