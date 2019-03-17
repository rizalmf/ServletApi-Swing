/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectanggota.service;

import java.util.List;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import projectanggota.model.Anggota;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author PKane_NS
 * ini class untuk consume API tadi
 * contoh : http://localhost:8080/anggota/save?nama=
 * ada kata anggota disana berarti API td sudah dibuild dan dideploy ke tomcat folder webapps dengan nama anggota 
 * kalau belum di build trus lngsung di run dari IDE cukup http://localhost:8080/save?nama= saja :D
 */
public class AnggotaClient {
    private Gson gson;
    private HttpResponse response;
    private HttpClient client;
    private HttpGet request;

    private void setRequest(String set, String key1, String key2){
        switch (set){
            case "save" : client = new DefaultHttpClient();
                    request = new HttpGet("http://localhost:8080/anggota/save?nama="+key1+"&nim="+key2);
                    break;
            case "delete" : client = new DefaultHttpClient();
                    request = new HttpGet("http://localhost:8080/anggota/delete?id="+key1);
                    break;
            case "list_kepo" : client = new DefaultHttpClient();
                    request = new HttpGet("http://localhost:8080/anggota/get_list_by_kepo?kepo="+key1);
                    break;
            case "list" : client = new DefaultHttpClient();
                    request = new HttpGet("http://localhost:8080/anggota/get_list");
                    break;
        }
    }
    public List<Anggota> getAnggotas(){
        try {
            setRequest("list", null, null);
            String json = parsing();
            System.out.println(json);
            gson = new Gson();
            List<Anggota> anggotas = gson.fromJson(json, new TypeToken<List<Anggota>>(){}.getType());
            return anggotas;
        } catch (Exception e) {
            Logger.getLogger(AnggotaClient.class.getName()).log(Level.SEVERE, null, e);
        }
        return new ArrayList<>();
    }
    public List<Anggota> getAnggotasByKepo(String kepo){
        try {
            setRequest("list_kepo", kepo, null);
            String json = parsing();
            gson = new Gson();
            List<Anggota> anggotas = gson.fromJson(json, new TypeToken<List<Anggota>>(){}.getType());
            return anggotas;
        } catch (Exception e) {
            Logger.getLogger(AnggotaClient.class.getName()).log(Level.SEVERE, null, e);
        }
        return new ArrayList<>();
    }
    public String saveAnggota(String nama, String nim){
        String returnString= "Gk ada koneksi :D";
        try {
            setRequest("save", nama, nim);
            String json = parsing();
            Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>(){}.getType());
            String status = (String) map.get("status");
            if (status.equals("sukses")) {
                returnString = (String) map.get("msg");
            }
        } catch (Exception e) {
            Logger.getLogger(AnggotaClient.class.getName()).log(Level.SEVERE, null, e);
        }
        return returnString;
    }
    public String deleteAnggota(long id){
        String returnString= "Gk ada koneksi :D";
        try {
            setRequest("delete", String.valueOf(id), null);
            String json = parsing();
            Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>(){}.getType());
            returnString = (String) map.get("msg");
        } catch (Exception e) {
            Logger.getLogger(AnggotaClient.class.getName()).log(Level.SEVERE, null, e);
        }
        return returnString;
    }
    private String parsing() throws IOException{
        response = client.execute(request);
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = null;
        line = br.readLine();
        return line;
    }
}
