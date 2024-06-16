package br.edu.fateczl.salaodebeleza;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            loadFragment(bundle);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment, new HomeFragment());
            fragmentTransaction.commit();
        }
    }

    private void loadFragment(Bundle bundle){
        String tipo = bundle.getString("tipo");

        assert tipo != null;
        Fragment fragment;
        if (tipo.equals("cliente")){
            fragment = new CustomerFragment();
        } else if (tipo.equals("vip")) {
            fragment = new VipFragment();
        } else if (tipo.equals("servico")) {
            fragment = new ServiceFragment();
        } else {
            fragment = new SchedulingFragment();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, MainActivity.class);

        if (id == R.id.item_cliente){
            bundle.putString("tipo", "cliente");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        } else if (id == R.id.item_vip){
            bundle.putString("tipo", "vip");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        } else if (id == R.id.item_servico) {
            bundle.putString("tipo", "servico");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        } else {
            bundle.putString("tipo", "agendamento");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        }
        //    return super.onOptionsItemSelected(item);
    }
}