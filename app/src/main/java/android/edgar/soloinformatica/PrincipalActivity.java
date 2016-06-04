package android.edgar.soloinformatica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {

    public void convertidor(View vista)
    {
        finish();
    }

    public void calculadora(View vista)
    {
        Intent calcu = new Intent(this, CalcualdoraActivity.class);
        startActivity(calcu);
    }
    public void terminosBasicos(View vista)
    {
        Intent terminosBasicos = new Intent(this, TerminosBasicosActivity.class);
        startActivity(terminosBasicos);
    }

    public void clases(View vista)
    {
        Intent clases = new Intent(this, ClasesActivity.class);
        startActivity(clases);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toast.makeText(this,"3dgar.c.c@gmail.com",Toast.LENGTH_LONG).show();
    }
}
