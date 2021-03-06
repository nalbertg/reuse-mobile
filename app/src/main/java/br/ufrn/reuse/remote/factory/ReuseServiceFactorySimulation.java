package br.ufrn.reuse.remote.factory;

import android.content.Context;

import br.ufrn.reuse.remote.anuncio.AnuncioRemoteService;
import br.ufrn.reuse.remote.anuncio.CategoriaRemoteService;
import br.ufrn.reuse.remote.anuncio.EtiquetaRemoteService;
import br.ufrn.reuse.remote.anuncio.FotoRemoteService;
import br.ufrn.reuse.remote.anuncio.HistoricoAnuncioRemoteService;
import br.ufrn.reuse.remote.anuncio.InteresseRemoteService;
import br.ufrn.reuse.remote.anuncio.StatusAnuncioRemoteService;

/**
 * Created by Daniel on 11/28/2017.
 */

public class ReuseServiceFactorySimulation implements ReuseServiceFactory {

    @Override
    public AnuncioRemoteService createAnuncioRemoteService(Context context) {
        return new AnuncioRemoteService();
    }

    @Override
    public CategoriaRemoteService createCategoriaRemoteService(Context context) {
        return new CategoriaRemoteService(context);
    }

    @Override
    public EtiquetaRemoteService createEtiquetaRemoteService(Context context) {
        return new EtiquetaRemoteService(context);
    }

    @Override
    public FotoRemoteService createFotoRemoteService(Context context) {
        return new FotoRemoteService(context);
    }

    @Override
    public HistoricoAnuncioRemoteService createHistoricoAnuncioRemoteService(Context context) {
        return new HistoricoAnuncioRemoteService(context);
    }

    @Override
    public InteresseRemoteService createInteresseAnuncioRemoteService(Context context) {
        return new InteresseRemoteService(context);
    }

    @Override
    public StatusAnuncioRemoteService createStatusAnuncioRemoteService(Context context) {
        return new StatusAnuncioRemoteService(context);
    }

}
