package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class teleop_teleop extends OpMode {
    Drive drive;
    @Override
    public void init() {
        drive = new Drive(hardwareMap);
        drive.setAllRun();
    }

    @Override
    public void loop() {
        drive.goForward(48, 3.747);
    }
}
