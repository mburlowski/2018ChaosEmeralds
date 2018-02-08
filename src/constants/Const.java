package constants;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class Const {
	//distance sensor values
	public static int dSensL = 0;
	public static int dSensR = 0;
	//motors
	public static int motorBL = 0;
	public static int motorBR = 1;
	public static int motorFL = 2;
	public static int motorFR = 3;
	//arms
	public static int armLIntake = 4;
	public static int armRIntake = 5;
	//lift
	public static int lift = 6;
	//joysticcs
	public static int jstickL = 0;
	public static int jstickR = 0;
	public static int gpad = 0;
	public static int box = 0;
	//joysticc xbox buttons and axes
	public static int xButtonA = 1;
	public static int xButtonB = 2;
	public static int xButtonX = 3;
	public static int xButtonY = 4;
	public static int xButtonLB = 5;
	public static int xButtonRB = 6;
	public static int xButtonSelect = 7;
	public static int xButtonStart = 8;
	public static int xButtonLS = 9;
	public static int xButtonRS = 10;
	public static int xAxisLX = 1;//left stick horizontal
	public static int xAxisLY = 2;//left stick vertical
	public static int xAxisRX = 4;//right stick horizontal
	public static int xAxisRY = 5;//right stick vertical
	//public static int xAxisDX = 6; //apparently the dpad has an axis, but only on X?
	public static int xAxisTrigger = 3;//triggers; 0 to 1 for LT, 0 to -1 for RT
	//joysticc functions
	public static double ledzone = .1;//deadzone of left thing (decimal from 0 to 1)
	public static double redzone = .1;//deadone of right thing
	public static double edge = 1;//outer ring
	//IMU serial port
	public static SerialPort imuPort = new SerialPort(57600,Port.kUSB2);
}
