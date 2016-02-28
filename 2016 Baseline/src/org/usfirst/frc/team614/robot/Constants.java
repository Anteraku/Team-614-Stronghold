package org.usfirst.frc.team614.robot;

public final class Constants {

	
	/*
	 * Motor Controllers
	 */
public static final double ROTATE_MOTOR_MAX_SPEED = 0.7;
public static final double DRIVE_MOTOR_MAX_SPEED = 0.7;
public static final double MOTOR_TURN_SPEED = 0.3;

	/*
	 * Drivetrain
	 */
	public static final double JOYSTICK_DEADBAND = .25;
	public static final boolean DRIVE_USE_SQUARED_INPUT = false;
	public static final double DRIVETRAIN_ENCODER_PULSES_PER_REV = 240.0;
	public static final double DRIVETRAIN_WHEEL_DIAMETER = 8;
	
	
	/*
	 * Shooter
	 */
	public static final double MOTOR_FORWARD = .85;
	public static final double MOTOR_REVERSE = .5;
	public static final double MOTOR_UP = .1;
	public static final double MOTOR_DOWN = .1;
	public static final double SERVO_ANGLE = 135;
	public static final double TARGET_RPM = 60 ; //to be changed when encoders come in
	public static final double TARGET_RATE = 760000; //to be changed when the max rate of the flywheels are
	public static final double ANGLE_REDUCTION_SPEED = .5;
	public static final double AUTO_ANGLE_SPEED = .2;
	public static final double FLYWHEEL_SHOOT_SPEED = 1;
	public static final double FLYWHEEL_RETRIEVE_SPEED = -0.6;
	public static final double SHOOTER_WHEEL_DIAMETER = 5;
	public static final double TED_REDUCTION_SPEED = .5;

	/*
	 * Statistics / SmartDashboard
	 */
	
	public static final double SEND_STATS_INTERVAL = 0.25;
	
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
