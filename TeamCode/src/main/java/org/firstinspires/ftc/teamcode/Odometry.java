package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Odometry {

    private final DcMotorEx leftEncoder;
    private final DcMotorEx rightEncoder;
    private final DcMotorEx perpEncoder;

    private final Pose pose;

    private int lastLeft;
    private int lastRight;
    private int lastPerp;


    public Odometry(
            HardwareMap hardwareMap,
            String leftName,
            String rightName,
            String perpName
    ) {

        leftEncoder =
                hardwareMap.get(DcMotorEx.class, leftName);

        rightEncoder =
                hardwareMap.get(DcMotorEx.class, rightName);

        perpEncoder =
                hardwareMap.get(DcMotorEx.class, perpName);


        pose = new Pose(
                OdoConfig.START_X,
                OdoConfig.START_Y,
                OdoConfig.START_HEADING
        );


        lastLeft = getLeftPosition();
        lastRight = getRightPosition();
        lastPerp = getPerpPosition();
    }


    //update

    public void update() {

        //get encdoer pose
        int currentLeft = getLeftPosition();
        int currentRight = getRightPosition();
        int currentPerp = getPerpPosition();


        // encoder movement
        int leftTicks =
                currentLeft - lastLeft;

        int rightTicks =
                currentRight - lastRight;

        int perpTicks =
                currentPerp - lastPerp;


        //save pose
        lastLeft = currentLeft;
        lastRight = currentRight;
        lastPerp = currentPerp;


        //ticks -> mm
        double leftMM =
                leftTicks * OdoConfig.mmPerTick();

        double rightMM =
                rightTicks * OdoConfig.mmPerTick();

        double perpMM =
                perpTicks * OdoConfig.mmPerTick();


        //heading

        double parallelDistance =
                Math.abs(
                        OdoConfig.LEFT_Y_MM
                                - OdoConfig.RIGHT_Y_MM
                );


        double deltaHeading =
                (rightMM - leftMM)
                        / parallelDistance;


        //forward
        double forwardMM =
                (leftMM + rightMM) / 2.0;


        //strafe

        double strafeMM =
                perpMM
                        - deltaHeading
                        * OdoConfig.PERP_X_MM;


        double midHeading =
                pose.heading
                        + deltaHeading / 2.0;

        double fieldX =
                forwardMM * Math.cos(midHeading)
                        - strafeMM * Math.sin(midHeading);

        double fieldY =
                forwardMM * Math.sin(midHeading)
                        + strafeMM * Math.cos(midHeading);


//update pose

        pose.x += fieldX;
        pose.y += fieldY;

        pose.heading += deltaHeading;


        // Keep heading between -PI and PI
        pose.heading =
                normalizeAngle(pose.heading);
    }


//encoder pose
    private int getLeftPosition() {

        int position =
                leftEncoder.getCurrentPosition();

        if (OdoConfig.LEFT_REVERSED) {
            position = -position;
        }

        return position;
    }


    private int getRightPosition() {

        int position =
                rightEncoder.getCurrentPosition();

        if (OdoConfig.RIGHT_REVERSED) {
            position = -position;
        }

        return position;
    }


    private int getPerpPosition() {

        int position =
                perpEncoder.getCurrentPosition();

        if (OdoConfig.PERP_REVERSED) {
            position = -position;
        }

        return position;
    }


    //get

    public Pose getPose() {
        return pose;
    }


    public double getX() {
        return pose.x;
    }


    public double getY() {
        return pose.y;
    }


    public double getHeading() {
        return pose.heading;
    }


    public double getHeadingDegrees() {
        return Math.toDegrees(pose.heading);
    }


    //set

    public void setPose(
            double x,
            double y,
            double heading
    ) {

        pose.x = x;
        pose.y = y;
        pose.heading = normalizeAngle(heading);

        resetEncoders();
    }


    public void setHeading(double heading) {

        pose.heading =
                normalizeAngle(heading);
    }


    // reset

    public void resetEncoders() {

        lastLeft = getLeftPosition();
        lastRight = getRightPosition();
        lastPerp = getPerpPosition();
    }


    //normalize the angle

    private double normalizeAngle(double angle) {

        while (angle > Math.PI) {
            angle -= 2.0 * Math.PI;
        }

        while (angle < -Math.PI) {
            angle += 2.0 * Math.PI;
        }

        return angle;
    }
}