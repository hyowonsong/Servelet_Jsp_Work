package kosta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kosta.dto.MemberDTO;
import kosta.util.DbUtil;

public class MemberDAOImpl implements MemberDAO{

	@Override
	public List<MemberDTO> selectAll() throws SQLException{
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MemberDTO> Member = new ArrayList<>();
        String sql = "SELECT * FROM member";
        try {
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	Member.add(new MemberDTO(
            			rs.getString("id"),
            			rs.getString("pwd"),
            			rs.getString("name"),
            			rs.getInt("age"),
            			rs.getString("phone"),
            			rs.getString("addr"),
            			rs.getString("join_date")                
                ));
            }
        } catch (SQLException e) {
			e.printStackTrace();
        } finally {
            DbUtil.dbClose(rs, pstmt, conn);
        }
        return Member;
	}

	@Override
	public int insert(MemberDTO memberDTO) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = 
        		"insert into member(id,pwd,name,age,phone,addr, join_date) values(?,?,?,?,?,?, now())";
        try {
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberDTO.getId());
            pstmt.setString(2, memberDTO.getPwd());
            pstmt.setString(3, memberDTO.getName());
            pstmt.setInt(4, memberDTO.getAge());
            pstmt.setString(5, memberDTO.getPhone());
            pstmt.setString(6, memberDTO.getAddr());
            result =  pstmt.executeUpdate();
        } catch (SQLException e) {
			e.printStackTrace();
        } finally {
            DbUtil.dbClose(pstmt, conn);
        }
        return result;
	}

	@Override
	public MemberDTO getSelectById(String id) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberDTO dto = null;
        String sql = "SELECT * FROM member WHERE id = ?";
        try {
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
            	dto = new MemberDTO(
            			rs.getString("id"),
            			rs.getString("pwd"),
            			rs.getString("name"),
            			rs.getInt("age"),
            			rs.getString("phone"),
            			rs.getString("addr"),
            			rs.getString("join_date")  
                );
            }
        } catch (SQLException e) {
			e.printStackTrace();
        } finally {
            DbUtil.dbClose(rs,pstmt,conn);
        }
        return dto;
	}


	@Override
	public int delete(String id) {
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		String sql="DELETE FROM MEMBER WHERE ID = ? ";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			//?가 있다면 개수만큼 setXxx()필요
			ps.setString(1, id);
            result = ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(ps,con);
		}
		return result;
	}

	@Override
	public int update(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberDTO> findBykeyFieldWord(String keyField, String keyWord) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		String sql="SELECT * FROM MEMBER WHERE ";
		
		//sql ="SELECT * FROM MEMBER WHERE 1=1 -- like ?";   // keyField =  1=1 --
		
		try {
			switch(keyField) {
			   case "id": sql+="ID LIKE ?"; break;
			   case "name": sql+="name LIKE ?"; break;
			   case "addr": sql+="addr LIKE ?"; break;
			   default:
			        System.out.println("유효하지 않은 keyField: " + keyField);
			        return list;
			}
			System.out.println("sql = " + sql);
			
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			//?가 있다면 개수만큼 setXxx()필요
			ps.setString(1, "%"+keyWord+"%");
			
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberDTO member = new MemberDTO(rs.getString(1), rs.getString(2), 
				   rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7));
				
				list.add(member);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(rs,ps,con);
		}
		
		return list;
	}
}
