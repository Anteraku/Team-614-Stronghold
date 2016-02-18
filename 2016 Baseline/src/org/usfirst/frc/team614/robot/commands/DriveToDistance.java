package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.Constants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToDistance extends Command {
	
	//double speed = 1.0;
	//double targetDistance;
	double area = 0.0;
    public DriveToDistance(double area) {
        // Use requires() here to declare subsystem dependencies
        // requires(chassis);
    	this.area = area;
    	//this.targetDistance = targetDistance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.drivetrain.arcadeDriveMode(speed, 0.0);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double move = 0;
    	if((area / Constants.targetArea)-1 > Constants.shooterTolerance){
    		if((area / Constants.targetArea) > 1.0){
    			move = ((area / Constants.targetArea)-1)/Constants.motorsBetterNotGoTooFast;
    			Robot.drivetrain.arcadeDriveMode(-move, 0.0);
    		}else{
    			move = ((area / Constants.targetArea)-1)/Constants.motorsBetterNotGoTooFast;
    			Robot.drivetrain.arcadeDriveMode(move, 0.0);
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return Robot.drivetrain.getDistance() <= targetDistance;
    	return (area / Constants.targetArea)-1 <= Constants.shooterTolerance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.arcadeDriveMode(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
