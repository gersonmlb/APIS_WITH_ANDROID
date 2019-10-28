package upeu.edu.pe.rest2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button php, java, node, python;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        java = findViewById(R.id.java);
        php = findViewById(R.id.php);
        node = findViewById(R.id.node);
        python = findViewById(R.id.python);


    }
    String ruta = "http://192.168.43.201";

    public void listphp(View view){
        String url = "/APIS_WITH_ANDROID/PHP/api.php/GET";
        String rt = ruta + url;
        Intent intent = new Intent(getApplicationContext(), ListarActivity.class);
        intent.putExtra("url",rt);
        startActivity(intent);
    }

    public void listpython(View view){
        String url = ":5000/getAll/";
        String rt = ruta + url;
        Intent intent = new Intent(getApplicationContext(), ListarActivity.class);
        intent.putExtra("url",rt);
        startActivity(intent);
    }

    String id;
    String nombre;
    String apellidos;
    String usuarios;
    String clave;
    String estado;
    public void listnode(View view){
        String url = ":3001/api/product/GET";
        String rtM = ruta + url;
        Intent intent = new Intent(getApplicationContext(), listarMActivity.class);
        intent.putExtra("urlM",rtM);
        intent.putExtra("id",id= "_id");
        startActivity(intent);
    }

    public void listjava(View view){
        String url = ":8080/api/users";
        String rtM = ruta + url;
        Intent intent = new Intent(getApplicationContext(), listarMActivity.class);
        intent.putExtra("urlM",rtM);
        intent.putExtra("id",id = "id");
        startActivity(intent);
    }
}
