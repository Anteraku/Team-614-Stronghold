package org.usfirst.frc.team614.robot.subsystems;

import java.util.ArrayList;

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
	private NumberArray goalBoundaries;
	//private NumberArray containerCrosshair;
	private boolean hasGoal;
	public boolean hasContainer;
	private double blobCount;
	
	private final double imageWidth = 320;
	private final double imageHeight = 240;
	private double targetX;
	
	private double thresholdX = 7.5;
	private double thresholdY = 0.1;
	
	private double YLimit = imageHeight * .5;
	

	private double[] goalX;
	private ArrayList goalCoord =  new ArrayList();
	private double goalWidth;
	
	public VisionProcessor() {
		super("Vision Processor");
		roboRealmInfo = NetworkTable.getTable("SmartDashboard");
		goalBoundaries = new NumberArray();
		//containerCrosshair = new NumberArray();
		goalX = new double[2];
		targetX = imageWidth *.65;
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
			roboRealmInfo.getNumberArray("MEQ_COORDINATES", goalX);
			roboRealmInfo.retrieveValue("MEQ_COORDINATES", goalBoundaries);
//			roboRealmInfo.getNumberArray("MEQ_COORDINATES", goalBoundaries);
			//if (goalX.length >0) {			
			if(roboRealmInfo.getNumberArray("MEQ_COORDINATES").length > 0){
				goalX[0] = goalBoundaries.get(0);
				goalX[1] = goalBoundaries.get(2);
				
				//goalX = getGoalX();
				
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
			double difference = targetX - ((goalX[0] + goalX[1]) / 2);
			
			if (Math.abs(difference) <= thresholdX) {
				difference = 0.0;
			}
			
			rotate = difference / targetX;
			
			if (Math.abs(rotate) < 0.4 && Math.abs(rotate) != 0.0) {
				if (rotate >= 0.0) {
					rotate = 0.4;
				} else {
					rotate = -0.4;
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
	
	public double[] getGoalX(){
		
		int index = 0;
		double largestGoalWidth = 0;
		double [] goals = new double[goalBoundaries.size()];
		
		for(int i = 0;i<goalBoundaries.size();i++){
			goals[i] = goalBoundaries.get(i);
		}
		
		for(int i = 0;i<goals.length;i+=8){
			if(goals[i+1]<YLimit || goals[i+3]<YLimit || goals[i+5]<YLimit || goals[i+7]<YLimit){
				break;
			}
			double currGoalWidth = (goals[i] + goals[i+2]) / 2;
			if(currGoalWidth > largestGoalWidth){
				largestGoalWidth = currGoalWidth;
				index = i;
			}
			
		}
		return new double [] {goalBoundaries.get(index), goalBoundaries.get(index+2)};
		
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
		SmartDashboard.putNumber("goalX[0]", goalX[0]);
		SmartDashboard.putNumber("goalX[1]", goalX[1]);
		SmartDashboard.putNumber("goalX[0]", goalX[0]);
		SmartDashboard.putNumber("goalX[1]", goalX[1]);
		SmartDashboard.putNumber("goalX[0]", goalX[0]);
		SmartDashboard.putNumber("goalX[1]", goalX[1]);
		SmartDashboard.putNumber("goalBoundaries.size()", goalBoundaries.size());
		
		SmartDashboard.putNumber("Blob Count", blobCount);
//		SmartDashboard.putNumber("Blob Count Test", blobCount = roboRealmInfo.getNumber("BLOB_COUNT", blobCount));
	
	}

  public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      setDefaultCommand(new ProcessData());
  }
}
