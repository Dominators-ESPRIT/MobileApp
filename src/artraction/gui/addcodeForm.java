/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.gui;

import artraction.entity.codepromo;
import artraction.services.codepromoService;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author zeyne
 */
public class addcodeForm extends BaseForm{
    Form current;
    public addcodeForm(Resources res){
        super("NewsFeed",BoxLayout.y());
        Toolbar tb= new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        setTitle("Ajout code promo");
        getContentPane().setScrollVisible(false);
        
        TextField label=new TextField("","Entrer Label");
        label.setUIID("TextFieldBlack");
        addStringValue("Label: ",label);
        
        TextField val=new TextField("","Entrer Valeur");
        val.setUIID("TextFieldBlack");
        addStringValue("Valeur: ",val);
        
        Button addbutt= new Button("Ajouter code promo");
        addStringValue("",addbutt);
        addbutt.addActionListener((e)->{
            try{
                if(label.getText()=="" || val.getText()=="")
                    Dialog.show("Champs vides!!","","Annuler","OK");
                else {
                        InfiniteProgress ip = new InfiniteProgress();
                        final Dialog id=ip.showInfiniteBlocking();
                        int valeur=Integer.parseInt(val.getText());
                        codepromo cp=new codepromo(String.valueOf(label.getText().toString()),valeur);
                       codepromoService.getInstance().ajoutercode(cp);
                       id.dispose();
                       new ListcodeForm(res).show();
                      refreshTheme();
                           System.out.println("data == "+cp);
                }
                  
            }catch(Exception ex){
                ex.printStackTrace();
                
            }
        });
         Button showbut= new Button("Afficher codes promo");
         addStringValue("",showbut);
         
        showbut.addActionListener((e)->{
            try{
                
                        InfiniteProgress ip = new InfiniteProgress();
                        final Dialog id=ip.showInfiniteBlocking();
                        
                       id.dispose();
                       new ListcodeForm(res).show();
                      refreshTheme();
                
                  
            }catch(Exception ex){
                ex.printStackTrace();
                
            }
        }
);
        
    }

    private void addStringValue(String s, Component v) {
            add(BorderLayout.west(new Label (s,"PaddedLabel"))
            .add(BorderLayout.CENTER,v));
            add(createLineSeparator(0xeeeeee));
            
    
    }
    
    
}
