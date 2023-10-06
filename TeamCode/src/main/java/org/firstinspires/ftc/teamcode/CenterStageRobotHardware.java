package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class CenterStageRobotHardware {

    public DcMotor FrontLeft;
    public DcMotor FrontRight;
    public DcMotor BackLeft;
    public DcMotor BackRight;
    public Servo Claw;
    public BNO055IMU Imu;

    public ElapsedTime MoveTime = new ElapsedTime();

    public double power = 0;



    public void Init(HardwareMap map){
        FrontLeft = map.get(DcMotor.class, "Fleft");
        FrontRight = map.get(DcMotor.class, "Fright");
        BackRight = map.get(DcMotor.class, "Bright");
        BackLeft = map.get(DcMotor.class, "Bleft");
        Claw = map.get(Servo.class, "Claw");
        Imu = map.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters newImuParameters;

        newImuParameters = new BNO055IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP
                )
        );

        Imu.initialize(newImuParameters);

        BackLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Claw.setPosition(0);
    }

    public void Stop(){
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
    }

    public void Drive(String dir, int blocks){
        MoveTime.reset();
        int newTime = blocks * 1450;
        MoveTime.startTime();
        if(dir == "Forward" && MoveTime.milliseconds() <= newTime){
            FrontLeft.setPower(1);
            FrontRight.setPower(1);
            BackLeft.setPower(1);
            BackRight.setPower(1);
        }
        else if(dir == "Backwards" && MoveTime.milliseconds() <= newTime){
            FrontLeft.setPower(-1);
            FrontRight.setPower(-1);
            BackLeft.setPower(-1);
            BackRight.setPower(-1);
        }
        else{this.Stop();}

    }

}
