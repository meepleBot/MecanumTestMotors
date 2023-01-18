// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/* ShufflboardInfo
 * This is a static class which sets up all the Shuffleboard tabs for each subsystem or command that choose to
 * implement an entry on Shuffleboard. 
 * 
 * This class can be reused as-is across any project.
 * To use, RobotContainer should add each ShuffleboardTab class. This function will then automatically
 * call update during its periodic function to write to Shuffleboard the data.
 * If the debug flag is set to true, values will be written. If set to false, they will not be output
 * to save processing time and data being transmitted. It automatically defaults to data being written.
*/ 
package frc.robot.lib;

import java.util.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.ShuffleboardTabBase;

public class ShuffleboardInfo extends SubsystemBase {
 
   // Trims unneccesary tabs when in competition
    private boolean mDebug = true;

    private ArrayList<ShuffleboardTabBase> mTabs = new ArrayList<ShuffleboardTabBase>();

    /* ShuffleBoardInteractions Instance */
    private static ShuffleboardInfo mInstance; 

    public static ShuffleboardInfo getInstance() {
        if (mInstance == null) {
            mInstance = new ShuffleboardInfo();
        }
        return mInstance;
    }

    private ShuffleboardInfo()
    {

    }

      // This method will be called once per scheduler run
  @Override
  public void periodic() {

    // If we are in competition mode, we want mDebug to be false so we don't spend the resources to update
    // Shuffleboard
    if (mDebug)
    {
      // Update all the entries in Shuffleboard
      for (ShuffleboardTabBase i : mTabs)
      {
        i.update();
      }
      }
  }


  public void addTab(ShuffleboardTabBase tab)
  {
    mTabs.add(tab);
  }

  public void createTabs(){
    // Iterate through the ArrayList and create all the tabs
    for (ShuffleboardTabBase i : mTabs)
    {
      i.createEntries();
    }
  }

  /* 
   * This function turns off the data being written to Shuffleboard 
  */
  public void writeData(boolean writeFlag)
  {
    mDebug = writeFlag;
  }
}
