/*
 * PARG Desenvolvimento de Sistemas
 * Pablo Alexander - pablo@parg.com.br
 * 
 * Obtem um CEP no SearchCEP
 */
package functioncontroller;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe java para obter um CEP no SearchCEP
 *
 * @author Pablo Alexander da Rocha Gonçalves
 */
public class SearchCEP extends SearchCEPBase {

    // constantes
    public static final double VIACEP_VERSAO = 0.33;

    /**
     * Constrói uma nova classe
     */
    public SearchCEP() {
        super();
    }

    /**
     * Constrói uma nova classe
     *
     * @param events eventos para a classe
     */
    public SearchCEP(SearchCEPEvents events) {
        super();
        this.Events = events;
    }

    /**
     * Constrói uma nova classe e busca um CEP no ViaCEP
     *
     * @param events eventos para a classe
     * @param cep
     * @throws functioncontroller.SearchCEPException caso ocorra algum erro
     */
    public SearchCEP(String cep, SearchCEPEvents events) throws SearchCEPException {
        super();
        this.Events = events;
        this.buscar(cep);
    }

    /**
     * Constrói uma nova classe e busca um CEP no ViaCEP
     *
     * @param cep
     * @throws functioncontroller.SearchCEPException caso ocorra algum erro
     */
    public SearchCEP(String cep) throws SearchCEPException {
        super();
        this.buscar(cep);
    }

    /**
     * Busca um CEP no SearchCEP
     *
     * @param cep
     * @throws functioncontroller.SearchCEPException caso ocorra algum erro
     */
    @Override
    public final void buscar(String cep) throws SearchCEPException {
        // define o cep atual
        currentCEP = cep;

        // define a url
        String url = "http://viacep.com.br/ws/" + cep + "/json/";

        // define os dados
        JSONObject obj = null;
        try {
            obj = new JSONObject(getHttpGET(url));
        } catch (JSONException ex) {
            Logger.getLogger(SearchCEP.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!obj.has("erro")) {
            CEP novoCEP = null;
            try {
                novoCEP = new CEP(obj.getString("cep"),
                        obj.getString("logradouro"),
                        obj.getString("complemento"),
                        obj.getString("bairro"),
                        obj.getString("localidade"),
                        obj.getString("uf"),
                        obj.getString("ibge"),
                        obj.getString("gia"));
            } catch (JSONException ex) {
                Logger.getLogger(SearchCEP.class.getName()).log(Level.SEVERE, null, ex);
            }

            // insere o novo CEP
            CEPs.add(novoCEP);

            // atualiza o index
            index = CEPs.size() - 1;

            // verifica os Eventos
            if (Events instanceof SearchCEPEvents) {
                Events.onCEPSuccess(this);
            }
        } else {
            // verifica os Eventos
            if (Events instanceof SearchCEPEvents) {
                Events.onCEPError(currentCEP);
            }

            throw new SearchCEPException("NÃO FOI POSSÍVEL ENCONTRAR O CEP", cep, SearchCEPException.class.getName());
        }
    }
    
    /**
     * Busca um CEP usando um endereço
     *
     * @param cep classe CEP com uf, localidade, logradouro
     * @throws SearchCEPException
     */
    @Override
    public void buscarCEP(CEP cep) throws SearchCEPException {
        buscarCEP(cep.Uf, cep.Localidade, cep.Logradouro);
    }

    /**
     * Busca um CEP usando um endereço
     *
     * @param Uf Estado
     * @param Localidade Municipio
     * @param Logradouro Rua, Avenidade, Viela...
     * @throws SearchCEPException
     */
    @Override
    public void buscarCEP(String Uf, String Localidade, String Logradouro) throws SearchCEPException {
        // define o cep atual
        currentCEP = "?????-???";

        // define a url
        String url = "http://viacep.com.br/ws/" + Uf.toUpperCase() + "/" + Localidade + "/" + Logradouro + "/json/";

        // obtem a lista de CEP's
        JSONArray ceps = null;
        try {
            ceps = new JSONArray(getHttpGET(url));
        } catch (JSONException ex) {
            Logger.getLogger(SearchCEP.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (ceps.length() > 0) {
            for (int i = 0; i < ceps.length(); i++) {
                JSONObject obj = null;
                try {
                    obj = ceps.getJSONObject(i);
                } catch (JSONException ex) {
                    Logger.getLogger(SearchCEP.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (!obj.has("erro")) {
                    CEP novoCEP = null;
                    try {
                        novoCEP = new CEP(obj.getString("cep"),
                                obj.getString("logradouro"),
                                obj.getString("complemento"),
                                obj.getString("bairro"),
                                obj.getString("localidade"),
                                obj.getString("uf"),
                                obj.getString("ibge"),
                                obj.getString("gia"));
                    } catch (JSONException ex) {
                        Logger.getLogger(SearchCEP.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // insere o novo CEP
                    CEPs.add(novoCEP);

                    // atualiza o index
                    index = CEPs.size() - 1;

                    // verifica os Eventos
                    if (Events instanceof SearchCEPEvents) {
                        Events.onCEPSuccess(this);
                    }
                } else {
                    // verifica os Eventos
                    if (Events instanceof SearchCEPEvents) {
                        Events.onCEPError(currentCEP);
                    }

                    throw new SearchCEPException("NÃO FOI POSSÍVEL VALIDAR O CEP", currentCEP, SearchCEPException.class.getName());
                }
            }
        } else {
            throw new SearchCEPException("NENHUM CEP ENCONTRADO", currentCEP, getClass().getName());
        }
    }
}
