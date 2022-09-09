import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.math.BigDecimal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class project_reverse {

	protected Shell shell;
	private Text inputNumber;
	private Text result;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			project_reverse window = new project_reverse();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(36, 57, 61, 17);
		label.setText("请输入数字:");
		
		inputNumber = new Text(shell, SWT.BORDER);
		inputNumber.setBounds(129, 54, 178, 23);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(36, 147, 61, 17);
		label_1.setText("翻转结果:");
		
		result = new Text(shell, SWT.BORDER);
		result.setBounds(129, 141, 178, 23);
		
		Button button = new Button(shell, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			private String num;
			@Override
			public void mouseUp(MouseEvent e) {
				num = inputNumber.getText();
				if(!num.matches("[-]?[0-9]*[.]?[0-9]*")) {
					result.setText("你输入的不是数字");
				}else {
					BigDecimal inputNum = new BigDecimal(num);
					inputNum = inputNum.stripTrailingZeros();
					result.setText(reverse(inputNum).toString());
				}
			}
			private BigDecimal reverse(BigDecimal x) {	
				BigDecimal result = new BigDecimal("0");
				String str = x.toString();
				String[] strs = str.split("\\.");
				
				
				//正负号判断
				boolean sign;
				if(str.charAt(0) == '-') {
					sign = true;
					str = str.substring(1,str.length());
				}else {
					sign = false;
				}
				
				
				if(strs.length == 1) {
					String str1 = str.split("\\.")[0];
					if(sign) {
						result = new BigDecimal("-" + reverse(str1));
					}else {
						result = new BigDecimal(reverse(str1));
					}
					
				}else if(strs.length == 2) {
					String str1 = str.split("\\.")[0];
					String str2 = str.split("\\.")[1];
					if(sign) {
						result = new BigDecimal("-" + reverse(str1) + "." + reverse(str2));
					}else {
						result = new BigDecimal(reverse(str1) + "." + reverse(str2));
					}
					
				}
				
				return result;
			}
			
			private String reverse(String str) {
				StringBuilder sb = new StringBuilder(str);
				sb = sb.reverse();
				return sb.toString();
			}
		});
		button.setText("转换");
		button.setBounds(129, 97, 42, 27);
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				inputNumber.setText("");
				result.setText("");
			}
		});
		button_1.setText("清空");
		button_1.setBounds(265, 97, 42, 27);

	}
}
