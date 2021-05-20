/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.gui;

import artraction.entity.codepromo;
import artraction.services.codepromoService;
import com.codename1.components.FloatingHint;
import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author zeyne
 */
public class updatecodeForm extends BaseForm{
    Form current;
    public updatecodeForm(Resources res,codepromo c ){
         super("NewsFeed",BoxLayout.y());
        Toolbar tb= new Toolbar(true);
        current=this;
        Container cnt=new Container();
        setToolbar(tb);
        getTitleArea().setUIID("container");
        setTitle("Update code promo");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        TextField label=new TextField(c.getLabel());
         TextField valeur=new TextField(String.valueOf(c.getValeur()));
         label.setUIID("NewsTopLine");
         valeur.setUIID("NewsTopLine");
         label.setSingleLineTextArea(true);
         valeur.setSingleLineTextArea(true);
         Button modifier=new Button("Modifier");
         modifier.setUIID("button");
         modifier.addPointerPressedListener(l->{
             
             c.setLabel(label.getText());
            
             int val=Integer.parseInt(valeur.getText());
             c.setValeur(val);
             
             if(codepromoService.getInstance().updatecode(c))
             {  System.out.println("UPDARE DONE");
                // new ListcodeForm(res).show();
             }
            });
           Button ann=new Button("Annuler");
             ann.addActionListener(l->{
                 new ListcodeForm(res).show();
             });
             cnt =BoxLayout.encloseY(
                    new FloatingHint(label),
                    createLineSeparator(),
                    new FloatingHint(valeur),
                    createLineSeparator(),
                    modifier  ,
                     ann
                    
            );
            add(cnt);
            show();
                    
         

    }
    
}
