package constants;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class Const {
	//distance sensors -- finalize these
	public static int dSensL = 0;
	public static int dSensR = 0;
	//Talons
	public static int motorBL = 0;
	public static int motorBR = 1;
	public static int motorFL = 2;
	public static int motorFR = 3;
	public static int armLIntake = 4;
	public static int armRIntake = 5;
	public static int lift = 6;
	//pneumatic pistons -- finalize these
	public static int armLFwd = 0;
	public static int armLRev = 0;
	public static int armRFwd = 0;
	public static int armRRev = 0;
	//joysticcs -- finalize these
	public static int jstickL = 0;//left flight stick
	public static int jstickR = 0;//right flight stick
	public static int xbox = 0;//xbox controller
	public static int box = 0;//autonomous control box
	//joysticc POVs
	public static int povUp = 0;
	public static int povUpRight = 45;
	public static int povRight = 90;
	public static int povDownRight = 135;
	public static int povDown = 180;
	public static int povDownLeft = 225;
	public static int povLeft = 270;
	public static int povUpLeft = 315;
	//joysticc limits
	public static double ledzone = .1;//deadzone of left thing (decimal from 0 to 1)
	public static double redzone = .1;//deadone of right thing
	public static double edge = 1;//outer ring
	//IMU serial port
	public static SerialPort imuPort = new SerialPort(57600,Port.kUSB2);
}
