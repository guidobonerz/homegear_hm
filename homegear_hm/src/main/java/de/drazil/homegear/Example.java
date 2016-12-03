package de.drazil.homegear;

import java.util.ArrayList;
import java.util.List;

import de.drazil.homegear.bean.HeatingPhase;
import de.drazil.homegear.bean.HeatingProfile;

public class Example {
	public static void main(String args[]) {
		List<HeatingPhase> roomWorkdayHeatingPhases = new ArrayList<>();
		roomWorkdayHeatingPhases.add(new HeatingPhase("00:00", 17.0));
		roomWorkdayHeatingPhases.add(new HeatingPhase("19:00", 21.0));
		roomWorkdayHeatingPhases.add(new HeatingPhase("22:00", 17.0));

		List<HeatingPhase> roomWeekendHeatingPhases = new ArrayList<>();
		roomWeekendHeatingPhases.add(new HeatingPhase("00:00", 17.0));
		roomWeekendHeatingPhases.add(new HeatingPhase("11:00", 21.0));
		roomWeekendHeatingPhases.add(new HeatingPhase("22:00", 18.0));

		try {
			HomegearDeviceFactory.getRemoteWallThermostatBySerialNo("LEQxyz").setHeatingProfile(
					WeekProgram.WEEK_PROGRAM_1, true, new HeatingProfile(Day.WORK_DAYS, roomWorkdayHeatingPhases),
					new HeatingProfile(Day.WEEKEND_DAYS, roomWeekendHeatingPhases));
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
