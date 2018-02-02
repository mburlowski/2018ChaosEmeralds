package constants;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class Const {
	//distance sensor values
	public static int dSensL = 0;
	public static int dSensR = 0;
	//motors
	public static int motorBL = 0;
	public static int motorBR = 0;
	public static int motorFL = 0;
	public static int motorFR = 0;
	//joysticcs
	public static int jstickL = 0;
	public static int jstickR = 0;
	public static int gpad = 0;
	public static int box = 0;
	//joysticc functions
	public static double edzone = .1;//deadzone (decimal from 0 to 1)
	//IMU serial port
	public static SerialPort imuPort = new SerialPort(57600,Port.kUSB2);
}
