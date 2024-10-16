package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This file is a LinearOpMode, A Operation Mode that runs line by Line
 * When deployed, this class should appear in the "Tele-Op" dropdown menu in alphabetical order
 * When the class is selected, the classes is loaded with all the code before the "runOpMode" method
 * After the "INIT" button is pressed, all the code before the "waitForStart()" function is ran
 * After the "PLAY" button is pressed, all the code after the "waitForStart()" function is ran
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp", group="Android Studio")
public class TeleOp extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public static DcMotorEx leftFront;
    public static DcMotorEx rightFront;
    public static DcMotorEx leftRear;
    public static DcMotorEx rightRear;
    public static DcMotorEx rightRobotArm;
    public static DcMotorEx leftRobotArm;
    public static Servo wrist;
    public static Servo claw;
    public static Servo elbow1;
    public static Servo elbow2;
    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");


        //Drivetrain.init(leftFront, rightFront, leftRear, rightRear);
//        Telemetry.init();

        waitForStart();

        while (opModeIsActive()) {
            leftFront.setPower((gamepad1.left_stick_y) + (gamepad1.right_stick_x) + (gamepad1.left_stick_x));
            rightFront.setPower((gamepad1.left_stick_y) + (-gamepad1.right_stick_x) + (-gamepad1.left_stick_x));
            leftRear.setPower((gamepad1.left_stick_y) + (gamepad1.right_stick_x) + (-gamepad1.left_stick_x));
            rightRear.setPower((-gamepad1.left_stick_y) + (-gamepad1.right_stick_x) + (gamepad1.left_stick_x));

    }
}
}