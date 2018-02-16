package subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import constants.Const;
public class SecondLift {
	TalonSRX liftMotor=new TalonSRX(Const.lift);

public void lift(double speed) 
{
	//if(liftMotor.getSensorCollection().isFwdLimitSwitchClosed() && speed < 0) speed = 0;
	//if(liftMotor.getSensorCollection().isRevLimitSwitchClosed() && speed > 0) speed = 0;
liftMotor.set(ControlMode.PercentOutput,speed);	
}
	}