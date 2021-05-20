/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.gui;

import artraction.entity.Oeuvre;
import artraction.services.PanierService;
import artraction.services.codepromoService;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author zeyne
 */
public class PanierForm extends BaseForm {
     Form current;
    public PanierForm(Resources res){
      super("NewsFeed",BoxLayout.y());
        Toolbar tb= new Toolbar(true);
        current=this;
        Container cnt=new Container();
        setToolbar(tb);
        getTitleArea().setUIID("container");
        setTitle("Panier");
        getContentPane().setScrollVisible(false);
        Label titre= new Label("Mon Panier");
//        titre.setTextPosition(CENTER);
        ArrayList<Oeuvre> list=PanierService.getInstance().afficheOeuvre() ;
       
        for(Oeuvre o:list ){
            addButton(o.getNom(),o.getPrix(),o,res); 
            
        }
    
}

    private void addButton(String nom, Double prix, Oeuvre o, Resources res) {
         Container cnt= new Container();
        Label ta=new Label("Label: "+nom,"NewsTopline2");
        String prixx=String.valueOf(prix);
        Label tv=new Label("Prix: "+prixx+" DT","NewsTopline2");
       Label line=new Label("_______________________");
        cnt.add(BoxLayout.encloseY(
                BoxLayout.encloseX(ta,tv),
              
             
                BoxLayout.encloseX(line)));
        //cnt.add(BoxLayout.encloseY(tv));
        add(cnt);
    }
    }
