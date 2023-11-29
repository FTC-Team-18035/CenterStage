    package org.firstinspires.ftc.teamcode;

    import com.qualcomm.robotcore.eventloop.opmode.Disabled;
    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
    import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
    import com.qualcomm.robotcore.hardware.CRServo;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.DcMotorSimple;
    import com.qualcomm.robotcore.hardware.Servo;
    import com.qualcomm.robotcore.util.ElapsedTime;
@Disabled
    @TeleOp(name = "Intake_Test")
    public class IntakeTest extends LinearOpMode {
        // variables
        static final double     COUNTS_PER_MOTOR_REV    = 288 ;    // eg: TETRIX Motor Encoder
        static final double     DRIVE_GEAR_REDUCTION    = 1 ;     // This is < 1.0 if geared UP
        static final double     WHEEL_DIAMETER_INCHES   = .5 ;     // For figuring circumference
        static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

        static final int        MAX_LIFT_HEIGHT         = 400;

        private ElapsedTime     ClawTime = new ElapsedTime();    // sets up a timer function
        private ElapsedTime     LiftTime = new ElapsedTime();
        private ElapsedTime     DroneTime = new ElapsedTime();
        private ElapsedTime     IntakeTime = new ElapsedTime();
        private ElapsedTime     IntakeServoTime = new ElapsedTime();
        private ElapsedTime     ReverseIntakeTime = new ElapsedTime();
        private double          frontLeftPower = 0;     // declare motor power variable
        private double          backLeftPower = 0;      // declare motor power variable
        private double          frontRightPower = 0;    // declare motor power variable
        private double          backRightPower = 0;     // declare motor power variable
        private double          denominator = 1;        // declare motor power calculation variable
        private int             precision = 1;          // chassis motor power reduction factor 1 = full 2 = half power 3 = third power 4 = quarter power
                                                        // ** 231023 set default speed to FULL power *******

        private double          liftPower = 0.7;     // declare lift motor power variable *******

        private boolean         isClosed = false;

        private int             ClawInput = 0;

        private int             LiftTarget = 0;

        private boolean         BeganPressed = false;

        private boolean         IntakeRunning = false;


        @Override
        public void runOpMode() throws InterruptedException {
            // Declare our motors
            // Make sure your ID's match your configuration

            // DcMotor Fleft = hardwareMap.dcMotor.get("Fleft");
            //   DcMotor Bleft = hardwareMap.dcMotor.get("Bleft");
            // DcMotor Fright = hardwareMap.dcMotor.get("Fright");
            //  DcMotor Bright = hardwareMap.dcMotor.get("Bright");
            // Servo Claw1 = hardwareMap.servo.get("Claw1");
            // Servo Claw2 = hardwareMap.servo.get("Claw2");
            //  Servo Drone = hardwareMap.servo.get("Drone");
            //  DcMotor LeftLiftMotor = hardwareMap.dcMotor.get("Lift1");    // Lift Motors *******
            //   DcMotor RightLiftMotor = hardwareMap.dcMotor.get("Lift2");    // Lift Motors *******
            //   DcMotor ArmRotationMotor = hardwareMap.dcMotor.get("ArmRotationMotor");
            DcMotor IntakeMotor = hardwareMap.dcMotor.get("IntakeMotor");


            // Reverse the right side motors
            // Reverse left motors if you are using NeveRests

            // Fleft.setDirection(DcMotorSimple.Direction.REVERSE);
           //Fright.setDirection(DcMotorSimple.Direction.REVERSE);
            //Bright.setDirection(DcMotorSimple.Direction.REVERSE);
            IntakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
/*
            LeftLiftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            RightLiftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

            RightLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LeftLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            // Lift Motors direction?? *******

            Fleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Fright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Bleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Bright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            LeftLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            RightLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);*/
            IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            // Lift Motors behavior *******
            /*RightLiftMotor.setTargetPosition(0);
            LeftLiftMotor.setTargetPosition(0);
            RightLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            Claw1.setPosition(0);
            Claw2.setPosition(0);
            IntakeServo.setPosition(0);


// Beginning of code to interpret operator controlled movements

*/
            waitForStart();

            //if (isStopRequested()) return;

            while (opModeIsActive()) {

                // check for driving input

                //double y = gamepad1.left_stick_y; // Remember, this is reversed!
                // double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
                //  double rx = -gamepad1.right_stick_x;

                // telemetry.update();

                // calculate motor movement math and adjust according to lift height or manual precision mode selection

                //  denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
                //  if(gamepad1.left_bumper){
                //       precision = 4;            // reset default speed to half power
                //   }
                //   else{precision = 2;}

/*
                denominator = denominator * precision;
                frontLeftPower = (y + x + rx) / denominator;
                backLeftPower = (y - x + rx) / denominator;
                frontRightPower = (y - x - rx) / denominator;
                backRightPower = (y + x - rx) / denominator;

	// check for claw movement input and 

                switch(ClawInput){
                        //If the answer is 1 it runs the code here
                    case 1:
                        if(gamepad2.b && ClawTime.milliseconds() > 300){//This checks to see if it has been more than 300 milliseconds since the "a" button has been pressed
                            ClawTime.reset();//This resets the timer to 0
                            Claw1.setPosition(0);//this opens claw 1
                            ClawInput++;//This adds one to the variable to keep track of times the "a" button has been pressed
                        }
                        //If the answer is 3 it runs the code here
                    case 2:
                        if(gamepad2.b && ClawTime.milliseconds() > 300){//This checks to see if it has been more than 300 milliseconds since the "a" button has been pressed
                            ClawTime.reset();//This resets the timer to 0
                            Claw2.setPosition(0);//this opens claw 2
                            ClawInput++;//This adds one to the variable to keep track of times the "a" button has been pressed
                        }
                        //If the answer is 4 it runs the code here
                    case 3:
                        if(gamepad2.b){//This checks to see if the "a" button has been pressed on gamepad1
                            ClawTime.reset();//This starts the timer since the "a" button was just pressed
                            Claw1.setPosition(1);//this closes claw 1
                            Claw2.setPosition(1);//this closes claw 2
                            ClawInput = 0;//This adds one to the variable to keep track of times the "a" button has been pressed
                        }
                }
                if(gamepad2.right_bumper){
                    Claw1.setPosition(1);
                    Claw2.setPosition(1);
                    ClawTime.reset();
                }
                else if(gamepad2.right_bumper && ClawTime.milliseconds() > 300){
                    Claw1.setPosition(0);
                    Claw2.setPosition(0);
                    ClawTime.reset();
                }



                // check for lift movement input
                if(gamepad2.a && LiftTime.seconds() > 1.0){
                    LiftTarget = 350;
                    LiftTime.reset();
                }
                else if (gamepad2.x && LiftTime.seconds() > 1.0){
                    LiftTarget = 200;
                    LiftTime.reset();
                }
                else if (gamepad2.y && LiftTime.seconds() > 1.0){
                    LiftTarget = 100;
                    LiftTime.reset();
                }

                /*if((gamepad2.dpad_up) && (LiftTarget + 10) < MAX_LIFT_HEIGHT){
                    LiftTarget = LiftTarget + 1;
                }*/
                /*if(gamepad2.dpad_up && RightLiftMotor.getCurrentPosition() < MAX_LIFT_HEIGHT - 10){
                    LiftTarget = LiftTarget + 10;
                }
                if(gamepad2.dpad_down && RightLiftMotor.getCurrentPosition() > 10){
                    LiftTarget = LiftTarget - 10;
                }

                if(LiftTarget > RightLiftMotor.getCurrentPosition()){liftPower = 0.7;}
                else{liftPower = 0.3;}




	// issue lift power for movement
                if(!(LiftTarget > MAX_LIFT_HEIGHT)){
                    RightLiftMotor.setTargetPosition(LiftTarget);
                    LeftLiftMotor.setTargetPosition(LiftTarget);
                    RightLiftMotor.setPower(liftPower);
                    LeftLiftMotor.setPower(liftPower);
                }





                if(gamepad1.b){
                    DroneTime.reset();
                    BeganPressed = true;
                }
                if(!gamepad1.b && DroneTime.seconds() > 3.0 && BeganPressed == true){
                    Drone.setPosition(1);
                }
                else{BeganPressed = false;}
                // issue chassis power for movement

*/

                if (gamepad1.a && !IntakeRunning && IntakeTime.seconds() > 0.5) {
                    IntakeMotor.setPower(1);
                    IntakeRunning = true;
                    IntakeTime.reset();
                }
                else if (gamepad1.a && IntakeRunning && IntakeTime.seconds() > 0.5) {
                    IntakeMotor.setPower(0);
                    IntakeRunning = false;
                    IntakeTime.reset();
                }


                if (gamepad1.x) {				// Evaluates x button pushed
                    if (ReverseIntakeTime.seconds() > 0.25) {	   // If the button has been held for 1/4 second
                        IntakeMotor.setPower(-1);		      // The intake rollers are reversed
                        IntakeRunning = true;			      // The intake status is marked as running
                    } else {					   // If the button has not been held for 1/4 second
                        IntakeMotor.setPower(0);		      // The intake rollers are stopped
                        IntakeRunning = false;			      // The intake status is marked as stopped
                    }
                } else {					// If x button has not been pushed
                    ReverseIntakeTime.reset();			   // The timer is reset
                }

               // if(gamepad2.left_bumper) {
                 //   IntakeServo.setPosition(1);
                 //   IntakeServoTime.reset();
               // }
               // if(gamepad2.left_bumper && IntakeServoTime.seconds() > 1.0){
               //     IntakeServo.setPosition(0);
               //     IntakeServoTime.reset();
               // }
            /*
                Fleft.setPower(frontLeftPower);
                Bleft.setPower(backLeftPower);
                Fright.setPower(frontRightPower);
                Bright.setPower(backRightPower);

            }

        }*/
            }
        }
    }
