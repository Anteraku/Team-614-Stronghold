package org.usfirst.frc.team614.robot.commands.drivetrain;

import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightForATime extends Command {

	private double time, speed;
	private boolean usePID = false;
	private boolean goForward;
    public DriveStraightForATime(double time, double speed, boolean usePID, boolean goForward) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.time = time;
    	this.speed = speed;
//    	this.usePID = usePID;
    	this.goForward = goForward;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    	Robot.drivetrain.resetAngle();
    	Robot.drivetrain.resetEncoders();
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(goForward){
    		Robot.drivetrain.arcadeDriveMode(-speed, 0.0, usePID);
    	}
    	
    	if(!goForward){
    		Robot.drivetrain.arcadeDriveMode(speed, 0.0, usePID);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
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
