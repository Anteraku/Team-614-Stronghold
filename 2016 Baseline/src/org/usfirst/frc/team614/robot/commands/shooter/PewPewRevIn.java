package org.usfirst.frc.team614.robot.commands.shooter;

import org.usfirst.frc.team614.robot.Constants;
import org.usfirst.frc.team614.robot.OI;
import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PewPewRevIn extends Command {
	boolean inTeleop;
	boolean hadTED = false;
	Timer time;
	
    public PewPewRevIn(boolean inTeleop) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    	this.inTeleop = inTeleop;    
    	hadTED = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(!inTeleop){
    		setTimeout(5);
    	} else {
    		if(!hadTED){
		    	Robot.shooter.TEDOut(.7);
		    	Timer.delay(.5);
		    	Robot.shooter.controlTED(0);
		    	hadTED = true;
    		}
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.shootMode(Constants.MOTOR_REVERSE, false);	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(inTeleop){
    		if(!(OI.driverGamepad.getButton(OI.PEW_PEW_REV_IN))) {
    			hadTED = false;
    		}
    		
    		return  !(OI.driverGamepad.getButton(OI.PEW_PEW_REV_IN));
    	}
    	else {
    		return isTimedOut();
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
//    	Robot.shooter.stopFlywheel();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    
    }
}
