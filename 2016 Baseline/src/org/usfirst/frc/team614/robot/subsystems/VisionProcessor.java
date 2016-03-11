package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.commands.visionProcessor.ProcessData;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables2.type.NumberArray;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
*
*/
public class VisionProcessor extends Subsystem {
  
	private NetworkTable roboRealmInfo;
	//private NumberArray goalBoundaries;
	//private NumberArray containerCrosshair;
	private boolean hasGoal;
	public boolean hasContainer;
	private double blobCount;
	
	private final double imageWidth = 320;
	private double targetX;
	
	private double thresholdX = 20.0;
	private double thresholdY = 0.1;
	

	private double[] goalX = new double[2];
	private double goalWidth;
	
	public VisionProcessor() {
		super("Vision Processor");
		roboRealmInfo = NetworkTable.getTable("SmartDashboard");
		//goalBoundaries = new NumberArray();
		//containerCrosshair = new NumberArray();
		targetX = imageWidth / 1.6;
	}
	
	public void processData() {
		try {
//			roboRealmInfo.retrieveValue("CROSSHAIR_COORDINATES", containerCrosshair);
//			if (containerCrosshair.size() > 0) {
//				containerX = containerCrosshair.get(0);
//				hasContainer = true;
//			} else {
//				hasContainer = false;
//			}
			blobCount = roboRealmInfo.getNumber("BLOB_COUNT", blobCount);
			roboRealmInfo.getNumberArray("MEW_COORDINATES", goalX);
//			roboRealmInfo.getNumberArray("MEQ_COORDINATES", goalBoundaries);
			if (goalX.length >0) {			
				hasGoal = true;
			} else {
				hasGoal = false;
			}
			goalWidth = goalX[0] - goalX[1];
		} catch (TableKeyNotDefinedException e) {
			e.printStackTrace();
		}
	}
	
	public double getRotate() {
		double rotate;
		
		if (hasGoal) {
			double difference = targetX - ((goalX[0] + goalX[2]) / 2);
			
			if (Math.abs(difference) <= thresholdX) {
				difference = 0.0;
			}
			
			rotate = difference / targetX;
			
			if (Math.abs(rotate) < 0.65 && Math.abs(rotate) != 0.0) {
				if (rotate >= 0.0) {
					rotate = 0.65;
				} else {
					rotate = -0.65;
				}
			}
		} else {
			rotate = 0.65;
		}
		
		return rotate;
	}
	
	public double getMove(double targetAmount) {
		double move;
		
		if (hasGoal) {
			double ratio = goalWidth / imageWidth;
			
			double difference = ratio - targetAmount;
			
			if (Math.abs(difference) <= thresholdY) {
				difference = 0.0;
			}
			
			move = difference / targetAmount;
			
			if (Math.abs(move) < 0.65 && Math.abs(move) != 0.0) {
				if (move >= 0.0) {
					move = 0.65;
				} else {
					move = -0.65;
				}
			}	
		} else {
			move = 0.0;
		}
		
		return move;
	}
	
	
	/**
	 * Returns if the robot sees a tote
	 * @return
	 */
	public boolean isHasGoal() {
		return hasGoal;
	}
	
	public void sendToDashboard() {
		SmartDashboard.putBoolean("Has Goal", isHasGoal());
		SmartDashboard.putNumber("Target Width", goalWidth);
	
		SmartDashboard.putNumber("Rotate Speed", getRotate());
		SmartDashboard.putNumber("goalX[0]", goalX[0]);
		SmartDashboard.putNumber("goalX[1]", goalX[1]);
		SmartDashboard.putNumber("goalX.length", goalX.length);
		SmartDashboard.putNumber("Blob Count", blobCount);
	
	}

  public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      setDefaultCommand(new ProcessData());
  }
}
