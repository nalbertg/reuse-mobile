package br.ufrn.reuse.remote.factory;

import android.content.Context;

import br.ufrn.reuse.remote.comum.UnidadeRemoteService;
import br.ufrn.reuse.remote.comum.UsuarioRemoteService;
import br.ufrn.reuse.remote.comum.impl.UnidadeRemoteServiceSimulacaoImpl;
import br.ufrn.reuse.remote.comum.impl.UsuarioRemoteServiceSimulacaoImpl;
import br.ufrn.reuse.remote.patrimonio.BemRemoteService;
import br.ufrn.reuse.remote.patrimonio.BemRemoteServiceSimulacao;

/**
 * Created by Daniel on 11/28/2017.
 */

public class APISinfoServiceFactorySimulated implements APISinfoServiceFactory {

    private static APISinfoServiceFactory instance;

    public static APISinfoServiceFactory getInstance() {
        if(instance==null){
            instance = new APISinfoServiceFactorySimulated();
        }
        return instance;
    }

    @Override
    public BemRemoteService createBemRemoteService(Context context) {
        return new BemRemoteServiceSimulacao(context);
    }

    @Override
    public UnidadeRemoteService createUnidadeRemoteService(Context context) {
        return new UnidadeRemoteServiceSimulacaoImpl(context);
    }

    @Override
    public UsuarioRemoteService createUsuarioRemoteService(Context context) {
        return new UsuarioRemoteServiceSimulacaoImpl(context);
    }
}
