package model;

import java.util.ArrayList;
import java.util.List;

import util.ThubanSQLManager;

public class StudentDAO extends ThubanSQLManager{

	public boolean writeStudent(List<Object> infos){
		int result = 0;
		String query ="INSERT INTO tbl_score_001 VALUES(?,?,?,?,?,?,?)";
		
		result = updateQuery(query, infos);
		
		return result>0;
	}

	public List<StudentVO> getStudents(){
		String query ="SELECT * FROM tbl_score_001 ORDER BY bun";
		List<StudentVO> list = new ArrayList<StudentVO>();
		StudentVO vo = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			
			while(rs.next()){
				vo = new StudentVO();
				
				vo.setBun(Integer.valueOf(rs.getString("bun")));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setKor(Integer.valueOf(rs.getString("kor")));
				vo.setEng(Integer.valueOf(rs.getString("eng")));
				vo.setMat(Integer.valueOf(rs.getString("mat")));
				int tot = Integer.valueOf(rs.getString("kor"))+Integer.valueOf(rs.getString("eng"))+Integer.valueOf(rs.getString("mat"));
				vo.setTot(tot);
				double avg = Math.round(((double)tot/3)*100)/(double)100;
				vo.setAvg(avg);
				
				
				String grade = null;
				if(avg >= 90){
					grade = "수";
				}else if(avg >= 80){
					grade = "우";
				}else if(avg >= 70){
					grade = "미";
				}else if(avg >= 60){
					grade = "양";
				}else{
					grade = "가";
				}
				
				
				vo.setGrade(grade);
				
				list.add(vo);
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return list;
	}
	
}
