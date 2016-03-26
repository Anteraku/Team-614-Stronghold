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
Timer time;
    public PewPewRevIn(boolean inTeleop) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
this.inTeleop = inTeleop;    
}

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(!inTeleop){
    	setTimeout(5);
    	}
//    	Robot.shooter.TEDIn(.7);
//    	Timer.delay(.5);
//    	Robot.shooter.controlTED(0);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.shootMode(Constants.MOTOR_REVERSE, false);	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(inTeleop){
    		return  !(OI.driverGamepad.getButton(OI.PEW_PEW_REV_IN));
    	}
//    	 return !(OI.driverGamepad.getButton(OI.PEW_PEW_MEASURE_RETRIEVE));
    	else return isTimedOut();
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
