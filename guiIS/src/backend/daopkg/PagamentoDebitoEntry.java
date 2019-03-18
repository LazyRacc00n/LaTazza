package backend.daopkg;
import backend.Euro;
import java.util.Date;

public class PagamentoDebitoEntry {

    private String nomePersonale;
    private String cognomePersonale;
    private Date data;
    private Euro importo;

    public PagamentoDebitoEntry(String nomePersonale, String cognomePersonale, Date data, Euro importo) {
        this.nomePersonale = nomePersonale;
        this.cognomePersonale = cognomePersonale;
        this.data = data;
        this.importo = importo;
    }

    public String getNomePersonale() {
        return nomePersonale;
    }

    public void setNomePersonale(String nomePersonale) {
        this.nomePersonale = nomePersonale;
    }

    public String getCognomePersonale() {
        return cognomePersonale;
    }

    public void setCognomePersonale(String cognomePersonale) {
        this.cognomePersonale = cognomePersonale;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Euro getImporto() {
        return importo;
    }

    public void setImporto(Euro importo) {
        this.importo = importo;
    }


}
