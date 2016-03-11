package org.usfirst.frc.team614.robot.commands.drivetrain;

import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class DriveStraightForADistance extends Command {

	
	private double distance, speed; 
	private boolean usePID;
	private boolean goForward;
	
    public DriveStraightForADistance(double distance, double speed, boolean usePID, boolean goForward) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	
    	this.distance = distance; 
    	this.speed = speed;
    	this.usePID = usePID;
    	this.goForward = goForward;
    	Robot.drivetrain.resetEncoders();
    	Robot.drivetrain.resetAngle();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    	Robot.drivetrain.resetAngle();
    	
    	
    	if(goForward){
    		Robot.drivetrain.arcadeDriveMode(speed, 0.0, usePID);
    	}
    	if(!goForward){
    		Robot.drivetrain.arcadeDriveMode(-speed, 0.0, usePID);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//return Robot.drivetrain.getEncoderTrueDistance(Robot.drivetrain.LEncoder) >= distance || Robot.drivetrain.getEncoderTrueDistance(Robot.drivetrain.REncoder) >= distance;    
    //return Robot.drivetrain.getEncoderDistance(Robot.drivetrain.LEncoder) >= distance/Robot.drivetrain.distancePerPulse || Robot.drivetrain.getEncoderDistance(Robot.drivetrain.REncoder) >distance/Robot.drivetrain.distancePerPulse;
    	if(Robot.drivetrain.LEncoder > Robot.drivetrain.REncoder){
    	
    		if(goForward) {
    			return (Math.abs(Robot.drivetrain.getEncoderDistance(Robot.drivetrain.LEncoder)) >= distance);
    		}
    	}
    	else if(Robot.drivetrain.REncoder > Robot.drivetrain.LEncoder){
        	
        	if(goForward) {
            	return (Math.abs(Robot.drivetrain.getEncoderDistance(Robot.drivetrain.REncoder)) >= distance);
        	}
    	}
    	return false;
    
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
