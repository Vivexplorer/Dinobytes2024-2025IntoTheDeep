package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Drive {
    public static DcMotorEx leftFront;
    public static DcMotorEx rightFront;
    public static DcMotorEx leftRear;
    public static DcMotorEx rightRear;


    public static DcMotorEx leftRobotArm;
    public static DcMotorEx rightRobotArm;
    public static DcMotorEx armExtender;
    public static Servo rightClaw;
    public static Servo leftClaw;
    public static Servo wrist;

    public Drive() {
    }

    public static void init(DcMotorEx leftFront, DcMotorEx rightFront, DcMotorEx leftRear, DcMotorEx rightRear) {
        Drivetrain.leftFront = leftFront;
        Drivetrain.rightFront = rightFront;
        Drivetrain.leftRear = leftRear;
        Drivetrain.rightRear = rightRear;
    }

    public static boolean isMoving() {
        return (leftFront.isBusy() || leftRear.isBusy() || rightRear.isBusy() || rightFront.isBusy());
    }

    public static void forward(double power) {
        leftRear.setPower(power);
        rightRear.setPower(power);
        rightFront.setPower(power);
        leftFront.setPower(power);
    }

    public static void strafe(double power) {
        leftRear.setPower(-power);
        rightRear.setPower(power);
        leftFront.setPower(-power);
        rightFront.setPower(power);
    }

    public static void stop() {
        leftRear.setPower(0);
        rightRear.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
    }

    public static void turn(double power) {
        leftFront.setPower(power);
        rightRear.setPower(power);
        rightFront.setPower(-power);
        leftRear.setPower(-power);
    }

    }
