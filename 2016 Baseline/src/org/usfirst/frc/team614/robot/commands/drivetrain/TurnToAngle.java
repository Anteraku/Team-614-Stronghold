package org.usfirst.frc.team614.robot.commands.drivetrain;

import org.usfirst.frc.team614.robot.Constants;
import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {

	private double targetAngle;
	private double rotationSpeed;

	
    public TurnToAngle(double targetAngle, double rotationSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	
    	this.targetAngle = targetAngle;
    	this.rotationSpeed = rotationSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.arcadeDriveMode(0, Constants.MOTOR_TURN_SPEED, true);
    	if (targetAngle > 0) {
    		Robot.drivetrain.arcadeDriveMode(0, Constants.MOTOR_TURN_SPEED, true);
    	} else if(targetAngle <0){
    		Robot.drivetrain.arcadeDriveMode(0, Constants.MOTOR_TURN_SPEED, true);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		if(Robot.drivetrain.getAngle() >= targetAngle && targetAngle > 0)
		{
			return true;
		} else if(Robot.drivetrain.getAngle() <= targetAngle && targetAngle < 0){
			return true;
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
