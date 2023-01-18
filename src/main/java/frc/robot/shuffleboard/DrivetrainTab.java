// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.shuffleboard;


import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.lib.ShuffleboardTabBase;
import frc.robot.subsystems.Drivetrain;

/** Add your docs here. */
public class DrivetrainTab extends ShuffleboardTabBase{

    private Drivetrain m_Drivetrain;;

    private NetworkTableEntry m_gyroHeadingEntry;
    private NetworkTableEntry m_leftFrontMotorEncoderEntry, m_leftBackMotorEncoderEntry;
    private NetworkTableEntry m_rightFrontMotorEncoderEntry, m_rightBackMotorEncoderEntry;
    private NetworkTableEntry m_leftFrontMotorCurrentEntry, m_leftBackMotorCurrentEntry;
    private NetworkTableEntry m_rightFrontMotorCurrentEntry, m_rightBackMotorCurrentEntry;
    private NetworkTableEntry m_leftFrontPDPCurrentEntry,m_leftBackPDPCurrentEntry; 
    private NetworkTableEntry m_rightFrontPDPCurrentEntry,m_rightBackPDPCurrentEntry;


    private ShuffleboardLayout m_DataLayout, m_PoseLayout, m_PDPLayout;

    PowerDistribution powerDistribution = new PowerDistribution();
    private int pdLeftFront = 14;
    private int pdLeftBack = 13;
    private int pdRightFront = 0;
    private int pdRightBack = 12;


    public DrivetrainTab(Drivetrain dt)
    {
        m_Drivetrain = dt;
    }

	@Override
	public void createEntries() {
		m_Tab = Shuffleboard.getTab("Drivetrain");

        m_DataLayout = m_Tab.getLayout("Encoder", BuiltInLayouts.kGrid).withProperties(Map.of("Label Position", "top")).withPosition(2, 0).withSize(2, 2);
        m_leftFrontMotorEncoderEntry = m_DataLayout.add("LF Encoder", 0).withPosition(0,0).getEntry();
        m_rightFrontMotorEncoderEntry = m_DataLayout.add("RF Encoder", 0).withPosition(1,0).getEntry();
        m_leftBackMotorEncoderEntry = m_DataLayout.add("LB Encoder",0).withPosition(0, 1).getEntry();
        m_rightBackMotorEncoderEntry = m_DataLayout.add("RB Encoder",0).withPosition(1,1).getEntry();

        m_PoseLayout = m_Tab.getLayout("Encoder Current", BuiltInLayouts.kGrid).withPosition(4, 0).withProperties(Map.of("Label Position", "top")).withSize(2, 2);
        m_leftFrontMotorCurrentEntry = m_PoseLayout.add("LF Current", 0).withPosition(0,0).getEntry();
        m_rightFrontMotorCurrentEntry = m_PoseLayout.add("RF Current", 0).withPosition(1,0).getEntry();
        m_leftBackMotorCurrentEntry = m_PoseLayout.add("LB Current",0).withPosition(0, 1).getEntry();
        m_rightBackMotorCurrentEntry = m_PoseLayout.add("RB Current",0).withPosition(1,1).getEntry();

        m_PDPLayout = m_Tab.getLayout("PDP Current", BuiltInLayouts.kGrid).withPosition(4, 4).withProperties(Map.of("Label Position", "top")).withSize(2, 2);
        m_leftFrontPDPCurrentEntry = m_PDPLayout.add("LF Current", 0).withPosition(0,0).getEntry();
        m_rightFrontPDPCurrentEntry = m_PDPLayout.add("RF Current", 0).withPosition(1,0).getEntry();
        m_leftBackPDPCurrentEntry = m_PDPLayout.add("LB Current",0).withPosition(0, 1).getEntry();
        m_rightBackPDPCurrentEntry = m_PDPLayout.add("RB Current",0).withPosition(1,1).getEntry();


        m_gyroHeadingEntry = m_Tab.add("Gyro Heading", m_Drivetrain.getAngle()).getEntry();

        

    }

    @Override
	public void update() {

        m_rightFrontMotorEncoderEntry.setDouble(m_Drivetrain.getRightFrontEncoderDistance());
        m_rightBackMotorEncoderEntry.setDouble(m_Drivetrain.getRightBackEncoderDistance());
        m_leftFrontMotorEncoderEntry.setDouble(m_Drivetrain.getLeftFrontEncoderDistance());
        m_leftBackMotorEncoderEntry.setDouble(m_Drivetrain.getLeftBackEncoderDistance());

        m_rightFrontMotorCurrentEntry.setDouble(m_Drivetrain.getRightFrontMotorCurrent());
        m_rightBackMotorCurrentEntry.setDouble(m_Drivetrain.getRightBackMotorCurrent());
        m_leftFrontMotorCurrentEntry.setDouble(m_Drivetrain.getLeftFrontMotorCurrent());
        m_leftBackMotorCurrentEntry.setDouble(m_Drivetrain.getLeftBackMotorCurrent());

        m_rightFrontPDPCurrentEntry.setDouble(powerDistribution.getCurrent(pdRightFront));
        m_rightBackPDPCurrentEntry.setDouble(powerDistribution.getCurrent(pdRightBack));
        m_leftFrontPDPCurrentEntry.setDouble(powerDistribution.getCurrent(pdLeftFront));
        m_leftBackPDPCurrentEntry.setDouble(powerDistribution.getCurrent(pdLeftBack));


        m_gyroHeadingEntry.setDouble(m_Drivetrain.getAngle());
    }
}
