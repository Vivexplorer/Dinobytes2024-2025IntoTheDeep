package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
public class Arm {
    public static DcMotorEx leftRobotArm;
    public static DcMotorEx rightRobotArm;
    public static DcMotorEx armTilt;
    public static Servo claw;
    public static Servo wrist;
    public static Servo elbow;

    public static void init(DcMotorEx leftRobotArm, DcMotorEx rightRobotArm, DcMotorEx armTilt, Servo claw, Servo wrist, Servo elbow1, Servo elbow2) {
        TeleOp.rightRobotArm = rightRobotArm;
        TeleOp.leftRobotArm = leftRobotArm;
        TeleOp.armTilt = armTilt;
        TeleOp.wrist = wrist;
        TeleOp.claw = claw;
        TeleOp.elbow1 = elbow1;
        TeleOp.elbow2 = elbow2;

        rightRobotArm.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftRobotArm.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armTilt.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        rightRobotArm.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        leftRobotArm.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        armTilt.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        rightRobotArm.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftRobotArm.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        armTilt.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

    }


    }

