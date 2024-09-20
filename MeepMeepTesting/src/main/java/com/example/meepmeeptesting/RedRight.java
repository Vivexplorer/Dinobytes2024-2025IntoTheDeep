package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
public class RedRight {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(12, -62, Math.toRadians(90)))
                .lineToY(-36)
                .waitSeconds(2)
                .lineToY(-40)
                .splineToConstantHeading(new Vector2d(48,-38), Math.toRadians(90))
                .waitSeconds(2)
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(52,-56, Math.toRadians(300)), Math.toRadians(90))
                .waitSeconds(2)

                .splineToLinearHeading(new Pose2d(56, -38, Math.toRadians(90)), Math.toRadians(300))
                .waitSeconds(2)

                .splineToLinearHeading(new Pose2d(52,-56, Math.toRadians(300)), Math.toRadians(90))
                .waitSeconds(2)

                .splineToLinearHeading(new Pose2d(58, -24, Math.toRadians(0)), Math.toRadians(300))
                .waitSeconds(2)
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(52,-56, Math.toRadians(300)), Math.toRadians(0))


                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}