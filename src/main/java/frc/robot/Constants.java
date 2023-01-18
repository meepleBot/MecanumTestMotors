// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final int LEFT_FRONT_WHEEL_PORT = 8;
    public static final int RIGHT_FRONT_WHEEL_PORT = 9;
    public static final int LEFT_BACK_WHEEL_PORT = 7;
    public static final int RIGHT_BACK_WHEEL_PORT =6;

    public static final int MAX_CURRENT_DRIVETRAIN = 40;

    public static final double LOW_GEAR = 0.25;
    public static final double HIGH_GEAR = 0.50;
    public static final double SUPER_HIGH_GEAR = 0.60;

    public static final int DRIVER_CONTROLLER_PORT = 0;

    public static final int WHEEL_RADIUS = 4;//inches
    public static final double WHEEL_GEAR_RATIO = 1/12.75;
    public static final double DRIVETRAIN_ENCODER_DISTANCE_PER_ROTATION = (2*Math.PI*Units.inchesToMeters(WHEEL_RADIUS)*WHEEL_GEAR_RATIO);
 


}
