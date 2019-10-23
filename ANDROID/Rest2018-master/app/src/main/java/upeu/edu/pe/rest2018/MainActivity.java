package upeu.edu.pe.rest2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private EditText edtn,edtap,edtuser,edtclave;
    private ListView lvdatos;
    private Button boton1, boton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtn = (EditText)findViewById(R.id.nombres);
        edtap = (EditText)findViewById(R.id.apellidos);
        edtuser = (EditText)findViewById(R.id.user);
        edtclave = (EditText)findViewById(R.id.clave);
        boton1 = (Button)findViewById(R.id.btncalcular);
        boton2 = (Button)findViewById(R.id.btnlimpiar);
        edtn.requestFocus();
        }
        public void reset(View view){
            limpiar();
        }
        public void save(View view){
            String nombres = edtn.getText().toString();
            String apellidos = edtap.getText().toString();
            String usuario = edtuser.getText().toString();
            String clave = edtclave.getText().toString();
            datosUsuarios(nombres,apellidos,usuario,clave);
        }
        public void datosUsuarios(String nombres, String apellidos, String usuario, String clave) {
            AsyncHttpClient client = new AsyncHttpClient();
            String url = "http://10.40.90.113/rest/registro.php?";
            String parametros = "Nombres=" + nombres + "&Apellidos=" + apellidos + "&Usuario=" + usuario + "&Clave=" + clave;
            client.post(url + parametros, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200) {
                        String resultado = new String(responseBody);
                        Toast.makeText(MainActivity.this, "Registro Guardado correctamente...! " + resultado, Toast.LENGTH_LONG).show();
                        limpiar();
                        Intent intent = new Intent(getApplicationContext(), ListarActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "No se Registro", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(MainActivity.this, "Mal: " + error, Toast.LENGTH_LONG).show();
                }
            });
        }
    public void cargarLisview(ArrayList<String> datos){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        lvdatos.setAdapter(adapter);
    }
    public void listarUsuarios(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://192.168.74.17/rest/ListaUsuarios.php";
        RequestParams  params = new RequestParams();
        client.get(url, null, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if (statusCode == 200) {
                            cargarLisview(getJson(new String(responseBody)));
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
    }
    public ArrayList<String> getJson(String response){
        ArrayList<String> lista = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(response);
            String cadena;
            for(int i=0; i<array.length();i++){
                cadena = array.getJSONObject(i).getString("usuario");
                lista.add(cadena);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }
    public void limpiar(){
        edtn.setText("");
        edtap.setText("");
        edtuser.setText("");
        edtclave.setText("");
        edtn.requestFocus();

    }
}
