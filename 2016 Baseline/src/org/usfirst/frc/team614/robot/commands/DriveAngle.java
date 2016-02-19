/*package org.usfirst.frc.team614.robot.commands;
import java.lang.Math;
import org.usfirst.frc.team614.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team614.robot.Constants;

*//**
 *
 *//*
public class DriveAngle extends Command {

	//double speed = 1.0;
	double center_x = 0.0;
	double width = 0.0;
	
    public DriveAngle(double center_x, double width) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.width = width;
    	this.center_x = center_x;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//getAngle(center_x, width);
    	double angle1 = center_x / ((Constants.xPixels / 2) / (width / 2));
    	Robot.drivetrain.resetEncoders();
    	while(Robot.drivetrain.getEncoderDistance(LFEncoder) < Constants.shortEncoderDistance){
    		Robot.drivetrain.arcadeDriveMode(0.45, 90 - angle1);
    	}
    	double angle2 = center_x / ((Constants.xPixels / 2) / (width / 2));
    	if(angle1 > angle2){
    		double driveDistance = Constants.bestDistance * Math.sin(angle2);
    	}
    	//double driveDistance = Constants.bestDistance * Math.sin(angle);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
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
*/