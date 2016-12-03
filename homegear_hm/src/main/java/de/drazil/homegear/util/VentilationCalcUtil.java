package de.drazil.homegear.util;

public class VentilationCalcUtil
{
	public static Double getAbsoluteHumidity(Double temperature, Integer relativeHumidity)
	{
		Double absoluteHumidity = null;
		if (temperature < 0.0)
		{
			temperature = 0.0;
		}
		if (temperature < 10.0)
		{
			absoluteHumidity = (3.78 + (0.285 * temperature) + (0.0052 * temperature * temperature) + (0.0005 * temperature * temperature * temperature));
		}
		else
		{
			absoluteHumidity = (7.62 + (0.524 * (temperature - 10.0)) + (0.0131 * (temperature - 10.0) * (temperature - 10.0)) + (0.00048 * (temperature - 10.0)
					* (temperature - 10.0) * (temperature - 10.0)));
		}

		absoluteHumidity = (absoluteHumidity * relativeHumidity) / (100.0 + absoluteHumidity * (100.0 - relativeHumidity) / 622);
		return absoluteHumidity;
	}

	public static boolean doVentilate(Double temperatureIn, Integer humidityIn, Double temperatureOut, Integer humidityOut)
	{
		return getAbsoluteHumidity(temperatureOut, humidityOut) < getAbsoluteHumidity(temperatureIn, humidityIn);
	}

	public static boolean doVentilate(Double temperatureIn, Integer humidityIn, Double humidityLevelOut)
	{
		return humidityLevelOut < getAbsoluteHumidity(temperatureIn, humidityIn);
	}

	public static boolean doVentilate(Double humidityLevelIn, Double humidityLevelOut)
	{
		return humidityLevelOut < humidityLevelIn;
	}
}
