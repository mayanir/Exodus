package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;






public class HardwareRobot {
    private ElapsedTime runtime = new ElapsedTime();
    private final int ENCODERS = 1120;
    private final int DIAMETER = 10;
    private final double ENCODERS_PER_CM = ENCODERS /(DIAMETER*Math.PI);
    public DcMotor A1 = null;
    public DcMotor A2 = null;

    public void HardwareRobot(){}

    public void init(HardwareMap maya){
        A1  = maya.get(DcMotor.class, "A1");
        A2 = maya.get(DcMotor.class, "A2");

        A1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        A2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void Forward(int cm,  double power) {
        int target =(int) Math.floor (cm * ENCODERS_PER_CM);
        A1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        A2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        A1.setTargetPosition(target);
        A2.setTargetPosition(-target);
        A1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        A2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (A1.isBusy()&& A2.isBusy()){
            A1.setPower(power);
            A2.setPower(-power);
        }
        A1.setPower(0);
        A2.setPower(0);

    }

    int leftEncorders =0;
    int rightEncorders = 0;
    int p1=0;
    int p2=0;
    int i=0;
    int d=0;
    double result=0;
    public double pidCalculator(double power){
        if (A1.isBusy()&& A2.isBusy()){

            leftEncorders=A1.getCurrentPosition()-leftEncorders;
            rightEncorders=A2.getCurrentPosition()-rightEncorders;
            p2=p1;
            p1= leftEncorders- rightEncorders;
            i+=p1;
            d=p2-p1;
            result= power+0.05*p1+0.05*i+0.05*d;
            if (result>1){return 1;}
            else if (result<-1){return -1;}
            return result;

        }
            return 0;
    }












    /*public void RUN_USING_ENCODERS(){
        A1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        A2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void RUN_WITHOUT_ENCODERS(){
        A1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        A2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void setZeroPowerBeahavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior){
        A1.setZeroPowerBehavior(zeroPowerBehavior);
        A2.setZeroPowerBehavior(zeroPowerBehavior);
    }
    public void STOP_AND_RESET_ENCODER(){
        A1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        A2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }


     *//*
     * This Method defines driving using encoder
     *//*
    public void Foward(int distance, double power){
        int target;
        target = (int) Math.round(distance*ENCODERS_PER_CM);
        A1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        A2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        A1.setTargetPosition(target);
        A2.setTargetPosition(-target);

        A1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        A2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();

        while (A1.getCurrentPosition()!= target && A2.getCurrentPosition()!=-target && runtime.seconds()< 30.0){
            A1.setPower(power);
            A2.setPower(-power);
        }
        A1.setPower(0);
        A2.setPower(0);
    }*/
}