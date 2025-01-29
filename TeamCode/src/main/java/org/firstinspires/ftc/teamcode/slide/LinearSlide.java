package org.firstinspires.ftc.teamcode.Slide;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;


public class LinearSlide {

    // Slide power parameters
    public static double powerMin = 0;
    public static double power = 0.1;
    public static double powerMax = 1;

    // Slide height parameters
    public static int heightMin = 0;
    public static int heightMax = 0;

    // Sets up motors
    public DcMotorEx slideMotorL;
    public DcMotorEx slideMotorR;

    LinearSlide(HardwareMap hardwareMap) {
        slideMotorL = hardwareMap.get(DcMotorEx.class, "slideMotorL");
        slideMotorR = hardwareMap.get(DcMotorEx.class, "slideMotorR");


        // Initialization
        slideMotorL.setDirection(DcMotorEx.Direction.FORWARD);
        slideMotorR.setDirection(DcMotorEx.Direction.REVERSE);
        slideMotorL.setTargetPosition();
        slideMotorR.setTargetPosition();
        slideMotorL.setPower();
        slideMotorR.setPower();
        slideMotorL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        slideMotorR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        slideMotorL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        slideMotorR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        slideMotorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public double setSlide(int steps) {
        slideMotorSteps = Range.clip(steps, heightMin, heightMax);
        return slideMotorSteps;
    }
}