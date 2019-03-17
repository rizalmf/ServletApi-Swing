package services.servlets;

import org.json.simple.JSONObject;
import services.dao.Dao;
import services.models.Anggota;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @PKANE_NS
 */
public class SaveAnggotaServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try{
            Dao dao = new Dao();
            PrintWriter out = response.getWriter();
            String nama = request.getParameter("nama");
            String nim = request.getParameter("nim");
            Anggota anggota = new Anggota();
            anggota.setNama(nama);
            anggota.setNim(nim);
            dao.save(anggota);
            JSONObject obj = new JSONObject();
            obj.put("status", "sukses");
            obj.put("msg", anggota.getNama()+" tersimpan dalam database anggota");
            out.print(obj.toJSONString());
        }catch (Exception ex){
            Logger.getLogger(SaveAnggotaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}