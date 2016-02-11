package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.RobotMap;
import org.usfirst.frc.team614.robot.commands.JoystickDrive;
import org.usfirst.frc.team614.robot.RobotDrive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    

	private VictorSP frontLeftMotor, frontRightMotor, rearLeftMotor, rearRightMotor, midLeftMotor, midRightMotor; 
	/**private VictorSP leftMotor, rightMotor; */ //for use when PWMS split into 2
	
	private Encoder frontLeftEncoder, frontRightEncoder, rearLeftEncoder, rearRightEncoder, midLeftEncoder, midRightEncoder;
	
	private Solenoid Piston;
	
	private AnalogGyro Gyro;
	
	private RobotDrive drivetrain;											// FRC provided drivetrain class
	
	public Drivetrain(){
	
		//Initializes the motors
		 frontLeftMotor = new VictorSP(RobotMap.drivetrainFrontLeftMotor);
    	 rearLeftMotor = new VictorSP(RobotMap.drivetrainRearLeftMotor);
    	 frontRightMotor = new VictorSP(RobotMap.drivetrainFrontRightMotor);
    	 rearRightMotor = new VictorSP(RobotMap.drivetrainRearRightMotor);
    	 midLeftMotor = new VictorSP(RobotMap.drivetrainMidLeftMotor);
    	 midRightMotor = new VictorSP(RobotMap.drivetrainMidRightMotor);
    	 
    	 /**  FOR USE WHEN PWMS SPLIT INTO 2
 		leftMotor = new VictorSP(RobotMap.drivetrainFrontLeftMotor);
 		rightMotor = new VictorSP(RobotMap.drivetrainFrontRightMotor);
 		*/
    	 //Initializes the solenoid
    	 Piston = new Solenoid(RobotMap.solenoid_A, RobotMap.solenoid_B);
    	 
		//Initializes the Encoders
    	 /**
    	 frontLeftEncoder = new Encoder(RobotMap.frontLeftEncoder_A, RobotMap.frontLeftEncoder_B);
    	 frontRightEncoder = new Encoder(RobotMap.frontRightEncoder_A, RobotMap.frontRightEncoder_B);
    	 rearLeftEncoder = new Encoder(RobotMap.rearLeftEncoder_A, RobotMap.rearLeftEncoder_B);
    	 rearRightEncoder = new Encoder(RobotMap.rearRightEncoder_A, RobotMap.rearRightEncoder_B);
    	 midLeftEncoder = new Encoder(RobotMap.midLeftEncoder_A, RobotMap.midLeftEncoder_B);
		 midRightEncoder = new Encoder(RobotMap.midRightEncoder_A, RobotMap.midRightEncoder_B);
		*/
		//Initializes gryo
		 Gyro = new AnalogGyro(RobotMap.gyro_ID);
		 Gyro.reset();
		
		 //Initializes drivetrain class
		 drivetrain = new RobotDrive(frontLeftMotor, frontRightMotor, rearLeftMotor, rearRightMotor, midLeftMotor, midRightMotor);
		 //drivetrain = new RobotDrive(leftMotor, rightMotor);		// Initializes drivetrain class; for use when PWMS split into 2
		
	
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new JoystickDrive());
    
    }
    

    /*
     * Motor Methods
     */
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
    
    /*
     * Solenoid Methods
     */
    public void extend(){
    Piston.set(true);	
    }
    
    public void retract(){
    Piston.set(false);	
    }
    
    
    /*
     * Encoder Methods
     */
    public double getFrontLeftEncoderRPM(){
    	return frontLeftEncoder.getRate();
    }
    
    public double getFrontRightEncoderRPM(){
    	return frontRightEncoder.getRate();
    }
    
    public double getRearLeftEncoderRPM(){
    	return rearLeftEncoder.getRate();
    }
    
    public double getRearRightEncoderRPM(){
    	return rearRightEncoder.getRate();
    }
    
    public double getMidLeftEncoderRPM(){
    	return midLeftEncoder.getRate();
    }
    
    public double getMidRightEncoderRPM(){
    	return midRightEncoder.getRate();
    }
    
    public void resetEncoders(){
    	frontLeftEncoder.reset();
    	frontRightEncoder.reset();
    	rearLeftEncoder.reset();
    	rearRightEncoder.reset();
    	midLeftEncoder.reset();
    	midRightEncoder.reset();
    }
    
    /*
     * Gyro Methods
     */
    public double getAngle(){
    	return Gyro.getAngle();
    }
    
    public void resetGyro(){
    	Gyro.reset();
    }
    
    
    
   
}

