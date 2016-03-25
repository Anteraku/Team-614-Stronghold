package org.usfirst.frc.team614.robot.commands.shooter;

import org.usfirst.frc.team614.robot.Constants;
import org.usfirst.frc.team614.robot.OI;
import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PewPewRevOut extends Command {
boolean inTeleop;
    public PewPewRevOut(boolean inTeleop) {
        // Use requires() here to declare subsystem dependencies
      requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(!inTeleop){
    	setTimeout(6);
    	}
//    	Robot.shooter.revUpForward();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.shootMode(-Constants.MOTOR_REVERSE, Robot.shooter.getUsePID());    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(inTeleop){
    		return  !(OI.operatorGamepad.getButton(OI.PEW_PEW_REV_OUT)); //should be operatorGamepad. don't change rn because it works
    	}
        //return !(OI.driverGamepad.getButton(OI.PEW_PEW_MEASURE_OUT));
    	else return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.stopFlywheel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
  
    }
}
