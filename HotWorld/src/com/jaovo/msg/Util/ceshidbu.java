package com.jaovo.msg.Util;
import com.jaovo.msg.model.Word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jaovo.msg.Util.WordDaoImpl;
public class ceshidbu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Word word=new Word();
		word.setWord1("����");
		//word.setWord2("maih");
		Connection connection = DBUtil.getConnection();
		//׼��sql���
		String sql="select * from word where text = ?";
		//������䴫�����
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1, word.getWord1());
				//���ս����
				resultSet=preparedStatement.executeQuery();
				//���������
				boolean pan=false;
				int numall=0;
				while(resultSet.next())
				{
					numall=resultSet.getInt("num");
					System.out.println(resultSet.getInt("num")+"��ѻ�");
					if(resultSet.getInt(1)>0)//>0˵�����ݿ����Ѵ��ڸ��û�
					{
						pan=true;
						System.out.println(pan);
						numall++;	
					}
					sql="update word set num= ? where text =?";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(2, word.getWord1());
					preparedStatement.setInt(1, numall);
					preparedStatement.executeUpdate();//����
				}
				System.out.println(pan);
				if(pan==false) {
				
					
					sql="insert into word(text,num) value (?,?)";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, word.getWord1());
					preparedStatement.setInt(2, 1);
					preparedStatement.executeUpdate();//����
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtil.close(resultSet);
				DBUtil.close(preparedStatement);
				DBUtil.close(connection);
			}
	}

}
