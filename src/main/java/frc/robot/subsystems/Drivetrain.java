// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  private final CANSparkMax leftFrontMotor = new CANSparkMax(Constants.LEFT_FRONT_WHEEL_PORT,MotorType.kBrushless);
  private final CANSparkMax rightFrontMotor = new CANSparkMax(Constants.RIGHT_FRONT_WHEEL_PORT,MotorType.kBrushless);
  private final CANSparkMax leftBackMotor = new CANSparkMax(Constants.LEFT_BACK_WHEEL_PORT,MotorType.kBrushless);
  private final CANSparkMax rightBackMotor = new CANSparkMax(Constants.RIGHT_BACK_WHEEL_PORT,MotorType.kBrushless);
 
  private final RelativeEncoder leftFrontEncoder = leftFrontMotor.getEncoder();
  private final RelativeEncoder rightFrontEncoder = rightFrontMotor.getEncoder();
  private final RelativeEncoder leftBackEncoder = leftBackMotor.getEncoder();
  private final RelativeEncoder rightBackEncoder = rightBackMotor.getEncoder();
 
  private final AHRS gyro = new AHRS(SerialPort.Port.kUSB);

  private final MecanumDrive drivetrain;

  


  public Drivetrain() {

    configureMotor(leftFrontMotor);
    configureMotor(leftBackMotor);
    configureMotor(rightFrontMotor);
    configureMotor(rightBackMotor);

    leftFrontMotor.setInverted(true);
    leftBackMotor.setInverted(true);

    configureEncoder(leftFrontEncoder);
    configureEncoder(leftBackEncoder);
    configureEncoder(rightFrontEncoder);
    configureEncoder(rightBackEncoder);

    drivetrain = new MecanumDrive(leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor);
    drivetrain.setMaxOutput(Constants.HIGH_GEAR);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(double ySpeed, double xSpeed, double zRotation){
    drivetrain.driveCartesian(ySpeed, xSpeed, zRotation);
  }

  public void drive(double ySpeed, double xSpeed, double zRotation, double gyroHeading){
    drivetrain.driveCartesian(ySpeed, xSpeed, zRotation, gyroHeading);
  }

  private void configureMotor(CANSparkMax motorController)
  {
    motorController.restoreFactoryDefaults();
    motorController.setIdleMode(IdleMode.kCoast);
    motorController.setSmartCurrentLimit(Constants.MAX_CURRENT_DRIVETRAIN);
 
  }

  private void configureEncoder(RelativeEncoder motorEncoder)
  {
    motorEncoder.setPositionConversionFactor(Constants.DRIVETRAIN_ENCODER_DISTANCE_PER_ROTATION);
    motorEncoder.setPosition(0);

  }

  public void setMaxSpeed(double speed) {
    drivetrain.setMaxOutput(speed);
  }

  public void setWheelsToCoast(boolean isCoast)
  {
    if (isCoast){
      leftFrontMotor.setIdleMode(IdleMode.kCoast);
      leftBackMotor.setIdleMode(IdleMode.kCoast);
      rightFrontMotor.setIdleMode(IdleMode.kCoast);
      rightBackMotor.setIdleMode(IdleMode.kCoast);
    }
     else
    {
      leftFrontMotor.setIdleMode(IdleMode.kBrake);
      leftBackMotor.setIdleMode(IdleMode.kBrake);
      rightFrontMotor.setIdleMode(IdleMode.kBrake);
      rightBackMotor.setIdleMode(IdleMode.kBrake);

    }

  }

  public double getAngle(){
    return -gyro.getAngle();
  }

  public double getLeftFrontEncoderDistance()
  {
    return leftFrontEncoder.getPosition();
  }
  
  public double getLeftBackEncoderDistance()
  {
    return leftBackEncoder.getPosition();
  }
  
  public double getRightFrontEncoderDistance()
  {
    return rightFrontEncoder.getPosition();
  }
  public double getRightBackEncoderDistance()
  {
    return rightBackEncoder.getPosition();
  }
  
  public double getLeftFrontMotorCurrent()
  {
    return leftFrontMotor.getOutputCurrent();
  }
  
  public double getLeftBackMotorCurrent()
  {
    return leftBackMotor.getOutputCurrent();
  }
  
  public double getRightFrontMotorCurrent()
  {
    return rightFrontMotor.getOutputCurrent();
  }
  public double getRightBackMotorCurrent()
  {
    return rightBackMotor.getOutputCurrent();
  }

  public double getLeftFrontSpeed()
  {
    return leftFrontEncoder.getVelocity();
  }

}
