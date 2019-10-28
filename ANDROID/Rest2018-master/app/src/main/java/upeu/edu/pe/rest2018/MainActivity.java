package upeu.edu.pe.rest2018;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MainActivity extends AppCompatActivity {
    private EditText edtn,edtap,edtuser,edtclave;
    private ListView lvdatos;
    private Button php,python,node,java, boton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtn = (EditText)findViewById(R.id.nombres);
        edtap = (EditText)findViewById(R.id.apellidos);
        edtuser = (EditText)findViewById(R.id.user);
        edtclave = (EditText)findViewById(R.id.clave);
        php = (Button)findViewById(R.id.sphp);
        python = (Button)findViewById(R.id.spython);
        node = (Button)findViewById(R.id.snode);
        java = (Button)findViewById(R.id.sjava);
        boton2 = (Button)findViewById(R.id.btnlimpiar);
        edtn.requestFocus();
        }
        public void reset(View view){
            limpiar();
        }


        // Consumiendo API
    public void CrearUsuario(
            String url,
            final String n,
            final String nombres,
            final String a,
            final String apellidos,
            final String u,
            final String usuario,
            final String c,
            final String clave,
            final String urlw) {

        AsyncHttpClient client = new AsyncHttpClient();
        String parametros = n+nombres + a+apellidos + u+usuario + c+clave;
        String urlParameter = parametros.replace(" ","%20");
        System.out.println(url + parametros);
        client.post( url + urlParameter,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String resultado = new String(responseBody);
                    Toast.makeText(MainActivity.this, "Guardado " + resultado, Toast.LENGTH_LONG).show();
                    listar(urlw);
                    limpiar();
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

    // Consumiendo API
    public void CrearUsuarios(String url,
                              final String n,
                              final String nombres,
                              final String a,
                              final String apellidos,
                              final String u,
                              final String usuario,
                              final String c,
                              final String clave,
                              final String urlw) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Exitosa", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String,String> parametres = new HashMap<String, String>();
                parametres.put(n,nombres);
                parametres.put(a,apellidos);
                parametres.put(u,usuario);
                parametres.put(c,clave);
                return parametres;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        listar(urlw);
        /*AsyncHttpClient client = new AsyncHttpClient();
        String parametros = n+nombres + a+apellidos + u+usuario + c+clave;
         RequestParams paramss = new RequestParams();
        //JSONObject paramss = new JSONObject();
        paramss.put(n,nombres);
        paramss.put(a,apellidos);
        paramss.put(u,usuario);
        paramss.put(c,clave);
        //StringEntity entity = new StringEntity();
        System.out.println(url + paramss);
        //JSONObject params = new JSONObject();
        // params.put("idusuario",parametros);
        //StringEntity entity = new StringEntity(params.toString());
        client.post( url+parametros,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String resultado = new String(responseBody);
                    Toast.makeText(MainActivity.this, "Guardado " + resultado, Toast.LENGTH_LONG).show();
                    listar(urlw);
                    limpiar();
                } else {
                    Toast.makeText(MainActivity.this, "No se Registro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, "Mal: " + error, Toast.LENGTH_LONG).show();
            }

        });

         */
    }

    String ruta = "http://192.168.43.201";

    public void listar(String urlw){
        Intent intent = new Intent(getApplicationContext(), ListarActivity.class);
        String url = ruta + urlw;
        intent.putExtra("url",url);
        startActivity(intent);
    }

    public void sphp(View view){
        String url = "/APIS_WITH_ANDROID/PHP/registrar.php";
        String urlw = "/APIS_WITH_ANDROID/PHP/api.php/get";
        String rt = ruta + url;
        String n = "nombres";
        String nombres = edtn.getText().toString();
        String a = "&apellidos";
        String apellidos = edtap.getText().toString();
        String u = "&usuarios";
        String usuario = edtuser.getText().toString();
        String c = "&claves";
        String clave = edtclave.getText().toString();
        CrearUsuarios(rt,n,nombres,a,apellidos,u,usuario,c,clave, urlw);
    }

    public void spython(View view){
        String url = ":5000/addUser/?";
        String urlw = ":5000/getAll/";
        String rt = ruta + url;
        String n = "Nombres=";
        String nombres = edtn.getText().toString();
        String a = "&Apellidos=";
        String apellidos = edtap.getText().toString();
        String u = "&Usuario=";
        String usuario = edtuser.getText().toString();
        String c = "&Clave=";
        String clave = edtclave.getText().toString();
        CrearUsuarios(rt,n,nombres,a,apellidos,u,usuario,c,clave,urlw);
    }

    public void snode(View view){
        String urlw = ":3001/api/product/GET";
        String url = ":3001/api/product/POST";
        String rt = ruta + url;
        String n = "nombres";
        String nombres = edtn.getText().toString();
        String a = "apellidos";
        String apellidos = edtap.getText().toString();
        String u = "usuario";
        String usuario = edtuser.getText().toString();
        String c = "clave";
        String clave = edtclave.getText().toString();
        CrearUsuarios(rt,n,nombres,a,apellidos,u,usuario,c,clave,urlw);
    }

    //SUPUESTAMENTE YA ESTA
    public void sjava(View view){
        String url = ":8080/api/users/post?";
        String urlw = ":8080/api/users";
        String rt = ruta + url;
        String n = "nombres=";
        String nombres = edtn.getText().toString();
        String a = "&apellidos=";
        String apellidos = edtap.getText().toString();
        String u = "&usuario=";
        String usuario = edtuser.getText().toString();
        String c = "&clave=";
        String clave = edtclave.getText().toString();
        CrearUsuario(rt,n,nombres,a,apellidos,u,usuario,c,clave, urlw);
    }

    public void limpiar(){
        edtn.setText("");
        edtap.setText("");
        edtuser.setText("");
        edtclave.setText("");
        edtn.requestFocus();
    }

}
