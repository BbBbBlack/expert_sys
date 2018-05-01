package expert_sys.db;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.security.auth.login.Configuration;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * @author lpf
 *
 */
public class DBHelper {
    //����Connection����
    Connection con;
    //����������
    String driver;
    //URLָ��Ҫ���ʵ����ݿ���mydata
    String url;
    //MySQL����ʱ���û���
    String user;
    //MySQL����ʱ������
    String password;
    
    
    /**
     * ���캯��
     * @param driver
     * @param url
     * @param user
     * @param password
     */
    public DBHelper(String driver,String url,String user,String password) {
		this.driver=driver;
		this.url=url;
		this.user=user;
		this.password=password;
		try {
	        //������������
	        Class.forName(driver);
	        //1.getConnection()����������MySQL���ݿ⣡��
	        con = (Connection) DriverManager.getConnection(url, user, password);
	        if(!con.isClosed())
	            System.out.println("Succeeded connecting to the Database!");
		} catch(ClassNotFoundException e) {   
            //���ݿ��������쳣����
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //���ݿ�����ʧ���쳣����
            e.printStackTrace();  
            }catch (Exception e) {
            e.printStackTrace();
        }
	}
    
    
    /**
     *�޲������캯����Ĭ�϶�ȡconfigĿ¼��dbconfig.xml�ĵ�һ�����ݿ����� 
     */
    public DBHelper() {
    	setDBConfig();
		try {
	        //������������
	        Class.forName(driver);
	        //1.getConnection()����������MySQL���ݿ⣡��
	        con = (Connection) DriverManager.getConnection(url, user, password);
	        if(!con.isClosed())
	            System.out.println("Succeeded connecting to the Database!");
		} catch(ClassNotFoundException e) {   
            //���ݿ��������쳣����
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //���ݿ�����ʧ���쳣����
            e.printStackTrace();  
            }catch (Exception e) {
            e.printStackTrace();
        }
	}
    
