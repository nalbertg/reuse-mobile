package br.ufrn.reuse.repository.comum;

import android.content.Context;

import br.ufrn.reuse.dominio.comum.Usuario;
import br.ufrn.reuse.remote.comum.UsuarioRemoteService;

/**
 * Created by Daniel on 10/27/2017.
 */

public class UsuarioRepository {

    private final Context context;
    private final UsuarioRemoteService remoteService;

    public UsuarioRepository(Context context) {
        this.context = context;
        this.remoteService = new UsuarioRemoteService(context);
    }

    public Usuario findUsuarioById(Long id) {
        return remoteService.findUsuarioById(id);
    }
}
