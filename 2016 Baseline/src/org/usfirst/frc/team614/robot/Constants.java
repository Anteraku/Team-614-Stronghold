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
	
	//Shooter
	
	public static final double MOTOR_FORWARD = .85;
	public static final double MOTOR_REVERSE = .5;
	
	//Vision Processor
	public static final double targetArea = 420;
	public static final double xPixels = 720;
	public static final double shooterTolerance = 0.03;
	//public static final double motorsBetterNotGoTooFast = 2;
	public static final double bestDistance = 16;
	public static final double feetToEncoderRatio = .3;
	public static final int shortEncoderDistance = 200;
	
	
	
	
	
}
