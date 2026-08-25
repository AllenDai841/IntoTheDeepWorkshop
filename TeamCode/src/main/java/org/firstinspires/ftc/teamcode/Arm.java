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

}
