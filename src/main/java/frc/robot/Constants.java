/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


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
            public static final int LB_MOTOR_ID = 5;
            public static final int RF_MOTOR_ID = 3;
            public static final int RB_MOTOR_ID = 7;
        //#endregion Drivetrain IDs
            
    //#endregion Drivetrain Constants

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

