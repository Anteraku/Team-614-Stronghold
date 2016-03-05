package org.usfirst.frc.team614.robot.commands.autonomous;

import org.usfirst.frc.team614.robot.commands.shooter.PewPewMeasureOut;
import org.usfirst.frc.team614.robot.commands.shooter.PewPewShoot;
import org.usfirst.frc.team614.robot.commands.shooter.TEDIn;
import org.usfirst.frc.team614.robot.commands.shooter.TEDOut;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ShootFromSpyBox extends CommandGroup {
    
    public  ShootFromSpyBox() {
    	
    	addSequential(new TEDIn(.5));
    	addSequential(new WaitCommand(1));
    	
    	//Rev up and shoot at goal
    	addParallel(new PewPewMeasureOut());
    	
    	addSequential(new WaitCommand(3));
    	addSequential(new PewPewShoot(2));
    	
    	
    	
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
