package org.usfirst.frc.team614.robot.commands.autonomous;

import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.commands.shooter.RaiseLift;
import org.usfirst.frc.team614.robot.commands.visionProcessor.LineUpShot;
import org.usfirst.frc.team614.robot.commands.autonomous.DropItLikeItsHot;
import org.usfirst.frc.team614.robot.commands.drivetrain.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootThroughLowBar extends CommandGroup {
    
	private final double shotAngle = 30;
    public  ShootThroughLowBar() {
    	
    	addSequential(new DropItLikeItsHot()); //Drive through lowbar
    	
   
    	addSequential(new TurnToAngle(shotAngle, 0.7, 1)); //turn to goal
    	
    	addSequential(new LineUpShot());
    	
    	addSequential(new Shoot());//shoot
    	
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
