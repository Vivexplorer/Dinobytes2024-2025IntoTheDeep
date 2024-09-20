package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class RedLeft {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-12, -62, Math.toRadians(90)))
                .lineToY(-36)
                .waitSeconds(2)
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-48,-38), Math.toRadians(90))
                .waitSeconds(2)
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(-54,-54, Math.toRadians(225)), Math.toRadians(90))
                .waitSeconds(2)
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(-57,-38, Math.toRadians(90)), Math.toRadians(225))
                .waitSeconds(2)
                .splineToLinearHeading(new Pose2d(-54,-54, Math.toRadians(225)), Math.toRadians(90))
                .waitSeconds(2)
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(-57,-24,Math.toRadians(180)), Math.toRadians(225))
                .waitSeconds(2)
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(-54,-54, Math.toRadians(225)), Math.toRadians(90))






                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}