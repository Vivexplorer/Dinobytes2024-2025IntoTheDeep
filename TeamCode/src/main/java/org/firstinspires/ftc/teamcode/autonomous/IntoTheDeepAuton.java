package org.firstinspires.ftc.teamcode.autonomous;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.InstantFunction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.tuning.MecanumDrive;

@Autonomous(name = "IntoTheDeepAuton")
public class IntoTheDeepAuton extends LinearOpMode {

    PIDController pidController = new PIDController(kp,ki,kd);
    public static double kp = 0.005, kd = 0.0001, ki = 0, kf = 0.05, target =0;
    public static double ticksInInches = 720/180;// i tune this by extending my system to some place and dividing the read encoder ticks by the length(in your
    Servo rightClaw;
    Servo leftClaw;

    Servo wrist;

    DcMotorEx LeftArm;
    DcMotorEx RightArm;

    public class Arm {


        public Arm(HardwareMap hardwareMap) {
            LeftArm = hardwareMap.get(DcMotorEx.class, "leftRobotArm");
            RightArm = hardwareMap.get(DcMotorEx.class, "rightRobotArm");

            LeftArm.setDirection(DcMotorSimple.Direction.REVERSE);
            RightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }



        public class ArmLow implements InstantFunction {

            @Override
            public void run() {
                target = 400;
            }
        }
        public InstantFunction armLow() {
            return new ArmLow();
        }

        public class ArmGround implements InstantFunction {

            @Override
            public void run() {
                target = 40;
            }
        }
        public InstantFunction armGround() {
            return new ArmGround();
        }

        public class ArmUpdate implements Action {

            private boolean initialized = true;
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                pidController.setPID(kp, ki, kd);
                int armPos = (RightArm.getCurrentPosition());
                double pid = pidController.calculate(armPos, target);
                double ff = Math.cos(Math.toRadians(target / ticksInInches)) * kf;

                double power = pid + ff;

                RightArm.setPower(power);
                LeftArm.setPower(power);

