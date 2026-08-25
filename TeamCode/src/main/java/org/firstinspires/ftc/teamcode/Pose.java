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

    @Override
    public String toString() {
        return String.format(
                "X: %.2f | Y: %.2f | Heading: %.2f°",
                x,
                y,
                Math.toDegrees(heading)
        );
    }
}