package br.ufrn.reuse.activity;

import android.app.Activity;
import android.content.SharedPreferences;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.ufrn.reuse.R;
import br.ufrn.reuse.dominio.comum.Usuario;
import br.ufrn.reuse.facade.ReuseFacadeImpl;

/**
 * Tela de login
 * @author Esther
 */
public class LoginActivity extends AbstractActivity{

    public String tombamento_g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        autenticarPorToken();

        //EditText usernameText = (Button) findViewById(R.id.username);
        String username = "Apuena"; //usernameText.getText().toString();

        Button entrarButton = (Button) findViewById(R.id.entrarButton);
        entrarButton.setOnClickListener(view -> {

            startActivity(new Intent(this, APISinfoLoginActivity.class));

//            Usuario usuarioLogado = new ReuseFacadeImpl(this).autenticar(null, null);
//            if (usuarioLogado.getId() >= 0){
//                //Caso ocorra autenticação, usuario logado é salvo para facilitar próximo acesso
//                salvarUltimoUsuarioLogado(username);
//                iniciarTimeline(usuarioLogado.getId(), null);
//            }
        });

    }

    //TODO: Olhar isso
    private void iniciarTimeline(Long usuario, Long unidade){
        Intent intent = new Intent(this, VitrineActivity.class);
        intent.putExtra("usuarioLogado", usuario);
        intent.putExtra("unidadeLogada", unidade);
        startActivity(intent);
    }

    //TODO: Olhar isso
    private void autenticarPorToken(){
        //Recupera preferencias para tentar autenticar por token
        SharedPreferences sp = getPreferences(Activity.MODE_PRIVATE);
        Boolean logarAuto = sp.getBoolean("logarAuto", false);

        if(logarAuto) {
            String usuario = sp.getString("ultimoLogado", "non");
            //logger.log(Level.SEVERE, ">>> "+usuario);
            Long unidade = sp.getLong("unidadeUltimoLogado", 0);
            String senha = sp.getString("pswd", "123");
            if (usuario != null && usuario != "" && senha != null && senha != "") {
                Usuario usuarioLogado = new ReuseFacadeImpl(this).autenticar(null, null);
                if (usuarioLogado.getId() >= 0) {
                    iniciarTimeline(usuarioLogado.getId(), unidade);
                    salvarUltimoUsuarioLogado(usuario);
                }
            }
        }
    }
}
