package org.usfirst.frc.team614.robot.commands.autonomous;

import org.usfirst.frc.team614.robot.AutoConstants;
import org.usfirst.frc.team614.robot.commands.drivetrain.DriveStraightForADistance;
import org.usfirst.frc.team614.robot.commands.drivetrain.DriveStraightForATime;
import org.usfirst.frc.team614.robot.commands.drivetrain.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GoOverRockwall extends CommandGroup {
    
    public  GoOverRockwall() {
    	
//    	addSequential(new DriveStraightForADistance(AutoConstants.toDefense, 0.7, true, true));
//    	addSequential(new DriveStraightForATime(3, 0.7, true , true));
//    	addSequential(new DriveStraightForADistance(AutoConstants.toShot, 0.7, true, true));
    	 addSequential(new DriveStraightForATime(8, 0.8, true, true));
    	 
//    	addSequential(new TurnToAngle(180.0, 0.4, 0)); //turn so that shooter is facing the tower
    	
    	
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
