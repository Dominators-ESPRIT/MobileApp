/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.gui;

import artraction.entity.codepromo;
import artraction.services.codepromoService;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author zeyne
 */
public class ListcodeForm extends BaseForm {
    Form current;
    public ListcodeForm(Resources res){
      super("NewsFeed",BoxLayout.y());
        Toolbar tb= new Toolbar(true);
        current=this;
        Container cnt=new Container();
        setToolbar(tb);
        getTitleArea().setUIID("container");
        setTitle("Liste des codes promo");
        getContentPane().setScrollVisible(false);
        Label titre= new Label("Liste des codes promos");
//        titre.setTextPosition(CENTER);
        ArrayList<codepromo> list= codepromoService.getInstance().affichecode();
       
        for(codepromo co:list ){
            addButton(co.getLabel(),co.getValeur(),co,res); 
            
        }
}

    private void addButton(String label, int valeur, codepromo co, Resources res) {
        //int height=Display.getInstance().convertToPixels(ll.5f);
        Container cnt= new Container();
        Label ta=new Label("Label: "+label,"NewsTopline2");
        String val=String.valueOf(valeur);
        Label tv=new Label("Valeur: "+val+"%","NewsTopline2");
       Label line=new Label("_______________________");
        
        Label Supp =new Label("");
        Supp.setUIID("NewsTopline");
        Style suppstyle=new Style(Supp.getUnselectedStyle());
        suppstyle.setFgColor(0xf21f1f);
        FontImage suppim= FontImage.createMaterial(FontImage.MATERIAL_DELETE,suppstyle);
        Supp.setIcon(suppim);
        Supp.setTextPosition(RIGHT);
        
        Supp.addPointerPressedListener(l->{
        Dialog dig=new Dialog("Suppression");
        if(dig.show("Supression","Vous voulez supprimer ce code?","Annuler","Oui")){
            dig.dispose();
        }else{
        dig.dispose();
        if(codepromoService.getInstance().deletecode(co.getId()))
           new ListcodeForm(res).show();
                System.out.println("del");
        }
    });
         Label mod =new Label("");
        Supp.setUIID("NewsTopline");
        Style modstyle=new Style(mod.getUnselectedStyle());
        suppstyle.setFgColor(0xf21f1f);
        FontImage modim= FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT,modstyle);
        mod.setIcon(modim);
        mod.setTextPosition(LEFT);
        mod.addPointerPressedListener(l->{
            //System.out.println("Update");
           new updatecodeForm(res,co).show();
        });
        
        cnt.add(BoxLayout.encloseY(
                BoxLayout.encloseX(ta),
                BoxLayout.encloseX(tv),
                BoxLayout.encloseX(Supp,mod),
                BoxLayout.encloseX(line)));
        //cnt.add(BoxLayout.encloseY(tv));
        add(cnt);
    }
}
