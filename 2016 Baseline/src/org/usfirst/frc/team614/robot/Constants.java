package org.usfirst.frc.team614.robot;

public final class Constants {


	/*
	 * Field Dimensions
	 */
	public static final double FIELD_LENGTH = 27.0416;
	public static final double FIELD_WIDTH = 26.583;
	public static final double DEFENSE_WIDTH = 3.5;
	public static final double DEFENSE_LENGTH = 4;
	public static final double DISTANCE_TO_SHOOT_ZONE = 16.041667;
	public static final double DISTANCE_TO_FIRST_DEFENSE = 0;
	
	
	/*
	 * Robot Dimensions
	 */
	public static final double ROBOT_DIAMETER_IN = 28.5;
	
	/*
	 * Motor Controllers
	 */
	public static final double ROTATE_MOTOR_MAX_SPEED = 0.7;
	public static final double DRIVE_MOTOR_MAX_SPEED = 0.7;
	public static final double MOTOR_TURN_SPEED = 0.7;
	public static final double ENCODER_TO_DEGREES = .865;

	/*
	 * Drivetrain
	 */
	public static final double JOYSTICK_DEADBAND = .25;
	public static final boolean DRIVE_USE_SQUARED_INPUT = false;
	public static final double DRIVETRAIN_ENCODER_PULSES_PER_REV = 375;//240.0; 
	public static final double DRIVETRAIN_WHEEL_DIAMETER = 8;//24/(Math.PI);
	public static final double DRIVETRAIN_ENCODER_PERCENT_ERROR = .974;
	
	/*
	 * Shooter
	 */
	public static final double MOTOR_FORWARD = 1.0;
	public static final double MOTOR_REVERSE = 1.0;
	public static final double MOTOR_UP = .1; //not used
	public static final double MOTOR_DOWN = .1; //not used
	
	public static final double TARGET_RPM = 60 ; //to be changed when encoders come in
	public static final double TARGET_RATE = 760000; //to be changed when the max rate of the flywheels are
	public static final double ANGLE_REDUCTION_SPEED_DOWN = 1;//.5;
	public static final double ANGLE_REDUCTION_SPEED_UP = 1;//.85;
	public static final double FLYWHEEL_SHOOT_SPEED = 1;
	public static final double FLYWHEEL_RETRIEVE_SPEED = -0.6;
	//public static final double SHOOTER_WHEEL_DIAMETER = 5;
	public static final double TED_REDUCTION_SPEED = 1;

	/*
	 * Statistics / SmartDashboard
	 */
	
	public static final double SEND_STATS_INTERVAL = 0.25;
	public static final boolean DEBUG = true;
	
	/*
	 * PID Tuning Parameters
	 */
	
	public static final double Kp = 0.0;
	public static final double Ki = 0.0;
	public static final double Kd = 0.0;
	
	public static final double KpDrive =0.1;
	public static final double KiDrive = 0.01;
	public static final double KdDrive = .005;
	
	public static final double pid_tolerance = 1;
	
			
	
}