	/**
	 * ���õ�ǰ���ӵ����ݿ�
	 */
	private void setDBConfig()
	{
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			//��ӡ���ݿ���������
			db = dbf.newDocumentBuilder();
			//URL path = Configuration.class.getClassLoader().getResource("dbconfig.xml");
			String path = DBHelper.class.getResource("/").toString();  
			//System.out.println(path+"------path");
			Document doc=db.parse(path+"/dbconfig.xml");
			NodeList dblist=doc.getElementsByTagName("DataBase");
			//���ñ�����ʹ�õ����ݿ�
			String configs="";
			Node cur_config=(Node) dblist.item(0);
			NodeList cur_items=(NodeList) cur_config.getChildNodes();
			for(int j=0;j<cur_items.getLength();j++)
			{
				if(cur_items.item(j).getNodeType() == Node.ELEMENT_NODE){
					switch(cur_items.item(j).getNodeName())
					{
							case "driver":
								this.driver=cur_items.item(j).getTextContent();
								break;
							case "url":
								this.url=cur_items.item(j).getTextContent()+"?useUnicode=true&characterEncoding=UTF-8";
								break;
							case "user":
								this.user=cur_items.item(j).getTextContent();
								break;
							case "password":
								this.password=cur_items.item(j).getTextContent();
								break;
							default:
								break;
					
					}
				}
			}			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	/**
	 * ����������ݿ�������ļ�
	 */
	private void ShowDBConfig()
	{
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			//��ӡ���ݿ���������
			db = dbf.newDocumentBuilder();
			Document doc=db.parse("config/dbconfig.xml");
			NodeList dblist=doc.getElementsByTagName("DataBase");
			//�鿴�������ݿ�����
			System.out.printf("����������ݿ����ã�һ����%d�����ݿ�%n",dblist.getLength());
			for(int i=0;i<dblist.getLength();i++)
			{
				System.out.printf("�����%d�����ݿ�%n",i+1);
				Node dbnode=(Node) dblist.item(i);
				NodeList dbconfigs=(NodeList) dbnode.getChildNodes();
				for(int j=0;j<dbconfigs.getLength();j++)
				{
					if(dbconfigs.item(j).getNodeType() == Node.ELEMENT_NODE){
					System.out.printf("  �����%d�����ݿ�����%n",j+1);
					System.out.printf("�ڵ���   %s    �ڵ�ֵ��%s  %n",dbconfigs.item(j).getNodeName(),dbconfigs.item(j).getTextContent());
					}
				}
			}
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
    public void query(){
    	//������ѯ�����
        try {
            //2.����statement���������ִ��SQL��䣡��
            Statement statement = (Statement) con.createStatement();
            //Ҫִ�е�SQL���
            String sql = "select * from Diagnose";
            //3.ResultSet�࣬������Ż�ȡ�Ľ��������
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("ִ�н��������ʾ:");  
            System.out.println("-----------------");  
            System.out.println("����" + "\t" + "ְ��");  
            System.out.println("-----------------");  
             
            String job = null;
            String id = null;
            while(rs.next()){
                //��ȡstuname��������
                job = rs.getString("job");
                //��ȡstuid��������
                id = rs.getString("ename");

                //������
                System.out.println(id + "\t" + job);
            }
            rs.close();
        } catch(SQLException e) {
            //���ݿ�����ʧ���쳣����
            e.printStackTrace();  
            }catch (Exception e) {
            e.printStackTrace();
        }finally{
            System.out.println("���ݿ����ӳɹ�������ִ����ϣ�");
        }	
    }
    
    
    /**
     * �������ݿ��ѯ����ѯ����
     * @param sql �� Ҫִ�еĲ�ѯ���
     */
    public ResultSet query2(String sql){
    	//������ѯ�����
        try {
            //2.����statement���������ִ��SQL��䣡��
            Statement statement = (Statement) this.con.createStatement();
            //Ҫִ�е�SQL���
            //3.ResultSet�࣬������Ż�ȡ�Ľ��������
            ResultSet rs = statement.executeQuery(sql);
            //rs.close();
            return rs;
        } catch(SQLException e) {
            //���ݿ�����ʧ���쳣����
            e.printStackTrace();  
            }catch (Exception e) {
            e.printStackTrace();
        }finally{
            System.out.println("���ݿ����ݳɹ���ȡ����");
        }
		return null;	
    }
    

    public void insert() throws SQLException{
    	PreparedStatement psql=null;
    	//Ԥ����������ݣ���������������--������
		psql = (PreparedStatement) con.prepareStatement("insert into Diagnose (empno,ename,job,hiredate,sal) "
    	        + "values(?,?,?,?,?)");
    	psql.setInt(1, 3214);              //���ò���1������idΪ3212������
    	psql.setString(2, "��С��");      //���ò���2��name Ϊ����
    	psql.setString(3, "�ܲ�����");
    	DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    	Date myDate2=null;
		try {
			myDate2 = (Date) dateFormat2.parse("2010-09-13");
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	psql.setDate(4,new java.sql.Date(myDate2.getTime()));
    	psql.setFloat(5, (float) 2000.3);		
    	psql.executeUpdate();
    }
	
    public void update() throws SQLException{
    	PreparedStatement psql=null;
    	//Ԥ������£��޸ģ����ݣ������յ�sal��Ϊ5000.0
		if(con==null)
			System.out.println("����ָ��Ϊ��");
		psql =(PreparedStatement) con.prepareStatement("update Diagnose set sal = ? where ename = ?");
		if(psql==null)
			System.out.println("psqlΪ�գ�");
		psql.setFloat(1, (float) 5000);	
    	psql.setString(2,"����");        
		psql.executeUpdate();        //ִ�и���
    }
    
    public void delete() throws SQLException{
    	PreparedStatement psql;
    	//Ԥ����ɾ������
    	psql = (PreparedStatement) con.prepareStatement("delete from Diagnose where sal > ?");
    	psql.setFloat(1, 4500);
    	psql.executeUpdate();
    }
	
    
    /**
     * �����������
     * @return ����ɹ��ļ�¼��
     */
    public int excuteBatch(){
    	try {
    		Statement sql;
    		//����������������ʧ�ܵ���䣬ʹ���ֶ��ύ����ķ�ʽ
			con.setAutoCommit(false);
	    	sql=(Statement) con.createStatement();
	    	sql.addBatch("insert into Diagnose (empno,ename,job,hiredate,sal) values('10','��ѫ','����','2010-09-13',1300)");
	    	sql.addBatch("insert into Diagnose (empno,ename,job,hiredate,sal) values('11','����','����','2010-09-13',1300)");
	    	sql.addBatch("insert into Diagnose (empno,ename,job,hiredate,sal) values('12','����','����','2010-09-13',1300)");
	    	sql.addBatch("insert into Diagnose (empno,ename,job,hiredate,sal) values('13','�Ź�','����','2010-09-13',1300)");
	    	sql.addBatch("insert into Diagnose (empno,ename,job,hiredate,sal) values('14','������','����','2010-09-13',1300)");
	    	int[] nums=sql.executeBatch();
	    	con.commit();
	    	sql.clearBatch();
	    	//�ָ������ύ
	    	con.setAutoCommit(true);
	    	//����ִ�е������
	    	return nums.length;
		} catch (SQLException e) {
			//������ʧ�ܾͻع�����
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return 0;
		}
    }
    
    public void close() throws SQLException
    {
        this.con.close();
    }
}
