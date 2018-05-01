package expert_sys.dao;

import java.sql.SQLException;
import java.util.List;

import expert_sys.dao.Diagnose;
import expert_sys.dao.Medicine;


public class TestDemo {

	public static void main(String[] args) throws SQLException {
		//test_getMedicineInfo();
		test_diagnoseProcess();
//		test_diagnose_getPrescriptionByIllId();
	}
	
	public static void  test_getMedicineInfo() throws SQLException
	{
		Diagnose diagnose=new Diagnose();
//		List<Medicine> medlist=diagnose.getMedicineInfo();
//		List<Medicine> medlist=diagnose.getMedicineInfoByTradeName("��ð");
//		List<Medicine> medlist=diagnose.getMedicineInfoByIllId(1);
		List<Medicine> medlist=diagnose.getMedicineByIllName("��ð");

		if(!medlist.isEmpty())
		{
			int i=0;
			Medicine m=null;
			for(i=0;i<medlist.size();i++)
			{
				m=medlist.get(i);
				System.out.println(m.toString());
			}
			System.out.printf("����ȡ��%d��ҩƷ����%n",i);
		}
	}
		
	public static void  test_diagnoseProcess() throws SQLException
	{
		Diagnose diagnose=new Diagnose();
		String[] symptoms={"����"};

		List<Diagnose> diagnosisList=diagnose.diagnoseProcess(symptoms);
		if(diagnosisList!=null && !diagnosisList.isEmpty())
		{
			for(int i=0;i<diagnosisList.size();i++)
			{
				System.out.println(diagnosisList.get(i));
			}
			System.out.printf("����ȡ��%d������id��%n",diagnosisList.size());
		}
		else
		{
			System.out.printf("�޷�������֪��Ϣ�жϼ��������");
			
		}
	
	}
	
	public static void  test_diagnose_getPrescriptionByIllId() throws SQLException
	{
		Diagnose diagnose=new Diagnose();
		List<Priscription> priscriptionList=diagnose.getPrescriptionByIllId(1);
		if(priscriptionList!=null && !priscriptionList.isEmpty())
		{
			for(int i=0;i<priscriptionList.size();i++)
			{
				System.out.println(priscriptionList.get(i).toString());
			}
			System.out.printf("����ȡ��%d��ҩ��id��%n",priscriptionList.size());
            for(int k=0;k<priscriptionList.size();k++)
            	System.out.printf("%s%n%n",priscriptionList.get(k).toString());
		}
		else
		{
			System.out.printf("%n�޷�������֪��Ϣ��ȡ��ҩ����%n");
		}
	
	}
		

}
