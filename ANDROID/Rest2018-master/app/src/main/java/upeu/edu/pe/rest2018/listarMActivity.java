package upeu.edu.pe.rest2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class listarMActivity extends AppCompatActivity {

    ListView list;
    Button crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_m);

        list = findViewById(R.id.lvdatos);
        crear = findViewById(R.id.crear);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Usuario usuario = (Usuario) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(),editarActivity.class);
                intent.putExtra("ID", String.valueOf(usuario.getIdusuario()));
                intent.putExtra("NAME", usuario.getNombres());
                intent.putExtra("APELLIDOS", usuario.getApellidos());
                intent.putExtra("CLAVE", usuario.getClave());
                intent.putExtra("USUARIO", usuario.getUsuario());
                intent.putExtra("ESTADO", usuario.getEstado());
                startActivity(intent);
            }
        });

        ListarUsuariosM(getIntent().getStringExtra("urlM"));
    }



    public void Crear(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

    public void ListarUsuariosM(String urlM){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlM, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    cargarLisviewM(getJsonM(new String(responseBody)));
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public void cargarLisviewM(ArrayList<Usuario> datos){
        list.setAdapter(null);
        ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>(this,android.R.layout.simple_list_item_1,datos);
        list.setAdapter(adapter);
    }
    public ArrayList<Usuario> getJsonM(String response){

        String id=(getIntent().getStringExtra("id"));

        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(response);
            for(int i=0; i<array.length();i++){
                Usuario usuario = new Usuario();
                usuario.setIdusuario(array.getJSONObject(i).getString( id));
                usuario.setNombres(array.getJSONObject(i).getString("nombres"));
                usuario.setApellidos(array.getJSONObject(i).getString("apellidos"));
                usuario.setClave(array.getJSONObject(i).getString("usuario"));
                usuario.setUsuario(array.getJSONObject(i).getString("clave"));
                usuario.setEstado(array.getJSONObject(i).getString("estado"));
                lista.add(usuario);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;

    }

}
