package expert_sys.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import expert_sys.dao.Diagnose;
import expert_sys.dao.Illness;
import expert_sys.dao.Medicine;
import expert_sys.dao.Priscription;
import expert_sys.db.DBHelper;

/**
 * @author yu
 * @date ����ʱ�䣺2017��4��10�� ����10:04:58 
 */
public class DiagnoseService {

	public List<Diagnose> getDiagnoseList(String[] symptoms) throws SQLException{
		Diagnose diagnose=new Diagnose();
//		String[] symptoms={"��ð","����"};

		List<Diagnose> diagnosisList=diagnose.diagnoseProcess(symptoms);
		
//		if(diagnosisList!=null && !diagnosisList.isEmpty())
//		{
//			for(int i=0;i<diagnosisList.size();i++)
//			{
//				System.out.println(diagnosisList.get(i));
//			}
//			System.out.printf("����ȡ��%d������id��%n",diagnosisList.size());
//		}
//		else
//		{
//			System.out.printf("�޷�������֪��Ϣ�жϼ��������");
//			
//		}
		return diagnosisList;
	}
	
	/**
	 * ���ݼ���id��ô�����Ϣ
	 * @return ������Ӧ��ҩƷ��Ϣ
	 * @throws SQLException
	 */
	public  List<Medicine> getPrescriptionByIllId(int illId) throws SQLException
	{
	    DBHelper dbhelper=new DBHelper();
		String sqlS=String.format("SELECT * from view_priscription_en WHERE illness_id = %d", illId);
        ResultSet rs=dbhelper.query2(sqlS);
        List<Medicine> medlist=null;
        if(rs!=null)
        {	
        	medlist=new ArrayList<Medicine>();
            while(rs.next()){
/*                String tradename = null;
                String id = null;
                String functions = null;
                String cat = null;
		         id = rs.getString("id");
		         tradename= rs.getString("tradename");
		         functions= rs.getString("functions");
		         cat= rs.getString("category");
                //������
                System.out.println(id + "\t" + tradename + "\t" + functions + cat);*/
            	Medicine medicine = new Medicine(rs.getString("registration_numbers"), rs.getString("tradename"),rs.getString("generic_name"),
                		rs.getString("functions"),rs.getString("adverse_reaction"), rs.getString("notice"),rs.getString("traits"),rs.getString("norms"), 
                		rs.getString("imgurl"),rs.getString("manufacturers"),rs.getString("preparation"), rs.getString("packaging"), 
                		rs.getString("ingredient"),	rs.getString("dosage"), rs.getString("category"), rs.getDate("registrate_date"));
            	medicine.setId(rs.getInt("medicine_id"));
                medlist.add(medicine);
            }
        }

        rs.close();
	    dbhelper.close();
	    return medlist;
	}
	
	/**
	 * ��ȡ��ҩƷ���ؼ�����ص�ҩƷ��Ϣ����
	 * @param medicineId ҩƷId
	 * @return ���ҩƷ��Ϣ����
	 * @throws SQLException
	 */
	public  List<Medicine> getMedicineInfoByMedicineId(int medicineId) throws SQLException
	{
        System.out.println("-----------------");
        System.out.println("ִ�н��������ʾ:");  
        System.out.println("-----------------");  
        System.out.println("id" + "\t" + "��Ʒ��" + "\t" + "��������" + "\t" + "����");  
        System.out.println("-----------------");  
        
	    DBHelper dbhelper=new DBHelper();
	    String sqlS=String.format("SELECT * FROM view_medicine WHERE id = %d ",medicineId);
		System.out.println(sqlS);
        ResultSet rs=dbhelper.query2(sqlS);
        List<Medicine> medlist=null;
        if(rs!=null)
        {	
        	medlist=new ArrayList<Medicine>();
            while(rs.next()){
/*                String tradename = null;
                String id = null;
                String functions = null;
                String cat = null;
                 id = rs.getString("id");
                 tradename= rs.getString("tradename");
                 functions= rs.getString("functions");
                 cat= rs.getString("category");
                //������
                System.out.println(id + "\t" + tradename + "\t" + functions + cat);*/

                medlist.add(new Medicine(rs.getString("registration_numbers"), rs.getString("tradename"),rs.getString("generic_name"),
                		rs.getString("functions"),rs.getString("adverse_reaction"), rs.getString("notice"),rs.getString("traits"),rs.getString("norms"), 
                		rs.getString("imgurl"),rs.getString("manufacturers"),rs.getString("preparation"), rs.getString("packaging"), 
                		rs.getString("ingredient"),	rs.getString("dosage"), rs.getString("category"), rs.getDate("registrate_date")));
            }
        }

        rs.close();
	    dbhelper.close();
	    return medlist;
	}
	
