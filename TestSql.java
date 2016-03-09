package JDB;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TestSql 
{
  public static void main(String args[]) throws Exception
  {
	  new Adm();	  
  }
}

class Adm extends Frame
{
	Panel p1,p2,p3,p4,p5,p6,p7,p8,p9;   //  ������ʾ
	Label s1,s2,s3,s4,s5,c1,c2,c3,c4,sc1,sc2,sc3,mes,mes1,mes2,mes3,mes4;
	TextField st1,st2,st4,st5,ct1,ct2,ct3,ct4,sct1,sct2,sct3;
	TextArea msg;
	CheckboxGroup table,sex;
	Checkbox student,course,sc;
	Checkbox m,w;   //�ֱ����  ��  Ů
	Button b1,b2,b3,b4;  //  �ֱ�Ϊ   ��ѯ   ����  �޸�    ɾ��
	Connection cn;   //�����ݿ�����
	PreparedStatement cx,cr,xg,sg;  //  �ֱ�Ϊ   ��ѯ   ����  �޸�    ɾ��
	String scx,scr,sxg,ssc,ssex;    //  �ֱ�Ϊ   ��ѯ   ����  �޸�    ɾ��
	String ccx,ccr,cxg,csc;
	String sccx,sccr,scxg,scsc;
	
