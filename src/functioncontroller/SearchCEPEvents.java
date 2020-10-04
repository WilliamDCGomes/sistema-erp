/*
 * PARG Desenvolvimento de Sistemas
 * Pablo Alexander - pablo@parg.com.br
 * 
 * Obtem um CEP no SearchCEP
 */
package functioncontroller;

/**
 * Interface para os eventos
 *
 * @author Pablo Alexander da Rocha Gonçalves
 */
public interface SearchCEPEvents {
    /**
     * Quando o CEP for encontrado com sucesso
     * @param cep retorna o objeto SearchCEP
     */
    public void onCEPSuccess(SearchCEP cep);
    
    /**
     * Quando ocorrer qualquer erro ao encontrar o CEP
     * @param cep retorna o CEP que foi requisitado
     */
    public void onCEPError(String cep);
}
