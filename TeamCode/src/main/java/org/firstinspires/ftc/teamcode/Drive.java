package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drive{
    private DcMotorEx fl;
    private DcMotorEx fr;
    private DcMotorEx bl;
    private DcMotorEx br;
    public Drive(HardwareMap hardwareMap){
        fl = hardwareMap.get(DcMotorEx.class, "fl");
        fr = hardwareMap.get(DcMotorEx.class, "fr");
        bl = hardwareMap.get(DcMotorEx.class, "bl");
        br = hardwareMap.get(DcMotorEx.class, "br");
    }

    public void drive(double forward, double strafe, double turn) {

        double flPower = forward + strafe + turn;
        double frPower = forward - strafe - turn;
        double blPower = forward - strafe + turn;
        double brPower = forward + strafe - turn;

        double maxValue = Math.max(Math.abs(flPower), Math.max(Math.abs(frPower), Math.max(Math.abs(blPower), Math.abs(brPower))));
        if(maxValue > 1){
            flPower = flPower/maxValue;
            frPower = frPower/maxValue;
            blPower = blPower/maxValue;
            brPower = brPower/maxValue;
        }
        fl.setPower(flPower);
        fr.setPower(frPower);
        bl.setPower(blPower);
        br.setPower(brPower);
    }
    public void goToPose(Pose targetPose, Pose currentPose) {

        double changeX = targetPose.x - currentPose.x;
        double changeY = targetPose.y - currentPose.y;
        double changeH = targetPose.heading - currentPose.heading;

        double heading = currentPose.heading;

        double forward = changeX * Math.sin(heading)
                + changeY * Math.cos(heading);

        double strafe = changeX * Math.cos(heading)
                - changeY * Math.sin(heading);

        double constant = 0.05;

        drive(forward * constant, strafe * constant, changeH * constant);
    }
}