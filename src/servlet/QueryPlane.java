package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PlaneDao;
import information.Plane;

/**
 * Servlet implementation class QueryPlane
 */
@WebServlet("/QueryPlane")
public class QueryPlane extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String action ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryPlane() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		if (request.getParameter("action")!=null) {
			this.action=request.getParameter("action");
			if (action.equals("showall")) {
				List<Plane> planes=queryAll();
				request.setAttribute("planeList", planes);
				request.getRequestDispatcher("Queryplane.jsp").forward(request,response);
			}
			if (action.equals("show")) {
				String select=request.getParameter("sel_city");
				System.out.println(select);
				if (select.equals("100")) {
					
				}
				if (select.equals("0")) {
					String startcity=request.getParameter("sta_start");
					String arrivecity=request.getParameter("sta_arrive");
					List<Plane> planes=queryById(startcity, arrivecity);
					request.setAttribute("planeList", planes);
					request.getRequestDispatcher("Queryplane.jsp").forward(request, response);
				}
			}
		}
	}
	public List<Plane> queryAll(){
		PlaneDao dao=new PlaneDao();
		List<Plane> list=dao.queryAll();
		return list;
	}
	public List<Plane> queryById(String startcity,String arrivecity){
		PlaneDao dao=new PlaneDao();
		List<Plane> list=dao.querybyId(startcity, arrivecity);
		return list;
	}

}
