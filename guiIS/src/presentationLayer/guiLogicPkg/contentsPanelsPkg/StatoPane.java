package presentationLayer.guiLogicPkg.contentsPanelsPkg;

import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import presentationLayer.guiConfig.contentsPanelsPropertiesPkg.StatoPaneProperties;
import utils.MyJLabel;
import javax.swing.*;
import java.util.List;
import static presentationLayer.guiConfig.contentsPanelsPropertiesPkg.StatoPaneProperties.*;


public class StatoPane extends AbstractPanel {

    private MyJLabel labelDebitiPersonale;
    private MyJLabel labelCassa;
    private MyJLabel labelTitolo;
    private MyJLabel labelMagazzino;
    private MyJLabel labelSaldo;
    private JPanel panelMagazzino;
    private JPanel panelCassa1;
    private JPanel panelDebiti1;
    private JTextArea debitiPersonaleTextArea;
    private JScrollPane scrollPane;

    /**TEMPORANEI**///todo da eliminare
    private String[] tipoCialde = new String[]{"Arabica","Decaffeinato","Espresso","Thè","Thè limone","Cioccolata","Camomilla"};
    private String[] debitiPersonaleS = new String[]{"Gabriele Armanino 40","Jacopo Dapueto 80","Simone Campisi 30","Andrea Straforini 50"};
    /**----------**/

    public StatoPane() {

        //inizializza tutti i campi necessari
		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);
		StatoPaneProperties.initStatoPanel(this);
        add(labelTitolo);
        add(panelMagazzino=StatoPaneProperties.createAndInitPanelMagazzino());
        panelMagazzino.add(labelMagazzino=StatoPaneProperties.createAndInitLabelMagazzino());
		add(panelCassa1=StatoPaneProperties.createAndInitPanelCassa1());
        panelCassa1.add(labelCassa=StatoPaneProperties.createAndInitLabelCassa());
        add(labelSaldo=StatoPaneProperties.createAndInitLabelSaldo());
        add(panelDebiti1=StatoPaneProperties.createAndInitPanelDebiti1());
        panelDebiti1.add(labelDebitiPersonale=StatoPaneProperties.createAndInitLabelDebitiPersonale());
        debitiPersonaleTextArea=StatoPaneProperties.createAndInitDebitiPersonaleTextArea();
        add(scrollPane=createAndInitScrollPane(debitiPersonaleTextArea));


        //todo da eliminare
        for (String s : debitiPersonaleS)
            debitiPersonaleTextArea.append("\n "+s+"\n");
        int i=0;
        for (String s : tipoCialde)
        {
            MyJLabel lb= new MyJLabel(s+": ",DEFAULT_FONT_DESCRIZIONI2,
                    DEFAULTX_COLONNA1,DEFAULTY_RIGA1+DEFAULT_HEIGHT_LABELTITOLO+DEFAULT_GAP_LABEL*i++,DEFAULT_WIDTH_LABELDESCRIZIONE,DEFAULT_HEIGHT_LABELDESCRIZIONE,null);
            add(lb);
        }

        //fino qui
	}


    public void setCialdeList(List<CialdeEntry> listaCialde){
        int i=0;
        for (CialdeEntry s : listaCialde)
        {
            add(StatoPaneProperties.createAndInitJLabelCialda(s.getTipo(),i++));
        }
    }

    public void setDebitiPersonaleTextArea(List<Personale> listaPersonale){
        for(Personale i:listaPersonale){
            debitiPersonaleTextArea.append(
                    i.getNome()+
                            " "+
                            i.getCognome()+
                            " "+
                            i.getImportoDebito().getEuro()+
                                    ","+
                            i.getImportoDebito().getCentesimi()
            );
        }
    }


    public void setLabelTitolo(MyJLabel labelTitolo) {
        this.labelTitolo = labelTitolo;
    }



}
