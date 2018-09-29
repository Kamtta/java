package bean;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action.equals("del")) {
			String url = "jdbc:mysql://localhost:3306/db_book";
			String username = "root";
			String password = "chenjingrong";
			Connection connection = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url, username, password);
				String sql = "delete from books where id=" + request.getParameter("id");
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();
				response.sendRedirect("bookList.jsp");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String action = request.getParameter("action");
		if (action != null) {
			String url = "jdbc:mysql://localhost:3306/db_book";
			String username = "root";
			String password = "chenjingrong";
			Connection connection = null;
			if (action.equals("add")) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection(url, username, password);
					String sql = "insert into books values(?,?,?,?,?,?)";
					PreparedStatement pstmt = connection.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(request.getParameter("id")));
					pstmt.setString(2, request.getParameter("name"));
					pstmt.setString(3, request.getParameter("author"));
					pstmt.setInt(4, Integer.parseInt(request.getParameter("price")));
					pstmt.setString(5, request.getParameter("publisher"));
					pstmt.setString(6, request.getParameter("comments"));
					pstmt.executeUpdate();
					pstmt.close();
					response.sendRedirect("bookList.jsp");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (action.equals("update")) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection(url, username, password);
					String sql = "update books set name=?,author=?,price=?,publisher=?,intro=? where id=?";
					PreparedStatement pstmt = connection.prepareStatement(sql);
					pstmt.setString(1, request.getParameter("name"));
					pstmt.setString(2, request.getParameter("author"));
					pstmt.setInt(3, Integer.parseInt(request.getParameter("price")));
					pstmt.setString(4, request.getParameter("publisher"));
					pstmt.setString(5, request.getParameter("comments"));
					pstmt.setInt(6, Integer.parseInt(request.getParameter("id")));
					pstmt.executeUpdate();
					pstmt.close();
					response.sendRedirect("bookList.jsp");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
