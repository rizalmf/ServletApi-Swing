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
public class GetAnggotaByKepoServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try{
            PrintWriter out = response.getWriter();
            Dao dao = new Dao();
            String kepo = request.getParameter("kepo");
            List<Anggota> anggotas = dao.getAnggotaByKepo(kepo);
            JSONArray rOb = new JSONArray();
            for (Anggota a: anggotas){
                JSONObject obj = new JSONObject();
                obj.put("id", a.getId());
                obj.put("nama", a.getNama());
                obj.put("nim", a.getNim());
                rOb.add(obj);
            }
            out.print(rOb.toJSONString());
        } catch (Exception ex){
            Logger.getLogger(GetAnggotaByKepoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}