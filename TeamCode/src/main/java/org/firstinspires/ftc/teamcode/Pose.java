package org.firstinspires.ftc.teamcode;

public class Pose {

    public double x;
    public double y;
    public double heading;

    public Pose(double x, double y, double heading) {
        this.x = x;
        this.y = y;
        this.heading = heading;
    }

    public Pose copy() {
        return new Pose(x, y, heading);
    }
    public Pose fieldToRobot(Pose robotPose) {
        double dx = this.x - robotPose.x;
        double dy = this.y - robotPose.y;

        double robotX = dx * Math.cos(robotPose.heading)
                + dy * Math.sin(robotPose.heading);

        double robotY = -dx * Math.sin(robotPose.heading)
                + dy * Math.cos(robotPose.heading);

        double robotHeading = this.heading - robotPose.heading;

        return new Pose(robotX, robotY, robotHeading);
    }
    @Override
    public String toString() { //used ai to format the string
        return String.format(
                "X: %.2f | Y: %.2f | Heading: %.2f°",
                x,
                y,
                Math.toDegrees(heading)
        );
    }
}