	/**
	 * ��ȡ��ҩƷ���ؼ�����ص�ҩƷ��Ϣ����
	 * @param tradeName ҩƷ��Ʒ��
	 * @return ���ҩƷ��Ϣ����
	 * @throws SQLException
	 */
	public  List<Medicine> getMedicineInfoByTradeName(String tradeName) throws SQLException
	{
        System.out.println("-----------------");
        System.out.println("ִ�н��������ʾ:");  
        System.out.println("-----------------");  
        System.out.println("id" + "\t" + "��Ʒ��" + "\t" + "��������" + "\t" + "����");  
        System.out.println("-----------------");  
        
	    DBHelper dbhelper=new DBHelper();
	    String sqlS=String.format("SELECT * FROM view_medicine WHERE tradename LIKE '%%%s%%' ",tradeName.trim());
		System.out.println(sqlS);
        ResultSet rs=dbhelper.query2(sqlS);
        List<Medicine> medlist=null;
        if(rs!=null)
        {	
        	medlist=new ArrayList<Medicine>();
            while(rs.next()){
/*                String tradename = null;
                String id = null;
                String functions = null;
                String cat = null;
                 id = rs.getString("id");
                 tradename= rs.getString("tradename");
                 functions= rs.getString("functions");
                 cat= rs.getString("category");
                //������
                System.out.println(id + "\t" + tradename + "\t" + functions + cat);*/
            	Medicine medicine = new Medicine(rs.getString("registration_numbers"), rs.getString("tradename"),rs.getString("generic_name"),
                		rs.getString("functions"),rs.getString("adverse_reaction"), rs.getString("notice"),rs.getString("traits"),rs.getString("norms"), 
                		rs.getString("imgurl"),rs.getString("manufacturers"),rs.getString("preparation"), rs.getString("packaging"), 
                		rs.getString("ingredient"),	rs.getString("dosage"), rs.getString("category"), rs.getDate("registrate_date"));
            	medicine.setId(rs.getInt("id"));
                medlist.add(medicine);
            }
        }

        rs.close();
	    dbhelper.close();
	    return medlist;
	}
	
	/**
	 * ���ݼ������󷵻�ҩ���б�
	 * @param ��������
	 * @return ҩ���б�
	 * @throws SQLException
	 */
	public  List<Priscription> getPrescriptionByIllness(Illness ill) throws SQLException
	{
		System.out.println("��ʼ���ݼ�������ҩ��...");  
	    DBHelper dbhelper=new DBHelper();
		String sqlS=String.format("SELECT * from view_priscription_en WHERE illness_id = %d",  ill.getId());
        ResultSet rs=dbhelper.query2(sqlS);
        List<Priscription> priscriptionlist=null;
        List<Medicine> medlist=null;
        List<Integer> pris_ids=new ArrayList<Integer>();
        if(rs!=null)
        {	
        	priscriptionlist = new ArrayList<Priscription>();       	
            while(rs.next()){
            	if(pris_ids.contains(rs.getInt("prescription_id")))
            	{
            		//���ҩ����Ϣ�Ѿ����ڣ�Ϊҩ������ҩƷ
                	Medicine medicine=new Medicine(rs.getString("registration_numbers"), rs.getString("tradename"),rs.getString("generic_name"),
                    		rs.getString("functions"),rs.getString("adverse_reaction"), rs.getString("notice"),rs.getString("traits"),rs.getString("norms"), 
                    		rs.getString("imgurl"),rs.getString("manufacturers"),rs.getString("preparation"), rs.getString("packaging"), 
                    		rs.getString("ingredient"),	rs.getString("dosage"), rs.getString("category"), rs.getDate("registrate_date"));
                	medicine.setId(rs.getInt("medicine_id"));
                	medlist.add(medicine);
            	}
            	else
            	{ 
            		//�����µ�ҩ����Ϣ
            		medlist=new ArrayList<Medicine>();
            		Priscription priscription=new Priscription(rs.getInt("prescription_id"), ill, medlist);
            		pris_ids.add(rs.getInt("prescription_id"));
            		priscriptionlist.add(priscription);
            	}

            }
            System.out.println(priscriptionlist.toString());
            System.out.println("�ɹ���ȡҩ��...");
        }

        rs.close();
	    dbhelper.close();

		return priscriptionlist;
	}
}
