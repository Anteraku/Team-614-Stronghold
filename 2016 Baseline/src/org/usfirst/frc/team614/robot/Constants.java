package org.usfirst.frc.team614.robot;

public final class Constants {

	
	/*
	 * Motor Controllers
	 */
public static final double ROTATE_MOTOR_MAX_SPEED = 0.7;
public static final double DRIVE_MOTOR_MAX_SPEED = 0.7;

	/*
	 * Drivetrain
	 */
	public static final double JOYSTICK_DEADBAND = .25;
	public static final boolean DRIVE_USE_SQUARED_INPUT = false;
	public static final double DRIVETRAIN_ENCODER_PULSES_PER_REV = 256.0;
	
	
	/*
	 * Shooter
	 */
	public static final double MOTOR_FORWARD = .85;
	public static final double MOTOR_REVERSE = .5;
	public static final double SERVO_ANGLE = 135;
	public static final double TARGET_RPM = 60 ; //to be changed when encoders come in
	
	
	
}
