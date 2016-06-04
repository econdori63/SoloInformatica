package android.edgar.soloinformatica;


import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText DecNum;
    private LinearLayout lyTable;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

 // ejecutar activity principal
        Intent ListSong = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivity(ListSong);
////

        // ejecuatr Main activity calculadora



        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //
        DecNum = ((EditText) findViewById(R.id.DecNum));
        lyTable = ((LinearLayout) findViewById(R.id.lyTable));

        //cambiamos el color del boton flotante
        fab = ((FloatingActionButton) findViewById(R.id.fab));
        fab.setBackgroundTintList(new ColorStateList(new int[][]{new int[]{0}}, new int[]{Color.BLUE}));

        //Solo se permiten digitos del 0-9


        DecNum.setFilters(new InputFilter[]{
                new InputFilter() {
                    String numericCharacters = "0123456789";

                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        if (source != null && !numericCharacters.contains((source.toString()))) {
                            return "";
                        }
                        return null;
                    }
                }
        });




        /////////////////////

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DecNum.getText().toString().length() > 0) {
                    int num = Integer.valueOf(DecNum.getText().toString());
                    if (num <= 255 && num > 0) {
                        convertDecimalToBinary(num);
                        //oculta keyboard
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                       imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    } else {
                        Toast.makeText(MainActivity.this, "Solo números del 1 al 255", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Debe escribir un número", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ////////////////////////////

    }//onCreate:end

    /**
     * Metodo privado que convierte un numero decimal a binario mediante divisiones sucesivas
     *
     * @param num
     */
    public void convertDecimalToBinary(int num) {

        String strBinaryNumber = "";
        String strDecimalNumber = num + "";
        lyTable.removeAllViews();//limpia layout

        //header
        LinearLayout lyIndex = (LinearLayout) getLayoutInflater().inflate(R.layout.row_header, null);
        EditText tvIA = (EditText) lyIndex.findViewById(R.id.tvIA);
        tvIA.setText("" + num);
        tvIA.setTextColor(Color.rgb(176, 0, 0));
        tvIA.setTypeface(null, Typeface.BOLD);
        EditText tvIB = (EditText) lyIndex.findViewById(R.id.tvIB);
        tvIB.setText("2");
        tvIB.setTextColor(Color.rgb(176, 0, 0));
        tvIB.setTypeface(null, Typeface.BOLD);
        lyTable.addView(lyIndex);

        //
        while (num > 0) {

            int quotient = num / 2;//cociente
            int remainder = num % 2;//residuo
            num = quotient;

            LinearLayout lyItems = (LinearLayout) getLayoutInflater().inflate(R.layout.row, null);
            EditText tvA = (EditText) lyItems.findViewById(R.id.tvA);
            EditText tvB = (EditText) lyItems.findViewById(R.id.tvB);

            if (quotient > 0) {
                tvA.setText("" + quotient);
                tvB.setText("" + remainder);
            } else {
                tvA.setText("");
                tvB.setTextColor(Color.rgb(176, 0, 0));
                tvB.setText("" + remainder);
            }
            strBinaryNumber += remainder;
            lyTable.addView(lyItems);

        }
        //resultado
        strBinaryNumber = new StringBuilder(strBinaryNumber).reverse().toString();
        LinearLayout lyRes = (LinearLayout) getLayoutInflater().inflate(R.layout.row_result, null);
        TextView tvResDec = (TextView) lyRes.findViewById(R.id.tvResDec);
        TextView tvResBin = (TextView) lyRes.findViewById(R.id.tvResBin);
        tvResDec.setText(strDecimalNumber);
        tvResBin.setText(strBinaryNumber);
        lyTable.addView(lyRes);
    }
    public void  retornarMenu(View vista)
    {
        Intent retorPrin = new Intent(this, PrincipalActivity.class);
        startActivity(retorPrin);
    }


}