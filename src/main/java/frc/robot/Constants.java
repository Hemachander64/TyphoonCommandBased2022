/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //#region Drivetrain Constants
        //#region Drivetrain IDs
            public static final int LF_MOTOR_ID = 1;
            public static final int LB_MOTOR_ID = 3;
            public static final int RF_MOTOR_ID = 5;
            public static final int RB_MOTOR_ID = 7;
        //#endregion Drivetrain IDs
            
        //#region Drive To Distance PID Constants
            public static final double DTD_KP = 1.15;
            public static final double DTD_TOLERANCE = 0.1;
            public static final double DTDP_KP = 1.5;
            public static final double DTDP_MV = Units.feetToMeters(13.69);
            public static final double DTDP_MA = DTDP_MV / 0.5;
        //#endregion Drive To Distance PID Constants

        //#region Turn To Angle PID Constants
            public static final double TTAP_KP = 0.0125;
            public static final double TTAP_MV = 1800;
            public static final double TTAP_MA = TTAP_MV / 2;
            public static final double TTA_TOLERANCE = 4.5;
        //#endregion Turn To Angle PID Constants
    //#endregion Drivetrain Constants

    //#region Feeder Constants
        //#region Feeder IDs
            public static final int FEEDER_MOTOR_ID = 15;
        //#endregion Feeder IDs
    //#endregion Feeder Constants

    //#region Lift Constants
        //#region Lift IDs
            public static final int LEFT_LIFT_MOTOR_ID = 10;
            public static final int RIGHT_LIFT_MOTOR_ID = 8;
            public static final int LEFT_TRAVERSAL_MOTOR_ID = 17;
            public static final int RIGHT_TRAVERSAL_MOTOR_ID = 18;
        //#endregion Lift IDs
    //#endregion Lift Constants

    //#region Intake Constants
        //#region Intake IDs
            public static final int INTAKE_MOTOR_ID = 6;
        //#endregion Intake IDs
    //#endregion Intake Constants

    //#region Hood Constants
        //#region Hood IDs
            public static final int HOOD_MOTOR_ID = 410; //TODO: CHANGE THIS WHEN READY
            public static final int HOOD_ANGLE_MOTOR_ID = 420; //TODO: change THIS!!!!!
        //#endregion Hood IDs
        
        //#region Hood PID Constans
            public static final double HOOD_KP = 0;
        //#endregion Hood PID Constants
    //#endregion Hood Constants
        
        //#region
            public static final double kMaxSpeedMetersPerSecond = 3.0 / 3;
            public static final double kMaxAccelerationMetersPerSecondSquared = 3.0 / 3;
    //#region Shooter Constants
        //#region Shooter PIDF Constants
            public static final double SHOOT_TOLERANCE = 10;

            public static final double KP_SHOOT = 0.0075;
            
            public static final double KS_SHOOT = 0.66;
            public static final double KV_SHOOT = 0.00165;
        //#endregion Shooter Constants

        //#region Shooter IDs
            public static final int LEFT_SHOOTER_MOTOR_ID = 16;
            public static final int RIGHT_SHOOTER_MOTOR_ID = 14;
        //#endregion Shooter IDs
    //#endregion Shooter Constants

    //#region TeleOp Constants
        //#region Drive Mode Constants
        public static final int EVIL_STALL_CURRENT_LIMIT = 35;
        public static final int EVIL_FREE_CURRENT_LIMIT  = 35;
        public static final int GOOD_STALL_CURRENT_LIMIT = 25;
        public static final int GOOD_FREE_CURRENT_LIMIT  = 25;
        //#endregion Drive Mode Constants
    //#endregion TeleOp Constants

    //#region Auton Contsants
        //#region Trajectory Constants
        public static final double ksVolts = 0.16;
        public static final double kvVoltSecondsPerMeter = 0;
        public static final double kaVoltSecondsSquaredPerMeter = 0;
        public static final double kPDriveVel = 0;
        //#endregion Trajectory Constants
    //#endregion Auton Constants
    //#region LED Constants
            public static final int LED_PWM_PORT = 0;
    //#endregion LED Constants
}

