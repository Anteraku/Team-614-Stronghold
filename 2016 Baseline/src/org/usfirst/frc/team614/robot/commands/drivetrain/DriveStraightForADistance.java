package org.usfirst.frc.team614.robot.commands.drivetrain;

import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class DriveStraightForADistance extends Command {

	
	private double speed, distance; 
	private boolean usePID;
	
    public DriveStraightForADistance(double speed, double distance, boolean usePID) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	
    	this.speed = speed;
    	this.distance = distance;
    	this.usePID = usePID;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.arcadeDriveMode(speed, 0.0, usePID);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.drivetrain.getEncoderTrueDistance(Robot.drivetrain.LEncoder) >= distance || Robot.drivetrain.getEncoderTrueDistance(Robot.drivetrain.REncoder) >= distance;    

    }

    // Called once after isFinished returns true
    protected void end() {
     Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
     Robot.drivetrain.stop();
    }
}
