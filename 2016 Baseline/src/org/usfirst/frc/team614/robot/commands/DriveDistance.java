package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveDistance extends Command {

	// Holds the Encoder Value to kill at(+ = Forwards/ - = Backwards)
	private double distance;
	private boolean driveForward;

	// Timeout/Ending Variables
	private boolean isDone;
	private double timeout;

	public DriveDistance(double distance, boolean forward, double time) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);

		this.distance = distance;
		driveForward = forward;
		timeout = time;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.stopDrive();
		Robot.drivetrain.resetDistance();
		setTimeout(timeout);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double motorSpeed = RobotMap.AUTO_MOTOR_MAGNITUDE;// define this
		if (!driveForward) {
			motorSpeed *= -1.0;
		}
		Robot.drivetrain.arcadeDriveMode(motorSpeed, 0);
		if (Math.abs(Robot.drivetrain.getDistanceTravelled()) > distance) {
			isDone = true;
		}
	}

	protected boolean isFinished() {
		return isDone || isTimedOut();
	}

	protected void end() {
		Robot.drivetrain.stopDrive();
	}

	protected void interrupted() {
		Robot.drivetrain.stopDrive();
	}
}
