package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Posicao;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorgebavaresco@ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Named(value = "converterPosicao")
@RequestScoped
public class ConverterPosicao implements Serializable, Converter {

    @PersistenceContext(unitName = "PW-2021-1-WebPU")
    private EntityManager em;    
    
    // converter o que vem da tela para um objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Posicao.class, Integer.parseInt(string));
    }

    // converte o objeto que vem do banco em uma string para tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null){
            return null;
        }
        Posicao obj = (Posicao) t;
        return obj.getId().toString();
    }

}
