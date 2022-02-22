package sist.com.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;

import sist.com.vo.UsersVO;

import javax.naming.*;

public class UsersDAO {
   private Connection conn;
   private PreparedStatement ps;
   
   // 1. 미리 생성된 Connection주소를 얻어온다 (톰캣 => 10개 Connection)
   // ==> 저장공간의 이름을 POOL ==> ConnectionPool
   public void getConnection()
   {
       try
       {
           Context init=new InitialContext();
           // JNDI => 디렉토리 형식 (탐색기) 
           Context c=(Context)init.lookup("java://comp/env");
           // java://comp/env => Connection객체가 저장됨 
           // jdbc/oracle => 저장된 주소의 별칭 
           DataSource ds=(DataSource)c.lookup("jdbc/oracle");
           // DataSource => 데이터베이스에 대한 모든 정보가 저장된 클래스 
           conn=ds.getConnection();
       }catch(Exception ex) 
       {
           ex.printStackTrace();
       }
   }
   // 2. 사용후에 반환 
   public void disConnection()
   {
       try
       {
           if(ps!=null) ps.close();
           if(conn!=null) conn.close(); 
           // 반환 => POOL영역에 설정 => 재사용 
           // Connection객체의 갯수를 조정할 수 있다 
           // 웹에서 일반적으로 사용되는 데이터베이스 프로그램 
           // => ORM (MyBatis => DBCP)
       }catch(Exception ex){}
   }
   // 기능 (로그인 처리) => 결과값이 3개 => int , String(가독성) 
   //                   결과값이 2개 => boolean 
   public UsersVO isLogin(String id,String pwd)
   {
       UsersVO result=null;
       try
       {
           // 1. 미리 연결된 Connection주소 얻어오기 
           getConnection();
           // 2. SQL문장 => id가 존재하는지 확인 
           String sql="SELECT * "
                     +"FROM users_1 "
                     +"WHERE u_id=?";
           // 3. 오라클로 전송 (SQL)
           ps=conn.prepareStatement(sql);
           // 4. ?에 값을 채운다 
           ps.setString(1, id);
           // 5. 실행요청 => 결과값 얻기 
           ResultSet rs=ps.executeQuery();
           boolean isExist=rs.next();
          
           rs.close();
           if(isExist) //Id가 없는 상태
           {
                   String password=rs.getString(2);
                   
                 
                   if(password.equals(pwd))
                   {
                       UsersVO usersVo=new UsersVO();
                       usersVo.setU_id(rs.getString(1));
                       usersVo.setU_name(rs.getString(3));
                       usersVo.setU_profile(rs.getString(4));
                       usersVo.setU_birthday(rs.getTimestamp(5));
                       usersVo.setU_gender(rs.getString(6));
                       usersVo.setU_email(rs.getString(7));
                       usersVo.setU_address(rs.getString(8));
                       usersVo.setU_question(rs.getString(9));
                       usersVo.setU_answer(rs.getString(10));// 로그인 
                       result=usersVo; // session에 저장 
                       // id,name,addr저장 
                       // 화면 이동 => 카테고리 
                   }
           }
       }catch(Exception ex)
       {
           ex.printStackTrace();
       }
       finally
       {
           // 사용후에 반환 (Connection)
           disConnection();
       }
       return result;
   }
}





