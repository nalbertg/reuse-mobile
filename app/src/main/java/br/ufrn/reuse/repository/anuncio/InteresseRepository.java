package br.ufrn.reuse.repository.anuncio;

import java.util.List;

import br.ufrn.reuse.dominio.anuncio.Anuncio;
import br.ufrn.reuse.dominio.anuncio.Interesse;
import br.ufrn.reuse.dominio.comum.Usuario;

/**
 * Created by Daniel on 10/27/2017.
 */

public class InteresseRepository {
    public List<Interesse> findInteressesByIdAnuncio(Long id) {
        return null;
    }

    public List<Interesse> findAllInteresses(Usuario usuario) {
        return null;
    }

    public Interesse demonstrarInteresse(Usuario usuario, Anuncio anuncio) {
        return null;
    }

    public void removerInteresse(Interesse interesse) {

    }
}
