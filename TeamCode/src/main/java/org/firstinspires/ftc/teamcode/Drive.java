package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class Drive {
    public static DcMotorEx leftFront;
    public static DcMotorEx rightFront;
    public static DcMotorEx leftRear;
    public static DcMotorEx rightRear;

    public Drive() {
    }


    public static void init(DcMotorEx leftFront, DcMotorEx rightFront, DcMotorEx leftRear, DcMotorEx rightRear) {
        TeleOp.leftFront = leftFront;
        TeleOp.rightFront = rightFront;
        TeleOp.leftRear = leftRear;
        TeleOp.rightRear = rightRear;
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
