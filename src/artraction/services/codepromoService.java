/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.services;

import artraction.entity.codepromo;
import artraction.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zeyne
 */
public class codepromoService {
      public static codepromoService instance =null;
      public static boolean ok=true;

    public static codepromoService getInstance() {
   if(instance==null){
            instance= new codepromoService();
        }
        return instance;    }
    private ConnectionRequest req;
    
  
    
     public codepromoService(){
        req=new ConnectionRequest();
    }
     
     public void ajoutercode(codepromo c){
        String url=Statics.BASE_URL+"/add?label="+c.getLabel()+"&valeur="+c.getValeur();
        req.setUrl(url);
        req.addResponseListener((e)->{
            String str= new String(req.getResponseData());
           // System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
                
        }
     
      public ArrayList<codepromo> affichecode(){
        ArrayList <codepromo> res= new ArrayList<>();
        String url=Statics.BASE_URL+"/codepromojson";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>()
        {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp= new JSONParser();
                try{
                    Map<String, Object> mapoeuv=jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> lom=(List<Map<String, Object>>) mapoeuv.get("root");
                    
                    for(Map<String, Object> ob: lom){
                        codepromo code = new codepromo();
                        float id=Float.parseFloat(ob.get("id").toString());
                        float val=Float.parseFloat(ob.get("valeur").toString());
                     
                        System.out.println("valeeeeeeeeeeeeur: "+val);
                        String label=ob.get("label").toString();
                        code.setId((int)id);
                        code.setLabel(label);
                        code.setValeur((int)val);
                        res.add(code);
                    }
                }catch(Exception ex){
                    ex.printStackTrace();                }
                }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return res;
    }
      
      public boolean deletecode(int id){
          String url=Statics.BASE_URL+"/Supp/"+id;
        req.setUrl(url);
         
        req.addResponseListener(new ActionListener<NetworkEvent>() {
              @Override
              public void actionPerformed(NetworkEvent evt) {
                  req.removeResponseCodeListener(this);
              }
          });
                NetworkManager.getInstance().addToQueueAndWait(req);
                return ok;

      }
      
      public boolean updatecode(codepromo cp){
          String url=Statics.BASE_URL+"/update?id="+cp.getId()+"&label="+cp.getLabel()+"&valeur="+cp.getValeur()+"";
        req.setUrl(url);
        System.out.println("URLL: "+url);
         String str= new String(req.getResponseData());
           System.out.println("data == "+str);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
              @Override
              public void actionPerformed(NetworkEvent evt) {
                  ok=req.getResponseCode()==200;
                  req.removeResponseListener(this);
                  System.out.println("OKK: "+ok);
              }
            
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
                return ok;
      }
    
}
    