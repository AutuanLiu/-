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
	Panel p1,p2,p3,p4,p5,p6,p7,p8,p9;   //  分行显示
	Label s1,s2,s3,s4,s5,c1,c2,c3,c4,sc1,sc2,sc3,mes,mes1,mes2,mes3,mes4;
	TextField st1,st2,st4,st5,ct1,ct2,ct3,ct4,sct1,sct2,sct3;
	TextArea msg;
	CheckboxGroup table,sex;
	Checkbox student,course,sc;
	Checkbox m,w;   //分别代表  男  女
	Button b1,b2,b3,b4;  //  分别为   查询   插入  修改    删除
	Connection cn;   //与数据库连接
	PreparedStatement cx,cr,xg,sg;  //  分别为   查询   插入  修改    删除
	String scx,scr,sxg,ssc,ssex;    //  分别为   查询   插入  修改    删除
	String ccx,ccr,cxg,csc;
	String sccx,sccr,scxg,scsc;
	
	Adm()
	{
		String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎	    
	    try 
	     {
	       Class.forName(JDriver);// 加载数据库引擎，返回给定字符串名的类
	     } 
	    catch (ClassNotFoundException e) 
	     {    // e.printStackTrace();
	          System.out.println("加载数据库引擎失败");
	           System.exit(0);
	     };
	      
	     table=new CheckboxGroup();
	     student=new Checkbox("student 表",true,table);  // 表的选择
	     course=new Checkbox("course 表",false,table);
	     sc=new Checkbox("sc 表",false,table);
	     
	     s1=new Label("学号 ");
	     s2=new Label("姓名 ");
	     s3=new Label("年龄 ");
	     s4=new Label("系别 "); //  student  
	     s5=new Label("性别");
	     st1=new TextField(10);
	     st2=new TextField(10);
	     st4=new TextField(10);
	     st5=new TextField(10);
	     sex=new CheckboxGroup();
	     m=new Checkbox("男",true,sex);
	     w=new Checkbox("女",false,sex);
	     
	     c1=new Label("课程号 ");  
	     c2=new Label("课程名 ");
	     c3=new Label("先行课  ");  //  course 表
	     c4=new Label("学分 ");
	     ct1=new TextField(10);
	     ct2=new TextField(10);
	     ct3=new TextField(10);
	     ct4=new TextField(10);
	     
	     sc1=new Label("学号 ");
	     sc2=new Label("课程号 ");  //  SC  表
	     sc3=new Label("成绩 ");
	     sct1=new TextField(10);
	     sct2=new TextField(10);
	     sct3=new TextField(10);
	     msg=new TextArea(10,80);
	     mes=new Label("                                    ");
	     mes1=new Label("说明：                                                                                                                                                                                                            ");
	     mes2=new Label("1. 本数据库系统基于java语言编写，使用JDBC技术实现与SQL Server 连接 ，通过Eclispe构建开发平台。 ");
	     mes3=new Label("2.本系统暂时只能实现插入功能，可以对数据的有效性进行检验。关于其他的功能还有待开发。 操作过程中无需打开SQL Server");		               
	     mes4=new Label("3.该系统处处体现了人性化设计，具有一定的实用性和可移植性。")	;                  
	     msg.setText("欢迎进入学生信息管理系统，祝您愉快！O(∩_∩)O~"+"\n"+"\n"
	     +"本数据库管理系统  不支持     级联  删除     删除前请注意   数据的  唯一性 ！！%>_<%"+"\n"+"\n"
	     +"student  支持由学号修改姓名，course  支持由课程号修改学分，sc  支持由学号和科目修改分数。"+"\n"+"\n"
	     + "student  支持由学号删除其他项，course  支持由课程号删除其他项，sc  暂不支持删除功能。");
	     
	     b1=new Button("查询");
	     b2=new Button("插入");  //  操作布局
	     b3=new Button("修改");
	     b4=new Button("删除");
	     
	     p1=new Panel();  //  容器构造
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
	     p1.add(m);     //   p1  容器
	     p1.add(w);
	     
	     p2.add(course);
	     p2.add(c1); p2.add(ct1);
	     p2.add(c2); p2.add(ct2);
	     p2.add(c3); p2.add(ct3);
	     p2.add(c4);  p2.add(ct4);  //  p2 容器
    
	     p3.add(sc);
	     p3.add(sc1); p3.add(sct1);   // p3  容器
	     p3.add(sc2); p3.add(sct2);
	     p3.add(sc3); p3.add(sct3);
	     p3.add(mes);
	     
	     p4.add(b1);
	     p4.add(b2);  //   p4 容器
	     p4.add(b3);
	     p4.add(b4);
	     p5.add(msg);
	     p6.add(mes1);
	     p7.add(mes2);
	     p8.add(mes3);
	     p9.add(mes4);
	     
	     setLayout(new FlowLayout(0,8,8));   //布局构造
	     add(p1);
	     add(p2);    // 添加容器到当前布局
	     add(p3);
	     add(p4);
	     add(p5);
	     add(p6);
	     add(p7);
	     add(p8);
	     add(p9);
	    
	     b1.addActionListener(new B1());
	     b2.addActionListener(new B2());  // 为按钮创建事件
	     b3.addActionListener(new B3());
	     b4.addActionListener(new B4());
	     
	     addWindowListener(new WinClose());
	     setSize(800,560);
	     setTitle("学生信息管理系统");
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
					String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=学生课程管理";// 数据源
				    String user = "sa";
			        String password = "sa";
			        Connection cn = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象
			        Statement stmt;
					ResultSet  rs;
					stmt=cn.createStatement();					   					 					    					 
				    rs=stmt.executeQuery("SELECT * FROM student ");				    
				    while(rs.next())
			       {
			         System.out.println(rs.getString("sno")+"  "+rs.getString("sname")+
			       "  "+rs.getString("ssex")+"  "+rs.getInt("sage")+"  "+rs.getString("sdept"));
			         msg.setText("数据查询成功，已经显示在     控制台窗口！");
			       };				       						
			       }
			     catch (SQLException e1) 
			     {
			    	System.out.println("数据库连接错误"+"\n");
			        System.exit(0);
			     };					     				 
			}
	     else if(course.getState())
	     {					
	    	 try 
		     {
				String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=学生课程管理";// 数据源
			    String user = "sa";
		        String password = "sa";
		        Connection cn = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象
		        Statement stmt;
				ResultSet  rs;
				stmt=cn.createStatement();					   					 					    					 
			    rs=stmt.executeQuery("SELECT * FROM course ");				    
			    while(rs.next())
		       {
		         System.out.println(rs.getString("cno")+"  "+rs.getString("cname")+"  "+
		    	 rs.getString("cpno")+"  "+rs.getString("ccredit"));
		         msg.setText("数据查询成功，已经显示在     控制台窗口！");
		       };				       						
		       }
		     catch (SQLException e1) 
		     {
		    	System.out.println("数据库连接错误"+"\n");
		        System.exit(0);
		     };					     				 
		}
	     else
	     {					
	    	 try 
		     {
				String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=学生课程管理";// 数据源
			    String user = "sa";
		        String password = "sa";
		        Connection cn = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象
		        Statement stmt;
				ResultSet  rs;
				stmt=cn.createStatement();					   					 					    					 
			    rs=stmt.executeQuery("SELECT * FROM sc ");				    
			    while(rs.next())
		       {
		         System.out.println(rs.getString("sno")+"  "+rs.getString("cno")+"  "
		        +rs.getString("grade"));
		         msg.setText("数据查询成功，已经显示在     控制台窗口！");
		       };				       						
		       }
		     catch (SQLException e1) 
		     {
		    	System.out.println("数据库连接错误"+"\n");
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
				String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=学生课程管理";// 数据源
			    String user = "sa";
		        String password = "sa";
		        Connection cn = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象
		        scr="INSERT INTO student VALUES(?,?,?,?,?)" ;
				cr=cn.prepareStatement(scr);
				msg.setText("连接数据库成功"+"\n");
		     }
		     catch (SQLException e1) 
		     {
		    	 System.out.print("数据库连接错误"+"\n");
		        System.exit(0);
		     };
			 try 
			     {
			       cr.setString(1, st1.getText());  //为SQL语句参数赋值
			       cr.setString(2, st2.getText());
			       if(m.getState())
			    	   ssex="男";
			       else
			    	   ssex="女";
			       cr.setString(3, ssex);
			       cr.setInt(4, Integer.parseInt(st4.getText()));
			       cr.setString(5, st5.getText());
			       cr.executeUpdate();
			       msg.setText("插入数据成功");
			       st1.setText("");
			       st2.setText("");  //清空各输入框
			       st4.setText("");
			       st5.setText("");
			       st1.requestFocus();			       
			     } 
			  catch (Exception s2) 
			     {    
			    	 msg.setText("输入数据有误！");
			    	 st1.requestFocus();
			     };
		}
			else if(course.getState())
			{
				try 
			     {
					String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=学生课程管理";// 数据源
				    String user = "sa";
			        String password = "sa";
			        Connection cn = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象
			        ccr="INSERT INTO course VALUES(?,?,?,?)" ;
					cr=cn.prepareStatement(ccr);
					System.out.print("连接数据库成功"+"\n");
			     }
			     catch (SQLException e1) 
			     {
			    	 msg.setText("数据库连接错误"+"\n");
			        System.exit(0);
			     };
				 try 
				     {
					   cr.setInt(1, Integer.parseInt(ct1.getText()));  //为SQL语句参数赋值
				       cr.setString(2, ct2.getText());				
				       cr.setString(3, ct3.getText());
				       cr.setString(4, ct4.getText());
				       cr.executeUpdate();
				       msg.setText("插入数据成功");
				       ct1.setText("");
				       ct2.setText("");  //清空各输入框
				       ct3.setText("");
				       ct4.setText("");
				       ct1.requestFocus();			       
				     } 
				  catch (Exception e2) 
				     {    
				    	 msg.setText("输入数据有误！");
				    	 ct1.requestFocus();
				     };
			}
			else 
			{
					try 
			     {
					String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=学生课程管理";// 数据源
				    String user = "sa";
			        String password = "sa";
			        Connection cn = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象
			        sccr="INSERT INTO sc VALUES(?,?,?)" ;
					cr=cn.prepareStatement(sccr);
					System.out.print("连接数据库成功"+"\n");
			     }
			     catch (SQLException e1) 
			     {
			    	 msg.setText("数据库连接错误"+"\n");
			        System.exit(0);
			     };
				 try 
				     {
				       cr.setString(1, sct1.getText());  //为SQL语句参数赋值
				       cr.setInt(2, Integer.parseInt(sct2.getText()));				       
				       cr.setInt(3, Integer.parseInt(sct3.getText()));				       
				       cr.executeUpdate();
				       msg.setText("插入数据成功");
				       sct1.setText("");
				       sct2.setText("");  //清空各输入框
				       sct3.setText("");				      
				       sct1.requestFocus();			       
				     } 
				  catch (Exception e2) 
				     {    
				    	 msg.setText("输入数据有误！");
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
							String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=学生课程管理";// 数据源
						    String user = "sa";
					        String password = "sa";
					        Connection cn = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象
					        sxg="UPDATE student SET sname=? WHERE sno=?" ;
							xg=cn.prepareStatement(sxg);
							System.out.print("连接数据库成功"+"\n");
					     }
					     catch (SQLException e1) 
					     {
					    	 msg.setText("数据库连接错误"+"\n");
					        System.exit(0);
					     };					     
						 try 
						     {
						       xg.setString(1, st2.getText());  //为SQL语句参数赋值
						       xg.setString(2, st1.getText());
						       xg.executeUpdate();
						       msg.setText("修改数据成功!");						       
						       st1.setText("");
						       st2.setText("");  //清空各输入框	
						       st4.setText("");
						       st5.setText("");
						       st1.requestFocus();			       
						     } 
						  catch (Exception s2) 
						     {    
						    	 msg.setText("输入数据有误！");
						    	 st1.requestFocus();
						     };
					}
			     else if(course.getState())
			     {					
			    	 try 
					     {
							String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=学生课程管理";// 数据源
						    String user = "sa";
					        String password = "sa";
					        Connection cn = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象
					        cxg="UPDATE course SET ccredit=? WHERE cno=?" ;
							xg=cn.prepareStatement(cxg);
							System.out.print("连接数据库成功"+"\n");
					     }
					     catch (SQLException e1) 
					     {
					    	 msg.setText("数据库连接错误"+"\n");
					        System.exit(0);
					     };					     
						 try 
						     {
						       xg.setString(1, ct4.getText());  //为SQL语句参数赋值
						       xg.setString(2, ct1.getText());
						       xg.executeUpdate();
						       msg.setText("修改数据成功!");						       
						       ct1.setText("");
						       ct2.setText("");
						       ct3.setText("");
						       ct4.setText("");  //清空各输入框						       
						       ct1.requestFocus();			       
						     } 
						  catch (Exception s2) 
						     {    
						    	 msg.setText("输入数据有误！");
						    	 ct1.requestFocus();
						     };
					}
			     else
			     {					
			    	 try 
					     {
							String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=学生课程管理";// 数据源
						    String user = "sa";
					        String password = "sa";
					        Connection cn = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象
					        scxg="UPDATE sc SET grade=? WHERE sno=? AND cno=?" ;
							xg=cn.prepareStatement(scxg);
							System.out.print("连接数据库成功"+"\n");
					     }
					     catch (SQLException e1) 
					     {
					    	 msg.setText("数据库连接错误"+"\n");
					        System.exit(0);
					     };					     
						 try 
						     {
						       xg.setString(1, sct3.getText());  //为SQL语句参数赋值
						       xg.setString(2, sct1.getText());
						       xg.setString(3, sct2.getText());
						       xg.executeUpdate();
						       msg.setText("修改数据成功!");						       
						       sct1.setText("");
						       sct2.setText("");
						       sct3.setText("");  //清空各输入框						       
						       sct1.requestFocus();			       
						     } 
						  catch (Exception s2) 
						     {    
						    	 msg.setText("输入数据有误！");
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
						String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=学生课程管理";// 数据源
					    String user = "sa";
				        String password = "sa";
				        Connection cn = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象
				        ssc="DELETE FROM student WHERE sno=?" ;
						sg=cn.prepareStatement(ssc);
						System.out.print("连接数据库成功"+"\n");
				     }
				     catch (SQLException e1) 
				     {
				    	 System.out.print("数据库连接错误"+"\n");
				        System.exit(0);
				     };					     
					 try 
					     {					         
					       sg.setString(1, st1.getText());  //为SQL语句参数赋值
					       sg.executeUpdate();
					       msg.setText("删除数据成功!");						       
					       st1.setText("");
					       st2.setText("");  //清空各输入框						       
					       st1.requestFocus();			       
					     } 
					  catch (Exception s2) 
					     {    
					    	 msg.setText("输入数据有误！");
					    	 st1.requestFocus();
					     };
				}
		     else if(course.getState())
		     {					
		    	 try 
			     {
					String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=学生课程管理";// 数据源
				    String user = "sa";
			        String password = "sa";
			        Connection cn = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象
			        csc="DELETE FROM course WHERE cno=?" ;
					sg=cn.prepareStatement(csc);
					System.out.print("连接数据库成功"+"\n");
			     }
			     catch (SQLException e1) 
			     {
			    	 System.out.print("数据库连接错误"+"\n");
			        System.exit(0);
			     };					     
				 try 
				     {					         
				       sg.setInt(1, Integer.parseInt(ct1.getText()));  //为SQL语句参数赋值
				       sg.executeUpdate();
				       msg.setText("删除数据成功!");						       
				       ct1.setText("");
				       ct2.setText(""); 
				       ct3.setText("");
				       ct4.setText("");//清空各输入框						       
				       ct1.requestFocus();			       
				     } 
				  catch (Exception s2) 
				     {    
				    	 msg.setText("输入数据有误！");
				    	 ct1.requestFocus();
				     };
			 }
		      else
		      {
		    	 msg.setText(" 对不起，暂不支持对  SC 表进行删除操作！");
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

