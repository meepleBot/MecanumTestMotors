// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.lib.ShuffleboardInfo;
import frc.robot.shuffleboard.DrivetrainTab;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private static final XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER_PORT);
  private static final Drivetrain drivetrain = new Drivetrain();


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    drivetrain.setDefaultCommand(new RunCommand(() -> drivetrain.drive(driverController.getLeftY(),-driverController.getLeftX(),-driverController.getRightX()), drivetrain));

    configureShuffleboard();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    JoystickButton speedButton = new JoystickButton(driverController, XboxController.Button.kLeftBumper.value);
    speedButton.whenPressed(new InstantCommand(()-> drivetrain.setMaxSpeed(Constants.LOW_GEAR))
        .alongWith(new RunCommand(() -> drivetrain.setWheelsToCoast(false), drivetrain)));
    speedButton.whenReleased(new InstantCommand(()-> drivetrain.setMaxSpeed(Constants.HIGH_GEAR))
        .alongWith(new RunCommand(() -> drivetrain.setWheelsToCoast(true), drivetrain)));


   JoystickButton highSpeedButton = new JoystickButton(driverController, XboxController.Button.kRightBumper.value);
   highSpeedButton.whenPressed(new InstantCommand(()-> drivetrain.setMaxSpeed(Constants.SUPER_HIGH_GEAR))
       .alongWith(new RunCommand(() -> drivetrain.drive
             (driverController.getLeftY(),-driverController.getLeftX(),-driverController.getRightX(), drivetrain.getAngle()), drivetrain))
       );
   highSpeedButton.whenReleased(new InstantCommand(()-> drivetrain.setMaxSpeed(Constants.HIGH_GEAR)));



  }


  private void configureShuffleboard()
  {
    ShuffleboardInfo shuffleboardData = ShuffleboardInfo.getInstance();

    shuffleboardData.addTab(new DrivetrainTab(drivetrain));
    
    // After all tabs have been added, create the tabs
    shuffleboardData.createTabs();

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
