package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.Constants;
import org.usfirst.frc.team614.robot.RobotMap;
import org.usfirst.frc.team614.robot.commands.JoystickDrive;
import org.usfirst.frc.team614.robot.commands.shooter.LiftDrive;
import org.usfirst.frc.team614.robot.commands.shooter.ShootSequence;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {
    
	private VictorSP leftMotor, rightMotor, angleMotor;
	private Servo servo;
	private Encoder leftEncoder, rightEncoder, angleEncoder;
	private double distancePerPulse;
	
	public Shooter() {
		
		//Initializes the motors
		leftMotor = new VictorSP(RobotMap.shooterLeftMotor);
		rightMotor = new VictorSP(RobotMap.shooterRightMotor);
		angleMotor = new VictorSP(RobotMap.shooterAngleMotor);
	
		//Initializes the encoders
		leftEncoder = new Encoder(RobotMap.leftShooterEncoder_A, RobotMap.leftShooterEncoder_B);
		rightEncoder = new Encoder(RobotMap.rightShooterEncoder_A, RobotMap.rightShooterEncoder_B);
		angleEncoder = new Encoder(RobotMap.angleShooterEncoder_A, RobotMap.angleShooterEncoder_B);
		
		resetEncoders();
		
		distancePerPulse = 10; //change to whatever the rate is when the encoder comes in
		leftEncoder.setDistancePerPulse(distancePerPulse);
		rightEncoder.setDistancePerPulse(distancePerPulse);
		angleEncoder.setDistancePerPulse(distancePerPulse);
		
		
		//Initialize the servos
		servo = new Servo(RobotMap.servo_ID);
	}
	
	  public void initDefaultCommand() {
	        // Set the default command for a subsystem here.
	    	setDefaultCommand(new LiftDrive());
	    	//setDefaultCommand(new ShootSequence());
	    
	    }
	    
	/*
	 * Motor Methods
	 */
	
	/**
	 * Spins up the shooter flywheel to shoot out
	 */
	public void revUpForward(){
		leftMotor.set(Constants.MOTOR_FORWARD);
		rightMotor.set(-Constants.MOTOR_FORWARD);
		
	}
	/**
	 * Spins up the shooter flywheel to bring in the ball
	 */
	public void revUpReverse(){
		leftMotor.set(Constants.MOTOR_REVERSE);
		rightMotor.set(-Constants.MOTOR_REVERSE);
	}
	
	/**
	 * Moves the shooter up to shoot higher
	 */
	
	public void stopFlywheel(){
		leftMotor.set(0);
		rightMotor.set(0);
	}
	
	
	/*
	 * Servo Methods
	 */
	
	/**
	 * Moves the servo to hit the ball into the flywheel
	 */
	public void flickBall(){
		servo.set(Constants.SERVO_ANGLE);
	}
	
	public void resetServo(){
		servo.set(0);
	}
	
	
	/*
	 * AngleMotor Methods
	 */
	 
	 public void setMotorSpeed(double motorSpeed){
		 angleMotor.set(motorSpeed* Constants.ANGLE_REDUCTION_SPEED);
	 }
	 
	 /*
	 * Moves the shooter up to shoot higher
	 */
	public void angleUp(){
		angleMotor.set(Constants.MOTOR_UP);
	}
		
	/*
	 * Moves the shooter down to shoot lower
	 */	
	public void angleDown(){
		angleMotor.set(Constants.MOTOR_DOWN);
	}
	
	public void stopAngle(){
		angleMotor.set(0);
	}
	
	/*
	 * Encoder Methods
	 */
	
	
	
	public double getLeftEncoderRPM(){
		return leftEncoder.getRate();
	}
	
	public double getRightEncoderRPM(){
		return rightEncoder.getRate();
	}
	
	public double getAngleEncoderRPM(){
		return angleEncoder.getRate();
	}
	
	public void resetFlywheelEncoders(){
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public void resetAngleEncoder(){
		angleEncoder.reset();
	}
	
	public void resetEncoders(){
		leftEncoder.reset();
		rightEncoder.reset();
		angleEncoder.reset();
	}
	
	public void sendToDashboard(){
		SmartDashboard.putNumber("Left Flywheel RPM: ", getLeftEncoderRPM());
		SmartDashboard.putNumber("Right Flywheel RPM: ", getRightEncoderRPM());
		SmartDashboard.putNumber("Lift RPM: ", getAngleEncoderRPM());
	}

 
}

