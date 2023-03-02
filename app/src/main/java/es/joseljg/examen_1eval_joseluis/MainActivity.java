package es.joseljg.examen_1eval_joseluis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_TOTAL_A_PAGAR = "es.joseljg.examen_1eval_joseluis.mainactivity.total_a_pagar";
    private Spinner sp_dia_semana = null;
    private EditText edt_cantidad_entradas = null;
    private TextView txt_entradas_disponibles_numero = null;
    private TextView txt_precio_entradas_numero = null;
    private TextView txt_descuento_aplicado_numero = null;
    private TextView txt_total_numero = null;
    private int cantidad_entradas = 0;
    private int cantidad_entradas_disponibles = 0;
    //---------------------------------------------------------
    private String dia;
    private double precio_entrada;
    private double total_precio_entradas;
    private double descuento_aplicado;
    private double total_descuento;

    private double total_a_pagar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------------------------------------------
        cantidad_entradas_disponibles = 15;

        sp_dia_semana = (Spinner) findViewById(R.id.sp_dia_semana);
        edt_cantidad_entradas = (EditText) findViewById(R.id.edt_cantidad_entradas);
        txt_entradas_disponibles_numero = (TextView) findViewById(R.id.txt_numero_disponible);
        txt_precio_entradas_numero = (TextView) findViewById(R.id.txt_precio_entradas_numero);
        txt_descuento_aplicado_numero = (TextView) findViewById(R.id.txt_descuento_aplicado_numero);
        txt_total_numero = (TextView) findViewById(R.id.txt_total_numero);
        //----------------------------------------------------------------------------------------
        txt_entradas_disponibles_numero.setText(String.valueOf(cantidad_entradas_disponibles));
       // codigo para el spinner----------------------------------------------------------------------
        String[] dias ={"lunes","martes","miercoles","jueves","viernes","sabado","domingo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_dia_semana.setAdapter(adapter);
        sp_dia_semana.setOnItemSelectedListener(this);
        //-----------------------------------------------------------------------------------------
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       dia = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        dia = "lunes";
    }

    public void calcular_precios(View view) {
        switch (dia)
        {
            case "lunes":
                precio_entrada = 30.0;
                break;
            case "martes":
                precio_entrada = 30.0;
                break;
            case "miercoles":
                precio_entrada = 30.0;
                break;
            case "jueves":
                precio_entrada = 30.0;
                break;
            case "viernes":
                precio_entrada = 30.0;
                break;
            case "sabado":
                precio_entrada = 35.0;
                break;
            case "domingo":
                precio_entrada = 35.0;
                break;
            default:
                precio_entrada = 35.0;
        }
        //----------------- actualizar en la pantalla
        // ---------------- el precio de la entrada
        // validamos la casilla de la cantidad de entradas
        if(String.valueOf(edt_cantidad_entradas.getText()).isEmpty())
        {
            edt_cantidad_entradas.setError("debes poner la cantidad de entradas");
            return;
        }
        //-----------------------------------------------
        cantidad_entradas = Integer.valueOf(String.valueOf(edt_cantidad_entradas.getText()));
        //------------------------------------------------
        if(cantidad_entradas_disponibles < cantidad_entradas)
        {
            edt_cantidad_entradas.setError("no hay suficiente cantidad de entradas disponibles");
            return;
        }
        //--------------------------------------------------
        total_precio_entradas = precio_entrada * cantidad_entradas;
        txt_precio_entradas_numero.setText(String.valueOf(total_precio_entradas));
        if(cantidad_entradas > 4)
        {
            descuento_aplicado = 10;
        }
        else{
            descuento_aplicado = 0;
        }
        total_descuento = total_precio_entradas * (descuento_aplicado /100.0);

        txt_descuento_aplicado_numero.setText(String.valueOf(total_descuento));

        total_a_pagar = total_precio_entradas - total_descuento;

        txt_total_numero.setText(String.valueOf(total_a_pagar));

    }

    public void ir_al_pago(View view) {
       Intent intent = new Intent(this, Pantalla2Activity.class);
       intent.putExtra(EXTRA_TOTAL_A_PAGAR, total_a_pagar);
       startActivity(intent);
    }
}