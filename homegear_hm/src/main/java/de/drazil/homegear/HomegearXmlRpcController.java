package de.drazil.homegear;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import de.drazil.homegear.bean.Device;
import de.drazil.homegear.bean.DeviceConfig;
import de.drazil.homegear.bean.DeviceId;
import de.drazil.xml.XmlHandler;

public class HomegearXmlRpcController {

	private static HomegearXmlRpcController homegearWrapper = null;
	private XmlRpcClientConfigImpl rpcConfig = null;
	private XmlRpcClient rpcClient = null;

	private DeviceConfig devices = null;
	private Map<String, DeviceId> deviceSerialMap;
	private Map<String, Device> deviceMap;

	private HomegearXmlRpcController() throws Exception {

		Properties xmlrpcProperties = new Properties();
		xmlrpcProperties.load(new FileInputStream(new File("xmlrpc.properties")));
		rpcConfig = new XmlRpcClientConfigImpl();
		rpcConfig.setServerURL(new URL(xmlrpcProperties.getProperty("homegear.server.url")));
		rpcClient = new XmlRpcClient();
		rpcClient.setConfig(rpcConfig);

		XmlHandler xh = new XmlHandler();
		devices = xh.readFromXml(DeviceConfig.class, "devices.xml");
		createDeviceIdMapping();
		deviceMap = devices.getDeviceMap();

	}

	public static Object executeMethod(String methodName, Object parameter[]) throws Exception {
		buildInstance();
		return homegearWrapper.rpcClient.execute(methodName, parameter);
	}

	public static void init(String url, String interfaceId, Integer flags) throws Exception {
		executeMethod("init", new Object[] { url, interfaceId, flags });
	}

	public static void addLink(Integer senderID, Integer senderChannel, Integer receiverID, Integer receiverChannel,
			String name, String description) throws Exception {
		executeMethod("addLink",
				new Object[] { senderID, senderChannel, receiverID, receiverChannel, name, description });
	}

	public static void removeLink(Integer senderID, Integer senderChannel, Integer receiverID, Integer receiverChannel)
			throws Exception {
		executeMethod("removeLink", new Object[] { senderID, senderChannel, receiverID, receiverChannel });
	}

	public static DeviceId getDeviceIdBySerialNo(String serialNo) {
		return homegearWrapper.deviceSerialMap.get(serialNo);
	}

	public static <D extends ISmartDevice> List<DeviceId> getDeviceIdList(Class<? super D> deviceClass)
			throws Exception {
		buildInstance();
		List<DeviceId> deviceIdList = new ArrayList<>();
		for (DeviceId id : homegearWrapper.deviceSerialMap.values()) {
			Device device = homegearWrapper.deviceMap.get(id.getType());
			if (device != null) {
				Class<?> cls = Class.forName(device.getAdapterClassName());
				if (deviceClass.isAssignableFrom(cls) && deviceIdList.indexOf(id) == -1) {
					deviceIdList.add(id);
				}
			}
		}
		return deviceIdList;
	}

	public static Device getDeviceBySerialNo(String serialNo) throws Exception {
		buildInstance();
		DeviceId deviceId = getDeviceIdBySerialNo(serialNo);
		return getDevice(deviceId);
	}

	public static Device getDevice(DeviceId deviceId) throws Exception {
		Device device = homegearWrapper.deviceMap.get(deviceId.getType());
		device.setPeerId(deviceId.getId());
		device.setSerialNo(deviceId.getAddress());
		return device;
	}

	private static HomegearXmlRpcController buildInstance() throws Exception {
		if (homegearWrapper == null) {
			homegearWrapper = new HomegearXmlRpcController();
		}
		return homegearWrapper;
	}

	@SuppressWarnings("unchecked")
	private void createDeviceIdMapping() throws Exception {
		deviceSerialMap = new HashMap<>();
		Object deviceListArray[] = (Object[]) rpcClient.execute("listDevices",
				new Object[] { new Boolean(false), new Object[] { "ID", "ADDRESS", "TYPE", "FAMILYID" } });

		for (Object o : deviceListArray) {
			DeviceId deviceId = new DeviceId();

			HashMap<Object, Object> map = (HashMap<Object, Object>) o;
			for (Object key : map.keySet()) {
				Object value = map.get(key);
				if (key.equals("ID")) {
					deviceId.setId((Integer) value);
				} else if (key.equals("TYPE")) {
					deviceId.setType((String) value);
				} else if (key.equals("ADDRESS")) {
					deviceId.setAddress((String) value);
				} else if (key.equals("FAMILYID")) {
					deviceId.setFamilyId((Integer) value);
				}
			}
			HashMap<Object, Object> deviceInfoMap = (HashMap<Object, Object>) rpcClient.execute("getDeviceInfo",
					new Object[] { deviceId.getId(), new String[] { "NAME" } });
			deviceId.setLocation((String) deviceInfoMap.get("NAME"));
			deviceSerialMap.put(deviceId.getAddress(), deviceId);
		}
	}

	public static String getControlModeText(Integer mode) {
		String controlModeName = "unkown";
		switch (mode) {
		case 0:
			controlModeName = "automatic";
			break;
		case 1:
			controlModeName = "manual";
			break;
		}
		return controlModeName;
	}

	public static void main(String args[]) throws Exception {

	}
}
