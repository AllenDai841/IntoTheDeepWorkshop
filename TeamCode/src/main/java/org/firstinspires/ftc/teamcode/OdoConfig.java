package org.firstinspires.ftc.teamcode;

public class OdoConfig {

    // ticks per rev
    public static final double TICKS_PER_REV = 2000.0;

    // dead wheel diameter
    public static final double WHEEL_DIAMETER_MM = 35.0;

    // wheel/encoder gear ratio
    public static final double GEAR_RATIO = 1.0;


    //robot center = (0,0)
    //
    // x = forward
    // y = left

    public static final double LEFT_X_MM = 0.0;
    public static final double LEFT_Y_MM = 70.0;

    public static final double RIGHT_X_MM = 0.0;
    public static final double RIGHT_Y_MM = -70.0;

    public static final double PERP_X_MM = -120.0;
    public static final double PERP_Y_MM = 0.0;


    // encoder direction

    public static final boolean LEFT_REVERSED = false;
    public static final boolean RIGHT_REVERSED = false;
    public static final boolean PERP_REVERSED = false;


    // starting pose

    public static final double START_X = 0.0;
    public static final double START_Y = 0.0;
    public static final double START_HEADING = 0.0;


    // math

    public static double wheelCircumference() {
        return Math.PI * WHEEL_DIAMETER_MM;
    }

    public static double mmPerTick() {
        return (wheelCircumference() / TICKS_PER_REV) / GEAR_RATIO;
    }
}