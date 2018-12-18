/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaguiforfirebase;

/**
 *
 * @author Paoyimpae
 */
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import javax.swing.event.*;
import org.jfree.data.category.SlidingCategoryDataset;

public class LineChartDataTemp extends JFrame implements ChangeListener {
    
    JScrollBar scroller;
    SlidingCategoryDataset t;
    int dataCount = 0;

   public LineChartDataTemp( String applicationTitle , String chartTitle ) {
      super(applicationTitle);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LineChartDataTemp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LineChartDataTemp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LineChartDataTemp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LineChartDataTemp.class.getName()).log(Level.SEVERE, null, ex);
        }
      this.setLayout(new BorderLayout());
      this.getContentPane().setBackground(new java.awt.Color(34, 34, 34));
      this.setSize(850, 650);
//      this.setResizable(false);
      
      t = createDataset();
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Date (YYYYMMDD)","Average in Date (Degree Celsius)",
         t,
         PlotOrientation.VERTICAL,
         true,true,false);
      
      
      lineChart.setBackgroundPaint(new java.awt.Color(34, 34, 34));
      lineChart.getTitle().setPaint(new java.awt.Color(255, 255, 255));
      lineChart.getTitle().setFont(new java.awt.Font("RSU", 0, 24));
      lineChart.getPlot().setBackgroundPaint(new java.awt.Color(100, 100, 100));
      lineChart.getCategoryPlot().getRenderer().setBaseItemLabelPaint(new java.awt.Color(0, 0, 255));
      lineChart.getCategoryPlot().getDomainAxis().setLabelPaint(new java.awt.Color(255, 255, 255));
      lineChart.getCategoryPlot().getDomainAxis().setLabelFont(new java.awt.Font("RSU", 0, 18));
      lineChart.getCategoryPlot().getDomainAxis().setTickLabelPaint(new java.awt.Color(255, 255, 255));
      lineChart.getCategoryPlot().getDomainAxis().setTickLabelFont(new java.awt.Font("RSU", 0, 18));
      lineChart.getCategoryPlot().getRangeAxis().setLabelPaint(new java.awt.Color(255, 255, 255));
      lineChart.getCategoryPlot().getRangeAxis().setLabelFont(new java.awt.Font("RSU", 0, 18));
      lineChart.getCategoryPlot().getRangeAxis().setTickLabelPaint(new java.awt.Color(255, 255, 255));
      lineChart.getCategoryPlot().getRangeAxis().setTickLabelFont(new java.awt.Font("RSU", 0, 18));
      lineChart.getCategoryPlot().getRenderer().setSeriesPaint(0, new java.awt.Color(249, 124, 220));
     // System.out.println(lineChart.);
      
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 800 , 550 ) );
      
      chartPanel.setBackground( new java.awt.Color(0, 0, 255) );
      
      this.add(chartPanel, BorderLayout.CENTER);
      
      if (dataCount > 5){
        this.scroller = new JScrollBar(SwingConstants.HORIZONTAL, 0, 5, 0, dataCount);
        this.scroller.getModel().addChangeListener(this);
        this.add(this.scroller, BorderLayout.PAGE_END);
      }
     
      this.setVisible(true);
   }

   private SlidingCategoryDataset createDataset( ) {
      DefaultCategoryDataset dataset2 = new DefaultCategoryDataset( );
      // SecurityUtil.setUp();
      DataServices.initialTempData();
      DataServices.waiting();
      for(HistoryData data: DataServices.getTemperatureData()) {
          dataset2.addValue( data.getAverage(), "Temperature", data.getDate() );
      }
      dataCount = DataServices.getTemperatureData().size();
      DataServices.turnOff();
      SlidingCategoryDataset t = new SlidingCategoryDataset(dataset2, 0, 5);
      return t;
   }
   
   public static void main( String[ ] args ) {
      LineChartDataTemp chart = new LineChartDataTemp(
         "Temperature Average Value Analysis" ,
         "Summary the Average Value of Temperature");
     
   }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.t.setFirstCategoryIndex(this.scroller.getValue());
    }


}
