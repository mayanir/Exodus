package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HardwareRobot;

@TeleOp(name="Drive", group="Iterative Opmode")
//@Disabled
public class RemoteControl extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private double y = 0;
    private double x = 0;
    private double t = 0.5;
    private double absoluteValue = 0;
    private HardwareRobot Shachar = new HardwareRobot();

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables.
        Shachar.init(hardwareMap);



        //leftDrive.setDirection(DcMotor.Direction.FORWARD);
        //rightDrive.setDirection(DcMotor.Direction.REVERSE);
        //Shachar.STOP_AND_RESET_ENCODER();
        //Shachar.RUN_WITHOUT_ENCODERS();
        Shachar.A1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Shachar.A2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        // variable for each drive wheel's power level
        y = t*Math.pow(gamepad1.left_stick_y,3);
        x = -t*Math.pow(gamepad1.left_stick_x,3);
        absoluteValue = Math.abs( Math.max(Math.abs(y+x), Math.abs(x-y)));


        if (absoluteValue>1){
            Shachar.A1.setPower((x-y)/ absoluteValue );
            Shachar.A2.setPower(x+y / absoluteValue );
        } else {
            Shachar.A1.setPower(x-y);
            Shachar.A2.setPower(x+y);
        }





        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("A1", Shachar.A1.getCurrentPosition());
        telemetry.addData("A2", Shachar.A2.getCurrentPosition());
        telemetry.update();

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}