	Adm()
	{
		String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����	    
	    try 
	     {
	       Class.forName(JDriver);// �������ݿ����棬���ظ����ַ���������
	     } 
	    catch (ClassNotFoundException e) 
	     {    // e.printStackTrace();
	          System.out.println("�������ݿ�����ʧ��");
	           System.exit(0);
	     };
	      
	     table=new CheckboxGroup();
	     student=new Checkbox("student ��",true,table);  // ���ѡ��
	     course=new Checkbox("course ��",false,table);
	     sc=new Checkbox("sc ��",false,table);
	     
	     s1=new Label("ѧ�� ");
	     s2=new Label("���� ");
	     s3=new Label("���� ");
	     s4=new Label("ϵ�� "); //  student  
	     s5=new Label("�Ա�");
	     st1=new TextField(10);
	     st2=new TextField(10);
	     st4=new TextField(10);
	     st5=new TextField(10);
	     sex=new CheckboxGroup();
	     m=new Checkbox("��",true,sex);
	     w=new Checkbox("Ů",false,sex);
	     
	     c1=new Label("�γ̺� ");  
	     c2=new Label("�γ��� ");
	     c3=new Label("���п�  ");  //  course ��
	     c4=new Label("ѧ�� ");
	     ct1=new TextField(10);
	     ct2=new TextField(10);
	     ct3=new TextField(10);
	     ct4=new TextField(10);
	     
	     sc1=new Label("ѧ�� ");
	     sc2=new Label("�γ̺� ");  //  SC  ��
	     sc3=new Label("�ɼ� ");
	     sct1=new TextField(10);
	     sct2=new TextField(10);
	     sct3=new TextField(10);
	     msg=new TextArea(10,80);
	     mes=new Label("                                    ");
	     mes1=new Label("˵����                                                                                                                                                                                                            ");
	     mes2=new Label("1. �����ݿ�ϵͳ����java���Ա�д��ʹ��JDBC����ʵ����SQL Server ���� ��ͨ��Eclispe��������ƽ̨�� ");
	     mes3=new Label("2.��ϵͳ��ʱֻ��ʵ�ֲ��빦�ܣ����Զ����ݵ���Ч�Խ��м��顣���������Ĺ��ܻ��д������� ���������������SQL Server");		               
	     mes4=new Label("3.��ϵͳ�������������Ի���ƣ�����һ����ʵ���ԺͿ���ֲ�ԡ�")	;                  
	     msg.setText("��ӭ����ѧ����Ϣ����ϵͳ��ף����죡O(��_��)O~"+"\n"+"\n"
	     +"�����ݿ����ϵͳ  ��֧��     ����  ɾ��     ɾ��ǰ��ע��   ���ݵ�  Ψһ�� ����%>_<%"+"\n"+"\n"
	     +"student  ֧����ѧ���޸�������course  ֧���ɿγ̺��޸�ѧ�֣�sc  ֧����ѧ�źͿ�Ŀ�޸ķ�����"+"\n"+"\n"
	     + "student  ֧����ѧ��ɾ�������course  ֧���ɿγ̺�ɾ�������sc  �ݲ�֧��ɾ�����ܡ�");
	     
	     b1=new Button("��ѯ");
	     b2=new Button("����");  //  ��������
	     b3=new Button("�޸�");
	     b4=new Button("ɾ��");
	     
	     p1=new Panel();  //  ��������
	     p2=new Panel();
	     p3=new Panel();
	     p4=new Panel();
	     p5=new Panel();
	     p6=new Panel();
	     p7=new Panel();
	     p8=new Panel();
	     p9=new Panel();
	     
	     
	     p1.add(student);
	     p1.add(s1); p1.add(st1);
	     p1.add(s2); p1.add(st2);
	     p1.add(s3); p1.add(st4);
	     p1.add(s4); p1.add(st5);
	     p1.add(s5);
	     p1.add(m);     //   p1  ����
	     p1.add(w);
	     
	     p2.add(course);
	     p2.add(c1); p2.add(ct1);
	     p2.add(c2); p2.add(ct2);
	     p2.add(c3); p2.add(ct3);
	     p2.add(c4);  p2.add(ct4);  //  p2 ����
    
	     p3.add(sc);
	     p3.add(sc1); p3.add(sct1);   // p3  ����
	     p3.add(sc2); p3.add(sct2);
	     p3.add(sc3); p3.add(sct3);
	     p3.add(mes);
	     
	     p4.add(b1);
	     p4.add(b2);  //   p4 ����
	     p4.add(b3);
	     p4.add(b4);
	     p5.add(msg);
	     p6.add(mes1);
	     p7.add(mes2);
	     p8.add(mes3);
	     p9.add(mes4);
	     
	     setLayout(new FlowLayout(0,8,8));   //���ֹ���
	     add(p1);
	     add(p2);    // �����������ǰ����
	     add(p3);
	     add(p4);
	     add(p5);
	     add(p6);
	     add(p7);
	     add(p8);
	     add(p9);
	    
	     b1.addActionListener(new B1());
	     b2.addActionListener(new B2());  // Ϊ��ť�����¼�
	     b3.addActionListener(new B3());
	     b4.addActionListener(new B4());
	     
	     addWindowListener(new WinClose());
	     setSize(800,560);
	     setTitle("ѧ����Ϣ����ϵͳ");
	     setVisible(true);
	}
	
	class B1 implements ActionListener
	{
	   
