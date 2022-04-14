package Program;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener
{
	
	//Attributes
		JButton Button;
		JRadioButton Male;
		JRadioButton Female;
		ButtonGroup Gender;
		JRadioButton NoOwn;
		JRadioButton YesOwn;
		ButtonGroup OwnBusiness;
		JRadioButton NoPart;
		JRadioButton YesPart;
		ButtonGroup PartTime;
		JRadioButton Rural;
		JRadioButton Urban;
		ButtonGroup Region;
		JRadioButton NoStudy;
		JRadioButton YesStudy;
		ButtonGroup StudyBusiness;
		JLabel l1;
		JLabel l2;
		JLabel l3;
		JLabel l4;
		JLabel l5;
		JLabel Heading;
		JTextArea area;
		ArrayList<String> Person = new ArrayList<>();
		
		//TrainingData3.csv used as it is the most accurate after testing
		NaiveBayesTraining nbt = new NaiveBayesTraining(5, "TrainingData3.csv", 5, "Yes", "No");
		
		JPanel panel;
		//

		
		//GUI constructor
		GUI(String title)
		{
			super(title);
			setSize(700,700);
			setLayout(new FlowLayout());
			setVisible(true);
			
			//Initializing attributes
			Heading = new JLabel("Are you an entrepreneur test");
			Heading.setFont (Heading.getFont().deriveFont(64.0f));
			
			Button = new JButton("Save");
			
			l1 = new JLabel("Gender:");
			Male = new JRadioButton();
			Male.setText("Male");
			Male.setActionCommand("Male");
			Female = new JRadioButton();
			Female.setText("Female");
			Female.setActionCommand("Female");
			
			l2 = new JLabel("Does your parent/guardian own a business:");
			YesOwn = new JRadioButton();
			YesOwn.setText("Yes");
			YesOwn.setActionCommand("Yes");
			NoOwn = new JRadioButton();
			NoOwn.setText("No");
			NoOwn.setActionCommand("No");
			
			l3 = new JLabel("Do you have a part time job:");
			YesPart = new JRadioButton();
			YesPart.setText("Yes");
			YesPart.setActionCommand("Yes");
			NoPart = new JRadioButton();
			NoPart.setText("No");
			NoPart.setActionCommand("No");
			
			l4 = new JLabel("Do You live in an urban or rural reigion:");
			Urban = new JRadioButton();
			Urban.setText("Urban");
			Urban.setActionCommand("Urban");
			Rural = new JRadioButton();
			Rural.setText("Rural");
			Rural.setActionCommand("Rural");
			
			l5 = new JLabel("Do you study business");
			YesStudy = new JRadioButton();
			YesStudy.setText("Yes");
			YesStudy.setActionCommand("yes");
			NoStudy = new JRadioButton();
			NoStudy.setText("No");
			NoStudy.setActionCommand("No");
			
			Gender = new ButtonGroup();
			OwnBusiness = new ButtonGroup();
			PartTime = new ButtonGroup();
			Region = new ButtonGroup();
			StudyBusiness = new ButtonGroup();
			
			area = new JTextArea("Answer:");
			
			panel = new JPanel();
			//////
			
			//Setting layout to boxlayout
			BoxLayout bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
			panel.setLayout(bl);
			GridBagConstraints gc = new GridBagConstraints();
			/////
			
			//Adding attributes to panel 
			panel.add(Box.createVerticalStrut(25));
			panel.add(Heading);
			
			panel.add(Box.createVerticalStrut(100));
			panel.add(l1,gc);
			panel.add(Male,gc);
			panel.add(Female,gc);
			Gender.add(Male);
			Gender.add(Female);
			panel.add(Box.createVerticalStrut(25));
			
			panel.add(l2,gc);
			panel.add(YesOwn,gc);
			panel.add(NoOwn,gc);
			OwnBusiness.add(YesOwn);
			OwnBusiness.add(NoOwn);
			panel.add(Box.createVerticalStrut(25));
			
			panel.add(l3,gc);
			panel.add(YesPart,gc);
			panel.add( NoPart,gc);
			PartTime.add(YesPart);
			PartTime.add(NoPart);
			panel.add(Box.createVerticalStrut(25));
			
			panel.add(l4,gc);
			panel.add(Urban,gc);
			panel.add(Rural,gc);
			Region.add(Urban);
			Region.add(Rural);
			panel.add(Box.createVerticalStrut(25));
			
			panel.add(l5,gc);
			panel.add(YesStudy,gc);
			panel.add(NoStudy,gc);
			StudyBusiness.add(YesStudy);
			StudyBusiness.add(NoStudy);
			panel.add(Box.createVerticalStrut(25));


			panel.add(Box.createVerticalStrut(25));
			gc.anchor = GridBagConstraints.CENTER;
			panel.add(Button,gc);
			Button.addActionListener(this);
			
			panel.add(Box.createVerticalStrut(25));
			panel.add(area,gc);
			
			add(panel);
			///
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
	       
	        if (e.getSource().equals(Button))
	        {
	        	
	        	Person.clear();
	        	
	        	Person.add(getButton(Gender));
	        	Person.add(getButton(OwnBusiness));
	        	Person.add(getButton(PartTime));
	        	Person.add(getButton(Region));
	        	Person.add(getButton(StudyBusiness));
	        	
	        	for (int i = 0; i<Person.size(); i++)
	        	{
	        		if (Person.get(i).equals("null"))
	        		{
	        			JOptionPane.showMessageDialog(this,"Please select answer for all attributes");
	        			return;
	        		}
	        	}
	        	area.setText(nbt.guess(Person, nbt.probYes, nbt.probNo));
	        }
	        
		}
		
		String getButton(ButtonGroup group)
		{
			try
			{
				return group.getSelection().getActionCommand();
			}
			catch (Exception e)
			{
				System.out.println("in catch");
				return "null";
			}
		}
	
}
