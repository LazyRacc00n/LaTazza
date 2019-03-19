package backend.daopkg.rowdatapkg;

import java.util.Date;

public class RifornimentoEntry {
    private Date data;
    private String tipoCialda;
    private int qta;

    public RifornimentoEntry(Date data, int qta, String tipoCialda) {
        this.data = data;
        this.qta = qta;
        this.tipoCialda = tipoCialda;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public String getTipoCialda() {
        return tipoCialda;
    }

    public void setTipoCialda(String tipoCialda) {
        this.tipoCialda = tipoCialda;
    }

    @Override
    public String toString() {
        return "RifornimentoEntry: data:"+data.toString()+" tipoCialda:"+tipoCialda+" qta:"+qta;
    }


}