		public void actionPerformed(ActionEvent e)
		{			
			  if(student.getState())
			{					
	    	 try 
			     {
					String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ѧ���γ̹���";// ����Դ
				    String user = "sa";
			        String password = "sa";
			        Connection cn = DriverManager.getConnection(connectDB, user, password);// �������ݿ����
			        Statement stmt;
					ResultSet  rs;
					stmt=cn.createStatement();					   					 					    					 
				    rs=stmt.executeQuery("SELECT * FROM student ");				    
				    while(rs.next())
			       {
			         System.out.println(rs.getString("sno")+"  "+rs.getString("sname")+
			       "  "+rs.getString("ssex")+"  "+rs.getInt("sage")+"  "+rs.getString("sdept"));
			         msg.setText("���ݲ�ѯ�ɹ����Ѿ���ʾ��     ����̨���ڣ�");
			       };				       						
			       }
			     catch (SQLException e1) 
			     {
			    	System.out.println("���ݿ����Ӵ���"+"\n");
			        System.exit(0);
			     };					     				 
			}
	     else if(course.getState())
	     {					
	    	 try 
		     {
				String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ѧ���γ̹���";// ����Դ
			    String user = "sa";
		        String password = "sa";
		        Connection cn = DriverManager.getConnection(connectDB, user, password);// �������ݿ����
		        Statement stmt;
				ResultSet  rs;
				stmt=cn.createStatement();					   					 					    					 
			    rs=stmt.executeQuery("SELECT * FROM course ");				    
			    while(rs.next())
		       {
		         System.out.println(rs.getString("cno")+"  "+rs.getString("cname")+"  "+
		    	 rs.getString("cpno")+"  "+rs.getString("ccredit"));
		         msg.setText("���ݲ�ѯ�ɹ����Ѿ���ʾ��     ����̨���ڣ�");
		       };				       						
		       }
		     catch (SQLException e1) 
		     {
		    	System.out.println("���ݿ����Ӵ���"+"\n");
		        System.exit(0);
		     };					     				 
		}
	     else
	     {					
	    	 try 
		     {
				String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ѧ���γ̹���";// ����Դ
			    String user = "sa";
		        String password = "sa";
		        Connection cn = DriverManager.getConnection(connectDB, user, password);// �������ݿ����
		        Statement stmt;
				ResultSet  rs;
				stmt=cn.createStatement();					   					 					    					 
			    rs=stmt.executeQuery("SELECT * FROM sc ");				    
			    while(rs.next())
		       {
		         System.out.println(rs.getString("sno")+"  "+rs.getString("cno")+"  "
		        +rs.getString("grade"));
		         msg.setText("���ݲ�ѯ�ɹ����Ѿ���ʾ��     ����̨���ڣ�");
		       };				       						
		       }
		     catch (SQLException e1) 
		     {
		    	System.out.println("���ݿ����Ӵ���"+"\n");
		        System.exit(0);
		     };					     				
		 }			    	 				    		    
		}
	}
	
