package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Time;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorgebavaresco@ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class TimeDAO<TIPO> extends DAOGenerico<Time> implements Serializable {
    
    public TimeDAO(){
        super();
        classePersistente = Time.class;
        // definição da lista de ordenações
        listaOrdem.add(new Ordem("id","ID", "="));
        listaOrdem.add(new Ordem("nome","Nome", "like"));
        listaOrdem.add(new Ordem("cidade.nome","Cidade", "like"));
        // definição da ordem atual
        ordemAtual = listaOrdem.get(1); // vai pegar o segundo da lista de ordens
        // criando uma instancia do conversor
        converterOrdem = new ConverterOrdem();
        // associando a lista de ordens ao conversor
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
    @Override
    public Time localizar(Object id) throws Exception {
        Time objeto = em.find(Time.class, id);
        // Deve-se inicializar a coleção ou coleçoes do objeto para não
        // dar um erro de lazy inicialization exception
        objeto.getJogadores().size();        
        return objeto;
    }
    
    public List<Time> getListaCompleta(){
        String jpql = "select distinct t from Time t left join fetch t.jogadores order by t.id";
        return em.createQuery(jpql).getResultList();        
    }
        

}
