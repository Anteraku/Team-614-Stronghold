package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Constants;
import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {

	private double targetAngle = 0;

	
    public TurnToAngle(double targetAngle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.targetAngle = targetAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/**
    	if (targetAngle >= 0) {
    		Robot.drivetrain.arcadeDriveMode(Constants.MOTOR_TURN_SPEED, 0);
    	} else {
    		Robot.drivetrain.arcadeDriveMode(0, Constants.MOTOR_TURN_SPEED);
    	}
    	*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return false;
       
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
