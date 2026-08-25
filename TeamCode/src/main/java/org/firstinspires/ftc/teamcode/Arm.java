package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    private DcMotorEx verticalLeft;
    private DcMotorEx verticalRight;
    private Servo armRotateLeft;
    private Servo armRotateRight;
    private Servo armExtend;
    private Servo wrist;
    private Servo intakeRight;
    private Servo intakeLeft;

    public Arm(HardwareMap hardwareMap){
        verticalLeft = hardwareMap.get(DcMotorEx.class, "verticalLeft");
        verticalRight = hardwareMap.get(DcMotorEx.class, "verticalRight");
        armRotateLeft = hardwareMap.get(Servo.class, "armRotateLeft");
        armRotateRight = hardwareMap.get(Servo.class, "armRotateRight");
        armExtend = hardwareMap.get(Servo.class, "armExtend");
        wrist = hardwareMap.get(Servo.class, "wrist");
        intakeRight = hardwareMap.get(Servo.class, "intakeRight");
        intakeLeft = hardwareMap.get(Servo.class, "intakeLeft");
    }

    public void vertical(int distance, double power){
        verticalLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        verticalRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        verticalLeft.setTargetPosition(distance);
        verticalRight.setTargetPosition(distance);

        verticalLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        verticalRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        verticalLeft.setPower(power);
        verticalRight.setPower(power);
    }

    public void armRotate(double position){
        armRotateLeft.setPosition(position);
        armRotateRight.setPosition(position);
    }

    public void extend(double position){
        armExtend.setPosition(position);
    }

    public void intake(boolean in){
        if(in){//this one intakes
            intakeRight.setPosition(0);//these are placeholder values
            intakeLeft.setPosition(1);
        }
        else{
            intakeRight.setPosition(1);
            intakeLeft.setPosition(0);
        }
    }

}
