package br.ufrn.reuse.repository.local.config;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by danielsmith on 07/11/2017.
 */

public class Migracao {
    /**
     * Nome completo do arquivo;
     */
    private String nomeArquivo;

    /**
     * Versão do arquivo
     */
    private int versao;

    public Migracao(String nomeArquivo, int versao) {
        this.nomeArquivo = nomeArquivo;
        this.versao = versao;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Migracao migracao = (Migracao) o;

        return versao == migracao.versao;

    }

    @Override
    public int hashCode() {
        return versao;
    }


    /**
     * Aplica a migração na base de dados
     *
      * @param database
     */
    public void aplicar(SQLiteDatabase database) {
        database.execSQL(getSqlMigracao());
    }

    private String getSqlMigracao() {
        return null;
    }
}
