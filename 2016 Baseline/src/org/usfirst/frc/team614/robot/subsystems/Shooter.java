package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.Constants;
import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private VictorSP leftMotor, rightMotor;
	
	public Shooter() {
		//Initializes the motors
		leftMotor = new VictorSP(RobotMap.shooterLeftMotor);
		rightMotor = new VictorSP(RobotMap.shooterRightMotor);
	
		//Initializes the encoders
		
	}
	
	public void shoot(){
		leftMotor.set(Constants.MOTOR_FORWARD);
		rightMotor.set(-Constants.MOTOR_FORWARD);
	}
	
	public void bringIn(){
		leftMotor.set(Constants.MOTOR_REVERSE);
		rightMotor.set(-Constants.MOTOR_REVERSE);
	
	}
	public void stop(){
		leftMotor.set(0);
		rightMotor.set(0);
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

