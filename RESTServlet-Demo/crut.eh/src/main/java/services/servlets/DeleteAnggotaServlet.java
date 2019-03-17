package services.servlets;

import org.json.simple.JSONObject;
import services.dao.Dao;
import services.models.Anggota;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @PKANE_NS
 */
public class DeleteAnggotaServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        JSONObject obj = new JSONObject();
        PrintWriter out = null;
        Anggota anggota = new Anggota();
        try{
            Dao dao = new Dao();
            int id = Integer.parseInt(request.getParameter("id"));
            out = response.getWriter();
            anggota = dao.getAnggota(id);
            obj.put("status", "sukses");
            obj.put("msg", "bye "+anggota.getNama()+" :D");
            dao.delete(anggota);
            out.print(obj);
        } catch (Exception ex){
            Logger.getLogger(DeleteAnggotaServlet.class.getName()).log(Level.SEVERE, null, ex);
            obj.put("status", "gagal");
            obj.put("msg", "parameter yang dimasukkan salah :D");
            try {
                out = response.getWriter();
                out.print(obj);
            } catch (IOException e) {
            }
        }
    }

}