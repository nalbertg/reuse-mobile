package br.ufrn.reuse.facade;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufrn.reuse.dominio.anuncio.Anuncio;
import br.ufrn.reuse.dominio.anuncio.CategoriaAnuncio;
import br.ufrn.reuse.dominio.anuncio.Etiqueta;
import br.ufrn.reuse.dominio.anuncio.Interesse;
import br.ufrn.reuse.dominio.comum.Usuario;
import br.ufrn.reuse.dominio.patrimonio.Bem;
import br.ufrn.reuse.remote.anuncio.AnuncioRemoteService;
import br.ufrn.reuse.remote.auth.TokenRepository;
import br.ufrn.reuse.remote.rest.ApiConfig;
import ca.mimic.oauth2library.OAuth2Client;
import ca.mimic.oauth2library.OAuthResponse;

/**
 * Implementação da fachada da aplicação.
 *
 * @author Nalbert
 * @author Daniel
 */
public class ReuseFacadeImpl implements ReuseFacade {

    private final Context context;
    /**
     * Dependência do módulo de anúncios.
     */
    private AnuncioFacade anuncioFacade;

    /**
     * Dependência do módulo de interesses.
     */
    private InteresseFacade interesseFacade;

    /**
     * Dependência do módulo de patrimonio.
     */
    private PatrimonioFacade patrimonioFacade;

    /**
     * Dependência da parte comum dos sistemas.
     */
    private ComumFacade comumFacade;

    public ReuseFacadeImpl(Context context){
        this.context = context;
        this.anuncioFacade = new AnuncioFacadeImpl(context);
        this.interesseFacade = new InteresseFacade(context);
        this.patrimonioFacade = new PatrimonioFacade(context);
        this.comumFacade = new ComumFacade(context);
    }

    @Override
    public Anuncio cadastrar(Anuncio anuncio){
        return anuncioFacade.cadastrar(anuncio);
    }

    /**
     * Busca todos os anúncios publicados.
     *
     * @return todos os anúncios publicados
     */
    @Override
    public Anuncio findAnunciobyId(Long id) {
        return new AnuncioRemoteService().findById(id);
    }

    /**
     * Busca todos os anúncios publicados.
     *
     * @return todos os anúncios publicados
     */
    @Override
    public List<Anuncio> findAllAnunciosPublicados() {
        //List<Anuncio> anuncios = anuncioFacade.findAllAnunciosPublicados();
        return new AnuncioRemoteService().findAll(null);
    }

    /**
     * Busca todos os anúncios do usuário.
     *
     * @param usuario o usuário
     * @return os anúncios do usuário
     */
    @Override
    public List<Anuncio> findAllAnuncios(Usuario usuario) {
        return anuncioFacade.findAllAnuncios(usuario);
    }

    @Override
    public List<Anuncio> findAllAnunciosPublicados(String textoBusca) {
        return anuncioFacade.findAllAnunciosPublicados(textoBusca);
    }

    @Override
    public List<Anuncio> findAllAnunciosPublicadosCategorias(List<CategoriaAnuncio> categoria) {
        return anuncioFacade.findAllAnunciosPublicadosCategorias(categoria);
    }

    /**
     *
     * Busca todos os interesses do usuário
     *
     * @param usuario O usuário logado no sistema.
     * @return <code>List</code> contendo os interesses do usuário.
     */
    @Override
    public List<Interesse> findAllInteresse(Usuario usuario) {
        return interesseFacade.findAllInteresses(usuario);
    }

    /**
     *  Demonstra interesse em um anúncio
     *
     * @param usuario Usuário do interesado
     * @param anuncio anúncio que o usuário está interessado
     * @return interesse concretizado
     */
    @Override
    public Interesse demonstraInteresse(Usuario usuario, Anuncio anuncio){
        return interesseFacade.demonstrarInteresse(usuario, anuncio);
    }

    /**
     * Remove o interesse em um anúncio.
     *
     * @param interesse usuário
     *
     */
    @Override
    public void removerInteresse(Interesse interesse){
        interesseFacade.removerInteresse(interesse);
    }

    @Override
    public List<Anuncio> findAllAnuncios(CategoriaAnuncio categoria, String denominacaoBem, Integer numeroTombamento, List<Etiqueta> etiquetas){
        return anuncioFacade.findAllAnuncios(categoria,denominacaoBem,numeroTombamento,etiquetas);
    }

    @Override
    public Bem findBemByNumTombamento(int tombamento) {
        return patrimonioFacade.findByTombamento(tombamento);
    }

    @Override
    public List<CategoriaAnuncio> findAllCategorias() {
        return anuncioFacade.findAllCategorias();
    }

    public List<Etiqueta> findAllEtiquetas(){
        return anuncioFacade.findAllEtiquetas();
    }

    /**
     * Busca se as credenciais informadas são válidas.
     * @return sucesso no login
     */
    @Override
    public Usuario autenticar(String usuario, String senha) {
        return comumFacade.autenticar(usuario, senha); //new ComumRemoteService().credenciaisValidas(usuario, senha);
    }

    @Override
    public boolean autenticar(String authorizationCode) throws IOException {

        Map<String, String> map = new HashMap<>();
        map.put(ApiConfig.REDIRECT_URI_PARAM, ApiConfig.REDIRECT_URI);
        map.put(ApiConfig.RESPONSE_TYPE_VALUE, authorizationCode);

        OAuthResponse response = new OAuth2Client.Builder(ApiConfig.getClientId(), ApiConfig.getClientSecret(), ApiConfig.ACCESS_TOKEN_URL)
                .grantType(ApiConfig.getGrantType())
                .parameters(map)
                .build()
                .requestAccessToken();

        if (response != null) {
            if(response.isSuccessful()){

                TokenRepository tokenRepository = TokenRepository.createTokenRepository(this.context);

                tokenRepository.putToken(response.getAccessToken());
                tokenRepository.putAuthorizationCode(authorizationCode);

                return true;
            }
        }

        return false;
    }

    @Override
    public Usuario findUsuarioById(Long idUsuario) {
        return comumFacade.findUsuarioById(idUsuario);
    }

    @Override
    public Usuario findUsuarioLogado(){
        return comumFacade.findUsuarioLogado();
    }

}