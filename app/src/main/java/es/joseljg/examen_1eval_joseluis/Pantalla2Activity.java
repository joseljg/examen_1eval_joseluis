package es.joseljg.examen_1eval_joseluis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class Pantalla2Activity extends AppCompatActivity {

    private double total_recibido;
    private double comision;
    private double total_final;
    TextView txt_total_recibido = null;
    TextView txt_pago_comision = null;

    TextView txt_pago_final = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);
        //-------------------------------------------------------
        comision = 0;
        txt_total_recibido = (TextView) findViewById(R.id.txt_pago_total_recibido);
        txt_pago_comision = (TextView) findViewById(R.id.txt_pago_comision);
        txt_pago_comision.setText(String.valueOf(comision));
        Intent intent = getIntent();
        if(intent != null)
        {
           total_recibido = intent.getDoubleExtra(MainActivity.EXTRA_TOTAL_A_PAGAR,0);
           txt_total_recibido.setText(String.valueOf(total_recibido));
        }
        total_final = total_recibido + comision;
        txt_pago_final = (TextView) findViewById(R.id.txt_pago_final);
        txt_pago_final.setText(String.valueOf(total_final));
    }

    public void actualizar_comision_tarjeta(View view) {
        RadioButton rb = (RadioButton) view;
        if(rb.isChecked())
        {
            switch (rb.getId())
            {
                case R.id.rb_pago_con_tarjeta:
                    comision = 0;
                    break;
                case R.id.rb_pago_con_paypal:
                    comision = total_recibido * 0.07; // 7% de comision de paypal
                    break;
                default:
                    comision = 0;
            }
            DecimalFormat df = new DecimalFormat("#.00");

            txt_pago_comision.setText(String.valueOf(df.format(comision)));
            total_final = total_recibido + comision;
            txt_pago_final.setText(String.valueOf(total_final));
        }
    }

    public void mostrar_mensaje(View view) {
        Toast.makeText(this,"pago realizado correctamente ", Toast.LENGTH_SHORT).show();
    }
}