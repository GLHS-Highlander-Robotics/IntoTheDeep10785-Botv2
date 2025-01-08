package org.firstinspires.ftc.teamcode.Slide;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class LinearSlide {

    // Slide power parameters

    // Slide height parameters

    // Sets up motors
    public DcMotorEx slideMotorL;
    public DcMotorEx slideMotorR;

    LinearSlide(HardwareMap hardwareMap) {
        slideMotorL = hardwareMap.get(DcMotorEx.class, "slideMotorL");
        slideMotorR = hardwareMap.get(DcMotorEx.class, "slideMotorR");


        // Initialization
        slideMotorL.setDirection(DcMotorEx.Direction.FORWARD);
        slideMotorL.setDirection(DcMotorEx.Direction.REVERSE);

        slideMotorL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        slideMotorR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }
}