

package javaguiforfirebase;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.jfree.data.category.DefaultCategoryDataset;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Paoyimpae
 */
public class DataUtil {
    private String date;
    private double temperature;
    private double smoke;
    
    public DataUtil(String date, double temperature, double smoke) {
        this.date = date;
        this.temperature = temperature;
        this.smoke = smoke;
    }

    public DataUtil() {
         //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getSmoke() {
        return smoke;
    }

    public void setSmoke(double smoke) {
        this.smoke = smoke;
    }
    
    public DefaultCategoryDataset getValue(int value) {
      ArrayList<DataUtil> group = new ArrayList<DataUtil>();
      
      DataUtil datas;
      String[] dataset = new String[3];
      SecurityUtil.setUp();
      try {
          DataServices.initialTempData();
          TimeUnit.SECONDS.sleep(15);
      } catch (InterruptedException ex) {
          ex.printStackTrace();
      }

      for (HistoryData data: DataServices.getTemperatureData()) {
          dataset[0] = data.getDate();
          dataset[1] = String.valueOf(data.getAverage());
          System.out.println(dataset[0] + " " + dataset[1]);
      }

      try {
          DataServices.initialSmokeData();
          TimeUnit.SECONDS.sleep(15);
      } catch (InterruptedException ex) {
          ex.printStackTrace();
      }
      for (HistoryData data: DataServices.getSmokeData()) {
          dataset[2] = String.valueOf(data.getAverage()); 
          System.out.println(" " + dataset[1]);
      }
      
      

//      datas = new DataUtil(dataset[0], Double.parseDouble(dataset[1]), Double.parseDouble(dataset[2]));
       
      // System.out.println(datas.getDate() + " " +  datas.getTemperature() + " " + datas.getSmoke()); 
  //    group.add(datas);
      // }
      if (value == 1) {
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset( );
        for (int i=0; i<group.size(); i++) {
          dataset2.addValue( Float.parseFloat(dataset[1]), "Temperature", dataset[2] );
        }
      }
      else if (value == 2) {
        DefaultCategoryDataset dataset3 = new DefaultCategoryDataset( );
        for (int i=0; i<group.size(); i++) {
          dataset3.addValue( group.get(i).getSmoke(), "Smoke", group.get(i).getDate() );
        }
      }
      return null;
    }
    
}
