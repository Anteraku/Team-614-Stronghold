package org.usfirst.frc.team614.robot.commands.drivetrain;

import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {

	private double targetAngle;
	private double rotationSpeed;
	private int position;
	
    public TurnToAngle(double targetAngle, double rotationSpeed, int position) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	Robot.drivetrain.resetAngle();
    	
    	this.rotationSpeed = rotationSpeed;
    	this.position = position;
    	
    	
    	switch(position){
    	
    		case 1: //if = 0
    			targetAngle = 30;
    		case 2: //if = 1
    			targetAngle = 20;
    		case 3:
    			targetAngle = 0;
    		case 4:
    			targetAngle = -20;
    		case 5:
    			targetAngle = -30;
    		default:{
    			this.targetAngle = targetAngle;
    			}
    		}
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    			if (targetAngle > 0) {
    				Robot.drivetrain.arcadeDriveMode(0, rotationSpeed, true);
    			} else if(targetAngle <0){
    				Robot.drivetrain.arcadeDriveMode(0, -rotationSpeed, true);
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
