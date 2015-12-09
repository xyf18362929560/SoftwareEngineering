package nju.express.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import nju.express.util.ConnectionFactory;

public class BaseDataServiceImpl {
	public static ArrayList getList(Class cl) {
		ArrayList ar = new ArrayList();
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Field[] fields = cl.getDeclaredFields();
		ArrayList<String> col = getAllColumnsByRSM(cl.getSimpleName());

		// String sql = "select * from " + cl.getSimpleName();
		StringBuffer sb = new StringBuffer();
		sb.append(" select  ");

		for (int i = 0; i < col.size(); i++) {
			sb.append(" " + col.get(i) + " ");
			if (i != col.size() - 1) {
				sb.append(" , ");
			}
		}
		sb.append(" from ");
		sb.append(cl.getSimpleName());

		System.out.println(sb.toString());
		try {
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				Object ob = cl.newInstance();
				// for (Field field : fields) {
				// field.setAccessible(true);
				// field.set(ob, rs.getObject(field.getName()));
				// }
				for (String str : col) {
					for (Field field : fields) {
						if (str.equals(field.getName())) {
							field.setAccessible(true);
							field.set(ob, rs.getObject(field.getName()));
							break;
						}
					}
				}

				ar.add(ob);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeRes(conn, ps, rs);
		}
		return ar;
	}

