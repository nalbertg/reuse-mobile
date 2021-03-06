package br.ufrn.reuse.facade;

import java.io.IOException;
import java.util.List;

import br.ufrn.reuse.dominio.anuncio.Anuncio;
import br.ufrn.reuse.dominio.anuncio.CategoriaAnuncio;
import br.ufrn.reuse.dominio.anuncio.Etiqueta;
import br.ufrn.reuse.dominio.anuncio.Interesse;
import br.ufrn.reuse.dominio.comum.Usuario;
import br.ufrn.reuse.dominio.patrimonio.Bem;

/**
 * Fachada da aplicação.
 *
 * @author Daniel
 * @author Nalbert
 */
public interface ReuseFacade {

    //TODO: PAGINAÇÃO, Async
    Anuncio cadastrar(Anuncio anuncio);
    Anuncio findAnunciobyId(Long id);
    List<Anuncio> findAllAnunciosPublicados();
    List<Anuncio> findAllAnuncios(Usuario usuario);
    List<Anuncio> findAllAnunciosPublicados(String textoBusca);
    List<Anuncio> findAllAnunciosPublicadosCategorias(List<CategoriaAnuncio> categoria);
    List<Interesse> findAllInteresse(Usuario usuario);
    Interesse demonstraInteresse(Usuario usuario, Anuncio anuncio);
    void removerInteresse(Interesse interesse);
    List<Anuncio> findAllAnuncios(CategoriaAnuncio categoria, String denominacaoBem, Integer numeroTombamento, List<Etiqueta> etiquetas);
    Bem findBemByNumTombamento(int tombamento);
    List<CategoriaAnuncio> findAllCategorias();
    Usuario autenticar(String usuario, String senha);

    boolean autenticar(String authorizationCode) throws IOException;

    abstract Usuario findUsuarioById(Long idUsuario);

    Usuario findUsuarioLogado();
}