	class B2 implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(student.getState())
		{
				try 
		     {
				String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ѧ���γ̹���";// ����Դ
			    String user = "sa";
		        String password = "sa";
		        Connection cn = DriverManager.getConnection(connectDB, user, password);// �������ݿ����
		        scr="INSERT INTO student VALUES(?,?,?,?,?)" ;
				cr=cn.prepareStatement(scr);
				msg.setText("�������ݿ�ɹ�"+"\n");
		     }
		     catch (SQLException e1) 
		     {
		    	 System.out.print("���ݿ����Ӵ���"+"\n");
		        System.exit(0);
		     };
			 try 
			     {
			       cr.setString(1, st1.getText());  //ΪSQL��������ֵ
			       cr.setString(2, st2.getText());
			       if(m.getState())
			    	   ssex="��";
			       else
			    	   ssex="Ů";
			       cr.setString(3, ssex);
			       cr.setInt(4, Integer.parseInt(st4.getText()));
			       cr.setString(5, st5.getText());
			       cr.executeUpdate();
			       msg.setText("�������ݳɹ�");
			       st1.setText("");
			       st2.setText("");  //��ո������
			       st4.setText("");
			       st5.setText("");
			       st1.requestFocus();			       
			     } 
			  catch (Exception s2) 
			     {    
			    	 msg.setText("������������");
			    	 st1.requestFocus();
			     };
		}
			else if(course.getState())
			{
				try 
			     {
					String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ѧ���γ̹���";// ����Դ
				    String user = "sa";
			        String password = "sa";
			        Connection cn = DriverManager.getConnection(connectDB, user, password);// �������ݿ����
			        ccr="INSERT INTO course VALUES(?,?,?,?)" ;
					cr=cn.prepareStatement(ccr);
					System.out.print("�������ݿ�ɹ�"+"\n");
			     }
			     catch (SQLException e1) 
			     {
			    	 msg.setText("���ݿ����Ӵ���"+"\n");
			        System.exit(0);
			     };
				 try 
				     {
					   cr.setInt(1, Integer.parseInt(ct1.getText()));  //ΪSQL��������ֵ
				       cr.setString(2, ct2.getText());				
				       cr.setString(3, ct3.getText());
				       cr.setString(4, ct4.getText());
				       cr.executeUpdate();
				       msg.setText("�������ݳɹ�");
				       ct1.setText("");
				       ct2.setText("");  //��ո������
				       ct3.setText("");
				       ct4.setText("");
				       ct1.requestFocus();			       
				     } 
				  catch (Exception e2) 
				     {    
				    	 msg.setText("������������");
				    	 ct1.requestFocus();
				     };
			}
			else 
			{
					try 
			     {
					String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ѧ���γ̹���";// ����Դ
				    String user = "sa";
			        String password = "sa";
			        Connection cn = DriverManager.getConnection(connectDB, user, password);// �������ݿ����
			        sccr="INSERT INTO sc VALUES(?,?,?)" ;
					cr=cn.prepareStatement(sccr);
					System.out.print("�������ݿ�ɹ�"+"\n");
			     }
			     catch (SQLException e1) 
			     {
			    	 msg.setText("���ݿ����Ӵ���"+"\n");
			        System.exit(0);
			     };
				 try 
				     {
				       cr.setString(1, sct1.getText());  //ΪSQL��������ֵ
				       cr.setInt(2, Integer.parseInt(sct2.getText()));				       
				       cr.setInt(3, Integer.parseInt(sct3.getText()));				       
				       cr.executeUpdate();
				       msg.setText("�������ݳɹ�");
				       sct1.setText("");
				       sct2.setText("");  //��ո������
				       sct3.setText("");				      
				       sct1.requestFocus();			       
				     } 
				  catch (Exception e2) 
				     {    
				    	 msg.setText("������������");
				    	 sct1.requestFocus();
				     };				
			}
		}
	}
				
	class B3 implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{     
			     if(student.getState())
					{					
			    	 try 
					     {
							String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ѧ���γ̹���";// ����Դ
						    String user = "sa";
					        String password = "sa";
					        Connection cn = DriverManager.getConnection(connectDB, user, password);// �������ݿ����
					        sxg="UPDATE student SET sname=? WHERE sno=?" ;
							xg=cn.prepareStatement(sxg);
							System.out.print("�������ݿ�ɹ�"+"\n");
					     }
					     catch (SQLException e1) 
					     {
					    	 msg.setText("���ݿ����Ӵ���"+"\n");
					        System.exit(0);
					     };					     
						 try 
						     {
						       xg.setString(1, st2.getText());  //ΪSQL��������ֵ
						       xg.setString(2, st1.getText());
						       xg.executeUpdate();
						       msg.setText("�޸����ݳɹ�!");						       
						       st1.setText("");
						       st2.setText("");  //��ո������	
						       st4.setText("");
						       st5.setText("");
						       st1.requestFocus();			       
						     } 
						  catch (Exception s2) 
						     {    
						    	 msg.setText("������������");
						    	 st1.requestFocus();
						     };
					}
			     else if(course.getState())
			     {					
			    	 try 
					     {
							String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ѧ���γ̹���";// ����Դ
						    String user = "sa";
					        String password = "sa";
					        Connection cn = DriverManager.getConnection(connectDB, user, password);// �������ݿ����
					        cxg="UPDATE course SET ccredit=? WHERE cno=?" ;
							xg=cn.prepareStatement(cxg);
							System.out.print("�������ݿ�ɹ�"+"\n");
					     }
					     catch (SQLException e1) 
					     {
					    	 msg.setText("���ݿ����Ӵ���"+"\n");
					        System.exit(0);
					     };					     
						 try 
						     {
						       xg.setString(1, ct4.getText());  //ΪSQL��������ֵ
						       xg.setString(2, ct1.getText());
						       xg.executeUpdate();
						       msg.setText("�޸����ݳɹ�!");						       
						       ct1.setText("");
						       ct2.setText("");
						       ct3.setText("");
						       ct4.setText("");  //��ո������						       
						       ct1.requestFocus();			       
						     } 
						  catch (Exception s2) 
						     {    
						    	 msg.setText("������������");
						    	 ct1.requestFocus();
						     };
					}
			     else
			     {					
			    	 try 
					     {
							String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ѧ���γ̹���";// ����Դ
						    String user = "sa";
					        String password = "sa";
					        Connection cn = DriverManager.getConnection(connectDB, user, password);// �������ݿ����
					        scxg="UPDATE sc SET grade=? WHERE sno=? AND cno=?" ;
							xg=cn.prepareStatement(scxg);
							System.out.print("�������ݿ�ɹ�"+"\n");
					     }
					     catch (SQLException e1) 
					     {
					    	 msg.setText("���ݿ����Ӵ���"+"\n");
					        System.exit(0);
					     };					     
						 try 
						     {
						       xg.setString(1, sct3.getText());  //ΪSQL��������ֵ
						       xg.setString(2, sct1.getText());
						       xg.setString(3, sct2.getText());
						       xg.executeUpdate();
						       msg.setText("�޸����ݳɹ�!");						       
						       sct1.setText("");
						       sct2.setText("");
						       sct3.setText("");  //��ո������						       
						       sct1.requestFocus();			       
						     } 
						  catch (Exception s2) 
						     {    
						    	 msg.setText("������������");
						    	 sct1.requestFocus();
						     };
					}			    	 			     
				}
		   }		
		
	class B4 implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				if(student.getState())
				{					
		    	 try 
				     {
						String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ѧ���γ̹���";// ����Դ
					    String user = "sa";
				        String password = "sa";
				        Connection cn = DriverManager.getConnection(connectDB, user, password);// �������ݿ����
				        ssc="DELETE FROM student WHERE sno=?" ;
						sg=cn.prepareStatement(ssc);
						System.out.print("�������ݿ�ɹ�"+"\n");
				     }
				     catch (SQLException e1) 
				     {
				    	 System.out.print("���ݿ����Ӵ���"+"\n");
				        System.exit(0);
				     };					     
					 try 
					     {					         
					       sg.setString(1, st1.getText());  //ΪSQL��������ֵ
					       sg.executeUpdate();
					       msg.setText("ɾ�����ݳɹ�!");						       
					       st1.setText("");
					       st2.setText("");  //��ո������						       
					       st1.requestFocus();			       
					     } 
					  catch (Exception s2) 
					     {    
					    	 msg.setText("������������");
					    	 st1.requestFocus();
					     };
				}
		     else if(course.getState())
		     {					
		    	 try 
			     {
					String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ѧ���γ̹���";// ����Դ
				    String user = "sa";
			        String password = "sa";
			        Connection cn = DriverManager.getConnection(connectDB, user, password);// �������ݿ����
			        csc="DELETE FROM course WHERE cno=?" ;
					sg=cn.prepareStatement(csc);
					System.out.print("�������ݿ�ɹ�"+"\n");
			     }
			     catch (SQLException e1) 
			     {
			    	 System.out.print("���ݿ����Ӵ���"+"\n");
			        System.exit(0);
			     };					     
				 try 
				     {					         
				       sg.setInt(1, Integer.parseInt(ct1.getText()));  //ΪSQL��������ֵ
				       sg.executeUpdate();
				       msg.setText("ɾ�����ݳɹ�!");						       
				       ct1.setText("");
				       ct2.setText(""); 
				       ct3.setText("");
				       ct4.setText("");//��ո������						       
				       ct1.requestFocus();			       
				     } 
				  catch (Exception s2) 
				     {    
				    	 msg.setText("������������");
				    	 ct1.requestFocus();
				     };
			 }
		      else
		      {
		    	 msg.setText(" �Բ����ݲ�֧�ֶ�  SC �����ɾ��������");
		      }
			}
		}
		
	class WinClose extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			(e.getWindow()).dispose();
			System.exit(0);
		}
	}
}

