package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.RobotMap;
import org.usfirst.frc.team614.robot.commands.JoystickDrive;
import org.usfirst.frc.team614.robot.RobotDrive;

import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private VictorSP frontLeftMotor, frontRightMotor, rearLeftMotor, rearRightMotor, midLeftMotor, midRightMotor; 
	/**private VictorSP leftMotor, rightMotor; */
	
	private RobotDrive drivetrain;											// FRC provided drivetrain class
	
	private Solenoid Piston;
	
	public Drivetrain(){
	
		
		 frontLeftMotor = new VictorSP(RobotMap.drivetrainFrontLeftMotor);
    	 rearLeftMotor = new VictorSP(RobotMap.drivetrainRearLeftMotor);
    	 frontRightMotor = new VictorSP(RobotMap.drivetrainFrontRightMotor);
    	 rearRightMotor = new VictorSP(RobotMap.drivetrainRearRightMotor);
    	 midLeftMotor = new VictorSP(RobotMap.drivetrainMidLeftMotor);
    	 midRightMotor = new VictorSP(RobotMap.drivetrainMidRightMotor);
    	 
    	 Piston = new Solenoid(RobotMap.solenoid_A, RobotMap.solenoid_B);
		
		/**
		leftMotor = new VictorSP(RobotMap.drivetrainFrontLeftMotor);
		rightMotor = new VictorSP(RobotMap.drivetrainFrontRightMotor);
		*/
		/**Encoder Stuff
		 * 
		 */
		
		
		drivetrain = new RobotDrive(frontLeftMotor, frontRightMotor, rearLeftMotor, rearRightMotor, midLeftMotor, midRightMotor);
		//drivetrain = new RobotDrive(leftMotor, rightMotor);		// Initializes drivetrain class
		
	
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new JoystickDrive());
    }
    
    public void extend(){
    Piston.set(true);	
    }
    
    public void retract(){
    Piston.set(false);	
    }
    
    public void arcadeDriveMode(double leftValue, double rightValue){
    /**	
    	// Simplified IF statement. If leftValue is in the deadband range(-JoystickDeadband, JoystickDeadband), it returns 0
    	leftValue = (leftValue < RobotMap.JOYSTICK_DEADBAND && leftValue > -RobotMap.JOYSTICK_DEADBAND ? 0 : leftValue);
    	
    	// Simplified IF statement. If rightValue is in the deadband range(-JoystickDeadband, JoystickDeadband), it returns 0
    	rightValue = (rightValue < RobotMap.JOYSTICK_DEADBAND && rightValue > -RobotMap.JOYSTICK_DEADBAND ? 0 : rightValue);
    	
    	//System.out.println("Tank Drive: " + leftValue + ", " + rightValue);
    */
    	drivetrain.arcadeDrive(leftValue, rightValue);
    }
    
    
    
    
    
   
}

