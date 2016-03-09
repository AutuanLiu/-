import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AccEmp3
{
	public static void main(String args[])
	{
		new Emp();
	}
}

class Emp extends Frame
{
	Panel p1,p2,p3;
	Label e1,e2,e3,e4,e5,e6,msg;
	TextField text1,text2,text3,text4,text5,text6;
	CheckboxGroup sex;
	Checkbox m,w;
	Button b1;
	Connection cn;
	PreparedStatement ps;
	String ssql,ssex;

	Emp()
	{
		try
		{
			DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
			cn=DriverManager.getConnection("jdbc:odbc:employee");
			ssql="INSERT INTO emp VALUES(?,?,?,?,?,?)";
			ps=cn.prepareStatement(ssql);
		} catch(SQLException el) { msg.setText("数据库连接有误！"); };
	e1=new Label("职工号");
	e2=new Label("姓名");
	e3=new Label("性别");
	e4=new Label("出生年月");
	e5=new Label("工资");
	e6=new Label("部门号");
	msg=new Label("		");
	text1=new TextField(10);
	text2=new TextField(10);
	text3=new TextField(10);
	text4=new TextField(10);
	text5=new TextField(10);
	text6=new TextField(10);
	sex=new CheckboxGroup();
	m=new Checkbox("男",true,sex);
	w=new Checkbox("女",false,sex);
	b1=new Button("插入职工记录");
	p1.add(e1);p1.add(text1);
	p1.add(e2);p1.add(text2);
	p1.add(e3);p1.add(m);p1.add(w);
	p2.add(e4);p2.add(text4);
	p2.add(e5);p2.add(text5);
	p2.add(e6);p2.add(text6);
	p3.add(msg);p3.add(b1);
	setLayout(new FlowLayout());
	add(p1);add(p2);add(p3);
	b1.addActionListener(new B1());
	addWindowListener(new WinClose());
	setSize(500,200);
	setTitle("职工信息维护");
	setVisible(true);
	}

	class B1 implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				ps.setString(1,text1.getText());
				ps.setString(2,text2.getText());
				if(m.getState())
					ssex="男";
				else
					ssex="女";
				ps.setString(3,ssex);
				ps.setString(4,text4.getText());
				ps.setInt(5,Integer.parseInt(text5.getText()));
				ps.setString(6,text6.getText());
				ps.executeUpdate();
				msg.setText("记录插入成功!");
				text2.setText("");
				text4.setText("");
				text5.setText("");
				text6.setText("");
				text1.setText("");
				text1.requestFocus();
			} catch(Exception e2){msg.setText("输入数据有误！");text1.requestFocus(); }
		}
	}
	class WinClose extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			try
			{
				cn.commit();
				cn.close();
			} catch(SQLException e3){}
			(e.getWindow()).dispose();
			System.exit(0);
		}
	}
}