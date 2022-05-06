package sg.edu.rp.c346.id21018812.billplease;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity<str> extends AppCompatActivity {
    EditText amtInput;
    EditText numPax;
    EditText valDisc;
    ToggleButton svs;
    ToggleButton gst;
    RadioGroup payMeth;
    RadioButton cash;
    RadioButton payNow;
    Button split;
    Button reset;
    TextView totalPrice;
    TextView eachPay;
    double svsBill = 0;
    double gstBill = 0;
    boolean cashBill = false;
    boolean payNowBill = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amtInput = findViewById(R.id.amtInput);
        numPax = findViewById(R.id.numPax);
        valDisc = findViewById(R.id.valDisc);
        svs = findViewById(R.id.svs);
        gst = findViewById(R.id.gst);
        payMeth = findViewById(R.id.payMeth);
        cash = findViewById(R.id.cash);
        payNow = findViewById(R.id.payNow);
        split = findViewById(R.id.split);
        reset = findViewById(R.id.reset);
        totalPrice = findViewById(R.id.totalPrice);
        eachPay = findViewById(R.id.eachPay);

        svs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                gstBill = svs.isChecked() ? 0.07 : 0;
                refreshDisplay();
            }
        });
        gst.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                gstBill = gst.isChecked() ? 0.1 : 0;
                refreshDisplay();
            }
        });
        payMeth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payNowBill = payNow.isChecked();
            }
        });
        split.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                refreshDisplay();
            }
        });
    }
    public void refreshDisplay(){
        double totalAmt = Double.parseDouble(amtInput.getText().toString());
        double totalPax = Double.parseDouble(numPax.getText().toString());
        double totalDisc = valDisc.getText().toString().isEmpty() ? 0 : Double.parseDouble(valDisc.getText().toString())/100;
        double paymentDue = totalAmt * (1 - (svsBill + gstBill + totalDisc));
        double split = paymentDue / totalPax;
        String num = payNowBill ?  "99999999" : "";
        totalPrice.setText(String.format("Total Bill: ", totalPrice));
        eachPay.setText(String.format("Each Pays: ", paymentDue ,"912345678"));
    }
}