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
    public static final int LF_MOTOR_ID = 1;
    public static final int LB_MOTOR_ID = 5;
    public static final int RF_MOTOR_ID = 7;
    public static final int RB_MOTOR_ID = 8;
    public static final int FEEDER_MOTOR_ID = 0;
    public static final int LEFT_SHOOTER_MOTOR_ID = 0;
    public static final int RIGHT_SHOOTER_MOTOR_ID = 0;

    /**
     * Stores PID gain constants.
     * <p> Usage: new PIDGains(kP, kI, kD) passed as an argument, with kP as your P constant and so on.
     */
    public static class PIDGains
    {
        public double kP, kI, kD;
        
        public PIDGains(double kP, double kI, double kD)
        {
            this.kP = kP;
            this.kI = kI;
            this.kD = kD;
        }
    }
}
