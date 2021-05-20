/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.services;
import artraction.entity.Oeuvre;
import artraction.entity.Panier;
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
public class PanierService {
    public static PanierService instance =null;
    private ConnectionRequest req;
    
    public static PanierService getInstance(){
        if(instance==null){
            instance= new PanierService();
        }
        return instance;
    }
    
    public PanierService(){
        req=new ConnectionRequest();
    }
    
    public void addtopanier(Oeuvre o){
        String url=Statics.BASE_URL+"/addtocart/{"+o.getId()+"}";
        req.setUrl(url);
        req.addResponseListener((e)->{
            String str= new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
                
        }
    
   
    public ArrayList<Oeuvre> afficheOeuvre(){
        ArrayList <Oeuvre> res= new ArrayList<>();
        String url=Statics.BASE_URL+"/panierr";
        req.setUrl(url);
                System.out.println("URL:"+url);

        System.out.println("REQ:"+req);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp= new JSONParser();
                try{
                    Map<String, Object> mapoeuv=jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> lom=(List<Map<String, Object>>) mapoeuv.get("root");
                    
                    for(Map<String, Object> ob: lom){
                        Oeuvre oeuv = new Oeuvre();
                        float id=Float.parseFloat(ob.get("id").toString());
                        double prix=Double.parseDouble(ob.get("prix").toString());
                        String nom=ob.get("nom").toString();
                        oeuv.setId((int)id);
                        oeuv.setPrix(prix);
                        oeuv.setNom(nom);
                        res.add(oeuv);
                                System.out.println("oeuvreee: "+nom);

                                
                    }
                }catch(Exception ex){
                    ex.printStackTrace();                }
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return res;
    }
    
    
            
}
