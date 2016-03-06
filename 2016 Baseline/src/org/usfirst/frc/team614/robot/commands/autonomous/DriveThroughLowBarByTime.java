package org.usfirst.frc.team614.robot.commands.autonomous;

import org.usfirst.frc.team614.robot.Constants;
import org.usfirst.frc.team614.robot.commands.drivetrain.DriveStraightForADistance;
import org.usfirst.frc.team614.robot.commands.drivetrain.DriveStraightForATime;
import org.usfirst.frc.team614.robot.commands.drivetrain.TurnToAngle;
import org.usfirst.frc.team614.robot.commands.shooter.LowerLift;
import org.usfirst.frc.team614.robot.commands.shooter.PewPewMeasureOut;
import org.usfirst.frc.team614.robot.commands.shooter.PewPewShoot;
import org.usfirst.frc.team614.robot.commands.shooter.RaiseLift;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DriveThroughLowBarByTime extends CommandGroup {
	
	private static final double driveStraightSpeed = 1;
	private static final double driveStraightTime = 5;
	
	private static final double turnSpeed = .5;
	private static final double shootAngle = 30;
    
    public  DriveThroughLowBarByTime(/*double firstTurn, double alignTime, double secondTurn, double crossTime, double shootAngle*/) {
    	
    	

    	//NAM AND KYLE: doing the one-size-fits-all way ends up in a robot drive- output not updated enough error.
    	//just change the values within here and the other drivethoughlowbar command instead of doing it in autonomous.
    	
    	
    	addSequential(new LowerLift());
    	//First turn
    	//Turn left to become aligned with the low bar
    	addSequential(new TurnToAngle(-90, turnSpeed));
    	addSequential(new WaitCommand(1));
    	
    	//AlignTime
    	//Drive towards the low bar to become aligned with it
    	addSequential(new DriveStraightForATime(3, driveStraightSpeed, false, true));
    	addSequential(new WaitCommand(1));
    	addSequential(new DriveStraightForATime(1, .5, true, false));
    	addSequential(new WaitCommand(1));
    	
    	//SecondTurn
    	//Turn right to face the low bar
    	addSequential(new TurnToAngle(90, turnSpeed));
    	addSequential(new WaitCommand(1));
    	
    	//CrossTime
    	//Drive through the low bar
    	addSequential(new DriveStraightForATime(driveStraightTime, driveStraightSpeed, false, true));
    	addSequential(new WaitCommand(1));
    	
    	
    	addSequential(new RaiseLift());
    	
    	//Face the goal
    	addSequential(new TurnToAngle(180+shootAngle, turnSpeed));
    	
    	//Rev up and shoot at goal
    	addParallel(new PewPewMeasureOut());
    	addSequential(new PewPewShoot(3));
    	
//    	addSequential(new LowerLift());
//    	
//    	//Turn left to become aligned with the low bar
//    	addSequential(new TurnToAngle(firstTurn, turnSpeed));
//    	addSequential(new WaitCommand(1));
//    	
//    	//Drive towards the low bar to become aligned with it
//    	addSequential(new DriveStraightForADistance(alignTime, driveStraightSpeed, true, true)); //Constants.FIELD_WIDTH/2
//    	addSequential(new WaitCommand(1));
//    	
//    	//Turn right to face the low bar
//    	addSequential(new TurnToAngle(90, turnSpeed));
//    	addSequential(new WaitCommand(1));
//    	
//    	//Drive through the low bar
//    	addSequential(new DriveStraightForADistance(crossTime, driveStraightSpeed, true, true));
//    	addSequential(new WaitCommand(1));
//    	
//    	addParallel(new RaiseLift());
//    	
//    	//Face the goal
//    	addSequential(new TurnToAngle(180+shootAngle, turnSpeed)); //-180 since the shooter is on back of the robot
//    	
//    	addParallel(new PewPewMeasureOut());
//    	addSequential(new PewPewShoot(3));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
