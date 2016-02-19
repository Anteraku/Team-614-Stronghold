package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Constants;
import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AimAtTarget extends Command {
	
    public static final double CENTER_X = Constants.xPixels / 2;
    private int index;//index of best thingy
    private boolean done;
    
	public AimAtTarget() {
		requires(Robot.visionProcessor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	index = Robot.visionProcessor.getBest();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double x = Robot.visionProcessor.getCenterX(index);
    	if(Math.abs(x-CENTER_X) < 30) {//pixel threshhold from either side
    		done = true;
    	}else {
    		if(x > CENTER_X) {
    			//tilt left
    		} 
    		if(x < CENTER_X) {
    			//tilt right
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
