package nz.co.fortytwo.signalk.model.impl;

import static nz.co.fortytwo.signalk.server.util.JsonConstants.SELF;
import static nz.co.fortytwo.signalk.server.util.JsonConstants.VESSELS;
import static nz.co.fortytwo.signalk.server.util.JsonConstants.env_wind;
import static nz.co.fortytwo.signalk.server.util.JsonConstants.env_wind_directionTrue;
import static nz.co.fortytwo.signalk.server.util.JsonConstants.env_wind_speedTrue;
import static nz.co.fortytwo.signalk.server.util.JsonConstants.environment;
import static nz.co.fortytwo.signalk.server.util.JsonConstants.nav_courseOverGroundMagnetic;
import static nz.co.fortytwo.signalk.server.util.JsonConstants.nav_position_latitude;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import mjson.Json;
import nz.co.fortytwo.signalk.model.impl.SignalKModelImpl;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SignalKModelImplTest {

	private static Logger logger = Logger.getLogger(SignalKModelImplTest.class);
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldCreateSignalkModel() {
		SignalKModelImpl signalk = new SignalKModelImpl();
		Json data = Json.read("{\"vessels\":{\"self\":{\"navigation\":{\"courseOverGroundTrue\": {\"value\":11.9600000381},\"courseOverGroundMagnetic\": {\"value\":93.0000000000},\"headingMagnetic\": {\"value\":0.0000000000},\"magneticVariation\": {\"value\":0.0000000000},\"headingTrue\": {\"value\":0.0000000000},\"pitch\": {\"value\":0.0000000000},\"rateOfTurn\": {\"value\":0.0000000000},\"roll\": {\"value\":0.0000000000},\"speedOverGround\": {\"value\":0.0399999980},\"speedThroughWater\": {\"value\":0.0000000000},\"state\": {\"value\":\"Not defined (example)\"},\"anchor\":{\"alarmRadius\": {\"value\":0.0000000000},\"maxRadius\": {\"value\":0.0000000000},\"position\":{\"latitude\": {\"value\":-41.2936935424},\"longitude\": {\"value\":173.2470855712},\"altitude\": {\"value\":0.0000000000}}},\"position\":{\"latitude\": {\"value\":-41.2936935424},\"longitude\": {\"value\":173.2470855712},\"altitude\": {\"value\":0.0000000000}}},\"alarm\":{\"anchorAlarmMethod\": {\"value\":\"sound\"},\"anchorAlarmState\": {\"value\":\"disabled\"},\"autopilotAlarmMethod\": {\"value\":\"sound\"},\"autopilotAlarmState\": {\"value\":\"disabled\"},\"engineAlarmMethod\": {\"value\":\"sound\"},\"engineAlarmState\": {\"value\":\"disabled\"},\"fireAlarmMethod\": {\"value\":\"sound\"},\"fireAlarmState\": {\"value\":\"disabled\"},\"gasAlarmMethod\": {\"value\":\"sound\"},\"gasAlarmState\": {\"value\":\"disabled\"},\"gpsAlarmMethod\": {\"value\":\"sound\"},\"gpsAlarmState\": {\"value\":\"disabled\"},\"maydayAlarmMethod\": {\"value\":\"sound\"},\"maydayAlarmState\": {\"value\":\"disabled\"},\"panpanAlarmMethod\": {\"value\":\"sound\"},\"panpanAlarmState\": {\"value\":\"disabled\"},\"powerAlarmMethod\": {\"value\":\"sound\"},\"powerAlarmState\": {\"value\":\"disabled\"},\"silentInterval\": {\"value\":300},\"windAlarmMethod\": {\"value\":\"sound\"},\"windAlarmState\": {\"value\":\"disabled\"},\"genericAlarmMethod\": {\"value\":\"sound\"},\"genericAlarmState\": {\"value\":\"disabled\"},\"radarAlarmMethod\": {\"value\":\"sound\"},\"radarAlarmState\": {\"value\":\"disabled\"},\"mobAlarmMethod\": {\"value\":\"sound\"},\"mobAlarmState\": {\"value\":\"disabled\"}},\"steering\":{\"rudderAngle\": {\"value\":0.0000000000},\"rudderAngleTarget\": {\"value\":0.0000000000},\"autopilot\":{\"state\": {\"value\":\"off\"},\"mode\": {\"value\":\"powersave\"},\"targetHeadingNorth\": {\"value\":0.0000000000},\"targetHeadingMagnetic\": {\"value\":0.0000000000},\"alarmHeadingXte\": {\"value\":0.0000000000},\"headingSource\": {\"value\":\"compass\"},\"deadZone\": {\"value\":0.0000000000},\"backlash\": {\"value\":0.0000000000},\"gain\": {\"value\":0},\"maxDriveAmps\": {\"value\":0.0000000000},\"maxDriveRate\": {\"value\":0.0000000000},\"portLock\": {\"value\":0.0000000000},\"starboardLock\": {\"value\":0.0000000000}}},\"environment\":{\"airPressureChangeRateAlarm\": {\"value\":0.0000000000},\"airPressure\": {\"value\":1024.0000000000},\"waterTemp\": {\"value\":0.0000000000},\"wind\":{\"speedAlarm\": {\"value\":0.0000000000},\"directionChangeAlarm\": {\"value\":0.0000000000},\"directionApparent\": {\"value\":0.0000000000},\"directionTrue\": {\"value\":0.0000000000},\"speedApparent\": {\"value\":0.0000000000},\"speedTrue\": {\"value\":7.68}}}}}}");
		signalk.merge(data);
		logger.debug(signalk);
		Json cogM = signalk.findValue(signalk.self(),nav_courseOverGroundMagnetic);
		assertEquals(cogM.asDouble(),93.0,0.01);
		Json lat = signalk.findValue(signalk.self(),nav_position_latitude);
		assertEquals(lat.asDouble(),-41.29,0.01);
		Json wind = signalk.findValue(signalk.self(),env_wind_speedTrue);
		assertEquals(wind.asDouble(),7.68,0.000001);
	}

	@Test
	public void shouldReturnBranch() {
		SignalKModelImpl signalk = new SignalKModelImpl();
		Json data = Json.read("{\"vessels\":{\"self\":{\"navigation\":{\"courseOverGroundTrue\": {\"value\":11.9600000381},\"courseOverGroundMagnetic\": {\"value\":93.0000000000},\"headingMagnetic\": {\"value\":0.0000000000},\"magneticVariation\": {\"value\":0.0000000000},\"headingTrue\": {\"value\":0.0000000000},\"pitch\": {\"value\":0.0000000000},\"rateOfTurn\": {\"value\":0.0000000000},\"roll\": {\"value\":0.0000000000},\"speedOverGround\": {\"value\":0.0399999980},\"speedThroughWater\": {\"value\":0.0000000000},\"state\": {\"value\":\"Not defined (example)\"},\"anchor\":{\"alarmRadius\": {\"value\":0.0000000000},\"maxRadius\": {\"value\":0.0000000000},\"position\":{\"latitude\": {\"value\":-41.2936935424},\"longitude\": {\"value\":173.2470855712},\"altitude\": {\"value\":0.0000000000}}},\"position\":{\"latitude\": {\"value\":-41.2936935424},\"longitude\": {\"value\":173.2470855712},\"altitude\": {\"value\":0.0000000000}}},\"alarm\":{\"anchorAlarmMethod\": {\"value\":\"sound\"},\"anchorAlarmState\": {\"value\":\"disabled\"},\"autopilotAlarmMethod\": {\"value\":\"sound\"},\"autopilotAlarmState\": {\"value\":\"disabled\"},\"engineAlarmMethod\": {\"value\":\"sound\"},\"engineAlarmState\": {\"value\":\"disabled\"},\"fireAlarmMethod\": {\"value\":\"sound\"},\"fireAlarmState\": {\"value\":\"disabled\"},\"gasAlarmMethod\": {\"value\":\"sound\"},\"gasAlarmState\": {\"value\":\"disabled\"},\"gpsAlarmMethod\": {\"value\":\"sound\"},\"gpsAlarmState\": {\"value\":\"disabled\"},\"maydayAlarmMethod\": {\"value\":\"sound\"},\"maydayAlarmState\": {\"value\":\"disabled\"},\"panpanAlarmMethod\": {\"value\":\"sound\"},\"panpanAlarmState\": {\"value\":\"disabled\"},\"powerAlarmMethod\": {\"value\":\"sound\"},\"powerAlarmState\": {\"value\":\"disabled\"},\"silentInterval\": {\"value\":300},\"windAlarmMethod\": {\"value\":\"sound\"},\"windAlarmState\": {\"value\":\"disabled\"},\"genericAlarmMethod\": {\"value\":\"sound\"},\"genericAlarmState\": {\"value\":\"disabled\"},\"radarAlarmMethod\": {\"value\":\"sound\"},\"radarAlarmState\": {\"value\":\"disabled\"},\"mobAlarmMethod\": {\"value\":\"sound\"},\"mobAlarmState\": {\"value\":\"disabled\"}},\"steering\":{\"rudderAngle\": {\"value\":0.0000000000},\"rudderAngleTarget\": {\"value\":0.0000000000},\"autopilot\":{\"state\": {\"value\":\"off\"},\"mode\": {\"value\":\"powersave\"},\"targetHeadingNorth\": {\"value\":0.0000000000},\"targetHeadingMagnetic\": {\"value\":0.0000000000},\"alarmHeadingXte\": {\"value\":0.0000000000},\"headingSource\": {\"value\":\"compass\"},\"deadZone\": {\"value\":0.0000000000},\"backlash\": {\"value\":0.0000000000},\"gain\": {\"value\":0},\"maxDriveAmps\": {\"value\":0.0000000000},\"maxDriveRate\": {\"value\":0.0000000000},\"portLock\": {\"value\":0.0000000000},\"starboardLock\": {\"value\":0.0000000000}}},\"environment\":{\"airPressureChangeRateAlarm\": {\"value\":0.0000000000},\"airPressure\": {\"value\":1024.0000000000},\"waterTemp\": {\"value\":0.0000000000},\"wind\":{\"speedAlarm\": {\"value\":0.0000000000},\"directionChangeAlarm\": {\"value\":0.0000000000},\"directionApparent\": {\"value\":0.0000000000},\"directionTrue\": {\"value\":0.0000000000},\"speedApparent\": {\"value\":0.0000000000},\"speedTrue\": {\"value\":7.68}}}}}}");
		signalk.merge(data);
		Json wind = Json.read("{\"speedAlarm\": {\"value\":0.0000000000},\"directionChangeAlarm\": {\"value\":0.0000000000},\"directionApparent\": {\"value\":0.0000000000},\"directionTrue\": {\"value\":0.0000000000},\"speedApparent\": {\"value\":0.0000000000},\"speedTrue\": {\"value\":7.68}}");
		Json branch = signalk.atPath(VESSELS, SELF, env_wind);
		assertEquals(wind, branch);
	}
	@Test
	public void shouldReturnLeaf() {
		SignalKModelImpl signalk = new SignalKModelImpl();
		Json data = Json.read("{\"vessels\":{\"self\":{\"navigation\":{\"courseOverGroundTrue\": {\"value\":11.9600000381},\"courseOverGroundMagnetic\": {\"value\":93.0000000000},\"headingMagnetic\": {\"value\":0.0000000000},\"magneticVariation\": {\"value\":0.0000000000},\"headingTrue\": {\"value\":0.0000000000},\"pitch\": {\"value\":0.0000000000},\"rateOfTurn\": {\"value\":0.0000000000},\"roll\": {\"value\":0.0000000000},\"speedOverGround\": {\"value\":0.0399999980},\"speedThroughWater\": {\"value\":0.0000000000},\"state\": {\"value\":\"Not defined (example)\"},\"anchor\":{\"alarmRadius\": {\"value\":0.0000000000},\"maxRadius\": {\"value\":0.0000000000},\"position\":{\"latitude\": {\"value\":-41.2936935424},\"longitude\": {\"value\":173.2470855712},\"altitude\": {\"value\":0.0000000000}}},\"position\":{\"latitude\": {\"value\":-41.2936935424},\"longitude\": {\"value\":173.2470855712},\"altitude\": {\"value\":0.0000000000}}},\"alarm\":{\"anchorAlarmMethod\": {\"value\":\"sound\"},\"anchorAlarmState\": {\"value\":\"disabled\"},\"autopilotAlarmMethod\": {\"value\":\"sound\"},\"autopilotAlarmState\": {\"value\":\"disabled\"},\"engineAlarmMethod\": {\"value\":\"sound\"},\"engineAlarmState\": {\"value\":\"disabled\"},\"fireAlarmMethod\": {\"value\":\"sound\"},\"fireAlarmState\": {\"value\":\"disabled\"},\"gasAlarmMethod\": {\"value\":\"sound\"},\"gasAlarmState\": {\"value\":\"disabled\"},\"gpsAlarmMethod\": {\"value\":\"sound\"},\"gpsAlarmState\": {\"value\":\"disabled\"},\"maydayAlarmMethod\": {\"value\":\"sound\"},\"maydayAlarmState\": {\"value\":\"disabled\"},\"panpanAlarmMethod\": {\"value\":\"sound\"},\"panpanAlarmState\": {\"value\":\"disabled\"},\"powerAlarmMethod\": {\"value\":\"sound\"},\"powerAlarmState\": {\"value\":\"disabled\"},\"silentInterval\": {\"value\":300},\"windAlarmMethod\": {\"value\":\"sound\"},\"windAlarmState\": {\"value\":\"disabled\"},\"genericAlarmMethod\": {\"value\":\"sound\"},\"genericAlarmState\": {\"value\":\"disabled\"},\"radarAlarmMethod\": {\"value\":\"sound\"},\"radarAlarmState\": {\"value\":\"disabled\"},\"mobAlarmMethod\": {\"value\":\"sound\"},\"mobAlarmState\": {\"value\":\"disabled\"}},\"steering\":{\"rudderAngle\": {\"value\":0.0000000000},\"rudderAngleTarget\": {\"value\":0.0000000000},\"autopilot\":{\"state\": {\"value\":\"off\"},\"mode\": {\"value\":\"powersave\"},\"targetHeadingNorth\": {\"value\":0.0000000000},\"targetHeadingMagnetic\": {\"value\":0.0000000000},\"alarmHeadingXte\": {\"value\":0.0000000000},\"headingSource\": {\"value\":\"compass\"},\"deadZone\": {\"value\":0.0000000000},\"backlash\": {\"value\":0.0000000000},\"gain\": {\"value\":0},\"maxDriveAmps\": {\"value\":0.0000000000},\"maxDriveRate\": {\"value\":0.0000000000},\"portLock\": {\"value\":0.0000000000},\"starboardLock\": {\"value\":0.0000000000}}},\"environment\":{\"airPressureChangeRateAlarm\": {\"value\":0.0000000000},\"airPressure\": {\"value\":1024.0000000000},\"waterTemp\": {\"value\":0.0000000000},\"wind\":{\"speedAlarm\": {\"value\":0.0000000000},\"directionChangeAlarm\": {\"value\":0.0000000000},\"directionApparent\": {\"value\":0.0000000000},\"directionTrue\": {\"value\":256.3},\"speedApparent\": {\"value\":0.0000000000},\"speedTrue\": {\"value\":7.68}}}}}}");
		signalk.merge(data);
		Json dirTrue = signalk.findValue(signalk.self(), env_wind_directionTrue);
		assertEquals(dirTrue.asDouble(),256.3,0.000001);
	}
	
	@Test
	public void shouldMergeBranch() {
		SignalKModelImpl signalk = new SignalKModelImpl();
		Json data = Json.read("{\"vessels\":{\"self\":{\"navigation\":{\"courseOverGroundTrue\": {\"value\":11.9600000381},\"courseOverGroundMagnetic\": {\"value\":93.0000000000},\"headingMagnetic\": {\"value\":0.0000000000},\"magneticVariation\": {\"value\":0.0000000000},\"headingTrue\": {\"value\":0.0000000000},\"pitch\": {\"value\":0.0000000000},\"rateOfTurn\": {\"value\":0.0000000000},\"roll\": {\"value\":0.0000000000},\"speedOverGround\": {\"value\":0.0399999980},\"speedThroughWater\": {\"value\":0.0000000000},\"state\": {\"value\":\"Not defined (example)\"},\"anchor\":{\"alarmRadius\": {\"value\":0.0000000000},\"maxRadius\": {\"value\":0.0000000000},\"position\":{\"latitude\": {\"value\":-41.2936935424},\"longitude\": {\"value\":173.2470855712},\"altitude\": {\"value\":0.0000000000}}},\"position\":{\"latitude\": {\"value\":-41.2936935424},\"longitude\": {\"value\":173.2470855712},\"altitude\": {\"value\":0.0000000000}}},\"alarm\":{\"anchorAlarmMethod\": {\"value\":\"sound\"},\"anchorAlarmState\": {\"value\":\"disabled\"},\"autopilotAlarmMethod\": {\"value\":\"sound\"},\"autopilotAlarmState\": {\"value\":\"disabled\"},\"engineAlarmMethod\": {\"value\":\"sound\"},\"engineAlarmState\": {\"value\":\"disabled\"},\"fireAlarmMethod\": {\"value\":\"sound\"},\"fireAlarmState\": {\"value\":\"disabled\"},\"gasAlarmMethod\": {\"value\":\"sound\"},\"gasAlarmState\": {\"value\":\"disabled\"},\"gpsAlarmMethod\": {\"value\":\"sound\"},\"gpsAlarmState\": {\"value\":\"disabled\"},\"maydayAlarmMethod\": {\"value\":\"sound\"},\"maydayAlarmState\": {\"value\":\"disabled\"},\"panpanAlarmMethod\": {\"value\":\"sound\"},\"panpanAlarmState\": {\"value\":\"disabled\"},\"powerAlarmMethod\": {\"value\":\"sound\"},\"powerAlarmState\": {\"value\":\"disabled\"},\"silentInterval\": {\"value\":300},\"windAlarmMethod\": {\"value\":\"sound\"},\"windAlarmState\": {\"value\":\"disabled\"},\"genericAlarmMethod\": {\"value\":\"sound\"},\"genericAlarmState\": {\"value\":\"disabled\"},\"radarAlarmMethod\": {\"value\":\"sound\"},\"radarAlarmState\": {\"value\":\"disabled\"},\"mobAlarmMethod\": {\"value\":\"sound\"},\"mobAlarmState\": {\"value\":\"disabled\"}},\"steering\":{\"rudderAngle\": {\"value\":0.0000000000},\"rudderAngleTarget\": {\"value\":0.0000000000},\"autopilot\":{\"state\": {\"value\":\"off\"},\"mode\": {\"value\":\"powersave\"},\"targetHeadingNorth\": {\"value\":0.0000000000},\"targetHeadingMagnetic\": {\"value\":0.0000000000},\"alarmHeadingXte\": {\"value\":0.0000000000},\"headingSource\": {\"value\":\"compass\"},\"deadZone\": {\"value\":0.0000000000},\"backlash\": {\"value\":0.0000000000},\"gain\": {\"value\":0},\"maxDriveAmps\": {\"value\":0.0000000000},\"maxDriveRate\": {\"value\":0.0000000000},\"portLock\": {\"value\":0.0000000000},\"starboardLock\": {\"value\":0.0000000000}}},\"environment\":{\"airPressureChangeRateAlarm\": {\"value\":0.0000000000},\"airPressure\": {\"value\":1024.0000000000},\"waterTemp\": {\"value\":0.0000000000}\"}}}}");
		signalk.merge(data);
		assertNull(signalk.atPath(VESSELS, SELF, env_wind));
		Json wind = Json.read("{\"vessels\":{\"self\":{\"environment\":{\"airPressureChangeRateAlarm\": {\"value\":0.0000000000},\"airPressure\": {\"value\":1024.0000000000},\"waterTemp\": {\"value\":0.0000000000},\"wind\":{\"speedAlarm\": {\"value\":0.0000000000},\"directionChangeAlarm\": {\"value\":0.0000000000},\"directionApparent\": {\"value\":0.0000000000},\"directionTrue\": {\"value\":0.0000000000},\"speedApparent\": {\"value\":0.0000000000},\"speedTrue\": {\"value\":7.68}}}}}}");
		signalk.merge(wind);
		assertNotNull(signalk.atPath(VESSELS, SELF, env_wind));
		Json dirTrue = signalk.atPath(VESSELS, SELF, env_wind_directionTrue);
		assertEquals(dirTrue.asDouble(),256.3,0.000001);
	}
	
	@Test
	public void shouldMergeBranchAtPath() {
		SignalKModelImpl signalk = new SignalKModelImpl();
		Json data = Json.read("{\"vessels\":{\"self\":{\"navigation\":{\"courseOverGroundTrue\":0.0000000000,\"courseOverGroundMagnetic\":93.0000000000,\"headingMagnetic\":0.0000000000,\"magneticVariation\":0.0000000000,\"headingTrue\":0.0000000000,\"pitch\":0.0000000000,\"rateOfTurn\":0.0000000000,\"roll\":0.0000000000,\"speedOverGround\":0.0000000000,\"speedThroughWater\":0.0000000000,\"state\":\"Not defined (example)\",\"anchor\":{\"alarmRadius\":0.0000000000,\"maxRadius\":0.0000000000,\"position\":{\"latitude\":0.0000000000,\"longitude\":0.0000000000,\"altitude\":0.0000000000}},\"position\":{\"latitude\":-43.2000000000,\"longitude\":0.0000000000,\"altitude\":0.0000000000}},\"alarm\":{\"anchorAlarmMethod\":\"sound\",\"anchorAlarmState\":\"disabled\",\"autopilotAlarmMethod\":\"sound\",\"autopilotAlarmState\":\"disabled\",\"engineAlarmMethod\":\"sound\",\"engineAlarmState\":\"disabled\",\"fireAlarmMethod\":\"sound\",\"fireAlarmState\":\"disabled\",\"gasAlarmMethod\":\"sound\",\"gasAlarmState\":\"disabled\",\"gpsAlarmMethod\":\"sound\",\"gpsAlarmState\":\"disabled\",\"maydayAlarmMethod\":\"sound\",\"maydayAlarmState\":\"disabled\",\"panpanAlarmMethod\":\"sound\",\"panpanAlarmState\":\"disabled\",\"powerAlarmMethod\":\"sound\",\"powerAlarmState\":\"disabled\",\"silentInterval\":300,\"windAlarmMethod\":\"sound\",\"windAlarmState\":\"disabled\",\"genericAlarmMethod\":\"sound\",\"genericAlarmState\":\"disabled\",\"radarAlarmMethod\":\"sound\",\"radarAlarmState\":\"disabled\",\"mobAlarmMethod\":\"sound\",\"mobAlarmState\":\"disabled\"},\"steering\":{\"rudderAngle\":0.0000000000,\"rudderAngleTarget\":0.0000000000,\"autopilot\":{\"state\":\"off\",\"mode\":\"powersave\",\"targetHeadingNorth\":0.0000000000,\"targetHeadingMagnetic\":0.0000000000,\"alarmHeadingXte\":0.0000000000,\"headingSource\":\"compass\",\"deadZone\":0.0000000000,\"backlash\":0.0000000000,\"gain\":0,\"maxDriveAmps\":0.0000000000,\"maxDriveRate\":0.0000000000,\"portLock\":0.0000000000,\"starboardLock\":0.0000000000}},\"environment\":{\"airPressureChangeRateAlarm\":0.0000000000,\"airPressure\":1024.0000000000,\"waterTemp\":0.0000000000}}}}");
		signalk.merge(data);
		assertNull(signalk.atPath(VESSELS, SELF, env_wind));
		Json wind = Json.read("{\"speedAlarm\":0.0000000000,\"directionChangeAlarm\":0.0000000000,\"directionApparent\":0.0000000000,\"directionTrue\":256.300000000,\"speedApparent\":0.0000000000,\"speedTrue\":7.6800000000}");
		signalk.mergeAtPath(VESSELS+"."+ SELF+".environment","wind", wind);
		assertNotNull(signalk.atPath(VESSELS, SELF, env_wind));
		Json dirTrue = signalk.atPath(VESSELS, SELF, env_wind_directionTrue);
		assertEquals(dirTrue.asDouble(),256.3,0.000001);
	}
	
	@Test
	public void shouldMergeBranchAtExistingPath() {
		SignalKModelImpl signalk = new SignalKModelImpl();
		Json data = Json.read("{\"vessels\":{\"self\":{\"navigation\":{\"courseOverGroundTrue\":0.0000000000,\"courseOverGroundMagnetic\":93.0000000000,\"headingMagnetic\":0.0000000000,\"magneticVariation\":0.0000000000,\"headingTrue\":0.0000000000,\"pitch\":0.0000000000,\"rateOfTurn\":0.0000000000,\"roll\":0.0000000000,\"speedOverGround\":0.0000000000,\"speedThroughWater\":0.0000000000,\"state\":\"Not defined (example)\",\"anchor\":{\"alarmRadius\":0.0000000000,\"maxRadius\":0.0000000000,\"position\":{\"latitude\":0.0000000000,\"longitude\":0.0000000000,\"altitude\":0.0000000000}},\"position\":{\"latitude\":-43.2000000000,\"longitude\":0.0000000000,\"altitude\":0.0000000000}},\"alarm\":{\"anchorAlarmMethod\":\"sound\",\"anchorAlarmState\":\"disabled\",\"autopilotAlarmMethod\":\"sound\",\"autopilotAlarmState\":\"disabled\",\"engineAlarmMethod\":\"sound\",\"engineAlarmState\":\"disabled\",\"fireAlarmMethod\":\"sound\",\"fireAlarmState\":\"disabled\",\"gasAlarmMethod\":\"sound\",\"gasAlarmState\":\"disabled\",\"gpsAlarmMethod\":\"sound\",\"gpsAlarmState\":\"disabled\",\"maydayAlarmMethod\":\"sound\",\"maydayAlarmState\":\"disabled\",\"panpanAlarmMethod\":\"sound\",\"panpanAlarmState\":\"disabled\",\"powerAlarmMethod\":\"sound\",\"powerAlarmState\":\"disabled\",\"silentInterval\":300,\"windAlarmMethod\":\"sound\",\"windAlarmState\":\"disabled\",\"genericAlarmMethod\":\"sound\",\"genericAlarmState\":\"disabled\",\"radarAlarmMethod\":\"sound\",\"radarAlarmState\":\"disabled\",\"mobAlarmMethod\":\"sound\",\"mobAlarmState\":\"disabled\"},\"steering\":{\"rudderAngle\":0.0000000000,\"rudderAngleTarget\":0.0000000000,\"autopilot\":{\"state\":\"off\",\"mode\":\"powersave\",\"targetHeadingNorth\":0.0000000000,\"targetHeadingMagnetic\":0.0000000000,\"alarmHeadingXte\":0.0000000000,\"headingSource\":\"compass\",\"deadZone\":0.0000000000,\"backlash\":0.0000000000,\"gain\":0,\"maxDriveAmps\":0.0000000000,\"maxDriveRate\":0.0000000000,\"portLock\":0.0000000000,\"starboardLock\":0.0000000000}},\"environment\":{\"airPressureChangeRateAlarm\":0.0000000000,\"airPressure\":1024.0000000000,\"waterTemp\":0.0000000000,\"wind\":{\"speedAlarm\":0.0000000000,\"directionChangeAlarm\":0.0000000000,\"directionApparent\":0.0000000000,\"directionTrue\":256.300000000,\"speedApparent\":0.0000000000,\"speedTrue\":7.6800000000}}}}}");
		signalk.merge(data);
		assertNotNull(signalk.atPath(VESSELS, SELF, env_wind));
		Json wind = Json.read("{\"speedAlarm\":0.0000000000,\"directionChangeAlarm\":0.0000000000,\"directionApparent\":0.0000000000,\"directionTrue\":256.300000000,\"speedApparent\":0.0000000000,\"speedTrue\":7.6800000000}");
		signalk.mergeAtPath(VESSELS+"."+ SELF+".environment","wind", wind);
		assertNotNull(signalk.atPath(VESSELS, SELF, env_wind));
		Json dirTrue = signalk.atPath(VESSELS, SELF, env_wind_directionTrue);
		assertEquals(dirTrue.asDouble(),256.3,0.000001);
	}
	
	@Test
	public void shouldMergeBranchWithoutKey() {
		SignalKModelImpl signalk = new SignalKModelImpl();
		Json data = Json.read("{\"vessels\":{\"self\":{\"navigation\":{\"courseOverGroundTrue\":0.0000000000,\"courseOverGroundMagnetic\":93.0000000000,\"headingMagnetic\":0.0000000000,\"magneticVariation\":0.0000000000,\"headingTrue\":0.0000000000,\"pitch\":0.0000000000,\"rateOfTurn\":0.0000000000,\"roll\":0.0000000000,\"speedOverGround\":0.0000000000,\"speedThroughWater\":0.0000000000,\"state\":\"Not defined (example)\",\"anchor\":{\"alarmRadius\":0.0000000000,\"maxRadius\":0.0000000000,\"position\":{\"latitude\":0.0000000000,\"longitude\":0.0000000000,\"altitude\":0.0000000000}},\"position\":{\"latitude\":-43.2000000000,\"longitude\":0.0000000000,\"altitude\":0.0000000000}},\"alarm\":{\"anchorAlarmMethod\":\"sound\",\"anchorAlarmState\":\"disabled\",\"autopilotAlarmMethod\":\"sound\",\"autopilotAlarmState\":\"disabled\",\"engineAlarmMethod\":\"sound\",\"engineAlarmState\":\"disabled\",\"fireAlarmMethod\":\"sound\",\"fireAlarmState\":\"disabled\",\"gasAlarmMethod\":\"sound\",\"gasAlarmState\":\"disabled\",\"gpsAlarmMethod\":\"sound\",\"gpsAlarmState\":\"disabled\",\"maydayAlarmMethod\":\"sound\",\"maydayAlarmState\":\"disabled\",\"panpanAlarmMethod\":\"sound\",\"panpanAlarmState\":\"disabled\",\"powerAlarmMethod\":\"sound\",\"powerAlarmState\":\"disabled\",\"silentInterval\":300,\"windAlarmMethod\":\"sound\",\"windAlarmState\":\"disabled\",\"genericAlarmMethod\":\"sound\",\"genericAlarmState\":\"disabled\",\"radarAlarmMethod\":\"sound\",\"radarAlarmState\":\"disabled\",\"mobAlarmMethod\":\"sound\",\"mobAlarmState\":\"disabled\"},\"steering\":{\"rudderAngle\":0.0000000000,\"rudderAngleTarget\":0.0000000000,\"autopilot\":{\"state\":\"off\",\"mode\":\"powersave\",\"targetHeadingNorth\":0.0000000000,\"targetHeadingMagnetic\":0.0000000000,\"alarmHeadingXte\":0.0000000000,\"headingSource\":\"compass\",\"deadZone\":0.0000000000,\"backlash\":0.0000000000,\"gain\":0,\"maxDriveAmps\":0.0000000000,\"maxDriveRate\":0.0000000000,\"portLock\":0.0000000000,\"starboardLock\":0.0000000000}},\"environment\":{\"airPressureChangeRateAlarm\":0.0000000000,\"airPressure\":1024.0000000000,\"waterTemp\":0.0000000000,\"wind\":{\"speedAlarm\":0.0000000000,\"directionChangeAlarm\":0.0000000000,\"directionApparent\":0.0000000000,\"directionTrue\":256.300000000,\"speedApparent\":0.0000000000,\"speedTrue\":7.6800000000}}}}}");
		signalk.merge(data);
		assertNotNull(signalk.atPath(VESSELS, SELF, env_wind));
		Json wind = Json.read("{\"speedAlarm\":0.0000000000,\"directionChangeAlarm\":0.0000000000,\"directionApparent\":0.0000000000,\"directionTrue\":256.300000000,\"speedApparent\":0.0000000000,\"speedTrue\":7.6800000000}");
		signalk.mergeAtPath(VESSELS+"."+ SELF+".environment.wind", wind);
		assertNotNull(signalk.atPath(VESSELS, SELF, env_wind));
		Json dirTrue = signalk.atPath(VESSELS, SELF, env_wind_directionTrue);
		assertEquals(dirTrue.asDouble(),256.3,0.000001);
	}
	@Test
	public void shouldSetLeaf() {
		
	}
	@Test
	public void shouldDeleteBranch() {
		SignalKModelImpl signalk = new SignalKModelImpl();
		Json data = Json.read("{\"vessels\":{\"self\":{\"navigation\":{\"courseOverGroundTrue\":0.0000000000,\"courseOverGroundMagnetic\":93.0000000000,\"headingMagnetic\":0.0000000000,\"magneticVariation\":0.0000000000,\"headingTrue\":0.0000000000,\"pitch\":0.0000000000,\"rateOfTurn\":0.0000000000,\"roll\":0.0000000000,\"speedOverGround\":0.0000000000,\"speedThroughWater\":0.0000000000,\"state\":\"Not defined (example)\",\"anchor\":{\"alarmRadius\":0.0000000000,\"maxRadius\":0.0000000000,\"position\":{\"latitude\":0.0000000000,\"longitude\":0.0000000000,\"altitude\":0.0000000000}},\"position\":{\"latitude\":-43.2000000000,\"longitude\":0.0000000000,\"altitude\":0.0000000000}},\"alarm\":{\"anchorAlarmMethod\":\"sound\",\"anchorAlarmState\":\"disabled\",\"autopilotAlarmMethod\":\"sound\",\"autopilotAlarmState\":\"disabled\",\"engineAlarmMethod\":\"sound\",\"engineAlarmState\":\"disabled\",\"fireAlarmMethod\":\"sound\",\"fireAlarmState\":\"disabled\",\"gasAlarmMethod\":\"sound\",\"gasAlarmState\":\"disabled\",\"gpsAlarmMethod\":\"sound\",\"gpsAlarmState\":\"disabled\",\"maydayAlarmMethod\":\"sound\",\"maydayAlarmState\":\"disabled\",\"panpanAlarmMethod\":\"sound\",\"panpanAlarmState\":\"disabled\",\"powerAlarmMethod\":\"sound\",\"powerAlarmState\":\"disabled\",\"silentInterval\":300,\"windAlarmMethod\":\"sound\",\"windAlarmState\":\"disabled\",\"genericAlarmMethod\":\"sound\",\"genericAlarmState\":\"disabled\",\"radarAlarmMethod\":\"sound\",\"radarAlarmState\":\"disabled\",\"mobAlarmMethod\":\"sound\",\"mobAlarmState\":\"disabled\"},\"steering\":{\"rudderAngle\":0.0000000000,\"rudderAngleTarget\":0.0000000000,\"autopilot\":{\"state\":\"off\",\"mode\":\"powersave\",\"targetHeadingNorth\":0.0000000000,\"targetHeadingMagnetic\":0.0000000000,\"alarmHeadingXte\":0.0000000000,\"headingSource\":\"compass\",\"deadZone\":0.0000000000,\"backlash\":0.0000000000,\"gain\":0,\"maxDriveAmps\":0.0000000000,\"maxDriveRate\":0.0000000000,\"portLock\":0.0000000000,\"starboardLock\":0.0000000000}},\"environment\":{\"airPressureChangeRateAlarm\":0.0000000000,\"airPressure\":1024.0000000000,\"waterTemp\":0.0000000000,\"wind\":{\"speedAlarm\":0.0000000000,\"directionChangeAlarm\":0.0000000000,\"directionApparent\":0.0000000000,\"directionTrue\":256.300000000,\"speedApparent\":0.0000000000,\"speedTrue\":7.6800000000}}}}}");
		signalk.merge(data);
		assertNotNull(signalk.atPath(VESSELS, SELF, env_wind));
		signalk.delete(signalk.atPath(VESSELS, SELF, environment), "wind");
		assertNull(signalk.atPath(VESSELS, SELF, env_wind));
	}
	@Test
	public void shouldDeleteBranchFromPath() {
		SignalKModelImpl signalk = new SignalKModelImpl();
		Json data = Json.read("{\"vessels\":{\"self\":{\"navigation\":{\"courseOverGroundTrue\":0.0000000000,\"courseOverGroundMagnetic\":93.0000000000,\"headingMagnetic\":0.0000000000,\"magneticVariation\":0.0000000000,\"headingTrue\":0.0000000000,\"pitch\":0.0000000000,\"rateOfTurn\":0.0000000000,\"roll\":0.0000000000,\"speedOverGround\":0.0000000000,\"speedThroughWater\":0.0000000000,\"state\":\"Not defined (example)\",\"anchor\":{\"alarmRadius\":0.0000000000,\"maxRadius\":0.0000000000,\"position\":{\"latitude\":0.0000000000,\"longitude\":0.0000000000,\"altitude\":0.0000000000}},\"position\":{\"latitude\":-43.2000000000,\"longitude\":0.0000000000,\"altitude\":0.0000000000}},\"alarm\":{\"anchorAlarmMethod\":\"sound\",\"anchorAlarmState\":\"disabled\",\"autopilotAlarmMethod\":\"sound\",\"autopilotAlarmState\":\"disabled\",\"engineAlarmMethod\":\"sound\",\"engineAlarmState\":\"disabled\",\"fireAlarmMethod\":\"sound\",\"fireAlarmState\":\"disabled\",\"gasAlarmMethod\":\"sound\",\"gasAlarmState\":\"disabled\",\"gpsAlarmMethod\":\"sound\",\"gpsAlarmState\":\"disabled\",\"maydayAlarmMethod\":\"sound\",\"maydayAlarmState\":\"disabled\",\"panpanAlarmMethod\":\"sound\",\"panpanAlarmState\":\"disabled\",\"powerAlarmMethod\":\"sound\",\"powerAlarmState\":\"disabled\",\"silentInterval\":300,\"windAlarmMethod\":\"sound\",\"windAlarmState\":\"disabled\",\"genericAlarmMethod\":\"sound\",\"genericAlarmState\":\"disabled\",\"radarAlarmMethod\":\"sound\",\"radarAlarmState\":\"disabled\",\"mobAlarmMethod\":\"sound\",\"mobAlarmState\":\"disabled\"},\"steering\":{\"rudderAngle\":0.0000000000,\"rudderAngleTarget\":0.0000000000,\"autopilot\":{\"state\":\"off\",\"mode\":\"powersave\",\"targetHeadingNorth\":0.0000000000,\"targetHeadingMagnetic\":0.0000000000,\"alarmHeadingXte\":0.0000000000,\"headingSource\":\"compass\",\"deadZone\":0.0000000000,\"backlash\":0.0000000000,\"gain\":0,\"maxDriveAmps\":0.0000000000,\"maxDriveRate\":0.0000000000,\"portLock\":0.0000000000,\"starboardLock\":0.0000000000}},\"environment\":{\"airPressureChangeRateAlarm\":0.0000000000,\"airPressure\":1024.0000000000,\"waterTemp\":0.0000000000,\"wind\":{\"speedAlarm\":0.0000000000,\"directionChangeAlarm\":0.0000000000,\"directionApparent\":0.0000000000,\"directionTrue\":256.300000000,\"speedApparent\":0.0000000000,\"speedTrue\":7.6800000000}}}}}");
		signalk.merge(data);
		assertNotNull(signalk.atPath(VESSELS, SELF, env_wind));
		signalk.delete(VESSELS+"."+SELF+"."+environment, "wind");
		assertNull(signalk.atPath(VESSELS, SELF, env_wind));
	}
	@Test
	public void shouldDeleteUnderscored() {
		SignalKModelImpl signalk = new SignalKModelImpl();
		Json data = Json.read("{\"vessels\":{\"self\":{\"navigation\":{\"courseOverGroundTrue\":0.0000000000,\"courseOverGroundMagnetic\":93.0000000000,\"headingMagnetic\":0.0000000000,\"magneticVariation\":0.0000000000,\"headingTrue\":0.0000000000,\"pitch\":0.0000000000,\"rateOfTurn\":0.0000000000,\"roll\":0.0000000000,\"speedOverGround\":0.0000000000,\"speedThroughWater\":0.0000000000,\"state\":\"Not defined (example)\",\"anchor\":{\"alarmRadius\":0.0000000000,\"maxRadius\":0.0000000000,\"position\":{\"latitude\":0.0000000000,\"longitude\":0.0000000000,\"altitude\":0.0000000000}},\"position\":{\"latitude\":-43.2000000000,\"longitude\":0.0000000000,\"altitude\":0.0000000000}},\"alarm\":{\"anchorAlarmMethod\":\"sound\",\"anchorAlarmState\":\"disabled\",\"autopilotAlarmMethod\":\"sound\",\"autopilotAlarmState\":\"disabled\",\"engineAlarmMethod\":\"sound\",\"engineAlarmState\":\"disabled\",\"fireAlarmMethod\":\"sound\",\"fireAlarmState\":\"disabled\",\"gasAlarmMethod\":\"sound\",\"gasAlarmState\":\"disabled\",\"gpsAlarmMethod\":\"sound\",\"gpsAlarmState\":\"disabled\",\"maydayAlarmMethod\":\"sound\",\"maydayAlarmState\":\"disabled\",\"panpanAlarmMethod\":\"sound\",\"panpanAlarmState\":\"disabled\",\"powerAlarmMethod\":\"sound\",\"powerAlarmState\":\"disabled\",\"silentInterval\":300,\"windAlarmMethod\":\"sound\",\"windAlarmState\":\"disabled\",\"genericAlarmMethod\":\"sound\",\"genericAlarmState\":\"disabled\",\"radarAlarmMethod\":\"sound\",\"radarAlarmState\":\"disabled\",\"mobAlarmMethod\":\"sound\",\"mobAlarmState\":\"disabled\"},\"steering\":{\"rudderAngle\":0.0000000000,\"rudderAngleTarget\":0.0000000000,\"autopilot\":{\"state\":\"off\",\"mode\":\"powersave\",\"targetHeadingNorth\":0.0000000000,\"targetHeadingMagnetic\":0.0000000000,\"alarmHeadingXte\":0.0000000000,\"headingSource\":\"compass\",\"deadZone\":0.0000000000,\"backlash\":0.0000000000,\"gain\":0,\"maxDriveAmps\":0.0000000000,\"maxDriveRate\":0.0000000000,\"portLock\":0.0000000000,\"starboardLock\":0.0000000000}},\"environment\":{\"airPressureChangeRateAlarm\":0.0000000000,\"airPressure\":1024.0000000000,\"waterTemp\":0.0000000000}}}}");
		signalk.merge(data);
		assertNull(signalk.atPath(VESSELS, SELF, "navigation._arduino"));
		Json wind = Json.read("{\"speedAlarm\":0.0000000000,\"directionChangeAlarm\":0.0000000000,\"directionApparent\":0.0000000000,\"directionTrue\":256.300000000,\"speedApparent\":0.0000000000,\"speedTrue\":7.6800000000}");
		signalk.mergeAtPath(VESSELS+"."+ SELF+".navigation","_arduino", wind);
		assertNotNull(signalk.atPath(VESSELS, SELF, "navigation._arduino"));
		Json safeNode = signalk.safe(signalk.duplicate());
		assertNull(signalk.findNode(safeNode, VESSELS+"."+SELF+".navigation._arduino"));
		assertNotNull(signalk.atPath(VESSELS, SELF, "navigation._arduino"));
	}
}