This is a reworking of DRIVE_LIFT code to include movement control for 2 claws and framework for lift but **** not yet activated in hardware config ****


    package org.firstinspires.ftc.teamcode;

    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
    import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.DcMotorSimple;
    import com.qualcomm.robotcore.hardware.Servo;
    import com.qualcomm.robotcore.util.ElapsedTime;

    @TeleOp(name = "DRIVE_")
    public class DRIVE_ extends LinearOpMode {
        // variables
        static final double     COUNTS_PER_MOTOR_REV    = 288 ;    // eg: TETRIX Motor Encoder
        static final double     DRIVE_GEAR_REDUCTION    = 1 ;     // This is < 1.0 if geared UP
        static final double     WHEEL_DIAMETER_INCHES   = .5 ;     // For figuring circumference
        static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
        private ElapsedTime     timeClaw1 = new ElapsedTime();    // sets up a timer function for Claw 1
        private ElapsedTime     timeClaw2 = new ElapsedTime();    // sets up a timer function for Claw 2
        private double          frontLeftPower = 0;     // declare motor power variable
        private double          backLeftPower = 0;      // declare motor power variable
        private double          frontRightPower = 0;    // declare motor power variable
        private double          backRightPower = 0;     // declare motor power variable
        private double          denominator = 1;        // declare motor power calculation variable
        private int             precision = 1;          // chassis motor power reduction factor 1 = full 2 = half power 3 = third power 4 = quarter power
                                                        // ** 231023 set default speed to FULL power *******
        private double          liftPower = 0;          // declare lift motor power variable *******
        private boolean         isClosed1 = false;	// declare position variable for Claw1
        private boolean         isClosed2 = false;	// declare position variable for Claw1	    


        @Override
        public void runOpMode() throws InterruptedException {
            // Declare our motors
            // Make sure your ID's match your configuration

            DcMotor Fleft = hardwareMap.dcMotor.get("Fleft");
            DcMotor Bleft = hardwareMap.dcMotor.get("Bleft");
            DcMotor Fright = hardwareMap.dcMotor.get("Fright");
            DcMotor Bright = hardwareMap.dcMotor.get("Bright");
            //Servo Claw1 = hardwareMap.servo.get("Claw1");             // Claw removed for testing until added to physical build
            //Servo Claw2 = hardwareMap.servo.get("Claw2");             // Claw removed for testing until added to physical build
            //DcMotor "******" = hardwareMap.dcMotor.get("*****");      // Lift Motors *******
            //DcMotor "******" = hardwareMap.dcMotor.get("*****");      // Lift Motors *******



            // Reverse the right side motors
            // Reverse left motors if you are using NeveRests

            Fright.setDirection(DcMotorSimple.Direction.REVERSE);
            Bright.setDirection(DcMotorSimple.Direction.REVERSE);

            // Lift Motors direction?? *******
            
            Fleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Fright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Bleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Bright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            // Lift Motors behavior *******

            Claw1.setPosition(0);  //set Claw1 to open postion 
	          Claw2.setPosition(0);  //set Claw2 to open position


// Beginning of code to interpret operator controlled movements


            waitForStart();

            //if (isStopRequested()) return;

            while (opModeIsActive()) {

                // check for driving input

                double y = gamepad1.left_stick_y; // Remember, this is reversed!
                double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
                double rx = -gamepad1.right_stick_x;

                telemetry.update();

                // calculate motor movement math and adjust according to lift height or manual precision mode selection

                denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
                precision = 2;            // reset default speed to half power

                denominator = denominator * precision;
                frontLeftPower = (y + x + rx) / denominator;
                backLeftPower = (y - x + rx) / denominator;
                frontRightPower = (y - x - rx) / denominator;
                backRightPower = (y + x - rx) / denominator;

            		// check for claw movement input and regulate by position and timing

                if(gamepad2.a && timeClaw1.milliseconds() > 300 && !isClosed){		// checks for button a input-elapsed time-claw postion
                    Claw1.setPosition(1);  // closes Claw1	
                    isClosed1 = true;	  // sets position to closed
                    timeClaw1.reset();    // resets timer for Claw1
                }
                else if(gamepad2.a && timeClaw1.milliseconds() > 300 && isClosed){	// checks for button a input-elapsed time-claw postion
                    Claw1.setPosition(0);  // opens Claw1
                    isClosed1 = false;	  // sets position to open
            		    timeClaw1.reset();    // resets timer for Claw1
                }
                if(gamepad2.b && timeClaw2.milliseconds() > 300 && !isClosed){		// checks for button a input-elapsed time-claw postion
                    Claw2.setPosition(1);  // closes Claw2	
                    isClosed2 = true;	  // sets position to closed
                    timeClaw2.reset();    // resets timer for Claw2
                }
                else if(gamepad2.b && timeClaw2.milliseconds() > 300 && isClosed){	// checks for button a input-elapsed time-claw postion
                    Claw2.setPosition(0);  // opens Claw2
                    isClosed2 = false;	  // sets position to open
	            	    timeClaw2.reset();    // resets timer for Claw2
                }

		            // check for lift movement input





		            // issue lift power for movement





                // issue chassis power for movement

                Fleft.setPower(frontLeftPower);
                Bleft.setPower(backLeftPower);
                Fright.setPower(frontRightPower);
                Bright.setPower(backRightPower);

            }

        }
    }
