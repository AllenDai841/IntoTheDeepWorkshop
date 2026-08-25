package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Sample Autonomous", group = "Autonomous")
public class Autonomous extends LinearOpMode {

    private Arm arm;
    private Odometry odometry;

    @Override
    public void runOpMode() {
        // init hardware
        arm = new Arm(hardwareMap);
        odometry = new Odometry(hardwareMap, "leftEncoder", "rightEncoder", "perpEncoder"); //[cite: 6]

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            // update pose
            odometry.update();

            // lift
            arm.vertical(2000, 0.8);
            arm.armRotate(0.8);
            arm.extend(0.5);
            sleep(1500);

            // outtake
            arm.intake(false);
            sleep(1000);

            // reset arm
            arm.extend(0.0); //[cite: 1]
            arm.armRotate(0.0); //[cite: 1]
            arm.vertical(0, 0.5); //[cite: 1]
            sleep(1000);

            // update pose
            odometry.update(); //[cite: 6]
            telemetry.addData("Pose", odometry.getPose().toString()); //[cite: 6, 7]
            telemetry.update();
        }
    }
}