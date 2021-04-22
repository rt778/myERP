package interfaceManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.*;
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;


public class DatePicker {
	
	public static String selectedDate;
	
	
	public JDatePickerImpl showDatePicker() {
	   
	        
	      UtilDateModel model = new UtilDateModel();
	    
	      Properties p = new Properties();
	      p.put("text.today", "Today");
	      p.put("text.month", "Month");
	      p.put("text.year", "Year");
	      JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
	      JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
	      
	      datePicker.addActionListener(new ActionListener() {

				//@Override
				public void actionPerformed(ActionEvent e) {
					selectedDate = datePicker.getJFormattedTextField().getText();
				}
			});
	      
	      return datePicker;
	      
	 
	        
	    }
	
	public class DateLabelFormatter extends AbstractFormatter {

		private static final long serialVersionUID = 1L;
		private String datePattern = "yyyy-MM-dd";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }

	}
	

	public static void main(String[] args) {
		
		JFrame f1 = new JFrame();
	    f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f1.setSize(300, 300);
		
		DatePicker d1 = new DatePicker(); // create an instance of DatePicker 
        f1.add(d1.showDatePicker()); // add the instance to the frame
        
        
	    f1.pack(); // adjust the size of the frame to the date
	    f1.setVisible(true);
	    
	  
	}


}