	public  static ArrayList getListByCondition(Class cl, String columnname, Object value) {
		ArrayList ar = new ArrayList();
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Field[] fields = cl.getDeclaredFields();

		ArrayList<String> col = getAllColumnsByRSM(cl.getSimpleName());

		// String sql = "select * from " + cl.getSimpleName() +
		// " where " + columnname + " = '" + value + "'";
		StringBuffer sb = new StringBuffer();
		sb.append(" select  ");

		for (int i = 0; i < col.size(); i++) {
			sb.append(" " + col.get(i) + " ");
			if (i != col.size() - 1) {
				sb.append(" , ");
			}
		}
		sb.append(" from ");
		sb.append(cl.getSimpleName());
		sb.append(" where ");
		sb.append(columnname);
		sb.append("  = ?  ");
		try {
			ps = conn.prepareStatement(sb.toString());
			ps.setObject(1, value);
			rs = ps.executeQuery();
			while (rs.next()) {
				Object ob = cl.newInstance();
				for (String str : col) {
					for (Field field : fields) {
						if (str.equals(field.getName())) {
							field.setAccessible(true);
							field.set(ob, rs.getObject(field.getName()));
							break;
						}
					}
				}
				ar.add(ob);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeRes(conn, ps, rs);
		}
		return ar;
	}

	public static  Object getObById(Class cl, int id) {

		Connection conn = ConnectionFactory.getInstance().makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Field[] fields = cl.getDeclaredFields();
		ArrayList<String> col = getAllColumnsByRSM(cl.getSimpleName());

		// String sql = "select * from " + cl.getSimpleName() + " where " +
		// fields[0].getName() + " = " + id;

		StringBuffer sb = new StringBuffer();
		sb.append(" select  ");

		for (int i = 0; i < col.size(); i++) {
			sb.append(" " + col.get(i) + " ");
			if (i != col.size() - 1) {
				sb.append(" , ");
			}
		}
		sb.append(" from ");
		sb.append(cl.getSimpleName());
		sb.append(" where ");
		sb.append(col.get(0));
		sb.append(" =  ?  ");
		System.out.println(sb.toString());
		Object ob = null;

		try {
			ps = conn.prepareStatement(sb.toString());
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				ob = cl.newInstance();
				for (String str : col) {
					for (Field field : fields) {
						if (str.equals(field.getName())) {
							field.setAccessible(true);
							field.set(ob, rs.getObject(field.getName()));
							break;
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeRes(conn, ps, rs);
		}
		return ob;
	}

	public static  ArrayList getListByLike(Class cl, String columnname, Object value) {
		ArrayList ar = new ArrayList();
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Field[] fields = cl.getDeclaredFields();

		ArrayList<String> col = getAllColumnsByRSM(cl.getSimpleName());

		// String sql = "select * from " + cl.getSimpleName() +
		// " where " + columnname + " = '" + value + "'";
		StringBuffer sb = new StringBuffer();
		sb.append(" select  ");

		for (int i = 0; i < col.size(); i++) {
			sb.append(" " + col.get(i) + " ");
			if (i != col.size() - 1) {
				sb.append(" , ");
			}
		}
		sb.append(" from ");
		sb.append(cl.getSimpleName());
		sb.append(" where ");
		sb.append(columnname);
		sb.append("  like  '%");
		sb.append(value);
		sb.append("%'");

		System.out.println(sb.toString());
		try {
			ps = conn.prepareStatement(sb.toString());
			// ps.setObject(1, value);
			rs = ps.executeQuery();
			while (rs.next()) {
				Object ob = cl.newInstance();
				for (String str : col) {
					for (Field field : fields) {
						if (str.equals(field.getName())) {
							field.setAccessible(true);
							field.set(ob, rs.getObject(field.getName()));
							break;
						}
					}
				}
				ar.add(ob);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeRes(conn, ps, rs);
		}
		return ar;
	}

	/**
	 * 该方法用于所有的查询操作，注意保证表的数据除了主外键关联不要有重复列名 所以在列名前可以添加表名
	 * 
	 * @param cl
	 *            实体类
	 * @param sql
	 *            查询的sql语句
	 * @param obs
	 *            所有的条件值
	 * @return
	 */
	public static  ArrayList getListBySql(Class cl, String sql, Object[] obs) {

		ArrayList ar = new ArrayList();
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Field[] fields = cl.getDeclaredFields();

		ArrayList<String> col = new ArrayList<String>();

		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < obs.length; i++) {
				ps.setObject(i + 1, obs[i]);
			}

			rs = ps.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnName(i);
				col.add(columnName);

			}

			while (rs.next()) {
				Object ob = cl.newInstance();

				for (String str : col) {
					for (Field field : fields) {
						if (str.equals(field.getName())) {
							field.setAccessible(true);
							field.set(ob, rs.getObject(field.getName()));
							break;
						}
					}
				}

				ar.add(ob);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeRes(conn, ps, rs);
		}
		return ar;

	}

	public static  boolean insert(Object ob) {
		boolean b = false;
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		PreparedStatement ps = null;

		Class cl = ob.getClass();
		Field[] fields = cl.getDeclaredFields();
		ArrayList<String> col = getAllColumnsByRSM(cl.getSimpleName());

		StringBuffer sb = new StringBuffer();
		// String sql="insert into Animals (name,age,anid) values (?,?,?)";
		sb.append(" insert into " + cl.getSimpleName() + " ( ");
		for (int i = 1; i < col.size(); i++) {
			sb.append(col.get(i));
			if (i != col.size() - 1) {
				sb.append(" , ");
			}
		}
		sb.append(" ) values ( ");
		for (int i = 1; i < col.size(); i++) {
			sb.append(" ? ");
			if (i != col.size() - 1) {
				sb.append(" , ");
			}
		}
		sb.append(" ) ");
		System.out.println(sb.toString());
		try {
			ps = conn.prepareStatement(sb.toString());

			for (int i = 1; i < col.size(); i++) {
				for (Field field : fields) {
					if (col.get(i).equals(field.getName())) {
						fields[i].setAccessible(true);
						ps.setObject(i, fields[i].get(ob));
						break;
					}
				}
			}

			int a = ps.executeUpdate();
			if (a > 0) {
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeRes(conn, ps);
		}
		return b;
	}

	/**
	 * 插入并获得自增主键 只有插入才能使用
	 * 
	 * @param ob
	 * @return
	 */
	public static int insertGetGeneratedKey(Object ob) {
		int id = 0;
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Class cl = ob.getClass();
		Field[] fields = cl.getDeclaredFields();
		ArrayList<String> col = getAllColumnsByRSM(cl.getSimpleName());

		StringBuffer sb = new StringBuffer();
		// String sql="insert into Animals (name,age,anid) values (?,?,?)";
		sb.append(" insert into " + cl.getSimpleName() + " ( ");
		for (int i = 1; i < col.size(); i++) {
			sb.append(col.get(i));
			if (i != col.size() - 1) {
				sb.append(" , ");
			}
		}
		sb.append(" ) values ( ");
		for (int i = 1; i < col.size(); i++) {
			sb.append(" ? ");
			if (i != col.size() - 1) {
				sb.append(" , ");
			}
		}
		sb.append(" ) ");
		System.out.println(sb.toString());
		try {
			ps = conn.prepareStatement(sb.toString());

			for (int i = 1; i < col.size(); i++) {
				for (Field field : fields) {
					if (col.get(i).equals(field.getName())) {
						fields[i].setAccessible(true);
						ps.setObject(i, fields[i].get(ob));
						break;
					}
				}
			}

			int a = ps.executeUpdate();

			if (a > 0) {

				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getInt(1);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeRes(conn, ps);
		}
		return id;
	}

	public  static boolean update(Object ob) {
		boolean b = false;
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		PreparedStatement ps = null;
		Class cl = ob.getClass();
		Field[] fields = cl.getDeclaredFields();
		ArrayList<String> col = getAllColumnsByRSM(cl.getSimpleName());

		StringBuffer sb = new StringBuffer();

		// String sql = "update Animals set name=?,age=?,anid=? where id =?";
		sb.append(" update ");
		sb.append(cl.getSimpleName());
		sb.append(" set ");

		for (int i = 1; i < col.size(); i++) {
			sb.append(col.get(i));
			sb.append(" = ? ");
			if (i != col.size() - 1) {
				sb.append(" , ");
			}
		}
		sb.append(" where ");
		sb.append(col.get(0));
		sb.append(" = ? ");

		System.out.println(sb.toString());
		try {
			ps = conn.prepareStatement(sb.toString());
			for (int i = 1; i < col.size(); i++) {
				for (Field field : fields) {
					if (col.get(i).equals(field.getName())) {
						fields[i].setAccessible(true);
						ps.setObject(i, fields[i].get(ob));
						break;
					}
				}
			}

			// 主键需要是第一个属性
			fields[0].setAccessible(true);
			ps.setObject(col.size(), fields[0].get(ob));

			int a = ps.executeUpdate();
			if (a > 0) {
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeRes(conn, ps);
		}
		return b;
	}

	/**
	 * 通过表名获取数据库该表列名的集合 使用ResultSetMetaData获得查询出来的所有列名
	 * 
	 * @param tablename
	 * @return
	 */
	public static ArrayList<String> getAllColumnsByRSM(String tablename) {
		ArrayList<String> ar = new ArrayList<String>();
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " select * from " + tablename + " where 1=2 ";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnName(i);
				ar.add(columnName);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeRes(conn, ps, rs);
		}
		return ar;
	}

	/**
	 * 通过数据库元对象获取列名，只能获取单表列名
	 * 
	 * @param tablename
	 * @return
	 */
	public static ArrayList<String> getAllColumnsByDBM(String tablename) {
		ArrayList<String> ar = new ArrayList<String>();
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		ResultSet rs = null;
		try {
			DatabaseMetaData dbm = conn.getMetaData();
			rs = dbm.getColumns(null, "%", tablename, "%");
			while (rs.next()) {

				String columnName = rs.getString("COLUMN_NAME");

				// String columnType=rs.getString("TYPE_NAME");
				// int datasize=rs.getInt("DECIMAL_DIGITS");
				// int nullable=rs.getInt("NULLABLE");
				//
				// System.out.println("columnName: "+columnName);
				// System.out.println("columnType: "+columnType);
				// System.out.println("datasize: "+datasize);
				// System.out.println("nullable: "+nullable);

				ar.add(columnName);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeRes(conn, null, rs);
		}
		return ar;
	}

}
