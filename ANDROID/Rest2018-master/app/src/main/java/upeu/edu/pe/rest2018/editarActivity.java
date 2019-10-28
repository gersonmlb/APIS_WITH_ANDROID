package upeu.edu.pe.rest2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class editarActivity extends AppCompatActivity {
    private Usuario usuario = new Usuario();
    Button PHP, java, node, python;
    private EditText edtn, edtap, edtuser, edtclave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        usuario = new Usuario(
                getIntent().getStringExtra("ID"),
                getIntent().getStringExtra("NAME"),
                getIntent().getStringExtra("APELLIDOS"),
                getIntent().getStringExtra("USUARIO"),
                getIntent().getStringExtra("CLAVE"));
        //Toast.makeText(this,usuario.toString(),Toast.LENGTH_LONG).show();

        setContentView(R.layout.activity_editar);

        edtn = (EditText) findViewById(R.id.nombres);
        edtap = (EditText) findViewById(R.id.apellidos);
        edtuser = (EditText) findViewById(R.id.user);
        edtclave = (EditText) findViewById(R.id.clave);

        PHP = (Button) findViewById(R.id.sphp);
        java = (Button) findViewById(R.id.sjava);
        node = (Button) findViewById(R.id.snode);
        python = (Button) findViewById(R.id.spython);
        edtn.requestFocus();

        edtn.setText(usuario.getNombres());
        edtap.setText(usuario.getApellidos());
        edtuser.setText(usuario.getUsuario());
        edtclave.setText(usuario.getClave());

    }

    public void datosUsuarios(String url,
                              final String idusu,
                              final String usi,
                              final String n,
                              final String nombres,
                              final String a,
                              final String apellidos,
                              final String u,
                              final String usuario,
                              final String c,
                              final String clave) {
        String parametros = idusu+usi+n+nombres + a+apellidos + u+usuario + c+clave;
        System.out.println(parametros);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Exitosa", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error: "+error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametres = new HashMap<String, String>();
                parametres.put(idusu,usi);
                parametres.put(n,nombres);
                parametres.put(a,apellidos);
                parametres.put(u,usuario);
                parametres.put(c,clave);
                return parametres;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void EliminarUsuarios(String url,
                              final String idusu,
                              final String usi) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Exitosa", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametres = new HashMap<String, String>();
                parametres.put(idusu, usi);
                return parametres;

            }

        };
    }

    String ruta = "http://192.168.56.82";

    public void Ephp(View view) {
        String url = "/APIS_WITH_ANDROID/PHP/editar.php";
        String rt = ruta + url;
        String idusu = "idusuario=";
        String id = this.usuario.getIdusuario();
        String n = "Nombres=";
        String nombres = edtn.getText().toString();
        String a = "Apellidos=";
        String apellidos = edtap.getText().toString();
        String u = "Usuario=";
        String usuario = edtuser.getText().toString();
        String c = "Clave=";
        String clave = edtclave.getText().toString();
        datosUsuarios(rt,idusu,id,n,nombres,a,apellidos,u,usuario,c,clave);
    }

    public void Epython(View view) {
        String url = ":5000/addUser/";
        String rt = ruta + url;
        String idusu = "idusuario=";
        String id = this.usuario.getIdusuario();
        String n = "Nombres=";
        String nombres = edtn.getText().toString();
        String a = "&Apellidos=";
        String apellidos = edtap.getText().toString();
        String u = "&Usuario=";
        String usuario = edtuser.getText().toString();
        String c = "&Clave=";
        String clave = edtclave.getText().toString();
        datosUsuarios(rt,idusu,id,n,nombres,a,apellidos,u,usuario,c,clave);
    }

    public void Enode(View view) {
        String url = ":3001/product/POST?";
        String rt = ruta + url;
        String idusu = "idusuario=";
        String id = this.usuario.getIdusuario();
        String n = "&nombres=";
        String nombres = edtn.getText().toString();
        String a = "&apellidos=";
        String apellidos = edtap.getText().toString();
        String u = "&usuario=";
        String usuario = edtuser.getText().toString();
        String c = "&clave=";
        String clave = edtclave.getText().toString();
        datosUsuarios(rt,idusu,id,n,nombres,a,apellidos,u,usuario,c,clave);
    }

    public void Ejava(View view) {
        String url = ":8080/api/users/put";
        String rt = ruta + url;
        String idusu = "idusuario=";
        String id = this.usuario.getIdusuario();
        String n = "&nombres=";
        String nombres = edtn.getText().toString();
        String a = "&apellidos=";
        String apellidos = edtap.getText().toString();
        String u = "&usuario=";
        String usuario = edtuser.getText().toString();
        String c = "&clave=";
        String clave = edtclave.getText().toString();
        datosUsuarios(rt,idusu,id,n,nombres,a,apellidos,u,usuario,c,clave);
    }
public void listar(String urlw){
        Intent intent = new Intent(getApplicationContext(), ListarActivity.class);
        String url = ruta + urlw;
        intent.putExtra("url",url);
        startActivity(intent);
    }
    public void ELjava(View view) {
        String url = ":8080/api/users/";
        String rt = ruta + url;
        String idusu = "idusuario=";
        String id = this.usuario.getIdusuario();
        EliminarUsuarios(rt, idusu, id);
    }

    public void ELnode(View view) {
        String url = ":3001/product/DELETE/";
        String rt = ruta + url;
        String idusu = "idusuario=";
        String id = this.usuario.getIdusuario();
        EliminarUsuarios(rt, idusu, id);
    }
    public void ELpython(View view) {
        String url = ":5000/deleteUser/";
        String rt = ruta + url;
        String idusu = "idusuario=";
        String id = this.usuario.getIdusuario();
        EliminarUsuarios(rt, idusu, id);
    }
    public void ELphp(View view) {
        String url = "/APIS_WITH_ANDROID/PHP/api.php/delete?";
        String rt = ruta + url;
        String idusu = "idusuario=";
        String id = this.usuario.getIdusuario();
        EliminarUsuarios(rt, idusu, id);
    }
}
