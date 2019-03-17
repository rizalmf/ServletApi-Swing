package services.servlets;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import services.dao.Dao;
import services.models.Anggota;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @PKANE_NS
 */
public class GetListAnggotaServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            Dao dao = new Dao();
            PrintWriter out = response.getWriter();
            List<Anggota> anggotas = dao.getAnggotas();
            JSONArray rOb = new JSONArray();
            for (Anggota a: anggotas){
                JSONObject obj = new JSONObject();
                obj.put("id", a.getId());
                obj.put("nama", a.getNama());
                obj.put("nim", a.getNim());
                rOb.add(obj);
            }
            out.print(rOb.toJSONString());
        } catch (Exception e) { // jgn ditiru :D. harusnya Exception dipisahkan ya..
            Logger.getLogger(GetListAnggotaServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}