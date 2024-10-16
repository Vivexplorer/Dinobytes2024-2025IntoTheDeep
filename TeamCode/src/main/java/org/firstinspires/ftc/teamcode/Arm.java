package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
public class Arm {
    public static DcMotorEx leftRobotArm;
    public static DcMotorEx rightRobotArm;
    public static Servo claw;
    public static Servo wrist;
    public static Servo elbow;

    public static void init(DcMotorEx leftRobotArm, DcMotorEx rightRobotArm, Servo claw, Servo wrist, Servo elbow1, Servo elbow2) {
        TeleOp.rightRobotArm = rightRobotArm;
        TeleOp.leftRobotArm = leftRobotArm;
        TeleOp.wrist = wrist;
        TeleOp.claw = claw;
        TeleOp.elbow1 = elbow1;
        TeleOp.elbow2 = elbow2;

        public static void up {
            rightRobotArm.setPower(1);
            leftRobotArm.setPower(1);
        }

        public static void down{
            rightRobotArm.setPower(-1);
            leftRobotArm.setPower(-1);
        }
    }
}
