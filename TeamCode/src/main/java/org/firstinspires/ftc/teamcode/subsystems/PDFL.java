package org.firstinspires.ftc.teamcode.subsystems;

public class PDFL {

    private double kP, kD, kF, kL;


    public PDFL (double kP, double kD, double kf, double kl) {
        this.kP = kP;
        this.kD = kD;
        this.kL = kL;
        this.kF = kF;
    }

    public double run(double error) {
        double p = pComponent(error);
        double f = fComponent();
        double l = lComponent(error);

        double response = p+f+l;

        return response;
    }

    private double pComponent(double error) {

        double response = kP * error;

        return response;
    }

    private double dComponent(double delta_error, double delta_time) {
        double derivative = delta_error/delta_time;

        double response = derivative * kD;
        return response;
    }

    private double fComponent() {

        double response = kF;

        return response;
    }

    private double lComponent(double error) {

        double direction = Math.signum(error);

        double response = direction * kL;

        return response;
    }
}

