import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SqlFunction2 {
//	//匯入資料
//	public void inputCsvToSql() {
//		int i1;//確認要匯入資料? 1.CSV，2.網路
//        Scanner sc1 = new Scanner(System.in);
//        System.out.println("確認要匯入資料方式? 1.電腦CSV檔案，2.網路:");
//        i1 = sc1.nextInt();
//        
//		if(i1==1) {
//			int i2;//確認要匯入資料? 1.確定，2.取消:
//	        Scanner sc2 = new Scanner(System.in);
//	        System.out.println("確認要匯入資料? 1.確定，2.取消:");
//	        i2 = sc2.nextInt();
//	        
//	        for(int i=0;i<1;) {
//	        	if (i2==2) { //2.取消
//	        		System.out.println("取消匯入");
//	        		i=1;
//	        		break; //跳出FOR迴圈
//	        	}	        
//			new Pm25CsvToSql().inputFromCsv();//從檔案匯入
//			i=1;//執行完跳出FOR迴圈
//	        }
//		}
//		if(i1==2) {
//			int i2;//確認要匯入資料? 1.確定，2.取消:
//	        Scanner sc2 = new Scanner(System.in);
//	        System.out.println("確認要匯入資料? 1.確定，2.取消:");
//	        i2 = sc2.nextInt();
//	        
//	        for(int i=0;i<1;) {
//	        	if (i2==2) { //2.取消
//	        		System.out.println("取消匯入");
//	        		i=1;
//	        		break; //跳出FOR迴圈
//	        	}	        
//			new Pm25CsvToSql().inputFromUrl();//從網路匯入
//			i=1;//執行完跳出FOR迴圈
//	        }
//		}
//	}	
		//匯入資料
		public void inputCsvToSql() {
			String s1;//確認要匯入方式? 1.CSV，2.網路				
	        Scanner sc1 = new Scanner(System.in);
	        System.out.println("確認要匯入資料方式? 1.CSV檔案，2.網路:");
	        s1 = sc1.next();
	        switch (s1) {
				case "1"://檔案匯入資料
					String si2;
			        Scanner sc2 = new Scanner(System.in);
			        System.out.println("確認要匯入資料? 1.確定，2.取消:");
			        si2 = sc2.next();
			        switch (si2) {
						case "1":
							new Pm25CsvToSql().inputFromCsv();//從檔案匯入
							break;
						case "2":
							System.out.println("取消操作，請重新執行");
							break;
						default:
							System.out.println("輸入錯誤，請重新輸入!!!");
							break;	//			       
			        }
					break;
				case "2"://網路匯入資料
					String si3;
			        Scanner sc3 = new Scanner(System.in);
			        System.out.println("確認要匯入資料? 1.確定，2.取消:");
			        si3 = sc3.next();
			        switch (si3) {
						case "1":
							new Pm25CsvToSql().inputFromUrl();//從網路匯入
							break;
						case "2":
							System.out.println("取消操作，請重新執行");
							break;
						default:
							System.out.println("輸入錯誤，請重新輸入!!!");
							break;	//			       
			        }
					break;
				default:
					System.out.println("輸入錯誤，請重新輸入!!!");
					break;				
			}
		}	
		//新增
		public void insertIntoSql() {
			int i1;
	    	String s1;
	    	String s2;
	    	String s3;
	    	int i2;
//	        int typeToInt=0;
//	        try {
//	        	//把使用者輸入的值轉成INT
//	        	typeToInt= Integer.valueOf(i1); }
//	        catch(NumberFormatException e){
//	        	typeToInt=-1;
//	        }
	        Scanner sc = new Scanner(System.in);	
	        System.out.println("請輸入編號以新增資料:");
	        i1 = sc.nextInt();
	        System.out.println("請輸入區域名稱以新增資料:");
	        s1 = sc.next();
	        System.out.println("請輸入縣市名稱以新增資料:");
	        s2 = sc.next();
	        System.out.println("請輸入日期以新增資料(ex:2020-09-09):");
	        s3 = sc.next();
	        System.out.println("請輸入PM25數值以新增資料:");
	        i2 = sc.nextInt();
	        
	        Pm25JDBCDAO pm25insertdao = new Pm25JDBCDAO();
	        List<Pm25> listPm25s = pm25insertdao.listPm25s();
	        int count=0;
	        	for(Pm25 list1:listPm25s) {	        		
//	        		System.out.println(list1);
	        		list1.setSiteid(i1);
	        		list1.setSitename(s1);
	        		list1.setCounty(s2);
	        		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	        		Date date = null;
					try {
						date = sdf.parse(s3);
						} catch (ParseException e) {
						e.printStackTrace();
					}
					list1.setMonitordate(date);
					list1.setConcentration(i2);
					if (true) {
						count++;
						pm25insertdao.insertIntoPm25(list1);
						break;
					}
	        	}	
				System.out.println("新增" + count + "筆資料完成");
		}		
		//查詢
		public void querySql() {
			String is1;
	        Scanner sc1 = new Scanner(System.in);	             
	        System.out.println("請輸入數值查詢各地區PM2.5大於該數值資料(15以下為良好指標):");
	        is1 = sc1.next();
	        
			    int count=0;    
			    Pm25JDBCDAO pm25querydao = new Pm25JDBCDAO();
			    List<Pm25> list2 = pm25querydao.listPm25s();
		        int typeToInt = 0;
				try {
		        	//把使用者輸入的值轉成INT
		        	typeToInt= Integer.valueOf(is1); }
		        catch(NumberFormatException e){
		        	System.out.println("輸入錯誤，請重新輸入!!!");		        	
		        }
				
			    for(Pm25 querylist:list2) {
			    	try {
			    		if (querylist.getConcentration()== typeToInt) {
					    	count++;  
			                System.out.println(querylist.getCounty()+",PM2.5超過"+ is1 +"μg/m3的區域: "+querylist.getSitename()+",編號:"+querylist.getSiteid()+ ",數值:"+ querylist.getConcentration()+"μg/m3" );
					        pm25querydao.updatePm25(querylist);
					        }
					} catch (Exception e) {
						// TODO: handle exception
					}
			      
			    }
			    System.out.println("總共有:"+count+"筆資料PM2.5數值大於"+is1);
		}	
		//更新
		public void updateSql() {
			String i1 = null; // 地區代號
			String i2 = null; // 確認取消
			int i3 = 0; // 欲修改數值
			int count = 0;
			for (int i = 0; i < 1;) {//為了讓查詢清單完可以重複查詢				
				Scanner sc = new Scanner(System.in);
				System.out.println("請輸入區域代號以用來修改PM2.5指標(ex:1.基隆2:汐止3.萬里):\n(若不知道代號請輸入Q查詢)");
				i1 = sc.next();
				if (i1.equals("Q")) {

					Pm25JDBCDAO pm25updatedao = new Pm25JDBCDAO();
					List<Pm25> list3 = pm25updatedao.listPm25s();
					ArrayList list1 = new ArrayList();
					for (Pm25 Updatelist : list3) {
						list1.add(Updatelist.getSiteid() + Updatelist.getSitename());
					}
					System.out.print(list1);
				} else {
					System.out.println("您輸入的值為" + i1 + "確認要修改? 1.確定，2.取消");
					i2 = sc.next();
					switch (i2) {
					case "1":// 確定
						try {
							System.out.println("請輸入數字修改PM2.5數值:");
							i3 = sc.nextInt();
							Pm25JDBCDAO pm25updatedao1 = new Pm25JDBCDAO();
							List<Pm25> list2 = pm25updatedao1.listPm25s();
							int typeToInt = 0;
							try {
								typeToInt = Integer.valueOf(i1);
							} // 把使用者輸入的值轉成INT
							catch (NumberFormatException e) {
								System.out.println("輸入錯誤，請重新輸入!!!");// 如果不是輸入數字把就顯示輸入錯誤
								i=1; //跳出最外面FOR迴圈
								break;
							}
							for (Pm25 Updatelist : list2) {
								if (Updatelist.getSiteid() == typeToInt) {
									count++;
									System.out.println("編號:" + Updatelist.getSiteid() + ",縣市:" + Updatelist.getCounty()
											+ ",地區:" + Updatelist.getSitename() + ",監測日期:" + Updatelist.getMonitordate()
											+ ",更新前汙染指數:" + Updatelist.getConcentration() + "μg/m3");
									Updatelist.setConcentration(i3);
									pm25updatedao1.updatePm25(Updatelist);
								}
							}
							System.out.println("總共更新" + count + "筆資料");
							i=1; //跳出最外面FOR迴圈
							break;
						} catch (InputMismatchException e) {
							System.out.println("輸入錯誤，請重新輸入!!!");
							i=1; //跳出最外面FOR迴圈
							break;
						}
					case "2":
						System.out.println("取消操作，請重新執行");
						i=1; //跳出最外面FOR迴圈
						break;
					default:
						System.out.println("輸入錯誤，請重新輸入!!");
						i=1; //跳出最外面FOR迴圈
						break;
					}
				}
			}
		}
		
		//刪除
		public void deleteSql() {
			String i1 = null;
			int i2;

	        for(int i = 0;i<1;) {
		        Scanner sc = new Scanner(System.in);	             
		        System.out.println("請輸入代號以刪除地區資料1.基隆2:汐止3.萬里\n(若不知道代號請輸入Q查詢)");
		        i1 = sc.next();
		        if(i1.equals("Q")) { //查詢所有編號
//			        System.out.println("輸入數字1查詢清單:");
//			        i3 = sc.nextInt();
			        Pm25JDBCDAO pm25 = new Pm25JDBCDAO();
			        List<Pm25> list3 = pm25.listPm25s();
			        ArrayList list1 =  new ArrayList();
			        for(Pm25 Updatelist:list3) {
			        	list1.add(Updatelist.getSiteid()+Updatelist.getSitename());
			        }
			        System.out.print(list1);
			        break;
		        }
		        System.out.println("確認要刪除? 1.確定，2.取消");
		        i2 = sc.nextInt();
		        if(i2==2) {
		        	i1=null; //代號設為0，否則跳出還是會刪除
		        	break;		        	
		        }
		        i=1;
	    	}    
	      int count=0;
	      Pm25JDBCDAO pm25deletedao = new Pm25JDBCDAO();
	      List<Pm25> list4 = pm25deletedao.listPm25s();
	      int typeToInt;
	      try {
	        	//把使用者輸入的值轉成INT
	        	typeToInt= Integer.valueOf(i1); }
	        catch(NumberFormatException e){
	        	typeToInt=-1;
	        }
	      for(Pm25 Deletelist:list4) {
		        if (Deletelist.getSiteid()== typeToInt) {
		        	count++;
		        	System.out.println("編號:"+Deletelist.getSiteid()+",縣市:"+Deletelist.getCounty()+",地區:"+Deletelist.getSitename()+",監測日期:"+Deletelist.getMonitordate()+",汙染指數:"+Deletelist.getConcentration()+"μg/m3");
		        	Deletelist.setConcentration(typeToInt);
		        	pm25deletedao.deletePm25(Deletelist);
		        }
	      }
	      System.out.println("總共刪除"+count+"筆資料");
		}	
		
	//匯出
	public void outputToCsv() throws IOException{	
		String i1;//確認要匯出資料? 1.確定，2.取消:
        Scanner sc = new Scanner(System.in);
        System.out.println("確認要匯出資料? 1.確定，2.取消:");
        i1 = sc.next();
        switch (i1) {
		case "1":
			new OracleToCsv().DBtoCsv();
			break;
		case "2":
			System.out.println("取消匯出");
			break;
		default:
			System.out.println("輸入錯誤，請重新輸入!!");
			break;        	
        }	

	}	
    
	   
}	
