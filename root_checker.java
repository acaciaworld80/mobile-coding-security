package com.example.root.checking_root;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.scottyab.rootbeer.RootBeer;

public class MainActivity extends AppCompatActivity  {

    TextView result_prod, result_danger, result_rw, result_busy, result_su, result_all = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_prod=(TextView)findViewById(R.id.result_prod_keys);
        result_danger=(TextView)findViewById(R.id.result_danger);
        result_rw=(TextView)findViewById(R.id.result_rw_path);
        result_busy=(TextView)findViewById(R.id.result_busybox);
        result_su = (TextView)findViewById(R.id.result_su_bin);
        result_all = (TextView)findViewById(R.id.result_over);

        if(check_root()){
            result_all.setText("Device is rooted");
        }else{
            result_all.setText("Device is not rooted");
        }
    }


    public boolean check_root() {
        RootBeer rootchecker = new RootBeer(this);
        boolean status = false;

            if(rootchecker.detectTestKeys()){

                result_prod.setText("TRUE");
                status = true;
            }


            if(rootchecker.detectPotentiallyDangerousApps()){
                result_danger.setText("TRUE");
                status = true;
            }


            if(rootchecker.checkForRWPaths()){
                result_rw.setText("TRUE");
                status = true;
            }


            if(rootchecker.checkForBusyBoxBinary()){
                result_busy.setText("TRUE");
                status = true;
            }

            if(rootchecker.checkForSuBinary()){
                result_su.setText("TRUE");
                status = true;
            }

            if(status == true){
                return status;
            }else {
                return status;
            }
        }


}