                return true;

            }
        }
        public Action armUpdate() {
            return new ArmUpdate();
        }
    }








    public class Claw {
        private Servo clawPivot;

        private Servo rightClaw;

        private Servo leftClaw;

        public Claw(HardwareMap hardwareMap) {
            rightClaw = hardwareMap.get(Servo.class, "rightClaw");
            leftClaw = hardwareMap.get(Servo.class, "leftClaw");
            clawPivot = hardwareMap.get(Servo.class, "wrist");



        }

        public class OpenClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                rightClaw.setPosition(0.6);
                leftClaw.setPosition(0.35);

                return false;
            }

        }

        public Action openClaws() {
            return new OpenClaw();
        }

        public class ClawPivotGround implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {

                clawPivot.setPosition(0);

                return false;
            }

        }

        public Action clawPivotGround() {
            return new ClawPivotGround();
        }

        public class ClawPivotGrabSecondPixel implements Action {
            @Override
            public boolean run(@Nullable TelemetryPacket packet) {
                clawPivot.setPosition(0.6);

                return false;
            }
        }

        public Action clawPivotGrabSecondPixel() {
            return new ClawPivotGrabSecondPixel();
        }

        public class CloseClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                rightClaw.setPosition(0.9);
                leftClaw.setPosition(0);

                return false;
            }

        }

        public Action closeClaw() {
            return new CloseClaw();
        }

        public class ClawPivotUp implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {

                clawPivot.setPosition(0.1);

                return false;
            }

        }

        public Action clawPivotUp() {
            return new ClawPivotUp();
        }


        public void openClaw() {
            rightClaw.setPosition(0.6);
            leftClaw.setPosition(0.3);
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {

        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        wrist = hardwareMap.get(Servo.class, "wrist");


        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap, new Pose2d(-12, -62, Math.toRadians(90)));
        Claw claw = new Claw(hardwareMap);
        Arm arm = new Arm(hardwareMap);
        TelemetryPacket packet = new TelemetryPacket();
        TestAutonomous testAutonomous = new TestAutonomous();
        Action trajectoryAction1;
        Action trajectoryAction2;
        Action trajectoryAction3;
        Action trajectoryAction4;
        Action wait;
        Action wait2;


        trajectoryAction1 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .splineToConstantHeading(new Vector2d(-12,-36), Math.toRadians(90))
                //                    .stopAndAdd(arm.armLow())
//                    .waitSeconds(2)
//                    .stopAndAdd(claw.clawPivotUp())
//                    .stopAndAdd(claw.openClaws())
//                    .splineToConstantHeading(new Vector2d(38,-45),Math.toRadians(180))
//                    .stopAndAdd(claw.clawPivotGround())
//                    .waitSeconds(1)
//                    .stopAndAdd(claw.closeClaw())
//                    .stopAndAdd(claw.clawPivotUp())
//                    .waitSeconds(2)
//                    .splineTo(new Vector2d(53, -50), Math.toRadians(310))
//                    .stopAndAdd(arm.armLow())
//                    .waitSeconds(2)
//                    .stopAndAdd(claw.openClaws())
//                    .waitSeconds(2)
//                    .splineTo(new Vector2d(38, -55), Math.toRadians(180))
//                    .stopAndAdd(claw.clawPivotGround())
//                    .waitSeconds(1)
//                    .stopAndAdd(claw.closeClaw())
//                    .stopAndAdd(claw.clawPivotUp())
//                    .waitSeconds(2)
//                    .splineTo(new Vector2d(53, -50), Math.toRadians(310))
//                    .stopAndAdd(claw.openClaws())
//                    .waitSeconds(2)
//                    .splineTo(new Vector2d(24, -55), Math.toRadians(270))
//                    .stopAndAdd(claw.clawPivotGround())
//                    .waitSeconds(1)
//                    .stopAndAdd(claw.closeClaw())
//                    .stopAndAdd(claw.clawPivotUp())
//                    .waitSeconds(2)
//                    .lineToY(-50)
//                    .splineTo(new Vector2d(53, -50), Math.toRadians(130))
//                    .stopAndAdd(claw.openClaws())
                .build();

        trajectoryAction2 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .stopAndAdd(claw.openClaws())
                .waitSeconds(2)

                .build();

        trajectoryAction3 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .splineToConstantHeading(new Vector2d(-48,-38), Math.toRadians(90))
                .stopAndAdd(claw.clawPivotGround())
                .stopAndAdd(claw.closeClaw())
                .waitSeconds(2)
                .splineToLinearHeading(new Pose2d(-54,-54, Math.toRadians(225)), Math.toRadians(90))
                .build();

        trajectoryAction4 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(-57,-38, Math.toRadians(90)), Math.toRadians(225))
                .stopAndAdd(arm.armGround())
                .stopAndAdd(claw.closeClaw())
                .waitSeconds(2)
                .splineToLinearHeading(new Pose2d(-54,-54, Math.toRadians(225)), Math.toRadians(90))
                .stopAndAdd(arm.armLow())
                .waitSeconds(2)
                .stopAndAdd(claw.openClaws())
                .waitSeconds(1)
                .build();


        wait = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .waitSeconds(2)
                .build();
        wait2 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .waitSeconds(2)
                .build();

        wrist.setPosition(0);

        rightClaw.setPosition(0.9);
        leftClaw.setPosition(0);

        waitForStart();


        Actions.runBlocking(


                new ParallelAction(
                        arm.armUpdate(),

                        new SequentialAction(
                                trajectoryAction1,

                                new InstantAction(arm.armLow()),

                                wait,

                                trajectoryAction2,



                                new InstantAction(arm.armGround()),

                                trajectoryAction3,

                                new InstantAction(arm.armLow()),

                                wait2,

                                trajectoryAction4







                        )





                )

        );

    }

}
