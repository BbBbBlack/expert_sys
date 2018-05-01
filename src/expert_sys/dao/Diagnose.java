package expert_sys.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import expert_sys.db.DBHelper;


public class Diagnose {
	/**
	 * ������
	 */
	private int id;
	/**
	 * ֢״��ż���
	 */
	private String symptom_ids;
	/**
	 * ����
	 */
	private Illness illness;
	/**
	 * ���Ŷ�
	 */
	private float confidence;
	
	/**
	 * ���׼ȷ��
	 */
	private float accuracy_rate;
	
	@Override
	public String toString() {
		return "Diagnose [id=" + id + ", symptom_ids=" + symptom_ids + ", illness=" + illness + ", confidence="
				+ confidence + ",accuracy_rate="+accuracy_rate +  "]";
	}

	public Diagnose(int id, String symptom_ids, Illness illness, float confidence) {
		super();
		this.id = id;
		this.symptom_ids = symptom_ids;
		this.illness = illness;
		this.confidence = confidence;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSymptom_ids() {
		return symptom_ids;
	}

	public void setSymptom_ids(String symptom_ids) {
		this.symptom_ids = symptom_ids;
	}

	public Illness getIllness() {
		return illness;
	}

	public void setIllness(Illness illness) {
		this.illness = illness;
	}

	public float getConfidence() {
		return confidence;
	}

	public void setConfidence(float confidence) {
		this.confidence = confidence;
	}


	public float getAccuracy_rate() {
		return accuracy_rate;
	}

	public void setAccuracy_rate(float accuracy_rate) {
		this.accuracy_rate = accuracy_rate;
	}

	/**
	 * ��ȡȫ��ҩƷ��Ϣ
	 * @return ȫ��ҩƷ��Ϣ����
	 * @throws SQLException
	 */
	public  List<Medicine> getMedicineInfo() throws SQLException
	{
        System.out.println("-----------------");
        System.out.println("ִ�н��������ʾ:");  
        System.out.println("-----------------");  
        System.out.println("id" + "\t" + "��Ʒ��" + "\t" + "��������" + "\t" + "����");  
        System.out.println("-----------------");  
        
	    DBHelper dbhelper=new DBHelper();
		String sqlS="SELECT * FROM view_medicine";
        ResultSet rs=dbhelper.query2(sqlS);
        List<Medicine> medlist=null;
        if(rs!=null)
        {	
        	medlist=new ArrayList<Medicine>();
            while(rs.next()){
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
	    DBHelper dbhelper=new DBHelper();
	    String sqlS=String.format("SELECT * FROM view_medicine WHERE tradename LIKE '%%%s%%' ",tradeName.trim());
		System.out.println(sqlS);
        ResultSet rs=dbhelper.query2(sqlS);
        List<Medicine> medlist=null;
        if(rs!=null)
        {	
        	medlist=new ArrayList<Medicine>();
            while(rs.next()){
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
	 * ���ݼ���id���ҩƷ��Ϣ
	 * @return ����ҩƷ��Ϣ
	 * @throws SQLException
	 */
	public  List<Medicine> getMedicinesByIllId(int illId) throws SQLException
	{
		System.out.println("��ʼ���ݼ���id����ҩƷ�б�...");  
	    DBHelper dbhelper=new DBHelper();
		String sqlS=String.format("SELECT * from view_priscription_en WHERE illness_id = %d", illId);
        ResultSet rs=dbhelper.query2(sqlS);
        List<Medicine> medlist=null;
        if(rs!=null)
        {	
        	medlist=new ArrayList<Medicine>();
            while(rs.next()){
                medlist.add(new Medicine(rs.getInt("medicine_id"),rs.getString("registration_numbers"), rs.getString("tradename"),rs.getString("generic_name"),
                		rs.getString("functions"),rs.getString("adverse_reaction"), rs.getString("notice"),rs.getString("traits"),rs.getString("norms"), 
                		rs.getString("imgurl"),rs.getString("manufacturers"),rs.getString("preparation"), rs.getString("packaging"), 
                		rs.getString("ingredient"),	rs.getString("dosage"), rs.getString("category"), rs.getDate("registrate_date")));
            }
            System.out.println(medlist.toString());
            System.out.println("�ɹ���ȡҩƷ�б�...");
        }

        rs.close();
	    dbhelper.close();
	    return medlist;
	}
	
	/**
	 * ���ݼ���id��ö������
	 * @return ���д�����Ϣ
	 * @throws SQLException
	 */
	public  List<Priscription> getPrescriptionByIllId(int illId) throws SQLException
	{
		System.out.println("��ʼ���ݼ���id���Ҽ���...");  
		this.illness=Illness.getIllnessById(illId);
		System.out.println("�ɹ����ҵ��ü�������:"+this.illness.getIllnessname());  
		
		System.out.println();
		System.out.println("��ʼ���ݼ���id����ҩ��...");  
	    DBHelper dbhelper=new DBHelper();
		String sqlS=String.format("SELECT * from view_priscription_en WHERE illness_id = %d", illId);
        ResultSet rs=dbhelper.query2(sqlS);
        List<Priscription> priscriptionlist=null;
        List<Integer> pris_ids=new ArrayList<Integer>();
        if(rs!=null)
        {	
        	
        	priscriptionlist = new ArrayList<Priscription>();    
        	while(rs.next())
        	{
        		if(!pris_ids.contains(rs.getInt("prescription_id")))
        		{
        			pris_ids.add(rs.getInt("prescription_id"));
            		//�����µ�ҩ����Ϣ
            		List<Medicine> medlist=new ArrayList<Medicine>();
            		Priscription priscription=new Priscription(rs.getInt("prescription_id"), this.illness, medlist);  		
            		priscriptionlist.add(priscription); 
        		}
        	}
        	
        	rs.beforeFirst();      	
            while(rs.next()){            	
        		//���ҩ����Ϣ�Ѿ����ڣ�Ϊҩ������ҩƷ
            	Medicine medicine=new Medicine(rs.getInt("medicine_id"),rs.getString("registration_numbers"), rs.getString("tradename"),rs.getString("generic_name"),
                		rs.getString("functions"),rs.getString("adverse_reaction"), rs.getString("notice"),rs.getString("traits"),rs.getString("norms"), 
                		rs.getString("imgurl"),rs.getString("manufacturers"),rs.getString("preparation"), rs.getString("packaging"), 
                		rs.getString("ingredient"),	rs.getString("dosage"), rs.getString("category"), rs.getDate("registrate_date"));
                for(int i=0;i<priscriptionlist.size();i++)
                {
                	if(priscriptionlist.get(i).prescription_id==rs.getInt("prescription_id"))
                	{
                		System.out.printf("ҩ��%d����ҩƷ%d:%n",priscriptionlist.get(i).prescription_id,medicine.getId());
                		priscriptionlist.get(i).medicinelist.add(medicine);
                	}
                }
            }
            System.out.printf("�ɹ���ȡ%d��ҩ��...%n",priscriptionlist.size());
        }

        rs.close();
	    dbhelper.close();

		return priscriptionlist;
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
	/**
	 * ���ݼ������ƴ���ƥ�䴦����Ϣ
	 * @param illName ��������
	 * @return ������Ӧ��ҩƷ��Ϣ
	 * @throws SQLException
	 */
	public  List<Medicine> getMedicineByIllName(String illName) throws SQLException
	{
	    DBHelper dbhelper=new DBHelper();
		String sqlS=String.format("SELECT * FROM view_priscription_en WHERE illnessname LIKE '%%%s%%' ",illName.trim());
        ResultSet rs=dbhelper.query2(sqlS);
        List<Medicine> medlist=null;
        if(rs!=null)
        {	
        	medlist=new ArrayList<Medicine>();
            while(rs.next()){
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
	 * ����֢״������ϣ������ؿ��ܵļ������
	 * @param ֢״�ַ�������
	 * @return ���ܵļ������
	 * @throws SQLException 
	 */
	public List<Diagnose> diagnoseProcess(String[] symptoms) throws SQLException
	{
	    DBHelper dbhelper=new DBHelper();
		System.out.println("�����֢״Ϊ��");
		for(int k=0;k<symptoms.length;k++)
			System.out.print(symptoms[k]+"   ");
		
		System.out.println();
		System.out.println();
	    //�����ݿ����ҳ���׼֢״�ؼ��ʶ�Ӧ��id
	    System.out.println("�����ݿ����ҳ���׼֢״�ؼ��ʶ�Ӧ��id...");
	    List<Symptom> symptomList=new ArrayList<Symptom>();
	    ResultSet rs1=null;
	    for(int j=0;j<symptoms.length;j++)
	    {
	    	String sqlSymp=String.format("SELECT * FROM t_symptom WHERE symptom LIKE '%%%s%%' ", symptoms[j].trim());
	    	System.out.println(sqlSymp);
	    	rs1=dbhelper.query2(sqlSymp);
	    	if(rs1!=null)
	    	{
		    	while(rs1.next())
		    	{
		    		Symptom symp=new Symptom(rs1.getInt("id"), rs1.getString("symptom"),rs1.getString("remark"));
		    		if(!symptomList.contains(symp))
		    			{
		    				symptomList.add(symp);
		    				System.out.println(symp.toString());
		    			}
		    	}
	    	}
	    	else
	    		continue;
	    }
	    
	    //�����ݿ����ҳ����������֢״�ļ���
	    System.out.println("�����ݿ����ҳ����������֢״�ļ���...");
	    List<Diagnose> diagnosisList=new ArrayList<Diagnose>();
	    ResultSet rs=null;
	    String sqlS="";
	    for(int i=0;i<symptomList.size();i++)
	    {
	    	if(i==0)
	    		sqlS=String.format("SELECT * FROM view_diagnose WHERE symptoms LIKE '%%#%d#%%'",symptomList.get(i).getId());
	    	else 
	    		sqlS+=String.format("OR symptoms LIKE '%%#%d#%%'",symptomList.get(i).getId());
	    }
	    System.out.println(sqlS);
	    if(sqlS!="" && sqlS != null)
	    	rs=dbhelper.query2(sqlS);
	    if(rs!=null)
        {	
            while(rs.next()){
            	Illness ill=new Illness(rs.getInt("illness_id"), rs.getString("illnessname"), rs.getString("division"), 
            			rs.getString("prescription_cn"),rs.getInt("level"));
            	Diagnose diag=new Diagnose(rs.getInt("id"), rs.getString("symptoms"), ill, rs.getFloat("confidence"));
            	
            	//�������׼ȷ��
            	System.out.println("��ʼ�������׼ȷ��...");
            	String[] sympotomArray=rs.getString("symptoms").split(";");
            	List<String> symptoms_in_db=new ArrayList<String>();
            	
            	for(int i=0;i<sympotomArray.length;i++)
            		if(sympotomArray[i]!=null && sympotomArray[i]!="")
            		{
            			symptoms_in_db.add(sympotomArray[i].replace("#", ""));	
            		}
            			
            	float  weight_sum=0.0000f;
            	float weight_average=0.0000f;
            	if(!symptoms_in_db.isEmpty())
            		weight_average=(float)1/symptoms_in_db.size();
            	System.out.println("weight_average="+weight_average);
            	for(int k=0;k<symptomList.size();k++)
            	{
            		if(symptoms_in_db.contains(Integer.toString(symptomList.get(k).getId())))
            		{
            			weight_sum+=weight_average;
            		}
            	}
            	System.out.println("weight_sum="+weight_sum);
            	
            	diag.setAccuracy_rate((diag.confidence*weight_sum));
            	if(!diagnosisList.contains(diag))
            		diagnosisList.add(diag);
            }
            rs.close();
        }
	    dbhelper.close();

	    if(diagnosisList.size()<=0)
	    	return null;
	    else
	    	return diagnosisList;
	}

	public Diagnose() {
		super();
	}


}
